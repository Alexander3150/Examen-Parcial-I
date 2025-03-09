public class Cliente {
    // Datos del cliente 
    String nombre;
    int solicitudes;
    // Funciona igual que crear una clase nodo es lo mismo, solo que con diferente nombre.
    Cliente siguiente;

    public Cliente(String nombre) {
        this.nombre = nombre;
        this.solicitudes = 0;
        this.siguiente = null;
    }
}
