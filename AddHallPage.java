import java.net.MalformedURLException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddHallPage
	{
	public Scene createScene() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		String filmName = AdminMainPage.comboBox.getValue();
		Label title = new Label(filmName+" ("+((Film)Main.backup.getItem(filmName)).duration+" minutes)");
		title.setTranslateX(20);
		Label row = new Label("Row:     ");
		ComboBox<Integer> digits1 = new ComboBox<>();
		digits1.getItems().addAll(3,4,5,6,7,8,9,10);
		digits1.getSelectionModel().select(0);
		digits1.setTranslateX(1);
		HBox rowBox = new HBox();
		rowBox.setSpacing(15);
		rowBox.getChildren().addAll(row,digits1);
		Label column = new Label("Column:");
		ComboBox<Integer> digits2 = new ComboBox<>();
		digits2.getItems().addAll(3,4,5,6,7,8,9,10);
		digits2.getSelectionModel().select(0);
		HBox columnBox = new HBox();
		columnBox.setSpacing(15);
		columnBox.getChildren().addAll(column,digits2);
		Label name = new Label("Name:");
		TextField nameField = new TextField();
		HBox nameBox = new HBox();
		nameBox.setSpacing(15);
		nameBox.getChildren().addAll(name,nameField);
		Label price = new Label("Price:  ");
		TextField priceField = new TextField();
		HBox priceBox = new HBox();
		priceBox.setSpacing(15);
		priceBox.getChildren().addAll(price,priceField);
		Label warningMessage = new Label();
		
		Button backButton = new Button("<- BACK");
		backButton.setOnAction(e-> Main.switchScenes(AdminMainPage.mediaPlayerScene));
		Button okButton = new Button("OK");
		okButton.setOnAction(e->{
			if(nameField.getLength()>0) {
				if(priceField.getLength()>0 && Main.isNumeric(priceField.getText()) && Integer.valueOf(priceField.getText())>0) {
					Hall hall = new Hall(AdminMainPage.comboBox.getValue(),nameField.getText(),Integer.valueOf(priceField.getText()),digits1.getValue(),digits2.getValue());
					Main.backup.items.add(hall);
					for(int x=0; x<digits1.getValue();x++) {
						for(int y = 0;y<digits2.getValue();y++) {
							Main.backup.seats.add(new Seat(hall.filmName,hall.name,x,y,"null",Integer.valueOf(priceField.getText())));
						}
						
					}
					MediaPlayerPage.halls.getItems().add(hall.name);
					MediaPlayerPage.halls.getSelectionModel().select(0);
					warningMessage.setText("SUCCESS: Hall successfully created!");
				}
				else {
					warningMessage.setText("ERROR: Price could not be empty!");
					try {
						Main.playErrorSound();
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			else {
				warningMessage.setText("ERROR: Hall name could not be empty!");
				try {
					Main.playErrorSound();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
		});
		HBox buttonBox = new HBox();
		buttonBox.setSpacing(105);
		buttonBox.getChildren().addAll(backButton,okButton);
		
		VBox elements = new VBox();
		elements.setSpacing(10);
		elements.getChildren().addAll(title,rowBox,columnBox,nameBox,priceBox,buttonBox,warningMessage);
		grid.add(elements, 0, 0);
		
		Scene scene = new Scene(grid, 250,250);
		return scene;
		
		
	}
	
	
}
