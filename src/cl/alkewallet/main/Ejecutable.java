package cl.alkewallet.main;

import java.util.Scanner;
import java.util.Locale; // Importante para manejar puntos decimales
import cl.alkewallet.modelo.Cuenta;

/**
 * Clase principal que ejecuta la aplicación de consola Alke Wallet.
 * Contiene el menú de interacción con el usuario.
 * * @author TuNombre
 * @version 1.0
 */
public class Ejecutable {

    /**
     * Método principal de entrada al programa.
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        // Configuramos Scanner para aceptar punto (.) como decimal
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        
        // Datos simulados (Mock data)
        Cuenta miCuenta = new Cuenta("Estudiante Alkemy", 50000.0, 1001);
        int opcion = 0;

        System.out.println("--------------------------------");
        System.out.println("   BIENVENIDO A ALKE WALLET");
        System.out.println("   Usuario: " + miCuenta.getTitular());
        System.out.println("--------------------------------");

        while (opcion != 5) {
            mostrarMenu();
            
            // Validación para evitar que el programa falle si escriben letras
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                procesarOpcion(opcion, scanner, miCuenta);
            } else {
                System.out.println("Error: Por favor ingrese un número válido.");
                scanner.next(); // Limpiar el buffer incorrecto
            }
        }
        
        scanner.close();
    }

    /**
     * Muestra las opciones disponibles en consola.
     */
    private static void mostrarMenu() {
        System.out.println("\nSeleccione una operación:");
        System.out.println("1. Consultar saldo");
        System.out.println("2. Depositar fondos");
        System.out.println("3. Retirar fondos");
        System.out.println("4. Convertir moneda (Simulación USD)");
        System.out.println("5. Salir");
        System.out.print("Su elección: ");
    }

    /**
     * Procesa la opción seleccionada por el usuario.
     * * @param opcion El número de opción elegido.
     * @param scanner El objeto Scanner para leer montos.
     * @param cuenta La cuenta sobre la que se opera.
     */
    private static void procesarOpcion(int opcion, Scanner scanner, Cuenta cuenta) {
        switch (opcion) {
            case 1:
                System.out.println("Saldo actual: $" + cuenta.consultarSaldo());
                break;
            case 2:
                System.out.print("Ingrese monto a depositar: ");
                if (scanner.hasNextDouble()) {
                    cuenta.depositar(scanner.nextDouble());
                } else {
                    System.out.println("Monto inválido.");
                    scanner.next();
                }
                break;
            case 3:
                System.out.print("Ingrese monto a retirar: ");
                if (scanner.hasNextDouble()) {
                    cuenta.retirar(scanner.nextDouble());
                } else {
                    System.out.println("Monto inválido.");
                    scanner.next();
                }
                break;
            case 4:
                // Tasa de cambio ejemplo: 1 peso = 0.0011 USD
                double saldoUSD = cuenta.convertirMoneda(0.0011);
                System.out.println("Su saldo en Dólares es aprox: US$" + saldoUSD);
                break;
            case 5:
                System.out.println("Gracias por usar Alke Wallet. ¡Hasta pronto!");
                break;
            default:
                System.out.println("Opción no reconocida, intente nuevamente.");
        }
    }
}