package tk.adamj57.LMenu;

import java.awt.Point;
import java.util.ArrayList;

import tk.adamj57.LChar.LDisplay;

import com.rngtng.launchpad.LButton;
import com.rngtng.launchpad.LColor;
import com.rngtng.launchpad.Launchpad;
import com.rngtng.launchpad.LaunchpadListener;

public class LMenu {
	
	private ArrayList<LMenuItem> items = new ArrayList<LMenuItem>();
	
	private final int LEFT = 0;
	private final int RIGHT = 1;
	private final int UP = 2;
	private final int DOWN = 3;
	
	
	private LDisplay lDisplay;
	
	private ArrayList<Integer> indexOfCurrentItem = new ArrayList<Integer>();
	
	private LaunchpadListener listener = new LaunchpadListener(){

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
				new Thread(new Runnable(){

					@Override
					public void run() {
						
						getLp().dispose();
						
					}
					
				}).start();
				
				break;
			}
			
		}
		
	};
	
	public LMenu(Launchpad lp){
		
		indexOfCurrentItem.add(new Integer(0));
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
				
				indexOfCurrentItem.add(new Integer(0));
				
				displayItem(0);
				
				break;
				
			case DOWN:
				
				indexOfCurrentItem.remove(indexOfCurrentItem.size() - 1);
				
				displayItem(indexOfCurrentItem.get(indexOfCurrentItem.size() - 1));
				
				break;
				
		}
				
				
	}

	private void displayItem(int index){
		
		lDisplay.clear();
		
		int size = indexOfCurrentItem.size();
		
		
		LMenuItem toSelect = items.get(index);
		if(size == 1){
			
			toSelect = items.get(index);
			
		}else{
			ArrayList<LMenuItem> list = items;
			for(int i = 1; i < size - 1; i++){
				
				list = ((LMenuContainer) list.get(indexOfCurrentItem.get(i))).getList();
				
			}	
			
			toSelect = ((LMenuContainer)toSelect).get(index);
		}
		
		
		
		
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
			
			displayItem(indexOfCurrentItem.get(indexOfCurrentItem.size()-1) - 1);
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
				
			}else{
				
				//Do nothing!!!
			}
			
		}
	}

	
}
