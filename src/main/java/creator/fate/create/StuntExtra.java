package creator.fate.create;

import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

class StuntExtra {
	CharacterData cdat;
	
	public StuntExtra(CharacterData cd){
		cdat = cd;
	}
	
	public Tab buildStuntExtra(){
		Tab stuntExtra = new Tab("Stunts/Extras");
		
		stuntExtra.setContent(stuntsAndExtras());
		stuntExtra.setClosable(false);
		
		return stuntExtra;
	}
	
	private VBox stuntsAndExtras(){
		VBox vb = new VBox();
		
		vb.getChildren().addAll(buildStunts(), buildExtras());
		
		return vb;
	}
	
	//TODO: buildStunts
	private VBox buildStunts(){
		VBox stunts = new VBox();
		
		return stunts;
	}
	
	//TODO: buildExtras
	private VBox buildExtras(){
		VBox extras = new VBox();
		
		return extras;
	}
}