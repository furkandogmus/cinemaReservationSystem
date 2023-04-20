
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SeatImage {
	Button seatButton;
	boolean isReserved = true;
	Seat seat;
	InputStream stream;
	Image seatImage;
	ImageView emptySeat;
	InputStream stream1;
	Image seatImage1;
	ImageView reservedSeat;
	
	public SeatImage(Seat seat) throws FileNotFoundException {
		this.seat = seat;
		if(seat.ownerName.equals("null")) {
			isReserved = false;
		}
		stream = new FileInputStream("assets\\icons\\empty_seat.png");
		seatImage = new Image(stream);
		emptySeat= new ImageView(seatImage);
		stream1 = new FileInputStream("assets\\icons\\reserved_seat.png");
		seatImage1 = new Image(stream1);
		reservedSeat = new ImageView(seatImage1);
		emptySeat.setFitHeight(40);
		emptySeat.setFitWidth(40);
		reservedSeat.setFitHeight(40);
		reservedSeat.setFitWidth(40);
		seatButton = new Button();
		
		
		if(!isReserved) {
		seatButton.setGraphic(emptySeat);
		}
		else {
			seatButton.setGraphic(reservedSeat);
		}
	}
	public void changeImage() {
		if(isReserved) {
			isReserved = false;
			seat.removeOwnerName();
			seatButton.setGraphic(emptySeat);
		}
		else {
			isReserved = true;
			seatButton.setGraphic(reservedSeat);	}
	
	}
	}
