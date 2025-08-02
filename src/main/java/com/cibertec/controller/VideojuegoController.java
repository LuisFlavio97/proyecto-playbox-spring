package com.cibertec.controller;

import java.io.Console;
import java.util.List;

import com.cibertec.dto.TransacUsuarioJuegoDto;
import com.cibertec.dto.UsuarioSecurityDto;
import com.cibertec.models.entity.Consola;
import com.cibertec.models.entity.Genero;
import com.cibertec.models.entity.Usuario;
import com.cibertec.models.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.models.entity.Videojuego;


@Controller
@RequestMapping("/views/videojuegos")
@PreAuthorize("hasRole('Administrador')")
public class VideojuegoController {
	@Autowired
	private IVideojuegoService videojuegoService;
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private TransaccionServiceImpl transaccionService;
	@Autowired
	private IConsolaService consolaService;
	@Autowired
	private IGeneroService generoService;
	
	@GetMapping("/")
	public String listarVideojuegos(Model model) {
		System.out.println("Estoy en el listar videojuegos.controller");
		List<Videojuego> listadoVideojuegos = videojuegoService.listarTodos();
		
		model.addAttribute("titulo", "Lista de Videojuegos");
		model.addAttribute("videojuegos", listadoVideojuegos);
		
		return "/views/videojuegos/listar";
	}
		
	@GetMapping("/create")
	public String crear(Model model) {
		Videojuego videojuego = new Videojuego();
		
		model.addAttribute("titulo", "Formulario: Nuevo videojuego");
		model.addAttribute("videojuego", videojuego);
		model.addAttribute("consolas", consolaService.listarConsolas());
		model.addAttribute("generos", generoService.listarGeneros());
		
		return "/views/videojuegos/frmCrear";
	}
	
	@PostMapping("/save")
	public String guardar(@ModelAttribute Videojuego videojuego, RedirectAttributes attribute) {
		// Convertir consola y género a Integer
		Integer consolaId = Integer.parseInt(videojuego.getConsola());
		Integer generoId = Integer.parseInt(videojuego.getGenero());

		// Obtener la consola y el género por ID
		Consola consola = consolaService.findById(consolaId);
		Genero genero = generoService.findById(generoId);

		videojuego.setConsola(consola.getDescripcion());
		videojuego.setGenero(genero.getDescripcion());

		// Asignar las descripciones al videojuego
		videojuego.setConsola(consola.getDescripcion());
		videojuego.setGenero(genero.getDescripcion());

		videojuegoService.guardar(videojuego);
		System.out.println("Videojuego guardado con éxito...!!!");
		attribute.addFlashAttribute("success","Videojuego guardado con éxito...!!!");
		
		return "redirect:/views/videojuegos/";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Integer idVideojuego, Model model, RedirectAttributes attribute) {
		Videojuego videojuego = null;
		
		if (idVideojuego > 0) {
			videojuego = videojuegoService.buscarPorId(idVideojuego);
			model.addAttribute("consolas", consolaService.listarConsolas());
			model.addAttribute("generos", generoService.listarGeneros());
			
			if (videojuego == null) {
				System.out.println("Error: El ID del Videojuego a EDITAR no existe...!!!");
				attribute.addFlashAttribute("error", "Atención: El ID del Videojuego a EDITAR no existe...!!!");
				return "redirect:/views/videojuegos/";
			}
		} else {
			System.out.println("Error: Error con el ID del Videojuego a EDITAR...!!!");
			attribute.addFlashAttribute("error", "Atención: Error con el ID del Videojuego a EDITAR...!!!");
			return "redirect:/views/videojuegos/";
		}
		
		model.addAttribute("titulo", "Formulario: Editar videojuego");
		model.addAttribute("videojuego", videojuego);
		
		return "/views/videojuegos/frmCrear";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") Integer idVideojuego, RedirectAttributes attribute) {
		Videojuego videojuego = null;
		
		if (idVideojuego > 0) {
			videojuego = videojuegoService.buscarPorId(idVideojuego);
			
			if (videojuego == null) {
				System.out.println("Error: El ID del Videojuego a ELIMINAR no existe...!!!");
				attribute.addFlashAttribute("error", "Atención: El ID del Videojuego a ELIMINAR no existe...!!!");
				return "redirect:/views/videojuegos/";
			}
		} else {
			System.out.println("Error: Error con el ID del Videojuego a ELIMINAR...!!!");
			attribute.addFlashAttribute("error", "Atención: Error con el ID del Videojuego a ELIMINAR...!!!");
			return "redirect:/views/videojuegos/";
		}

		boolean tieneTransacciones = videojuegoService.tieneTransacciones(idVideojuego);

		if (tieneTransacciones) {
			videojuegoService.anular(idVideojuego);
			attribute.addFlashAttribute("warning", "Registro eliminado con éxito...!!!");
		} else {
			videojuegoService.eliminar(idVideojuego); // eliminación física
			attribute.addFlashAttribute("warning", "Registro eliminado con éxito...!!!");
		}
		
		return "redirect:/views/videojuegos/";
	}

	@GetMapping("/movimiento/{id}")
	public String mostrarFormularioMovimiento(@PathVariable("id") Integer id, Model model) {
		Videojuego videojuego = videojuegoService.buscarPorId(id);
		TransacUsuarioJuegoDto transacDto = new TransacUsuarioJuegoDto();
		transacDto.setVidejoJuegoId(id);

		model.addAttribute("videojuego", videojuego);
		model.addAttribute("transacDto", transacDto);
		return "Movimiento/formulario-movimiento";
	}

	@Transactional
	@PostMapping("/guardarMovimiento")
	public String guardarMovimiento(@ModelAttribute("transacDto") TransacUsuarioJuegoDto transacDto, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		System.out.println("Estoy en post guardar movimiento");
		Videojuego videojuego = videojuegoService.buscarPorId(transacDto.getVidejoJuegoId());

		System.out.println(videojuego.toString());

		try {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UsuarioSecurityDto usuarioSecurityDto = (UsuarioSecurityDto) userDetails;

			// Buscar el usuario en la base de datos para obtener su ID
			Usuario usuarioLogeado = usuarioService.obtenerUsuario(usuarioSecurityDto.getUsername());
			transacDto.setUsuarioId(usuarioLogeado.getIdusuario());

			if ("SALIDA".equalsIgnoreCase(transacDto.getTipoMoviento())) {
				videojuego.setStock(videojuego.getStock() - transacDto.getCantMovida());
			} else if ("ENTRADA".equalsIgnoreCase(transacDto.getTipoMoviento())) {
				videojuego.setStock(videojuego.getStock() + transacDto.getCantMovida());
			}

			transaccionService.saveTransaccion(transacDto);
			redirectAttributes.addFlashAttribute("success", "Movimiento guardado exitosamente.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Error al guardar el movimiento: " + e.getMessage());
		}

		return "redirect:/views/videojuegos/";
	}
	
}
