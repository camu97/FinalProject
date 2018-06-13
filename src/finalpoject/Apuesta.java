/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalpoject;

import java.awt.Color;
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
import java.util.InputMismatchException;
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

    protected JButton btnApostar, btnCancelar, btnTiempos;
    protected JTextField txfImporte;
    protected JLabel lblImporte;
    protected double saldo, importe;
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
        String.format("%-20s %.3f", caballos[0], cuota[0]),
        String.format("%-20s %.3f", caballos[1], cuota[1]),
        String.format("%-20s %.3f", caballos[2], cuota[2]),
        String.format("%-20s %.3f", caballos[3], cuota[3]),
        String.format("%-20s %.3f", caballos[4], cuota[4]),
        String.format("%-20s %.3f", caballos[5], cuota[5]),
        String.format("%-20s %.3f", caballos[6], cuota[6])
    };
//    protected double saldo;
    protected DefaultListModel<String> modelo = new DefaultListModel<String>();
    protected JList<String> cbCaballos;

    protected Apuesta(Hipódromo h, double saldo, boolean flagCarrera) {
        super(h, true);
        setTitle(h.getTitle());
        setLayout(null);

        this.saldo = saldo;

        cbCaballos = new JList<>(modelo);
        cbCaballos.setVisibleRowCount(7);
        cbCaballos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cbCaballos.setSize(200, 150);
        cbCaballos.setLocation(15, 15);
        cbCaballos.setEnabled(flagCarrera);
        this.add(cbCaballos);

        for (int i = 0; i < caballosCuota.length; i++) {
            modelo.addElement(caballosCuota[i]);
        }

        lblImporte = new JLabel("Importe");
        lblImporte.setSize(70, 20);
        lblImporte.setLocation(15, 180);
        this.add(lblImporte);

        txfImporte = new JTextField();
        txfImporte.setSize(100, 20);
        txfImporte.setLocation(100, 180);
        txfImporte.setEnabled(flagCarrera);
        this.add(txfImporte);

//        btnTiempos = new JButton("<html>Visualizar tiempos anteriores<html>");
//        btnTiempos.setSize(250, 30);
//        btnTiempos.setLocation(20, 100);
//        btnTiempos.addActionListener(this);
//        this.add(btnTiempos);
        btnApostar = new JButton("Aceptar");
        btnApostar.setSize(160, 30);
        btnApostar.setLocation(7, 250);
        btnApostar.addActionListener(this);
        this.add(btnApostar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setSize(100, 30);
        btnCancelar.setLocation(180, 250);
        btnCancelar.addActionListener(this);
        this.add(btnCancelar);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Hipódromo h = (Hipódromo) this.getOwner();
        if (e.getSource() == btnApostar) {
            if (h.flagCarrera) {
                try {
                    importe = Double.parseDouble(txfImporte.getText());
                    if (importe > h.saldo) {
                        throw new InputMismatchException();
                    } else if (importe == 0) {
                        throw new NumberFormatException();
                    } else {
                        int respuesta = JOptionPane.showConfirmDialog(null, "<html>Confirmación de apuesta:"
                                + "<br/>Caballo:" + cbCaballos.getSelectedValue() + "<br/>Importe:  " + importe + "<br/>"
                                + "Ganancias potenciales:  " + String.format("%.3f", importe * cuota[cbCaballos.getSelectedIndex()]) + "</html>", "Confirmación de apuesta", JOptionPane.YES_NO_OPTION);
                        if ((respuesta == JOptionPane.YES_OPTION)) {
                            h.saldo = h.saldo - importe;
                            h.lblSaldo.setText("Saldo = " + h.saldo + " €");
                            super.setVisible(false);
                            System.err.println(cbCaballos.getSelectedValue());
                            Carrera c = new Carrera(this, h ,h.flagCarrera);
                            c.setSize(700, 500);
                            c.setBackground(Color.BLACK);
                            c.setLocationRelativeTo(null);
                            c.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                            c.setVisible(true);
                        }
                    }
                } catch (NumberFormatException exp) {
                    JOptionPane.showMessageDialog(null, "Ha introducido una cantidad no válida", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
                } catch (InputMismatchException ime) {
                    JOptionPane.showMessageDialog(null, "Insuficiente dinero", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
                } catch (ArrayIndexOutOfBoundsException aiae) {
                    JOptionPane.showMessageDialog(null, "No has seleccionado un caballo", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                Carrera c = new Carrera(this, h, h.flagCarrera);
                c.setSize(700, 500);
                c.setLocationRelativeTo(null);
                c.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                c.setVisible(true);
            }

        }
        if (e.getSource() == btnCancelar) {
            super.dispose();
//            super.setVisible(false);
        }
//        if (e.getSource() == btnTiempos) {
//            try (Scanner sc = new Scanner(new File("tiempos.txt"))) {
//                String tiempo = "<html>";
//                while (sc.hasNext()) {
//                    tiempo += sc.nextLine() + "<br/><br/>";
//                }
//                tiempo += "</html>";
//                JOptionPane.showMessageDialog(null, tiempo, "Tiempos de caballos", JOptionPane.PLAIN_MESSAGE);
//            } catch (FileNotFoundException epa) {
//                System.err.println("WOW");
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(null, "FAIL");
//            }
//        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
    }

}
