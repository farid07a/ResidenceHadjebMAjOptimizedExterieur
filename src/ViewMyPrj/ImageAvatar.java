/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewMyPrj;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 *
 * @author faridPC
 */
public class ImageAvatar extends JComponent {

    /**
     * @return the icon
     */
    public Icon getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    /**
     * @return the borderSize
     */
    public int getBorderSize() {
        return borderSize;
    }

    /**
     * @param borderSize the borderSize to set
     */
    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
    }

    private Icon icon;
    private int borderSize;

    @Override
    protected void paintComponent(Graphics g) {
        if (icon != null) {
            int width = getWidth();
            int height = getHeight();
            int diameter = Math.min(width, height);
            int x = width / 2 - diameter / 2;
            int y = height / 2 - diameter / 2;
            int border=borderSize*2;
            
            diameter-=border;
            
            Dimension size;
            size = getAutoSize(icon, diameter);
            BufferedImage bfimg=new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
            
            
            Graphics2D g_img=bfimg.createGraphics();
            g_img.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            
            g_img.fillOval(0, 0, diameter, diameter);
            Composite composite=g_img.getComposite();
            g_img.setComposite(AlphaComposite.SrcIn);
            g_img.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            
            g_img.drawImage(ToImage(icon), 0, 0, size.width,size.height,null);
            g_img.setComposite(composite);
            g_img.dispose();
            Graphics2D g2=(Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (borderSize>0) {
                diameter+=border;
                g2.setColor(getForeground());
                g2.fillOval(x, y, width, height);
            }
            if (isOpaque()) {
                g2.setColor(getBackground());
                diameter-=borderSize;
            }
             
            g2.drawImage(bfimg, x+borderSize     , y+borderSize, null);
            
            //Buffer
        }
        
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
    }

    private Image ToImage(Icon icon) {

        return ((ImageIcon) icon).getImage();
    }

    private Dimension getAutoSize(Icon image, int size) {
        int w = size;
        int h = size;
        int iw = image.getIconWidth();
        int ih = image.getIconHeight();
        double xscale =(double) w / iw;
        double yscale =(double)  h / ih;
        
        double scale=Math.max(xscale, yscale);
        int width=(int) (scale*iw);
        int height=(int) (scale*ih);
        return new Dimension(width, height);
        
        
    }

}
