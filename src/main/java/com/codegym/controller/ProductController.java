package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value="/create",method = RequestMethod.GET)
    public ModelAndView createProduct(){
        ModelAndView mav=new ModelAndView("create");
        mav.addObject("product",new Product());
        return mav;
    }

    @RequestMapping(value="/create",method=RequestMethod.POST,produces= MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Product createProduct(@RequestBody Product product){
        return productService.save(product);
    }

    @RequestMapping(value = "",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<Product> allProducts(){
        return productService.findAll();
    }

    @GetMapping("")
    public ModelAndView allProductsPage(){
        ModelAndView modelAndView=new ModelAndView("list");
        modelAndView.addObject("products",allProducts());
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editProductPage(@PathVariable int id) {
        ModelAndView mav = new ModelAndView("edit");
        Product product = productService.findById(id);
        mav.addObject("product", product);
        return mav;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Product editProduct(@PathVariable int id, @RequestBody Product product) {
        product.setId(id);
        return productService.save(product);
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteProductPage(@PathVariable int id) {
        ModelAndView mav = new ModelAndView("delete");
        Product product = productService.findById(id);
        mav.addObject("product", product);
        return mav;
    }
    @RequestMapping(value="/delete/{id}",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Product deleteProduct(@PathVariable Integer id){
        return productService.remove(id);
    }
}
