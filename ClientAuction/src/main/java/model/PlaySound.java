package model;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 *
 * @author Nathan
 */
public class PlaySound {

    private Clip clip;

    public void playBip() {
        try {
            URL audioUrl = getClass().getResource("/sounds/bipSound.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioUrl);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Erro ao reproduzir som: " + e.getMessage());
        }
    }

    public void auctionStarted() {
        try {
            URL audioUrl = getClass().getResource("/sounds/auctionStarted.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioUrl);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Erro ao reproduzir som: " + e.getMessage());
        }
    }

    public void newItem() {
        new Thread(() -> {
            try {
                URL audioUrl = getClass().getResource("/sounds/newItem.wav");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioUrl);
                AudioFormat format = audioStream.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                clip = (Clip) AudioSystem.getLine(info);
                clip.open(audioStream);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.err.println("Erro ao reproduzir som: " + e.getMessage());
            }
        }).start();
    }

    public void newBuyer() {
        try {
            URL audioUrl = getClass().getResource("/sounds/newBuyer.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioUrl);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Erro ao reproduzir som: " + e.getMessage());
        }
    }
}
