package com.ecommerce.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.domain.Pedidos;
import com.ecommerce.service.PedidosService;

@RestController
@RequestMapping("/pedidos")
public class PedidosResource {

	@Autowired
	private PedidosService pedidosService;
	
	@PostMapping
	public ResponseEntity<Pedidos> save(@RequestBody Pedidos pedidos) {
		Pedidos createdPedidos = pedidosService.save(pedidos);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdPedidos);
	}
}
