
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package residence;

import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
//import javax.management.monitor.MonitorNotification;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author admin
 */
public class Resident_Gl {
    
    private int Id_Resident_Gl;
    private String first_name;
    private String last_name;
    private int Numbre_CardRes;
    private String Barcode;
    private Date DateBirth;
    private String placeBirth;
    private int ID_gender;
    private int Id_Ptrn_Res;
    private int CaseResident;
    private File ImageRes;
   private int ValConfiramation=1;
    Get_Info_DB get_Info_DB=new Get_Info_DB();
   Home1 home1;
    private Statement stm;
    private ResultSet res;
     ConnectionDB cnx=new ConnectionDB();
    MessageErrorControl  messagerror;
     ArrayList<String> ListInfo=new  ArrayList<>();
    public  Resident_Gl(String first_name,String last_name,int Numbre_CardRes,
            String Barcode,Date DateBirth, String placeBirth,int ID_gender,int Id_Ptrn_ResS,int CaseResident){
    
        this.first_name=first_name;
        this.last_name=last_name;
        this.Numbre_CardRes=Numbre_CardRes;
        this.Barcode=Barcode;
        this.DateBirth=DateBirth;
        this.placeBirth=placeBirth;
        this.ID_gender=ID_gender;
        this.Id_Ptrn_Res=Id_Ptrn_ResS;
        this.CaseResident=CaseResident;
        
  }
    
  /* int ConfIRMAResGl;*/
    public Resident_Gl(){
    
    }
    
