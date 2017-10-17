package br.com.eduardo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.eduardo.entity.model.Produto;
import br.com.eduardo.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	ProdutoService produtoService;
	
	@RequestMapping (method = RequestMethod.GET)
	public ArrayList<Produto> listarProdutos(){
		return produtoService.obterProdutos();
	}

}
