import java.util.Scanner;

public class EndGameFunctions {

    public boolean gameFinished = false;
    public int playerOneWins = 0;
    public int playerTwoWins = 0;
    Scanner scanner = new Scanner(System.in);

    public void boardState(String[][] board, int squares, String playerOne, String playerTwo) {

        if(won(board) == 1) {
            playerOneWins++;
            System.out.println("Congratulations " + playerOne + ", You Won");
            printScore(playerOne, playerTwo);
            System.out.println();
            System.out.println("Press ENTER to Continue");
            scanner.nextLine();
            gameFinished = true;
        }
        else if(won(board) == 2) {
            playerTwoWins++;
            System.out.println("Congratulations " + playerTwo + ", You Won");
            printScore(playerOne, playerTwo);
            System.out.println();
            System.out.println("Press ENTER to Continue");
            scanner.nextLine();
            gameFinished = true;
        }
        else if(squares == 9){
            System.out.println("The Game Has Drawn!");
            printScore(playerOne, playerTwo);
            System.out.println();
            System.out.println("Press ENTER to Continue");
            scanner.nextLine();
            gameFinished = true;
        }
    }

    private void printScore(String playerOne, String playerTwo) {
        System.out.println("Current score is: ");
        System.out.println(playerOne + ": " + playerOneWins);
        System.out.println(playerTwo + ": " + playerTwoWins);
    }

    private int won(String[][] board){

        int xLine = 0;
        int oLine = 0;

        for(int i = 0; i < 6; i++){

            for(int j = 0; j < 3; j++){

                if(i <= 2){
                    if(board[i*2][j*2].equals(" X ")){

                        xLine++;
                        if(xLine == 3){
                            return 1;
                        }
                    }
                    if(board[i*2][j*2].equals(" O ")){

                        oLine++;
                        if(oLine == 3){
                            return 2;
                        }
                    }
                }
                else if(board[j*2][i*2-6].equals(" X ")){

                    xLine++;
                    if(xLine == 3){
                        return 1;
                    }
                }
                else if(board[j*2][i*2-6].equals(" O ")){

                    oLine++;
                    if(oLine == 3){
                        return 2;
                    }
                }
            }
            xLine = 0;
            oLine = 0;
        }

        if(board[2][2].equals(" X ")){

            if((board[0][0].equals(" X ") && board[4][4].equals(" X ")) || (board[0][4].equals(" X ") && board[4][0].equals(" X "))){

                return 1;
            }
        }

        if(board[2][2].equals(" O ")){

            if((board[0][0].equals(" O ") && board[4][4].equals(" O ")) || (board[0][4].equals(" O ") && board[4][0].equals(" O "))){

                return 2;
            }
        }
        return 0;
    }
}