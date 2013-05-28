package automat_working;

public class Q1 extends AutomatZustand
{				
	@Override
	public void wechsel_null()
	{
		Automat.stack.push(new Q1());
	}
	
	@Override
	public void wechsel_eins()
	{
		Automat.stack.push(new Q3());	
	}	
}