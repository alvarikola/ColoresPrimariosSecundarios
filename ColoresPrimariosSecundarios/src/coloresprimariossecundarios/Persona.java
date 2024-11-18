package coloresprimariossecundarios;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Persona implements Runnable {
    private GestorPinturas gestorPinturas;
    private String nombre;
    private String colorSecundario;
    private int tiempoPreparacion;
    private int tiempoDescanso;
    private final Random random = new Random();
    // Constructor (insert code > constructor > 3 first variable)
    public Persona(GestorPinturas gestorPinturas, String nombre, String colorSecundario) {
        this.gestorPinturas = gestorPinturas;
        this.nombre = nombre;
        this.colorSecundario = colorSecundario;
    }
    
    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getColorSecundario() {
        return colorSecundario;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public int getTiempoDescanso() {
        return tiempoDescanso;
    }
    
    public String getColores() {
        String result = "";
        if (getColorSecundario() == "Azul") result = "Amarillo y Magenta";
        if (getColorSecundario() == "Rojo") result = "Cian y Magenta";
        if (getColorSecundario() == "Verde") result = "Amarillo y Cian";
        return result;
    }
    
    // Run
    @Override
    public void run() {
        while (true) {
            // Tiempo aleatorios.
            tiempoPreparacion = 100 + random.nextInt(401);
            tiempoDescanso = 1000 + random.nextInt(1001);
            // Solicitar colores.
            System.out.printf("La %s quiere preparar color %s durante %d ms.\n",
                    this.getNombre(), this.getColorSecundario(), this.getTiempoPreparacion());           
            gestorPinturas.solicitarColores(this, this.getColorSecundario());            
            try {
                // Prepararlos.
                Thread.sleep(tiempoPreparacion);
            } catch (InterruptedException ex) {
            }
            // Devolverlos.
            gestorPinturas.devolverColores(this, this.getColorSecundario());
            try {
                // Descansar.
                Thread.sleep(tiempoDescanso);
            } catch (InterruptedException ex) {
            }
        }
    }
    
}
