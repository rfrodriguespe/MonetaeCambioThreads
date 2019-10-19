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
import br.com.monetae.model.Servicos;

/**
 *
 * @author Rodrigo Ferreira Rodrigues
 * <Email: rodrigo2208@gmail.com GitHub: https://github.com/rfrodriguespe>
 */
public class TestaServicos {
    
    public static void main(String[] args) {
        
        Cliente cli1 = new Cliente();
        Cliente cli2 = new Cliente();
        Cliente cli3 = new Cliente();
        Cliente cli4 = new Cliente();
        Cliente cli5 = new Cliente();
        Cliente cli6 = new Cliente();
        
        cli1.setServico(Servicos.CompraMoeda);
        cli2.setServico(Servicos.VendaMoeda);
        cli3.setServico(Servicos.RecRemessa);
        cli4.setServico(Servicos.EnvRemessa);
        cli5.setServico(Servicos.Swift);
        cli6.setServico(Servicos.SeguroViagem);
        
        System.out.println(cli1.getServico());
        System.out.println(cli1.getServico().toString().equals("CompraMoeda"));
        System.out.println(cli2.getServico());
        System.out.println(cli3.getServico());
        System.out.println(cli4.getServico());
        
    }
    
}
