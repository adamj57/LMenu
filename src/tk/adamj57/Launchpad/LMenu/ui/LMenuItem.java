package tk.adamj57.Launchpad.LMenu.ui;

import tk.adamj57.Launchpad.LMenu.Animation;

import java.awt.*;

public class LMenuItem {

	private Animation animation;
	private Point[] logo;
	
	
	public LMenuItem(Animation animation, Point[] logo){
		this.animation = animation;
		this.logo = logo;
	}
	
	public Animation getAnimation(){
		return animation;
	}
	
	public void setAnimation(Animation animation){
		this.animation = animation;
	}

    public Point[] getLogo() {
        return logo;
    }

    public void setLogo(Point[] logo){
		this.logo = logo;
	}

}
	
