package application;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class MediaBar extends HBox {
	
	private Slider time;
	private Slider volume;
	private Button btnPlay;
	private Label lblVolume;
	private MediaPlayer player;
	
	public MediaBar(MediaPlayer p) {
		
		this.player = p;
		this.time = new Slider();
		this.volume = new Slider();
		this.btnPlay = new Button("||");
		this.lblVolume = new Label("Volume: ");
		
		setAlignment(Pos.CENTER);
		setPadding(new Insets(5, 10, 5, 10));
		
		this.volume.setPrefWidth(70);
		this.volume.setMinWidth(30);
		this.volume.setValue(100);
		
		HBox.setHgrow(this.time, Priority.ALWAYS);
		
		this.btnPlay.setPrefWidth(30);
		
		getChildren().add(this.btnPlay);
		getChildren().add(this.time);
		getChildren().add(this.lblVolume);
		getChildren().add(this.volume);
		
		this.btnPlay.setOnAction(new EventHandler<ActionEvent>(){
			
			public void handle(ActionEvent e) {
				
				playing();
				
			}
			
		});
		
		this.player.currentTimeProperty().addListener(new InvalidationListener() {
			
			public void invalidated(Observable ov) {
			
				updateValues();
				
			}
			
		});
		
		this.time.valueProperty().addListener(new InvalidationListener() {
			
			public void invalidated(Observable ov) {
				
				if (time.isPressed()) {
				
					player.seek(player.getMedia().getDuration().multiply(time.getValue() / 100));
					
				}
				
			}
			
		});
		
		this.volume.valueProperty().addListener(new InvalidationListener() {
			
			public void invalidated(Observable ov) {
				
				if (volume.isPressed()) {
				
					player.setVolume(volume.getValue() / 100);

				}
				
			}
			
		});
		
	}
	
	private void updateValues() {
		
		Platform.runLater(new Runnable() {
			
			public void run() {
				
				time.setValue(player.getCurrentTime().toMillis() / player.getTotalDuration().toMillis() * 100);
				
			}
			
		});
		
	}
	
	private void playing() {
		
		Status status = this.player.getStatus();
		
		if (status == Status.PLAYING) {
			
			if (this.player.getCurrentTime().greaterThanOrEqualTo(this.player.getTotalDuration())) {
				
				this.player.seek(this.player.getStartTime());
				this.player.play();
				
			}
			
			else {
				
				this.player.pause();
				this.btnPlay.setText(">");
				
			}
			
		}
		
		if (status == Status.PAUSED || status == Status.HALTED || status == Status.STOPPED) {
			
			this.player.play();
			this.btnPlay.setText("||");
			
		}
		
	}

}
