package Medium.TicTacToe;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TicTacToeDemo {
    public static void run() {

        Player p1 = new Player(1,"Babu");
        Player p2 = new Player(2,"Kiran");

        TicTaeController ticTacToe = TicTaeController.getInstance(p1,p2);
        ticTacToe.startGame();
    }
}
