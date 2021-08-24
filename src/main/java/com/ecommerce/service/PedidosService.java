package com.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.domain.Pedidos;
import com.ecommerce.repository.PedidosRepository;

@Service
public class PedidosService {

	@Autowired
	private PedidosRepository pedidosRepository;
	
	public Pedidos save(Pedidos pedidos) {
		Pedidos createdPedidos = pedidosRepository.save(pedidos);
		return createdPedidos;
	}
}
