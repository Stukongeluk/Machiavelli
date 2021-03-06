package Machiavelli.Interfaces.Remotes;


import Machiavelli.Factories.KarakterFactory;
import Machiavelli.Interfaces.Observers.SpelObserver;



import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Machiavelli.Factories.KarakterFactory;
import Machiavelli.Interfaces.Observers.SpelObserver;
import Machiavelli.Models.PuntenModel;

/**
 * Created by badmuts on 10-6-15.
 */
public interface SpelRemote extends Remote {

    int getAantalSpelers() throws RemoteException;
    void addObserver(SpelObserver spelObserver) throws RemoteException;
    void removeObserver(SpelObserver observer) throws RemoteException;
    void notifyObservers() throws RemoteException;
    void addSpeler(SpelerRemote speler) throws RemoteException;
    void createNewSpel(int maxAantalSpelers) throws RemoteException;
    ArrayList<SpelerRemote> getSpelers() throws RemoteException;
    int getMaxAantalSpelers() throws RemoteException;
    BankRemote getBank() throws RemoteException;
    BeurtRemote getBeurt() throws RemoteException;
    GebouwFactoryRemote getGebouwFactory() throws RemoteException;
    void createNewSpeler() throws RemoteException;
    KarakterFactoryRemote getKarakterFactory() throws RemoteException;

    void setMaxAantalSpelers(int maxAantalSpelers) throws RemoteException;

    void setBank(BankRemote bank) throws RemoteException;

    void setGebouwFactory(GebouwFactoryRemote gebouwFactory) throws RemoteException;

    void setSpelers(ArrayList<SpelerRemote> spelers) throws RemoteException;

    void laadSpel(SpelRemote loadSpel) throws RemoteException;

    ArrayList<SpelerRemote> getTempSpelers() throws RemoteException;

    void setTempSpelers(ArrayList<SpelerRemote> list) throws RemoteException;


    ArrayList<SpelObserver> getObservers() throws RemoteException;

    public void setKarakterFactory(KarakterFactory karakterFactory) throws RemoteException;

    public PuntenRemote getPuntenModel() throws RemoteException;

}
