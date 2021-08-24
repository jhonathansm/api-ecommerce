package com.ecommerce.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.ecommerce.domain.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "pedidos")
public class Pedidos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Long protocolo;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private Usuario user;
	
	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Column(nullable = false)
	private BigDecimal quantidade;
	
	@Column(length = 100)
	private String infos;
	
	@OneToMany(mappedBy = "pedidos")
	private List<Faturados> faturados = new ArrayList<Faturados>();
	
	@ManyToMany
	private List<Product> produto = new ArrayList<Product>();
}
