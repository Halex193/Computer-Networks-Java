package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client
{
    private static final String IP = "172.30.117.248";
    private static final int PORT = 1234;

    public static void main(String[] args)
    {
        try
        {
            String message = "Vlad restanta la ASC";
            Socket socket = new Socket(IP, PORT);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeShort(message.length());
            dataOutputStream.write(message.getBytes());

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            System.out.println(dataInputStream.readShort());

        } catch (IOException e)
        {
            System.out.println("Connection error");
        }
    }
}
