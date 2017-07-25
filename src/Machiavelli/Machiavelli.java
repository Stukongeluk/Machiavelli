package Machiavelli;

import Machiavelli.Controllers.MenuController;
import com.sun.org.apache.regexp.internal.RE;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Machiavelli client applicatie. Verbind met server en roept het menu aan.
 * Deze klasse is een singleton zodat het Registry en de
 * Stage van JavaFX kunnen worden aangeroepen.
 *
 * @author Daan Rosbergen
 * @version 1.0
 */

public class Machiavelli extends Application {

    private static Machiavelli uniqueInstance;
    private Stage stage;
    private Registry registry;
    private Rectangle2D screenBounds;

    /**
     * Machiavelli constructor. Is een aangepaste singleton ivm. JavaFX
     */
    public Machiavelli() {
        super();
        synchronized(Machiavelli.class){
            if(uniqueInstance != null) throw new UnsupportedOperationException(
                    getClass()+" is singleton but constructor called more than once");
            uniqueInstance = this;
        }
    }

    /**
     * Start method van applicatie. Start applicatie, verbind met server
     * en start het menu.
     *
     * @param primaryStage
     * @throws Exception
     */
	@Override
	public void start(Stage primaryStage) throws Exception {
        this.screenBounds = Screen.getPrimary().getVisualBounds();
        this.stage = primaryStage; // Create stage
        this.stage.setResizable(false); // Make stage unresizable
        this.stage.setX(this.screenBounds.getMinX());
        this.stage.setY(this.screenBounds.getMinY());
        this.stage.setWidth(this.screenBounds.getWidth());
        this.stage.setHeight(this.screenBounds.getHeight());
        this.stage.setTitle("Machiavelli"); // Set title of stage
        this.stage.getIcons().add(new Image("/Machiavelli/Resources/icon.png"));

      try {
            System.out.println("Getting access to the registry");
            // get access to the RMI registry on the remote server
            // Wijzig naar lokaal ip adres voor testen
            //this.registry = LocateRegistry.getRegistry("145.97.16.203", 1099); // if server on another machine: provide that machine's IP address. Default port  1099
          // TODO: Dont search for a server at startup.. only when trying to join a game
           this.registry = LocateRegistry.getRegistry("localhost", 1099);
            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        new MenuController(); // Start menu
    }

    /**
     * Singleton instance van applicatie
     * @return Machiavelli
     */
    public static synchronized Machiavelli getInstance() {
        return uniqueInstance;
    }

    /**
     * Main method, start client applicatie
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Stage van applicatie
     * @return Stage
     */
    public Stage getStage() {
        return this.stage;
    }

    /**
     * Registry van applicatie (verbinding met server)
     * @return Registry
     */
    public Registry getRegistry() {
        return this.registry;
    }

    public Rectangle2D getScreenBounds() {
        return screenBounds;
    }
}
