package project.List;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String row1 = input.next();
        String col1 = input.next();
        int row = Integer.parseInt(row1);
        int col = Integer.parseInt(col1);
        String[] chessBoardOrigin = new String[row];
        for(int i = 0; i < row; i++){
            chessBoardOrigin[i] = input.nextLine();
        }
        String connected = input.next();
        for(int i = 0; i < Integer.parseInt(connected) ; i++ ){

        }
        TreeLinkedList list = new TreeLinkedList();

    }
}
