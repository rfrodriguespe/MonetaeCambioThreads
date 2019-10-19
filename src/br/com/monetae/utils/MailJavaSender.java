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
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;

public class MailJavaSender {

    //cria as propriedades necessarias para o envio de email
    public void senderMail(final MailJava mail) throws
         UnsupportedEncodingException, MessagingException {

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", mail.getSmtpHostMail());
        props.setProperty("mail.smtp.auth", mail.getSmtpAuth());
        props.setProperty("mail.smtp.starttls.enable", mail.getSmtpStarttls());
        props.setProperty("mail.smtp.port", mail.getSmtpPortMail());
        props.setProperty("mail.mime.charset", mail.getCharsetMail());

        //classe anonima que realiza a autenticacao
        //do usuario no servidor de email.
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                                   mail.getUserMail(), mail.getPassMail()
                 );
            }
        };

        // Cria a sessao passando as propriedades e a autenticacao
        Session session = Session.getDefaultInstance(props, auth);
        // Gera um log no console referente ao processo de envio
        session.setDebug(true);

        //cria a mensagem setando o remetente e seus destinatarios
        Message msg = new MimeMessage(session);
        //aqui seta o remetente
        msg.setFrom(new InternetAddress(
                          mail.getUserMail(), mail.getFromNameMail())
         );
        boolean first = true;
        for (Map.Entry<String, String> map : mail.getToMailsUsers().entrySet()) {
            if (first) {
                //setamos o 1° destinatario
                msg.addRecipient(Message.RecipientType.TO,
                          new InternetAddress(map.getKey(), map.getValue())
                 );
                first = false;
            } else {
                //setamos os demais destinatarios
                msg.addRecipient(Message.RecipientType.CC,
                          new InternetAddress(map.getKey(), map.getValue())
                 );
            }
        }

        // Adiciona um Assunto a Mensagem
        msg.setSubject(mail.getSubjectMail());

        // Cria o objeto que recebe o texto do corpo do email
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setContent(mail.getBodyMail(), mail.getTypeTextMail());

        // Monta a mensagem SMTP  inserindo o conteudo, texto e anexos
        Multipart mps = new MimeMultipart();
        for (int index = 0; index < mail.getFileMails().size(); index++) {

            // Cria um novo objeto para cada arquivo, e anexa o arquivo
            MimeBodyPart attachFilePart = new MimeBodyPart();
            FileDataSource fds =   new FileDataSource(
                 mail.getFileMails().get(index)
             );
            attachFilePart.setDataHandler(new DataHandler(fds));
            attachFilePart.setFileName(fds.getName());

            //adiciona os anexos da mensagem
            mps.addBodyPart(attachFilePart, index);

        }

        //adiciona o corpo texto da mensagem
       mps.addBodyPart(textPart);

        //adiciona a mensagem o conteudo texto e anexo
        msg.setContent(mps);

        // Envia a Mensagem
        Transport.send(msg);
    }
}
