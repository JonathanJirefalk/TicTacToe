import java.util.Scanner;

public class GameFunctions {

    Scanner scanner = new Scanner(System.in);
    EndGameFunctions results = new EndGameFunctions();
    boolean isPlaying = true;
    boolean playerOneTurn = true;
    String[][] board = new String[][]{{"   ", "|", "   ", "|", "   "}, {"---","+","---", "+", "---"}, {"   ", "|", "   ", "|", "   "}, {"---","+","---", "+", "---"}, {"   ", "|", "   ", "|", "   "}};

    int modeSelected = 0;
    int inputPos = 0;

    String playerOneName;
    String playerTwoName;

    public int squaresFilled = 0;

    public void startGame(){
        if (results.gameFinished){
            results.gameFinished = false;
            resetBoard();
        }

        switch (modeSelected){
            case 0:
                selectMode();
                break;
            case 1:
                chooseName(1);
                modeSelected = 3;
                break;
            case 2:
                chooseName(2);
                modeSelected = 4;
                break;
            case 3:
                againstComputer();
                break;
            case 4:
                twoPlayer();
                break;
            default:
                break;
        }
    }

    private void selectMode(){
        System.out.println("Would you like to play 1 or 2 player mode?");
        String userInput = scanner.nextLine().trim();
        if (userInput.equals("1")){
            modeSelected = 1;
            System.out.println("Singleplayer mode against computer selected!");
        }
        else if (userInput.equals("2")){
            modeSelected = 2;
            System.out.println("2 Player mode selected!");
        }
        else{
            System.out.println("Invalid input, press 1 or 2!");
        }
    }

    private void chooseName(int playerMode){
        if (playerMode == 1){
            System.out.println("What is your player name?");
            System.out.println();
            playerOneName = scanner.nextLine().trim();
            playerTwoName = "Computer";
            System.out.println("Player 1 confirmed as: " + playerOneName + " playing against Computer!");
        }
        else{
            System.out.println("What is the name of player 1?");
            System.out.println();
            playerOneName = scanner.nextLine();
            System.out.println("Player 1 confirmed as: " + playerOneName);
            System.out.println();

            System.out.println("What is the name of player 2?");
            System.out.println();
            playerTwoName = scanner.nextLine();
            System.out.println("Player 2 confirmed as: " + playerTwoName);
            System.out.println();
        }
    }

    private void twoPlayer(){
        if(playerOneTurn){
            System.out.println(playerOneName + ", in what square would you like to play? (You are X)");
        }
        else{
            System.out.println(playerTwoName + ", in what square would you like to play? (You are O)");
        }
        checkTurn(false);
    }

    private void againstComputer(){
        if(playerOneTurn){
            System.out.println(playerOneName + ", in what square would you like to play? (You are X)");
            checkTurn(false);
        }
        else{
            checkTurn(true);
        }
    }

    private void checkTurn(boolean isComputerTurn){

        if(!isComputerTurn){
            inputPos = Integer.parseInt(isNumber(scanner.nextLine())) - 1;
        }
        else{
            inputPos = (1 + (int)(Math.random() * 8));
        }

        int col = (inputPos % 3) * 2;
        int row = (Math.floorDiv(inputPos, 3) * 2);

        if (inputPos >= 0 && inputPos <= 8 && board[row][col].equals("   ")){
            updateBoard(col, row, isComputerTurn);
        }
        else if (!isComputerTurn){

            for (String[] strings : board) {
                for (String string : strings) {
                    System.out.print(string);
                }
                System.out.println();
            }

            System.out.println("Please pick a number 1 - 9 that isn't occupied :)");
            checkTurn(false);
        }
        else{
            checkTurn(true);
        }
    }

    private String isNumber(String user){

        while (!user.matches("(\\d*)") || user.isEmpty()){
            System.out.println();
            System.out.println("Invalid input, please insert a number");
            user = scanner.nextLine();
        }
        return user;
    }

    private void updateBoard (int col, int row, boolean isSinglePlayer){

        if(playerOneTurn){
            board[row][col] = " X ";
            playerOneTurn = false;
        }else {
            board[row][col] = " O ";
            playerOneTurn = true;
        }

        if(isSinglePlayer){
            System.out.println("The computer played O at square " + (inputPos + 1));
        }
        for (String[] strings : board) {

            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println();
        }
        squaresFilled++;
        results.boardState(board, squaresFilled, playerOneName, playerTwoName);
    }

    public void resetBoard (){
        squaresFilled = 0;
        board = new String[][]{{"   ", "|", "   ", "|", "   "}, {"---", "+", "---", "+", "---"}, {"   ", "|", "   ", "|", "   "}, {"---", "+", "---", "+", "---"}, {"   ", "|", "   ", "|", "   "}};
        System.out.println("Board reset successfully!");
        System.out.println();

        System.out.println("Would you like to play again, or change mode/opponent?");
        System.out.println("1. Play again!");
        System.out.println("2. Change mode/opponent?");
        System.out.println("3. Quit Application");
        String userInput = scanner.nextLine().trim();

        while(!(userInput.equals("1") || userInput.equals("2") || userInput.equals("3"))){

            System.out.println("Invalid input, please type 1, 2 or 3!");
            userInput = scanner.nextLine().trim();
        }

        playerOneTurn = true;

        if(userInput.equals("1")){
            if(modeSelected == 3){
                againstComputer();
            }
            else{
                twoPlayer();
            }
        }
        else if (userInput.equals("2")){
            results.playerOneWins = 0;
            results.playerTwoWins = 0;
            modeSelected = 0;
        }
        else{
            isPlaying = false;
            modeSelected = 5;
        }
    }
}