/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monetae.view;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JOptionPane;

/**
 *
 * @author Rodrigo
 */
public class TelaSobre extends javax.swing.JInternalFrame {

    /**
     * Creates new form SobreProgramaView
     */
    public TelaSobre() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelLogo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabelLGitHub1 = new javax.swing.JLabel();
        jPanelRedesSociais = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabelFacebook = new javax.swing.JLabel();
        jLabelInsta = new javax.swing.JLabel();
        jLabelWhatsApp = new javax.swing.JLabel();
        jLabelLTwitter = new javax.swing.JLabel();
        jLabelLLinkdeIn = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabelLGitHub = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setTitle("Sobre o Programa");
        setPreferredSize(new java.awt.Dimension(1373, 712));

        jLabel1.setText("Programa desenvolvimento para simular atendimentos a cliente de uma casa de cambio");

        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/monetae/imagens/logo_fnr.png"))); // NOI18N
        jLabelLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLogoMouseClicked(evt);
            }
        });

        jLabel2.setText("Faculdade Noa Roma");

        jLabel3.setText("Curso: Análise e Desenvolvimento de Sistemas");

        jLabel4.setText("Cadeira: Técnicas Avançadas de Programação");

        jLabel5.setText("Professor(a): Anderson");

        jLabel6.setText("Aluno(a): Rodrigo Ferreira Rodrigues");

        jLabel7.setText("usando threads.");

        jLabel11.setText("Link do Projeto no GitHub:");

        jLabelLGitHub1.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabelLGitHub1.setForeground(new java.awt.Color(51, 51, 255));
        jLabelLGitHub1.setText("MonetaeCambioThreads");
        jLabelLGitHub1.setToolTipText("@rodrigo2208");
        jLabelLGitHub1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLGitHub1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabelLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(0, 100, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelLGitHub1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelLogo)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11)
                    .addComponent(jLabelLGitHub1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setText("Rodrigo Ferreira Rodrigues");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Redes Sociais");

        jLabelFacebook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/monetae/imagens/Facebook160px.png"))); // NOI18N
        jLabelFacebook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelFacebookMouseClicked(evt);
            }
        });

        jLabelInsta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/monetae/imagens/Instagram121px.png"))); // NOI18N
        jLabelInsta.setToolTipText("@rfrodrigues");
        jLabelInsta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInstaMouseClicked(evt);
            }
        });

        jLabelWhatsApp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/monetae/imagens/WhatsApp121px.png"))); // NOI18N
        jLabelWhatsApp.setToolTipText("+55 81 98855 - 7802");

        jLabelLTwitter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/monetae/imagens/Twitter121px.png"))); // NOI18N
        jLabelLTwitter.setToolTipText("@rodrigo2208");
        jLabelLTwitter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLTwitterMouseClicked(evt);
            }
        });

        jLabelLLinkdeIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/monetae/imagens/LinkedIn121px.png"))); // NOI18N
        jLabelLLinkdeIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLLinkdeInMouseClicked(evt);
            }
        });

        jButton1.setText("Fechar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabelLGitHub.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/monetae/imagens/github.png"))); // NOI18N
        jLabelLGitHub.setToolTipText("@rodrigo2208");
        jLabelLGitHub.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLGitHubMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelRedesSociaisLayout = new javax.swing.GroupLayout(jPanelRedesSociais);
        jPanelRedesSociais.setLayout(jPanelRedesSociaisLayout);
        jPanelRedesSociaisLayout.setHorizontalGroup(
            jPanelRedesSociaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRedesSociaisLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelRedesSociaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRedesSociaisLayout.createSequentialGroup()
                        .addGroup(jPanelRedesSociaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelLLinkdeIn)
                            .addComponent(jLabelFacebook))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelRedesSociaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelRedesSociaisLayout.createSequentialGroup()
                                .addComponent(jLabelInsta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelWhatsApp))
                            .addGroup(jPanelRedesSociaisLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel9))
                            .addGroup(jPanelRedesSociaisLayout.createSequentialGroup()
                                .addGroup(jPanelRedesSociaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelLTwitter, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRedesSociaisLayout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addGap(47, 47, 47)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelLGitHub)
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRedesSociaisLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(55, 55, 55))))
        );
        jPanelRedesSociaisLayout.setVerticalGroup(
            jPanelRedesSociaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRedesSociaisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelRedesSociaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelInsta)
                    .addComponent(jLabelWhatsApp)
                    .addComponent(jLabelFacebook))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelRedesSociaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelLLinkdeIn)
                    .addComponent(jLabelLTwitter)
                    .addComponent(jLabelLGitHub))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/monetae/imagens/monetae-logo.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelRedesSociais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 878, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelRedesSociais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(44, 44, 44))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelFacebookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelFacebookMouseClicked

        try {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            Desktop.getDesktop().browse(new URI("https://www.facebook.com/rfrodrigues.pe"));
        } catch (IOException | URISyntaxException e) {
            if (e.toString().substring(9, 29).equals("UnknownHostException")) {
                JOptionPane.showMessageDialog(this, "Você precisa estar conectado na internet");
            }
        }
    }//GEN-LAST:event_jLabelFacebookMouseClicked

    private void jLabelInstaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInstaMouseClicked
        try {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            Desktop.getDesktop().browse(new URI("https://www.instagram.com/rfrodrigues/?hl=pt-br"));
        } catch (IOException | URISyntaxException e) {
            if (e.toString().substring(9, 29).equals("UnknownHostException")) {
                JOptionPane.showMessageDialog(this, "Você precisa estar conectado na internet");
            }
        }
    }//GEN-LAST:event_jLabelInstaMouseClicked

    private void jLabelLTwitterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLTwitterMouseClicked
        try {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            Desktop.getDesktop().browse(new URI("https://twitter.com/rodrigo2208"));
        } catch (IOException | URISyntaxException e) {
            if (e.toString().substring(9, 29).equals("UnknownHostException")) {
                JOptionPane.showMessageDialog(this, "Você precisa estar conectado na internet");
            }
        }
    }//GEN-LAST:event_jLabelLTwitterMouseClicked

    private void jLabelLLinkdeInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLLinkdeInMouseClicked
        // TODO add your handling code here:
        try {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/rodrigo-ferreira-rodrigues-32966059/"));
        } catch (IOException | URISyntaxException e) {
            if (e.toString().substring(9, 29).equals("UnknownHostException")) {
                JOptionPane.showMessageDialog(this, "Você precisa estar conectado na internet");
            }
        }
    }//GEN-LAST:event_jLabelLLinkdeInMouseClicked

    private void jLabelLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLogoMouseClicked
        try {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            Desktop.getDesktop().browse(new URI("http://www.faculdadenovaroma.com.br/"));
        } catch (IOException | URISyntaxException e) {
            if (e.toString().substring(9, 29).equals("UnknownHostException")) {
                JOptionPane.showMessageDialog(this, "Você precisa estar conectado na internet");
            }
        }
    }//GEN-LAST:event_jLabelLogoMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabelLGitHubMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLGitHubMouseClicked
        // TODO add your handling code here:
        try {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            Desktop.getDesktop().browse(new URI("https://github.com/rfrodriguespe"));
        } catch (IOException | URISyntaxException e) {
            if (e.toString().substring(9, 29).equals("UnknownHostException")) {
                JOptionPane.showMessageDialog(this, "Você precisa estar conectado na internet");
            }
        }
    }//GEN-LAST:event_jLabelLGitHubMouseClicked

    private void jLabelLGitHub1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLGitHub1MouseClicked
        // TODO add your handling code here:
        try {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            Desktop.getDesktop().browse(new URI("https://github.com/rfrodriguespe/MonetaeCambioThreads"));
        } catch (IOException | URISyntaxException e) {
            if (e.toString().substring(9, 29).equals("UnknownHostException")) {
                JOptionPane.showMessageDialog(this, "Você precisa estar conectado na internet");
            }
        }
    }//GEN-LAST:event_jLabelLGitHub1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelFacebook;
    private javax.swing.JLabel jLabelInsta;
    private javax.swing.JLabel jLabelLGitHub;
    private javax.swing.JLabel jLabelLGitHub1;
    private javax.swing.JLabel jLabelLLinkdeIn;
    private javax.swing.JLabel jLabelLTwitter;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelWhatsApp;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelRedesSociais;
    // End of variables declaration//GEN-END:variables
}
