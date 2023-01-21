package org.example;

public class Voucher extends Person{
    private short VoucherID;
    private boolean isFree;

    public Voucher(short personid,String PersonName, int PersonAge, String PersonTelephone, boolean IsDisabled, short voucherID,boolean isFree) {
        super(personid,PersonName, PersonAge, PersonTelephone, IsDisabled);
        this.VoucherID = voucherID;
        this.isFree = isFree;
    }

    public Voucher(){}
    public void setFree(boolean free) {
        isFree = free;
    }


    @Override
    public String toString() {
        return "VoucherID: " + VoucherID +
                ", Name: " + getPersonName();
    }
}
