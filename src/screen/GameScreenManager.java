package screen;

import java.util.ArrayList;
import java.util.List;

import design.CircularObstacle;
import design.ColorSwitch;
import design.LinearObstacle;
import design.Obstacle;
import design.Player;
import design.Star;
import design.TriangularObstacle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameScreenManager {
	private final static int WIDTH = 500;
	private final static int HEIGHT = 750;
	private final static String backGroundColor =  "212121";
	
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	private Stage menuStage;
	
	private Player ball;
	private List<Star> starList;
	private List<Obstacle> obstacleList ;
	private List<ColorSwitch> switchList;
	private static ImageView handy;
	private Label scoreLabel;
	
	private PauseSubScene pauseSubScene; 
	
	
	public GameScreenManager(String playerName) {
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		
		ball = new Player(playerName);
		mainPane.getChildren().add(ball);
		createPauseIcon();
		createObstacle();
		checkCollision();
		addStar();
		addSwitch();
		addHand();
		displayScore();
		pauseSubScene = new PauseSubScene();
		
		mainStage.setScene(mainScene); 
		mainStage.setTitle("Color Switch 2.0");
		mainPane.setBackground(new Background(new BackgroundFill(Color.web(backGroundColor), CornerRadii.EMPTY, Insets.EMPTY)));
		setUserControl();
		mainPane.getChildren().add(pauseSubScene);
	}
	
	public static int getScreenHeight() {
		return HEIGHT;
	}
	public static int getScreenWidth() {
		return WIDTH;
	}
	
	public Player getPLayer() {
		return ball;
	}
	
	public void startNewGame(Stage menuStag) {
		menuStage = menuStag;
		menuStage.hide();
		this.mainStage.show();
	}
	
	private void setUserControl() {
		mainScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.SPACE) {
					ball.moveUp();
					moveGameElements(ball.displacement);
				}	
			}
		});
	}
	
	private void addHand() {
		handy = new ImageView() ;
		Image handImage = new Image("/screen/resource/hand.png",80, 80, false, true);
		handy.setImage(handImage);
		handy.setFitHeight(120);
		handy.setFitWidth(170);
		handy.setLayoutX(175);
		handy.setLayoutY(630);
		mainPane.getChildren().add(handy);
	}
	
	private void moveGameElements(int dist) {
		for(int i=0;i<obstacleList.size(); i++) {
			obstacleList.get(i).getNode().setLayoutY( obstacleList.get(i).getNode().getLayoutY() + dist);
		}
		
		for(int i=0; i<starList.size(); i++) {
			starList.get(i).setLayoutY(starList.get(i).getLayoutY() + dist);
		}
		
		for(int i=0; i<switchList.size(); i++) {
			switchList.get(i).setLayoutY(switchList.get(i).getLayoutY() + dist);
		}
		
		handy.setLayoutY(handy.getLayoutY() + dist);
		relocateElements();
	}
	
	private void relocateElements() {
		for(int i=0; i<obstacleList.size(); i++) {
			if(obstacleList.get(i).getClass()== CircularObstacle.class) {
				if( obstacleList.get(i).getNode().getLayoutY() > HEIGHT + ((CircularObstacle) obstacleList.get(i)).getRadius()) 
					obstacleList.get(i).getNode().setLayoutY(-((CircularObstacle) obstacleList.get(i)).getRadius());
			}
			else if(obstacleList.get(i).getClass()== LinearObstacle.class){
				if(obstacleList.get(i).getNode().getLayoutY() > HEIGHT + 350) {
					obstacleList.get(i).getNode().setLayoutY(-250);
				}
			}
			else {
				//System.out.println("Add new code");
			}
		}
		
		//relocating STARS
		for(int i=0; i<starList.size(); i++) {
			if(starList.get(i).getLayoutY() > HEIGHT) {
				starList.get(i).setLayoutY(-2650 - starList.get(i).getFitHeight()-10);
			}
		}
		
		for(int i=0; i<switchList.size(); i++) {
			if(switchList.get(i).getLayoutY() > HEIGHT) {
				switchList.get(i).setLayoutY(-700 - switchList.get(i).getFitHeight() - 10);
			}
		}
		
	}
	
	private void addSwitch() {
		switchList = new ArrayList<ColorSwitch>();
		switchList.add(new ColorSwitch(100));
		switchList.add(new ColorSwitch(-200));
		switchList.add(new ColorSwitch(-900));
		switchList.add(new ColorSwitch(-1500));
		switchList.add(new ColorSwitch(-2500));
		
		for(int i=0; i<switchList.size(); i++) {
			mainPane.getChildren().add(switchList.get(i));
		}
	}
	private void addStar() {
		starList = new ArrayList<Star>();
		starList.add(new Star(300));
		starList.add(new Star(-100));
		starList.add(new Star(-650));
		starList.add(new Star(-1450));
		starList.add(new Star(-2050));
		
		for(int i=0; i<starList.size(); i++)
			mainPane.getChildren().addAll(starList.get(i));
	}
	
	private void createObstacle() {
		obstacleList = new ArrayList<Obstacle>();
		
		obstacleList.add(new CircularObstacle(250, 350));
		obstacleList.add(new LinearObstacle());
		obstacleList.add(new TriangularObstacle(120, -140));
		
		for(int i=0;i<obstacleList.size(); i++) {
			mainPane.getChildren().add(obstacleList.get(i).getNode());
		}
	}
	
	private void displayScore() {
		scoreLabel = new Label("0");
		scoreLabel.setPrefWidth(90);
		scoreLabel.setPrefHeight(80);
		scoreLabel.setFont(Font.font("Verdana", 40));
		scoreLabel.setTextFill(Color.GHOSTWHITE);
		scoreLabel.setLayoutX(20);
		scoreLabel.setLayoutY(25);
		mainPane.getChildren().add(scoreLabel);
		
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		Duration duration = Duration.millis(16);
		KeyFrame keyframe = new KeyFrame(duration, e -> updateScoreDisplay());
		timeline.getKeyFrames().add(keyframe);
		timeline.play();
	}
	private void updateScoreDisplay() {
		scoreLabel.setText(String.valueOf(ball.getScore()));
	}
	
	private void createPauseIcon() {
		ImageView pauseIcon = new ImageView();
		String pauseImage = "/screen/resource/pauseIcon.png";
		Image icon = new Image(pauseImage, 80, 80, true, true);
		pauseIcon.setImage(icon);
		pauseIcon.prefHeight(70);
		pauseIcon.prefWidth(70);
		//pauseIcon.setFitHeight(80);
		//pauseIcon.setFitWidth(80);
		pauseIcon.setLayoutX(410);
		pauseIcon.setLayoutY(30);
		
		pauseIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				//pauseIcon.setVisible(false);						//hide pause button			
				for(int i=0; i<obstacleList.size(); i++) {
					if(obstacleList.get(i).getClass() != LinearObstacle.class) {
						obstacleList.get(i).pauseRotation();
					}
					pauseSubScene.moveSubScene();
				}
			}
		});
		mainPane.getChildren().add(pauseIcon);
	}
	
	private void check() {
		for(int i=0; i<starList.size(); i++) {
			if(ball.intersects(starList.get(i).getBoundsInParent())) { 
				ball.incrementScore();
				starList.get(i).fadeAwayStar();
				starList.remove(i);
				double d = starList.get(starList.size()-1).getLayoutY();
				starList.add(new Star(d + Math.random()*(750 - 600 +1)+ 600));
			}
		}
		
		for(int i=0; i<switchList.size(); i++) {
			if(ball.intersects(switchList.get(i).getBoundsInParent())) {
				ball.changeColor();
				switchList.get(i).fadeAwaySwitch();
				switchList.remove(i);
				
			}
		}
			
		checkColorCollision(ball, obstacleList.get(0));
	}
	
	private boolean checkColorCollision(Player ball, Obstacle c) {
		Group group = c.getNode();
		
		for(int i=0; i<group.getChildren().size(); i++) {
			if(Shape.intersect(ball,  (Shape)group.getChildren().get(i)).getBoundsInParent().getHeight() > 0) {
				if(ball.getFill().equals(((Arc) group.getChildren().get(i)).getStroke())) {					//if color don't match
					System.out.print(((Arc) group.getChildren().get(i)).getStroke() + " ");
					System.out.println(ball.getFill());
					explodeBall();
					return true;
				}
			}
			
//			if(Shape.intersect(ball, (Shape)group.getChildren().get(i)).getBoundsInParent().getHeight() > 0) {
//				if(!((Line)group.getChildren().get(i)).getStroke().equals(ball.getFill())) {
//					explodeBall();
//				}
//				//System.out.println();
//			}
//			
			
		}
		return false;
	}
	
	private void checkCollision() {
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		
		Duration duration = Duration.millis(16);
		
		KeyFrame keyframe = new KeyFrame(duration, e -> check());
		
		timeline.getKeyFrames().add(keyframe);
			
		timeline.play();
	}
	
	
	private void explodeBall() {
		System.out.println("Exlosion !!!!");
		ball.explode();
	}
	
}
