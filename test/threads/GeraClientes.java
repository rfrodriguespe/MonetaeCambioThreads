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
import java.util.ArrayList;

/**
 *
 * @author Rodrigo Ferreira Rodrigues
 * <Email: rodrigo2208@gmail.com GitHub: https://github.com/rfrodriguespe>
 */
public class GeraClientes {

    public static void main(String[] args) {

        ArrayList<Cliente> lista = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            lista.add(new Cliente());
        }

        for (Cliente cliente : lista) {
            System.out.println(cliente);

        }

        System.out.println("------- mais tres ------ ");

        for (int i = 0; i < 3; i++) {
            lista.add(new Cliente());
        }

        for (Cliente cliente : lista) {
            System.out.println(cliente);

        }
    }

}
