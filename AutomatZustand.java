package automat_working;

/* Superklasse für Zustände. Alle neuen Zustände müssen diese Klasse ableiten */
public abstract class AutomatZustand
{	
	// Jedes Zustandobjekt beinhaltet das Eingabewort zum Zeitpunkt seiner Erstellung
	protected String ew_lokal;
	
	// Bei erstellung speichere das aktuelle Eingabewort
	public AutomatZustand()
	{
		this.ew_lokal = Automat.eingabewort;
	}
	
	// Funktionen für die Zustandsübergänge werfen standardmäßig eine Exception,
	// es sei denn sie werden von einer Subklasse überschrieben
	// Kommen neue Übergänge hinzu müssen diese hier definiert werden (neue wechsel_X Funktion)
	public void wechsel_null() throws UnterspezifiertException
	{
		throw new UnterspezifiertException("0");
	}
	
	public void wechsel_eins() throws UnterspezifiertException
	{
		throw new UnterspezifiertException("1");
	}
	
	public String get_ew() 
	{ 
		return this.ew_lokal;
	}
	
	@Override
	public String toString()
	{
		return this.getClass().getName();
	}
	
	// Ist ein Zustand Endzustand muss er diese Funktion überschreiben und true zurückgeben
	public boolean get_endzustand()
	{
		return false;
	}
}