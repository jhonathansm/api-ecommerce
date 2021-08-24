package com.ecommerce.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import com.ecommerce.domain.Pedidos;
import com.ecommerce.domain.Product;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProdutoDto {

	@NotBlank(message = "Nome obrigatório")
	private String name;
	
	@NotBlank(message = "Descrição obrigatório")
	private String description;
	
	@NotBlank(message = "Valor obrigatório")
	private BigDecimal valor;
	
	@NotBlank(message = "Peso obrigatório")
	private Double peso; 
	
	@NotBlank(message = "Imagem obrigatório")
	private String imagem;
	
	@NotBlank(message = "Quantidade obrigatório")
	private BigDecimal quantidade;
	
	
	public Product transformToProduct() {
		Product produto = new Product(null, this.name, this.description, this.valor, this.peso, this.quantidade,  this.imagem);
		return produto;
	}
}
