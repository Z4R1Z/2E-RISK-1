package UI;

import javafx.stage.Stage;
import sample.GameController;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MenuController {

    Stage stag;
    int playerCount, playerIndex = 0;
    String selectedCountry, selectedLeader;
    ArrayList<String> selectedCountries;
    GameController cntrl = new GameController();
    public MenuController(Stage stage, ArrayList<String> selectedCountries){
        stag = stage;
        this.selectedCountries = selectedCountries;
    }

    public void launch() throws FileNotFoundException {
        stag.setResizable(false);
        MainMenu men = new MainMenu(stag, this);
        men.show();
    }

    public void credits(){
        Credits cre = new Credits(stag, this);
        cre.show();
    }
    public void newGame() throws FileNotFoundException {
        cntrl.initBoard();
        PlayersMenu playerMenu = new PlayersMenu(stag);
        playerMenu.askForPlayerCount(this);
    }

    public void countrySelection() throws  FileNotFoundException{
        System.out.println("count " +playerCount);
        NewGame newG = new NewGame(stag, playerCount-playerIndex, this, selectedCountries);
        newG.show();
    }

    public void countrySelected(String country, String leader, String playerName){
        System.out.println("selected:  " + country + leader + playerName);
        selectedCountries.add(country);
        cntrl.initPlayer(country, leader, playerName, playerIndex);
        playerIndex++;
    }//

    public void gameplay() throws FileNotFoundException {
        cntrl.gameplay(stag);
    }

    public String getCountry(){
        return selectedCountry;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
        cntrl.setPlayerCount(playerCount);
    }
}
