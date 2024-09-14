package br.com.analyzer.backendjava.controller;

import br.com.analyzer.backendjava.dto.ClienteRequestDTO;
import br.com.analyzer.backendjava.dto.ClienteResponseDTO;
import br.com.analyzer.backendjava.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

  private final ClienteService clienteService;

  public ClienteController(ClienteService clienteService) {
    this.clienteService = clienteService;
  }

  @GetMapping
  public String listarTodosClientes(Model model) {
    List<ClienteResponseDTO> clientes = clienteService.listarTodosClientes();
    model.addAttribute("clientes", clientes);
    return "clientes"; // Nome do template Thymeleaf para listar clientes
  }

  @GetMapping("/novo")
  public String novoCliente(Model model) {
    model.addAttribute("cliente", new ClienteRequestDTO());
    return "cliente-form"; // Nome do template Thymeleaf para criar novo cliente
  }

  @PostMapping
  public String criarCliente(@Valid @ModelAttribute ClienteRequestDTO clienteRequestDTO) {
    clienteService.criarCliente(clienteRequestDTO);
    return "redirect:/clientes"; 

  @GetMapping("/{id}/editar")
  public String editarCliente(@PathVariable Long id, Model model) {
    ClienteResponseDTO cliente = clienteService.listarClientePorId(id);
    model.addAttribute("cliente", cliente);
    return "cliente-form";
  }

  @PostMapping("/{id}")
  public String atualizarCliente(@PathVariable Long id, @Valid @ModelAttribute ClienteRequestDTO clienteRequestDTO) {
    clienteService.atualizarCliente(id, clienteRequestDTO);
    return "redirect:/clientes"; 
  }

  @GetMapping("/{id}/deletar")
  public String deletarCliente(@PathVariable Long id) {
    clienteService.deletarCliente(id);
    return "redirect:/clientes"; 
  }
}
