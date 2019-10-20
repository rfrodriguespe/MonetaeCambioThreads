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
import br.com.monetae.testes.TelaTeste;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Rodrigo Ferreira Rodrigues
 * <Email: rodrigo2208@gmail.com GitHub: https://github.com/rfrodriguespe>
 */
public class EstatisticasTESTE implements Runnable {

    private int totalCompra = 0;
    private int totalVenda = 0;
    private int totalRecRemessa = 0;
    private int totalEnvRemessa = 0;
    private int totalSwift = 0;
    private int totalSeguro = 0;

    public EstatisticasTESTE() {

        new Thread(this).start();
    }

    @Override
    public void run() {

        Timer tarefa = new Timer();
        TimerTask tarefaTask = new TimerTask() {
            @Override
            public void run() {
                for (Cliente cliente : TelaTeste.listaDeAtendidos) {
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
                TelaTeste.jLabelTotalCompra.setText("" + totalCompra);
                TelaTeste.jLabelTotalVenda.setText("" + totalVenda);
                TelaTeste.jLabelTotalRecRemessa.setText("" + totalRecRemessa);
                TelaTeste.jLabelTotalEnvRemessa.setText("" + totalEnvRemessa);
                TelaTeste.jLabelTotalSwift.setText("" + totalSwift);
                TelaTeste.jLabelTotalSeguro.setText("" + totalSeguro);
                TelaTeste.jLabelTotalClientesAtendidos.setText("" + TelaTeste.listaDeAtendidos.size());
                //ATUALIZAR AS LABELS DA TELA

                //ZERANDO AS VARIAVEIS PARA A PROXIMA TAREFA
                totalCompra = 0;
                totalVenda = 0;
                totalRecRemessa = 0;
                totalEnvRemessa = 0;
                totalSwift = 0;
                totalSeguro = 0;
                //ZERANDO AS VARIAVEIS PARA A PROXIMA TAREFA
            }
        };
        tarefa.scheduleAtFixedRate(tarefaTask,
                5000, 5000);
    }

}
