import java.sql.Struct;

public class Car {
    protected int ticket_row_position;
    protected int ticket_col_position;
    protected boolean ticket_IsValid;
    protected int parking_time_etm;
    protected int parking_time;
    protected int id;
    public Car(int time)
    {
        ticket_IsValid = false;
        parking_time_etm = time;
        parking_time = time;
    }
    public void assigned_to_lot(int x, int y)
    {
        ticket_col_position = y;
        ticket_row_position = x;
        ticket_IsValid = true;
    }

    // CALL WHEN CAR LEFT TO RESET CAR INFO
    public void exit_lot()
    {
        ticket_row_position = -1;
        ticket_col_position = -1;
        ticket_IsValid = false;
        parking_time_etm = 0;
    }
}
