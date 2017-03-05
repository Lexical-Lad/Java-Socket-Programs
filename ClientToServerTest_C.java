import java.io.*;
import java.net.*;

class ClientToServerTest_C
{
	public static void main(String args[])
	{

		Socket socket;
		String temp;
		int port;
		String ip;


		//port=Integer.parseInt(args[1]);
		//ip=args[0];

		port=8080;
		ip="127.0.0.1";



		try
		{
			socket=new Socket(ip,port);
			System.out.println("Connected to the Server : "+socket.getRemoteSocketAddress());

			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			bw.write("Sup Nigga!");
			bw.newLine();
			bw.flush();


			socket.close();


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




