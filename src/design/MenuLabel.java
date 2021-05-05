package design;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MenuLabel extends Label{
	private String FONT_1 = "resource/Kenney_Pixel_Square.ttf";
	
	public MenuLabel() {
		setLabel();
	}
	
	private void setLabel() {
		setPrefHeight(200);
		setPrefWidth(400);
		setPadding(new Insets(40,40,40,40));
		setText("MENU");
		
		try {
			setFont(Font.loadFont(getClass().getResourceAsStream(FONT_1),70));
		} catch (Exception e) {
			setFont(Font.font("Verdana", 70));
		}
		
		setTextFill(Color.WHITESMOKE);
		setTranslateX(100);
	}
}
