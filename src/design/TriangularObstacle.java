package design;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class TriangularObstacle extends Obstacle{

	public TriangularObstacle(double layoutX, double layoutY) {
		setObstacle();
		setLayout(layoutX, layoutY);
	}
	
	private void setObstacle() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/design/CircularObstacle.fxml"));
		
		try {
			@SuppressWarnings("unused")
			Parent tempLoader = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FXMLObstacleController controller = loader.getController();					//get the controller
		obstacle = controller.gettriangularObstacle();								//get obstacle from the controller
		rotate = controller.getTransition();
	}
}
