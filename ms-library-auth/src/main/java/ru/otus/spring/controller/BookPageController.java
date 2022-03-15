package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BookPageController {

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/list")
    public String listPage(Model model) {
        return "list";
    }

}
