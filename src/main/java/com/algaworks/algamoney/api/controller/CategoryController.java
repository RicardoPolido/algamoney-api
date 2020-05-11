package com.algaworks.algamoney.api.controller;

import com.algaworks.algamoney.api.event.CreatedResourceEvent;
import com.algaworks.algamoney.api.model.Category;
import com.algaworks.algamoney.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Category> list() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {

        Category category = categoryRepository.findById(id).orElse(null);

        if (category != null) {
            return ResponseEntity.ok().body(category);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Category> save(@Valid @RequestBody Category category, HttpServletResponse response) {

        Category categoryCreated = categoryRepository.save(category);

        publisher.publishEvent(new CreatedResourceEvent(this, response, categoryCreated.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryCreated);

    }

}
