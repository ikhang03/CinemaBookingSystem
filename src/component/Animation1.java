/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;
import component.PanelGradient;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


import static component.BookForm.labelhome1;
import static component.BookForm.panelhome1;
import static component.BookForm.labelbook1;
import static component.BookForm.panelbook1;
import static component.BookForm.labelfood1;
import static component.BookForm.panelfood1;
import static component.BookForm.labeldetail1;
import static component.BookForm.paneldetail1;
/**
 *
 * @author acer
 */
public class Animation1 {
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
    
     public void AnimateHome1(){
        mostrarColor(panelhome1, panelhome1, labelhome1, "/Icon/homeo.png");
        establecColor( panelbook1,  panelbook1, labelbook1, "/Icon/books.png");
        establecColor( panelfood1,  panelfood1, labelfood1, "/Icon/comingsoon.png");
        establecColor(paneldetail1, paneldetail1, labeldetail1, "/Icon/detail.png");
    }

    public void AnimateBook1(){
        mostrarColor( panelbook1,  panelbook1, labelbook1, "/Icon/booko.png");
        establecColor(panelhome1, panelhome1, labelhome1, "/Icon/home.png");
        establecColor(panelfood1, panelfood1, labelfood1, "/Icon/comingsoon.png");
        establecColor(paneldetail1, paneldetail1, labeldetail1, "/Icon/detail.png");
    }

    public void AnimateFood1(){
        mostrarColor(panelfood1, panelfood1, labelfood1, "/Icon/comingsoono.png");
        establecColor(panelhome1, panelhome1, labelhome1, "/Icon/home.png");
        establecColor( panelbook1, panelbook1, labelbook1, "/Icon/books.png");
        establecColor(paneldetail1, paneldetail1, labeldetail1, "/Icon/detail.png");
    }

    public void AnimateDetail1(){
        mostrarColor(paneldetail1, paneldetail1, labeldetail1, "/Icon/detailo.png");
        establecColor(panelhome1, panelhome1, labelhome1, "/Icon/home.png");
        establecColor( panelbook1,  panelbook1, labelbook1, "/Icon/books.png");
        establecColor(panelfood1, panelfood1, labelfood1, "/Icon/comingsoon.png");
        
    }
}
