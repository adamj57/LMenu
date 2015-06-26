package tk.adamj57.LMenu;

import java.awt.Point;
import java.util.ArrayList;

import tk.adamj57.LChar.LDisplay;

import com.rngtng.launchpad.LButton;
import com.rngtng.launchpad.LColor;
import com.rngtng.launchpad.Launchpad;
import com.rngtng.launchpad.LaunchpadListener;

public class LMenu {
	
	private ArrayList<LMenuItem> menuItems = new ArrayList<LMenuItem>();
	
	private final int LEFT = 0;
	private final int RIGHT = 1;
	
	private Launchpad lp;
	
	LDisplay lDisplay;
	
	boolean displaying = false;
	
	int indexOfDisplayingItem;
	
	LaunchpadListener listener = new LaunchpadListener(){

		@Override
		public void launchpadGridPressed(int x, int y) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void launchpadGridReleased(int x, int y) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void launchpadButtonPressed(int buttonCode) {
			
			switch(buttonCode){
			
			case LButton.LEFT:
				
				scroll(LEFT);
				break;
				
			case LButton.RIGHT:
				
				scroll(RIGHT);
				break;
				
			case LButton.MIXER:
				
				launchItem();
				break;
				
			}
			
		}


		@Override
		public void launchpadButtonReleased(int buttonCode) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void launchpadSceneButtonPressed(int buttonCode) {
			
			switch(buttonCode){
			
			case LButton.SCENE8:
				lp.dispose();
				break;
			}
			
		}

		@Override
		public void launchpadSceneButtonReleased(int buttonCode) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
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
		
		lp.addListener(listener);
		displayItem(0);
		
		displaying = true;
	}
	


	private void launchItem() {
		
		lp.removeListener(listener);
		lDisplay.clear();
		hideControls();
		displaying = false;
		menuItems.get(indexOfDisplayingItem).launch();
		
		display();
		
	}

	private void displayItem(int index){
		
		lDisplay.clear();
		
		Animation animation = menuItems.get(index).getMenuAnimation();
		if(animation.isRaw()){
			
			lDisplay.displayRawAnimation(animation.getAnimation(), animation.getSpeed(), animation.getColor());
		}else{
			
			lDisplay.display(animation.getStringToAnimate(), animation.getSpeed(), animation.getColor());
		}
		
		
		lDisplay.clear();
		
		Point[] logo = menuItems.get(index).getLogo();
		lDisplay.display(logo);
		
		indexOfDisplayingItem = index;
		
		
	}
	private void displayControls(){
		
		lp.changeButton(LButton.LEFT, LColor.GREEN_HIGH);
		lp.changeButton(LButton.RIGHT, LColor.GREEN_HIGH);
		lp.changeButton(LButton.MIXER, LColor.YELLOW_HIGH);
		lp.changeSceneButton(LButton.SCENE8, LColor.HIGH);
		
	}
	
	private void hideControls(){
		
		lp.changeButton(LButton.LEFT, LColor.GREEN_OFF);
		lp.changeButton(LButton.RIGHT, LColor.GREEN_OFF);
		lp.changeButton(LButton.MIXER, LColor.YELLOW_OFF);
		lp.changeSceneButton(LButton.SCENE8, LColor.OFF);
		
	}
	
	private void scroll(int direction){
		
		switch(direction){
			
		case LEFT:
			
			displayItem(indexOfDisplayingItem - 1);
			break;
		
		case RIGHT:
			
			displayItem(indexOfDisplayingItem + 1);
			break;
			
		
		}
		
	}
	
}
