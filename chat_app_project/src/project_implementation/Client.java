package project_implementation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.net.*;
import java.util.*;

import static java.lang.System.*;


public class Client extends Thread {
    Socket socket;

    PrintWriter out;
    BufferedReader in;
    BufferedReader console;

    public Client() {
        try {
            System.out.println("Client sending request.....");
            socket = new Socket("192.168.56.1", 7777);
            System.out.println("connection done");


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
                        System.out.println("Server terminated the chat");
                        socket.close();
                        break;
                    }
                    System.out.println("Server: " + msg);
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
        System.out.println("This is client");
        new Client();
    }
}