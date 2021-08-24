package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.domain.Pedidos;

public interface PedidosRepository extends JpaRepository<Pedidos, Long> {

}
