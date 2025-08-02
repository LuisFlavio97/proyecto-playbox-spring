package com.cibertec.controller;

import com.cibertec.dto.TransacUsuarioJuegoDto;
import com.cibertec.dto.TransaccionDetalleDto;
import com.cibertec.models.entity.Transaccion;
import com.cibertec.models.service.TransaccionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/transaccion")
@PreAuthorize("hasRole('Administrador')")
public class TransactionController {


    @Autowired
    private TransaccionServiceImpl transaccionService;


    @GetMapping("/list")
    public String listarMovimientos(Model model) {
        List<TransaccionDetalleDto> movimientos = transaccionService.findAllTransaccion();
        model.addAttribute("movimientos", movimientos);
        return "Movimiento/listar-movimientos"; // Ruta hacia el template Thymeleaf
    }

    @PostMapping("save")
    public Transaccion saveTransaccion(@PathVariable TransacUsuarioJuegoDto transacUsuarioJuegoDto){
        Transaccion transaccionGuardada = transaccionService.saveTransaccion(transacUsuarioJuegoDto).orElseThrow();
        return  transaccionGuardada;
    }
}
