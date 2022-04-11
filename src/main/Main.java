package main;

import database.*;

/**
 *
 * @author lduong
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // mode cmdline
        if (args.length == 5) {

            /* Changer les parametres par défaut.  */
            String user = new String(args[0]);    // mettre username ici
            String passwd = new String(args[1]);  // mettre password ici
            String host = new String(args[2]);    // host
            String port = new String(args[3]);    // port
            String dbname = new String(args[4]);    // dbname
            String inputLine = new String("");

            try {
                RSQConnect connect = new RSQConnect();
                connect.open(new RSQUser(user, passwd), new RSQAddress(host, port, dbname));
                if (connect.isOpen()) {
                    System.err.println("Please enter SQL statement. Type 'exit' to terminate application.");
                    while ((inputLine.trim()).compareTo("exit") != 0) {
                        System.out.print(">>");
                        while (true) {
                            char c = (char)System.in.read();
                            if (c == '\n') {
                                if ((inputLine.trim()).compareTo("exit") == 0) {
                                    break;
                                }

                                if ((inputLine.trim()).indexOf(';') != -1) {
                                    connect.send((inputLine.trim()).substring(0, inputLine.indexOf(';')));
                                    inputLine = "";
                                    break;
                                }
                            }
                            // store the cmdline
                            inputLine += c;
                        }
                    }
                    System.err.println("(RSQConnect) Connection closed. ");
                    connect.close();
                    //System.exit(0);
                }

            } catch (Exception e) {
                System.err.println("(RSQConnect) Cannot connect to DB because : " + e.getMessage());
            }
        } else {
            System.err.println("RSQConnect");
            System.err.println("=====================");
            System.err.println("Connect to RSQ Database and allow to send via the command line ");
            System.err.println("valid SQL statements.");
            System.err.println(" Usage: RSQConnect [user] [passwd] [host] [port] [dbname]");
            System.err.println("\n");
            //System.exit(1);
        }
    }


}
