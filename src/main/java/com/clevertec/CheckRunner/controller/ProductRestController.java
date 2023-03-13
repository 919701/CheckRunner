package com.clevertec.CheckRunner.controller;

import com.clevertec.CheckRunner.model.Product;
import com.clevertec.CheckRunner.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductRestController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.findById(id)
                .map(product -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .body(product)
                ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
    }

    @GetMapping()
    public ResponseEntity<List<Product>> listAllProducts() {
        final List<Product> products = productService.findAllProducts();
        return products != null
                ? new ResponseEntity<>(products,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        final boolean save = productService.saveProduct(product);
        return save
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable(name = "id") Long id, @RequestBody Product product) {
        final boolean update = productService.updateProduct(id, product);
        return update
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable Long id) {
        final boolean deleted = productService.deleteProduct(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
