package design;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Star extends ImageView{
	
	public Star(double size, double layoutY) {
		super("/design/resource/star.png");
		prefHeight(size);
		prefWidth(size);
		setFitHeight(size+10);
		setFitWidth(size+10);
		setLayoutY(layoutY);
		setLayoutX(220);
	}
	public Star( double layoutY) {
		super("/design/resource/star.png");
		prefHeight(40);
		prefWidth(40);
		setFitHeight(60);
		setFitWidth(60);
		setLayoutY(layoutY);
		setLayoutX(220);
		
	}
	
	public void fadeAwayStar() {
		//System.out.println("Fading !!");
		ParallelTransition parallelTransition = new ParallelTransition();
		Star star = new Star(20, this.getLayoutY());
		FadeTransition fade = new FadeTransition(Duration.millis(300), this);
		fade.setFromValue(1);
		fade.setToValue(0);
		fade.setCycleCount(1);
		
		Path path = new Path();
		path.getElements().add(new MoveTo(this.getLayoutX(),this.getLayoutY()));
		path.getElements().add(new CubicCurveTo(this.getLayoutX()+10, this.getLayoutY()-10, this.getLayoutX()+10, this.getLayoutY()-10, this.getLayoutX()+10, this.getLayoutY()-10));
		PathTransition transition = new PathTransition();
		transition.setDuration(Duration.millis(1));
		transition.setPath(path);
		transition.setNode(star);
		transition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		transition.setCycleCount(1);
		
		parallelTransition.getChildren().addAll(fade, transition);
		parallelTransition.setCycleCount(1);
		parallelTransition.play();
	}
	
}
