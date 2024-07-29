public class ExecutiveRoom extends Room {
    private double executiveRoomBasePrice;
    public ExecutiveRoom(int number) {
        super(number);
        this.executiveRoomBasePrice = 0;
    }
    @Override
    public double getBasePrice(){
        this.executiveRoomBasePrice = super.getBasePrice() + (super.getBasePrice() * 0.35);
        return this.executiveRoomBasePrice;
    }
}
