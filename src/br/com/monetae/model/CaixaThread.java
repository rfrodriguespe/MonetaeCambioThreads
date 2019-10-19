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
package br.com.monetae.model;

import br.com.monetae.view.TelaAtendimento;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

/**
 *
 * @author Rodrigo Ferreira Rodrigues
 * <Email: rodrigo2208@gmail.com GitHub: https://github.com/rfrodriguespe>
 */
public class CaixaThread implements Runnable {

    private String nome;
    private ArrayList<Servicos> listaServicos = new ArrayList<>();
    private final JProgressBar barra;
    private final JLabel labelClienteDaVez;
    private final JLabel tempoCliente;
    private final JLabel servicoCliente;

    //TESTE PRA CRIAÇÂO DO LOG
    private final String inicioLog = "" + new SimpleDateFormat("[dd'/'MM'/'yyyy - HH:mm:ss]").format(GregorianCalendar.getInstance().getTime());
    private final JTextArea areaLog;
    //TESTE PRA CRIAÇÂO DO LOG

    //CONTADORES PARA SOMAR AS ESTATISTICAS INDIVIDUAIS DOS CAIXAS
    private int cx1 = 0;
    private int cx2 = 0;
    private int cx3 = 0;
    private int cx4 = 0;
    private int cx5 = 0;
    private int cx6 = 0;
    private int cx7 = 0;
    private int cx8 = 0;
    //CONTADORES PARA SOMAR AS ESTATISTICAS INDIVIDUAIS DOS CAIXAS

    public CaixaThread(String nome, JProgressBar barra, JLabel labelClienteDaVez, JLabel tempoCliente, JTextArea areaLog, JLabel servicoCliente) {

        this.nome = nome;

        switch (nome) {
            case "01":
                listaServicos.add(Servicos.CompraMoeda);
                TelaAtendimento.jLabelCompraCx1.setForeground(Color.GREEN);
                listaServicos.add(Servicos.VendaMoeda);
                TelaAtendimento.jLabelVendaCx1.setForeground(Color.GREEN);
                listaServicos.add(Servicos.RecRemessa);
                TelaAtendimento.jLabelRecRemessaCx1.setForeground(Color.GREEN);
                listaServicos.add(Servicos.EnvRemessa);
                TelaAtendimento.jLabelEnvRemessaCx1.setForeground(Color.GREEN);
                break;
            case "02":
                listaServicos.add(Servicos.Swift);
                TelaAtendimento.jLabelSwiftCx2.setForeground(Color.GREEN);
                listaServicos.add(Servicos.CompraMoeda);
                TelaAtendimento.jLabelCompraCx2.setForeground(Color.GREEN);
                break;
            case "03":
                listaServicos.add(Servicos.SeguroViagem);
                TelaAtendimento.jLabelSeguroCx3.setForeground(Color.GREEN);
                break;
            case "04":
                listaServicos.add(Servicos.CompraMoeda);
                TelaAtendimento.jLabelCompraCx4.setForeground(Color.GREEN);
                listaServicos.add(Servicos.VendaMoeda);
                TelaAtendimento.jLabelVendaCx4.setForeground(Color.GREEN);
                break;
            case "05":
                listaServicos.add(Servicos.RecRemessa);
                TelaAtendimento.jLabelRecRemessaCx5.setForeground(Color.GREEN);
                listaServicos.add(Servicos.EnvRemessa);
                TelaAtendimento.jLabelEnvRemessaCx5.setForeground(Color.GREEN);
                break;
            case "06":
                listaServicos.add(Servicos.EnvRemessa);
                TelaAtendimento.jLabelEnvRemessaCx6.setForeground(Color.GREEN);
                break;
            case "07":
                listaServicos.add(Servicos.Swift);
                TelaAtendimento.jLabelSwiftCx7.setForeground(Color.GREEN);
                break;
            case "08":
                listaServicos.add(Servicos.RecRemessa);
                TelaAtendimento.jLabelRecRemessaCx8.setForeground(Color.GREEN);
                listaServicos.add(Servicos.VendaMoeda);
                TelaAtendimento.jLabelVendaCx8.setForeground(Color.GREEN);
                break;
            default:
                break;
        }

        this.barra = barra;
        this.labelClienteDaVez = labelClienteDaVez;
        this.tempoCliente = tempoCliente;
        this.servicoCliente = servicoCliente;
        this.areaLog = areaLog;
        new Thread(this, nome).start();
    }

    public ArrayList<Servicos> getListaServicos() {
        return listaServicos;
    }

