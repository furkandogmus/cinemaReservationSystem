import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Data {
	List<Item> items = new ArrayList<>();
	List<Seat> seats = new ArrayList<>();
	
	public Data(List<String> data) {
	for(String text: data) {
		String info[] = text.split("\t");
		if(info[0].equals("user")) {
			items.add(new User(info[1], info[2], info[3], info[4]));
		}
		else if(info[0].equals("film")) {
			items.add(new Film(info[1], info[2], Integer.valueOf(info[3])));
		}
		else if(info[0].equals("hall")) {
			items.add(new Hall(info[1],info[2],Integer.valueOf(info[3]),Integer.valueOf(info[4]),Integer.valueOf(info[5])));
		}
		else if(info[0].equals("seat")) {
			seats.add(new Seat(info[1],info[2],Integer.valueOf(info[3]),Integer.valueOf(info[4]),info[5],Integer.valueOf(info[6])));

			}
	}
	
	for(Seat seat: seats) {
			if(seat.price<=0) {
			Hall hall = (Hall) getItem(seat.hallName);
			seat.price = hall.pricePerSeat;
	}
			}
	
	}
	public boolean isContain(String name) {
		Iterator<Item> iterator = items.iterator();
		while(iterator.hasNext()) {
			Item item = iterator.next();
			if(item.name.equals(name)) {
				return true;
			}
		}
		return false;
	}


	public Seat getSeat(int row,int column,String hallName) {
		Seat seat = null;
		Iterator<Seat> iterator = seats.iterator();
		while(iterator.hasNext()) {
			seat = iterator.next();
			if(seat.column == column && seat.row == row && seat.hallName.equals(hallName)) {
				break;
			}
		}
		return seat;
	}
	
	public Item getItem(String name) {
		
		Item item = null;
		Iterator<Item> iterator = items.iterator();
		while(iterator.hasNext()) {
			item = iterator.next();
			if(item.name.equals(name)) {
				break;
			}
			
		}
		return item;
	}
	
}

