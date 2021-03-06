package Machiavelli.Interfaces.Observers;

import Machiavelli.Interfaces.Remotes.StadRemote;
import Machiavelli.Models.Stad;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by badmuts on 10-6-15.
 */
public interface StadObserver extends Remote {

    void modelChanged(StadRemote stad) throws RemoteException;

}
