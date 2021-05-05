package design;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HiScoreTable extends TableView<Player>{
	
	private static ObservableList<Player> players;
	private static HiScoreTable hiScoreTable;
	
	@SuppressWarnings("unchecked")
	private HiScoreTable() {
		players = FXCollections.observableArrayList();
		
		//Name Column
		TableColumn<Player, String> nameColumn = new TableColumn<>("Player Name");
		nameColumn.setMinWidth(190);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
				
		//Score Column
		TableColumn<Player, Integer> scoreColumn = new TableColumn<>("Score");
		scoreColumn.setMinWidth(160);
		scoreColumn.setCellValueFactory(new PropertyValueFactory<>("hiScore"));
				
		getColumns().addAll(nameColumn, scoreColumn);
	}
	
	public static HiScoreTable createScoreTable() {
		if(hiScoreTable==null){
			hiScoreTable = new HiScoreTable();
			return hiScoreTable;
		}
		return hiScoreTable;
	}
	
	public void putGame(Player player) {
		players.add(player);
		setItems(players);
	}
	
	public void updateScoreTable(Player player) {
		for(int i=0; i<players.size(); i++) {
			if(players.get(i) == player) {
				if(players.get(i).getHiScore() < player.getHiScore()) {
					players.get(i).setHiScore(player.getHiScore());
					return;
				}
			}
		}
		putGame(player);
	}
}
