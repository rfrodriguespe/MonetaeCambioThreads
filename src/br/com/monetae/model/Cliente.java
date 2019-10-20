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

import java.util.Random;

/**
 *
 * @author Rodrigo Ferreira Rodrigues
 * <Email: rodrigo2208@gmail.com GitHub: https://github.com/rfrodriguespe>
 */
public final class Cliente {

    private static int autoIncrement = 0;
    private int id;
    private String nome;
    private boolean ehBrasileiro;
    private Servicos servico;
    private int tempoAtendimento;
    private boolean foiAtendido;
    private String quemAtendeu;

    /**
     * Classe Cliente,
     */
    public Cliente() {
        this.id = ++autoIncrement;
        this.nome = geraNome();
        this.ehBrasileiro = geraNacionalidade();
        this.servico = geraServico();
        this.tempoAtendimento = (isEhBrasileiro()) ? 1000 + getServico().getTempoServico() : getServico().getTempoServico() + 1500;
        this.quemAtendeu = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isEhBrasileiro() {
        return ehBrasileiro;
    }

    public void setEhBrasileiro(boolean ehBrasileiro) {
        this.ehBrasileiro = ehBrasileiro;
    }

    public Servicos getServico() {
        return servico;
    }

    public void setServico(Servicos servico) {
        this.servico = servico;
    }

    public int getTempoAtendimento() {
        return tempoAtendimento;
    }

    public void setTempoAtendimento(int tempoAtendimento) {
        this.tempoAtendimento = tempoAtendimento;
    }
    
     public boolean isFoiAtendido() {
        return foiAtendido;
    }

    public void setFoiAtendido(boolean foiAtendido) {
        this.foiAtendido = foiAtendido;
    }

    public String getQuemAtendeu() {
        return quemAtendeu;
    }

    public void setQuemAtendeu(String quemAtendeu) {
        this.quemAtendeu = quemAtendeu;
    }

    public static void setAutoIncrement(int autoIncrement) {
        Cliente.autoIncrement = autoIncrement;
    }

    
    
    /**
     *
     * @return Retoan o nome do cliente aleatoriamente, tendo um ENUM como fonte
     * dos dados
     */
    public String geraNome() {
        return Nomes.values()[new Random().nextInt(Nomes.values().length)].toString();
    }

    /**
     *
     * @return Retorna a nacionalidade do cliente de maneira aleatória True para
     * brasileiro e false para estrangeiro, considera 70% para brasileiro e 30%
     * para estrangeiro, simulando que serão mais clientes brasileiros que
     * estrangeiros na casa de câmbio
     */
    public boolean geraNacionalidade() {

        return (new Random().nextInt(100) <= 70);
    }

    /**
     *
     * @return Retorna de maneira aleatória o serviço que o cliente irá
     * solicitar ao caixa
     */
    public Servicos geraServico() {
        return Servicos.values()[new Random().nextInt(Servicos.values().length)];
    }

    /**
     *
     * @return O Método toString() foi sobreescrito para debug nos testes
     */
    @Override
    public String toString() {
        String nacionalidade;
        String atendido;
        return  "Id: " + id
                + " Nome: " + nome
                + " Nacionalidade: " + (ehBrasileiro ? nacionalidade = "Brasileiro" : "Estrangeiro")
                + " Serviço requerido: " + servico
                + " Serviço requerido: " + (foiAtendido ? atendido = "Sim" : "Não")
                + " Atendido por: " + quemAtendeu
                + " Tempo total de atendimento: " + getTempoAtendimento();
    }

}
