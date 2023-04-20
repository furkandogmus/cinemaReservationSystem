
import java.net.MalformedURLException;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;



public class LoginPage {
	static int countError=0;
	static Scene registerScene,adminMainScene,userMainScene;
	RegisterPage registerPage = new RegisterPage();
	AdminMainPage adminMainPage = new AdminMainPage();
	UserMainPage userMainPage = new UserMainPage();
	static User user;
    public Scene createScene() {
    	
    	
    	
    	
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(40,10,10,10));
        Label welcomeMessage1 = new Label("Welcome to the HUCS Cinema Reservation System!\nPlease enter your credentials below and click LOGIN.\nYou can create a new account by clicking SIGN UP button.");
        welcomeMessage1.setTextAlignment(TextAlignment.CENTER);
        VBox labelBox = new VBox();
        labelBox.getChildren().addAll(welcomeMessage1,new Label(""));
        grid.add(labelBox, 0, 0);
        
        
        Label usernameLabel = new Label("Username:   ");
        TextField username = new TextField();
        username.setTranslateY(-5);
        HBox userBox = new HBox();
        userBox.getChildren().addAll(usernameLabel,username);
        
        Label passwordLabel = new Label("\nPassword:    ");
        PasswordField password = new PasswordField();
        password.setTranslateY(13);
        HBox passwordBox = new HBox();
        passwordBox.getChildren().addAll(passwordLabel,password);

        VBox loginBox = new VBox();
        loginBox.getChildren().addAll(userBox, passwordBox);
        loginBox.setTranslateX(30);
        grid.add(loginBox, 0, 1);
        Label warningMessage = new Label();
        warningMessage.setTranslateY(20);
        warningMessage.setTranslateX(30);
        
        
        Button register = new Button("SIGN UP");
        register.setOnAction(e-> 
        {
        username.setText("");
        password.setText("");
        registerScene = registerPage.createScene();
        Main.switchScenes(registerScene);});
        Button login = new Button("LOG IN");
        login.setOnAction(e-> {
        	String userName = username.getText();
        	
        	if(countError<=Main.MAXIMUM_ERROR_WITHOUT_GETTING_BLOCKED) {
        		if(!Main.timeIsOver) {
        			warningMessage.setText("ERROR: Please wait until end of the 5 seconds to make a new operation!");
        			try {
						Main.playErrorSound();
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
        		}
        		else {
        	if(Main.backup.isContain(userName) && ((User)Main.backup.getItem(userName)).getHashedPassword().equals(Main.hashPassword(password.getText()))) {
        		user = (User)Main.backup.getItem(userName);
        		if(((User)Main.backup.getItem(userName)).isAdmin()) {
        		adminMainScene = adminMainPage.createScene();
        		Main.switchScenes(adminMainScene);}
        		else {
        		userMainScene = userMainPage.createScene();
          		Main.switchScenes(userMainScene);
        		}
        		warningMessage.setText("");
        		password.setText("");
        		username.setText("");
        		}
        		else {
        			
        		countError++;
        		warningMessage.setText("ERROR: There is no such a credential!");
        		try {
					Main.playErrorSound();
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
        		
        	}
        		}
        		if(countError == Main.MAXIMUM_ERROR_WITHOUT_GETTING_BLOCKED) {
            		countError = 0;
            		warningMessage.setText("ERROR: Please wait for "+Main.BLOCK_TIME+" seconds to make a new operation!");
            		Main.Wait();
        		}}
        	});
        
        
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(register,login);
        buttonBox.setTranslateY(13);
        buttonBox.setTranslateX(30);
        buttonBox.setSpacing(100);
        grid.add(buttonBox, 0, 2);
        grid.add(warningMessage, 0, 4);
        
        
        
        
        
        Scene scene = new Scene(grid, 425, 275);
		return scene;
    }
}

