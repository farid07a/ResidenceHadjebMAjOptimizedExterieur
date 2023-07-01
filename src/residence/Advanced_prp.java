/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
farid khe --- chaima khebb
 */
package residence;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author farid
 */
public class Advanced_prp extends javax.swing.JFrame {

    /**
     * Creates new form Advanced_prp
     */
    ConnectionDB cnx = new ConnectionDB();
    Import_Export_Data ExportDataExce = new Import_Export_Data();
    Get_Info_DB filling = new Get_Info_DB();
    Room rom = new Room();
    String[] columns = {"الحالة", "الغرفة", "المستوى ", "التخصص", "الجنسية", "الولاية", "البلدية", "الدئرة", "بكالوريا",
        "رقم التسجيل", "مكان الميلاد", "تاريخ الميلاد", "Nom", "prenom", "اللقب", "الاسم", "البطاقة", "الصورة"
    };

    /*    res.getInt("BacYear"),res.getString("Name_Daira"),
                    res.getString("Name_Commune"),res.getString("NameWilaya"),
                    res.getString("Nationalite"),res.getString("BranchStd_Name"),
                    res.getString("Level_study"),res.getString("Room_Code"),
                    res.getString("Resident_Case")};
            }*/
    Student_Res student_ResRemplissage = new Student_Res();
    DefaultTableModel df;
    String[] ComInvers = {"Chambre", "Année_Etd_Abr", "Annee_Etd"};
    Conf cnf;

    public Advanced_prp(Conf cnf) {
        initComponents();
        this.cnf = cnf;
        df = new DefaultTableModel(columns, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 17) {
                    return Icon.class;
                } else {
                    return getValueAt(0, columnIndex).getClass();
                }
            }
            
            
        };

        jTable2.setModel(df);
        this.Filling(Resident_Case, "Resident_Case", "Resident_Case", 1);
        this.Filling(Pavilion, "Pavilion", "Pavilion_Name", 1);
        this.Filling(Branch_Study, "Branch_Study", "BranchStd_Name", 1);
        this.Filling(Level_Study, "Level_Study", "DescriptionLevel", 1);

        this.Filling(Name_Commune, "Student_Res", "Name_Commune", 0);
        this.Filling(Name_Daira, "Student_Res", "Name_Daira", 0);
        this.Filling(wilaya_List, "Wilaya", "NameWilaya", 1);
        FillListYear();
        Align_List(Resident_Case);
        Align_List(Name_Daira);
        Align_List(Name_Commune);
        Align_List(Level_Study);

        Align_List(wilaya_List);
        Align_List(Branch_Study);
        Align_List(Pavilion);
        //   Align_List(bacYearList);
        PanWilaya.setVisible(false);
        PanLevel.setVisible(false);
        PanBranch.setVisible(false);
        PanCommune.setVisible(false);
        PanDaira.setVisible(false);
        PanBloc.setVisible(false);
        PanCase.setVisible(false);
        PanBacYear.setVisible(false);
        RomPvinPanInf.setVisible(false); //combobox is false when jframe opened
        Filling_Data(28);
        this.cnf.Filling_NameWilaya(NameWilaya);

