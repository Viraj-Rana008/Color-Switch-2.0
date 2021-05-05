package design;

import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ColorSwitch extends ImageView{
	public ColorSwitch( double y) {
		setLayoutX(220);
		setLayoutY(y);
		prefWidth(30);
		prefHeight(30);
		setFitHeight(50);
		setFitWidth(50);
		setSwitch();
	}
	
	private void setSwitch() {
		Image image = new Image("/design/resource/switch.png", 40, 40, false, true);
		setImage(image);
	}
	
	public void fadeAwaySwitch() {
		FadeTransition fade = new FadeTransition(Duration.millis(300), this);
		fade.setFromValue(1);
		fade.setToValue(0);
		fade.setCycleCount(1);
		fade.play();
	}
}
