package src.umov.ticket;

class CalculatorTicketMain {
    public static void main(String[] args) {
        TheaterTicketCalculator calculator = new TheaterTicketCalculator();

        // Testes
        System.out.println("=== TESTES ===");

        // Segunda-feira
        System.out.printf("Segunda - Criança: R$ %.2f%n",
                calculator.calcularPreco(TipoIngresso.CRIANCA, DiaSemana.SEGUNDA, false));
        System.out.printf("Segunda - Estudante (sem carteirinha): R$ %.2f%n",
                calculator.calcularPreco(TipoIngresso.ESTUDANTE, DiaSemana.SEGUNDA, false));
        System.out.printf("Segunda - Estudante (com carteirinha): R$ %.2f%n",
                calculator.calcularPreco(TipoIngresso.ESTUDANTE, DiaSemana.SEGUNDA, true));

        // Quarta-feira
        System.out.printf("Quarta - Idoso: R$ %.2f%n",
                calculator.calcularPreco(TipoIngresso.IDOSO, DiaSemana.QUARTA, false));
        System.out.printf("Quarta - Estudante (com carteirinha): R$ %.2f%n",
                calculator.calcularPreco(TipoIngresso.ESTUDANTE, DiaSemana.QUARTA, true));

        // Sábado
        System.out.printf("Sábado - Idoso: R$ %.2f%n",
                calculator.calcularPreco(TipoIngresso.IDOSO, DiaSemana.SABADO, false));
        System.out.printf("Sábado - Estudante (com carteirinha): R$ %.2f%n",
                calculator.calcularPreco(TipoIngresso.ESTUDANTE, DiaSemana.SABADO, true));
    }
}
