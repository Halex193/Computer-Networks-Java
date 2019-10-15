import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public static void main(String[] args)
    {
        System.out.println("Server started...");
        try
        {
            ServerSocket socket = new ServerSocket(1921);
            while (true)
            {
                Socket connection = socket.accept();
                InputStream inputStream = connection.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                int readInt = dataInputStream.readInt();
                System.out.println(readInt);
                DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
                dataOutputStream.writeUTF("Thank you for your contribution");
            }

        } catch (IOException e)
        {
            System.out.println("Could not bind socket to port");
        }
    }
}
