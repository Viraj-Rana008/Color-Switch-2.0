package screen;

import application.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class NewGameSubScene extends MenuSubScene {

	private VBox vBox = new VBox();
	private TextField textField = new TextField();
	private Label label = new Label();
	private Button startButton = new Button();
	private final static String FONT = "/design/resource/kenvector_future.ttf";
	
	public NewGameSubScene(int xLayout, int yLayout) {
		super(xLayout, yLayout);
		
		setScene();
		setStartButtonHandler();
	}
	
	
	private void setScene() {
		label.setText("Enter\nPlayer Name");
		label.setPrefWidth(350);
		label.setPrefHeight(190);
		label.setTextAlignment(TextAlignment.CENTER);
		label.setPadding(new Insets(40,40,40,40));
		//label.setEffect(new DropShadow());
		
		try {
			label.setFont(Font.loadFont(getClass().getResourceAsStream(FONT),30));
		} catch (Exception e) {
			label.setFont(Font.font("Verdana", 30));
		}
		
		startButton.setText("Start Button");
		startButton.setPrefWidth(100);
		startButton.setPrefHeight(58);
		startButton.setAlignment(Pos.CENTER);
		startButton.setLayoutX(mainPane.getWidth()/2 - startButton.getPrefWidth()/2);
		startButton.setLayoutY(350);
		
		textField.setPrefHeight(40);
		textField.setPrefWidth(200);
		textField.setLayoutX(80);
		textField.setLayoutY(220);
		
		
		mainPane.getChildren().addAll(label, textField, startButton);
	}
	
	private void setStartButtonHandler() {
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				GameScreenManager gameScreen = new GameScreenManager(textField.getText());
				Game game = new Game();
				gameScreen.startNewGame((Stage) getScene().getWindow());
			}
		});
	}
}
