
import java.net.MalformedURLException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;

public class UserMainPage {
	
	
	public Scene createScene() {
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(40,10,10,10));
		Label message = new Label("Welcome " +LoginPage.user.getName()+"!\nSelect a film and then click OK to continue.");
		message.setTextAlignment(TextAlignment.CENTER);
		message.setAlignment(Pos.BOTTOM_RIGHT);
		message.setTranslateX(70);
		message.setTranslateY(30);
		pane.setTop(message);
		ComboBox<String> films = new ComboBox<>();
		for(Item item: Main.backup.items) {
			if(item instanceof Film) {
			Film film = (Film)item;
			films.getItems().add(film.name);
		}}
		films.getSelectionModel().selectFirst();
		Button okButton = new Button("OK");
		okButton.setOnAction(e->{
			
				try {
					MediaPlayerPage mediaPlayerPage = new MediaPlayerPage(films.getValue());
					AdminMainPage.mediaPlayerScene = mediaPlayerPage.createScene();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Main.switchScenes(AdminMainPage.mediaPlayerScene);
			
			
			
		});
		
		Button logoutButton = new Button("LOGOUT");
		logoutButton.setOnAction(e-> Main.switchScenes(Main.login));
		pane.setRight(okButton);
		pane.setBottom(logoutButton);
		pane.setCenter(films);
		Scene scene = new Scene(pane,400,200);
		return scene;
	}
}
