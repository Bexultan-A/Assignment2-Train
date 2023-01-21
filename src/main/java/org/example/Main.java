package org.example;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    // allows to use input
    private static final Scanner scanner = new Scanner(System.in);

    private static final TrainDB TrainDB = new TrainDB();

    private static final Statement statement;

    // creation of database queries
    static {
        try {
            statement = TrainDB.getConnection().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        startOfTrainProject();
    }


    public static void startOfTrainProject(){


        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("Hello user! This is console programm that show train hierarchy");
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("There are several function of code. If you want to use them,please input number near of function");
        System.out.println("1) Add Passenger 2) Buy ticket 3) Add train 4) Add wagon 5) Add voucher 6) Show all train hierarchy");

        int n = scanner.nextInt();
        switch (n){

            case 1: AddPerson();//Creating new passenger and adding to database
            case 2:BuyTicket();
            case 3:AddTrain();
            case 4:AddWagon();
            case 5:AddVoucher();
            case 6:ShowAllTrains();
        }


    }
    public static void AddPerson(){
        //method that add person to the database
        //Input of all personal data
        //////////////////////////////////////////////////////////////////////////////
        scanner.nextLine();
        System.out.println("Please enter name of passenger");
        String name = scanner.nextLine();

        System.out.println("Please enter age of passenger");
        int age = scanner.nextInt();

        System.out.println("Please enter telephone number of passenger");
        scanner.nextLine();
        String telenumber = scanner.nextLine();

        System.out.println("Is passenger disabled?");
        boolean isDisabled = scanner.nextBoolean();
        /////////////////////////////////////////////////////////////////////////////

        //Adding person to database
        String querry = "INSERT INTO public.person(id, personname, age, personnumber, \"isdisabled\")" +
                "VALUES (DEFAULT,'"+ name +"',"+ age +","+ telenumber +","+ isDisabled +");";
        try {
            Statement statement = TrainDB.getConnection().createStatement();
            statement.executeUpdate(querry);
        }catch (SQLException e){
            e.printStackTrace();
        }


    }
    public static void BuyTicket(){

        AddPerson();

        System.out.println("There are several trains. Which direction is suitable for you?");
        System.out.println("Choose suitable train id.");

        String querry="SELECT * FROM TRAIN";

        //Output interface of train information
        try {
            ResultSet resultset = statement.executeQuery(querry);
            while (resultset.next()){
                Train train = new Train(resultset.getShort(1),resultset.getString(2),resultset.getString(3),resultset.getString(4),resultset.getString(5), resultset.getInt(6));
                System.out.println(train.toString("Adress"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        //Choosing suitable train
        int trainId = scanner.nextInt();
        scanner.nextLine();

        //choosing type of wagon
        System.out.println("There are several types of wagons. Choose one of them.\n" +
                "1.\tPlaccart\n" +
                "2.\tCoupe\n" +
                "3.\tLuxe\n");
        String typeOfWagon = scanner.nextLine();

        //identify wagonid by type of train
        querry = "SELECT * FROM wagon WHERE typeofwagon = '" + typeOfWagon + "' and trainid = " + trainId;
        short wagonid = 0;
        try {
            ResultSet resultset = statement.executeQuery(querry);
            while (resultset.next()){
                wagonid = resultset.getShort(1);
                System.out.println(wagonid);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("You are succesfully bought ticket! Your wagon id is" + wagonid + " and train id is " + trainId);
        //output that user successfully bought ticket
    }
    public static void AddWagon(){
        //input data that user wants wagon add to train
        scanner.nextLine();
        System.out.println("There are several types of wagons. Choose one of them.\n" +
                "1.\tPlaccart\n" +
                "2.\tCoupe\n" +
                "3.\tLuxe\n");
        int chose = scanner.nextInt();
        scanner.nextLine();
        String typeOfWagon="";
        int numberOfSeats = 0;
        if(chose == 1) {
            typeOfWagon="Placcart";
            numberOfSeats=12;
        }
        if(chose == 2) {
            typeOfWagon="Coupe";
            numberOfSeats=24;
        }
        if(chose == 3) {
            typeOfWagon="Luxe";
            numberOfSeats = 50;
        }

        //output all suitable train
        System.out.println("there are several trains. Which train do you want to add wagon");
        String querry="SELECT * FROM TRAIN";
        try {

            ResultSet resultset = statement.executeQuery(querry);
            while (resultset.next()){
                Train train = new Train(resultset.getShort(1),resultset.getString(2),resultset.getString(3),resultset.getString(4),resultset.getString(5), resultset.getInt(6));
                System.out.println(train.toString("Adress"));
            }

        }
        catch (SQLException e){

            e.printStackTrace();

        }

        System.out.println("Chose TrainID");
        int trainID= scanner.nextInt();

        System.out.println("There are free Vouchers.Choose one of them");
        querry="SELECT * FROM voucher  WHERE isfree= true";
        //output all vouchers that are free for wagon
        try {

            ResultSet resultset = statement.executeQuery(querry);
            Voucher voucher=new Voucher();

            while (resultset.next()) {

                int voucherId = resultset.getInt(1);
                int vouchPersonId = resultset.getInt(2);

                String personName;
                int personAge;
                String personNumber;
                boolean pisDisabled;
                String querry2 = "SELECT * FROM person WHERE id =" + vouchPersonId;

                try {

                    ResultSet res2 = statement.executeQuery(querry2);

                    while (res2.next()) {

                        personName = res2.getString(2);
                        personAge = res2.getInt(3);
                        personNumber = res2.getString(4);
                        pisDisabled = res2.getBoolean(5);
                        voucher = new Voucher((short) vouchPersonId,personName, personAge, personNumber, pisDisabled, (short)voucherId, true);
                        System.out.println(voucher.toString());

                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        System.out.println("Chose VoucherID");
        int voucherID= scanner.nextInt();
        //input voucher id

        //insert all data of wagon to database and change field of voucher from true to false
        querry = "INSERT INTO public.wagon(wagonid, typeofwagon, trainid, voucherid , numberofseats) VALUES (DEFAULT," + typeOfWagon + ", "+ trainID +","+ voucherID +"," + numberOfSeats + ");";
        String querry2 = "UPDATE public.voucher SET isfree= True WHERE voucherid =" + voucherID + ";";
        try {
            statement.executeUpdate(querry);
            statement.executeUpdate(querry2);
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("The wagon has been successfully tied to the train! Voucher has been successfully tied to wagon!");
    }
    public static void AddTrain(){
        //Collection information about train
        //////////////////////////////////////////////////////////////////////////////////
        scanner.nextLine();
        System.out.println("Please enter from Adress");
        String fromAdress = scanner.nextLine();

        System.out.println("Please enter to Adress");
        String toAdress = scanner.nextLine();

        System.out.println("Please choose departure date");
        String departureDate = scanner.nextLine();

        System.out.println("Please choose departure time");
        String departureTime = scanner.nextLine();

        System.out.println("Please choose time");
        int totalTime = scanner.nextInt();
        ///////////////////////////////////////////////////////////////////////////////////

        //Adding train to database
        String querry = "INSERT INTO public.train(trainid, fromadress, toadress, dateofdeparture, timeofdeparture, totaltime)" +
                "VALUES (DEFAULT,'"+ fromAdress +"','"+ toAdress +"','"+ departureDate +"','"+ departureTime +"',"+ totalTime +");";
        try {
            statement.executeUpdate(querry);
        }catch (SQLException e){
            e.printStackTrace();
        }


    }
    public static void AddVoucher(){

        //Taking data about voucher
        scanner.nextLine();
        System.out.println("Please enter number of person");

        String telephoneNumber = scanner.nextLine();


        //Making a querry to get information about person
        String querry="SELECT id FROM person WHERE personnumber = '" + telephoneNumber + "'";

        int id= 0;
        try {
            ResultSet resultset = statement.executeQuery(querry);
            while(resultset.next()){
                id = resultset.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }


        //Adding voucher to database
        if(id!=0){

            querry = "insert into voucher (voucherid, isfree, personid) values (DEFAULT, True ," + id + ");";
            try {
                Statement statement = TrainDB.getConnection().createStatement();
                statement.executeUpdate(querry);
            }catch (SQLException e){
                e.printStackTrace();
            }

            System.out.println("New voucher is added!");

        }
    }

    public static void ShowAllTrains(){
        //method that shaw all train hirrarhy mean all trains. In trains all fields and list of wagons that contain passengers and voucher
        String querry="SELECT * FROM TRAIN";
        //sql querry that ask all from table train
        try {
            ResultSet resultset = statement.executeQuery(querry);//making statement
            while (resultset.next()){
                Train train = new Train(resultset.getShort(1),resultset.getString(2),resultset.getString(3),resultset.getString(4),resultset.getString(5), resultset.getInt(6));
                System.out.println(train.toString());
            }
        }catch (SQLException e){
            e.printStackTrace();//showing errors
        }
    }
}