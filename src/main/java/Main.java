import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main
{
    public static void main(String[] args)
    {
        // You can use print statements as follows for debugging, they' ll be visible when running tests.
        System.out.println("Logs from your program will appear here!");
        // Uncomment this block to pass the first stage
        try
        {
            ServerSocket serverSocket = new ServerSocket(4221);
            Socket clientSocket = new Socket();
            // Since the tester restarts your program quite often, setting SO_REUSEADDR
            // ensures that we don't run into 'Address already in use' errors
            serverSocket.setReuseAddress(true);
            clientSocket = serverSocket.accept(); // Wait for connection from client.
            InputStream inputStream = clientSocket.getInputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
            String request = input.readLine();
            System.out.println("Accepted new connection");
            System.out.println(request);
            if (request.split(" ")[1].equals("/")) // GET /asdf HTTP/1.1
            {
                clientSocket.getOutputStream().write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                System.out.println("Response OK");
            }
            else // GET / HTTP/1.1
            {
                clientSocket.getOutputStream().write("HTTP/1.1 404 Not Found\r\n\r\n".getBytes());
                System.out.println("Not Found");
            }
            clientSocket.close();
            serverSocket.close();
        }
        catch (IOException e)
        {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
