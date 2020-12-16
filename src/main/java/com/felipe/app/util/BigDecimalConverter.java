package com.felipe.app.util;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class BigDecimalConverter {
		
	/**
	 * Remove o ponto da casa de milhar 1.000,00 para 1000,00
	 * e a virgula do real para o ponto 1000.00
	 * @param value
	 * @return
	 */
	public BigDecimal converter(String value) {
		if(value == null) {
			return null;
		}
		value = value.replace(".",",").replace(",", ".");
		return new BigDecimal(value);
	}
}
