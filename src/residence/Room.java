/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package residence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Basma01
 */
public class Room {
    private int Id_Room;
    private int ID_Pavilion;
    private String Room_Code;
    private int Nbr_Bed;
    
    private Statement stm;
    private ResultSet res;
    
    private ConnectionDB cnx=new ConnectionDB();
    
    public Room(){
    }
    public Room(int ID_Pavilion,String Room_Code,int Nbr_Bed){
        this.ID_Pavilion=ID_Pavilion;
        this.Room_Code=Room_Code;
        this.Nbr_Bed=Nbr_Bed;
    }
    public void AddRoom(){
    
        String Query="INSERT INTO Room (ID_Pavilion,Room_Code,Nbr_Bed) "
                + " VALUES (?,?,?)";
        PreparedStatement prstm=null;
        try {
            prstm=cnx.getConnect().prepareStatement(Query);
            prstm.setInt(1, getID_Pavilion());
            prstm.setString(2, getRoom_Code());
            prstm.setInt(3, getNbr_Bed());
            int x= prstm.executeUpdate();
            if (x>0) {
                //JOptionPane.showMessageDialog(null, "The Room Is ADDED");
            }else JOptionPane.showMessageDialog(null, "The Room Is  Noooooooooot ADDED");
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
    
    public void Add_Room(int ID_Pavilion,String Pavilion_Name,int Nbr_Room){
        int i=1;
        while (i<=Nbr_Room) {
            PreparedStatement prstm=null;
            String Nam_Room= Pavilion_Name +""+i;
            String Query="INSERT INTO Room (ID_Pavilion,Room_Code,Nbr_Bed) "
                  + "VALUES (?,?,?)";
            
            try {
                prstm=cnx.getConnect().prepareStatement(Query);
                prstm.setInt(1,ID_Pavilion);
                prstm.setString(2,Nam_Room);
                prstm.setInt(3,3);
                 int x=prstm.executeUpdate();
                 if (x>0) {
                         //JOptionPane.showMessageDialog(null, "The Room is added ");
                         System.out.println("Room is added");
            }else 
                     JOptionPane.showMessageDialog(null, "Error in added ");
            
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error SQl :"+ex.getMessage());
            } 
            try {
                prstm.close();
            } catch (SQLException ex) {
                   JOptionPane.showMessageDialog(null, "Error Close :"+ex.getMessage());
            }
            cnx.Deconnect();
            
            i++;
        }
    
    }
    public void DisplayTabRoom(JTable tabRes){
         DefaultTableModel dftabMd=(DefaultTableModel)tabRes.getModel();   
         DefaultTableModel dftabMd1=dftabMd;
        dftabMd.setRowCount(0);
         
        String Query="Select *from Room";
  cnx.Connecting();
         try {
            stm=cnx.getConnect().createStatement();
            res=stm.executeQuery(Query);
            
            while (res.next()){
                Object arg[]={res.getInt("Nbr_Bed_Reserved"),res.getInt("Nbr_Bed"),res.getString("Room_Code"),res.getInt("Id_Room")};
                  dftabMd.addRow(arg);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Sql" +e.getMessage());
        }     
        try {
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Close :" +ex);
        }
   
    }
     
    public int GetIDRoom(int IDRoom,int IDPav,int Renovl){
 if(Renovl == 3|| Renovl==4||Renovl==5||Renovl==6 || Renovl==7|| IDRoom==0){
   // JOptionPane.showMessageDialog(null, "ID :"+0);
     return 0;
 }else{
 int i=0;
    if(IDPav==1){i=IDRoom;}
    else i=80*(IDPav-1)+IDRoom;
 
 
  //String Query="SELECT  Pavilion_Name,Room.Id_Room  FROM  Pavilion,Room WHERE ID_Pavilion="+IDPav+" Id_Room.Room=Pavilion.ID_Pavilion and  Id_Room="+IDRoom+"";
    String Query="SELECT Id_Room,Room_Code FROM  Room,Pavilion WHERE Pavilion.ID_Pavilion="+IDPav+" and"
            + " Pavilion.ID_Pavilion=Room.ID_Pavilion and Id_Room="+i+"";
     
  int id=0;
        cnx.Connecting();
        try {
            stm=cnx.getConnect().createStatement();
            res=stm.executeQuery(Query);
            
           if(res.next()) 
           {          
                id=res.getInt(1);  
               // JOptionPane.showMessageDialog(null, "ID Rom :" +IDRoom+" id p :"+IDPav  + " RoomCod  :" +res.getString("Room_Code"));
            }else  JOptionPane.showMessageDialog(null, "res Null");
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error SQL  Getid:"+e.getMessage());
        }
        
        try {
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Close :"+e.getMessage());
        }
        
        return id;
 }
 }
     public void Remplir_Pavilion(){
      String Query="Select *From Pavilion ";
       
        try {
            stm=cnx.getConnect().createStatement();
            res=stm.executeQuery(Query);
            
            while (res.next()){
                int Id_Pavilion=res.getInt("ID_Pavilion");
                String Nam_Pavilion=res.getString("Pavilion_Name");
                int Nbr_Room=res.getInt("Nbr_Room");
                
                Add_Room(Id_Pavilion, Nam_Pavilion, Nbr_Room);
                
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Sql" +e.getMessage());
        }
        
        try {
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Close :" +ex);
        }
    }
    
     
    public void Remplir_Tab_Room(){
        
        
        
    }
    public int Get_ID_Room(String Room){
      String Query="Select id_Room from Room where Room_Code='"+Room+"'";
      
      cnx.Connecting();
      int id=0;
      try{
      stm=cnx.getConnect().createStatement();
      res=stm.executeQuery(Query);
          if (res.next()) {
              id=res.getInt(1);
          }     
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        try {
            stm.close();
            res.close();
        } catch (Exception e) {
        }
        cnx.Deconnect();
      return  id;
    }

    public void FillComboboxRooms(JComboBox cmb,String IdPav){
   
   // DefaultComboBoxModel Dfcmb=new DefaultComboBoxModel();
       DefaultComboBoxModel Dfcmb=(DefaultComboBoxModel) cmb.getModel();
        Dfcmb.removeAllElements();
         Statement stm=null;
         ResultSet res=null;
    cnx.Connecting();
        try {
            stm=cnx.getConnect().createStatement();
            res=stm.executeQuery("select Room_Code FROM Pavilion,Room WHERE  Pavilion.ID_Pavilion=Room.ID_Pavilion AND Pavilion_Name='"+IdPav+"'");
            while (res.next()) {                
                Dfcmb.addElement(res.getString("Room_Code"));
            }
            Dfcmb.addElement("/");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error in sql :"+e.getMessage());
        }
        try {
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "The Close Exception Erron FillComboboxRooms :"+e.getMessage());
        }
     }
    

    public void GetRoomANDPavillionForStudent(int NumCard,JComboBox cmbPv,JComboBox cmbRom,JLabel labAnsRom){
        
                Statement stm=null;
         ResultSet res=null;
         String CodeRoomStd;
         String PavillonStd;
    cnx.Connecting();
        try {
            stm=cnx.getConnect().createStatement();
            res=stm.executeQuery("select Room_Code,Pavilion_Name FROM Pavilion,Student_Res,Room,Resident_Gl\n" +
" WHERE Resident_Gl.ID_Rsident=Student_Res.ID_Rsident AND Student_Res.Id_Room=Room.Id_Room \n" +
"AND Pavilion.ID_Pavilion=Room.ID_Pavilion AND NumCard_Resident="+NumCard);
            

            if (res.next()) {                
             CodeRoomStd=res.getString("Room_Code");
             
             PavillonStd=res.getString("Pavilion_Name");
             
             cmbPv.setSelectedItem((String)PavillonStd);
             cmbRom.setSelectedItem((String)CodeRoomStd);
             
             labAnsRom.setText(CodeRoomStd);
            }else{
               cmbRom.setSelectedItem("/");
               labAnsRom.setText("/");
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error in sql :"+e.getMessage());
        }
        try {
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "The Close Exception Erron FillComboboxRooms :"+e.getMessage());
        }
    }
    
    
    
    public static void main(String[] args) {
        Room r=new Room();
      //  r.Remplir_Pavilion();
        
     r.GetIDRoom(2, 5, 1);
    }

    /**
     * @return the Id_Room
     */
    public int getId_Room() {
        return Id_Room;
    }

    /**
     * @param Id_Room the Id_Room to set
     */
    public void setId_Room(int Id_Room) {
        this.Id_Room = Id_Room;
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
     * @return the Room_Code
     */
    public String getRoom_Code() {
        return Room_Code;
    }

    /**
     * @param Room_Code the Room_Code to set
     */
    public void setRoom_Code(String Room_Code) {
        this.Room_Code = Room_Code;
    }

    /**
     * @return the Nbr_Bed
     */
    public int getNbr_Bed() {
        return Nbr_Bed;
    }

    /**
     * @param Nbr_Bed the Nbr_Bed to set
     */
    public void setNbr_Bed(int Nbr_Bed) {
        this.Nbr_Bed = Nbr_Bed;
    }
 
    
    
}
