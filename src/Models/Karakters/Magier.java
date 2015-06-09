package Models.Karakters;

import Enumerations.Type;
import Interfaces.Karakter;
import Models.GebouwKaart;
import Models.Hand;
import Models.Speler;

import java.util.ArrayList;

/**
 * Created by daanrosbergen on 03/06/15.
 * Edited by Sander de Jong on 05/06/15.
 */

public class Magier implements Karakter {

    // Variables
    private final String  naam        = "Magier";
    private final int     nummer      = 3;
    private final int     bouwLimiet  = 1;
    private final Type    type        = Type.NORMAAL;

    private Speler  speler      = null;

    @Override
    public void gebruikEigenschap() {
        // TODO: ruilen bouwkaarten
        System.out.println("faka");
    }

    @Override
    public void setSpeler(Speler speler) {
        this.speler = speler;
    }

    // Ruil alle bouwkaarten met alle bouwkaarten van een ander speler/karakter??
    public void ruilMetKarakter(Speler target, Speler magier)
    {
        ArrayList<GebouwKaart> handTarget = target.getHand().getKaartenLijst();
        ArrayList<GebouwKaart> magierHand = magier.getHand().getKaartenLijst();
        target.getHand().setKaartenLijst(magierHand);
        magier.getHand().setKaartenLijst(handTarget);
    }

    // Leg een x aantal kaarten af op de stapel en pak een gelijk aantal nieuwe kaarten
    public void ruilMetStapel(Hand hand, ArrayList<GebouwKaart> ruilLijst)
    {
        // Afleggen en tellen gebouwkaarten.
        int count = 0;
        for (int i = 0; i < ruilLijst.size(); i++)
        {
            hand.removeGebouw(ruilLijst.get(i));
            count ++;
        }

        // Trek nieuwe kaarten. Misschien functie maken die een lijst van gebouwen aan hand kan toevoegen?
        ArrayList<GebouwKaart> tempList = hand.getSpeler().trekkenKaart(count);;
        for (int i = 0; i < tempList.size(); i++)
        {
            hand.addGebouw(tempList.get(i));
        }
    }

    // Return karakternummer
    public int getNummer() {
        return this.nummer;
    }

    // Return gebouwlimiet
    public int getBouwLimiet() {
        return this.bouwLimiet;
    }

    public Type getType() {
        return type;
    }

    public String getNaam() {

        return naam;
    }
}