import java.io.*;
import java.net.*;
import java.math.BigInteger;

class OperationOnServer_S
{

	static String bigIntegerFactorial(String n)
	{
		BigInteger N=new BigInteger(n);

		for(int i=Integer.parseInt(n)-1;i>=2;i--)
		N=N.multiply(BigInteger.valueOf(i));


		return N.toString();
	}

	public static void main(String args[])
		{
			ServerSocket serverSocket;
			Socket socket;
			String outMessage="";

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

					BufferedReader xxx=new BufferedReader(new InputStreamReader(socket.getInputStream()));
					BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


					String op=xxx.readLine();
					int opNum=0;



					if(op.indexOf("+")!=-1)
					opNum=1;
					else if(op.indexOf("-")!=-1)
							opNum=2;
					else if(op.indexOf("*")!=-1)
							opNum=3;
					else if(op.indexOf("/")!=-1)
							opNum=4;
					else if(op.indexOf("%")!=-1)
							opNum=5;
					else if(op.indexOf("!")!=-1)
							opNum=6;
					else if(op.indexOf("~")!=-1)
							opNum=7;
					else if(op.indexOf("@")!=-1)
							opNum=8;
					else if(op.indexOf("substring")!=-1)
							opNum=9;




					String result;

					if(opNum!=0)
					{
							switch(opNum)
						{
							case 1:
									outMessage=String.valueOf(Double.parseDouble(op.substring(0,op.indexOf("+")).trim())+Double.parseDouble(op.substring(op.indexOf("+")+1).trim()));
									break;

							case 2:
									outMessage=String.valueOf(Double.parseDouble(op.substring(0,op.indexOf("-")).trim())-Double.parseDouble(op.substring(op.indexOf("-")+1).trim()));
									break;

							case 3:
									outMessage=String.valueOf(Double.parseDouble(op.substring(0,op.indexOf("*")).trim())*Double.parseDouble(op.substring(op.indexOf("*")+1).trim()));
									break;

							case 4:
									if(Double.parseDouble(op.substring(op.indexOf("/")+1).trim()) == 0.0)
									outMessage="Dividing by zero!!!";
									else
									outMessage=String.valueOf(Double.parseDouble(op.substring(0,op.indexOf("/")).trim())/Double.parseDouble(op.substring(op.indexOf("/")+1).trim()));
									break;
							case 5:
									if(op.indexOf(".")!=-1)
									outMessage="Modulus ain't defined for non-integral values!";
									else
									outMessage=String.valueOf(Integer.parseInt(op.substring(0,op.indexOf("%")).trim())%Integer.parseInt(op.substring(op.indexOf("%")+1).trim()));
									break;
							case 6:
									if(op.indexOf(".")!=-1)
									outMessage="Factorial ain't defined for non-integral values!";
									else
									outMessage=bigIntegerFactorial(op.substring(0,op.indexOf("!")));
									break;
							case 7:
									if(op.trim().indexOf("~")==0)
									outMessage="Syntax error!";
									else
									{

										outMessage="";

										for(int i=0;i<op.length()-1;i++)
										outMessage+=op.charAt(i);

									}

									break;

							case 8:
									if(op.trim().indexOf("~")==0)
										outMessage="Syntax error!";
									else
										outMessage=String.valueOf(op.trim().length()-1);

									break;

							case 9:
									if(op.substring(op.indexOf("(")+1,op.indexOf(",")).toLowerCase().indexOf(op.substring(op.indexOf(",")+1,op.indexOf(")")).toLowerCase())!=-1)
									outMessage="YES";
									else
									outMessage="NO";

									break;

						}

					}

					else
						outMessage="Follow the rules, sucka!!! The operation requested was more invalid than you!!!";



					bw.write(outMessage);
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
