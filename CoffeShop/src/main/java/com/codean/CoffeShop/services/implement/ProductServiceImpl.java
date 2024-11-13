package com.codean.CoffeShop.services.implement;

import com.codean.CoffeShop.exception.ResourceAlreadyExistException;
import com.codean.CoffeShop.exception.ResourceNotExistException;
import com.codean.CoffeShop.models.dtos.ProductAddDTO;
import com.codean.CoffeShop.models.dtos.ProductGetDTO;
import com.codean.CoffeShop.models.dtos.responseDTO.ProductResponseDTO;
import com.codean.CoffeShop.models.entities.Product;
import com.codean.CoffeShop.repositories.ProductRepository;
import com.codean.CoffeShop.services.ProductService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;



@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public ProductGetDTO addProduct(ProductAddDTO productAddDTO) throws ResourceAlreadyExistException, ResourceNotExistException{

        Product convertedProduct = convertFromDTO(productAddDTO);
        checkDuplicate(null, convertedProduct.getName());

        Product product1 = new Product();
        product1.setName(productAddDTO.getName());
        product1.setDescription(productAddDTO.getDescription());
        product1.setPrice(productAddDTO.getPrice());
        product1.setStock(productAddDTO.getStock());

        productRepository.save(product1);

        return convertToProductGetDTO(product1);
    }

    @Override
    public ProductResponseDTO getAllProducts(int page_number, int page_size, String sort_order, String sort_by){
        //sorting data
        Sort sortByAndOrders = sort_order.equalsIgnoreCase("ASC")? Sort.by(sort_by).ascending() :
                Sort.by(sort_by).descending();
        //create request
        Pageable detail = PageRequest.of(page_number, page_size, sortByAndOrders);
        //get data from repo
        Page<Product> productPage = productRepository.findAll(detail);
        //data to list
        List<Product> productList = productPage.getContent();
        //convert DTO
        List<ProductGetDTO> productGetDTOList = productList.stream().map(u -> modelMapper.map(u, ProductGetDTO.class)
        ).collect(Collectors.toList());
        //response
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setContent(productGetDTOList);
        productResponseDTO.setPage_number(productPage.getNumber());
        productResponseDTO.setPage_size(productPage.getSize());
        productResponseDTO.setTotal_element(productPage.getTotalElements());
        productResponseDTO.setLast_page(productPage.isLast());

        return productResponseDTO;
    }

    @Override
    public ProductGetDTO getProductById(UUID product_id) throws ResourceNotExistException {

        Product existedProduct = getExistence(product_id);
        return convertToProductGetDTO(existedProduct);
    }

    @Override
    public List<Product> searchProduct (String query){
        List<Product> products = productRepository.searchProduct(query);
        return products;
    }

    @Override
    public void updateProductById(UUID product_id, ProductAddDTO productAddDTO) throws ResourceNotExistException, ResourceAlreadyExistException{

        Product existedProduct = getExistence(product_id);

        Product convertedProduct = convertFromDTO(productAddDTO);
        checkDuplicate(product_id, convertedProduct.getName());

        existedProduct.setName(productAddDTO.getName());
        existedProduct.setDescription(productAddDTO.getDescription());
        existedProduct.setPrice(productAddDTO.getPrice());
        existedProduct.setStock(productAddDTO.getStock());

        productRepository.save(existedProduct);
    }

    @Override
    public void deleteProductById(UUID product_id) throws ResourceNotExistException {

        Product existedProduct = getExistence(product_id);
        productRepository.delete(existedProduct);
    }

    private void checkDuplicate(UUID productId, String productName) throws ResourceAlreadyExistException{
        Product existedProduct = productRepository.findProductByName(productName);
        if (existedProduct != null && !existedProduct.getProductId().equals(productId)){
            String errorMsg = "Resource product with same name already exist!";
            logger.error(errorMsg);
            throw new ResourceAlreadyExistException(errorMsg);
        }
    }

    private Product getExistence(UUID productId) throws ResourceNotExistException{
        Product existedProduct = productRepository.findProductByUUID(productId);
        if (existedProduct == null){
            String errorMsg = "Resource with product id " + productId + " doesn't exist!";
            logger.error(errorMsg);
            throw new ResourceNotExistException(errorMsg);
        }

        return existedProduct;
    }

    private ProductGetDTO convertToProductGetDTO(Product product){
        return modelMapper.map(product, ProductGetDTO.class);
    }

    private ProductAddDTO convertToProductAddDTO(Product product){
        return modelMapper.map(product, ProductAddDTO.class);
    }

    private Product convertFromDTO(ProductAddDTO dto){
        return modelMapper.map(dto, Product.class);
    }
    private Product convertFromDTO(ProductGetDTO dto){
        return modelMapper.map(dto, Product.class);
    }
}
