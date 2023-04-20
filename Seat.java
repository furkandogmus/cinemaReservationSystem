
public class Seat extends Item{
	String filmName,hallName,ownerName;
	int row,column,price;
	public Seat(String filmName,String hallName,int row,int column,String ownerName,int price) {
		this.filmName = filmName;
		this.hallName =hallName;
		this.row = row;
		this.column = column;
		this.ownerName = ownerName;
		if(price<=0) {
		this.price = 0;
	}
		else {
		this.price = price;	
		}
		}
	
	public void removeOwnerName() {
		this.ownerName = "null"; 
	}
	
}
