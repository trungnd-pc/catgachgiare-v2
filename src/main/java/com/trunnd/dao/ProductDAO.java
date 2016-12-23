package com.trunnd.dao;

import com.trunnd.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product, Integer>, PagingAndSortingRepository<Product, Integer> {

    List<Product> findTop3ByOrderByIdDesc();
    List<Product> findTop8ByOrderByIdDesc();
    Page<Product> findByCategoriesOrderByIdDesc(String categories, Pageable pageable);

    @Query(value = "SELECT * FROM PRODUCT WHERE CATEGORIES = ?1 ORDER BY RANDOM() LIMIT 4", nativeQuery = true)
    List<Product> findRelatedProduct(String categories);
}
