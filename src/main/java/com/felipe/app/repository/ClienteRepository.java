package com.felipe.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipe.app.model.Cliente;



public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
