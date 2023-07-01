/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package residence;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Restu extends javax.swing.JFrame implements ActionListener{

   TakeRepast takeRepast;
   TakeRepast takeRepastRemplissage=new TakeRepast();
   Resident_Gl Resident_GlRemplissage =new Resident_Gl();
    /**
     * Creates new form Restu
     */
  ConnectionDB cnx=new ConnectionDB();
   SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
Timer timer;
int TimeRepat=1;
                    int IdRepat=1;
/*JFXPanel j;
 MediaPlayer MedSnd;
  String uri=new File("bepErr.MP3").toURI().toString();*/
    public Restu() {
        initComponents();
       // PlaySound();
    
                    System.out.println("ToTake.getHours()="+TimeRepat);
                    TimeRepat=ToTake.getHours();
                    jLabel3.setText(takeRepastRemplissage.NBResident(TimeRepat)+"");
                    
                    
         jLabel2.setVisible(false);
         if((TimeRepat<5 || (TimeRepat>8 &&TimeRepat<11)) || (TimeRepat>15 && TimeRepat<17) || (TimeRepat>21) ){
           jLabel26.setText("الـــرجـــاء مـــراعــــاة الــوقـــت أو ضــبـط تــوقــيـت الـجــهــاز");
           jTextField1.setEnabled(false);
           jLabel2.setVisible(true);
       }else{
            if (TimeRepat>=5&&TimeRepat<=8) {
                        jLabel21.setText("وجــــبة الفطــــور");
                        IdRepat=1;
                    }else if(TimeRepat>=11&&TimeRepat<=15){
                    jLabel21.setText("وجــــبة الغــــداء");
                    IdRepat=2;
                    }else{
                        if(TimeRepat>=17 && TimeRepat<=21){
                        jLabel21.setText("وجــــبة الـعـشــاء");
                         IdRepat=3;}
                    }
         
         }           
                    
       timeLabel.setText(sdf.format(new Date(System.currentTimeMillis())));
       cnx.Connecting();
       Connect=cnx.getConnect();
        timer = new Timer(500,this);
        timer.setRepeats(true);
        timer.start(); 
       this.getInputContext().selectInputMethod(new Locale("fr", "FR"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanRestaura = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        LabPrfResd_Std = new javax.swing.JLabel();
        LabPrfDate_Std = new javax.swing.JLabel();
        LabPrfFullNam_Std = new javax.swing.JLabel();
        LabPrfBranch_Std = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        LabPrfImgStd = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        LabPrfBranch_Std1 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        warning = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        HourTak = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(960, 710));

        PanRestaura.setBackground(new java.awt.Color(255, 255, 255));
        PanRestaura.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 1, 1, 1, new java.awt.Color(0, 0, 0)));
        PanRestaura.setForeground(java.awt.Color.blue);
        PanRestaura.setPreferredSize(new java.awt.Dimension(960, 710));
        PanRestaura.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 102, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        PanRestaura.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 180, 40));

        timeLabel.setBackground(new java.awt.Color(0, 0, 0));
        timeLabel.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        timeLabel.setForeground(new java.awt.Color(0, 0, 255));
        timeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 2));
        timeLabel.setOpaque(true);
        timeLabel.setText(new Date().getHours()+":"+new Date().getMinutes()+":"+new Date().getSeconds());
        timeLabel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                timeLabelFocusGained(evt);
            }
        });
        PanRestaura.add(timeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 180, 40));

        jTextField1.setBackground(new java.awt.Color(255, 255, 204));
        jTextField1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 51, 51));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 3));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });
        PanRestaura.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 140, 420, 40));

        jLabel64.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/AddUsr.png"))); // NOI18N
        jLabel64.setToolTipText("");
        jLabel64.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel64.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel64.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel64MouseClicked(evt);
            }
        });
        PanRestaura.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 90, 152, 40));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 3));
        jPanel11.setForeground(new java.awt.Color(255, 255, 204));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LabPrfResd_Std.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        LabPrfResd_Std.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabPrfResd_Std.setText("بـطـاقـة الــمـقـيـم       :");
        LabPrfResd_Std.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel11.add(LabPrfResd_Std, new org.netbeans.lib.awtextra.AbsoluteConstraints(307, 70, 100, 30));

        LabPrfDate_Std.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        LabPrfDate_Std.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabPrfDate_Std.setText("الاســـم و الــلـقـب   : ");
        LabPrfDate_Std.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel11.add(LabPrfDate_Std, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, 90, 30));

        LabPrfFullNam_Std.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        LabPrfFullNam_Std.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabPrfFullNam_Std.setText("تاريـخ الـميـلاد   :");
        LabPrfFullNam_Std.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel11.add(LabPrfFullNam_Std, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 90, 30));

        LabPrfBranch_Std.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        LabPrfBranch_Std.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabPrfBranch_Std.setText("الـــشــعــبــة     :");
        LabPrfBranch_Std.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel11.add(LabPrfBranch_Std, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, 90, 30));

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 51, 51));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        jPanel11.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 130, 30));

        jLabel65.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(0, 0, 153));
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel11.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 130, 30));

        jLabel67.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(0, 0, 153));
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel11.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 160, 30));

        LabPrfImgStd.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        LabPrfImgStd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabPrfImgStd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        LabPrfImgStd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel11.add(LabPrfImgStd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 105));

        jLabel68.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(0, 0, 153));
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel11.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 300, 30));

        jLabel69.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(0, 0, 153));
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel11.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 100, 30));

        jLabel52.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(0, 0, 153));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel11.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 140, 30));

        jLabel99.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(153, 153, 153));
        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel99.setText("بـــ");
        jPanel11.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 20, 20));

        LabPrfBranch_Std1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        LabPrfBranch_Std1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabPrfBranch_Std1.setText("الاقـامـة الــجـامـعـيــة الــحـاجــب");
        LabPrfBranch_Std1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel11.add(LabPrfBranch_Std1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 160, 20));

        jLabel101.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel101.setText("مـديـريـة الـخـدمـات الـجـامـعـيـة بـسكـرة");
        jLabel101.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel11.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 210, 20));

        PanRestaura.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 200, 420, 240));

        jPanel5.setLayout(new java.awt.CardLayout());

        jPanel6.setLayout(new java.awt.CardLayout());

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/Validate.png"))); // NOI18N
        jLabel22.setOpaque(true);
        jPanel6.add(jLabel22, "card2");

        jPanel5.add(jPanel6, "card3");

        warning.setLayout(new java.awt.CardLayout());

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/Error.png"))); // NOI18N
        jLabel20.setOpaque(true);
        warning.add(jLabel20, "card2");

        jPanel5.add(warning, "card4");

        PanRestaura.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 210, 210));

        jLabel25.setFont(new java.awt.Font("Arabic Typesetting", 1, 55)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 102, 102));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("الاقــــامــــة الــــــجــــامــــعــــيــة الـــــحـــــاجـــب بـسـكــرة");
        jLabel25.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 102, 102)));
        PanRestaura.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 710, 60));

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 51, 51));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PanRestaura.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 454, 490, 40));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/iconsCls.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        PanRestaura.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 10, 20, -1));

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("الـتـوقـيـت      : ");
        jLabel27.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        PanRestaura.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 106, 40));

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("الوجـــــبة      : ");
        jLabel28.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        PanRestaura.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 106, 40));

        HourTak.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        HourTak.setForeground(new java.awt.Color(255, 0, 51));
        HourTak.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PanRestaura.add(HourTak, new org.netbeans.lib.awtextra.AbsoluteConstraints(224, 504, 120, 40));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/Refresh.jpg"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        PanRestaura.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 40, 40));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        PanRestaura.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, 130, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PanRestaura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 801, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(PanRestaura, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void timeLabelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_timeLabelFocusGained

    }//GEN-LAST:event_timeLabelFocusGained
