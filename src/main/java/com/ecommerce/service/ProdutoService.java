package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ecommerce.domain.Product;
import com.ecommerce.exception.NotFoundException;
import com.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Product save(Product produto) {
		Product createdProduct = produtoRepository.save(produto);
		return createdProduct;
	}
	
	public List<Product> listAll() {
		List<Product> produtos = produtoRepository.findAll();
		return produtos;
	}
	
	public Product getById(Long id) {
		Optional<Product> result = produtoRepository.findById(id);
		return result.orElseThrow(() -> new NotFoundException("Produto não encontrado de ID = " + id));
	}
	
	public Product update(Long id, Product produto) {
		Optional<Product> updatedProduct = buscarProduto(id);
		BeanUtils.copyProperties(produto, updatedProduct, "id");
		return produtoRepository.save(updatedProduct);
	}
	
	public Optional<Product> buscarProduto(Long id) {
		Optional<Product> produto = produtoRepository.findById(id);
		if (produto == null) throw new EmptyResultDataAccessException(1);
		return produto;
	}
}
