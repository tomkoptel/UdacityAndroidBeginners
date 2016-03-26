package com.tom.udacityandroidbegin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class CalculatorActivity extends AppCompatActivity {

    private TextView mQuantityView;
    private TextView mPriceView;
    private int mNumberOfCoffees;

    private MenuItem mShareMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calculator);
        mQuantityView = (TextView) findViewById(R.id.quantity_text_view);
        mPriceView = (TextView) findViewById(R.id.price_text_view);

        display(0);
        displayPrice(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean created = super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.share_menu, menu);

        mShareMenu = menu.findItem(R.id.share_action);
        MenuItemCompat.setActionProvider(mShareMenu, new ShareActionProvider(this));

        return created;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean prepared = super.onPrepareOptionsMenu(menu);

        ShareActionProvider actionProvider = (ShareActionProvider)
                MenuItemCompat.getActionProvider(mShareMenu);
        Intent intent = createShareIntent(getString(R.string.hello_world));
        actionProvider.setShareIntent(intent);

        return prepared;
    }

    private Intent createShareIntent(String text) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");

        //THIS TEXT IS WHAT I WANT TO OBTAIN DYNAMICALLY
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, text);
        return shareIntent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean selected = super.onOptionsItemSelected(item);


        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.another_action:
                openBirthdayPage();
                break;
        }

        return selected;
    }

    private void openBirthdayPage() {
        Intent birthdayPage = new Intent(this, BirthDayPage.class);
        startActivity(birthdayPage);
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