Date ToTake=new Date();
    Connection Connect;
    
    
    public void PlaySound(){
        /*InputStream in;
        try {
            /*in=new FileInputStream(new File("WarSnd.wav"));
            
            //in =new InputStream();
            
            AudioStream audio=new AudioStream(in);
            AudioPlayer.player.start(audio);
            
        } catch (Exception e) {
        }*/
  /*      try{
            URL defaultSound = this.getClass().getResource("/WarSnd.wav"); 
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(defaultSound);
   Clip clip = AudioSystem.getClip(); 
   clip.open(audioInputStream); clip.start(); }
        catch (Exception ex) {ex.printStackTrace();}*/
        
    }
    
    int idResident;
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
       // JOptionPane.showMessageDialog(null, "IN Action Performand Methode");
       
       jLabel26.setText("");
        HourTak.setText("");
        //MedSnd.stop();
        int numCard=0;
        String Cod;
        
        String a=jTextField1.getText();
      
        if(a.startsWith("0251")||a.startsWith("251") ){
           
         if(a.startsWith("0")){ 
             Cod =a.substring(1);
           }else  Cod =a;
         
            int P=Resident_GlRemplissage.GetIformationCard(Cod,jLabel23 ,jLabel65,jLabel67,
                        jLabel69,jLabel52,jLabel68,LabPrfImgStd,new JLabel());
            //JOptionPane.showMessageDialog(null, "The Id Resident :"+idResident);
            idResident=Resident_GlRemplissage.getId_Resident_Gl();
        if(P!=0){
            // JOptionPane.showMessageDialog(null, "P=!!!!!!!!!!!!!!!!!!!!!=0"+P);
            //numCard=Integer.parseInt(jLabel23.getText());
            if(P==1 || P==3){LabPrfResd_Std.setText("بـطـاقـة الــمـقـيـم       :");
                LabPrfBranch_Std.setText("الـــشــعــبــة     :");}
            if(P==2){{LabPrfResd_Std.setText("بـطـاقـة الــطــالـب     :");}
                LabPrfBranch_Std.setText("الـــشــعــبــة     :");}
            if(P==4){LabPrfResd_Std.setText("بـطـاقـة الــعـامــل       :");
                LabPrfBranch_Std.setText("الــمــهــنـــــة           :");}
 
            boolean  Case=takeRepastRemplissage.GetEtat(Cod,Connect);
            if(Case==true){
                
                //JOptionPane.showMessageDialog(null, "IN Case Of Student ");
                boolean Etat= takeRepastRemplissage.Exist_Not_Exist(Cod,jLabel26,HourTak,Connect);
                if(Etat==true) {
                    jPanel6.setVisible(true);
                    warning.setVisible(false);
                   
                    ToTake=new Date();
                    takeRepast =new TakeRepast(IdRepat, idResident, ToTake.getHours(),ToTake,ToTake.getMinutes(),Cod);  
                    takeRepast.TakeRopa();
                    jLabel3.setText(takeRepastRemplissage.NBResident(takeRepast.getHour_Take())+"");
                    
                }
                else{
                    jPanel6.setVisible(false);
                    warning.setVisible(true);
                }
            }else{
            jPanel6.setVisible(false);
                warning.setVisible(true);
                jLabel26.setForeground(Color.red);
                jLabel26.setText("لـم يـتـم تـــجـديـــد الـمـلـف الـرجـاء الـتوجــه الى  مـصـلــحـة الايـواء");

            }
        }else{
             LabPrfImgStd.setIcon(new ImageIcon(getClass().getResource("/residence/Image/imageRes.png")));
           
        // PlaySound();

             jLabel65.setText("");
                jLabel67.setText("");
                    jLabel69.setText("");
                    jLabel52.setText("");
                    jLabel68.setText("");
              jLabel26.setForeground(Color.red);
              jLabel26.setText("غــــيــــر مـــــقــــيــــم");
              jPanel6.setVisible(false);
                warning.setVisible(true);
               jLabel23.setText("");
        }
        }else{
         LabPrfImgStd.setIcon(new ImageIcon(getClass().getResource("/residence/Image/imageRes.png")));
           
        // PlaySound();

             jLabel65.setText("");
                jLabel67.setText("");
                    jLabel69.setText("");
                    jLabel52.setText("");
                    jLabel68.setText("");
              jLabel26.setForeground(Color.red);
              jLabel26.setText("غــــيــــر مـــــقــــيــــم");
              jPanel6.setVisible(false);
                warning.setVisible(true);
               jLabel23.setText("");
        
        }
        jTextField1.setText("");
        
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped

    }//GEN-LAST:event_jTextField1KeyTyped

    private void jLabel64MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel64MouseClicked
       
    }//GEN-LAST:event_jLabel64MouseClicked
