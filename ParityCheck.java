import java.io.*;
import java.util.Random;

class ParityCheck
{
	public static void main(String args[])throws IOException
	{

		BufferedReader xx=new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter the 8-bit binary string :");

		String s1=xx.readLine();
		int count=0;
		String s2;

		for(int i=0;i<s1.length();i++)
		if(s1.charAt(i)=='1')count++;

		if(count%2==0)s2=s1+"0";
		else
		s2=s1+"1";


		Random rand=new Random();

		int r1=rand.nextInt(9);


		for(int i=0;i<r1;i++)
		{
			int r2=rand.nextInt(8);

			if(s2.charAt(r2)=='1')
			s2=s2.substring(0,r2)+"0"+s2.substring(r2+1);
			else
			s2=s2.substring(0,r2)+"1"+s2.substring(r2+1);
		}
		count=0;

		for(int i=0;i<9;i++)
		if(s2.charAt(i)=='1')count++;

		System.out.println("The sent string is : "+s1);
		System.out.println("The received string is :"+s2);

		if(count%2==0)
		System.out.println("No errors!");
		else
		System.out.println("Error(s) Detected!");

	}

}
