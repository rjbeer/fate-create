package creator.fate.create;

import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.geometry.Side;
import javafx.geometry.Pos;


class FateControl{
	private Scene scene;
	private TabPane tabPane = new TabPane();
	private BorderPane root;
	private ComponentBuilder cb = new ComponentBuilder();
	private MenuBar mb = new MenuBar();
	private HBox skillList = new HBox(5);
	private VBox skill = new VBox(5);
	private Text skillNP = new Text("Skills");
	
	
	//TODO: refactor sceneOneBuilder() variables to class variables
	
	//new character button scene
	public Scene sceneOneBuild(){
		VBox vb = new VBox(5);//holds aspects and concepts needs to be renamed to something meaningful
		VBox vbtwo = new VBox(15);//holds skills, stress and consequences to be renamed into something meaningful
		VBox vbthree = new VBox(15);//holds stunts and extras to be renamed into something meaningful
		ScrollPane sp = new ScrollPane();//first scroll pane holds concepts needs to be renamed
		ScrollPane spTwo = new ScrollPane();//second scroll pane holds skills and stress needs to be renamed
		ScrollPane spThree = new ScrollPane();//third scroll pane holds stunts and extras needs to be renamed
		root = new BorderPane();
		//tabs for the scene
		//TODO: make tabs class variables
		Tab concept = new Tab("Concept");
		Tab skills = new Tab("Skills/Stress");
		Tab stuntAndExtra = new Tab("Stunts/Extras");
		
		concept.setClosable(false);
		skills.setClosable(false);
		stuntAndExtra.setClosable(false);
		
		
		//menubar option for saving, loading, exiting, and new characters
		mb.getMenus().add(buildMenu());
		
		
		//TODO: add tabpane build to component builder
		
		vb.getChildren().addAll(cb.namePlatesBuilder(), cb.conceptBuilder(), cb.aspectContainerBuilder());
		vbtwo.getChildren().addAll(cb.buildSkillList(), cb.buildPhysStress(), cb.buildMentalStress(), cb.buildConsequences());
		vbthree.getChildren().addAll(cb.buildStuntsPanel(), cb.buildExtrasPanel());
		
		sp.setContent(vb);
		spTwo.setContent(vbtwo);
		spThree.setContent(vbthree);
		sp.setStyle("-fx-font-size: 15px");
		vb.setStyle("-fx-font-size: 11");
		
		concept.setContent(sp);
		skills.setContent(spTwo);
		stuntAndExtra.setContent(spThree);
		
		tabPane.setSide(Side.LEFT);
		tabPane.getTabs().addAll(concept, skills, stuntAndExtra);
		root.setCenter(tabPane);
		root.setTop(mb);
		
		scene = new Scene(root, Color.DARKGREY);
		String css = this.getClass().getResource("/styling.css").toExternalForm();
		scene.getStylesheets().add(css);

		return scene;
	}
	
	
	//TODO: move buildMenu() to Main.java should be apart of the root scene in main
	private Menu buildMenu(){
		MenuItem save = new MenuItem("Save");
		MenuItem saveAs = new MenuItem("Save As");
		MenuItem newChar = new MenuItem("New Character");
		MenuItem exit = new MenuItem("Exit");
		Menu file = new Menu("File");
		
		file.getItems().addAll(newChar, save, saveAs, exit);
		
		exit.setOnAction(e -> {
			System.exit(0);
		});
		
		return file;
	}
	
	
}