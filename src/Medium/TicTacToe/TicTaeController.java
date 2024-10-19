package Medium.TicTacToe;

import java.util.Scanner;

public class TicTaeController {
    private static TicTaeController instance;
    private final Board board;
    private final Player p1;
    private final Player p2;
    private int turn;
    private int moves;

    public static TicTaeController getInstance(Player p1,Player p2){
        if(instance == null){
            instance = new TicTaeController(p1,p2);
        }
        return instance;
    }

    private TicTaeController(Player p1,Player p2){
        this.p1 = p1;
        this.p2 = p2;
        board = new Board();
        turn = 0;
        moves = 0;
    }

    public boolean makeMove(int ind){
        board.markMove(turn,ind);
        if(turn == 0){
            if(board.checkIfP1Won()) return true;
        }
        else if(board.checkIfP1Won()) return true;
        turn = turn == 0 ? 1: 0;
        return false;
    }

    public void startGame(){
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        for(int i=0;i<9;i++){
            System.out.println("Please enter your input: ");
            int move = sc.nextInt();
            if(makeMove(move)){
                if(i%2==0){
                    System.out.println(p1.getName()+" Won");
                    flag = false;
                    break;
                }
                else{
                    System.out.println(p2.getName()+" Won");
                    flag = false;
                    break;
                }
            }

        }
        if(flag){
            System.out.println("Draw");
        }
    }


}
