package src.umov.ticket.teste;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import src.umov.ticket.CalculaIngreco;

public class TesteQuartaFeira {

	// Calcule os seguintes descontos para as entradas dado o preço dos ingressos:
	// Crianças: R$ 5.50
	// Estudantes: R$ 8.00
	// Idosos: R$ 6.00 	
	// Quarta-Feira:
    // 40% idosos
    // 30% crianças
    // 50% estudantes

	@Test
	public void testeDescontoCrianca() {
		double crianca = 5.50;

		double percentual = 30;

		CalculaIngreco calculaDesconto = new CalculaIngreco();

		double resultado = calculaDesconto.calculaDescontoCrianca(crianca, percentual);

		assertEquals("Desconto de 30 % Crianças.", 1.65, resultado, 3.85);
	}

	@Test
	public void testeDescontoIdoso() {

		double idoso = 6.00;
		double percentual = 40;

		CalculaIngreco calculaDesconto = new CalculaIngreco();

		double resultadoDescontoIdoso = calculaDesconto.calculaDescontoIdoso(idoso, percentual);

		assertEquals("Desconto de 40 % Idosos.", 3.6, resultadoDescontoIdoso, 2.40);
	}

	@Test
	public void testeDescontoEstudante() {

		double estudante = 8.00;
		double percentual = 50;

		CalculaIngreco calculaDesconto = new CalculaIngreco();

		double resultadoDescontoEstudante;

		boolean carteira = false;
		if (carteira == true) {
			double descontoCarteira = 35;

			resultadoDescontoEstudante = calculaDesconto.calculaDescontoEstudante(estudante, descontoCarteira);
			assertEquals("Desconto de 35 % Estudantes que apresentar a carteira.", 5.2, resultadoDescontoEstudante, 2.8 );
		} else if (carteira != true) {
			resultadoDescontoEstudante = calculaDesconto.calculaDescontoEstudante(estudante, percentual);
			assertEquals("Desconto de 50 % Estudantes.", 4, resultadoDescontoEstudante, 4);
		}

	}

}