    public void AddRsident(){
    
      PreparedStatement prstm=null;
    String Query="INSERT INTO Resident_Gl (Name_Resident,LastName_Resident,NumCard_Resident,CodeBare_Resident,"
            + "DateBirth,PlaceBirth,ID_gender,Id_Ptrn_Res,ID_Case_Resident) "
                  + "VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            prstm=getCnx().getConnect().prepareStatement(Query);
            prstm.setString(1, getFirst_name());
            prstm.setString(2, getLast_name());
             Numbre_CardRes=(Get_MAX_Numbre_CardRes(0)+1);
            prstm.setInt(3,Numbre_CardRes); //Corretct For TEsting 
            //prstm.setInt(3,11219);
            prstm.setString(4 ,"251"+""+Numbre_CardRes+"");
            prstm.setDate(5,new java.sql.Date(getDateBirth().getTime()));
            prstm.setString(6, getPlaceBirth());
            prstm.setInt(7, getID_gender());
            prstm.setInt(8, getId_Ptrn_Res());
            prstm.setInt(9, getCaseResident());
           // prstm.setBytes(9,getImage());
            int x=prstm.executeUpdate();
            if (x>0) {
                //JOptionPane.showMessageDialog(null, "The Resident is added ");
                System.out.println("Succes Add First data of Resident");
            }else 
                JOptionPane.showMessageDialog(null, "Error in added ");
            
            
            InsertImageResidentInDatabase(ImageRes, Numbre_CardRes);
            this.ValConfiramation=1;
        prstm.close(); 
        } catch (HeadlessException | SQLException e) {
            this.ValConfiramation=0;
            JOptionPane.showMessageDialog(null, "حدث خطأ اثناء حفظ المعلومات"+e.getMessage());
            
             try {
              cnx.getConnect().rollback();
          } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "RollBack In Add Student Parent Class :"+ex.getMessage());
          }
        }
    }
    
    public void DisplayTabToTakeRoom(JTable tabRes,int Etat) {
    String Query="select Student_Res.ID_Rsident,NumCard_Resident,Name_Resident,LastName_Resident,DateBirth,PlaceBirth, "
          + "Student_Res.ID_Rsident,ID_Case_Resident,Student_Res.Id_Room,Room_Code\n" +
        " from Resident_Gl,Student_Res,Room\n" +
        "Where Student_Res.ID_Rsident=Resident_Gl.ID_Rsident\n" +
        " and (Student_Res.Id_Room=Room.Id_Room ) and ID_Case_Resident=2"; //and ID_Case_Resident= 2
      DefaultTableModel dftabMd=(DefaultTableModel)tabRes.getModel();   
         dftabMd.setRowCount(0);
         cnx.Connecting();
         
     get_Info_DB.filling_ArrayList("Resident_Case", ListInfo);
     try {
             stm=getCnx().getConnect().createStatement();
             res=stm.executeQuery(Query);
                     while (res.next()) {
                         int a=res.getInt("ID_Rsident");
                    
                    if(Etat==1||Etat==2 ){ 
                     //   JOptionPane.showMessageDialog(null, "Etat 1 :"+Etat);
                        if(Etat==1&& res.getString("Room_Code").equals("/")){
                         //   JOptionPane.showMessageDialog(null, "Etat 1 :"+Etat);
                         Object arg[]={ListInfo.get(res.getInt("ID_Case_Resident")-1),res.getString("Room_Code"),"طالب داخلي",
                  res.getString("PlaceBirth"),res.getDate("DateBirth"),res.getString("Name_Resident"),
                      res.getString("LastName_Resident"),res.getInt("NumCard_Resident"),res.getInt("ID_Rsident"),res.getInt("ID_Rsident")};
                        dftabMd.addRow(arg);
                        }else{
                             if(Etat==2&& !res.getString("Room_Code").equals("/")){
                        //  JOptionPane.showMessageDialog(null, "Etat 2 :"+Etat);
                         Object arg[]={ListInfo.get(res.getInt("ID_Case_Resident")-1),res.getString("Room_Code"),"طالب داخلي",
                  res.getString("PlaceBirth"),res.getDate("DateBirth"),res.getString("Name_Resident"),
                      res.getString("LastName_Resident"),res.getInt("NumCard_Resident"),res.getInt("ID_Rsident"),res.getInt("ID_Rsident")};
                         dftabMd.addRow(arg);
                             }   
                        }     
                    }  else{  // JOptionPane.showMessageDialog(null, "Etat 0 :"+Etat);
                           if(Etat !=2&&Etat !=1){
                             Object arg[]={ListInfo.get(res.getInt("ID_Case_Resident")-1),res.getString("Room_Code"),"طالب داخلي",
                  res.getString("PlaceBirth"),res.getDate("DateBirth"),res.getString("Name_Resident"),
                      res.getString("LastName_Resident"),res.getInt("NumCard_Resident"),res.getInt("ID_Rsident"),res.getInt("ID_Rsident")};
                           dftabMd.addRow(arg); 
                           }
                             } 
                  
             }                
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error in sql :"+e.getMessage());
         }
        try {
            stm.close();
            res.close();
        } catch (SQLException ex) {
            Logger.getLogger(Resident_Gl.class.getName()).log(Level.SEVERE, null, ex);
        }
         Query="select Professor_Res.ID_Rsident,NumCard_Resident,Name_Resident,LastName_Resident,DateBirth,PlaceBirth, \n" +
"          ID_Case_Resident,Room_Code\n" +
"         from Resident_Gl,Professor_Res,Room\n" +
"        Where Professor_Res.ID_Rsident=Resident_Gl.ID_Rsident\n" +
"         and (Professor_Res.Id_Room=Room.Id_Room )";
     try {
             stm=getCnx().getConnect().createStatement();
             res=stm.executeQuery(Query);
                     while (res.next()) {
                         
                if(Etat==1||Etat==2 ){ 
                     //   JOptionPane.showMessageDialog(null, "Etat 1 :"+Etat);
                        if(Etat==1&& res.getString("Room_Code").equals("/")){         
                  Object arg[]={ListInfo.get(res.getInt("ID_Case_Resident")-1),res.getString("Room_Code"),"اســـتـاذ",
                  res.getString("PlaceBirth"),res.getDate("DateBirth"),res.getString("Name_Resident"),
                      res.getString("LastName_Resident"),res.getInt("NumCard_Resident"),res.getInt("ID_Rsident")};      
                         System.out.println("residence.Professor_Res.DisplayTabToTakeRoomProf()"+1);
                  dftabMd.addRow(arg);
                  }else{
                             if(Etat==2&& !res.getString("Room_Code").equals("/")){// 
                                 Object arg[]={ListInfo.get(res.getInt("ID_Case_Resident")-1),res.getString("Room_Code"),"اســـتـاذ",
                  res.getString("PlaceBirth"),res.getDate("DateBirth"),res.getString("Name_Resident"),
                      res.getString("LastName_Resident"),res.getInt("NumCard_Resident"),res.getInt("ID_Rsident")};      
                         System.out.println("residence.Professor_Res.DisplayTabToTakeRoomProf()"+1);
                  dftabMd.addRow(arg);  }   
                        }     
                    }  else{  // JOptionPane.showMessageDialog(null, "Etat 0 :"+Etat);
                           if(Etat !=2&&Etat !=1){Object arg[]={ListInfo.get(res.getInt("ID_Case_Resident")-1),res.getString("Room_Code"),"اســـتـاذ",
                  res.getString("PlaceBirth"),res.getDate("DateBirth"),res.getString("Name_Resident"),
                      res.getString("LastName_Resident"),res.getInt("NumCard_Resident"),res.getInt("ID_Rsident")};      
                         System.out.println("residence.Professor_Res.DisplayTabToTakeRoomProf()"+1);
                  dftabMd.addRow(arg); 
             }
                             } 
                  
             }                
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error in sql :"+e.getMessage());
         }
      try {
            stm.close();
            res.close();
        } catch (SQLException ex) {
            Logger.getLogger(Resident_Gl.class.getName()).log(Level.SEVERE, null, ex);
        }
        cnx.Deconnect();
    }

    public void ChangeRoom(int ID_Rsident,int ID_RoomA,int ID_RoomN,String PaternResident){//Faridch
   PreparedStatement prstm=null;
    PreparedStatement prstmRA=null;
    PreparedStatement prstmRN=null;
     String QueryS="Update Student_Res set Id_Room =?  Where ID_Rsident="+ID_Rsident+"";
      String QueryP ="Update Professor_Res set Id_Room =?  Where ID_Rsident="+ID_Rsident+"";
      String QueryRN="Update Room set Nbr_Bed_Reserved =Nbr_Bed_Reserved+1  Where id_Room="+ID_RoomN+"";
      String QueryRA="Update Room set Nbr_Bed_Reserved =Nbr_Bed_Reserved-1  Where id_Room="+ID_RoomA+"";
   cnx.Connecting();
      switch(PaternResident){
          
          case "طالب داخلي": 
               try {
                cnx.getConnect().setAutoCommit(false);
           prstm=cnx.getConnect().prepareStatement(QueryS);
           prstm.setInt(1,ID_RoomN);
           int x=prstm.executeUpdate();
           if (x>0) {
             // JOptionPane.showMessageDialog(null, "the Id_Room of Studenr Resident is Update");
          }
           
           prstmRN=cnx.getConnect().prepareStatement(QueryRN);
         //  prstm.setInt(1, );
           int x1=prstmRN.executeUpdate();
           if (x1>0) {
             // JOptionPane.showMessageDialog(null, "the ID Of Room Update +++ ");
          }
           
            prstmRA=cnx.getConnect().prepareStatement(QueryRA);
         //  prstm.setInt(1, );
           int x2=prstmRA.executeUpdate();
           if (x2>0) {
              //JOptionPane.showMessageDialog(null, "the ID Of Room Update -----");
              
          }
           cnx.getConnect().commit();
        } catch (Exception e) {
             try {
              cnx.getConnect().rollback();
          } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "RollBack In Add Student Parent Class :"+ex.getMessage());
          }
            JOptionPane.showMessageDialog(null, "Error in Sql :"+e.getMessage());
        }finally{
        
              try {
                    prstm.close();
                  } catch (SQLException e) {
                      e.printStackTrace();
                        }
                try {
                    cnx.getConnect().setAutoCommit(true);
                } catch (SQLException ex) {
                }
        
        } try {
           prstm.close();
           prstmRA.close();   
           prstmRN.close(); 
             cnx.Deconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Resident_Gl.class.getName()).log(Level.SEVERE, null, ex);
        } 
               
              
            break;
          case "اســـتـاذ" : 
              try {
                  cnx.getConnect().setAutoCommit(false);
           prstm=cnx.getConnect().prepareStatement(QueryP);
           prstm.setInt(1,ID_RoomN);
           int x=prstm.executeUpdate();
           if (x>0) {
             // JOptionPane.showMessageDialog(null, "the Id_Room of Studenr Resident is Update");
          }
           
           prstmRN=cnx.getConnect().prepareStatement(QueryRN);
         //  prstm.setInt(1, );
           int x1=prstmRN.executeUpdate();
           if (x1>0) {
              //JOptionPane.showMessageDialog(null, "the ID Of Room Update +++ ");
          }
           
            prstmRA=cnx.getConnect().prepareStatement(QueryRA);
         //  prstm.setInt(1, );
           int x2=prstmRA.executeUpdate();
           if (x2>0) {
              //JOptionPane.showMessageDialog(null, "the ID Of Room Update -----");
          }
            cnx.getConnect().commit();
        } catch (Exception e) {
             try {
              cnx.getConnect().rollback();
          } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "RollBack In Add Student Parent Class :"+ex.getMessage());
          }
            JOptionPane.showMessageDialog(null, "Error in Sql :"+e.getMessage());
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
               try {
           prstm.close();
           prstmRA.close();   
           prstmRN.close(); 
             cnx.Deconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Resident_Gl.class.getName()).log(Level.SEVERE, null, ex);
        } 
               break;
             
      }
     }
  public void DisplayAllResidentInTable(JTable tabRes,int Id_Patern, JLabel LabNameRes,String NameResidents,JLabel labCount){
         String Query="Select NumCard_Resident,Name_Resident,LastName_Resident ,DateBirth,PlaceBirth FROM Resident_Gl WHERE Id_Ptrn_Res="+Id_Patern+"";
         DefaultTableModel dftabMd=(DefaultTableModel)tabRes.getModel();   
         dftabMd.setRowCount(0);
        cnx.Connecting();
         try {
             stm=getCnx().getConnect().createStatement();
             res=stm.executeQuery(Query);
             while (res.next()) {                 
                 Object arg[]={res.getString("PlaceBirth"),res.getDate("DateBirth"),res.getString("Name_Resident"),res.getString("LastName_Resident"),res.getInt("NumCard_Resident")};
                 dftabMd.addRow(arg);
             }
             LabNameRes.setText(NameResidents);
             labCount.setText(""+tabRes.getRowCount());
         } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Error in sql :"+e.getMessage());
         }
        try {
            stm.close();
            res.close();
            getCnx().Deconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Resident_Gl.class.getName()).log(Level.SEVERE, null, ex);
        }  
     }
   /**********************************************************************************/
    public void DisplayAllResidentInTablToConsult(JTable tabRes, JLabel LabNameRes,String NameResidents,JLabel labCount,String NamPatern){
         DefaultTableModel dftabMd=(DefaultTableModel)tabRes.getModel();   
         dftabMd.setRowCount(0);
         String  Query;
         
         cnx.Connecting();
     switch(NamPatern){
        case "طالب داخلي":
            Query="select  Name_Resident,LastName_Resident ,DateBirth,PlaceBirth,NumCard_Resident,Gender.gender,\n" +
"                Student_Res.ID_Wilaya,NameWilaya,Address,Student_Res.Id_BranchStd,BranchStd_Name,Student_Res.Id_LevelStudy,\n" +
"                Level_study,Resident_Case.Resident_Case,Student_Res.Id_Room,Room.Room_Code\n" +
"                 from Resident_Gl,Gender,Student_Res,Wilaya,Branch_Study,Room,Level_Study,\n" +
"                Resident_Case  where  Resident_Gl.ID_Rsident=Student_Res.ID_Rsident \n" +
"                and Resident_Gl.ID_gender=Gender.ID_gender\n" +
"                and Resident_Case.ID_Case_Resident=Resident_Gl.ID_Case_Resident\n" +
"                and Wilaya.ID_Wilaya=Student_Res.ID_Wilaya\n" +
"                and Student_Res.Id_BranchStd=Branch_Study.Id_BranchStd\n" +
"                and Student_Res.Id_LevelStudy=Level_Study.Id_LevelStudy\n" +
"                    and Room.Id_Room=Student_Res.Id_Room order by Room_Code";   
               try {
             stm=getCnx().getConnect().createStatement();
             res=stm.executeQuery(Query);
                     while (res.next()) {                   
                  Object arg[]={res.getString("Resident_Case"),res.getString("Room_Code"),res.getString("Level_study"),res.getString("BranchStd_Name"),res.getString("Address"),
                      res.getString("NameWilaya"),res.getString("gender"),
                      res.getString("PlaceBirth"),res.getDate("DateBirth"),
                      res.getString("LastName_Resident"),res.getString("Name_Resident"),res.getInt("NumCard_Resident")};
                 // System.out.println("residence.Student.DisplayAllStudentInTable()"+arg[0]+"   "+arg[1]+"   "+arg[2]+"    "+arg[3]+"  "+arg[4]+" "+arg[4]);       
                  dftabMd.addRow(arg);
             }
             LabNameRes.setText(NameResidents);
             labCount.setText(""+tabRes.getRowCount());          
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error in sql :"+e.getMessage());
         }
         break;
         case "اســـتـاذ":
            
            Query="Select Name_Resident,LastName_Resident ,DateBirth,PlaceBirth,NumCard_Resident," +
                "Gender.gender,Resident_Case,Professor_Res.ID_Profession,Profession,Professor_Res.Id_Room,Room.Room_Code   " +
                "FROM  Resident_Gl,Gender,Resident_Case,Professor_Res,Profession,Room WHERE " +
                "  Resident_Gl.ID_gender=Gender.ID_gender" +
                " and Resident_Case.ID_Case_Resident=Resident_Gl.ID_Case_Resident" +
                "  and Resident_Gl.ID_Rsident=Professor_Res.ID_Rsident " +
                "  and Professor_Res.ID_Profession=Profession.ID_Profession" +
                " and Room.Id_Room=Professor_Res.Id_Room";            
             try {
             stm=getCnx().getConnect().createStatement();
             res=stm.executeQuery(Query);
                     while (res.next()) {
                       
                  Object arg[]={res.getString("Resident_Case"),res.getString("Room_Code"),"اســـتـاذ",res.getString("gender"),
                      res.getString("PlaceBirth"),res.getDate("DateBirth"),
                      res.getString("LastName_Resident"),res.getString("Name_Resident"),res.getInt("NumCard_Resident")};
                 // System.out.println("residence.Student.DisplayAllStudentInTable()"+res.getInt("NumCard_Resident") +" "+res.getString("Name_Resident")+" "+res.getString("LastName_Resident"));       
                  dftabMd.addRow(arg);
             }
             LabNameRes.setText(NameResidents);
             labCount.setText(""+tabRes.getRowCount());          
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error in sql Prof:"+e.getMessage());
         }
           break;
        case "طالب خـــارجــي": 
             Query="select  Name_Resident,LastName_Resident ,DateBirth,PlaceBirth,NumCard_Resident,Gender.gender,\n" +
"             Student_ResExt.Id_BranchStd,BranchStd_Name,Student_ResExt.Id_LevelStudy,\n" +
"                Level_study,Resident_Case.Resident_Case\n" +
"                from Resident_Gl,Gender,Student_ResExt,Branch_Study,Level_Study,\n" +
"                Resident_Case  where  Resident_Gl.ID_Rsident=Student_ResExt.ID_Rsident\n" +
"                and Resident_Gl.ID_gender=Gender.ID_gender and\n" +
"                Resident_Case.ID_Case_Resident=Resident_Gl.ID_Case_Resident\n" +
"                and Student_ResExt.Id_BranchStd=Branch_Study.Id_BranchStd\n" +
"                and Student_ResExt.Id_LevelStudy=Level_Study.Id_LevelStudy";   
               try {
             stm=getCnx().getConnect().createStatement();
             res=stm.executeQuery(Query);
                     while (res.next()) {
                    Object arg[]={res.getString("Resident_Case"),res.getString("Level_study"),res.getString("BranchStd_Name"),            
                      res.getString("PlaceBirth"),res.getDate("DateBirth"),res.getString("gender"),
                      res.getString("LastName_Resident"),res.getString("Name_Resident"),res.getInt("NumCard_Resident")};       
                       
                  dftabMd.addRow(arg);
             }
             LabNameRes.setText(NameResidents);
             labCount.setText(""+tabRes.getRowCount());          
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error in sql :"+e.getMessage());
         }break;
        case "عـــامـــل":  
         Query="select  Name_Resident,LastName_Resident ,DateBirth,PlaceBirth,NumCard_Resident,Gender.gender\n" +
        ",Employer.ID_Profession,Profession ,Resident_Case.Resident_Case\n" +
        " from Resident_Gl,Gender,Employer,Profession,Resident_Case where \n" +
        " Resident_Gl.ID_Rsident=Employer.ID_Rsident and Employer.ID_Profession=Profession.ID_Profession\n" +
        "and Resident_Gl.ID_gender=Gender.ID_gender\n" +
        "and Resident_Case.ID_Case_Resident=Resident_Gl.ID_Case_Resident";
            
        try {
             stm=getCnx().getConnect().createStatement();
             res=stm.executeQuery(Query);
                     while (res.next()) {
                       
                  Object arg[]={res.getString("Resident_Case"),res.getString("Profession"),res.getString("gender"),res.getString("PlaceBirth"),res.getDate("DateBirth"),
                      res.getString("LastName_Resident"),res.getString("Name_Resident"),res.getInt("NumCard_Resident")};
                 // System.out.println("residence.Student.DisplayAllStudentInTable()"+res.getInt("NumCard_Resident") +" "+res.getString("Name_Resident")+" "+res.getString("LastName_Resident"));       
                  dftabMd.addRow(arg);
             }
             LabNameRes.setText(NameResidents);
             labCount.setText(""+tabRes.getRowCount());          
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error in sql Prof:"+e.getMessage());
         }
           break;
             
        
        default:JOptionPane.showMessageDialog(null, "Cannot get Name Correct From resident");
        break;
    
     }// ferme switch
                  
        try {
            stm.close();
            res.close();
            getCnx().Deconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Resident_Gl.class.getName()).log(Level.SEVERE, null, ex);
        }  
     }
    
    
   /**********************************************************************************/
   public void GetInformationResidentN(int NumCard_Resident ,JTextField Numcard,JComboBox CasResident,JTextField txtNam_std5,JTextField txtSurNam_std5,
                                        JFormattedTextField DatBirth_std3,JTextField txtPlcBirth_std5,JCheckBox CheckMale2,JCheckBox checkFemal2,
                                        JTextField txt_NumInsc2,
                                        JTextField txtNam_Father2,JTextField txtNam_mother2,JTextField txtProfission_Std2,JTextField txtProfission_Moth2,
                                        JTextField txtAddress_Std2,JComboBox WilayaList2,
                                        JTextField txtDairaStd2,
                                        JComboBox txtCommuneStd2,
                                        JComboBox National_list2,
                                        JRadioButton Sti_Single3,
                                        JRadioButton Std_Maried2,JTextField TtxtBacYear2,
                                        JTextField txtBacMoy2,JTextField txtPlaceGetBac2,JComboBox txtBranch_std5,JComboBox txtDepa_Std2,
                                        JComboBox LevelStd2,JLabel LabImage,String NamPatern,String SituationFmly,
                                        JTextField Name_ResidentFrUp,JTextField LastName_ResidentFrUp,JTextField PlaceBirthFrUp,
                                        JTextField Name_FatherFrUp,JTextField Name_MotherFrUp,JTextField LastName_MotheFrUp,JTextField NamMrA) throws IOException{
   String Query;
       cnx.Connecting();
    switch(NamPatern){
        case "طالب داخلي":
             Query="Select Name_Resident,LastName_Resident ,DateBirth,PlaceBirth,Gender.gender,Num_InscritBac,Name_Father,FullName_Mother,ProfessionFather,ProfessionMother,\n" +
"Address,ID_Wilaya,Name_Daira,Name_Commune  ,Id_Nationalite,SituationFamily,BacYear,BacMoyen,PlaceGetBac,Id_BranchStd,DateInscrp,\n" +
"Id_Faculty,Id_LevelStudy, imageStd,ID_Case_Resident,\n" +
"Name_ResidentFr,LastName_ResidentFr,PlaceBirthFr,Name_FatherFr,Name_MotherFr,LastName_MotheFr,LastNamMothAR\n" +
" FROM  Resident_Gl,Gender,Student_Res Where Resident_Gl.NumCard_Resident="+NumCard_Resident+
" and Resident_Gl.ID_gender=Gender.ID_gender and Resident_Gl.ID_Rsident=Student_Res.ID_Rsident";
            
               try {
            stm=getCnx().getConnect().createStatement();
            res=stm.executeQuery(Query);
             
            if (res.next()){
               Numcard.setText(""+NumCard_Resident);
               txtNam_std5.setText(res.getString("Name_Resident"));
               txtSurNam_std5.setText(res.getString("LastName_Resident"));
               SimpleDateFormat format=new  SimpleDateFormat("dd/MM/yyyy");   
               String s=format.format(res.getDate("DateBirth"));
               DatBirth_std3.setText(s);
               txtPlcBirth_std5.setText(res.getString("PlaceBirth"));
               if(res.getString("gender").equals("مذكر")){
                   CheckMale2.setSelected(true);
                   ID_gender=1;                //this Gender For IF You do not change check For Gender ('Gender Take The last Value ')
                                               // Donc we give it The correct VALUE Of Gender Fou Update  Get With Methode ResidentStd
                                               //Std Or Prof Or Employer .GetIDGender To put New Resident Std /Prf /Emp (Nm,Last,...,Gender)
                   checkFemal2.setSelected(false);
               }else{
                   checkFemal2.setSelected(true);
                   ID_gender=2;
                   CheckMale2.setSelected(false);
               }
            txt_NumInsc2.setText(res.getString("Num_InscritBac"));
            txtNam_Father2.setText(res.getString("Name_Father"));
            txtNam_mother2.setText(res.getString("FullName_Mother"));
            txtProfission_Std2.setText(res.getString("ProfessionFather"));
            txtProfission_Moth2.setText(res.getString("ProfessionMother"));
            txtAddress_Std2.setText(res.getString("Address"));
            txtDairaStd2.setText(res.getString("Name_Daira"));           
            
            if(res.getInt("ID_Wilaya")==49){ 
                WilayaList2.setSelectedIndex(0);
            }else WilayaList2.setSelectedIndex(res.getInt("ID_Wilaya"));
            
            txtCommuneStd2.setSelectedItem((String)res.getString("Name_Commune"));            
            if(res.getString("SituationFamily").equals("اعـــزب")){
                 Sti_Single3.setSelected(true);
                 SituationFmly=res.getString("SituationFamily");
                 Std_Maried2.setSelected(false); 
             }else{
                 if(res.getString("SituationFamily").equals("متزوج")){
                     Std_Maried2.setSelected(true); 
                      SituationFmly=res.getString("SituationFamily");
                     Sti_Single3.setSelected(false);
                 }
            }
            TtxtBacYear2.setText(String.valueOf(res.getInt("BacYear")));
            txtBacMoy2.setText(res.getFloat("BacMoyen")+"");
            txtPlaceGetBac2.setText(res.getString("PlaceGetBac"));
            
            
            if(res.getInt("Id_Nationalite")==19){
               National_list2.setSelectedIndex(0);
            }else National_list2.setSelectedItem((String)get_Info_DB.GetNAME_From_DB("Nationalite", "Nationalite", "Id_Nationalite",res.getInt("Id_Nationalite")));
            
            if(res.getInt("Id_BranchStd")==24){
               txtBranch_std5.setSelectedIndex(0);
            }else txtBranch_std5.setSelectedItem((String)get_Info_DB.GetNAME_From_DB("BranchStd_Name","Branch_Study", "Id_BranchStd", res.getInt("Id_BranchStd")));
            
            
            if(res.getInt("Id_Faculty")==7){
               txtDepa_Std2.setSelectedIndex(0);
            }else txtDepa_Std2.setSelectedItem((String)get_Info_DB.GetNAME_From_DB("NameFact", "Faculty", "Id_Faculty", res.getInt("Id_Faculty")));         
            
            /*if(res.getInt("Id_LevelStudy")==9){    
               LevelStd2.setSelectedIndex(0);
            }else LevelStd2.setSelectedIndex( res.getInt("Id_LevelStudy"));*/
            
             if(res.getInt("Id_LevelStudy")==9){
               LevelStd2.setSelectedIndex(0);
            }else {//LevelStd2.setSelectedIndex(res.getInt("Id_LevelStudy"));
              LevelStd2.setSelectedItem((String)get_Info_DB.GetNAME_From_DB("DescriptionLevel", "Level_Study", "Id_LevelStudy", res.getInt("Id_LevelStudy")));         
              
          }
            
              byte tab[]=res.getBytes("imageStd");
              int indexCase=(res.getInt("ID_Case_Resident")-1);
             CasResident.setSelectedIndex(indexCase);
             if (res.getBytes("imageStd")!=null) {
                InputStream in = new ByteArrayInputStream(tab);
			BufferedImage bImageFromConvert = ImageIO.read(in);
                       LabImage.setIcon(new ImageIcon(new ImageIcon(bImageFromConvert).getImage().getScaledInstance(LabImage.getWidth(), LabImage.getHeight(), Image.SCALE_SMOOTH) )); 
                }else{
               LabImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/imageRes.png")));
                 }
              Name_ResidentFrUp.setText(res.getString("Name_ResidentFr"));
             LastName_ResidentFrUp.setText(res.getString("LastName_ResidentFr"));
              PlaceBirthFrUp.setText(res.getString("PlaceBirthFr"));
               Name_FatherFrUp.setText(res.getString("Name_FatherFr"));
              Name_MotherFrUp.setText(res.getString("Name_MotherFr"));
              LastName_MotheFrUp.setText(res.getString("LastName_MotheFr"));
              NamMrA.setText(res.getString("LastNamMothAR"));
             }else{
                  messagerror=new MessageErrorControl(home1, true, "عذرا لم يتم العثور علي رقم البطاقة  ");
        messagerror.setVisible(true);
             }
              
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error IN SQL in Get Information Resident: "+ex.getMessage());
         }
              
        break;
        case "اســـتـاذ":

             Query="Select Name_Resident,LastName_Resident ,DateBirth,PlaceBirth,Gender.gender,imageStd,ID_Case_Resident FROM  Resident_Gl,Gender Where NumCard_Resident="+ NumCard_Resident+
"              and Resident_Gl.ID_gender=Gender.ID_gender "; 
              try {
            stm=getCnx().getConnect().createStatement();
             res=stm.executeQuery(Query);
             
             if (res.next()){

               Numcard.setText(""+NumCard_Resident);
                 int indexCase=(res.getInt("ID_Case_Resident")-1);
               CasResident.setSelectedIndex(indexCase); 
               txtNam_std5.setText(res.getString("Name_Resident"));
               txtSurNam_std5.setText(res.getString("LastName_Resident"));
               SimpleDateFormat format=new  SimpleDateFormat("dd/MM/yyyy");   
               String s=format.format(res.getDate("DateBirth"));
                DatBirth_std3.setText(s);
                 txtPlcBirth_std5.setText(res.getString("PlaceBirth"));
               if(res.getString("gender").equals("مذكر")){
                   CheckMale2.setSelected(true);
                    ID_gender=1;  
                   checkFemal2.setSelected(false);
               }else{
                   checkFemal2.setSelected(true);
                    ID_gender=2;  
                   CheckMale2.setSelected(false);
               }
               byte tab[]=res.getBytes("imageStd"); 
             // JOptionPane.showMessageDialog(null, "Table Byte "+res.getBytes("imageStd"));
             if (res.getBytes("imageStd")!=null) {
                     InputStream in = new ByteArrayInputStream(tab);
			BufferedImage bImageFromConvert = ImageIO.read(in);
                       // .getScaledInstance(LabImage.getWidth(), LabImage.getHeight(), Image.SCALE_SMOOTH)
                       LabImage.setIcon(new ImageIcon(new ImageIcon(bImageFromConvert).getImage().getScaledInstance(LabImage.getWidth(), LabImage.getHeight(), Image.SCALE_SMOOTH) )); 
                      }else{
                LabImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/imageRes.png")));
                 }
            }else{
                  messagerror=new MessageErrorControl(home1, true, "عذرا لم يتم العثور علي رقم البطاقة  ");
        messagerror.setVisible(true);     
                     }
              
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error IN SQL in Get Information Resident: "+ex.getMessage());
         }
      
        break;
        case "طالب خـــارجــي": 
            Query="Select Name_Resident,LastName_Resident ,DateBirth,PlaceBirth,Gender.gender,Num_InscritBac, Id_BranchStd,Id_LevelStudy,imageStd,ID_Case_Resident FROM  Resident_Gl,Gender,Student_ResExt Where Resident_Gl.NumCard_Resident="+NumCard_Resident +
"             and Resident_Gl.ID_gender=Gender.ID_gender and Resident_Gl.ID_Rsident=Student_ResExt.ID_Rsident";
              try {
            stm=getCnx().getConnect().createStatement();
             res=stm.executeQuery(Query);
             if (res.next()){
               Numcard.setText(""+NumCard_Resident);
               int IndxCas=res.getInt("ID_Case_Resident");
                 if (IndxCas==8) {
                     CasResident.setSelectedIndex(0); 
                 }else {CasResident.setSelectedIndex(1); }
               txt_NumInsc2.setText(res.getString("Num_InscritBac"));
               txtNam_std5.setText(res.getString("Name_Resident"));
               txtSurNam_std5.setText(res.getString("LastName_Resident"));
               SimpleDateFormat format=new  SimpleDateFormat("dd/MM/yyyy");   
               String s=format.format(res.getDate("DateBirth"));
                DatBirth_std3.setText(s);
               txtPlcBirth_std5.setText(res.getString("PlaceBirth"));
                 if(res.getString("gender").equals("مذكر")){
               //    JOptionPane.showMessageDialog(null, "ID_gender :"+res.getString("gender"));
                   CheckMale2.setSelected(true);
                   ID_gender=1;
                   checkFemal2.setSelected(false);
               }else{
                //JOptionPane.showMessageDialog(null, "ID_gender :"+res.getString("gender"));
                   checkFemal2.setSelected(true);
                   ID_gender=2;
                   CheckMale2.setSelected(false);
               }
                  if(res.getInt("Id_BranchStd")==24){
               txtBranch_std5.setSelectedIndex(0);
            }else txtBranch_std5.setSelectedItem((String)get_Info_DB.GetNAME_From_DB("BranchStd_Name","Branch_Study", "Id_BranchStd", res.getInt("Id_BranchStd")));
            
            if(res.getInt("Id_LevelStudy")==9){
               LevelStd2.setSelectedIndex(0);
            }else LevelStd2.setSelectedIndex(res.getInt("Id_LevelStudy"));
                byte tab[]=res.getBytes("imageStd"); 
              if (res.getBytes("imageStd")!=null) {
                 InputStream in = new ByteArrayInputStream(tab);
			BufferedImage bImageFromConvert = ImageIO.read(in);
                       // .getScaledInstance(LabImage.getWidth(), LabImage.getHeight(), Image.SCALE_SMOOTH)
                       LabImage.setIcon(new ImageIcon(new ImageIcon(bImageFromConvert).getImage().getScaledInstance(LabImage.getWidth(), LabImage.getHeight(), Image.SCALE_SMOOTH) )); 
                 }else{   
                LabImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/imageRes.png")));
                 }
             }else {
              messagerror=new MessageErrorControl(home1, true, "عذرا لم يتم العثور علي رقم البطاقة  ");
        messagerror.setVisible(true);
             
             }
             }catch(IOException | SQLException e){
                  JOptionPane.showMessageDialog(null, "Error in Get Data From DataBase With Number Card "+e.getMessage());
                     }            
             break;
        case "عـــامـــل":     
            
            Query="Select Name_Resident,LastName_Resident ,DateBirth,PlaceBirth,Gender.gender,ID_Profession,imageStd,ID_Case_Resident "
                    + "FROM  Resident_Gl,Gender,Employer Where Resident_Gl.NumCard_Resident="+NumCard_Resident +
"             and Resident_Gl.ID_gender=Gender.ID_gender and Resident_Gl.ID_Rsident=Employer.ID_Rsident";
            
              try {
            stm=getCnx().getConnect().createStatement();
             res=stm.executeQuery(Query);
             if (res.next()){
               Numcard.setText(""+NumCard_Resident);
               
               int IndxCas=res.getInt("ID_Case_Resident");
                 if (IndxCas==10) {
                     CasResident.setSelectedIndex(0); 
                 }else {CasResident.setSelectedIndex(1);
                 }
               
               
               txtNam_std5.setText(res.getString("Name_Resident"));
               txtSurNam_std5.setText(res.getString("LastName_Resident"));
               SimpleDateFormat format=new  SimpleDateFormat("dd/MM/yyyy");   
               String s=format.format(res.getDate("DateBirth"));
                DatBirth_std3.setText(s);
               txtPlcBirth_std5.setText(res.getString("PlaceBirth"));
                 if(res.getString("gender").equals("مذكر")){
                    CheckMale2.setSelected(true);
                   ID_gender=1;
                   checkFemal2.setSelected(false);
               }else{
                   checkFemal2.setSelected(true);
                    ID_gender=2;
                   CheckMale2.setSelected(false);
               }
                 if(res.getInt("ID_Profession")==43){
               LevelStd2.setSelectedIndex(0);
            }else{  LevelStd2.setSelectedIndex(res.getInt("ID_Profession"));}
             byte tab[]=res.getBytes("imageStd");   
              if (res.getBytes("imageStd")!=null) {
                     InputStream in = new ByteArrayInputStream(tab);
			BufferedImage bImageFromConvert = ImageIO.read(in);
                     LabImage.setIcon(new ImageIcon(new ImageIcon(bImageFromConvert).getImage().getScaledInstance(LabImage.getWidth(), LabImage.getHeight(), Image.SCALE_SMOOTH) )); 
                       }else{
                  LabImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/imageRes.png")));
                 }
            }else {
                messagerror=new MessageErrorControl(home1, true, "عذرا لم يتم العثور علي رقم البطاقة  ");
                 messagerror.setVisible(true);
             }
             }catch(Exception ex){
                     JOptionPane.showMessageDialog(null, "Error in Sql Get Information "+ex.getMessage());
                     }
        break;  
        
        default:JOptionPane.showMessageDialog(null, "Cannot get Name Correct From resident");
        break;
    }
        try {
            stm.close();
            res.close();
            getCnx().Deconnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error IN Close () :"+ex);
        } 
   }
  /***********************************************************************************/
   public void GetInformationResident(int NumCard_Resident,JTextField Numcard,JTextField txtNam_std5,JTextField txtSurNam_std5,
                                        JTextField DatBirth_std3,JTextField txtPlcBirth_std5,JCheckBox CheckMale2,JCheckBox checkFemal2){
      String Query="Select ID_Rsident,Name_Resident,LastName_Resident ,DateBirth,PlaceBirth,Gender.gender FROM  Resident_Gl,Gender Where NumCard_Resident="+NumCard_Resident+""
              + "and Resident_Gl.ID_gender=Gender.ID_gender ";  
        cnx.Connecting();
        try {
            stm=getCnx().getConnect().createStatement();
             res=stm.executeQuery(Query);
             
             if (res.next()){
                 
                 System.out.println("residence.Resident_Gl.GetInformationResident()"+res.getString("ID_Rsident")+"");
                  System.out.println("residence.Resident_Gl.GetInformationResident()"+res.getString("Name_Resident")+"");
                 System.out.println("residence.Resident_Gl.GetInformationResident()"+res.getString("gender")+"");
               Numcard.setText(""+NumCard_Resident);
               txtNam_std5.setText(res.getString("Name_Resident"));
               txtSurNam_std5.setText(res.getString("LastName_Resident"));
               SimpleDateFormat format=new  SimpleDateFormat("dd/MM/yyyy");   
               String s=format.format(res.getDate("DateBirth"));
               JOptionPane.showMessageDialog(null, ""+res.getDate("DateBirth"));
               DatBirth_std3.setText(s);
                //JOptionPane.showMessageDialog(null, " "+res.getInt(1)+" "+res.getString(2)+" "+res.getString(3));
               txtPlcBirth_std5.setText(res.getString("PlaceBirth"));
               if(res.getString("gender").equals("مذكر")){
                   CheckMale2.setSelected(true);
                   checkFemal2.setSelected(false);
               }else{
                   checkFemal2.setSelected(true);
                   CheckMale2.setSelected(false);
               }
             }else
                 JOptionPane.showMessageDialog(null, "Error in Get Information Resident  ");
              
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error IN SQL in Get Information Resident: "+ex.getMessage());
         }
      
        try {
            stm.close();
            res.close();
            getCnx().Deconnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error IN Close () :"+ex);
        } 
    }
    
   /**********************************************************************/
   
          public void FilterResident(String Query,JTable tab,DefaultTableModel dm){  //filtrer dans le tableau fournisseur
        TableRowSorter<DefaultTableModel> tr=new TableRowSorter<DefaultTableModel>(dm);
        tab.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(Query));   
    }
