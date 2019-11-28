package com.tema.input;

import com.tema.players.Player;

import java.util.ArrayList;

public final class Battlefield {
    private Lot[][] battlefieldMat;
    private int nrRows;
    private int nrCols;

    private static Battlefield instance = null;

    private Battlefield(){}
    public static Battlefield getInstance() {
        if(instance == null) {
            instance = new Battlefield();
        }
        return instance;
    }

    protected void CreateBattlefield(int nrRows, int nrCols){
        this.battlefieldMat = new Lot[nrRows][nrCols];
        this.nrRows = nrRows;
        this.nrCols = nrCols;
    }

    protected void SetBattlefieldRow(String fileString, int rowNumber){

        for(int i=0; i<nrCols; i++){
            battlefieldMat[rowNumber][i] = new Lot(fileString.charAt(i));
        }
    }

    public void RemovePlayer(Player player){
        int rowPos = player.getrowPos();
        int columnPos = player.getcolumnPos();

        battlefieldMat[rowPos][columnPos].RemoveOccupant(player);
    }

    public void AddPlayer(Player player){
        int rowPos = player.getrowPos();
        int columnPos = player.getcolumnPos();

        battlefieldMat[rowPos][columnPos].AddOccupant(player);
    }

    public Lot getLot(Player player){
        int rowPos = player.getrowPos();
        int columnPos = player.getcolumnPos();
        return battlefieldMat[rowPos][columnPos];
    }

    public Lot[][] getBattlefieldMat() {
        return battlefieldMat;
    }

    public int getNrRows() {
        return nrRows;
    }

    public int getNrCols() {
        return nrCols;
    }

    public static class Lot {
        private final char landType;
        private ArrayList<Player> occupants;

        public Lot(char landType){
            this.landType = landType;
        }

        public void RemoveOccupant(Player player){
            for(Player p: occupants){
                if(p.equals(player)){
                    occupants.remove(p);
                }
            }
        }

        public void AddOccupant(Player player){
            occupants.add(player);
        }

        public char getLandType() {
            return landType;
        }

        public ArrayList<Player> getOccupants() {
            return occupants;
        }

        public void setOccupants(ArrayList<Player> occupants) {
            this.occupants = occupants;
        }
    }
}