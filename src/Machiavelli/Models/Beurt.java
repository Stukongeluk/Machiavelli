package Machiavelli.Models;

import java.util.ArrayList;

/**
 *
 * @author Sander de Jong
 *
 */
public class Beurt
{
    // Variables
    private Spel spel;
    private ArrayList<Speler> spelerLijst;
    private Speler speler;

    public Beurt(Spel spel, ArrayList<Speler> spelerLijst)
    {
        this.spel = spel;
        this.spelerLijst = spelerLijst;
    }

    // Best method naam
    public void geefBeurt(Speler speler)
    {
        // TODO: een speler object een beurt geven.
    }
    
    public Speler getSpeler()
    {
    	return this.speler;
    }
    
    public void setSpeler(Speler speler) {
    	this.speler = speler;
    }
}