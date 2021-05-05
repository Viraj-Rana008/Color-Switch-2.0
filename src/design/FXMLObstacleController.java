package design;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class FXMLObstacleController implements Initializable {
		
	private RotateTransition rotateTransition;
	@FXML
	private Group ob1;					//thin circle
	@FXML
	private Group ob2;					//triangle
	@FXML
	private Group ob3;					//thick circle
	@FXML
	private Group ob4;
	@FXML
	private Group ob41;
	@FXML
	private Group ob5;	
	@FXML
	private Line purpleLine;
	@FXML
	private Line yellowLine;
	@FXML
	private Line pinkLine;
	@FXML
	private Line blueLine;
	@FXML
	private Group w1;
	@FXML
	private Group w2;
	@FXML
	private Group w3;
	
	private void rotateObstacle(Group group, boolean reverse, double rate, double ang) {
		RotateTransition rotate = new RotateTransition(Duration.seconds(5), group);
		rotate.setAutoReverse(reverse);
		rotate.setRate(rate);
		rotate.setByAngle(359 * ang);					//ang == -1 for clockwise			ang= 1 for anti-clock
		rotate.setCycleCount(Timeline.INDEFINITE);
		rotate.play();
		rotateTransition = rotate;
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rotateObstacle(ob1, false, 1, -1);
		rotateObstacle(ob2, false, 2, -1);
		rotateObstacle(ob3, false, 2.2, -1);
		rotateObstacle(ob4, false, 1, -1);
		rotateObstacle(ob5, false, 2, -1);
		rotateObstacle(ob41, false, 1, -1);
		rotateObstacle(w1, false, 2, 1);
		rotateObstacle(w2, false, 2, -1);
		rotateObstacle(w3, false, 2, 1);
	}
	
	public RotateTransition getTransition() {
		return rotateTransition;
	}
	
	public Group getcircularObstacle(int i) {
		switch (i) {
		case 1:
			return ob1;		
		case 2:
			return ob2;
		case 3:
			return ob3;
		case 4:
			return ob4;
		case 5:
			return ob5;
		case 41:
			return ob41;
		case 11:
			return w1;
		case 12:
			return w2;
		case 13:
			return w3;
		default:
			break;
		}
		return ob1;
	}
	
//	public Group gettriangularObstacle() {
//		return ob2;
//	}
	
	public Line[] getLinearObstacle() {
		Line[] line = new Line[4];	
		line[0] = purpleLine;
		line[1] = yellowLine;
		line[2] = pinkLine;
		line[3] = blueLine;
		
		return line;
	}
	
}
