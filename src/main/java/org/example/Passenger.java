package org.example;

public class Passenger extends Person{
    private short wagonId;

    //Constructor of passenger based on wagonID
    public Passenger(short wagonId) {
        this.wagonId = wagonId;
    }

    //Constructor of Passenger
    public Passenger(short id,String PersonName, int PersonAge, String PersonTelephone, boolean IsDisabled, short wagonId) {
        super(id,PersonName, PersonAge, PersonTelephone, IsDisabled);
        this.wagonId = wagonId;
    }

    public short getWagonId() {
        return wagonId;
    }

    public void setWagonId(short wagonId) {
        this.wagonId = wagonId;
    }
}
