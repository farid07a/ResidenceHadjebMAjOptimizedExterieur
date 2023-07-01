/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package residence;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelReader {
    File F_excl=new File("D:\\FileExl.xlsx");
Object arg_Champs[];

String Code_Etablissement,Etablissement,Année_académique,Matricule_du_bac,
  Numéro_inscription,NIN,Nom_latin,Prénom_latin,Nom_arabe,Prénom_arabe,Civilité,
        Date_de_naissance,Prénom_père_latin,Prénom_père_arabe,Nom_mère_latin,
        Prénom_mère_latin,Nom_mère_arabe,Prénom_mère_arabe,Commune,Code_Filière,
        Filière,Code_Niveau,Niveau,Code_Cycle,Cycle,Code_Domaine,Domaine,
        Choix_1,Choix_2,Choix_3,DOU,Résidence
        ;

    public ExcelReader(){
        
    }


public void SetFileExcel(File f){
F_excl=f;

}

private int ConfirmExcelFile(File f){
FileInputStream fIP;
 XSSFWorkbook workbook;
        try {
            fIP = new FileInputStream(F_excl);
             workbook = new XSSFWorkbook(fIP);
              XSSFSheet spreadsheet = workbook.getSheetAt(0);
              
              XSSFRow row =spreadsheet.getRow(1);
              
              if (row.getCell(0).getCellType()==Cell.CELL_TYPE_STRING) {
                
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcelReader.class.getName()).log(Level.SEVERE, null, ex);
        }

     

    
    
return 0;} 
public void ReadDataExcel() throws FileNotFoundException, IOException{
  //File file = new File("D:\\Loucif.xlsx");
  ConnectionDB cnx=new  ConnectionDB();
 
      FileInputStream fIP = new FileInputStream(F_excl);
 XSSFWorkbook workbook = new XSSFWorkbook(fIP);
   if(F_excl.isFile() && F_excl.exists()) {
    XSSFSheet spreadsheet = workbook.getSheetAt(0);
      Iterator < Row >  rowIterator = spreadsheet.iterator();
       //rowIterator.
      DataFormatter dataFormatter = new DataFormatter();
         while (rowIterator.hasNext()) {
         XSSFRow row = (XSSFRow) rowIterator.next();
       Cell cell;  
       cell=row.getCell(0);
       
       Code_Etablissement=cell.getStringCellValue();
       cell=row.getCell(1);
       Etablissement=cell.getStringCellValue();
       
       cell=row.getCell(2);
       Année_académique=cell.getStringCellValue();
       
              cell=row.getCell(3);
         Matricule_du_bac= cell.getStringCellValue();
     
            cell=row.getCell(4);
         Numéro_inscription= cell.getStringCellValue();
         
           cell=row.getCell(5);
         NIN= cell.getStringCellValue();
         
          cell=row.getCell(6);
         Nom_latin= cell.getStringCellValue();
         
         cell=row.getCell(7);
         Prénom_latin= cell.getStringCellValue();
         
          cell=row.getCell(8);
         Nom_arabe= cell.getStringCellValue();
         
         cell=row.getCell(9);
         Prénom_arabe= cell.getStringCellValue();
         
         cell=row.getCell(10);
         Civilité= cell.getStringCellValue();
        Date Date_de_naissance = null; 
        String dateStdNas="";
        cell=row.getCell(11);
        if(DateUtil.isCellDateFormatted(cell)){
       Date_de_naissance=cell.getDateCellValue();
          SimpleDateFormat frm=new SimpleDateFormat("MM/dd/yyyy");
           this.Date_de_naissance=frm.format(Date_de_naissance);
          
      }else if(cell.getCellType()==1) 
      {
          JOptionPane.showMessageDialog(null, "Error in Date Colum");   
      }
       cell=row.getCell(12);
         Prénom_père_latin= cell.getStringCellValue(); 
       
         cell=row.getCell(13); 
         Prénom_père_arabe= cell.getStringCellValue();
       
         cell=row.getCell(14); 
         Nom_mère_latin= cell.getStringCellValue();
         
       cell=row.getCell(15); 
         Prénom_mère_latin= cell.getStringCellValue();
      
         cell=row.getCell(16); 
         Nom_mère_arabe= cell.getStringCellValue();  
         
         cell=row.getCell(17); 
         Prénom_mère_arabe= cell.getStringCellValue(); 
         
         cell=row.getCell(18); 
         Commune= cell.getStringCellValue();
         
         cell=row.getCell(19); 
         Code_Filière= cell.getStringCellValue();
         
         cell=row.getCell(20); 
         Filière= cell.getStringCellValue(); 
         
         cell=row.getCell(21); 
         Code_Niveau= cell.getStringCellValue(); 
         
         cell=row.getCell(22); 
         Niveau= cell.getStringCellValue();
         
         cell=row.getCell(23); 
         Code_Cycle= cell.getStringCellValue();
         
         cell=row.getCell(24); 
         Cycle= cell.getStringCellValue();
         
          cell=row.getCell(25); 
         Code_Domaine= cell.getStringCellValue();
         
         cell=row.getCell(26); 
         Domaine= cell.getStringCellValue();
         cell=row.getCell(27); 
         Choix_1= cell.getStringCellValue();
         cell=row.getCell(28); 
         Choix_2= cell.getStringCellValue();
         cell=row.getCell(29); 
         Choix_3= cell.getStringCellValue();
         cell=row.getCell(30); 
         DOU= cell.getStringCellValue();
         
         cell=row.getCell(31); 
         Résidence= cell.getStringCellValue();
         
         
         /*,Code_Filière,
        Filière,Code_Niveau,Niveau,Code_Cycle,Cycle,Code_Domaine,Domaine,
        Choix_1,Choix_2,Choix_3,DOU,Résidence
      
         
         */
         
    System.out.println("javaapplication95.ExcelReader.ReadDataExcel()"+Code_Etablissement+"---"+
               Etablissement+"----"+Année_académique+"---"+Matricule_du_bac+"---"+this.Date_de_naissance+" "+NIN
    +Nom_latin+" "+Prénom_latin+" "+Nom_arabe+" "+Prénom_arabe+" "+Civilité+" "+Date_de_naissance+" "+Prénom_père_latin+" "
    +Nom_mère_latin+" "+Prénom_mère_latin+" "+Nom_mère_arabe+" "+Prénom_mère_arabe+" "+Commune+" "+Code_Filière+" "+
     Filière+" "+Code_Niveau+" "+Code_Cycle+" "+Cycle+" "+Code_Domaine+" "+Domaine+" "+Choix_1+" "+Choix_2+" "+Choix_3+" "+DOU+" "+Résidence       
    );
       
             
             
         Iterator < Cell >  cellIterator = row.cellIterator();
      /*   
         while ( cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            
           Object Value;
           
           String cellValue = dataFormatter.formatCellValue(cell); 
           
             System.out.println("javaapplication95.ExcelReader.ReadDataExcel() "+cellValue+" \t\t");
           
           
             System.out.println("******************************************");
           /*
            switch (cell.getCellType()) {
            
                case Cell.CELL_TYPE_NUMERIC:
            
                    System.out.print(cell.getNumericCellValue() + " \t\t ");
                    Value=cell.getNumericCellValue();
                    
                  break;
               
               case Cell.CELL_TYPE_STRING:
                  System.out.print(
                  cell.getStringCellValue() + " \t\t ");
                   Value=cell.getStringCellValue();
                  break;
            }
         }*/
         System.out.println();
         
         
             Insert_DataExcel();
         
         
      }
      
      fIP.close();
       
       System.out.println("FileExl.xlsx file open successfully.");
   JOptionPane.showMessageDialog(null, "لقد تم استيراد البيانات بنجاح");
   
   
   } else {
         System.out.println("Error to open FileExl.xlsx file.");
         
      }
   
    //cnx.Deconnect();
}
    public void Insert_DataExcel(){
       
        
        PreparedStatement prstm=null;
        ConnectionDB cnx=new ConnectionDB();
        cnx.Connecting();
        String Query="insert into Nev_Resident([Code Etablissement],Etablissement,[Année académique],[Matricule du bac],[Numéro d'inscription],NIN,[Nom latin],\n" +
"[Prénom latin],[Nom arabe],[Prénom arabe],Civilité,[Date de naissance],[Prénom père latin],[Prénom père arabe],[Nom mère latin],\n" +
"[Prénom mère latin],[Nom mère arabe],[Prénom mère arabe],Commune,[Code Filière],Filière,[Code Niveau],Niveau,[Code Cycle],\n" +
"Cycle,[Code Domaine],Domaine,[Choix 1],[Choix 2],[Choix 3],DOU,Résidence\n" +
")\n" +
"\n" +
"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
       
        try {
            prstm=cnx.getConnect().prepareStatement(Query);
            
            //JOptionPane.showMessageDialog(null, "The PrepareStateùment is exe");
            prstm.setString(1,Code_Etablissement);
            prstm.setString(2,Etablissement);
             prstm.setString(3,Année_académique);
            prstm.setString(4,Matricule_du_bac);
            prstm.setString(5,Numéro_inscription); 
            prstm.setString(6,NIN);
            prstm.setString(7,Nom_latin);
            prstm.setString(8,Prénom_latin);
            prstm.setString(9,Nom_arabe);
            prstm.setString(10,Prénom_arabe);
            prstm.setString(11,Civilité);
            
            prstm.setDate(12, new java.sql.Date(new Date(Date_de_naissance).getTime()));
            prstm.setString(13,Prénom_père_latin);
            prstm.setString(14,Prénom_père_arabe);
            prstm.setString(15,Nom_mère_latin);
            prstm.setString(16,Prénom_mère_latin);
            prstm.setString(17,Nom_mère_arabe);
            prstm.setString(18,Prénom_mère_arabe);
            prstm.setString(19,Commune);
            prstm.setString(20,Code_Filière);
            prstm.setString(21,Filière);
            prstm.setString(22,Code_Niveau);
            prstm.setString(23,Niveau);
            prstm.setString(24,Code_Cycle);
            prstm.setString(25,Cycle);
            prstm.setString(26,Code_Domaine);
            prstm.setString(27,Domaine);
            prstm.setString(28,Choix_1);
            prstm.setString(29,Choix_2);
            prstm.setString(30,Choix_3);
            prstm.setString(31,DOU);
            prstm.setString(32,Résidence);
          /*  Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);*/
        //    prstm.setInt(33, year);
            //JOptionPane.showMessageDialog(null, "Finish All Preparestatement");
            
            int x=prstm.executeUpdate();
            if (x>0) {
                System.out.println("javaapplication95.ExcelReader.Insert_DataExcel()"+ "IS Succesful");
            }else {
            
            JOptionPane.showMessageDialog(null, "Error in Else Condition");
            }
            prstm.close();
            
            /*   
         Code_Etablissement,Etablissement,Année_académique,Matricule_du_bac,
  Numéro_inscription,NIN,Nom_latin,Prénom_latin,Nom_arabe,Prénom_arabe,Civilité,
        Date_de_naissance,Prénom_père_latin,Prénom_père_arabe,Nom_mère_latin,
        Prénom_mère_latin,Nom_mère_arabe,Prénom_mère_arabe,Commune,Code_Filière,
        Filière,Code_Niveau,Niveau,Code_Cycle,Cycle,Code_Domaine,Domaine,
        Choix_1,Choix_2,Choix_3,DOU,Résidence*/
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Sql :"+e.getMessage());
        }
        
        cnx.Deconnect();
    
    }
    public static void main(String[] args) throws FileNotFoundException, IOException {
        /*try {    
            Workbook workbook = WorkbookFactory.create(F_excl);
            System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
          //  workbook.close();
        } catch (IOException ex) {
            Logger.getLogger(ExcelReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(ExcelReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Workbook workbookCrt= new XSSFWorkbook();
        */
         /* CreationHelper helps us create instances of various things like DataFormat, 
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
       /* CreationHelper createHelper = workbookCrt.getCreationHelper();
          // Create a Sheet
      Sheet sheet = workbookCrt.createSheet("liste_Etudiant");
      // Create a Font for styling header cells
     //   Font headerFont = workbookCrt.createFont();
        
        Font headerFont = workbookCrt.createFont();
        headerFont.setBoldweight((short)14);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());
      //headerFont.setColor(IndexedColors.RED.getIndex());
         // Create a CellStyle with the font
        CellStyle headerCellStyle = workbookCrt.createCellStyle();
        headerCellStyle.setFont(headerFont);
      
         Row headerRow = sheet.createRow(0);
         
         
        // Create cells
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            //Cell cel1=headerRow.getCell(0);
           // cel1.
            
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }
        // Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbookCrt.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
        int rowNum=1;
        for (int i = 1; i < 10; i++) {
            Row row = sheet.createRow(i);
            
            row.createCell(0).setCellValue("farid");
            row.createCell(1).setCellValue("Khebbache");
            row.createCell(2).setCellValue("10-10-2009");
            row.createCell(3).setCellValue("10000");
        }
        // Resize all columns to fit the content size
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("D:\\Student.xlsx");
        workbookCrt.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbookCrt.close();*/
       new ExcelReader().ReadDataExcel();
        
    }
      private static String[] columns = {"Name", "Email", "Date Of Birth", "Salary"};
}
