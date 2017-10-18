package br.com.eduardo.entity.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUTO")
public class Produto implements Serializable{
	
	private static final String SQ_NAME = "SQ_PRODUTO";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SQ_NAME)
    @SequenceGenerator(name = SQ_NAME, sequenceName = SQ_NAME, allocationSize = 1)
    @Column(name = "ID_PRODUTO")
    private Long id;
	
    @Basic(optional = false)
    @Column(name = "NOME")
	private String nome;
    
    @Basic(optional = false)
    @Column(name = "QUANTIDADE")
	private int quantidade;
	
	public Produto(String nome, int quantidade) {
		this.nome = nome;
		this.quantidade = quantidade;
	}
	
	public Produto(){
		
	}

	public String getNome() {
		return nome;
	}
	
	public Long getId(){
		return id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}	
}
