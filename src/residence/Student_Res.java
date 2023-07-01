/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package residence;

import java.io.File;
import java.io.FileFilter;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.PrinterName;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author admi;
 */
public class Student_Res extends Resident_Gl{

    
   
    private int ID_Rsident;
    private String Name_Father;
    private String FullName_Mother;
    private String ProfessionFather;
    private String ProfessionMother;
    private String Address;
    private int ID_Wilaya;
    private String Name_Commune;
    private String Name_Daira;
    private int Id_Nationalite;
    private String SituationFamily;
    private int BacYear;
    private double BacMoyen;
    private String PlaceGetBac;
    private int Id_BranchStd;
    private Date DateInscrp;
    private int Id_Faculty;
    private  int Id_LevelStudy;
    private  int Id_Room;
    private String Num_InscritBac;
    
    private String Name_ResidentFr;
    private String LastName_ResidentFr;
    private String PlaceBirthFr;
    private String Name_FatherFr;
    private String Name_MotherFr;
    private String LastName_MotheFr;
    private String LastNamMothAR;
    private Statement stm;
    private ResultSet res;
    
    private PreparedStatement prstm=null;
     //private ConnectionDB cnx=new ConnectionDB();
    MessageErrorControl messagerror;
  Get_Info_DB get_Info_DB=new Get_Info_DB();
     public Student_Res( String first_name,String last_name,int Numbre_CardResDate,String Barcode,
              Date DateBirth,String placeBirth,int ID_gender,int Id_Ptrn_Res,int CaseResident,
             String FatherName,String MotherName,String ProfessionFather,String ProfessionMother,
            String Address,int ID_Wilaya,String Name_Commune,String Name_Daira,int  Id_Nationalite,
            String SituationFamily,int BacYear,double BacMoyen,String PlaceGetBac,
            int Id_BranchStd,Date DateInscrp,int Id_Faculty,int Id_LevelStudy,int Id_Room,String Num_InscritBac,int ID_Rsident,
            String Name_ResidentFr,String LastName_ResidentFr,String PlaceBirthFr,String Name_FatherFr,String Name_MotherFr,String LastName_MotheFr
            ,String LastNamMothAR) {
       super(first_name, last_name, Numbre_CardResDate, Barcode, DateBirth, placeBirth, ID_gender, Id_Ptrn_Res,CaseResident);

        this.Name_Father=FatherName;
        this.FullName_Mother=MotherName;
        this.ProfessionFather=ProfessionFather;
        this.ProfessionMother=ProfessionMother;
        this.Address=Address;
        this.ID_Wilaya=ID_Wilaya;
        this.Name_Commune=Name_Commune;
        this.Name_Daira=Name_Daira;
        this.Id_Nationalite=Id_Nationalite;
        this.SituationFamily=SituationFamily;
        this.BacYear=BacYear;
        this.BacMoyen=BacMoyen;
        this.PlaceGetBac=PlaceGetBac;
        this.Id_BranchStd=Id_BranchStd;
        this.DateInscrp=DateInscrp;
        this.Id_Faculty=Id_Faculty;
        this.Id_LevelStudy=Id_LevelStudy;
        this.Id_Room=Id_Room;
        this.Num_InscritBac=Num_InscritBac;
        this.ID_Rsident=ID_Rsident;
        this.Name_ResidentFr=Name_ResidentFr;
        this.LastName_ResidentFr=LastName_ResidentFr;
        this.PlaceBirthFr=PlaceBirthFr;
        this.Name_FatherFr=Name_FatherFr;
        this.Name_MotherFr=Name_MotherFr;
        this.LastName_MotheFr=LastName_MotheFr;
        this.LastNamMothAR=LastNamMothAR;
    }

