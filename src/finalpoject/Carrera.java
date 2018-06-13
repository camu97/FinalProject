/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalpoject;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.*;

/**
 *
 * @author Javier
 */
public class Carrera extends JDialog implements ActionListener {

    protected JLabel[] caballo;
    protected JLabel meta;
    protected int x = 20, y = 20;
    protected int[] acuMov;
    protected Timer timer;
    protected JButton btnIniciar, btnDejarCarrera;
    protected boolean flagFin = false, flagApuesta=false;

    public Carrera(Apuesta a, Hipódromo h, boolean flagCarrera) {
        super(a, true);
        setTitle("Carrera");
        setLayout(null);

//        acuMov = new int[a.caballos.length];
//        for (int i = 0; i < acuMov.length; i++) {
//            acuMov[i] = 0;
//        }
        caballo = new JLabel[a.caballos.length];
        for (int i = 0; i < a.caballos.length; i++) {
            caballo[i] = new JLabel(new ImageIcon("caballoCarrera.png"));
            caballo[i].setBorder(BorderFactory.createLineBorder(Color.red));
            caballo[i].setSize(30, 30);
            caballo[i].setLocation(x, y);
            caballo[i].setToolTipText(a.caballos[i]);
            this.add(caballo[i]);
            y += 60;
        }

        meta = new JLabel(new ImageIcon("meta.jpg"));
        meta.setSize(30, 500);
        //FALLO EN LAS COORDENADAS
        meta.setLocation(500, -30);
        this.add(meta);

        btnIniciar = new JButton("INICIAR");
        btnIniciar.setSize(100, 40);
        btnIniciar.setLocation(580, 200);
        btnIniciar.addActionListener(this);
        this.add(btnIniciar);

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < caballo.length; i++) {
                    if (caballo[i].getX() < 500 && flagFin) {
                        if (a.cuota[i] <= 2) {
                            caballo[i].setLocation(caballo[i].getX() + (int) (Math.random() * 20 + 5), caballo[i].getY());
                        } else if (a.cuota[i] > 2 && a.cuota[i] <= 3.5) {
                            caballo[i].setLocation(caballo[i].getX() + (int) (Math.random() * 15 + 5), caballo[i].getY());
                        } else if (a.cuota[i] > 3.5 && a.cuota[i] < 6) {
                            caballo[i].setLocation(caballo[i].getX() + (int) (Math.random() * 10 + 5), caballo[i].getY());
                        }
                        if (caballo[i].getX() >= 500) {
                            if (i == a.cbCaballos.getSelectedIndex() && flagCarrera) {
                                JOptionPane.showMessageDialog(null, "<html> TU CABALLO  <u><s>" + a.caballos[i] + "</s></u>  HA GANADO</html>", "GANADOR", JOptionPane.PLAIN_MESSAGE);
                                h.saldo += a.importe * a.cuota[i];
                                flagApuesta=true;
                                h.lblSaldo.setText(String.format("Saldo = %.3f €",h.saldo));
                            } else {
                                JOptionPane.showMessageDialog(null, "<html>CABALLO  <u><s>" + a.caballos[i] + "</s></u>  HA GANADO</html>", "GANADOR", JOptionPane.PLAIN_MESSAGE);
                            }
                            timer.stop();
                            btnIniciar.setEnabled(false);
                            flagFin = false;
                            try (PrintWriter pw = new PrintWriter(new FileWriter("historialApuestas.txt", true))) {
                                pw.println("Caballo:  " + a.cbCaballos.getSelectedValue());
                                pw.println("Importe:  " + a.importe);
                                if (flagApuesta) {
                                    pw.println("[GANADA]   #Cantidad"+ a.importe*a.cuota[i]);
                                } else {
                                    pw.println("[PERDIDA]");
                                }
                                pw.println("-------------------");
                            } catch (Exception ex) {
                                System.err.println("Error guardado de apuesta");
                            }
                        }
                    }
                }
            }
        });

        btnDejarCarrera = new JButton("<html>Salir de<br/>la carrera</html>");
        btnDejarCarrera.setSize(100, 50);
        btnDejarCarrera.setVisible(false);
        btnDejarCarrera.setLocation(580, 400);
        btnDejarCarrera.addActionListener(this);
        this.add(btnDejarCarrera);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIniciar) {
            timer.start();
            flagFin = true;
        }
        btnDejarCarrera.setVisible(true);
        if (e.getSource() == btnDejarCarrera) {
            Apuesta a = (Apuesta) this.getOwner();
            timer.stop();
            flagApuesta = false;
            super.dispose();
        }
    }
}
