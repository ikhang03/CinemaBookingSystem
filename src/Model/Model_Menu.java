package Model;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author acer
 */
public class Model_Menu {

    /**
     *
     * @return
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     *
     * @param menuName
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     *
     * @return
     */
    public String getIcon() {
        return icon;
    }

    /**
     *
     * @param icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     *
     * @param menuName
     * @param icon
     */
    public Model_Menu(String menuName, String icon) {
        this.menuName = menuName;
        this.icon = icon;
    }

    /**
     *
     */
    public Model_Menu() {
    }

    private String menuName;
    private String icon;

    /**
     *
     * @return
     */
    public Icon toIcon() {
        return new ImageIcon(getClass().getResource("/Icon/" + icon + ".png"));
    }

    /**
     *
     * @return
     */
    public Icon toIconSelected() {
        return new ImageIcon(getClass().getResource("/Icon/" + icon + "_selected.png"));
        
    }
    
    
}
