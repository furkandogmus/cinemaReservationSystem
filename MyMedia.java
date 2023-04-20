
import java.net.MalformedURLException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class MyMedia {
	Media media;
	MediaPlayer mediaPlayer;
	MediaView mediaView;
	
	public MyMedia(String name,int width,int height) throws MalformedURLException {
		media = new Media(Main.pathToUrl("trailers", ((Film)Main.backup.getItem(name)).trailPath));
		mediaPlayer = new MediaPlayer(media);
		mediaView = new MediaView(mediaPlayer);
		mediaView.setFitWidth(width);
		mediaView.setFitHeight(height);
	}
}
