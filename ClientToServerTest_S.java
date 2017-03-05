import java.io.*;
import java.net.*;


class ClientToServerTest_S

{
	public static void main(String args[])
	{
		int listenPort;
		ServerSocket serverSocket;
		Socket socket;
		String inMessage;

		//listenPort=Integer.parseInt(args[0]);
		listenPort=8080;
		try
		{
			serverSocket=new ServerSocket(listenPort);
			System.out.println("Listening to Port : "+listenPort);

			while(true)
			{

				socket=serverSocket.accept();

				System.out.println("Connected Successfully to Client : "+socket.getInetAddress().getHostAddress()); // socket.getInetAddress() returns an InetAddress object that contains the IP address of the remote machine. InetAddress.getHostAddress() returns a String object with the textual representation of that address.
				//System.out.println("Test");

				BufferedReader xxx=new BufferedReader(new InputStreamReader(socket.getInputStream()));


				while((inMessage=xxx.readLine())!=null)
				{
					System.out.println("\n\nThe Client says : "+ inMessage+"\n\n");
				}

				/*another alternative to check if the client has disconnected or not is to use the xxx.read() method. It returns -1 if the inputstream has been closed

				as
				while(xxx.read!=-1)
				{...}

				Another option is to try to write to the remote machine(if such a functioning stream exists. It will throw a 'connection reset' exception
				*/


				System.out.println("The Client has disconnected!");
				socket.close();

			}



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






