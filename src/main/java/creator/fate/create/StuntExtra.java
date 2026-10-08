package creator.fate.create;

import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

class StuntExtra {
	CharacterData cdat;
	
	public StuntExtra(CharacterData cd){
		cdat = cd;
	}
	
	public Tab buildStuntExtra(){
		Tab stuntExtra = new Tab("Stunts/Extras");
		ScrollPane sb = new ScrollPane();
		
		sb.setContent(stuntsAndExtras());
		
		stuntExtra.setContent(sb);
		stuntExtra.setClosable(false);
		
		return stuntExtra;
	}
	
	private VBox stuntsAndExtras(){
		VBox vb = new VBox();
		Button test = new Button("Test");
		
		test.setOnAction(e -> {
			System.out.println(cdat.getStunts());
			System.out.println(cdat.getExtras());
		});
		
		vb.getChildren().addAll(buildStunts(), buildExtras(), test);
		
		return vb;
	}
	
	//TODO: buildStunts
	private VBox buildStunts(){
		VBox stuntsBox = new VBox(5);
		Text stuntsText = new Text("Stunts");
		TextArea stunts = new TextArea();
		
		stuntsText.setUnderline(true);
		stuntsText.setStyle("-fx-font-size: 15");
		
		stunts.textProperty().addListener((obs, old, nu)->{
			cdat.setStunts(nu);
		});
		
		stuntsBox.getChildren().addAll(stuntsText, stunts);
		
		return stuntsBox;
	}
	
	//TODO: buildExtras
	private VBox buildExtras(){
		VBox extrasBox = new VBox();
		Text extrasText = new Text("Extras");
		TextArea extras = new TextArea();
		
		extras.textProperty().addListener((obs, old, nu)->{
			cdat.setExtras(nu);
		});
		
		extrasText.setUnderline(true);
		extrasText.setStyle("-fx-font-size: 15");
		
		extrasBox.getChildren().addAll(extrasText, extras);
		
		return extrasBox;
	}
	
	
}