import java.io.*;
import java.net.*;

class ClientToServerMessaging_S
{
	public static void main(String args[])
	{
		ServerSocket serverSocket;
		Socket socket;
		String inMessage;

		//listenPort=Integer.parseInt(args[0]);
		int listenPort=8080;


		try
		{
			while(true)
			{

				serverSocket=new ServerSocket(listenPort);
				serverSocket.setSoTimeout(10000);

				System.out.println("Listening to port : "+ listenPort);

				socket=serverSocket.accept();

				System.out.println("Connected to client :"+socket.getRemoteSocketAddress());

				BufferedReader xxx=new BufferedReader(new InputStreamReader(socket.getInputStream()));

				while((inMessage=xxx.readLine())!=null)
				System.out.println(inMessage);

				socket.close();
				serverSocket.close();

				System.out.println("The client bailed! \n\n\n");

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


