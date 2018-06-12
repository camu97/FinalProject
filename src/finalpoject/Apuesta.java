/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalpoject;

import java.awt.FlowLayout;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Javier
 */
public class Apuesta extends JDialog implements ActionListener, ItemListener {

    protected boolean flagApuesta;
    protected JButton btnApostar, btnCancelar, btnTiempos;
    protected JTextField txfImporte;
    protected JLabel lblImporte;
    protected double[] cuota = {
        Math.random() * 3 + 1.5,
        Math.random() * 3 + 1.5,
        Math.random() * 3 + 1.5,
        Math.random() * 3 + 1.5,
        Math.random() * 3 + 1.5,
        Math.random() * 3 + 1.5,
        Math.random() * 3 + 1.5
    };

    protected String[] caballos = {
        "PabloIglesias´s Dog ",
        "Caudillo Unihuevo",
        "MuyEspañol Rivera",
        "Elefante JuanKarlos",
        "Tabarnia Independiente",
        "Astronauta Carrero",
        "M.Rajoy Runner"
    };
    protected String[] caballosCuota = {
        String.format("%20s %3f", caballos[0], cuota[0]),
        String.format("%20s %3f", caballos[1], cuota[1]),
        String.format("%20s %3f", caballos[2], cuota[2]),
        String.format("%20s %3f", caballos[3], cuota[3]),
        String.format("%20s %3f", caballos[4], cuota[4]),
        String.format("%20s %3f", caballos[5], cuota[5]),
        String.format("%20s %3f", caballos[6], cuota[6])
    };
    protected double saldo;
    protected DefaultListModel<String> modelo = new DefaultListModel<String>();
    protected JList<String> cbCaballos;

    protected Apuesta(Hipódromo h, double saldo) {
        super(h, true);
        setTitle(h.getTitle());
//        setLayout(null);
        setLayout(new FlowLayout());
        this.saldo = saldo;

        cbCaballos = new JList<>(modelo);
        cbCaballos.setVisibleRowCount(7);
        cbCaballos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cbCaballos.setSize(200, 180);
        cbCaballos.setLocation(15, 15);
        this.add(cbCaballos);

        for (int i = 0; i < caballosCuota.length; i++) {
            modelo.addElement(caballosCuota[i]);
        }

        lblImporte = new JLabel("Importe");
        lblImporte.setSize(70, 20);
        lblImporte.setLocation(15, 200);
        this.add(lblImporte);

        txfImporte = new JTextField();
        txfImporte.setSize(100, 20);
        txfImporte.setLocation(100, 200);
        this.add(txfImporte);

        btnTiempos = new JButton("<html>Visualizar tiempos anteriores<html>");
        btnTiempos.setSize(250, 30);
        btnTiempos.setLocation(20, 100);
        btnTiempos.addActionListener(this);
        this.add(btnTiempos);

        btnApostar = new JButton("Realizar apuesta");
        btnApostar.setSize(160, 30);
        btnApostar.setLocation(10, 150);
        btnApostar.addActionListener(this);
        this.add(btnApostar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setSize(100, 30);
        btnCancelar.setLocation(180, 150);
        btnCancelar.addActionListener(this);
        this.add(btnCancelar);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        flagApuesta = false;
        if (e.getSource() == btnApostar) {
            try {
                double importe = Double.parseDouble(txfImporte.getText());
                if (importe > saldo) {
                    throw new NumberFormatException();
                } else {
                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Confirmar apuesta:  " + "<html>" + cbCaballos.getSelectedValue() + "<br/>" + importe + "</html>" + "?", "Confirmación de apuesta", JOptionPane.YES_NO_OPTION);
                    if ((respuesta == JOptionPane.YES_OPTION)) {
                        try (PrintWriter pw = new PrintWriter(new FileWriter("historialApuestas.txt", true))) {
                            pw.println(cbCaballos.getSelectedValue() + "\n" + importe);
                            pw.println("-------------------");
                        } catch (Exception ex) {
                            System.err.println("Error guardado de apuesta");
                        }
                        flagApuesta = true;
                        Carrera c = new Carrera(this);
                        c.setSize(700, 500);
                        c.setLocationRelativeTo(null);
                        c.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                        c.setVisible(true);
                    }
                }
            } catch (NumberFormatException exp) {
                JOptionPane.showMessageDialog(null, "Ha introducido una cantidad no válida", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == btnCancelar) {
//            super.setVisible(false);
            super.dispose();
        }
        if (e.getSource() == btnTiempos) {
            try (Scanner sc = new Scanner(new File("tiempos.txt"))) {
                String tiempo = "<html>";
                while (sc.hasNext()) {
                    tiempo += sc.nextLine() + "<br/><br/>";
                }
                tiempo += "</html>";
                JOptionPane.showMessageDialog(null, tiempo, "Tiempos de caballos", JOptionPane.PLAIN_MESSAGE);
            } catch (FileNotFoundException epa) {
                System.err.println("WOW");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "FAIL");
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
    }

}
