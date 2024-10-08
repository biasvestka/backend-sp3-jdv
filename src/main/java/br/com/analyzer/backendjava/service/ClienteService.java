package br.com.analyzer.backendjava.service;

import br.com.analyzer.backendjava.dto.ClienteRequestDTO;
import br.com.analyzer.backendjava.dto.ClienteResponseDTO;
import br.com.analyzer.backendjava.model.Cliente;
import br.com.analyzer.backendjava.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

  private final ClienteRepository clienteRepository;

  public ClienteService(ClienteRepository clienteRepository) {
    this.clienteRepository = clienteRepository;
  }

  public ClienteResponseDTO criarCliente(ClienteRequestDTO clienteRequestDTO) {
    Cliente cliente = fromDTO(clienteRequestDTO);
    Cliente clienteSalvo = clienteRepository.save(cliente);
    return toDTO(clienteSalvo);
  }

  public List<ClienteResponseDTO> listarTodosClientes() {
    return clienteRepository.findAll()
        .stream()
        .map(this::toDTO)
        .collect(Collectors.toList());
  }

  public ClienteResponseDTO listarClientePorId(Long id) {
    Optional<Cliente> clienteOpt = clienteRepository.findById(id);
    if (clienteOpt.isPresent()) {
      return toDTO(clienteOpt.get());
    } else {
      throw new RuntimeException("Cliente não encontrado com o ID: " + id);
    }
  }

  public ClienteResponseDTO atualizarCliente(Long id, ClienteRequestDTO clienteRequestDTO) {
    Optional<Cliente> clienteOpt = clienteRepository.findById(id);
    if (clienteOpt.isPresent()) {
      Cliente cliente = clienteOpt.get();
      cliente.setNome(clienteRequestDTO.nome());
      cliente.setEmail(clienteRequestDTO.email());
      cliente.setPassword(clienteRequestDTO.password());

      Cliente clienteAtualizado = clienteRepository.save(cliente);
      return toDTO(clienteAtualizado);
    } else {
      throw new RuntimeException("Cliente não encontrado com o ID: " + id);
    }
  }

  public void deletarCliente(Long id) {
    Optional<Cliente> clienteOpt = clienteRepository.findById(id);
    if (clienteOpt.isPresent()) {
      clienteRepository.deleteById(id);
    } else {
      throw new RuntimeException("Cliente não encontrado com o ID: " + id);
    }
  }

  private Cliente fromDTO(ClienteRequestDTO dto) {
    Cliente cliente = new Cliente();
    cliente.setNome(dto.nome());
    cliente.setEmail(dto.email());
    cliente.setPassword(dto.password());
    return cliente;
  }

  private ClienteResponseDTO toDTO(Cliente cliente) {
    return new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getEmail());
  }
}
