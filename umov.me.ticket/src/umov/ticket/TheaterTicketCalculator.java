package src.umov.ticket;


public class TheaterTicketCalculator {

    // Preços base dos ingressos
    private static final double PRECO_CRIANCA = 5.50;
    private static final double PRECO_ESTUDANTE = 8.00;
    private static final double PRECO_IDOSO = 6.00;

    public double calcularPreco(TipoIngresso tipo, DiaSemana dia, boolean temCarteirinhaEstudante) {
        double precoBase = getPrecoBase(tipo);
        double desconto = 0.0;

        // Aplicar desconto de estudante com carteirinha (exceto finais de semana)
        if (tipo == TipoIngresso.ESTUDANTE && temCarteirinhaEstudante && !isFinalDeSemana(dia)) {
            desconto = 0.35; // 35% de desconto
        } else {
            // Aplicar descontos específicos do dia
            desconto = calcularDescontoDia(tipo, dia);
        }

        return precoBase * (1 - desconto);
    }

    public double getPrecoBase(TipoIngresso tipo) {
        switch (tipo) {
            case CRIANCA: return PRECO_CRIANCA;
            case ESTUDANTE: return PRECO_ESTUDANTE;
            case IDOSO: return PRECO_IDOSO;
            default: return 0.0;
        }
    }

    public double calcularDescontoDia(TipoIngresso tipo, DiaSemana dia) {
        switch (dia) {
            case SEGUNDA:
                return 0.10; // 10% para todos

            case TERCA:
                if (tipo == TipoIngresso.IDOSO || tipo == TipoIngresso.CRIANCA) {
                    return 0.15; // 15% idosos e crianças
                } else if (tipo == TipoIngresso.ESTUDANTE) {
                    return 0.05; // 5% estudantes
                }
                break;

            case QUARTA:
                if (tipo == TipoIngresso.IDOSO) {
                    return 0.40; // 40% idosos
                } else if (tipo == TipoIngresso.CRIANCA) {
                    return 0.30; // 30% crianças
                } else if (tipo == TipoIngresso.ESTUDANTE) {
                    return 0.50; // 50% estudantes
                }
                break;

            case QUINTA:
                if (tipo == TipoIngresso.IDOSO || tipo == TipoIngresso.ESTUDANTE) {
                    return 0.30; // 30% idosos e estudantes
                }
                break;

            case SEXTA:
                if (tipo == TipoIngresso.CRIANCA) {
                    return 0.11; // 11% crianças
                }
                break;

            case SABADO: case DOMINGO: case FERIADO:
                if (tipo == TipoIngresso.IDOSO) {
                    return 0.05; // 5% idosos
                }
                break;
        }

        return 0.0; // Sem desconto
    }

    private boolean isFinalDeSemana(DiaSemana dia) {
        return dia == DiaSemana.SABADO || dia == DiaSemana.DOMINGO || dia == DiaSemana.FERIADO;
    }
}
