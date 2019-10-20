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

import br.com.monetae.model.Cliente;
import br.com.monetae.view.TelaAtendimento;
import static br.com.monetae.view.TelaAtendimento.totalCompra;
import static br.com.monetae.view.TelaAtendimento.totalEnvRemessa;
import static br.com.monetae.view.TelaAtendimento.totalRecRemessa;
import static br.com.monetae.view.TelaAtendimento.totalSeguro;
import static br.com.monetae.view.TelaAtendimento.totalSwift;
import static br.com.monetae.view.TelaAtendimento.totalVenda;
import static br.com.monetae.view.TelaAtendimento.cx1;
import static br.com.monetae.view.TelaAtendimento.cx2;
import static br.com.monetae.view.TelaAtendimento.cx3;
import static br.com.monetae.view.TelaAtendimento.cx4;
import static br.com.monetae.view.TelaAtendimento.cx5;
import static br.com.monetae.view.TelaAtendimento.cx6;
import static br.com.monetae.view.TelaAtendimento.cx7;
import static br.com.monetae.view.TelaAtendimento.cx8;

/**
 *
 * @author Rodrigo Ferreira Rodrigues
 * <Email: rodrigo2208@gmail.com GitHub: https://github.com/rfrodriguespe>
 */
public class MonitorPrograma implements Runnable {

    private String nome;
    private boolean estaSuspensa;
    private boolean foiTerminada;

    public MonitorPrograma(String nome) {
        this.nome = nome;
        new Thread(this, nome).start();
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void suspend() {
        this.estaSuspensa = true;
    }

    public synchronized void resume() {
        this.estaSuspensa = false;
        notify();
    }

    public synchronized void stop() {
        this.foiTerminada = true;
        notify();
    }

    @Override
    public void run() {
        try {
            while (!foiTerminada) {
                // BLOCO RESPONSÁVEL POR MONITORAR CLIENTES NA LOJA
                if (TelaAtendimento.listaDeClientesGerados.size() < 20) {
                    ClienteControl.geraCliente(10);
                }
                // BLOCO RESPONSÁVEL POR MONITORAR CLIENTES NA LOJA

                //BLOCO RESPONSÁVEL POR ALIMENTAR AS ESTATÍSTICAS
                for (Cliente cliente : TelaAtendimento.listaDeAtendidos) {
                    // ALIMENTA AS ESTATÍSTICAS POR SERVIÇO
                    switch (cliente.getServico().toString()) {
                        case "CompraMoeda":
                            totalCompra++;
                            break;
                        case "VendaMoeda":
                            totalVenda++;
                            break;
                        case "RecRemessa":
                            totalRecRemessa++;
                            break;
                        case "EnvRemessa":
                            totalEnvRemessa++;
                            break;
                        case "Swift":
                            totalSwift++;
                            break;
                        case "SeguroViagem":
                            totalSeguro++;
                            break;
                        default:
                            break;
                    }
                    // ALIMENTA AS ESTATÍSTICAS POR SERVIÇO

                    //ALIMENTA A ESTATISTICA DE ATEDNDIMENTO INDIVIDUAL POR CAIXA
                    switch (cliente.getQuemAtendeu()) {
                        case "01":
                            cx1 ++;
                            break;
                        case "02":
                            cx2 ++;
                            break;
                        case "03":
                            cx3 ++;
                            break;
                        case "04":
                            cx4 ++;
                            break;
                        case "05":
                            cx5 ++;
                            break;
                        case "06":
                            cx6 ++;
                            break;
                        case "07":
                            cx7 ++;
                            break;
                        case "08":
                            cx8 ++;
                            break;
                        default:
                            break;
                    }
                    //ALIMENTA A ESTATISTICA DE ATEDNDIMENTO INDIVIDUAL POR CAIXA

                }
                //ATUALIZAR AS LABELS DA TELA
                TelaAtendimento.jLabelTotalCompra.setText("" + totalCompra);
                TelaAtendimento.jLabelTotalVenda.setText("" + totalVenda);
                TelaAtendimento.jLabelTotalRecRemessa.setText("" + totalRecRemessa);
                TelaAtendimento.jLabelTotalEnvRemessa.setText("" + totalEnvRemessa);
                TelaAtendimento.jLabelTotalSwift.setText("" + totalSwift);
                TelaAtendimento.jLabelTotalSeguro.setText("" + totalSeguro);
                TelaAtendimento.jLabelTotalClientesAtendidos.setText("" + TelaAtendimento.listaDeAtendidos.size());
                TelaAtendimento.labelClientesGerados.setText("" + TelaAtendimento.listaDeClientesGerados.size());
                TelaAtendimento.labelClientesAtendidos.setText("" + TelaAtendimento.listaDeAtendidos.size());
                TelaAtendimento.jLabelTotalAtCx1.setText("" + cx1);
                TelaAtendimento.jLabelTotalAtCx2.setText("" + cx2);
                TelaAtendimento.jLabelTotalAtCx3.setText("" + cx3);
                TelaAtendimento.jLabelTotalAtCx4.setText("" + cx4);
                TelaAtendimento.jLabelTotalAtCx5.setText("" + cx5);
                TelaAtendimento.jLabelTotalAtCx6.setText("" + cx6);
                TelaAtendimento.jLabelTotalAtCx7.setText("" + cx7);
                TelaAtendimento.jLabelTotalAtCx8.setText("" + cx8);
                
                //ATUALIZAR AS LABELS DA TELA

                //ZERANDO AS VARIAVEIS PARA A PROXIMA TAREFA
                totalCompra = 0;
                totalVenda = 0;
                totalRecRemessa = 0;
                totalEnvRemessa = 0;
                totalSwift = 0;
                totalSeguro = 0;
                cx1 = 0;
                cx2 = 0;
                cx3 = 0;
                cx4 = 0;
                cx5 = 0;
                cx6 = 0;
                cx7 = 0;
                cx8 = 0;
                //BLOCO RESPONSÁVEL POR ALIMENTAR AS ESTATÍSTICAS
                // INTERVALO DE REPETIÇÃO DA THREAD
                Thread.sleep(800);
                // INTERVALO DE REPETIÇÃO DA THREAD
                synchronized (this) {
                    while (estaSuspensa) {
                        wait();
                    }
                    if (this.foiTerminada) {
                        break;
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