/*************************************************************************
public File UploadPictResident(JLabel label){
  String path = "D:\\Photo_residents\\";
 // String Path1="\\src\\residence\\Image\\ResidentIc.png";
  String FileName = "";  
  
  File fileImage=new File("src\\residence\\Image\\imageRes.png");
  
  File dir = new File(path);
  File[] files = dir.listFiles();
    
    if (files.length==1) {
         fileImage=files[0];
         FileName=fileImage.getAbsolutePath();
        System.out.println("residence.Resident_Gl.UploadPictResident()"+FileName);
         ImageIcon imgeicon=new ImageIcon (new ImageIcon(FileName).getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
          label.setIcon(imgeicon);
          return fileImage;
    }else {
        
    return fileImage;
    }
}
********************************************************************/

public void InsertImageResident(File FileImg,int NumCard){

    PreparedStatement prstm=null;
    byte [] ResidentImg = null;
  
     try {
          //  File image=new File(FileName);
            FileInputStream fis=new FileInputStream(FileImg);
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            byte[]Buf=new  byte[1024];
            for (int ReadNum; (ReadNum=fis.read(Buf))!=-1; ) {
                bos.write(Buf, 0, ReadNum);
            }
            ResidentImg=bos.toByteArray();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error In File "+e.getMessage());
        }
      String Query="UPDATE Resident_Gl SET imageStd=? WHERE NumCard_Resident ="+NumCard;
  //   cnx.Connecting();
      try {
           prstm=cnx.getConnect().prepareStatement(Query);
           prstm.setBytes(1, ResidentImg);
           int x=prstm.executeUpdate();
           if (x>0) {
              //JOptionPane.showMessageDialog(null, "The Image is Updated");
          }else {
           //JOptionPane.showMessageDialog(null, "Error in Image  Updated");
           }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error in Sql :"+e.getMessage());
        }
      
      try {
        prstm.close();
      //  cnx.Deconnect();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error in Sql :"+e.getMessage());
    }

}
/******************EEEEEEEEEE
     * @param FileImgE*************************/
