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

import br.com.monetae.view.TelaAtendimento;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Rodrigo Ferreira Rodrigues
 * <Email: rodrigo2208@gmail.com GitHub: https://github.com/rfrodriguespe>
 */
public class VerificaQtdeClientes implements Runnable {

    private String nome;

    public VerificaQtdeClientes(String nome) {
        this.nome = nome;
        new Thread(this, nome).start();
    }

    @Override
    public void run() {
        Timer tarefa = new Timer();
        TimerTask tarefaTask = new TimerTask() {
            @Override
            public void run() {
                if(TelaAtendimento.listaDeClientesGerados.size() < 20 ) {
                    ClienteControl.geraCliente(20);
                }   
            }
        };
        tarefa.scheduleAtFixedRate(tarefaTask,0, 1000);
    }
}
