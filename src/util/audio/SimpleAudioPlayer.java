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
	private Long currentFrame; 
	private Clip clip; 	
	private String status; 
	private static final String DEFAULT_FILE_PATH = "resources/funny_clip_1.wav"; 
	private String filePath;

	// constructor to initialize streams and clip 
	public SimpleAudioPlayer(String filePath) 
		throws UnsupportedAudioFileException, IOException, LineUnavailableException 
	{ 
		this.filePath = filePath;
		resetAudioStream();
	}

	public SimpleAudioPlayer()
		throws UnsupportedAudioFileException, IOException, LineUnavailableException  
	{
		this(DEFAULT_FILE_PATH);
	}
	
	public void play() 
	{ 
		clip.start(); 		
		status = "play"; 
	} 
	
	public void pause() 
	{ 
		if (status.equals("paused")) return;
		this.currentFrame = this.clip.getMicrosecondPosition(); 
		clip.stop(); 
		status = "paused"; 
	} 
	
    public void resumeAudio() 
        throws UnsupportedAudioFileException, IOException, LineUnavailableException 
	{ 
		if (status.equals("play")) return;
		clip.close(); 
		resetAudioStream(); 
		clip.setMicrosecondPosition(currentFrame); 
		this.play(); 
	} 
	
    public void restart() 
        throws IOException, LineUnavailableException, UnsupportedAudioFileException 
	{ 
		clip.stop(); 
		clip.close(); 
		resetAudioStream(); 
		currentFrame = 0L; 
		clip.setMicrosecondPosition(0); 
		this.play(); 
	} 
	
    public void stop()
	{ 
		currentFrame = 0L; 
		clip.stop(); 
		clip.close(); 
	} 
	
    public void jump(long c) 
        throws UnsupportedAudioFileException, IOException, LineUnavailableException 
	{ 
		if (c > 0 && c < clip.getMicrosecondLength()) { 
			clip.stop(); 
			clip.close(); 
			resetAudioStream(); 
			currentFrame = c; 
			clip.setMicrosecondPosition(c); 
			this.play(); 
		} 
		else restart();
	} 
	
    public void resetAudioStream() 
        throws UnsupportedAudioFileException, IOException, LineUnavailableException 
	{ 
        final AudioInputStream audioInputStream; 
		audioInputStream = AudioSystem.getAudioInputStream(
			new File(filePath).getAbsoluteFile()
		); 
		clip = AudioSystem.getClip(); 
		clip.open(audioInputStream);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public Long getCurrentFrame() {
		return currentFrame;
	}

	public String getStatus() {
		return status;
	}
} 
