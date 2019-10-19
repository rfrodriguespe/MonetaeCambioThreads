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

/**
 *
 * @author Rodrigo Ferreira Rodrigues
 * <Email: rodrigo2208@gmail.com GitHub: https://github.com/rfrodriguespe>
 */
public enum Servicos {
    
    /**
     * O Serviço Venda de moeda tem tempo de atendimento definido em 900 ms.
     */
    VendaMoeda(1000),

    /**
     * O Serviço Compra de moeda tem tempo de atendimento definido em 700 ms.
     */
    CompraMoeda(1300),

    /**
     * O Serviço Recebimento de remessa tem tempo de atendimento definido em 1100 ms.
     */
    RecRemessa(2100),

    /**
     * O Serviço Envio de remessa tem tempo de atendimento definido em 1000 ms.
     */
    EnvRemessa(2500),

    /**
     * O serviço de swift tem tempo de atendimento definido em 1500 ms.
     */
    Swift(3500),

    /**
     * O serviço de contratação de Seguro de viagem tem tempo definido em 1600 ms.
     */
    SeguroViagem(3700);

    private int tempoServico;

    private Servicos(int tempoServico) {
        this.tempoServico = tempoServico;
    }

    /**
     *
     * @return
     */
    public int getTempoServico() {
        return tempoServico;
    }

    /**
     *
     * @param tempoServico
     */
    public void setTempoServico(int tempoServico) {
        this.tempoServico = tempoServico;
    }
}