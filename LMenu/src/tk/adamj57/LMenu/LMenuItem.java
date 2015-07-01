package tk.adamj57.LMenu;

import java.awt.Point;

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
	
	public Point[] getLogo(){
		return logo;
	}
	
	public void setAnimation(Animation animation){
		this.animation = animation;
	}
	
	public void setLogo(Point[] logo){
		
		this.logo = logo;
	}

}
	