public void InsertImageResidentInDatabase(File FileImg, int NumCard){

    int len;

    PreparedStatement prstm=null;
      try {
               //File file = new File("E:\\BUREAU\\REP251\\f3836.jpg");
                        FileInputStream fis = new FileInputStream(FileImg);
                        len = (int)FileImg.length();
    
      String Query="UPDATE Resident_Gl SET imageStd=? WHERE NumCard_Resident ="+NumCard;
           prstm=cnx.getConnect().prepareStatement(Query);
           //prstm.setBytes(1, ResidentImg);
           prstm.setBinaryStream(1, fis, len);
           
           int x=prstm.executeUpdate();
           
           if (x>0) {
    //          JOptionPane.showMessageDialog(null, "The Image is Updated");
          }else {
           JOptionPane.showMessageDialog(null, "Error in Image  Updated");
           }
           
           prstm.close();
        } catch (HeadlessException | FileNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error in Sql :"+e.getMessage());
        }
        //cnx.Deconnect();
      

}

int VarPht=0;

public void SetVarPht(int VarPht){
this.VarPht=VarPht;
}
public int GetVarPht(){
return this.VarPht;
}
/*******************************************************************************/    
public void UpdateResident(  int ID_Resident, int NumCard){
    PreparedStatement prstm=null;
 Numbre_CardRes=NumCard;
    String Query="UPDATE Resident_Gl SET Name_Resident=? ,LastName_Resident=? ,"
            + " DateBirth=? ,PlaceBirth=?,ID_gender=?,ID_Case_Resident=?  WHERE ID_Rsident="+ID_Resident;
   // cnx.Connecting();
    try {
        prstm=cnx.getConnect().prepareStatement(Query);
        prstm.setString(1, this.first_name);
        prstm.setString(2,this.last_name);
        prstm.setDate(3, new java.sql.Date(this.DateBirth.getTime()));
        prstm.setString(4, this.placeBirth);
        prstm.setInt(5, this.ID_gender);
        prstm.setInt(6, this.CaseResident);
        
         int x=prstm.executeUpdate();
        //JOptionPane.showMessageDialog(null, "I am After  Exectue Field Resident_Gl"); 
        
        if(VarPht==1){ 
        InsertImageResidentInDatabase(ImageRes, NumCard);
        }
        
        /*else {
        //JOptionPane.showMessageDialog(null, "We Don't Insert Image ");
        }*/
         //JOptionPane.showMessageDialog(null, "We After insert Image");
         if (x>0) {
           // JOptionPane.showMessageDialog(null, "The Resident Is Update ");
        }else  JOptionPane.showMessageDialog(null, " Error Update The Resident ");

         setValConfiramation(1);
    } catch (Exception e) {
        setValConfiramation(0);
              JOptionPane.showMessageDialog(null, "Error in SQL Class Resident "+e.getMessage());
            try {
              cnx.getConnect().rollback();
          } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "RollBack In Add Student Parent Class :"+ex.getMessage());
          }
    }
    try {
        prstm.close();
    } catch (SQLException e) {
    }
}
public void ReserveRoom(int ID_Rsident,int ID_Room,String PaternResident){
    PreparedStatement prstmS=null;
    PreparedStatement prstmR=null;
      String QueryS="Update Student_Res set Id_Room =?  Where ID_Rsident="+ID_Rsident+"";
      String QueryP ="Update Professor_Res set Id_Room =?  Where ID_Rsident="+ID_Rsident+"";
      String QueryR="Update Room set Nbr_Bed_Reserved =Nbr_Bed_Reserved+1  Where id_Room="+ID_Room+"";
         cnx.Connecting();
      switch(PaternResident){
          case "طالب داخلي": 

          cnx.Connecting();
      try {
           prstmS=cnx.getConnect().prepareStatement(QueryS);
           prstmS.setInt(1,ID_Room);
           int x=prstmS.executeUpdate();
           if (x>0) {
              //JOptionPane.showMessageDialog(null, "the Id_Room of Studenr Resident is Update");
          }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Sql :"+e.getMessage());
        }
      
       try {
           prstmR=cnx.getConnect().prepareStatement(QueryR);
         //  prstm.setInt(1, );
           int x=prstmR.executeUpdate();
           if (x>0) {
            //  JOptionPane.showMessageDialog(null, "the ID Of Room Update  ");
          }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Sql :"+e.getMessage());
        }
             
              break;
          case "اســـتـاذ" :
              
            try {
           prstmS=cnx.getConnect().prepareStatement(QueryP);
           prstmS.setInt(1,ID_Room);
           int x=prstmS.executeUpdate();
           if (x>0) {
             // JOptionPane.showMessageDialog(null, "the Id_Room of Prof is Update");
          }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Sql :"+e.getMessage());
        }
            try {
           prstmR=cnx.getConnect().prepareStatement(QueryR);
         //  prstm.setInt(1, );
           int x=prstmR.executeUpdate();
           if (x>0) {
             // JOptionPane.showMessageDialog(null, "the ID Of Room Update");
          }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Sql :"+e.getMessage());
        }
              
              break;
               
      }
    
        try {
           prstmS.close();
           prstmR.close();   
             cnx.Deconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Resident_Gl.class.getName()).log(Level.SEVERE, null, ex);
        }
} 
public void GetImageOfResident(JLabel labImg,int NumCard_Resident){
    String Query="Select imageStd FROM  Resident_Gl Where Resident_Gl.NumCard_Resident="+NumCard_Resident ;
    cnx.Connecting();
        try {
      stm=getCnx().getConnect().createStatement();
             res=stm.executeQuery(Query);
            if (res.next()) {
            byte tab[]=res.getBytes("imageStd");
            if (res.getBytes("imageStd")!=null) {
            InputStream in = new ByteArrayInputStream(tab);
            BufferedImage bImageFromConvert = ImageIO.read(in);
                // .getScaledInstance(LabImage.getWidth(), LabImage.getHeight(), Image.SCALE_SMOOTH)
            labImg.setIcon(new ImageIcon(new ImageIcon(bImageFromConvert).getImage().getScaledInstance(labImg.getWidth(), labImg.getHeight(), Image.SCALE_SMOOTH) ));
            }
            }else {
            
            JOptionPane.showMessageDialog(null, "No Image For This Number !");
            }
            
          
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error in Get Image From Data Base");
           // Logger.getLogger(Resident_Gl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error in Input  output  From Image");
            //Logger.getLogger(Resident_Gl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
        stm.close();
        res.close();
        cnx.Deconnect();
    } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "Error in Closing Operation Of Get Image  "+e.getMessage());
        e.printStackTrace();
    }
}
private Statement stm1;
private ResultSet res1;

