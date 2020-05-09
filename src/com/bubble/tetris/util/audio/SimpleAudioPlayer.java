package com.bubble.tetris.util.audio;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException; 

public class SimpleAudioPlayer 
{
	private final Clip clip;

	// constructor to initialize streams and clip 
	public SimpleAudioPlayer(String filePath) 
		throws UnsupportedAudioFileException, IOException, LineUnavailableException 
	{ 
        clip = AudioSystem.getClip(); 
		resetAudioStream(filePath);
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
	
	public void reset() {
		pause();
		clip.setFramePosition(0);
	}

    public void resetAudioStream(String filePath) 
        throws UnsupportedAudioFileException, IOException, LineUnavailableException 
	{ 
        final AudioInputStream audioInputStream; 
		audioInputStream = AudioSystem.getAudioInputStream(
			// new File(filePath).getAbsoluteFile()
			this.getClass().getResource(filePath)
        ); 
		clip.open(audioInputStream);
	}

	public boolean isPlaying() {
		return clip.isActive();
	}
} 
