/**
 * Juego de apuestas de caballos
 *
 */
package finalpoject;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.swing.JFrame;

/**
 *
 * @author Javier
 */
public class FinalPoject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Hipódromo h = new Hipódromo();
        h.setSize(700, 650);
        h.setVisible(true);
        h.setResizable(false);
        h.setLocationRelativeTo(null);
        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE/*DO_NOTHING_ON_CLOSE*/);
        
        Calendar calendario = Calendar.getInstance();
        try (PrintWriter pw = new PrintWriter(new FileWriter("historialApuestas.txt", true))) {
            pw.println("");
            pw.println("NUEVA ENTRADA "+calendario.get(Calendar.HOUR_OF_DAY)+":"+calendario.get(Calendar.MINUTE)+"-"+calendario.get(Calendar.DATE));
            pw.println("-------------------");
        } catch (Exception ex) {
            System.err.println("Error guardado de apuesta");
        }
    }

}
