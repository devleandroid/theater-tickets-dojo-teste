package src.umov.ticket.teste;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import src.umov.ticket.CalculaIngreco;

public class TesteQuintaFeira {

	// Calcule os seguintes descontos para as entradas dado o preço dos ingressos:
	// Crianças: R$ 5.50
	// Estudantes: R$ 8.00
	// Idosos: R$ 6.00 	
	// Quinta-Feira
    // 30% idosos e estudantes
	@Test
	public void testeDescontoIdoso() {

		double idoso = 6.00;
		double percentual = 30;

		CalculaIngreco calculaDesconto = new CalculaIngreco();

		double resultadoDescontoIdoso = calculaDesconto.calculaDescontoIdoso(idoso, percentual);

		assertEquals("Desconto de 30 % Idosos.", 1.8, resultadoDescontoIdoso, 4.2);
	}

	@Test
	public void testeDescontoEstudante() {

		double estudante = 8.00;
		double percentual = 30;

		CalculaIngreco calculaDesconto = new CalculaIngreco();

		double resultadoDescontoEstudante;

		boolean carteira = true;
		if (carteira == true) {
			double descontoCarteira = 35;

			resultadoDescontoEstudante = calculaDesconto.calculaDescontoEstudante(estudante, descontoCarteira);
			assertEquals("Desconto de 35 % Estudantes que apresentar a carteira.", 2.8, resultadoDescontoEstudante, 5.2);
		} else if (carteira != true) {
			resultadoDescontoEstudante = calculaDesconto.calculaDescontoEstudante(estudante, percentual);
			assertEquals("Desconto de 30 % Estudantes.", 2.4, resultadoDescontoEstudante, 5.6);
		}

	}

}
