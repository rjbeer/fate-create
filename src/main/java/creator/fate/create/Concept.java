package creator.fate.create;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.collections.ObservableList;

class Concept {
	
	private ObservableList<Node> child;
	private int index = 0;
	private CharacterData cdat;
	//private int aspectCount = 0;
	
	
	public Concept(CharacterData cd){
		cdat = cd;
	}
	
	public Tab buildConceptTab(){
		Tab concepts = new Tab("Concept");
		
		
		concepts.setContent(namesAndAspects());
		
		concepts.setClosable(false);
		
		
		return concepts;
	}
	
	private ScrollPane namesAndAspects(){
		ScrollPane sp = new ScrollPane();
		VBox vb = new VBox(5);
		
		vb.getChildren().addAll(namePlateBuilder(), conceptBuilder(), aspectContainerBuilder());
		sp.setContent(vb);
		
		return sp;
	}
	
	private HBox namePlateBuilder(){
		HBox names = new HBox(10);
		VBox player = new VBox(5);
		VBox charName = new VBox(5);
		VBox ref = new VBox(5);
		Text pName = new Text("Player Name");
		Text cName = new Text("Character Name");
		Text refresh = new Text("Refresh");
		
		//TODO: butt is for testing purposes only
		//remove before release
		Button butt = new Button("test");
		
		butt.setOnAction(e -> {
			System.out.println(cdat.getPName());
			System.out.println(cdat.getCName());
			System.out.println(cdat.getRefresh());
			System.out.println(cdat.getHConcept());
			System.out.println(cdat.getTrouble());
			System.out.println(cdat.getAspects());
			System.out.println(cdat.getDescriptions());
		});
		
		setTextStyle(pName);
		setTextStyle(cName);
		setTextStyle(refresh);
		
		player.getChildren().addAll(pName, buildPNameArea(), butt);
		charName.getChildren().addAll(cName, buildCNameArea());
		ref.getChildren().addAll(refresh, buildRefreshArea());
		
		names.getChildren().addAll(player, charName, ref);
		
		return names;
	}
	
	private TextArea buildPNameArea(){
		TextArea pNameArea =  new TextArea(cdat.getPName());
		
		setTAreaStyle(pNameArea, 10, 1, "15");
		
		pNameArea.textProperty().addListener((obs, old, nu)->{
			cdat.setPName(nu);
		});
		
		return pNameArea;
	}
	
	private TextArea buildCNameArea(){
		TextArea cNameArea =  new TextArea(cdat.getCName());
		
		setTAreaStyle(cNameArea, 10, 1, "15");
		
		cNameArea.textProperty().addListener((obs, old, nu)->{
			cdat.setCName(nu);
		});
		
		return cNameArea;
	}
	
	private TextArea buildRefreshArea(){
		TextArea refreshArea =  new TextArea(cdat.getRefresh());
		
		setTAreaStyle(refreshArea, 1, 1, "15");
		
		refreshArea.textProperty().addListener((obs, old, nu)->{
			cdat.setRefresh(nu);
		});
		
		return refreshArea;
	}
	
	private HBox conceptBuilder(){
		Text hConcept = new Text("High Concept");
		Text trouble = new Text("Trouble");
		HBox conceptContainer = new HBox(20);
		VBox concept = new VBox(5);
		
		
		concept.getChildren().addAll(conceptAreaBuilder(hConcept), conceptAreaBuilder(trouble));
		conceptContainer.getChildren().add(concept);
		
		return conceptContainer;
	}
	
	private VBox conceptAreaBuilder(Text t){
		VBox concepts = new VBox(5);
		TextArea concept = new TextArea("");
		
		if(t.getText().equals("High Concept")){
			concept.textProperty().addListener((obs, old, nu)->{
				cdat.setHConcept(nu);
			});
		}else{
			concept.textProperty().addListener((obs, old, nu)->{
				cdat.setTrouble(nu);
			});
		}
		
		setTextStyle(t);
		setTAreaStyle(concept, 20, 1, "16");
		
		concepts.getChildren().addAll(t, concept);
		
		return concepts;
	}
	
