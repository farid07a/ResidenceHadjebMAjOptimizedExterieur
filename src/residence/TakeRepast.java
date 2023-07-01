/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package residence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.Query;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TakeRepast {
    private  int ID_Repat;
    private  int ID_Student;
    private int Hour_Take;
    private Date Date_Take;
    private int MinTake;
    private String CodBare;
   ConnectionDB cnx=new ConnectionDB();
   private Statement stm;
    private ResultSet res;
    
    
   public TakeRepast(){}
   
   public TakeRepast(int ID_Repat,int ID_Student,int Hour_Take,Date Date_Take,int MinTake,String CodBare){
   
       this.ID_Repat=ID_Repat;
       this.ID_Student=ID_Student;
       this.Hour_Take=Hour_Take;
       this.Date_Take=Date_Take;
       this.MinTake=MinTake;
       this.CodBare=CodBare;
   }
    
    public void TakeRopa() {          
         PreparedStatement prstm=null;
             String Query="INSERT INTO Res_Take_Repat (ID_Repat,ID_Rsident,Hour_Take,Date_Take,Min_Take,CodBare)"
                    + "VALUES (?,?,?,?,?,?)";        
         //To change body of generated methods, choose Tools | Templates.
        cnx.Connecting();

         try {
              cnx.getConnect().setAutoCommit(false);               
             prstm=cnx.getConnect().prepareStatement(Query);
             prstm.setInt(1,getID_Repat());
         
             prstm.setInt(2, getID_Student());
           
             prstm.setInt(3, getHour_Take());
            
            
            prstm.setDate(4, new java.sql.Date(getDate_Take().getTime()));
             prstm.setInt(5,this.MinTake);
             prstm.setString(6, this.CodBare);
            int x=prstm.executeUpdate();
            if (x>0) {
             //   JOptionPane.showMessageDialog(null, "The Student is added ");
            
            }else{ 
               // JOptionPane.showMessageDialog(null, "Error in added ");
            }     
             cnx.getConnect().commit(); 
        } catch (Exception e) {    //Catch Error Statements        
            JOptionPane.showMessageDialog(null, "Error Sql: "+e.getMessage());
                 JOptionPane.showMessageDialog(null, "Error Sql:"+e.getMessage());
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
    
   public boolean GetEtat(String CodeBar,Connection cnnex){
   boolean Etat= false;
  //cnx.Connecting();
        try {
      String Query="select  Cast_To_Eat from  Resident_Gl,Resident_Case  where\n" +
"                    Resident_Gl.ID_Case_Resident= Resident_Case.ID_Case_Resident  and  CodeBare_Resident='"+CodeBar+"'";
         //stm=cnx.getConnect().createStatement(); //cnnex
                    stm=cnnex.createStatement();
            res=stm.executeQuery(Query);   
            if (res.next()){
                Etat =res.getBoolean("Cast_To_Eat");
              //  JOptionPane.showMessageDialog(null, " Etat of Student To Eating  is  :   "+ res.getBoolean("Cast_To_Eat"));
            }else Etat =false; 
        } catch (SQLException ex) {
            Logger.getLogger(TakeRepast.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            stm.close();
            res.close();
           // cnx.Deconnect();
        } catch (SQLException ex) {
            Logger.getLogger(TakeRepast.class.getName()).log(Level.SEVERE, null, ex);
        }
      return Etat;
   }
     
    public boolean Exist_Not_Exist(String CodBare,JLabel j,JLabel J2,Connection cnnx){
   
        //cnx.Connecting(); //Correct
    boolean Etat=false; 
 String date=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
 int h=new Date().getHours();
                if(h>=5 && h<=8){         
                    String Query="select  * from  Res_Take_Repat  where (Hour_Take >=5 and Hour_Take <=8) "
                            + "and Date_Take= '"+date+"' and CodBare='"+CodBare+"'";
                     try {
                         //stm=cnx.getConnect().createStatement(); //correct 
                   stm= cnnx.createStatement();
                     res=stm.executeQuery(Query);
                     if(res.next()){       
                         Etat= false;
                         j.setText("لــقـــد تــم أخـــذ وجـــبـــة الـــفــطــور ســابــقــا بالتوقيت ");
                         
                         if (res.getInt("Min_Take")<10) {
                          J2.setText(res.getInt("Hour_Take")+":0"+res.getInt("Min_Take")+" ");   
                         }else J2.setText(res.getInt("Hour_Take")+":"+res.getInt("Min_Take")+" ");

                     }else{ 
                         Etat= true;
                    //  j.setText("الـــرجـــاء مـــراعــــاة الــوقـــت أو ضــبـط تــوقــيـت الـجــهــاز");
                      //   JOptionPane.showMessageDialog(null, " The Stusent NOOOOOTTTTTT  Eatiinnnggg 6<=h&& h<=8........."+ Etat);
                     }
                  } catch (SQLException ex) {
            Logger.getLogger(TakeRepast.class.getName()).log(Level.SEVERE, null, ex);
                     }       
                }else if(h>=11&& h<=15){
                        String Query="select * from  Res_Take_Repat  where (Hour_Take >=11 and Hour_Take <=15)"
                                + "and Date_Take= '"+date+"' "                   
                                  + " and   CodBare='"+CodBare+"'";
                     try {
                       // stm=cnx.getConnect().createStatement();      
                        stm= cnnx.createStatement();
                     res=stm.executeQuery(Query);
                     if(res.next()){ 
                         
                         System.out.println(""+res.getInt("ID_Rsident")+"  "+res.getInt("Hour_Take")+"  "+res.getDate("Date_Take"));                      
                                Etat= false;
                        j.setText("لــقـــد تــم أخـــذ وجـــبـــة الــغـــداء ســابــقــا");
                           if (res.getInt("Min_Take")<10) {
                          J2.setText(res.getInt("Hour_Take")+":0"+res.getInt("Min_Take")+" ");   
                         }else J2.setText(res.getInt("Hour_Take")+":"+res.getInt("Min_Take")+" ");
             //     
                     }else {
                                Etat= true;
                        }
                     } catch (SQLException ex) {
                                Logger.getLogger(TakeRepast.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                      }else if(h>=17 && h<=21){
                          String Query="select  * from  Res_Take_Repat  where (Hour_Take >=17 and Hour_Take <=21) "
                                  + "and Date_Take= '"+date+"' and CodBare='"+CodBare+"'";
                          try { 
                             // stm=cnx.getConnect().createStatement();  
                             stm= cnnx.createStatement();
                              res=stm.executeQuery(Query);
                           if(res.next()){
                              Etat= false;
                     //       JOptionPane.showMessageDialog(null, " The Stusent Eatiinnnggg at 18<=h && h<=20 ......."+ Etat);
                             j.setText("لــقـــد تــم أخـــذ وجـــبـــة الــعـــشــاء ســابــقــا");
                                if (res.getInt("Min_Take")<10) {
                          J2.setText(res.getInt("Hour_Take")+":0"+res.getInt("Min_Take")+" ");   
                         }else J2.setText(res.getInt("Hour_Take")+":"+res.getInt("Min_Take")+" ");
                     }else  {  Etat= true;
                         }
                     } catch (SQLException ex) {
                              Logger.getLogger(TakeRepast.class.getName()).log(Level.SEVERE, null, ex);
                         }
                      }
        try {
            stm.close();
            res.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error in Exist Studen");
        }
      return Etat;
    }
      public int NBResident (int h){
          String date=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String Query="";
         int Count=0;
      if(h>=5 && h<=8){
       Query="select count(*) from Res_Take_Repat where Date_Take = '"+date+"' and "
            + "(Hour_Take >=5 AND  Hour_Take <=8)";
       //int Count=0;
        cnx.Connecting();
        try {
            stm=cnx.getConnect().createStatement();
             res=stm.executeQuery(Query);
          
             if(res.next()){
              Count=res.getInt(1);
             }
             
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error IN SQL : "+ex.getMessage());
         }
           try {
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error IN Close () :"+ex);
        }
      }else {
          if((h>=11&& h<=15)){
          Query="select count(*) from Res_Take_Repat where Date_Take = '"+date+"'"
            + "AND (Hour_Take >=11 AND  Hour_Take <=15)";
         // int Count=0;
        cnx.Connecting();
        try {
            stm=cnx.getConnect().createStatement();
             res=stm.executeQuery(Query);
          
             if(res.next()){
              Count=res.getInt(1);
             }
             
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error IN SQL : "+ex.getMessage());
         }
           try {
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error IN Close () :"+ex);
        }
        
          }else{
              if(h>=17 && h<=21){
          Query="select count(*) from Res_Take_Repat where Date_Take = '"+date+"'"
            + "AND (Hour_Take>=17 AND  Hour_Take <=21)";
          //int Count=0;
        cnx.Connecting();
        try {
            stm=cnx.getConnect().createStatement();
             res=stm.executeQuery(Query);
          
             if(res.next()){
              Count=res.getInt(1);
             }
             
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error IN SQL : "+ex.getMessage());
         }
             try {
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error IN Close () :"+ex);
        }
        
              }
          }
      }

  return Count;
}
    public int DeletAllResTakeRopast(){
      int x = 0;
        PreparedStatement preparedStmt=null; 
        String date=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String Query="Delete from Res_Take_Repat where Date_Take < '"+date+"'";
        cnx.Connecting();
       try {
            preparedStmt=cnx.getConnect().prepareStatement(Query);
  
       x=preparedStmt.executeUpdate();
  
      preparedStmt.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error SQL  in Delete User  :  "+e.getMessage());
        }
        
        try {
            
            
            cnx.Deconnect();
          
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in close Connection");
        }
        
        return x;
    }
     public static void main(String[] args) {
 //   new  TakeRepast().DeletAllResTakeRopast();
         System.out.println(""+new  TakeRepast().NBResident(12));
    }

    public int getID_Repat() {
        return ID_Repat;
    }

    public void setID_Repat(int ID_Repat) {
        this.ID_Repat = ID_Repat;
    }

    public int getID_Student() {
        return ID_Student;
    }

    public void setID_Student(int ID_Student) {
        this.ID_Student = ID_Student;
    }
    public int getHour_Take() {
        return Hour_Take;
    }
    public void setHour_Take(int Hour_Take) {
        this.Hour_Take = Hour_Take;
    }
    public Date getDate_Take() {
        return Date_Take;
    }
    public void setDate_Take(Date Date_Take) {
        this.Date_Take = Date_Take;
    }

    
}
