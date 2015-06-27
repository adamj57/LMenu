package tk.adamj57.LMenu;

import java.awt.Point;

import com.rngtng.launchpad.Launchpad;

public class LMenuContainer extends LMenu implements LMenuItem {

	private Animation animation;
	private Point[] logo;
	
	public LMenuContainer(Launchpad lp, Animation animation, Point[] logo) {
		super(lp);
		this.animation = animation;
		this.logo = logo;
		
	}

	@Override
	public void launch() {
		display();
	}

	@Override
	public Animation getMenuAnimation() {
		return animation;
	}

	@Override
	public Point[] getLogo() {
		return logo;
	}

}
