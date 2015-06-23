package Machiavelli.Models;

import Machiavelli.Interfaces.Observers.StadObserver;
import Machiavelli.Interfaces.Remotes.GebouwKaartRemote;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Interfaces.Remotes.StadRemote;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by daanrosbergen on 03/06/15.
 */
public class Stad extends UnicastRemoteObject implements StadRemote, Serializable {
	private SpelerRemote speler;
	private ArrayList<GebouwKaartRemote> gebouwen = new ArrayList<>();
	private SpelRemote spel;
	private int waardeStad;
	private ArrayList<StadObserver> observers = new ArrayList<>();

	public Stad(SpelerRemote speler) throws RemoteException
	{
		this.speler = speler;
	}

	public ArrayList<GebouwKaartRemote> getGebouwen() throws RemoteException
	{
		return this.gebouwen;
	}

	public void addGebouw(GebouwKaartRemote gebouw) throws RemoteException
	{
		// Limiet moet nog gecheckt worden?
		this.gebouwen.add(gebouw);
        notifyObservers();
	}

	public void removeGebouw(GebouwKaartRemote gebouw) throws RemoteException
	{
		this.gebouwen.remove(gebouw);
		// Plaats gebouwkaart terug op gebouwenstapel (GebouwFactory)
		this.spel.getGebouwFactory().addGebouw(gebouw);
		// Update StadView
		// this.stadView.modelChanged();
        notifyObservers();
	}
	
	public int getWaardeStad() throws RemoteException
	{
		berekenWaarde(gebouwen);
		return this.waardeStad;
	}
	
	private void berekenWaarde(ArrayList<GebouwKaartRemote> lijst) throws RemoteException
	{
		int waarde = 0;
		for(int i = 0; i < lijst.size(); i++)
		{
			waarde += lijst.get(i).getKosten();
		}
		this.waardeStad = waarde;
		notifyObservers();
	}

	public void addObserver(StadObserver observer) throws RemoteException {
		observers.add(observer);
        System.out.println("Stad observer added! Observers: " + observers.size());
	}

	public void notifyObservers() throws RemoteException {
        System.out.println("Stad model changed! Observers: " + observers.size());
		for (StadObserver observer: observers) {
			observer.modelChanged(this);
		}
	}

	public SpelerRemote getSpeler() throws RemoteException {
		// TODO Auto-generated method stub
		return speler;
	}

	

}