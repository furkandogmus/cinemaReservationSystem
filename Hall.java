
public class Hall extends Item{
	String filmName,name;
	int pricePerSeat,row,column;
	public Hall(String filmName,String name,int pricePerSeat,int row,int column) {
		this.filmName = filmName;
		this.name =super.name=name;
		this.pricePerSeat = pricePerSeat;
		this.row = row;
		this.column = column;
	}
}
