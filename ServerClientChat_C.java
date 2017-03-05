import java.io.*;
import java.net.*;

class MutableBooleanWrapper
{
	boolean value;

	MutableBooleanWrapper(boolean value)
	{
		 this.value=value;
	 }

	 MutableBooleanWrapper()
	 {
		 value=false;
 	}

 }

class ReaderThread_C extends Thread
{

	String inMessage;
	Socket socket;
	Object lock;
	MutableBooleanWrapper flag;


	ReaderThread_C(Socket socket,Object lock,MutableBooleanWrapper flag)
	{
		this.socket=socket;
		this.lock=lock;
		this.flag=flag;
		start();

	}

	public void mayday()
	{
		synchronized(lock)
		{
			flag.value=false;
		}

	}

	public boolean flagStatus()
	{
		boolean temp;
		synchronized(lock)
		{
			temp=flag.value;
		}

		return temp;
	}


	public void run()
	{

		try
		{

			BufferedReader xxx=new BufferedReader(new InputStreamReader(socket.getInputStream()));


			while(flagStatus())
			{
				inMessage=xxx.readLine();

				if(inMessage==null)
				{
					System.out.println("\nRIP Server!");
					mayday();
				}
				else if(inMessage.equalsIgnoreCase("_|_"))
				{
					System.out.println("\nThe Server booted you!");
					mayday();
				}
				else
				{
					System.out.println("\nServer Says :  "+inMessage);
				}


			}

		}
		catch(SocketException e)
		{
			System.out.println("\nRIP Server!");
			mayday();

		}
		catch(IOException e)
		{
			System.out.println("\nIOException Occurred!!!");
			e.printStackTrace();

		}

	}

}



class WriterThread_C extends Thread
{
	String outMessage;
	Socket socket;
	Object lock;
	MutableBooleanWrapper flag;

	WriterThread_C(Socket socket,Object lock,MutableBooleanWrapper flag)
	{
		this.socket=socket;
		this.lock=lock;
		this.flag=flag;
		start();
	}


	public boolean flagStatus()
	{
		boolean temp;
		synchronized(lock)
		{
			temp=flag.value;
		}

		return temp;
	}

	public void mayday()
	{
		synchronized(lock)
		{
			flag.value=false;
		}

	}



	public void run()
	{

		try
		{
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader xx=new BufferedReader(new InputStreamReader(System.in));

			while(flagStatus())
			{
				outMessage=xx.readLine();
				if(!flagStatus())
				return;
				bw.write(outMessage);
				bw.newLine();
				bw.flush();



				if(outMessage.equalsIgnoreCase("_|_"))
				{
					System.out.println("\n Connection Closed!");
					mayday();
					return;
				}
				System.out.println("\nYou said :"+ outMessage);//only executed if we don't signal an intentional shutdown of the socket("_|_")

			}

		}
		catch(IOException e)
		{
			System.out.println("\nIOException occurred!");
			e.printStackTrace();
		}

	}

}


class ServerClientChat_C
{
	public static void main(String args[])
	{
		Socket socket;
		int port;
		String ip;
		ReaderThread_C reader;
		WriterThread_C writer;
		MutableBooleanWrapper flag;
		Object lock;

		//port=Integer.parseInt(args[1]);
		//ip=args[0];

		port=8080;
		ip="127.0.0.1";

		try
		{

			socket=new Socket(ip,port);
			System.out.println("Connected to Server : "+ socket.getRemoteSocketAddress());

			System.out.println("Type Away! (Enter is send; no takebacks!)(Send '_|_' to close the chat)");

			lock=new Object();
			flag=new MutableBooleanWrapper(true);

			reader=new ReaderThread_C(socket,lock,flag);
			writer=new WriterThread_C(socket,lock,flag);

			reader.join();
			writer.join();
			socket.close();


		}
		catch(InterruptedException e)
		{
			System.out.println("Interrupted!");
			e.printStackTrace();
		}

		catch(IOException e)
		{
			System.out.println("IOException occurred!");
			e.printStackTrace();

		}

	}

}



