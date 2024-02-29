package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.entity.Product;
import com.nhnacademy.springjpa.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@RequiredArgsConstructor
@Controller
public class ProductController {
    private final ProductRepository productRepository;

    @GetMapping("/")
    public ModelAndView getProductList(@PageableDefault Pageable pageable) {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("products", productRepository.findAllBy(pageable).getContent());
        return mav;
    }

    @GetMapping(path = "/", params = {"search"})
    public ModelAndView getProductList(@RequestParam String search, Pageable pageable) {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("products", productRepository.findAllByProductNameContaining(search, pageable).getContent());
        return mav;
    }

    @GetMapping("/{id}")
    public ModelAndView getProduct(@PathVariable("id") Product product) {
        ModelAndView mav = new ModelAndView("detail");
        mav.addObject("product", product);
        return mav;
    }
}
