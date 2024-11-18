package coloresprimariossecundarios;

public class ColoresPrimariosSecundarios {

    public static void main(String[] args) {
        // Crear el recurso compartido (Botes de pintura)
        GestorPinturas gestorPinturas = new GestorPinturas();
        // Crear los hilos
        Thread persona1 = new Thread(new Persona(gestorPinturas, "Persona 1", "Rojo"));
        Thread persona2 = new Thread(new Persona(gestorPinturas, "Persona 2", "Azul"));
        Thread persona3 = new Thread(new Persona(gestorPinturas, "Persona 3", "Verde"));
        // Ejecutar los hilos.
        persona1.start();
        persona2.start();
        persona3.start();
    }
}
