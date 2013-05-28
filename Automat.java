/* 
	Implementierung des Automaten von AT/FS_AB2-5
	unter Verwendung von rekursivem Backtracking
	und des State Patterns
	
	Folgende Dateien gehören zum Projekt:
		Automat.java
		AutomatZustand.java
		Q0.java
		Q1.java
		Q2.java
		Q3.java
		Q4.java
		WortendeException.java		
		ErkanntException.java
		NichtErkanntException.java
		UnterspezifiertException.java		
*/

package automat_working;

import java.util.*;

public class Automat
{
	// Aufrufstack, speichert Zustände nachdem sie aufgerufen wurden
	public static Stack<AutomatZustand> stack;
	// Temporäre Variable damit der aktuelle Zustand gelöscht werden kann
	private AutomatZustand zustand;
	public static String eingabewort;
		
	public Automat(String ew)
	{
		stack = new Stack<AutomatZustand>();
		// Startzustand ist Q0
		stack.push(new Q0());
		suche(ew);
	}							
	
	/* Funktion zum prüfen auf potentielles Ende */
	private void fertig() throws NichtErkanntException
	{
		try
		{				
			// Ist Eingabewort zu Ende?
			if(Automat.eingabewort.length() <= 0)
			{
				throw new WortendeException("Wort zu ende!");
			}			
		}
		catch(EmptyStackException ex)
		{
			throw new NichtErkanntException("Wort wurde nicht erkannt!");
		}		
		catch(WortendeException e)
		{
			try
			{
				// Ist Zustand Endzustand?
				if(stack.peek().get_endzustand())
				{
					throw new ErkanntException("Wort wurde erkannt!");
				}		
				// Eingabewort zu ende und kein Endzustand, versuche Neustart
				stack.pop();
				suche(stack.peek().get_ew().substring(1));
			}			
			catch(EmptyStackException ex)
			{
				// Falls Stack leer dann Wort nicht erkannt
				throw new NichtErkanntException("Wort wurde nicht erkannt!");
			}
			catch(ErkanntException ex)
			{
				System.exit(0);
			}
		}	
	}		

	/* Rekursive Funktion zum suchen nach einer Lösung */
	public void suche(String ew)
	{		
		// Optionale Ausgabe um zu überprüfen was der Automat macht
		System.out.println(stack.peek() + " [" + ew + "]");
		// Nötig da wir mit public static Variablen arbeiten
		// Könnte auch durch Übergabe von Referenzen realisiert werden
		Automat.eingabewort = ew;
		try
		{
			fertig();
		}		
		catch(NichtErkanntException e)
		{
			System.exit(0);
		}
		// Ermittle nächsten Zustand (Im Prinzip die Delta Funktion)
		try
		{
			// Entferne aktuelles Element da es im nächsten Schritt abgearbeitet wird
			zustand = stack.pop();
			// Hätten wir mehr/andere Übergänge müssten diese hier angepasst werden
			if(ew.charAt(0) == '0')
			{						
				zustand.wechsel_null();										
			}			
			if(ew.charAt(0) == '1')
			{
				zustand.wechsel_eins();				
			}	
			// Optionale Ausgabe um zu überprüfen was der Automat macht
			System.out.println("--> " + stack.peek() + "\n");
			// Um Rekursion zu verstehen muss man zuerst Rekursion verstehen
			suche(ew.substring(1));	
		}
		catch(UnterspezifiertException e)
		{
			try
			{
				// Nichtdefinierter Übergang, versuche Neustart
				suche(stack.peek().get_ew().substring(1));
			}			
			catch(EmptyStackException ex)
			{
				// Stack leer? Wort nicht erkannt
				try
				{
					throw new NichtErkanntException("Wort wurde nicht erkannt!");
				}				
				catch(NichtErkanntException exc)
				{
					System.exit(0);
				}
			}			
		}		
	}
		
	public static void main(String[] args)
	{
		// Sicherstellen das ein Eingabewort übergeben wurde
		if(args.length > 0)
		{
			new Automat(args[0]);		
		}
		else
		{
			System.out.println("Kein Eingabewort als Parameter übergeben! Fahre fort mit vorgegebenem Beispiel...");			
			new Automat("00001");
		}		
	}
}