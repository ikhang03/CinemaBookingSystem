/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
/**
 *
 * @author acer
 */
public class Panel extends JPanel {
    public Panel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setColor(getBackground());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        g2.fillRect(0, getHeight() - 25, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }
   
}
