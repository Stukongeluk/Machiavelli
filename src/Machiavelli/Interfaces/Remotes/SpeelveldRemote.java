package Machiavelli.Interfaces.Remotes;

import Machiavelli.Interfaces.Observers.SpeelveldObserver;
import Machiavelli.Models.Speler;
import Machiavelli.Views.SpeelveldView;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by badmuts on 18-6-15.
 */
public interface SpeelveldRemote extends Remote {

    public void setKoning(Speler spelers) throws RemoteException;
    public void toonKarakterLijst() throws RemoteException;
    public void addObserver(SpeelveldObserver observer) throws RemoteException;
    public void notifyObservers() throws RemoteException;
    public void registratieView(SpeelveldView speelveldview) throws RemoteException;
    public Speler getSpeler() throws RemoteException;
    public void addSpeler(Speler speler) throws RemoteException;

}
