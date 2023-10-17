package com.cstore.domain.product.edit;

import com.cstore.dto.NewProductDto;
import com.cstore.dto.ProductDto;
import com.cstore.dto.SelectedProduct;
import com.cstore.dto._Property;
import com.cstore.model.product.Property;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController @RequestMapping(path = "api/v1/products") @Tag(name = "Edit Products")
@RequiredArgsConstructor
public class ProductEditingController {
    private final ProductEditingService productEditingService;

    @RequestMapping(method = RequestMethod.POST, path = "/add/1")
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
                    schema = @Schema(implementation = ProductAddRequest.class)
                ),
                description = "Success",
                responseCode = "200"
            )
        }
    )
    public Long addBareProduct(@RequestBody(required = true) ProductAddRequest toAdd) {
        return productEditingService.addBareProduct(toAdd);
    }

    @RequestMapping(method = RequestMethod.POST, path = "add/2")
    @Operation(
        description = """
            Should be invoked to populate the bare product with properties.
            Just adds the properties to the 'product' table.
            Returns the property identifiers along with property names & values as a map.""",
        method = "addBareProduct",
        responses = {
            @ApiResponse(
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = PropertyMap.class)
                ),
                description = "Success",
                responseCode = "200"
            )
        }
    )
    public PropertyMap populateWithProperties(@RequestBody(required = true)  List<Property> properties) {
        return productEditingService.populateWithProperties(properties);
    }

    @RequestMapping(method = RequestMethod.POST, path = "add/3/{product_id}")
    @Operation(
        description = """
            Should be invoked to define the variants of a product.
            Adds the variants to the 'variant' table.
            Adds relevant product-property-variant mappings to the 'varies_on' table.""",
        method = "addBareProduct",
        responses = {
            @ApiResponse(
                content = @Content(
                    mediaType = "application/json"
                ),
                description = "Success",
                responseCode = "200"
            )
        }
    )
    public void addVariants(
        @PathVariable(name = "product_id", required = true) Long productId,
        @RequestBody(required = true) List<Variant_> variantproperties
    ) {
        productEditingService.addVariants(productId, variantproperties);
    }
}
