
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User extends Item{
	StringProperty tableName,tableIsAdmin,tableIsClubMember;
	private String name,password,hashedPassword;
	private boolean isClubMember,isAdmin;
	public User(String name,String hashedPassword,String isClubMember,String isAdmin) {
		this.setName(name);
		super.name = name;
		this.setHashedPassword(hashedPassword);
		if(isClubMember.equals("false")) {
			this.setClubMember(false);
		}
		else {
			this.setClubMember(true);
		}
		if(isAdmin.equals("false")) {
			this.setAdmin(false);
		}
		else {
			this.setAdmin(true);
		}
		
	}
	public boolean isClubMember() {
		return isClubMember;
	}
	public void setClubMember(boolean isClubMember) {
		this.isClubMember = isClubMember;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHashedPassword() {
		return hashedPassword;
	}
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	
	
	public String getTableName() {
		return nameProperty().get();
	}
	public void setTableName(String tableName) {
		nameProperty().set(tableName);
	}
	public String getIsAdmin() {
		return isAdminProperty().get();
	}
	public void setIsAdmin(String tableName) {
		isAdminProperty().set(tableName);
	}
	public String getIsClubMember() {
		return isClubMemberProperty().get();
	}
	public void setIsClubMember(String tableName) {
		isClubMemberProperty().set(tableName);
	}

	public StringProperty nameProperty() {
		if(tableName == null) 
		
		tableName = new SimpleStringProperty(this, getName());
		tableName.set(getName());
		return tableName;
	}
	public StringProperty isClubMemberProperty() {
		if(tableIsClubMember == null) 
		
		tableIsClubMember = new SimpleStringProperty(this, String.valueOf(isClubMember()));
		tableIsClubMember.set(String.valueOf(isClubMember()));
		return tableIsClubMember;
	}
	public StringProperty isAdminProperty() {
		if(tableIsAdmin == null) 
	
		tableIsAdmin = new SimpleStringProperty(this,getName());
		tableIsAdmin.set(String.valueOf(isAdmin()));
		return tableIsAdmin;
	}

}
