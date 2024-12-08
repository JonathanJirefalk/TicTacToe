public class GameLoop {

    GameFunctions gameFunctions = new GameFunctions();

    public void playGame(){

        while(gameFunctions.isPlaying){
            gameFunctions.startGame();
        }
    }
}