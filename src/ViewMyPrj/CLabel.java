/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewMyPrj;

/**
 *
 * 
 * @author faridPC
 */
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.AbstractBorder;
public class CLabel extends JLabel {
    private AbstractBorder circleBorder = new CircleBorder();
    private int lineBorder = 1;
    private Color lineColor = Color.BLACK;

    public CLabel() {
        Dimension d = new Dimension(100, 100);
        setSize(d);
        setPreferredSize(d);
        setText("CLabel");
        setOpaque(true);
        setHorizontalAlignment(CENTER);
        setVisible(true);
        setBorder(circleBorder);
    }
    //Warna Border
    public Color getLineColor() {
        return lineColor;
    }
    public void setLineColor(Color color) {
        circleBorder = new CircleBorder(color, lineBorder);
        lineColor = color;
        setBorder(circleBorder);
    }
    //Ukuran Border
    public int getLineBorder() {
        return lineBorder;
    }
    public void setLineBorder(int lineBorder) {
        circleBorder = new CircleBorder(lineColor, lineBorder);
        this.lineBorder = lineBorder;
        setBorder(circleBorder);
    }
}
