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

import br.com.monetae.model.Cliente;

/**
 *
 * @author Rodrigo Ferreira Rodrigues
 * <Email: rodrigo2208@gmail.com GitHub: https://github.com/rfrodriguespe>
 */
public class SuspendResumeStop implements Runnable {
    
    public static Thread t;
    
    private String nome;
    private boolean estaSuspensa;
    private boolean foiTerminada;

    public SuspendResumeStop(String nome) {
        this.nome = nome;
        this.estaSuspensa = false;
        this.t = new Thread(this, nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public void run() {        
        System.out.println("A thread "+nome+" inicou sua execução");
        try {
            while(!foiTerminada){
                int i = 1;
                System.out.println("Thread "+nome+" está contando "+(i++));
                System.out.println("Current Thread "+Thread.currentThread().getName());
                Thread.sleep(500);
                synchronized(this){
                    while(estaSuspensa){
                        System.out.println(getNome()+" está suspensa");
                        wait();
                    }
                    if (this.foiTerminada){
                        break;
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("A thread "+nome+" foi terminada");
        //FIM BLOCO TESTE

    }

    public synchronized void suspend() {
        this.estaSuspensa = true;
    }

    public synchronized void resume() {
        System.out.println(getNome()+" voltou a executar");
        this.estaSuspensa = false;
        notify();
    }

    public synchronized void stop() {
        this.foiTerminada = true;
        notify();
    }
}