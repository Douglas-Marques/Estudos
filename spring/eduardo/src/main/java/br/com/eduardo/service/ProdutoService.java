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
    		produtoRepository.save(produto);
    		return "Produto adicionado com sucesso";
    	}else{
            return "Erro ao adicionar produto";          
    	}
    }
    
    public Produto findOne(Long id){
    	if(id != null){
    		return produtoRepository.findOne(id);
    	}else{
            throw new IllegalArgumentException("Digite um id válido");          
    	}
    }
    
    public String editarProduto(Produto produto){
    	if(camposPreenchidos(produto)){
    		produtoRepository.editarProduto(produto.getQuantidade(), produto.getNome(), produto.getId());
    		return "Produto editado com sucesso";
    	}else{
            return "Erro ao editar produto";          
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
