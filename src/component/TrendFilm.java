/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;

import javax.swing.Icon;
/**
 *
 * @author acer
 */
public class TrendFilm {
    public Icon getImage() {
        return image;
    }

    public void setImage(Icon image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TrendFilm(Icon image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public TrendFilm() {
    }

    private Icon image;
    private String title;
    private String description;
}
