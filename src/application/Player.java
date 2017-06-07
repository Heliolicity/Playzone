package application;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Player extends BorderPane {

	private String fileLink;
	private Media media;
	private MediaPlayer mediaPlayer;
	private MediaView mediaView;
	private Pane pane;
	private MediaBar mediaBar;
	
	public Player(String fileLink) {
		
		this.fileLink = fileLink;
		this.media = new Media(this.fileLink);
		this.mediaPlayer = new MediaPlayer(this.media);
		this.mediaView = new MediaView(this.mediaPlayer);
		this.pane = new Pane();
		this.pane.getChildren().add(this.mediaView);
		
		setCenter(this.pane);
		
		this.mediaBar = new MediaBar(this.mediaPlayer);
		
		setBottom(this.mediaBar);
		setStyle("-fx-background-color: #bfc2c7");
		
		this.mediaPlayer.play();
		
	}
	
	public void pausePlayer() { this.mediaPlayer.pause(); }
	
}
