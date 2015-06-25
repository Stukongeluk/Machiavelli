package Machiavelli.Controllers;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javafx.application.Platform;
import Machiavelli.Interfaces.Bonusable;
import Machiavelli.Interfaces.Observers.SpelObserver;
import Machiavelli.Interfaces.Remotes.BeurtRemote;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Models.Beurt;
import Machiavelli.Models.Speelveld;
import Machiavelli.Views.SpeelveldView;

/**
 * 
 * Het speelveld controller maakt het speelveld view aan en kijkt of het speelveld model is veranderd
 * doormiddel van de modelChanged method.
 *
 */

public class SpeelveldController extends UnicastRemoteObject implements SpelObserver {
  
    private SpelerRemote speler;
    private BeurtRemote beurt;
    private SpelRemote spel;
    
    private MeldingController meldingController;
    private BeurtController beurtController;
    private GebouwKaartController gebouwKaartController;
    private Speelveld speelveld;
	private SpeelveldView speelveldview;


    public SpeelveldController(SpelRemote spel, SpelerRemote speler, GebouwKaartController gebouwKaartController, BeurtRemote beurt) throws RemoteException {
        this.spel = spel;
        this.speler = speler;
        this.beurt = beurt;
        this.speelveld = new Speelveld(this.spel);
        this.speelveld.addSpeler(speler);
        this.gebouwKaartController = gebouwKaartController;
        this.beurtController = new BeurtController(this.beurt,this.spel);

        this.speelveldview = new SpeelveldView(this, this.speelveld, this.gebouwKaartController, this.speler,this.beurt, this.beurtController);
        
//		speelveldview.getSpelregels().setOnAction((event) ->
//		{
//			RaadplegenSpelregelsController spelregelscontroller = new RaadplegenSpelregelsController();
//			spelregelscontroller.cmdSluitSpelregelView();
//		});
        this.meldingController = new MeldingController();

        this.spel.addObserver(this);
        this.speelveldview.show();

        if (this.spel.getAantalSpelers() < this.spel.getMaxAantalSpelers()) {
            this.meldingController.build("Wachten op spelers: " + this.spel.getAantalSpelers() + "/" + this.spel.getMaxAantalSpelers());
            this.meldingController.getSluitButton().setDisable(true);
            this.meldingController.getSluitButton().setText("Wachten...");
            this.meldingController.cmdWeergeefMeldingView();
        }
    }

	public SpelRemote getSpel() {
		return this.spel;
	}

    public void cmdBonusGoud() {
        try {
            Bonusable karakter = (Bonusable)this.speler.getKarakter();
            karakter.ontvangenBonusGoud();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SpelerRemote getSpeler() {
        return this.speler;
    }
    
    public BeurtRemote getBeurt() {
      return this.beurt;
    }
    

    public void cmdBouwGebouw() {
        // TODO: Implement gebouwbouwen method
        this.gebouwKaartController.cmdBouwGebouw();
    }

    public void cmdEindeBeurt() {
        // TODO: Implement eindeBeurt method
        this.beurtController.cmdGeefBeurt();
    }

    @Override
    public void modelChanged(SpelRemote spel) throws RemoteException {
        this.spel = spel;
        if (this.spel.getMaxAantalSpelers() == this.spel.getAantalSpelers()) {
            Platform.runLater(() -> this.meldingController.cmdSluitMeldingView());
        } else {
            this.meldingController.build("Wachten op spelers: " + this.spel.getAantalSpelers() + "/" + this.spel.getMaxAantalSpelers());
        }
        System.out.println("SpeelveldController: Spel model changed!");
        System.out.println("Aantal spelers: " + this.spel.getAantalSpelers());
    }
}
