package design;

import java.io.File;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javafx.animation.FillTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player extends Circle {
	private String name;
	private int starsCollected;
	private Color color;
	private int score;
	private int hiScore;
	private final static double RADIUS = 15;  
	private final static double INITIAL_X = 250;
	private final static double INITIAL_Y = 620;
	private final static Random random = new Random();
	private double velocity = 0;
	public int displacement = 0;
	private final static int baseLimit = (int) (INITIAL_Y - 80) ;
	private int baseLine = baseLimit;
	private double gravity = 0.25; 
	private boolean jumpOnce = false;
	private Timeline timeline;
	
	
	//Pink: #ff0080	-->(rgb(255, 0, 128))	Purple: #7420e8/rgb(116, 32, 232)---"#8d13fa-->rgb(116, 32, 232)"		Blue: #22e0f0/rgb(34, 224, 240) ---"#1e90ff-->rgb(20, 144, 255)"		Yellow: -->rgb(244, 222, 14)		Orange: #f75b20		Black: #292929-->rgb(41, 41, 41)
	
	public Player(String name) {
		this.name = name;
		initailize();
		animate();
	}
	
	public int getScore() {
		return this.score;
	}
	
	public String getName() {
		return name;
	}
	
	public int getHiScore() {
		return hiScore;
	}
	
	public void setHiScore(int score) {
		this.hiScore = score;
	}
	
	private void initailize() {
		starsCollected = 0;
		hiScore = 0;
		setRandomColor();
		score = 0;
		setRadius(RADIUS);
		setFill(color);
		setCenterX(INITIAL_X);
		setCenterY(INITIAL_Y);
		setEffect(new DropShadow());
	}
	
	
	private void animate() {
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		//timeline.setAutoReverse(true);
		
		Duration duration = Duration.millis(16);
		
		KeyFrame keyframe = new KeyFrame(duration, e -> move());
		
		timeline.getKeyFrames().add(keyframe);
			
		timeline.play();
	}
	
	private void goDown() {
		if(velocity < 2) {
			velocity += gravity;
		}
		if(jumpOnce && velocity>-5) {
			velocity += gravity;
		}
		else {
			jumpOnce = false;
			gravity = 0.25;
		}
		
		setCenterY(getCenterY() + velocity);
		if(getCenterY() > 620) {
			setCenterY(getCenterY() - velocity);
		}
	}
	
	private void move() {
		goDown();
		
		if(baseLine - (int)getCenterY() > 0) {
			displacement = baseLine - (int)getCenterY();
			baseLine -= displacement-20;
		}
		if(getCenterY() > baseLimit) {
			baseLine = baseLimit;
		}
	}
	
	public void moveUp() {
		jumpOnce = true;
		//velocity = -2 ;
		gravity = -0.25;
	}
	
	public void pauseTransition() {
		timeline.pause();
	}
	public void playTransition() {
		timeline.play();
	}
	
	public void incrementScore() {
		this.score += 1;
		if(hiScore < score) {
			hiScore = score;
		}
		this.starsCollected += 1;
	}
	
	
	public void explode() {
		final int count = 6;
		final Circle[] particles = new Circle[count];
		//final long delay[] = new long[count];
		//final double[] angles = new double[count];
		AnchorPane pane = (AnchorPane)getParent();
		this.setVisible(false);
		
		for(int i=0; i<count; i++) {
			particles[i] = new Circle();
			pane.getChildren().add(particles[i]);
			particles[i].setCenterX(this.getCenterX());
			particles[i].setCenterY(this.getCenterY());
			particles[i].setRadius(ThreadLocalRandom.current().nextInt(5,12));		//ThreadLocalRandom.current().nextInt(5,12)
			randomColor(particles[i]);
			
			TranslateTransition translate = new TranslateTransition(Duration.millis(1000), particles[i]);
			if(i%2==0)		translate.setToX(-550);
			else 			translate.setToX(700);	
			translate.setToY(ThreadLocalRandom.current().nextInt((int)this.getCenterY() - 100, (int)this.getCenterY() + 200));				//ThreadLocalRandom.current().nextInt((int)this.getCenterY() - 100, (int)this.getCenterY() + 200)
			translate.setCycleCount(1);
			translate.play();
		}
		
	}
	
	public void reSet() {
		this.score = 0;
		this.displacement = 0;
		setCenterX(INITIAL_X);
		setCenterY(INITIAL_Y);  
		setRandomColor();
		if(!isVisible())
			setVisible(true);
	}
	
	private void randomColor(Circle c) {
		int n = random.nextInt(4);
		switch (n) {
		case 0:
			c.setFill(Color.rgb(255, 0, 128));
			break;
		case 1:
			c.setFill(Color.rgb(116, 32, 232));
			break;
		case 2:
			c.setFill(Color.rgb(34, 224, 240));
			break;
		case 3:
			c.setFill(Color.rgb(244, 222, 14));
			break;
		default:
			break;
		}
	}

	public void setRandomColor() {
		int n = random.nextInt(4);
		FillTransition ft = new FillTransition(Duration.millis(3), this);
		ft.setCycleCount(1);
		
		if(color==null) {
			switch (n) {
			case 0:
				color = Color.rgb(255, 0, 128);
				break;
			case 1:
				color = Color.rgb(116, 32, 232);
				break;
			case 2:
				color = Color.rgb(34, 224, 240);
				break;
			case 3:
				color = Color.rgb(244, 222, 14);
				break;
			default:
				break;
			}
			return;
		}
		
		ft.setFromValue(color);
		
		if(!color.equals(Color.rgb(255, 0, 128)) && n==0) {						//pink
			color = Color.rgb(255, 0, 128);
			ft.setToValue(Color.rgb(255, 0, 128));
		}
			
		else if(!color.equals(Color.rgb(116, 32, 232)) && n==1) {				//purple
			color = Color.rgb(116, 32, 232);
			ft.setToValue(Color.rgb(116, 32, 232));
		}
			
		else if(!color.equals(Color.rgb(34, 224, 240)) && n==2) {				//blue
			color = Color.rgb(20, 144, 255);
			ft.setToValue(Color.rgb(34, 224, 240));

		}
			
		else if(!color.equals(Color.rgb(244, 222, 14)) && n==3) {				//yellow
			color = Color.rgb(244, 222, 14);
			ft.setToValue(Color.rgb(244, 222, 14));
		}
			
		ft.play();
	}
}

