package Project.model;
import Project.controller.ClickController;
import Project.view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

    public class KnightChessComponent extends ChessComponent {

        private static Image Knight_WHITE;
        private static Image Knight_BLACK;
        private Image knightImage;
        private String chessType2;




        public void loadResource() throws IOException {
            if (Knight_WHITE == null) {
                Knight_WHITE = ImageIO.read(new File("src/images/knight-white-0.png"));
            }

            if (Knight_BLACK == null) {
                 Knight_BLACK = ImageIO.read(new File("src/images/knight-black-0.png"));
            }
        }


        /**
         * 在构造棋子对象的时候，调用此方法以根据颜色确定rookImage的图片是哪一种
         *
         * @param color 棋子颜色
         */

        private void initiateKnightImage(ChessColor color) {
            try {
                loadResource();
                if (color == ChessColor.WHITE) {
                    knightImage = Knight_WHITE;
                } else if (color == ChessColor.BLACK) {
                    knightImage = Knight_BLACK;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public KnightChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
            super(chessboardPoint, location, color, listener, size,"Knight");
            if(color == ChessColor.BLACK){
                chessType2 = "N";
            }else{
               chessType2 = "n";
            }
            initiateKnightImage(color);
        }

        public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
            ChessboardPoint source = getChessboardPoint();
            int r = Math.abs(source.getX() - destination.getX()); //列的差距
            int c = Math.abs(source.getY() - destination.getY()); //行的差距
            if ((r == 1 && c == 2) || (r == 2 && c == 1)) { // 判断是否是2和1或1和2
                return true;
            }
            else return false;
        }


        /**
         * 注意这个方法，每当窗体受到了形状的变化，或者是通知要进行绘图的时候，就会调用这个方法进行画图。
         *
         * @param g 可以类比于画笔
         */
        @Override
        protected void paintComponent (Graphics g) {
            super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
            g.drawImage(knightImage, 0, 0, getWidth() , getHeight(), this);
            g.setColor(Color.BLACK);
            if (isSelected()) { // Highlights the model if selected.
                g.setColor(Color.red);
                g.drawOval(0, 0, getWidth() , getHeight());
            }
        }
        @Override
        public String getChessType2() {return chessType2;}

    }
