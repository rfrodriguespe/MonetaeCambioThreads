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

import br.com.monetae.model.Cliente;
import br.com.monetae.view.TelaAtendimento;
import java.text.SimpleDateFormat;
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
    private JProgressBar barra;

    //Depois acho que removo
    private JLabel labelClienteDaVez;
    private JLabel tempoCliente;
    
    //
    
    //TESTE PRA CRIAÇÂO DO LOG
    private String inicioLog = ""+new SimpleDateFormat("[dd'/'MM'/'yyyy - HH:mm:ss]").format(GregorianCalendar.getInstance().getTime());
    private JTextArea areaLog;
    //TESTE PRA CRIAÇÂO DO LOG

    public CaixaThread(String nome, JProgressBar barra, JLabel labelClienteDaVez, JLabel tempoCliente,JTextArea areaLog) {
        this.nome = nome;
        this.barra = barra;
        //
        this.labelClienteDaVez = labelClienteDaVez;
        this.tempoCliente = tempoCliente;
        
        //
        
        //LOG
        this.areaLog = areaLog;
        //LOG
        
        new Thread(this, nome).start();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public void run() {

        if (TelaAtendimento.listaDeClientesGerados.size() != 0) {
            Timer tarefa = new Timer();
            TimerTask tarefaTask = new TimerTask() {
                @Override
                public void run() {
                    try {
                        if (TelaAtendimento.listaDeClientesGerados.size() != 0) {
                            //Pegando o cliente fa vez//
                            Cliente clienteDaVez = TelaAtendimento.listaDeClientesGerados.get(0);
                            //Tentativa de sincronizar o cliente
                            synchronized (clienteDaVez) {
                                
                                //ADD AO LOG
                                areaLog.append(inicioLog+" Caixa: "+nome+" está atendendo o cliente: "+clienteDaVez+"\n");
                                //ADD AO LOG
                                
                                //Adicionando o cliente à lista dos atendidos e retire o da vez
                                TelaAtendimento.listaDeAtendidos.add(clienteDaVez);
                                TelaAtendimento.listaDeClientesGerados.remove(clienteDaVez);
                                Cliente clienteEmAtendimento = TelaAtendimento.listaDeAtendidos.get(TelaAtendimento.listaDeAtendidos.size()-1);
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
                                labelClienteDaVez.setText("ID: "+clienteEmAtendimento.getId() + ". Nome: " + clienteEmAtendimento.getNome());

                                //incrementando a barra
                                for (int i = 0; i <= 100; i++) {
                                    barra.setValue(i);
                                    Thread.sleep(tCliente / 100);
                                }
           
                                //Definindo que o cliente foi atendido, incrementando o espaço
                                TelaAtendimento.labelClientesAtendidos.setText("" + TelaAtendimento.listaDeAtendidos.size());
                                TelaAtendimento.labelClientesGerados.setText("" + TelaAtendimento.listaDeClientesGerados.size());

                                //ADD AO LOG
                                areaLog.append(inicioLog+" Caixa: "+nome+" terminou o atendimento do cliente ID: "+clienteEmAtendimento.getId()+"\n");
                                //ADD AO LOG
                            }
                            //Tentativa de sincronizar o cliente
                        } else {
                            JOptionPane.showMessageDialog(null, "Não há mais clientes para atender");
                            tarefa.cancel();
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
