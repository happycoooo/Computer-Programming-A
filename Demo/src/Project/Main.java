package Project;

import Project.view.ChessGameFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Music audioPlayWave = new Music("backGroundMusic.wav");// 开音乐 音樂名
        audioPlayWave.start();
        @SuppressWarnings("unused")
        int musicOpenLab = 1;

        SwingUtilities.invokeLater(() -> {
            ChessGameFrame mainFrame = new ChessGameFrame(1000, 760);
            mainFrame.setVisible(true);
        });
    }
}
