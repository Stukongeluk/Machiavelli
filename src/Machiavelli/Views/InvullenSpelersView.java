package Machiavelli.Views;

import Machiavelli.Controllers.MenuController;
import Machiavelli.Machiavelli;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InvullenSpelersView extends TextField {
//	private SpelController spelcontroller;
//	private Spel spel;
	private Button okbutton;
	private Button terugbutton;
	private TextField textfield;
	private Scene invulscene;
	private Text invoertekst;
	private Stage stage = Machiavelli.getInstance().getStage();
	private MenuController menuController;

	public InvullenSpelersView(MenuController menuController){
		this.menuController = menuController;
		VBox vbox = new VBox();
		HBox hbox = new HBox();
		invoertekst = new Text("Vul hier minimaal 2 en maximaal 7 spelers in");
		invoertekst.setFill(Color.WHITE);
		okbutton    = new Button("Ok");
		okbutton.getStyleClass().add("button-success");
		okbutton.setPrefWidth(70);
		terugbutton = new Button("Terug");
		terugbutton.getStyleClass().add("button-danger");
		terugbutton.setPrefWidth(70);
		hbox.getChildren().addAll(okbutton, terugbutton);
		hbox.setSpacing(20);
		hbox.setAlignment(Pos.CENTER);

        textfield   = new TextField();
		textfield.setMaxSize(170, 10);
		textfield.setAlignment(Pos.CENTER);

		vbox.getStylesheets().add("Machiavelli/Resources/style.css");
		vbox.getStyleClass().add("menu");
		vbox.getChildren().addAll(invoertekst,textfield, hbox);
		vbox.setAlignment(Pos.CENTER);
		
		invulscene = new Scene(vbox, 400, 200);
	}
 
    public void show(){
		stage.setScene(invulscene);
		stage.centerOnScreen();
		stage.show();
	}

    public Button getOkButton(){
    	return okbutton;
    }

    public Button getTerugButton(){
    	return terugbutton;
    }

    public String getTextField(){
    	return textfield.getText();
    }
}

