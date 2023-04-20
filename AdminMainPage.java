import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import java.net.MalformedURLException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class AdminMainPage {
	static ComboBox<String> comboBox;
	
	RemoveFilmPage removeFilmPage = new RemoveFilmPage();
	EditUserPage editUserPage = new EditUserPage();
	static Scene mediaPlayerScene,addFilmScene,removeFilmScene,editUsersScene;
	
	
	public Scene createScene() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setPadding(new Insets(40,10,10,10));
		Label welcomeMessage = new Label("Welcome admin(Admin - Club Member)!\nYou can either select film below or do edits.");
		welcomeMessage.setTextAlignment(TextAlignment.CENTER);
		comboBox= new ComboBox<>();	
		for(Item item: Main.backup.items) {
			if(item instanceof Film) {
			comboBox.getItems().add(item.name);
		}}
		comboBox.getSelectionModel().select(0);
		Button okButton = new Button("OK");
		
		okButton.setOnAction(e-> 
		{
		try {
			MediaPlayerPage mediaPlayerPage = new MediaPlayerPage(comboBox.getValue());
			mediaPlayerScene = mediaPlayerPage.createScene();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Main.switchScenes(mediaPlayerScene);}
		);
		HBox comboButton = new HBox();
		comboButton.setSpacing(10);
		comboButton.getChildren().addAll(comboBox,okButton);
		
		
		Button addFilm = new Button("Add Film");
		addFilm.setOnAction(e-> {
		AddFilmPage addFilmPage = new AddFilmPage();
		addFilmScene = addFilmPage.createScene();
		Main.switchScenes(addFilmScene);});
		
		
		Button removeFilm = new Button("Remove Film");
		removeFilm.setOnAction(e-> {
		removeFilmScene = removeFilmPage.createScene();
		Main.switchScenes(removeFilmScene);});
		
		
		Button editUsers = new Button("Edit Users");
		editUsers.setOnAction(e-> {
		editUsersScene = editUserPage.createScene();	
		Main.switchScenes(editUsersScene);});
		
		
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(10);
		buttons.getChildren().addAll(addFilm,removeFilm,editUsers);
		
		Button logoutButton = new Button("LOG OUT");
		logoutButton.setOnAction(e-> Main.switchScenes(Main.login));
		logoutButton.setTranslateX(260);
		VBox boxes = new VBox();
		boxes.setSpacing(10);
		boxes.getChildren().addAll(welcomeMessage,comboButton,buttons,logoutButton);
		grid.add(boxes, 0, 0);
		
		Scene scene = new Scene(grid,375,200);
		return scene;
		
	}
}
