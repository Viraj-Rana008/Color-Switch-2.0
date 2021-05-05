package design;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class MenuButtons extends Button {
	
	private final static String FONT = "resource/kenvector_future.ttf";
	private final static String BUTTON_STYLE = "-fx-background-color: transparent; -fx-background-image: url('resource/blue_button.png');";
	private final static String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('resource/blue_button_pressed.ong');";
	
	public MenuButtons(String text) {
		setButtonFont();
		setText(text);
		setStyle(BUTTON_STYLE);
		setPrefWidth(450);
		setPrefHeight(58);
		setButtonListeners();
		setTextAlignment(TextAlignment.CENTER);
	}
	
	private void setButtonFont() {
		try {
			setFont(Font.loadFont(getClass().getResourceAsStream(FONT),40));
		} catch (Exception e) {
			setFont(Font.font("Verdana", 40));
		}
	}
	
	private void setButtonPressedStyle() {
		setStyle(BUTTON_PRESSED_STYLE);
		setPrefHeight(54);
		setLayoutY(getLayoutY() + 4);
	}
	
	private void setButtonReleasedStyle() {
		setStyle(BUTTON_STYLE);
		setPrefHeight(58);
		setLayoutY(getLayoutY() - 4);
	}
	
	private void setButtonListeners() {
		setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY))
					setButtonPressedStyle();
			}
		});
		
		setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY))
					setButtonReleasedStyle();
			}
		});
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setEffect(new DropShadow());
			}
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setEffect(null);
			}
		});
	}
}
