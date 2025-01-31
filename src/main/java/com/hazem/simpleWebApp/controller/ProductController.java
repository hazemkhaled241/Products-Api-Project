package com.hazem.simpleWebApp.controller;

import com.hazem.simpleWebApp.model.Product;
import com.hazem.simpleWebApp.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        Product product = service.getProductById(id);
        if (product != null)
            return new ResponseEntity<>(product, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile
    ) {
        try {
            Product addedProduct = service.addProduct(product, imageFile);
            return new ResponseEntity<>(addedProduct, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId) {
        Product product = service.getProductById(productId);
        byte[] imageFie = product.getImageDate();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFie);
    }

    @PutMapping("/product/update")
    public ResponseEntity<String> updateProduct( @RequestPart Product product,
                                           @RequestPart MultipartFile imageFile) {
        Product updatedProduct;
        try {
            updatedProduct = service.updateProduct( product, imageFile);

        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update",HttpStatus.BAD_REQUEST);
        }
        if (updatedProduct != null)
            return new ResponseEntity<>("Updated",HttpStatus.OK);
        else{
            return new ResponseEntity<>("Failed to update",HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Product product = service.getProductById(id);
        if(product != null) {
            service.deleteProduct(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);

    }
}
