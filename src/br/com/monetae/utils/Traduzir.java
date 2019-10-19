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
package br.com.monetae.utils;

import br.com.monetae.view.TelaAtendimento;
import br.com.monetae.view.TelaPrincipal;
import java.util.Locale;

/**
 *
 * @author Rodrigo Ferreira Rodrigues
 * <Email: rodrigo2208@gmail.com GitHub: https://github.com/rfrodriguespe>
 */
public class Traduzir {

    private TelaPrincipal telaPrincipal = new TelaPrincipal();
    private TelaAtendimento telaAtendimento = new TelaAtendimento();

    public Traduzir() {

    }

    public TelaPrincipal getTelaPrincipal() {
        return telaPrincipal;
    }

    public void setTelaPrincipal(TelaPrincipal telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
    }

    public TelaAtendimento getTelaAtendimento() {
        return telaAtendimento;
    }

    public void setTelaAtendimento(TelaAtendimento telaAtendimento) {
        this.telaAtendimento = telaAtendimento;
    }
    
    
    
    public static void traduzir(int idioma) {

        String[] inicio = {"Iníco","Start"};
        TelaPrincipal.menuInicio.setText(inicio[idioma]);
        String[] subMenuCostumer = {"Atendimento ao Cliente","Costumer Service"};
        TelaPrincipal.subMenuCostumer.setText(subMenuCostumer[idioma]);
        String[] subMenuSair = {"Sair","Exit"};
        TelaPrincipal.subMenuSair.setText(subMenuSair[idioma]);
        
        String[] config = {"Configurações","Settings"};
        TelaPrincipal.menuSettings.setText(config[idioma]);
        String[] subMenuIdioma = {"Idioma","Language"};
        TelaPrincipal.subMenuIdioma.setText(subMenuIdioma[idioma]);
        String[] subMenuLinguasBR = {"Português BR","Brazilian Portuguese"};
        String[] subMenuLinguasEN = {"Inglês","English"};
        TelaPrincipal.subMenuIdiomaBR.setText(subMenuLinguasBR[idioma]);
        TelaPrincipal.subMenuIdiomaEN.setText(subMenuLinguasEN[idioma]);
        
        String[] sobre = {"Sobre", "About"};
        TelaPrincipal.menuAbout.setText(sobre[idioma]);
        String[] subMenuSobre = {"Sobre o Programa", "About Application"};
        TelaPrincipal.submenuSobre.setText(subMenuSobre[idioma]);
        
        String[] botaoFechar = {"Fechar","Close"};
        TelaAtendimento.btnAtendimentoSair.setText(botaoFechar[idioma]);
        
        
    }

}
