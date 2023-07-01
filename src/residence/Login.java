package residence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.sun.glass.events.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TimerTask;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class Login extends javax.swing.JFrame {

    Home1 home; 
    int TabSrv[]=new int[5];
    UserService user;
    ConnectionDB Cnx=new ConnectionDB();
    
    public Login(Home1 h) {
        initComponents();
        //home=new Home();
        //home=h;
        home=h;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Pan_bg = new javax.swing.JPanel();
        pan1 = new javax.swing.JPanel();
        Login_pan = new javax.swing.JPanel();
        txtuserLg = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPassLg = new javax.swing.JPasswordField();
        LabMessagCnt = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Loader = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        Pan_bg.setBackground(new java.awt.Color(255, 255, 255));
        Pan_bg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Pan_bgMousePressed(evt);
            }
        });

        pan1.setBackground(new java.awt.Color(255, 255, 255));
        pan1.setLayout(new java.awt.CardLayout());

        Login_pan.setBackground(new java.awt.Color(255, 255, 255));
        Login_pan.setForeground(new java.awt.Color(102, 102, 102));
        Login_pan.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                Login_panMouseDragged(evt);
            }
        });
        Login_pan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Login_panMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Login_panMouseReleased(evt);
            }
        });
        Login_pan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtuserLg.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtuserLg.setForeground(new java.awt.Color(102, 102, 102));
        txtuserLg.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtuserLg.setText("اسم المستخدم");
        txtuserLg.setBorder(null);
        txtuserLg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtuserLgFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtuserLgFocusLost(evt);
            }
        });
        Login_pan.add(txtuserLg, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 251, 20));

        jSeparator1.setBackground(new java.awt.Color(51, 102, 0));
        jSeparator1.setForeground(new java.awt.Color(51, 153, 0));
        Login_pan.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 151, 251, 6));

        jSeparator2.setBackground(new java.awt.Color(51, 102, 0));
        jSeparator2.setForeground(new java.awt.Color(51, 153, 0));
        Login_pan.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 194, 251, 10));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/userIcon - Copie.png"))); // NOI18N
        Login_pan.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 23, 20));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/PassIcon2.png"))); // NOI18N
        Login_pan.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(107, 170, 30, 30));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("لقد نسيت كلمة المـرور ؟");
        Login_pan.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 255, 130, 26));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/circle-close-128.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        Login_pan.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 0, -1, 27));

        txtPassLg.setForeground(new java.awt.Color(102, 102, 102));
        txtPassLg.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPassLg.setText("jPasswordField1");
        txtPassLg.setBorder(null);
        txtPassLg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPassLgFocusGained(evt);
            }
        });
        txtPassLg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPassLgKeyPressed(evt);
            }
        });
        Login_pan.add(txtPassLg, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 173, 251, 20));

        LabMessagCnt.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        LabMessagCnt.setForeground(new java.awt.Color(255, 0, 0));
        Login_pan.add(LabMessagCnt, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 218, 251, 22));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 204));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Login");
        Login_pan.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 70, 30));

        jButton1.setBackground(new java.awt.Color(0, 102, 0));
        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("دخــول");
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setOpaque(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        Login_pan.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 250, 90, 30));

        pan1.add(Login_pan, "card2");

        Loader.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/ring.gif"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 51));
        jLabel2.setText("تسجيل الدخول  ......");

        javax.swing.GroupLayout LoaderLayout = new javax.swing.GroupLayout(Loader);
        Loader.setLayout(LoaderLayout);
        LoaderLayout.setHorizontalGroup(
            LoaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoaderLayout.createSequentialGroup()
                .addGroup(LoaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LoaderLayout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LoaderLayout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(206, Short.MAX_VALUE))
        );
        LoaderLayout.setVerticalGroup(
            LoaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoaderLayout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        pan1.add(Loader, "card3");

        javax.swing.GroupLayout Pan_bgLayout = new javax.swing.GroupLayout(Pan_bg);
        Pan_bg.setLayout(Pan_bgLayout);
        Pan_bgLayout.setHorizontalGroup(
            Pan_bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pan1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Pan_bgLayout.setVerticalGroup(
            Pan_bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pan1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Pan_bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Pan_bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(519, 365));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtuserLgFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtuserLgFocusGained
        // TODO add your handling code here:
        txtuserLg.setText("");
    }//GEN-LAST:event_txtuserLgFocusGained

    private void txtuserLgFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtuserLgFocusLost
        
  
    }//GEN-LAST:event_txtuserLgFocusLost
    
    
    public int Authenfication(String Usr,String psw){
        
        int service1,service2,service3,service4,service5;
        int NumberRow=0;
        int EtatConnect=0;
        Statement stm=null; 
        ResultSet res=null;
        //ConnectionDB cnnx=new ConnectionDB();
        String Requette="SELECT  * FROM UserService WHERE Idantifiant_usr =N'"+Usr+"' AND pass_usr=N'"+psw+"'";
        //String Requette="SELECT * FROM user ";
        System.out.println("Login.Authenfication() The connection is etablished");

        Cnx.Connecting();
        try {
            
            stm=Cnx.getConnect().createStatement();
           
            System.out.println("Login.Authenfication() The statment is created");
            res=stm.executeQuery(Requette);
            
            System.out.println("Login.Authenfication() The query is executed ");
            
            if (res.next()) {
                
            System.out.println("the message"+res.getString("Idantifiant_usr"));
            System.out.println("the message"+res.getString(2));
            System.out.println("the message"+res.getString(3));
            System.out.println("the message"+res.getString(4));
            
             service1=GetEtatSrv(res.getBoolean("Inscription_Resrv_srv"));
            System.out.println("the message"+service1);
            TabSrv[0]=service1;
            service2=GetEtatSrv(res.getBoolean("ResrveRom_srv"));
            System.out.println("the message"+service2);
            TabSrv[1]=service2;
            service3=GetEtatSrv(res.getBoolean("Restauration_srv"));
            System.out.println("the message"+service3);
            TabSrv[2]=service3;
            service4=GetEtatSrv(res.getBoolean("accessResident_srv"));
            System.out.println("the message"+service4);
            TabSrv[3]=service4;
            service5=GetEtatSrv(res.getBoolean("administration"));
            System.out.println("the message"+service5);
            TabSrv[4]=service5;
        
                
            //TabSrv[]={0,1,1,1,0};this function use in Function GetMenu;
            
                EtatConnect=1;
            }
            
            
        }catch(SQLException e){
        JOptionPane.showMessageDialog(null, "Error ine sal Exception 1"+e.getMessage());
        }
            
  
        try {
            Cnx.Deconnect();
            stm.close();
            res.close();
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in close "+e.getMessage());
            }
        
     return EtatConnect;
            
    }
    
   public int GetEtatSrv(boolean etat){
   if(etat){
   return 1;
   }else 
   {return 0;
   }
   
   }
    
    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void txtPassLgFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPassLgFocusGained
        // TODO add your handling code here:
            txtPassLg.setText("");
    }//GEN-LAST:event_txtPassLgFocusGained

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        int x=Authenfication(txtuserLg.getText(), txtPassLg.getText());
       if (x==0) {
         LabMessagCnt.setText("خــطأ في اسم المسستخدم او كلمــة المرور");
        }else
        
        {
            Loader.show();
        Login_pan.hide();
        
        new java.util.Timer().schedule(new TimerTask() {

            @Override
            public void run() {
               java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               home.setVisible(true);
               // new Home1().UploadPictResident(new JLabel());
            }
        });   //home.GetMenuUser(TabSrv);
               dispose();
            }
        },1000*5);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtPassLgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassLgKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
          int x=Authenfication(txtuserLg.getText(), txtPassLg.getText());
       if (x==0) {
         LabMessagCnt.setText("خــطأ في اسم المسستخدم او كلمــة المرور");
        }else
        
        {
            Loader.show();
        Login_pan.hide();
        
        new java.util.Timer().schedule(new TimerTask() {

            @Override
            public void run() {
               java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               home.setVisible(true);
               // new Home1().UploadPictResident(new JLabel());
            }
        });   //home.GetMenuUser(TabSrv);
               dispose();
            }
        },1000*5);
        }
        }
    }//GEN-LAST:event_txtPassLgKeyPressed
int xx,yy;
    private void Login_panMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Login_panMousePressed
               this.setOpacity((float) 0.7);
        xx = evt.getX();
        yy = evt.getY();
    }//GEN-LAST:event_Login_panMousePressed

    private void Login_panMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Login_panMouseReleased
        this.setOpacity(1);
    }//GEN-LAST:event_Login_panMouseReleased

    private void Login_panMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Login_panMouseDragged
            int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
       this.setLocation(x - xx, y - yy);
    }//GEN-LAST:event_Login_panMouseDragged

    private void Pan_bgMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pan_bgMousePressed
        
    }//GEN-LAST:event_Pan_bgMousePressed

 
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login(new Home1()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabMessagCnt;
    private javax.swing.JPanel Loader;
    private javax.swing.JPanel Login_pan;
    private javax.swing.JPanel Pan_bg;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel pan1;
    private javax.swing.JPasswordField txtPassLg;
    private javax.swing.JTextField txtuserLg;
    // End of variables declaration//GEN-END:variables
}