Ok1 ok;
    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
       this.setVisible(false);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
       ToTake=new Date();
        TimeRepat=ToTake.getHours();
        
          if((TimeRepat<5 || (TimeRepat>8 &&TimeRepat<11)) || (TimeRepat>15 && TimeRepat<17) || (TimeRepat>21) ){
           jLabel26.setText("الـــرجـــاءاعــادة التأكـــد من ضــبـط تــوقــيـت الـجــهــاز");
           jTextField1.setEnabled(false);
       }else {
              jLabel26.setText("تمت عمليـــة تغيير الوقت بنجــاح");
              
                if (TimeRepat>=5&&TimeRepat<=8) {
                        jLabel21.setText("وجــــبة الفطــــور");
                        IdRepat=1;
                    }else if(TimeRepat>=11&&TimeRepat<=15){
                    jLabel21.setText("وجــــبة الغــــداء");
                    IdRepat=2;
                    }else{
                       if(TimeRepat>=17 && TimeRepat<=21){ 
                        jLabel21.setText("وجــــبة الـعـشــاء");
                    IdRepat=3;}
                    }
              
          jTextField1.setEnabled(true);
          jTextField1.requestFocus();
          jLabel2.setVisible(false);
          } 
         
    }//GEN-LAST:event_jLabel2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

               new Restu().setVisible(true); 
            // Restu r=   new Restu();
              //r.PlaySound();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel HourTak;
    private javax.swing.JLabel LabPrfBranch_Std;
    private javax.swing.JLabel LabPrfBranch_Std1;
    private javax.swing.JLabel LabPrfDate_Std;
    private javax.swing.JLabel LabPrfFullNam_Std;
    private javax.swing.JLabel LabPrfImgStd;
    private javax.swing.JLabel LabPrfResd_Std;
    private javax.swing.JPanel PanRestaura;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JPanel warning;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
                             if (e.getSource().equals(timer)) {
timeLabel.setText(sdf.format(new Date(System.currentTimeMillis())));}  
    }
}
