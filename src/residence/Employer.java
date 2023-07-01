
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package residence;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.sf.jasperreports.engine.JRException;
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
public class Employer extends Resident_Gl{

    private int ID_Profession;
    private int Id_Room;
    private int ID_Rsident;
    
    Statement stm;
    ResultSet res;
    public Employer( String first_name, String last_name, int Numbre_CardResDate, String Barcode,
            Date DateBirth, String placeBirth, int ID_gender, int Id_Ptrn_Res,int CaseRes ,
             int ID_Profession, int Id_Room, int ID_Rsident) {
        super(first_name, last_name, Numbre_CardResDate, Barcode, DateBirth, placeBirth, ID_gender, Id_Ptrn_Res,CaseRes);
        this.ID_Profession=ID_Profession;
        this.Id_Room=Id_Room;
        this.ID_Rsident=ID_Rsident;
    }
      public Employer(){}
    @Override
    public void GetInformationResident(int NumCard_Resident, JTextField Numcard,
            JTextField txtNam_std5, JTextField txtSurNam_std5, JTextField DatBirth_std3,
            JTextField txtPlcBirth_std5, JCheckBox CheckMale2, JCheckBox checkFemal2) {
        super.GetInformationResident(NumCard_Resident, Numcard, txtNam_std5, txtSurNam_std5, DatBirth_std3, txtPlcBirth_std5, CheckMale2, checkFemal2); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    
   

    @Override
    public void AddRsident() {
        PreparedStatement prstm=null;
         int ValMax=0 ;
        
        String Query="INSERT INTO  Employer (ID_Profession,ID_Rsident) VALUES(?,?)";
         String QuerySlct="SELECT MAX(ID_Rsident) FROM Resident_Gl";
         
        cnx.Connecting();
        
        try {
             cnx.getConnect().setAutoCommit(false);
             
            super.AddRsident(); 
       /*************************************************/
       
            stm=cnx.getConnect().createStatement();
               res=stm.executeQuery(QuerySlct);        
               
               if (res.next()) {
                  ValMax = res.getInt(1); 
                   System.out.println("residence.Employer.AddRsident() Max ID"+ValMax);
               }
            
            prstm=cnx.getConnect().prepareStatement(Query);
                  
            prstm.setInt(1, ID_Profession);
            prstm.setInt(2, ValMax);
            int x=prstm.executeUpdate();
            if (x>0) {
                //JOptionPane.showMessageDialog(null, "The Employer Is Added");
            } 
           cnx.getConnect().commit();
            setValConfiramation(1);
            
        } catch (SQLException e) {
            setValConfiramation(0);
             JOptionPane.showMessageDialog(null, "Error in sql Add Resident Class Employer"+e.getMessage());
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
        
        try {
            cnx.Deconnect();
        } catch (Exception e) {
        }
        
    }

    @Override
    public void UpdateResident(int ID_Resident, int NumCard) {
        
            PreparedStatement prstm=null; 
            String QueryUpdPrf="UPDATE Employer  SET ID_Profession=?"+"  WHERE ID_Rsident="+ID_Resident;
     cnx.Connecting();
            try {
             cnx.getConnect().setAutoCommit(false);              
        super.UpdateResident( ID_Resident, NumCard);
        prstm=cnx.getConnect().prepareStatement(QueryUpdPrf);
        prstm.setInt(1, this.ID_Profession);
        int x=prstm.executeUpdate();
                  if (x>0) {
        }else  JOptionPane.showMessageDialog(null, " Error Update The Resident ");
            cnx.getConnect().commit();    
             setValConfiramation(1);
            }catch(Exception e){
                 setValConfiramation(0);
                          JOptionPane.showMessageDialog(null, "Error in SQL Update Employer Class Employer "+e.getMessage());
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
    
       // super.UpdateResident(ID_Resident, NumCard); //To change body of generated methods, choose Tools | Templates.
    }
 public void ListEmployer(){
 try {
         JasperReport jasperreport;          
          // InputStream file=getClass().getResourceAsStream("/Reports/CardResident.jrxml");
            InputStream file=getClass().getResourceAsStream("/Reports/ListEmployer.jrxml");
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
    
 
public void PrintCardEmpl_Prof(int NumCrd,String Ptr){
/*String Query="Select Name_Resident,LastName_Resident ,DateBirth,NumCard_Resident,PlaceBirth,CodeBare_Resident,imageStd\n" +
"FROM  Resident_Gl Where  NumCard_Resident="+NumCrd+";";*/
String s22="Select Name_Resident,LastName_Resident ,DateBirth,NumCard_Resident,PlaceBirth,CodeBare_Resident,imageStd,Profession\n" +
"FROM  Resident_Gl,Profession,Employer Where  NumCard_Resident="+NumCrd+""+
"AND Employer.ID_Rsident=Resident_Gl.ID_Rsident\n" +
"AND Employer.ID_Profession=Profession.ID_Profession;";

        try {
             JasperReport jasperreport;          
          // InputStream file=getClass().getResourceAsStream("/Reports/CardResident.jrxml");
            InputStream file=getClass().getResourceAsStream("/Reports/CardExternal.jrxml");
           //NewReportCard
            JasperDesign jasperdesign=JRXmlLoader.load(file);
           JRDesignQuery newQuery=new JRDesignQuery();
           newQuery.setText(s22);
           jasperdesign.setQuery(newQuery);
           jasperreport=JasperCompileManager.compileReport(jasperdesign);
            Map parametres=new HashMap<String ,Object>();
            parametres.put("Patern", Ptr);
           //JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametres, Cnx1);
           this.cnx.Connecting();
           Connection cnxt=this.cnx.getConnect();
           JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametres,cnxt);
           
            JasperViewer JspViewr=new JasperViewer(jasperprint, false);
              JspViewr.viewReport(jasperprint,false);
    } catch (JRException e) {
        e.printStackTrace();
    }

}
public void PrintCardEmpl_ProfOnly(int NumCrd,String Ptr){
String s2="Select Name_Resident,LastName_Resident,DateBirth,NumCard_Resident,PlaceBirth,CodeBare_Resident,imageStd,Profession\n" +
"FROM  Resident_Gl,Profession,Professor_Res Where  NumCard_Resident="+NumCrd+"\n" +
"AND Professor_Res.ID_Rsident=Resident_Gl.ID_Rsident\n" +
"AND Professor_Res.ID_Profession=Profession.ID_Profession;";
       try {
             JasperReport jasperreport;          
          // InputStream file=getClass().getResourceAsStream("/Reports/CardResident.jrxml");
            InputStream file=getClass().getResourceAsStream("/Reports/CardExternalProf.jrxml");
           //NewReportCard
            JasperDesign jasperdesign=JRXmlLoader.load(file);
           JRDesignQuery newQuery=new JRDesignQuery();
           newQuery.setText(s2);
           jasperdesign.setQuery(newQuery);
           jasperreport=JasperCompileManager.compileReport(jasperdesign);
            Map parametres=new HashMap<String ,Object>();
            parametres.put("Patern", Ptr);
           //JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametres, Cnx1);
           this.cnx.Connecting();
           Connection cnxt=this.cnx.getConnect();
           JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametres,cnxt);
           
            JasperViewer JspViewr=new JasperViewer(jasperprint, false);
              JspViewr.viewReport(jasperprint,false);
    } catch (Exception e) {
        e.printStackTrace();
    }

}

public void PrintEnsemble(){

   //ReportEnsPrf_Emp 
    
String Query="Select Name_Resident,LastName_Resident ,DateBirth,NumCard_Resident,PlaceBirth,Branch_Study.BranchStd_Name,CodeBare_Resident,imageStd,Room_Code FROM  Resident_Gl,Student_Res,Branch_Study,Room\n" +
"WHERE\n" +
"Resident_Gl.ID_Rsident=Student_Res.ID_Rsident \n" +
"AND Branch_Study.Id_BranchStd=Student_Res.Id_BranchStd\n" +
"AND Student_Res.Id_Room=Room.Id_Room\n" +
"AND Id_Ptrn_Res=1 AND (ID_Case_Resident!=3 and ID_Case_Resident!=5 and ID_Case_Resident!=6 and ID_Case_Resident!=7 )";
        try {
             JasperReport jasperreport;          
          // InputStream file=getClass().getResourceAsStream("/Reports/CardResident.jrxml");
            InputStream file=getClass().getResourceAsStream("/Reports/newReport.jrxml");
           //NewReportCard
            JasperDesign jasperdesign=JRXmlLoader.load(file);
          JRDesignQuery newQuery=new JRDesignQuery();
           newQuery.setText(Query);
           jasperdesign.setQuery(newQuery);
           jasperreport=JasperCompileManager.compileReport(jasperdesign);
            Map parametres=new HashMap<String ,Object>();
            parametres.put("TypRes", "بطاقـة الطالب");
           //JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametres, Cnx1);
           this.cnx.Connecting();
           Connection cnxt=this.cnx.getConnect();
           JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametres,cnxt);
           
            JasperViewer JspViewr=new JasperViewer(jasperprint, false);
              JspViewr.viewReport(jasperprint,false);
    } catch (Exception e) {
        e.printStackTrace();
    }

}

public int ConrolSais_Prof(String NamPrf){
String Query="SELECT *FROM Profession WHERE Profession=N'"+NamPrf+"'";
cnx.Connecting();
int Val=0;
    try {
        stm=cnx.getConnect().createStatement();
        res=stm.executeQuery(Query);
        if (res.next()) {
            Val=1;
        }
    } catch (SQLException e) {
    e.printStackTrace();
    }
    try {
        stm.close();
        res.close();
        cnx.Deconnect();
    } catch (SQLException e) {
    e.printStackTrace();
    }
    return Val;
}

public int InsertProffession(String NameProffession){
String Query="INSERT INTO Profession (Profession)VALUES ( N'"+NameProffession+"')";
int y=0;
PreparedStatement prst=null;
ConnectionDB connect=new ConnectionDB();
connect.Connecting();
    try {
         prst=connect.getConnect().prepareStatement(Query);
       int x= prst.executeUpdate();
        if (x>0) {
            y=1;
           JOptionPane.showMessageDialog(null, "تمت الاضافة بنجاح");
        }else {
        JOptionPane.showMessageDialog(null, "لم تتم الاضافة بنجاح  ");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    try {
        prst.close();
        connect.Deconnect();
    } catch (Exception e) {
    }
return y;
}



//ListEmployer
    public static void main(String[] args) {
         Employer emp=new Employer("hhhhhh", "hhhhhh", 11111, "0251"+1111, new Date(), "biskra", 1, 2, 3,7 , 0, 2);
          //Employer emp=new Employer("faridEmp", "faridEmp", 11111, "1452", new Date(), "biskra", 1, 2, 3,7 , 0, 2);
         
        // emp.PrintEnsemble();
         emp.PrintCardEmpl_ProfOnly(11321, "بطاقة مقيــم");
    }
    
}