public void GetImageOfResidentN(JLabel labImg,String Cod){
    String Query="Select imageStd FROM  Resident_Gl Where CodeBare_Resident ='"+Cod+"'" ;
    cnx.Connecting();
        try {
      stm=getCnx().getConnect().createStatement();
             res=stm.executeQuery(Query);
            if (res.next()) {
            byte tab[]=res.getBytes("imageStd");
            if (res.getBytes("imageStd")!=null) {
            InputStream in = new ByteArrayInputStream(tab);
            BufferedImage bImageFromConvert = ImageIO.read(in);
                // .getScaledInstance(LabImage.getWidth(), LabImage.getHeight(), Image.SCALE_SMOOTH)
            labImg.setIcon(new ImageIcon(new ImageIcon(bImageFromConvert).getImage().getScaledInstance(labImg.getWidth(), labImg.getHeight(), Image.SCALE_SMOOTH) ));
            }
            }else {
          //  labImg.setIcon( new ImageIcon(getClass().getResource("/residence/Image/imageRes.png")).getImage().getScaledInstance(labImg.getWidth(), labImg.getHeight(), Image.SCALE_SMOOTH));
           File ImageResidentToSv=new File("D:\\Image_Signature\\imageRes.png");
      ImageIcon imgeicon=new ImageIcon (new ImageIcon(ImageResidentToSv.getAbsolutePath()).getImage().getScaledInstance(labImg.getWidth(), labImg.getHeight(), Image.SCALE_SMOOTH));
          labImg.setIcon(imgeicon);
            }
      
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error in Get Image From Data Base");
           // Logger.getLogger(Resident_Gl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error in Input  output  From Image");
        }
        
        
        try {
        stm.close();
        res.close();
        cnx.Deconnect();
    } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "Error in Closing Operation Of Get Image  "+e.getMessage());
        e.printStackTrace();
    }
}

