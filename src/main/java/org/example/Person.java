package org.example;

public class Person {
    protected short id;
    protected String PersonName;
    protected int PesonAge;
    protected String PersonTelephone;
    protected boolean IsDisabled;


    //Empty Constructor
    public Person(){}

    //Constructor
    public Person(short id, String PersonName, int PersonAge, String PersonTelephone, boolean IsDisabled){
        this.id= id;
        this.PersonName = PersonName;
        this.PesonAge = PersonAge;
        this.PersonTelephone = PersonTelephone;
        this.IsDisabled = IsDisabled;
    }
    public void setPersonName(String personName) {
        PersonName = personName;
    }

    public void setPesonAge(int pesonAge) {
        PesonAge = pesonAge;
    }

    public void setPersonTelephone(String personTelephone) {
        PersonTelephone = personTelephone;
    }

    public void setDisabled(boolean disabled) {
        IsDisabled = disabled;
    }

    public String getPersonTelephone() {
        return PersonTelephone;
    }

    public int getPesonAge() {
        return PesonAge;
    }

    public String getPersonName() {
        return PersonName;
    }
}