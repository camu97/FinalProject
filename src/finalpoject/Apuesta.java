/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalpoject;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Javier
 */
public class Apuesta extends JDialog implements ActionListener, ItemListener {

    protected JButton btnApostar, btnCancelar, btnTiempos;
    protected JTextField txfImporte;
    protected JLabel lblImporte;
    protected JComboBox<String> cbCaballos;
    protected String[] caballos = {
        String.format("%15s", "BigRunner [4.00]"),
        String.format("%15s", "AmoSiprit [2.72]"),
        String.format("%15s", "Sta Klaus [3.90]"),
        String.format("%15s", "Little Peter [2.50]")
    };

    protected Apuesta(Hipódromo h) {
        super(h, true);
        setTitle(h.getTitle());
        setLayout(null);

        cbCaballos = new JComboBox<>(caballos);
        cbCaballos.setMaximumRowCount(5);
        cbCaballos.addItemListener(this);
        cbCaballos.setSize(200, 20);
        cbCaballos.setLocation(15, 15);
        this.add(cbCaballos);

        lblImporte = new JLabel("Importe");
        lblImporte.setSize(50, 20);
        lblImporte.setLocation(10, 50);
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
        btnApostar.setSize(140, 30);
        btnApostar.setLocation(10, 150);
        btnApostar.addActionListener(this);
        this.add(btnApostar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setSize(100, 30);
        btnCancelar.setLocation(160, 150);
        btnCancelar.addActionListener(this);
        this.add(btnCancelar);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancelar) {
//            super.setVisible(false);
            super.dispose();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
//       Hipódromo h=(Hipódromo) this.getOwner();
    }

}
