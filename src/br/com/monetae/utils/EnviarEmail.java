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

/**
 *
 * @author Rodrigo Ferreira Rodrigues
 * <Email: rodrigo2208@gmail.com GitHub: https://github.com/rfrodriguespe>
 */
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class EnviarEmail {

    public static String data() {
        Date data = new Date();
        Locale local = new Locale("pt", "BR");
        DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);
        String dataFormatada = formato.format(data);
        
        return dataFormatada;
    }

    public static void envia(String destinatario) {

        //
        //
        MailJava mj = new MailJava();
        //configuracoes de envio
        mj.setSmtpHostMail("smtp.gmail.com");
        mj.setSmtpPortMail("587");
        mj.setSmtpAuth("true");
        mj.setSmtpStarttls("true");
        mj.setUserMail("monetaecambio@gmail.com");
        mj.setFromNameMail("Monetae Câmbio Recife");
        mj.setPassMail("rfr017450");
        mj.setCharsetMail("ISO-8859-1");
        mj.setSubjectMail("Monetae Câmbio - Relatório de Atendimento");
        //mj.setBodyMail(htmlMessage());
        mj.setBodyMail(htmlMessage());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();
        map.put(destinatario, "email gmail");

        mj.setToMailsUsers(map);

        //seta quatos anexos desejar
        List<String> files = new ArrayList<String>();
        files.add("C:/temp/relatorio.pdf");

        mj.setFileMails(files);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static String textMessage() {
        return "Leia o novo tutorial JavaMail do Programando com Java.n"
                + "Saiba como enviar emails com anexo, em formato texto e html.n"
                + "Envie seu email para mais de um destinatario.";
    }

    private static String htmlMessage() {
        return "<html> "
                + "<head>"
                + "<title>Email no formato HTML com Javamail!</title> "
                + "</head> "
                + "<body> "
                + "<h2><img alt=\"\" height=\"91\" src=\"http://monetaecambio.com/wp-content/themes/monetae/assets/images/logo.png\" width=\"315\" /></h2>"
                + "<h1>Monetae C&acirc;mbio Recife</h1>"
                + "<h2>Relat&oacute;rio de Atendimento</h2>"
                + "<p>&nbsp;</p>"
                + "<pre>\n"
                + "Voc&ecirc; est&aacute; recebendo esse email com o relat&oacute;rio de atendimento gerado pelo nosso programa em anexo.</pre>"
                + "<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:600px\">"
                + "<thead>"
                + "<tr>"
                + "<th scope=\"col\">Compra de Moeda</th>"
                + "<th scope=\"col\">Venda de Moeda</th>"
                + "<th scope=\"col\">Envio de Remessa</th>"
                + "<th scope=\"col\">Recebimento de Remessa</th>"
                + "<th scope=\"col\">Contrato Swift</th>"
                + "<th scope=\"col\">Seguro Viagem</th>"
                + "<th scope=\"col\">Total de Clientes atendidos</th>"
                + "</tr>"
                + "<tr>"
                + "<th scope=\"col\">10</th>"
                + "<th scope=\"col\">11</th>"
                + "<th scope=\"col\">12</th>"
                + "<th scope=\"col\">13</th>"
                + "<th scope=\"col\">14</th>"
                + "<th scope=\"col\">15</th>"
                + "<th scope=\"col\">75</th>"
                + "</tr>"
                + "</thead>"
                + "<tbody>"
                + "</tbody>"
                + "</table>"
                + ""
                + "<p>Recife: " + data() + "</p>"
                + "<p>&nbsp;</p>"
                + "<p><span style=\"font-size:10px\">Email sem v&iacute;rus:&nbsp;</span></p>"
                + "<p><span style=\"font-size:10px\"><img alt=\"\" height=\"136\" src=\"https://www.enespa-software.de/media/image/44/3b/14/kaspersky-security-for-mail-server.jpg\" width=\"133\" /></span></p>"
                + ""
                + "</body> "
                + "</html>";
    }
}
