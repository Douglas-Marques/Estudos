package br.com.eduardo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eduardo.entity.dao.ProdutoRepository;
import br.com.eduardo.entity.model.Produto;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository produtoRepository;

    public Produto save(Produto produto) {
    	if(produto.getNome() != null && produto.getNome().trim().length() > 0 && produto.getQuantidade() > 0){
    		return produtoRepository.save(produto);
    	}else{
            throw new IllegalArgumentException("Digite um id válido");          
    	}
    }
    
    public Produto findOne(Long id){
    	if(id != null){
    		return produtoRepository.findOne(id);
    	}else{
            throw new IllegalArgumentException("Digite um id válido");          
    	}
    }
    
    public Produto findByNome(String nome){
    	if(nome != null && nome.trim().length() > 0){
    		return produtoRepository.findByNome(nome);
    	}else{
            throw new IllegalArgumentException("Digite um id válido");          
    	}
    }
    
    public ArrayList<Produto> findAll(){
    	Iterable<Produto> todosProdutos = produtoRepository.findAll();
    	return iterableToArrayList(todosProdutos);
    }
    
    public ArrayList<Produto> iterableToArrayList(Iterable<Produto> produto){
    	ArrayList<Produto> produtos = new ArrayList<Produto>();
    	produto.forEach(produtos::add);
    	return produtos;
    }
}
