import java.io.*;
import java.net.*;

class UDP_S
{
	public static void main(String args[])
	{

		BufferedReader xx=new BufferedReader(new InputStreamReader(System.in));

		DatagramSocket socket;
		String outMessage,inMessage;
		int port=7777;

		try

		{
			socket=new DatagramSocket(port);
			System.out.println("Listening to port : "+port);

			while(true)
			{




				DatagramPacket packet=new DatagramPacket(new byte[100],100);

				socket.receive(packet);

				inMessage=new String(packet.getData());

				InetAddress clientIP=packet.getAddress();

				System.out.println("The Client "+packet.getSocketAddress()+"says :"+ inMessage);


				System.out.println("Enter a message to send :");
				outMessage=xx.readLine();

				socket.send(new DatagramPacket(outMessage.getBytes(),outMessage.length(),clientIP,packet.getPort()));

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}


