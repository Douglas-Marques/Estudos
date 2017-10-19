package br.com.eduardo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eduardo.entity.dao.ProdutoRepository;
import br.com.eduardo.entity.model.Produto;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository produtoRepository;

    public String save(Produto produto) {
    	if(camposPreenchidos(produto)){    		
    		return "Produto adicionado com sucesso";
    	}else{
            return "Faltam campos obrigatórios";          
    	}
    }
    
    public String editarProduto(Produto produto){
    	if(camposPreenchidos(produto)){
    		int produtosAlterados = produtoRepository.editarProduto(produto.getQuantidade(), produto.getNome(), produto.getId());
    		if(produtosAlterados > 0){
    			return "Produto editado com sucesso";
    		}else{
    			return "Nenhum produto encontrado com este ID";
    		}
    	}else{
            return "Faltam campos obrigatórios";          
    	}
    }
    
    public String delete (Long id){
    	if(id != null && id > 0){
    		produtoRepository.delete(id);
    		return "Produto deletado com sucesso";
    	}else{
    		return "Digite um id válido";
    	}
    }
    
    public Iterable<Produto> findAll(){
    	return produtoRepository.findAll();
    }
    
    public boolean camposPreenchidos(Produto produto){
    	return (produto.getNome() != null && produto.getNome().trim().length() > 0 && produto.getQuantidade() > 0);
    }
}
