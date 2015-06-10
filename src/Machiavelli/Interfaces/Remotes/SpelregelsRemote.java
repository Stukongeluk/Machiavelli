package Machiavelli.Interfaces.Remotes;

import Machiavelli.Interfaces.Observers.SpelregelsObserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Created by badmuts on 10-6-15.
 */
public interface SpelregelsRemote extends Remote {

    public String getSpelregels() throws IOException;
    public void addObserver(SpelregelsObserver spelregelsObserver) throws RemoteException;
    public void notifyObservers() throws RemoteException;

}