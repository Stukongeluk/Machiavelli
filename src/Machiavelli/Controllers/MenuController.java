package Machiavelli.Controllers;

import Machiavelli.Factories.GebouwFactory;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Machiavelli;
import Machiavelli.Models.Speler;
import Machiavelli.Views.InvullenSpelersView;
import Machiavelli.Views.MainMenuView;

import java.rmi.registry.Registry;

public class MenuController {

    private MainMenuView mainMenuView;
    private InvullenSpelersView invullenspeler;
    private SpelController spelController;
    private Registry registry;

    /**
     * Maakt de MainMenuView aan en koppelt de buttons aan cmd's
     * zodat deze kunnen worden afgehandeld.
     *
     */
    public MenuController() {
        this.mainMenuView = new MainMenuView(this);
        this.registry = Machiavelli.getInstance().getRegistry();
        System.out.println("Loaded Registry");

        // Start het overzicht met spelknoppen (Nieuw spel, Deelnemen spel en Hervatten spel)
        mainMenuView.getStartButton().setOnAction(event -> mainMenuView.showSelect());

        // Sluit applicatie af bij exit buttons
        mainMenuView.getExitButton().setOnAction(event -> System.exit(0));
        mainMenuView.getExitButton2().setOnAction(event -> System.exit(0));

        // Handel spel commands af
        mainMenuView.getNieuwSpelKnop().setOnAction(event -> this.cmdNieuwSpel());
        mainMenuView.getHervattenknop().setOnAction(event -> this.cmdHervattenSpel());
        mainMenuView.getDeelnemenKnop().setOnAction(event -> this.cmdDeelnemenSpel());

        // Raadpleeg de spelregels
        mainMenuView.getSpelregelsButton().setOnAction(event -> new RaadplegenSpelregelsController().cmdWeergeefSpelregels());

        // Start de MainMenuView
        this.cmdMainMenu();
    }

    /**
     * Handelt het event af voor de 'Nieuw Spel' knop. Start de
     * InvullenSpelersView om te vragen met hoeveel spelers er
     * gespeeld gaat worden.
     */
    public void cmdNieuwSpel() {
        this.invullenspeler = new InvullenSpelersView(this);
        invullenspeler.getTerugButton().setOnAction(event -> this.cmdMainMenu());
        invullenspeler.getOkButton().setOnAction(event -> this.cmdInvullenSpelersStartNewGame());
        this.invullenspeler.show();
    }

    /**
     * Handelt het event af voor de 'Deelnemen spel' knop.
     */
    public void cmdDeelnemenSpel() {
        // TODO: Show new games
    	try{
    		SpelRemote spel = (SpelRemote)registry.lookup("Spel");
            this.spelController = new SpelController(spel);
            this.spelController.cmdAddSpeler(new Speler());
    	} catch(Exception re) {
    		re.printStackTrace();
    	}
    }

    /**
     * Handelt het event af voor de 'Hervatten spel' knop. Start het
     * overzicht met hervatbare spellen.
     */
    public void cmdHervattenSpel() {
        // TODO: Show resumable games
    }

    /**
     * Handelt het event af wanneer de gebruiker een aantal spelers
     * heeft ingevuld.
     */
    public void cmdInvullenSpelersStartNewGame() {
        // TODO: Create new spel instance?
    	try {
    		int maxAantalSpelers = Integer.parseInt(this.invullenspeler.getTextField());
            SpelRemote spelStub = (SpelRemote)this.registry.lookup("Spel");
            spelStub.createNewSpel(maxAantalSpelers);
            GebouwFactory factory1 = spelStub.getGebouwFactory();
            GebouwFactory factory2 = spelStub.getGebouwFactory();
            GebouwFactory factory3 = spelStub.getGebouwFactory();
            GebouwFactory factory4 = spelStub.getGebouwFactory();
            this.spelController = new SpelController(spelStub);
            this.spelController.cmdAddSpeler(new Speler());
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    /**
     * Start de MainMenuView
     */
    public void cmdMainMenu() {
        mainMenuView.show();
    }

}
