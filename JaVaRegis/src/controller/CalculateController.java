package controller;

import Model.Calculator;
import Model.GradeCalculation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Database;

import java.io.IOException;
import java.util.ArrayList;

public class CalculateController {
    @FXML
    Button mainpage,add,remover;
    @FXML
    TextField course;
    @FXML
    ChoiceBox<String> gradeguess;
    @FXML
    TableView<Calculator> tableView;
    @FXML
    Label totalcredit,totalpoint,gpax,warning;
    @FXML
    TableColumn<Calculator,String> courseid,coursename,grade;
    @FXML TableColumn<Calculator,Integer> credit;

    ArrayList<GradeCalculation> subject = new ArrayList();

    ObservableList<Calculator> data = FXCollections.observableArrayList();

    public void addBtn(){
        if(course.getText().equals("")){
            warning.setText("Please Enter Course ID");
            course.clear();
            warning.setVisible(true);
        }
        if(!Database.checkCourse(course.getText())){
            warning.setText("No course with this ID");
            course.clear();
            warning.setVisible(true);
        }
        else{
            warning.setVisible(false);
            String [] s =  Database.search(course.getText()).split(":");
            course.clear();
            subject.add(new GradeCalculation(s[0],s[1],Integer.parseInt(s[2]),gradeguess.getValue()));
            data.add(new Calculator(s[0],s[1],Integer.parseInt(s[2]),gradeguess.getValue()));
            calculate();
        }
    }
    public void removeBtn(){
        if(tableView.getSelectionModel().isEmpty()){
            warning.setVisible(true);
            warning.setText("No row selected");
        }
        else{
            String courseID = tableView.getSelectionModel().getSelectedItem().getCourseid();
            warning.setVisible(false);
            for(int i = 0;i < subject.size();i++){
                if(subject.get(i).getCourseid().equals(courseID)){
                    subject.remove(i);
                    break;
                }
            }
            data.clear();
            for(int i = 0;i < subject.size();i++){
                data.add(new Calculator(subject.get(i).getCourseid(),subject.get(i).getCoursename(),subject.get(i).getCredit(),subject.get(i).getGrade()));
            }
            calculate();
        }
    }

    public void calculate(){
        double point  = 0;
        int credit = 0;
        for(int i = 0;i < subject.size();i++){
            point += (subject.get(i).getGrade()*subject.get(i).getCredit());
            credit += subject.get(i).getCredit();
        }
        totalpoint.setText(point + "");
        totalcredit.setText(credit + "");
        gpax.setText(String.format("%.2f",(point/credit)));
    }

    @FXML public void toMainPageBtn(ActionEvent event){
        mainpage = (Button) event.getSource();
        Stage stage = (Stage) mainpage.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resource/MainPage.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load(), 700, 500));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize(){
        gradeguess.getItems().add("A");
        gradeguess.getItems().add("B+");
        gradeguess.getItems().add("B");
        gradeguess.getItems().add("C+");
        gradeguess.getItems().add("C");
        gradeguess.getItems().add("D+");
        gradeguess.getItems().add("D");
        gradeguess.getItems().add("F");
        gradeguess.setValue("A");
        courseid.setCellValueFactory(new PropertyValueFactory<Calculator, String>("courseid"));
        courseid.setStyle("-fx-alignment: CENTER;");
        coursename.setCellValueFactory(new PropertyValueFactory<Calculator, String>("coursename"));
        coursename.setStyle("-fx-alignment: CENTER;");
        credit.setCellValueFactory(new PropertyValueFactory<Calculator, Integer>("credit"));
        credit.setStyle("-fx-alignment: CENTER;");
        grade.setCellValueFactory(new PropertyValueFactory<Calculator, String>("grade"));
        grade.setStyle("-fx-alignment: CENTER;");
        totalcredit.setAlignment(Pos.CENTER_RIGHT);
        totalpoint.setAlignment(Pos.CENTER_RIGHT);
        gpax.setAlignment(Pos.CENTER_RIGHT);
        warning.setVisible(false);
        tableView.setItems(data);
    }


}
