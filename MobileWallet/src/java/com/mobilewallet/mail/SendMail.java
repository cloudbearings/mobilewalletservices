package com.mobilewallet.mail;

import java.io.*;
import java.util.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.event.TransportEvent;
import javax.mail.event.TransportListener;
import javax.mail.internet.*;
import javax.naming.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Gopi
 */
public class SendMail {

    private static Log log = LogFactory.getLog(SendMail.class);
    private Session session = null;

    public SendMail() {
        log.info("*********SendMail Object Created**********");
        session = getSession();
    }

    public Session getSession() {
        Session session = null;
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            log.info(envCtx.lookup("mail/Session"));
            session = (Session) envCtx.lookup("mail/Session");
            log.info("java mail session object created :: vijay");

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Can not create MailSession :" + e.getMessage());
        }

        try {
            // if JNDI fails, try the old way that should work everywhere
            if (session == null) {
                Properties props = null;
                try {
                    props = System.getProperties();
                } catch (SecurityException sex) {
                    props = new Properties();
                }
                session = Session.getInstance(props, null);
            }
        } catch (Exception ex) {
            log.error(" Error Session : " + ex.getMessage());
        }
        return session;

    }

    public void sendHTMLMessage(String from, String to, String subject, String body) {
        try {

            // Get a Transport object to send e-mail
            Transport bus = session.getTransport("smtp");
            bus.addTransportListener(new TListener());

            // Connect only once here
            // Transport.send() disconnects after each send
            // Usually, no username and password is required for SMTP
            bus.connect();
            //bus.connect("67.18.28.10", "root", "wlxa9idq2");

            // Instantiate a message
            //Message msg = new MyMessage(session);
            Message msg = new MimeMessage(session);

            // Set message attributes
            msg.setFrom(new InternetAddress(from));
            //InternetAddress[] address = {new InternetAddress(to,false)};
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            // msg.setRecipients(Message.RecipientType.BCC,InternetAddress.parse(to, false));

            msg.setSubject(subject);
            msg.setSentDate(new Date());

            setHTMLContent(msg, body);
            msg.saveChanges();
            bus.sendMessage(msg, InternetAddress.parse(to, false));

            bus.close();
            bus = null;
            msg = null;

        } catch (MessagingException mex) {
            mex.printStackTrace();
            // Prints all nested (chained) exceptions as well
            log.error("Error occured in sendHTMLMessage:" + mex.getMessage());
            // How to access nested exceptions
            while (mex.getNextException() != null) {
                // Get next exception in chain
                Exception ex = mex.getNextException();
                ex.printStackTrace();
                if (!(ex instanceof MessagingException)) {
                    break;
                } else {
                    mex = (MessagingException) ex;
                }
            }
        }
        log.info(" End of Sendmail For : " + to + " AND TIME : " + System.currentTimeMillis());

    }

    public void sendHTMLMessage(String from, String to, String replyTo, String subject, String body) {
        try {

            // Get a Transport object to send e-mail
            Transport bus = session.getTransport("smtp");
            bus.addTransportListener(new TListener());

            // Connect only once here
            // Transport.send() disconnects after each send
            // Usually, no username and password is required for SMTP
            bus.connect();
            //bus.connect("67.18.28.10", "root", "wlxa9idq2");

            // Instantiate a message
            //Message msg = new MyMessage(session);
            Message msg = new MimeMessage(session);

            // Set message attributes
            msg.setFrom(new InternetAddress(from));
            //InternetAddress[] address = {new InternetAddress(to,false)};
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            // msg.setRecipients(Message.RecipientType.BCC,InternetAddress.parse(to, false));

            try {
                msg.setReplyTo(new javax.mail.Address[]{
                    new javax.mail.internet.InternetAddress(replyTo)
                });
            } catch (Exception ex) {

            }
            msg.setSubject(subject);
            msg.setSentDate(new Date());

            setHTMLContent(msg, body);
            msg.saveChanges();
            bus.sendMessage(msg, InternetAddress.parse(to, false));

            bus.close();
            bus = null;
            msg = null;

        } catch (MessagingException mex) {
            mex.printStackTrace();
            // Prints all nested (chained) exceptions as well
            log.error("Error occured in sendHTMLMessage:" + mex.getMessage());
            // How to access nested exceptions
            while (mex.getNextException() != null) {
                // Get next exception in chain
                Exception ex = mex.getNextException();
                ex.printStackTrace();
                if (!(ex instanceof MessagingException)) {
                    break;
                } else {
                    mex = (MessagingException) ex;
                }
            }
        }
        log.info(" End of Sendmail For : " + to + " AND TIME : " + System.currentTimeMillis());

    }

    public void sendHTMLMessageWithHeader(String from, String to, String subject, String body, String header) {
        try {

            // Get a Transport object to send e-mail
            Transport bus = session.getTransport("smtp");
            bus.addTransportListener(new TListener());

            // Connect only once here
            // Transport.send() disconnects after each send
            // Usually, no username and password is required for SMTP
            bus.connect();
            //bus.connect("67.18.28.10", "root", "wlxa9idq2");

            // Instantiate a message
            //Message msg = new MyMessage(session);
            Message msg = new MimeMessage(session);

            // Set message attributes
            msg.setFrom(new InternetAddress(from));
            //InternetAddress[] address = {new InternetAddress(to,false)};
            //msg.setRecipients(Message.RecipientType.TO, address);
            msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(to, false));
            msg.setHeader("List-Unsubscribe", header);
            msg.setSubject(subject);
            msg.setSentDate(new Date());

            setHTMLContent(msg, body);
            msg.saveChanges();
            bus.sendMessage(msg, InternetAddress.parse(to, false));

            bus.close();
            bus = null;
            msg = null;

        } catch (MessagingException mex) {
            mex.printStackTrace();
            // Prints all nested (chained) exceptions as well
            log.error("Error occured in sendHTMLMessage:" + mex.getMessage());
            // How to access nested exceptions
            while (mex.getNextException() != null) {
                // Get next exception in chain
                Exception ex = mex.getNextException();
                ex.printStackTrace();
                if (!(ex instanceof MessagingException)) {
                    break;
                } else {
                    mex = (MessagingException) ex;
                }
            }
        }
        log.info(" End of Sendmail For : " + to + " AND TIME : " + System.currentTimeMillis());

    }

    // A simple, single-part text/plain e-mail.
    public void setTextContent(Message msg, String mytxt) throws MessagingException {
        // Set message content
        msg.setText(mytxt);

        // Alternate form
        msg.setContent(mytxt, "text/plain");

    }

    // Set a file as an attachment.  Uses JAF FileDataSource.
    public void setFileAsAttachment(Message msg, String mytxt, String filename)
            throws MessagingException {

        // Create and fill first part
        MimeBodyPart p1 = new MimeBodyPart();
        p1.setText(mytxt);

        // Create second part
        MimeBodyPart p2 = new MimeBodyPart();

        // Put a file in the second part
        FileDataSource fds = new FileDataSource(filename);
        p2.setDataHandler(new DataHandler(fds));
        p2.setFileName(fds.getName());

        // Create the Multipart.  Add BodyParts to it.
        Multipart mp = new MimeMultipart();
        mp.addBodyPart(p1);
        mp.addBodyPart(p2);

        // Set Multipart as the message's content
        msg.setContent(mp);
    }

    // Set a single part html content.
    // Sending data of any type is similar.
    public void setHTMLContent(Message msg, String html) throws MessagingException {

        // HTMLDataSource is an inner class
        msg.setDataHandler(new DataHandler(new HTMLDataSource(html)));
    }

    /*
     * Inner class to act as a JAF datasource to send HTML e-mail content
     */
    class HTMLDataSource implements DataSource {

        private String html;

        public HTMLDataSource(String htmlString) {
            html = htmlString;
        }

        // Return html string in an InputStream.
        // A new stream must be returned each time.
        public InputStream getInputStream() throws IOException {
            if (html == null) {
                throw new IOException("Null HTML");
            }
            return new ByteArrayInputStream(html.getBytes());
        }

        public OutputStream getOutputStream() throws IOException {
            throw new IOException("This DataHandler cannot write HTML");
        }

        public String getContentType() {
            return "text/html";
        }

        public String getName() {
            return "JAF text/html dataSource to send e-mail only";
        }
    }

    class TListener implements TransportListener {

        public void messageDelivered(TransportEvent transportEvent) {
            log.info("Message Delivered");
            Message msg = transportEvent.getMessage();
            String msg_id = "";
            String to = "";
            String[] sa;
            try {
                sa = msg.getHeader("Message-ID");
                for (String s : sa) {
                    msg_id = s;
                }
            } catch (MessagingException ex) {
                log.error(" Error : " + ex.getMessage());
            }
            log.info("Message ID : " + msg_id);
            try {
                Address[] a = msg.getRecipients(Message.RecipientType.TO);
                for (Address i : a) {
                    to = i.toString();
                    log.info("TO : " + i.toString());
                }
            } catch (MessagingException ex) {
                log.error(" Error : " + ex.getMessage());
            }

            //AddMessageIDThread at = new AddMessageIDThread(msg_id, to, "no");
            //Thread t = new Thread(at);
            //t.start();
            log.info("Current Time In SendMail TransportListener : " + System.currentTimeMillis() + " For MsgId : " + msg_id);

        }

        public void messageNotDelivered(TransportEvent transportEvent) {
            log.info("Message Id Not Delivered");
            Message msg = transportEvent.getMessage();
            String msg_id = "";
            String to = "";
            String[] sa;
            try {
                sa = msg.getHeader("Message-ID");
                for (String s : sa) {
                    msg_id = s;
                }
            } catch (MessagingException ex) {
                log.error(" Error : " + ex.getMessage());
            }
            log.info("Message ID : " + msg_id);

            try {
                Address[] a = msg.getRecipients(Message.RecipientType.TO);
                for (Address i : a) {
                    to = i.toString();
                    log.info("TO : " + i.toString());
                }
            } catch (MessagingException ex) {
                log.error(" Error : " + ex.getMessage());
            }

            //AddMessageIDThread at = new AddMessageIDThread(msg_id, to, "yes");
            //Thread t = new Thread(at);
            //t.start();
            log.info("Current Time In SendMail TransportListener : " + System.currentTimeMillis() + " For MsgId : " + msg_id);
        }

        public void messagePartiallyDelivered(TransportEvent transportEvent) {
        }
    }
}
