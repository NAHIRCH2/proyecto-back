package com.ucc.crudservice.repositories;

import com.ucc.crudservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product, Long>{
}
