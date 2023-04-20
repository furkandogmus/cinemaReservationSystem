import java.io.File;
import java.net.MalformedURLException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddFilmPage {
	public Scene createScene() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		Label message = new Label("Please give name, relative path of the trailer and duration of the film.");
		message.setTranslateY(-10);
		grid.add(message, 0, 0);
		
		Label name = new Label("Name:              ");
		name.setTranslateY(5);
		TextField nameField = new TextField();
		HBox nameBox = new HBox();
		nameBox.getChildren().addAll(name,nameField);
		
		Label trailerPath = new Label("Trailer (Path):   ");
		trailerPath.setTranslateY(5);
		TextField trailerPathField = new TextField();
		HBox trailerBox = new HBox();
		trailerBox.getChildren().addAll(trailerPath,trailerPathField);
		
		Label duration = new Label("Duration (m):   ");
		duration.setTranslateY(5);
		TextField durationField = new TextField();
		HBox durationBox = new HBox();
		durationBox.getChildren().addAll(duration,durationField);
		
		Label warningMessage = new Label();
		Button backButton = new Button("<- BACK");
		backButton.setOnAction(e-> {
			Main.switchScenes(LoginPage.adminMainScene);
			nameField.setText("");
			trailerPathField.setText("");
			durationField.setText("");
			
		});
		Button okButton = new Button("OK");
		okButton.setOnAction(e-> {
			if(nameField.getLength()>0) {
				if(trailerPathField.getLength()>0) {
					File media = new File("assets\\trailers\\"+trailerPathField.getText());
					if(durationField.getLength()>0 && Main.isNumeric(durationField.getText()) && Integer.valueOf(durationField.getText())>0) {
					if(media.exists()) {
						if(!Main.backup.isContain(nameField.getText())) {
						Main.backup.items.add(new Film(nameField.getText(),trailerPathField.getText(),Integer.valueOf(durationField.getText())));
						AdminMainPage.comboBox.getItems().add(nameField.getText());
						System.out.println(((Film)Main.backup.getItem(nameField.getText())).trailPath);
					}
						else {
							warningMessage.setText("ERROR: The film is already exists!");
							try {
								Main.playErrorSound();
							} catch (MalformedURLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}	
					}
					else {
						warningMessage.setText("ERROR: There is no such a trailer.");
						try {
							Main.playErrorSound();
						} catch (MalformedURLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					}
					else {
						warningMessage.setText("ERROR: Duration has to be positive integer!");
						try {
							Main.playErrorSound();
						} catch (MalformedURLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
			}
			else {
			warningMessage.setText("ERROR: Trailer path could not be empty!");
			try {
				Main.playErrorSound();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				}}
				
			
			else {
				warningMessage.setText("ERROR: Film name could not be empty!");
				try {
					Main.playErrorSound();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
			
		});
		
		HBox buttons = new HBox();
		buttons.setSpacing(135);
		buttons.getChildren().addAll(backButton,okButton);
		
		
		
		VBox boxes = new VBox();
		boxes.setSpacing(6);
		boxes.getChildren().addAll(nameBox,trailerBox,durationBox,buttons,warningMessage);
		grid.add(boxes, 0, 1);
		boxes.setTranslateX(70);
		
		Scene scene = new Scene(grid, 400,200);
		return scene;
	}
}