    public Student_Res(){
    
    }
  
  
    @Override
    public void AddRsident() {
        
             int IdRoom=new Room().Get_ID_Room("/");
         PreparedStatement prstm=null;
             String Query="INSERT INTO Student_Res (Name_Father,FullName_Mother,ProfessionFather,"
                   + "ProfessionMother,Address,ID_Wilaya,Name_Commune,Name_Daira"
                    + ",Id_Nationalite,SituationFamily,BacYear,BacMoyen,PlaceGetBac,Id_BranchStd,DateInscrp,"
                    + "Id_Faculty,Id_LevelStudy,Num_InscritBac,ID_Rsident,Id_Room,"
                     + "Name_ResidentFr,LastName_ResidentFr,PlaceBirthFr,Name_FatherFr,Name_MotherFr,LastName_MotheFr,LastNamMothAR) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                     + "?,?,?,?,?,?,"
                     + "?)";
         String QuerySlct="SELECT MAX(ID_Rsident) FROM Resident_Gl";
         int ValMax=0 ;
        
         //To change body of generated methods, choose Tools | Templates.
        cnx.Connecting();

         try {
              cnx.getConnect().setAutoCommit(false);
             
             super.AddRsident();
           
               stm=cnx.getConnect().createStatement();
               res=stm.executeQuery(QuerySlct);        
               
               if (res.next()) {
                  ValMax = res.getInt(1); 
                   System.out.println("residence.Employer.AddRsident() Max ID"+ValMax);
               }
               
             prstm=cnx.getConnect().prepareStatement(Query);
            prstm.setString(1, ControleSaisirDataCorrect(getName_Father()));
            // System.out.println("Name ");
            prstm.setString(2, ControleSaisirDataCorrect(getFullName_Mother()));
            prstm.setString(3,ControleSaisirDataCorrect(getProfessionFather()));
            prstm.setString(4, ControleSaisirDataCorrect(getProfessionMother()));
            prstm.setString(5,ControleSaisirDataCorrect( getAddress()));
            prstm.setInt(6, getID_Wilaya());
            prstm.setString(7  , ControleSaisirDataCorrect(getName_Commune()));
            prstm.setString(8, ControleSaisirDataCorrect(getName_Daira()));
            prstm.setInt(9, getId_Nationalite());
            prstm.setString(10, getSituationFamily());
            prstm.setInt(11,getBacYear());
            prstm.setDouble(12, getBacMoyen());
            prstm.setString(13, ControleSaisirDataCorrect(this.PlaceGetBac));
            prstm.setInt(14, getId_BranchStd());
            prstm.setDate(15, new java.sql.Date(getDateInscrp().getTime()));
            prstm.setInt(16,getId_Faculty());
            prstm.setInt(17,getId_LevelStudy());
            // prstm.setInt(17, getId_Room());
            prstm.setString(18, getNum_InscritBac());
            prstm.setInt(19, ValMax);
            prstm.setInt(20, IdRoom);
            prstm.setString(21, ControleSaisirDataCorrect(this.Name_ResidentFr));
            prstm.setString(22, ControleSaisirDataCorrect(this.LastName_ResidentFr));
            prstm.setString(23,ControleSaisirDataCorrect( this.PlaceBirthFr));
            prstm.setString(24, ControleSaisirDataCorrect(this.Name_FatherFr));
            prstm.setString(25, ControleSaisirDataCorrect(this.Name_MotherFr));
            prstm.setString(26, ControleSaisirDataCorrect(this.LastName_MotheFr));
            prstm.setString(27, ControleSaisirDataCorrect(this.LastNamMothAR));
            int x=prstm.executeUpdate();
            if (x>0) {
                //JOptionPane.showMessageDialog(null, "The std_Resident is added ");
            }else 
                JOptionPane.showMessageDialog(null, "Error in added ");
 
            
             cnx.getConnect().commit();
            
             setValConfiramation(1); 
        } catch (Exception e) {    //Catch Error Statements
             setValConfiramation(0);
              JOptionPane.showMessageDialog(null, "Error in Add Student In DataBase Class Student"+e.getMessage());
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
     public void DisplayTabToTakeRoom(JTable tabRes){
  String Query="select Student_Res.ID_Rsident,NumCard_Resident,Name_Resident,LastName_Resident,DateBirth,PlaceBirth, "
          + "Student_Res.ID_Rsident,ID_Case_Resident,Room_Code\n" +
        " from Resident_Gl,Student_Res,Room\n" +
        "Where Student_Res.ID_Rsident=Resident_Gl.ID_Rsident\n" +
        " and (Student_Res.Id_Room=Room.Id_Room )";
      DefaultTableModel dftabMd=(DefaultTableModel)tabRes.getModel();   
         dftabMd.setRowCount(0);
     get_Info_DB.filling_ArrayList("Resident_Case", ListInfo);
    cnx.Connecting();
     try {
             stm=getCnx().getConnect().createStatement();
             res=stm.executeQuery(Query);
             String Branch_Profession="";
                     while (res.next()) {
                  Object arg[]={ListInfo.get(res.getInt("ID_Case_Resident")-1),res.getString("Room_Code"),
                  res.getString("PlaceBirth"),res.getDate("DateBirth"),res.getString("Name_Resident"),
                      res.getString("LastName_Resident"),res.getInt("NumCard_Resident"),res.getInt("ID_Rsident")};          
                  dftabMd.addRow(arg);
             }
                       
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error in sql :"+e.getMessage());
         }

}

   
    public String ControleSaisirDataCorrect(String InputData){
        if (InputData.contains("..")) {
            return "";
        }else
    return InputData;
    }
    /************************************************************************************/
    public void DisplayAccommdation_Rapporteur(String FrsName,String LastName,int NumCard,String DateBirh,String PlaceBirth,String BranchStdy,String NameResidence,String PavilionAndRoom ){
      //  String path="src\\Reports\\Accommdation_Rapporteur.jrxml";    
        JasperReport jasperreport;
        try {
            InputStream file=getClass().getResourceAsStream("/Reports/Accommdation_Rapporteur.jrxml");   
            JasperDesign jasperdesign=JRXmlLoader.load(file);
            
         jasperreport=JasperCompileManager.compileReport(jasperdesign);
         Map parametre=new HashMap<String, Object>();
         parametre.put("NameStsRes", FrsName);
         parametre.put("SurNameStdRes", LastName);
         parametre.put("NumCard", ""+NumCard);
         parametre.put("DateBirth", DateBirh);
         parametre.put("PlaceBirth", PlaceBirth);
         parametre.put("BranchStudy", BranchStdy);
         parametre.put("NameResidence", NameResidence);
         parametre.put("PavilionAndRoom", PavilionAndRoom); 
         Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
         
         parametre.put("Annet", new SimpleDateFormat("YYYY").format(new Date())); 
          year=year+1;
         parametre.put("AnneNxt", year+""); 
         /*****************************************/
         String QuerResid="Select * from Manager where kind_Manager="+2;
                    cnx.Connecting();
                    try {
                        stm=cnx.getConnect().createStatement();
                        res=stm.executeQuery(QuerResid);
                        if (res.next()) {
                         SimpleDateFormat format=new  SimpleDateFormat("dd/MM/yyyy");
                            String s=format.format(res.getDate("Date_Decision"));
                           parametre.put("FullNamDrcDirc", res.getString("FirstName")+" "+res.getString("LastName")); 
                            parametre.put("DateDcsDirc",s);
                            parametre.put("NumDecDirct",res.getInt("Num_Decision")+"");
                         }
                        stm.close();
                        res.close();
                        String QuerDirection="Select * from Manager where kind_Manager="+1;        
                        stm=cnx.getConnect().createStatement();
                        res=stm.executeQuery(QuerDirection);
                         if (res.next()) {
                           SimpleDateFormat format=new  SimpleDateFormat("dd/MM/yyyy");
                            String s=format.format(res.getDate("Date_Decision"));
                            parametre.put("FullNamDrcRes", res.getString("FirstName")+" "+res.getString("LastName")); 
                            parametre.put("DateDecResi",s);
                            parametre.put("NumDecisionRes",res.getInt("Num_Decision")+"");
                           }
                        } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "ERROR Get Information Manager"+e.getMessage());
                    }
                    try {
                        stm.close();
                        res.close();
                        cnx.Deconnect();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }/****************************************/
         JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametre, new JREmptyDataSource());
           JasperViewer jasperviewer=new JasperViewer(jasperprint, false);
           
          // jasperviewer.show();
         jasperviewer.viewReport(jasperprint,false);
             
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "ERROR IN JASPERREPORT"+e.getMessage());
        }
    
    }
    
    
    public void Acquittal_Report(int NumCard){ //Acquittal_ReportFor One 
      //  String path="src\\Reports\\Accommdation_Rapporteur.jrxml"; 
      
       
    String QueryCase="SELECT Resident_Case.Resident_Case FROM Resident_Gl,Resident_Case WHERE  Resident_Gl.NumCard_Resident="+NumCard+" AND Resident_Gl.ID_Case_Resident=Resident_Case.ID_Case_Resident ";
    String CaseNew="";
    cnx.Connecting();
    
    try {
         stm=cnx.getConnect().createStatement();
         res=stm.executeQuery(QueryCase);
         
         if (res.next()) {
             
            CaseNew=res.getString("Resident_Case");
         //  JOptionPane.showMessageDialog(null  , "The Case of Std  is "+CaseNew+"num card "+NumCard);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error In GetException "+e.getMessage());
    }
    try {
        res.close();
        stm.close();
        cnx.Deconnect();
    } catch (Exception e) {
    }
    if (CaseNew.equals("ســــاكن")) { 
     String FrsName="";String LastName="";String numInscrpUinv="";String DateBirh="";
            String PlaceBirth="";String BranchStdy="";String Room="";      
 String SS="SELECT Name_Resident,\n" +
"NumCard_Resident,\n" +
"LastName_Resident,\n" +
"DateBirth,\n" +
"PlaceBirth,\n" +
"BranchStd_Name,\n" +
"Room_Code,\n" +
"Num_InscritBac\n" +
"FROM Resident_Gl,Student_Res,Room,Branch_Study,Resident_Case \n" +
"WHERE\n" +
"Resident_Gl.ID_Rsident=Student_Res.ID_Rsident\n" +
"AND Student_Res.Id_Room=Room.Id_Room \n" +
"AND Branch_Study.Id_BranchStd=Student_Res.Id_BranchStd \n" +
"AND Resident_Gl.ID_Case_Resident =Resident_Case.ID_Case_Resident\n" +
"AND Resident_Gl.NumCard_Resident=+"+NumCard+"\n" +
";";
     
      cnx.Connecting();
        try {
            stm=cnx.getConnect().createStatement();
            res=stm.executeQuery(SS);
            
            while (res.next()) {                
               // JOptionPane.showMessageDialog(null, "I am in Res.next");
                FrsName=res.getString("Name_Resident");
                System.out.println("residence.Student_Res.Acquittal_Report()"+FrsName);
                LastName=res.getString("LastName_Resident");
                 System.out.println("residence.Student_Res.Acquittal_Report()"+LastName);
                NumCard=res.getInt("NumCard_Resident");
                System.out.println("residence.Student_Res.Acquittal_Report()"+NumCard);
                DateBirh=new SimpleDateFormat("dd/MM/YYYY").format(res.getDate("DateBirth"));
                System.out.println("residence.Student_Res.Acquittal_Report()"+DateBirh);
                PlaceBirth=res.getString("PlaceBirth");
                System.out.println("residence.Student_Res.Acquittal_Report()"+PlaceBirth);
                BranchStdy=res.getString("BranchStd_Name");
                System.out.println("residence.Student_Res.Acquittal_Report()"+BranchStdy);
                numInscrpUinv=res.getString("Num_InscritBac");
                System.out.println("residence.Student_Res.Acquittal_Report()"+numInscrpUinv);
                Room=res.getString("Room_Code");
                System.out.println("residence.Student_Res.Acquittal_Report()"+Room);
            }
            
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in sql Server"+e.getMessage());
        }
        
        try {
            res.close();
            stm.close();
            cnx.Deconnect();
        } catch (Exception e) {
        }
      JasperReport jasperreport;
        try {
            InputStream file=getClass().getResourceAsStream("/Reports/acquittal.jrxml");   
            JasperDesign jasperdesign=JRXmlLoader.load(file);
         jasperreport=JasperCompileManager.compileReport(jasperdesign);
         Map parametre=new HashMap<String, Object>();
         parametre.put("PrNameStd", FrsName);
         parametre.put("PrLasName", LastName);
         parametre.put("NumInscBac", numInscrpUinv);
         parametre.put("PrNumCard", ""+NumCard);
         parametre.put("DatBirth", DateBirh);
         parametre.put("PrPlcBirth", PlaceBirth);
         parametre.put("PrBranch", BranchStdy);
         parametre.put("Prroom", Room);
         parametre.put("PrPavilion", Room.substring(0, 1)); 
         JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametre, new JREmptyDataSource());
           JasperViewer jasperviewer=new JasperViewer(jasperprint, false);
         jasperviewer.viewReport(jasperprint,false);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "ERROR IN JASPERREPORT"+e.getMessage());
        }
    }else {
           messagerror=new MessageErrorControl(home1, true, "لا يمكن طبــاعة التبرئة لعدم تجديد الملف ");
        messagerror.setVisible(true);
      }
    }
    
    public void Decision_Housing(int NumCard,String DateBefInsc){

     String FrsName="";String LastName="";String numInscrpUinv="";String DateBirh="";
            String PlaceBirth="";String BranchStdy="";String Room=""; 
            
 String SS="SELECT Name_Resident,\n" +
"LastName_Resident,\n" +
"DateBirth,\n" +
"PlaceBirth,\n" +
"Room_Code\n" +

"FROM Resident_Gl,Student_Res,Room \n" +
"WHERE\n" +
"Resident_Gl.ID_Rsident=Student_Res.ID_Rsident\n" +
"AND Student_Res.Id_Room=Room.Id_Room \n" +
"AND Resident_Gl.NumCard_Resident=+"+NumCard+"\n" +
"";
     
      cnx.Connecting();
        try {
            stm=cnx.getConnect().createStatement();
            res=stm.executeQuery(SS);
            
            while (res.next()) {                
               // JOptionPane.showMessageDialog(null, "I am in Res.next");
                FrsName=res.getString("Name_Resident");
                System.out.println("residence.Student_Res.Acquittal_Report()"+FrsName);
              
                LastName=res.getString("LastName_Resident");
                 System.out.println("residence.Student_Res.Acquittal_Report()"+LastName);
              //  NumCard=res.getInt("NumCard_Resident");
                System.out.println("residence.Student_Res.Acquittal_Report()"+NumCard);
                DateBirh=new SimpleDateFormat("dd/MM/YYYY").format(res.getDate("DateBirth"));
                System.out.println("residence.Student_Res.Acquittal_Report()"+DateBirh);
                
                PlaceBirth=res.getString("PlaceBirth");
                System.out.println("residence.Student_Res.Acquittal_Report()"+PlaceBirth);
                //BranchStdy=res.getString("BranchStd_Name");
                System.out.println("residence.Student_Res.Acquittal_Report()"+BranchStdy);
                //numInscrpUinv=res.getString("Num_InscritBac");
                System.out.println("residence.Student_Res.Acquittal_Report()"+numInscrpUinv);
                
                Room=res.getString("Room_Code");
                System.out.println("residence.Student_Res.Acquittal_Report()"+Room);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in sql Server"+e.getMessage());
        }
        
        try {
            res.close();
            stm.close();
            cnx.Deconnect();
        } catch (Exception e) {
        }
      JasperReport jasperreport;
        try {
            InputStream file=getClass().getResourceAsStream("/Reports/Decision_Housing.jrxml");   
            JasperDesign jasperdesign=JRXmlLoader.load(file);
         jasperreport=JasperCompileManager.compileReport(jasperdesign);
         Map parametre=new HashMap<String, Object>();
         parametre.put("PrNameStd", FrsName);
         parametre.put("PrLastNameStd", LastName);
         parametre.put("PrDateBirth", DateBirh);
         parametre.put("PrPlaceBirth", PlaceBirth);
      
         parametre.put("PrRoom", Room);
         parametre.put("PrPavilion", Room.substring(0, 1)); 
         new SimpleDateFormat("YYYY");
         parametre.put("AnneeEtd",new SimpleDateFormat("YYYY").format(new Date()));

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
         year=year+1;
         //int NexYears=Integer.valueOf(ValNex);
         parametre.put("AnnNext",year+"");
          //parametre.put("AnnNext",(new Date().getYear()+1)+"");
           parametre.put("DatInscBef",DateBefInsc);
           
         //AnneeEtd
         //AnnNext   DatInscBef
         JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametre, new JREmptyDataSource());
           JasperViewer jasperviewer=new JasperViewer(jasperprint, false);
         jasperviewer.viewReport(jasperprint,false);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "ERROR IN JASPERREPORT"+e.getMessage());
        }
    }

