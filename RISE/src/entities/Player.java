package entities;

import entities.Countries.*;
import entities.Troop.Troop;

public class Player {
    String countryName, leader, name;
    Country country;
    Troop[] artilleries, tanks, nerds, infantries;
    Troop[][] troops;
//    int attackingland;
    General selectedGeneral;
    int troopNumber;

    public Player(String countr, String leade, String name){
        countryName = countr;
        leader = leade;
        this.name = name;
        troops = new Troop[4][];
        if(countr == "German Reich") {
            country = new Germany(leader);
            country.initializeTroops(troops);
            troopNumber = country.getTroopNumber();
            setTroopTypePoints();
        } else if(countr == "Soviet Union"){
            country = new SovietUnion(leader);
            country.initializeTroops(troops);
            setTroopTypePoints();
        } // bu else ifleri biri tamamlasın
    }

    public String getCountry() {
        return countryName;
    }

    public String getLeader() {
        return leader;
    }

    public float getAttackPoints(){
        return 0;
    }

    //0 - Artillery, 1- Infantry, 2-Tank, 3-Nerds
    public void setTroopTypePoints(){
        for(int i = 0; i < country.getInUse().getHowManyUnitType(); i++){
            for(int j = 0; j < troops[country.getInUse().getWhichUnits()[i]].length; j++){
                troops[country.getInUse().getWhichUnits()[i]][j].setAttack(troops[country.getInUse().getWhichUnits()[i]][j].getAttack() + country.getInUse().getBufferAtack());
                troops[country.getInUse().getWhichUnits()[i]][j].setDefense(troops[country.getInUse().getWhichUnits()[i]][j].getDefense() + country.getInUse().getBufferDefense());
            }
        }

    }

    public  void printTroops(){
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < troops[j].length; i++) {
                System.out.println(troops[j][i].getType() + ":   " + troops[j][i].getAttack());
            }
        }
    }

    public float attackPointsAt(int coordinates){
        float attack = 0;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < troops[j].length; i++) {
                if (troops[j][i].getPosition() == coordinates)
                    attack += troops[j][i].getAttack();
            }
        }
        for(int i = 0; i < 4; i++) {
            attack += selectedGeneral.attackEffectOnCertainUnit(troops, i, coordinates);
        }
        return attack;
    }

    public float defensePointsAt(int coordinates){
        float def = 0;
        for(int j = 0; j < 4; j++) {
            for (int i = 0; i < troops[j].length; i++) {
                if (troops[j][i].getPosition() == coordinates)
                    def += troops[j][i].getDefense();
            }
        }
        for(int i = 0; i < 4; i++)
            def += selectedGeneral.defenseEffectOnCertainUnit(troops,i, coordinates);
        return def;
    }

    public void selectGeneral(String name){//name of general
        selectedGeneral = country.selectGeneral(name);
    }

    public float generalAggressionDefenseEffect(String enemy, int attackingland){
       // System.out.println("enemyName: " + enemy + " attackingland:  " + attackingland+ "  selectedGeneral: "+ selectedGeneral.getName());
        return selectedGeneral.againstCountryDefense(enemy,troops, attackingland);
    }

    public float generalAggressionAttackEffect(String enemy, int attackingland){
       // System.out.println("enemyName: " + enemy + " attackingland:  " + attackingland+ "  selectedGeneral: "+ selectedGeneral.getName());
        return selectedGeneral.againstCountryAttack(enemy,troops, attackingland);
    }

    public boolean attackingTo(Player enemy, int attackingland) {
        float def = enemy.defensePointsAt(attackingland) + enemy.generalAggressionDefenseEffect(this.getCountry(), attackingland);
        System.out.println("defens:  " + def);
        float attack = this.attackPointsAt(attackingland) + this.generalAggressionAttackEffect(enemy.getCountry(), attackingland);
        System.out.println("attack : " + attack);
        return attack > def;
    }

    public void print(){
        System.out.println(name + "\n"  +  countryName+ " : "+ country.getInUse().getName() + "\n" + troopNumber + "   " + country.getIdeology());
        for (int i = 0 ; i < 4 ; i++) {
            System.out.println(troops[i][0].getType());
            System.out.println(troops[i][0].getAttack() + " " + troops[i][0].getDefense());
        }
    }

}
