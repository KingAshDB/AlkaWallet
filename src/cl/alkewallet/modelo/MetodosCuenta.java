package cl.alkewallet.modelo;

/**
 * Define las operaciones financieras básicas que debe tener cualquier cuenta
 * dentro del sistema Alke Wallet.
 * * @author TuNombre
 * @version 1.0
 */
public interface MetodosCuenta {

    /**
     * Realiza un depósito de dinero en la cuenta.
     * * @param monto La cantidad a depositar (debe ser mayor a 0).
     */
    void depositar(double monto);

    /**
     * Realiza un retiro de dinero de la cuenta si hay fondos suficientes.
     * * @param monto La cantidad a retirar.
     * @return true si la operación fue exitosa, false si falló.
     */
    boolean retirar(double monto);

    /**
     * Consulta el saldo actual disponible en la cuenta.
     * * @return El saldo actual como double.
     */
    double consultarSaldo();

    /**
     * Calcula el valor del saldo actual en una moneda extranjera.
     * * @param tasaCambio El factor de conversión (ej. 0.0011 para CLP a USD).
     * @return El valor convertido.
     */
    double convertirMoneda(double tasaCambio);
}