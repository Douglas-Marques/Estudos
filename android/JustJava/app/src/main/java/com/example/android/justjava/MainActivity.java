package com.example.android.justjava;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int quantity = 2;
    private int price = 5;
    private boolean checkBox = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void decrement(View view){
        if(quantity >= 1) {
            quantity -= 1;
            displayQuantity();
        }
    }

    public void increment(View view){
        quantity +=1;
        displayQuantity();
    }

    private int total(){
        return quantity * price;
    }

    private void preparePrice(){
        displayMessage("Amount Due: R$" + total());
    }

    private void displayQuantity(){
        TextView quanitityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quanitityTextView.setText(Integer.toString(quantity));
        if(quantity != 0){
            preparePrice();
        }
        else{
            displayMessage("Free");
        }
    }

    private void displayMessage(String message){
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    public void submitOrder(View view){
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(formatOrder());

        executeToast();
    }

    private String formatOrder(){
        return "Amount Due: R$" + total()+
               "\nAdd whipped cream? " + checkBox +
               "\nQuantity: " + quantity +
               "\nTotal: R$" + total();
    }

    private void executeToast(){
        Context context = getApplicationContext();
        Toast toastMessage = Toast.makeText(context, "Thank You!!!", Toast.LENGTH_SHORT);
        toastMessage.show();
    }

   /* public void onCheckBoxClicked(View view){
        CheckBox checkBox = (CheckBox) findViewById(R.id.notify_me_checkbox);
        //checkBox.isChecked() ? checkBox.setChecked(false) : checkBox.setChecked(true);
        if(checkBox.isChecked()){
            checkBox.setChecked(true);
        }
        else{
            checkBox.setChecked(false);
        }
    }*/
   public void onCheckBoxClicked(View view){
       checkBox = !checkBox;
   }
}
