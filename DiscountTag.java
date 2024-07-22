public class DiscountTag {
    private boolean iWorkHereCode;
    private boolean stay4Get1Code;
    private boolean paydayCode;

    public DiscountTag(boolean iWorkHereCode, boolean stay4Get1Code, boolean paydayCode){
        this.iWorkHereCode = iWorkHereCode;
        this.stay4Get1Code = stay4Get1Code;
        this.paydayCode = paydayCode;
    }

    public boolean getIWorkHereCode(){
        return this.iWorkHereCode;
    }
    public boolean getStay4Get1Code(){
        return this.stay4Get1Code;
    }
    public boolean getPaydayCode(){
        return this.paydayCode;
    }
}
