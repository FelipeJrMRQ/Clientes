package com.felipe.app.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.app.model.ServicoPrestado;
import com.felipe.app.repository.ClienteRepository;
import com.felipe.app.repository.ServicoPrestadoRepository;
import com.felipe.app.rest.dto.ServicoPrestadoDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoController {

	private final ServicoPrestadoRepository servicoRepository;

	private final ClienteRepository clienteRepository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDTO dto) {
		ServicoPrestado sp = dto.dtoConvert(dto, clienteRepository);
		return servicoRepository.save(sp);
	}

	@PutMapping
	public ServicoPrestado atualizar(@RequestBody ServicoPrestadoDTO dto) {
		ServicoPrestado sp = dto.dtoConvert(dto, clienteRepository);
		return servicoRepository.save(sp);
	}

	@GetMapping
	public List<ServicoPrestado> pequisar(
			@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
			@RequestParam(value = "mes", required = false) Integer mes) {

		return servicoRepository.findByNomeClienteAndMes("%" + nome + "%", mes);
	}
}
