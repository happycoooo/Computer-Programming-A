package Project.model;
import Project.controller.ClickController;
import Project.view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PawnChessComponent extends ChessComponent {

    private static Image Pawn_WHITE;
    private static Image Pawn_BLACK;
    private Image pawnImage;
    public String chessType2;



    public void loadResource() throws IOException {
        if (Pawn_WHITE == null) {
            Pawn_WHITE = ImageIO.read(new File("src/images/pawn-white-0.png"));
        }

        if (Pawn_BLACK == null) {
            Pawn_BLACK = ImageIO.read(new File("src/images/pawn-black-0.png"));
        }
    }

    private void initiatePawnImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                pawnImage = Pawn_WHITE;
            } else if (color == ChessColor.BLACK) {
                pawnImage = Pawn_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PawnChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size, "Pawn");
        if(color == ChessColor.BLACK){
            chessType2 = "P";
        }else{
            chessType2 = "p";
        }
        initiatePawnImage(color);
    }

    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        //if (source.getX() == destination.getX() && source.getY() == destination.getY()) { return false; }

        //白棋的走法
        if (chessColor == ChessColor.WHITE) {
            // 斜线吃子
            if (source.getX() - destination.getX() == 1 && Math.abs(destination.getY() - source.getY()) == 1 && (!(chessComponents[destination.getX()][destination.getY()] instanceof EmptySlotComponent))){
                return true;
            }
            // 下方白棋处于初始位置
            if (source.getX() == 6) {
                // 向前一步或者两步
                if (destination.getY() == source.getY()) {
                    if (destination.getX() == 5) {
                        if (chessComponents[5][source.getY()] instanceof EmptySlotComponent)
                            return true;
                    }
                    if (destination.getX() == 4) {
                        if (chessComponents[5][source.getY()] instanceof EmptySlotComponent && chessComponents[4][source.getY()] instanceof EmptySlotComponent)
                            return true;
                    }
                }
            }
            else{ // 白棋不在初始位置
                if(source.getX() - destination.getX() == 1 && destination.getY() == source.getY() && (chessComponents[destination.getX()][destination.getY()] instanceof EmptySlotComponent)) {
                    return true;
                }
            }
            return false;
        }
        //黑棋的走法
        if (chessColor == ChessColor.BLACK) {
            if (source.getX() - destination.getX() == -1 && Math.abs(destination.getY() - source.getY()) == 1 && (!(chessComponents[destination.getX()][destination.getY()] instanceof EmptySlotComponent))){
                return true;
            }
            // 下方黑棋处于初始位置
            if (source.getX() == 1) {
                // 向前一步或者两步
                if (destination.getY() == source.getY()) {
                    if (destination.getX() == 2) {
                        if (chessComponents[2][source.getY()] instanceof EmptySlotComponent)
                            return true;
                    }
                    if (destination.getX() == 3) {
                        if (chessComponents[2][source.getY()] instanceof EmptySlotComponent && chessComponents[3][source.getY()] instanceof EmptySlotComponent)
                            return true;
                    }
                }
            }
            else {
                if (source.getX() - destination.getX() == -1 && destination.getY() == source.getY() && (chessComponents[destination.getX()][destination.getY()] instanceof EmptySlotComponent)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(pawnImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
    @Override
    public String getChessType2() {return chessType2;}
}
