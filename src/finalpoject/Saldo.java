/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalpoject;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.Scanner;
import javax.swing.*;

/**
 *
 * @author Javier
 */
public class Saldo extends JDialog implements ActionListener {

    protected JLabel lblSaldoActual;
    protected JButton btnRetirar, btnAbono;
    private JMenuBar menu;
    private JMenu ConsultarApuesta;
    private JMenuItem miConsultarApuesta;
//    protected double saldo;
    protected boolean flagIngresar, flagRetirar;
    protected JComboBox<String> cbMetodo;
    protected JTextField txfNumero, txfImporte;
    protected JButton btnCancelar, btnAceptar;
    protected JLabel lblMetodo, lblImporte;
    protected String[] metodos = {
        "Transferencia",
        "PaySafeCard",
        "PayPal"
    };

    public Saldo(Hipódromo h, double saldo) {
        super(h, true);
        setLayout(null);
        setTitle("Saldo");

        menu = new JMenuBar();
        this.setJMenuBar(menu);

        ConsultarApuesta = new JMenu("Historial");
        ConsultarApuesta.setMnemonic('h');
        miConsultarApuesta = new JMenuItem("Consultar apuestas");
        miConsultarApuesta.setMnemonic('c');
        miConsultarApuesta.addActionListener(this);
        ConsultarApuesta.add(miConsultarApuesta);
        menu.add(ConsultarApuesta);

        lblSaldoActual = new JLabel("Saldo actual = " + h.saldo + " €");
        lblSaldoActual.setFont(new Font("Verdana", Font.BOLD, 14));
        lblSaldoActual.setSize(200, 30);
        lblSaldoActual.setLocation(50, 20);
        this.add(lblSaldoActual);

        btnRetirar = new JButton("Retirar");
        btnRetirar.addActionListener(this);
        btnRetirar.setSize(100, 30);
        btnRetirar.setLocation(30, 100);
        this.add(btnRetirar);

        btnAbono = new JButton("Ingreso");
        btnAbono.addActionListener(this);
        btnAbono.setSize(100, 30);
        btnAbono.setLocation(150, 100);
        this.add(btnAbono);
        /**
         * ****************************************
         */
        lblMetodo = new JLabel("Metodo");
        lblMetodo.setVisible(false);
        lblMetodo.setSize(70, 30);
        lblMetodo.setLocation(20, 150);
        this.add(lblMetodo);

        cbMetodo = new JComboBox<>(metodos);
        cbMetodo.setMaximumRowCount(5);
        cbMetodo.setVisible(false);
        cbMetodo.setSize(130, 30);
        cbMetodo.setLocation(100, 150);
        this.add(cbMetodo);

        txfNumero = new JTextField();
        txfNumero.setVisible(false);
        txfNumero.setSize(200, 20);
        txfNumero.setLocation(40, 200);
        this.add(txfNumero);

        lblImporte = new JLabel("Importe");
        lblImporte.setVisible(false);
        lblImporte.setSize(100, 20);
        lblImporte.setLocation(20, 240);
        this.add(lblImporte);

        txfImporte = new JTextField();
        txfImporte.setVisible(false);
        txfImporte.setSize(100, 20);
        txfImporte.setLocation(80, 240);
        this.add(txfImporte);

        btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(this);
        btnAceptar.setVisible(false);
        btnAceptar.setSize(120, 30);
        btnAceptar.setLocation(20, 300);
        this.add(btnAceptar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(this);
        btnCancelar.setVisible(false);
        btnCancelar.setSize(120, 30);
        btnCancelar.setLocation(150, 300);
        this.add(btnCancelar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Hipódromo h = (Hipódromo) this.getOwner();
        btnAbono.setBackground(null);
        btnRetirar.setBackground(null);
        if (e.getSource().getClass() == JButton.class) {
            if (e.getSource() == btnAbono) {
                super.setSize(300, 450);
                btnAbono.setBackground(Color.lightGray);
                flagIngresar = true;
                lblMetodo.setVisible(true);
                cbMetodo.setVisible(true);
                txfNumero.setVisible(true);
                lblImporte.setVisible(true);
                txfImporte.setVisible(true);
                btnAceptar.setVisible(true);
                btnCancelar.setVisible(true);
            }
            if (e.getSource() == btnRetirar) {
                super.setSize(300, 450);
                btnRetirar.setBackground(Color.lightGray);
                flagRetirar = true;
                lblMetodo.setVisible(true);
                cbMetodo.setVisible(true);
                txfNumero.setVisible(true);
                lblImporte.setVisible(true);
                txfImporte.setVisible(true);
                btnAceptar.setVisible(true);
                btnCancelar.setVisible(true);
            }
            if (e.getSource() == btnAceptar) {
                double importe;
                int numero;
                try {
                    numero = Integer.parseInt(txfNumero.getText());
                    importe = Double.parseDouble(txfImporte.getText());
                    if (importe <= 0 || (importe > h.saldo && flagRetirar)) {
                        throw new NumberFormatException();
                    }
                    if(txfNumero.getText().length()<16 && cbMetodo.getSelectedItem().equals("PaySafeCard")){
                        JOptionPane.showMessageDialog(null, "<html>Codigo de PaySafeCard incorrecto</html>", "ERROR", JOptionPane.ERROR_MESSAGE);
                        flagIngresar=false;
                        flagRetirar=false;
                    }
                    if(txfNumero.getText().length()<16 && !cbMetodo.getSelectedItem().equals("PaySafeCard")){
                        JOptionPane.showMessageDialog(null, "<html>Numero de cuenta incorrecta</html>", "ERROR", JOptionPane.ERROR_MESSAGE);
                        flagIngresar=false;
                        flagRetirar=false;
                    }
                    if (flagIngresar) {
                        JOptionPane.showMessageDialog(null, "<html>Ingreso de " + importe + " €<br/> correctamente realizado</html>", "Ingresar", JOptionPane.INFORMATION_MESSAGE);
                        h.saldo += importe;
                        h.lblSaldo.setText("Saldo = " + h.saldo + " €");
                        super.dispose();
                    }
                    if (flagRetirar) {
                        JOptionPane.showMessageDialog(null, "<html>Retirada de " + importe + " €<br/> correctamente realizado</html>", "Retirar", JOptionPane.INFORMATION_MESSAGE);
                        h.saldo =h.saldo- importe;
                        h.lblSaldo.setText("Saldo = " + h.saldo + " €");
                        super.dispose();
                    }
                    flagIngresar = false;
                    flagRetirar = false;
                } catch (NumberFormatException exp) {
                    JOptionPane.showMessageDialog(null, "Ha introducido una cantidad no válida", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getSource() == btnCancelar) {
                flagIngresar = false;
                flagRetirar = false;
                super.dispose();
            }
        }
        if (e.getSource().getClass() == JMenuItem.class) {
            if (e.getSource() == miConsultarApuesta) {
                String historico = "<html>";
                try (Scanner sc = new Scanner(new FileReader("historialApuetas.txt"))) {
                    if (sc.hasNext()) {
                        historico += sc.nextLine() + "<br/>";
                    }
                    historico += "</html>";
                    JOptionPane.showMessageDialog(null, historico, "HISTORIAL", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception exp) {
                    System.err.println("Fallo lectura");
                }
            }
        }
    }

}
