import java.io.*;
import java.net.*;

class TestClient
{
	private static Socket socket;

	public static void main(String args[])

	{
		try
		{
			//args[0]="localhost";
			//args[1]="8080";

			socket=new Socket(args[0],Integer.parseInt(args[1]));

			System.out.println("Connected to Server  "+ socket.getRemoteSocketAddress());

			while(true)
			{


				BufferedReader xxx=new BufferedReader(new InputStreamReader(System.in));

				BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

				System.out.println("Enter the message to send:");

				bw.write("Whatever");
				bw.flush();


				InputStream is=socket.getInputStream();

				BufferedReader xx=new BufferedReader(new InputStreamReader(is));

				String message=xx.readLine();

				System.out.println("The Server "+socket.getRemoteSocketAddress().toString()+" says : "+ message);

			}




		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

	}

}


