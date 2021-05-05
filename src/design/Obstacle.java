package design;


import javafx.animation.RotateTransition;
import javafx.scene.Group;

public abstract class Obstacle{
	protected Group obstacle;
	protected RotateTransition rotate;
	protected int id;
	
	public Obstacle() {	}

	public Group getNode() {
		return obstacle;
	}
	
	public int getId() {
		return id;
	}
	
	protected void setLayout(double x, double y) {
		obstacle.setLayoutX(x);
		obstacle.setLayoutY(y);
	}
	
	public void pauseRotation() {
		rotate.pause();
	}
	
	public void startRoatation() {
		rotate.play();
	}
}
