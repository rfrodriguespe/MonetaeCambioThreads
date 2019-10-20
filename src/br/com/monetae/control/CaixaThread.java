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
package br.com.monetae.control;

import br.com.monetae.model.*;
import br.com.monetae.view.TelaAtendimento;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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
    private boolean estaSuspensa;
    private boolean foiTerminada;
    private boolean lojaFechando;

    private ArrayList<Servicos> listaServicos = new ArrayList<>();
    private final JProgressBar barra;
    private final JLabel labelClienteDaVez;
    private final JLabel tempoCliente;
    private final JLabel servicoCliente;

    //TESTE PRA CRIAÇÂO DO LOG
    private final String inicioLog = "" + new SimpleDateFormat("[dd'/'MM'/'yyyy - HH:mm:ss]").format(GregorianCalendar.getInstance().getTime());
    private final JTextArea areaLog;
    //TESTE PRA CRIAÇÂO DO LOG
 
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
        this.foiTerminada = false;
        new Thread(this, nome).start();
    }

    public boolean isLojaFechando() {
        return lojaFechando;
    }
    
    public void setLojaFechando(boolean lojaFechando) {
        this.lojaFechando = lojaFechando;
    }

    public boolean isEstaSuspensa() {
        return estaSuspensa;
    }

    public void setEstaSuspensa(boolean estaSuspensa) {
        this.estaSuspensa = estaSuspensa;
    }

    public boolean isFoiTerminada() {
        return foiTerminada;
    }

    public void setFoiTerminada(boolean foiTerminada) {
        this.foiTerminada = foiTerminada;
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

    public void suspend() {
        //ADD AO LOG
        areaLog.append(inicioLog + " Caixa: " + nome + " pausou as suas atividades.\n");
        //ADD AO LOG
        this.estaSuspensa = true;
    }

    public synchronized void resume() {
        //ADD AO LOG
        areaLog.append(inicioLog + " Caixa: " + nome + " retornou às suas atividades.\n");
        //ADD AO LOG
        this.estaSuspensa = false;
        notify();
    }

    public synchronized void stop() {
        //ADD AO LOG
        areaLog.append(inicioLog + " Caixa: " + nome + " encerrou as suas atividades.\n");
        //ADD AO LOG
        this.foiTerminada = true;
        barra.setString("Caixa Fechado");
        notify();
    }

    public boolean estaSuspensa() {
        return estaSuspensa;
    }

    @Override
    public void run() {
        //ADD AO LOG
        areaLog.append(inicioLog + " Caixa: " + nome + " iniciou as suas atividades.\n");
        //ADD AO LOG
        try {
            while (!foiTerminada) {
                barra.setString("Caixa disponível");
                tempoCliente.setText("----");
                labelClienteDaVez.setText("----");
                servicoCliente.setText("----");
                barra.setValue(0);
                atendeCliente();
                synchronized (this) {
                    while (estaSuspensa) {
                        barra.setString("Caixa Pausado");
                        wait();
                    }
                    if (this.foiTerminada) {
                        barra.setString("Caixa Fechado");
                        break;
                    }
                }
            }
            barra.setString("Caixa Fechado");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void atendeCliente() {
        if (!TelaAtendimento.listaDeClientesGerados.isEmpty()) {
            //Pegando o cliente fa vez//
            Cliente clienteDaVez = TelaAtendimento.listaDeClientesGerados.get(0);
            //TESTA SE O CLIENTE PEGO PODE SER ATENDIDO PELO CAIXA
            if (listaServicos.contains(clienteDaVez.getServico()) && !clienteDaVez.isFoiAtendido()) {
                //Seta o cliente como atendido
                clienteDaVez.setFoiAtendido(true);
                // Remove o cliente da lista dos gerados nesse momento e adiciona na de atendidos
                TelaAtendimento.listaDeClientesGerados.remove(clienteDaVez);
                clienteDaVez.setQuemAtendeu(nome);
                TelaAtendimento.listaDeAtendidos.add(clienteDaVez);
                // Remove o cliente da lista dos gerados nesse momento e adiciona na de atendidos

                //ADD AO LOG
                areaLog.append(inicioLog + " Caixa: " + nome + " está atendendo o cliente: " + clienteDaVez + "\n");
                //ADD AO LOG

                Cliente clienteAt = clienteDaVez;

                //
                int tCliente = clienteAt.getTempoAtendimento();
                //Cliente pego

                //Setando as propriedades da barra para o início da tarefa
                barra.setMinimum(0);
                barra.setMaximum(100);
                barra.setValue(0);
                // barras Setadas

                //infromando tempo, nome e id de quem está sendo atendido
                tempoCliente.setText("" + tCliente);
                labelClienteDaVez.setText("ID: " + clienteAt.getId() + ". Nome: " + clienteAt.getNome());
                servicoCliente.setText("" + clienteAt.getServico());
                //incrementando a barra
                for (int i = 0; i <= 100; i++) {
                    barra.setString("Cliente: "+clienteAt.getNome());
                    barra.setValue(i);
                    try {
                        Thread.sleep(tCliente / 100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CaixaThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //ADD AO LOG
                areaLog.append(inicioLog + " Caixa: " + nome + " terminou o atendimento do cliente ID: " + clienteAt.getId() + "\n");
                //ADD AO LOG
                
                // Zera as informações de quem foi atendido
                tempoCliente.setText("----");
                labelClienteDaVez.setText("----");
                servicoCliente.setText("----");
                barra.setValue(0);
                // Zera as informações de quem foi atendido
            } else {
                //JOptionPane.showMessageDialog(null, "Caixa " + nome + " não consegue atender o serviço requisitado pelo cliente.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Caixa " + nome + " informa que não há mais clientes para atender.");
        }
    }
}
