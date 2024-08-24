package com.enes.fitnes_api.controller;

import com.enes.fitnes_api.handler.GenericResponse;
import com.enes.fitnes_api.model.Category;
import com.enes.fitnes_api.services.interfaces.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryRestController {

    @Autowired
    private CategoryServices categoryService;

    @GetMapping("/all")
    public ResponseEntity<GenericResponse<List<Category>>> getAll() {
        try {
            return ResponseEntity.ok(GenericResponse.<List<Category>>builder().success(true).data(categoryService.getAll()).message("All categories fetched successfully").build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(GenericResponse.<List<Category>>builder().success(false).data(null).message(e.getMessage()).build());
        }
    }
}
