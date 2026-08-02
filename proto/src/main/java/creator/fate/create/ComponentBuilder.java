package creator.fate.create;

import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.collections.FXCollections;

class ComponentBuilder{
	
	private String[] skills = {"", "Athletics", "Burglary", "Contacts", "Crafts", 
								"Deceive", "Drive", "Empathy", "Fight", 
								"Investigate", "Lore", "Notice", "Physique", 
								"Provoke", "Rapport", "Resources", "Shoot",
								"Stealth", "Will"};
	private Text[] rating = {new Text("Superb +5"), new Text("Great +4"), 
							new Text("Good +3"), new Text("Fair +2"), new Text("Average +1")};
	
	
	
	/* Below will be the methods necessary to creating the scenes
	* for each tab located on a character sheet.
	* The order in which these are created is;
	* 1) Concept
	* 2) Skills/Stress
	* 3) Stunts/Extras
	*/
	
	//Below are the methods related to the Concept tab
	public HBox namePlatesBuilder(){
		HBox names = new HBox(10);
		VBox player = new VBox(5);
		VBox character = new VBox(5);
		VBox refresh = new VBox(5);
		Text pName = new Text("Player Name");
		Text cName = new Text("Character Name");
		Text ref = new Text("Refresh");
		TextArea pNameArea = new TextArea("");
		TextArea cNameArea = new TextArea("");
		TextArea refArea = new TextArea("");
		
		setTextStyle(pName);
		setTextStyle(cName);
		setTextStyle(ref);
		
		setTAreaStyle(refArea, 1, 1, "15");
		setTAreaStyle(pNameArea, 10, 1, "15");
		setTAreaStyle(cNameArea, 10, 1, "15");
		
		player.getChildren().addAll(pName, pNameArea);
		character.getChildren().addAll(cName, cNameArea);
		refresh.getChildren().addAll(ref, refArea);
		
		names.getChildren().addAll(player, character, refresh);
		
		return names;
	}
	
	public HBox conceptBuilder(){
		Text hConcept = new Text("High Concept");
		Text trouble = new Text("Trouble");
		HBox conceptContainer = new HBox(20);
		VBox concept = new VBox(5);
		
		
		concept.getChildren().addAll(conceptAreaBuilder(hConcept), conceptAreaBuilder(trouble));
		conceptContainer.getChildren().add(concept);
		
		return conceptContainer;
	}
	
	public VBox conceptAreaBuilder(Text t){
		VBox concepts = new VBox(5);
		TextArea concept = new TextArea("");
		
		setTextStyle(t);
		setTAreaStyle(concept, 20, 1, "16");
		
		concepts.getChildren().addAll(t, concept);
		
		return concepts;
	}
	
	public HBox aspectContainerBuilder(){
		HBox aspectsContainer = new HBox(10);
		VBox container = new VBox(5);
		VBox aspects = new VBox(20);
		Button add = new Button("Add");
		Text t = new Text("Aspects");
		
		setTextStyle(t);
		
		aspects.getChildren().addAll(aspectBuilder(), aspectBuilder(), aspectBuilder());
		
		add.setOnAction(e -> {
			Button rem = new Button("Remove");
			HBox newAspect = addAspectBuilder();
			newAspect.getChildren().addAll(rem);
			aspects.getChildren().add(newAspect);
			rem.setOnAction(ev -> {
				aspects.getChildren().remove(newAspect);
			});
		});
		
		
		container.getChildren().addAll(t, aspects, add);
		aspectsContainer.getChildren().addAll(container);
		
		return aspectsContainer;
	}
	
	public HBox aspectBuilder(){
		TextArea aspectName = new TextArea("");
		TextArea aspectDescription = new TextArea("");
		VBox aspect = new VBox(5);
		HBox contain = new HBox(10);
		
		setTAreaStyle(aspectName, 20, 1, "16", "Aspect...");
		setTAreaStyle(aspectDescription, 20, 5, "14", "Description...");
		
		aspect.getChildren().addAll(aspectName, aspectDescription);
		contain.getChildren().addAll(aspect);
		
		return contain;
	}
	
