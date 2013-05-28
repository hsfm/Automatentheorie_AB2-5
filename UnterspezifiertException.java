package automat_working;

public class UnterspezifiertException extends Exception
{
	public UnterspezifiertException(String s)
	{
		System.out.println("Übergang mit " + s + " ist für diesen Zustand nicht definiert!");
	}
}