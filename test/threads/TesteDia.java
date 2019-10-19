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

import static br.com.monetae.view.TelaAtendimento.logDoPrograma;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Rodrigo Ferreira Rodrigues
 * <Email: rodrigo2208@gmail.com GitHub: https://github.com/rfrodriguespe>
 */
public class TesteDia {
    
    public static void main(String[] args) {
        
        // Adiciona rodapé com a data e hora atuais
        Date dia = GregorianCalendar.getInstance().getTime();
        SimpleDateFormat formatadoData = new SimpleDateFormat("[dd'/'MM'/'yyyy - HH:mm:ss] ");
        System.out.println(formatadoData.format(dia));
        //
        
        
    }
    
}
