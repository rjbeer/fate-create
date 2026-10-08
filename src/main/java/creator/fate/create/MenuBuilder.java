package creator.fate.create;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

class MenuBuilder{
	
	MenuBar mb = new MenuBar();
	
	public MenuBuilder(){
		Menu file = new Menu("File");
		MenuItem save = new MenuItem("Save");
		MenuItem saveAs = new MenuItem("Save As...");
		MenuItem newChar = new MenuItem("New Character");
		MenuItem open = new MenuItem("Open...");
		MenuItem exit = new MenuItem("Exit");
		
		file.getItems().addAll(newChar, open, save, saveAs, exit);
		
		exit.setOnAction(e -> {
			System.exit(0);
		});
		
		//file.getItems().addAll(newChar, save, saveAs, exit);
		mb.getMenus().add(file);
	}
	
	public MenuBar getMenu(){
		return mb;
	}
	
	
}