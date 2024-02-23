package Project.model;

import Project.controller.ClickController;
import Project.view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class KingChessComponent extends ChessComponent {
    private static Image King_WHITE;
    private static Image King_BLACK;
    private Image kingImage;
    private String chessType2;

    public void loadResource() throws IOException {
        if (King_WHITE == null) {
            King_WHITE = ImageIO.read(new File("src/images/king-white-0.png"));
        }

        if (King_BLACK == null) {
            King_BLACK = ImageIO.read(new File("src/images/king-black-0.png"));
        }
    }

    private void initiateKingImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                kingImage = King_WHITE;
            } else if (color == ChessColor.BLACK) {
                kingImage = King_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public KingChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size ,"King");
        if(color == ChessColor.BLACK){
            chessType2 = "K";
        }else{
            chessType2 = "k";
        }
        initiateKingImage(color);
    }

    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        int r1 = Math.abs(source.getX() - destination.getX());
        int r2 = Math.abs(source.getY() - destination.getY());
        if (r1 + r2 == 1) { //横和直着走
            if (r1==0) {
                if (!(chessComponents[destination.getX()][destination.getY()] instanceof EmptySlotComponent)) {
                    return false;
                }
            }
            else if (r2==0) {
                if (!(chessComponents[destination.getX()][destination.getY()] instanceof EmptySlotComponent)) {
                    return false;
                }
            }
            return true;
        }else if ( r1 == 1 && r2 == 1){ // 斜着走
                if(!(chessComponents[destination.getX()][destination.getY()] instanceof EmptySlotComponent)){
                    return false;
                }
            return true;
        }
        return false;
        }


    /**
     * 注意这个方法，每当窗体受到了形状的变化，或者是通知要进行绘图的时候，就会调用这个方法进行画图。
     *
     * @param g 可以类比于画笔
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(kingImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }

    @Override
    public String getChessType2() {return chessType2;}
}
