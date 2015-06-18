package Machiavelli.Models.Karakters;

import Machiavelli.Enumerations.Type;
import Machiavelli.Interfaces.Bonusable;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.KarakterObserver;
import Machiavelli.Models.GebouwKaart;
import Machiavelli.Models.Speler;
import javafx.scene.image.Image;

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
public class Prediker implements Karakter, Bonusable {
	
	private Speler speler = null;

	/** Eigenschappen van karakter Prediker. */
    private final int nummer = 5;	
    private final int bouwLimiet = 1; 
    private final String naam = "Prediker";
    private final Type type = Type.KERKELIJK;
    private Object target;
    
    private Image image = new Image("Machiavelli/Resources/Karakterkaarten/Portrait-Prediker.png");
    private ArrayList<KarakterObserver> observers = new ArrayList<>();

    @Override
    public void setSpeler(Speler speler) throws RemoteException {
        this.speler = speler;
    }

    @Override
    public Speler getSpeler() throws RemoteException {
        return speler;
    }

    @Override
    public void gebruikEigenschap() throws RemoteException {
        // TODO: beschermt tegen karakter Condotierre
    }
    
    /** ontvangen bonusgoud voor Kerk gebouwen */
    public void ontvangenBonusGoud() throws RemoteException {
        ArrayList<GebouwKaart> gebouwen = speler.getStad().getGebouwen();
        for(GebouwKaart gebouw: gebouwen) {
            if (gebouw.getType() == this.type)
                speler.getPortemonnee().ontvangenGoud(1);
        }
    }

    public String getNaam() throws RemoteException {
    	return this.naam;
    }
   
    public int getNummer() throws RemoteException {
    	return this.nummer;
    }

    @Override
    public int getBouwLimiet() throws RemoteException {
        return this.bouwLimiet;
    }

    public Type getType() throws RemoteException {
		return this.type;
	}

    @Override
    public void setTarget(Object target) throws RemoteException {
        this.target = target;
    }

    @Override
    public Image getImage() throws RemoteException {
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

    @Override
    public void beurtOverslaan() throws RemoteException {

    }
}
