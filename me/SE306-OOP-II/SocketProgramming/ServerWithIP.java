package labserver;

import java.io.*;
import java.net.*;
public class ServerWithIP implements Runnable
{
    ServerSocket serverSocket;
    PrintStream streamToClient;
    BufferedReader streamFromClient;
    Socket fromClient;
    static int count = 0;
    Thread thread;
    public ServerWithIP()
    {
    	System.out.println("Welcome to Server");
        try{
            serverSocket = new ServerSocket(1001);
        }
        catch(Exception e)
        {
            System.out.println("Socket could not be created"+e);
        }
        thread = new Thread(this);
        thread.start();
    }
    public void run()
    {
        try{
            while(true)
            {
                fromClient = serverSocket.accept();
                count++;    
                System.out.println("Client connection number "+count);
                streamFromClient = new BufferedReader(new
 
InputStreamReader((fromClient.getInputStream())));
                streamToClient = new PrintStream(fromClient.getOutputStream());
                String str = streamFromClient.readLine();
                System.out.println("Client connection name "+str);
                streamToClient.println("Welcome "+str);
            }
        }
        catch(Exception e){
            System.out.println("Exception "+e);         
        }
        finally{
            try{
                fromClient.close();
            }
            catch(Exception e)
            {
                System.out.println("could not close connection "+e);
            }
        }
         
    }
public static void main(String args[])  
{
    new ServerWithIP();
}
}