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
package threads;

import br.com.monetae.model.Cliente;

/**
 *
 * @author Rodrigo Ferreira Rodrigues
 * <Email: rodrigo2208@gmail.com GitHub: https://github.com/rfrodriguespe>
 */
public class SuspendResumeStop implements Runnable {
    
    public static Thread t;
    
    private String nome;
    private Cliente cliente;
    private boolean estaSuspensa;
    private boolean foiTerminada;

    public SuspendResumeStop(String nome) {
        this.nome = nome;
        this.cliente = cliente;
        this.estaSuspensa = false;
        this.t = new Thread(this, nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
//        try {
//            while (true) {
//                Timer timer = new Timer();
//                TimerTask tarefa = new TimerTask() {
//                    @Override
//                    public void run() {
//                        TelaAtendimento.jLabelTempoClienteDaVez.setText("" + cliente.getTempoAtendimento());
//                        TelaAtendimento.barraProgressoCx01.setMinimum(0);
//                        TelaAtendimento.barraProgressoCx01.setMaximum(cliente.getTempoAtendimento());
//                        TelaAtendimento.barraProgressoCx01.setValue(0);
//                        for (int i = 0; i < TelaAtendimento.barraProgressoCx01.getMaximum(); i++) {
//                            TelaAtendimento.barraProgressoCx01.setValue(i);
//                            try {
//                                Thread.sleep(1);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        TelaAtendimento.barraProgressoCx01.setValue(0);
//                    }
//                };
//                timer.scheduleAtFixedRate(tarefa, 0, 100000);
//                synchronized (this) {
//                    while (estaSuspensa) {
//                        wait();
//                    }
//                    if (this.foiTerminada) {
//                        break;
//                    }
//                }
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //BLOCO TESTE
        
        System.out.println("A thread "+nome+" inicou sua execução");
        try {
            
            while(!foiTerminada){
                int i = 1;
                System.out.println("Thread "+nome+" está contando "+(i++));
                Thread.sleep(500);
                synchronized(this){
                    while(estaSuspensa){
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
}


/*
*        synchronized (this) {
            
            while (estaSuspensa) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadBarra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (foiTerminada){
                break;
            }
        }

        Timer timer = new Timer();
        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {

                TelaAtendimento.jLabelTempoClienteDaVez.setText("" + cliente.getTempoAtendimento());

                TelaAtendimento.barraProgressoCx01.setMinimum(0);
                TelaAtendimento.barraProgressoCx01.setMaximum(cliente.getTempoAtendimento());
                TelaAtendimento.barraProgressoCx01.setValue(0);

                for (int i = 0; i < TelaAtendimento.barraProgressoCx01.getMaximum(); i++) {
                    TelaAtendimento.barraProgressoCx01.setValue(i);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                TelaAtendimento.barraProgressoCx01.setValue(0);

            }
        };
        timer.scheduleAtFixedRate(tarefa, 0, 100000);
 */
