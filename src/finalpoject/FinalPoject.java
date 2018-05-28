/**
 * Juego de apuestas de caballos
 *
 */
package finalpoject;

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
        h.setSize(700,650);
        h.setVisible(true);
        h.setResizable(false);
        h.setLocationRelativeTo(null);
        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE/*DO_NOTHING_ON_CLOSE*/);
    }

}
