package Machiavelli.Models.Karakters;

import Machiavelli.Enumerations.Type;
import Machiavelli.Interfaces.Bonusable;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.KarakterObserver;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Models.GebouwKaart;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by daanrosbergen on 03/06/15.
 * Edited by Sander de Jong on 08/06/15.
 * 
 * De speler heeft het karakter Prediker gekozen. 
 * De eigenschappen van dit karakter worden gebruikt
 * door de speler tijdens de duur van een ronde.
 * 
 * Uit de stad van de prediker kunnen geen gebouwen worden
 * verwijderd.
 */
public class Prediker implements Karakter, Bonusable, Serializable {
	
	private SpelerRemote speler = null;

	/** Eigenschappen van karakter Prediker. */
    private final int nummer = 5;	
    private final int bouwLimiet = 1; 
    private final String naam = "Prediker";
    private final Type type = Type.KERKELIJK;
    @SuppressWarnings("unused")
	private Object target;
    
    private final String image = "Machiavelli/Resources/Karakterkaarten/Portrait-Prediker.png";
    private ArrayList<KarakterObserver> observers = new ArrayList<>();

    @Override
    public void setSpeler(SpelerRemote speler) throws RemoteException {
        this.speler = speler;
    }

    @Override
    public SpelerRemote getSpeler() throws RemoteException {
        return speler;
    }

    @Override
    public void gebruikEigenschap() throws RemoteException {
        // TODO: beschermt tegen karakter Condotierre
    }
    
    /** ontvangen bonusgoud voor Kerk gebouwen */
    @Override
    public void ontvangenBonusGoud() throws RemoteException {
        ArrayList<GebouwKaart> gebouwen = speler.getStad().getGebouwen();
        for(GebouwKaart gebouw: gebouwen) {
            if (gebouw.getType() == this.type)
                speler.getPortemonnee().ontvangenGoud(1);
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

    @Override
    public void setTarget(Object target) throws RemoteException {
        this.target = target;
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
}
