package br.com.analyzer.backendjava.controller;

import br.com.analyzer.backendjava.dto.EmpresaRequestDTO;
import br.com.analyzer.backendjava.dto.EmpresaResponseDTO;
import br.com.analyzer.backendjava.service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Bem-vindo ao sistema!";
    }
}