	private HBox aspectContainerBuilder(){
		HBox aspectsContainer = new HBox(10);
		VBox container = new VBox(5);
		VBox aspects = new VBox(20);
		Button add = new Button("Add");
		Text t = new Text("Aspects");
		
		setTextStyle(t);
		// TODO: aspectBuilder does not work as expected, all text entered will not be recorded in cdat.
		// make sure to update. Remember to have concept build itself off of the data provided by cdat.
		for(int i = 0; i < cdat.getCount(); i++){
			aspects.getChildren().add(aspectBuilder(cdat.getAspect(i), cdat.getDescription(i), i));
		}
		
		add.setOnAction(e -> {
			Button rem = new Button("Remove");
			HBox newAspect = aspectBuilder(cdat.getCount());
			newAspect.getChildren().addAll(rem);
			aspects.getChildren().add(newAspect);
			rem.setOnAction(ev -> {
				child = aspects.getChildren();
				index = child.indexOf(newAspect);
				cdat.removeAspect(index);
				cdat.removeDescription(index);
				//aspectCount--;
				aspects.getChildren().remove(newAspect);
			});
		});
		
		container.getChildren().addAll(t, aspects, add);
		aspectsContainer.getChildren().addAll(container);
		
		return aspectsContainer;
	}
	
	//TODO:!!!will need to be tested for bugs!!!
	private HBox aspectBuilder(int i){
		TextArea aspectName = new TextArea("");
		TextArea aspectDescription = new TextArea("");
		VBox aspect = new VBox(5);
		HBox contain = new HBox(10);
		
		cdat.addAspect("");
		cdat.addDescription("");
		aspectName.setText(cdat.getAspect(i));
		aspectDescription.setText(cdat.getDescription(i));
		
		aspectName.textProperty().addListener((obs, old, nu)->{
			cdat.setAspect(nu, i);
		});
		aspectDescription.textProperty().addListener((obs, old, nu)->{
			cdat.setDescription(nu, i);
		});
		
		setTAreaStyle(aspectName, 20, 1, "16", "Aspect...");
		setTAreaStyle(aspectDescription, 20, 5, "14", "Description...");
		
		aspect.getChildren().addAll(aspectName, aspectDescription);
		contain.getChildren().addAll(aspect);
		
		//aspectCount++;
		
		return contain;
	}
	
	//TODO: this is the aspectBuilder that needs to be adjusted, we need to use the info gathered from cdat
	// and then make it so all info will be recorded into cdat when changed
	private HBox aspectBuilder(String asp, String description, int i){
		HBox newAspect = new HBox(10);
		VBox aspect = new VBox(5);
		TextArea aspectName = new TextArea(asp);
		TextArea aspectDescription = new TextArea(description);
		
		setTAreaStyle(aspectName, 20, 1, "16", "Aspect...");
		setTAreaStyle(aspectDescription, 20, 5, "14", "Description...");
		
		aspectName.textProperty().addListener((obs, old, nu)->{
			cdat.setAspect(nu, i);
		});
		aspectDescription.textProperty().addListener((obs, old, nu)->{
			cdat.setDescription(nu, i);
		});
		
		aspect.getChildren().addAll(aspectName, aspectDescription);
		newAspect.getChildren().addAll(aspect);
		
		return newAspect;
	}
	
	private void setTextStyle(Text text){
		text.setStyle("-fx-font-size: 12");
		text.setUnderline(true);
	}
	
	private void setTAreaStyle(TextArea ta, int col, int row, String size){
		ta.setPrefColumnCount(col);
		ta.setPrefRowCount(row);
		ta.setStyle("-fx-font-size: " + size);
	}
	
	private void setTAreaStyle(TextArea ta, int col, int row, String size, String prompt){
		ta.setPrefColumnCount(col);
		ta.setPrefRowCount(row);
		ta.setStyle("-fx-font-size: " + size);
		ta.setPromptText(prompt);
	}
	
}