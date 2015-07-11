package tk.adamj57.LMenu;

import java.awt.Point;
import java.util.ArrayList;

import com.rngtng.launchpad.Launchpad;

public class LMenuContainer extends LMenuItem {

	private ArrayList<LMenuItem> items = new ArrayList<LMenuItem>();
	
	public LMenuContainer(Animation animation, Point[] logo) {
		super(animation, logo);
		
	}

	
	public void addItem(LMenuItem item){
		
		items.add(item);

	}
	
	public void addItem(LMenuItem item, int index){
		
		items.add(index, item);

	}
	
	public void removeItem(int index){
		
		items.remove(index);
		
	}
	
	public void removeItem(LMenuItem item){
		
		items.remove(item);
		
	}
	
	public LMenuItem get(int index){
		
		return items.get(index);
		
	}
	
	public ArrayList<LMenuItem> getList(){
		
		return items;
	}
}
