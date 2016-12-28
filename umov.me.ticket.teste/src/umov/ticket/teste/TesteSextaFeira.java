package src.umov.ticket.teste;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import src.umov.ticket.CalculaIngreco;

public class TesteSextaFeira {

	// Calcule os seguintes descontos para as entradas dado o preço dos ingressos:
	// Crianças: R$ 5.50
	// Estudantes: R$ 8.00
	// Idosos: R$ 6.00
	//Sexta-Feira
  // 11% crianças

	@Test
	public void testeDescontoCrianca() {
		double crianca = 5.50;

		double percentual = 11;

		CalculaIngreco calculaDesconto = new CalculaIngreco();

		double resultado = calculaDesconto.calculaDescontoCrianca(crianca, percentual);

		assertEquals("Desconto de 11 % para crianças.", 0.60, resultado, 4.90);
	}

}
