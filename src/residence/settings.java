/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package residence;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class settings {
    
    public settings(){
//    Properties settings = new Properties();
//    settings.put("title","This is simple example");
//    settings.put("color","Color.BLACK");
//    FileOutputStream out = null;
//        try {
//            out = new FileOutputStream("mySettings.properties");
//            settings.store(out , "This is My Settings");
//        } catch (FileNotFoundException ex) {
//            System.out.println("residence.settings.<init>()"+ex.getMessage());
//        } catch (IOException ex) {
//            
//            System.out.println("residence.settings.<init>()"+ex.getMessage());
//        }
//    FileInputStream in = null;
//    
//        try {
//            in = new FileInputStream("mySettings.properties");
//            settings.load(in);
//            System.out.println("residence.settings.<init>()"+settings.getProperty("title2", "defaultValue") ); 
//            
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(settings.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(settings.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        
//        
//        
//        settings.put("title","The update");
//        try {
//            settings.load(in);
//            
//            System.out.println("residence.settings.<init>()"+settings.getProperty("title", "defaultValue") ); 
//        } catch (IOException ex) {
//            Logger.getLogger(settings.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        String userDir = System.getProperty("src");
//		File propertiesDir = new File ( userDir, ".myApp" );
    }
    
    
    ConnectionDB cnx=new ConnectionDB();
    
    public void ExportExternalData_To_AccessDB(){
         int MaxNumCardSqlSrv=0,MaxNumCardAccess=0;
     
    Statement stmMaxSqlsrv=null;
    ResultSet ResMaxSqlsrv=null;
        
     String Query_Max_IdSQLSrv="SELECT MAX (NumCard_Resident) FROM Resident_Gl WHERE Id_Ptrn_Res=2";
         cnx.Connecting();
        try {
            stmMaxSqlsrv=cnx.getConnect().createStatement();
            ResMaxSqlsrv=stmMaxSqlsrv.executeQuery(Query_Max_IdSQLSrv);
            if (ResMaxSqlsrv.next()) {
                MaxNumCardSqlSrv=ResMaxSqlsrv.getInt(1);
                System.out.println("MaxIdSqlSrv :"+MaxNumCardSqlSrv);
           }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmMaxSqlsrv.close();
            ResMaxSqlsrv.close();
            cnx.Deconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /********************************To Calc Max In Acces External Student**********************************/
        String  QueryMaxAccess="SELECT MAX (NumCard_Resident) FROM Resident_Gl WHERE Id_Ptrn_Res=2";   
        ResultSet ResMaxAccess=null;
       Statement  StmMaxAccess=null;
        ConnectingAccess();
        try {
            StmMaxAccess=GetConnectAccess().createStatement();
            ResMaxAccess=StmMaxAccess.executeQuery(QueryMaxAccess);
            if (ResMaxAccess.next()) {
                MaxNumCardAccess=ResMaxAccess.getInt(1);
                System.out.println("MaxIdAccess :"+MaxNumCardAccess);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        try {
           StmMaxAccess.close();
           ResMaxAccess.close();
            GetConnectAccess().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       /***************************************************************************************/ 
                  
            if (MaxNumCardSqlSrv>MaxNumCardAccess) {
                
 /*********************************Two QueryTo Insert Information*************************************/                
                String QueryInsertInAccess="INSERT INTO Resident_Gl (Name_Resident,LastName_Resident,NumCard_Resident,"
              + "CodeBare_Resident,"
            + "DateBirth,PlaceBirth,ID_gender,Id_Ptrn_Res,ID_Case_Resident,imageStd) "
                  + " VALUES (?,?,?,?,?,?,?,?,?,?)";
      
      
     String QueryInsertInAccessStdRes="INSERT INTO Student_ResExt (ID_Rsident,ID_Wilaya,Address,"
                   + "Id_Nationalite,BacYear,Id_BranchStd,Id_LevelStudy,Num_InscritBac ,DateInscrp) "
                    + " VALUES (?,?,?,?,?,?,?,?,?)";
      
/********************************************************************************************/      
//      String QueryComplete="Select Resident_Gl.ID_Rsident,Name_Resident,LastName_Resident ,CodeBare_Resident,NumCard_Resident,DateBirth,PlaceBirth,Resident_Gl.ID_gender,Id_Ptrn_Res,"
//              + "ID_Wilaya,Address,Id_Nationalite,BacYear,Id_BranchStd,\n" +
//"Id_LevelStudy,Num_InscritBac,DateInscrp,imageStd,ID_Case_Resident\n" +
//
//" FROM  Resident_Gl,Gender,Student_ResExt Where "
//              + " ( Resident_Gl.NumCard_Resident> "+MaxNumCardAccess+" and Resident_Gl.NumCard_Resident <= "+ MaxNumCardSqlSrv +" )"+
//" and Resident_Gl.ID_gender=Gender.ID_gender and Resident_Gl.ID_Rsident=Student_ResExt.ID_Rsident  and  Resident_Gl.Id_Ptrn_Res=2";
//      
   String  QueryComplete= "Select Resident_Gl.ID_Rsident,Name_Resident,LastName_Resident ,CodeBare_Resident,NumCard_Resident,\n" +
"DateBirth,PlaceBirth,Resident_Gl.ID_gender,Id_Ptrn_Res,\n" +
           
"ID_Wilaya,Address,Id_Nationalite,BacYear,Id_BranchStd,\n" +
"Id_LevelStudy,Num_InscritBac,DateInscrp,imageStd,ID_Case_Resident\n" +
"FROM  Resident_Gl,Gender,Student_ResExt Where \n" +
"( Resident_Gl.NumCard_Resident>  "+MaxNumCardAccess+" and Resident_Gl.NumCard_Resident <=  "+MaxNumCardSqlSrv+"  )\n" +
"and Resident_Gl.ID_gender=Gender.ID_gender \n" +
"and Resident_Gl.ID_Rsident=Student_ResExt.ID_Rsident  \n" +
"and  Resident_Gl.Id_Ptrn_Res=2";
      
   QueryComplete="Select Resident_Gl.ID_Rsident,Name_Resident,LastName_Resident ,CodeBare_Resident,NumCard_Resident,\n" +
"DateBirth,PlaceBirth,Resident_Gl.ID_gender,Id_Ptrn_Res,\n" +
"ID_Wilaya,Address,Id_Nationalite,BacYear,Id_BranchStd,\n" +
"Id_LevelStudy,Num_InscritBac,DateInscrp,imageStd,ID_Case_Resident\n" +
"FROM  Resident_Gl,Gender,Student_ResExt \n" +
"Where \n" +
"( Resident_Gl.NumCard_Resident>  183389 and Resident_Gl.NumCard_Resident <=  183847  )\n" +
"and Resident_Gl.ID_gender=Gender.ID_gender \n" +
"and Resident_Gl.ID_Rsident=Student_ResExt.ID_Rsident  \n" +
"and  Resident_Gl.Id_Ptrn_Res=2";
      
String QuerySlct="SELECT MAX(ID_Rsident) FROM Resident_Gl  WHERE Id_Ptrn_Res=2";      
      
/**********************************************************************************************/      
    
PreparedStatement prstStdRes=null; //To Insert Student
PreparedStatement prstm=null;      //To Insert Resident Gl

 cnx.Connecting(); //Connect SQLsever
 ConnectingAccess();// Connect Access 
 int ValMax=0 ;
  try {
      
    //  cnnct.setAutoCommit(false);
    //cnx.getConnect().setAutoCommit(false);
      
/*************Execut Query To Get All Student Exit In SQLServer But Not Found In Accesss******/
    stmMaxSqlsrv=cnx.getConnect().createStatement();
    ResMaxSqlsrv=stmMaxSqlsrv.executeQuery(QueryComplete);
    System.out.println("Execute Query To Get Difference Data Between SQLserver And Access ");

    /*************To prepare The Two prepare Statement ***********************************/
  
    prstm=cnnct.prepareStatement(QueryInsertInAccess);
    
    prstStdRes=cnnct.prepareStatement(QueryInsertInAccessStdRes);
    
    ResMaxSqlsrv.next();
     System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getString("Name_Resident"));
     System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getString("LastName_Resident"));
      System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getInt("NumCard_Resident"));
      
//      if (ResMaxSqlsrv.getRow()==0) {
//          JOptionPane.showMessageDialog(null, "لا يوجد بيانات للتحميل");
//      }else {
//      JOptionPane.showMessageDialog(null, "سيتم تحميل البيانات");
//      }
    //JOptionPane.showMessageDialog(null, "ResMaxSqlsrv :"+ResMaxSqlsrv.;
    
    while (ResMaxSqlsrv.next()) {
            prstm.setString(1, ResMaxSqlsrv.getString("Name_Resident"));
//            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getString("Name_Resident"));
            prstm.setString(2, ResMaxSqlsrv.getString("LastName_Resident"));
//            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getString("LastName_Resident"));
            // Numbre_CardRes=(Get_MAX_Numbre_CardRes(0)+1);
            prstm.setInt(3,ResMaxSqlsrv.getInt("NumCard_Resident")); //Corretct For TEsting 
//            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getInt("NumCard_Resident"));
            //prstm.setInt(3,11219);
            prstm.setString(4 ,ResMaxSqlsrv.getString("CodeBare_Resident"));
//            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getString("CodeBare_Resident"));
            prstm.setDate(5,ResMaxSqlsrv.getDate("DateBirth")   /*new java.sql.Date(getDateBirth().getTime())*/);
//            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getDate("DateBirth"));
            prstm.setString(6, ResMaxSqlsrv.getString("PlaceBirth"));
//            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getString("PlaceBirth"));
            prstm.setInt(7, ResMaxSqlsrv.getInt("ID_gender"));
//            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getInt("ID_gender"));
            prstm.setInt(8,ResMaxSqlsrv.getInt("Id_Ptrn_Res"));
//            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getInt("Id_Ptrn_Res"));
            prstm.setInt(9, ResMaxSqlsrv.getInt("ID_Case_Resident"));
//            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getInt("ID_Case_Resident"));
            prstm.setBlob(10, ResMaxSqlsrv.getBlob("imageStd"));
//            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getBinaryStream("imageStd"));
//            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getInt(1));
//            /***************************************************************************/
           
//            (ID_Rsident,ID_Wilaya,Address,"
//                   + "Id_Nationalite,BacYear,Id_BranchStd,Id_LevelStudy,Num_InscritBac"
//                    + ",DateInscrp"
//                    + "VALUES (?,?,?,?,?,?,?,?,?)";
             int x=prstm.executeUpdate();
            if (x>0) {
               //JOptionPane.showMessageDialog(null, "Success Executed In Global Res ");
            }else JOptionPane.showMessageDialog(null, "Error in added ");
            
           /*******************************************/        
            Statement stmX=cnnct.createStatement();
            ResultSet resX=stmX.executeQuery(QuerySlct);
            if (resX.next()) {
                ValMax=resX.getInt(1);
            }
             stmX=null;
            resX=null;
           /***************************************/ 
            
            prstStdRes.setInt(1, ValMax/*ResMaxSqlsrv.getInt("ID_Rsident")*/);
            prstStdRes.setInt(2, ResMaxSqlsrv.getInt("ID_Wilaya"));
            prstStdRes.setString(3,  ResMaxSqlsrv.getString("Address"));
            prstStdRes.setInt(4,ResMaxSqlsrv.getInt("Id_Nationalite"));
            prstStdRes.setInt(5, ResMaxSqlsrv.getInt("BacYear"));
            prstStdRes.setInt(6,ResMaxSqlsrv.getInt("Id_BranchStd"));
            prstStdRes.setInt(7, ResMaxSqlsrv.getInt("Id_LevelStudy"));
            prstStdRes.setString(8  , ResMaxSqlsrv.getString("Num_InscritBac"));
            prstStdRes.setString(9, ResMaxSqlsrv.getString("DateInscrp"));
              
            int xx=prstStdRes.executeUpdate();
            
             if (xx>0) {
              // JOptionPane.showMessageDialog(null, "Success Executed In Student Res ");
            }else JOptionPane.showMessageDialog(null, "Error in added ");
            }
    
   //  cnnct.commit();
     JOptionPane.showMessageDialog(null, "تم تحميل البيانات بنجاح ");
     
     
     } catch (SQLException e) {
         //JOptionPane.showMessageDialog(null, "حدث خطأ اثناء تحميل البيانات "+e.getMessage());
         e.printStackTrace();
         // cnnct.rollback();
         }finally{
                    //          cnnct.setAutoCommit(true);
                    //cnx.getConnect().setAutoCommit(true);

        }
        try {
        stmMaxSqlsrv.close();
        ResMaxSqlsrv.close();
//        prstStdRes.close();
        prstm.close();
        cnnct.close();
        cnx.Deconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }else {
            
            JOptionPane.showMessageDialog(null, "No Number Student Added");
            }       
    }
    public void Export_Data_To_AccessDB(){
    
   //     Statement stmInsert,stmCount = null;
    int MaxNumCardSqlSrv=0,MaxNumCardAccess=0;
     
    Statement stmMaxSqlsrv=null;
    ResultSet ResMaxSqlsrv=null;
    
    
     Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
    int year = cal.get(Calendar.YEAR);            
    String Address="بسكرة";
    int id_wil=7;
    int id_nat=1;
    
    File DB_Access=new File("D:\\Base.accdb");
    if (DB_Access.exists()) {
        /**********************To Calculate MAX NumCard_Resident In Sql Server ***************************/
        String Query_Max_IdSQLSrv="SELECT MAX (NumCard_Resident) FROM Resident_Gl WHERE Id_Ptrn_Res=1";
        cnx.Connecting();
        try {
            stmMaxSqlsrv=cnx.getConnect().createStatement();
            ResMaxSqlsrv=stmMaxSqlsrv.executeQuery(Query_Max_IdSQLSrv);
            if (ResMaxSqlsrv.next()) {
                MaxNumCardSqlSrv=ResMaxSqlsrv.getInt(1);
                System.out.println("MaxIdSqlSrv :"+MaxNumCardSqlSrv);
           }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmMaxSqlsrv.close();
            ResMaxSqlsrv.close();
            cnx.Deconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /***********************************************************************************************/
      /**************************To Calculate Max NumCard_Resident In Access  **************************/  
        String QueryMaxAccess="SELECT MAX (NumCard_Resident) FROM Resident_Gl WHERE Id_Ptrn_Res=1";   
        ResultSet ResMaxAccess=null;
        Statement StmMaxAccess=null;
        ConnectingAccess();
        try {
            StmMaxAccess=GetConnectAccess().createStatement();
            ResMaxAccess=StmMaxAccess.executeQuery(QueryMaxAccess);
            if (ResMaxAccess.next()) {
                MaxNumCardAccess=ResMaxAccess.getInt(1);
                System.out.println("MaxIdAccess :"+MaxNumCardAccess);
            }
         } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
           StmMaxAccess.close();
           ResMaxAccess.close();
            GetConnectAccess().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
            if (MaxNumCardSqlSrv>MaxNumCardAccess) {
                
 /*********************************Two QueryTo Insert Information*************************************/                
                String QueryInsertInAccess="INSERT INTO Resident_Gl (Name_Resident,LastName_Resident,NumCard_Resident,"
              + "CodeBare_Resident,"
            + "DateBirth,PlaceBirth,ID_gender,Id_Ptrn_Res,ID_Case_Resident,imageStd) "
                  + "VALUES (?,?,?,?,?,?,?,?,?,?)";
      
      
     String QueryInsertInAccessStdRes="INSERT INTO Student_Res (Name_Father,FullName_Mother,ProfessionFather,"
                   + "ProfessionMother,Address,ID_Wilaya,Name_Commune,Name_Daira"
                    + ",Id_Nationalite,SituationFamily,BacYear,BacMoyen,PlaceGetBac,Id_BranchStd,DateInscrp,"
                    + "Id_Faculty,Id_LevelStudy,Num_InscritBac,ID_Rsident,Id_Room,"
                     + "Name_ResidentFr,LastName_ResidentFr,PlaceBirthFr,Name_FatherFr,Name_MotherFr,LastName_MotheFr,LastNamMothAR) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                     + "?,?,?,?,?,?,?)";
      
/********************************************************************************************/      
      String QueryComplete="Select Resident_Gl.ID_Rsident,Name_Resident,LastName_Resident ,CodeBare_Resident,NumCard_Resident,DateBirth,PlaceBirth,Resident_Gl.ID_gender,Id_Ptrn_Res,"
              + "Num_InscritBac,Name_Father,FullName_Mother,ProfessionFather,ProfessionMother,\n" +
"Address,ID_Wilaya,Name_Daira,Name_Commune  ,Id_Nationalite,SituationFamily,BacYear,BacMoyen,PlaceGetBac,Id_BranchStd,DateInscrp,\n" +
"Id_Faculty,Id_LevelStudy, imageStd,ID_Case_Resident,\n" +
"Name_ResidentFr,LastName_ResidentFr,PlaceBirthFr,"
              + "Name_FatherFr,Name_MotherFr,LastName_MotheFr,LastNamMothAR\n" +
" FROM  Resident_Gl,Gender,Student_Res Where "
              + " ( Resident_Gl.NumCard_Resident> "+MaxNumCardAccess+" and Resident_Gl.NumCard_Resident <= "+ MaxNumCardSqlSrv +" )"+
" and Resident_Gl.ID_gender=Gender.ID_gender and Resident_Gl.ID_Rsident=Student_Res.ID_Rsident  and  Resident_Gl.Id_Ptrn_Res=1";
      
String QuerySlct="SELECT MAX(ID_Rsident) FROM Resident_Gl  WHERE Id_Ptrn_Res=1";      
      
/**********************************************************************************************/      
    
PreparedStatement prstStdRes=null; //To Insert Student
PreparedStatement prstm=null;      //To Insert Resident Gl

 cnx.Connecting(); //Connect SQLsever
 ConnectingAccess();// Connect Access 
 int ValMax=0 ;
  try {
/*************Execut Query To Get All Student Exit In SQLServer But Not Found In Accesss******/
    stmMaxSqlsrv=cnx.getConnect().createStatement();
    ResMaxSqlsrv=stmMaxSqlsrv.executeQuery(QueryComplete);
    System.out.println("Execute Query To Get Difference Data Between SQLserver And Access ");
    /*************To prepare The Two prepare Statement ***********************************/
    prstm=cnnct.prepareStatement(QueryInsertInAccess);
    prstStdRes=cnnct.prepareStatement(QueryInsertInAccessStdRes);
    while (ResMaxSqlsrv.next()) {
            prstm.setString(1, ResMaxSqlsrv.getString("Name_Resident"));
            
            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getString("Name_Resident"));
            prstm.setString(2, ResMaxSqlsrv.getString("LastName_Resident"));
            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getString("LastName_Resident"));
            // Numbre_CardRes=(Get_MAX_Numbre_CardRes(0)+1);
            prstm.setInt(3,ResMaxSqlsrv.getInt("NumCard_Resident")); //Corretct For TEsting 
            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getInt("NumCard_Resident"));
            //prstm.setInt(3,11219);
            prstm.setString(4 ,ResMaxSqlsrv.getString("CodeBare_Resident"));
            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getString("CodeBare_Resident"));
            prstm.setDate(5,ResMaxSqlsrv.getDate("DateBirth")   /*new java.sql.Date(getDateBirth().getTime())*/);
            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getDate("DateBirth"));
            prstm.setString(6, ResMaxSqlsrv.getString("PlaceBirth"));
            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getString("PlaceBirth"));
            prstm.setInt(7, ResMaxSqlsrv.getInt("ID_gender"));
            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getInt("ID_gender"));
            prstm.setInt(8,ResMaxSqlsrv.getInt("Id_Ptrn_Res"));
            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getInt("Id_Ptrn_Res"));
            prstm.setInt(9, ResMaxSqlsrv.getInt("ID_Case_Resident"));
            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getInt("ID_Case_Resident"));
            prstm.setBlob(10, ResMaxSqlsrv.getBlob("imageStd"));
            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getBinaryStream("imageStd"));
            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getInt(1));
            
            prstStdRes.setString(1, ResMaxSqlsrv.getString("Name_Father"));
            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getString("Name_Father"));
            prstStdRes.setString(2,  ResMaxSqlsrv.getString("FullName_Mother"));
            System.out.println("Inser Student In Access With Number : "+ ResMaxSqlsrv.getString("FullName_Mother"));
            prstStdRes.setString(3,ResMaxSqlsrv.getString("ProfessionFather"));
            System.out.println("Inser Student In Access With Number : "+ ResMaxSqlsrv.getString("ProfessionFather"));
            prstStdRes.setString(4, ResMaxSqlsrv.getString("ProfessionMother"));
            System.out.println("Inser Student In Access With Number : "+  ResMaxSqlsrv.getString("ProfessionMother"));
            prstStdRes.setString(5,ResMaxSqlsrv.getString("Address"));
            System.out.println("Inser Student In Access With Number : "+  ResMaxSqlsrv.getString("Address"));
            prstStdRes.setInt(6, ResMaxSqlsrv.getInt("ID_Wilaya"));
            System.out.println("Inser Student In Access With Number : "+  ResMaxSqlsrv.getInt("ID_Wilaya"));
            
            prstStdRes.setString(7  , ResMaxSqlsrv.getString("Name_Commune"));
            System.out.println("Inser Student In Access With Number : "+   ResMaxSqlsrv.getString("Name_Commune"));
            prstStdRes.setString(8, ResMaxSqlsrv.getString("Name_Daira"));
            System.out.println("Inser Student In Access With Number : "+   ResMaxSqlsrv.getString("Name_Daira"));
            prstStdRes.setInt(9, ResMaxSqlsrv.getInt("Id_Nationalite"));
            System.out.println("Inser Student In Access With Number : "+   ResMaxSqlsrv.getInt("Id_Nationalite"));
            prstStdRes.setString(10,  ResMaxSqlsrv.getString("SituationFamily"));
            System.out.println("Inser Student In Access With Number : "+   ResMaxSqlsrv.getString("SituationFamily"));
            prstStdRes.setInt(11,ResMaxSqlsrv.getInt("BacYear"));
            System.out.println("Inser Student In Access With Number : "+   ResMaxSqlsrv.getInt("BacYear"));
            prstStdRes.setDouble(12, ResMaxSqlsrv.getDouble("BacMoyen"));
            System.out.println("Inser Student In Access With Number : "+   ResMaxSqlsrv.getDouble("BacMoyen"));
            prstStdRes.setString(13, ResMaxSqlsrv.getString("PlaceGetBac"));
            System.out.println("Inser Student In Access With Number : "+   ResMaxSqlsrv.getString("PlaceGetBac"));
            prstStdRes.setInt(14, ResMaxSqlsrv.getInt("Id_BranchStd"));
            System.out.println("Inser Student In Access With Number : "+   ResMaxSqlsrv.getInt("Id_BranchStd"));
            prstStdRes.setDate(15,ResMaxSqlsrv.getDate("DateInscrp") /*new java.sql.Date(getDateInscrp().getTime())*/);
            System.out.println("Inser Student In Access With Number : "+   ResMaxSqlsrv.getDate("DateInscrp"));
            prstStdRes.setInt(16, ResMaxSqlsrv.getInt("Id_Faculty"));
            System.out.println("Inser Student In Access With Number : "+   ResMaxSqlsrv.getInt("Id_Faculty"));
            prstStdRes.setInt(17,ResMaxSqlsrv.getInt("Id_LevelStudy"));
            System.out.println("Inser Student In Access With Number : "+   ResMaxSqlsrv.getInt("Id_LevelStudy"));
            // prstm.setInt(17, getId_Room());
            prstStdRes.setString(18, ResMaxSqlsrv.getString("Num_InscritBac"));
            System.out.println("Inser Student In Access With Number : "+   ResMaxSqlsrv.getString("Num_InscritBac"));
           
            int x=prstm.executeUpdate();
            if (x>0) {
               //JOptionPane.showMessageDialog(null, "Success Executed In Global Res ");
            }else JOptionPane.showMessageDialog(null, "Error in added ");
            
           /*******************************************/        
            Statement stmX=cnnct.createStatement();
            ResultSet resX=stmX.executeQuery(QuerySlct);

            
            if (resX.next()) {
                ValMax=resX.getInt(1);
            }
             stmX=null;
            resX=null;
           /***************************************/ 
            
            prstStdRes.setInt(19, ValMax/*ResMaxSqlsrv.getInt("ID_Rsident")*/);
            System.out.println("Inser Student In Access With Number : "+    ResMaxSqlsrv.getInt("ID_Rsident"));
            prstStdRes.setInt(20, 20);
            System.out.println("Inser Student In Access With Number : "+    20);
            
            prstStdRes.setString(21, ResMaxSqlsrv.getString("Name_ResidentFr"));
            System.out.println("Inser Student In Access With Number : "+    ResMaxSqlsrv.getString("Name_ResidentFr"));
            prstStdRes.setString(22, ResMaxSqlsrv.getString("LastName_ResidentFr"));
             System.out.println("Inser Student In Access With Number : "+    ResMaxSqlsrv.getString("LastName_ResidentFr"));
            prstStdRes.setString(23, ResMaxSqlsrv.getString("PlaceBirthFr"));
            System.out.println("Inser Student In Access With Number : "+    ResMaxSqlsrv.getString("PlaceBirthFr"));
            prstStdRes.setString(24, ResMaxSqlsrv.getString("Name_FatherFr"));
            System.out.println("Inser Student In Access With Number : "+    ResMaxSqlsrv.getString("Name_FatherFr"));
            prstStdRes.setString(25, ResMaxSqlsrv.getString("Name_MotherFr"));
            System.out.println("Inser Student In Access With Number : "+   ResMaxSqlsrv.getString("Name_MotherFr"));
            prstStdRes.setString(26, ResMaxSqlsrv.getString("LastName_MotheFr"));
            System.out.println("Inser Student In Access With Number : "+   ResMaxSqlsrv.getString("LastName_MotheFr"));
            prstStdRes.setString(27, ResMaxSqlsrv.getString("LastNamMothAR"));
            System.out.println("Inser Student In Access With Number : "+   ResMaxSqlsrv.getString("LastNamMothAR"));  
              
            int xx=prstStdRes.executeUpdate();
            
             if (xx>0) {
              // JOptionPane.showMessageDialog(null, "Success Executed In Student Res ");
            }else JOptionPane.showMessageDialog(null, "Error in added ");
             }
    //JOptionPane.showMessageDialog(null, "تم تحميل البيانات بنجاح ");
     } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "حدث خطأ اثناء تحميل البيانات "+e.getMessage());
            // cnnct.rollback();
         }
    try {
        stmMaxSqlsrv.close();
        ResMaxSqlsrv.close();
        prstStdRes.close();
        prstm.close();
        cnnct.close();
        cnx.Deconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }else {
            
            JOptionPane.showMessageDialog(null, "No Number Student Added");
            }
            
            
     /******************************process to add in External Student Data In Access*****************/
        
         Query_Max_IdSQLSrv="SELECT MAX (NumCard_Resident) FROM Resident_Gl WHERE Id_Ptrn_Res=2";
         cnx.Connecting();
        try {
            stmMaxSqlsrv=cnx.getConnect().createStatement();
            ResMaxSqlsrv=stmMaxSqlsrv.executeQuery(Query_Max_IdSQLSrv);
            if (ResMaxSqlsrv.next()) {
                MaxNumCardSqlSrv=ResMaxSqlsrv.getInt(1);
                System.out.println("MaxIdSqlSrv :"+MaxNumCardSqlSrv);
           }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmMaxSqlsrv.close();
            ResMaxSqlsrv.close();
            cnx.Deconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /********************************To Calc Max In Acces External Student**********************************/
          QueryMaxAccess="SELECT MAX (NumCard_Resident) FROM Resident_Gl WHERE Id_Ptrn_Res=2";   
         ResMaxAccess=null;
         StmMaxAccess=null;
        ConnectingAccess();
        try {
            StmMaxAccess=GetConnectAccess().createStatement();
            ResMaxAccess=StmMaxAccess.executeQuery(QueryMaxAccess);
            if (ResMaxAccess.next()) {
                MaxNumCardAccess=ResMaxAccess.getInt(1);
                System.out.println("MaxIdAccess :"+MaxNumCardAccess);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        try {
           StmMaxAccess.close();
           ResMaxAccess.close();
            GetConnectAccess().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       /***************************************************************************************/ 
                  
            if (MaxNumCardSqlSrv>MaxNumCardAccess) {
                
 /*********************************Two QueryTo Insert Information*************************************/                
                String QueryInsertInAccess="INSERT INTO Resident_Gl (Name_Resident,LastName_Resident,NumCard_Resident,"
              + "CodeBare_Resident,"
            + "DateBirth,PlaceBirth,ID_gender,Id_Ptrn_Res,ID_Case_Resident,imageStd) "
                  + "VALUES (?,?,?,?,?,?,?,?,?,?)";
    String QueryInsertInAccessStdRes="INSERT INTO Student_ResExt (ID_Rsident,ID_Wilaya,Address, "
                   + "Id_Nationalite,BacYear,Id_BranchStd,Id_LevelStudy,Num_InscritBac "
                    + ",DateInscrp) "
                    + " VALUES (?,?,?,?,?,?,?,?,?)";
      
/********************************************************************************************/      
String  QueryComplete= "Select Resident_Gl.ID_Rsident,Name_Resident,LastName_Resident ,CodeBare_Resident,NumCard_Resident,\n" +
"DateBirth,PlaceBirth,Resident_Gl.ID_gender,Id_Ptrn_Res,\n" +
"ID_Wilaya,Address,Id_Nationalite,BacYear,Id_BranchStd,\n" +
"Id_LevelStudy,Num_InscritBac,DateInscrp,imageStd,ID_Case_Resident\n" +
"FROM  Resident_Gl,Gender,Student_ResExt Where \n" +
"( Resident_Gl.NumCard_Resident>  "+MaxNumCardAccess+" and Resident_Gl.NumCard_Resident <=  "+MaxNumCardSqlSrv+"  )\n" +
"and Resident_Gl.ID_gender=Gender.ID_gender \n" +
"and Resident_Gl.ID_Rsident=Student_ResExt.ID_Rsident  \n" +
"and  Resident_Gl.Id_Ptrn_Res=2";
String QuerySlct="SELECT MAX(ID_Rsident) FROM Resident_Gl  WHERE Id_Ptrn_Res=2";      
/**********************************************************************************************/      
PreparedStatement prstStdRes=null; //To Insert Student
PreparedStatement prstm=null;      //To Insert Resident Gl

 cnx.Connecting(); //Connect SQLsever
 ConnectingAccess();// Connect Access 
 int ValMax=0 ;
  try {
  /*************Execut Query To Get All Student Exit In SQLServer But Not Found In Accesss******/
    stmMaxSqlsrv=cnx.getConnect().createStatement();
    ResMaxSqlsrv=stmMaxSqlsrv.executeQuery(QueryComplete);
    System.out.println("Execute Query To Get Difference Data Between SQLserver And Access ");
    /*************To prepare The Two prepare Statement ***********************************/
    prstm=cnnct.prepareStatement(QueryInsertInAccess);
    prstStdRes=cnnct.prepareStatement(QueryInsertInAccessStdRes);
     while (ResMaxSqlsrv.next()) {
            prstm.setString(1, ResMaxSqlsrv.getString("Name_Resident"));
//            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getString("Name_Resident"));
            prstm.setString(2, ResMaxSqlsrv.getString("LastName_Resident"));
//            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getString("LastName_Resident"));
            // Numbre_CardRes=(Get_MAX_Numbre_CardRes(0)+1);
            prstm.setInt(3,ResMaxSqlsrv.getInt("NumCard_Resident")); //Corretct For TEsting 
//            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getInt("NumCard_Resident"));
            //prstm.setInt(3,11219);
            prstm.setString(4 ,ResMaxSqlsrv.getString("CodeBare_Resident"));
//            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getString("CodeBare_Resident"));
            prstm.setDate(5,ResMaxSqlsrv.getDate("DateBirth")   /*new java.sql.Date(getDateBirth().getTime())*/);
//            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getDate("DateBirth"));
            prstm.setString(6, ResMaxSqlsrv.getString("PlaceBirth"));
//            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getString("PlaceBirth"));
            prstm.setInt(7, ResMaxSqlsrv.getInt("ID_gender"));
//            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getInt("ID_gender"));
            prstm.setInt(8,ResMaxSqlsrv.getInt("Id_Ptrn_Res"));
//            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getInt("Id_Ptrn_Res"));
            prstm.setInt(9, ResMaxSqlsrv.getInt("ID_Case_Resident"));
//            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getInt("ID_Case_Resident"));
            prstm.setBlob(10, ResMaxSqlsrv.getBlob("imageStd"));
            prstm.executeUpdate();
                 
            Statement stmX=cnnct.createStatement();
            ResultSet resX=stmX.executeQuery(QuerySlct);
            if (resX.next()) {
                ValMax=resX.getInt(1);
            }
             stmX=null;
            resX=null;
           /***************************************/ 
            prstStdRes.setInt(1, ValMax/*ResMaxSqlsrv.getInt("ID_Rsident")*/);
            System.out.println("ValMax");
            prstStdRes.setInt(2, ResMaxSqlsrv.getInt("ID_Wilaya"));
            System.out.println(ResMaxSqlsrv.getInt("ID_Wilaya"));
            prstStdRes.setString(3,  ResMaxSqlsrv.getString("Address"));
            System.out.println(ResMaxSqlsrv.getString("Address"));
            prstStdRes.setInt(4,ResMaxSqlsrv.getInt("Id_Nationalite"));
            System.out.println(ResMaxSqlsrv.getInt("Id_Nationalite"));
            prstStdRes.setInt(5, ResMaxSqlsrv.getInt("BacYear"));
            System.out.println(ResMaxSqlsrv.getInt("BacYear"));
            prstStdRes.setInt(6,ResMaxSqlsrv.getInt("Id_BranchStd"));
            System.out.println(ResMaxSqlsrv.getInt("Id_BranchStd"));
            prstStdRes.setInt(7, ResMaxSqlsrv.getInt("Id_LevelStudy"));
            System.out.println(ResMaxSqlsrv.getInt("Id_LevelStudy"));
            prstStdRes.setString(8  , ResMaxSqlsrv.getString("Num_InscritBac"));
            System.out.println(ResMaxSqlsrv.getString("Num_InscritBac"));
            prstStdRes.setString(9, ResMaxSqlsrv.getString("DateInscrp"));
            System.out.println(ResMaxSqlsrv.getString("DateInscrp"));
            prstStdRes.executeUpdate();
     
            }
      System.out.println("تم تحميل البيانات للطلبة الخارجيين ");
     } catch (SQLException e) {
         //JOptionPane.showMessageDialog(null, "حدث خطأ اثناء تحميل البيانات "+e.getMessage());
         System.out.println("حدث خطأ اثناء تحميل البيانات");
         // cnnct.rollback();
         }
        try {
        stmMaxSqlsrv.close();
        ResMaxSqlsrv.close();
        prstStdRes.close();
        prstm.close();
        cnnct.close();
        cnx.Deconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }else {
                //JOptionPane.showMessageDialog(null, "No Number Student Added");
                System.out.println("No Number Student Added");
            }        
   /*********************************Process To Put Data Of Employer********************************/
   Query_Max_IdSQLSrv="SELECT MAX (NumCard_Resident) FROM Resident_Gl WHERE Id_Ptrn_Res=4";
         cnx.Connecting();
        try {
            stmMaxSqlsrv=cnx.getConnect().createStatement();
            ResMaxSqlsrv=stmMaxSqlsrv.executeQuery(Query_Max_IdSQLSrv);
            if (ResMaxSqlsrv.next()) {
                MaxNumCardSqlSrv=ResMaxSqlsrv.getInt(1);
                System.out.println("MaxIdSqlSrv :"+MaxNumCardSqlSrv);
           }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmMaxSqlsrv.close();
            ResMaxSqlsrv.close();
            cnx.Deconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /********************************To Calc Max In Acces Employer**********************************/
          QueryMaxAccess="SELECT MAX (NumCard_Resident) FROM Resident_Gl WHERE Id_Ptrn_Res=4";   
         ResMaxAccess=null;
         StmMaxAccess=null;
        ConnectingAccess();
        try {
            StmMaxAccess=GetConnectAccess().createStatement();
            ResMaxAccess=StmMaxAccess.executeQuery(QueryMaxAccess);
            if (ResMaxAccess.next()) {
                MaxNumCardAccess=ResMaxAccess.getInt(1);
                System.out.println("MaxIdAccess :"+MaxNumCardAccess);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
           StmMaxAccess.close();
           ResMaxAccess.close();
            GetConnectAccess().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
 /**********************************************************************************/       
        if (MaxNumCardSqlSrv>MaxNumCardAccess) {               
                String QueryInsertInAccess="INSERT INTO Resident_Gl (Name_Resident,LastName_Resident,NumCard_Resident,"
              + "CodeBare_Resident,"
            + "DateBirth,PlaceBirth,ID_gender,Id_Ptrn_Res,ID_Case_Resident,imageStd) "
                  + "VALUES (?,?,?,?,?,?,?,?,?,?)";
     String QueryInsertInAccessStdRes="INSERT INTO Employer (ID_Rsident,ID_Profession) VALUES (?,?)";
    String  QueryComplete= "Select Resident_Gl.ID_Rsident,Name_Resident,LastName_Resident ,CodeBare_Resident,NumCard_Resident,\n" +
"DateBirth,PlaceBirth,Resident_Gl.ID_gender,Id_Ptrn_Res, imageStd,ID_Case_Resident,ID_Profession \n" +
"FROM  Resident_Gl,Gender,Employer Where \n" +
"( Resident_Gl.NumCard_Resident>  "+MaxNumCardAccess+" and Resident_Gl.NumCard_Resident <=  "+MaxNumCardSqlSrv+"  )\n" +
"and Resident_Gl.ID_gender=Gender.ID_gender \n" +
"and Resident_Gl.ID_Rsident=Employer.ID_Rsident  \n" +
"and  Resident_Gl.Id_Ptrn_Res=4";
String QuerySlct="SELECT MAX(ID_Rsident) FROM Resident_Gl  WHERE Id_Ptrn_Res=4";      
/**********************************************************************************************/      
PreparedStatement prstStdRes=null; //To Insert Student
PreparedStatement prstm=null;      //To Insert Resident Gl

 cnx.Connecting(); //Connect SQLsever
 ConnectingAccess();// Connect Access 
 int ValMax=0 ;
  try {
    /*************Execut Query To Get All Student Exit In SQLServer But Not Found In Accesss******/
    stmMaxSqlsrv=cnx.getConnect().createStatement();
    ResMaxSqlsrv=stmMaxSqlsrv.executeQuery(QueryComplete);
    System.out.println("Execute Query To Get Difference Data Between SQLserver And Access ");
    /*************To prepare The Two prepare Statement ***********************************/
    prstm=cnnct.prepareStatement(QueryInsertInAccess);
    prstStdRes=cnnct.prepareStatement(QueryInsertInAccessStdRes);
    while (ResMaxSqlsrv.next()) {
            prstm.setString(1, ResMaxSqlsrv.getString("Name_Resident"));
            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getString("Name_Resident"));
            prstm.setString(2, ResMaxSqlsrv.getString("LastName_Resident"));
            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getString("LastName_Resident"));
            // Numbre_CardRes=(Get_MAX_Numbre_CardRes(0)+1);
            prstm.setInt(3,ResMaxSqlsrv.getInt("NumCard_Resident")); //Corretct For TEsting 
            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getInt("NumCard_Resident"));
            //prstm.setInt(3,11219);
            prstm.setString(4 ,ResMaxSqlsrv.getString("CodeBare_Resident"));
            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getString("CodeBare_Resident"));
            prstm.setDate(5,ResMaxSqlsrv.getDate("DateBirth")   /*new java.sql.Date(getDateBirth().getTime())*/);
            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getDate("DateBirth"));
            prstm.setString(6, ResMaxSqlsrv.getString("PlaceBirth"));
            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getString("PlaceBirth"));
            prstm.setInt(7, ResMaxSqlsrv.getInt("ID_gender"));
            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getInt("ID_gender"));
            prstm.setInt(8,ResMaxSqlsrv.getInt("Id_Ptrn_Res"));
            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getInt("Id_Ptrn_Res"));
            prstm.setInt(9, ResMaxSqlsrv.getInt("ID_Case_Resident"));
            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getInt("ID_Case_Resident"));
            prstm.setBlob(10, ResMaxSqlsrv.getBlob("imageStd"));
            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getBinaryStream("imageStd"));
            System.out.println("Inser Student In Access With Number : "+ResMaxSqlsrv.getInt(1));
   
            int x=prstm.executeUpdate();
            Statement stmX=cnnct.createStatement();
            ResultSet resX=stmX.executeQuery(QuerySlct);
            if (resX.next()) {
                ValMax=resX.getInt(1);
            }
             stmX=null;
            resX=null;
            prstStdRes.setInt(1, ValMax/*ResMaxSqlsrv.getInt("ID_Rsident")*/);
            prstStdRes.setInt(2, ResMaxSqlsrv.getInt("ID_Profession"));
            int xx=prstStdRes.executeUpdate();
            }
       System.out.println("تم تحميل البيانات  للعمال بنجاح");
     } catch (SQLException e) {
          System.out.println("حدث خطأ اثناء تحميل البيانات "+e.getMessage());
                    }
        try {
        stmMaxSqlsrv.close();
        ResMaxSqlsrv.close();
        prstStdRes.close();
        prstm.close();
        cnnct.close();
        cnx.Deconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        JOptionPane.showMessageDialog(null, "تم تحميل البيانات بنجاح");
        
    }else {
               JOptionPane.showMessageDialog(null, "لا يوجد طلبة يتم تحميلهم");
                
            }        
     }else{
           JOptionPane.showMessageDialog(null, "لم يتم العثـور علي ملف التحميل");
        }
      }
  /*****************************************************************************************/  
    public void Import_Data_ToSqlServer(){
      ConnectionDB cnx=new ConnectionDB();
   //     Statement stmInsert,stmCount = null;
    int MaxNumCardSqlSrv=0,MaxNumCardAccess=0;
      Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
    int year = cal.get(Calendar.YEAR);            
    String Address="بسكرة";
    int id_wil=7;
    int id_nat=1;
    
     File DB_Access=new File("D:\\Base.accdb");
    
    
        if (DB_Access.exists()) {
        /***********************************************************************/
        String Query_Max_IdSQLSrv="SELECT MAX (NumCard_Resident) FROM Resident_Gl WHERE Id_Ptrn_Res=1";
        Statement stmMaxSqlsrv=null;
        ResultSet ResMaxSqlsrv=null;
        cnx.Connecting();
        try {
            stmMaxSqlsrv=cnx.getConnect().createStatement();
            ResMaxSqlsrv=stmMaxSqlsrv.executeQuery(Query_Max_IdSQLSrv);
            if (ResMaxSqlsrv.next()) {
                MaxNumCardSqlSrv=ResMaxSqlsrv.getInt(1);
                System.out.println("MaxIdSqlSrv :"+MaxNumCardSqlSrv);
           }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmMaxSqlsrv.close();
            ResMaxSqlsrv.close();
            cnx.Deconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
      /********************************************************************************/  
        String QueryMaxAccess="SELECT MAX (NumCard_Resident) FROM Resident_Gl WHERE Id_Ptrn_Res=1";   
        ResultSet ResMaxAccess=null;
        Statement StmMaxAccess=null;
        ConnectingAccess();
        try {
            StmMaxAccess=GetConnectAccess().createStatement();
            ResMaxAccess=StmMaxAccess.executeQuery(QueryMaxAccess);
            if (ResMaxAccess.next()) {
                MaxNumCardAccess=ResMaxAccess.getInt(1);
                System.out.println("MaxIdAccess :"+MaxNumCardAccess);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        try {
           StmMaxAccess.close();
           ResMaxAccess.close();
            GetConnectAccess().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
 if (MaxNumCardAccess>MaxNumCardSqlSrv) {
            
        PreparedStatement prstm=null;
     String QueryInsertInAccess="INSERT INTO Resident_Gl (Name_Resident,LastName_Resident,NumCard_Resident,"
              + "CodeBare_Resident,"
            + "DateBirth,PlaceBirth,ID_gender,Id_Ptrn_Res,ID_Case_Resident,imageStd) "
                  + "VALUES (?,?,?,?,?,?,?,?,?,?)";
      
      
     String QueryInsertInAccessStdRes="INSERT INTO Student_Res (Name_Father,FullName_Mother,ProfessionFather,"
                   + "ProfessionMother,Address,ID_Wilaya,Name_Commune,Name_Daira"
                    + ",Id_Nationalite,SituationFamily,BacYear,BacMoyen,PlaceGetBac,Id_BranchStd,DateInscrp,"
                    + "Id_Faculty,Id_LevelStudy,Num_InscritBac,ID_Rsident,Id_Room,"
                     + "Name_ResidentFr,LastName_ResidentFr,PlaceBirthFr,Name_FatherFr,Name_MotherFr,LastName_MotheFr,LastNamMothAR) "
                    + "VALUES "
                                + "(?,?,?,?,?,?,?,?,?,?,"
                                + "?,?,?,?,?,?,?,"
                                + " ?,?,?,?,?,?,?,?,?,?)";
/********************************************************************************************/      
       String QueryComplete="Select Resident_Gl.ID_Rsident,Name_Resident,LastName_Resident ,CodeBare_Resident,NumCard_Resident,DateBirth,PlaceBirth,Resident_Gl.ID_gender,Id_Ptrn_Res,"
              + "Num_InscritBac,Name_Father,FullName_Mother,ProfessionFather,ProfessionMother,\n" +
"Address,ID_Wilaya,Name_Daira,Name_Commune  ,Id_Nationalite,SituationFamily,BacYear,BacMoyen,PlaceGetBac,Id_BranchStd,DateInscrp,\n" +
"Id_Faculty,Id_LevelStudy, imageStd,ID_Case_Resident,\n" +
"Name_ResidentFr,LastName_ResidentFr,PlaceBirthFr,"
              + "Name_FatherFr,Name_MotherFr,LastName_MotheFr,LastNamMothAR\n" +
" FROM  Resident_Gl,Student_Res Where "
              + " ( Resident_Gl.NumCard_Resident> "+MaxNumCardSqlSrv+" and Resident_Gl.NumCard_Resident <= "+ MaxNumCardAccess +" )"+
"  and Resident_Gl.ID_Rsident=Student_Res.ID_Rsident  and  Resident_Gl.Id_Ptrn_Res=1";
/**********************************************************************************************/      
Statement StmGetAllStd_ResGl=null/*,StmGetAllStd_Std=null*/;
ResultSet ResGetAllStd_ResGl=null/*,ResGetAllStd_Std=null*/;
PreparedStatement prstStdRes=null;
ConnectingAccess();
cnx.Connecting();
try {
    StmGetAllStd_ResGl=cnnct.createStatement();
    ResGetAllStd_ResGl=StmGetAllStd_ResGl.executeQuery(QueryComplete);
    System.out.println("Execute Query To Get Difference Data Between SQLserver And Access "); 
    prstm=cnx.getConnect().prepareStatement(QueryInsertInAccess);
    prstStdRes=cnx.getConnect().prepareStatement(QueryInsertInAccessStdRes);
    System.out.println("Execute Query To prepare To Inser Data in Access "); 
    String QuerySlct="SELECT MAX(ID_Rsident) FROM Resident_Gl";
         int ValMax=0 ;
     while (ResGetAllStd_ResGl.next()) {
            //ResGetAllStd_Std.next();
          System.out.println("Data Of Student ******************************************");
            prstm.setString(1, ResGetAllStd_ResGl.getString("Name_Resident"));
            
            System.out.println("Name_Resident : "+ResGetAllStd_ResGl.getString("Name_Resident"));
            prstm.setString(2, ResGetAllStd_ResGl.getString("LastName_Resident"));
            System.out.println("LastName_Resident : "+ResGetAllStd_ResGl.getString("LastName_Resident"));
            // Numbre_CardRes=(Get_MAX_Numbre_CardRes(0)+1);
            prstm.setInt(3,ResGetAllStd_ResGl.getInt("NumCard_Resident")); //Corretct For TEsting 
            System.out.println("NumCard_Resident : "+ResGetAllStd_ResGl.getInt("NumCard_Resident"));
            //prstm.setInt(3,11219);
            prstm.setString(4 ,ResGetAllStd_ResGl.getString("CodeBare_Resident"));
            System.out.println("CodeBare_Resident : "+ResGetAllStd_ResGl.getString("CodeBare_Resident"));
            prstm.setDate(5,ResGetAllStd_ResGl.getDate("DateBirth")   /*new java.sql.Date(getDateBirth().getTime())*/);
            System.out.println("DateBirth : "+ResGetAllStd_ResGl.getDate("DateBirth"));
            prstm.setString(6, ResGetAllStd_ResGl.getString("PlaceBirth"));
            System.out.println("PlaceBirth : "+ResGetAllStd_ResGl.getString("PlaceBirth"));
            prstm.setInt(7, ResGetAllStd_ResGl.getInt("ID_gender"));
            System.out.println("ID_gender : "+ResGetAllStd_ResGl.getInt("ID_gender"));
            prstm.setInt(8,ResGetAllStd_ResGl.getInt("Id_Ptrn_Res"));
            System.out.println("Id_Ptrn_Res: "+ResGetAllStd_ResGl.getInt("Id_Ptrn_Res"));
            prstm.setInt(9, ResGetAllStd_ResGl.getInt("ID_Case_Resident"));
            System.out.println("ID_Case_Resident: "+ResGetAllStd_ResGl.getInt("ID_Case_Resident"));
            prstm.setBlob(10, ResGetAllStd_ResGl.getBlob("imageStd"));
            System.out.println("imageStd : "+ResGetAllStd_ResGl.getBinaryStream("imageStd"));
            //System.out.println("Inser Student In Access With Number : "+ResGetAllStd_ResGl.getInt(1));
            
            prstStdRes.setString(1, ResGetAllStd_ResGl.getString("Name_Father"));
            System.out.println("Name_Father : "+ResGetAllStd_ResGl.getString("Name_Father"));
            prstStdRes.setString(2,  ResGetAllStd_ResGl.getString("FullName_Mother"));
            System.out.println("FullName_Mother : "+  ResGetAllStd_ResGl.getString("FullName_Mother"));
            prstStdRes.setString(3,ResGetAllStd_ResGl.getString("ProfessionFather"));
            System.out.println("ProfessionFather : "+ ResGetAllStd_ResGl.getString("ProfessionFather"));
            prstStdRes.setString(4, ResGetAllStd_ResGl.getString("ProfessionMother"));
            System.out.println("ProfessionMother : "+  ResGetAllStd_ResGl.getString("ProfessionMother"));
            prstStdRes.setString(5,ResGetAllStd_ResGl.getString("Address"));
            System.out.println("Address : "+  ResGetAllStd_ResGl.getString("Address"));
            prstStdRes.setInt(6, ResGetAllStd_ResGl.getInt("ID_Wilaya"));
            System.out.println("ID_Wilaya : "+  ResGetAllStd_ResGl.getInt("ID_Wilaya"));
            
            prstStdRes.setString(7  , ResGetAllStd_ResGl.getString("Name_Commune"));
            System.out.println("Name_Commune : "+   ResGetAllStd_ResGl.getString("Name_Commune"));
            prstStdRes.setString(8, ResGetAllStd_ResGl.getString("Name_Daira"));
            System.out.println("Name_Daira : "+   ResGetAllStd_ResGl.getString("Name_Daira"));
            prstStdRes.setInt(9, ResGetAllStd_ResGl.getInt("Id_Nationalite"));
            System.out.println("Id_Nationalite : "+    ResGetAllStd_ResGl.getInt("Id_Nationalite"));
            prstStdRes.setString(10,  ResGetAllStd_ResGl.getString("SituationFamily"));
            System.out.println("SituationFamily : "+   ResGetAllStd_ResGl.getString("SituationFamily"));
            prstStdRes.setInt(11,ResGetAllStd_ResGl.getInt("BacYear"));
            System.out.println("BacYear : "+   ResGetAllStd_ResGl.getInt("BacYear"));
            prstStdRes.setDouble(12, ResGetAllStd_ResGl.getDouble("BacMoyen"));
            System.out.println("BacMoyen : "+   ResGetAllStd_ResGl.getDouble("BacMoyen"));
            prstStdRes.setString(13, ResGetAllStd_ResGl.getString("PlaceGetBac"));
            System.out.println("PlaceGetBac : "+   ResGetAllStd_ResGl.getString("PlaceGetBac"));
            prstStdRes.setInt(14, ResGetAllStd_ResGl.getInt("Id_BranchStd"));
            System.out.println("Id_BranchStd : "+   ResGetAllStd_ResGl.getInt("Id_BranchStd"));
            prstStdRes.setDate(15,ResGetAllStd_ResGl.getDate("DateInscrp") /*new java.sql.Date(getDateInscrp().getTime())*/);
            System.out.println("DateInscrp : "+   ResGetAllStd_ResGl.getDate("DateInscrp"));
            prstStdRes.setInt(16, ResGetAllStd_ResGl.getInt("Id_Faculty"));
            System.out.println("Id_Faculty : "+   ResGetAllStd_ResGl.getInt("Id_Faculty"));
            prstStdRes.setInt(17,ResGetAllStd_ResGl.getInt("Id_LevelStudy"));
            System.out.println("Id_LevelStudy : "+   ResGetAllStd_ResGl.getInt("Id_LevelStudy"));
            // prstm.setInt(17, getId_Room());
            prstStdRes.setString(18, ResGetAllStd_ResGl.getString("Num_InscritBac"));
            prstm.executeUpdate();  
            
            Statement stmX=cnx.getConnect().createStatement();
            ResultSet resX=stmX.executeQuery(QuerySlct);
            if (resX.next()) {
                ValMax=resX.getInt(1);
            }
             stmX=null;
            resX=null;
           /***************************************/
            prstStdRes.setInt(19, ValMax);
            System.out.println("Inser Student In Access With Number : "+    ResGetAllStd_ResGl.getInt("ID_Rsident"));
            prstStdRes.setInt(20, 20);
            System.out.println("Inser Student In Access With Number : "+    20);
            
            prstStdRes.setString(21, ResGetAllStd_ResGl.getString("Name_ResidentFr"));
            System.out.println("Name_ResidentFr : "+   ResGetAllStd_ResGl.getString("Name_ResidentFr"));
            
            prstStdRes.setString(22, ResGetAllStd_ResGl.getString("LastName_ResidentFr"));
            System.out.println("LastName_ResidentFr : "+   ResGetAllStd_ResGl.getString("LastName_ResidentFr"));
            
            prstStdRes.setString(23, ResGetAllStd_ResGl.getString("PlaceBirthFr"));
            System.out.println("PlaceBirthFr : "+    ResGetAllStd_ResGl.getString("PlaceBirthFr"));
            
            prstStdRes.setString(24, ResGetAllStd_ResGl.getString("Name_FatherFr"));
            System.out.println("Name_FatherFr : "+    ResGetAllStd_ResGl.getString("Name_FatherFr"));
            
            prstStdRes.setString(25, ResGetAllStd_ResGl.getString("Name_MotherFr"));
            System.out.println("Name_MotherFr : "+   ResGetAllStd_ResGl.getString("Name_MotherFr"));
            prstStdRes.setString(26, ResGetAllStd_ResGl.getString("LastName_MotheFr"));
            System.out.println("LastName_MotheFr : "+   ResGetAllStd_ResGl.getString("LastName_MotheFr"));
            prstStdRes.setString(27, ResGetAllStd_ResGl.getString("LastNamMothAR"));
            System.out.println("LastNamMothAR : "+   ResGetAllStd_ResGl.getString("LastNamMothAR"));   
            prstStdRes.executeUpdate(); 
            }
          System.out.println("تم تحميل البيانات بنجاح للطلبة");
          //JOptionPane.showMessageDialog(null, "تم تحميل البيانات بنجاح للطلبة");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "حدث خطأ في عملية التحميل "+e.getMessage());
                      e.printStackTrace();
        }
       try {
        stmMaxSqlsrv.close();
        ResMaxSqlsrv.close();
        prstStdRes.close();
        prstm.close();
        
        cnnct.close();
        cnx.Deconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
    
    /******************************Process Import Data Of External Student Data*********************************/
    
      Query_Max_IdSQLSrv="SELECT MAX (NumCard_Resident) FROM Resident_Gl WHERE Id_Ptrn_Res=2";
         stmMaxSqlsrv=null;
         ResMaxSqlsrv=null;
        
        cnx.Connecting();
        try {
            stmMaxSqlsrv=cnx.getConnect().createStatement();
            ResMaxSqlsrv=stmMaxSqlsrv.executeQuery(Query_Max_IdSQLSrv);
            if (ResMaxSqlsrv.next()) {
                MaxNumCardSqlSrv=ResMaxSqlsrv.getInt(1);
                System.out.println("MaxIdSqlSrv :"+MaxNumCardSqlSrv);
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmMaxSqlsrv.close();
            ResMaxSqlsrv.close();
            cnx.Deconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
      /********************************************************************************/  
        QueryMaxAccess="SELECT MAX (NumCard_Resident) FROM Resident_Gl WHERE Id_Ptrn_Res=2";   
         ResMaxAccess=null;
         StmMaxAccess=null;
        ConnectingAccess();
        try {
            StmMaxAccess=GetConnectAccess().createStatement();
            ResMaxAccess=StmMaxAccess.executeQuery(QueryMaxAccess);
            if (ResMaxAccess.next()) {
                MaxNumCardAccess=ResMaxAccess.getInt(1);
                System.out.println("MaxIdAccess :"+MaxNumCardAccess);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
           StmMaxAccess.close();
           ResMaxAccess.close();
            GetConnectAccess().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
         if (MaxNumCardAccess>MaxNumCardSqlSrv) {
          /****************************Insert Into SQL Server****************************/
PreparedStatement     prstm=null;
   String QueryInsertInAccess="INSERT INTO Resident_Gl (Name_Resident,LastName_Resident,NumCard_Resident,"
              + "CodeBare_Resident,"
            + "DateBirth,PlaceBirth,ID_gender,Id_Ptrn_Res,ID_Case_Resident,imageStd) "
                  + "VALUES (?,?,?,?,?,?,?,?,?,?)";
      
      
   String QueryInsertInAccessStdRes="INSERT INTO Student_ResExt (ID_Rsident,ID_Wilaya,Address, "
                   + "Id_Nationalite,BacYear,Id_BranchStd,Id_LevelStudy,Num_InscritBac "
                    + ",DateInscrp) "
                    + " VALUES (?,?,?,?,?,?,?,?,?)";
/********************************************************************************************/      
        String QueryComplete="Select Name_Resident,LastName_Resident ,CodeBare_Resident,NumCard_Resident,\n" +
"DateBirth,PlaceBirth,Resident_Gl.ID_gender,Id_Ptrn_Res,\n" +
 "ID_Wilaya,Address,Id_Nationalite,BacYear,Id_BranchStd,\n" +
"Id_LevelStudy,Num_InscritBac,DateInscrp,imageStd,ID_Case_Resident\n" +
"FROM  Resident_Gl,Student_ResExt Where \n" +
"( Resident_Gl.NumCard_Resident>  "+MaxNumCardSqlSrv+" and Resident_Gl.NumCard_Resident <=  "+MaxNumCardAccess+"  )\n" +
"and Resident_Gl.ID_Rsident=Student_ResExt.ID_Rsident  \n" +
"and  Resident_Gl.Id_Ptrn_Res=2";
/**************************************************************************************/
 Statement StmGetAllStd_ResGl=null/*,StmGetAllStd_Std=null*/;
 ResultSet ResGetAllStd_ResGl=null/*,ResGetAllStd_Std=null*/;
 PreparedStatement prstStdRes=null;
ConnectingAccess();
cnx.Connecting();
try {
    StmGetAllStd_ResGl=cnnct.createStatement();
    ResGetAllStd_ResGl=StmGetAllStd_ResGl.executeQuery(QueryComplete);
    System.out.println("Execute Query To Get Difference Data Between SQLserver And Access ");
 
    prstm=cnx.getConnect().prepareStatement(QueryInsertInAccess);
    prstStdRes=cnx.getConnect().prepareStatement(QueryInsertInAccessStdRes);
   
    String QuerySlct="SELECT MAX(ID_Rsident) FROM Resident_Gl";
         int ValMax=0 ;
    while (ResGetAllStd_ResGl.next()) {
        System.out.println("Case Of External Student ************************************************");
            prstm.setString(1, ResGetAllStd_ResGl.getString("Name_Resident"));
            
            System.out.println("Name_Resident : "+ResGetAllStd_ResGl.getString("Name_Resident"));
            prstm.setString(2, ResGetAllStd_ResGl.getString("LastName_Resident"));
            System.out.println("LastName_Resident : "+ResGetAllStd_ResGl.getString("LastName_Resident"));
            // Numbre_CardRes=(Get_MAX_Numbre_CardRes(0)+1);
            prstm.setInt(3,ResGetAllStd_ResGl.getInt("NumCard_Resident")); //Corretct For TEsting 
            System.out.println("NumCard_Resident : "+ResGetAllStd_ResGl.getInt("NumCard_Resident"));
            //prstm.setInt(3,11219);
            prstm.setString(4 ,ResGetAllStd_ResGl.getString("CodeBare_Resident"));
            System.out.println("CodeBare_Resident : "+ResGetAllStd_ResGl.getString("CodeBare_Resident"));
            prstm.setDate(5,ResGetAllStd_ResGl.getDate("DateBirth")   /*new java.sql.Date(getDateBirth().getTime())*/);
            System.out.println("DateBirth : "+ResGetAllStd_ResGl.getDate("DateBirth"));
            prstm.setString(6, ResGetAllStd_ResGl.getString("PlaceBirth"));
            System.out.println("PlaceBirth : "+ResGetAllStd_ResGl.getString("PlaceBirth"));
            prstm.setInt(7, ResGetAllStd_ResGl.getInt("ID_gender"));
            System.out.println("ID_gender : "+ResGetAllStd_ResGl.getInt("ID_gender"));
            prstm.setInt(8,ResGetAllStd_ResGl.getInt("Id_Ptrn_Res"));
            System.out.println("Id_Ptrn_Res: "+ResGetAllStd_ResGl.getInt("Id_Ptrn_Res"));
            prstm.setInt(9, ResGetAllStd_ResGl.getInt("ID_Case_Resident"));
            System.out.println("ID_Case_Resident: "+ResGetAllStd_ResGl.getInt("ID_Case_Resident"));
            prstm.setBlob(10, ResGetAllStd_ResGl.getBlob("imageStd"));
            System.out.println("Inser Student In Access With Number : "+ResGetAllStd_ResGl.getBinaryStream("imageStd"));
           // System.out.println("Inser Student In Access With Number : "+ResGetAllStd_ResGl.getInt(1));
            prstm.executeUpdate();
            System.out.println("Successful insert Data Of Residen_Gl ****");
            
            Statement stmX=cnx.getConnect().createStatement();
            ResultSet resX=stmX.executeQuery(QuerySlct);
            System.out.println("Successful Execut Request Query ****");
            if (resX.next()) {
                ValMax=resX.getInt(1);
                System.out.println("The Max Id In SQL Server Resident_Gl : "+ValMax);
            }
            stmX=null;
            resX=null;
            prstStdRes.setInt(1, ValMax);
            
            System.out.println("ValMax : "+ValMax);
            
           if (ResGetAllStd_ResGl.getInt("ID_Wilaya")==0){ prstStdRes.setInt(2,  7);}
           else prstStdRes.setInt(2,  ResGetAllStd_ResGl.getInt("ID_Wilaya")); 
            System.out.println("ID_Wilaya: "+ ResGetAllStd_ResGl.getInt("ID_Wilaya"));
            
            prstStdRes.setString(3,ResGetAllStd_ResGl.getString("Address"));
            System.out.println("Address : "+ ResGetAllStd_ResGl.getString("Address"));
            
            if(ResGetAllStd_ResGl.getInt("Id_Nationalite")==0){prstStdRes.setInt(4, 1);}else prstStdRes.setInt(4, ResGetAllStd_ResGl.getInt("Id_Nationalite"));
            System.out.println("Id_Nationalite : "+  ResGetAllStd_ResGl.getInt("Id_Nationalite"));
            
            if (ResGetAllStd_ResGl.getInt("BacYear")==0){prstStdRes.setInt(5,year);}else prstStdRes.setInt(5,ResGetAllStd_ResGl.getInt("BacYear"));
           System.out.println("BacYear : "+  ResGetAllStd_ResGl.getInt("BacYear"));
           
           if (ResGetAllStd_ResGl.getInt("Id_BranchStd")==0){ prstStdRes.setInt(6  , 1);}else  prstStdRes.setInt(6  , ResGetAllStd_ResGl.getInt("Id_BranchStd"));
            System.out.println("Id_BranchStd : "+   ResGetAllStd_ResGl.getInt("Id_BranchStd"));
          
            if (ResGetAllStd_ResGl.getInt("Id_LevelStudy")==0) {prstStdRes.setInt(7, 1);}else  prstStdRes.setInt(7, ResGetAllStd_ResGl.getInt("Id_LevelStudy"));
            System.out.println("Id_LevelStudy "+   ResGetAllStd_ResGl.getInt("Id_LevelStudy"));
            
            prstStdRes.setString(8, ResGetAllStd_ResGl.getString("Num_InscritBac"));
            System.out.println("Num_InscritBac : "+   ResGetAllStd_ResGl.getString("Num_InscritBac"));
          
            prstStdRes.setString(9,ResGetAllStd_ResGl.getString("DateInscrp"));
            System.out.println("DateInscrp : "+   ResGetAllStd_ResGl.getString("DateInscrp"));
           prstStdRes.executeUpdate();
   }
      System.out.println("تم تحميل البيانات للطلبة بنجاح"); 
      //JOptionPane.showMessageDialog(null, "تم تحميل البيانات للطلبة الخارجيين بنجاح");
        } catch (SQLException e) {
            System.out.println("حدث خطأ في عملية التحميل "+e.getMessage());
                     // e.printStackTrace();
        }
 try {
       // stmMaxSqlsrv.close();
        ResMaxSqlsrv.close();
        prstStdRes.close();
        prstm.close();
        cnnct.close();
        cnx.Deconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
         }
        
   
 
        /******************************Process Import Data Of Employer Data*********************************/
    
      Query_Max_IdSQLSrv="SELECT MAX (NumCard_Resident) FROM Resident_Gl WHERE Id_Ptrn_Res=4";
         stmMaxSqlsrv=null;
         ResMaxSqlsrv=null;
        
        cnx.Connecting();
        try {
            stmMaxSqlsrv=cnx.getConnect().createStatement();
            ResMaxSqlsrv=stmMaxSqlsrv.executeQuery(Query_Max_IdSQLSrv);
            if (ResMaxSqlsrv.next()) {
                MaxNumCardSqlSrv=ResMaxSqlsrv.getInt(1);
                System.out.println("MaxIdSqlSrv :"+MaxNumCardSqlSrv);
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmMaxSqlsrv.close();
            ResMaxSqlsrv.close();
            cnx.Deconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
      /********************************************************************************/  
        QueryMaxAccess="SELECT MAX (NumCard_Resident) FROM Resident_Gl WHERE Id_Ptrn_Res=4";   
         ResMaxAccess=null;
         StmMaxAccess=null;
        ConnectingAccess();
        try {
            StmMaxAccess=GetConnectAccess().createStatement();
            ResMaxAccess=StmMaxAccess.executeQuery(QueryMaxAccess);
            if (ResMaxAccess.next()) {
                MaxNumCardAccess=ResMaxAccess.getInt(1);
                System.out.println("MaxIdAccess :"+MaxNumCardAccess);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
           StmMaxAccess.close();
           ResMaxAccess.close();
            GetConnectAccess().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if (MaxNumCardAccess>MaxNumCardSqlSrv) {
         /****************************Insert Into SQL Server****************************/
   PreparedStatement prstm=null;
      String QueryInsertInAccess="INSERT INTO Resident_Gl (Name_Resident,LastName_Resident,NumCard_Resident,"
              + "CodeBare_Resident,"
            + "DateBirth,PlaceBirth,ID_gender,Id_Ptrn_Res,ID_Case_Resident,imageStd) "
                  + "VALUES (?,?,?,?,?,?,?,?,?,?)";
      
      
      String QueryInsertInAccessStdRes="INSERT INTO Employer (ID_Rsident,ID_Profession) VALUES (?,?)";
/********************************************************************************************/      
       String QueryComplete="Select Name_Resident,LastName_Resident ,CodeBare_Resident,NumCard_Resident,\n" +
"DateBirth,PlaceBirth,Resident_Gl.ID_gender,Id_Ptrn_Res, imageStd,ID_Case_Resident,ID_Profession \n" +
"FROM  Resident_Gl,Employer Where \n" +
"( Resident_Gl.NumCard_Resident>  "+MaxNumCardSqlSrv+" and Resident_Gl.NumCard_Resident <=  "+MaxNumCardAccess+"  )\n" +
"and Resident_Gl.ID_Rsident=Employer.ID_Rsident  \n" +
"and  Resident_Gl.Id_Ptrn_Res=4";
/**************************************************************************************/
Statement StmGetAllStd_ResGl=null/*,StmGetAllStd_Std=null*/;
 ResultSet ResGetAllStd_ResGl=null/*,ResGetAllStd_Std=null*/;
PreparedStatement prstStdRes=null;
ConnectingAccess();
cnx.Connecting();
try {
    StmGetAllStd_ResGl=cnnct.createStatement();
    ResGetAllStd_ResGl=StmGetAllStd_ResGl.executeQuery(QueryComplete);
    System.out.println("Execute Query To Get Difference Data Between SQLserver And Access ");
 
    prstm=cnx.getConnect().prepareStatement(QueryInsertInAccess);
    prstStdRes=cnx.getConnect().prepareStatement(QueryInsertInAccessStdRes);
   
    String QuerySlct="SELECT MAX(ID_Rsident) FROM Resident_Gl";
         int ValMax=0 ;
         ConnectionDB cnx2;
    while (ResGetAllStd_ResGl.next()) {
System.out.println("Case Of Employer Student ************************************************");
            prstm.setString(1, ResGetAllStd_ResGl.getString("Name_Resident"));
            
            System.out.println("Name_Resident: "+ResGetAllStd_ResGl.getString("Name_Resident"));
            prstm.setString(2, ResGetAllStd_ResGl.getString("LastName_Resident"));
            System.out.println("LastName_Resident : "+ResGetAllStd_ResGl.getString("LastName_Resident"));
            // Numbre_CardRes=(Get_MAX_Numbre_CardRes(0)+1);
            prstm.setInt(3,ResGetAllStd_ResGl.getInt("NumCard_Resident")); //Corretct For TEsting 
            System.out.println("NumCard_Resident : "+ResGetAllStd_ResGl.getInt("NumCard_Resident"));
            //prstm.setInt(3,11219);
            prstm.setString(4 ,ResGetAllStd_ResGl.getString("CodeBare_Resident"));
            System.out.println("CodeBare_Resident: "+ResGetAllStd_ResGl.getString("CodeBare_Resident"));
            prstm.setDate(5,ResGetAllStd_ResGl.getDate("DateBirth")   /*new java.sql.Date(getDateBirth().getTime())*/);
            System.out.println("DateBirth : "+ResGetAllStd_ResGl.getDate("DateBirth"));
            prstm.setString(6, ResGetAllStd_ResGl.getString("PlaceBirth"));
            System.out.println("PlaceBirth: "+ResGetAllStd_ResGl.getString("PlaceBirth"));
            prstm.setInt(7, ResGetAllStd_ResGl.getInt("ID_gender"));
            System.out.println("ID_gender: "+ResGetAllStd_ResGl.getInt("ID_gender"));
            prstm.setInt(8,ResGetAllStd_ResGl.getInt("Id_Ptrn_Res"));
            System.out.println("Id_Ptrn_Res : "+ResGetAllStd_ResGl.getInt("Id_Ptrn_Res"));
            prstm.setInt(9, ResGetAllStd_ResGl.getInt("ID_Case_Resident"));
            System.out.println("ID_Case_Resident : "+ResGetAllStd_ResGl.getInt("ID_Case_Resident"));
            prstm.setBlob(10, ResGetAllStd_ResGl.getBlob("imageStd"));
            System.out.println("imageStd : "+ResGetAllStd_ResGl.getBlob("imageStd"));
            prstm.executeUpdate();
            prstm.close();
            System.err.println("insert Information Resident_Gl");
            /********************************************************/
             cnx2=new ConnectionDB();
             cnx2.Connecting();
            Statement stmX=cnx2.getConnect().createStatement();
            ResultSet resX=stmX.executeQuery(QuerySlct);
            if (resX.next()) {
                ValMax=resX.getInt(1);
                System.out.println("Max Id Sql Server :"+ValMax);
            }
            cnx2.Deconnect();
             stmX=null;
            resX=null;
            /******************************************************/
            prstStdRes.setInt(1, ValMax);
           System.out.println("Id Max Employer: "+ValMax);
            prstStdRes.setInt(2,  ResGetAllStd_ResGl.getInt("ID_Profession"));
           System.out.println("ID_Profession Employer: "+ValMax);
            prstStdRes.executeUpdate();
   
            }
       System.out.println("تم تحميل البيانات للعمال بنجاح");
        } catch (SQLException e) {
            System.out.println("تم تحميل البيانات للعمال بنجاح"+e.getMessage());
                     // e.printStackTrace();
        }
 try {
       // stmMaxSqlsrv.close();
        ResMaxSqlsrv.close();
        prstStdRes.close();
        cnnct.close();
        cnx.Deconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        }
        JOptionPane.showMessageDialog(null, "تم تحميل البيانات  بنجاح");
        
        }else {
        JOptionPane.showMessageDialog(null, "لم يتم الحصول علي ملف التحميل");
        }
    }
    /*****************************************************/
    
    public void Create_Backup(){
    
     String Query=  "BACKUP DATABASE DB_Residence TO DISK = 'D:\\DB_Residence.bak'";
         ConnectionDB cnx=new ConnectionDB();// Obj=new Connection_DB();
         cnx.Connecting();
        Statement  stm=null;
        try {
             cnx.getConnect().setAutoCommit(true);
             stm=cnx.getConnect().createStatement();
            
            stm.executeUpdate(Query);
            JOptionPane.showMessageDialog(null, "لقد تم انشاء نسخة احتياطية بنجاح");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "حدث خطأ في انشاء النسخة الاحتياطية");
           
        }
        try {
            cnx.Deconnect();
                stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  Connection cnnct;  
    
    public void ConnectingAccess(){
        try {
Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String DB;
            System.out.println("Success Download ");          
            DB = "jdbc:ucanaccess:////D:\\Base.accdb";
             cnnct=DriverManager.getConnection(DB);
                System.out.println("Success Connecting... ");
           // JOptionPane.showMessageDialog(null, "The connection is etablished...");
        } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Eroooooor conect for Server  "+e.getMessage());
           
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Connection GetConnectAccess(){
    return cnnct;
    }
    public static void main(String[] args) {
        new settings().Import_Data_ToSqlServer();
    }
}
