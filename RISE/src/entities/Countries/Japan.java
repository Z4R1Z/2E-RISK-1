package entities.Countries;

import entities.Troop.*;
import javafx.scene.input.GestureEvent;

public class Japan extends Country {

    String selected;
    Leader Hirohito = new Leader("Hirohito", "Fascist", 0, 0, 4, 4, new int[]{0, 1, 2, 3});
    Leader inUse;
    Leader[] leaders = {Hirohito};

    //general oluştururken önce tüm trooplara etkisini giriyoz sonra belli bi ülkeye karşı varsa onları giriyoz, belli ülkeye yoksa null, 0, 0 gir
    General Yamashita = new General("Tomoyuki Yamashita", new float[]{0,0,0,0}, new float[]{0,0,0,0}, 4, "Soviet Union" , -3, 0);
    General Terauchi = new General("Hisaichi Terauchi", new float[]{0,0,0,0}, new float[]{0,0,0,0}, 0, "China", 0, 2);
    General inUseForBattle;
    General[] generals = {Yamashita, Terauchi};

    //Land[] lands;    şimdilik boş kalsın bunlar
    //ResearchTree tree;

    public Japan(String leader){
        selected = leader;
        for (Leader a: leaders) {
            if (a.getName().equals(selected)){
                a.setSelected();
                inUse = a;
            }
        }
        ideology = inUse.getIdeology();
        troopNumber = 15;
    }

    public Leader getInUse() {
        return inUse;
    }

    public General[] getGenerals() {
        return generals;
    }

    //Troops
    @Override//her ülkeye farklı troop destesi verelim diye
    public Troop[][] initializeTroops(Troop[][] troops) {
        troops[0] = new Artillery[15];
        for (int i = 0; i < 15; i++) {
            troops[0][i] = new Artillery();
        }
        troops[1] = new Infantry[13];
        for (int i = 0; i < 13; i++) {
            troops[1][i] = new Infantry();
        }
        troops[2] = new Tank[25];
        for (int i = 0; i < 25; i++) {
            troops[2][i] = new Tank();
        }
        troops[3] = new Nerds[5];
        for (int i = 0; i < 5; i++) {
            troops[3][i] = new Nerds();
        }
        return troops;
    }

    public General selectGeneral(String name){
        for (General g: generals) {
            if(g.getName().equals(name))
                return g;
        }
        return null;
    }


    /*public void positionTroops(int[] troopNumbers, int targetPosition){
        for(int i = 0; i < troopNumber; i++){

        }
    }*/



}
