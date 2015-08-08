package tk.adamj57.Launchpad.LMenu;

import java.awt.Point;

import com.rngtng.launchpad.LColor;

import tk.adamj57.Launchpad.LChar.LChar;

public class Animation {

	private Point[][] animation = null;
	private LChar[] stringToAnimate = null;
	private long speed;
	private int color;
	
	public Animation(Point[][] animation){
		
		this.setAnimation(animation);
		this.setSpeed(100);
		this.setColor(LColor.HIGH);
	}
	
	public Animation(Point[][] animation, long speed){
		
		this.setAnimation(animation);
		this.setSpeed(speed);
		this.setColor(LColor.HIGH);
	}
	
	public Animation(Point[][] animation, int color){
		
		this.setAnimation(animation);
		this.setColor(color);
		this.setSpeed(100);
	}
	
	public Animation(Point[][] animation, long speed, int color){
		
		this.setAnimation(animation);
		this.setColor(color);
		this.setSpeed(speed);
	}
	
	public Animation(LChar[] stringToAnimate){
		
		this.setStringToAnimate(stringToAnimate);
		this.setSpeed(100);
		this.setColor(LColor.HIGH);
	}
	
	public Animation(LChar[] stringToAnimate, long speed){
		
		this.setStringToAnimate(stringToAnimate);
		this.setSpeed(speed);
		this.setColor(LColor.HIGH);
	}
	
	public Animation(LChar[] stringToAnimate, int color){
		
		this.setStringToAnimate(stringToAnimate);
		this.setColor(color);
		this.setSpeed(100);
	}
	

	public Animation(LChar[] stringToAnimate, long speed, int color){
		
		this.setStringToAnimate(stringToAnimate);
		this.setColor(color);
		this.setSpeed(speed);
	}

	public Point[][] getAnimation() {
		return animation;
	}

	public void setAnimation(Point[][] animation) {
		this.animation = animation;
	}

	public long getSpeed() {
		return speed;
	}

	public void setSpeed(long speed) {
		this.speed = speed;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public LChar[] getStringToAnimate() {
		return stringToAnimate;
	}

	public void setStringToAnimate(LChar[] stringToAnimate) {
		this.stringToAnimate = stringToAnimate;
	}
	
	public boolean isRaw(){
		
		if(animation == null){
			return false;
		}else{
			return true;
		}
	}
}
