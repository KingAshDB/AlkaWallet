package cl.alkewallet.modelo;

/**
 * Representa una cuenta bancaria de un usuario en Alke Wallet.
 * Implementa la interfaz {@link MetodosCuenta} para gestionar fondos.
 * * @author TuNombre
 * @version 1.0
 */
public class Cuenta implements MetodosCuenta {

    /** Nombre del dueño de la cuenta */
    private String titular;
    
    /** Saldo disponible en la cuenta */
    private double saldo;
    
    /** Número único de identificación de la cuenta */
    private int numeroCuenta;

    /**
     * Constructor para inicializar una nueva cuenta.
     * * @param titular Nombre del propietario.
     * @param saldoInicial Dinero con el que abre la cuenta.
     * @param numeroCuenta Identificador numérico.
     */
    public Cuenta(String titular, double saldoInicial, int numeroCuenta) {
        this.titular = titular;
        this.saldo = saldoInicial;
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * {@inheritDoc}
     * Valida que el monto sea positivo antes de sumar.
     */
    @Override
    public void depositar(double monto) {
        if (monto > 0) {
            this.saldo += monto;
            System.out.println("Depósito de $" + monto + " realizado con éxito.");
        } else {
            System.out.println("Error: El monto a depositar debe ser positivo.");
        }
    }

    /**
     * {@inheritDoc}
     * Verifica que el monto sea positivo y que exista saldo suficiente.
     */
    @Override
    public boolean retirar(double monto) {
        if (monto > 0 && this.saldo >= monto) {
            this.saldo -= monto;
            System.out.println("Retiro de $" + monto + " realizado con éxito.");
            return true;
        } else {
            System.out.println("Error: Saldo insuficiente o monto inválido.");
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double consultarSaldo() {
        return this.saldo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double convertirMoneda(double tasaCambio) {
        return this.saldo * tasaCambio;
    }

    // Getters y Setters básicos si se necesitaran en el futuro
    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }
}