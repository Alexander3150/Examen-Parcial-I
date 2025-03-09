import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        CentroAtencion centro = new CentroAtencion();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0; // Se inicializa para evitar errores de variable no asignada

        do {
            try {
                System.out.println("\n--- Menú Centro de Atención ---");
                System.out.println("1. Registrar solicitud");
                System.out.println("2. Atender solicitud");
                System.out.println("3. Consultar historial");
                System.out.println("4. Mostrar clientes frecuentes");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");

                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el nombre del cliente: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese la descripción de la solicitud: ");
                        String descripcion = scanner.nextLine();
                        centro.registrarSolicitud(nombre, descripcion);
                        break;
                    case 2:
                        centro.atenderSolicitud();
                        break;
                    case 3:
                        centro.consultarHistorial();
                        break;
                    case 4:
                        centro.mostrarClientesFrecuentes();
                        break;
                    case 5:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.nextLine(); // Limpiar el buffer del scanner para evitar bucles infinitos
            }
        } while (opcion != 5); 

        scanner.close();
    }
}
