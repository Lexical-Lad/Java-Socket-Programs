import java.io.*;
import java.net.*;

class TestServer
{
	private static Socket socket;

	public static void main(String args[])

	{
		try
		{

			//args[0]="8080";
			ServerSocket serverSocket=new ServerSocket(Integer.parseInt(args[0]));

			System.out.println("Listening to port :"+args[0]);

			while(true)
			{
				socket=serverSocket.accept();

				System.out.println("Connected to Client : "+ socket.getRemoteSocketAddress());

				InputStream is=socket.getInputStream();

				BufferedReader xx=new BufferedReader(new InputStreamReader(is),1);

				System.out.println("Test");


				String message=xx.readLine();

				System.out.println("Test");


				System.out.println("The Client "+socket.getRemoteSocketAddress().toString()+" says : "+ message);



				BufferedReader xxx=new BufferedReader(new InputStreamReader(System.in));

				System.out.println("Enter the message to send :");

				String s=xx.readLine();

				OutputStream os=socket.getOutputStream();

				BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os));

				bw.write(s);

				bw.flush();

			}

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

	}

}


