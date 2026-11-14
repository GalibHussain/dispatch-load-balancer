
package com.freightfox.dispatch.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freightfox.dispatch.dto.ApiResponse;
import com.freightfox.dispatch.dto.OrderRequest;
import com.freightfox.dispatch.dto.VehicleRequest;
import com.freightfox.dispatch.dto.DispatchPlanResponse;
import com.freightfox.dispatch.service.DispatchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/dispatch")
@Tag(
    name = "Dispatch Management",
    description = "APIs for managing delivery orders, vehicles and dispatch planning"
)
public class DispatchController 
{

    private final DispatchService dispatchService;

    public DispatchController(DispatchService dispatchService) 
    {
        this.dispatchService = dispatchService;
    }

   
    @PostMapping("/orders")
    @Operation(
        summary = "Accept delivery orders",
        description = "Accepts and stores delivery orders with location, package weight and priority."
    )
    @ApiResponses(value = 
{
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Delivery orders accepted successfully"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "400",
            description = "Invalid order request",
            content = @Content
        )
    })
    public ApiResponse addOrders(
        @RequestBody
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "List of delivery orders",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = OrderRequest.class),
                examples = @ExampleObject(
                    name = "Sample Orders",
                    value = "{\"orders\":[{\"orderId\":\"ORD002\",\"latitude\":28.5355,\"longitude\":77.3910,\"address\":\"Noida\",\"packageWeight\":30,\"priority\":\"HIGH\"}]}"
                )
            )
        )
        OrderRequest request
    ) {
        return dispatchService.acceptOrders(request);
    }

    
    @PostMapping("/vehicles")
    @Operation(
        summary = "Accept vehicle details",
        description = "Accepts and stores vehicle details including capacity and current location."
    )
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Vehicle details accepted successfully"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "400",
            description = "Invalid vehicle request",
            content = @Content
        )
    })
    public ApiResponse addVehicles(@RequestBody VehicleRequest request)
    {
        return dispatchService.acceptVehicles(request);
    }

   
    @GetMapping("/plan")
    @Operation(
        summary = "Generate dispatch plan",
        description = "Generates a dispatch plan based on priority, vehicle capacity and travel distance."
    )
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Dispatch plan generated successfully"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "422",
            description = "Some orders could not be assigned",
            content = @Content
        )
    })
    public DispatchPlanResponse getDispatchPlan() {
        return dispatchService.getDispatchPlan();
    }
}
