import java.io.*;
import java.net.*;

class ServerToClientMessaging_S
{
	public static void main(String args[])
		{
			ServerSocket serverSocket;
			Socket socket;
			String outMessage;

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


					BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

					BufferedReader xx=new BufferedReader(new InputStreamReader(System.in));

					System.out.println("Say what you wanna say! (Type 'So Long Sucker' (not case sensitive) or just 'goodbye' to disconnect))");

					while(!((outMessage=xx.readLine()).equalsIgnoreCase("so long sucker")) && !(outMessage.equalsIgnoreCase("goodbye")))
					{
						bw.write(outMessage);
						bw.newLine();
						bw.flush();
					}

					bw.write("_|_");
					bw.newLine();
					bw.flush();

					socket.close();
					serverSocket.close();



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

