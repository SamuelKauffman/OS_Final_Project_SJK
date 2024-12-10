
import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * The Client class represents a client in the client-server architecture.
 * It initializes a connection to the server, handles user input,
 * and sends messages to the server using the Communication class.
 */
public class Client {
    /**
     * Entry point for the client application.
     * Starts a thread for communication and continuously reads user input to send
     * to the server.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        Communication com = new Communication();
        Thread t = new Thread(com);
        t.start();

        Scanner scan = new Scanner(System.in);
        String input;

        while (true) {
            input = scan.nextLine();
            com.sendToServer(input);
        }
    }
}

/**
 * The Communication class manages the connection between the client and the
 * server.
 * It handles sending messages to the server and receiving messages from the
 * server.
 */
class Communication implements Runnable {

    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    /**
     * Constructs a Communication object and initializes the connection to the
     * server.
     * Connects to the server at "localhost" on port 7777 and sets up input/output
     * streams.
     */
    public Communication() {
        try {
            socket = new Socket("localhost", 7777);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendToServer(String s) {
        try {
            out.writeUTF(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Continuously listens for messages from the server and prints them to the
     * console.
     * Implements the Runnable interface to run in a separate thread.
     */
    @Override
    public void run() {
        while (true) {
            try {
                if (in.available() == 0) {
                    continue;
                }
                String message = in.readUTF();
                System.out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
