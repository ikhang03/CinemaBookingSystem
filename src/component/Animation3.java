/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;
import component.PanelGradient;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import static component.ComingSoon.labelhome3;
import static component.ComingSoon.panelhome3;
import static component.ComingSoon.labelbook3;
import static component.ComingSoon.panelbook3;
import static component.ComingSoon.labelfood3;
import static component.ComingSoon.panelfood3;
import static component.ComingSoon.labeldetail3;
import static component.ComingSoon.paneldetail3;

/**
 *
 * @author acer
 */
public class Animation3 {
    
    private void mostrarColor(PanelGradient paneX, PanelGradient paneZ, JLabel lblx, String url) {
        paneX.setBackground(new Color(52, 100, 174));
        paneZ.setColorGradient(new Color(59, 207, 255));
        lblx.setForeground(Color.WHITE);
        
        lblx.setIcon(new ImageIcon(getClass().getResource(url)));
    }

    private void establecColor(PanelGradient paneX, PanelGradient paneZ, JLabel lblx, String url) {
        paneX.setBackground(new Color(23, 27, 36));
        paneZ.setColorGradient(new Color(23, 27, 36));
        lblx.setForeground(new Color(166, 166, 166));

        lblx.setIcon(new ImageIcon(getClass().getResource(url)));
    }

    public void AnimateHome3(){
        mostrarColor(panelhome3, panelhome3, labelhome3, "/Icon/homeo.png");
        establecColor( panelbook3,  panelbook3, labelbook3, "/Icon/books.png");
        establecColor( panelfood3,  panelfood3, labelfood3, "/Icon/comingsoon.png");
        establecColor(paneldetail3, paneldetail3, labeldetail3, "/Icon/detail.png");
    }

    public void AnimateBook3(){
        mostrarColor( panelbook3,  panelbook3, labelbook3, "/Icon/booko.png");
        establecColor(panelhome3, panelhome3, labelhome3, "/Icon/home.png");
        establecColor(panelfood3, panelfood3, labelfood3, "/Icon/comingsoon.png");
        establecColor(paneldetail3, paneldetail3, labeldetail3, "/Icon/detail.png");
    }

    public void AnimateFood3(){
        mostrarColor(panelfood3, panelfood3, labelfood3, "/Icon/comingsoono.png");
        establecColor(panelhome3, panelhome3, labelhome3, "/Icon/home.png");
        establecColor( panelbook3, panelbook3, labelbook3, "/Icon/books.png");
        establecColor(paneldetail3, paneldetail3, labeldetail3, "/Icon/detail.png");
    }

    public void AnimateDetail3(){
        mostrarColor(paneldetail3, paneldetail3, labeldetail3, "/Icon/detailo.png");
        establecColor(panelhome3, panelhome3, labelhome3, "/Icon/home.png");
        establecColor( panelbook3,  panelbook3, labelbook3, "/Icon/books.png");
        establecColor(panelfood3, panelfood3, labelfood3, "/Icon/comingsoon.png");
    }
    
   
}
