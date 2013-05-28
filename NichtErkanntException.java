package automat_working;

public class NichtErkanntException extends Exception
{
	public NichtErkanntException()
	{
	}
	
	public NichtErkanntException(String s)
	{
		System.out.println(s);
	}
}