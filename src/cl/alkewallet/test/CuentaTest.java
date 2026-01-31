package cl.alkewallet.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import cl.alkewallet.modelo.Cuenta;

/**
 * Clase de pruebas unitarias para la clase Cuenta usando JUnit 5.
 */
class CuentaTest {

    private Cuenta cuenta;

    /**
     * Configuración inicial antes de cada prueba.
     * Resetea la cuenta para que las pruebas no interfieran entre sí.
     */
    @BeforeEach
    void setUp() {
        cuenta = new Cuenta("Test User", 1000.0, 12345);
    }

    @Test
    void testConsultarSaldoInicial() {
        assertEquals(1000.0, cuenta.consultarSaldo(), "El saldo inicial debería ser 1000");
    }

    @Test
    void testDepositar() {
        cuenta.depositar(500.0);
        assertEquals(1500.0, cuenta.consultarSaldo(), "El saldo debería aumentar a 1500 tras depositar 500");
    }
    
    @Test
    void testDepositarNegativo() {
        cuenta.depositar(-200.0);
        assertEquals(1000.0, cuenta.consultarSaldo(), "El saldo no debe cambiar si el depósito es negativo");
    }

    @Test
    void testRetirarExitoso() {
        boolean resultado = cuenta.retirar(200.0);
        assertTrue(resultado, "El retiro debería ser verdadero (exitoso)");
        assertEquals(800.0, cuenta.consultarSaldo(), "El saldo debería bajar a 800");
    }

    @Test
    void testRetirarFallidoPorFondos() {
        boolean resultado = cuenta.retirar(2000.0); // Más de lo que tiene
        assertFalse(resultado, "El retiro debería ser falso (fallido)");
        assertEquals(1000.0, cuenta.consultarSaldo(), "El saldo no debe cambiar si no hay fondos");
    }
    
    @Test
    void testConvertirMoneda() {
        // 1000 * 0.0011 = 1.1
        double valorConvertido = cuenta.convertirMoneda(0.0011);
        assertEquals(1.1, valorConvertido, 0.001, "La conversión no es correcta");
    }
}