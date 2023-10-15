package com.cstore.domain.category.edit;

import com.cstore.model.category.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/categories/edit") @Tag(name = "Edit Categories", description = "Provides controller methods for editing categories.")
public class CategoryEditingController {
    private final CategoryEditingService categoryEditingService;

    @Operation(
        method = "getAllCategories",
        responses = {
            @ApiResponse(
                content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Category.class))
                ),
                description = "Success",
                responseCode = "200"
            )
        },
        summary = "Returns all the categories."
    )
    @RequestMapping(method = RequestMethod.GET, path = "")
    public List<Category> getAllCategories() {
        return categoryEditingService.getAllCategories();
    }

    @Operation(
        method = "getCategoryById",
        responses = {
            @ApiResponse(
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Category.class)
                ),
                description = "Success",
                responseCode = "200"
            )
        },
        summary = "Returns the category given the category identifier."
    )
    @RequestMapping(method = RequestMethod.GET, path = "/{category_id}")
    public Category getCategoryById(@PathVariable(name = "category_id", required = true) Long categoryId) {
        return categoryEditingService.getCategoryById(categoryId);
    }

    @RequestMapping(method = RequestMethod.POST, path = "")
    @Operation(
        method = "addNewCategory",
        description = "Creates a new category given the necessary details." +
                      "    If an identical category already exists, throws a 'CategoryAlreadyExistsException'." +
                      "    Otherwise, returns the new category identifier.",
        responses = {
            @ApiResponse(
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Long.class)
                ),
                description = "Success",
                responseCode = "200"
            )
        },
        summary = "Creates a new category."
    )
    public Long addNewCategory(@RequestBody(required = true)
        @Schema(implementation = Category.class) Category category) {
        return categoryEditingService.addNewCategory(category);
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/{category_id}")
    @Operation(
        method = "updateCategory",
        responses = {
            @ApiResponse(
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Category.class)
                ),
                description = "Success",
                responseCode = "200"
            )
        },
        summary = "Updates & returns the category, given the category identifier & new details."
    )
    public Category updateCategory(@PathVariable(name = "category_id", required = true) Long categoryId, @RequestBody(required = true)  Map<String, Object> newDetails) {
        return categoryEditingService.updateCategory(categoryId, newDetails);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{category_id}")
    @Operation(
        method = "deleteCategory",
        responses = {
            @ApiResponse(
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Category.class)
                ),
                description = "Success",
                responseCode = "200"
            )
        },
        summary = "Deletes the category, given the category identifier."
    )
    public Category deleteCategory(@PathVariable(name = "category_id", required = true) Long categoryId) {
        return categoryEditingService.deleteCategory(categoryId);
    }
}
