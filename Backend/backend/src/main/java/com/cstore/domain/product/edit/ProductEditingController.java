package com.cstore.domain.product.edit;

import com.cstore.dto.NewProductDto;
import com.cstore.dto.ProductDto;
import com.cstore.dto.SelectedProduct;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController @RequestMapping(path = "api/v1/products/edit") @Tag(name = "Edit Products")
@RequiredArgsConstructor
public class ProductEditingController {
    private final ProductEditingService productEditingService;

    @RequestMapping(method = RequestMethod.POST, path = "")
    @Operation(
        description = """
            Should be invoked to add a new product.
            Just creates a bare product with no properties & a default variant.
            Returns the product identifier of the new product.""",
        method = "addBareProduct",
        responses = {
            @ApiResponse(
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = SelectedProduct.class)
                ),
                description = "Success",
                responseCode = "200"
            )
        }
    )
    public Long addBareProduct(@RequestBody(required = true) ProductAddRequest toAdd) {
        return productEditingService.addBareProduct(toAdd);
    }

    /*@RequestMapping(method = RequestMethod.POST, path = "")
    @Operation(
        description = """
            Should be invoked when a product is selected.
            Returns all the necessary details of the product including its properties, categories it belongs to & stock count.""",
        method = "getProductById",
        responses = {
            @ApiResponse(
                content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = SelectedProduct.class))
                ),
                description = "Success",
                responseCode = "200"
            )
        }
    )
    public ProductDto addNewProduct(@RequestBody(required = true) NewProductDto product) throws SQLException {
        return productEditingService.addNewProduct(product);
    }*/
}
