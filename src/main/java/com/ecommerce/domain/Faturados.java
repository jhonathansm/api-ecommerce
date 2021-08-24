package com.ecommerce.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ecommerce.domain.enums.StatusPagamento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "faturados")
public class Faturados implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "pedidos_id", nullable = false)
	private Pedidos pedidos;
	
	@Column(length = 20, nullable = false)
	private BigDecimal subTotal;
	
	@Column(length = 20, nullable = false)
	private BigDecimal frete;
	
	@Column(length = 20, nullable = false)
	private BigDecimal desconto;
	
	@Column(length = 100)
	private Number quantidade;
	
	@Enumerated(EnumType.STRING)
	private StatusPagamento status;

}