//        jList8.setSize(220, 200);
        // jList8.setPreferredSize(new Dimension(100,100));
    }

    public void Align_List(JList list) {
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
    }

    public void Filling(JList list, String Tab, String Field, int i) { //i==0 for distict select for commune daira field
        //i==2 for field integer 
        Statement stm = null;
        ResultSet res = null;
        String Query = "SELECT * FROM  " + Tab + "";
        if (i == 0) {
            Query = "SELECT DISTINCT " + Field + " FROM  " + Tab + " ";
        }

        DefaultListModel listModel = new DefaultListModel();

        listModel.removeAllElements();
//       listModel= list.;
//        listModel.removeAllElements();
        cnx.Connecting();

        try {
            stm = cnx.getConnect().createStatement();
            res = stm.executeQuery(Query);

            while (res.next()) {
                listModel.addElement(res.getString(Field));
            }
            list.setModel(listModel);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL :" + e.getMessage());
        }

        try {
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Close :" + e.getMessage());
        }

    }

    public void FillListYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);

        DefaultListModel listModel = new DefaultListModel();
        int i = (year - 30);
        for (; i <= year + 1;) {
            listModel.addElement("" + i);
            i++;
        }
        bacYearList.setModel(listModel);
    }

    public void Filling_Data(int numcard) {

        Statement stm = null;
        ResultSet res = null;
        df = (DefaultTableModel) jTable2.getModel();
        df.setRowCount(0);
        String Query1 = "SELECT Name_Resident,LastName_Resident,Name_ResidentFr,LastName_ResidentFr,NumCard_Resident,imageStd,DateBirth,PlaceBirth,PlaceBirthFr,\n"
                + "Name_Father,FullName_Mother,LastNamMothAR,Name_FatherFr,Name_MotherFr,LastName_MotheFr,Num_InscritBac,DateInscrp,BacMoyen,PlaceGetBac,\n"
                + "BacYear,BacMoyen,SituationFamily,Name_Daira,Name_Commune,NameWilaya,Address,ProfessionMother,ProfessionFather,\n"
                + "Nationalite,Nationalite_Fr,BranchStd_Name,BranchStd_NameFr,NameFact,DescriptionLevel,Level_study,Room_Code,Resident_Case\n"
                + "FROM Resident_Gl,Student_Res,Nationalite,Branch_Study,Faculty,Level_Study,Room,Wilaya,Resident_Case \n"
                + "WHERE \n"
                + "Student_Res.Id_Nationalite=Nationalite.Id_Nationalite\n"
                + "AND Student_Res.Id_BranchStd=Branch_Study.Id_BranchStd\n"
                + "AND Student_Res.ID_Wilaya=Wilaya.ID_Wilaya\n"
                + "\n"
                + "AND Student_Res.Id_Faculty=Faculty.Id_Faculty\n"
                + "AND Student_Res.Id_LevelStudy=Level_Study.Id_LevelStudy\n"
                + "AND Student_Res.Id_Room=Room.Id_Room\n"
                + "AND Resident_Gl.ID_Rsident=Student_Res.ID_Rsident\n"
                + "AND Resident_Gl.ID_Case_Resident=Resident_Case.ID_Case_Resident "
                + "AND Resident_Gl.Id_Ptrn_Res=1 order by Room_Code ";

//"AND Resident_Gl.Id_Ptrn_Res=1 AND NumCard_Resident="+numcard+" order by Room_Code ";
        cnx.Connecting();

        try {
            stm = cnx.getConnect().createStatement();
            res = stm.executeQuery(Query1);
            Object arg[] = null;
            ImageIcon image;
            while (res.next()) {

                if (res.getBytes("imageStd") != null) {
                    image = new ImageIcon(
                            new ImageIcon(res.getBytes("imageStd")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
                } else {
                    image = null;
                }

                arg = new Object[]{
                    res.getString("Resident_Case"),
                    res.getString("Room_Code"),
                    res.getString("Level_study"),
                    res.getString("BranchStd_Name"),
                    res.getString("Nationalite"),
                    res.getString("NameWilaya"),
                    res.getString("Name_Commune"),
                    res.getString("Name_Daira"),
                    res.getInt("BacYear"),
                    res.getString("Num_InscritBac"),
                    res.getString("PlaceBirth"),
                    res.getDate("DateBirth"),
                    res.getString("LastName_ResidentFr"),
                    res.getString("Name_ResidentFr"),
                    res.getString("LastName_Resident"),
                    res.getString("Name_Resident"),
                    res.getString("NumCard_Resident"),
                    image

                };
                df.addRow(arg);
            }
            //jTable2.setModel(df);
            //  df.addRow(arg);

            //System.out.println(arg.toString());
            
            
            counStd.setText("" + jTable2.getRowCount());
        } catch (SQLException e) {
            System.err.println(e);
        }

        try {
            res.close();
            stm.close();
            cnx.Deconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        CheckLevel = new javax.swing.JCheckBox();
        CheckCase = new javax.swing.JCheckBox();
        CheckCommune = new javax.swing.JCheckBox();
        CheckWilaya = new javax.swing.JCheckBox();
        CheckBranch = new javax.swing.JCheckBox();
        CheckBloc = new javax.swing.JCheckBox();
        CheckDaira = new javax.swing.JCheckBox();
        PanCase = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Resident_Case = new javax.swing.JList<>();
        PanWilaya = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        wilaya_List = new javax.swing.JList<>();
        PanBranch = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        Branch_Study = new javax.swing.JList<>();
        PanLevel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        Level_Study = new javax.swing.JList<>();
        PanCommune = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Name_Commune = new javax.swing.JList<>();
        NameWilaya = new javax.swing.JComboBox<>();
        PanDaira = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Name_Daira = new javax.swing.JList<>();
        PanBloc = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        Pavilion = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        CheckRoom = new javax.swing.JCheckBox();
        RomPvinPanInf = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        PanBacYear = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        bacYearList = new javax.swing.JList<>();
        CheckBacYear = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtname = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        txtBirthplace = new javax.swing.JTextField();
        txtCom = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDair = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtBranch = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtLevel = new javax.swing.JTextField();
        txtChambre = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtCase = new javax.swing.JTextField();
        txtBacYear = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtNumCrd = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        counStd = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
        });
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        jTable2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "null", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12", "null", "Title 14", "Title 15", "Title 16", "Title 17", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable2.setRowHeight(40);
        jTable2.setRowMargin(0);
        jTable2.setShowVerticalLines(false);
        jTable2.getTableHeader().setResizingAllowed(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable2.setDefaultRenderer(String.class, centerRenderer);

        jTable2.getColumnModel().getColumn(17).setPreferredWidth(50);
        JTableHeader headTab = jTable2.getTableHeader();
        headTab.setBackground(new java.awt.Color(221,160,221));
        headTab.setFont(new java.awt.Font("Times New Roman",Font.BOLD, 20));
        headTab.setDefaultRenderer(centerRenderer);
        headTab.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane2.setViewportView(jTable2);

        jPanel3.setLayout(new java.awt.CardLayout());

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CheckLevel.setBackground(new java.awt.Color(255, 255, 255));
        CheckLevel.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        CheckLevel.setText("المستوى الدراسي");
        CheckLevel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CheckLevel.setBorderPainted(true);
        CheckLevel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CheckLevel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        CheckLevel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        CheckLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckLevelActionPerformed(evt);
            }
        });
        jPanel9.add(CheckLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 10, 150, 30));

        CheckCase.setBackground(new java.awt.Color(255, 255, 255));
        CheckCase.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        CheckCase.setText("الحالة");
        CheckCase.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CheckCase.setBorderPainted(true);
        CheckCase.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CheckCase.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        CheckCase.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        CheckCase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckCaseActionPerformed(evt);
            }
        });
        jPanel9.add(CheckCase, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 10, 100, 30));

        CheckCommune.setBackground(new java.awt.Color(255, 255, 255));
        CheckCommune.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        CheckCommune.setText("البلدية");
        CheckCommune.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CheckCommune.setBorderPainted(true);
        CheckCommune.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CheckCommune.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        CheckCommune.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        CheckCommune.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckCommuneActionPerformed(evt);
            }
        });
        jPanel9.add(CheckCommune, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 180, 30));

        CheckWilaya.setBackground(new java.awt.Color(255, 255, 255));
        CheckWilaya.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        CheckWilaya.setText("الولاية");
        CheckWilaya.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CheckWilaya.setBorderPainted(true);
        CheckWilaya.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CheckWilaya.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        CheckWilaya.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        CheckWilaya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckWilayaActionPerformed(evt);
            }
        });
        jPanel9.add(CheckWilaya, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 70, 30));

        CheckBranch.setBackground(new java.awt.Color(255, 255, 255));
        CheckBranch.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        CheckBranch.setText("التخصص");
        CheckBranch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CheckBranch.setBorderPainted(true);
        CheckBranch.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CheckBranch.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        CheckBranch.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        CheckBranch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBranchActionPerformed(evt);
            }
        });
        jPanel9.add(CheckBranch, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, 150, 30));

        CheckBloc.setBackground(new java.awt.Color(255, 255, 255));
        CheckBloc.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        CheckBloc.setText("الجناح");
        CheckBloc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CheckBloc.setBorderPainted(true);
        CheckBloc.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CheckBloc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        CheckBloc.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        CheckBloc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBlocActionPerformed(evt);
            }
        });
        jPanel9.add(CheckBloc, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 70, 30));

        CheckDaira.setBackground(new java.awt.Color(255, 255, 255));
        CheckDaira.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        CheckDaira.setText("الدائرة");
        CheckDaira.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CheckDaira.setBorderPainted(true);
        CheckDaira.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CheckDaira.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        CheckDaira.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        CheckDaira.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckDairaActionPerformed(evt);
            }
        });
        jPanel9.add(CheckDaira, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 80, 30));

        PanCase.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Resident_Case.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        Resident_Case.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        Resident_Case.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(Resident_Case);

        javax.swing.GroupLayout PanCaseLayout = new javax.swing.GroupLayout(PanCase);
        PanCase.setLayout(PanCaseLayout);
        PanCaseLayout.setHorizontalGroup(
            PanCaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
        );
        PanCaseLayout.setVerticalGroup(
            PanCaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
        );

        jPanel9.add(PanCase, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 50, 100, 155));

        PanWilaya.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        wilaya_List.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        wilaya_List.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        wilaya_List.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane8.setViewportView(wilaya_List);

        javax.swing.GroupLayout PanWilayaLayout = new javax.swing.GroupLayout(PanWilaya);
        PanWilaya.setLayout(PanWilayaLayout);
        PanWilayaLayout.setHorizontalGroup(
            PanWilayaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
        );
        PanWilayaLayout.setVerticalGroup(
            PanWilayaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
        );

        jPanel9.add(PanWilaya, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, 80, 155));

        PanBranch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Branch_Study.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        Branch_Study.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        Branch_Study.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane6.setViewportView(Branch_Study);

        javax.swing.GroupLayout PanBranchLayout = new javax.swing.GroupLayout(PanBranch);
        PanBranch.setLayout(PanBranchLayout);
        PanBranchLayout.setHorizontalGroup(
            PanBranchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
        );
        PanBranchLayout.setVerticalGroup(
            PanBranchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
        );

        jPanel9.add(PanBranch, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 50, 150, 155));

        PanLevel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Level_Study.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        Level_Study.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        Level_Study.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane5.setViewportView(Level_Study);

        javax.swing.GroupLayout PanLevelLayout = new javax.swing.GroupLayout(PanLevel);
        PanLevel.setLayout(PanLevelLayout);
        PanLevelLayout.setHorizontalGroup(
            PanLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
        );
        PanLevelLayout.setVerticalGroup(
            PanLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
        );

        jPanel9.add(PanLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 50, 150, 155));

        PanCommune.setBackground(new java.awt.Color(255, 255, 255));
        PanCommune.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Name_Commune.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        Name_Commune.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        Name_Commune.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane4.setViewportView(Name_Commune);

        NameWilaya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameWilayaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanCommuneLayout = new javax.swing.GroupLayout(PanCommune);
        PanCommune.setLayout(PanCommuneLayout);
        PanCommuneLayout.setHorizontalGroup(
            PanCommuneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanCommuneLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(NameWilaya, 0, 76, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanCommuneLayout.setVerticalGroup(
            PanCommuneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
            .addGroup(PanCommuneLayout.createSequentialGroup()
                .addComponent(NameWilaya, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel9.add(PanCommune, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 50, 180, 155));

        PanDaira.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Name_Daira.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        Name_Daira.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        Name_Daira.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane3.setViewportView(Name_Daira);

        javax.swing.GroupLayout PanDairaLayout = new javax.swing.GroupLayout(PanDaira);
        PanDaira.setLayout(PanDairaLayout);
        PanDairaLayout.setHorizontalGroup(
            PanDairaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
        );
        PanDairaLayout.setVerticalGroup(
            PanDairaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
        );

        jPanel9.add(PanDaira, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, 80, 155));

        PanBloc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Pavilion.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        Pavilion.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        Pavilion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Pavilion.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                PavilionValueChanged(evt);
            }
        });
        jScrollPane7.setViewportView(Pavilion);

        javax.swing.GroupLayout PanBlocLayout = new javax.swing.GroupLayout(PanBloc);
        PanBloc.setLayout(PanBlocLayout);
        PanBlocLayout.setHorizontalGroup(
            PanBlocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
        );
        PanBlocLayout.setVerticalGroup(
            PanBlocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
        );

        jPanel9.add(PanBloc, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, 80, 155));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/excel (1).png"))); // NOI18N
        jButton1.setText("قامة الطلبة 02 ");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, 35));

        CheckRoom.setBackground(new java.awt.Color(255, 255, 255));
        CheckRoom.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        CheckRoom.setText("الغرفة");
        CheckRoom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CheckRoom.setBorderPainted(true);
        CheckRoom.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CheckRoom.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        CheckRoom.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        CheckRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckRoomActionPerformed(evt);
            }
        });
        jPanel9.add(CheckRoom, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 70, 30));

        RomPvinPanInf.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel9.add(RomPvinPanInf, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, 70, 30));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton2.setText("قائمة الطلبة الراسبين");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 120, 40));

        PanBacYear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        bacYearList.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        bacYearList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        bacYearList.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bacYearList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                bacYearListValueChanged(evt);
            }
        });
        jScrollPane9.setViewportView(bacYearList);

        javax.swing.GroupLayout PanBacYearLayout = new javax.swing.GroupLayout(PanBacYear);
        PanBacYear.setLayout(PanBacYearLayout);
        PanBacYearLayout.setHorizontalGroup(
            PanBacYearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
        );
        PanBacYearLayout.setVerticalGroup(
            PanBacYearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
        );

        jPanel9.add(PanBacYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 80, 155));

        CheckBacYear.setBackground(new java.awt.Color(255, 255, 255));
        CheckBacYear.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        CheckBacYear.setText("سنة البكالوريا");
        CheckBacYear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CheckBacYear.setBorderPainted(true);
        CheckBacYear.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CheckBacYear.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        CheckBacYear.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        CheckBacYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBacYearActionPerformed(evt);
            }
        });
        jPanel9.add(CheckBacYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 80, 30));

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/excel (1).png"))); // NOI18N
        jButton3.setText("قامة الطلبة01 ");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 110, 35));

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton5.setText("استمارة المعلومات");
        jButton5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 120, 40));

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 0, 30));

        jPanel3.add(jPanel9, "card2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 88, -1, -1));

        txtname.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtname.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnameKeyReleased(evt);
            }
        });
        jPanel2.add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(1288, 52, 73, 30));

        txtLastName.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtLastName.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLastNameActionPerformed(evt);
            }
        });
        txtLastName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLastNameKeyReleased(evt);
            }
        });
        jPanel2.add(txtLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(1202, 52, 68, 30));

        txtBirthplace.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtBirthplace.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBirthplace.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBirthplaceKeyReleased(evt);
            }
        });
        jPanel2.add(txtBirthplace, new org.netbeans.lib.awtextra.AbsoluteConstraints(1104, 52, 80, 30));

        txtCom.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtCom.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtComKeyReleased(evt);
            }
        });
        jPanel2.add(txtCom, new org.netbeans.lib.awtextra.AbsoluteConstraints(1006, 52, 80, 30));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("الاسم");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1288, 12, 73, 34));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("اللقب");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1202, 12, 70, 34));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("مكان الميلاد");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1104, 12, 80, 34));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("البلــدية");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1006, 12, 80, 34));

        txtDair.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtDair.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDair.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDairKeyReleased(evt);
            }
        });
        jPanel2.add(txtDair, new org.netbeans.lib.awtextra.AbsoluteConstraints(908, 52, 80, 30));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("الدائرة");
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(908, 12, 80, 34));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("التخصص");
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 12, 80, 34));

        txtBranch.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtBranch.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBranch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBranchActionPerformed(evt);
            }
        });
        txtBranch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBranchKeyReleased(evt);
            }
        });
        jPanel2.add(txtBranch, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 52, 80, 30));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("المستوى");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(712, 12, 80, 34));

        txtLevel.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtLevel.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtLevel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLevelKeyReleased(evt);
            }
        });
        jPanel2.add(txtLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(712, 52, 80, 30));

        txtChambre.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtChambre.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtChambre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtChambreKeyReleased(evt);
            }
        });
        jPanel2.add(txtChambre, new org.netbeans.lib.awtextra.AbsoluteConstraints(614, 52, 80, 30));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("الغرفة/الجناح");
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(614, 12, 80, 34));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("سنة البكالوريا");
        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(418, 16, 80, 30));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("الحالة");
        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(516, 16, 80, 30));

        txtCase.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtCase.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCaseKeyReleased(evt);
            }
        });
        jPanel2.add(txtCase, new org.netbeans.lib.awtextra.AbsoluteConstraints(516, 52, 80, 30));

        txtBacYear.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtBacYear.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBacYear.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBacYearKeyReleased(evt);
            }
        });
        jPanel2.add(txtBacYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(418, 52, 80, 30));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("عدد الطلبة:");
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 16, 91, 30));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("رقم البطاقة");
        jLabel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(328, 16, 80, 30));

        txtNumCrd.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtNumCrd.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtNumCrd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumCrdKeyReleased(evt);
            }
        });
        jPanel2.add(txtNumCrd, new org.netbeans.lib.awtextra.AbsoluteConstraints(328, 52, 80, 30));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/excel (1).png"))); // NOI18N
        jLabel14.setToolTipText("قائمة الطلبة");
        jLabel14.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 48, 55, -1));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("طباعة قائمة الطلبة:");
        jLabel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 52, 90, 30));

        counStd.setBackground(new java.awt.Color(255, 255, 255));
        counStd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        counStd.setForeground(new java.awt.Color(255, 0, 51));
        counStd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        counStd.setText("2");
        counStd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        counStd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                counStdMouseClicked(evt);
            }
        });
        jPanel2.add(counStd, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 15, 44, 30));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("مسح");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(271, 52, 51, 30));

        getContentPane().add(jPanel2, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnameKeyReleased
        FilterResidentMlt(txtname, txtLastName, txtBirthplace, txtCom,
                txtNumCrd, txtDair, txtBranch, txtLevel,
                txtBacYear, txtCase, txtChambre,
                jTable2, df);
        counStd.setText(jTable2.getRowCount() + "");
    }//GEN-LAST:event_txtnameKeyReleased

    private void txtLastNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLastNameKeyReleased
        FilterResidentMlt(txtname, txtLastName, txtBirthplace, txtCom,
                txtNumCrd, txtDair, txtBranch, txtLevel,
                txtBacYear, txtCase, txtChambre,
                jTable2, df);
        counStd.setText(jTable2.getRowCount() + "");
    }//GEN-LAST:event_txtLastNameKeyReleased

    private void txtBirthplaceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBirthplaceKeyReleased
        FilterResidentMlt(txtname, txtLastName, txtBirthplace, txtCom,
                txtNumCrd, txtDair, txtBranch, txtLevel,
                txtBacYear, txtCase, txtChambre,
                jTable2, df);
        counStd.setText(jTable2.getRowCount() + "");
    }//GEN-LAST:event_txtBirthplaceKeyReleased

    private void txtComKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComKeyReleased
        FilterResidentMlt(txtname, txtLastName, txtBirthplace, txtCom,
                txtNumCrd, txtDair, txtBranch, txtLevel,
                txtBacYear, txtCase, txtChambre,
                jTable2, df);
        counStd.setText(jTable2.getRowCount() + "");

    }//GEN-LAST:event_txtComKeyReleased

    private void txtLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLastNameActionPerformed

    private void CheckBranchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBranchActionPerformed

        Dispay_hide_panState(CheckBranch, PanBranch, Branch_Study);
    }//GEN-LAST:event_CheckBranchActionPerformed

    private void CheckCaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckCaseActionPerformed

        Dispay_hide_panState(CheckCase, PanCase, Resident_Case);
    }//GEN-LAST:event_CheckCaseActionPerformed

    private void CheckBlocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBlocActionPerformed

        Dispay_hide_panState(CheckBloc, PanBloc, Pavilion);
    }//GEN-LAST:event_CheckBlocActionPerformed

    private void CheckDairaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckDairaActionPerformed

        Dispay_hide_panState(CheckDaira, PanDaira, Name_Daira);
    }//GEN-LAST:event_CheckDairaActionPerformed

    private void CheckWilayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckWilayaActionPerformed

        Dispay_hide_panState(CheckWilaya, PanWilaya, wilaya_List);

    }//GEN-LAST:event_CheckWilayaActionPerformed

    private void CheckCommuneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckCommuneActionPerformed

        Dispay_hide_panState(CheckCommune, PanCommune, Name_Commune);
    }//GEN-LAST:event_CheckCommuneActionPerformed

    private void CheckLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckLevelActionPerformed
        Dispay_hide_panState(CheckLevel, PanLevel, Level_Study);
    }//GEN-LAST:event_CheckLevelActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            this.GetDataWithMultMethod(0);
        } catch (IOException ex) {
            Logger.getLogger(Advanced_prp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void CheckRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckRoomActionPerformed
        if (CheckRoom.isSelected()) {
            RomPvinPanInf.setVisible(true);
        } else {
            RomPvinPanInf.setVisible(false);
        }
    }//GEN-LAST:event_CheckRoomActionPerformed

    private void PavilionValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_PavilionValueChanged
        rom.FillComboboxRooms(RomPvinPanInf, Pavilion.getSelectedValue());
    }//GEN-LAST:event_PavilionValueChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//       try {
