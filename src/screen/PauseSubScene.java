package screen;

import com.sun.javafx.effect.EffectDirtyBits;

import design.Player;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PauseSubScene extends ColorSwitchSubScene{

	private Label pauseLabel;
	private ImageView resumeIcon;
	private ImageView replayIcon;
	private ImageView exitIcon;
	
	public PauseSubScene() {
		super(350, 500, 75, -600);
		isHidden = true;
		this.setBlendMode(BlendMode.SRC_OVER);
		this.setFill(Color.LIGHTGRAY);		
		
		addBackButton();
		addPauseLabel();
		addButtons();
	}

	@Override
	public void moveSubScene() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.2));
		transition.setNode(this);
		update();
		
		if(isHidden){	transition.setToY(750);	}
		else 		{	transition.setToY(-600);		}
		isHidden = !isHidden;
		transition.play();
	}

	@Override
	void addBackButton() {
		Image image = new Image("/screen/resource/backIcon1.png", 40, 40, false, true);
		backIcon.setImage(image);
		backIcon.prefHeight(50);
		backIcon.prefWidth(50);
		backIcon.setLayoutX(mainPane.getWidth()/2 - 20);
		backIcon.setLayoutY(440);
		backIcon.setRotate(90);
		
		backIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				moveSubScene();
			}
		});
		
		mainPane.getChildren().add(backIcon);
	}
	
	private void update() {
		int score = ((Player)(((AnchorPane)this.getParent()).getChildren().get(0))).getScore();
		pauseLabel.setText("SCORE\n" + score);
	}
	private void addPauseLabel() {
		pauseLabel = new Label("SCORE\n"+ 0);
		pauseLabel.setPrefHeight(100);
		pauseLabel.setPrefWidth(150);
		pauseLabel.setTextAlignment(TextAlignment.CENTER);
		
		pauseLabel.setFont(Font.font("Cooper Black", 40));
		pauseLabel.setTextFill(Color.CORNFLOWERBLUE);
		pauseLabel.setTranslateX(110);
		mainPane.getChildren().add(pauseLabel);
	}
	
	private void addButtons() {
		resumeIcon = new ImageView();
		exitIcon = new ImageView();
		replayIcon = new ImageView();
		ImageView decorate1 = new ImageView();
		ImageView decorate2 = new ImageView();
		
		Image resumeImage = new Image("/screen/resource/resumeIcon.png");
		Image deco = new Image("/screen/resource/p5.png");
		Image replayImage = new Image("/screen/resource/replay.png",0,0,false, true);
		Image exitImage = new Image("/screen/resource/exitIcon1.png", 0,0,false, true);
		
		decorate1.setImage(deco);
		decorate2.setImage(deco);
		decorate1.prefHeight(90);
		decorate1.prefWidth(90);
		decorate1.setLayoutX(165);
		decorate1.setLayoutY(400);
		decorate2.prefHeight(90);
		decorate2.prefWidth(90);
		decorate2.setLayoutX(-145);
		decorate2.setLayoutY(400);
		decorate2.setRotate(180);
		
		resumeIcon.setImage(resumeImage);
		resumeIcon.setEffect(new Lighting());
		resumeIcon.prefHeight(80);
		resumeIcon.prefWidth(80);
		resumeIcon.setLayoutX(30);
		resumeIcon.setLayoutY(180);
		
		replayIcon.setImage(replayImage);
		replayIcon.setEffect(new Lighting());
		replayIcon.setFitHeight(80);
		replayIcon.setFitWidth(80);
		replayIcon.setLayoutX(30);
		replayIcon.setLayoutY(310);
		
		exitIcon.setImage(exitImage);
		exitIcon.setEffect(new Lighting());
		exitIcon.setFitHeight(60);
		exitIcon.setFitWidth(60);
		exitIcon.setLayoutX(230);
		exitIcon.setLayoutY(320);
		
		setIconListner();
		mainPane.getChildren().addAll(resumeIcon, replayIcon, exitIcon, decorate1, decorate2);
	}
	
	private void setIconListner() {
		resumeIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				moveSubScene();
				((Player)(((AnchorPane)getParent()).getChildren().get(0))).playTransition();
			}
		});
		
		replayIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				moveSubScene();
				
				((Stage) getScene().getWindow()).close();
				GameScreenManager gameScreen = new GameScreenManager("Player");
				gameScreen.startNewGame((Stage) getScene().getWindow());
			}
		});
		
		exitIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				((Stage) getScene().getWindow()).close();				//add saving machenism
			}
		});
	}

}
