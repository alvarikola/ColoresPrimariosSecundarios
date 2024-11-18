package coloresprimariossecundarios;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GestorPinturas {
    private boolean amarilloDisponible = true;
    private boolean cianDisponible = true;
    private boolean magentaDisponible = true;
    
    synchronized public void solicitarColores(Persona persona, String color){
        while ( (color.equals("Azul") && (!magentaDisponible || !cianDisponible)) ||
                (color.equals("Rojo") && (!amarilloDisponible || !magentaDisponible)) || 
                (color.equals("Verde") && (!amarilloDisponible || !cianDisponible))) {
           
            System.out.printf("--- La %s espera para preparar el color %s. Necesita %s.\n",
                    persona.getNombre(), color, persona.getColores());
            
            try {
                this.wait();
            } catch (InterruptedException ex) {
            }
        }
        System.out.printf(">>> La %s empieza a preparar el color %s. Utiliza %s.Tardará %d ms.\n",
                    persona.getNombre(), color, persona.getColores(), persona.getTiempoPreparacion());
        
        if (color.equals("Azul")) {
            magentaDisponible = false;
            cianDisponible = false;
        } 
        if (color.equals("Rojo")) {
            magentaDisponible = false;
            amarilloDisponible = false;
        }
        if (color.equals("Verde")) {
            amarilloDisponible = false;
            cianDisponible = false;
        }        
    }

    synchronized public void devolverColores(Persona persona, String color) {
        System.out.printf(">>> La %s termina de preparar el color %s. Devuelve %s.Descansara %d ms.\n",
                    persona.getNombre(), color, persona.getColores(), persona.getTiempoDescanso());
        
        if (color.equals("Azul")) {
            magentaDisponible = true;
            cianDisponible = true;
        } 
        if (color.equals("Rojo")) {
            magentaDisponible = true;
            amarilloDisponible = true;
        }
        if (color.equals("Verde")) {
            amarilloDisponible = true;
            cianDisponible = true;
        }
        this.notifyAll();
    }
    
}
