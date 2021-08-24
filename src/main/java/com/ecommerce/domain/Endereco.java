package com.ecommerce.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Endereco implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Column(length = 75)
	private String logradouro;
	
	@NotBlank
	@Column(length = 75)
	private String numero;
	
	@NotBlank
	@Column(length = 75)
	private String complemento;
	
	@NotBlank
	@Column(length = 75)
	private String bairro;
	
	@NotBlank
	@Column(length = 75)
	private String cep;
	
	@NotBlank
	@Column(length = 75)
	private String cidade;
	
	@NotBlank
	@Column(length = 75)
	private String estado;
	
	@NotBlank
	@Column(length = 75)
	private String pontoReferencia;
	
}
