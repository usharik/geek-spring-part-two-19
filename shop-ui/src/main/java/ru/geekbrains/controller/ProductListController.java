package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.exceptions.NotFoundException;
import ru.geekbrains.persist.repo.BrandRepository;
import ru.geekbrains.persist.repo.CategoryRepository;
import ru.geekbrains.service.ProductService;

@Controller
public class ProductListController {

    private final ProductService productService;

    private final CategoryRepository categoryRepository;

    private final BrandRepository brandRepository;

    @Autowired
    public ProductListController(ProductService productService, CategoryRepository categoryRepository, BrandRepository brandRepository) {
        this.productService = productService;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
    }

    @RequestMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("brands", brandRepository.findAll());
        return "shop";
    }

    @RequestMapping("/product/{id}")
    public String productPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("brands", brandRepository.findAll());
        model.addAttribute("product", productService.findById(id)
                .orElseThrow(NotFoundException::new));
        return "product-details";
    }
}
