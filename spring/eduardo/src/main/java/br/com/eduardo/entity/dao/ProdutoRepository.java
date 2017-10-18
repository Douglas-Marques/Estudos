package br.com.eduardo.entity.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.eduardo.entity.model.Produto;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long>{
	
	Produto findByNome(String nome);
	
    @Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update Produto p set p.quantidade = ?1, p.nome = ?2 where p.id = ?3")
	int editarProduto(int quantidade, String nome, Long id);
}
