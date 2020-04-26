package util.audio;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

public enum SoundEffect {
    FALL("funny_fall1.wav"),
    FELL(""),
    EXPLOSION("");
   
   public enum Volume {
      MUTE, LOW, MEDIUM, HIGH
   }
   
   public static Volume volume = Volume.LOW;
   
   private static final String PATH_TO_SOUNDS = "../resources/";
   private Clip clip;
   
   SoundEffect(String soundFileName) {
      try {
         URL url = this.getClass().getClassLoader().getResource(PATH_TO_SOUNDS + soundFileName);
         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
         clip = AudioSystem.getClip();
         clip.open(audioInputStream);
      } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
          util.log.GameLogger.warning(e.toString());
      }
   }
   
   public void play() {
      if (volume != Volume.MUTE) {
         if (clip.isRunning())
            clip.stop();
         clip.setFramePosition(0);
         clip.start();
      }
   }
   
   // Optional static method to pre-load all the sound files.
   static void init() {
      values(); // calls the constructor for all the elements
   }
}