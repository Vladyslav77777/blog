package com.example.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home"; // Возвращает вид для домашней страницы
    }

    @GetMapping("/")
    public String index() {
        return "index"; // Возвращает вид для главной страницы
    }
}
