/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkwebsite;

import java.awt.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.validator.routines.UrlValidator;

/**
 *
 * @author Vimlesh
 */
public class Mainframe extends javax.swing.JFrame {

    /**
     * Creates new form Mainframe
     */
    WebsiteBean wbdata;
    String wurl;
    boolean st;

    public Mainframe() {
        initComponents();
    }

    public boolean checkURL(String url) {
        String[] schemes = {"http", "https"}; // DEFAULT schemes = "http", "https", "ftp"
        UrlValidator urlValidator = new UrlValidator(schemes);
        return urlValidator.isValid(url);
    }

    public WebsiteBean checkUrlStatus(String gUrl) {
        WebsiteBean wb = new WebsiteBean();
        URL url;
        int resCode = 2;
        clearField();
        try {
            url = new URL(gUrl);
            long starTime = System.currentTimeMillis();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
            connection.connect();
            long elapsedTime = System.currentTimeMillis() - starTime;
            wb.setRespTime((int) elapsedTime);
            resCode = connection.getResponseCode();
            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("E dd/MM/yyyy 'at' hh:mm:ss a zzz");
            wb.setCurrTime(ft.format(dNow));
            wb.setRespCode(resCode);
        } catch (UnknownHostException ex) {
            System.err.println("1Error : " + ex);
            wb.setStatus("URL not resolved!");
            //Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            System.err.println("2Error : " + ex);
            wb.setRespCode(1);
            //Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.err.println("3Error : " + ex);
            wb.setRespCode(2);
            //Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
        }
        lblCurTime1.setText(wb.getCurrTime());
        lblHttpRespCode1.setText(String.valueOf(resCode));
        if (resCode >= 200 && resCode < 300) {
            wb.setStatus("Active");
            lblStatus1.setText("Active");
        } else if (resCode >= 300 && resCode < 400) {
            wb.setStatus("Redirection found");
            lblStatus1.setText("Redirection found");
        } else if (resCode >= 400 && resCode < 500) {
            wb.setStatus("Doesn't exists!");
            lblStatus1.setText("Doesn't exists!");
        } else if (resCode >= 500 && resCode < 600) {
            wb.setStatus("Server Error");
            lblStatus1.setText("Server Error");
        } else if (resCode == 0) {
            wb.setStatus("URL Not Resolved");
            lblStatus1.setText("URL Not Resolved");
            lblHttpRespCode1.setText("---");
        } else if (resCode == 1) {
            wb.setStatus("MalformedURL");
            lblStatus1.setText("MalformedURL");
            lblHttpRespCode1.setText("---");
        } else if (resCode == 2) {
            wb.setStatus("Webpage not found!");
            lblStatus1.setText("WebPage not found!");
            lblHttpRespCode1.setText("---");
        } else {
            wb.setStatus("Error getting response!");
            lblStatus1.setText("Error getting response!");
        }
        if (wb.getRespTime() > 0) {
            lblResponseTime1.setText(wb.getRespTime() + " ms");
        } else {
            lblResponseTime1.setText("--- ms");
        }
        return wb;
    }

    public void clearField() {
//        lblWarning.setText("");
        lblStatus1.setText("---");
        lblHttpRespCode1.setText("---");
        lblResponseTime1.setText("---");
    }

    class Monitoring extends Thread {

