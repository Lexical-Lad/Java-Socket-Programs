import java.io.*;
import java.net.*;

class MultipleClientsToServer_C
{
	public static void main(String args[])
		{

			Socket socket;
			int port;
			String ip;
			String outMessage;


			//port=Integer.parseInt(args[1]);
			//ip=args[0];

			port=8080;
			ip="127.0.0.1";


			try
			{
				socket=new Socket(ip,port);

				System.out.println("Connected to the server : "+socket.getRemoteSocketAddress());

				BufferedReader xx=new BufferedReader(new InputStreamReader(System.in));


				BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

				System.out.println("Say what you wanna say! (Type 'So Long sucker' (not case sensitive) or just 'goodbye' to disconnect))");

				while((!(outMessage=xx.readLine()).equalsIgnoreCase("so long sucker")) && !(outMessage.equalsIgnoreCase("goodbye")))
				{
					bw.write(outMessage);
					bw.newLine();
					bw.flush();

				}
				bw.write(outMessage);
				bw.newLine();
				bw.flush();


				socket.close();

				System.out.println("Disconnected from the server!");
				bw.close();

			}
			catch(SocketException e) //SocketException extends IOEcxeption
			{
				System.out.println("The server is AWOL!");
				System.exit(0);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}


		}

}


