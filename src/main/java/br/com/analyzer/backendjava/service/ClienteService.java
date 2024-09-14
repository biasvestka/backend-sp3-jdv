package br.com.analyzer.backendjava.service;

import br.com.analyzer.backendjava.dto.ClienteRequestDTO;
import br.com.analyzer.backendjava.dto.ClienteResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    // Simulando uma base de dados com uma lista
    private final List<ClienteResponseDTO> clientes = List.of();

    public List<ClienteResponseDTO> listarTodosClientes() {
        return clientes;
    }

    public ClienteResponseDTO listarClientePorId(Long id) {
        // Implementar lógica para buscar cliente por ID
        return null; // Ajustar para retorno correto
    }

    public void criarCliente(ClienteRequestDTO clienteRequestDTO) {
        // Aqui você usa os getters para acessar os dados
        String nome = clienteRequestDTO.getNome();
        String email = clienteRequestDTO.getEmail();
        String password = clienteRequestDTO.getPassword();

        // Implementar lógica para criar cliente
    }

    public void atualizarCliente(Long id, ClienteRequestDTO clienteRequestDTO) {
        // Aqui você usa os getters para acessar os dados
        String nome = clienteRequestDTO.getNome();
        String email = clienteRequestDTO.getEmail();
        String password = clienteRequestDTO.getPassword();

        // Implementar lógica para atualizar cliente
    }

    public void deletarCliente(Long id) {
        // Implementar lógica para deletar cliente
    }
}
