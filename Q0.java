package automat_working;

public class Q0 extends AutomatZustand
{	
	@Override
	public void wechsel_null()
	{		
		Automat.stack.push(new Q1());
		// Q2 liegt oben und wird deshalb immer vor Q1 bearbeitet
		// So ist sichergestellt, dass immer der schwerere Weg zuerst durchlaufen wird
		// Soll das nicht mehr der Fall sein müssen die beiden einfach vertauscht werden
		Automat.stack.push(new Q2());		
	}		
}