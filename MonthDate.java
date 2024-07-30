public class MonthDate {
    private int date;
    private double rate;

    public MonthDate(int date){
        this.date = date;
        this.rate = 1;
    }

    
    /** 
     * @param rate
     */
    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return this.rate;
    }

    public int getDate() {
        return date;
    }
}
