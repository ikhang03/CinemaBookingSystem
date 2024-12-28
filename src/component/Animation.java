/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;
import component.PanelGradient;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import static component.DashBoard.labelhome;
import static component.DashBoard.panelhome;
import static component.DashBoard.labelbook;
import static component.DashBoard.panelbook;
import static component.DashBoard.labelfood;
import static component.DashBoard.panelfood;
import static component.DashBoard.labeldetail;
import static component.DashBoard.paneldetail;

/**
 *
 * @author acer
 */
public class Animation {
    
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

    public void AnimateHome(){
        mostrarColor(panelhome, panelhome, labelhome, "/Icon/homeo.png");
        establecColor( panelbook,  panelbook, labelbook, "/Icon/books.png");
        establecColor( panelfood,  panelfood, labelfood, "/Icon/comingsoon.png");
        establecColor(paneldetail, paneldetail, labeldetail, "/Icon/detail.png");
    }

    public void AnimateBook(){
        mostrarColor( panelbook,  panelbook, labelbook, "/Icon/booko.png");
        establecColor(panelhome, panelhome, labelhome, "/Icon/home.png");
        establecColor(panelfood, panelfood, labelfood, "/Icon/comingsoon.png");
        establecColor(paneldetail, paneldetail, labeldetail, "/Icon/detail.png");
    }

    public void AnimateFood(){
        mostrarColor(panelfood, panelfood, labelfood, "/Icon/comingsoono.png");
        establecColor(panelhome, panelhome, labelhome, "/Icon/home.png");
        establecColor( panelbook, panelbook, labelbook, "/Icon/books.png");
        establecColor(paneldetail, paneldetail, labeldetail, "/Icon/detail.png");
    }

    public void AnimateDetail(){
        mostrarColor(paneldetail, paneldetail, labeldetail, "/Icon/detailo.png");
        establecColor(panelhome, panelhome, labelhome, "/Icon/home.png");
        establecColor( panelbook,  panelbook, labelbook, "/Icon/books.png");
        establecColor(panelfood, panelfood, labelfood, "/Icon/comingsoon.png");
        
    }
    
   
}
