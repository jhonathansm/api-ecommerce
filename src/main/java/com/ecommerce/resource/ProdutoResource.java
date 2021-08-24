package com.ecommerce.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.domain.Product;
import com.ecommerce.dto.ProdutoDto;
import com.ecommerce.repository.ProdutoRepository;
import com.ecommerce.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@PostMapping
	public ResponseEntity<Product> save(@RequestBody ProdutoDto produtoDto) {
		Product produto = produtoDto.transformToProduct();
		Product createdProduct = produtoService.save(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> listAll() {
		List<Product> produtos = produtoService.listAll();
		return ResponseEntity.ok(produtos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getById(Long id) {
		Product produto = produtoService.getById(id);
		return ResponseEntity.ok(produto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> update(Long id, Product produto) {
		Product updatedProduct = produtoService.update(id, produto);
		return ResponseEntity.ok(updatedProduct);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		produtoRepository.deleteById(id);
	}
}
