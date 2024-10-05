package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.cibertec.models.entity.Videojuego;
import com.cibertec.models.service.IVideojuegoService;

import java.util.List;

@Controller
public class CatalogoController {
	
	 	@Autowired
	    private IVideojuegoService videojuegoService;

	    @GetMapping("/catalogo")
	    public String mostrarCatalogo(Model model) {
	        List<Videojuego> videojuegos = videojuegoService.listarTodos();
	        
	        model.addAttribute("titulo", "Catálogo de Videojuegos");
	        model.addAttribute("videojuegos", videojuegos);
	        
	        return "views/videojuegos/catalogo";
	    }
}
