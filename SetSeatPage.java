
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SetSeatPage {
	public Scene createScene() throws FileNotFoundException {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(30,10,10,10));
		
		Hall hall = (Hall)Main.backup.getItem(MediaPlayerPage.halls.getValue());
		Label title = new Label(hall.filmName+" ("+((Film)Main.backup.getItem(hall.filmName)).duration+" Minutes) Hall: "+hall.name);
		title.setTranslateX(200);
		ComboBox<String> users = new ComboBox<>();
		Label message = new Label();
		Label message1 = new Label();
		VBox vbox = new VBox();
		vbox.getChildren().add(title);
		for(int x=0;x<hall.row;x++ ) {
			HBox rowBox = new HBox();
			rowBox.setSpacing(10);
			for(int y=0;y<hall.column;y++) {
				Seat seatItem = Main.backup.getSeat(x, y, hall.name);
				SeatImage seat = new SeatImage(seatItem);
				if(!LoginPage.user.isAdmin() && seat.isReserved && !seat.seat.ownerName.equals(LoginPage.user.getName())) {
					seat.seatButton.setDisable(true);
				}
				seat.seatButton.setOnMouseClicked(e-> {
					if(!seat.isReserved) {
					if(LoginPage.user.isAdmin()) {
					seat.seat.ownerName = users.getValue();}
					else {
						seat.seat.ownerName = LoginPage.user.getName();
					}
					if((!LoginPage.user.isAdmin() && LoginPage.user.isClubMember()) || users.getValue()!=null && ((User)(Main.backup.getItem(users.getValue()))).isClubMember()) {
						seat.seat.price = Math.round((100-Main.DISCOUNT_PERCENTAGE) * seat.seat.price / 100);
					}
					String name = users.getValue();
					if((!LoginPage.user.isAdmin() && LoginPage.user.isClubMember())) {
						name = LoginPage.user.getName();
					}
					message1.setText("Seat at "+(seat.seat.row+1)+"-"+(seat.seat.column+1)+" is bought for "+ name+ " for "+seat.seat.price+" TL successfully!");
					}
					else {
						if(seat.seat.price != ((Hall)Main.backup.getItem(seat.seat.hallName)).pricePerSeat) {
							seat.seat.price = ((Hall) Main.backup.getItem(seat.seat.hallName)).pricePerSeat;
						}
						if(LoginPage.user.isAdmin() || seat.seat.ownerName.equals(LoginPage.user.getName())) {
						message1.setText("Seat at "+ (seat.seat.row+1)+"-"+(seat.seat.column+1)+" is refunded successfully!");
						}

						seat.seat.ownerName = "null";
					}
					seat.changeImage();
					});
				seat.seatButton.setOnMouseEntered(e->{
					
					if(seat.isReserved) {
						String name = seat.seat.ownerName;
						if(!LoginPage.user.isAdmin()) {name = LoginPage.user.getName();}
						message.setText("Bought by "+name+" for "+seat.seat.price+" TL!" );
					}
					else {
						message.setText("Not bought yet!");

					}

					
				});
				seat.seatButton.setOnMouseExited(e-> message.setText(""));
				rowBox.getChildren().add(seat.seatButton);
			}
			vbox.getChildren().add(rowBox);
		}

		
		vbox.setSpacing(10);
		users.setTranslateX(200);
		
		if(LoginPage.user.isAdmin()) {
		for(Item item: Main.backup.items) {
			if(item instanceof User) {
			User user = (User)item;
			users.getItems().add(user.getName());
		}}
		users.getSelectionModel().selectFirst();
		vbox.getChildren().add(users);
		}
		
		Button backButton = new Button("<- BACK");
		backButton.setOnAction(e->Main.switchScenes(AdminMainPage.mediaPlayerScene));
		
		vbox.getChildren().addAll(message,message1,backButton);
		grid.add(vbox, 0, 0);
		
		Scene scene = new Scene(grid,800,800);
		return scene;
		
	}
}
