package Project.controller;
import java.awt.*;
import java.util.ArrayList;

import Project.model.ChessComponent;
import Project.view.Chessboard;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import Project.model.ChessColor;

import javax.swing.*;

import static Project.view.Chessboard.*;

public class GameController {
    private Chessboard chessboard;


    public GameController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }
    public List<String> loadGameFromFile(String path) {
        JFrame f = new JFrame(); // add new frame to provide promote options
        f.setLayout(new FlowLayout());
        f.setSize(350, 350);
        f.setLocation(500,300);
        Dimension a = new Dimension(200, 200);
        JLabel error1 = new JLabel();
        JLabel error2 = new JLabel();
        JLabel error3 = new JLabel();
        JLabel error4 = new JLabel();
        f.add(error1);
        f.add(error2);
        f.add(error3);
        f.add(error4);

        int r = 1;
        //文件格式错误
        String pathName = path.substring(path.lastIndexOf('\\')+1);

        if(!pathName.substring(pathName.lastIndexOf('.')).equals(".txt")) {
            System.out.println("false");
            error4.setText("错误编码： 104\n");
            f.setVisible(true);
            r = 0;

        }else {
            try {
                List<String> chessData = Files.readAllLines(Paths.get(path));
                //棋盘错误
                if (!Chessboard.isValid1(chessData)) {
                    error1.setText("错误编码： 101\n");
                    r = 0;
                }
                //棋子错误
                else if (!Chessboard.isValid2(chessData)) {
                    error2.setText("错误编码： 102\n");
                    r = 0;
                }
                //缺少行棋方
                else if (!Chessboard.isValid3(chessData)) {
                    error3.setText("错误编码： 103\n");
                    r = 0;
                }

                if (r == 1) {
                    chessboard.loadGame(chessData);
                    return chessData;
                }
                f.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            return null;
    }

    public void downLoadGameToFile (String path) {
        List<String> chessDate = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            StringBuilder s = new StringBuilder();
            for (int j = 0; j < 8; j++) {
                s.append(saveChessBoard[i][j].getChessType2());
            }
            chessDate.add(String.valueOf(s));
            chessDate.add("\n");
        } if (currentColor== ChessColor.BLACK){
            chessDate.add("b");
        }else {
            chessDate.add("w");
        }
        chessboard.downLoadGame(chessDate,path);
    }

    public Chessboard getChessboard(){
        return chessboard;
    }

}


