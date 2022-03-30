package screen;

import javafx.scene.SubScene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public abstract class ColorSwitchSubScene extends SubScene implements SubSceneTransition{

	protected ImageView backIcon = new ImageView();
	protected AnchorPane mainPane;
	
	protected boolean isHidden;
	
	public ColorSwitchSubScene(int width, int height, int xLayout, int yLayout) {
		super(new AnchorPane(), width, height);
		mainPane = (AnchorPane)this.getRoot();
		
		setLayoutX(xLayout);
		setLayoutY(yLayout);
		prefWidth(width);
		prefHeight(height);
				
	}
	
	abstract void addBackButton();
	
	
	public AnchorPane getPane() {
		return this.mainPane;
	}
}
