/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package residence;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Basma01
 */
public class Pavilion {
    
    private  int ID_Pavilion;
    private String Pavilion_Name;
    private int Nbr_Room;
    private Statement stm;
    private ResultSet res;
    
  
    private ConnectionDB cnx=new ConnectionDB();
    
    public Pavilion(){
        
    }
    public Pavilion(String Pavilion_Name,int Nbr_Room){
        this.Pavilion_Name=Pavilion_Name;
        this.Nbr_Room=Nbr_Room;
    }
    
    public void AddPavilion(){
        
        String Query="INSERT INTO Pavilion (Pavilion_Name,Nbr_Room) "
                + " VALUES (?,?)";
        PreparedStatement prstm=null;
        cnx.Connecting();
        try {
            prstm=cnx.getConnect().prepareStatement(Query);
            prstm.setString(1, getPavilion_Name());
            prstm.setInt(2, getNbr_Room());
             int x= prstm.executeUpdate();
            if (x>0) {
               // JOptionPane.showMessageDialog(null, "The Pavilion Is ADDED");
            }else JOptionPane.showMessageDialog(null, "The Pavilion Is  Noooooooooot ADDED");
        } catch (SQLException ex) {
            Logger.getLogger(Pavilion.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            prstm.close();
            cnx.Deconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public int GetNumberPavilion(){
    int CountPavion=0;
    
    String Query="SELECT COUNT (ID_Pavilion) FROM Pavilion";
    cnx.Connecting();
        try {
            stm=cnx.getConnect().createStatement();
            res=stm.executeQuery(Query);
            if (res.next()) {
                
               CountPavion=res.getInt(1);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in sql "+e.getMessage());
        }
        CloseService();
    return CountPavion;}
    
    /***********************************************************************/
    public void DisplayAllPavilionInPan(JPanel pan){
    
     String Query="SELECT Pavilion_Name   FROM Pavilion";  
     
     String NamePavilion="";
     
     //pan.setLayout(new FlowLayout(10));
     
    // JButton btn=btnPv;
    cnx.Connecting();
         try {
            stm=cnx.getConnect().createStatement();
            res=stm.executeQuery(Query);
            while (res.next()) {
               NamePavilion=res.getString("Pavilion_Name"); 
                System.out.println("residence.Pavilion.DisplayAllPavilionInPan()"+NamePavilion);
                 JButton btnPv1=new JButton(NamePavilion);
                    btnPv1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
                    btnPv1.setForeground(new java.awt.Color(255, 255, 255));
                    btnPv1.setContentAreaFilled(false);
                    btnPv1.setBackground(new Color(0,173,153));
                    btnPv1.setPreferredSize(new Dimension(55, 25));
                    btnPv1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnPv1.setOpaque(true);
                  pan.add(btnPv1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in sql "+e.getMessage());
        }
        CloseService();
    }
   
    public void CloseService(){
        try {
            res.close();
            stm.close();
            cnx.Deconnect();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in close Sql"+e.getMessage());
        }
    }
    public static void main(String[] args) {
        Pavilion p=new  Pavilion("A",20);
       // p.AddPavilion();
       
       p=new Pavilion();
        System.out.println("residence.Pavilion.main()"+p.GetNumberPavilion());
        p.DisplayAllPavilionInPan(null);
        
    }
    

    /**
     * @return the ID_Pavilion
     */
    public int getID_Pavilion() {
        return ID_Pavilion;
    }

    /**
     * @param ID_Pavilion the ID_Pavilion to set
     */
    public void setID_Pavilion(int ID_Pavilion) {
        this.ID_Pavilion = ID_Pavilion;
    }

    /**
     * @return the Pavilion_Name
     */
    public String getPavilion_Name() {
        return Pavilion_Name;
    }

    /**
     * @param Pavilion_Name the Pavilion_Name to set
     */
    public void setPavilion_Name(String Pavilion_Name) {
        this.Pavilion_Name = Pavilion_Name;
    }

    /**
     * @return the Nbr_Room
     */
    public int getNbr_Room() {
        return Nbr_Room;
    }

    /**
     * @param Nbr_Room the Nbr_Room to set
     */
    public void setNbr_Room(int Nbr_Room) {
        this.Nbr_Room = Nbr_Room;
    }
    
}

