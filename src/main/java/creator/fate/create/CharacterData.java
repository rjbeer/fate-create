package creator.fate.create;

import java.util.ArrayList;

class CharacterData{
	//boolean load = false;
	private int aspectCount = 3;
	private String playerName = "";
	private String characterName = "";
	private String refresh = "";
	private String highConcept = "";
	private String trouble = "";
	private ArrayList<String> aspects = new ArrayList<String>();
	private ArrayList<String> descriptions = new ArrayList<String>();
	private String[][] skills = new String[5][5];
	private String[] consequences = {"", "", "", ""};
	
	
	public CharacterData(){
		for(int i = 0; i < skills.length; i++){
			for(int j = 0; j < skills[i].length; j++){
				skills[i][j] = "";
			}
		}
		
		for(int i = 0; i < aspectCount; i++){
			aspects.add("");
			descriptions.add("");
		}
		
		aspects.set(0, "test aspect");
		
	}
	
	public void setPName(String pName){
		playerName = pName;
	}
	
	public String getPName(){
		return playerName;
	}
	
	public void setCName(String cName){
		characterName = cName;
	}
	
	public String getCName(){
		return characterName;
	}
	
	public void setRefresh(String ref){
		refresh = ref;
	}
	
	public String getRefresh(){
		return refresh;
	}
	
	public void setHConcept(String hc){
		highConcept = hc;
	}
	
	public String getHConcept(){
		return highConcept;
	}
	
	public void setTrouble(String tr){
		trouble = tr;
	}
	
	public String getTrouble(){
		return trouble;
	}
	
	public void updateCount(){
		aspectCount++;
	}
	
	public int getCount(){
		return aspectCount;
	}
	
	public void addAspect(String aspect){
		aspects.add(aspect);
		aspectCount++;
	}
	
	public void addAspect(){
		aspects.add("");
		aspectCount++;
	}
	
	public void setAspect(String aspect, int i){
		aspects.set(i, aspect);
	}
	
	public ArrayList<String> getAspects(){
		return aspects;
	}
	
	public String getAspect(int i){
		return aspects.get(i);
	}
	
	public void removeAspect(int i){
		aspects.remove(i);
		aspectCount--;
	}
	
	public void addDescription(String description){
		descriptions.add(description);
	}
	
	public void setDescription(String description, int i){
		descriptions.set(i, description);
	}
	
	
	
	public void removeDescription(int i){
		descriptions.remove(i);
	}
	
	public ArrayList<String> getDescriptions(){
		return descriptions;
	}
	
	public String getDescription(int i){
		return descriptions.get(i);
	}
	
	public void setSkill(int i, int j, String s){
		skills[i][j] = s;
	}
	
	public boolean checkFor(int i, String s){
		for(int j = 0; j < 5; j++){
			if(skills[i][j].equals(s)){
				return true;
			}
		}
		return false;
	}
	
	public String getSkill(int i, int j){
		return skills[i][j];
	}
}