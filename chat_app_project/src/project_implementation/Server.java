package project_implementation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.net.*;
import java.util.*;

import static java.lang.System.*;


public class Server extends Thread {
    ServerSocket server;
    Socket socket;

    PrintWriter out;
    BufferedReader in;
    BufferedReader console;

    public Server() {
        try {
            server = new ServerSocket(7777);
            System.out.println("Server is waiting to connect");
            System.out.println("waiting.......");
            socket = server.accept();

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            console = new BufferedReader(new InputStreamReader(System.in));

            start_reading();
            start_writing();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void start_reading() {
        Runnable r1 = () -> {
            try {
                while (true) {
                    String msg = in.readLine();
                    if (msg == null || msg.equals("exit")) {
                        System.out.println("Client terminated the chat");
                        socket.close();
                        break;
                    }
                    System.out.println("Client: " + msg);
                }
            } catch (IOException e) {
                System.out.println("Connection closed");
            }
        };
        new Thread(r1).start();
    }

    public void start_writing() {
        Runnable r2 = () -> {
            try {
                while (!socket.isClosed()) {
                    String content = console.readLine();
                    out.println(content);

                    if(content == null || content.equals("exit")) {
                        socket.close();
                        break;
                    }
                }

            } catch (Exception e) {
                System.out.println("Connection closed");
            }
        };
        new Thread(r2).start();

    }


    public static void main(String[] args) {
        System.out.println("This is Server");
        new Server();
    }
}