public int GetIformationCard(String Cod,JLabel j,JLabel j1,JLabel j2,JLabel j3,JLabel j4,JLabel j5,JLabel j6,JLabel j7){
    
       int Pateren=0;
       int ID=0;
       int ID_Case=1;
       GetImageOfResidentN(j6,Cod);
       String Query="Select Id_Ptrn_Res,ID_Rsident,NumCard_Resident,Name_Resident,LastName_Resident ,DateBirth,PlaceBirth, ID_Case_Resident"
              + " FROM  Resident_Gl Where CodeBare_Resident='"+Cod+"'";   
       cnx.Connecting();
       try {
            stm=getCnx().getConnect().createStatement();
             res=stm.executeQuery(Query);
       if(res.next()){
           ID=res.getInt("ID_Rsident"); 
         Id_Resident_Gl=ID;
           j.setText(res.getInt("NumCard_Resident")+"");
           j1.setText(res.getString("Name_Resident"));
           j2.setText(res.getString("LastName_Resident"));
           SimpleDateFormat format=new  SimpleDateFormat("dd/MM/yyyy");   
          String s=format.format(res.getDate("DateBirth"));
          j3.setText(s);                   
          j4.setText(res.getString("PlaceBirth"));
          Pateren=res.getInt("Id_Ptrn_Res");
          ID_Case=res.getInt("ID_Case_Resident");
          
          if(Pateren==1){
               String Query1 ="select  BranchStd_Name from Student_Res,Branch_Study where\n" +
    "  Student_Res.Id_BranchStd=Branch_Study.Id_BranchStd and \n" +
    "   Student_Res.ID_Rsident="+ID; 
                try {  
              stm1=getCnx().getConnect().createStatement();
             res1=stm1.executeQuery(Query1);
            if(res1.next()){ 
             j5.setText(res1.getString("BranchStd_Name"));
            }
                } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error IN SQL in GetIformationCard 1 : "+ex.getMessage());
       }
                try {
                  stm1.close();
            res1.close();
            cnx.Deconnect();
              } catch (Exception e) {
              }
                
                String CaseStdLab =GetCaseResidence(ID_Case);
                j7.setText(CaseStdLab);
          }
          
          if(Pateren==2){ 
              String Query2="select  BranchStd_Name from Student_ResExt,Branch_Study where \n" +
"              Student_ResExt.Id_BranchStd=Branch_Study.Id_BranchStd  \n" +
"            and ID_Rsident="+ID;
            try {                
              stm1=getCnx().getConnect().createStatement();
             res1=stm1.executeQuery(Query2);
            if(res1.next()){ 
             j5.setText(res1.getString("BranchStd_Name"));
            }
          } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error IN SQL in GetIformationCard 2 : "+ex.getMessage());
                  }
            try {
                  stm1.close();
            res1.close();
            cnx.Deconnect();
              } catch (Exception e) {
              }
            
            String CaseStdLab =GetCaseResidence(ID_Case);
                j7.setText(CaseStdLab);
          }
          if(Pateren==3){
            j5.setText("/");
          }
          if(Pateren==4){        
        String Query3="select  Profession from Employer,Profession\n" +
    "where Employer.ID_Profession=Profession.ID_Profession \n" +
    "and ID_Rsident="+ID;           
               try {   
              stm1=getCnx().getConnect().createStatement();
             res1=stm1.executeQuery(Query3);
           if(res1.next()){   
             j5.setText(res1.getString(1));}
          } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error IN SQL in GetIformationCard 3 : "+ex.getMessage()); 
          }
               try {
                  stm1.close();
            res1.close();
            cnx.Deconnect();
              } catch (Exception e) {
              }
               String CaseStdLab =GetCaseResidence(ID_Case);
                j7.setText(CaseStdLab);
       }
       }else{
         Pateren=0;
       }
       }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error IN SQL in GetIformationCard : "+ex.getMessage());
       }
       try {
            stm.close();
            res.close();
            
            cnx.Deconnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error IN Close () :"+ex);
        }
       return Pateren; 
   }
