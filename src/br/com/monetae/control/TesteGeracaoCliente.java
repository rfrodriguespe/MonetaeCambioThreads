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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Rodrigo Ferreira Rodrigues
 * <Email: rodrigo2208@gmail.com GitHub: https://github.com/rfrodriguespe>
 */
public class TesteGeracaoCliente {

    public static void main(String[] args) {

        ArrayList<Cliente> listaClientes = new ArrayList<>();

        Date inicioDia = GregorianCalendar.getInstance().getTime();
        SimpleDateFormat formatadoData = new SimpleDateFormat("hh:mm:ss");
        System.out.println();
        long inicio = System.currentTimeMillis();
        long fim;
        long tempo = 5000; //1000ms x 1s.
        long fimEsperado = inicio+tempo;
        
        fim = System.currentTimeMillis();
        
        
        System.out.println("Hora do inicio da tarefa: "+formatadoData.format(inicioDia));
        System.out.println(inicio);
        do {
            System.out.println("Testando");
            fim = System.currentTimeMillis();
        } while (fim < fimEsperado);
        
        Date fimDia = GregorianCalendar.getInstance().getTime();
        
        System.out.println("Hora do término da tarefa: "+formatadoData.format(fimDia));

        

        
    }
}
