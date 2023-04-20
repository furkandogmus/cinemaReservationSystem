
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RemoveFilmPage {
	public Scene createScene() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		Label message = new Label("Select the film that you desire to remove and then click OK");
		
		ComboBox<String> filmList = new ComboBox<>();
		for(Item item: Main.backup.items) {
			if(item instanceof Film) {
			Film film = (Film) item;
			filmList.getItems().add(film.name);
		}}
		filmList.getSelectionModel().select(0);
		
		Button backButton = new Button("<- BACK");
		backButton.setOnAction(e-> Main.switchScenes(LoginPage.adminMainScene));
		Button okButton = new Button("OK");
		okButton.setOnAction(e-> {
		AdminMainPage.comboBox.getItems().remove(filmList.getSelectionModel().getSelectedItem());
		Main.backup.items.remove((Film)Main.backup.getItem(filmList.getSelectionModel().getSelectedItem()));
		filmList.getItems().remove(filmList.getSelectionModel().getSelectedItem());
		filmList.getSelectionModel().selectNext();
		AdminMainPage.comboBox.getSelectionModel().selectFirst();
		});
		
		HBox buttons = new HBox();
		buttons.getChildren().addAll(backButton,okButton);
		buttons.setSpacing(10);
		buttons.setAlignment(Pos.CENTER);

		VBox vbox = new VBox();
		vbox.setSpacing(10);
		vbox.getChildren().addAll(message,filmList,buttons);
		grid.add(vbox, 0, 0);
		
		Scene scene = new Scene(grid, 400,200);
		return scene;
	}
}