public void AcceptReapteResid(int NumCard){

 String FrsName="";String LastName="";String numInscrpUinv="";String DateBirh="";
            String PlaceBirth="";String BranchStdy="";String Room="";String LevelStd="";      
 String SSS="SELECT Name_Resident,\n" +
"NumCard_Resident,\n" +
"LastName_Resident,\n" +
"DateBirth,\n" +
"PlaceBirth,\n" +
"BranchStd_Name,\n" +
"Room_Code,\n" +
"Num_InscritBac,\n" +
"DescriptionLevel        \n" +
"FROM Resident_Gl,Student_Res,Room,Branch_Study,Resident_Case,Level_Study \n" +
"WHERE\n" +
"\n" +
"\n" +
"Resident_Gl.ID_Rsident=Student_Res.ID_Rsident\n" +
"AND Student_Res.Id_Room=Room.Id_Room \n" +
"AND Branch_Study.Id_BranchStd=Student_Res.Id_BranchStd \n" +
"AND Resident_Gl.ID_Case_Resident =Resident_Case.ID_Case_Resident\n" +
"AND Level_Study.Id_LevelStudy=Student_Res.Id_LevelStudy     \n" +
"AND Resident_Gl.NumCard_Resident="+NumCard;
     
      cnx.Connecting();
        try {
            stm=cnx.getConnect().createStatement();
            res=stm.executeQuery(SSS);
            
            while (res.next()) {                
               // JOptionPane.showMessageDialog(null, "I am in Res.next");
                FrsName=res.getString("Name_Resident");//1
                System.out.println("residence.Student_Res.Acquittal_Report()"+FrsName);
                LastName=res.getString("LastName_Resident");//2
                 System.out.println("residence.Student_Res.Acquittal_Report()"+LastName);
                //NumCard=res.getInt("NumCard_Resident");
                System.out.println("residence.Student_Res.Acquittal_Report()"+NumCard);
                DateBirh=new SimpleDateFormat("dd/MM/YYYY").format(res.getDate("DateBirth"));//3
                System.out.println("residence.Student_Res.Acquittal_Report()"+DateBirh);
                PlaceBirth=res.getString("PlaceBirth");//4
                System.out.println("residence.Student_Res.Acquittal_Report()"+PlaceBirth);
                BranchStdy=res.getString("BranchStd_Name");//5
                System.out.println("residence.Student_Res.Acquittal_Report()"+BranchStdy);
                LevelStd=res.getString("DescriptionLevel");//6
                 System.out.println("residence.Student_Res.Acquittal_Report()levelStd"+LevelStd);
                //numInscrpUinv=res.getString("Num_InscritBac");
                System.out.println("residence.Student_Res.Acquittal_Report()"+numInscrpUinv);
                Room=res.getString("Room_Code");//7
                System.out.println("residence.Student_Res.Acquittal_Report()"+Room);
            }
            
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in sql Server"+e.getMessage());
        }
        
        try {
            res.close();
            stm.close();
            cnx.Deconnect();
        } catch (Exception e) {
        }
        
      JasperReport jasperreport;
        try {
            InputStream file=getClass().getResourceAsStream("/Reports/AcceptRepeatResd.jrxml");   
            JasperDesign jasperdesign=JRXmlLoader.load(file);
         jasperreport=JasperCompileManager.compileReport(jasperdesign);
         Map parametre=new HashMap<String, Object>();
         parametre.put("PrNameStd", FrsName);
         parametre.put("PrLasName", LastName);
        // parametre.put("NumInscBac", numInscrpUinv);//
        // parametre.put("PrNumCard", ""+NumCard);//
         parametre.put("DatBirth", DateBirh);
         parametre.put("PrPlcBirth", PlaceBirth);
         parametre.put("PrBranch", BranchStdy);
          parametre.put("LevelStd", BranchStdy);//
         parametre.put("Prroom", Room);
         parametre.put("PrPavilion", Room.substring(0, 1)); 
           

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
         parametre.put("AnneeEtd",year+"");
         year=year+1;
         //int NexYears=Integer.valueOf(ValNex);
         parametre.put("AnnNext",year+"");
         JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametre, new JREmptyDataSource());
           JasperViewer jasperviewer=new JasperViewer(jasperprint, false);
         jasperviewer.viewReport(jasperprint,false);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "ERROR IN JASPERREPORT"+e.getMessage());
        }
  }

    public void Acquittal_ReportTotal(String Case ){ //Acquittal_ReportFor One  

  String S1="SELECT Name_Resident,LastName_Resident,NumCard_Resident,DateBirth,PlaceBirth,Branch_Study.BranchStd_Name,Room.Room_Code,Pavilion.Pavilion_Name,Student_Res.Num_InscritBac\n" +
" FROM Resident_Gl,Student_Res,Pavilion,Room,Branch_Study,Resident_Case WHERE Resident_Case.Resident_Case=N'ســــاكن' AND  Resident_Gl.ID_Rsident=Student_Res.ID_Rsident\n" +
"AND Student_Res.Id_Room=Room.Id_Room AND Branch_Study.Id_BranchStd=Student_Res.Id_BranchStd AND Room.ID_Pavilion=Pavilion.ID_Pavilion \n" +
"AND Resident_Gl.ID_Case_Resident =Resident_Case.ID_Case_Resident  order by Room.Room_Code";    
  
  
      JasperReport jasperreport;
        try {
            InputStream file=getClass().getResourceAsStream("/Reports/TotalAcquittalStd.jrxml"); 
          
            JasperDesign jasperdesign=JRXmlLoader.load(file);
         JRDesignQuery newQuery=new JRDesignQuery();
            
           newQuery.setText(S1);
           jasperdesign.setQuery(newQuery);
           
         jasperreport=JasperCompileManager.compileReport(jasperdesign);

            Connection Cnx1;
         cnx.Connecting();
        Cnx1 =cnx.getConnect();
         JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,null,Cnx1);
         JasperViewer jasperviewer=new JasperViewer(jasperprint, false);           
         // jasperviewer.show();
         jasperviewer.viewReport(jasperprint,false);
             
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "ERROR IN JASPERREPORT"+e.getMessage());
        }
    
    }

    JFrame home1;