public void Get_Std_Of_Guest(int ID_Rsident,JLabel j1,JLabel j2,JLabel j3){
          String Query="Select NumCard_Resident,Name_Resident,LastName_Resident  FROM Resident_Gl WHERE ID_Rsident="+ID_Rsident+""; 
          cnx.Connecting();
          try {
     
             stm=cnx.getConnect().createStatement();
             res=stm.executeQuery(Query);
            if(res.next()){
                j1.setText(res.getInt("NumCard_Resident")+"");
                j2.setText(res.getString("Name_Resident"));
                j3.setText(res.getString("LastName_Resident"));
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
    
public void DisplayAllResidentInTableGuest(JTable tabRes){
         String Query="Select ID_Rsident, NumCard_Resident,Name_Resident,LastName_Resident ,"
                 + "DateBirth,PlaceBirth FROM Resident_Gl WHERE Id_Ptrn_Res=1 or Id_Ptrn_Res=2 ";
         DefaultTableModel dftabMd=(DefaultTableModel)tabRes.getModel();   
         dftabMd.setRowCount(0);
        cnx.Connecting();
         try {
             stm=getCnx().getConnect().createStatement();
             res=stm.executeQuery(Query);
             while (res.next()) {                 
               //  JOptionPane.showMessageDialog(null, " While");
                  Object arg[]={res.getString("PlaceBirth"),res.getDate("DateBirth"),
                      res.getString("LastName_Resident"),res.getString("Name_Resident"),res.getInt("NumCard_Resident"),res.getInt("ID_Rsident")};
                  dftabMd.addRow(arg);
                  
             }             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error in sql :"+e.getMessage());
         }
        try {
            stm.close();
            res.close();
            getCnx().Deconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Resident_Gl.class.getName()).log(Level.SEVERE, null, ex);
        }  
     }
    
public String GetCaseResidence(int IdCaseRes){
Statement stm1=null;
ResultSet res1=null;
String CaseStd="";
String Query="SELECT Resident_Case FROM Resident_Case where ID_Case_Resident= "+IdCaseRes; 
this.cnx.Connecting();
try {
     stm1 =cnx.getConnect().createStatement();
     res1=stm1.executeQuery(Query);
     if (res1.next()) {
        CaseStd=res1.getString("Resident_Case");
    }
   
     
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error In Get Cas Residence "+e.getMessage());
    }
    try {
        res1.close();
        stm1.close();
    } catch (Exception e) {
    JOptionPane.showMessageDialog(null, "Error In Close ResultSet And Close Statement in Get Case Std"+e.getMessage());        
    }
return CaseStd;
}

  public void UpdateCase(int NumCard,String Case){
            PreparedStatement prstm=null;
            String Query="Update Resident_Gl set ID_Case_Resident =?  Where NumCard_Resident="+NumCard+"";
            cnx.Connecting();
         try {      
            cnx.getConnect().setAutoCommit(false);
           
           int ID_Case=get_Info_DB.GetId_From_DB("ID_Case_Resident","Resident_Case","Resident_Case",Case); 
           //JOptionPane.showMessageDialog(null, " iD Case N : "+ID_Case);
           prstm=cnx.getConnect().prepareStatement(Query);
           prstm.setInt(1,ID_Case);
           int x=prstm.executeUpdate();
           if (x>0) {
             // JOptionPane.showMessageDialog(null, "the Case  Resident is Update");
          }
            cnx.getConnect().commit();
         } catch (Exception e) {    //Catch Error Statements
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
}
public void PrintCardStudent(int NumCard){
    
try {
          String TestQuery="Select Name_Resident,LastName_Resident ,DateBirth,NumCard_Resident,PlaceBirth,Branch_Study.BranchStd_Name,CodeBare_Resident,imageStd "
        + "FROM  Resident_Gl,Student_Res,Branch_Study Where Resident_Gl.NumCard_Resident="+NumCard+"and Resident_Gl.ID_Rsident=Student_Res.ID_Rsident AND Branch_Study.Id_BranchStd=Student_Res.Id_BranchStd";
          JasperReport jasperreport;          
           InputStream file=getClass().getResourceAsStream("/Reports/CardResident.jrxml");
            JasperDesign jasperdesign=JRXmlLoader.load(file);
           JRDesignQuery newQuery=new JRDesignQuery();
           newQuery.setText(TestQuery);
           jasperdesign.setQuery(newQuery);
          Map parametres=new HashMap<String ,Object>();
         jasperreport=JasperCompileManager.compileReport(jasperdesign);
         Connection Cnx1;
         cnx.Connecting();
        Cnx1 =cnx.getConnect();
         JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametres, Cnx1);
        // jp=JasperFillManager.fillReport(jr, parametres, cnx.getConnect());
           JasperViewer JspViewr=new JasperViewer(jasperprint, false);
              JspViewr.viewReport(jasperprint,false);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error in jasper Report"+e.getMessage());
    }
}
public void DeleteResident(String Pateren,int NumCards){
    PreparedStatement prstm=null;
    PreparedStatement prstm1=null;
    int ID=Get_ID_Resident(NumCards);
    String Query="Delete From Resident_Gl where ID_Rsident="+ID+"";
    String Query1="Delete From Student_Res where ID_Rsident="+ID+"";
    String Query2="Delete From Student_ResExt where ID_Rsident="+ID+"";
     String Query3="Delete From Professor_Res where ID_Rsident="+ID+"";
      String Query4="Delete From Employer where ID_Rsident="+ID+"";
    cnx.Connecting();
    switch(Pateren){
        case "طالب داخلي":
         try {   
           cnx.getConnect().setAutoCommit(false);  
           prstm1=cnx.getConnect().prepareStatement(Query1);          
           int x1=prstm1.executeUpdate();
           if (x1>0) {
            //  JOptionPane.showMessageDialog(null, "the Student_Res is Delete");
          }
            try {      
                   
           prstm=cnx.getConnect().prepareStatement(Query);
           int x=prstm.executeUpdate();
           if (x>0) {
           //  JOptionPane.showMessageDialog(null, "the Resident is Delete");
          }
        } catch (Exception e) {    //Catch Error Statements
            JOptionPane.showMessageDialog(null, "Error in sql :"+e.getMessage());
        }
           cnx.getConnect().commit();
        } catch (Exception e) {
             try {
           cnx.getConnect().rollback();
          } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "RollBack In Add Student Parent Class :"+ex.getMessage());
          }
            JOptionPane.showMessageDialog(null, "Error in Sql :"+e.getMessage());
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
        
        } try {  
           prstm1.close(); 
             cnx.Deconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Resident_Gl.class.getName()).log(Level.SEVERE, null, ex);
        }
         break;
        case "طالب خـــارجــي":
            try {      
           prstm1=cnx.getConnect().prepareStatement(Query2);          
           int x1=prstm1.executeUpdate();
           if (x1>0) {
             // JOptionPane.showMessageDialog(null, "the Student_Extern is Delete");
          }
           
           try {      
                   
           prstm=cnx.getConnect().prepareStatement(Query);
           int x=prstm.executeUpdate();
           if (x>0) {
             // JOptionPane.showMessageDialog(null, "the Resident is Delete");
             
          }
 
         } catch (Exception e) {    //Catch Error Statements
            JOptionPane.showMessageDialog(null, "Error in sql :"+e.getMessage());
        }
            cnx.getConnect().commit();
         } catch (Exception e) {
             try {
              cnx.getConnect().rollback();
          } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "RollBack In Add Student Parent Class :"+ex.getMessage());
          }
            JOptionPane.showMessageDialog(null, "Error in Sql :"+e.getMessage());
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
        
        } try {
                      prstm1.close(); 
             cnx.Deconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Resident_Gl.class.getName()).log(Level.SEVERE, null, ex);
        }  
            break;
        case "اســـتـاذ":
            try {      
           prstm1=cnx.getConnect().prepareStatement(Query3);          
           int x1=prstm1.executeUpdate();
           if (x1>0) {
             // JOptionPane.showMessageDialog(null, "the Professor is Delete");
          }
           try {      
                   
           prstm=cnx.getConnect().prepareStatement(Query);
           int x=prstm.executeUpdate();
           if (x>0) {
              //JOptionPane.showMessageDialog(null, "the Resident is Delete");
          }
 
         } catch (Exception e) {    //Catch Error Statements
            JOptionPane.showMessageDialog(null, "Error in sql :"+e.getMessage());
        }
             cnx.getConnect().commit();
         } catch (Exception e) {
             try {
              cnx.getConnect().rollback();
          } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "RollBack In Add Student Parent Class :"+ex.getMessage());
          }
            JOptionPane.showMessageDialog(null, "Error in Sql :"+e.getMessage());
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
        
        } try {
           prstm1.close(); 
             cnx.Deconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Resident_Gl.class.getName()).log(Level.SEVERE, null, ex);
        }
            
          break;
        case "عـــامـــل":
            try {      
           prstm1=cnx.getConnect().prepareStatement(Query4);          
           int x1=prstm1.executeUpdate();
           if (x1>0) {
             // JOptionPane.showMessageDialog(null, "the Emplyor is Delete");
          }
           try {      
                   
           prstm=cnx.getConnect().prepareStatement(Query);
           int x=prstm.executeUpdate();
           if (x>0) {
            //  JOptionPane.showMessageDialog(null, "the Resident is Delete");
          }
 
         } catch (Exception e) {    //Catch Error Statements
            JOptionPane.showMessageDialog(null, "Error in sql :"+e.getMessage());
        }
            cnx.getConnect().commit();
         } catch (Exception e) {
             try {
              cnx.getConnect().rollback();
          } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "RollBack In Add Student Parent Class :"+ex.getMessage());
          }
            JOptionPane.showMessageDialog(null, "Error in Sql :"+e.getMessage());
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
        
        } try {
           prstm1.close(); 
             cnx.Deconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Resident_Gl.class.getName()).log(Level.SEVERE, null, ex);
        }
         break;         
    }
}
public void ListEtrangerStd(){
 try {
         JasperReport jasperreport;          
          // InputStream file=getClass().getResourceAsStream("/Reports/CardResident.jrxml");
            InputStream file=getClass().getResourceAsStream("/Reports/ListEtrangerStd.jrxml");
           //NewReportCard
            JasperDesign jasperdesign=JRXmlLoader.load(file);
           jasperreport=JasperCompileManager.compileReport(jasperdesign);
           //JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametres, Cnx1);
           this.cnx.Connecting();
           Connection cnxt=this.cnx.getConnect();
           JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,null,cnxt);
           
            JasperViewer JspViewr=new JasperViewer(jasperprint, false);
              JspViewr.viewReport(jasperprint,false);
    } catch (Exception e) {
        e.printStackTrace();
    }

}
public void UpdateAllResident(){

    PreparedStatement preparedStmt1=null;
    PreparedStatement preparedStmt2=null;
    PreparedStatement preparedStmt3=null; 

       String Query1="Update Resident_Gl set ID_Case_Resident =4  Where (Id_Ptrn_Res=1  and ID_Case_Resident =1) or(Id_Ptrn_Res=1  and ID_Case_Resident =2)"
               + " or (Id_Ptrn_Res = 3  and ID_Case_Resident =1) or (Id_Ptrn_Res= 3  and ID_Case_Resident = 2)";
       String Query2=" Update Resident_Gl set ID_Case_Resident =9  Where (Id_Ptrn_Res= 2  and ID_Case_Resident = 8) ";
       String Query3="Update Resident_Gl set ID_Case_Resident =11  Where  (Id_Ptrn_Res= 4  and ID_Case_Resident = 10)";
   
       cnx.Connecting();
       try {
            preparedStmt1=cnx.getConnect().prepareStatement(Query1);
            preparedStmt2=cnx.getConnect().prepareStatement(Query2);
            preparedStmt3=cnx.getConnect().prepareStatement(Query3);
         int x1=preparedStmt1.executeUpdate();
         
            if (x1>0) {
               // JOptionPane.showMessageDialog(null, " Student AND Prof is Updated  ");
                
            }
            int x2=preparedStmt2.executeUpdate();
            if (x2>0) {
                //JOptionPane.showMessageDialog(null, " Student Externl is Updated  ");
                
            }
            int x3=preparedStmt3.executeUpdate();
            if (x3>0) {
                //JOptionPane.showMessageDialog(null, " Employer is Updated  ");
                
            }

            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Update Case All Resident "+e.getMessage());
        }
        
        try {
            
            preparedStmt1.close();
            preparedStmt2.close();
            preparedStmt3.close();
            cnx.Deconnect();
          
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in close Connection");
        }
}
/************************************************************************/          
    public static void main(String[] args) {
    Resident_Gl r=new  Resident_Gl("ttttt", "tttttt", 777, "777777", new Date(), "ttttt",2,1,1);
    JOptionPane.showMessageDialog(null, "The Value is :"+r.GetCaseResidence(1));
    //r.PrintCardEmpl_Prof(2754,"عــامل");
    }
    
     public  int Get_ID_Resident(int NumCard_Resident){
      String Query="Select ID_Rsident FROM  Resident_Gl Where NumCard_Resident="+NumCard_Resident+"";  
        int ID=0;
        cnx.Connecting();
        try {
            stm=getCnx().getConnect().createStatement();
             res=stm.executeQuery(Query);
             
             if (res.next()){
                ID =res.getInt(1);
               // JOptionPane.showMessageDialog(null, "ID_Resident : "+res.getInt(1));
            }else
                 JOptionPane.showMessageDialog(null, "Error in Get ID_Resident ");
              
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error IN SQL : "+ex.getMessage());
         }
      
        try {
            stm.close();
            res.close();
            getCnx().Deconnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error IN Close () :"+ex);
        }
      return  ID;
    }
    
