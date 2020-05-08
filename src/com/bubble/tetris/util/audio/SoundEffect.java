package com.bubble.tetris.util.audio;

import java.io.*;
import javax.sound.sampled.*;

public enum SoundEffect {
   BAW("baw"),
   DASH("dash1"),
   FELL("fell1"),
   GAMEOVER("gameover"),
   POOF("poof"),
   STACK("stack"),
   EXPLOSION("explosion");
   
   public enum Volume {
      MUTE, LOW, MEDIUM, HIGH
   }
   
   public static Volume volume = Volume.LOW;
   
   private static final String PATH_TO_SOUNDS = "resources/";
   private static final String FORMAT = ".wav";
   private Clip clip;
   
   private SoundEffect(String soundFileName) {
      try {
         File url = new File(PATH_TO_SOUNDS+soundFileName+FORMAT).getAbsoluteFile();
         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
         clip = AudioSystem.getClip();
         clip.open(audioInputStream);
      } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
          com.bubble.tetris.util.log.GameLogger.warning(e.toString());
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
   public void stop() {
      clip.stop();
      clip.setFramePosition(0);
   }
   
   // Optional static method to pre-load all the sound files.
   public static void init() {
      values(); // calls the constructor for all the elements
   }
}