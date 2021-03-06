package Machiavelli.Views;

import Machiavelli.Controllers.SpeelveldController;
import Machiavelli.Interfaces.Bonusable;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.KarakterObserver;
import Machiavelli.Interfaces.Observers.SpelerObserver;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Deze view maakt een GridPane aan en plaatst daar buttons in. Buttons worden vervolgens gekoppeld
 * aan de juiste methodes in bijbehorende controllers.
 *
 * Deze klasse update wanneer Speler of Karakter wijzigen en schakelt buttons naar behoren uit.
 *
 * Deze klasse extends UnicastRemoteObject en kan op die manier ontvangen van andere Remote
 * objecten.
 *
 * @author Daan Rosbergen
 * @version 1.0
 */
public class ButtonHolderActionBarView extends UnicastRemoteObject implements SpelerObserver,
        KarakterObserver {

    private SpeelveldController speelveldController;
    private GridPane buttonGrid = new GridPane();
    private Button gebruikEigenschap;
    private Button exitbutton;
    private Button spelregels;
    private Button opslaanknop;
    private Button goudbutton;
    private Button bouwbutton;
    private Button eindebeurtbutton;
    private Rectangle buttonholder;
    private SpelerRemote speler;
    private StackPane container;
    private boolean disabled;
    private Karakter karakter;

    /**
     * Constructor maakt GridPane en een container StackPane aan. Maakt alle nodige buttons aan en
     * voegt zichzelf toe aan de als observer aan Speler en Karakter van speler. Koppelt buttons aan
     * commands in Controllers.
     *
     * @param speelveldController
     * @throws RemoteException
     */
    public ButtonHolderActionBarView(SpeelveldController speelveldController)
            throws RemoteException {
        this.speelveldController = speelveldController;
        this.speler = speelveldController.getSpeler();
        this.karakter = this.speler.getKarakter();

        container = new StackPane();
        gebruikEigenschap = new Button();
        exitbutton = new Button();
        spelregels = new Button();
        opslaanknop = new Button();
        goudbutton = new Button();
        bouwbutton = new Button();
        eindebeurtbutton = new Button();

        this.speler.addObserver(this);
        this.karakter.addObserver(this);

        this.buttonGrid.setHgap(10);
        this.buttonGrid.setVgap(10);
        this.buttonGrid.setPadding(new Insets(22.5, 0, 22.5, 0));

        initButton(gebruikEigenschap, "Eigenschap", "button-primary", 1, 1, 160f, 55f);
        initButton(bouwbutton, "Bouwen", "button-primary", 2, 1, 160f, 55f);
        initButton(goudbutton, "Bonusgoud", "button-primary", 1, 2, 160f, 55f);
        initButton(eindebeurtbutton, "Einde beurt", "button-danger", 2, 2, 160f, 55f);
        initButton(opslaanknop, "Opslaan", "button-success", 1, 3, 160f, 55f);
        initButton(exitbutton, "Afsluiten", "button-danger", 2, 3, 160f, 55f);

        opslaanknop.setOnAction(event1 -> this.speelveldController.cmdOpslaan());
        goudbutton.setOnAction(event -> this.speelveldController.cmdBonusGoud());
        bouwbutton.setOnAction(event -> this.speelveldController.cmdBouwGebouw());
        eindebeurtbutton.setOnAction(event -> this.speelveldController.cmdEindeBeurt());
        gebruikEigenschap.setOnAction(event -> this.speelveldController.cmdGebruikEigenschap());
        exitbutton.setOnAction(event -> System.exit(0));

        isKarakterBonusable();

        buttonholder = new Rectangle(0, 0, 350, 250);
        buttonholder.setFill(Color.rgb(57, 57, 57));
        this.container.getChildren().addAll(buttonholder, buttonGrid);
    }

    /**
     * Controleer of Karakter bonusable is. Wanneer karakter niet bonusable is word de 'Bonusgoud'
     * knop uitgezet.
     */
    private void isKarakterBonusable() {
        try {
            Bonusable bonusable = (Bonusable) this.karakter;
            if (!bonusable.isBonusable()) {
                goudbutton.setDisable(true);
            }
        } catch (Exception e) {
            if (e instanceof ClassCastException) {
                goudbutton.setDisable(true); // Disable button
            } else {
                goudbutton.setDisable(false); // Enable button
            }
        }
    }

    /**
     * Helper function voor het opbouwen van buttons. Voegt button toe aan aan GridPane
     *
     * @param button Button object
     * @param tekst Tekst in button
     * @param styleClass CSS class voor button
     * @param columnIndex Column in GridPane
     * @param rowIndex Row in GridPane
     * @param sizeX Breedte van button
     * @param sizeY Hoogte van button
     */
    private void initButton(Button button, String tekst, String styleClass, int columnIndex,
            int rowIndex, float sizeX, float sizeY) {
        button.setText(tekst);
        button.getStyleClass().add(styleClass);
        button.setMinWidth(sizeX);
        button.setMinHeight(sizeY);
        this.buttonGrid.add(button, columnIndex, rowIndex);
    }

    /**
     * @return Spelregels button
     */
    public Button getSpelregels() {
        return this.spelregels;
    }

    /**
     * @return Afsluiten button
     */
    public Button getExitbutton() {
        return this.exitbutton;
    }

    /**
     * @return Bonusgoud button
     */
    public Button getGoudbutton() {
        return this.goudbutton;
    }

    /**
     * @return Gebruik eigenschap button
     */
    public Button getEigenschapButton() {
        return this.gebruikEigenschap;
    }

    /**
     * @return Bouwen button
     */
    public Button getBouwButton() {
        return this.bouwbutton;
    }

    /**
     * @return Opslaan button
     */
    public Button getOpslaanButton() {
        return this.opslaanknop;
    }

    /**
     * @return Einde beurt button
     */
    public Button getEindeBeurtButton() {
        return this.eindebeurtbutton;
    }

    /**
     * SpelerObserver method. Update view wanneer Speler object wijzigd.
     *
     * @param speler model changed.
     * @throws RemoteException
     */
    @Override
    public void modelChanged(SpelerRemote speler) throws RemoteException {
        Platform.runLater(() -> {
            try {
                this.speler = speler;
                this.karakter = speler.getKarakter();
                this.karakter.addObserver(this);
                this.container.getChildren().clear();
                isKarakterBonusable();
                isAbleToBuild();
                IsAbleToGebruikEigenschap();
                this.container.getChildren().addAll(buttonholder, buttonGrid);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * KarakterObserver method. Update view wanneer Karakter wijzigd.
     *
     * @param karakter
     */
    @Override
    public void modelChanged(Karakter karakter) {
        Platform.runLater(() -> {
              try {
                this.karakter = karakter;
                this.container.getChildren().clear();
                isKarakterBonusable();
                isAbleToBuild();
                IsAbleToGebruikEigenschap();
                this.container.getChildren().addAll(buttonholder, buttonGrid);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Enabled de Bouwen button wanneer een Speler mag bouwen.
     *
     * @throws RemoteException
     */
    private void isAbleToBuild() throws RemoteException {
        int gebouwdeGebouwen = speler.getGebouwdeGebouwen();
        int bouwLimiet = speler.getKarakter().getBouwLimiet();
        if (gebouwdeGebouwen >= bouwLimiet) {
            bouwbutton.setDisable(true); // Disable button
        }
    }

    /**
     * Enabled Gebruik eigenschap button wanneer een Karakter zijn eigenschap kan gebruiken.
     *
     * @throws RemoteException
     */
    private void IsAbleToGebruikEigenschap() throws RemoteException {
        boolean eigenschapGebruikt = speler.EigenschapGebruikt();
        if (eigenschapGebruikt == true) {
            gebruikEigenschap.setDisable(true);
        }
    }

    /**
     * Methode om opgebouwde view op te halen.
     *
     * @return StackPane De uiteindelijke ButtonHolderActionBarView
     */
    public StackPane getPane() {
        return this.container;
    }

}
