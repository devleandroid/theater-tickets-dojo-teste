package src.umov.ticket;

public class CalculaIngreco {

	public double calculaDescontoCrianca(double crianca, double percentual) {
		double result = (crianca * percentual)/100;
		return result;
	}

	public double calculaDescontoIdoso(double idoso, double percentual) {
		return (idoso * percentual)/100;
	}

	public double calculaDescontoEstudante(double estudante, double percentual) {
		return (estudante * percentual)/100;
	}

}
