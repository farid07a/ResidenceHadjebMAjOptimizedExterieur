/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package residence;

import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Basma01
 */
public class Guest {
    
    private int ID_Rsident;
    private String first_name;
    private String last_name;
    private int Numbre_CardRes;
    private  Date Date_Hosting;
    private int Nmbr_Days;
    private  Date Date_limit;
    private  String NumInsc;
    private Statement stm;
    private ResultSet res;
   Resident_Gl Resident_Gl=new Resident_Gl();
     ConnectionDB cnx=new ConnectionDB();
    
    public  Guest(){}
    
    public Guest(String first_name,String last_name,int Numbre_CardRes,Date Date_Hosting,int Nmbr_Days,Date Date_limit,String NumInsc,int ID_Rsident){
      this.first_name=first_name;
      this.last_name=last_name;
      this.Numbre_CardRes=Numbre_CardRes;
     this.Date_Hosting=Date_Hosting;
      this.Nmbr_Days=Nmbr_Days;
     this.ID_Rsident=ID_Rsident;
     this.Date_limit=Date_limit;
     this.NumInsc=NumInsc;
    }
     
    public void AddGuest(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat format = new SimpleDateFormat("dd/MM/YYYY");
        PreparedStatement prstm=null;                                                                   // Date_Hosting,              
        String Query="INSERT INTO Guests (ID_Rsident,Num_Card,NamGuest,SurNameGuest,Date_Hosting,Nbr_Days,Date_limit,Num_InscritBac)\n" +
                        "values (?,?,?,?,?,?,?,?)";
        cnx.Connecting();
        
         try {
            cnx.getConnect().setAutoCommit(false);
            prstm=cnx.getConnect().prepareStatement(Query);
            prstm.setInt(1,getID_Rsident() );      
            prstm.setInt(2,getNumbre_CardRes());
           // prstm.setString(3,25Fmain1+getNumbre_CardRes()+"");
            prstm.setString(3,getFirst_name());
            prstm.setString(4,getLast_name());        
             prstm.setDate(5,new java.sql.Date(getDate_Hosting().getTime()));
            prstm.setInt(6,getNmbr_Days() );
            prstm.setDate(7,new java.sql.Date(getDate_limit().getTime()));
            prstm.setString(8, getNumInsc());
            int x=prstm.executeUpdate();
            if (x>0) {
                //JOptionPane.showMessageDialog(null, "The Guest is added ");
            }else 
                JOptionPane.showMessageDialog(null, "Error in added ");
 
            
             cnx.getConnect().commit();
            
            
        } catch (HeadlessException | SQLException e) {    //Catch Error Statements
              JOptionPane.showMessageDialog(null, "Error in sql"+e.getMessage());
             if (cnx.getConnect() != null) {
                    try {
                        System.err.print("Transaction is being rolled back");
                        cnx.getConnect().rollback();
                    } catch(SQLException excep) {
                        excep.printStackTrace();
                    }
             }
             
        }finally{
              try {
                    prstm.close();
                  } catch (Exception e) {
                        e.printStackTrace();
                        }
                try {
                    cnx.getConnect().setAutoCommit(true);
                } catch (SQLException ex) {
                   ex.printStackTrace();
                }
        }
         cnx.Deconnect();
   } 
    public void DisplayTableGuests(JTable tabRes){
      //  JOptionPane.showMessageDialog(null, " Fanction");
        String Query="select ID_Rsident,Num_Card,NamGuest,SurNameGuest,Date_Hosting,Nbr_Days,Date_limit,Num_InscritBac\n" +
                    " from Guests";
        DefaultTableModel dftabMd=(DefaultTableModel)tabRes.getModel();   
         dftabMd.setRowCount(0);
        cnx.Connecting();
         try {
             stm=cnx.getConnect().createStatement();
             res=stm.executeQuery(Query);
             while (res.next()) {
                 
                 Date Limit=res.getDate("Date_limit");
                 Date Hosting =res.getDate("Date_Hosting");
                 int NbrDay=res.getInt("Nbr_Days"); 
                 String Validate="";
                 long timeDifferenceMilliseconds = new Date().getTime() - Hosting.getTime();
                 long diffDays1 = timeDifferenceMilliseconds / (60 * 60 * 1000 * 24);
                 
                  timeDifferenceMilliseconds = Limit.getTime() - Hosting.getTime();
                  long diffDays2 = timeDifferenceMilliseconds / (60 * 60 * 1000 * 24);
                /* JOptionPane.showMessageDialog(null, "DiffDays1 :"+diffDays1);
                 JOptionPane.showMessageDialog(null, "DiffDays2 :"+diffDays2);
                 */
                 if (diffDays1>30) {
                     Validate="مقبول";
                 }else {
                     if (diffDays1>=(NbrDay+1)) {
                         if((diffDays1-diffDays2)>2)
                         {
                         Validate="غير مقبول";
                         }else{
                         Validate="نهاية الاستضافة";
                         }
                         
                     }else{
                     Validate="مستضيف";
                     }
                     
                 }
                  
                  
                  Object arg[]={Validate,res.getDate("Date_limit"),res.getInt("Nbr_Days"),res.getDate("Date_Hosting"),
                       res.getString("Num_InscritBac"),res.getString("SurNameGuest"),
                       res.getString("NamGuest"),res.getInt("Num_Card"),res.getInt("ID_Rsident")};
                  dftabMd.addRow(arg);
             }             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error in sql :"+e.getMessage());
         }
        try {
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Resident_Gl.class.getName()).log(Level.SEVERE, null, ex);
        }  
     }       
    public Date GetDate_Hosting(int NbrDay) throws ParseException{
        NbrDay=NbrDay+1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	//Getting current date
	  Calendar cal = Calendar.getInstance();
	//Displaying current date in the desired format
	System.out.println("Current Date: "+sdf.format(cal.getTime()));
	   
	//Number of Days to add
        cal.add(Calendar.DAY_OF_MONTH,NbrDay);  
	//Date after adding the days to the current date
	String newDate = sdf.format(cal.getTime());  
	//Displaying the new Date after addition of Days to current date
	System.out.println("Date after Addition: "+newDate);
   return sdf.parse(newDate);
    }

    
