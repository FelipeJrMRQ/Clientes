package com.felipe.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.felipe.app.model.ServicoPrestado;

public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado, Integer> {

	
	@Query("SELECT s FROM ServicoPrestado s JOIN s.cliente "
			+ "c WHERE upper(c.nome) like upper(:nome) "
			+ "AND MONTH(s.data)=:mes ")
	List<ServicoPrestado> findByNomeClienteAndMes(
			@Param("nome") String nome, 
			@Param("mes") Integer mes);	
}