//           DefaultTableModel dm= (DefaultTableModel) jTable2.getModel();
//           JOptionPane.showMessageDialog(null, dm.getRowCount()); 
//           writeToExcell(jTable2,dm);
//           
//       } catch (IOException ex) {
//           ex.printStackTrace();
//       }

//filling.ListStudentPrecipitate();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void counStdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_counStdMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_counStdMouseClicked

    private void txtDairKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDairKeyReleased
        FilterResidentMlt(txtname, txtLastName, txtBirthplace, txtCom,
                txtNumCrd, txtDair, txtBranch, txtLevel,
                txtBacYear, txtCase, txtChambre,
                jTable2, df);
        counStd.setText(jTable2.getRowCount() + "");
    }//GEN-LAST:event_txtDairKeyReleased

    private void txtBranchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBranchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBranchActionPerformed

    private void txtBranchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBranchKeyReleased
        FilterResidentMlt(txtname, txtLastName, txtBirthplace, txtCom,
                txtNumCrd, txtDair, txtBranch, txtLevel,
                txtBacYear, txtCase, txtChambre,
                jTable2, df);
        counStd.setText(jTable2.getRowCount() + "");
    }//GEN-LAST:event_txtBranchKeyReleased

    private void txtLevelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLevelKeyReleased
        FilterResidentMlt(txtname, txtLastName, txtBirthplace, txtCom,
                txtNumCrd, txtDair, txtBranch, txtLevel,
                txtBacYear, txtCase, txtChambre,
                jTable2, df);
        counStd.setText(jTable2.getRowCount() + "");
    }//GEN-LAST:event_txtLevelKeyReleased

    private void txtChambreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChambreKeyReleased
        FilterResidentMlt(txtname, txtLastName, txtBirthplace, txtCom,
                txtNumCrd, txtDair, txtBranch, txtLevel,
                txtBacYear, txtCase, txtChambre,
                jTable2, df);
        counStd.setText(jTable2.getRowCount() + "");
    }//GEN-LAST:event_txtChambreKeyReleased

    private void txtCaseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCaseKeyReleased
        FilterResidentMlt(txtname, txtLastName, txtBirthplace, txtCom,
                txtNumCrd, txtDair, txtBranch, txtLevel,
                txtBacYear, txtCase, txtChambre,
                jTable2, df);
        counStd.setText(jTable2.getRowCount() + "");
    }//GEN-LAST:event_txtCaseKeyReleased

    private void txtBacYearKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBacYearKeyReleased
        FilterResidentMlt(txtname, txtLastName, txtBirthplace, txtCom,
                txtNumCrd, txtDair, txtBranch, txtLevel,
                txtBacYear, txtCase, txtChambre,
                jTable2, df);
        counStd.setText(jTable2.getRowCount() + "");
    }//GEN-LAST:event_txtBacYearKeyReleased

    private void txtNumCrdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumCrdKeyReleased
        FilterResidentMlt(txtname, txtLastName, txtBirthplace, txtCom,
                txtNumCrd, txtDair, txtBranch, txtLevel,
                txtBacYear, txtCase, txtChambre,
                jTable2, df);
        counStd.setText(jTable2.getRowCount() + "");
    }//GEN-LAST:event_txtNumCrdKeyReleased

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        DefaultTableModel dm = (DefaultTableModel) jTable2.getModel();

        try {
            writeToExcell(jTable2, dm);
        } catch (IOException ex) {
            Logger.getLogger(Advanced_prp.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Desktop dt = Desktop.getDesktop();
            // dt.open(new File("src\\OurFile\\AppClose.xlsx"));
            dt.open(new File("D:\\List_Etudiants.xlsx"));  //when exporting with jtable
            //  dt.open(new File(""+FullNam.getText()+".xlsx"));

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error in Opened The File");
        }
    }//GEN-LAST:event_jLabel14MouseClicked

    public void ClearTxtField() {
        txtBacYear.setText("");
        txtBirthplace.setText("");
        txtBranch.setText("");
        txtCase.setText("");
        txtChambre.setText("");
        txtCom.setText("");
        txtDair.setText("");
        txtLastName.setText("");
        txtLevel.setText("");
        txtNumCrd.setText("");
        txtname.setText("");
    }

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        ClearTxtField();
        FilterResidentMlt(txtname, txtLastName, txtBirthplace, txtCom,
                txtNumCrd, txtDair, txtBranch, txtLevel,
                txtBacYear, txtCase, txtChambre,
                jTable2, df);
        counStd.setText(jTable2.getRowCount() + "");
    }//GEN-LAST:event_jLabel1MouseClicked

    private void bacYearListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_bacYearListValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_bacYearListValueChanged

    private void CheckBacYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBacYearActionPerformed
        Dispay_hide_panState(CheckBacYear, PanBacYear, bacYearList);
    }//GEN-LAST:event_CheckBacYearActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            this.GetDataWithMultMethod(1);
        } catch (IOException ex) {
            Logger.getLogger(Advanced_prp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    public void FillTableCommune_Jlist(String Wly, JList list) {

        //dfm=(DefaultTableModel)jTable2.getModel();
        String Query = "SELECT * FROM Commune,Wilaya where Commune.ID_Wilaya=Wilaya.ID_Wilaya "
                + " AND Wilaya.NameWilaya=N'" + Wly + "' ;";

        Statement stm = null;
        ResultSet Res = null;
        /**
         * *******************************************************
         */

        DefaultListModel listModel = new DefaultListModel();
        listModel.removeAllElements();
//       listModel= list.;
//        listModel.removeAllElements();
        cnx.Connecting();
        try {
            stm = cnx.getConnect().createStatement();
            Res = stm.executeQuery(Query);

            while (Res.next()) {
                listModel.addElement(Res.getString("Commune_Ar"));
            }
            list.setModel(listModel);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error In Fill Table Of managers " + e.getMessage());
        }
        /**
         * ***************************************************
         */
        try {
            stm.close();
            Res.close();
            cnx.Deconnect();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error In Close Statement" + e.getMessage());
        }
    }

    private void NameWilayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameWilayaActionPerformed
        FillTableCommune_Jlist((String) NameWilaya.getSelectedItem(), Name_Commune);
    }//GEN-LAST:event_NameWilayaActionPerformed

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden

        this.cnf.h.CloseAdvancedprp();
        //this.cnf.h.F.setVisible(true);
        //JOptionPane.showMessageDialog(null, "Hidden Compenet Test");
    }//GEN-LAST:event_formComponentHidden

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            JasperReport jasperreport;
            InputStream file = getClass().getResourceAsStream("/Reports/Form_Student_Information.jrxml");
            JasperDesign jasperdesign = JRXmlLoader.load(file);

            jasperreport = JasperCompileManager.compileReport(jasperdesign);
            /*  Connection Cnx1;
         cnx.Connecting();
        Cnx1 =cnx.getConnect();*/
            JasperPrint jasperprint = JasperFillManager.fillReport(jasperreport, null, new JREmptyDataSource());
            // jp=JasperFillManager.fillReport(jr, parametres, cnx.getConnect());
            JasperViewer JspViewr = new JasperViewer(jasperprint, false);
            JspViewr.viewReport(jasperprint, false);
        } catch (JRException ex) {
            Logger.getLogger(Advanced_prp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed
    /**
     * *********
     */

    public void TestCard_Pavillon_Mult_Choice(String pav) {
        try {
            String Query1 = "SELECT  Name_Resident,LastName_Resident,Name_ResidentFr,LastName_ResidentFr,imageStd,CodeBare_Resident,\n"
                    + "NumCard_Resident,DateBirth,PlaceBirth,PlaceBirthFr,\n"
                    + "Name_Father,FullName_Mother,LastNamMothAR,Name_FatherFr,Name_MotherFr,\n"
                    + "LastName_MotheFr,Num_InscritBac,DateInscrp,BacMoyen,PlaceGetBac,\n"
                    + "BacYear,SituationFamily,Name_Daira,\n"
                    + "Name_Commune,NameWilaya,Address,ProfessionMother,\n"
                    + "ProfessionFather,Branch_Study.BranchStd_Name,Branch_Study.branch_code,\n"
                    + "Branch_Study.BranchStd_NameFr,Nationalite,Nationalite_Fr,NameFact,DescriptionLevel,\n"
                    + "Level_study,Room_Code,Resident_Case,Wilaya.NumWilaya,\n"
                    + "Resident_Gl.ID_gender\n"
                    + "FROM Resident_Gl,Student_Res,Nationalite,Branch_Study,Faculty,Level_Study,Room,Wilaya,\n"
                    + "Resident_Case,Gender,Pavilion \n"
                    + "WHERE Student_Res.Id_Nationalite=Nationalite.Id_Nationalite  \n"
                    + "AND Student_Res.Id_BranchStd=Branch_Study.Id_BranchStd  \n"
                    + "AND Student_Res.ID_Wilaya=Wilaya.ID_Wilaya  \n"
                    + "AND Student_Res.Id_Faculty=Faculty.Id_Faculty \n"
                    + "AND Gender.ID_gender=Resident_Gl.ID_gender\n"
                    + "AND Student_Res.Id_LevelStudy=Level_Study.Id_LevelStudy \n"
                    + "AND Student_Res.Id_Room=Room.Id_Room \n"
                    + "AND Room.ID_Pavilion=Pavilion.ID_Pavilion\n"
                    + "AND Resident_Gl.ID_Rsident=Student_Res.ID_Rsident \n"
                    + "AND Resident_Gl.ID_Case_Resident=Resident_Case.ID_Case_Resident   \n"
                    + "AND Resident_Gl.Id_Ptrn_Res=1 ";
            /**
             * **************************************
             */
            List<String> list = null;
            /**
             * **************************************************************
             */
            if (CheckCase.isSelected()) {
                String Cond = " ";

                if (Resident_Case.getSelectedIndex() == -1) {
                    Query1 += " ";
                } else {
                    list = Resident_Case.getSelectedValuesList();
                    Cond = " AND Resident_Case.Resident_Case in( ";

                    int sizeList = list.size();
                    for (int i = 0; i < sizeList; i++) {

                        if (i < sizeList - 1) {
                            Cond += "N'" + list.get(i) + "', ";  //when selected first elements in list 
                        } else {
                            Cond += "N'" + list.get(i) + "' ";     //when 
                        }
                    }
                    Cond += " ) ";
                }
                Query1 += Cond;
            }
            /**
             * ********************************************************
             */
            if (CheckBranch.isSelected()) {
                String Cond = " ";
                if (Branch_Study.getSelectedIndex() == -1) {
                    Query1 += " ";
                } else {
                    list = Branch_Study.getSelectedValuesList();
                    Cond = " AND Branch_Study.BranchStd_Name in( ";
                    int sizeList = list.size();
                    for (int i = 0; i < sizeList; i++) {
                        if (i < sizeList - 1) {
                            Cond += "N'" + list.get(i) + "', ";  //when selected first elements in list 
                        } else {
                            Cond += "N'" + list.get(i) + "' ";     //when 
                        }
                    }
                    Cond += " ) ";
                }
                Query1 += Cond;
            }
            /**
             * *********************************************************
             */
            if (CheckLevel.isSelected()) {
                String Cond = " ";
                if (Level_Study.getSelectedIndex() == -1) {
                    Query1 += " ";
                } else {
                    list = Level_Study.getSelectedValuesList();
                    Cond = " AND Level_Study.DescriptionLevel in( ";

                    int sizeList = list.size();
                    for (int i = 0; i < sizeList; i++) {
                        if (i < sizeList - 1) {
                            Cond += "N'" + list.get(i) + "', ";  //when selected first elements in list 
                        } else {
                            Cond += "N'" + list.get(i) + "' ";     //when 
                        }
                    }
                    Cond += " ) ";
                }
                Query1 += Cond;
            }
            /**
             * *******************************************************************
             */

            if (CheckBacYear.isSelected()) {
                String Cond = " ";
                if (bacYearList.getSelectedIndex() == -1) {
                    Query1 += " ";
                } else {
                    list = bacYearList.getSelectedValuesList();
                    Cond = " AND Student_Res.BacYear in( ";

                    int sizeList = list.size();

                    int year = 0;
                    for (int i = 0; i < sizeList; i++) {

                        //JOptionPane.showMessageDialog(null, "The Year is :"+list.get(i));
                        year = Integer.parseInt(list.get(i));

                        //  JOptionPane.showMessageDialog(null, "The Year is :"+year);
                        if (i < sizeList - 1) {

                            Cond += " " + year + ", ";  //when selected first elements in list 
                        } else {
                            Cond += " " + year + " ";     //when 
                        }
                    }
                    Cond += " ) ";
                }
                Query1 += Cond;
            }
            /**
             * ********************************************************************
             */
            if (CheckCommune.isSelected()) {
                String Cond = " ";
                if (Name_Commune.getSelectedIndex() == -1) {
                    Query1 += " ";

                } else {
                    list = Name_Commune.getSelectedValuesList();
                    Cond = " AND Student_Res.Name_Commune in( ";
                    int sizeList = list.size();
                    for (int i = 0; i < sizeList; i++) {
                        if (i < sizeList - 1) {
                            Cond += "N'" + list.get(i) + "', ";  //when selected first elements in list 
                        } else {
                            Cond += "N'" + list.get(i) + "' ";     //when 
                        }
                    }
                    Cond += " ) ";
                }
                Query1 += Cond;
            }
            /**
             * *******************************************************************
             */
            if (CheckWilaya.isSelected()) {
                String Cond = " ";
                if (wilaya_List.getSelectedIndex() == -1) {
                    Query1 += " ";
                } else {
                    list = wilaya_List.getSelectedValuesList();
                    Cond = " AND Wilaya.NameWilaya in( ";
                    int sizeList = list.size();
                    for (int i = 0; i < sizeList; i++) {
                        if (i < sizeList - 1) {
                            Cond += "N'" + list.get(i) + "', ";  //when selected first elements in list 
                        } else {
                            Cond += "N'" + list.get(i) + "' ";     //when 
                        }
                    }
                    Cond += " ) ";
                }
                Query1 += Cond;
            }
            /**
             * ********************************************************************
             */
            if (CheckDaira.isSelected()) {
                String Cond = " ";
                if (Name_Daira.getSelectedIndex() == -1) {
                    Query1 += " ";
                } else {
                    list = Name_Daira.getSelectedValuesList();
                    Cond = " AND Student_Res.Name_Daira in( ";
                    int sizeList = list.size();
                    for (int i = 0; i < sizeList; i++) {
                        if (i < sizeList - 1) {
                            Cond += "N'" + list.get(i) + "', ";  //when selected first elements in list 
                        } else {
                            Cond += "N'" + list.get(i) + "' ";     //when 
                        }
                    }
                    Cond += " ) ";
                }
                Query1 += Cond;
            }
            /**
             * *********************************************************************
             */
            if (CheckBloc.isSelected()) {
                String Cond = " ";
                if (Pavilion.getSelectedIndex() == -1) {
                    Query1 += " ";
                } else {
                    list = Pavilion.getSelectedValuesList();
                    Cond = " AND Pavilion.Pavilion_Name in( ";
                    int sizeList = list.size();
                    for (int i = 0; i < sizeList; i++) {
                        if (i < sizeList - 1) {
                            Cond += "'" + list.get(i) + "', ";  //when selected first elements in list 
                        } else {
                            Cond += "'" + list.get(i) + "' ";     //when 
                        }
                    }
                    Cond += " ) ";
                }
                Query1 += Cond;
            }
            /**
             * **********************************************************************
             */
            if (CheckRoom.isSelected()) {

                String Cond = " ";

                if (RomPvinPanInf.getSelectedIndex() == -1) {
                    Query1 += " ";
                } else {

                    String romNam = " ";
                    romNam = (String) RomPvinPanInf.getSelectedItem();
                    Cond += " AND Room.Room_Code = '" + romNam + "' ";
                }
                Query1 += Cond;
            }
            /**
             * **************************************
             */

            JasperReport jasperreport;
            // InputStream file=getClass().getResourceAsStream("/Reports/CardResident.jrxml");
            InputStream file = getClass().getResourceAsStream("/Reports/NewReportCard_1_1.jrxml");
            //NewReportCard
            JasperDesign jasperdesign = JRXmlLoader.load(file);
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(Query1);
            jasperdesign.setQuery(newQuery);
            jasperreport = JasperCompileManager.compileReport(jasperdesign);
            //JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametres, Cnx1);
            this.cnx.Connecting();
            Connection cnxt = this.cnx.getConnect();
            JasperPrint jasperprint = JasperFillManager.fillReport(jasperreport, null, cnxt);

            JasperViewer JspViewr = new JasperViewer(jasperprint, false);
            JspViewr.viewReport(jasperprint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ****************
     */
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        TestCard_Pavillon_Mult_Choice("");
    }//GEN-LAST:event_jButton4ActionPerformed
    private static void writeToExcell(JTable table, DefaultTableModel model) throws FileNotFoundException, IOException {
        WorkbookFactory workbookFactory = new WorkbookFactory();
        Workbook wb = new XSSFWorkbook(); //Excell workbook
        Sheet sheet = wb.createSheet(); //WorkSheet
        Row row = sheet.createRow(2); //Row created at line 3
        // TableModel model = table.getModel(); //Table model

        Row headerRow = sheet.createRow(0); //Create row at line 0
        for (int headings = 0; headings < model.getColumnCount(); headings++) { //For each column
            headerRow.createCell(headings).setCellValue(model.getColumnName(headings));//Write column name
        }

        for (int rows = 0; rows < table.getRowCount(); rows++) { //For each table row
            for (int cols = 0; cols < table.getColumnCount(); cols++) { //For each table column
                row.createCell(cols).setCellValue(table.getValueAt(rows, cols).toString()); //Write value
            }

            //Set the row to the next one in the sequence 
            row = sheet.createRow((rows + 3));
        }
        wb.write(new FileOutputStream("D:\\List_Etudiants.xlsx"));//Save the file     
    }

    public void Dispay_hide_panState(JCheckBox ch, JPanel pan, JList lst) {
        if (ch.isSelected()) {
            pan.setVisible(true);
        } else {
            pan.setVisible(false);
            lst.clearSelection();
        }
    }

    public void GetDataWithMultMethod(int choice) throws IOException {

        String Query1;
        if (choice == 0) {
            Query1 = "SELECT distinct Name_Resident,LastName_Resident,Name_ResidentFr,LastName_ResidentFr,NumCard_Resident,DateBirth,PlaceBirth,PlaceBirthFr,\n"
                    + "Name_Father,FullName_Mother,LastNamMothAR,Name_FatherFr,Name_MotherFr,LastName_MotheFr,Num_InscritBac,DateInscrp,PlaceGetBac,\n"
                    + "BacYear,BacMoyen,SituationFamily,Name_Daira,Name_Commune,NameWilaya,Address,ProfessionMother,ProfessionFather,\n"
                    + "Nationalite,Nationalite_Fr,BranchStd_Name,BranchStd_NameFr,NameFact,DescriptionLevel,Level_study,Room_Code,Resident_Case ,Branch_Study.branch_code, \n"
                    + "Branch_Study.domaine_Code,Branch_Study.domaine_Label,Branch_Study.domaine_Label_ar \n"
                    + "FROM Resident_Gl,Student_Res,Nationalite,Branch_Study,Faculty,Level_Study,Room,Wilaya,Resident_Case \n"
                    + "WHERE \n"
                    + "Student_Res.Id_Nationalite=Nationalite.Id_Nationalite \n"
                    + "AND Student_Res.Id_BranchStd=Branch_Study.Id_BranchStd \n"
                    + "AND Student_Res.ID_Wilaya=Wilaya.ID_Wilaya \n"
                    + "\n"
                    + "AND Student_Res.Id_Faculty=Faculty.Id_Faculty \n"
                    + "AND Student_Res.Id_LevelStudy=Level_Study.Id_LevelStudy \n"
                    + "AND Student_Res.Id_Room=Room.Id_Room \n"
                    + /*"AND Room.ID_Pavilion =Pavilion.ID_Pavilion \n"+*/ "AND Resident_Gl.ID_Rsident=Student_Res.ID_Rsident \n"
                    + "AND Resident_Gl.ID_Case_Resident=Resident_Case.ID_Case_Resident \n"
                    + "AND Resident_Gl.Id_Ptrn_Res=1 ";

            // GENDER NOT GETED IN THIS QUERY
        } else {
            /**
             * legacy code "FROM
             * Resident_Gl,Student_Res,Nationalite,Branch_Study,Faculty,Level_Study,Room,Wilaya,Resident_Case,Commune,Pavilion,Gender
             * \n" + //,Pavilion.Pavilion_Name
 *
             */

            Query1 = "SELECT distinct Name_Resident,LastName_Resident,Name_ResidentFr,LastName_ResidentFr,NumCard_Resident,DateBirth,PlaceBirth,PlaceBirthFr, \n"
                    + "Name_Father,FullName_Mother,LastNamMothAR,Name_FatherFr,Name_MotherFr,LastName_MotheFr,Num_InscritBac,DateInscrp,BacMoyen,PlaceGetBac,\n"
                    + "BacYear,SituationFamily,Name_Daira,Name_Commune,NameWilaya,Address,ProfessionMother,ProfessionFather,Branch_Study.BranchStd_Name,Branch_Study.branch_code,Branch_Study.BranchStd_NameFr, \n"
                    + "Nationalite,Nationalite_Fr,NameFact,DescriptionLevel,Level_study,Room_Code,Resident_Case,Wilaya.NumWilaya,Resident_Gl.ID_gender\n"
                    + "FROM Resident_Gl,Student_Res,Nationalite,Branch_Study,Faculty,Level_Study,Room,Wilaya,Resident_Case,Gender \n"
                    + //,Pavilion.Pavilion_Name
                    "WHERE  \n"
                    + "Student_Res.Id_Nationalite = Nationalite.Id_Nationalite  \n"
                    + "AND Student_Res.Id_BranchStd = Branch_Study.Id_BranchStd  \n"
                    + "AND Student_Res.ID_Wilaya = Wilaya.ID_Wilaya  \n"
                    + "AND Student_Res.Id_Faculty=Faculty.Id_Faculty \n"
                    + "AND Gender.ID_gender=Resident_Gl.ID_gender\n"
                    + "AND Student_Res.Id_LevelStudy=Level_Study.Id_LevelStudy \n"
                    + "AND Student_Res.Id_Room=Room.Id_Room \n"
                    + "AND Resident_Gl.ID_Rsident=Student_Res.ID_Rsident        \n"
                    + //"AND Pavilion.ID_Pavilion=Room.ID_Pavilion \n" +
                    "AND Resident_Gl.ID_Case_Resident=Resident_Case.ID_Case_Resident    \n"
                    + "AND Resident_Gl.Id_Ptrn_Res=1  \n";
        }

        List<String> list;
        /**
         * **************************************************************
         */
        if (CheckCase.isSelected()) {
            String Cond = " ";

            if (Resident_Case.getSelectedIndex() == -1) {
                Query1 += " ";
            } else {
                list = Resident_Case.getSelectedValuesList();
                Cond = " AND Resident_Case.Resident_Case in( ";

                int sizeList = list.size();
                for (int i = 0; i < sizeList; i++) {

                    if (i < sizeList - 1) {
                        Cond += "N'" + list.get(i) + "', ";  //when selected first elements in list 
                    } else {
                        Cond += "N'" + list.get(i) + "' ";     //when 
                    }
                }
                Cond += " ) ";
            }
            Query1 += Cond;
        }
        /**
         * ********************************************************
         */
        if (CheckBranch.isSelected()) {
            String Cond = " ";
            if (Branch_Study.getSelectedIndex() == -1) {
                Query1 += " ";
            } else {
                list = Branch_Study.getSelectedValuesList();
                Cond = " AND Branch_Study.BranchStd_Name in( ";
                int sizeList = list.size();
                for (int i = 0; i < sizeList; i++) {
                    if (i < sizeList - 1) {
                        Cond += "N'" + list.get(i) + "', ";  //when selected first elements in list 
                    } else {
                        Cond += "N'" + list.get(i) + "' ";     //when 
                    }
                }
                Cond += " ) ";
            }
            Query1 += Cond;
        }
        /**
         * *********************************************************
         */
        if (CheckLevel.isSelected()) {
            String Cond = " ";
            if (Level_Study.getSelectedIndex() == -1) {
                Query1 += " ";
            } else {
                list = Level_Study.getSelectedValuesList();
                Cond = " AND Level_Study.DescriptionLevel in( ";

                int sizeList = list.size();
                for (int i = 0; i < sizeList; i++) {
                    if (i < sizeList - 1) {
                        Cond += "N'" + list.get(i) + "', ";  //when selected first elements in list 
                    } else {
                        Cond += "N'" + list.get(i) + "' ";     //when 
                    }
                }
                Cond += " ) ";
            }
            Query1 += Cond;
        }
        /**
         * *******************************************************************
         */

        if (CheckBacYear.isSelected()) {
            String Cond = " ";
            if (bacYearList.getSelectedIndex() == -1) {
                Query1 += " ";
            } else {
                list = bacYearList.getSelectedValuesList();
                Cond = " AND Student_Res.BacYear in( ";

                int sizeList = list.size();

                int year = 0;
                for (int i = 0; i < sizeList; i++) {

                    //JOptionPane.showMessageDialog(null, "The Year is :"+list.get(i));
                    year = Integer.parseInt(list.get(i));

                    //  JOptionPane.showMessageDialog(null, "The Year is :"+year);
                    if (i < sizeList - 1) {

                        Cond += " " + year + ", ";  //when selected first elements in list 
                    } else {
                        Cond += " " + year + " ";     //when 
                    }
                }
                Cond += " ) ";
            }
            Query1 += Cond;
        }
        /**
         * ********************************************************************
         */
        if (CheckCommune.isSelected()) {
            String Cond = " ";
            if (Name_Commune.getSelectedIndex() == -1) {
                Query1 += " ";

            } else {
                list = Name_Commune.getSelectedValuesList();
                Cond = " AND Student_Res.Name_Commune in( ";
                int sizeList = list.size();
                for (int i = 0; i < sizeList; i++) {
                    if (i < sizeList - 1) {
                        Cond += "N'" + list.get(i) + "', ";  //when selected first elements in list 
                    } else {
                        Cond += "N'" + list.get(i) + "' ";     //when 
                    }
                }
                Cond += " ) ";
            }
            Query1 += Cond;
        }
        /**
         * *******************************************************************
         */
        if (CheckWilaya.isSelected()) {
            String Cond = " ";
            if (wilaya_List.getSelectedIndex() == -1) {
                Query1 += " ";
            } else {
                list = wilaya_List.getSelectedValuesList();
                Cond = " AND Wilaya.NameWilaya in( ";
                int sizeList = list.size();
                for (int i = 0; i < sizeList; i++) {
                    if (i < sizeList - 1) {
                        Cond += "N'" + list.get(i) + "', ";  //when selected first elements in list 
                    } else {
                        Cond += "N'" + list.get(i) + "' ";     //when 
                    }
                }
                Cond += " ) ";
            }
            Query1 += Cond;
        }
        /**
         * ********************************************************************
         */
        if (CheckDaira.isSelected()) {
            String Cond = " ";
            if (Name_Daira.getSelectedIndex() == -1) {
                Query1 += " ";
            } else {
                list = Name_Daira.getSelectedValuesList();
                Cond = " AND Student_Res.Name_Daira in( ";
                int sizeList = list.size();
                for (int i = 0; i < sizeList; i++) {
                    if (i < sizeList - 1) {
                        Cond += "N'" + list.get(i) + "', ";  //when selected first elements in list 
                    } else {
                        Cond += "N'" + list.get(i) + "' ";     //when 
                    }
                }
                Cond += " ) ";
            }
            Query1 += Cond;
        }
        /**
         * *********************************************************************
         */
        if (CheckBloc.isSelected()) {
            String Cond = " ";
            if (Pavilion.getSelectedIndex() == -1) {
                Query1 += " ";
            } else {
                list = Pavilion.getSelectedValuesList();
                Cond = " AND Pavilion.Pavilion_Name in( ";
                int sizeList = list.size();
                for (int i = 0; i < sizeList; i++) {
                    if (i < sizeList - 1) {
                        Cond += "'" + list.get(i) + "', ";  //when selected first elements in list 
                    } else {
                        Cond += "'" + list.get(i) + "' ";     //when 
                    }
                }
                Cond += " ) ";
            }
            Query1 += Cond;
        }
        /**
         * **********************************************************************
         */
        if (CheckRoom.isSelected()) {

            String Cond = " ";

            if (RomPvinPanInf.getSelectedIndex() == -1) {
                Query1 += " ";
            } else {

                String romNam = " ";
                romNam = (String) RomPvinPanInf.getSelectedItem();
                Cond += " AND Room.Room_Code = '" + romNam + "' ";
            }
            Query1 += Cond;
        }
        /**
         * **********************************************************************
         */

        Query1 += " order by Room.Room_Code ";
        if (choice == 0) {
            ExportDataExce.Fill_FileExcel_StdNoCodeCommune(Query1);   // NOT GENDER 
        } else {
            ExportDataExce.Fill_FileExcel_Std2(Query1);    // WITH GENDER
        }

    }

    /*
    public void FillComboboxRooms(JComboBox cmb,String IdPav){
   
   // DefaultComboBoxModel Dfcmb=new DefaultComboBoxModel();
       DefaultComboBoxModel Dfcmb=(DefaultComboBoxModel) cmb.getModel();
        Dfcmb.removeAllElements();
         Statement stm=null;
         ResultSet res=null;
    cnx.Connecting();
        try {
            stm=cnx.getConnect().createStatement();
            res=stm.executeQuery("select Room_Code FROM Pavilion,Room WHERE  Pavilion.ID_Pavilion=Room.ID_Pavilion AND Pavilion_Name='"+IdPav+"'");
            while (res.next()) {                
                Dfcmb.addElement(res.getString("Room_Code"));
            }
            Dfcmb.addElement("/");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error in sql :"+e.getMessage());
        }
        try {
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "The Close Exception Erron FillComboboxRooms :"+e.getMessage());
        }
     }
    
     */
    public void FilterResidentMlt(JTextField NumCard, JTextField NameRes, JTextField LastNm, JTextField DateNais,
            JTextField commune, JTextField Daira, JTextField branch, JTextField level, JTextField caseStd, JTextField bacyear,
            JTextField Cham,
            JTable tab, DefaultTableModel dm) {  //filtrer dans le tableau fournisseur
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dm);
        tab.setRowSorter(tr);
        List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(2);
        filters.add(RowFilter.regexFilter(NumCard.getText()));
        filters.add(RowFilter.regexFilter(NameRes.getText()));
        filters.add(RowFilter.regexFilter(LastNm.getText()));
        filters.add(RowFilter.regexFilter(DateNais.getText()));

        filters.add(RowFilter.regexFilter(commune.getText()));
        filters.add(RowFilter.regexFilter(Daira.getText()));
        filters.add(RowFilter.regexFilter(branch.getText()));
        filters.add(RowFilter.regexFilter(level.getText()));

        filters.add(RowFilter.regexFilter(caseStd.getText()));
        filters.add(RowFilter.regexFilter(bacyear.getText()));
        filters.add(RowFilter.regexFilter(Cham.getText()));
        RowFilter rf = RowFilter.andFilter(filters);
        tr.setRowFilter(rf);
    }

    public void ToolsRoomTotal_PvlOne(int Ncd) {

        String TestQuery = "SELECT Name_Resident,LastName_Resident,Branch_Study.BranchStd_Name,Room_Code\n"
                + "FROM Resident_Gl,Branch_Study,Student_Res,Room \n"
                + "WHERE Resident_Gl.ID_Rsident=Student_Res.ID_Rsident\n"
                + "AND Student_Res.Id_BranchStd=Branch_Study.Id_BranchStd \n"
                + "AND Student_Res.Id_Room=Room.Id_Room\n"
                + "AND Resident_Gl.NumCard_Resident='" + Ncd + "'";
        try {
            JasperReport jasperreport;
            InputStream file = getClass().getResourceAsStream("/Reports/Room_Hardware.jrxml");
            JasperDesign jasperdesign = JRXmlLoader.load(file);
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(TestQuery);
            jasperdesign.setQuery(newQuery);
            Map parametres = new HashMap<String, Object>();
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int year = cal.get(Calendar.YEAR);
            parametres.put("AnneEtd", year + "");
            //parametres.put("PrPav", Ncd+"");

            jasperreport = JasperCompileManager.compileReport(jasperdesign);
            Connection Cnx1;
            cnx.Connecting();
            Cnx1 = cnx.getConnect();
            JasperPrint jasperprint = JasperFillManager.fillReport(jasperreport, parametres, Cnx1);
            // jp=JasperFillManager.fillReport(jr, parametres, cnx.getConnect());
            JasperViewer JspViewr = new JasperViewer(jasperprint, false);
            JspViewr.viewReport(jasperprint, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in jasper Report" + e.getMessage());
        }
    }

    /**
     * *************************************************************
     */

    /**
     * ***************************************************************
     */
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Advanced_prp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Advanced_prp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Advanced_prp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Advanced_prp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Advanced_prp Ap = new Advanced_prp(null);
                Ap.setVisible(true);
                Ap.Filling_Data(28);
                //     Ap.CreateDomaineTable();
                //Ap.AddDomaine("d001","ادب عربي","test");
                // JOptionPane.showMessageDialog(null, "finish running");

                // Ap.ToolsRoomTotal_PvlOne(120);
            }
        });
        //new Advanced_prp().ToolsRoomTotal_PvlOne(120);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> Branch_Study;
    private javax.swing.JCheckBox CheckBacYear;
    private javax.swing.JCheckBox CheckBloc;
    private javax.swing.JCheckBox CheckBranch;
    private javax.swing.JCheckBox CheckCase;
    private javax.swing.JCheckBox CheckCommune;
    private javax.swing.JCheckBox CheckDaira;
    private javax.swing.JCheckBox CheckLevel;
    private javax.swing.JCheckBox CheckRoom;
    private javax.swing.JCheckBox CheckWilaya;
    private javax.swing.JList<String> Level_Study;
    private javax.swing.JComboBox<String> NameWilaya;
    private javax.swing.JList<String> Name_Commune;
    private javax.swing.JList<String> Name_Daira;
    private javax.swing.JPanel PanBacYear;
    private javax.swing.JPanel PanBloc;
    private javax.swing.JPanel PanBranch;
    private javax.swing.JPanel PanCase;
    private javax.swing.JPanel PanCommune;
    private javax.swing.JPanel PanDaira;
    private javax.swing.JPanel PanLevel;
    private javax.swing.JPanel PanWilaya;
    private javax.swing.JList<String> Pavilion;
    private javax.swing.JList<String> Resident_Case;
    private javax.swing.JComboBox<String> RomPvinPanInf;
    private javax.swing.JList<String> bacYearList;
    private javax.swing.JLabel counStd;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txtBacYear;
    private javax.swing.JTextField txtBirthplace;
    private javax.swing.JTextField txtBranch;
    private javax.swing.JTextField txtCase;
    private javax.swing.JTextField txtChambre;
    private javax.swing.JTextField txtCom;
    private javax.swing.JTextField txtDair;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtLevel;
    private javax.swing.JTextField txtNumCrd;
    private javax.swing.JTextField txtname;
    private javax.swing.JList<String> wilaya_List;
    // End of variables declaration//GEN-END:variables
}
