package com.cstore.domain.product.select;

import com.cstore.dto.Product__;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/user/products/select")
@Tag(name = "Select Product")
public class ProductSelectionController {
    private final ProductSelectionService productSelectionService;

    public ProductSelectionController(ProductSelectionService productSelectionService) {
        this.productSelectionService = productSelectionService;
    }

    @Operation(
        description = """
            Not Recommended! Heavy load on the entire system.
            If invoked, should be invoked when a product is selected.
            Returns all the necessary details of the product including its properties, categories it belongs to & stock count.""",
        method = "Find Product by Id",
        responses = {
            @ApiResponse(
                content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Product__.class))
                ),
                description = "Success",
                responseCode = "200"
            )
        }
    )
    @RequestMapping(method = RequestMethod.GET, path = "/{product_id}")
    public Product__ findById(@PathVariable(name = "product_id", required = true) Long productId) {
        return productSelectionService.findById(productId);
    }
}
