package application;
	
import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.media.MediaException;
import javafx.scene.paint.Color;

public class Main extends Application {
	
	private Player player;
	private FileChooser fileChooser;
	
	@Override
	public void start(Stage primaryStage) {
	
		MenuItem mitOpen = new MenuItem("Open");
		Menu mnuFile = new Menu("File");
		MenuBar mnbBar = new MenuBar();
		
		mnuFile.getItems().add(mitOpen);
		mnbBar.getMenus().add(mnuFile);
		
		this.fileChooser = new FileChooser();
		
		mitOpen.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				
				player.pausePlayer();
				File file = fileChooser.showOpenDialog(primaryStage);
				
				if (file != null) {
					
					try {
						
						player = new Player(file.toURI().toURL().toExternalForm());
						Scene scene = new Scene(player, 720, 535, Color.BLACK);
						primaryStage.setScene(scene);
					
					} 
					
					catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					catch (MediaException e2) {
						System.out.println("Unsupported file signature");
					}
					
				}
				
			}
			
		});
		
		this.player = new Player("file:///C:/Users/BobHi/Videos/TEST.mp4");
		this.player.setTop(mnbBar);
		Scene scene = new Scene(player, 720, 535, Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}

/*
try {
BorderPane root = new BorderPane();
Scene scene = new Scene(root,400,400);
scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
primaryStage.setScene(scene);
primaryStage.show();
} catch(Exception e) {
e.printStackTrace();
}
*/