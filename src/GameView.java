/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class GameView extends javax.swing.JPanel implements KeyListener, MouseListener{

    private boolean run = true;
    EightBall ball;
    
    public GameView() {
        initComponents();
        ball = new EightBall(getWidth()/2,getHeight()/2);
        setBackground(Color.WHITE);
        this.addKeyListener(this);
    }

    public void start(){
        this.requestFocus();
        new Thread(){
            @Override
            public void run(){
                long time;
                while(run){
                    try {
                        time = System.currentTimeMillis();
                        Thread.sleep(10);
                        tic(System.currentTimeMillis()-time);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();
    }
    
    public void stop(){
        run = false;
    }
    
    private void tic(long millis){
        ball.tic(millis);
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        ball.draw(g);
        g.setColor(Color.BLACK);
        int height = g.getFontMetrics().getHeight();
        g.drawString("Press Spacebar To Shake", 5, height+5);
        g.drawString("Change Colors With The Left/Right Arrow Keys", 5, 2*height+5);
        g.drawString("Toggle Strobe With 'S'", 5, 3*height+5);
        g.drawString("Speed Up/Slow Down Strobe With The Up/Down Arrow Keys", 5, 4*height+5);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        ball.setCenterX(getWidth()/2);
        ball.setCenterY(getHeight()/2);
    }//GEN-LAST:event_formComponentResized

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == KeyEvent.VK_SPACE){
            ball.activate();
        }else if(e.getKeyCode()== KeyEvent.VK_RIGHT){
            ball.right();
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            ball.left();
        }if(e.getKeyCode() == KeyEvent.VK_S){
            ball.strobe();
        }if(e.getKeyCode() == KeyEvent.VK_UP){
            ball.strobeUp();
        }if(e.getKeyCode() == KeyEvent.VK_DOWN){
            ball.strobeDown();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        requestFocus();
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
