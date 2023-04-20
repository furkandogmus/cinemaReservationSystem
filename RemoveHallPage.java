
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class RemoveHallPage {
	
	public Scene createScene() {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(20,10,10,10));
		Label title = new Label("Select the hall that you desire to remove from "+ AdminMainPage.comboBox.getValue()+" and then click OK.");
		title.setTextAlignment(TextAlignment.CENTER);
		ComboBox<String> halls = new ComboBox<>();
		
		for(String hall: MediaPlayerPage.halls.getItems()) {
			halls.getItems().add(hall);
		}
		halls.getSelectionModel().select(0);
		
		Button backButton = new Button("<- BACK");
		backButton.setOnAction(e-> Main.switchScenes(AdminMainPage.mediaPlayerScene));
		Button okButton = new Button("OK");
		okButton.setOnAction(e->{
			MediaPlayerPage.halls.getItems().remove(halls.getValue());
			halls.getItems().remove(halls.getValue());
			halls.getSelectionModel().selectFirst();
			MediaPlayerPage.halls.getSelectionModel().selectFirst();
			
		});
		HBox buttonBox = new HBox();
		buttonBox.setSpacing(20);
		buttonBox.getChildren().addAll(backButton,okButton);
		VBox vbox = new VBox();
		vbox.setSpacing(10);
		vbox.getChildren().addAll(title,halls,buttonBox);
		grid.add(vbox, 0, 0);
		
		
		Scene scene = new Scene(grid,500,150);
		return scene;
	}
}
