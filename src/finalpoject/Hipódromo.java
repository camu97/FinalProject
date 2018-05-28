/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalpoject;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import sun.net.www.content.text.plain;

/**
 *
 * @author Javier
 */
public class Hipódromo extends JFrame implements ActionListener {

//    private double[] cuota;
    private JLabel lblTitulo, lblImg, lblSaldo;
    private JButton btnSaldo, btnApuesta, btnCarreras;
    private JMenuBar menu;
    private JMenu info, salir;
    private JMenuItem miAyuda, miInfo, miSalir;
    private double saldo = 10.00;
    private String mensajeAyuda = "Para poder apostar primero debes ingresa dinero.\n"
            + "Con el nuevo bono \"El todo por el todo\", le doblamos el primer ingreso.\n"
            + "Para poder ver carreras sin apostar deberá tener dinero en la cuenta y seleccionar"
            + " \"CaballosTV\", que emite todas las carreras de caballos que hay dentro del recinto.\n"
            + "La opción de \"Comprobar saldo\" le permitirá ver si tiene saldo en su cuenta, su historial"
            + " de apuestas y le da la posibilidad de retirar el dinero, siempre que cumpla los requisitos "
            + "minimos de retirada(30 euros)\n"
            + "Para más información póngase en contacto con nosotros en la siguiente dirección:  "
            + "NOTMO@hipodromovivas.com";

    public Hipódromo() {
        super("NOTMO");
        setLayout(null);
        setFocusable(true);

        menu = new JMenuBar();
        this.setJMenuBar(menu);

        info = new JMenu("Información");
        info.setMnemonic('i');

        salir = new JMenu("Salir");
        salir.setMnemonic('s');

        miAyuda = new JMenuItem("Ayuda");
        miAyuda.setMnemonic('a');
        miAyuda.addActionListener(this);
        info.add(miAyuda);
        info.addSeparator();
        miInfo = new JMenuItem("Acerca de...");
        miInfo.setMnemonic('i');
        miInfo.addActionListener(this);
        info.add(miInfo);

        miSalir = new JMenuItem("Salir");
        miSalir.setMnemonic('s');
        miSalir.addActionListener(this);
        salir.add(miSalir);

        menu.add(info);
        menu.add(salir);

        lblTitulo = new JLabel("<html><h1>Hipódromo NOTMO</h1></html>");
        lblTitulo.setSize(500, 50);
        lblTitulo.setLocation(225, 10);
        this.add(lblTitulo);

        lblImg = new JLabel(new ImageIcon("caballo.png"));
        lblImg.setSize(450, 510);
        lblImg.setLocation(250, 50);
        this.add(lblImg);

        btnApuesta = new JButton("<html>Apostar</html>");
        btnApuesta.setSize(150, 80);
        btnApuesta.setLocation(50, 150);
        btnApuesta.addActionListener(this);
        this.add(btnApuesta);

        btnCarreras = new JButton("<html><span style=\"color:blue;\">CaballosTV</span><br/>Ver streaming</html>");
        btnCarreras.setSize(150, 80);
        btnCarreras.setLocation(50, 275);
        btnCarreras.addActionListener(this);
        this.add(btnCarreras);

        btnSaldo = new JButton("<html>Comprobar saldo</html>");
        btnSaldo.setSize(150, 80);
        btnSaldo.setLocation(50, 400);
        btnSaldo.addActionListener(this);
        this.add(btnSaldo);

        lblSaldo = new JLabel("Saldo = " + saldo + " €");
        lblSaldo.setSize(100, 30);
        lblSaldo.setLocation(50, 520);
        this.add(lblSaldo);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().getClass() == JButton.class) {
            if (((JButton) e.getSource()) == btnApuesta) {
                //            cuota=new double[4];
                //            for(int i=0;i<cuota.length;i++){
                //                cuota[i]=Math.random()*4+1;
                //            }
                Apuesta bet = new Apuesta(this);
                bet.setSize(300, 250);
                bet.setLocationRelativeTo(null);
                bet.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                bet.setVisible(true);

            } else if (((JButton) e.getSource()) == btnCarreras) {

            } else if (((JButton) e.getSource()) == btnSaldo) {
            }

        } else if (((JMenuItem) e.getSource()).getActionCommand().equals("Ayuda")) {
            JOptionPane.showMessageDialog(null, mensajeAyuda, "Ayuda", JOptionPane.PLAIN_MESSAGE);
        } else if (((JMenuItem) e.getSource()).getActionCommand().equals("Salir")) {
            System.exit(0);
        } else if (((JMenuItem) e.getSource()).getActionCommand().equals("Acerca de...")) {
            JOptionPane.showMessageDialog(null, "Autor: Javier Cameselle Abreu\n"
                    + "Desarrollado por \"Grupo de Programación PacoTilla S.L.\"", "Acerca de...", JOptionPane.PLAIN_MESSAGE);
        }
    }

}
