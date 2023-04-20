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

public class RegisterPage {
	Scene scene;
	Button button,button2;
	Label label;
	VBox layout;

	
	public Scene createScene() {
		GridPane grid = new GridPane();
        grid.setPadding(new Insets(40,10,10,10));
        Label welcomeMessage1 = new Label("Welcome to the HUCS Cinema Reservation System!\nFill the form below to create a new account.\nYou can go to Log In page by clicking LOG IN.");
        welcomeMessage1.setTextAlignment(TextAlignment.CENTER);
        VBox labelBox = new VBox();
        labelBox.getChildren().addAll(welcomeMessage1,new Label(""));
        grid.add(labelBox, 0, 0);
        
        Label usernameLabel = new Label("Username:   ");
        usernameLabel.setTranslateY(5);
        TextField username = new TextField();
        HBox userBox = new HBox();
        userBox.getChildren().addAll(usernameLabel,username);
        
        Label passwordLabel = new Label("Password:    ");
        passwordLabel.setTranslateY(5);
        PasswordField password = new PasswordField();
        HBox passwordBox = new HBox();
        passwordBox.getChildren().addAll(passwordLabel,password);
        
        Label passwordLabel1 = new Label("Password:    ");
        passwordLabel1.setTranslateY(5);
        PasswordField password1 = new PasswordField();
        HBox passwordBox1 = new HBox();
        passwordBox1.getChildren().addAll(passwordLabel1,password1);
		
        Label warningMessage = new Label();
        warningMessage.setTranslateY(20);
        warningMessage.setTranslateX(30);
        
        VBox loginBox = new VBox();
        loginBox.getChildren().addAll(userBox,passwordBox,passwordBox1);
        loginBox.setTranslateX(30);
        loginBox.setSpacing(10);
        grid.add(loginBox, 0, 1);
        
        Button register = new Button("SIGN UP");
        register.setOnAction(e-> {
        	
        if(username.getLength()!=0) {
        	if(password.getLength()==0) {
            	warningMessage.setText("ERROR: Password cannot be empty!");	
            	try {
    				Main.playErrorSound();
    			} catch (MalformedURLException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
            }
        	else {
        	if(Main.backup.isContain(username.getText())) {
        		warningMessage.setText("ERROR: This username already exists!");
        		try {
					Main.playErrorSound();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        	else {
        		if(!password.getText().equals(password1.getText())) {
        			warningMessage.setText("ERROR: Passwords do not match!");
        			try {
						Main.playErrorSound();
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        		}
        		else {
        			User user = new User(username.getText(),Main.hashPassword(password.getText()),"false","false");
        			Main.backup.items.add(user);
        			username.deleteText(0,username.getLength());
        			password.deleteText(0, password.getLength());
        			password1.deleteText(0, password1.getLength());
        			warningMessage.setText("SUCCESS: You have successfully registered with your new credentials!");
        			
        		}
        	}
        }}
        else{
        	warningMessage.setText("ERROR: Username cannot be empty!");	
        	try {
				Main.playErrorSound();
			} catch (MalformedURLException e1) {
				System.out.println(1);
				e1.printStackTrace();
			}
        }});
        
        
        
        
        
        Button login = new Button("LOG IN");
        login.setOnAction(e-> Main.switchScenes(Main.login));
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(login,register);
        buttonBox.setTranslateX(30);
        buttonBox.setTranslateY(10);
        buttonBox.setSpacing(100);
        grid.add(buttonBox, 0, 2);
        grid.add(warningMessage, 0, 3);
        Scene scene = new Scene(grid,425,275);
        
        
        return scene;
        
	}
}
