public class DeluxeRoom extends Room{
    private double deluxeRoomBasePrice;
    public DeluxeRoom(int roomNumber) {
        super(roomNumber);
        this.deluxeRoomBasePrice = 0;
    }
    @Override
    public double getBasePrice(){
        this.deluxeRoomBasePrice = super.getBasePrice() + super.getBasePrice() * 0.20;
        return this.deluxeRoomBasePrice;
    }
}
