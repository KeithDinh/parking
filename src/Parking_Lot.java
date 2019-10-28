import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Parking_Lot {
    // **************************************** CLASS MEMBERS **************************************** //

    private int drow = 3;
    private int dcol = 4;
    private Lot[][] capacity;
    private Queue<Integer> car_parking_time_queue;
    private boolean exit_gate = false;
    private boolean entrance_gate = false;
    private boolean parking_full = false;
    private int time_tracking = 0;
    private int id_tracking = 0;
    private double profit=0;

    // ************************************************** CLASS FUNCTION ************************************************ //


    // ************************************** MINOR FUNCTIONS ************************************** //

    // ********** CHECK IF PARKING IS EMPTY ********** //

    private boolean IsEmpty()
    {
        for(int i=0; i<drow; i++)
        {
            for(int j=0; j<dcol; j++)
            {
                if(capacity[i][j].lot_available == false)
                    return false;
            }
        }
        return true;
    }

    // ********** READ TIMES TO QUEUE ********** //

    private void read_file_to_queue(int test)
    {
        // ************** CAR QUEUE ****************//

        car_parking_time_queue = new LinkedList<Integer>();


        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("src//test"+ Integer.toString(test) + ".txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Error Reading File");
        }

        int row=0, column =0;

        System.out.print("Car Queue:");

        // ************** VALIDATION AND STRING DELIMETER *************** //
        while(scanner.hasNextLine())
        {
            String[] all_strings = scanner.nextLine().split("\\s+");
            for(String word : all_strings)
            {
                int temp;
                try {
                    temp = Integer.parseInt(word);
                }
                catch(NumberFormatException e)
                {
                    continue;
                }
                if(temp > 0)
                {
                    car_parking_time_queue.add(temp);
                    System.out.print(" " + Integer.toString(temp) );
                }
            }
        }
        if(car_parking_time_queue.isEmpty())
        {
            System.out.print(" empty\n");
            System.exit(0);
        }
    }
    // ********** LINEAR SEARCH AVAILABLE LOT ********** //

    private Lot available()
    {
        for(int i=0; i<drow; i++)
        {
            for(int j=0; j<dcol; j++)
            {
                if(capacity[i][j].lot_available == true)
                {
                    return capacity[i][j];
                }
            }
        }
        return null;
    }

    // ************************************** MAJOR FUNCTIONS ************************************** //

    // **********  PROCESS THE SYSTEM  ********** //

    public Parking_Lot(int testcase)
    {
        // INITIALIZE CAPACITY
        capacity = new Lot[drow][dcol];

        //ADD LOTS TO CAPACITY
        for(int i=0; i<drow; i++)
        {
            for(int j=0; j<dcol; j++)
            {
                capacity[i][j] = new Lot(i,j);
                if(i < 2) {
                    capacity[i][j].lot_fee = 2;
                    capacity[i][j].lot_discount = 0.05;
                    capacity[i][j].lot_policies= 180;
                }
                else
                {
                    capacity[i][j].lot_fee = 5;
                    capacity[i][j].lot_discount = 0.5;
                    capacity[i][j].lot_policies= 120;
                }
            }
        }
        System.out.println("\n************************ The parking is empty ************************");
        System.out.println("Current profit: " + Double.toString(profit));

        //********** READ FILE ********//
        read_file_to_queue(testcase);

        //******** PROCESS THE CAR QUEUE *********//
        while(!car_parking_time_queue.isEmpty())
        {
            System.out.println("\nAt minute: " + Integer.toString(time_tracking));
            if(entrance_gate == true )
            {
                Car new_car = new Car(car_parking_time_queue.remove());
                new_car.id = ++id_tracking;
                available().assigned_to(new_car);

                entrance_gate = false;
                System.out.println("Car " + Integer.toString(new_car.id)
                        + " has entered the lot at "+ Integer.toString(new_car.ticket_row_position+1) +" - " +  Integer.toString(new_car.ticket_col_position+1)
                        + " with parking time estimate: " + Integer.toString(new_car.parking_time_etm) +" minutes\n"
                        + "Entrance gate has closed.");


            }
            else if(parking_full == false)
            {
                // ********** NEW CAR WITH TIME & ID *********** //
               if(available() != null)
               {
                   entrance_gate = true;
                   System.out.println("Entrance gate has opened");
               }
               else{
                   System.out.println("Parking Full");
               }


            }
            else
                System.out.println("Parking Full");
            time_tracking++;
            car_time_left();

//            try {
//                TimeUnit.SECONDS.sleep(2);
//            }
//            catch (InterruptedException e)
//            {
//                System.out.println("wrong");
//            }
        }
        System.out.println("\n************************ The car queue is empty ************************");

        //******** PROCESS THE LEFTOVER IN PARKING *********//

        while(!IsEmpty())
        {
            System.out.println("\nAt minute: " + Integer.toString(time_tracking));
            time_tracking++;
            car_time_left();
        }
        System.out.println("\n************************ The parking is empty ************************");
    }



    /* ********** DECREASE PARKING TIME OF EACH CAR INSIDE THE PARKING
       ********** IF TIME REACHES 0, CAR WILL REACH THE EXIT GATE, EXIT GATE OPENS
       ********** IF TIME REACHES -1, CAR PAID AND LEFT, EXIT GATE CLOSES
       ********** RESET THE LOT
    */
    private void car_time_left()
    {
        for(int i=0; i<drow; i++)
        {
            for(int j=0; j<dcol; j++)
            {
                if(capacity[i][j].lot_available == false)
                {
                    capacity[i][j].car.parking_time_etm--;
                    if (capacity[i][j].car.parking_time_etm == 0 && exit_gate == false)
                    {
                        exit_gate = true;
                        System.out.println("Exit gate has opened");
                        time_tracking++;
                    }
                    else if (capacity[i][j].car.parking_time_etm < 0)
                    {
                        double fee =0;
                        if(capacity[i][j].car.parking_time > capacity[i][j].lot_policies) {
                            fee = capacity[i][j].lot_fee *
                                    Math.round(capacity[i][j].car.parking_time / capacity[i][j].lot_policies) *
                                    capacity[i][j].lot_discount;
                        }
                        else
                            fee = Math.round(capacity[i][j].car.parking_time/60f)*capacity[i][j].lot_fee;
                        profit += fee;
                        capacity[i][j].reset();

                        exit_gate = false;
                        System.out.println("Exit gate has closed, " + "Car " + Integer.toString(capacity[i][j].car.id)
                                + " has left the lot " + Integer.toString(i + 1) + " - "+
                                Integer.toString(j + 1)+" and paid: $" +Double.toString(fee)
                        + " for " + Integer.toString(capacity[i][j].car.parking_time) + " minute(s)");
                        System.out.println("Current Profit: " + Double.toString(profit));
                        if (parking_full == true)
                        {
                            parking_full = false;
                            System.out.println("Parking Available");
                        }
                        time_tracking++;
                    }
                }
            }
        }
    }
}
