import javax.swing.JOptionPane;
public class App {
    public static void main(String[] args) {
        CentroAtencion centro = new CentroAtencion();
        int opcion = 0; // Se inicializa para evitar errores de variable no asignada

        do {
            try {
                // Mostrar el menú en un cuadro de diálogo
                String input = JOptionPane.showInputDialog(null,
                        "--- Menú Centro de Atención ---\n" +
                                "1. Registrar solicitud\n" +
                                "2. Atender solicitud\n" +
                                "3. Consultar historial\n" +
                                "4. Mostrar clientes frecuentes\n" +
                                "5. Salir\n" +
                                "Seleccione una opción:",
                        "Centro de Atención", JOptionPane.QUESTION_MESSAGE);
                
                if (input == null) {
                    // Si el usuario cancela, salir del bucle
                    break;
                }
                
                opcion = Integer.parseInt(input);
                
                switch (opcion) {
                    case 1:
                        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del cliente:", "Registrar solicitud", JOptionPane.QUESTION_MESSAGE);
                        String descripcion = JOptionPane.showInputDialog(null, "Ingrese la descripción de la solicitud:", "Registrar solicitud", JOptionPane.QUESTION_MESSAGE);
                        
                        if (nombre != null && descripcion != null) {
                            centro.registrarSolicitud(nombre, descripcion);
                        }
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
                        JOptionPane.showMessageDialog(null, "Saliendo del programa...", "Salir", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida. Intente de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (opcion != 5);
    }
}
