package screen;

import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class MenuSubScene extends ColorSwitchSubScene {

	public MenuSubScene(int xLayout, int yLayout) {
		super(350, 500, xLayout, yLayout);
		
		isHidden = true;
		addBackButton();
	}
	
	
	@Override
	void addBackButton() {
		Image image = new Image("/screen/resource/p10.png", 40, 40, false, true);
		backIcon.setImage(image);
		backIcon.prefHeight(50);
		backIcon.prefWidth(50);
		backIcon.setLayoutX(mainPane.getWidth()/2 - 20);
		backIcon.setLayoutY(440);
		backIcon.setRotate(270);
		
		backIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				moveSubScene();
			}
		});
		
		setIconEffects();
		mainPane.getChildren().add(backIcon);		
	}
	
	private void setIconEffects() {
		backIcon.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				backIcon.setFitHeight(46);
				backIcon.setLayoutY(backIcon.getLayoutY() + 4);
			}
		});
		
		backIcon.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				backIcon.setFitHeight(50);
				backIcon.setLayoutY(backIcon.getLayoutY() - 4);
			}
		});
	}
	
	public void moveSubScene() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.2));
		transition.setNode(this);
		
		if(isHidden){	transition.setToY(-540);	}
		else 		{	transition.setToY(0);		}
		isHidden = !isHidden;
		transition.play();
	}
}
