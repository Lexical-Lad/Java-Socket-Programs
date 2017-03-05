import java.io.*;
import java.net.*;

class ServerToClientMessaging_C
{
	public static void main(String args[])
	{

		int port;
		String ip;
		Socket socket;
		String inMessage;


		//port=Integer.parseInt(args[1]);
		//ip=args[0];

		port=8080;
		ip="127.0.0.1";



		try
		{

			socket=new Socket(ip,port);
			System.out.println("Connected to Server : "+socket.getRemoteSocketAddress());

			BufferedReader xxx=new BufferedReader(new InputStreamReader(socket.getInputStream()));

			inMessage=xxx.readLine();

			if(inMessage==null)
			throw new SocketException();

			while(!(inMessage.equalsIgnoreCase("_|_")))
			{
				System.out.println(inMessage);
				inMessage=xxx.readLine();

				if(inMessage==null)
				throw new SocketException();

			}
			System.out.println("The Server booted you!");

			socket.close();

		}
		catch(SocketException e)
		{
			System.out.println("RIP server!");
			System.exit(0);
		}

		catch(IOException e)
		{
			System.out.println("IOException occurred!");
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();

		}

	}
}



