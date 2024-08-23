package com.enes.fitnes_api.services;

import com.enes.fitnes_api.model.Category;
import com.enes.fitnes_api.repositroy.CategoryRepository;
import com.enes.fitnes_api.services.interfaces.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServicesImpl implements CategoryServices {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
