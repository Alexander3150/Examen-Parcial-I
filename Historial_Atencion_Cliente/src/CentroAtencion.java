import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class CentroAtencion 
{
    private Queue<String[]> colaSolicitudes; // Cola para la solicitudes de los clientes
    private Stack<String[]> pilaHistorial; //  Pila para el historial de atencion  cuando un agente atiende a un cliente
    private Map<String, Cliente> clientes; // Base de datos con un arreglo 
    private Cliente clientesFrecuentes; // Lista enlazada para los clientes frecuentes 

    // Constructor 
    public CentroAtencion() 
    {
        colaSolicitudes = new LinkedList<>();
        pilaHistorial = new Stack<>();
        clientes = new HashMap<>();
        clientesFrecuentes = null; // Este es mi puntero para la almacenarlo en la lista enlazada con mas de 5 solicitudes 
    }

    // Utilizar cola para almacenar la solicitud del cliente 
    public void registrarSolicitud(String nombre, String descripcion) 
    {
        clientes.putIfAbsent(nombre, new Cliente(nombre));  // Si el cliente no existe, se agrega.
        clientes.get(nombre).solicitudes++;  // Se incrementa el contador de solicitudes del cliente.

        colaSolicitudes.add(new String[]{nombre, descripcion}); // Se almacena la solicitud en la cola.
        JOptionPane.showMessageDialog(null, "Solicitud registrada: " + nombre + " - " + descripcion);

        agregarClienteFrecuente(clientes.get(nombre));  // Si el cliente supera 5 solicitudes, se agrega a la lista enlazada.
    }
    
    // Atender clientes con una pila
    public void atenderSolicitud() {
        if (colaSolicitudes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay solicitudes pendientes.");
            return;
        }
        String[] solicitud = colaSolicitudes.poll();  // Se obtiene la primera solicitud de la cola y se elimina.
        pilaHistorial.push(solicitud);  // Se almacena la solicitud atendida en la pila.
        JOptionPane.showMessageDialog(null, "Atendiendo solicitud de: " + solicitud[0] + " - " + solicitud[1]);
    }
    
    // Historial de atencion por la pila
    public void consultarHistorial() {
        if (pilaHistorial.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay historial de atenciones.");
            return;
        }
        String[] ultimaAtencion = pilaHistorial.peek();  // Se obtiene la última solicitud atendida sin eliminarla.
        JOptionPane.showMessageDialog(null, "Último caso atendido: " + ultimaAtencion[0] + " - " + ultimaAtencion[1]);
    }
    
    // Lista enlazada simple de clientes frecuentes 
    private void agregarClienteFrecuente(Cliente cliente) {
        if (cliente.solicitudes > 5) {  // Si tiene más de 5 solicitudes
            if (clientesFrecuentes == null) {
                clientesFrecuentes = cliente;  // Primer cliente frecuente
            } else {
                Cliente actual = clientesFrecuentes;
    
                // Verificar si el cliente ya está en la lista para evitar duplicados
                while (actual != null) {
                    if (actual.nombre.equals(cliente.nombre)) {
                        return;  // El cliente ya está en la lista, no lo agregamos de nuevo.
                    }
                    if (actual.siguiente == null) {
                        break;  // Se detiene en el último nodo.
                    }
                    actual = actual.siguiente;
                }
                
                actual.siguiente = cliente;  // Agregar al final de la lista
            }
        }
    }
    
    // Mostrar clientes frecuentes 
    public void mostrarClientesFrecuentes() {
        if (clientesFrecuentes == null) {
            JOptionPane.showMessageDialog(null, "No hay clientes frecuentes.");
            return;
        }
        
        Cliente actual = clientesFrecuentes;
        StringBuilder listaClientes = new StringBuilder("Lista de clientes frecuentes:\n");
    
        while (actual != null) {
            try {
                if (actual.nombre == null) {
                    throw new NullPointerException("El cliente tiene un nombre nulo.");
                }
                if (actual.solicitudes < 0) {
                    throw new IllegalArgumentException("Número de solicitudes inválido para el cliente: " + actual.nombre);
                }
    
                listaClientes.append("- ").append(actual.nombre).append(" (").append(actual.solicitudes).append(" solicitudes)\n");
                actual = actual.siguiente;
                
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                actual = actual.siguiente; // Continúa con el siguiente nodo
                
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Advertencia: " + e.getMessage());
                actual = actual.siguiente; // Continúa con el siguiente nodo
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado: " + e.getMessage());
                break; // Si es un error desconocido, detiene la iteración
            }
        }
        
        JOptionPane.showMessageDialog(null, listaClientes.toString());
    }
}
