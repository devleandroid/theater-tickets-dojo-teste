package src.umov.ticket.teste;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.CsvSource;
import src.umov.ticket.DiaSemana;
import src.umov.ticket.TheaterTicketCalculator;
import src.umov.ticket.TipoIngresso;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static src.umov.ticket.DiaSemana.*;
import static src.umov.ticket.TipoIngresso.*;

class TheaterTicketCalculatorTest {

    private TheaterTicketCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new TheaterTicketCalculator();
    }

    @Test
    @DisplayName("Deve retornar preços base corretos para cada tipo de ingresso")
    void testPrecosBase() {
        assertEquals(5.50, calculator.getPrecoBase(CRIANCA), 0.001);
        assertEquals(8.00, calculator.getPrecoBase(ESTUDANTE), 0.001);
        assertEquals(6.00, calculator.getPrecoBase(IDOSO), 0.001);
    }

    @Test
    @DisplayName("Segunda-feira: 10% desconto para todos")
    void testSegundaFeira() {
        // 10% desconto para todos
        assertEquals(4.95, calculator.calcularPreco(CRIANCA, SEGUNDA, false), 0.001);     // 5.50 - 10%
        assertEquals(7.20, calculator.calcularPreco(ESTUDANTE, SEGUNDA, false), 0.001);   // 8.00 - 10%
        assertEquals(5.40, calculator.calcularPreco(IDOSO, SEGUNDA, false), 0.001);       // 6.00 - 10%
    }

    @Test
    @DisplayName("Terça-feira: descontos específicos")
    void testTercaFeira() {
        // 15% idosos e crianças, 5% estudantes
        assertEquals(4.675, calculator.calcularPreco(CRIANCA, TERCA, false), 0.001);      // 5.50 - 15%
        assertEquals(7.60, calculator.calcularPreco(ESTUDANTE, TERCA, false), 0.001);     // 8.00 - 5%
        assertEquals(5.10, calculator.calcularPreco(IDOSO, TERCA, false), 0.001);         // 6.00 - 15%
    }

    @Test
    @DisplayName("Quarta-feira: descontos altos")
    void testQuartaFeira() {
        // 40% idosos, 30% crianças, 50% estudantes
        assertEquals(3.85, calculator.calcularPreco(CRIANCA, QUARTA, false), 0.001);      // 5.50 - 30%
        assertEquals(4.00, calculator.calcularPreco(ESTUDANTE, QUARTA, false), 0.001);    // 8.00 - 50%
        assertEquals(3.60, calculator.calcularPreco(IDOSO, QUARTA, false), 0.001);        // 6.00 - 40%
    }

    @Test
    @DisplayName("Quinta-feira: 30% idosos e estudantes")
    void testQuintaFeira() {
        // 30% idosos e estudantes
        assertEquals(5.50, calculator.calcularPreco(CRIANCA, QUINTA, false), 0.001);      // Sem desconto
        assertEquals(5.60, calculator.calcularPreco(ESTUDANTE, QUINTA, false), 0.001);    // 8.00 - 30%
        assertEquals(4.20, calculator.calcularPreco(IDOSO, QUINTA, false), 0.001);        // 6.00 - 30%
    }

    @Test
    @DisplayName("Sexta-feira: 11% crianças")
    void testSextaFeira() {
        // 11% crianças
        assertEquals(4.895, calculator.calcularPreco(CRIANCA, SEXTA, false), 0.001);      // 5.50 - 11%
        assertEquals(8.00, calculator.calcularPreco(ESTUDANTE, SEXTA, false), 0.001);     // Sem desconto
        assertEquals(6.00, calculator.calcularPreco(IDOSO, SEXTA, false), 0.001);         // Sem desconto
    }

    @Test
    @DisplayName("Finais de semana: 5% idosos")
    void testFinaisDeSemana() {
        // 5% idosos em sábado, domingo e feriados
        assertEquals(5.70, calculator.calcularPreco(IDOSO, SABADO, false), 0.001);        // 6.00 - 5%
        assertEquals(5.70, calculator.calcularPreco(IDOSO, DOMINGO, false), 0.001);       // 6.00 - 5%
        assertEquals(5.70, calculator.calcularPreco(IDOSO, FERIADO, false), 0.001);       // 6.00 - 5%

        // Crianças e estudantes sem desconto
        assertEquals(5.50, calculator.calcularPreco(CRIANCA, SABADO, false), 0.001);
        assertEquals(8.00, calculator.calcularPreco(ESTUDANTE, SABADO, false), 0.001);
    }

    @Test
    @DisplayName("Estudante com carteirinha: 35% desconto em dias úteis")
    void testEstudanteComCarteirinhaDiasUteis() {
        // 35% desconto para estudantes com carteirinha em dias úteis
        assertEquals(5.20, calculator.calcularPreco(ESTUDANTE, SEGUNDA, true), 0.001);    // 8.00 - 35%
        assertEquals(5.20, calculator.calcularPreco(ESTUDANTE, TERCA, true), 0.001);      // 8.00 - 35%
        assertEquals(5.20, calculator.calcularPreco(ESTUDANTE, QUARTA, true), 0.001);     // 8.00 - 35%
        assertEquals(5.20, calculator.calcularPreco(ESTUDANTE, QUINTA, true), 0.001);     // 8.00 - 35%
        assertEquals(5.20, calculator.calcularPreco(ESTUDANTE, SEXTA, true), 0.001);      // 8.00 - 35%
    }

    @Test
    @DisplayName("Estudante com carteirinha: sem desconto extra em finais de semana")
    void testEstudanteComCarteirinhaFinaisSemana() {
        // Sem desconto de 35% em finais de semana
        assertEquals(8.00, calculator.calcularPreco(ESTUDANTE, SABADO, true), 0.001);     // Preço base
        assertEquals(8.00, calculator.calcularPreco(ESTUDANTE, DOMINGO, true), 0.001);    // Preço base
        assertEquals(8.00, calculator.calcularPreco(ESTUDANTE, FERIADO, true), 0.001);    // Preço base
    }

    @Test
    @DisplayName("Desconto de estudante prevalece sobre descontos do dia")
    void testPrecedenciaDescontoEstudante() {
        // Na quarta-feira, estudante com carteirinha deve ter 35% (não 50%)
        assertEquals(5.20, calculator.calcularPreco(ESTUDANTE, QUARTA, true), 0.001);     // 35% do desconto estudante

        // Na quinta-feira, estudante com carteirinha deve ter 35% (não 30%)
        assertEquals(5.20, calculator.calcularPreco(ESTUDANTE, QUINTA, true), 0.001);     // 35% do desconto estudante
    }

    @Test
    @DisplayName("Crianças e idosos não têm desconto de estudante")
    void testCriancasIdososSemDescontoEstudante() {
        // Crianças e idosos não recebem desconto de estudante mesmo com carteirinha
        assertEquals(4.95, calculator.calcularPreco(CRIANCA, SEGUNDA, true), 0.001);      // 10% normal
        assertEquals(5.40, calculator.calcularPreco(IDOSO, SEGUNDA, true), 0.001);        // 10% normal
    }

    @ParameterizedTest
    @DisplayName("Testes parametrizados para verificar consistência")
    @MethodSource("providerCasosTeste")
    void testCasosParametrizados(TipoIngresso tipo, DiaSemana dia, boolean carteirinha, double esperado) {
        double resultado = calculator.calcularPreco(tipo, dia, carteirinha);
        assertEquals(esperado, resultado, 0.001,
                String.format("Falha para %s, %s, carteirinha: %s", tipo, dia, carteirinha));
    }

    private static Stream<Arguments> providerCasosTeste() {
        return Stream.of(
                // (tipo, dia, carteirinha, preço_esperado)
                Arguments.of(CRIANCA, SEGUNDA, false, 4.95),
                Arguments.of(ESTUDANTE, SEGUNDA, false, 7.20),
                Arguments.of(ESTUDANTE, SEGUNDA, true, 5.20),
                Arguments.of(IDOSO, QUARTA, false, 3.60),
                Arguments.of(CRIANCA, SEXTA, false, 4.895),
                Arguments.of(IDOSO, SABADO, false, 5.70),
                Arguments.of(ESTUDANTE, SABADO, true, 8.00),
                Arguments.of(ESTUDANTE, QUARTA, true, 5.20)
        );
    }

    @ParameterizedTest
    @DisplayName("Testes CSV para casos críticos")
    @CsvSource({
            "CRIANCA, SEGUNDA, false, 4.95",
            "ESTUDANTE, TERCA, true, 5.20",
            "IDOSO, QUARTA, false, 3.60",
            "ESTUDANTE, SABADO, true, 8.00"
    })
    void testCasosCSV(TipoIngresso tipo, DiaSemana dia, boolean carteirinha, double esperado) {
        double resultado = calculator.calcularPreco(tipo, dia, carteirinha);
        assertEquals(esperado, resultado, 0.001);
    }

    @Test
    @DisplayName("Deve garantir que descontos não são cumulativos")
    void testDescontosNaoCumulativos() {
        // Estudante na quarta COM carteirinha: só 35%, não 35% + 50%
        double precoEstudanteQuarta = calculator.calcularPreco(ESTUDANTE, QUARTA, true);
        assertEquals(5.20, precoEstudanteQuarta, 0.001);

        // Verificar que não é menor que o desconto de estudante
        assertTrue(precoEstudanteQuarta >= 5.20);
    }

    @Test
    @DisplayName("Deve identificar corretamente finais de semana")
    void testIdentificacaoFinaisSemana() {
        TheaterTicketCalculator calc = new TheaterTicketCalculator();

        // Usando reflection para testar funcao privado (em produção, considere refatorar)
        // Alternativa: testar através do comportamento público
        assertEquals(8.00, calc.calcularPreco(ESTUDANTE, SABADO, true), 0.001);
        assertEquals(8.00, calc.calcularPreco(ESTUDANTE, DOMINGO, true), 0.001);
        assertEquals(8.00, calc.calcularPreco(ESTUDANTE, FERIADO, true), 0.001);
    }
}