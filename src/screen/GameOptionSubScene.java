package screen;


import application.Game;
import design.GameTable;
import javafx.scene.effect.BlendMode;


public class GameOptionSubScene extends MenuSubScene {

	private GameTable gameTable;
	
	public GameOptionSubScene(int xLayout, int yLayout) {
		super(xLayout, yLayout);
		
		setGameTable();
		
		GameTable.putGame(new Game());			//demo entry
		this.setBlendMode(BlendMode.ADD);
	}

	
	private void setGameTable() {
		gameTable = GameTable.createGameTable();
		getPane().getChildren().add(gameTable);
		
	}
	
	public void enterGameToTable(Game game) {					//call this, get table, add to the table
		GameTable.putGame(game);
	}
	

}
