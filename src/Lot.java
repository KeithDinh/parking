public class Lot {
    protected int lot_row;
    protected int lot_col;
    protected boolean lot_available;
    protected Car car;
    protected int lot_fee;
    protected int lot_policies;
    protected double lot_discount;
    public Lot(int x, int y)
    {
        lot_row = x;
        lot_col = y;
        lot_available = true;
    }
    public void assigned_to(Car new_car)
    {

        new_car.assigned_to_lot(lot_row,lot_col);
        car = new_car;
        lot_available = false;
    }

    // RESET WHEN CAR LEFT
    public void reset()
    {
        car.exit_lot();
        lot_available = true;
    }
}
