/*
 * Copyright (C) 2019 Rodrigo Ferreira Rodrigues <Email: rodrigo2208@gmail.com GitHub: https://github.com/rfrodriguespe>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.monetae.testes;

import br.com.monetae.model.CaixaThread;
import br.com.monetae.control.ClienteControl;
import br.com.monetae.control.Estatisticas;
import br.com.monetae.control.VerificaQtdeClientes;
import br.com.monetae.model.Cliente;
import br.com.monetae.utils.EnviarEmail;
import br.com.monetae.utils.GeraPdf;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Rodrigo Ferreira Rodrigues
 * <Email: rodrigo2208@gmail.com GitHub: https://github.com/rfrodriguespe>
 */
public class TelaTeste extends javax.swing.JInternalFrame {

    //LOG
    private static final String BARRA = System.getProperty("file.separator");
    private static final String PASTA_USUARIO = System.getProperty("user.home");
    private static final String BASE_LOGS = PASTA_USUARIO + BARRA + "monetae";
    private static final String ARQUIVO_LOG = BASE_LOGS + BARRA + "monetae-log.txt";
    private static final String RELATORIO = BASE_LOGS + BARRA + "relatorio.pdf";
    //LOG

    //LISTA DOS CLIENTES
    public static ArrayList<Cliente> listaDeClientesGerados = new ArrayList<>();
    public static ArrayList<Cliente> listaDeAtendidos = new ArrayList<>();
    //LISTA DOS CLIENTES

    //MONITORAMENTO DA QUANTIDADE DE CLIENTES
    public static VerificaQtdeClientes monitoraClientes;
    //MONITORAMENTO DA QUANTIDADE DE CLIENTES

    //CRONOMETRO
    private Timer timer;
    public static int currentSegundo = 0;
    public static int currentMinuto = 0;
    public static int currentHora = 0;
    private int velocidade = 1000;
    //CRONOMETRO

    //CAIXAS
    public static CaixaThread CxT01;
    public static CaixaThread CxT02;
    public static CaixaThread CxT03;
    public static CaixaThread CxT04;
    public static CaixaThread CxT05;
    public static CaixaThread CxT06;
    public static CaixaThread CxT07;
    public static CaixaThread CxT08;
    //CAIXAS

    //CAIXAS
    public static CaixaThreadStop TesteCxT01;
    public static CaixaThreadStop TesteCxT02;
    public static CaixaThreadStop TesteCxT03;
    public static CaixaThreadStop TesteCxT04;
    public static CaixaThreadStop TesteCxT05;
    public static CaixaThreadStop TesteCxT06;
    public static CaixaThreadStop TesteCxT07;
    public static CaixaThreadStop TesteCxT08;
    //CAIXAS

    //NESSA ÁREA TESTAREI AS THREAS COM START SUSPEND E STOP
    SuspendResumeStop testeCx1 = new SuspendResumeStop("#01");
    SuspendResumeStop testeCx2 = new SuspendResumeStop("#02");
    SuspendResumeStop testeCx3 = new SuspendResumeStop("#03");
    SuspendResumeStop testeCx4 = new SuspendResumeStop("#04");
    SuspendResumeStop testeCx5 = new SuspendResumeStop("#05");
    SuspendResumeStop testeCx6 = new SuspendResumeStop("#06");
    SuspendResumeStop testeCx7 = new SuspendResumeStop("#07");
    SuspendResumeStop testeCx8 = new SuspendResumeStop("#08");
    //NESSA ÁREA TESTAREI AS THREAS COM START SUSPEND E STOP

