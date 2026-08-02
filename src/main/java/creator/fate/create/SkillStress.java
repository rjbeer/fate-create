package creator.fate.create;

import java.util.Arrays;

import javafx.scene.control.Tab;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.Node;
import javafx.collections.FXCollections;

class SkillStress{
	private String[] skills = {"", "Athletics", "Burglary", "Contacts", "Crafts", 
								"Deceive", "Drive", "Empathy", "Fight", 
								"Investigate", "Lore", "Notice", "Physique", 
								"Provoke", "Rapport", "Resources", "Shoot",
								"Stealth", "Will"};
	private Text[] rating = {new Text("Superb +5"), new Text("Great +4"), 
							new Text("Good +3"), new Text("Fair +2"), new Text("Average +1")};
	private CheckBox[] phStress = {new CheckBox("1"), new CheckBox("2"), new CheckBox("3"), new CheckBox("4")};
	private CheckBox[] meStress = {new CheckBox("1"), new CheckBox("2"), new CheckBox("3"), new CheckBox("4")};
	private TextArea[] consequence = {new TextArea(""), new TextArea(""), new TextArea(""), new TextArea("")};
	private CharacterData cdat;
	//private TextArea[] consequence = {};
	
	public SkillStress(CharacterData cd){
		cdat = cd;
	}
	
	public Tab buildSkillStress(){
		Tab skillStress = new Tab("Skills/Stress");
		
		skillStress.setContent(skillsAndStress());
		skillStress.setClosable(false);
		
		return skillStress;
	}
	
	private ScrollPane skillsAndStress(){
		ScrollPane sp = new ScrollPane();
		VBox vb = new VBox(10);
		VBox physStress = new VBox();
		VBox mentStress = new VBox();
		Button test = new Button("Test");
		
		test.setOnAction(e -> {
			for(int i = 0; i < 5; i++){
				System.out.println("Skill + " + (i + 1));
				for(int j = 0; j < 5; j++){
					System.out.println(cdat.getSkill(i, j));
				}
			}
		});
		
		physStress.getChildren().add(buildPhysStress());
		mentStress.getChildren().add(buildMentalStress());
		
		vb.getChildren().addAll(buildSkillList(physStress, mentStress), physStress, mentStress, test);
		sp.setContent(vb);
		
		return sp;
	}
	
	private VBox buildSkillList(VBox p, VBox m){
		VBox skillContainer = new VBox(5);
		VBox skills = new VBox(5);
		HBox rateAndSkill = new HBox(5);
		Text skillText = new Text("Skills");
		
		skillText.setStyle("-fx-font-size: 15");
		skillText.setUnderline(true);
		
		skills.getChildren().addAll(buildSkills());
		rateAndSkill.getChildren().addAll(ratingSet(), skills);
		skillContainer.getChildren().addAll(skillText, rateAndSkill);
		
		return skillContainer;
	}
	
	//TODO: after setting up setSuperbBehavior() make sure to properly think through and implement
	//		the skills section. Perhaps it is a collection of VBoxes. As it stands the skills HBox
	//		houses only one line of skills max.
	private HBox buildSkills(){
		HBox skills = new HBox(10);
		
		skills.getChildren().addAll(makeSkill(3, 0), makeSkill(1, 0));
		
		return skills;
	}
	
	private ComboBox makeSkill(int i, int j){
		ComboBox skillBox = new ComboBox(FXCollections.observableArrayList(skills));
		
		switch(i){
			case 0:
			case 1:{
				setAveFairSkillBehavior(skillBox, i, j);
				break;
			}
			case 2:
			case 3:{
				setGoodGreatBehavior(skillBox, i, j);
				break;
			}
			case 4:
				setSuperbBehavior(skillBox, i, j);
				break;
		};
		
		return skillBox;
	}
	
