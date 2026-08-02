package creator.fate.create;

import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCombination;
import javafx.scene.control.Button;

public class Main extends Application{
	
	FateControl fc = new FateControl();

	@Override
	public void start(Stage stage) throws Exception{
		BorderPane root = new BorderPane();
		Button newButt = new Button("Create Character");
		Button loadButt = new Button("Load Character");
		
		newButt.setOnAction(e -> {
			Scene scene1 = fc.sceneOneBuild();
			stage.setScene(scene1);
		});
		
		VBox vb = new VBox(5);
		vb.getChildren().addAll(newButt, loadButt);
		vb.setAlignment(Pos.CENTER);

		root.setCenter(vb);
		//root.setCenter(loadButt);
		

		Scene scene = new Scene(root, Color.DARKGRAY);
		
		//to add icon in the future
		//Image icon = new Image("<Image name>");
		//stage.getIcons().add(icon);

		stage.setTitle("Fate Create");
		stage.setWidth(720);
		stage.setHeight(610);
		stage.setScene(scene);
		stage.show();
	}


	public static void main(String[] args){
		launch(args);
		
	}
}
