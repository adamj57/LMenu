package tk.adamj57.Launchpad.LMenu;

import com.rngtng.launchpad.LButton;
import com.rngtng.launchpad.LColor;
import com.rngtng.launchpad.Launchpad;
import com.rngtng.launchpad.LaunchpadListener;
import tk.adamj57.Launchpad.LChar.LDisplay;

import java.awt.*;
import java.util.ArrayList;

public class LMenu {

	private final ArrayList<LMenuItem> items = new ArrayList<>();
	
	private final int LEFT = 0;
	private final int RIGHT = 1;
	private final int UP = 2;
	private final int DOWN = 3;


	private final LDisplay lDisplay;

	private final ArrayList<Integer> indexOfCurrentItem = new ArrayList<>();

	private final LaunchpadListener listener = new LaunchpadListener() {

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
				
				getLp().changeButton(LButton.LEFT, LColor.GREEN_LOW);
				break;
				
			case LButton.RIGHT:
				
				getLp().changeButton(LButton.RIGHT, LColor.GREEN_LOW);
				break;
				
			case LButton.MIXER:
				
				getLp().changeButton(LButton.MIXER, LColor.YELLOW_LOW);
				break;
				
			case LButton.DOWN:
				
				getLp().changeButton(LButton.DOWN, LColor.LOW);
				break;
				
			}
			
		}


		@Override
		public void launchpadButtonReleased(int buttonCode) {
			
			switch(buttonCode){
			
			case LButton.LEFT:
			
				getLp().changeButton(LButton.LEFT, LColor.GREEN_HIGH);
				scroll(LEFT);
				break;
				
			case LButton.RIGHT:
				
				getLp().changeButton(LButton.RIGHT, LColor.GREEN_HIGH);
				scroll(RIGHT);
				break;
				
			case LButton.MIXER:
				getLp().changeButton(LButton.MIXER, LColor.YELLOW_HIGH);
				selectItem();
				break;
				
			case LButton.DOWN:
				
				getLp().changeButton(LButton.DOWN, LColor.HIGH);
				changeDir(DOWN);
				break;
				
			}
				
		}
			
		

		@Override
		public void launchpadSceneButtonPressed(int buttonCode) {
			
			switch(buttonCode){
			
			case LButton.SCENE8:
				
				getLp().changeSceneButton(LButton.SCENE8, LColor.RED_LOW);
				break;
			}
			
		}

		@Override
		public void launchpadSceneButtonReleased(int buttonCode) {
			
			switch(buttonCode){
			
			case LButton.SCENE8:
				
				getLp().changeSceneButton(LButton.SCENE8, LColor.RED_HIGH);
				//noinspection Convert2Lambda
				new Thread(() -> {

					getLp().dispose();

				}).start();
				
				break;
			}
			
		}
		
	};
	
	public LMenu(Launchpad lp){

		indexOfCurrentItem.add(0);
		lDisplay = new LDisplay(lp);
		
	}
	
	public Launchpad getLp() {
		
		return lDisplay.getLp();
	}

	public void setLp(Launchpad lp) {
		
		lDisplay.setLp(lp);
	}

	public void addItem(LMenuItem item){
		
		items.add(item);
	}
	
	public void addItem(LMenuItem item, int pos){
		
		items.add(pos, item);
	}
	
	public void removeItem(LMenuItem item){
		
		items.remove(item);
		
	}
	
	public void removeItem(int pos){
		
		items.remove(pos);
		
	}
	
	public LMenuItem get(int index){
		
		return items.get(index);
	}
	
	public void run(){
		
		//TODO display
		showControls();
		displayItem(0);
	}
	


	private void launch() {
		
		lDisplay.clear();
		hideControls();
		int size = indexOfCurrentItem.size();
		LMenuItem toLaunch = items.get(indexOfCurrentItem.get(0));
		
			
		for(int i = 1; i < size; i++){
				
				toLaunch = ((LMenuContainer)toLaunch).get(indexOfCurrentItem.get(i));
				
		}	
			
		((Launchable) toLaunch).launch();
		showControls();
		displayItem(indexOfCurrentItem.get(indexOfCurrentItem.size()-1));
		
	}
	
	private void changeDir(int direction){
		
		switch(direction) {
	
			
			case UP:

				indexOfCurrentItem.add(0);
				
				displayItem(0);
				
				break;
				
			case DOWN:
				
				if(indexOfCurrentItem.size() > 1){
					
					indexOfCurrentItem.remove(indexOfCurrentItem.size() - 1);
					
					displayItem(indexOfCurrentItem.get(indexOfCurrentItem.size() - 1));
					
				}
				
				
				break;
				
		}
				
				
	}

	private void displayItem(int index){
		
		
		try{
			
		
		int size = indexOfCurrentItem.size();
		
		LMenuItem toSelect = items.get(indexOfCurrentItem.get(0));
		
		for(int i = 1; i < size; i++){
			
			toSelect = ((LMenuContainer)(toSelect)).get(indexOfCurrentItem.get(i));
			
			
		}
		
		lDisplay.clear();
		
		Animation animation = toSelect.getAnimation();
		if(animation.isRaw()){
			
			lDisplay.displayRawAnimation(animation.getAnimation(), animation.getSpeed(), animation.getColor());
		}else{
			
			lDisplay.display(animation.getStringToAnimate(), animation.getSpeed(), animation.getColor());
		}
		
		
		lDisplay.clear();
		
		Point[] logo = toSelect.getLogo();
		lDisplay.display(logo);
		
		indexOfCurrentItem.set(indexOfCurrentItem.size() - 1, index);
		
		}catch(IndexOutOfBoundsException e){
			
			System.err.println("Error: Index out of bounds. Can't show " + index + "of that list, it don't exist.");
		}
		
	}
	private void showControls(){
		
		getLp().addListener(listener);
		getLp().changeButton(LButton.LEFT, LColor.GREEN_HIGH);
		getLp().changeButton(LButton.RIGHT, LColor.GREEN_HIGH);
		getLp().changeButton(LButton.MIXER, LColor.YELLOW_HIGH);
		getLp().changeSceneButton(LButton.SCENE8, LColor.HIGH);
		
	}
	
	private void hideControls(){
		
		getLp().removeListener(listener);
		getLp().changeButton(LButton.LEFT, LColor.GREEN_OFF);
		getLp().changeButton(LButton.RIGHT, LColor.GREEN_OFF);
		getLp().changeButton(LButton.MIXER, LColor.YELLOW_OFF);
		getLp().changeSceneButton(LButton.SCENE8, LColor.OFF);
		
	}
	
	private void scroll(int direction){
		
		switch(direction){
			
		case LEFT:
			if(indexOfCurrentItem.get(indexOfCurrentItem.size()-1) - 1 >= 0){
			displayItem(indexOfCurrentItem.get(indexOfCurrentItem.size()-1) - 1);
			}
			break;
		
		case RIGHT:
			
			displayItem(indexOfCurrentItem.get(indexOfCurrentItem.size()-1) + 1);
			break;
			
		
		}
		
	}

	private void selectItem() {
		int size = indexOfCurrentItem.size();
		LMenuItem toLaunch = items.get(indexOfCurrentItem.get(0));
		
			
		for(int i = 1; i < size; i++){
				
				toLaunch = ((LMenuContainer)toLaunch).get(indexOfCurrentItem.get(i));
				
		}	
		
		if(toLaunch instanceof LMenuContainer){
			
			changeDir(UP);
			
		}else{
			
			if(toLaunch instanceof Launchable){
				
				launch();
				
			}
		}
	}

	
}
