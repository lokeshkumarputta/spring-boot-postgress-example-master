package com.elife.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elife.domain.Product;

/**
 * Created by jt on 1/10/17.
 */
public interface ProductRepository extends CrudRepository<Product, Long> {
}
