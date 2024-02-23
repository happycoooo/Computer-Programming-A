import java.util.ArrayList;
import java.util.List;
public class test {
    private static ChessComponent[][] chessComponents;

    public static void main(String[] args) {
        List a = new ArrayList<>();
        a.add("RNBQK_NR");
        a.add("PP_PPPPP");
        a.add("________");
        a.add("________");
        a.add("________");
        a.add("__b_____");
        a.add("pppppp_p");
        a.add("rnbqk_nr");
        a.add("w");
        ConcreteChessGame b = new ConcreteChessGame();
        ChessboardPoint source = new ChessboardPoint(7,2);
        ChessComponent chessOnBoard = chessComponents [source.getX()] [source.getY()];
        System.out.println(chessOnBoard.name);
        b.moveChess(5,2,4,1);
        b.getCanMovePoints(source);
        System.out.println(b.getCanMovePoints(source));

    }
}