	private void setAveFairSkillBehavior(ComboBox cb, int i, int j){
		cb.getSelectionModel().selectedItemProperty().addListener((obs, old, nu)->{
			cdat.setSkill(i, j, (String)nu);
					
			if( nu != null && nu.equals("Physique") ){
				phStress[2].setDisable(false);
			}else if(old !=null && old.equals("Physique")){
				if(cdat.checkFor(2, (String)old) || cdat.checkFor(3, (String)old)){
					return;
				}
				if(!(cdat.checkFor(0, (String)old)) && !(cdat.checkFor(1, (String)old))){
					phStress[2].setDisable(true);
					phStress[2].setSelected(false);
				}
			}if( nu != null && nu.equals("Will") ){
				meStress[2].setDisable(false);
			}else if(old !=null && old.equals("Will")){
				if(cdat.checkFor(2, (String)old) || cdat.checkFor(3, (String)old)){
					return;
				}
				if(!(cdat.checkFor(0, (String)old)) && !(cdat.checkFor(1, (String)old))){
					meStress[2].setDisable(true);
					meStress[2].setSelected(false);
				}
			}
		});
	}
	
	private void setGoodGreatBehavior(ComboBox cb, int i, int j){
		cb.getSelectionModel().selectedItemProperty().addListener((obs, old, nu)->{
			cdat.setSkill(i, j, (String)nu);
			if(nu != null && nu.equals("Physique")){
				phStress[2].setDisable(false);
				phStress[3].setDisable(false);
			}else if(old != null && old.equals("Physique")){
				if(!(cdat.checkFor(2, (String)old)) && !(cdat.checkFor(3, (String)old))){
					phStress[3].setDisable(true);
					phStress[3].setSelected(false);
				}
				if(!(cdat.checkFor(0, (String)old)) && !(cdat.checkFor(1, (String)old))){
					phStress[2].setDisable(true);
					phStress[2].setSelected(false);
				}
			}if(nu != null && nu.equals("Will")){
				meStress[2].setDisable(false);
				meStress[3].setDisable(false);
			}else if(old != null && old.equals("Will")){
				if(!(cdat.checkFor(2, (String)old)) && !(cdat.checkFor(3, (String)old))){
					meStress[3].setDisable(true);
					meStress[3].setSelected(false);
				}
				if(!(cdat.checkFor(0, (String)old)) && !(cdat.checkFor(1, (String)old))){
					meStress[2].setDisable(true);
					meStress[2].setSelected(false);
				}
			}
		});
	}
	
	//TODO: finish setSuperbBehavior()
	private void setSuperbBehavior(ComboBox cb, int i, int j){
		
	}
	
	private VBox ratingSet(){
		VBox ratings = new VBox(14);
		
		for(int i = 0; i < rating.length; i++){
			ratings.getChildren().addAll(rating[i]);
		}
		
		return ratings;
	}
	
	private VBox buildMentalStress(){
		VBox mStress = new VBox(5);
		HBox mStressTicks = new HBox(10);
		Text mText = new Text("Mental Stress");
		Node node;
		
		mText.setStyle("-fx-font-size: 15");
		mText.setUnderline(true);
		
		//mStressTicks.getChildren().addAll(new CheckBox("1"), new CheckBox("2"), new CheckBox("3"), new CheckBox("4"));
		
		for(int i = 0; i < meStress.length; i++){
			mStressTicks.getChildren().add(meStress[i]);
		}
		
		meStress[2].setDisable(true);
		meStress[3].setDisable(true);
		
		mStress.getChildren().addAll(mText, mStressTicks);
		
		return mStress;
	}
	
	public VBox buildPhysStress(){
		VBox pStress = new VBox(5);
		HBox pStressTicks = new HBox(10);
		Text pText = new Text("Physical Stress");
		Node node;
		
		pText.setStyle("-fx-font-size: 15");
		pText.setUnderline(true);
		
		for(int i = 0; i < phStress.length; i++){
			pStressTicks.getChildren().add(phStress[i]);
		}
		
		phStress[2].setDisable(true);
		phStress[3].setDisable(true);
		
		pStress.getChildren().addAll(pText, pStressTicks);
		
		return pStress;
	}
}