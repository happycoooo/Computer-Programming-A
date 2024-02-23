package Project.view;

import Project.controller.GameController;
import Project.model.ChessColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private static int HEIGTH;
    public final int CHESSBOARD_SIZE;
    private GameController gameController;
    private Image img=new ImageIcon("src/images/b3.jpg").getImage();
    private static Image img1=new ImageIcon("src/images/img.png").getImage();
    private static Image img2=new ImageIcon("src/images/img.png").getImage();

    private static JComponent statusPanel;
    private ChessGameFrame chessGameFrame;
    private static JLabel resultLabel = new JLabel("ready");

    public static JLabel statusLabel = new JLabel("white");

    public ChessGameFrame(int width, int height) {
        setTitle("国际象棋"); //设置标题
        this.WIDTH = width ;
        this.HEIGTH = height ;
        this.CHESSBOARD_SIZE = HEIGTH * 4 / 5 ;

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        addPanel();
        addJComoBox();
        addChessboard();
        addResetButton();
        addLoadButton();
        addLabel1();
        addResultLabel();
        addDownLoadButton();
    }

    private void addPanel() {
        try {
            img = ImageIO.read(new File("images/b3.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        statusPanel = new BackGroundPanel(img);
        statusPanel.setLocation(0,0);
        statusPanel.setSize(WIDTH, HEIGTH);
        statusPanel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(statusPanel);
    }

    private void addJComoBox(){
        // 需要选择的条目
        String[] listData = new String[]{"Background1", "Background2"};
        // 创建一个下拉列表框
        JComboBox<String> comboBox = new JComboBox<String>(listData);
        comboBox.setSelectedItem(0);
        statusPanel.add(comboBox);

    }



    /**
     * 在游戏面板中添加棋盘
     */

    public void addChessboard() {
        Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE);
        gameController = new GameController(chessboard);
        chessboard.setLocation(HEIGTH / 10, HEIGTH / 10);
        statusPanel.add(chessboard);
    }

    /**
     * 在游戏面板中添加标签，表示行棋方
     */

    public static JLabel addLabel1() {
        //statusLabel.setText("black");
        //statusLabel.setBackground(Color.white);
        //statusLabel.setOpaque(true);
        statusLabel.setLocation(HEIGTH, HEIGTH / 7 + 40);
        statusLabel.setSize(250, 60);
        statusLabel.setFont(new Font("Rockwell", Font.PLAIN, 20));
        statusLabel.setForeground(Color.WHITE);
        statusPanel.add(statusLabel);
        try {
            img1 = ImageIO.read(new File("src/images/img_16.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Icon icon = new ImageIcon(img1);
        statusLabel.setIcon(icon);
        statusLabel.setHorizontalTextPosition(JLabel.CENTER);
        return statusLabel;
    }


    public static JLabel addResultLabel(){
        resultLabel.setText("Start the game ! ");
        resultLabel.setLocation(HEIGTH,HEIGTH / 10 /5 +20 );
        resultLabel.setSize(200,60);
        resultLabel.setFont(new Font("Rockwell",Font.BOLD, 20));
        resultLabel.setForeground(Color.WHITE);
        statusPanel.add(resultLabel);
        try {
            img2 = ImageIO.read(new File("src/images/img_19.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Icon icon = new ImageIcon(img2);
        resultLabel.setIcon(icon);
        resultLabel.setHorizontalTextPosition(JLabel.CENTER);
        return resultLabel;

    }

    private void addResetButton() {
        JButton button = new JButton("Reset");
        //button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Hello, world!"));
        button.setLocation(HEIGTH, HEIGTH / 10 + 200);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        button.setForeground(Color.DARK_GRAY);
        statusPanel.add(button);
        try {
            img2 = ImageIO.read(new File("src/images/img_15.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Icon icon = new ImageIcon(img2);
        button.setIcon(icon);
        button.setHorizontalTextPosition(JLabel.CENTER);

        button.addActionListener(e -> {
            gameController.getChessboard().setStatus("");
            gameController.getChessboard().setWinner("");

            gameController.getChessboard().initiateEmptyChessboard();
            gameController.getChessboard().initRookOnBoard(0, 0, ChessColor.BLACK);
            gameController.getChessboard().initRookOnBoard(0, 8 - 1, ChessColor.BLACK);
            gameController.getChessboard().initRookOnBoard(8 - 1, 0, ChessColor.WHITE);
            gameController.getChessboard().initRookOnBoard(8 - 1, 8 - 1, ChessColor.WHITE);
            gameController.getChessboard().initKingOnBoard(0, 4, ChessColor.BLACK);
            gameController.getChessboard().initKingOnBoard(8 - 1, 4, ChessColor.WHITE);
            gameController.getChessboard().initQueenOnBoard(0, 3, ChessColor.BLACK);
            gameController.getChessboard().initQueenOnBoard(8 - 1, 3, ChessColor.WHITE);
            gameController.getChessboard().initBishopOnBoard(0, 2, ChessColor.BLACK);
            gameController.getChessboard().initBishopOnBoard(0, 8 - 3, ChessColor.BLACK);
            gameController.getChessboard().initBishopOnBoard(8 - 1, 2, ChessColor.WHITE);
            gameController.getChessboard().initBishopOnBoard(8 - 1, 8 - 3, ChessColor.WHITE);
            gameController.getChessboard().initKnightOnBoard(0,1,ChessColor.BLACK);
            gameController.getChessboard().initKnightOnBoard(0,6,ChessColor.BLACK);
            gameController.getChessboard().initKnightOnBoard(8 - 1,1,ChessColor.WHITE);
            gameController.getChessboard().initKnightOnBoard(8 - 1,6,ChessColor.WHITE);
            gameController.getChessboard().initPawnOnBoard(1,0,ChessColor.BLACK);
            gameController.getChessboard().initPawnOnBoard(1,1,ChessColor.BLACK);
            gameController.getChessboard().initPawnOnBoard(1,2,ChessColor.BLACK);
            gameController.getChessboard().initPawnOnBoard(1,3,ChessColor.BLACK);
            gameController.getChessboard().initPawnOnBoard(1,4,ChessColor.BLACK);
            gameController.getChessboard().initPawnOnBoard(1,5,ChessColor.BLACK);
            gameController.getChessboard().initPawnOnBoard(1,6,ChessColor.BLACK);
            gameController.getChessboard().initPawnOnBoard(1,7,ChessColor.BLACK);
            gameController.getChessboard().initPawnOnBoard(8 - 2,0,ChessColor.WHITE);
            gameController.getChessboard().initPawnOnBoard(8 - 2,1,ChessColor.WHITE);
            gameController.getChessboard().initPawnOnBoard(8 - 2,2,ChessColor.WHITE);
            gameController.getChessboard().initPawnOnBoard(8 - 2,3,ChessColor.WHITE);
            gameController.getChessboard().initPawnOnBoard(8 - 2,4,ChessColor.WHITE);
            gameController.getChessboard().initPawnOnBoard(8 - 2,5,ChessColor.WHITE);
            gameController.getChessboard().initPawnOnBoard(8 - 2,6,ChessColor.WHITE);
            gameController.getChessboard().initPawnOnBoard(8 - 2,7,ChessColor.WHITE);

            gameController.getChessboard().currentColor = ChessColor.WHITE;
            chessGameFrame.addLabel1().setText("It's WHITE's turn ! ");
            resultLabel.setText("Start the game ! ");
            repaint();
        });
    }

    private void addLoadButton() {
        JButton button = new JButton("Load");
        button.setLocation( HEIGTH, HEIGTH / 10 + 300 ) ;
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        button.setForeground(Color.DARK_GRAY);
        statusPanel.add(button);
        try {
            img2 = ImageIO.read(new File("src/images/img_15.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Icon icon = new ImageIcon(img2);
        button.setIcon(icon);
        button.setHorizontalTextPosition(JLabel.CENTER);

        button.addActionListener(e -> {
            System.out.println("Click load");
            String path = JOptionPane.showInputDialog(this,"Input Path here");
            gameController.loadGameFromFile(path);
        });
    }

    private void addDownLoadButton() {
        JButton button = new JButton("DownLoad");
        button.setLocation(HEIGTH, HEIGTH / 10 + 400);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        button.setForeground(Color.DARK_GRAY);
        statusPanel.add(button);
        try {
            img2 = ImageIO.read(new File("src/images/img_15.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Icon icon = new ImageIcon(img2);
        button.setIcon(icon);
        button.setHorizontalTextPosition(JLabel.CENTER);

        button.addActionListener(e -> {
            System.out.println("Click load");
            String path = JOptionPane.showInputDialog(this,"Input Path here");
            gameController.downLoadGameToFile(path);
        });
    }

    private void setChessGameFrame (ChessGameFrame chessGameFrame) {
        this.chessGameFrame = chessGameFrame;
    }

    public JComponent getStatusPanel() {
        return this.statusPanel;
    }

    public ChessGameFrame getChessGameFrame(){
        return this.chessGameFrame;
    }
}
