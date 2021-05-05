package application;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataManager {
	
	public static void save(Serializable data) {
		try {
			ObjectOutputStream save = new ObjectOutputStream(Files.newOutputStream(Paths.get("gameTable")));
			save.writeObject(data);
		} catch (Exception e) {
			System.err.println("Couldn't save data :" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static Object load() {
		try {
			ObjectInputStream load = new ObjectInputStream(Files.newInputStream(Paths.get("gameTable")));
			return load.readObject();
		} catch (Exception e) {
			System.err.println("Couldn't load data : "+ e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
