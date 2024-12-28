/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;
import component.PanelGradient;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import static component.BookingDetail.labelhome2;
import static component.BookingDetail.panelhome2;
import static component.BookingDetail.labelbook2;
import static component.BookingDetail.panelbook2;
import static component.BookingDetail.labelfood2;
import static component.BookingDetail.panelfood2;
import static component.BookingDetail.labeldetail2;
import static component.BookingDetail.paneldetail2;

/**
 *
 * @author acer
 */
public class Animation2 {
    
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

    public void AnimateHome2(){
        mostrarColor(panelhome2, panelhome2, labelhome2, "/Icon/homeo.png");
        establecColor( panelbook2,  panelbook2, labelbook2, "/Icon/books.png");
        establecColor( panelfood2,  panelfood2, labelfood2, "/Icon/comingsoon.png");
        establecColor(paneldetail2, paneldetail2, labeldetail2, "/Icon/detail.png");
    }

    public void AnimateBook2(){
        mostrarColor( panelbook2,  panelbook2, labelbook2, "/Icon/booko.png");
        establecColor(panelhome2, panelhome2, labelhome2, "/Icon/home.png");
        establecColor(panelfood2, panelfood2, labelfood2, "/Icon/comingsoon.png");
        establecColor(paneldetail2, paneldetail2, labeldetail2, "/Icon/detail.png");
    }

    public void AnimateFood2(){
        mostrarColor(panelfood2, panelfood2, labelfood2, "/Icon/comingsoono.png");
        establecColor(panelhome2, panelhome2, labelhome2, "/Icon/home.png");
        establecColor( panelbook2, panelbook2, labelbook2, "/Icon/books.png");
        establecColor(paneldetail2, paneldetail2, labeldetail2, "/Icon/detail.png");
    }

    public void AnimateDetail2(){
        mostrarColor(paneldetail2, paneldetail2, labeldetail2, "/Icon/detailo.png");
        establecColor(panelhome2, panelhome2, labelhome2, "/Icon/home.png");
        establecColor( panelbook2,  panelbook2, labelbook2, "/Icon/books.png");
        establecColor(panelfood2, panelfood2, labelfood2, "/Icon/comingsoon.png");
        
    }
    
   
}
