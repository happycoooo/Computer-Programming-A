package Project.view;

import java.awt.*;
import javax.swing.*;

public class BackGroundPanel extends JComponent{
    private Image image = null;
    public BackGroundPanel(Image image) {
        this.image = image;
    }
    protected void paintComponent(Graphics g){
        g.drawImage(image,0,0,this.getWidth(),this.getHeight(),this);
    }
}

