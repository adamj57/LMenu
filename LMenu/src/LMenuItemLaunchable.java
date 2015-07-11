import java.awt.Point;

import tk.adamj57.LMenu.Animation;
import tk.adamj57.LMenu.LMenuItem;
import tk.adamj57.LMenu.Launchable;


public class LMenuItemLaunchable extends LMenuItem implements Launchable {

	public LMenuItemLaunchable(Animation animation, Point[] logo) {
		super(animation, logo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void launch() {
		System.out.println("Running!");

	}

}
