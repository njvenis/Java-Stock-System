import java.io.*;
import java.net.*;

public class Client
{
    Socket clientSocket = null;
    PrintWriter out = null;
    BufferedReader stdIn = null;
    String outString;

    ClientRead myRead;
    //ClientWrite myWrite;


    public Client()
    {
        try
        {
            clientSocket = new Socket("127.0.0.1", 5000);
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            stdIn = new BufferedReader(new InputStreamReader(System.in));

        }
        catch(IOException e)
        {
            System.out.println("Some Error: " + e);
        }

        myRead = new ClientRead(clientSocket);
    }HELO

    public void writeToServer()
    {
        String aString;
        try
        {
            aString = stdIn.readLine();
            out.println(aString);
        }
        catch(IOException e)
        {
            System.out.println("Something went wrong: " + e);
        }
    }

    public void StartThreads()
    {
        Thread t1 = new Thread(myRead);
        t1.start();
    }

    public static void main(String [] args)
    {
        Client Test = new Client();
        Test.StartThreads();
        while(true)
        {
            System.out.print("CMD: ");
            Test.writeToServer();
        }
    }
}