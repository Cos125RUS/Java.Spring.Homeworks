package com.example.shop.controller;

import com.example.shop.model.Product;
import com.example.shop.service.ShopService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class WebController {
    private final ShopService shopService;

    @GetMapping
    public String home(Model model) {
        List<Product> products = shopService.getAllProducts();
        model.addAttribute("products", products);
        return "index";
    }
}
