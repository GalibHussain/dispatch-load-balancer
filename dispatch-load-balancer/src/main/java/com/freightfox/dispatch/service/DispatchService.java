
package com.freightfox.dispatch.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freightfox.dispatch.dto.ApiResponse;
import com.freightfox.dispatch.dto.AssignedOrderResponse;
import com.freightfox.dispatch.dto.DispatchPlanResponse;
import com.freightfox.dispatch.dto.OrderRequest;
import com.freightfox.dispatch.dto.VehicleDispatchResponse;
import com.freightfox.dispatch.dto.VehicleRequest;

import com.freightfox.dispatch.entity.DeliveryOrder;
import com.freightfox.dispatch.entity.Vehicle;

import com.freightfox.dispatch.enums.Priority;

import com.freightfox.dispatch.repository.DeliveryOrderRepository;
import com.freightfox.dispatch.repository.VehicleRepository;

import com.freightfox.dispatch.util.DistanceCalculator;

@Service
public class DispatchService {

    private final DeliveryOrderRepository orderRepository;
    private final VehicleRepository vehicleRepository;

    public DispatchService(
            DeliveryOrderRepository orderRepository,
            VehicleRepository vehicleRepository) {

        this.orderRepository = orderRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    public ApiResponse acceptOrders(OrderRequest request) {

        if (request == null || request.getOrders() == null
                || request.getOrders().isEmpty()) {

            throw new IllegalArgumentException(
                    "Orders list cannot be empty.");
        }

        orderRepository.saveAll(request.getOrders());

        return new ApiResponse(
                "Delivery orders accepted.", "success");
    }

    @Transactional
    public ApiResponse acceptVehicles(VehicleRequest request) {

        if (request == null || request.getVehicles() == null
                || request.getVehicles().isEmpty()) {

            throw new IllegalArgumentException(
                    "Vehicles list cannot be empty.");
        }

        vehicleRepository.saveAll(request.getVehicles());

        return new ApiResponse(
                "Vehicle details accepted.", "success");
    }

    @Transactional(readOnly = true)
    public DispatchPlanResponse getDispatchPlan() {

        List<DeliveryOrder> orders = orderRepository.findAll();
        List<Vehicle> vehicles = vehicleRepository.findAll();

        // Sort orders by priority: HIGH, MEDIUM, LOW
        orders.sort(
                Comparator.comparingInt(
                        order -> getPriorityRank(order.getPriority())
                )
        );

        // Initialize vehicle states
        List<VehicleState> states = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            states.add(new VehicleState(vehicle));
        }

        List<String> unassignedOrders = new ArrayList<>();

        // Assign orders to vehicles
        for (DeliveryOrder order : orders) {

            VehicleState bestVehicle = null;
            double minimumDistance = Double.MAX_VALUE;

            for (VehicleState state : states) {

                BigDecimal newLoad =
                        state.totalLoad.add(order.getPackageWeight());

                // Check vehicle capacity
                if (newLoad.compareTo(
                        state.vehicle.getCapacity()) > 0) {
                    continue;
                }

                // Calculate distance
                double distance =
                        DistanceCalculator.calculateDistance(
                                state.currentLatitude,
                                state.currentLongitude,
                                order.getLatitude(),
                                order.getLongitude()
                        );

                // Select nearest available vehicle
                if (distance < minimumDistance) {
                    minimumDistance = distance;
                    bestVehicle = state;
                }
            }

            // If no vehicle is available, record the order
            if (bestVehicle == null) {
                unassignedOrders.add(order.getOrderId());
                continue;
            }

            // Update vehicle load and distance
            bestVehicle.totalLoad =
                    bestVehicle.totalLoad.add(
                            order.getPackageWeight());

            bestVehicle.totalDistance += minimumDistance;

            bestVehicle.currentLatitude = order.getLatitude();
            bestVehicle.currentLongitude = order.getLongitude();

            // Add assigned order
            bestVehicle.assignedOrders.add(
                    new AssignedOrderResponse(
                            order.getOrderId(),
                            order.getLatitude(),
                            order.getLongitude(),
                            order.getAddress(),
                            order.getPackageWeight(),
                            order.getPriority()
                    )
            );
        }

        // Prepare dispatch response
        List<VehicleDispatchResponse> result = new ArrayList<>();

        for (VehicleState state : states) {

            result.add(
                    new VehicleDispatchResponse(
                            state.vehicle.getVehicleId(),
                            state.totalLoad,
                            String.format(
                                    Locale.US,
                                    "%.2f km",
                                    state.totalDistance
                            ),
                            state.assignedOrders
                    )
            );
        }

        // Return assigned and unassigned orders together
        return new DispatchPlanResponse(result, unassignedOrders);
    }

    // Priority ranking
    private int getPriorityRank(Priority priority) {

        if (priority == Priority.HIGH) {
            return 1;
        }

        if (priority == Priority.MEDIUM) {
            return 2;
        }

        return 3;
    }

    // Internal vehicle state
    private static class VehicleState {

        private final Vehicle vehicle;

        private BigDecimal totalLoad = BigDecimal.ZERO;

        private double currentLatitude;
        private double currentLongitude;

        private double totalDistance = 0.0;

        private final List<AssignedOrderResponse> assignedOrders =
                new ArrayList<>();

        private VehicleState(Vehicle vehicle) {

            this.vehicle = vehicle;

            this.currentLatitude =
                    vehicle.getCurrentLatitude();

            this.currentLongitude =
                    vehicle.getCurrentLongitude();
        }
    }
}
