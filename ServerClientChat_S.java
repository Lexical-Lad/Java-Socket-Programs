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

class ReaderThread_S extends Thread
{

	String inMessage;
	Socket socket;
	Object lock;
	MutableBooleanWrapper flag;


	ReaderThread_S(Socket socket,Object lock,MutableBooleanWrapper flag)
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

			BufferedReader xxx=new BufferedReader(new InputStreamReader(socket.getInputStream()));

			while(flagStatus())
			{
				inMessage=xxx.readLine();

				if(inMessage==null)
				{
					System.out.println("\nThe Client is Sleeping with the fishes!");
					mayday();
				}

				else if(inMessage.equalsIgnoreCase("_|_"))
				{
					System.out.println("\nThe Client took to it's heels!");
					mayday();
				}
				else
				{
					System.out.println("\n Client Says :   "+inMessage);
				}

			}

		}
		catch(SocketException e)
		{
			System.out.println("\nThe Client is Sleeping with the fishes!");
			mayday();
		}

		catch(IOException e)
		{
			System.out.println("\nIOException occurred!");
			e.printStackTrace();
		}

	}
}



class WriterThread_S extends Thread
{
	String outMessage;
	Socket socket;
	Object lock;
	MutableBooleanWrapper flag;

	WriterThread_S(Socket socket,Object lock,MutableBooleanWrapper flag)
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

			BufferedReader xx=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

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
					System.out.println("\nConnection Closed!");
					mayday();
					return;
				}

				System.out.println("\n You Said : "+outMessage);

			}

		}
		catch(IOException e)
		{
			System.out.println("\nIOException occurred!");
			e.printStackTrace();
		}

	}

}




class ServerClientChat_S
{
	public static void main(String args[])
	{
		ServerSocket serverSocket;
		Socket socket;
		String inMessage;
		Object lock;


		//listenPort=Integer.parseInt(args[0]);
		int listenPort=8080;

		try
		{
			while(true)
			{

				MutableBooleanWrapper flag=new MutableBooleanWrapper(true);
				lock=new Object();

				serverSocket=new ServerSocket(listenPort);
				System.out.println("Listening to port :"+listenPort);
				serverSocket.setSoTimeout(20000);

				socket=serverSocket.accept();

				System.out.println("Connected to Client : " +socket.getRemoteSocketAddress());
				System.out.println("Type Away! (Enter is send; no takebacks!)(Send '_|_' to close the chat)");

				ReaderThread_S reader=new ReaderThread_S(socket,lock,flag);
				WriterThread_S writer=new WriterThread_S(socket,lock,flag);

				reader.join();
				writer.join();

				socket.close();
				serverSocket.close();
			}

		}
		catch(InterruptedException e)
		{
			System.out.println("Interrupted!");
			e.printStackTrace();
		}
		catch(SocketTimeoutException e)
		{
			System.out.println("The Server ain't gonna wait forever!");
			System.exit(0);
		}

		catch(IOException e)
		{
			System.out.println("IOException occurred!");
			e.printStackTrace();
		}
	}
}
