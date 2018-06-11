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
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Javier
 */
public class Apuesta extends JDialog implements ActionListener, ItemListener {

    protected JButton btnApostar, btnCancelar, btnTiempos;
    protected JTextField txfImporte;
    protected JLabel lblImporte;
    protected double[] cuota ={
        Math.random()*4 +1,
        Math.random()*4 +1,
        Math.random()*4 +1,
        Math.random()*4 +1,
        Math.random()*4 +1,
        Math.random()*4 +1,
        Math.random()*4 +1
    };
    
    protected String[] caballos = {
        "GandalfElNigga",
        "CaudilloUnihuevo",
        "MuyEspañolRivera",
        "ElefanteJuanKarlos",
        "TabarniaIndependiente",
        "Astronauta Carrero",
        "M. Rajoy Runner"
    };
    protected JComboBox<String> cbCaballos;
    protected String[] caballosCuota={
        String.format("%20s %3f", caballos[(int) (Math.random() * 8 + 1)] , cuota[(int) (Math.random() * 8 + 1)]),
        String.format("%20s %3f", caballos[(int) (Math.random() * 8 + 1)] , cuota[(int) (Math.random() * 8 + 1)]),
        String.format("%20s %3f", caballos[(int) (Math.random() * 8 + 1)] , cuota[(int) (Math.random() * 8 + 1)]),
        String.format("%20s %3f", caballos[(int) (Math.random() * 8 + 1)] , cuota[(int) (Math.random() * 8 + 1)]),
        String.format("%20s %3f", caballos[(int) (Math.random() * 8 + 1)] , cuota[(int) (Math.random() * 8 + 1)]),
        String.format("%20s %3f", caballos[(int) (Math.random() * 8 + 1)] , cuota[(int) (Math.random() * 8 + 1)]),
        String.format("%20s %3f", caballos[(int) (Math.random() * 8 + 1)] , cuota[(int) (Math.random() * 8 + 1)])
    };
        
    protected Apuesta(Hipódromo h) {
        super(h, true);
        setTitle(h.getTitle());
        setLayout(null);

        cbCaballos = new JComboBox<>(caballosCuota);
        cbCaballos.setMaximumRowCount(5);
        cbCaballos.addItemListener(this);
        cbCaballos.setSize(200, 20);
        cbCaballos.setLocation(15, 15);
        this.add(cbCaballos);

        lblImporte = new JLabel("Importe");
        lblImporte.setSize(70, 20);
        lblImporte.setLocation(15, 50);
        this.add(lblImporte);

        txfImporte = new JTextField();
        txfImporte.setSize(100, 20);
        txfImporte.setLocation(100, 50);
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
        if (e.getSource() == btnApostar) {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Confirmar apuesta:  " + cbCaballos.getSelectedItem() + "?", "Confirmación de apuesta", JOptionPane.YES_NO_OPTION);
            if ((respuesta == JOptionPane.YES_OPTION)) {
//                Carrera c = new Carrera(caballos[]);
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
