import java.io.*;
import java.net.*;

class OperationOnServer_C
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

			System.out.println("Enter the opeation to be sent :\n Supported operations -\n OPERATION \t SYMBOL 1)Addition \t + \n 2) Subtraction \t - \n 3)MUltiplication \t * \n 4)Division \t / \n 5)Modulus \t % \n 6)Factorial \t ! \n 7)Reverse String \t ~(unary, thus used like factorial operator \n 8)Character Count \t @ \n 9)Substring \t write 'substring(string1,string2) This tells if string2 is contained in string 1\n");

			String op=xx.readLine();

			bw.write(op);
			bw.newLine();
			bw.flush();

			BufferedReader xxx=new BufferedReader(new InputStreamReader(socket.getInputStream()));

			System.out.println(xxx.readLine());


		}

		catch(IOException e)
		{
			System.out.println("IOException occurred!");
			e.printStackTrace();
		}

	}

}



