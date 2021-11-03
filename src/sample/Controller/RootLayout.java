package sample.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import sample.calculate.RandomDemo;

import java.net.URL;
import java.util.ResourceBundle;

public class RootLayout implements Initializable {
    public Button button_start;
    public Label question1;
    public TextField result;
    public TextField showjudge;
    public Button button_confirm;
    public int work[];
    public int number[] = {0,0,0};//012依次表示为总题数，答对题数，错误题数
    public Label total;
    public Label total_right;
    public Label total_wrong;
    public int totalnumer = 50000;
    public int flag = 2;
    public int errorwork[] = new int[500] ;
    public Label error;
    public int position = 1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initial(){
        work[0] = 0;
        work[1] = 0;
        work[2] = 0;
        work[3] = 0;
        number[1] = 0;
        number[2] = 0;
        number[0] = 0;
        totalnumer = 50000;
        position = 0;
    }

    public void settotal10(javafx.event.ActionEvent actionEvent){
        totalnumer = 10;
    }

    public void settotal30(javafx.event.ActionEvent actionEvent){
        totalnumer = 30;
    }

    public void settotal50(javafx.event.ActionEvent actionEvent){
        totalnumer = 50;
    }

    public void setflag1(javafx.event.ActionEvent actionEvent){
        flag = 1;
    }

    public void setflag2(javafx.event.ActionEvent actionEvent){
        flag = 2;
    }

    public void setflag0(javafx.event.ActionEvent actionEvent){
        flag = 0;
    }

    public void button_restart_click(javafx.event.ActionEvent actionEvent){
        initial();
        button_start_click(actionEvent);
    }

    public void button_start_click(javafx.event.ActionEvent actionEvent) {
        if(number[2]!=number[0]-number[1])
        {
            number[2] = number[0]-number[1];
            if(number[2]!=0)
            {
                errorwork[4*(number[2]-1)] = work[0];
                errorwork[4*(number[2]-1)+1] = work[1];
                errorwork[4*(number[2]-1)+2] = work[2];
                errorwork[4*(number[2]-1)+3] = work[3];
                showerrornow(actionEvent);
            }
        }
        work = RandomDemo.getRandom(flag);
        String S_work = RandomDemo.toString(work);
        question1.setText(S_work);
        number[0]++;
        total.setText("总题数:      "+String.valueOf(number[0]));
        total_right.setText("答对题数:   "+String.valueOf(number[1]));
        total_wrong.setText("错误题数:   "+String.valueOf(number[2]));
    }

    public void button_next_click(javafx.event.ActionEvent actionEvent){
        if(number[0] < totalnumer)
            button_start_click(actionEvent);
        else
            showjudge.setText("已完成规定数量的练习");
    }
    public void getresult(ActionEvent actionEvent) {
        String myresult;
        int myresult_int;
        myresult=result.getText();
        myresult_int = Integer.parseInt(myresult);
        if(myresult_int != work[3])
        {
            showjudge.setText("回答错误，请重新输入");
            number[2]++;
            errorwork[4*(number[2]-1)] = work[0];
            errorwork[4*(number[2]-1)+1] = work[1];
            errorwork[4*(number[2]-1)+2] = work[2];
            errorwork[4*(number[2]-1)+3] = work[3];
        }
        else
        {
            showjudge.setText("你真棒，回答正确");
            number[1]++;
        }
    }

    public void showanswer(ActionEvent actionEvent){
        showjudge.setText("正确答案为：  " + String.valueOf(work[3]));
    }

    public void showerrornow(ActionEvent actionEvent){
        position = number[2];
        showerror(actionEvent);
    }
    public void showerror(ActionEvent actionEvent){
        if(errorwork[4*(position-1)+2] == 0)
            error.setText(String.valueOf(errorwork[4*(position-1)]) + "+" + String.valueOf(errorwork[4*(position-1)+1]) + "=" + String.valueOf(errorwork[4*(position-1)+3]));
        else
            error.setText(String.valueOf(errorwork[4*(position-1)]) + "-" + String.valueOf(errorwork[4*(position-1)+1]) + "=" + String.valueOf(errorwork[4*(position-1)+3]));
    }

    public void nexterror(ActionEvent actionEvent){
        if( position >= number[2])
        {
            error.setText("不能再往下了");
            return;
        }
        position ++;
        showerror(actionEvent);
    }

    public void foreerror(ActionEvent actionEvent){
        if( position <= 1)
        {
            error.setText("不能再往前了");
            return;
        }
        position --;
        showerror(actionEvent);
    }
}



