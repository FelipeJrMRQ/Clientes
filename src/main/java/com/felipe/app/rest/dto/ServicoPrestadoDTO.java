package com.felipe.app.rest.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.felipe.app.model.Cliente;
import com.felipe.app.model.ServicoPrestado;
import com.felipe.app.repository.ClienteRepository;
import com.felipe.app.util.BigDecimalConverter;

import lombok.Data;

@Data
public class ServicoPrestadoDTO {
	
	@NotEmpty(message = "{campo.descricao.obrigatorio}")
	private String descricao;
	@NotEmpty(message = "{campo.preco.obrigatorio}")
	private String valor;
	@NotEmpty(message = "{campo.data.obrigatorio}")
	private String data;
	@NotNull(message = "{campo.cliente.obrigatorio}")
	private Integer idCliente;
	
	
	private ServicoPrestado servicoPrestado;
	private BigDecimalConverter big = new BigDecimalConverter();
	
	/**
	 * Realiza a conversao de um objeto {@link ServicoPrestadoDTO} para {@link ServicoPrestado}
	 * @param dto
	 * @param repo
	 * @return {@link ServicoPrestado}
	 */
	public ServicoPrestado dtoConvert(ServicoPrestadoDTO dto, ClienteRepository repo) {
		servicoPrestado = new ServicoPrestado();
		Cliente c = new Cliente();
		c = repo.findById(dto.getIdCliente()).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não encontrado"));
		servicoPrestado.setCliente(c);
		servicoPrestado.setData(LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		servicoPrestado.setDescricao(dto.getDescricao());
		servicoPrestado.setValor(big.converter(dto.getValor()));
		return servicoPrestado;
	}
}
