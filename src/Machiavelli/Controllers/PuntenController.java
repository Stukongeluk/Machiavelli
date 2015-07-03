package Machiavelli.Controllers;

import java.rmi.RemoteException;
import java.util.ArrayList;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Models.PuntenModel;
import Machiavelli.Views.PuntenView;

public class PuntenController {
    private PuntenView puntenView;
    private PuntenModel puntenModel;
    private SpelerRemote winnaar;
    private SpelRemote spel;

    public PuntenController(SpelRemote spel, PuntenModel puntenModel) throws RemoteException {

        this.puntenView = new PuntenView(this);
        this.puntenModel = puntenModel;
        this.spel = spel;
    }

    public String cmdBerekenScorelijst()
    {
        try {
            ArrayList<SpelerRemote> tempList = puntenModel.berekenScorelijst();
            this.winnaar = tempList.get(0);
            for(int i = 0; i < this.spel.getSpelers().size(); i++)
            {
                if (this.spel.getSpelers().get(i).equals(this.winnaar))
                {
                    cmdGetWinnaar();
                } else
                {
                    cmdgetVerliezer();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String cmdGetWinnaar()
    {
        try {
            return "Je hebt gewonnen met " + this.winnaar.getStad().getWaardeStad();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String cmdgetVerliezer()
    {
        try {
            return "Je hebt verloren! De winnaar heeft " + this.winnaar.getStad().getWaardeStad();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
