package Medium.TicTacToe;

import java.util.Arrays;

public class Board {
    private final int[][] arr;
    public Board(){
        arr = new int[3][3];
        for(int i=0;i<3;i++) Arrays.fill(arr[i],-1);
    }

    public void markMove(int playerId,int ind){
        int row = ind/3;
        int col = ind%3;

        arr[row][col]=playerId;
        printBoad();
    }

    public void printBoad(){
        System.out.println("Board State");
        System.out.println("-------------");
        for(int i=0;i<3;i++){

            for(int j=0;j<3;j++){
                System.out.print(arr[i][j]+"  ");
            }
            System.out.println();
        }
        System.out.println("-------------");
    }

    public boolean checkIfP1Won(){
        // check Row
        for(int i=0;i<3;i++){
            boolean flag = true;
            for(int j=0;j<3;j++){
                if(arr[i][j]!=0){
                    flag=false;
                }
            }
            if(flag) return true;
        }

        // check cols
        for(int i=0;i<3;i++){
            boolean flag = true;
            for(int j=0;j<3;j++){
                if(arr[j][i]!=0){
                    flag=false;
                }
            }
            if(flag) return true;
        }

        // diagnols
        boolean flag = true;
        for(int i=0;i<3;i++){
            if(arr[i][i]!=0) {
                flag = false;
            }
        }
        if(flag) return true;

        flag = true;
        for(int i=2;i>=0;i--){
            if(arr[i][2-i]!=0) {
                flag = false;
            }
        }
        if(flag) return true;
        return false;

    }

    public boolean checkIfP2Won(){
        // check Row
        for(int i=0;i<3;i++){
            boolean flag = true;
            for(int j=0;j<3;j++){
                if(arr[i][j]!=1){
                    flag=false;
                }
            }
            if(flag) return true;
        }

        // check cols
        for(int i=0;i<3;i++){
            boolean flag = true;
            for(int j=0;j<3;j++){
                if(arr[j][i]!=1){
                    flag=false;
                }
            }
            if(flag) return true;
        }

        // diagnols
        boolean flag = true;
        for(int i=0;i<3;i++){
            if(arr[i][i]!=1) {
                flag = false;
            }
        }
        if(flag) return true;

        flag = true;
        for(int i=2;i>=0;i--){
            if(arr[i][2-i]!=1) {
                flag = false;
            }
        }
        if(flag) return true;
        return false;
    }
}