        @Override
        public void run() {
            String rprt;
            try {
                PrintWriter writer = new PrintWriter("WebsiteReport.txt", "UTF-8");
                writer.println("website report for : " + wurl);
                writer.println();
                while (st) {
                    wbdata = new WebsiteBean();
                    wbdata = checkUrlStatus(wurl);
                    rprt = "Time : " + wbdata.getCurrTime() + "\t Response Time : " + String.valueOf(wbdata.getRespTime()) + "ms \t Status : " + wbdata.getStatus() + "\n";
                    writer.print(rprt);
                    System.out.println(wbdata.getCurrTime());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }
                writer.close();
            } catch (Exception e) {
                System.out.println(e);
            }
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

        lblTitle = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanelInput = new javax.swing.JPanel();
        txtWebURL = new javax.swing.JTextField();
        btnAnalyze = new javax.swing.JButton();
        jPanelResult = new javax.swing.JPanel();
        lblStatus = new javax.swing.JLabel();
        lblHttpRespCode = new javax.swing.JLabel();
        lblResponseTime = new javax.swing.JLabel();
        lblStatus1 = new javax.swing.JLabel();
        lblHttpRespCode1 = new javax.swing.JLabel();
        lblResponseTime1 = new javax.swing.JLabel();
        txtNotifyEmail = new javax.swing.JTextField();
        btnReport = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblCurTime = new javax.swing.JLabel();
        lblCurTime1 = new javax.swing.JLabel();
        lblWarning = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Website Analysis");
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitle.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(0, 0, 102));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Website Analysis");
        getContentPane().add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 36));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 42, 440, 10));

        jPanelInput.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Website URL"));

        txtWebURL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtWebURLActionPerformed(evt);
            }
        });

        btnAnalyze.setText("Start Monitoring");
        btnAnalyze.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalyzeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelInputLayout = new javax.swing.GroupLayout(jPanelInput);
        jPanelInput.setLayout(jPanelInputLayout);
        jPanelInputLayout.setHorizontalGroup(
            jPanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInputLayout.createSequentialGroup()
                .addComponent(txtWebURL, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnAnalyze)
                .addContainerGap())
        );
        jPanelInputLayout.setVerticalGroup(
            jPanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInputLayout.createSequentialGroup()
                .addGroup(jPanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtWebURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnalyze))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanelInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 58, 440, -1));

        jPanelResult.setBorder(javax.swing.BorderFactory.createTitledBorder("Result"));

        lblStatus.setText("Status :");

        lblHttpRespCode.setText("HTTP Response Code :");

        lblResponseTime.setText("Response Time  :");

        lblStatus1.setText("---");

        lblHttpRespCode1.setText("---");

        lblResponseTime1.setText("---");

        txtNotifyEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNotifyEmailActionPerformed(evt);
            }
        });

        btnReport.setText("REPORT");
        btnReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportActionPerformed(evt);
            }
        });

        jLabel1.setText("Email :");

        lblCurTime.setText("Timestamp :");

        lblCurTime1.setText("---");

        javax.swing.GroupLayout jPanelResultLayout = new javax.swing.GroupLayout(jPanelResult);
        jPanelResult.setLayout(jPanelResultLayout);
        jPanelResultLayout.setHorizontalGroup(
            jPanelResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelResultLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelResultLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(txtNotifyEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnReport))
                    .addGroup(jPanelResultLayout.createSequentialGroup()
                        .addGroup(jPanelResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelResultLayout.createSequentialGroup()
                                .addGroup(jPanelResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblStatus)
                                    .addComponent(lblHttpRespCode))
                                .addGap(56, 56, 56)
                                .addGroup(jPanelResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblHttpRespCode1)
                                    .addComponent(lblStatus1)
                                    .addComponent(lblResponseTime1)))
                            .addComponent(lblResponseTime)
                            .addGroup(jPanelResultLayout.createSequentialGroup()
                                .addComponent(lblCurTime)
                                .addGap(108, 108, 108)
                                .addComponent(lblCurTime1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelResultLayout.setVerticalGroup(
            jPanelResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelResultLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCurTime)
                    .addComponent(lblCurTime1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStatus)
                    .addComponent(lblStatus1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHttpRespCode)
                    .addComponent(lblHttpRespCode1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblResponseTime)
                    .addComponent(lblResponseTime1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNotifyEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReport)
                    .addComponent(jLabel1))
                .addGap(20, 20, 20))
        );

        getContentPane().add(jPanelResult, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 440, 180));

        lblWarning.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblWarning.setForeground(new java.awt.Color(255, 51, 51));
        lblWarning.setToolTipText("");
        getContentPane().add(lblWarning, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 347, 13));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnalyzeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalyzeActionPerformed
        // TODO add your handling code here:
        Monitoring m1 = new Monitoring();
        wurl = txtWebURL.getText();
        if (wurl.equals("")) {
            lblWarning.setForeground(Color.RED);
            lblWarning.setText("Error: URL field can not be blank!");
        } else if (checkURL(wurl)) {
            if (btnAnalyze.getText().equalsIgnoreCase("start monitoring")) {
                //checkUrlStatus(wurl);
                lblWarning.setText("Monitoring...");
                st = true;
                m1.start();
                btnAnalyze.setText("Stop Monitoring");
            } else if (btnAnalyze.getText().equalsIgnoreCase("stop monitoring")) {
                lblWarning.setText("");
                st = false;
                m1.stop();
                btnAnalyze.setText("Start Monitoring");
            }
        } else {
            lblWarning.setForeground(Color.RED);
            lblWarning.setText("Error: URL not in correct format!");
        }
    }//GEN-LAST:event_btnAnalyzeActionPerformed

    /**
     *
     * @param email
     * @param msg
     */
    protected void notify(String email, String msg) {
        // Recipient's email ID needs to be mentioned.
        String to = email;//change accordingly

        // go to link below and turn on Access for less secure apps
        //https://www.google.com/settings/security/lesssecureapps
        // Sender's email ID needs to be mentioned
        String from = "";//Enter your email id here
        final String username = "";//Enter your email id here
        final String password = "";//Enter your password here

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Report of your website : " + wurl);

//            // Now set the actual message
//            message.setText("Hello, " + msg + " this is sample for to check send "
//                    + "email using JavaMailAPI ");
            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText("Please! find your website report in attachment");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = "WebsiteReport.txt";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);
            lblWarning.setText("report sent...");
            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    } // end of send mail


    private void txtWebURLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtWebURLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtWebURLActionPerformed

    private void txtNotifyEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNotifyEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNotifyEmailActionPerformed

    private void btnReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportActionPerformed
        // TODO add your handling code here:
        if (btnAnalyze.getText().equalsIgnoreCase("stop monitoring")) {
            lblWarning.setText("Stop monitoring first!");
        } else if(txtNotifyEmail.getText().equals("")){
            lblWarning.setText("Enter a valid email id...");
        } else {
            String email = txtNotifyEmail.getText();
            String msg = "";//lblStatus1.getText() + "( response time : " + lblResponseTime1.getText() + " ms)";
            notify(email, msg);
        }

    }//GEN-LAST:event_btnReportActionPerformed

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
            java.util.logging.Logger.getLogger(Mainframe.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mainframe.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mainframe.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mainframe.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mainframe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalyze;
    private javax.swing.JButton btnReport;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanelInput;
    private javax.swing.JPanel jPanelResult;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblCurTime;
    private javax.swing.JLabel lblCurTime1;
    private javax.swing.JLabel lblHttpRespCode;
    private javax.swing.JLabel lblHttpRespCode1;
    private javax.swing.JLabel lblResponseTime;
    private javax.swing.JLabel lblResponseTime1;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblStatus1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblWarning;
    private javax.swing.JTextField txtNotifyEmail;
    private javax.swing.JTextField txtWebURL;
    // End of variables declaration//GEN-END:variables
}
