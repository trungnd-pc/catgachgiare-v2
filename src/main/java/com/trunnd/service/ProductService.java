package com.trunnd.service;

import com.trunnd.dao.ProductDAO;
import com.trunnd.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDAO dao;


    public List<Product> findAll() {
        return dao.findAll();
    }

    public List<Product> findTop8Product() {
        return dao.findTop8ByOrderByIdDesc();
    }

    public List<Product> findPaging(int page, int size) {
        return dao.findAll(new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id"))).getContent();
    }

    public List<Product> findByCategoriesPaging(int page, int size, String categories) {
        return dao.findByCategoriesOrderByIdDesc(categories, new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id"))).getContent();
    }

    public List<Product> findNewestProduct() {
        return dao.findTop3ByOrderByIdDesc();
    }

    public Product findByOne(Integer id) {
        return dao.findOne(id);
    }


    public List<Product> findRelatedProduct(Integer id) {
        Product product = dao.findOne(id);
        if (product != null) {
            return dao.findRelatedProduct(product.getCategories());
        }
        return null;
    }

}
