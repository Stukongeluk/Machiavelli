package Machiavelli.Views;

import Machiavelli.Controllers.MenuController;
import Machiavelli.Machiavelli;
import javafx.geometry.Rectangle2D;
import javafx.scene.CacheHint;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenuView {
    private Button startbutton;
    private Button exitbutton;
    private Button spelregels;
    private Button deelnemenknop;
    private Button hervattenknop;
    private Button nieuwspelknop;
    private Button exitbutton2;
    private Button spelregels2;
    // private SpelController sc;
    // private Stage primaryStage;
    private Scene mainMenu;
    private Scene mainSelect;
    private Stage stage = Machiavelli.getInstance().getStage();
    private Rectangle2D screenbounds = Machiavelli.getInstance().getScreenBounds();
    private MenuController menuController;

    /**
     * @author Jimmy
     * Edited by: Daan & Jamie
     * 
     * 
     * @param menuController
     */

    public MainMenuView(MenuController menuController) {
        double screenwidth = screenbounds.getWidth();
        double screenheight = screenbounds.getHeight();
        double centerX = screenbounds.getWidth()/2;
        double centerY = screenbounds.getHeight()/2;

        float buttonWidth = 200f;
        Pane mainMenuPane = new Pane();
        Pane mainSelectPane = new Pane();

        nieuwspelknop = new Button();
        hervattenknop = new Button();
        deelnemenknop = new Button();
        startbutton = new Button();
        exitbutton = new Button();
        spelregels = new Button();
        exitbutton2 = new Button();
        spelregels2 = new Button();

        // Machiavelli tekst layout
        Text mainTx = new Text("Machiavelli");
        mainTx.setFill(Color.WHITE);
        DropShadow ds = new DropShadow();
        ds.setOffsetY(2);
        mainTx.setEffect(ds);
        mainTx.setScaleX(4);
        mainTx.setScaleY(4);
        mainTx.setLayoutX(centerX);
        mainTx.setLayoutY(centerY - (centerY/1.5));

        // Knoppen definieren
        initButton(startbutton, "Kies spel", "buttonstart", centerX -(buttonWidth/2), 450, buttonWidth, 75f, "button-success");
        initButton(exitbutton, "Afsluiten", "buttonexit", centerX -(buttonWidth/2), 530, buttonWidth, 75f, "button-danger");
        initButton(spelregels, "Spelregels", "buttonregels", 15, 10, 125f, 50f, "button-primary");
        initButton(nieuwspelknop, "Nieuw spel", "gamekiezen", centerX  -(buttonWidth/2), 290, buttonWidth, 75f, "button-primary");
        initButton(hervattenknop, "Hervatten", "gamekiezen", centerX -(buttonWidth/2), 370, buttonWidth, 75f, "button-primary");
        initButton(deelnemenknop, "Deelnemen", "gamekiezen", centerX -(buttonWidth/2), 450, buttonWidth, 75f, "button-primary");
        initButton(exitbutton2, "Afsluiten", "buttonexit", centerX -(buttonWidth/2), 530, buttonWidth, 75f, "button-danger");
        initButton(spelregels2, "Spelregels", "buttonregels", 15, 10, 125f, 50f, "button-primary");

        Image spelregelsbg = new Image("Machiavelli/Resources/SpelregelsBorder.png");
        ImageView iv = new ImageView(spelregelsbg);
        ImageView iv2 = new ImageView(spelregelsbg);
        iv.setCache(true);
        iv.setFitWidth(205);
        iv.setFitHeight(74);
        iv2.setCache(true);
        iv2.setFitWidth(205);
        iv2.setFitHeight(74);

        // toevoegen van elementen aan het frame
        mainMenuPane.getChildren().addAll(iv, startbutton, exitbutton, spelregels, mainTx);
        mainMenuPane.getStyleClass().add("menu");
        mainMenuPane.setPrefSize(screenwidth, screenheight);
        mainSelectPane.getChildren().addAll(iv2, nieuwspelknop, hervattenknop, deelnemenknop,
                exitbutton2, spelregels2, mainTx);
        mainSelectPane.getStyleClass().add("menu");
        mainSelectPane.setPrefSize(screenwidth, screenheight);

        Pane container1 = new Pane();
        container1.setPrefSize(screenwidth, screenheight);
        container1.getChildren().add(mainMenuPane);

        Pane container2 = new Pane();
        container2.setPrefSize(screenwidth, screenheight);
        container2.getChildren().add(mainSelectPane);

        container1.getStylesheets().add("Machiavelli/Resources/style.css");
        container2.getStylesheets().add("Machiavelli/Resources/style.css");

        container1.setCache(true);
        container1.setCacheShape(true);
        container1.setCacheHint(CacheHint.SPEED);

        container2.setCache(true);
        container2.setCacheShape(true);
        container2.setCacheHint(CacheHint.SPEED);

        // Instellen wat er weergeven moet worden
        mainSelect = new Scene(container2, screenwidth, screenheight);
        mainMenu = new Scene(container1, screenwidth, screenheight);
        this.menuController = menuController;
    }

    public void initButton(Button button, String tekst, String id, double posx, double posy, float sizeX,
            float sizeY, String className) {
        button.setText(tekst);
        button.setId(id);
        button.setLayoutX(posx);
        button.setLayoutY(posy);
        button.setMinWidth(sizeX);
        button.setMinHeight(sizeY);
        button.getStyleClass().add(className);
    }

    public void show() {
        stage.setScene(mainMenu);
        stage.centerOnScreen();
        stage.show();
    }

    public void showSelect() {
        stage.setScene(mainSelect);
        stage.centerOnScreen();
        stage.show();
    }

    public Button getStartButton() {
        return startbutton;
    }

    public Button getExitButton() {
        return exitbutton;
    }


    public Button getSpelregelsButton() {
        return this.spelregels;
    }

    public Button getSpelregelsButton2() {
        return spelregels2;
    }

    public Button getExitButton2() {
        return exitbutton2;
    }

    public Button getNieuwSpelKnop() {
        return nieuwspelknop;
    }

    public Button getDeelnemenKnop() {
        return deelnemenknop;
    }

    public Button getHervattenknop() {
        return hervattenknop;
    }

    public void StopStage() {
        this.stage.hide();
    }
}
