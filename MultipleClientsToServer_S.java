
import java.io.*;
import java.net.*;

class Clients extends Thread
{
	Socket socket;
	String clientAddress;

	Clients(Socket socket)
	{
		this.socket=socket;
		clientAddress=socket.getRemoteSocketAddress().toString();
		start();
	}

	public void run()
	{
		String inMessage;

		try
		{

			System.out.println("Connected to client :"+clientAddress);

			BufferedReader xxx=new BufferedReader(new InputStreamReader(socket.getInputStream()));

			while((inMessage=xxx.readLine())!=null)
			System.out.println("The Client "+clientAddress+" says : "+inMessage);
			socket.close();


			System.out.println("Client  :"+clientAddress +" disconnected!!!");


		}
		catch(SocketException e)
		{
			System.out.println("Client  :"+clientAddress +" disconnected!!!");
		}

		catch(IOException e)
		{
			System.out.println("IOException occurred!");
			e.printStackTrace();
		}



	}

}



class MultipleClientsToServer_S
{
	public static void main(String args[])
	{
		ServerSocket serverSocket;
		Socket socket;
		//listenPort=Integer.parseInt(args[0]);
		int listenPort=8080;


		try
		{
			serverSocket=new ServerSocket(listenPort);
			System.out.println("Listening to port : "+listenPort);
			serverSocket.setSoTimeout(100000);
			while(true)
			{

				socket=serverSocket.accept();

				new Clients(socket);

			}


		}
		catch(SocketTimeoutException e)
		{
			System.out.println("The Server ain't waiting forever, nigga!");
			System.exit(0);

		}
		catch(IOException e)
		{
			System.out.println("IOException occurred!");
			e.printStackTrace();

		}

	}

}
