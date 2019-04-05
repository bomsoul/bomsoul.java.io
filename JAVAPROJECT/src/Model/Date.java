package Model;

public class Date {
    private int hour,minute;
    private static Date ourInstance = new Date();

    public static Date getInstance() {
        return ourInstance;
    }

    private Date() {
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