/**********************************************************************************/
    public int Get_MAX_Numbre_CardRes(){   
        String Query="Select MAX(NumCard_Resident) FROM  Resident_Gl";
        int Max=0;
      cnx.Connecting();
        try {
            stm=getCnx().getConnect().createStatement();
            res=stm.executeQuery(Query);
            
            if(res.next()){
               Max=res.getInt(1);
               //JOptionPane.showMessageDialog(null, "MAX_NumCards : "+res.getInt(1));
   
            }
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Error sql :"+ex);
        }
         try {
             res.close();
             stm.close();
             getCnx().Deconnect();
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error in Sql :"+e.getMessage());
         }
         
    return Max;
    }
    /*****************************************************************************/
    
     public int Get_MAX_Numbre_CardRes(int x){ //in this function Enable Connecting And Deconnecting because use this Function in AddResident By Transaction
       
        String Query="Select MAX(NumCard_Resident) FROM  Resident_Gl";
        int Max=0;
        //cnx.Connecting();
        try {
            stm=getCnx().getConnect().createStatement();
            res=stm.executeQuery(Query);
            
            if(res.next()){
               Max=res.getInt(1);
              // JOptionPane.showMessageDialog(null, "MAX_NumCards : "+res.getInt(1));
              
            }
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Error in Get_MAX_Numbre_CardRes  :"+ex);
        }
         try {
             res.close();
             stm.close();
             //getCnx().Deconnect();
         } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Error in Sql :"+e.getMessage());
         }
         
    return Max;
    }
    
    
    /*************************************************************************************/
    public int GetMAX_ID_Resident(){
        String Query="Select MAX(ID_Rsident) from  Resident_Gl";
        int Max=0;
        cnx.Connecting();
        try {
            stm=getCnx().getConnect().createStatement();
            res=stm.executeQuery(Query);
            
            if(res.next()){
               Max=res.getInt(1);
               //JOptionPane.showMessageDialog(null, "MAX_ID_Resident : "+res.getInt(1));
   
            }
        } catch (SQLException ex) {
            Logger.getLogger(Resident_Gl.class.getName()).log(Level.SEVERE, null, ex);
        }
         try {
             res.close();
             stm.close();
             getCnx().Deconnect();
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error in Sql :"+e.getMessage());
         }
         

         

    return Max;
      }
    /********************************************************/
     public void NotFilterResident(String Query,JTable tab,DefaultTableModel dm){  //filtrer dans le tableau fournisseur
        TableRowSorter<DefaultTableModel> tr=new TableRowSorter<DefaultTableModel>(dm);
        tab.setRowSorter(tr);
        tr.setRowFilter(RowFilter.notFilter(RowFilter.regexFilter(Query)));   
    }
    /*******************************************************************************************/
    public  String CreatBarcod(int Resid_Cod,int Num_Card){
        
        String Barcode=Num_Card+""+Resid_Cod;
        return Barcode;
        }

    /**
     * @return the Id_Resident_Gl
     */
    public int getId_Resident_Gl() {
        return Id_Resident_Gl;
    }

    /**
     * @param Id_Resident_Gl the Id_Resident_Gl to set
     */
    public void setId_Resident_Gl(int Id_Resident_Gl) {
        this.Id_Resident_Gl = Id_Resident_Gl;
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
     * @return the Barcode
     */
    public String getBarcode() {
        return Barcode;
    }

    /**
     * @param Barcode the Barcode to set
     */
    public void setBarcode(String Barcode) {
        this.Barcode = Barcode;
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
     * @return the stm
     */
    public Statement getStm() {
        return stm;
    }

    /**
     * @param stm the stm to set
     */
    public void setStm(Statement stm) {
        this.stm = stm;
    }

    /**
     * @return the res
     */
    public ResultSet getRes() {
        return res;
    }

    /**
     * @param res the res to set
     */
    public void setRes(ResultSet res) {
        this.res = res;
    }

    /**
     * @return the cnx
     */
    public ConnectionDB getCnx() {
        return cnx;
    }

    /**
     * @param cnx the cnx to set
     */
    public void setCnx(ConnectionDB cnx) {
        this.cnx = cnx;
    }

    /**
     * @return the DateBirth
     */
    public Date getDateBirth() {
        return DateBirth;
    }

    /**
     * @param DateBirth the DateBirth to set
     */
    public void setDateBirth(Date DateBirth) {
        this.DateBirth = DateBirth;
    }

    /**
     * @return the placeBirth
     */
    public String getPlaceBirth() {
        return placeBirth;
    }

    /**
     * @param placeBirth the placeBirth to set
     */
    public void setPlaceBirth(String placeBirth) {
        this.placeBirth = placeBirth;
    }

    /**
     * @return the ID_gender
     */
    public int getID_gender() {
        return ID_gender;
    }

    /**
     * @param ID_gender the ID_gender to set
     */
    public void setID_gender(int ID_gender) {
        this.ID_gender = ID_gender;
    }

    /**
     * @return the Id_Ptrn_Res
     */
    public int getId_Ptrn_Res() {
        return Id_Ptrn_Res;
    }

    /**
     * @param Id_Ptrn_Res the Id_Ptrn_Res to set
     */
    public void setId_Ptrn_Res(int Id_Ptrn_Res) {
        this.Id_Ptrn_Res = Id_Ptrn_Res;
    }


    public File getImageRes() {
        return ImageRes;
    }


    public void setImageRes(File ImageRes) {
        this.ImageRes = ImageRes;
    }

    /**
     * @return the CaseResident
     */
    public int getCaseResident() {
        return CaseResident;
    }

    /**
     * @param CaseResident the CaseResident to set
     */
    public void setCaseResident(int CaseResident) {
        this.CaseResident = CaseResident;
    }

    /**
     * @return the ValConfiramation
     */
    public int getValConfiramation() {
        return ValConfiramation;
    }

    /**
     * @param ValConfiramation the ValConfiramation to set
     */
    public void setValConfiramation(int ValConfiramation) {
        this.ValConfiramation = ValConfiramation;
    }
    
    public void setHome1(Home1 h){
    home1=h;
    }
}
