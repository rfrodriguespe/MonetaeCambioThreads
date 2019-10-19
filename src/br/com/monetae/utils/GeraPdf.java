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

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Table;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodrigo Ferreira Rodrigues
 * <Email: rodrigo2208@gmail.com GitHub: https://github.com/rfrodriguespe>
 */
public class GeraPdf {

    public static Font fonteTitulo = new Font(Font.TIMES_ROMAN, 14, Font.BOLD, new Color(0,0,0));

    public static void geraPdf(String arquivo, int[] dados) {
        Document meuPdf = new Document();
        try {
            OutputStream  outPutStream = new FileOutputStream(arquivo);
            PdfWriter.getInstance(meuPdf, outPutStream);
            meuPdf.open();
            
            //Propriedades do PDF
            meuPdf.addAuthor("Monetae Cambio");
            meuPdf.addTitle("Monetae Câmbio - Relatório de Atendimento");
            meuPdf.addSubject("Relatórios emitidos pelo programa");
            meuPdf.addKeywords("Relatórios");
            //
            
            //Colocando a logo da empresa
            Image logoMonetae = Image.getInstance("c:/temp/monetae-logo-270p.png");
            meuPdf.add(logoMonetae);
            //
            
            
            
            Paragraph cabecalho = new Paragraph("\nMonetae Câmbio Recife - Relatório de Atendimento\n",fonteTitulo);
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            meuPdf.add((Element) cabecalho);
            meuPdf.add((Element) new Paragraph("\n"));
            
            
            // TABELA COM OS DADOS
            Table table = new Table(7,2);
            table.addCell(new Cell("Compra de Moeda"));
            table.addCell(new Cell("Compra de Moeda"));
            table.addCell(new Cell("Envio de Remessa"));
            table.addCell(new Cell("Recebimento de Remessa"));
            table.addCell(new Cell("Contrato Swift"));
            table.addCell(new Cell("Seguro Viagem"));
            table.addCell(new Cell("Total de clientes atendidos"));
            table.addCell(new Cell(""+dados[0]));
            table.addCell(new Cell(""+dados[1]));
            table.addCell(new Cell(""+dados[2]));
            table.addCell(new Cell(""+dados[3]));
            table.addCell(new Cell(""+dados[4]));
            table.addCell(new Cell(""+dados[5]));
            table.addCell(new Cell(""+dados[6]));
            
            
            
            meuPdf.add(table);
            // TABELA COM OS DADOS
            
            

            // Adiciona rodapé com a data e hora atuais
            Date dia = GregorianCalendar.getInstance().getTime();
            SimpleDateFormat formatadoData = new SimpleDateFormat("EEEEEE ',' dd ' de 'MMMM ' de ' yyyy. HH:mm:ss");
            meuPdf.add( new Paragraph("\n\nRecife, " + formatadoData.format(dia)));
            //

            meuPdf.close();
            //Desktop.getDesktop().open(new File(caminho));
        } catch (IOException | BadElementException ex) {
            Logger.getLogger(GeraPdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (com.lowagie.text.DocumentException ex) {
            Logger.getLogger(GeraPdf.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
