package com.mvt1927.SocketTCP;
import java.io.*;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class EchoChatClient {
    public final static String SERVER_IP = "127.0.0.1";
    public final static int SERVER_PORT = 2064;

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = null;
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT); // Connect to server
            InetAddress server = InetAddress.getByName(SERVER_IP);
            System.out.println("Connected: " + socket);

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            boolean exit = false;
            while(!exit){
                System.out.print("Enter your message: ");
                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr);
                String message = br.readLine();
                if (!message.equals("!exit")){
                    byte[] data = message.getBytes();
                    DatagramPacket dp = new DatagramPacket(data, data.length, server, SERVER_PORT);
                    os.write(dp);
                }else exit = true;
            }
        } catch (IOException ie) {
            System.out.println("Can't connect to server");
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}