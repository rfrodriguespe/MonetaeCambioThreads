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

/**
 *
 * @author Rodrigo Ferreira Rodrigues
 * <Email: rodrigo2208@gmail.com GitHub: https://github.com/rfrodriguespe>
 */
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

public class AtendimentoCaixaHELTON extends Thread {

    public int tempo;
    private String nome;
    private Thread thread;
    private boolean Gerente;
    private Clientes cli = new Clientes();
    private JLabel gerenteCli = null;
    private ProgressCaixasDisponivel progresso;
    private Sincronizacao sincronizar = new Sincronizacao();

    public AtendimentoCaixaHELTON(int numCaixa) {
        this.nome = "Caixa " + (numCaixa + 1);
        this.progresso = sincronizar.carregaProgressDisponivel();
        thread = new Thread();
    }

    public void run() {
        int delay = 1000;   // tempo de espera antes da 1ª execução da tarefa.
        int interval = 1000;  // intervalo no qual a tarefa será executada.
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {

                System.out.println("Thread N" + nome + " Qtd Clientes: " + frmPrincipal.listaCliente.size());

                cli = sincronizar.RetornaClienteDaVez();

                if (cli != null) {
                    cli.setCaixaAtendido(nome);
                    tempo = (cli.getServico().getSleep_Caixa() + cli.getServico().getSleep_Gerente());
                    Gerente = cli.getServico().getSleep_Gerente() > 0;

                    try {
                        progresso.setCadeira(cli.getCadeiraCliente());
                        progresso.getProgress().setMinimum(0);
                        progresso.getProgress().setMaximum(tempo);

                        progresso.getProgress().setString(cli.getDesc_prior() + " iniciando atendimento");

                        if (Gerente) {
                            progresso.getProgress().setString("Encaminhando ao Gerente");
                            gerenteCli = sincronizar.gerenteDisp(frmPrincipal.getListaGerentes());
                            //thread.sleep(200);
                        }

                        for (int x = 1; x <= tempo; x++) {
                            progresso.getProgress().setValue(x);
                            thread.sleep(1);

                            if (Gerente) {
                                if (x >= cli.getServico().getSleep_Caixa() / 2) {
                                    progresso.getProgress().setString("Gerente Atendeu " + gerenteCli.getName());
                                }
                            }
                        }
                        sincronizar.LiberaProgress(progresso);
                        frmPrincipal.listaClientesAtendidos.add(cli);
                        //thread.sleep(tempo);
                        System.out.println("Nome Thread " + nome + " PRIORIDADE " + cli.getDesc_prior() + " cadeira " + cli.getCadeiraCliente());
                        sincronizar.liberaCadeira(frmPrincipal.getListaCadeiras(), cli.getCadeiraLabel());
                        if (Gerente) {
                            sincronizar.liberaGerente(frmPrincipal.getListaGerentes(), gerenteCli);
                        }
                        frmPrincipal.listaCliente.remove(cli);

                        //thread.sleep(500);
                    } catch (Exception e) {
                    }
                } else {
                    if ((cli == null) && (frmPrincipal.listaCliente.size() > 0) && (frmPrincipal.TIME_ATENDIDO > frmPrincipal.TIME_ATENDIMENTO)) {
                        frmPrincipal.listaCliente.remove(frmPrincipal.listaCliente.get(0));
                    }
                }
            }
        }, delay, interval);
    }
}
