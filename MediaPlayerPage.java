
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MediaPlayerPage {
	
	
	
	AddHallPage addHallPage = new AddHallPage();
	RemoveHallPage removeHallPage = new RemoveHallPage();
	SetSeatPage setSeatPage = new SetSeatPage();
	static Scene addHallScene,removeHallScene,setSeatScene;
	static ComboBox<String> halls;
	
	Slider slider;
	Button buttonPlay,buttonStop,buttonNext,buttonPrevious,backButton,addHallButton,removeHallButton,okButton;
	
	String name;
	public MediaPlayerPage(String name) {
		this.name = name;
	}
	
	public Scene createScene() throws MalformedURLException {
		
		Pane pane = new Pane();
		
		
		Label title = new Label(name+ " ("+((Film)Main.backup.getItem(name)).duration+" minutes)");
		HBox titleBox = new HBox();
		titleBox.getChildren().add(title);
		titleBox.setTranslateX(280);
		pane.getChildren().add(titleBox);
		titleBox.setTranslateY(60);
		MyMedia myMedia = new MyMedia(name,600,850);
		myMedia.mediaView.setTranslateX(90);
		myMedia.mediaView.setTranslateY(100);
		pane.getChildren().add(myMedia.mediaView);
		buttonPlay = new Button(">");
		buttonPlay.setTranslateX(-5);
		buttonPlay.setScaleX(1.5);
		buttonPlay.setScaleY(1.2);
		buttonNext = new Button(">>");
		buttonNext.setScaleX(1.2);
		buttonNext.setScaleY(1.2);
		buttonPrevious = new Button("<<");
		buttonPrevious.setScaleX(1.2);
		buttonPrevious.setScaleY(1.2);
		buttonStop = new Button("|<<");
		buttonStop.setTranslateX(2);
		buttonStop.setScaleX(1.1);
		buttonStop.setScaleY(1.1);
		backButton = new Button("<- BACK");
		if(LoginPage.user.isAdmin()) {
		addHallButton = new Button("Add Hall");
		removeHallButton = new Button("Remove Hall");
		}
		halls = new ComboBox<>();
		for(Item item: Main.backup.items) {
			if(item instanceof Hall) {
			Hall hall = (Hall) item;
				if(hall.filmName.equals(name)) {
				halls.getItems().add(hall.name);
			}
			}
		}
		halls.getSelectionModel().selectFirst();
		
		okButton = new Button("OK");
		
		
		
		slider = new Slider();
		slider.setScaleX(1.2);
		slider.setScaleY(1.2);
		slider.setRotate(270);
		slider.setTranslateX(50);
		slider.setTranslateY(70);
		VBox vbox = new VBox();
		vbox.getChildren().addAll(buttonPlay, buttonStop, buttonNext, buttonPrevious,slider);
		vbox.setAlignment(Pos.CENTER_RIGHT);
		vbox.setSpacing(15);
		vbox.setTranslateX(600);
		vbox.setTranslateY(100);
		pane.getChildren().add(vbox);
		
		HBox commandButtonsBox = new HBox();
		commandButtonsBox.getChildren().add(backButton);
		if(LoginPage.user.isAdmin()) {
			commandButtonsBox.getChildren().addAll(addHallButton,removeHallButton);
		}
		commandButtonsBox.getChildren().addAll(halls,okButton);
		commandButtonsBox.setSpacing(10);
		commandButtonsBox.setTranslateX(200);
		commandButtonsBox.setTranslateY(450);
		pane.getChildren().add(commandButtonsBox);
		myMedia.mediaPlayer.setVolume(myMedia.mediaPlayer.getVolume()*0.5);
		slider.setValue(myMedia.mediaPlayer.getVolume()*100);
		
		slider.valueProperty().addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable arg0) {
				myMedia.mediaPlayer.setVolume(slider.getValue() / 100);
				
			}
			
			
		});
		buttonPlay.setOnAction(e-> {
		if(buttonPlay.getText().equals(">")) {
			buttonPlay.setText("||");
			myMedia.mediaPlayer.play();
			}
		else {
			buttonPlay.setText(">");
			myMedia.mediaPlayer.pause();
			}
		});
		buttonNext.setOnAction(e-> myMedia.mediaPlayer.seek(Duration.seconds(myMedia.mediaPlayer.getCurrentTime().toSeconds()+10)));
		buttonPrevious.setOnAction(e-> myMedia.mediaPlayer.seek(Duration.seconds(myMedia.mediaPlayer.getCurrentTime().toSeconds()-10)));
		buttonStop.setOnAction(e-> {
			myMedia.mediaPlayer.stop();
			myMedia.mediaPlayer.play();
		});
		backButton.setOnAction(e-> {
			if(buttonPlay.getText().equals("||")) {
				buttonPlay.setText(">");
			}
			myMedia.mediaPlayer.stop();
				if(LoginPage.user.isAdmin()) {
			Main.switchScenes(LoginPage.adminMainScene);}
		
			else {
			Main.switchScenes(LoginPage.userMainScene);
			}}
				);
		
		if(LoginPage.user.isAdmin()) {
		addHallButton.setOnAction(e->{
			if(buttonPlay.getText().equals("||")) {
				buttonPlay.setText(">");
			}
			myMedia.mediaPlayer.stop();
			addHallScene = addHallPage.createScene();
			Main.switchScenes(addHallScene);
			});
		
		removeHallButton.setOnAction(e-> {
		
		if(buttonPlay.getText().equals("||")) {
			buttonPlay.setText(">");
		}
		myMedia.mediaPlayer.stop();
		removeHallScene = removeHallPage.createScene();
		Main.switchScenes(removeHallScene);
		});
		}
		okButton.setOnAction(e->{
			if(buttonPlay.getText().equals("||")) {
				buttonPlay.setText(">");
			}
			try {
				setSeatScene = setSeatPage.createScene();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			myMedia.mediaPlayer.stop();
			Main.switchScenes(setSeatScene);
			 
		});
		
		
		Scene scene = new Scene(pane,800,500);
		return scene;
		
	}
}