public void UpdateGuets(int Num_Card){
  //  JOptionPane.showMessageDialog(null, " Fonction UpdateGuets");
    PreparedStatement prstm=null;
    String Query="UPDATE Resident_Gl SET Name_Resident=? ,LastName_Resident=? "+
          " WHERE NumCard_Resident="+Num_Card;

     cnx.Connecting();
            try {
             cnx.getConnect().setAutoCommit(false);
        prstm=cnx.getConnect().prepareStatement(Query);
       
        prstm.setString(1, getFirst_name());
       
      //  JOptionPane.showMessageDialog(null,""+getFirst_name());
        prstm.setString(2,getLast_name());
      //   JOptionPane.showMessageDialog(null,""+getLast_name());
        int x=prstm.executeUpdate();
                  if (x>0) {
            //JOptionPane.showMessageDialog(null, "The Guest  Is Update ");
        }else{  JOptionPane.showMessageDialog(null, " Error Update The  Guest  ");
                 
                  }

             cnx.getConnect().commit();

         }catch(Exception e){

             JOptionPane.showMessageDialog(null, "Error in SQL Update  Guest  "+e.getMessage());
             try {
              cnx.getConnect().rollback();
          } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "RollBack In Add Student Parent Class :"+ex.getMessage());
          }
            }finally{
            
              try {
             prstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
           try {
               cnx.getConnect().setAutoCommit(true);
           } catch (SQLException ex) {
              ex.printStackTrace();
           }
             }
   
            cnx.Deconnect();
 
} 

public void UpdateGuets2(int Num_Card){
    PreparedStatement prstm=null;
    String Query="UPDATE Resident_Gl SET Name_Resident=? ,LastName_Resident=? "+
          " WHERE NumCard_Resident="+Num_Card;
 int ID_Rsident= Resident_Gl.Get_ID_Resident(Num_Card);
    
     cnx.Connecting();
            try {
             cnx.getConnect().setAutoCommit(false);
        prstm=cnx.getConnect().prepareStatement(Query);
        prstm.setString(1, "ضيف");
      
        prstm.setString(2,"ضيف");
      
        int x=prstm.executeUpdate();
                  if (x>0) {
            //JOptionPane.showMessageDialog(null, "The Guest  Is Update ");
        }else{  JOptionPane.showMessageDialog(null, " Error Update The  Guest  ");
                  }
      
       
             cnx.getConnect().commit();

         }catch(Exception e){

             JOptionPane.showMessageDialog(null, "Error in SQL Update  Guest  "+e.getMessage());
             try {
              cnx.getConnect().rollback();
          } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "RollBack In Add Student Parent Class :"+ex.getMessage());
          }
            }finally{
            
              try {
             prstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
           try {
               cnx.getConnect().setAutoCommit(true);
           } catch (SQLException ex) {
              ex.printStackTrace();
           }
             }
   
            cnx.Deconnect();
 
} 
 public static void main(String[] args) {
     Guest  g=new Guest("zzzzzz", "zzzz",11237,new Date(), 3,new Date(),"00000000",1);
     g.UpdateGuets(11201);
  //   System.out.println("residence.Guest.main()"+g.GetDate_Hosting(4));
    //  g.UpdateGuets(11237);  
    //  g.DisplayTableGuests(null);
    }
       

    /**
     * @return the ID_Rsident
     */
    public int getID_Rsident() {
        return ID_Rsident;
    }

    /**
     * @param ID_Rsident the ID_Rsident to set
     */
    public void setID_Rsident(int ID_Rsident) {
        this.ID_Rsident = ID_Rsident;
    }

    /**
     * @return the first_name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * @param first_name the first_name to set
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * @return the last_name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * @param last_name the last_name to set
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * @return the Numbre_CardRes
     */
    public int getNumbre_CardRes() {
        return Numbre_CardRes;
    }

    /**
     * @param Numbre_CardRes the Numbre_CardRes to set
     */
    public void setNumbre_CardRes(int Numbre_CardRes) {
        this.Numbre_CardRes = Numbre_CardRes;
    }

    /**
     * @return the Barcode
     */
   
   

    /**
     * @return the DateBirth


    /**
    

    /**
     * @return the Nmbr_Days
     */
    public int getNmbr_Days() {
        return Nmbr_Days;
    }

    /**
     * @param Nmbr_Days the Nmbr_Days to set
     */
    public void setNmbr_Days(int Nmbr_Days) {
        this.Nmbr_Days = Nmbr_Days;
    }

    /**
     * @return the Date_limit
     */
    public Date getDate_limit() {
        return Date_limit;
    }

    /**
     * @param Date_limit the Date_limit to set
     */
    public void setDate_limit(Date Date_limit) {
        this.Date_limit = Date_limit;
    }

    /**
     * @return the Date_Hosting
     */
    public Date getDate_Hosting() {
        return Date_Hosting;
    }

    /**
     * @param Date_Hosting the Date_Hosting to set
     */
    public void setDate_Hosting(Date Date_Hosting) {
        this.Date_Hosting = Date_Hosting;
    }

    /**
     * @return the NumInsc
     */
    public String getNumInsc() {
        return NumInsc;
    }

    /**
     * @param NumInsc the NumInsc to set
     */
    public void setNumInsc(String NumInsc) {
        this.NumInsc = NumInsc;
    }
    
}
