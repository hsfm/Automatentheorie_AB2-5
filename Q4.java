package automat_working;
public class Q4 extends AutomatZustand
{
	@Override
	public void wechsel_eins()
	{
		Automat.stack.push(new Q3());
	}
}