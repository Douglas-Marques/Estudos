package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int quantity = 2;
    private boolean whippedCream, chocolate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void decrement(View view){
        if(quantity >= 2) {
            quantity -= 1;
            displayQuantity();
        }
    }

    public void increment(View view){
        if(quantity <= 99){
            quantity +=1;
            displayQuantity();
        }
    }

    private int total(){
        int price = 5;
        if(chocolate){
            price += 2;
        }
        if(whippedCream){
            price += 1;
        }
        return quantity * price;
    }

    private void preparePrice(){
        displayMessage(getResources().getString(R.string.money) + total());
    }

    private void displayQuantity(){
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(Integer.toString(quantity));
        preparePrice();
    }

    private void displayMessage(String message){
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    public void submitOrder(View view){
        String formatEmail= formatOrder();

        //enviar email
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));//only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + userName());
        intent.putExtra(Intent.EXTRA_TEXT, formatEmail);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

    //Usando string format para se adequar a convenção
    private String formatOrder(){
        String email = String.format("%1s: %2s",  getResources().getString(R.string.name), userName());
               email += String.format("\n%1s %2s", getResources().getString(R.string.addWhippedCream), whippedCream);
               email += String.format("\n%1s %2s", getResources().getString(R.string.addChocolate), chocolate);
               email += String.format("\n%1s: %2s", getResources().getString(R.string.quantity), quantity);
               email += String.format("\n%1s: %2s%3s\n", getResources().getString(R.string.total), getResources().getString(R.string.money),total());
               email += getResources().getString(R.string.thanks);
        return email;
    }

    //pega o nome que o usuario digitar
    private String userName(){
        EditText name = (EditText) findViewById(R.id.name_field);
        return name.getText().toString();
    }

    public void chocolateCheck(View view){
        chocolate = !chocolate;
        preparePrice();
    }

    public void whippedCreamCheck(View view){
        whippedCream = !whippedCream;
        preparePrice();
    }
}