	public HBox addAspectBuilder(){
		HBox newAspect = new HBox(10);
		VBox aspect = new VBox(5);
		TextArea aspectName = new TextArea("");
		TextArea aspectDescription = new TextArea("");
		
		setTAreaStyle(aspectName, 20, 1, "16", "Aspect...");
		setTAreaStyle(aspectDescription, 20, 5, "14", "Description...");
		
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
	
	//Below are all methods related to the skills/stress tab
	
	public VBox buildSkillList(){
		HBox skillContainer = new HBox(5);
		VBox skillList = new VBox(10);
		VBox skill = new VBox(5);
		Text skillText = new Text("Skills");
		
		//setTextStyle(skillText);
		
		skillText.setStyle("-fx-font-size: 15");
		skillText.setUnderline(true);
		
		skill.getChildren().addAll(skillBuilder(), skillBuilder(), skillBuilder(), skillBuilder(), skillBuilder());
		skillContainer.getChildren().addAll(ratingBuilder(), skill);
		skillList.getChildren().addAll(skillText, skillContainer);
		
		return skillList;
	}
	
	public VBox buildPhysStress(){
		VBox pStress = new VBox(5);
		HBox pStressTicks = new HBox(10);
		Text pText = new Text("Physical Stress");
		
		pText.setStyle("-fx-font-size: 15");
		pText.setUnderline(true);
		
		pStress.getChildren().add(pText);
		pStressTicks.getChildren().addAll(new CheckBox("1"), new CheckBox("2"), new CheckBox("3"), new CheckBox("4"));
		//TODO: set checkboxes 3 and 4 on both mental and physical to disabled and enable them when
		//physique and/or will skill(s) are set to specific profency to unlock new stress boxes
		//below is how to setDisable
		//CheckBox cb = (CheckBox) pStressTicks.getChildren().get(0);
		//cb.setDisable(true);
		
		pStress.getChildren().add(pStressTicks);
		
		return pStress;
	}
	
	public VBox buildMentalStress(){
		VBox mStress = new VBox(5);
		HBox mStressTicks = new HBox(10);
		Text mText = new Text("Mental Stress");
		
		mText.setStyle("-fx-font-size: 15");
		mText.setUnderline(true);
		
		mStress.getChildren().add(mText);
		mStressTicks.getChildren().addAll(new CheckBox("1"), new CheckBox("2"), new CheckBox("3"), new CheckBox("4"));
		//TODO: set checkboxes 3 and 4 on both mental and physical to disabled and enable them when
		//physique and/or will skill(s) are set to specific profency to unlock new stress boxes
		//below is how to setDisable
		//CheckBox cb = (CheckBox) pStressTicks.getChildren().get(0);
		//cb.setDisable(true);
		
		mStress.getChildren().add(mStressTicks);
		
		return mStress;
	}
	
	public HBox buildConsequences(){
		HBox consequences = new HBox(15);
		VBox mainConsequences = new VBox(5);
		VBox extraCons = new VBox(5);
		Text text = new Text("Consequences");
		
		text.setStyle("-fx-font-size: 15");
		text.setUnderline(true);
		
		extraCons.getChildren().addAll(new Text(""), consequenceRow("2", "Mild", true));
		mainConsequences.getChildren().addAll(text, consequenceRow("2", "Mild", false), 
												consequenceRow("4", "Moderate", false), 
												consequenceRow("6", "Severe", false));
		
		consequences.getChildren().addAll(mainConsequences, extraCons);
		
		return consequences;
	}
	
	private HBox consequenceRow(String shift, String prompt, boolean disable){
		HBox consequence = new HBox(5);
		Text text = new Text(shift);
		TextArea ta = new TextArea();
		
		ta.setPromptText(prompt);
		ta.setPrefRowCount(1);
		ta.setPrefColumnCount(20);
		ta.setDisable(disable);
		
		consequence.getChildren().addAll(text, ta);
		
		return consequence;
	}
	
	private HBox skillBuilder(){
		HBox playerSkills = new HBox(10);
		
		playerSkills.getChildren().addAll(buildSkillBox(), buildSkillBox(),
										buildSkillBox(), buildSkillBox(),
										buildSkillBox());
										
		
		
		return playerSkills;
	}
	
	private VBox ratingBuilder(){
		VBox ratings = new VBox(14);
		
		for(int i = 0; i < rating.length; i++){
			ratings.getChildren().add(rating[i]);
		}
		
		return ratings;
	}
	
	private ComboBox buildSkillBox(){
		ComboBox skillBox = new ComboBox(FXCollections.observableArrayList(skills));
		
		return skillBox;
	}
	
	//Below are all functions related to stunts and extras
	
	public VBox buildStuntsPanel(){
		VBox stunts = new VBox(5);
		Text t = new Text("Stunts");
		TextArea ta = new TextArea();
		
		t.setStyle("-fx-font-size: 15");
		t.setUnderline(true);
		
		stunts.getChildren().addAll(t, ta);
		
		return stunts;
		
	}
	
	public VBox buildExtrasPanel(){
		VBox extras = new VBox(5);
		Text t = new Text("Extras");
		TextArea ta = new TextArea();
		
		t.setStyle("-fx-font-size: 15");
		t.setUnderline(true);
		
		extras.getChildren().addAll(t, ta);
		
		return extras;
		
	}
	
}