    public void setListaServicos(ArrayList<Servicos> listaServicos) {
        this.listaServicos = listaServicos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public void run() {

        if (!TelaAtendimento.listaDeClientesGerados.isEmpty()) {
            Timer tarefa = new Timer();
            TimerTask tarefaTask = new TimerTask() {
                @Override
                public void run() {
                    try {
                        //Pegando o cliente fa vez//
                        Cliente clienteDaVez = TelaAtendimento.listaDeClientesGerados.get(0);
                        //TESTA SE O CLIENTE PEGO PODE SER ATENDIDO PELO CAIXA
                        if (listaServicos.contains(clienteDaVez.getServico())) {
                            //Tentativa de sincronizar o cliente
                            synchronized (clienteDaVez) {

                                //ADD AO LOG
                                areaLog.append(inicioLog + " Caixa: " + nome + " está atendendo o cliente: " + clienteDaVez + "\n");
                                //ADD AO LOG

                                //Adicionando o cliente à lista dos atendidos e retire o da vez
                                TelaAtendimento.listaDeAtendidos.add(clienteDaVez);
                                TelaAtendimento.listaDeClientesGerados.remove(clienteDaVez);
                                Cliente clienteEmAtendimento = TelaAtendimento.listaDeAtendidos.get(TelaAtendimento.listaDeAtendidos.size() - 1);
                                //
                                int tCliente = clienteEmAtendimento.getTempoAtendimento();
                                //Cliente pego

                                //Setando as propriedades da barra para o início da tarefa
                                barra.setMinimum(0);
                                barra.setMaximum(100);
                                barra.setValue(0);
                                tempoCliente.setText("" + tCliente);
                                // barras Setadas

                                //infromando o nome e id de quem está sendo atendido
                                labelClienteDaVez.setText("ID: " + clienteEmAtendimento.getId() + ". Nome: " + clienteEmAtendimento.getNome());
                                servicoCliente.setText("" + clienteDaVez.getServico());

                                //incrementando a barra
                                for (int i = 0; i <= 100; i++) {
                                    barra.setValue(i);
                                    Thread.sleep(tCliente / 100);
                                }

                                //Definindo que o cliente foi atendido, incrementando o espaço
                                TelaAtendimento.labelClientesAtendidos.setText("" + TelaAtendimento.listaDeAtendidos.size());
                                TelaAtendimento.labelClientesGerados.setText("" + TelaAtendimento.listaDeClientesGerados.size());

                                //ADD AO LOG
                                areaLog.append(inicioLog + " Caixa: " + nome + " terminou o atendimento do cliente ID: " + clienteEmAtendimento.getId() + "\n");
                                //ADD AO LOG

                                //ALIMENTA A ESTATISTICA DE ATEDNDIMENTO INDIVIDUAL POR CAIXA
                                switch (getNome()) {
                                    case "01":
                                        cx1 += 1;
                                        TelaAtendimento.jLabelTotalAtCx1.setText("" + cx1);
                                        break;
                                    case "02":
                                        cx2 += 1;
                                        TelaAtendimento.jLabelTotalAtCx2.setText("" + cx2);
                                        break;
                                    case "03":
                                        cx3 += 1;
                                        TelaAtendimento.jLabelTotalAtCx3.setText("" + cx3);
                                        break;
                                    case "04":
                                        cx4 += 1;
                                        TelaAtendimento.jLabelTotalAtCx4.setText("" + cx4);
                                        break;
                                    case "05":
                                        cx5 += 1;
                                        TelaAtendimento.jLabelTotalAtCx5.setText("" + cx5);
                                        break;
                                    case "06":
                                        cx6 += 1;
                                        TelaAtendimento.jLabelTotalAtCx6.setText("" + cx6);
                                        break;
                                    case "07":
                                        cx7 += 1;
                                        TelaAtendimento.jLabelTotalAtCx7.setText("" + cx7);
                                        break;
                                    case "08":
                                        cx8 += 1;
                                        TelaAtendimento.jLabelTotalAtCx8.setText("" + cx8);
                                        break;
                                    default:
                                        break;
                                }

                                //}
                                //ALIMENTA A ESTATISTICA DE ATEDNDIMENTO INDIVIDUAL POR CAIXA
                            }
                            //Tentativa de sincronizar o cliente
                        } else {
//                            JOptionPane.showMessageDialog(null, "Não há mais clientes para atender");
//                            tarefa.cancel();
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CaixaThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            tarefa.scheduleAtFixedRate(tarefaTask, 0, 4000);

        } else {
            JOptionPane.showMessageDialog(null, "Não há clientes para atender");
        }

    }
}
