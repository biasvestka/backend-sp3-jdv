package br.com.analyzer.backendjava.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_EMPRESA")
public class Empresa {

  public Empresa() {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_empresa")
  private Long id;

  @Column(name = "nm_empresa")
  private String nome;

  @Column(name = "cnpj_empresa")
  private String cnpj;

  @Column(name = "ctt_empresa")
  private String contato;

  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }

  public String getContato() {
    return contato;
  }

  public void setContato(String contato) {
    this.contato = contato;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }
}
