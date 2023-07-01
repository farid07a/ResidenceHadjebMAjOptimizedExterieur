package residence;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public class UserService {
    //private  ConnectionDB Obj_Cnx=new ConnectionDB();
    private String idantifiant;
    private String name;
    private String lastName;
    private String password; 
    private int tab[]={0,0,0,0,0};
     ConnectionDB cnx_obj=new ConnectionDB();
      Statement stm;
    ResultSet res;
    public UserService(String idantifiant,String name,String lastName,String password,int tab[]){
    //Obj_Cnx=new ConnectionDB();
    this.idantifiant=idantifiant;
    this.name=name;
    this.lastName=lastName;
    this.password=password;
    this.tab=tab;
    }
    
    public UserService(){
    
    }
    
    
    public void InsertUser() 
    { 
    
        String requette= " insert into UserService (Idantifiant_usr,Nam_usr,LstName_usr,pass_usr,"
                + "Inscription_Resrv_srv,ResrveRom_srv,Restauration_srv,accessResident_srv,administration)"
        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        cnx_obj.Connecting();
        try {
            PreparedStatement preparedStmt = cnx_obj.getConnect().prepareStatement(requette);
            preparedStmt.setString (1, getIdantifiant());
      preparedStmt.setString (2, getName());
      preparedStmt.setString(3, getLastName());
      preparedStmt.setString(4, getPassword());
      preparedStmt.setBoolean(5, GetService(this.getTab()[0]));         //T[0] insc SERVICE
      preparedStmt.setBoolean(6, GetService(this.getTab()[1]));         //T[1] stat SERVICE
      preparedStmt.setBoolean(7, GetService(this.getTab()[2]));         //T[2] Rapport SERVICE
      preparedStmt.setBoolean(8, GetService(this.getTab()[3]));         //T[3] Restauration_srv SERVICE
      preparedStmt.setBoolean(9, GetService(this.getTab()[4]));         //T[4] accessResident_srv SERVICE    
     // preparedStmt.setBoolean(10, GetService(this.getTab()[5]));        //T[5] Administration SERVICE
      
      int x=preparedStmt.executeUpdate();
      
            if (x>0) {
             //   JOptionPane.showMessageDialog(null, "The value is inserted ");
                
            }else {
            JOptionPane.showMessageDialog(null, "Error in insert ");
            }
      preparedStmt.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Vous n'avez pas le deroit de corriger Classe user "+e.getMessage());
        }
        
        try {
            
            
            cnx_obj.Deconnect();
          
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in close Connection");
        }
        
        
    }
    
    public void UpdateUserInDB(String Identifiant){
       PreparedStatement preparedStmt=null;
        
       String Query="UPDATE  UserService SET Idantifiant_usr=?, Nam_usr=?, LstName_usr=?,  pass_usr=?,"
               + " achat_srv=?, vente_srv=?, production_srv=?, payemant=?, statistique_srv=?, administration=? WHERE Idantifiant_usr='"+Identifiant+"'";
        try {
            preparedStmt=cnx_obj.getConnect().prepareStatement(Query);
      preparedStmt.setString (1, getIdantifiant());
      preparedStmt.setString (2, getName());
      preparedStmt.setString(3, getLastName());
      preparedStmt.setString(4, getPassword());
      preparedStmt.setBoolean(5, GetService(this.getTab()[0]));         //T[0] Achat SERVICE
      preparedStmt.setBoolean(6, GetService(this.getTab()[1]));         //T[1] Vente SERVICE
      preparedStmt.setBoolean(7, GetService(this.getTab()[2]));         //T[2] Production SERVICE
      preparedStmt.setBoolean(8, GetService(this.getTab()[3]));         //T[3] Payement SERVICE
      preparedStmt.setBoolean(9, GetService(this.getTab()[4]));         //T[4] Statistique SERVICE    
      preparedStmt.setBoolean(10, GetService(this.getTab()[5]));        //T[5] Administration SERVICE
      
      int x=preparedStmt.executeUpdate();
      
            if (x>0) {
             //   JOptionPane.showMessageDialog(null, "The value is Updated  ");
                
            }else {
            JOptionPane.showMessageDialog(null, "Error in update ");
            }
      preparedStmt.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Vous n'avez pas le deroit de corriger Classe user "+e.getMessage());
        }
        
        try {
            
            
            cnx_obj.Deconnect();
          
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in close Connection");
        }
    }
    
     public void UpdateUserInDB1(String Identifiant){
       PreparedStatement preparedStmt=null;
        
       String Query="UPDATE  UserService"
               + " SET Idantifiant_usr=?,"
               + "Nam_usr=?,"
               + " LstName_usr=?"
            
               + " WHERE Idantifiant_usr='"+Identifiant+"'";
        try {
            preparedStmt=cnx_obj.getConnect().prepareStatement(Query);
            preparedStmt.setString (1, getIdantifiant());
            preparedStmt.setString (2, getName());
            preparedStmt.setString(3, getLastName());
            preparedStmt.setString(4, getPassword());
            preparedStmt.setBoolean(5, GetService(this.getTab()[0]));         //T[0] Achat SERVICE
            preparedStmt.setBoolean(6, GetService(this.getTab()[1]));         //T[1] Vente SERVICE
            preparedStmt.setBoolean(7, GetService(this.getTab()[2]));         //T[2] Production SERVICE
            preparedStmt.setBoolean(8, GetService(this.getTab()[3]));         //T[3] Payement SERVICE
            preparedStmt.setBoolean(9, GetService(this.getTab()[4]));         //T[4] Statistique SERVICE    
            preparedStmt.setBoolean(10, GetService(this.getTab()[5])); 
    
      int x=preparedStmt.executeUpdate();
      
            if (x>0) {
              //  JOptionPane.showMessageDialog(null, "The value is Updated  ");
                
            }else {
            JOptionPane.showMessageDialog(null, "Error in update ");
            }
      preparedStmt.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Vous n'avez pas le deroit de corriger Classe user "+e.getMessage());
        }
        
        try {
            
            
            cnx_obj.Deconnect();
          
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in close Connection");
        }
    }
    
    public boolean GetService(int etat){
    if(etat==0){
    return false;
    }else return true; 
    }
    
      public int GetService(boolean etat){
    if(etat){
    return 1;
    }else return 0; 
    }
    
    public void DisplayUserIntable(DefaultTableModel dm){
    
        //Statement stm=null;
        //ResultSet res=null;
       // ConnectionDB cnx_obj=new ConnectionDB();
        
        System.out.println("UserService.DisplayUserIntable() i am after initialisation");
        cnx_obj.Connecting();
        try {
            
           stm=cnx_obj.getConnect().createStatement();
            System.out.println("UserService.DisplayUserIntable() i am after create statment");
           
           res=stm.executeQuery("SELECT  * FROM  UserService");
           
            System.out.println("UserService.DisplayUserIntable() after execute query ");
           
            while (res.next()) {         
                
                
                /*System.out.println("User.DisplayUser()"+res.getString(1));
                System.out.println("User.DisplayUser()"+res.getString(2));
                System.out.println("User.DisplayUser()"+res.getString(3));*/
                
                
                
                Object arg[]={res.getString(1),res.getString(2),res.getString(3),res.getString(4)};
                System.out.println("UserService.DisplayUserIntable() arg[]0="+arg[0]);
                System.out.println("UserService.DisplayUserIntable() arg[]0="+arg[1]);
                System.out.println("UserService.DisplayUserIntable() arg[]0="+arg[2]);
                System.out.println("UserService.DisplayUserIntable() arg[]0="+arg[3]);
                /*System.out.println("User.DisplayUser()"+res.getString(5));
                System.out.println("User.DisplayUser()"+res.getString(6));
                System.out.println("User.DisplayUser()"+res.getString(7));*/
                
                dm.addRow(arg);
            }
           
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in classe user function  Display() "+e.getMessage());
        }
        
        try {
            stm.close();
            res.close();
            cnx_obj.Deconnect();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in classe user function  Display() close res et stm and deconnect "+e.getMessage());
        }
    
    }
   
    public  void SupprimerUserInDB(String Identifiant){
    PreparedStatement prstm=null;
        
        try {
            prstm=cnx_obj.getConnect().prepareStatement("DELETE FROM UserService WHERE Idantifiant_usr='"+Identifiant+"'");
            
            int x=prstm.executeUpdate();
            if (x>0) {
                System.out.println("The User  is supprimer");
            }else{
            JOptionPane.showMessageDialog(null, "Error in deleting User");
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Delete User "+e.getMessage());
        }
        try {
            prstm.close();
            cnx_obj.Deconnect();
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Error Closing Connection "+ex.getMessage());
        }
    
    
    }
    
    
    public  void DisplayUsersInTabUpdate(DefaultTableModel Dm){ // This funtion use to Display ALL uSER iN  Table TableUserTouPDATE 3 Colum IdantiAnd nAM aND 
        //Statement Stm=null;
        //ResultSet res=null;
        try {
            String Query="Select Idantifiant_usr,Nam_usr,LstName_usr FROM UserService";
            stm=this.cnx_obj.getConnect().createStatement();
            res=stm.executeQuery(Query);
            
            while (res.next()) {
               
                Object arg[]={res.getString("Idantifiant_usr"),res.getString("Nam_usr"),res.getString("LstName_usr")};
                Dm.addRow(arg);
            }
            
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error in Fill TableUser To Update "+e.getMessage());
        }
        try {
            stm.close();
            res.close();
            cnx_obj.Deconnect();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in closing "+e.getMessage());
        }
        
        
    }
    
    public void EtatServices(JCheckBox Service,boolean  Etat){
      
        
    }
    
    public void ViewServicesUser(String IdentifiantPr ){ //this for update Vieus Service By clicked in TableUserToUpDATE iN dESCRIPTION jTEXT fILED aND CheckBox
                                                          // AND put information in Object USERsERV
        try {
            stm=cnx_obj.getConnect().createStatement();
            res=stm.executeQuery("SELECT  * FROM UserService WHERE Idantifiant_usr='"+IdentifiantPr+"'");
            
            while (res.next()) {
                
                idantifiant=res.getString("Idantifiant_usr");
                name=res.getString("Nam_usr");
                lastName=res.getString("LstName_usr");
                password=res.getString("pass_usr");
                tab[0]=GetService(res.getBoolean("achat_srv"));
                tab[1]=GetService(res.getBoolean("vente_srv"));
                tab[2]=GetService(res.getBoolean("production_srv"));
                tab[3]=GetService(res.getBoolean("payemant"));
                tab[4]=GetService(res.getBoolean("statistique_srv"));
                tab[5]=GetService(res.getBoolean("administration"));
                
                System.out.println(" ---------"+idantifiant);
                System.out.println(" ---------"+name);
                System.out.println(" ---------"+lastName);
                System.out.println(" ---------"+password);
                System.out.println(" ---------"+  tab[0]);
                System.out.println(" ---------"+  tab[1]);
                System.out.println(" ---------"+  tab[2]);
                System.out.println(" ---------"+  tab[3]);
                 System.out.println(" ---------"+  tab[4]);
                  System.out.println(" ---------"+  tab[5]);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in View sERVICES "+e.getMessage());
        }
        try {
            stm.close();
            res.close();
            cnx_obj.Deconnect();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    public String GetCompletName(String IdentifiantPr){
        
    String Query="SELECT Nam_usr,LstName_usr FROM UserService WHERE Idantifiant_usr='"+IdentifiantPr+"' ";
    String NameComplete = null ;
        try {
            stm=cnx_obj.getConnect().createStatement();
            res=stm.executeQuery(Query);
            if(res.next()) {
            NameComplete=res.getString(1)+" "+res.getString(2);
            
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in  GetCompletName"+e.getMessage());
        }
          try {
            stm.close();
            res.close();
            cnx_obj.Deconnect();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return NameComplete;
    }
     public void GetInformation(String Idantifiant_usr,JTextField TxtInsIdentifiantUsr,JTextField TxtInsPrenomUsr,JTextField TxtInsNomusr,JTextField TxtInsConPswUsr,JCheckBox CheckInscrRessevSrv,
            JCheckBox CheckSrvRoom,JCheckBox CheckRestarUsr,JCheckBox ChecAccessUsr,JCheckBox CheckAdministrationusr){
           String Query="select * from  UserService Where  Idantifiant_usr='"+Idantifiant_usr+"'";  
   cnx_obj.Connecting();
   /*ConnectionDB cnx1=new ConnectionDB();
   cnx1.Connecting();*/
        try {
            stm=cnx_obj.getConnect().createStatement();
           //  JOptionPane.showMessageDialog(null, "After execute Query in  std");
            
            res=stm.executeQuery(Query);
            if (res.next()){
                TxtInsIdentifiantUsr.setText(res.getString("Idantifiant_usr"));
                TxtInsPrenomUsr.setText(res.getString("Nam_usr"));
                TxtInsNomusr.setText(res.getString("LstName_usr"));
                TxtInsConPswUsr.setText(res.getString("pass_usr"));
                
                if(res.getBoolean("Inscription_Resrv_srv")==true){
                    CheckInscrRessevSrv.setSelected(true);
                }else CheckInscrRessevSrv.setSelected(false);
                
                if(res.getBoolean("ResrveRom_srv")==true){
                    CheckSrvRoom.setSelected(true);
                }else CheckSrvRoom.setSelected(false);
                
                if(res.getBoolean("Restauration_srv")==true){
                    CheckRestarUsr.setSelected(true);
                }else CheckRestarUsr.setSelected(false);
                
                if(res.getBoolean("accessResident_srv")==true){
                    ChecAccessUsr.setSelected(true);
                }else ChecAccessUsr.setSelected(false);
                
                 if(res.getBoolean("administration")==true){
                    CheckAdministrationusr.setSelected(true);
                }else CheckAdministrationusr.setSelected(false);
                
                
            }
              
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error in Get Information Of User: "+ex.getMessage());
         }
      
        try {
            stm.close();
            res.close();
            cnx_obj.Deconnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error IN Close () :"+ex.getMessage());
        }
    
    }
     
     public void UpdateUsers(String Idantifiant_usr){
        PreparedStatement prstm=null;
        
          /* String Query="UPDATE UserService SET Idantifiant_usr=? ,Nam_usr=?,"
            + " LstName_usr=? ,pass_usr=?,Inscription_Resrv_srv=?,ResrveRom_srv=?,Restauration_srv=?,"
                   + "accessResident_srv=?,administration=?  WHERE Idantifiant_usr=' "+Idantifiant_usr+" '";*/
          String Query= "UPDATE UserService SET Idantifiant_usr=?, Nam_usr=?,\n" +
"            LstName_usr=? ,pass_usr=?,Inscription_Resrv_srv=?,ResrveRom_srv=?,Restauration_srv=?,\n" +
"                   accessResident_srv=?,administration=?  WHERE Idantifiant_usr = '"+Idantifiant_usr+"'";
    
           cnx_obj.Connecting();
            try {
        prstm=cnx_obj.getConnect().prepareStatement(Query);
        prstm.setString(1,getIdantifiant());
     //   JOptionPane.showMessageDialog(null," "+getIdantifiant());
        prstm.setString(2,getName());
       // JOptionPane.showMessageDialog(null," "+getName());
        prstm.setString(3, getLastName());
        //JOptionPane.showMessageDialog(null," "+getLastName());
        prstm.setString(4,getPassword());
        //JOptionPane.showMessageDialog(null," "+getPassword());
        prstm.setBoolean(5,GetService(this.tab[0]));
        //JOptionPane.showMessageDialog(null," "+tab[0]);
        prstm.setBoolean(6,GetService(this.tab[1]));
        //JOptionPane.showMessageDialog(null," "+tab[1]);
        prstm.setBoolean(7,GetService(this.tab[2]));
        //JOptionPane.showMessageDialog(null," "+tab[2]);
        prstm.setBoolean(8,GetService(this.tab[3]));
        //JOptionPane.showMessageDialog(null," "+tab[3]);
        prstm.setBoolean(9,GetService(this.tab[4]));
       // JOptionPane.showMessageDialog(null," "+tab[4]);
        int x=prstm.executeUpdate();
       if (x>0) {
         //  JOptionPane.showMessageDialog(null, "The Users IS Update  ");
        }else  {JOptionPane.showMessageDialog(null, " Error Into Update In  User ");
                }
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null, "Error in SQL Update Users "+e.getMessage());            
      }
            try{
            prstm.close();
            cnx_obj.Deconnect();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error ");
            }
    }
     public void DeletUser(String Identifiant){
         PreparedStatement preparedStmt=null;
        
       String Query="delete from UserService where  Idantifiant_usr=N'"+Identifiant+"'";
      cnx_obj.Connecting();
       try {
            preparedStmt=cnx_obj.getConnect().prepareStatement(Query);
  
      int x=preparedStmt.executeUpdate();
      
            if (x>0) {
                //JOptionPane.showMessageDialog(null, " Delete User   ");
                
            }else{
                  JOptionPane.showMessageDialog(null, " Error Delete User   ");
            }
      preparedStmt.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error SQL  in Delete User  :  "+e.getMessage());
        }
        
        try {
            
            
            cnx_obj.Deconnect();
          
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in close Connection");
        }
    }
 
    public static void main(String[] args) {
        int tab[]={1,1,1,1,1};
        
        UserService us=new  UserService("user2", "user2", "user2", "user2", tab);
        us.UpdateUsers("user2");
     /*
              us.ViewServicesUser("amar07");
        System.out.println("  *******   "+us.idantifiant+"-------------------------"+us.name);
        
        us.UpdateUserInDB("what is this");
        
        System.out.println("tttttttt   "+us.GetCompletName("admin"));*/
    }

    /**
     * @return the idantifiant
     */
    public String getIdantifiant() {
        return idantifiant;
    }

    /**
     * @param idantifiant the idantifiant to set
     */
    public void setIdantifiant(String idantifiant) {
        this.idantifiant = idantifiant;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the tab
     */
    public int[] getTab() {
        return tab;
    }

    /**
     * @param tab the tab to set
     */
    public void setTab(int[] tab) {
        this.tab = tab;
    }
}
