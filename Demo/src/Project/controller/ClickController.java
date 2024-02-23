package Project.controller;

import Project.Music;
import Project.Music1;
import Project.model.*;
import Project.view.ChessGameFrame;
import Project.view.Chessboard;
import Project.view.ChessboardPoint;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class ClickController {
    private final Chessboard chessboard;
    private ChessComponent first;
    private int clickCount = 0;
    private static ArrayList<ChessboardPoint[]> recordedmove = new ArrayList<ChessboardPoint[]>();
    private String winner;
    private ChessComponent savedChess;
    private ChessGameFrame chessGameFrame;

    public ClickController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public void onClick(ChessComponent chessComponent) {
        winner = chessboard.getWinner();
        //checkWinner();

        if (first == null) {
            if (handleFirst(chessComponent)) {
                chessComponent.setSelected(true);
                first = chessComponent;
                first.repaint();
                System.out.println(first.getChessType());
                System.out.println(first.getChessType2());
            }
        } else {
            if (first == chessComponent) { // 再次点击取消选取
                chessComponent.setSelected(false);
                ChessComponent recordFirst = first;
                first = null;
                recordFirst.repaint();


            } else if (handleSecond(first, chessComponent) ) {
                Music1 audioPlayWave = new Music1("musicInChess.wav");// 开音乐 音樂名
                audioPlayWave.start();
                @SuppressWarnings("unused")
                int musicOpenLab = 1;

                //repaint in swap chess method.
                if (!first.getMoved()) { first.updateMoved(); }

                JFrame f = new JFrame(); // add new frame to provide promote options
                f.setLayout(new FlowLayout());
                f.setSize(350, 350);
                //Dimension a = new Dimension(200, 200);
                f.setLocation(500,300);
                JLabel result = new JLabel();
                f.add(result);

                if (chessComponent.getChessType() == "King") {  // assign winner, maybe add gui later
                    if (chessboard.getCurrentColor().getName() == "White") {
                        winner = "White";
                        chessboard.setWinner(winner);
                        chessboard.setStatus("WHITE win ! ");
                        result.setText("WHITE win ! ");
                        f.setVisible(true);
                        chessGameFrame.addChessboard();
                    } else {
                        winner = "Black";
                        chessboard.setWinner(winner);
                        chessboard.setStatus("BLACK win ! ");
                        result.setText("BLACK win ! ");
                        f.setVisible(true);
                        chessGameFrame.addChessboard();
                    }
                }
                chessboard.swapChessComponents(first, chessComponent);
                chessboard.swapColor();
                ChessboardPoint[] lastMove = new ChessboardPoint[2]; // 记录上一次走的位置
                lastMove[0] = chessComponent.getChessboardPoint();
                lastMove[1] = first.getChessboardPoint();
                recordedmove.add(lastMove);
                clickCount += 1;

                if (pawnPromote(first)) { // 兵升变
                    promote(first);
                }
                first.setSelected(false);
                first = null;
            } else if (pawnPass(first, chessComponent)) { // pawnPass 判定是否满足吃过路兵
                executePawn(chessComponent);
            } else if (canShift(first, chessComponent)) { // 判定是否满足王车移位
                System.out.println("王车移位！！！");
                shiftKingRook(chessComponent);
            }
            updateResultLabel();

        }
    }

    /**
     * @param chessComponent 目标选取的棋子
     * @return 目标选取的棋子是否与棋盘记录的当前行棋方颜色相同
     */

    private boolean handleFirst(ChessComponent chessComponent) {
        return chessComponent.getChessColor() == chessboard.getCurrentColor();
    }

    /**
     * @param chessComponent first棋子目标移动到的棋子second
     * @return first棋子是否能够移动到second棋子位置
     */

    private boolean handleSecond(ChessComponent first, ChessComponent chessComponent) {
        return chessComponent.getChessColor() != first.getChessColor() &&
                first.canMoveTo(chessboard.getChessComponents(), chessComponent.getChessboardPoint());

    }

    private boolean isLegalMove(ChessComponent first, ChessComponent chessComponent) {
        return handleSecond(first, chessComponent) || pawnPass(first, chessComponent);
    }

    private void updateResultLabel () {
        if (winner == ""){
            if ( isCheckingBefore() ) {
                chessboard.setStatus("将军");
            } else {
                chessboard.setStatus("Continuing");
            }
        }
    }

    // 判断是否已经将死
    private boolean checkWinner() {
        if (isCheckingBefore()) {
            if (!canMoveKing()) {
                System.out.print("canMoveKing1 ");
                System.out.println(true);
                return true;
            }
            System.out.print("canMoveKing2 ");
            System.out.println(false);
            /*
            if (!canMoveKing() || canRemoveEnemy() || canBlockKing()) {
                return true;
            }
            */
        }
        return false;
    }

    private boolean canMoveKing() {
        System.out.println("try move king!");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessComponent currentChess = chessboard.getChessComponents()[i][j];
                if (currentChess.getChessType() == "King" && currentChess.getChessColor().getName() == chessboard.getCurrentColor().getName()) {

                    for (int x = 0; x < 8; x++) {
                        for (int y = 0; y < 8; y++) {
                            ChessComponent nextChess = chessboard.getChessComponents()[x][y];
                            System.out.print("x : ");
                            System.out.print(x);
                            System.out.print("Y : ");
                            System.out.print(y);
                            System.out.println(isLegalMove(currentChess, nextChess));
                            if (isLegalMove(currentChess, nextChess)) {
                                ChessboardPoint tempPoint = currentChess.getChessboardPoint();

                                tryMove(currentChess, nextChess);
                                nextChess = chessboard.getChessComponents()[tempPoint.getX()][tempPoint.getY()];

                                //System.out.println(chessboard.getCurrentColor().getName());
                                //System.out.print("! isCheckingBefore() ");
                                //System.out.println(! isCheckingBefore());
                                if ( ! isCheckingBefore()) {
                                    undo(currentChess, nextChess);
                                    return true;
                                }
                                undo( currentChess , nextChess);
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    // 被将军后尝试着走
    private void tryMove(ChessComponent start, ChessComponent end) {
        /*
        System.out.print(start.getChessboardPoint().getX());
        System.out.print(" start ");
        System.out.println(start.getChessboardPoint().getY());
        System.out.print(end.getChessboardPoint().getX());
        System.out.print(" end ");
        System.out.println(end.getChessboardPoint().getY()); */
        ChessboardPoint savePoint = end.getChessboardPoint();

        if (end instanceof  EmptySlotComponent) { System.out.println("empty");this.savedChess = new EmptySlotComponent(savePoint, chessboard.calculatePoint(savePoint.getX(), savePoint.getY()),chessboard.getClickController(), chessboard.getCHESS_SIZE());}
        else {
            if (end.getChessType() == "Knight") {
                this.savedChess = new KnightChessComponent( savePoint, chessboard.calculatePoint(savePoint.getX(), savePoint.getY()), end.getChessColor(), chessboard.getClickController(), chessboard.getCHESS_SIZE());
            } else if (end.getChessType() == "Queen") {
                this.savedChess = new QueenChessComponent( savePoint, chessboard.calculatePoint(savePoint.getX(), savePoint.getY()), end.getChessColor(), chessboard.getClickController(), chessboard.getCHESS_SIZE());
            } else if (end.getChessType() == "Bishop") {
                this.savedChess = new BishopChessComponent( savePoint, chessboard.calculatePoint(savePoint.getX(), savePoint.getY()), end.getChessColor(), chessboard.getClickController(), chessboard.getCHESS_SIZE());
            } else if (end.getChessType() == "Rook") {
                this.savedChess = new RookChessComponent( savePoint, chessboard.calculatePoint(savePoint.getX(), savePoint.getY()), end.getChessColor(), chessboard.getClickController(), chessboard.getCHESS_SIZE());
            }
        }
        chessboard.swapChessComponents(start, end);
        return;
    }

    // 悔棋，如果走后仍然被将军就后退一步
    public void undo(ChessComponent start, ChessComponent end) {
        chessboard.swapChessComponents(start, end);
        this.savedChess.setChessboardPoint(start.getChessboardPoint());
        chessboard.putChessOnBoard(this.savedChess);
        return;
    }

    // 走之前判断是否被将军
    private boolean isCheckingBefore() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessComponent currentChess = chessboard.getChessComponents()[i][j];
                if (currentChess.getChessType() == "King" && currentChess.getChessColor().getName() == chessboard.getCurrentColor().getName()) {
                    //System.out.println("Checking" + currentChess.getChessboardPoint());
                    for (int x = 0; x < 8; x++) {
                        for (int y = 0; y < 8; y++) {
                            ChessComponent enemyChess = chessboard.getChessComponents()[x][y];
                            if (enemyChess instanceof EmptySlotComponent) continue;
                            //System.out.println(enemyChess.getChessboardPoint());
                            //System.out.println(enemyChess.getChessType());
                            //System.out.println(enemyChess.getChessColor().getName());
                            //System.out.println(currentChess.getChessColor().getName());
                            //System.out.println(isLegalMove(enemyChess, currentChess));
                            if (enemyChess.getChessColor().getName() != currentChess.getChessColor().getName() && isLegalMove(enemyChess, currentChess)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    // 走之后判断是否还被将军，因为swapColor() 所以判定条件改变
    private boolean isCheckingAfter() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessComponent currentChess = chessboard.getChessComponents()[i][j];
                if (currentChess.getChessType() == "King" && currentChess.getChessColor().getName() != chessboard.getCurrentColor().getName()) {
                    for (int x = 0; x < 8; x++) {
                        for (int y = 0; y < 8; y++) {
                            ChessComponent enemyChess = chessboard.getChessComponents()[x][y];
                            if (enemyChess instanceof EmptySlotComponent) continue;
                            if (enemyChess.getChessColor().getName() != currentChess.getChessColor().getName()) {
                                return isLegalMove(enemyChess, currentChess);
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    //判断某个位置是否会被攻击
    private boolean isThreatened(ChessboardPoint chessboardPoint) { // determine if one location is threatened by enemy chess
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessComponent chessComponent = chessboard.getChessComponents()[i][j];
                if (!(chessComponent instanceof EmptySlotComponent) && chessComponent.getChessColor().getName() != chessboard.getCurrentColor().getName()) {
                    if (chessComponent.canMoveTo(chessboard.getChessComponents(), chessboardPoint)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //判断王车移位的条件是否满足
    private boolean canShift(ChessComponent first, ChessComponent chessComponent) { //first is King, chessComponent is target position
        ChessboardPoint targetPoint = chessComponent.getChessboardPoint();
        ChessComponent rook;
        if (first.getChessType() != "King" || targetPoint.getX() != first.getChessboardPoint().getX()) { return false; }
        int dy = targetPoint.getY() - first.getChessboardPoint().getY();
        if (Math.abs(dy) != 2) { return false; }
        if (dy > 0) {
            rook = chessboard.getChessComponents()[targetPoint.getX()][7];
        } else {
            rook = chessboard.getChessComponents()[targetPoint.getX()][0];
        }

        if (rook.getChessType() != "Rook" || first.getMoved() || rook.getMoved()) { // 王和车不能被移动过
            return false;
        }

        dy = dy / Math.abs(dy);

        if (isThreatened(first.getChessboardPoint())) { return false; } //王不能被将军
        if (isThreatened(chessComponent.getChessboardPoint())) { return false; } //王到达的位置不能被将军

        for (int y = first.getChessboardPoint().getY() + dy; y != rook.getChessboardPoint().getY(); y += dy) {
            ChessComponent currentChess = chessboard.getChessComponents()[targetPoint.getX()][y];
            if (!( currentChess instanceof EmptySlotComponent)) { // 王和车之间不能被阻挡
                return false;
            } else if (isThreatened(currentChess.getChessboardPoint())) { // 王经过的位置不能受其他棋子攻击
                return false;
            }
        }
        return true;
    }

    // 执行王车移位
    private void shiftKingRook(ChessComponent chessComponent) {
        ChessboardPoint targetPoint = chessComponent.getChessboardPoint();
        // target position for rook
        ChessComponent targetRookComponent = chessboard.getChessComponents()[targetPoint.getX()][(first.getChessboardPoint().getY() + targetPoint.getY())/2];
        ChessComponent rook;
        int dy = targetPoint.getY() - first.getChessboardPoint().getY();
        if (dy > 0) {
            rook = chessboard.getChessComponents()[targetPoint.getX()][7];
        } else {
            rook = chessboard.getChessComponents()[targetPoint.getX()][0];
        }
        if (!first.getMoved()) { first.updateMoved(); }
        if (!rook.getMoved()) { rook.updateMoved(); }
        chessboard.swapChessComponents(first, chessComponent);
        chessboard.swapChessComponents(rook, targetRookComponent);
        chessboard.swapColor();
        chessGameFrame.addLabel1().setText("王车易位 ! ");
        /*
        ChessboardPoint[] lastMove = new ChessboardPoint[2]; // 记录上一次走的位置
        lastMove[0] = chessComponent.getChessboardPoint();
        lastMove[1] = first.getChessboardPoint();
        recordedmove.add(lastMove);
        clickCount += 1; */
    }


    // 是否满足吃过路兵
    private boolean pawnPass(ChessComponent first, ChessComponent chessComponent) { // chessComponent是下一步走的位置的棋子， 在吃过路兵的情况下是EmptyChessComponent
        if (recordedmove.size() <= 0) return false;

        ChessboardPoint[] lastMove = recordedmove.get(recordedmove.size() - 1);

        ChessComponent lastChess = chessboard.getChessComponents()[lastMove[1].getX()][lastMove[1].getY()]; // 上一步走的棋子

        if (first.getChessType() != "Pawn" || lastChess.getChessType() != "Pawn" ) { // 保证两步走的都是pawn
            return false;
        }


        if (first.getChessColor().getName() == "White" && lastChess.getChessColor().getName() == "Black") {  // 走白子吃过路兵的条件
            if (first.getChessboardPoint().getX() == 3 && lastMove[0].getX() == 1 && lastMove[1].getX() == 3  // 上一次黑子走了两格
                    && Math.abs(first.getChessboardPoint().getY() - lastMove[1].getY()) == 1 // 上一次黑子与白子相邻
                    && chessComponent.getChessboardPoint().getX() == 2 && chessComponent.getChessboardPoint().getY() == lastMove[1].getY()) { // 斜吃过路兵
                return true;
            }
        }
        else if (first.getChessColor().getName() == "Black" && lastChess.getChessColor().getName() == "White") { // 走黑子吃过路兵的条件
            if (first.getChessboardPoint().getX() == 4 && lastMove[0].getX() == 6 && lastMove[1].getX() == 4
                    && Math.abs(first.getChessboardPoint().getY() - lastMove[1].getY()) == 1
                    && chessComponent.getChessboardPoint().getX() == 5 && chessComponent.getChessboardPoint().getY() == lastMove[1].getY()) {
                return true;
            }
        }
        return false;
    }

    //执行吃过路兵
    private void executePawn(ChessComponent chessComponent) {
        ChessboardPoint[] lastMove = new ChessboardPoint[2]; // 记录上一次走的位置
        lastMove[0] = chessComponent.getChessboardPoint();
        lastMove[1] = first.getChessboardPoint();
        chessboard.swapChessComponents(first, chessComponent);
        ChessComponent lastChess = chessboard.getChessComponents()[chessComponent.getChessboardPoint().getX()][first.getChessboardPoint().getY()]; // 上一步走的棋子
        chessboard.swapChessComponents(chessComponent, lastChess);
        chessboard.swapColor();
        lastMove[0] = chessComponent.getChessboardPoint();
        lastMove[1] = first.getChessboardPoint();
        recordedmove.add(lastMove);
        clickCount += 1;
        chessGameFrame.addLabel1().setText("吃过路兵 ! ");
    }

    // 判断是否满足兵升变
    private boolean pawnPromote(ChessComponent chessComponent) {
        if (chessComponent.getChessType() == "Pawn") {
            if (chessComponent.getChessColor().getName() == "White" && chessComponent.getChessboardPoint().getX() == 0) {
                return true;
            }
            if (chessComponent.getChessColor().getName() == "Black" && chessComponent.getChessboardPoint().getX() == 7) {
                return true;
            }
        }
        return false;
    }

    // 执行兵升变
    private void promote(ChessComponent chessComponent) {
        ChessboardPoint chessboardPoint = chessComponent.getChessboardPoint();
        int row = chessboardPoint.getX();
        int col = chessboardPoint.getY();
        int size = chessboard.getChessSize();
        ChessColor color = chessComponent.getChessColor();

        JFrame f = new JFrame(); // add new frame to provide promote options
        f.setLayout(new FlowLayout());
        f.setSize(250, 250);
        Dimension a = new Dimension(100, 100);
        JButton queen = new JButton();
        JButton bishop = new JButton();
        JButton knight = new JButton();
        JButton rook = new JButton();

        if (color.getName() == "White") {
            try {
                Image img = ImageIO.read(new File("src/images/queen-white-0.png"));
                queen.setIcon(new ImageIcon(img));
                img =  ImageIO.read(new File("src/images/knight-white-0.png"));
                knight.setIcon(new ImageIcon(img));
                img =  ImageIO.read(new File("src/images/bishop-white-0.png"));
                bishop.setIcon(new ImageIcon(img));
                img =  ImageIO.read(new File("src/images/rook-white-0.png"));
                rook.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else if (color.getName() == "Black") {
            try {
                Image img = ImageIO.read(new File("src/images/queen-black-0.png"));
                queen.setIcon(new ImageIcon(img));
                img =  ImageIO.read(new File("src/images/knight-black-0.png"));
                knight.setIcon(new ImageIcon(img));
                img =  ImageIO.read(new File("src/images/bishop-black-0.png"));
                bishop.setIcon(new ImageIcon(img));
                img =  ImageIO.read(new File("src/images/rook-black-0.png"));
                rook.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        queen.setPreferredSize(a);
        queen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chessboard.initQueenOnBoard(row, col, color); // the method to place queen on specified location. Defined in chessboard.java
                ChessComponent promoted = chessboard.getChessComponents()[row][col];
                promoted.repaint();
                updateResultLabel();
                f.dispose();

            }
        });
        bishop.setPreferredSize(a);
        bishop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chessboard.initBishopOnBoard(row, col, color);
                ChessComponent promoted = chessboard.getChessComponents()[row][col];
                promoted.repaint();
                updateResultLabel();
                f.dispose();
            }
        });
        knight.setPreferredSize(a);
        knight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chessboard.initKnightOnBoard(row, col, color);
                ChessComponent promoted = chessboard.getChessComponents()[row][col];
                promoted.repaint();
                updateResultLabel();
                f.dispose();
            }
        });
        rook.setPreferredSize(a);
        rook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chessboard.initRookOnBoard(row, col, color);
                ChessComponent promoted = chessboard.getChessComponents()[row][col];
                promoted.repaint();
                updateResultLabel();
                f.dispose();
            }
        });

        chessGameFrame.addLabel1().setText("兵底线升变 ! ");

        f.add(queen);
        f.add(bishop);
        f.add(knight);
        f.add(rook);
        f.setVisible(true);
    }
}