public void SetHomeMsg(JFrame h){home1=h;

}
    /********************Method Printing Report ****************************/
    
    public void PrintReportToPrinter(JasperPrint jp) throws JRException {
    // TODO Auto-generated method stub
    PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
    // printRequestAttributeSet.add(MediaSizeName.ISO_A4); //setting page size
    printRequestAttributeSet.add(new Copies(1));
PrintService service = PrintServiceLookup.lookupDefaultPrintService();
    PrinterName printerName = new PrinterName(service.getName(), null); //gets printer 

    PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
    printServiceAttributeSet.add(printerName);

    JRPrintServiceExporter exporter = new JRPrintServiceExporter();

    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
    //JRPrintServiceExporterParameter
    
    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printServiceAttributeSet);
    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
    exporter.exportReport();
}
/************************************************************************/
 
/********************Printing Report With OUT Dialogue******************/    
public void PrintingWithoutDialogAccommdation_Rapporteur(String FrsName,String LastName,int NumCard,String DateBirh,String PlaceBirth,String BranchStdy,String NameResidence,String PavilionAndRoom ){
        JasperReport jasperreport;
        try {
            InputStream file=getClass().getResourceAsStream("/Reports/Accommdation_Rapporteur.jrxml");   
            JasperDesign jasperdesign=JRXmlLoader.load(file);
            
         jasperreport=JasperCompileManager.compileReport(jasperdesign);
         Map parametre=new HashMap<String, Object>();
         parametre.put("NameStsRes", FrsName);
         parametre.put("SurNameStdRes", LastName);
         parametre.put("NumCard", ""+NumCard);
         parametre.put("DateBirth", DateBirh);
         parametre.put("PlaceBirth", PlaceBirth);
         parametre.put("BranchStudy", BranchStdy);
         parametre.put("NameResidence", NameResidence);
         parametre.put("PavilionAndRoom", PavilionAndRoom); 
         Calendar cal = Calendar.getInstance();
         cal.setTime(new Date());
         int year = cal.get(Calendar.YEAR);
         parametre.put("Annet", new SimpleDateFormat("YYYY").format(new Date())); 
         year=year+1;
         parametre.put("AnneNxt", year+""); 
         /*************************************/
         String QuerResid="Select * from Manager where kind_Manager="+2;
                    cnx.Connecting();
                    try {
                        stm=cnx.getConnect().createStatement();
                        res=stm.executeQuery(QuerResid);
                        if (res.next()) {
                         SimpleDateFormat format=new  SimpleDateFormat("dd/MM/yyyy");
                            String s=format.format(res.getDate("Date_Decision"));
                           parametre.put("FullNamDrcDirc", res.getString("FirstName")+" "+res.getString("LastName")); 
                            parametre.put("DateDcsDirc",s);
                            parametre.put("NumDecDirct",res.getInt("Num_Decision")+"");
                         }
                        stm.close();
                        res.close();
                        String QuerDirection="Select * from Manager where kind_Manager="+1;        
                        stm=cnx.getConnect().createStatement();
                        res=stm.executeQuery(QuerDirection);
                         if (res.next()) {
                           SimpleDateFormat format=new  SimpleDateFormat("dd/MM/yyyy");
                            String s=format.format(res.getDate("Date_Decision"));
                            parametre.put("FullNamDrcRes", res.getString("FirstName")+" "+res.getString("LastName")); 
                            parametre.put("DateDecResi",s);
                            parametre.put("NumDecisionRes",res.getInt("Num_Decision")+"");
                           }
                        } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "ERROOOOOOOOR"+e.getMessage());
                    }
                    try {
                        stm.close();
                        res.close();
                        cnx.Deconnect();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
         /**************************************/
         JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametre, new JREmptyDataSource());
         PrintReportToPrinter(jasperprint);

        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "ERROR IN JASPERREPORT"+e.getMessage());
        }


}
/**********************************************************************/    
    
    
   /******************************************************************************/
    
     /***************************************************************************/
   @Override
    public void UpdateResident(int ID_Resident,int NumCard) {
        
        PreparedStatement prstm = null;
       // int ID_Resident=Get_ID_Resident(NumCard);
           String Query="UPDATE Resident_Gl SET Name_Resident=? ,LastName_Resident=? ,"
            + " DateBirth=? ,PlaceBirth=?,ID_gender=?,ID_Case_Resident=?  WHERE ID_Rsident="+ID_Resident;
           
            String QueryUpdStd="UPDATE Student_Res  SET Name_Father=?,FullName_Mother=?,\n" +
"            ProfessionFather=? ,ProfessionMother=?,Address=?,ID_Wilaya=?,Name_Commune=?,Name_Daira=?, \n" +
"                Id_Nationalite=?,SituationFamily=?,BacYear=?,BacMoyen=?,PlaceGetBac=?,Id_BranchStd=?,DateInscrp=?,\n" +
"                    Id_Faculty=?,Id_LevelStudy=?,Num_InscritBac=?,\n" +
"                    Name_ResidentFr=?,LastName_ResidentFr=?,PlaceBirthFr=?,\n" +
"                    Name_FatherFr=?,Name_MotherFr=?,LastName_MotheFr=?, LastNamMothAR=? \n" +
"                    WHERE ID_Rsident="+ID_Resident;
            
           /* QueryUpdStd="UPDATE Student_Res  SET Name_Father=? ,FullName_Mother=? ,"
            + " ProfessionFather=? ,ProfessionMother=?,Address=?,ID_Wilaya=?,Name_Commune=?,Name_Daira=?,"+
                "Id_Nationalite=?,SituationFamily=?,BacYear=?,BacMoyen=?,PlaceGetBac=?,Id_BranchStd=?,DateInscrp=?,"
                    + "Id_Faculty=?,Id_LevelStudy=?,Num_InscritBac=?,"
                    + "Name_ResidentFr=?,LastName_ResidentFr=?,PlaceBirthFr=?,"
                    + "Name_FatherFr=?,Name_MotherFr=?, LastNamMothAR=?"    
                    + "WHERE ID_Rsident="+ID_Resident;*/
     cnx.Connecting();
            try {
             cnx.getConnect().setAutoCommit(false);
                 
        super.UpdateResident( ID_Resident, NumCard);
        
         //JOptionPane.showMessageDialog(null, "I am After Update Resident_Gl super.UpdateResident ");
        
            prstm=cnx.getConnect().prepareStatement(QueryUpdStd);
        prstm.setString(1, this.Name_Father);
        prstm.setString(2,this.FullName_Mother);
          prstm.setString(3, this.ProfessionFather);
        prstm.setString(4,this.ProfessionMother);
          prstm.setString(5, this.Address);
        prstm.setInt(6,this.ID_Wilaya);
          prstm.setString(7, this.Name_Commune);
        prstm.setString(8,this.Name_Daira);
          prstm.setInt(9, this.Id_Nationalite);
        prstm.setString(10,this.SituationFamily);
          prstm.setInt(11, this.BacYear);
        prstm.setDouble(12,this.BacMoyen);
         prstm.setString(13,this.PlaceGetBac);
          prstm.setInt(14,this.Id_BranchStd);
        prstm.setDate(15,new java.sql.Date(this.DateInscrp.getTime()));
        prstm.setInt(16,this.Id_Faculty);
         prstm.setInt(17,this.Id_LevelStudy);
          prstm.setString(18,this.Num_InscritBac);
          prstm.setString(19, this.Name_ResidentFr);
                System.out.println("residence.Student_Res.UpdateResident()"+Name_ResidentFr);
          prstm.setString(20, this.LastName_ResidentFr);
          System.out.println("residence.Student_Res.UpdateResident()"+LastName_ResidentFr);
          prstm.setString(21, this.PlaceBirthFr);
         System.out.println("residence.Student_Res.UpdateResident()"+PlaceBirthFr);
          prstm.setString(22, this.Name_FatherFr);
          System.out.println("residence.Student_Res.UpdateResident()"+Name_FatherFr);
          prstm.setString(23, this.Name_MotherFr);
          System.out.println("residence.Student_Res.UpdateResident()"+Name_MotherFr);
          prstm.setString(24, this.LastName_MotheFr);
          prstm.setString(25, this.LastNamMothAR);
          System.out.println("residence.Student_Res.UpdateResident()"+LastNamMothAR);
          int x=prstm.executeUpdate();
        
                if (x>0) {
           // JOptionPane.showMessageDialog(null, "The Resident Is Update ");
        }else  {JOptionPane.showMessageDialog(null, " Error Update The Resident ");
                }

     
            cnx.getConnect().commit();
    
             setValConfiramation(1);
            
            
        } catch (Exception e) {
     setValConfiramation(0);
          JOptionPane.showMessageDialog(null, "Error in SQL Class Resident "+e.getMessage());
            
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
     
 public void DisplayTabToTakeRoomStd(JTable tabRes){
  String Query="select Student_Res.ID_Rsident,NumCard_Resident,Name_Resident,LastName_Resident,DateBirth,PlaceBirth, "
          + "Student_Res.ID_Rsident,ID_Case_Resident,Room_Code\n" +
        " from Resident_Gl,Student_Res,Room\n" +
        "Where Student_Res.ID_Rsident=Resident_Gl.ID_Rsident\n" +
        " and (Student_Res.Id_Room=Room.Id_Room )";
      DefaultTableModel dftabMd=(DefaultTableModel)tabRes.getModel();   
         dftabMd.setRowCount(0);
         cnx.Connecting();
     get_Info_DB.filling_ArrayList("Resident_Case", ListInfo);
     try {
             stm=getCnx().getConnect().createStatement();
             res=stm.executeQuery(Query);
             String Branch_Profession="";
                     while (res.next()) {
                  Object arg[]={ListInfo.get(res.getInt("ID_Case_Resident")-1),res.getString("Room_Code"),"طالب",
                  res.getString("PlaceBirth"),res.getDate("DateBirth"),res.getString("Name_Resident"),
                      res.getString("LastName_Resident"),res.getInt("NumCard_Resident"),res.getInt("ID_Rsident")};          
                  dftabMd.addRow(arg);
             }
                       
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error in sql :"+e.getMessage());
         }

}

public void DisplayReportAllStdDetaills(String Branch,String LevelStdy,String CaseStd){  //Interne_Student
    try {
        String TestQuery="SELECT Name_Resident,LastName_Resident,DateBirth,PlaceBirth,Branch_Study.BranchStd_Name,Level_Study.DescriptionLevel,Resident_Case.Resident_Case FROM Resident_Gl,Resident_Case,Branch_Study,Level_Study,Student_Res "
                  + "WHERE Branch_Study.BranchStd_Name=N'"+Branch+"' AND Level_Study.DescriptionLevel=N'"+LevelStdy+"' AND Resident_Case.Resident_Case=N'"+CaseStd+"' AND Resident_Gl.ID_Rsident=Student_Res.ID_Rsident AND Student_Res.Id_BranchStd=Branch_Study.Id_BranchStd  AND Level_Study.Id_LevelStudy=Student_Res.Id_LevelStudy AND Resident_Case.ID_Case_Resident=Resident_Gl.ID_Case_Resident";
         JasperReport jasperreport;
         InputStream file=getClass().getResourceAsStream("/Reports/ListStdAndBranch.jrxml");
          JasperDesign jasperdesign=JRXmlLoader.load(file);
           JRDesignQuery newQuery=new JRDesignQuery();
           newQuery.setText(TestQuery);
           jasperdesign.setQuery(newQuery);
          Map parametres=new HashMap<String ,Object>();
            parametres.put("PrBranch", Branch);
            // parametres.put("FornPrn", Fornisseur);
             parametres.put("PrLevelStd", LevelStdy);
             parametres.put("PrCaseStd", ""+CaseStd);
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



public void TestCard(int x){
     try {
             String TestQuery1=""+
"Select Name_Resident,LastName_Resident ,DateBirth,NumCard_Resident,PlaceBirth,Branch_Study.BranchStd_Name,CodeBare_Resident,imageStd,"+
"Room_Code FROM  Resident_Gl,Student_Res,Branch_Study,Room Where Resident_Gl.NumCard_Resident="+x+" AND Resident_Gl.ID_Rsident=Student_Res.ID_Rsident AND Branch_Study.Id_BranchStd=Student_Res.Id_BranchStd AND Student_Res.Id_Room=Room.Id_Room;";
                      
              JasperReport jasperreport;          
          // InputStream file=getClass().getResourceAsStream("/Reports/CardResident.jrxml");
            InputStream file=getClass().getResourceAsStream("/Reports/NewReportCard.jrxml");
           //NewReportCard
            JasperDesign jasperdesign=JRXmlLoader.load(file);
           JRDesignQuery newQuery=new JRDesignQuery();
           newQuery.setText(TestQuery1);
           jasperdesign.setQuery(newQuery);
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
public void ListStidentInPavilion(String Pvl){
 try {
     String TestQuery="SELECT Name_Resident,LastName_Resident,DateBirth,PlaceBirth,Branch_Study.BranchStd_Name,Level_Study.DescriptionLevel,Resident_Case.Resident_Case,Room_Code \n" +
"FROM Resident_Gl,Resident_Case,Branch_Study,Level_Study,Student_Res,Pavilion,Room WHERE Resident_Gl.ID_Rsident=Student_Res.ID_Rsident \n" +
"AND Student_Res.Id_BranchStd=Branch_Study.Id_BranchStd AND Level_Study.Id_LevelStudy=Student_Res.Id_LevelStudy AND Resident_Case.ID_Case_Resident=Resident_Gl.ID_Case_Resident\n" +
"AND Student_Res.Id_Room=Room.Id_Room AND Room.ID_Pavilion=Pavilion.ID_Pavilion AND Pavilion_Name='"+Pvl+"'";
            JasperReport jasperreport;          
            InputStream file=getClass().getResourceAsStream("/Reports/ListStudentInPavilon.jrxml");
            JasperDesign jasperdesign=JRXmlLoader.load(file);
           JRDesignQuery newQuery=new JRDesignQuery();
           newQuery.setText(TestQuery);
           jasperdesign.setQuery(newQuery);
           jasperreport=JasperCompileManager.compileReport(jasperdesign);
              Map parametres=new HashMap<String ,Object>();
            parametres.put("Pavilion", Pvl);
           this.cnx.Connecting();
           Connection cnxt=this.cnx.getConnect();
           JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametres,cnxt);
            JasperViewer JspViewr=new JasperViewer(jasperprint, false);
              JspViewr.viewReport(jasperprint,false);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void ListStudentInterne(){
     try {
         JasperReport jasperreport;          
          // InputStream file=getClass().getResourceAsStream("/Reports/CardResident.jrxml");
            InputStream file=getClass().getResourceAsStream("/Reports/RapportAllStdIn.jrxml");
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

public void ListStudentWithCase(String caseStd){

try {
     String TestQuery="SELECT Name_Resident,LastName_Resident,DateBirth,PlaceBirth,Branch_Study.BranchStd_Name,\n" +
"Level_Study.DescriptionLevel,Resident_Case.Resident_Case,Room_Code\n" +
"FROM Resident_Gl,Resident_Case,Branch_Study,Level_Study,Student_Res,Room \n" +
"WHERE Resident_Gl.ID_Rsident=Student_Res.ID_Rsident\n" +
"AND Student_Res.Id_BranchStd=Branch_Study.Id_BranchStd \n" +
"AND Level_Study.Id_LevelStudy=Student_Res.Id_LevelStudy \n" +
"AND Resident_Case.ID_Case_Resident=Resident_Gl.ID_Case_Resident\n" +
"AND Student_Res.Id_Room=Room.Id_Room\n" +
 "AND Resident_Case.Resident_Case=N'"+caseStd+"' AND Id_Ptrn_Res=1 Order by Room_Code";            
//"AND Resident_Case.Resident_Case=N'ســــاكن';";
            JasperReport jasperreport;          
            InputStream file=getClass().getResourceAsStream("/Reports/ListListStdCases.jrxml");
            JasperDesign jasperdesign=JRXmlLoader.load(file);
           JRDesignQuery newQuery=new JRDesignQuery();
           newQuery.setText(TestQuery);
           jasperdesign.setQuery(newQuery);
           jasperreport=JasperCompileManager.compileReport(jasperdesign);
              Map parametres=new HashMap<String ,Object>();
            parametres.put("CaseStd", caseStd);
           this.cnx.Connecting();
           Connection cnxt=this.cnx.getConnect();
           JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametres,cnxt);
            JasperViewer JspViewr=new JasperViewer(jasperprint, false);
              JspViewr.viewReport(jasperprint,false);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void ListStd_Cas_Brch(String caseStd,String Brnch){
try {
String TestQuery1="SELECT Name_Resident,LastName_Resident,DateBirth,PlaceBirth,Branch_Study.BranchStd_Name,Level_Study.DescriptionLevel,Resident_Case.Resident_Case,Room.Room_Code \n" +
"FROM Resident_Gl,Resident_Case,Branch_Study,Level_Study,Student_Res,Room WHERE Resident_Gl.ID_Rsident=Student_Res.ID_Rsident AND Student_Res.Id_BranchStd=Branch_Study.Id_BranchStd  AND Level_Study.Id_LevelStudy=Student_Res.Id_LevelStudy \n" +
"AND Resident_Case.ID_Case_Resident=Resident_Gl.ID_Case_Resident\n" +
"AND Student_Res.Id_Room=Room.Id_Room AND  Resident_Case=N'"+caseStd+"' AND BranchStd_Name=N'"+Brnch+"' order by Room_Code";
       // + "AND Resident_Case=N'"+caseStd+"' Order by Name_Resident";

JasperReport jasperreport;          
            InputStream file=getClass().getResourceAsStream("/Reports/BranchAndCase.jrxml");
            JasperDesign jasperdesign=JRXmlLoader.load(file);
           JRDesignQuery newQuery=new JRDesignQuery();
           newQuery.setText(TestQuery1);
           jasperdesign.setQuery(newQuery);
           jasperreport=JasperCompileManager.compileReport(jasperdesign);
              Map parametres=new HashMap<String ,Object>();
            parametres.put("PrCase", caseStd);
            parametres.put("PrBranch", Brnch);
           this.cnx.Connecting();
           Connection cnxt=this.cnx.getConnect();
           JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametres,cnxt);
            JasperViewer JspViewr=new JasperViewer(jasperprint, false);
              JspViewr.viewReport(jasperprint,false);
    } catch (Exception e) {
        e.printStackTrace();
    }

}

public void ListStdPavilon_Case(String Pv,String Case){
try {
String TestQuery1="SELECT Name_Resident,LastName_Resident,DateBirth,PlaceBirth,Branch_Study.BranchStd_Name,Level_Study.DescriptionLevel,Resident_Case.Resident_Case,Room.Room_Code \n" +
"FROM Resident_Gl,Resident_Case,Branch_Study,Level_Study,Student_Res,Room,Pavilion WHERE \n" +
"Resident_Gl.ID_Rsident=Student_Res.ID_Rsident \n" +
"AND Student_Res.Id_BranchStd=Branch_Study.Id_BranchStd  \n" +
"AND Level_Study.Id_LevelStudy=Student_Res.Id_LevelStudy \n" +
"AND Resident_Case.ID_Case_Resident=Resident_Gl.ID_Case_Resident\n" +
"AND Student_Res.Id_Room=Room.Id_Room \n" +
"AND Room.ID_Pavilion=Pavilion.ID_Pavilion\n" +
"AND Resident_Case=N'"+Case+"' AND Pavilion_Name='"+Pv+"' AND Id_Ptrn_Res=1 order by Room_Code ";
       // + "AND Resident_Case=N'"+caseStd+"' Order by Name_Resident";

JasperReport jasperreport;          
            InputStream file=getClass().getResourceAsStream("/Reports/LstStdPavin_Case.jrxml");
            JasperDesign jasperdesign=JRXmlLoader.load(file);
           JRDesignQuery newQuery=new JRDesignQuery();
           newQuery.setText(TestQuery1);
           jasperdesign.setQuery(newQuery);
           jasperreport=JasperCompileManager.compileReport(jasperdesign);
              Map parametres=new HashMap<String ,Object>();
            parametres.put("PrCase", Case);
            parametres.put("PrPvil", Pv);
           this.cnx.Connecting();
           Connection cnxt=this.cnx.getConnect();
           JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametres,cnxt);
            JasperViewer JspViewr=new JasperViewer(jasperprint, false);
              JspViewr.viewReport(jasperprint,false);
    } catch (Exception e) {
        e.printStackTrace();
    }

}

public void List_Branch_ofStd(String Branch){

 try {
        String TestQuery="SELECT Name_Resident,LastName_Resident,DateBirth,PlaceBirth,Branch_Study.BranchStd_Name,Level_Study.DescriptionLevel,Resident_Case.Resident_Case FROM Resident_Gl,Resident_Case,Branch_Study,Level_Study,Student_Res "
                  + "WHERE Branch_Study.BranchStd_Name=N'"+Branch+"'"
                + " AND Resident_Gl.ID_Rsident=Student_Res.ID_Rsident "
                + "AND Student_Res.Id_BranchStd=Branch_Study.Id_BranchStd  "
                + "AND Level_Study.Id_LevelStudy=Student_Res.Id_LevelStudy "
                + "AND Resident_Case.ID_Case_Resident=Resident_Gl.ID_Case_Resident order by Name_Resident";
         JasperReport jasperreport;
         InputStream file=getClass().getResourceAsStream("/Reports/ListStd_Branch.jrxml");
          JasperDesign jasperdesign=JRXmlLoader.load(file);
           JRDesignQuery newQuery=new JRDesignQuery();
           newQuery.setText(TestQuery);
           jasperdesign.setQuery(newQuery);
          Map parametres=new HashMap<String ,Object>();
            parametres.put("PrBranch", Branch);
       
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
public void FormStudentInf(int NumCard){
String Query="Select Name_Resident,LastName_Resident ,DateBirth,PlaceBirth,Num_InscritBac,Name_Father,FullName_Mother,LastNamMothAR,\n" +
"Address,BacYear,Branch_Study.BranchStd_Name,Level_Study.Level_study,\n" +
"Name_ResidentFr,LastName_ResidentFr\n" +
" FROM  Resident_Gl,Student_Res,Branch_Study,Level_Study Where\n" +
" Resident_Gl.NumCard_Resident="+NumCard+"\n" +
" and  Resident_Gl.ID_Rsident=Student_Res.ID_Rsident \n" +
" and Student_Res.Id_BranchStd=Branch_Study.Id_BranchStd\n" +
"AND Level_Study.Id_LevelStudy=Student_Res.Id_LevelStudy ";
String p1="",p2="",p3="",p4="",p5="",p6="",p7="",p8="",p9="",p10="",p11="",p12="",p13="",p14="";
cnx.Connecting();
   
try {
        stm=cnx.getConnect().createStatement();
            res=stm.executeQuery(Query);
            if(res.next()){
            p1=res.getString(1);
          if (p1==null) {
                    p1="";
                }
            p2=res.getString(2);
       if (p2==null) {
                    p2="";
                }
            p3= new SimpleDateFormat("dd/MM/YYYY").format(res.getDate(3)) ;
         if (p3==null) {
                    p3="";
                }
            p4=res.getString(4);
         if (p4==null) {
                    p4="";
                }
            p5=res.getString(5);
          if (p5==null) {
                    p5="";
                }
            p6=res.getString(6);
            if (p6==null) {
                    p6="";
                }
            p7=res.getString(7);
           if (p7==null) {
                    p7="";
                }
            p8=res.getString(8);
          if (p8==null) {
                    p8="";
                }
            p9=res.getString(9);
            if (p9==null) {
                    p9="";
                }
            p10=res.getInt(10)+"";
            p11=res.getString(11);
         if (p11==null) {
                    p11="";
                }
            p12=res.getString(12);
              if (p12==null) {
                    p12="";
                }
            p13=res.getString(13);
        
            if (p13==null) {
                    p13="";
                }
            p14=res.getString(14);
            if (p14==null) {
                    p14="";
                }
            }else{
                JOptionPane.showMessageDialog(null, " Error In FormStudentInf ");
            }
          JasperReport jasperreport;
         InputStream file=getClass().getResourceAsStream("/Reports/FormStd_Trc.jrxml");
          JasperDesign jasperdesign=JRXmlLoader.load(file);
          Map parametres=new HashMap<String ,String>();
            parametres.put("PrName", p1);
            
             parametres.put("PrLstNam", p2);
             parametres.put("PrDatBrth", p3);
             parametres.put("PrPlcBrth", p4);
             parametres.put("NumInsc", p5);
             parametres.put("PrNamFth", p6);
             parametres.put("PrNmLstM", p8+" "+p7);
             parametres.put("PrAdrss", p9);
             parametres.put("BacYear", p10);
             parametres.put("PrBrch",p11);
             parametres.put("PrLvlStd",p12);
             parametres.put("PrNamFr",p13);
             parametres.put("PrLastFr", p14);
             parametres.put("PrNumCrd", NumCard+"");
              Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
             parametres.put("AnneEtd", year+"");
             
         jasperreport=JasperCompileManager.compileReport(jasperdesign);
        /*  Connection Cnx1;
         cnx.Connecting();
        Cnx1 =cnx.getConnect();*/
        JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametres,new JREmptyDataSource());
        // jp=JasperFillManager.fillReport(jr, parametres, cnx.getConnect());
           JasperViewer JspViewr=new JasperViewer(jasperprint, false);
              JspViewr.viewReport(jasperprint,false);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error in jasper Report"+e.getMessage());
    }
    try {
        res.close();
        stm.close();
        cnx.Deconnect();
    } catch (Exception e) {
    }
}

public void ToolsRoomTotal_Pvl(String Pv){

    String TestQuery="SELECT Name_Resident,LastName_Resident,Branch_Study.BranchStd_Name,Room_Code\n" +
"FROM Resident_Gl,Branch_Study,Student_Res,Room,Pavilion \n" +
"WHERE Resident_Gl.ID_Rsident=Student_Res.ID_Rsident\n" +
"AND Student_Res.Id_BranchStd=Branch_Study.Id_BranchStd \n" +
"AND Student_Res.Id_Room=Room.Id_Room\n" +
"AND Room.ID_Pavilion=Pavilion.ID_Pavilion\n" +
"AND Pavilion.Pavilion_Name='"+Pv+"' order by Room_Code ";
    try {
         JasperReport jasperreport;
         InputStream file=getClass().getResourceAsStream("/Reports/ToolsRoom.jrxml");
          JasperDesign jasperdesign=JRXmlLoader.load(file);
           JRDesignQuery newQuery=new JRDesignQuery();
           newQuery.setText(TestQuery);
           jasperdesign.setQuery(newQuery);
          Map parametres=new HashMap<String ,Object>();
               Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
             parametres.put("AnneEtd", year+"");
           parametres.put("PrPav", Pv);
       
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
public void ToolsRoomTotal_PvlOne(int Ncd){

    String TestQuery="SELECT Name_Resident,LastName_Resident,Branch_Study.BranchStd_Name,Room_Code\n" +
"FROM Resident_Gl,Branch_Study,Student_Res,Room \n" +
"WHERE Resident_Gl.ID_Rsident=Student_Res.ID_Rsident\n" +
"AND Student_Res.Id_BranchStd=Branch_Study.Id_BranchStd \n" +
"AND Student_Res.Id_Room=Room.Id_Room\n" +
"AND Resident_Gl.NumCard_Resident='"+Ncd+"'";
    try {
         JasperReport jasperreport;
         InputStream file=getClass().getResourceAsStream("/Reports/ToolsRoom.jrxml");
          JasperDesign jasperdesign=JRXmlLoader.load(file);
           JRDesignQuery newQuery=new JRDesignQuery();
           newQuery.setText(TestQuery);
           jasperdesign.setQuery(newQuery);
          Map parametres=new HashMap<String ,Object>();
               Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
             parametres.put("AnneEtd", year+"");
           //parametres.put("PrPav", Ncd+"");
       
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
/***********************bayan mora9aba *******************************/
public void ControleFich(String Pv){
    
String TestQuery="SELECT Name_Resident,LastName_Resident,Branch_Study.BranchStd_Name,Room_Code\n" +
"FROM Resident_Gl,Branch_Study,Student_Res,Room,Pavilion \n" +
"WHERE Resident_Gl.ID_Rsident=Student_Res.ID_Rsident\n" +
"AND Student_Res.Id_BranchStd=Branch_Study.Id_BranchStd \n" +
"AND Student_Res.Id_Room=Room.Id_Room\n" +
"AND Room.ID_Pavilion=Pavilion.ID_Pavilion\n" +
"AND Pavilion.Pavilion_Name='"+Pv+"' order by Room_Code";
   try {
         JasperReport jasperreport;
         InputStream file=getClass().getResourceAsStream("/Reports/ControlFiche.jrxml");
          JasperDesign jasperdesign=JRXmlLoader.load(file);
           JRDesignQuery newQuery=new JRDesignQuery();
           newQuery.setText(TestQuery);
           jasperdesign.setQuery(newQuery);
          Map parametres=new HashMap<String ,Object>();
               Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
             parametres.put("AnneEtd", year+"");
           parametres.put("PrPav", Pv);
       
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
public void ControleFichOne(int Ncd){
    
String TestQuery="SELECT Name_Resident,LastName_Resident,Branch_Study.BranchStd_Name,Room_Code\n" +
"FROM Resident_Gl,Branch_Study,Student_Res,Room \n" +
"WHERE Resident_Gl.ID_Rsident=Student_Res.ID_Rsident\n" +
"AND Student_Res.Id_BranchStd=Branch_Study.Id_BranchStd \n" +
"AND Student_Res.Id_Room=Room.Id_Room\n" +
"AND Resident_Gl.NumCard_Resident='"+Ncd+"'";
   try {
         JasperReport jasperreport;
         InputStream file=getClass().getResourceAsStream("/Reports/ControlFiche.jrxml");
          JasperDesign jasperdesign=JRXmlLoader.load(file);
           JRDesignQuery newQuery=new JRDesignQuery();
           newQuery.setText(TestQuery);
           jasperdesign.setQuery(newQuery);
          Map parametres=new HashMap<String ,Object>();
               Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
             parametres.put("AnneEtd", year+"");
          // parametres.put("PrPav", Pv);
       
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
/***************************************************************************/
public void ListStdBacYear(int  BacYrs){
/*String TestQuery="SELECT Name_Resident,LastName_Resident,DateBirth,PlaceBirth,Branch_Study.BranchStd_Name,Level_Study.DescriptionLevel,Resident_Case.Resident_Case,Room_Code "
+"FROM Resident_Gl,Resident_Case,Branch_Study,Level_Study,Student_Res,Pavilion,Room"
+" WHERE Resident_Gl.ID_Rsident=Student_Res.ID_Rsident  AND Student_Res.Id_BranchStd=Branch_Study.Id_BranchStd"
+"AND Level_Study.Id_LevelStudy=Student_Res.Id_LevelStudy AND Resident_Case.ID_Case_Resident=Resident_Gl.ID_Case_Resident"
+"AND Student_Res.Id_Room=Room.Id_Room  AND Room.ID_Pavilion=Pavilion.ID_Pavilion AND Student_Res.BacYear=2018 order by Room_Code;";*/
  
String Qer="SELECT Name_Resident,LastName_Resident,DateBirth,PlaceBirth,Branch_Study.BranchStd_Name,Level_Study.DescriptionLevel,Resident_Case.Resident_Case,Room_Code \n" +
"FROM Resident_Gl,Resident_Case,Branch_Study,Level_Study,Student_Res,Pavilion,Room\n" +
" WHERE \n" +
" Resident_Gl.ID_Rsident=Student_Res.ID_Rsident \n" +
"AND Student_Res.Id_BranchStd=Branch_Study.Id_BranchStd\n" +
"AND Level_Study.Id_LevelStudy=Student_Res.Id_LevelStudy \n" +
"AND Resident_Case.ID_Case_Resident=Resident_Gl.ID_Case_Resident\n" +
"AND Student_Res.Id_Room=Room.Id_Room \n" +
"AND Room.ID_Pavilion=Pavilion.ID_Pavilion\n" +
"AND BacYear="+BacYrs+" order by Room_Code;";

try {
         JasperReport jasperreport;
         InputStream file=getClass().getResourceAsStream("/Reports/ListStudentbYBac.jrxml");
          JasperDesign jasperdesign=JRXmlLoader.load(file);
           JRDesignQuery newQuery=new JRDesignQuery();
           newQuery.setText(Qer);
           jasperdesign.setQuery(newQuery);
          Map parametres=new HashMap<String ,Object>();
             parametres.put("BacYear", "BacYrs");
          // parametres.put("PrPav", Pv);
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
/*********************************************************************************/

public void ListStdBacYear(int  BacYrs,String Branch){
String Query="SELECT Name_Resident,LastName_Resident,DateBirth,PlaceBirth,Branch_Study.BranchStd_Name,Level_Study.DescriptionLevel,Resident_Case.Resident_Case,Room_Code \n" +
"FROM Resident_Gl,Resident_Case,Branch_Study,Level_Study,Student_Res,Pavilion,Room\n" +
" WHERE \n" +
"Resident_Gl.ID_Rsident=Student_Res.ID_Rsident \n" +
"AND Student_Res.Id_BranchStd=Branch_Study.Id_BranchStd\n" +
"AND Level_Study.Id_LevelStudy=Student_Res.Id_LevelStudy \n" +
"AND Resident_Case.ID_Case_Resident=Resident_Gl.ID_Case_Resident\n" +
"AND Student_Res.Id_Room=Room.Id_Room \n" +
"AND Room.ID_Pavilion=Pavilion.ID_Pavilion\n" +
"AND BacYear="+BacYrs+"\n" +
"AND BranchStd_Name=N'"+Branch+"'\n" +
" order by Room_Code";

try {
         JasperReport jasperreport;
         InputStream file=getClass().getResourceAsStream("/Reports/ListStsWithAnnes_Branch.jrxml");
          JasperDesign jasperdesign=JRXmlLoader.load(file);
           JRDesignQuery newQuery=new JRDesignQuery();
           newQuery.setText(Query);
           jasperdesign.setQuery(newQuery);
          Map parametres=new HashMap<String ,Object>();
             parametres.put("Branch", Branch);
             parametres.put("BacYear", String.valueOf(BacYrs));
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
public void ListLevelStd(String Lvl){
String TestQuery="SELECT Name_Resident,LastName_Resident,DateBirth,PlaceBirth,Branch_Study.BranchStd_Name,\n" +
"Level_Study.DescriptionLevel\n" +
" FROM Resident_Gl,Branch_Study,Level_Study,Student_Res WHERE\n" +
"        Resident_Gl.ID_Rsident=Student_Res.ID_Rsident \n" +
"        AND Student_Res.Id_BranchStd=Branch_Study.Id_BranchStd \n" +
"        AND Level_Study.Id_LevelStudy=Student_Res.Id_LevelStudy \n" +
"        AND Level_Study.DescriptionLevel=N'"+Lvl+"' AND (Resident_Gl.ID_Case_Resident=2 or Resident_Gl.ID_Case_Resident=1) order by Name_Resident;";
   try {
         JasperReport jasperreport;
         InputStream file=getClass().getResourceAsStream("/Reports/ListStdLevel.jrxml");
          JasperDesign jasperdesign=JRXmlLoader.load(file);
           JRDesignQuery newQuery=new JRDesignQuery();
           newQuery.setText(TestQuery);
           jasperdesign.setQuery(newQuery);
          Map parametres=new HashMap<String ,Object>();
             parametres.put("LevelS", Lvl);
          // parametres.put("PrPav", Pv);
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


/***************************************************************************/
    public static void main(String[] args) {
        
        
        
//        Resident_Gl r=new  Resident_Gl("mmmmmm", "mmmmmm", 182, "100000", new Date(), "batna",1,1,7);
//        
//        Student_Res std=new Student_Res();
//        std.ListStdBacYear(2018, "علوم وتكنولوجيا");
//       /* Student_Res std=new Student_Res(r.getFirst_name(),r.getLast_name(),r.getNumbre_CardRes(),r.getBarcode(), 
//              r.getDateBirth(), r.getPlaceBirth(),r.getID_gender(),r.getId_Ptrn_Res(),7, "mmmmmm", "mmmmmm", "mmmmmm", "mmmmmm", "mmmmmm", 7,
//              "aa", "aa", 1, "aa", 2013, 10.2,"biskra", 2, new Date() , 2, 2, 1, "10000000",1);
//        */
//        
      //  std.UpdateResident(std.Get_ID_Resident(5378),5378);
   // std.DisplayReportAllStdDetaills("علوم الطبيعة والحياة","سنة أولي ليسانس","ســــاكن");
       // std.TestCard(4198);
        
        //std.ListStudentWithCase("ســــاكن");//
        //std.ListStd_Cas_Brch("ســــاكن","علوم الطبيعة والحياة");
        //std.ListStdPavilon_Case("A", "ســــاكن");
        //std.ControleFichOne(4198);
        //std.List_Branch_ofStd("علوم الطبيعة والحياة");
        
      /// std.Acquittal_ReportTotal("مجــــدد");
     //  std.SetHomeMsg(new JFrame());
       //std.AcquittalInDB(36);
       //std.TestReport();
      // std.Acquittal_Report(36);
      //  std.Acquittal_Report("Farid","Farid","Farid",1452,"Farid","Farid","Farid","Farid","Farid");
       // std.AddRsident();
    //    std.Insert_Old_student();
       // System.out.println("residence.Student_Res.main()"+std.ControleSaisirDataCorrect(".6."));  
      // std.DisplayAccommdation_Rapporteur("farid", "Khebbache", 542, "farid", "farid", "informatique", "El hadjeb", "100");
     // std.PrintingWithoutDialogAccommdation_Rapporteur("farid", "Khebbache", 542, "farid", "farid", "informatique", "El hadjeb", "100");
    /*std.DisplayStudentToUpdate(1, null, null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null, null, null, null, null);*/
   // std.Affiche();
    
    }
    
    public void Insert_Old_student(){
         String Query="Select *From Resident_GlRes";
         
        try {
            stm=cnx.getConnect().createStatement();
            res=stm.executeQuery(Query);
            
            while(res.next()){
                JOptionPane.showMessageDialog(null, "exist Rsident  ");
                Resident_Gl r=new Resident_Gl(res.getString("Name_Resident"),res.getString("LastName_Resident"),res.getInt("NumCard_Resident"),
                            "",new Date(res.getString("DateBirth")),res.getString("PlaceBirth"),res.getInt("ID_gender"), 1,7);
                /*
                if(){   //emply
                
                
                }else{  //Student intern_ Extern
                    if(){  // Student Entern
                        
                    }else{ //Student Intern
                    
                    }
                }*/
            } 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Sql :"+ex);
        }
        try {
            stm.close();
            cnx.Deconnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Deconnect :"+ex);
        }
        
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
     * @return the Name_Father
     */
    public String getName_Father() {
        return Name_Father;
    }

    /**
     * @param Name_Father the Name_Father to set
     */
    public void setName_Father(String Name_Father) {
        this.Name_Father = Name_Father;
    }

    /**
     * @return the FullName_Mother
     */
    public String getFullName_Mother() {
        return FullName_Mother;
    }

    /**
     * @param FullName_Mother the FullName_Mother to set
     */
    public void setFullName_Mother(String FullName_Mother) {
        this.FullName_Mother = FullName_Mother;
    }

    /**
     * @return the ProfessionFather
     */
    public String getProfessionFather() {
        return ProfessionFather;
    }

    /**
     * @param ProfessionFather the ProfessionFather to set
     */
    public void setProfessionFather(String ProfessionFather) {
        this.ProfessionFather = ProfessionFather;
    }

    /**
     * @return the ProfessionMother
     */
    public String getProfessionMother() {
        return ProfessionMother;
    }

    /**
     * @param ProfessionMother the ProfessionMother to set
     */
    public void setProfessionMother(String ProfessionMother) {
        this.ProfessionMother = ProfessionMother;
    }

    /**
     * @return the Address
     */
    public String getAddress() {
        return Address;
    }

    /**
     * @param Address the Address to set
     */
    public void setAddress(String Address) {
        this.Address = Address;
    }

    /**
     * @return the ID_Wilaya
     */
    public int getID_Wilaya() {
        return ID_Wilaya;
    }

    /**
     * @param ID_Wilaya the ID_Wilaya to set
     */
    public void setID_Wilaya(int ID_Wilaya) {
        this.ID_Wilaya = ID_Wilaya;
    }

    /**
     * @return the Name_Commune
     */
    public String getName_Commune() {
        return Name_Commune;
    }

    /**
     * @param Name_Commune the Name_Commune to set
     */
    public void setName_Commune(String Name_Commune) {
        this.Name_Commune = Name_Commune;
    }

    /**
     * @return the Name_Daira
     */
    public String getName_Daira() {
        return Name_Daira;
    }

    /**
     * @param Name_Daira the Name_Daira to set
     */
    public void setName_Daira(String Name_Daira) {
        this.Name_Daira = Name_Daira;
    }

    /**
     * @return the Id_Nationalite
     */
    public int getId_Nationalite() {
        return Id_Nationalite;
    }

    /**
     * @param Id_Nationalite the Id_Nationalite to set
     */
    public void setId_Nationalite(int Id_Nationalite) {
        this.Id_Nationalite = Id_Nationalite;
    }

    /**
     * @return the SituationFamily
     */
    public String getSituationFamily() {
        return SituationFamily;
    }

    /**
     * @param SituationFamily the SituationFamily to set
     */
    public void setSituationFamily(String SituationFamily) {
        this.SituationFamily = SituationFamily;
    }

    /**
     * @return the BacYear
     */
    public int getBacYear() {
        return BacYear;
    }

    /**
     * @param BacYear the BacYear to set
     */
    public void setBacYear(int BacYear) {
        this.BacYear = BacYear;
    }

    /**
     * @return the BacMoyen
     */
    public double getBacMoyen() {
        return BacMoyen;
    }

    /**
     * @param BacMoyen the BacMoyen to set
     */
    public void setBacMoyen(double BacMoyen) {
        this.BacMoyen = BacMoyen;
    }

    /**
     * @return the Id_BranchStd
     */
    public int getId_BranchStd() {
        return Id_BranchStd;
    }

    /**
     * @param Id_BranchStd the Id_BranchStd to set
     */
    public void setId_BranchStd(int Id_BranchStd) {
        this.Id_BranchStd = Id_BranchStd;
    }

    /**
     * @return the DateInscrp
     */
    public Date getDateInscrp() {
        return DateInscrp;
    }

    /**
     * @param DateInscrp the DateInscrp to set
     */
    public void setDateInscrp(Date DateInscrp) {
        this.DateInscrp = DateInscrp;
    }

    /**
     * @return the Id_Faculty
     */
    public int getId_Faculty() {
        return Id_Faculty;
    }

    /**
     * @param Id_Faculty the Id_Faculty to set
     */
    public void setId_Faculty(int Id_Faculty) {
        this.Id_Faculty = Id_Faculty;
    }

    /**
     * @return the Id_LevelStudy
     */
    public int getId_LevelStudy() {
        return Id_LevelStudy;
    }

    /**
     * @param Id_LevelStudy the Id_LevelStudy to set
     */
    public void setId_LevelStudy(int Id_LevelStudy) {
        this.Id_LevelStudy = Id_LevelStudy;
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
     * @return the Num_InscritBac
     */
    public String getNum_InscritBac() {
        return Num_InscritBac;
    }

    /**
     * @param Num_InscritBac the Num_InscritBac to set
     */
    public void setNum_InscritBac(String Num_InscritBac) {
        this.Num_InscritBac = Num_InscritBac;
    }

    /**
     * @param stm the stm to set
     */
    public void setStm(Statement stm) {
        this.stm = stm;
    }

    /**
     * @param res the res to set
     */
    public void setRes(ResultSet res) {
        this.res = res;
    }

    /**
     * @param prstm the prstm to set
     */
    public void setPrstm(PreparedStatement prstm) {
        this.prstm = prstm;
    }

    /**
     * @param cnx the cnx to set
     */
    public void setCnx(ConnectionDB cnx) {
        this.cnx = cnx;
    }

 
 
    
   
    
}
