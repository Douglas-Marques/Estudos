package br.com.eduardo.entity.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.eduardo.entity.model.Produto;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long>{
	
	Produto findByNome(String nome);
}
