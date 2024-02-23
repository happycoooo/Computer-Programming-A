package Project.model;

import Project.controller.ClickController;
import Project.view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class QueenChessComponent extends ChessComponent {

    private static Image QUEEN_WHITE;
    private static Image QUEEN_BLACK;
    private Image queenImage;
    private String chessType2;


    public void loadResource() throws IOException {
        if (QUEEN_WHITE == null) {
            QUEEN_WHITE = ImageIO.read(new File("src/images/queen-white-0.png"));
        }

        if (QUEEN_BLACK == null) {
            QUEEN_BLACK = ImageIO.read(new File("src/images/queen-black-0.png"));
        }
    }


    private void initiateQueenImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                queenImage = QUEEN_WHITE;
            } else if (color == ChessColor.BLACK) {
                queenImage = QUEEN_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public QueenChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size,"Queen");
        if(color == ChessColor.BLACK){
            chessType2 = "Q";
        }else{
            chessType2 = "q";
        }
        initiateQueenImage(color);
    }

    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        //象的方法
        int r1=Math.abs(source.getX() - destination.getX());
        int r2=Math.abs(source.getY() - destination.getY());
        //两种情况：斜线上或者直线上，先判断，若是走斜线，用象的方式；若是走直线，用车的方式
        //判断在斜线上
        if( r1 == r2 ){
            if( r1 == 0 ){ //确保目标位置不在原来的位置
                return false;
            }else{
                int dx = (destination.getX() - source.getX())/Math.abs(destination.getX() - source.getX());
                int dy = (destination.getY() - source.getY())/Math.abs(destination.getY() - source.getY());
                int i = source.getX() + dx;
                int j = source.getY() + dy;
                while(i != destination.getX()){
                    if(!(chessComponents[i][j] instanceof EmptySlotComponent)){
                        return false;
                    }
                    i += dx;
                    j += dy;
                }
                }
            } else if (source.getX() == destination.getX()) {
            int row = source.getX();
            for (int col = Math.min(source.getY(), destination.getY()) + 1; col < Math.max(source.getY(), destination.getY()); col++) {
                if (!(chessComponents[row][col] instanceof EmptySlotComponent)) {
                    return false;
                }
            }
        } else if (source.getY() == destination.getY()) {
            int col = source.getY();
            for (int row = Math.min(source.getX(), destination.getX()) + 1;
                 row < Math.max(source.getX(), destination.getX()); row++) {
                if (!(chessComponents[row][col] instanceof EmptySlotComponent)) {
                    return false;
                }
            }
        }
        else { // Not on the same row or the same column.
            return false;
        }
        return true;
    }


    /**
     * 注意这个方法，每当窗体受到了形状的变化，或者是通知要进行绘图的时候，就会调用这个方法进行画图。
     *
     * @param g 可以类比于画笔
     */


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(queenImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(queenImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
    @Override
    public String getChessType2() {return chessType2;}
}
