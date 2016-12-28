package src.umov.ticket.teste;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import src.umov.ticket.CalculaIngreco;

public class TesteSabDomFeriados {

	// Calcule os seguintes descontos para as entradas dado o preço dos ingressos:
	// Crianças: R$ 5.50
	// Estudantes: R$ 8.00
	// Idosos: R$ 6.00	
	// Domingo/Sábados/Feriados
    // 5% Idosos

	@Test
	public void testeDescontoIdoso() {

		double idoso = 6.00;
		double percentual = 5;

		CalculaIngreco calculaDesconto = new CalculaIngreco();

		double resultadoDescontoIdoso = calculaDesconto.calculaDescontoIdoso(idoso, percentual);

		assertEquals("Desconto de 5 % para Idosos.", 0.3, resultadoDescontoIdoso, 5.7);
	}

}
