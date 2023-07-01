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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class TestingTransc {
    
    ConnectionDB cnx=new ConnectionDB();
    public TestingTransc(){
    
    
    }
    
    public void InsertResident(String p1,String p2,int p3,String p4,Date p5,String p6,int p7,int p8,
                               String p9,String p10,String p11,String p12,String p13,int p14,String p15,
                               String p16,int p17,String p18,int p19,double p20,String p21,int p22,Date p23,
                               int p24,int p25,String p26,int p27
    
                                ) throws SQLException{
       // String Query1=""; 
       // String Query2="";
       PreparedStatement prstm=null;
       PreparedStatement prstm1=null;
       int ValMax = 0;
               Statement stm=null;
               ResultSet res=null;
         String Query1="INSERT INTO Resident_Gl (Name_Resident,LastName_Resident,NumCard_Resident,CodeBare_Resident,"
            + "DateBirth,PlaceBirth,ID_gender,Id_Ptrn_Res) "
                  + "VALUES (?,?,?,?,?,?,?,?)";
          
            String Query2="INSERT INTO Student_Res (Name_Father,FullName_Mother,ProfessionFather,"
                   + "ProfessionMother,Address,ID_Wilaya,Name_Commune,Name_Daira"
                    + ",Id_Nationalite,SituationFamily,BacYear,BacMoyen,PlaceGetBac,Id_BranchStd,DateInscrp,"
                    + "Id_Faculty,Id_LevelStudy,Num_InscritBac,ID_Rsident) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
           try {
               cnx.getConnect().setAutoCommit(false);
               
            prstm=cnx.getConnect().prepareStatement(Query1);
            prstm.setString(1, p1);
            prstm.setString(2, p2);
            prstm.setInt(3,p3);
            prstm.setString(4 ,p4);
            prstm.setDate(5,new java.sql.Date(p5.getTime()));
            prstm.setString(6, p6);
            prstm.setInt(7, p7);
            prstm.setInt(8, p8);
           // prstm.setBytes(9,getImage());
            int x=prstm.executeUpdate();
            
               if (x>0) {
               
                   JOptionPane.showMessageDialog(null, "The Resident Gl Is added ");
               }else {
                   
               JOptionPane.showMessageDialog(null, "Error in aded Resident");
               }
            
            
               String QuerySlct="SELECT MAX(ID_Rsident) FROM Resident_Gl";
            
               stm=cnx.getConnect().createStatement();
               res=stm.executeQuery(QuerySlct);
               
               if (res.next()) {
                   ValMax=res.getInt(1);   
               }
               
               JOptionPane.showMessageDialog(null, "The MaxVal is "+ValMax);
            
            
              String Query="INSERT INTO Student_Res (Name_Father,FullName_Mother,ProfessionFather,"
                   + "ProfessionMother,Address,ID_Wilaya,Name_Commune,Name_Daira"
                    + ",Id_Nationalite,SituationFamily,BacYear,BacMoyen,PlaceGetBac,Id_BranchStd,DateInscrp,"
                    + "Id_Faculty,Id_LevelStudy,Num_InscritBac,ID_Rsident) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
       
            prstm=cnx.getConnect().prepareStatement(Query);
            prstm.setString(1, p9);
            // System.out.println("Name ");
            prstm.setString(2, p10);
            prstm.setString(3,p11);
            prstm.setString(4, p12);
            prstm.setString(5,p13);
            prstm.setInt(6, p14);
            prstm.setString(7  ,p15);
            prstm.setString(8, p16);
            prstm.setInt(9, p17);
            prstm.setString(10, p18);
            prstm.setInt(11,p19);
            prstm.setDouble(12, p20);
            prstm.setString(13, p21);
            prstm.setInt(14, p22);
            prstm.setDate(15, new java.sql.Date(p23.getTime()));
            prstm.setInt(16,p24);
            prstm.setInt(17,p25);
            // prstm.setInt(17, getId_Room());
            prstm.setString(18, p26);
            prstm.setInt(19, ValMax);
     
            
            
            int x1=prstm.executeUpdate();
            
            
            
            
            if (x1>0) {
                JOptionPane.showMessageDialog(null, "The Student Is Added  is added ");
            }else 
                JOptionPane.showMessageDialog(null, "Error in added ");
            
            
            
            
            
            
            
            cnx.getConnect().commit();
        } catch (Exception e) {
          /*  JOptionPane.showMessageDialog(null, "Error in SQL "+e.getMessage());
            e.printStackTrace();
            
            
            
            cnx.getConnect().rollback();*/
          
             if (cnx.getConnect() != null) {
            try {
                System.err.print("Transaction is being rolled back");
                cnx.getConnect().rollback();
            } catch(SQLException excep) {
                excep.printStackTrace();
            }
        }
          
        }finally
           {
               if (prstm!=null) {
                   prstm.close();
                   
               }
                if (prstm1!=null) {
                   prstm1.close();
               }
                
                try {
                   stm.close();
                   res.close();
               } catch (Exception e) {
               }
               
           try {
               cnx.getConnect().setAutoCommit(true);
           } catch (SQLException ex) {
              ex.printStackTrace();
           }
           
           }
           
           
           
           
           
           
           
           
           // prstm.close();
           cnx.Deconnect();
        
    
    
        
    
    
    }
    
    public static void main(String[] args) throws SQLException {
        new TestingTransc().InsertResident("farid", "farid", 2, "farid", new Date(), "farid", 1, 1, "farid", 
                "farid", "farid", "farid", "farid", 2, "farid", "farid", 2, "farid", 2, 2, "farid", 2, new Date(), 2, 2, "farid", 34);
    }
}
