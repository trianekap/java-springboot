package com.example.mybatis.controllers;

import com.example.mybatis.mapper.ProductMapper;
import com.example.mybatis.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductMapper productMapper;

    @GetMapping("/getallproduct")
    public List<Product> getAllProduct(){
        return productMapper.findAllProduct();
    }

    @PostMapping("/addproduct")
    public String addProduct(Product product){
        productMapper.createProduct(product);
        return "berhasil menambahkan produk";
    }

    @PutMapping("/updateproduct")
    public String updateProduct(@RequestParam(value = "id", required = false)Integer id, int price){
        productMapper.updatePrice(id, price);
        return "berhasil mengupdate harga";
    }

    @DeleteMapping("/deleteproduct/{id}")
    public String deleteProduct(@PathVariable int id){
        productMapper.deleteProduct(id);
        return "berhasil menghapus data";
    }
}
