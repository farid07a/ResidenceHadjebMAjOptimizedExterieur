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
import javax.swing.JProgressBar;

/**
 *
 * @author formation
 */
public class Account {
    
    private  int ID_account;
    private  String Description_account;
    private  double  Balance_account;
    private  boolean Etat_acount;
    private int ID_Student;
    ConnectionDB cnx=new ConnectionDB();
    Statement stm;
    ResultSet res;
//    Student student=new Student();
    
    public Account(String Description_account,double  Balance_account,boolean Etat_acount,int ID_Student){
        this.Description_account=Description_account;
        this.Balance_account=Balance_account;
        this.Etat_acount=Etat_acount;
        this.ID_Student=ID_Student;
    }
    
    public Account(){
    }
    
    /*******************Function Create Account********************/
    
    public void CreateAccount (){
  //  String Num_Card=student.Get_Numbre_CardRes(ID_Std);
        
    String Query="INSERT INTO Account (Description_account,Balance_account,Etat_acount,ID_Student) "
            + " VALUES (?,?,?,?)";
        
        PreparedStatement prstm=null;
        try {
            prstm=cnx.getConnect().prepareStatement(Query);
            prstm.setString(1,Description_account);
            prstm.setDouble(2, Balance_account);
            prstm.setBoolean(3, Etat_acount);
            prstm.setInt(4, ID_Student);
            
            int x= prstm.executeUpdate();
            if (x>0) {
                JOptionPane.showMessageDialog(null, "The account Is ADDED");
            }else JOptionPane.showMessageDialog(null, "The account Is  Noooooooooot ADDED");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            prstm.close();
            cnx.Deconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void DisplayBalanceStdInPrsBar(int Id_std,JProgressBar PrgsBar){
    
        
    
    }
    public double GetBalanceStd(int Id_std){
    String Query="Select Balance_account FROM Account WHERE ID_Student="+Id_std+"";
    double Balance = 0;  
    try {
            stm=cnx.getConnect().createStatement();
            res=stm.executeQuery(Query);
            if (res.next()) {
                Balance=res
                        .getDouble("Balance_account");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Sql:"+e.getMessage());
        }
        try {
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (Exception e) {
        }
    return Balance;}
    
      
     public String GeneratNumdAccount(int ID_Std,String Numbre_CardRes){
        
        String NumAcc=null;
        NumAcc=ID_Std+"_"+Numbre_CardRes;
    return NumAcc;}
   
    
  
    
  
    
    public static void main(String[] args) {
        System.err.println(""+new Date()+3);
        
    }

    
}
