package com.tom.udacityandroidbegin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class CalculatorActivity extends AppCompatActivity {

    private TextView mQuantityView;
    private TextView mPriceView;
    private int mNumberOfCoffees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calculator);
        mQuantityView = (TextView) findViewById(R.id.quantity_text_view);
        mPriceView = (TextView) findViewById(R.id.price_text_view);

        display(0);
        displayPrice(0);
    }

    void submitOrder(View target) {
        displayPrice(mNumberOfCoffees * 5);
    }

    void increment(View target) {
        mNumberOfCoffees++;
        display(mNumberOfCoffees);
    }

    void decrement(View target) {
        mNumberOfCoffees--;
        display(mNumberOfCoffees);
    }

    private void display(int quantity) {
        mQuantityView.setText(String.valueOf(quantity));
    }

    private void displayPrice(int price) {
        mPriceView.setText(NumberFormat.getCurrencyInstance().format(price));
    }
}
