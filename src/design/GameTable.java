package design;

import java.io.Serializable;

import application.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class GameTable extends TableView<Game> implements Serializable{

	private static final long serialVersionUID = 8232117712284890848L;
	private static ObservableList<Game> games;
	private static GameTable gameTable;
	
	//ONLY ONE GameChooser to exist use Design Pattern
	@SuppressWarnings("unchecked")
	private GameTable() {
		games = FXCollections.observableArrayList();
		
		//Name Column
		TableColumn<Game, String> nameColumn = new TableColumn<>("Player Name");
		nameColumn.setMinWidth(190);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		//Score Column
		TableColumn<Game, Integer> scoreColumn = new TableColumn<>("Score");
		scoreColumn.setMinWidth(160);
		scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
		
		getColumns().addAll(nameColumn, scoreColumn);
		setListner();
	}
	
	public static GameTable createGameTable() {
		if(gameTable==null){
			gameTable = new GameTable();
			return gameTable;
		}
		return gameTable;
	}
	
	public void putGame(Game game) {
		games.add(game);
		setItems(games);
	}
	
	private void setListner() {
		setOnMouseClicked(e -> {getGame();});
	}
	
	public Game getGame() {
		Game game = getSelectionModel().getSelectedItem();
		return game;
	}
	
	 public void updateViaScore(Game game) {
		 for(int i=0; i<games.size(); i++) {
			 if(games.get(i)==game) {
				 games.get(i).setScore(game.getScore());
				 return;
			 }
		 }
		 //putGame(game);
	 }
	 
	 public void updateViaPlayer(Game game) {
		 for(int i=0; i<games.size(); i++) {
			 if(games.get(i).getPlayer() == game.getPlayer()) {
				 games.remove(i);
				 games.add(i, game);
				 this.updateViaScore(game);
				 return;
			 }
		 }
	 }
}
