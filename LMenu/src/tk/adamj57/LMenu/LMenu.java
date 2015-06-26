package tk.adamj57.LMenu;

import java.awt.Point;
import java.util.ArrayList;

import tk.adamj57.LChar.LDisplay;

import com.rngtng.launchpad.LButton;
import com.rngtng.launchpad.LColor;
import com.rngtng.launchpad.Launchpad;

public class LMenu {
	
	private ArrayList<LMenuItem> menuItems = new ArrayList<LMenuItem>();
	
	private final int LEFT = 0;
	private final int RIGHT = 1;
	
	private Launchpad lp;
	
	LDisplay lDisplay;
	
	boolean displaying = false;
	
	public LMenu(Launchpad lp){
		
		this.setLp(lp);
		
		lDisplay = new LDisplay(lp);
		
	}
	
	public Launchpad getLp() {
		
		return lp;
	}

	public void setLp(Launchpad lp) {
		
		this.lp = lp;
	}

	public LMenu addItem(LMenuItem item){
		
		menuItems.add(item);
		return this;
	}
	
	public LMenu addItem(LMenuItem item, int pos){
		
		menuItems.add(pos, item);
		return this;
	}
	
	public void removeItem(LMenuItem item){
		
		menuItems.remove(item);
		
	}
	
	public void removeItem(int pos){
		
		menuItems.remove(pos);
		
	}
	
	public void display(){
		
		//TODO display
		displayControls();
		//Tu dodam listener do controlsów
		Point[][] animation = menuItems.get(0).getMenuAnimation();
		lDisplay.display(animation);
		
		lDisplay.clear();
		
		Point[] logo = menuItems.get(0).getLogo();
		lDisplay.display(logo);
		
		displaying = true;
		
	}
	
	private void displayControls(){
		
		lp.changeSceneButton(LButton.LEFT, LColor.GREEN_HIGH);
		lp.changeSceneButton(LButton.RIGHT, LColor.GREEN_HIGH);
		lp.changeButton(LButton.SCENE1, LColor.RED_HIGH);
		
	}
	
	private void scroll(int direction){
		
		//TODO scroll
	}
	
}
