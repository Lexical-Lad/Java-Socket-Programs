import java.io.*;
import java.net.*;

class UDP_C
{
	public static void main(String args[])
	{

		BufferedReader xx=new BufferedReader(new InputStreamReader(System.in));

		DatagramSocket socket;
		String outMessage,inMessage;

		String ip="127.0.0.1";
		int port=7777;

		try
		{
			System.out.println("Enter a message to send :");
			outMessage=xx.readLine();

			socket=new DatagramSocket();

			DatagramPacket packet=new DatagramPacket(outMessage.getBytes(),outMessage.length(),InetAddress.getByName(ip),port);

			socket.send(packet);


			packet=new DatagramPacket(new byte[100],100);

			socket.receive(packet);

			inMessage=new String(packet.getData());

			System.out.println("The server says : "+ inMessage);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}


