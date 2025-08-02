package com.cibertec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
    @GetMapping("/error/access-denied")
    public String accessDenied() {
        return "error/access-denied"; // Ruta dentro de la carpeta templates
    }
}
