package run;


import server.DisscusionEngine;
import server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class run {

    private Server hostServer;
    private DisscusionEngine de;


    public static void main(String[] args) throws IOException {
    run runrun = new run();
    runrun.run();
    }


    public void run() throws IOException {
        String hostIP = "";
        try {
            URL url_name = new URL("http://bot.whatismyipaddress.com");

            BufferedReader sc =
                    new BufferedReader(new InputStreamReader(url_name.openStream()));

            // reads system IPAddress
            hostIP = sc.readLine().trim();
        } catch (Exception e) {
            hostIP = "Cannot Execute Properly";
        }

        de = new DisscusionEngine();
        hostServer = new Server(de,hostIP,836);
        hostServer.startServer();
    }

}
