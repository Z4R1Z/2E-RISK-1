package entities;

import entities.Troops.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;


public class Land implements java.io.Serializable{

    int landNo;
    float defensePoints;
    boolean ownedByPlayer;
    String owner;
    int[] troopcounts, borders;
    String landName;
    Player own;
    ArrayList<ArrayList<Troop>> troop;
    boolean forted;

    public Land(int landNo, String owner, int[] borders, String landName){
        this.landNo = landNo;
        this.owner = owner;
        this.borders = borders;
        this.landName = landName;
        troopcounts = new int[4];
        for (int a : troopcounts
             ) {
            a = 0;
        }
        defensePoints = 30;
        ownedByPlayer = false;
        forted = false;
    }

    //   public void setOwner(Country owner) {
    //      this.owner = owner;
    // }

    public boolean isForted() {
        return forted;
    }

    public void setForted(boolean forted) {
        this.forted = forted;
    }

    @Override
    public String toString() {
        return "Land{" +
                "landNo=" + landNo +
                '}' +  "\n" + "owner: " + owner + "\n Artillery: " + troopcounts[0] + "\n Infantry: " + troopcounts[1] +
                "\n Tank: " + troopcounts[2] + "\n Nerds: " + troopcounts[3] + "\n is: " + ownedByPlayer + "\n defPoints: " + defensePoints;
    }


    public String getTroopInfo(){
        Troop art = new Artillery();
        Troop inf = new Infantry();
        Troop tnk = new Tank();
        Troop nrd = new Nerds();
        String result = "";
        result += art.getType() + "  :  " + troopcounts[0] + "\n" + art.getAttack() + "   " + art.getDefense() + "\n";
        result += inf.getType() + "  :  " + troopcounts[1] + "\n" + inf.getAttack() + "   " + inf.getDefense() + "\n";
        result += tnk.getType() + "  :  " + troopcounts[2] + "\n" + tnk.getAttack() + "   " + tnk.getDefense() + "\n";
        result += nrd.getType() + "  :  " + troopcounts[3] + "\n" + nrd.getAttack() + "   " + nrd.getDefense() + "\n";
        return result;
    }

    public int[] getTroopcounts() {
        return troopcounts;
    }

    public void setTroopcounts(int[] troopcounts) {
        this.troopcounts = troopcounts;
    }

    public void setOneTypeTroop(int unitType, int amount){
        troopcounts[unitType] = amount;
    }

    public float getDefensePoints() {
        return defensePoints;
    }

    public void setOwn(Player own) {
        this.own = own;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean hasBorder(int landNo){
        for (int a : borders
             ) {
            if(a == landNo)
                return true;
        }
        return false;
    }

    public void isAttackable() {
        this.own = own;
    }

    public void setOwnedByPlayer(boolean ownedByPlayer) {
        this.ownedByPlayer = ownedByPlayer;
        defensePoints = 0;
    }

    public boolean isOwnedByPlayer() {
        return ownedByPlayer;
    }

    public void setDefensePoints(Player player)
    {
        this.defensePoints = player.defensePointsAt(landNo);
    }

    public int getLandNo() {
        return landNo;
    }
    public String getLandName(){
        return landName;
    }

    /*public Country getOwner() {
        return owner;
    }*/

    public int getArtilleryAmount(){
        return troopcounts[0];
    }

    public int getInfantryAmount(){
        return troopcounts[1];
    }

    public int getTankAmount(){
        return troopcounts[2];
    }
    public int getNerdsAmount(){
        return troopcounts[3];
    }

    public String getOwnerName(){
        return owner;
    }
}
