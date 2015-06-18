package Machiavelli.Views;

import java.rmi.RemoteException;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Machiavelli.Machiavelli;
import Machiavelli.Controllers.InkomstenController;
import Machiavelli.Models.Spel;
import Machiavelli.Models.Speler;

public class KiesInkomstenView {
	
	//Variables
	private InkomstenController inkomstenController;
	//comment out the new stage
//	private Stage stage;
	private Button ontvangGoud, ontvangKaarten;
	private ImageView goudImage, kaartenImage;
	private Text title;
	private Pane pane;
	
	public KiesInkomstenView() throws RemoteException
	{
		//TODO: inkomstencontroller moet de speler krijgen van beurt!
														//new speler is voor testen.
		this.inkomstenController = new InkomstenController(new Speler());
		
		this.title = new Text("Maak je keuze:");
		this.title.setId("title");
		this.title.setFill(Color.WHITE);
		this.title.setLayoutX(660);
		this.title.setLayoutY(50);
		
		this.goudImage = new ImageView(new Image("Machiavelli/Resources/placeholderimg.gif"));
		this.goudImage.setId("goudImg");
		this.goudImage.setLayoutX(300);
		this.goudImage.setLayoutY(200);
		Rectangle goudRect = new Rectangle(400, 400);
		goudRect.setArcHeight(400);
		goudRect.setArcWidth(400);
		this.goudImage.setClip(goudRect);
		
		this.kaartenImage = new ImageView(new Image("Machiavelli/Resources/placeholderimg.gif"));
		this.kaartenImage.setId("kaartenImg");
		this.kaartenImage.setLayoutX(900);
		this.kaartenImage.setLayoutY(200);
		Rectangle kaartenRect = new Rectangle(400, 400);
		kaartenRect.setArcHeight(400);
		kaartenRect.setArcWidth(400);
		this.kaartenImage.setClip(kaartenRect);
		
		this.ontvangGoud = new Button("Ontvang goud");
		this.ontvangGoud.setId("goudButton");
		this.ontvangGoud.setLayoutX(300);
		this.ontvangGoud.setLayoutY(700);
		this.ontvangGoud.setMinWidth(400f);
		this.ontvangGoud.setMinHeight(80f);
		
		this.ontvangKaarten = new Button("Ontvang kaarten");
		this.ontvangKaarten.setId("kaartenButton");
		this.ontvangKaarten.setLayoutX(900);
		this.ontvangKaarten.setLayoutY(700);
		this.ontvangKaarten.setMinWidth(400f);
		this.ontvangKaarten.setMinHeight(80f);
		
		this.ontvangGoud.setOnAction((event) -> 
		{
			this.cmdSluitKiesInkomstenView();
			inkomstenController.cmdKiezenGoud();
		});
		
		this.ontvangKaarten.setOnAction((event) -> {
			this.cmdSluitKiesInkomstenView();
			this.inkomstenController.weergeefTrekkenKaartView();
		});
		
		this.pane = new Pane();
		this.pane.setId("kiesInkomstenPane");
		Rectangle rect = new Rectangle(1600, 900);
		pane.setClip(rect);
		this.pane.getChildren().addAll(this.title, this.goudImage, this.kaartenImage, this.ontvangGoud, this.ontvangKaarten);
		this.pane.getStylesheets().add("Machiavelli/Resources/KiesInkomstenView.css");
        
	}
	
	public void weergeefKiesInkomstenView()
	{
		StackPane pane = new StackPane();
    	
    	Pane old = new Pane();
    	old.getChildren().add(Machiavelli.getInstance().getStage().getScene().getRoot());
    	pane.getChildren().addAll(old, this.pane);

    	
    	Scene scene = new Scene(pane, 1440, 900);
		Machiavelli.getInstance().getStage().setScene(scene);
	}
	
	public void cmdSluitKiesInkomstenView()
	{
		Pane newPane = new Pane();
    	Scene currentScene = Machiavelli.getInstance().getStage().getScene();

    	System.out.println("\nThe current scene contains the following nodes (panes): ");
    	for(Node node : currentScene.getRoot().getChildrenUnmodifiable())
    	{
    		System.out.println(node.idProperty());
    		if(currentScene.lookup("#kiesInkomstenPane").equals(node))
    		{
    			newPane.getChildren().add(node);
    			
    			System.out.println("\nVerwijderd: " + node.getId());
    			break;
    		}
    	}
    	
    	newPane = null;
    	
    	//show the nodes in the current list.
    	System.out.println("\nThe current scene contains the following nodes (panes): ");
    	for(Node node : currentScene.getRoot().getChildrenUnmodifiable())
    	{
    		System.out.println(node.idProperty());
    	}
	}
	
	public Pane getPane()
	{
		return this.pane;
	}

}
