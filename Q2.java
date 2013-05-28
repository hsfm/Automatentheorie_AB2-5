package automat_working;

public class Q2 extends AutomatZustand
{	
	@Override
	public void wechsel_null()
	{
		Automat.stack.push(new Q0());	
	}
	
	@Override
	public void wechsel_eins()
	{
		Automat.stack.push(new Q4());
	}
}