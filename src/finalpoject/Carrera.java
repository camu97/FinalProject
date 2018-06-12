/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalpoject;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Javier
 */
public class Carrera extends JDialog{

    protected JLabel[] caballo;
    protected int x=20,y=20;
    protected Timer timer;
    protected JButton btnIniciar;
    
    public Carrera(Apuesta a) {
        super(a);
        setTitle("Carrera");
        setLayout(null);
        
        for(int i=0;i<a.caballos.length;i++){
            caballo[i]=new JLabel(new ImageIcon("caballoCarrera.png"));
            caballo[i].setSize(30, 30);
            caballo[i].setLocation(x, y);
            caballo[i].setToolTipText(a.caballos[i]);
            this.add(caballo[i]);
            y+=40;
        }
        
        btnIniciar=new JButton("INICIAR");
        btnIniciar.setSize(100, 40);
        btnIniciar.setLocation(580, 235);
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.start();
            }
        });
        this.add(btnIniciar);
        
        timer=new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    
    
}
