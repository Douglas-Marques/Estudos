package br.com.eduardo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.eduardo.entity.model.Produto;
import br.com.eduardo.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	ProdutoService produtoService;
	
	@RequestMapping (method = RequestMethod.GET)
	public Iterable<Produto> listarProdutos(){
		return produtoService.findAll();
	}
	
	@RequestMapping (method = RequestMethod.POST)
	public String salvarProduto(@RequestBody Produto produto){
		return produtoService.save(produto);
	}
	
	@RequestMapping (method = RequestMethod.PATCH)
	public String editarProduto(@RequestBody Produto produto){
		return produtoService.editarProduto(produto);
	}
	
	@RequestMapping (method = RequestMethod.DELETE)
	public String deletarProduto(@RequestParam Long id){
		return produtoService.delete(id);
	}

}
