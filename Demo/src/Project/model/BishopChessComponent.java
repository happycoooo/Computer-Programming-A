package Project.model;
import Project.controller.ClickController;
import Project.view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * 这个类表示国际象棋里面的车
 */
public class BishopChessComponent extends ChessComponent {
    /**
     * 黑车和白车的图片，static使得其可以被所有车对象共享
     * <br>
     * FIXME: 需要特别注意此处加载的图片是没有背景底色的！！！
     */
    private static Image BISHOP_WHITE;
    private static Image BISHOP_BLACK;
    private String chessType;
    private String chessType2;

    /**
     * 车棋子对象自身的图片，是上面两种中的一种
     */
    private Image bishopImage;

    /**
     * 读取加载车棋子的图片
     *
     */
    public void loadResource() throws IOException {
        if (BISHOP_WHITE == null) {
            BISHOP_WHITE = ImageIO.read(new File("src/images/bishop-white-0.png"));
        }

        if (BISHOP_BLACK == null) {
            BISHOP_BLACK = ImageIO.read(new File("src/images/bishop-black-0.png"));
        }
    }


    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定rookImage的图片是哪一种
     *
     * @param color 棋子颜色
     */

    private void initiateBishopImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                bishopImage = BISHOP_WHITE;
            } else if (color == ChessColor.BLACK) {
                bishopImage = BISHOP_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BishopChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size,"Bishop");
        if(color == ChessColor.BLACK){
            chessType2 = "B";
        }else{
            chessType2 = "b";
        }
        initiateBishopImage(color);
    }



    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination){
        ChessboardPoint source = getChessboardPoint();
        //目前位置和目标位置的差距
        int r1=Math.abs(source.getX() - destination.getX());
        int r2=Math.abs(source.getY() - destination.getY());
        //保证在斜线上
        if( r1 != r2 ){
            return false;
        }
        //保证目标位置和原始位置不同
        if( r1==0 ){
            return false;
        }
        //判断路上是否有阻隔
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
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(bishopImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
    @Override
    public String getChessType2() {return chessType2;}
}
