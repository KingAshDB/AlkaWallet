package cl.alkewallet.main;

import java.util.Scanner;
import java.util.Locale;
import cl.alkewallet.modelo.Cuenta;

/**
 * Clase principal que ejecuta la aplicación de consola Alke Wallet.
 * Ahora incluye la creación dinámica de usuario.
 */
public class Ejecutable {

    public static void main(String[] args) {
        // Configuramos Scanner para aceptar punto (.) como decimal
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        
        System.out.println("--- BIENVENIDO A LA CONFIGURACIÓN INICIAL ---");
        
        // 1. SOLICITAMOS LOS DATOS DEL USUARIO
        System.out.print("Por favor, ingrese su nombre: ");
        // Usamos next() para leer una palabra. Si quieres nombre y apellido usa scanner.next() + scanner.nextLine();
        String nombreUsuario = scanner.next(); 
        
        System.out.print("Ingrese su número de cuenta deseado: ");
        int numeroCuenta = 0;
        if(scanner.hasNextInt()) {
            numeroCuenta = scanner.nextInt();
        } else {
            scanner.next(); // Limpiar error
            numeroCuenta = 123456; // Valor por defecto si fallan
            System.out.println("Entrada inválida. Se asignó cuenta: " + numeroCuenta);
        }

        System.out.print("Ingrese su saldo inicial: ");
        double saldoInicial = 0;
        if(scanner.hasNextDouble()) {
            saldoInicial = scanner.nextDouble();
        } else {
            scanner.next(); // Limpiar error
            System.out.println("Entrada inválida. Iniciando en 0.0");
        }

        // 2. CREAMOS EL OBJETO (LA CUENTA) CON LOS DATOS INGRESADOS
        Cuenta miCuenta = new Cuenta(nombreUsuario, saldoInicial, numeroCuenta);
        
        int opcion = 0;

        System.out.println("\n--------------------------------");
        System.out.println("   HOLA, " + miCuenta.getTitular().toUpperCase());
        System.out.println("   Tu cuenta #" + numeroCuenta + " está lista.");
        System.out.println("--------------------------------");

        // 3. INICIO DEL MENÚ (IGUAL QUE ANTES)
        while (opcion != 5) {
            mostrarMenu();
            
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                procesarOpcion(opcion, scanner, miCuenta);
            } else {
                System.out.println("Error: Por favor ingrese un número válido.");
                scanner.next(); 
            }
        }
        
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n¿Qué deseas hacer?");
        System.out.println("1. Consultar saldo");
        System.out.println("2. Depositar fondos");
        System.out.println("3. Retirar fondos");
        System.out.println("4. Convertir moneda (Simulación USD)");
        System.out.println("5. Salir");
        System.out.print("Elige una opción: ");
    }

    private static void procesarOpcion(int opcion, Scanner scanner, Cuenta cuenta) {
        switch (opcion) {
            case 1:
                System.out.println(">> Tu saldo actual es: $" + cuenta.consultarSaldo());
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
                double saldoUSD = cuenta.convertirMoneda(0.0011);
                System.out.println(">> Saldo aproximado en USD: $" + saldoUSD);
                break;
            case 5:
                System.out.println("Cerrando sesión... ¡Gracias por usar Alke Wallet!");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }
}