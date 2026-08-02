package creator.fate.create;

import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
//import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.geometry.Side;


class SheetGen{
	private Scene charSheet;
	private BorderPane root;
	private Concept con;
	private SkillStress ss;
	//private CharacterData charDat = new CharacterData();
	private TabPane sheetTabs = new TabPane();
	
	
	public Scene generateSheet(CharacterData charDat){
		root = new BorderPane();
		con = new Concept(charDat);
		ss = new SkillStress(charDat);
		
		sheetTabs.getTabs().addAll(con.buildConceptTab(), ss.buildSkillStress());
		sheetTabs.setSide(Side.LEFT);
		
		root.setCenter(sheetTabs);
		
		charSheet = new Scene(root, Color.DARKGREY);
		
		String css = this.getClass().getResource("/styling.css").toExternalForm();
		charSheet.getStylesheets().add(css);
		
		return charSheet;
	}
}
