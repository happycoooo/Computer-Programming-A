package Project.view;

import Project.model.*;
import Project.controller.ClickController;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;



/**
 * 这个类表示面板上的棋盘组件对象
 */
public class Chessboard extends JPanel {
    /**
     * CHESSBOARD_SIZE： 棋盘是8 * 8的
     * <br>
     * BACKGROUND_COLORS: 棋盘的两种背景颜色
     * <br>
     * chessListener：棋盘监听棋子的行动
     * <br>
     * A5.chessboard: 表示8 * 8的棋盘
     * <br>
     * currentColor: 当前行棋方
     */
    private static final int CHESSBOARD_SIZE = 8;
    public static final ChessComponent[][] chessComponents = new ChessComponent[CHESSBOARD_SIZE][CHESSBOARD_SIZE];
    public static ChessColor currentColor = ChessColor.WHITE;
    //all chessComponents in this A5.chessboard are shared only one model controller
    private final ClickController clickController = new ClickController(this);
    private final int CHESS_SIZE;
    private String winner = "";
    private ChessGameFrame chessGameFrame;
    public static ChessComponent[][] saveChessBoard;

    public void setSaveChessBoard (){
        saveChessBoard = chessComponents;
    }

    public Chessboard(int width, int height) {
        setLayout(null); // Use absolute layout.
        setSize(width, height);
        CHESS_SIZE = width / 8;
        System.out.printf("A5.chessboard size = %d, chess size = %d\n", width, CHESS_SIZE);
        initiateEmptyChessboard();

        // FIXME: Initialize A5.chessboard for testing only.
        initRookOnBoard(0, 0, ChessColor.BLACK);
        initRookOnBoard(0, CHESSBOARD_SIZE - 1, ChessColor.BLACK);
        initRookOnBoard(CHESSBOARD_SIZE - 1, 0, ChessColor.WHITE);
        initRookOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 1, ChessColor.WHITE);
        initKingOnBoard(0, 4, ChessColor.BLACK);
        initKingOnBoard(CHESSBOARD_SIZE - 1, 4, ChessColor.WHITE);
        initQueenOnBoard(0, 3, ChessColor.BLACK);
        initQueenOnBoard(CHESSBOARD_SIZE - 1, 3, ChessColor.WHITE);
        initBishopOnBoard(0, 2, ChessColor.BLACK);
        initBishopOnBoard(0, CHESSBOARD_SIZE - 3, ChessColor.BLACK);
        initBishopOnBoard(CHESSBOARD_SIZE - 1, 2, ChessColor.WHITE);
        initBishopOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 3, ChessColor.WHITE);
        initKnightOnBoard(0,1,ChessColor.BLACK);
        initKnightOnBoard(0,6,ChessColor.BLACK);
        initKnightOnBoard(CHESSBOARD_SIZE - 1,1,ChessColor.WHITE);
        initKnightOnBoard(CHESSBOARD_SIZE - 1,6,ChessColor.WHITE);
        initPawnOnBoard(1,0,ChessColor.BLACK);
        initPawnOnBoard(1,1,ChessColor.BLACK);
        initPawnOnBoard(1,2,ChessColor.BLACK);
        initPawnOnBoard(1,3,ChessColor.BLACK);
        initPawnOnBoard(1,4,ChessColor.BLACK);
        initPawnOnBoard(1,5,ChessColor.BLACK);
        initPawnOnBoard(1,6,ChessColor.BLACK);
        initPawnOnBoard(1,7,ChessColor.BLACK);
        initPawnOnBoard(CHESSBOARD_SIZE - 2,0,ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2,1,ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2,2,ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2,3,ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2,4,ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2,5,ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2,6,ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2,7,ChessColor.WHITE);

        setSaveChessBoard();
    }

    public ChessComponent[][] getChessComponents() {
        return chessComponents;
    }

    public ChessColor getCurrentColor() {
        return currentColor;
    }

    public void putChessOnBoard(ChessComponent chessComponent) {
        int row = chessComponent.getChessboardPoint().getX(), col = chessComponent.getChessboardPoint().getY();

        if (chessComponents[row][col] != null) {
            remove(chessComponents[row][col]);
        }
        add(chessComponents[row][col] = chessComponent);

        setSaveChessBoard();
    }

    public void swapChessComponents(ChessComponent chess1, ChessComponent chess2) {
        // Note that chess1 has higher priority, 'destroys' chess2 if exists.
        if (!(chess2 instanceof EmptySlotComponent)) {
            remove(chess2);
            add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation(), clickController, CHESS_SIZE));
        }
        chess1.swapLocation(chess2);
        int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
        chessComponents[row1][col1] = chess1;
        int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
        chessComponents[row2][col2] = chess2;

        chess1.repaint();
        chess2.repaint();
        setSaveChessBoard();
    }

    public void initiateEmptyChessboard() {
        for (int i = 0; i < chessComponents.length; i++) {
            for (int j = 0; j < chessComponents[i].length; j++) {
                putChessOnBoard(new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j), clickController, CHESS_SIZE));
            }
        }
    }

    public void swapColor() {
        currentColor = currentColor == ChessColor.BLACK ? ChessColor.WHITE : ChessColor.BLACK;
        if(getCurrentColor().equals(ChessColor.BLACK)){
            chessGameFrame.addLabel1().setText("It's BLACK's turn ! ");
        }else{
            chessGameFrame.addLabel1().setText("It's WHITE's turn ! ");
        }
        setSaveChessBoard();
    }

    public void initRookOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new RookChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE );
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    public void initKingOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new KingChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    public void initQueenOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new QueenChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    public void initBishopOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new BishopChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    public void initKnightOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new KnightChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    public void initPawnOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new PawnChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    public ChessGameFrame getChessGameFrame() {
        return this.chessGameFrame;
    }

    public String getWinner() {
        return this.winner;
    }

    public ClickController getClickController() {
        return this.clickController;
    }

    public int getCHESS_SIZE() {
        return this.CHESS_SIZE;
    }

    public void setWinner(String text) {
        this.winner = text;
    }

    public void setStatus(String text) {
        chessGameFrame.addResultLabel().setText(text);
    }

    public int getChessSize() { return CHESS_SIZE; }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }


    public Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE, row * CHESS_SIZE);
    }

    //棋盘错误
    public static boolean isValid1 (List<String> chessData) {
        if (chessData.size() - 1 == 8) { //行数为9
            for (int i = 0; i < chessData.size() - 1; i++) {
                if (chessData.get(i).length() != 8) {
                    return false;
                }
            }
        }else{
            if(chessData.get(chessData.size()-1).charAt(0) == 'w' || chessData.get(chessData.size()-1).charAt(0) == 'b')
            return false;
        }
        return true;
    }

    //棋子错误
    public static boolean isValid2( List<String> chessData) {
        int counter = 1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                char chess = chessData.get(i).charAt(j);
                if (chess != 'B' && chess != 'b' && chess != 'Q' && chess != 'q' && chess != 'P' && chess != 'p' && chess != 'R' && chess != 'r'
                        && chess != 'N' && chess != 'n' && chess != 'K' && chess != 'k' && chess != '_')
                    counter = 0;
            }
            if (counter == 0) {
                return false;
            }
        }
        return true;
    }

    //缺少行棋方
        public static boolean isValid3 (List<String> chessData) {
        if(chessData.get(chessData.size()-1).charAt(0)== 'b' ){
            return true;
        }else if(chessData.get(chessData.size()-1).charAt(0) == 'w' ){
            return true;
        }else{
            return false;
        }
        }

    public void loadGame(List<String> chessData) {
        initiateEmptyChessboard();
        ChessComponent chessComponent;
        for(int i = 0 ; i < chessData.size() ; i ++ ) {
            for (int j = 0; j < chessData.get(i).length(); j++) {
                if(chessData.get(i).charAt(j) == 'n') {
                    chessComponent = new KnightChessComponent(new ChessboardPoint(i,j), calculatePoint(i,j),ChessColor.WHITE, clickController, CHESS_SIZE);
                    chessComponent.setVisible(true);
                    putChessOnBoard(chessComponent);
                }
                if(chessData.get(i).charAt(j) == 'k') {
                    chessComponent = new KingChessComponent(new ChessboardPoint(i,j), calculatePoint(i,j),ChessColor.WHITE, clickController, CHESS_SIZE);
                    chessComponent.setVisible(true);
                    putChessOnBoard(chessComponent);
                }
                if(chessData.get(i).charAt(j) == 'b') {
                    chessComponent = new BishopChessComponent(new ChessboardPoint(i,j), calculatePoint(i,j),ChessColor.WHITE, clickController, CHESS_SIZE);
                    chessComponent.setVisible(true);
                    putChessOnBoard(chessComponent);
                }
                if(chessData.get(i).charAt(j) == 'q') {
                    chessComponent = new QueenChessComponent(new ChessboardPoint(i,j), calculatePoint(i,j),ChessColor.WHITE, clickController, CHESS_SIZE);
                    chessComponent.setVisible(true);
                    putChessOnBoard(chessComponent);
                }
                if(chessData.get(i).charAt(j) == 'r') {
                    chessComponent = new RookChessComponent(new ChessboardPoint(i,j), calculatePoint(i,j),ChessColor.WHITE, clickController, CHESS_SIZE);
                    chessComponent.setVisible(true);
                    putChessOnBoard(chessComponent);
                }
                if(chessData.get(i).charAt(j) == 'p') {
                    chessComponent = new PawnChessComponent(new ChessboardPoint(i,j), calculatePoint(i,j),ChessColor.WHITE, clickController, CHESS_SIZE);
                    chessComponent.setVisible(true);
                    putChessOnBoard(chessComponent);
                }

                if(chessData.get(i).charAt(j) == 'N') {
                    chessComponent = new KnightChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);
                    chessComponent.setVisible(true);
                    putChessOnBoard(chessComponent);
                }
                if(chessData.get(i).charAt(j) == 'K') {
                    chessComponent = new KingChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);
                    chessComponent.setVisible(true);
                    putChessOnBoard(chessComponent);
                }
                if(chessData.get(i).charAt(j) == 'B') {
                    chessComponent = new BishopChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);
                    chessComponent.setVisible(true);
                    putChessOnBoard(chessComponent);
                }
                if(chessData.get(i).charAt(j) == 'Q') {
                    chessComponent = new QueenChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);
                    chessComponent.setVisible(true);
                    putChessOnBoard(chessComponent);
                }
                if(chessData.get(i).charAt(j) == 'R') {
                    chessComponent = new RookChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);
                    chessComponent.setVisible(true);
                    putChessOnBoard(chessComponent);
                }
                if(chessData.get(i).charAt(j) == 'P') {
                    chessComponent = new PawnChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);
                    chessComponent.setVisible(true);
                    putChessOnBoard(chessComponent);
                }

                if(chessData.get(i).charAt(j) == '_') {
                    chessComponent = new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j),clickController, CHESS_SIZE);
                    chessComponent.setVisible(true);
                    putChessOnBoard(chessComponent);
                }
                if(chessData.get(8).charAt(0) == 'w'){
                    currentColor = ChessColor.WHITE;
                }
                if(chessData.get(8).charAt(0) == 'b'){
                    currentColor = ChessColor.BLACK;
                }
            }
        }
        chessGameFrame.addLabel1().setText("It's "+currentColor.getName()+"'s turn ! ");
        repaint();
    }


    public void downLoadGame(List<String> chessData, String path) {
//        String fileName = path.substring(path.lastIndexOf('/') + 1);
        String pathName = path.substring(0, path.lastIndexOf('\\'));
//        while (!fileName.substring(fileName.lastIndexOf('.')).equals(".txt")) {
//            path = JOptionPane.showInputDialog(this, "Wrong path form,please input a new path correctly!");
//            fileName = path.substring(path.lastIndexOf('/') + 1);
//        }

//        File directories = new File(pathName);
//        if (!directories.exists()) {
//            directories.mkdirs();
//        }
//        FileWriter writer;
//        try {
//            writer = new FileWriter(path);
//            writer.write("");
//            for (int i = 0; i < chessData.size(); i++) {
//                writer.write("" + chessData.get(i));
//            }
//            writer.flush();
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try{
            File file=new File(pathName);
            if(!file.exists())
                file.createNewFile();
        } catch(IOException e){
            e.printStackTrace();
        }

        try{
            FileWriter f=new FileWriter(path);
            f.write("");
            for (int i = 0; i < chessData.size(); i++) {
                f.write(chessData.get(i));
            }
            f.flush();
            f.close();
        } catch(IOException e){
            e.printStackTrace();
        }


    }
}
