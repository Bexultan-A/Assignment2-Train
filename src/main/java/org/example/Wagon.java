package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Wagon {
    private short WagonID;
    private String TypeOfWagon;
    private short TrainID;
    private Voucher Voucher;
    private ArrayList<Passenger> passengers;

    public Wagon(){}

    //Constructor
    public Wagon(short wagonID, String typeOfWagon, short trainID) {
        this.WagonID = wagonID;
        this.TypeOfWagon = typeOfWagon;
        this.TrainID = trainID;
        this.Voucher = getVoucher(wagonID);
        this.passengers = getPassengersByWagon(wagonID);
    }

    //Another constructor
    public Wagon(short wagonID, String typeOfWagon, short trainID, boolean showWagon) {
       if(showWagon){
           this.WagonID = wagonID;
           this.TypeOfWagon = typeOfWagon;
           this.TrainID = trainID;
       }
    }


    private static final TrainDB TrainDB = new TrainDB();
    private static final Statement statement;

    static {
        try {
            statement = TrainDB.getConnection().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public short getWagonID() {
        return WagonID;
    }

    public void setWagonID(short wagonID) {
        WagonID = wagonID;
    }

    public String getTypeOfWagon() {
        return TypeOfWagon;
    }

    public void setTypeOfWagon(String typeOfWagon) {
        TypeOfWagon = typeOfWagon;
    }

    public short getTrainID() {
        return TrainID;
    }

    public void setTrainID(short trainID) {
        TrainID = trainID;
    }


    //Method to take data about voucher through SQL statements from database
    public org.example.Voucher getVoucher(int wagonid) {

        String querry = "SELECT * FROM wagon where wagonid = "+ wagonid;
        try {
            ResultSet resultset = statement.executeQuery(querry);
            while (resultset.next()){
             short id = resultset.getShort(4);
             String querry2 = "SELECT person.id , person.personname, person.age, person.personnumber,person.isdisabled , voucher.voucherid , voucher.isfree\n" +
                     "FROM person,voucher,wagon\n" +
                     "WHERE person.id=voucher.personid and wagon.voucherid =" + id;
                try {
                    ResultSet result = statement.executeQuery(querry);
                    while (result.next()){
                        short personid = result.getShort(1);
                        String personName = result.getString(2);
                        int age= result.getInt(3);
                        String personNumber = result.getString(4);
                        boolean personIsDisabled = result.getBoolean(5);
                        boolean isFree = result.getBoolean(7);
                        Voucher voucher = new Voucher(personid, personName, age, personNumber, personIsDisabled,id,isFree);
                        return voucher;
                    }
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //Take data about passenger person from database
    public ArrayList<Passenger> getPassengersByWagon(int wagonId){
        ArrayList<Passenger> passengers = new ArrayList<Passenger>();
        String querry= "SELECT person.id , person.personname, person.age, person.personnumber,person.isdisabled, passenger.wagonid\n" +
                "FROM person,passenger\n" +
                "WHERE person.id=passenger.personid AND passenger.wagonid =" + wagonId;
        try {

            ResultSet resultset = statement.executeQuery(querry);
            while (resultset.next()){
                short id = resultset.getShort(1);
                String personName = resultset.getString(2);
                int age= resultset.getInt(3);
                String personNumber = resultset.getString(4);
                boolean personIsDisabled = resultset.getBoolean(5);
                Passenger passenger = new Passenger(id,personName,age,personNumber,personIsDisabled, (short) wagonId);
                passengers.add(passenger);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return passengers;
    }

    //Overrided ToString method to show some details
    public String toString() {
        return "Wagon{" +
                "WagonID=" + WagonID +
                ", TypeOfWagon='" + TypeOfWagon + '\'' +
                ", TrainID=" + TrainID +
                '}';
    }

}
