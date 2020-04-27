package util.audio;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException; 

public class SimpleAudioPlayer 
{
	private Clip clip;
	private static final String DEFAULT_FILE_PATH = "resources/funny_clip_1.wav"; 

	public SimpleAudioPlayer(String filePath) 
		throws UnsupportedAudioFileException, IOException, LineUnavailableException 
	{ 
		clip = AudioSystem.getClip();
		resetAudioStream(filePath);
		play();
	}

	public SimpleAudioPlayer()
		throws UnsupportedAudioFileException, IOException, LineUnavailableException  
	{
		this(DEFAULT_FILE_PATH);
	}
	
	public void play() {
		clip.start(); 	
		clip.loop(Clip.LOOP_CONTINUOUSLY);	
	} 
	
	public void pause() {
		clip.stop();
	} 
	
    public void stop() {
		clip.stop(); 
		clip.close(); 
	} 
	
    public void resetAudioStream(String filePath) 
        throws UnsupportedAudioFileException, IOException, LineUnavailableException 
	{ 
        final AudioInputStream audioInputStream; 
		audioInputStream = AudioSystem.getAudioInputStream(
			new File(filePath).getAbsoluteFile()
		); 
		clip.open(audioInputStream);
	}

	public boolean isPlaying() {
		return clip.isActive();
	}
} 
