package com.hazem.simpleWebApp.repository;


import com.hazem.simpleWebApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {


}
