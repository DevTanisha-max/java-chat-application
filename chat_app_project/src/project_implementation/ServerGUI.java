package project_implementation;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class ServerGUI {

    ServerSocket server;
    Socket socket;

    BufferedReader in;
    PrintWriter out;

    JFrame frame;
    JTextArea chatArea;
    JTextField inputField;
    JButton sendButton;

    public ServerGUI() {

        frame = new JFrame("Server Chat");
        chatArea = new JTextArea();
        inputField = new JTextField();
        sendButton = new JButton("Send");

        chatArea.setEditable(false);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 14));

        frame.setLayout(new BorderLayout());

        frame.add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        try {
            server = new ServerSocket(7777);
            chatArea.append("Server started...\nWaiting for client...\n");

            socket = server.accept();
            chatArea.append("Client connected!\n");

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {
            chatArea.append("Error starting server\n");
        }

        sendButton.addActionListener(e -> sendMessage());
        inputField.addActionListener(e -> sendMessage());

        startReading();
    }

    private void sendMessage() {
        String msg = inputField.getText();

        if (msg.isEmpty()) return;

        out.println(msg);
        chatArea.append("You: " + msg + "\n");
        inputField.setText("");

        if (msg.equals("exit")) {
            try {
                socket.close();
            } catch (IOException e) {}
        }
    }

    public void startReading() {
        Runnable r = () -> {
            try {
                while (true) {
                    String msg = in.readLine();

                    if (msg == null || msg.equals("exit")) {
                        chatArea.append("Client disconnected\n");
                        socket.close();
                        break;
                    }

                    chatArea.append("Client: " + msg + "\n");
                }
            } catch (IOException e) {
                chatArea.append("Connection closed\n");
            }
        };

        new Thread(r).start();
    }

    public static void main(String[] args) {
        new ServerGUI();
    }
}