    /**
     * Creates new form TelaAtendimento
     */
    public TelaTeste() {
        initComponents();
        monitoraClientes = new VerificaQtdeClientes("Thread Clientes #01");
        iniciarContagem();
        timer.stop();
        // Adiciona rodapé com a data e hora atuais
        String cabecalhoLog = "Monetae Câmbio - Log de eventos\n";
        String inicioLog = "" + new SimpleDateFormat("[dd'/'MM'/'yyyy - HH:mm:ss]").format(GregorianCalendar.getInstance().getTime());
        logDoPrograma.append(cabecalhoLog);
        logDoPrograma.append(inicioLog + " Tela de atendimento aberta\n");
        jLabelLocalDoLog.setText(BASE_LOGS);
        //

        //INSTANCIA DOS CAIXAS
        TesteCxT01 = new CaixaThreadStop("01", barraProgressoCx1, jLabelCaixa1, jLabelTempoClienteDaVezCx1, logDoPrograma, jLabelServicoCx1);
        TesteCxT02 = new CaixaThreadStop("02", barraProgressoCx2, jLabelCaixa2, jLabelTempoClienteDaVezCx2, logDoPrograma, jLabelServicoCx2);
        TesteCxT03 = new CaixaThreadStop("03", barraProgressoCx3, jLabelCaixa3, jLabelTempoClienteDaVezCx3, logDoPrograma, jLabelServicoCx3);
        TesteCxT04 = new CaixaThreadStop("04", barraProgressoCx4, jLabelCaixa4, jLabelTempoClienteDaVezCx4, logDoPrograma, jLabelServicoCx4);
        TesteCxT05 = new CaixaThreadStop("05", barraProgressoCx5, jLabelCaixa5, jLabelTempoClienteDaVezCx5, logDoPrograma, jLabelServicoCx5);
        TesteCxT06 = new CaixaThreadStop("06", barraProgressoCx6, jLabelCaixa6, jLabelTempoClienteDaVezCx6, logDoPrograma, jLabelServicoCx6);
        TesteCxT07 = new CaixaThreadStop("07", barraProgressoCx7, jLabelCaixa7, jLabelTempoClienteDaVezCx7, logDoPrograma, jLabelServicoCx7);
        TesteCxT08 = new CaixaThreadStop("08", barraProgressoCx8, jLabelCaixa8, jLabelTempoClienteDaVezCx8, logDoPrograma, jLabelServicoCx8);
        //INSTANCIA DOS CAIXAS

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupAnexoEmail = new javax.swing.ButtonGroup();
        jTabbedPaneAtendimento = new javax.swing.JTabbedPane();
        jPanelAtendimento = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        labelClientesGerados = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        labelClientesAtendidos = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanelControleCronometro = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabelCronometro = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        btnAtendimentoSair = new javax.swing.JButton();
        jLabelLogo = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanelCaixa1 = new javax.swing.JPanel();
        barraProgressoCx1 = new javax.swing.JProgressBar();
        jLabelTempoClienteDaVezCx1 = new javax.swing.JLabel();
        jLabelCaixa1 = new javax.swing.JLabel();
        jLabelServicoCx1 = new javax.swing.JLabel();
        jPanelCaixa2 = new javax.swing.JPanel();
        barraProgressoCx2 = new javax.swing.JProgressBar();
        jLabelTempoClienteDaVezCx2 = new javax.swing.JLabel();
        jLabelCaixa2 = new javax.swing.JLabel();
        jLabelServicoCx2 = new javax.swing.JLabel();
        jPanelCaixa3 = new javax.swing.JPanel();
        barraProgressoCx3 = new javax.swing.JProgressBar();
        jLabelTempoClienteDaVezCx3 = new javax.swing.JLabel();
        jLabelCaixa3 = new javax.swing.JLabel();
        jLabelServicoCx3 = new javax.swing.JLabel();
        jPanelCaixa4 = new javax.swing.JPanel();
        barraProgressoCx4 = new javax.swing.JProgressBar();
        jLabelTempoClienteDaVezCx4 = new javax.swing.JLabel();
        jLabelCaixa4 = new javax.swing.JLabel();
        jLabelServicoCx4 = new javax.swing.JLabel();
        jPanelCaixa5 = new javax.swing.JPanel();
        barraProgressoCx5 = new javax.swing.JProgressBar();
        jLabelTempoClienteDaVezCx5 = new javax.swing.JLabel();
        jLabelCaixa5 = new javax.swing.JLabel();
        jLabelServicoCx5 = new javax.swing.JLabel();
        jPanelCaixa6 = new javax.swing.JPanel();
        barraProgressoCx6 = new javax.swing.JProgressBar();
        jLabelTempoClienteDaVezCx6 = new javax.swing.JLabel();
        jLabelCaixa6 = new javax.swing.JLabel();
        jLabelServicoCx6 = new javax.swing.JLabel();
        jPanelCaixa7 = new javax.swing.JPanel();
        barraProgressoCx7 = new javax.swing.JProgressBar();
        jLabelTempoClienteDaVezCx7 = new javax.swing.JLabel();
        jLabelCaixa7 = new javax.swing.JLabel();
        jLabelServicoCx7 = new javax.swing.JLabel();
        jPanelCaixa8 = new javax.swing.JPanel();
        barraProgressoCx8 = new javax.swing.JProgressBar();
        jLabelTempoClienteDaVezCx8 = new javax.swing.JLabel();
        jLabelCaixa8 = new javax.swing.JLabel();
        jLabelServicoCx8 = new javax.swing.JLabel();
        jPanelServicosDisponiveis = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabelCompraCx1 = new javax.swing.JLabel();
        jLabelVendaCx1 = new javax.swing.JLabel();
        jLabelEnvRemessaCx1 = new javax.swing.JLabel();
        jLabelRecRemessaCx1 = new javax.swing.JLabel();
        jLabelSwiftCx1 = new javax.swing.JLabel();
        jLabelSeguroCx1 = new javax.swing.JLabel();
        jLabelCompraCx2 = new javax.swing.JLabel();
        jLabelVendaCx2 = new javax.swing.JLabel();
        jLabelEnvRemessaCx2 = new javax.swing.JLabel();
        jLabelRecRemessaCx2 = new javax.swing.JLabel();
        jLabelSwiftCx2 = new javax.swing.JLabel();
        jLabelSeguroCx2 = new javax.swing.JLabel();
        jLabelCompraCx3 = new javax.swing.JLabel();
        jLabelVendaCx3 = new javax.swing.JLabel();
        jLabelEnvRemessaCx3 = new javax.swing.JLabel();
        jLabelRecRemessaCx3 = new javax.swing.JLabel();
        jLabelSwiftCx3 = new javax.swing.JLabel();
        jLabelSeguroCx3 = new javax.swing.JLabel();
        jLabelCompraCx4 = new javax.swing.JLabel();
        jLabelVendaCx4 = new javax.swing.JLabel();
        jLabelEnvRemessaCx4 = new javax.swing.JLabel();
        jLabelRecRemessaCx4 = new javax.swing.JLabel();
        jLabelSwiftCx4 = new javax.swing.JLabel();
        jLabelSeguroCx4 = new javax.swing.JLabel();
        jLabelCompraCx5 = new javax.swing.JLabel();
        jLabelVendaCx5 = new javax.swing.JLabel();
        jLabelEnvRemessaCx5 = new javax.swing.JLabel();
        jLabelRecRemessaCx5 = new javax.swing.JLabel();
        jLabelSwiftCx5 = new javax.swing.JLabel();
        jLabelSeguroCx5 = new javax.swing.JLabel();
        jLabelCompraCx6 = new javax.swing.JLabel();
        jLabelVendaCx6 = new javax.swing.JLabel();
        jLabelEnvRemessaCx6 = new javax.swing.JLabel();
        jLabelRecRemessaCx6 = new javax.swing.JLabel();
        jLabelSwiftCx6 = new javax.swing.JLabel();
        jLabelSeguroCx6 = new javax.swing.JLabel();
        jLabelCompraCx7 = new javax.swing.JLabel();
        jLabelVendaCx7 = new javax.swing.JLabel();
        jLabelEnvRemessaCx7 = new javax.swing.JLabel();
        jLabelRecRemessaCx7 = new javax.swing.JLabel();
        jLabelSwiftCx7 = new javax.swing.JLabel();
        jLabelSeguroCx7 = new javax.swing.JLabel();
        jLabelCompraCx8 = new javax.swing.JLabel();
        jLabelVendaCx8 = new javax.swing.JLabel();
        jLabelEnvRemessaCx8 = new javax.swing.JLabel();
        jLabelRecRemessaCx8 = new javax.swing.JLabel();
        jLabelSwiftCx8 = new javax.swing.JLabel();
        jLabelSeguroCx8 = new javax.swing.JLabel();
        jPanelTestes = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanelLog = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        logDoPrograma = new javax.swing.JTextArea();
        jButton9 = new javax.swing.JButton();
        jLabelLocalDoLog = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabelLogo1 = new javax.swing.JLabel();
        jPanelRelatorios = new javax.swing.JPanel();
        jLabelLogo2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabelTotalAtCx1 = new javax.swing.JLabel();
        jLabelTotalVenda = new javax.swing.JLabel();
        jLabelTotalEnvRemessa = new javax.swing.JLabel();
        jLabelTotalRecRemessa = new javax.swing.JLabel();
        jLabelTotalSwift = new javax.swing.JLabel();
        jLabelTotalSeguro = new javax.swing.JLabel();
        jLabelTotalClientesAtendidos = new javax.swing.JLabel();
        jRadioButtonComAnexo = new javax.swing.JRadioButton();
        jRadioButtonSemAnexo = new javax.swing.JRadioButton();
        jLabel22 = new javax.swing.JLabel();
        jLabelTotalCompra = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabelTotalAtCx2 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabelTotalAtCx4 = new javax.swing.JLabel();
        jLabelTotalAtCx3 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabelTotalAtCx5 = new javax.swing.JLabel();
        jLabelTotalAtCx6 = new javax.swing.JLabel();
        jLabelTotalAtCx7 = new javax.swing.JLabel();
        jLabelTotalAtCx8 = new javax.swing.JLabel();

        setClosable(true);
        setMaximizable(true);
        setTitle("Monetae - Atendimento");
        setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Clientes na loja:");

        labelClientesGerados.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelClientesGerados.setText("9999");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/monetae/imagens/iconOpen32x32.png"))); // NOI18N
        jButton1.setText("Abrir Loja");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Clientes atendidos até o momento:");

        labelClientesAtendidos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelClientesAtendidos.setText("9999");

        jLabel4.setText("Tempo de AT");

        jLabel5.setText("Cliente em atendimento");

        jPanelControleCronometro.setBorder(javax.swing.BorderFactory.createTitledBorder("Cronômetro"));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/monetae/imagens/iconPause32x32.png"))); // NOI18N
        jButton4.setToolTipText("Pause");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/monetae/imagens/iconPlay32x32.png"))); // NOI18N
        jButton5.setToolTipText("Play");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/monetae/imagens/iconStop32x32.png"))); // NOI18N
        jButton6.setToolTipText("Stop");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabelCronometro.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabelCronometro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCronometro.setText("00:00:00");
        jLabelCronometro.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanelControleCronometroLayout = new javax.swing.GroupLayout(jPanelControleCronometro);
        jPanelControleCronometro.setLayout(jPanelControleCronometroLayout);
        jPanelControleCronometroLayout.setHorizontalGroup(
            jPanelControleCronometroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelControleCronometroLayout.createSequentialGroup()
                .addGroup(jPanelControleCronometroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelControleCronometroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelCronometro, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelControleCronometroLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanelControleCronometroLayout.setVerticalGroup(
            jPanelControleCronometroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelControleCronometroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelCronometro, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelControleCronometroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton3.setText("Iniciar os atendimento");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnAtendimentoSair.setText("Fechar Janela");
        btnAtendimentoSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtendimentoSairActionPerformed(evt);
            }
        });

        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/monetae/imagens/monetae-logo.png"))); // NOI18N

        jLabel21.setText("Serviço Solicitado");

        jPanelCaixa1.setBorder(javax.swing.BorderFactory.createTitledBorder("Caixa 01"));

        barraProgressoCx1.setStringPainted(true);

        jLabelTempoClienteDaVezCx1.setText("0000");

        jLabelCaixa1.setText("XXX");

        jLabelServicoCx1.setText("XXX");

        javax.swing.GroupLayout jPanelCaixa1Layout = new javax.swing.GroupLayout(jPanelCaixa1);
        jPanelCaixa1.setLayout(jPanelCaixa1Layout);
        jPanelCaixa1Layout.setHorizontalGroup(
            jPanelCaixa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCaixa1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(barraProgressoCx1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTempoClienteDaVezCx1)
                .addGap(53, 53, 53)
                .addComponent(jLabelCaixa1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelServicoCx1)
                .addGap(37, 37, 37))
        );
        jPanelCaixa1Layout.setVerticalGroup(
            jPanelCaixa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCaixa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(barraProgressoCx1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabelTempoClienteDaVezCx1)
                .addComponent(jLabelCaixa1)
                .addComponent(jLabelServicoCx1))
        );

        jPanelCaixa2.setBorder(javax.swing.BorderFactory.createTitledBorder("Caixa 02"));
        jPanelCaixa2.setPreferredSize(new java.awt.Dimension(826, 63));

        barraProgressoCx2.setStringPainted(true);

        jLabelTempoClienteDaVezCx2.setText("0000");

        jLabelCaixa2.setText("XXX");

        jLabelServicoCx2.setText("XXX");

        javax.swing.GroupLayout jPanelCaixa2Layout = new javax.swing.GroupLayout(jPanelCaixa2);
        jPanelCaixa2.setLayout(jPanelCaixa2Layout);
        jPanelCaixa2Layout.setHorizontalGroup(
            jPanelCaixa2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCaixa2Layout.createSequentialGroup()
                .addComponent(barraProgressoCx2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTempoClienteDaVezCx2)
                .addGap(55, 55, 55)
                .addComponent(jLabelCaixa2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelServicoCx2)
                .addGap(42, 42, 42))
        );
        jPanelCaixa2Layout.setVerticalGroup(
            jPanelCaixa2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCaixa2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(barraProgressoCx2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabelTempoClienteDaVezCx2)
                .addComponent(jLabelCaixa2)
                .addComponent(jLabelServicoCx2))
        );

        jPanelCaixa3.setBorder(javax.swing.BorderFactory.createTitledBorder("Caixa 03"));
        jPanelCaixa3.setPreferredSize(new java.awt.Dimension(826, 63));

        barraProgressoCx3.setStringPainted(true);

        jLabelTempoClienteDaVezCx3.setText("0000");

        jLabelCaixa3.setText("XXX");

        jLabelServicoCx3.setText("XXX");

        javax.swing.GroupLayout jPanelCaixa3Layout = new javax.swing.GroupLayout(jPanelCaixa3);
        jPanelCaixa3.setLayout(jPanelCaixa3Layout);
        jPanelCaixa3Layout.setHorizontalGroup(
            jPanelCaixa3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCaixa3Layout.createSequentialGroup()
                .addComponent(barraProgressoCx3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTempoClienteDaVezCx3)
                .addGap(56, 56, 56)
                .addComponent(jLabelCaixa3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
                .addComponent(jLabelServicoCx3)
                .addGap(45, 45, 45))
        );
        jPanelCaixa3Layout.setVerticalGroup(
            jPanelCaixa3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCaixa3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(barraProgressoCx3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabelTempoClienteDaVezCx3)
                .addComponent(jLabelCaixa3)
                .addComponent(jLabelServicoCx3))
        );

        jPanelCaixa4.setBorder(javax.swing.BorderFactory.createTitledBorder("Caixa 04"));

        barraProgressoCx4.setStringPainted(true);

        jLabelTempoClienteDaVezCx4.setText("0000");

        jLabelCaixa4.setText("XXX");

        jLabelServicoCx4.setText("XXX");

        javax.swing.GroupLayout jPanelCaixa4Layout = new javax.swing.GroupLayout(jPanelCaixa4);
        jPanelCaixa4.setLayout(jPanelCaixa4Layout);
        jPanelCaixa4Layout.setHorizontalGroup(
            jPanelCaixa4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCaixa4Layout.createSequentialGroup()
                .addComponent(barraProgressoCx4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTempoClienteDaVezCx4)
                .addGap(55, 55, 55)
                .addComponent(jLabelCaixa4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelServicoCx4)
                .addGap(41, 41, 41))
        );
        jPanelCaixa4Layout.setVerticalGroup(
            jPanelCaixa4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCaixa4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(barraProgressoCx4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabelTempoClienteDaVezCx4)
                .addComponent(jLabelCaixa4)
                .addComponent(jLabelServicoCx4))
        );

        jPanelCaixa5.setBorder(javax.swing.BorderFactory.createTitledBorder("Caixa 05"));

        barraProgressoCx5.setStringPainted(true);

        jLabelTempoClienteDaVezCx5.setText("0000");

        jLabelCaixa5.setText("XXX");

        jLabelServicoCx5.setText("XXX");

        javax.swing.GroupLayout jPanelCaixa5Layout = new javax.swing.GroupLayout(jPanelCaixa5);
        jPanelCaixa5.setLayout(jPanelCaixa5Layout);
        jPanelCaixa5Layout.setHorizontalGroup(
            jPanelCaixa5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCaixa5Layout.createSequentialGroup()
                .addComponent(barraProgressoCx5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTempoClienteDaVezCx5)
                .addGap(56, 56, 56)
                .addComponent(jLabelCaixa5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelServicoCx5)
                .addGap(40, 40, 40))
        );
        jPanelCaixa5Layout.setVerticalGroup(
            jPanelCaixa5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCaixa5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabelTempoClienteDaVezCx5)
                .addComponent(jLabelCaixa5)
                .addComponent(jLabelServicoCx5)
                .addComponent(barraProgressoCx5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelCaixa6.setBorder(javax.swing.BorderFactory.createTitledBorder("Caixa 06"));
        jPanelCaixa6.setPreferredSize(new java.awt.Dimension(640, 47));

        barraProgressoCx6.setStringPainted(true);

        jLabelTempoClienteDaVezCx6.setText("0000");

        jLabelCaixa6.setText("XXX");

        jLabelServicoCx6.setText("XXX");

        javax.swing.GroupLayout jPanelCaixa6Layout = new javax.swing.GroupLayout(jPanelCaixa6);
        jPanelCaixa6.setLayout(jPanelCaixa6Layout);
        jPanelCaixa6Layout.setHorizontalGroup(
            jPanelCaixa6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCaixa6Layout.createSequentialGroup()
                .addComponent(barraProgressoCx6, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTempoClienteDaVezCx6)
                .addGap(55, 55, 55)
                .addComponent(jLabelCaixa6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelServicoCx6)
                .addGap(42, 42, 42))
        );
        jPanelCaixa6Layout.setVerticalGroup(
            jPanelCaixa6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCaixa6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabelTempoClienteDaVezCx6)
                .addComponent(jLabelCaixa6)
                .addComponent(jLabelServicoCx6)
                .addComponent(barraProgressoCx6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelCaixa7.setBorder(javax.swing.BorderFactory.createTitledBorder("Caixa 07"));
        jPanelCaixa7.setPreferredSize(new java.awt.Dimension(640, 50));

        barraProgressoCx7.setStringPainted(true);

        jLabelTempoClienteDaVezCx7.setText("0000");

        jLabelCaixa7.setText("XXX");

        jLabelServicoCx7.setText("XXX");

        javax.swing.GroupLayout jPanelCaixa7Layout = new javax.swing.GroupLayout(jPanelCaixa7);
        jPanelCaixa7.setLayout(jPanelCaixa7Layout);
        jPanelCaixa7Layout.setHorizontalGroup(
            jPanelCaixa7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCaixa7Layout.createSequentialGroup()
                .addComponent(barraProgressoCx7, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTempoClienteDaVezCx7)
                .addGap(56, 56, 56)
                .addComponent(jLabelCaixa7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelServicoCx7)
                .addGap(38, 38, 38))
        );
        jPanelCaixa7Layout.setVerticalGroup(
            jPanelCaixa7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCaixa7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(barraProgressoCx7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabelTempoClienteDaVezCx7)
                .addComponent(jLabelCaixa7)
                .addComponent(jLabelServicoCx7))
        );

        jPanelCaixa8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Caixa 08")));

        barraProgressoCx8.setStringPainted(true);

        jLabelTempoClienteDaVezCx8.setText("0000");

        jLabelCaixa8.setText("XXX");

        jLabelServicoCx8.setText("XXX");

        javax.swing.GroupLayout jPanelCaixa8Layout = new javax.swing.GroupLayout(jPanelCaixa8);
        jPanelCaixa8.setLayout(jPanelCaixa8Layout);
        jPanelCaixa8Layout.setHorizontalGroup(
            jPanelCaixa8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCaixa8Layout.createSequentialGroup()
                .addComponent(barraProgressoCx8, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTempoClienteDaVezCx8)
                .addGap(59, 59, 59)
                .addComponent(jLabelCaixa8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelServicoCx8)
                .addGap(35, 35, 35))
        );
        jPanelCaixa8Layout.setVerticalGroup(
            jPanelCaixa8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCaixa8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabelTempoClienteDaVezCx8)
                .addComponent(jLabelCaixa8)
                .addComponent(jLabelServicoCx8)
                .addComponent(barraProgressoCx8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelServicosDisponiveis.setBorder(javax.swing.BorderFactory.createTitledBorder("Serviços Disponíveis"));

        jLabel1.setText("Caixa 01");

        jLabel6.setText("Caixa 02");

        jLabel8.setText("Caixa 04");

        jLabel9.setText("Caixa 03");

        jLabel14.setText("Caixa 08");

        jLabel15.setText("Caixa 07");

        jLabel16.setText("Caixa 06");

        jLabel17.setText("Caixa 05");

        jLabelCompraCx1.setForeground(new java.awt.Color(255, 0, 0));
        jLabelCompraCx1.setText("Compra de Moeda");

        jLabelVendaCx1.setForeground(new java.awt.Color(255, 0, 0));
        jLabelVendaCx1.setText("Venda de Moeda");

        jLabelEnvRemessaCx1.setForeground(new java.awt.Color(255, 0, 0));
        jLabelEnvRemessaCx1.setText("Envio de Remessa");

        jLabelRecRemessaCx1.setForeground(new java.awt.Color(255, 0, 0));
        jLabelRecRemessaCx1.setText("Recebimento de Remessa");

        jLabelSwiftCx1.setForeground(new java.awt.Color(255, 0, 0));
        jLabelSwiftCx1.setText("Contratos Swift");

        jLabelSeguroCx1.setForeground(new java.awt.Color(255, 0, 0));
        jLabelSeguroCx1.setText("Seguro Viagem");

        jLabelCompraCx2.setForeground(new java.awt.Color(255, 0, 0));
        jLabelCompraCx2.setText("Compra de Moeda");

        jLabelVendaCx2.setForeground(new java.awt.Color(255, 0, 0));
        jLabelVendaCx2.setText("Venda de Moeda");

        jLabelEnvRemessaCx2.setForeground(new java.awt.Color(255, 0, 0));
        jLabelEnvRemessaCx2.setText("Envio de Remessa");

        jLabelRecRemessaCx2.setForeground(new java.awt.Color(255, 0, 0));
        jLabelRecRemessaCx2.setText("Recebimento de Remessa");

        jLabelSwiftCx2.setForeground(new java.awt.Color(255, 0, 0));
        jLabelSwiftCx2.setText("Contratos Swift");

        jLabelSeguroCx2.setForeground(new java.awt.Color(255, 0, 0));
        jLabelSeguroCx2.setText("Seguro Viagem");

        jLabelCompraCx3.setForeground(new java.awt.Color(255, 0, 0));
        jLabelCompraCx3.setText("Compra de Moeda");

        jLabelVendaCx3.setForeground(new java.awt.Color(255, 0, 0));
        jLabelVendaCx3.setText("Venda de Moeda");

        jLabelEnvRemessaCx3.setForeground(new java.awt.Color(255, 0, 0));
        jLabelEnvRemessaCx3.setText("Envio de Remessa");

        jLabelRecRemessaCx3.setForeground(new java.awt.Color(255, 0, 0));
        jLabelRecRemessaCx3.setText("Recebimento de Remessa");

        jLabelSwiftCx3.setForeground(new java.awt.Color(255, 0, 0));
        jLabelSwiftCx3.setText("Contratos Swift");

        jLabelSeguroCx3.setForeground(new java.awt.Color(255, 0, 0));
        jLabelSeguroCx3.setText("Seguro Viagem");

        jLabelCompraCx4.setForeground(new java.awt.Color(255, 0, 0));
        jLabelCompraCx4.setText("Compra de Moeda");

        jLabelVendaCx4.setForeground(new java.awt.Color(255, 0, 0));
        jLabelVendaCx4.setText("Venda de Moeda");

        jLabelEnvRemessaCx4.setForeground(new java.awt.Color(255, 0, 0));
        jLabelEnvRemessaCx4.setText("Envio de Remessa");

        jLabelRecRemessaCx4.setForeground(new java.awt.Color(255, 0, 0));
        jLabelRecRemessaCx4.setText("Recebimento de Remessa");

        jLabelSwiftCx4.setForeground(new java.awt.Color(255, 0, 0));
        jLabelSwiftCx4.setText("Contratos Swift");

        jLabelSeguroCx4.setForeground(new java.awt.Color(255, 0, 0));
        jLabelSeguroCx4.setText("Seguro Viagem");

        jLabelCompraCx5.setForeground(new java.awt.Color(255, 0, 0));
        jLabelCompraCx5.setText("Compra de Moeda");

        jLabelVendaCx5.setForeground(new java.awt.Color(255, 0, 0));
        jLabelVendaCx5.setText("Venda de Moeda");

        jLabelEnvRemessaCx5.setForeground(new java.awt.Color(255, 0, 0));
        jLabelEnvRemessaCx5.setText("Envio de Remessa");

        jLabelRecRemessaCx5.setForeground(new java.awt.Color(255, 0, 0));
        jLabelRecRemessaCx5.setText("Recebimento de Remessa");

        jLabelSwiftCx5.setForeground(new java.awt.Color(255, 0, 0));
        jLabelSwiftCx5.setText("Contratos Swift");

        jLabelSeguroCx5.setForeground(new java.awt.Color(255, 0, 0));
        jLabelSeguroCx5.setText("Seguro Viagem");

        jLabelCompraCx6.setForeground(new java.awt.Color(255, 0, 0));
        jLabelCompraCx6.setText("Compra de Moeda");

        jLabelVendaCx6.setForeground(new java.awt.Color(255, 0, 0));
        jLabelVendaCx6.setText("Venda de Moeda");

        jLabelEnvRemessaCx6.setForeground(new java.awt.Color(255, 0, 0));
        jLabelEnvRemessaCx6.setText("Envio de Remessa");

        jLabelRecRemessaCx6.setForeground(new java.awt.Color(255, 0, 0));
        jLabelRecRemessaCx6.setText("Recebimento de Remessa");

        jLabelSwiftCx6.setForeground(new java.awt.Color(255, 0, 0));
        jLabelSwiftCx6.setText("Contratos Swift");

        jLabelSeguroCx6.setForeground(new java.awt.Color(255, 0, 0));
        jLabelSeguroCx6.setText("Seguro Viagem");

        jLabelCompraCx7.setForeground(new java.awt.Color(255, 0, 0));
        jLabelCompraCx7.setText("Compra de Moeda");

        jLabelVendaCx7.setForeground(new java.awt.Color(255, 0, 0));
        jLabelVendaCx7.setText("Venda de Moeda");

        jLabelEnvRemessaCx7.setForeground(new java.awt.Color(255, 0, 0));
        jLabelEnvRemessaCx7.setText("Envio de Remessa");

        jLabelRecRemessaCx7.setForeground(new java.awt.Color(255, 0, 0));
        jLabelRecRemessaCx7.setText("Recebimento de Remessa");

        jLabelSwiftCx7.setForeground(new java.awt.Color(255, 0, 0));
        jLabelSwiftCx7.setText("Contratos Swift");

        jLabelSeguroCx7.setForeground(new java.awt.Color(255, 0, 0));
        jLabelSeguroCx7.setText("Seguro Viagem");

        jLabelCompraCx8.setForeground(new java.awt.Color(255, 0, 0));
        jLabelCompraCx8.setText("Compra de Moeda");

        jLabelVendaCx8.setForeground(new java.awt.Color(255, 0, 0));
        jLabelVendaCx8.setText("Venda de Moeda");

        jLabelEnvRemessaCx8.setForeground(new java.awt.Color(255, 0, 0));
        jLabelEnvRemessaCx8.setText("Envio de Remessa");

        jLabelRecRemessaCx8.setForeground(new java.awt.Color(255, 0, 0));
        jLabelRecRemessaCx8.setText("Recebimento de Remessa");

        jLabelSwiftCx8.setForeground(new java.awt.Color(255, 0, 0));
        jLabelSwiftCx8.setText("Contratos Swift");

        jLabelSeguroCx8.setForeground(new java.awt.Color(255, 0, 0));
        jLabelSeguroCx8.setText("Seguro Viagem");

        javax.swing.GroupLayout jPanelServicosDisponiveisLayout = new javax.swing.GroupLayout(jPanelServicosDisponiveis);
        jPanelServicosDisponiveis.setLayout(jPanelServicosDisponiveisLayout);
        jPanelServicosDisponiveisLayout.setHorizontalGroup(
            jPanelServicosDisponiveisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelServicosDisponiveisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelServicosDisponiveisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel17)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14))
                .addGap(29, 29, 29)
                .addGroup(jPanelServicosDisponiveisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelServicosDisponiveisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelServicosDisponiveisLayout.createSequentialGroup()
                            .addComponent(jLabelCompraCx1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelVendaCx1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelEnvRemessaCx1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelRecRemessaCx1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelSwiftCx1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelSeguroCx1))
                        .addGroup(jPanelServicosDisponiveisLayout.createSequentialGroup()
                            .addComponent(jLabelCompraCx2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelVendaCx2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelEnvRemessaCx2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelRecRemessaCx2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelSwiftCx2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelSeguroCx2))
                        .addGroup(jPanelServicosDisponiveisLayout.createSequentialGroup()
                            .addComponent(jLabelCompraCx3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelVendaCx3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelEnvRemessaCx3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelRecRemessaCx3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelSwiftCx3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelSeguroCx3))
                        .addGroup(jPanelServicosDisponiveisLayout.createSequentialGroup()
                            .addComponent(jLabelCompraCx4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelVendaCx4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelEnvRemessaCx4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelRecRemessaCx4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelSwiftCx4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelSeguroCx4)))
                    .addGroup(jPanelServicosDisponiveisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelServicosDisponiveisLayout.createSequentialGroup()
                            .addComponent(jLabelCompraCx5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelVendaCx5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelEnvRemessaCx5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelRecRemessaCx5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelSwiftCx5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelSeguroCx5))
                        .addGroup(jPanelServicosDisponiveisLayout.createSequentialGroup()
                            .addComponent(jLabelCompraCx6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelVendaCx6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelEnvRemessaCx6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelRecRemessaCx6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelSwiftCx6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelSeguroCx6))
                        .addGroup(jPanelServicosDisponiveisLayout.createSequentialGroup()
                            .addComponent(jLabelCompraCx7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelVendaCx7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelEnvRemessaCx7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelRecRemessaCx7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelSwiftCx7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelSeguroCx7))
                        .addGroup(jPanelServicosDisponiveisLayout.createSequentialGroup()
                            .addComponent(jLabelCompraCx8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelVendaCx8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelEnvRemessaCx8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelRecRemessaCx8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelSwiftCx8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelSeguroCx8))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanelServicosDisponiveisLayout.setVerticalGroup(
            jPanelServicosDisponiveisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelServicosDisponiveisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelServicosDisponiveisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelServicosDisponiveisLayout.createSequentialGroup()
                        .addGroup(jPanelServicosDisponiveisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabelCompraCx1)
                            .addComponent(jLabelVendaCx1)
                            .addComponent(jLabelEnvRemessaCx1)
                            .addComponent(jLabelRecRemessaCx1)
                            .addComponent(jLabelSwiftCx1)
                            .addComponent(jLabelSeguroCx1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelServicosDisponiveisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabelCompraCx2)
                            .addComponent(jLabelVendaCx2)
                            .addComponent(jLabelEnvRemessaCx2)
                            .addComponent(jLabelRecRemessaCx2)
                            .addComponent(jLabelSwiftCx2)
                            .addComponent(jLabelSeguroCx2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8))
                    .addGroup(jPanelServicosDisponiveisLayout.createSequentialGroup()
                        .addGroup(jPanelServicosDisponiveisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelCompraCx3)
                            .addComponent(jLabelVendaCx3)
                            .addComponent(jLabelEnvRemessaCx3)
                            .addComponent(jLabelRecRemessaCx3)
                            .addComponent(jLabelSwiftCx3)
                            .addComponent(jLabelSeguroCx3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelServicosDisponiveisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelCompraCx4)
                            .addComponent(jLabelVendaCx4)
                            .addComponent(jLabelEnvRemessaCx4)
                            .addComponent(jLabelRecRemessaCx4)
                            .addComponent(jLabelSwiftCx4)
                            .addComponent(jLabelSeguroCx4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelServicosDisponiveisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelServicosDisponiveisLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14))
                    .addGroup(jPanelServicosDisponiveisLayout.createSequentialGroup()
                        .addGroup(jPanelServicosDisponiveisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelCompraCx5)
                            .addComponent(jLabelVendaCx5)
                            .addComponent(jLabelEnvRemessaCx5)
                            .addComponent(jLabelRecRemessaCx5)
                            .addComponent(jLabelSwiftCx5)
                            .addComponent(jLabelSeguroCx5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelServicosDisponiveisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelCompraCx6)
                            .addComponent(jLabelVendaCx6)
                            .addComponent(jLabelEnvRemessaCx6)
                            .addComponent(jLabelRecRemessaCx6)
                            .addComponent(jLabelSwiftCx6)
                            .addComponent(jLabelSeguroCx6))
                        .addGap(40, 40, 40))
                    .addGroup(jPanelServicosDisponiveisLayout.createSequentialGroup()
                        .addGroup(jPanelServicosDisponiveisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelCompraCx7)
                            .addComponent(jLabelVendaCx7)
                            .addComponent(jLabelEnvRemessaCx7)
                            .addComponent(jLabelRecRemessaCx7)
                            .addComponent(jLabelSwiftCx7)
                            .addComponent(jLabelSeguroCx7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelServicosDisponiveisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelCompraCx8)
                            .addComponent(jLabelVendaCx8)
                            .addComponent(jLabelEnvRemessaCx8)
                            .addComponent(jLabelRecRemessaCx8)
                            .addComponent(jLabelSwiftCx8)
                            .addComponent(jLabelSeguroCx8))))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanelTestes.setBorder(javax.swing.BorderFactory.createTitledBorder("Área de Testes"));

        jButton8.setText("Inicia os Caixas");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton10.setText("Pausa os Atendimentos");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Encerra os Atendimentos");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("Resume as Threads");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelTestesLayout = new javax.swing.GroupLayout(jPanelTestes);
        jPanelTestes.setLayout(jPanelTestesLayout);
        jPanelTestesLayout.setHorizontalGroup(
            jPanelTestesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTestesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelTestesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton11)
                    .addComponent(jButton10)
                    .addGroup(jPanelTestesLayout.createSequentialGroup()
                        .addComponent(jButton8)
                        .addGap(72, 72, 72)
                        .addComponent(jButton12)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanelTestesLayout.setVerticalGroup(
            jPanelTestesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTestesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelTestesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jButton12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelAtendimentoLayout = new javax.swing.GroupLayout(jPanelAtendimento);
        jPanelAtendimento.setLayout(jPanelAtendimentoLayout);
        jPanelAtendimentoLayout.setHorizontalGroup(
            jPanelAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAtendimentoLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanelAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAtendimentoLayout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(68, 68, 68)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 409, Short.MAX_VALUE)
                        .addComponent(jLabelLogo))
                    .addGroup(jPanelAtendimentoLayout.createSequentialGroup()
                        .addGroup(jPanelAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelAtendimentoLayout.createSequentialGroup()
                                .addGroup(jPanelAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanelCaixa8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanelCaixa7, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
                                    .addComponent(jPanelCaixa6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
                                    .addComponent(jPanelCaixa5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanelCaixa4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanelCaixa3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
                                    .addComponent(jPanelCaixa2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
                                    .addComponent(jPanelCaixa1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(57, 57, 57)
                                .addGroup(jPanelAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanelControleCronometro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanelAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanelAtendimentoLayout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(labelClientesGerados, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanelAtendimentoLayout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(labelClientesAtendidos, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jButton1)
                                        .addGroup(jPanelAtendimentoLayout.createSequentialGroup()
                                            .addComponent(jButton3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnAtendimentoSair)))))
                            .addGroup(jPanelAtendimentoLayout.createSequentialGroup()
                                .addComponent(jPanelServicosDisponiveis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jPanelTestes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelAtendimentoLayout.setVerticalGroup(
            jPanelAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAtendimentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel21))
                .addGroup(jPanelAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelAtendimentoLayout.createSequentialGroup()
                        .addGroup(jPanelAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelAtendimentoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelCaixa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelCaixa2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelCaixa3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelCaixa4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelAtendimentoLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jPanelControleCronometro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelCaixa5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelCaixa6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelCaixa7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelAtendimentoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(labelClientesGerados))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(labelClientesAtendidos))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(btnAtendimentoSair))))
                .addGroup(jPanelAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAtendimentoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelCaixa8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelServicosDisponiveis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelAtendimentoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelTestes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabelLogo)
                        .addGap(18, 18, 18))))
        );

        jTabbedPaneAtendimento.addTab("Atendimento", jPanelAtendimento);

        logDoPrograma.setColumns(20);
        logDoPrograma.setRows(5);
        jScrollPane1.setViewportView(logDoPrograma);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/monetae/imagens/iconSave32x32.png"))); // NOI18N
        jButton9.setText("Salvar Log");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabelLocalDoLog.setText("c:\\");

            jLabel13.setText("Local de Armazenamento do Arquivo:");

            jLabelLogo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/monetae/imagens/monetae-logo.png"))); // NOI18N

            javax.swing.GroupLayout jPanelLogLayout = new javax.swing.GroupLayout(jPanelLog);
            jPanelLog.setLayout(jPanelLogLayout);
            jPanelLogLayout.setHorizontalGroup(
                jPanelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLogLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jButton9)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel13)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabelLocalDoLog)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 696, Short.MAX_VALUE)
                    .addComponent(jLabelLogo1)
                    .addContainerGap())
            );
            jPanelLogLayout.setVerticalGroup(
                jPanelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelLogLayout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                    .addGroup(jPanelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabelLogo1)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLogLayout.createSequentialGroup()
                            .addGroup(jPanelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton9)
                                .addComponent(jLabel13)
                                .addComponent(jLabelLocalDoLog))
                            .addContainerGap())))
            );

            jTabbedPaneAtendimento.addTab("Logs", jPanelLog);

            jLabelLogo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/monetae/imagens/monetae-logo.png"))); // NOI18N

            jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/monetae/imagens/iconEmail24x24.png"))); // NOI18N
            jButton2.setText("Enviar Email");
            jButton2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton2ActionPerformed(evt);
                }
            });

            jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/monetae/imagens/iconePdf24x24.png"))); // NOI18N
            jButton7.setText("Gerar PDF");
            jButton7.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton7ActionPerformed(evt);
                }
            });

            jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabel7.setText("Caixa 01:");

            jLabel10.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabel10.setText("Recebimento de Remessa");

            jLabel11.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabel11.setText("Venda de Moeda Estrangeira");

            jLabel12.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabel12.setText("Envio de Remessa");

            jLabel18.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabel18.setText("Contratos Swift");

            jLabel19.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabel19.setText("Seguro Viagem");

            jLabel20.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabel20.setText("Total de Clientes Atendidos");

            jLabelTotalAtCx1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabelTotalAtCx1.setText("9999");

            jLabelTotalVenda.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabelTotalVenda.setText("9999");

            jLabelTotalEnvRemessa.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabelTotalEnvRemessa.setText("9999");

            jLabelTotalRecRemessa.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabelTotalRecRemessa.setText("9999");

            jLabelTotalSwift.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabelTotalSwift.setText("9999");

            jLabelTotalSeguro.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabelTotalSeguro.setText("9999");

            jLabelTotalClientesAtendidos.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabelTotalClientesAtendidos.setText("9999");

            buttonGroupAnexoEmail.add(jRadioButtonComAnexo);
            jRadioButtonComAnexo.setText("Anexar PDF ao email");
            jRadioButtonComAnexo.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jRadioButtonComAnexoActionPerformed(evt);
                }
            });

            buttonGroupAnexoEmail.add(jRadioButtonSemAnexo);
            jRadioButtonSemAnexo.setSelected(true);
            jRadioButtonSemAnexo.setText("Não anexar");

            jLabel22.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabel22.setText("Quantidade de atendimento por caixa");

            jLabelTotalCompra.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabelTotalCompra.setText("9999");

            jLabel23.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabel23.setText("Compra de Moeda Estrangeira");

            jLabel24.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabel24.setText("Caixa 02:");

            jLabelTotalAtCx2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabelTotalAtCx2.setText("9999");

            jLabel25.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabel25.setText("Caixa 03:");

            jLabel26.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabel26.setText("Caixa 04:");

            jLabelTotalAtCx4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabelTotalAtCx4.setText("9999");

            jLabelTotalAtCx3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabelTotalAtCx3.setText("9999");

            jLabel27.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabel27.setText("Caixa 05:");

            jLabel28.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabel28.setText("Caixa 06:");

            jLabel29.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabel29.setText("Caixa 07:");

            jLabel30.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabel30.setText("Caixa 08:");

            jLabelTotalAtCx5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabelTotalAtCx5.setText("9999");

            jLabelTotalAtCx6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabelTotalAtCx6.setText("9999");

            jLabelTotalAtCx7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabelTotalAtCx7.setText("9999");

            jLabelTotalAtCx8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabelTotalAtCx8.setText("9999");

            javax.swing.GroupLayout jPanelRelatoriosLayout = new javax.swing.GroupLayout(jPanelRelatorios);
            jPanelRelatorios.setLayout(jPanelRelatoriosLayout);
            jPanelRelatoriosLayout.setHorizontalGroup(
                jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRelatoriosLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabelLogo2))
                .addGroup(jPanelRelatoriosLayout.createSequentialGroup()
                    .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelRelatoriosLayout.createSequentialGroup()
                            .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanelRelatoriosLayout.createSequentialGroup()
                                    .addGap(24, 24, 24)
                                    .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel12)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel18)
                                        .addComponent(jLabel19)
                                        .addComponent(jLabel20))
                                    .addGap(30, 30, 30))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRelatoriosLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel23)
                                    .addGap(18, 18, 18)))
                            .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelTotalSeguro)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRelatoriosLayout.createSequentialGroup()
                                    .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRelatoriosLayout.createSequentialGroup()
                                                    .addGap(0, 0, Short.MAX_VALUE)
                                                    .addComponent(jLabelTotalClientesAtendidos)
                                                    .addGap(67, 67, 67))
                                                .addGroup(jPanelRelatoriosLayout.createSequentialGroup()
                                                    .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabelTotalVenda)
                                                        .addComponent(jLabelTotalCompra))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                            .addGroup(jPanelRelatoriosLayout.createSequentialGroup()
                                                .addComponent(jLabelTotalEnvRemessa)
                                                .addGap(67, 67, 67)))
                                        .addGroup(jPanelRelatoriosLayout.createSequentialGroup()
                                            .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabelTotalSwift)
                                                .addComponent(jLabelTotalRecRemessa))
                                            .addGap(67, 67, 67)))
                                    .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel22)
                                        .addGroup(jPanelRelatoriosLayout.createSequentialGroup()
                                            .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanelRelatoriosLayout.createSequentialGroup()
                                                    .addComponent(jLabel26)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabelTotalAtCx4))
                                                .addGroup(jPanelRelatoriosLayout.createSequentialGroup()
                                                    .addComponent(jLabel25)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabelTotalAtCx3))
                                                .addGroup(jPanelRelatoriosLayout.createSequentialGroup()
                                                    .addComponent(jLabel24)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabelTotalAtCx2))
                                                .addGroup(jPanelRelatoriosLayout.createSequentialGroup()
                                                    .addComponent(jLabel7)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabelTotalAtCx1)))
                                            .addGap(28, 28, 28)
                                            .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanelRelatoriosLayout.createSequentialGroup()
                                                    .addComponent(jLabel30)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabelTotalAtCx8))
                                                .addGroup(jPanelRelatoriosLayout.createSequentialGroup()
                                                    .addComponent(jLabel29)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabelTotalAtCx7))
                                                .addGroup(jPanelRelatoriosLayout.createSequentialGroup()
                                                    .addComponent(jLabel28)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabelTotalAtCx6))
                                                .addGroup(jPanelRelatoriosLayout.createSequentialGroup()
                                                    .addComponent(jLabel27)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabelTotalAtCx5))))))))
                        .addGroup(jPanelRelatoriosLayout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jRadioButtonComAnexo)
                                .addGroup(jPanelRelatoriosLayout.createSequentialGroup()
                                    .addComponent(jButton2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton7))
                                .addComponent(jRadioButtonSemAnexo))))
                    .addContainerGap(660, Short.MAX_VALUE))
            );
            jPanelRelatoriosLayout.setVerticalGroup(
                jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRelatoriosLayout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(jLabel23)
                        .addComponent(jLabelTotalCompra))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelRelatoriosLayout.createSequentialGroup()
                            .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(jLabelTotalVenda)
                                .addComponent(jLabelTotalAtCx1)
                                .addComponent(jLabel7))
                            .addGap(5, 5, 5)
                            .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(jLabelTotalEnvRemessa)
                                .addComponent(jLabelTotalAtCx2)
                                .addComponent(jLabel24))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanelRelatoriosLayout.createSequentialGroup()
                                    .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabelTotalRecRemessa))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel18)
                                        .addComponent(jLabelTotalSwift)))
                                .addGroup(jPanelRelatoriosLayout.createSequentialGroup()
                                    .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelTotalAtCx3)
                                        .addComponent(jLabel25))
                                    .addGap(5, 5, 5)
                                    .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelTotalAtCx4)
                                        .addComponent(jLabel26)))))
                        .addGroup(jPanelRelatoriosLayout.createSequentialGroup()
                            .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelTotalAtCx5)
                                .addComponent(jLabel27))
                            .addGap(5, 5, 5)
                            .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelTotalAtCx6)
                                .addComponent(jLabel28))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelTotalAtCx7)
                                .addComponent(jLabel29))
                            .addGap(5, 5, 5)
                            .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelTotalAtCx8)
                                .addComponent(jLabel30))))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(jLabelTotalSeguro))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(jLabelTotalClientesAtendidos))
                    .addGap(35, 35, 35)
                    .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jRadioButtonComAnexo)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jRadioButtonSemAnexo)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
                    .addComponent(jLabelLogo2))
            );

            jTabbedPaneAtendimento.addTab("Relatórios", jPanelRelatorios);

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPaneAtendimento)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPaneAtendimento, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)
            );

            setBounds(0, 0, 1373, 708);
        }// </editor-fold>//GEN-END:initComponents

    private void btnAtendimentoSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtendimentoSairActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnAtendimentoSairActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ClienteControl.geraCliente(20);
        labelClientesGerados.setText("" + listaDeClientesGerados.size());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        timer.stop();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        timer.restart();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        stopTime();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        timer.restart();
        try {
            CxT01 = new CaixaThread("01", barraProgressoCx1, jLabelCaixa1, jLabelTempoClienteDaVezCx1, logDoPrograma, jLabelServicoCx1);
            Thread.sleep(10);
            CxT02 = new CaixaThread("02", barraProgressoCx2, jLabelCaixa2, jLabelTempoClienteDaVezCx2, logDoPrograma, jLabelServicoCx2);
            Thread.sleep(10);
            CxT03 = new CaixaThread("03", barraProgressoCx3, jLabelCaixa3, jLabelTempoClienteDaVezCx3, logDoPrograma, jLabelServicoCx3);
            Thread.sleep(10);
            CxT04 = new CaixaThread("04", barraProgressoCx4, jLabelCaixa4, jLabelTempoClienteDaVezCx4, logDoPrograma, jLabelServicoCx4);
            Thread.sleep(10);
            CxT05 = new CaixaThread("05", barraProgressoCx5, jLabelCaixa5, jLabelTempoClienteDaVezCx5, logDoPrograma, jLabelServicoCx5);
            Thread.sleep(10);
            CxT06 = new CaixaThread("06", barraProgressoCx6, jLabelCaixa6, jLabelTempoClienteDaVezCx6, logDoPrograma, jLabelServicoCx6);
            Thread.sleep(10);
            CxT07 = new CaixaThread("07", barraProgressoCx7, jLabelCaixa7, jLabelTempoClienteDaVezCx7, logDoPrograma, jLabelServicoCx7);
            Thread.sleep(10);
            CxT08 = new CaixaThread("08", barraProgressoCx8, jLabelCaixa8, jLabelTempoClienteDaVezCx8, logDoPrograma, jLabelServicoCx8);

        } catch (InterruptedException ex) {
            Logger.getLogger(TelaTeste.class.getName()).log(Level.SEVERE, null, ex);
        }

        //INICIAR A THREAD DE ESTATISTICAS APOS INICAR ATENDIMENTO
        Estatisticas threadEstatisticas = new Estatisticas();
        //INICIAR A THREAD DE ESTATISTICAS APOS INICAR ATENDIMENTO

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:

        //VERIFICA CAMINHO DA PASTA
        File pastaLogs = new File(BASE_LOGS);
        if (!pastaLogs.exists()) {
            pastaLogs.mkdirs();
        }
        //VERIFICA CAMINHO DA PASTA

        //SALVA LOG
        File arquivoLog = new File(ARQUIVO_LOG);
        if (!arquivoLog.exists()) {
            try {
                arquivoLog.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            try {

                //PEGANDO O CONTEUDO ATUAL DO LOG
                FileReader fr = new FileReader(arquivoLog);
                BufferedReader br = new BufferedReader(fr);
                Vector logAtual = new Vector();
                while (br.ready()) {
                    logAtual.add(br.readLine());
                }
                //PEGANDO O CONTEUDO ATUAL DO LOG
                FileWriter fw = new FileWriter(arquivoLog);
                BufferedWriter bw = new BufferedWriter(fw);
                logAtual.add(logDoPrograma.getText());
                bw.write(logAtual.toString());
                bw.close();
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(TelaTeste.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //SALVA LOG
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int[] quantidade = {Integer.parseInt(jLabelTotalAtCx1.getText()), Integer.parseInt(jLabelTotalVenda.getText()),
            Integer.parseInt(jLabelTotalEnvRemessa.getText()), Integer.parseInt(jLabelTotalRecRemessa.getText()),
            Integer.parseInt(jLabelTotalSwift.getText()), Integer.parseInt(jLabelTotalSeguro.getText()), Integer.parseInt(jLabelTotalClientesAtendidos.getText())};

        String email = JOptionPane.showInputDialog("Digite o email");

        //GERA O PDF QUE IRA EM ANEXO CASO USUARIO OPTE
        if (jRadioButtonComAnexo.isSelected()) {
            //VERIFICA CAMINHO DA PASTA
            File pastaRelatorio = new File(BASE_LOGS);
            if (!pastaRelatorio.exists()) {
                pastaRelatorio.mkdirs();
            }
            //VERIFICA CAMINHO DA PASTA

            //VERIFICA SE O ARQUIVO EXISTE, SE NAO EXISTIR, O CRIA
            File arquivoRelatorio = new File(RELATORIO);
            if (!arquivoRelatorio.exists()) {
                try {
                    arquivoRelatorio.createNewFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                GeraPdf.geraPdf(RELATORIO, quantidade);
                EnviarEmail.enviaComAnexo(email, quantidade, RELATORIO);
            }
            //GERA O PDF QUE IRA EM ANEXO CASO USUARIO OPTE
        }
        if (jRadioButtonSemAnexo.isSelected()) {
            //ENVIA EMAIL SEM O RELATORIO EM ANEXO
            JOptionPane.showMessageDialog(this, "Email enviado para " + email);
            EnviarEmail.envia(email, quantidade);
            //ENVIA EMAIL SEM O RELATORIO EM ANEXO
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:

        int[] dados = {Integer.parseInt(jLabelTotalAtCx1.getText()), Integer.parseInt(jLabelTotalVenda.getText()),
            Integer.parseInt(jLabelTotalEnvRemessa.getText()), Integer.parseInt(jLabelTotalRecRemessa.getText()),
            Integer.parseInt(jLabelTotalSwift.getText()), Integer.parseInt(jLabelTotalSeguro.getText()), Integer.parseInt(jLabelTotalClientesAtendidos.getText())};

        JFileChooser relatorio = new JFileChooser();
        relatorio.setDialogTitle("Salvar Relatório");
        relatorio.setFileSelectionMode(JFileChooser.FILES_ONLY);
        relatorio.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo PDF", ".pdf");
        relatorio.setFileFilter(filter);
        int resultado = relatorio.showSaveDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File file = relatorio.getSelectedFile();
            if (file.exists()) {
                int selecionaOpcao = JOptionPane.showConfirmDialog(null,
                        " O arquivo já existe, deseja sobrescreve-lo? ", null,
                        JOptionPane.OK_CANCEL_OPTION);
                if (selecionaOpcao == JOptionPane.OK_OPTION) {
                    GeraPdf.geraPdf(file.getPath() + ".pdf", dados);
                    try {
                        Desktop.getDesktop().open(new File(file.getPath() + ".pdf"));
                    } catch (IOException ex) {
                        Logger.getLogger(TelaTeste.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (selecionaOpcao == JOptionPane.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(this, "Ação Cancelada");
                }
            } else {
                GeraPdf.geraPdf(file.getPath() + ".pdf", dados);
                try {
                    Desktop.getDesktop().open(new File(file.getPath() + ".pdf"));
                } catch (IOException ex) {
                    Logger.getLogger(TelaTeste.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (resultado == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(this, "Ação Cancelada");
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jRadioButtonComAnexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonComAnexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonComAnexoActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:

        ClienteControl.geraClienteTelaTeste(20);
        labelClientesGerados.setText("" + listaDeClientesGerados.size());

        timer.restart();
 
            
            TesteCxT01.run();
            

        //INICIAR A THREAD DE ESTATISTICAS APOS INICAR ATENDIMENTO
        Estatisticas threadEstatisticas = new Estatisticas();
        //INICIAR A THREAD DE ESTATISTICAS APOS INICAR ATENDIMENTO
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        new Thread() {
            public void run() {
                testeCx1.suspend();
                testeCx2.suspend();
                testeCx3.suspend();
                testeCx4.suspend();
                testeCx5.suspend();
                testeCx6.suspend();
                testeCx7.suspend();
                testeCx8.suspend();
            }
        }.start();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        new Thread() {
            public void run() {
                testeCx1.resume();
                testeCx2.resume();
                testeCx3.resume();
                testeCx4.resume();
                testeCx5.resume();
                testeCx6.resume();
                testeCx7.resume();
                testeCx8.resume();
            }
        }.start();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        new Thread() {
            public void run() {
                TesteCxT01.stop();
                TesteCxT02.stop();
                TesteCxT03.stop();
                TesteCxT04.stop();
                TesteCxT05.stop();
                TesteCxT06.stop();
                TesteCxT07.stop();
                TesteCxT08.stop();
                timer.stop();
            }
        }.start();
    }//GEN-LAST:event_jButton11ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JProgressBar barraProgressoCx1;
    public static javax.swing.JProgressBar barraProgressoCx2;
    public static javax.swing.JProgressBar barraProgressoCx3;
    public static javax.swing.JProgressBar barraProgressoCx4;
    public static javax.swing.JProgressBar barraProgressoCx5;
    public static javax.swing.JProgressBar barraProgressoCx6;
    public static javax.swing.JProgressBar barraProgressoCx7;
    public static javax.swing.JProgressBar barraProgressoCx8;
    public static javax.swing.JButton btnAtendimentoSair;
    private javax.swing.ButtonGroup buttonGroupAnexoEmail;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JLabel jLabelCaixa1;
    public static javax.swing.JLabel jLabelCaixa2;
    public static javax.swing.JLabel jLabelCaixa3;
    public static javax.swing.JLabel jLabelCaixa4;
    public static javax.swing.JLabel jLabelCaixa5;
    public static javax.swing.JLabel jLabelCaixa6;
    public static javax.swing.JLabel jLabelCaixa7;
    public static javax.swing.JLabel jLabelCaixa8;
    public static javax.swing.JLabel jLabelCompraCx1;
    public static javax.swing.JLabel jLabelCompraCx2;
    public static javax.swing.JLabel jLabelCompraCx3;
    public static javax.swing.JLabel jLabelCompraCx4;
    public static javax.swing.JLabel jLabelCompraCx5;
    public static javax.swing.JLabel jLabelCompraCx6;
    public static javax.swing.JLabel jLabelCompraCx7;
    public static javax.swing.JLabel jLabelCompraCx8;
    public static javax.swing.JLabel jLabelCronometro;
    public static javax.swing.JLabel jLabelEnvRemessaCx1;
    public static javax.swing.JLabel jLabelEnvRemessaCx2;
    public static javax.swing.JLabel jLabelEnvRemessaCx3;
    public static javax.swing.JLabel jLabelEnvRemessaCx4;
    public static javax.swing.JLabel jLabelEnvRemessaCx5;
    public static javax.swing.JLabel jLabelEnvRemessaCx6;
    public static javax.swing.JLabel jLabelEnvRemessaCx7;
    public static javax.swing.JLabel jLabelEnvRemessaCx8;
    private javax.swing.JLabel jLabelLocalDoLog;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelLogo1;
    private javax.swing.JLabel jLabelLogo2;
    public static javax.swing.JLabel jLabelRecRemessaCx1;
    public static javax.swing.JLabel jLabelRecRemessaCx2;
    public static javax.swing.JLabel jLabelRecRemessaCx3;
    public static javax.swing.JLabel jLabelRecRemessaCx4;
    public static javax.swing.JLabel jLabelRecRemessaCx5;
    public static javax.swing.JLabel jLabelRecRemessaCx6;
    public static javax.swing.JLabel jLabelRecRemessaCx7;
    public static javax.swing.JLabel jLabelRecRemessaCx8;
    public static javax.swing.JLabel jLabelSeguroCx1;
    public static javax.swing.JLabel jLabelSeguroCx2;
    public static javax.swing.JLabel jLabelSeguroCx3;
    public static javax.swing.JLabel jLabelSeguroCx4;
    public static javax.swing.JLabel jLabelSeguroCx5;
    public static javax.swing.JLabel jLabelSeguroCx6;
    public static javax.swing.JLabel jLabelSeguroCx7;
    public static javax.swing.JLabel jLabelSeguroCx8;
    public static javax.swing.JLabel jLabelServicoCx1;
    public static javax.swing.JLabel jLabelServicoCx2;
    public static javax.swing.JLabel jLabelServicoCx3;
    public static javax.swing.JLabel jLabelServicoCx4;
    public static javax.swing.JLabel jLabelServicoCx5;
    public static javax.swing.JLabel jLabelServicoCx6;
    public static javax.swing.JLabel jLabelServicoCx7;
    public static javax.swing.JLabel jLabelServicoCx8;
    public static javax.swing.JLabel jLabelSwiftCx1;
    public static javax.swing.JLabel jLabelSwiftCx2;
    public static javax.swing.JLabel jLabelSwiftCx3;
    public static javax.swing.JLabel jLabelSwiftCx4;
    public static javax.swing.JLabel jLabelSwiftCx5;
    public static javax.swing.JLabel jLabelSwiftCx6;
    public static javax.swing.JLabel jLabelSwiftCx7;
    public static javax.swing.JLabel jLabelSwiftCx8;
    public static javax.swing.JLabel jLabelTempoClienteDaVezCx1;
    public static javax.swing.JLabel jLabelTempoClienteDaVezCx2;
    public static javax.swing.JLabel jLabelTempoClienteDaVezCx3;
    public static javax.swing.JLabel jLabelTempoClienteDaVezCx4;
    public static javax.swing.JLabel jLabelTempoClienteDaVezCx5;
    public static javax.swing.JLabel jLabelTempoClienteDaVezCx6;
    public static javax.swing.JLabel jLabelTempoClienteDaVezCx7;
    public static javax.swing.JLabel jLabelTempoClienteDaVezCx8;
    public static javax.swing.JLabel jLabelTotalAtCx1;
    public static javax.swing.JLabel jLabelTotalAtCx2;
    public static javax.swing.JLabel jLabelTotalAtCx3;
    public static javax.swing.JLabel jLabelTotalAtCx4;
    public static javax.swing.JLabel jLabelTotalAtCx5;
    public static javax.swing.JLabel jLabelTotalAtCx6;
    public static javax.swing.JLabel jLabelTotalAtCx7;
    public static javax.swing.JLabel jLabelTotalAtCx8;
    public static javax.swing.JLabel jLabelTotalClientesAtendidos;
    public static javax.swing.JLabel jLabelTotalCompra;
    public static javax.swing.JLabel jLabelTotalEnvRemessa;
    public static javax.swing.JLabel jLabelTotalRecRemessa;
    public static javax.swing.JLabel jLabelTotalSeguro;
    public static javax.swing.JLabel jLabelTotalSwift;
    public static javax.swing.JLabel jLabelTotalVenda;
    public static javax.swing.JLabel jLabelVendaCx1;
    public static javax.swing.JLabel jLabelVendaCx2;
    public static javax.swing.JLabel jLabelVendaCx3;
    public static javax.swing.JLabel jLabelVendaCx4;
    public static javax.swing.JLabel jLabelVendaCx5;
    public static javax.swing.JLabel jLabelVendaCx6;
    public static javax.swing.JLabel jLabelVendaCx7;
    public static javax.swing.JLabel jLabelVendaCx8;
    private javax.swing.JPanel jPanelAtendimento;
    private javax.swing.JPanel jPanelCaixa1;
    private javax.swing.JPanel jPanelCaixa2;
    private javax.swing.JPanel jPanelCaixa3;
    private javax.swing.JPanel jPanelCaixa4;
    private javax.swing.JPanel jPanelCaixa5;
    private javax.swing.JPanel jPanelCaixa6;
    private javax.swing.JPanel jPanelCaixa7;
    private javax.swing.JPanel jPanelCaixa8;
    private javax.swing.JPanel jPanelControleCronometro;
    private javax.swing.JPanel jPanelLog;
    private javax.swing.JPanel jPanelRelatorios;
    private javax.swing.JPanel jPanelServicosDisponiveis;
    private javax.swing.JPanel jPanelTestes;
    private javax.swing.JRadioButton jRadioButtonComAnexo;
    private javax.swing.JRadioButton jRadioButtonSemAnexo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPaneAtendimento;
    public static javax.swing.JLabel labelClientesAtendidos;
    public static javax.swing.JLabel labelClientesGerados;
    public static javax.swing.JTextArea logDoPrograma;
    // End of variables declaration//GEN-END:variables

    private void stopTime() {
        timer.stop();
        currentHora = 0;
        currentMinuto = 0;
        currentSegundo = 0;
        jLabelCronometro.setText("00:00:00");
    }

    private void iniciarContagem() {
        ActionListener action = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentSegundo++;

                if (currentSegundo == 60) {
                    currentMinuto++;
                    currentSegundo = 0;
                }

                if (currentMinuto == 60) {
                    currentHora++;
                    currentMinuto = 0;
                }

                String hr = currentHora <= 9 ? "0" + currentHora : currentHora + "";
                String min = currentMinuto <= 9 ? "0" + currentMinuto : currentMinuto + "";
                String seg = currentSegundo <= 9 ? "0" + currentSegundo : currentSegundo + "";

                jLabelCronometro.setText(hr + ":" + min + ":" + seg);
            }
        };
        this.timer = new Timer(velocidade, action);
        this.timer.start();
    }

}
