package screen;


import design.MenuButtons;
import design.MenuLabel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MenuScreenManager {
	
	private final static int WIDTH = 500;
	private final static int HEIGHT = 750;
	private final static String backGroundColor =  "212121";
	
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	private MenuSubScene gameOptionScene;
	private MenuSubScene newGameScene;
	private MenuSubScene highScoresScene;
	//private MenuSubScene exitScene;
	
	public MenuScreenManager() {
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		
		mainPane.getChildren().add(new MenuLabel());
		
		mainStage.setScene(mainScene); 
		
		mainStage.setTitle("Color Switch 2.0");
		mainPane.setBackground(new Background(new BackgroundFill(Color.web(backGroundColor), CornerRadii.EMPTY, Insets.EMPTY)));
		
		createButton();
		createSubScene();
	}
	
	
	private void createSubScene() {
		createGameOptionScene();
		newGameScene = new NewGameSubScene(75, 750);
		highScoresScene = new MenuSubScene(75, 750);
		//exitScene = new MenuSubScene(75, 750);
		
		mainPane.getChildren().addAll(gameOptionScene, newGameScene, highScoresScene);			//exitScene not added'/
	}
	
	private void createGameOptionScene() {
		gameOptionScene = new GameOptionSubScene(75, 750);
	}
	
	private void createButton() {
		createChooseButton();
		createNewGameButton();
		createHighScoreButton();
		createExitButton();
	}
	
	private void createChooseButton() {
		MenuButtons butt1 = new MenuButtons("Choose Game");
		butt1.setLayoutX(30);
		butt1.setLayoutY(200);
		
		butt1.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				gameOptionScene.moveSubScene();				
			}
		});
		
		mainPane.getChildren().add(butt1);
	}
	
	private void createNewGameButton() {
		MenuButtons butt2 = new MenuButtons("New Game");
		butt2.setLayoutX(30);
		butt2.setLayoutY(300);
		
		butt2.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				newGameScene.moveSubScene();				
			}
		});
		
		mainPane.getChildren().add(butt2);
	}
	
	private void createHighScoreButton() {
		MenuButtons butt3 = new MenuButtons("High Scores");
		butt3.setLayoutX(30);
		butt3.setLayoutY(400);
		
		butt3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				highScoresScene.moveSubScene();				
			}
		});
		
		mainPane.getChildren().add(butt3);
	}
	
	private void createExitButton() {
		MenuButtons butt4 = new MenuButtons("Exit");
		butt4.setLayoutX(30);
		butt4.setLayoutY(500);
		
		butt4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mainStage.close();				
			}
		});
		mainPane.getChildren().add(butt4);
	}
	
	
	public Stage getStage() {
		return mainStage;
	}
}
