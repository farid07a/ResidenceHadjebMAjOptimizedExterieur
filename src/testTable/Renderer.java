/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testTable;

/**
 *
 * @author farid
 */
import ViewMyPrj.CLabel;
import java.awt.Component;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
 
public class Renderer extends DefaultTableCellRenderer{
 
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
         boolean hasFocus, int row, int column)
     {
 
        if(value instanceof CLabel){
           CLabel label = (CLabel)value;
           label.setText("");
           label.setSize(10, 10);
           label.setBorder(new EmptyBorder(0, 0, 0, 0));
            //you can add the image here
            //label.setIcon(new ImageIcon(getClass().getResource("C_Add_Mini_h.png")));
           File ImageResidentToSv = new File("D:\\Image_Signature\\C_Add_Mini_h.png");
        //ImageIcon imgeicon = new ImageIcon(new ImageIcon(ImageResidentToSv.getAbsolutePath()).getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
        ImageIcon imgeicon = new ImageIcon("D:\\Photo_residents\\C_Add_Mini_h.png");
        
            //label.setIcon(new ImageIcon(getClass().getResource("C_Add_Mini_h.png")));
            label.setIcon(imgeicon);
            label.setOpaque(true);
            
            return label;
        }
 
        else
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
 
     }
}
