package Machiavelli.Models.Karakters;

import Machiavelli.Enumerations.Type;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.KarakterObserver;
import Machiavelli.Interfaces.Remotes.SpelerRemote;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/** 
 * Created by daanrosbergen on 03/06/15.
 * Edit by Bernd Oostrum
 * 
 * De speler heeft het karakter Dief gekozen. 
 * De eigenschappen van dit karakter worden gebruikt
 * door de speler tijdens de duur van een ronde.
 * 
 * De dief kan als eigenschap een ander karakter bestelen
 * van al zijn goudstukken. De karakters moordenaar en het
 * het slachtoffer van de moordenaar kunnen niet gekozen worden
 * de dief ontvangt pas de goudstukken als het bestolen karakter
 * aan de beurt is
 */

public class Dief extends UnicastRemoteObject implements Karakter, Serializable {
	
	public  Dief() throws RemoteException {
//        super(1099);
	}

	private SpelerRemote speler = null;

	private Karakter target = null;
    
	/** Eigenschappen van karakter Dief. */
    private final int nummer = 2;	
    private final int bouwLimiet = 1; 
    private final String naam = "Dief";
    private final Type type = Type.NORMAAL;
    
    /*Afbeelding van de Dief*/
    private final String image = "Machiavelli/Resources/Karakterkaarten/Portrait-Dief.png";
    private ArrayList<KarakterObserver> observers = new ArrayList<>();

    /**
   	 * Overriden van de methode uit de interface Karakter,
   	 * de Dief wordt aan de speler gekoppeld.
   	 */
   	@Override
   	public void setSpeler(SpelerRemote speler) throws RemoteException {
           this.speler = speler;
       }

    @Override
    public SpelerRemote getSpeler() throws RemoteException {
        return speler;
    }

    @Override
    public void setTarget(Object target) throws RemoteException {
        this.target = (Karakter) target;
        gebruikEigenschap();
    }
    
    /**
	 * overriden van de methode uit de interface Karakter
	 * en aanroepen van de methode selectKarakterView
	 * Er wordt gewacht op de keuze van de speler. 
	 * Vervolgens wordt het het gekozen karakter bestolen van
	 * al zijn goudstukken op het moment dat deze aan de beurt is. 
	 */
    @Override
    public boolean gebruikEigenschap() throws RemoteException {
    	if (target != null && target.getNaam() != "Moordenaar") {
    		BesteelKarakter(this.speler, getTarget());
    		this.speler.setEigenschapGebruikt(true);
    		System.out.println("De dief heeft de" + getTarget().getNaam() + " bestolen!");
    		target = null; 
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    @Override
    public String getNaam() throws RemoteException {
    	return this.naam;
    }
    
    @Override
    public int getNummer() throws RemoteException {
    	return this.nummer;
    }

    @Override
    public int getBouwLimiet() throws RemoteException {
        return this.bouwLimiet;
    }
    
    @Override
	public Type getType() throws RemoteException {
		return this.type;
	}
	public Karakter getTarget() throws RemoteException {
		return this.target;
	}

    @Override
    public String getImage() throws RemoteException {
        return this.image;
    }

    @Override
    public void addObserver(KarakterObserver observer) throws RemoteException {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() throws RemoteException {
        for (KarakterObserver observer: observers) {
            observer.modelChanged(this);
        }
    }
    
    private void BesteelKarakter(SpelerRemote speler, Karakter target) {
		try {
			System.out.println("naam speler: " + speler.getKarakter().getNaam() + " | Goudstukken: " + speler.getPortemonnee().getGoudMunten());
			System.out.println("naam target: " + target.getNaam() + " | Goudstukken: " + target.getSpeler().getPortemonnee().getGoudMunten());
			speler.getGoudVanBank(speler.getSpel().getBank(), target.getSpeler().getPortemonnee().getGoudMunten());
			target.getSpeler().setGoudOpBank(target.getSpeler().getPortemonnee(), target.getSpeler().getPortemonnee().getGoudMunten());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}


}
