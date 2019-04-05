package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AlarmTable {
    private final SimpleStringProperty hour;
    private final SimpleStringProperty minute;
    private final ImageView alarm;
    public AlarmTable(String hours, String minutes,int status){
        super();
        this.hour = new SimpleStringProperty(hours);
        this.minute = new SimpleStringProperty(minutes);
        this.alarm = new ImageView();
        Image im = new Image("/image/alarm9.png");
        this.alarm.setImage(im);
        if(status == 0){
            this.alarm.setVisible(false);
        }
        else{
            this.alarm.setVisible(true);
        }
    }

    public String getHour() {
        return hour.get();
    }
    public String getMinute() {
        return minute.get();
    }

    public ImageView getAlarm() {
        return alarm;
    }

    public int getCheck(){
        if(alarm.isVisible())
            return 1;
        return 0;
    }
}
