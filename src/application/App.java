package application;

import java.io.IOException;

import design.CircularObstacle;
import design.FXMLObstacleController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import screen.MenuScreenManager;

public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		MenuScreenManager menu = MenuScreenManager.createMenuManager();
		primaryStage = menu.getStage();	
		welcomeScreen(primaryStage);
		//primaryStage.show();
	}
	
	private void welcomeScreen(Stage pStage) {
		Stage stage = new Stage();
		AnchorPane pane = new AnchorPane();
		Scene scene = new Scene(pane, 500, 750);
		
		stage.setScene(scene);
		stage.setTitle("Color Switch 2.0");
		pane.setBackground(new Background(new BackgroundFill(Color.web("212121"), CornerRadii.EMPTY, Insets.EMPTY)));
		
		ImageView logo = new ImageView();
		Image logoImage = new Image("/design/resource/logo.jpg", 250, 125, false, true);
		logo.setImage(logoImage);
		logo.prefWidth(250);
		logo.prefHeight(125);
		logo.setLayoutX(130);
		logo.setLayoutY(14);
		pane.getChildren().add(logo);
		

		pane.getChildren().addAll((new CircularObstacle(250, 370, 11)).getNode(), (new CircularObstacle(250, 400, 12)).getNode(), (new CircularObstacle(250, 400, 13)).getNode());
		
		Label tap = new Label("TAP TO CONTINUE");
		tap.setLayoutX(120);
		tap.setLayoutY(620);
		tap.setFont(Font.font(STYLESHEET_CASPIAN, 30));
		tap.setTextFill(Paint.valueOf("#d0c3c3"));
		pane.getChildren().add(tap);
		tap.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				stage.close();
				pStage.show();
			}
		});
		stage.show();
	}

}
