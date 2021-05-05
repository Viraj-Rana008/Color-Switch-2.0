package design;

import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class LinearObstacle extends Obstacle{
	
	private Line[] line;
	private ArrayList<Line> lineList;
	
	public LinearObstacle() {
		setObstacle();
		setTransition();
	}
	
	private void setObstacle() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/design/LinearObstacles.fxml"));
		
		try {
			@SuppressWarnings("unused")
			Parent tempLoader = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FXMLObstacleController controller = loader.getController();					//get the controller
		
		line = controller.getLinearObstacle();								//get obstacle from the controller
		lineList = new ArrayList<Line>();
		obstacle = new Group();
		arrangeLines();
	}
	
	private void arrangeLines() {
		int i;
		Line l = new Line(0,0,300,0);
		l.setStroke(Paint.valueOf("#f4de0e"));
		l.setStrokeWidth(20);
		lineList.add(l);
		for(i=0;i<4;i++)
			lineList.add(line[i]);
		
		//provide hard-coded layout here
		for(i=0;i<lineList.size(); i++) {
			lineList.get(i).setLayoutY(100);
			//lineList.get(i).setEndX(300);
			lineList.get(i).toBack();
		}
		
		lineList.get(0).setLayoutX(-175);
		lineList.get(1).setLayoutX(-75);
		lineList.get(2).setLayoutX(100);
		lineList.get(3).setLayoutX(225);
		lineList.get(4).setLayoutX(350);
		
		for(i=0; i<lineList.size(); i++)
			obstacle.getChildren().add(lineList.get(i));
		
	}
	
	private void setTransition() {	
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		//timeline.setAutoReverse(true);
		
		Duration duration = Duration.millis(50);
		
		KeyFrame keyframe = new KeyFrame(duration, e -> setMotion());
		
		timeline.getKeyFrames().add(keyframe);
			
		timeline.play();
	}
	
	private void setMotion() {
		for(int i=0; i<obstacle.getChildren().size(); i++) {
			obstacle.getChildren().get(i).setLayoutX(obstacle.getChildren().get(i).getLayoutX() + 4);
			if(obstacle.getChildren().get(i).getLayoutX() > 565)
				obstacle.getChildren().get(i).setLayoutX(-100);
		}
	}
	
	

}
