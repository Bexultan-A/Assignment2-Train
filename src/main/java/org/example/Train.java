package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class Train {
    short trainID;
    String typeOfTrain;
    ArrayList<Wagon> wagons;
    String fromAdress;
    String toAdress;
    String dateOfDerparture;
    String timeOfDeparture;
    int totalTime;

    private static final TrainDB TrainDB = new TrainDB();
    private static final Statement statement;

    static {
        try {
            statement = TrainDB.getConnection().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Constructor
    public Train(short trainID, String fromAdress, String toAdress, String dateOfDerparture, String timeOfDeparture, int totalTime) {
    this.trainID = trainID;
    this.fromAdress = fromAdress;
    this.toAdress = toAdress;
    this.dateOfDerparture = dateOfDerparture;
    this.timeOfDeparture = timeOfDeparture;
    this.totalTime = totalTime;
    wagons = getWagonByTrain(trainID);
    }


    //Method to get data about train wagon
    public ArrayList<Wagon> getWagonByTrain(short trainID){
        ArrayList<Wagon> wagons = new ArrayList<Wagon>();
        String querry= "SELECT * FROM wagon WHERE trainid = " + trainID;
        try {
            ResultSet resultset = statement.executeQuery(querry);
            while (resultset.next()){
                short wagonid = resultset.getShort(1);
                String typeOfWagon = resultset.getString(2);
                short trainid = resultset.getShort(3);
                Wagon wagon = new Wagon(wagonid,typeOfWagon,trainid);
                wagons.add(wagon);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return wagons;
    }

    //ToString method to show information about train
    @Override
    public String toString() {
        String res =
                "Trains:\n" +
                "•\tTrain id –" + trainID + "\n" +
                "o\tToAdress:" + toAdress + "\n" +
                "o\tFromAdress:" + fromAdress + "\n" +
                "o\tDate of departure:" + dateOfDerparture + "\n" +
                "o\tTime of departure:" + timeOfDeparture + "\n" +
                "o\tTotal time:" + totalTime + "\n" +
                "o\tWagons:";

                for(int i = 0; i < wagons.size(); i++){
                    res += wagons.get(i).toString();
                };
        return res;
    }
    public String toString(String ShowAdress) {
        if (ShowAdress == "Adress") {
            return " Train id: " + trainID +
                    ", toAdress: " + toAdress +
                    ", fromAdress: " + fromAdress +
                    ", dateOfDerparture: " + dateOfDerparture +
                    ", timeOfDeparture: " + timeOfDeparture;
        }else{
            return toString();
        }
    }
}
