package design;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.shape.Arc;

public class CircularObstacle extends Obstacle {
	
	private int radius;
	
	public CircularObstacle(double layoutX, double layoutY, int i) {
		setObstacle(i);
		setLayout(layoutX, layoutY);
		id = i;
	}
	
	private void setObstacle(int i) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/design/CircularObstacle.fxml"));
		
		try {
			@SuppressWarnings("unused")
			Parent tempLoader = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FXMLObstacleController controller = loader.getController();					//get the controller
		obstacle = controller.getcircularObstacle(i);								//get obstacle from the controller
		rotate = controller.getTransition();

		//radius = (int) ((Arc) obstacle.getChildren().get(0)).getRadiusX();
	}
	
	
	public int getRadius() {
		return radius;
	}
	
}
