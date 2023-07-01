/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package residence;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author admin
 */
public class Import_Export_Data {

    ConnectionDB cnx = new ConnectionDB();
    Statement stm;
    ResultSet res;
    Workbook workbookCrt = null;
    Sheet sheet;
    Row headerRow;
    //= new XSSFWorkbook()
    String[] columns = {"prénom_AR", "Nom_AR", "Nom_FR", "prénom_FR", "N° Carte", "Date_Nai", "Lieu_Nais_AR", "Lieu_Nais_FR",
        "Prenom_Pere_AR", "Prénom_Mere_AR", "Nom_Mere_AR", "Prenom_Pere_FR", "Prénom_Mere_FR", "Nom_Mere_FR",
        "N_Inscription", "Date_Inscrp", "Moyen_Bac", "Place_Bac", "Année_Bac",
        "Situation_Fam", "Daira", "Commune", "Wilaya", "Address", "Profession_Mer", "Profession_per",
        "Nationalite", "Nationalite_Fr", "Branche_AR",
        "Branch_Fr", "Faculte", "Annee_Etd", "Année_Etd_Abr", "Chambre",};

    public Import_Export_Data() {

    }

    public void Fill_FileExcel_StdNoCodeCommune(String Query) throws FileNotFoundException, IOException {
        workbookCrt = new XSSFWorkbook();
        /* CreationHelper helps us create instances of various things like DataFormat, 
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
        CreationHelper createHelper = workbookCrt.getCreationHelper();
        // Create a Sheet
        sheet = workbookCrt.createSheet("liste_Etudiant");
        // Create a Font for styling header cells
        //Font headerFont = workbookCrt.createFont();
        Font headerFont = workbookCrt.createFont();
        headerFont.setBoldweight((short) 14);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());
        //headerFont.setColor(IndexedColors.RED.getIndex());
        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbookCrt.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerRow = sheet.createRow(0);
        // Create cells
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            //Cell cel1=headerRow.getCell(0);
            // cel1.

            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }
        //Inistialise_Excle();

        /**
         * ***********************************************
         */
        /*  String Query1="SELECT Name_Resident,LastName_Resident,Name_ResidentFr,LastName_ResidentFr,NumCard_Resident,DateBirth,PlaceBirth,PlaceBirthFr,\n" +
"Name_Father,FullName_Mother,LastNamMothAR,Name_FatherFr,Name_MotherFr,LastName_MotheFr,Num_InscritBac,DateInscrp,BacMoyen,PlaceGetBac,\n" +
"BacYear,BacMoyen,SituationFamily,Name_Daira,Name_Commune,NameWilaya,Address,ProfessionMother,ProfessionFather,\n" +
"Nationalite,Nationalite_Fr,BranchStd_Name,BranchStd_NameFr,NameFact,DescriptionLevel,Level_study,Room_Code\n" +
"FROM Resident_Gl,Student_Res,Nationalite,Branch_Study,Faculty,Level_Study,Room,Wilaya\n" +
"WHERE \n" +
"Student_Res.Id_Nationalite=Nationalite.Id_Nationalite\n" +
"AND Student_Res.Id_BranchStd=Branch_Study.Id_BranchStd\n" +
"AND Student_Res.ID_Wilaya=Wilaya.ID_Wilaya\n" +
"\n" +
"AND Student_Res.Id_Faculty=Faculty.Id_Faculty\n" +
"AND Student_Res.Id_LevelStudy=Level_Study.Id_LevelStudy\n" +
"AND Student_Res.Id_Room=Room.Id_Room\n" +
"AND  Resident_Gl.ID_Rsident=Student_Res.ID_Rsident\n" +
"AND  Resident_Gl.Id_Ptrn_Res=1";*/
 /*String Query1="SELECT Name_Resident,LastName_Resident,Name_ResidentFr,LastName_ResidentFr,NumCard_Resident,DateBirth,PlaceBirth,PlaceBirthFr,\n" +
"Name_Father,FullName_Mother,LastNamMothAR,Name_FatherFr,Name_MotherFr,LastName_MotheFr,Num_InscritBac,DateInscrp,BacMoyen,PlaceGetBac,\n" +
"BacYear,BacMoyen,SituationFamily,Name_Daira,Name_Commune,NameWilaya,Address,ProfessionMother,ProfessionFather,\n" +
"Nationalite,Nationalite_Fr,BranchStd_Name,BranchStd_NameFr,NameFact,DescriptionLevel,Level_study,Room_Code,Resident_Case\n" +
"FROM Resident_Gl,Student_Res,Nationalite,Branch_Study,Faculty,Level_Study,Room,Wilaya,Resident_Case \n" +
"WHERE \n" +
"Student_Res.Id_Nationalite=Nationalite.Id_Nationalite\n" +
"AND Student_Res.Id_BranchStd=Branch_Study.Id_BranchStd\n" +
"AND Student_Res.ID_Wilaya=Wilaya.ID_Wilaya\n" +
"\n" +
"AND Student_Res.Id_Faculty=Faculty.Id_Faculty\n" +
"AND Student_Res.Id_LevelStudy=Level_Study.Id_LevelStudy\n" +
"AND Student_Res.Id_Room=Room.Id_Room\n" +
"AND Resident_Gl.ID_Rsident=Student_Res.ID_Rsident\n" +             
"AND Resident_Gl.ID_Case_Resident=Resident_Case.ID_Case_Resident "+             
"AND Resident_Gl.Id_Ptrn_Res=1 AND Resident_Case=N'"+SCase+"' order by Room_Code ";*/ //lAST CODE
        cnx.Connecting();
        try {
            stm = cnx.getConnect().createStatement();
            res = stm.executeQuery(Query);
            int NumRow = 1;
            Row row;
            while (res.next()) {
                // System.out.println("residence.Import_Export_Data.<init>()"+res.getString(1));
                row = sheet.createRow(NumRow++);
                //System.out.println("Row is Created "+res.getString(1));
                row.createCell(0).setCellValue(TraitNull(res.getString("Name_Resident")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("Name_Resident"));
                row.createCell(1).setCellValue(TraitNull(res.getString("LastName_Resident")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("LastName_Resident"));
                row.createCell(2).setCellValue(TraitNull(res.getString("Name_ResidentFr")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("Name_ResidentFr"));
                row.createCell(3).setCellValue(TraitNull(res.getString("LastName_ResidentFr")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("LastName_ResidentFr"));
                row.createCell(4).setCellValue("" + TraitNull(res.getInt("NumCard_Resident")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+""+res.getInt("NumCard_Resident"));
                String Date_Str = new SimpleDateFormat("dd/MM/yyyy").format(res.getDate("DateBirth"));
                /**
                 * ********************************************************
                 */
                row.createCell(5).setCellValue(TraitNull(res.getDate("DateBirth")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+Date_Str);
                row.createCell(6).setCellValue(TraitNull(res.getString("PlaceBirth")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("PlaceBirth"));                
                row.createCell(7).setCellValue(TraitNull(res.getString("PlaceBirthFr")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("PlaceBirthFr"));
                row.createCell(8).setCellValue(TraitNull(res.getString("Name_Father")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("Name_Father"));
                row.createCell(9).setCellValue(TraitNull(res.getString("FullName_Mother")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("FullName_Mother"));
                /**
                 * *****************************************************
                 */
                row.createCell(10).setCellValue(TraitNull(res.getString("LastNamMothAR")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("LastNamMothAR"));
                row.createCell(11).setCellValue(TraitNull(res.getString("Name_FatherFr")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("Name_FatherFr"));
                row.createCell(12).setCellValue(TraitNull(res.getString("Name_MotherFr")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("Name_MotherFr"));
                row.createCell(13).setCellValue(TraitNull(res.getString("LastName_MotheFr")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("LastName_MotheFr"));
                row.createCell(14).setCellValue(TraitNull(res.getString("Num_InscritBac")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("Num_InscritBac"));
                //Num_InscritBac
                /**
                 * ********************************************************
                 */
//                 String Date_Str2=new SimpleDateFormat("MM/dd/yyyy").format(res.getDate("DateInscrp"));
                row.createCell(15).setCellValue(TraitNull(res.getDate("DateInscrp")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+Date_Str2);
                row.createCell(16).setCellValue(TraitNull(res.getFloat("BacMoyen")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getFloat("BacMoyen"));
                row.createCell(17).setCellValue(TraitNull(res.getString("PlaceGetBac")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("PlaceGetBac"));
                row.createCell(18).setCellValue("" + TraitNull(res.getInt("BacYear")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getInt("BacYear"));
                row.createCell(19).setCellValue(TraitNull(res.getString("SituationFamily")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("SituationFamily"));
                /**
                 * ******************************************************************
                 */
                row.createCell(20).setCellValue(TraitNull(res.getString("Name_Daira")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("Name_Daira"));
                row.createCell(21).setCellValue(TraitNull(res.getString("Name_Commune")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("Name_Commune"));
                row.createCell(22).setCellValue(TraitNull(res.getString("NameWilaya")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("NameWilaya"));
                row.createCell(23).setCellValue(TraitNull(res.getString("Address")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("Address"));
                row.createCell(24).setCellValue(TraitNull(res.getString("ProfessionMother")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("ProfessionMother"));
                /**
                 * ***************************************************************
                 */
                row.createCell(25).setCellValue(TraitNull(res.getString("ProfessionFather")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("ProfessionFather"));
                row.createCell(26).setCellValue(TraitNull(res.getString("Nationalite")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("Nationalite"));

                row.createCell(27).setCellValue(TraitNull(res.getString("Nationalite_Fr")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("Nationalite_Fr"));
                row.createCell(28).setCellValue(TraitNull(res.getString("BranchStd_Name")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("BranchStd_Name"));

                row.createCell(29).setCellValue(TraitNull(res.getString("BranchStd_NameFr")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("BranchStd_NameFr"));
                row.createCell(30).setCellValue(TraitNull(res.getString("NameFact")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("NameFact"));
                row.createCell(31).setCellValue(TraitNull(res.getString("DescriptionLevel")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("DescriptionLevel"));
                row.createCell(32).setCellValue(TraitNull(res.getString("Level_study")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("Level_study"));
                row.createCell(33).setCellValue(TraitNull(res.getString("Room_Code")));
                //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("Room_Code"));
                //NameWilaya

                //DateInscr
                //Name_MotherFr
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error in sql " + e.getMessage());
        }
        try {
            res.close();
            stm.close();
            cnx.Deconnect();
        } catch (Exception e) {
        }

        // Resize all columns to fit the content size
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("D:\\Students_List01.xlsx");
        workbookCrt.write(fileOut);
        fileOut.close();

        JOptionPane.showMessageDialog(null, "تم عملية تصدير البيانات بنجاح");

        // Closing the workbook
        workbookCrt.close();

    }

    public void Fill_FileExcel_Std2(String Query1) throws FileNotFoundException, IOException {
        workbookCrt = new XSSFWorkbook();
        String[] columns = {"Annee BAC", "Matricule BAC", "Nom", "Prenom", "Nom Ar", "Prenom Ar", "Sexe", "Prenom Pere",
            "Nom Mere", "Prenom Mere", "Date Naissance", "Lieu Naissance", "Code Wilaya Residence",
            "Wilaya Residence", "Code Commune Residence", "Commune Residence", "Adresse", "Code filiere",
            "Filiere", "Filiere Ar", "Cycle", "Niveau", "Chambre"
        };
        /* CreationHelper helps us create instances of various things like DataFormat, 
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
        CreationHelper createHelper = workbookCrt.getCreationHelper();
        // Create a Sheet
        sheet = workbookCrt.createSheet("liste_Etudiant");
        // Create a Font for styling header cells
        //   Font headerFont = workbookCrt.createFont();

        Font headerFont = workbookCrt.createFont();
        headerFont.setBoldweight((short) 14);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());
        //headerFont.setColor(IndexedColors.RED.getIndex());
        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbookCrt.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerRow = sheet.createRow(0);
        // Create cells
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            //Cell cel1=headerRow.getCell(0);
            // cel1.

            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        //Inistialise_Excle();
        /**
         * ***********************************************
         */
        /*  String Query1="SELECT Name_Resident,LastName_Resident,Name_ResidentFr,LastName_ResidentFr,NumCard_Resident,DateBirth,PlaceBirth,PlaceBirthFr,\n" +
"Name_Father,FullName_Mother,LastNamMothAR,Name_FatherFr,Name_MotherFr,LastName_MotheFr,Num_InscritBac,DateInscrp,BacMoyen,PlaceGetBac,\n" +
"BacYear,BacMoyen,SituationFamily,Name_Daira,Name_Commune,NameWilaya,Address,ProfessionMother,ProfessionFather,\n" +
"Nationalite,Nationalite_Fr,BranchStd_Name,BranchStd_NameFr,NameFact,DescriptionLevel,Level_study,Room_Code\n" +
"FROM Resident_Gl,Student_Res,Nationalite,Branch_Study,Faculty,Level_Study,Room,Wilaya\n" +
"WHERE \n" +
"Student_Res.Id_Nationalite=Nationalite.Id_Nationalite\n" +
"AND Student_Res.Id_BranchStd=Branch_Study.Id_BranchStd\n" +
"AND Student_Res.ID_Wilaya=Wilaya.ID_Wilaya\n" +
"\n" +
"AND Student_Res.Id_Faculty=Faculty.Id_Faculty\n" +
"AND Student_Res.Id_LevelStudy=Level_Study.Id_LevelStudy\n" +
"AND Student_Res.Id_Room=Room.Id_Room\n" +
"AND  Resident_Gl.ID_Rsident=Student_Res.ID_Rsident\n" +
"AND  Resident_Gl.Id_Ptrn_Res=1";*/
 /*String Query11="SELECT distinct Name_Resident,LastName_Resident,Name_ResidentFr,LastName_ResidentFr,NumCard_Resident,DateBirth,PlaceBirth,PlaceBirthFr, \n" +
"Name_Father,FullName_Mother,LastNamMothAR,Name_FatherFr,Name_MotherFr,LastName_MotheFr,Num_InscritBac,DateInscrp,BacMoyen,PlaceGetBac,\n" +
"BacYear,SituationFamily,Name_Daira,Name_Commune,NameWilaya,Address,ProfessionMother,ProfessionFather,Branch_Study.BranchStd_Name,Branch_Study.branch_code,Branch_Study.BranchStd_NameFr, \n" +
"Nationalite,Nationalite_Fr,NameFact,DescriptionLevel,Level_study,Room_Code,Resident_Case,Wilaya.NumWilaya,Pavilion.Pavilion_Name,Resident_Gl.ID_gender\n" +
"FROM Resident_Gl,Student_Res,Nationalite,Branch_Study,Faculty,Level_Study,Room,Wilaya,Resident_Case,Commune,Pavilion,Gender \n" +
"WHERE  \n" +
"Student_Res.Id_Nationalite=Nationalite.Id_Nationalite  \n" +
"AND Student_Res.Id_BranchStd=Branch_Study.Id_BranchStd  \n" +
"AND Student_Res.ID_Wilaya=Wilaya.ID_Wilaya  \n" +
"AND Student_Res.Id_Faculty=Faculty.Id_Faculty \n" +
"AND Gender.ID_gender=Resident_Gl.ID_gender\n" +
"AND Student_Res.Id_LevelStudy=Level_Study.Id_LevelStudy \n" +
"AND Student_Res.Id_Room=Room.Id_Room \n" +
"AND Resident_Gl.ID_Rsident=Student_Res.ID_Rsident        \n" +
"AND Pavilion.ID_Pavilion=Room.ID_Pavilion \n" +
"AND Resident_Gl.ID_Case_Resident=Resident_Case.ID_Case_Resident      \n" +
"AND Resident_Gl.Id_Ptrn_Res=1 order by Room_Code ";
//"AND Resident_Gl.Id_Ptrn_Res=1 AND Resident_Case=N'"+SCase+"' order by Room_Code ";*/
        cnx.Connecting();
        try {
            stm = cnx.getConnect().createStatement();
            res = stm.executeQuery(Query1);
            int NumRow = 1;
            Row row;
            while (res.next()) {
                // System.out.println("residence.Import_Export_Data.<init>()"+res.getString(1));
                row = sheet.createRow(NumRow++);
                row.createCell(0).setCellValue(TraitNull(res.getFloat ("BacYear")));
                row.createCell(1).setCellValue(TraitNull(res.getString("Num_InscritBac")));
                row.createCell(2).setCellValue(TraitNull(res.getString("Name_ResidentFr")));
                row.createCell(3).setCellValue(TraitNull(res.getString("LastName_ResidentFr")));
                row.createCell(4).setCellValue(TraitNull(res.getString("Name_Resident")));
                row.createCell(5).setCellValue(TraitNull(res.getString("LastName_Resident")));
                int Gender = res.getInt("ID_gender");
                if (Gender == 1) {
                    row.createCell(6).setCellValue(TraitNull("مذكر"));
                } else {
                    row.createCell(6).setCellValue(TraitNull("مؤنث"));
                }
                row.createCell(7).setCellValue(TraitNull(res.getString("Name_FatherFr")));
                row.createCell(8).setCellValue(TraitNull(res.getString("LastName_MotheFr")));
                row.createCell(9).setCellValue(TraitNull(res.getString("Name_MotherFr")));
                row.createCell(10).setCellValue(TraitNull(res.getDate ("DateBirth")));
                row.createCell(11).setCellValue(TraitNull(res.getString("PlaceBirthFr")));
                row.createCell(12).setCellValue(TraitNull(res.getString("NumWilaya")));
                row.createCell(13).setCellValue(TraitNull(res.getString("NameWilaya")));
                row.createCell(14).setCellValue(TraitNull(GetCodeCommune(res.getString("Name_Commune"))));
                row.createCell(15).setCellValue(TraitNull(res.getString("Name_Commune")));
                row.createCell(16).setCellValue(TraitNull(res.getString("Address")));
                row.createCell(17).setCellValue(TraitNull(res.getString("branch_code")));
                row.createCell(18).setCellValue(TraitNull(res.getString("BranchStd_NameFr")));//Level_study
                row.createCell(19).setCellValue(TraitNull(res.getString("BranchStd_Name")));
                String NiveauCode = res.getString("Level_study");
                char Cy = ' ';
                char Nv;
                String Cycle = "" + Cy;
                String Niveau = "";
                if (NiveauCode.length() == 1) {
                    Cycle = "/";
                    Niveau = "/";
                } else {

                    Nv = TraitNull(NiveauCode).charAt(0);
                    Cy = TraitNull(NiveauCode).charAt(1);

                    switch (Cy) {
                        case 'L':
                            Cycle = "License";

                            switch (Nv) {
                                case '1':
                                    Niveau = "Licence 1ère Année";
                                    break;
                                case '2':
                                    Niveau = "Licence 2ème Année";
                                    break;
                                case '3':
                                    Niveau = "Licence 3ème Année";
                                    break;
                            }
                            break;
                        //Branch_Study.branch_code,Branch_Study.BranchStd_NameFr
                        //System.out.println("Row is Created "+res.getString(1)); Wilaya.NumWilaya
                        //System.out.println("residence.Import_Export_Data.Fill_FileExcel_Std()"+res.getString("Name_Resident"));
                        case 'M':
                            Cycle = "Master";

                            switch (Nv) {
                                case '1':
                                    Niveau = "Master 1ère Année";
                                    break;
                                case '2':
                                    Niveau = "Master 2ème Année";
                                    break;
                            }

                            break;
                        case 'D':
                            Cycle = "Doctorat";
                            switch (Nv) {
                                case '1':
                                    Niveau = "Doctorat 1ère Année";
                                    break;
                                case '2':
                                    Niveau = "Doctorat 2ème Année";
                                    break;
                                case '3':
                                    Niveau = "Doctorat 3ème Année";
                                    break;
                                case '4':
                                    Niveau = "Doctorat 4ème Année";
                                    break;
                                case '5':
                                    Niveau = "Doctorat 5ème Année";
                                    break;
                                case '6':
                                    Niveau = "Doctorat 6ème Année";
                                    break;
                            }
                            break;
                    }
                }
                row.createCell(20).setCellValue(TraitNull(Cycle));
                row.createCell(21).setCellValue(TraitNull(Niveau));
                row.createCell(22).setCellValue(TraitNull(res.getString("Room_Code")));

                // row.createCell(23).setCellValue(TraitNull(res.getString("Pavilion_Name")));
            }
            JOptionPane.showMessageDialog(null, "تم عملية تصدير البيانات بنجاح");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error in sql Fill_FileExcel_Std2 " + e.getMessage());
            e.printStackTrace();
        }
        try {
            res.close();
            stm.close();
            cnx.Deconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Resize all columns to fit the content size
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("D:\\Students_List02.xlsx");
        workbookCrt.write(fileOut);
        fileOut.close();

        try {
            Desktop dt = Desktop.getDesktop();
            // dt.open(new File("src\\OurFile\\AppClose.xlsx"));
            dt.open(new File("D:\\Students_List02.xlsx"));  //when crete file from multi choice checkBox 
            //  dt.open(new File(""+FullNam.getText()+".xlsx"));

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error in Opened The File");
        }

        workbookCrt.close();
    }

    public String GetCodeCommune(String Commune_AR) {
        String QueryCodeCommune = "SELECT Code_commune  from Commune where Commune_Ar =N'" + Commune_AR + "' ;";
        Statement stm = null;
        ResultSet res = null;
        String CodeCommune = "";
        try {
            stm = cnx.getConnect().createStatement();
            res = stm.executeQuery(QueryCodeCommune);
            if (res.next()) {
                CodeCommune = res.getString("Code_commune");
            }
            stm.close();
            res.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error In GetCODE Commune " + e.getMessage());
        }
        return CodeCommune;
    }

    public String TraitNull(Object Value) {
        String Value1 = "";
        if (Value != null) {
            if (Value instanceof Date) {
                Value1 = new SimpleDateFormat("dd-MM-yyyy").format(Value);

            } else if (Value instanceof String) {
                Value1 = (String) Value;

            } else if (Value instanceof Integer) {
                Value1 += "";
            } else if (Value instanceof Float) {
                Value1 += "";
            }
            return Value1;
        } else {
            return "";
        }

    }

    public void Inistialise_Excle() {
        workbookCrt = new XSSFWorkbook();

        /* CreationHelper helps us create instances of various things like DataFormat, 
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
        CreationHelper createHelper = workbookCrt.getCreationHelper();
        // Create a Sheet
        Sheet sheet = workbookCrt.createSheet("liste_Etudiant");
        // Create a Font for styling header cells
        //   Font headerFont = workbookCrt.createFont();

        Font headerFont = workbookCrt.createFont();
        headerFont.setBoldweight((short) 14);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());
        //headerFont.setColor(IndexedColors.RED.getIndex());
        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbookCrt.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerRow = sheet.createRow(0);
        // Create cells
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            //Cell cel1=headerRow.getCell(0);
            // cel1.
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }
    }
    public static void main(String[] args) throws IOException {
        new Import_Export_Data().Fill_FileExcel_StdNoCodeCommune("");
    }

}
