import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Main extends Application {
	static Stage stage;
	static Scene login;
	static Media media;
	static MediaPlayer mediaPlayer;
	static Data backup;
	static Properties properties;
	static boolean timeIsOver = true;
	
	static int MAXIMUM_ERROR_WITHOUT_GETTING_BLOCKED,DISCOUNT_PERCENTAGE,BLOCK_TIME;
	
	LoginPage loginPage = new LoginPage();
	AddHallPage addHallPage = new AddHallPage();
	RemoveHallPage removeHallPage = new RemoveHallPage();
	SetSeatPage setSeatPage = new SetSeatPage();
	
	public static void main(String[] args) throws IOException {
		backup = new Data(WriterReader.readData("assets\\data\\backup.dat"));
		FileReader rd = new FileReader("assets\\data\\properties.dat");
		
		properties = new Properties();
		properties.load(rd);
		MAXIMUM_ERROR_WITHOUT_GETTING_BLOCKED = Integer.valueOf(properties.getProperty("maximum-error-without-getting-blocked"));
		DISCOUNT_PERCENTAGE = Integer.valueOf(properties.getProperty("discount-percentage"));
		BLOCK_TIME = Integer.valueOf(properties.getProperty("block-time"));

		launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    	stage = primaryStage;
    	stage.setOnCloseRequest(e->{
    	String text = "";
    	ArrayList<Film> filmList = new ArrayList<>();
		ArrayList<Hall> hallList = new ArrayList<>();
        
    	for(Item item: Main.backup.items) {
    		if(item instanceof User) {
    			User user = (User) item;
    		text += "user\t"+user.getName()+"\t"+user.getHashedPassword()+"\t"+String.valueOf(user.isClubMember())+ "\t"+ String.valueOf(user.isAdmin());
    		text+= "\n";
    		}
    	}
    		for(Seat seat: Main.backup.seats) {
    			Film film = (Film)backup.getItem(seat.filmName);
    			if(!filmList.contains(film)) {
    			filmList.add(film);
        		text += "film\t"+seat.filmName+"\t"+film.trailPath+"\t"+film.duration;
        		text +="\n";
    			}
    			
        		Hall hall = (Hall) backup.getItem(seat.hallName);
        		if(!hallList.contains(hall)) {
        		hallList.add(hall);
    			text += "hall\t"+hall.filmName+"\t"+hall.name+"\t"+hall.pricePerSeat+"\t"+hall.row+"\t"+hall.column;
    			text += "\n";
        		}
    			if(seat.ownerName.equals("null")) {
    			text += "seat\t"+seat.filmName+"\t"+seat.hallName+"\t"+seat.row+"\t"+seat.column+"\t"+seat.ownerName+"\t"+ 0;
    			}
    			else {
    				text += "seat\t"+seat.filmName+"\t"+seat.hallName+"\t"+seat.row+"\t"+seat.column+"\t"+seat.ownerName+"\t"+ seat.price;
    			}
    			text += "\n";
    		}
 
    	
    	try {
			WriterReader.writeData("backup.dat", text);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    		
    		
    	});
    	login = loginPage.createScene();
    	
    	stage.setTitle(properties.getProperty("title"));
    	stage.getIcons().add(new Image("file:assets\\icons\\logo.png"));
    	
    	stage.setScene(login);
    	stage.show();
    	
    }

    public static void switchScenes(Scene scene) {
    	stage.setScene(scene);
    }
    
    public static String hashPassword(String password) {
    	byte[] 	bytesOfPassword = password.getBytes(StandardCharsets.UTF_8);
    	byte[] md5Digest = new byte[0];
    	try {
    		md5Digest = MessageDigest.getInstance("MD5").digest(bytesOfPassword);
    	}
    	catch(NoSuchAlgorithmException e) {
    		return null;
    	}
    	return Base64.getEncoder().encodeToString(md5Digest);
    }
    
    public static void playErrorSound() throws MalformedURLException  {
		media = new Media(pathToUrl("effects","error.mp3"));
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
    }
    
    public static String pathToUrl(String filePath,String fileName) throws MalformedURLException {
    	String path = "assets\\"+filePath+"\\"+fileName;
    	return new File(path).toURI().toURL().toString();
    }
    
    public static void Wait() {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			int counter = BLOCK_TIME;
			@Override
			
			public void run() {
				if(counter>0) {
					timeIsOver = false;
					counter--;
				
				}
				
				else {
					timeIsOver = true;
					timer.cancel();
				}
			}	
		};
		timer.scheduleAtFixedRate(task, 0, 1000);
    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            @SuppressWarnings("unused")
			double d = Double.parseDouble(strNum);
            
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
}