import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class EditUserPage {

	@SuppressWarnings("unchecked")
	public Scene createScene() {
		BorderPane grid = new BorderPane();
		TableView<User> userTable = new TableView<>();
		ObservableList<User> users= FXCollections.<User>observableArrayList();
		
		for(Item item: Main.backup.items) {
			if(item instanceof User) {
				users.add((User)item);
			}
		}
		
		userTable.setItems(users);
		TableColumn<User,String> nameColumn = new TableColumn<>("Username");
		nameColumn.setCellValueFactory(new Callback<CellDataFeatures<User, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<User, String> arg0) {
				return arg0.getValue().nameProperty();
			}
		  });
		TableColumn<User,String> isAdminColumn = new TableColumn<>("Admin");
		isAdminColumn.setCellValueFactory(new Callback<CellDataFeatures<User, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<User, String> arg0) {
				
				return arg0.getValue().isAdminProperty();
			}
		  });
		
		TableColumn<User,String> isClubMemberColumn = new TableColumn<>("Club Member");
		isClubMemberColumn.setCellValueFactory(new Callback<CellDataFeatures<User, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<User, String> arg0) {
				return arg0.getValue().isClubMemberProperty();
			}
		  });
		
		
		userTable.getColumns().addAll(nameColumn,isClubMemberColumn,isAdminColumn);
		userTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		
		
		Button clubMemberButton = new Button("Promote/Demote Club Member");
		Button backButton = new Button("Back");
		Button adminButton = new Button("Promote/Demote Admin");
		HBox hbox = new HBox();
		hbox.setSpacing(10);
		hbox.getChildren().addAll(backButton,clubMemberButton,adminButton);
		userTable.getSelectionModel().selectFirst();
		adminButton.setOnAction(e->{
			User tempUser = userTable.getSelectionModel().getSelectedItem();
			User user = (User) Main.backup.getItem(tempUser.getName());
			if(user.isAdmin()) {
				tempUser.setAdmin(false);
				user.setAdmin(false);
			}
			else {
				tempUser.setAdmin(true);
				user.setAdmin(true);
			}
			userTable.refresh();
			
		});
		clubMemberButton.setOnAction(e->{
			User tempUser = userTable.getSelectionModel().getSelectedItem();
			User user = (User) Main.backup.getItem(tempUser.getName());
			if(user.isClubMember()) {
				tempUser.setClubMember(false);
				user.setClubMember(false);
			}
			else {
				tempUser.setClubMember(true);
				user.setClubMember(true);
				}
			
			userTable.refresh();
			
		});
		
		
		backButton.setOnAction(e-> Main.switchScenes(LoginPage.adminMainScene));
		grid.setCenter(userTable);
		grid.setBottom(hbox);
		
		
		
		Scene scene = new Scene(grid, 600,600);
		return scene;
	}
}
