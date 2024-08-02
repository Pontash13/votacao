package org.example.Controle;

import java.io.File;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundPlayer {

    private static final String urlSounds = "";

    private static void tocar(String som) {
        try {
            URL soundFile = SoundPlayer.class.getResource("/sounds/" + som);
            if (soundFile == null) {
                System.err.println("Arquivo de som não encontrado: " + som);
                return;
            }
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            // Reproduz o som
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void tocar_bip() {
        tocar("bip.wav");
    }

    public static void tocar_confirma() {
        tocar("confirma.wav");
    }
}  



