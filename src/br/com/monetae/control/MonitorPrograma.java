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
                    if (cliente.getServico().toString().equals("CompraMoeda")) {
                        totalCompra++;
                    }
                    if (cliente.getServico().toString().equals("VendaMoeda")) {
                        totalVenda++;
                    }
                    if (cliente.getServico().toString().equals("RecRemessa")) {
                        totalRecRemessa++;
                    }
                    if (cliente.getServico().toString().equals("EnvRemessa")) {
                        totalEnvRemessa++;
                    }
                    if (cliente.getServico().toString().equals("Swift")) {
                        totalSwift++;
                    }
                    if (cliente.getServico().toString().equals("SeguroViagem")) {
                        totalSeguro++;
                    }
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
                //ATUALIZAR AS LABELS DA TELA

                //ZERANDO AS VARIAVEIS PARA A PROXIMA TAREFA
                totalCompra = 0;
                totalVenda = 0;
                totalRecRemessa = 0;
                totalEnvRemessa = 0;
                totalSwift = 0;
                totalSeguro = 0;
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
