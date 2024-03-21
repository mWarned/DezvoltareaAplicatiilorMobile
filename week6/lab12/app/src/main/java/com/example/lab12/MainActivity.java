package com.example.lab12;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{
    String[] countryNames={"India","China","Australia","Portugle","America","New Zealand"};
    private Button button1;
    int flags[] = {R.drawable.india, R.drawable.china, R.drawable.australia,
            R.drawable.portugal, R.drawable.america, R.drawable.new_zealand};
    private static final int MENU_ITEM_ONE_ID = R.id.one;
    private static final int MENU_ITEM_TWO_ID = R.id.two;
    private static final int MENU_ITEM_THREE_ID = R.id.three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spin = (Spinner) findViewById(R.id.simpleSpinner);
        spin.setOnItemSelectedListener(this);
        CustomAdapter customAdapter=new
                CustomAdapter(getApplicationContext(),flags,countryNames);
        spin.setAdapter(customAdapter);

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, button1);
                popup.getMenuInflater()
                        .inflate(R.menu.popup, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(
                                MainActivity.this, "You Clicked : " + item.getTitle(),
                                Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popup.show();
            }
        });

        Button contextMenuButton = findViewById(R.id.button2);
        registerForContextMenu(contextMenuButton);
    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        Toast.makeText(getApplicationContext(), countryNames[position],
                Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(this, "One selected", Toast.LENGTH_LONG).show();
                return true;
            case 2:
                Toast.makeText(this, "Two selected", Toast.LENGTH_LONG).show();
                return true;
            case 3:
                Toast.makeText(this, "Three selected", Toast.LENGTH_LONG).show();
                return true;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.overflow_menu_example, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        LinearLayout mainLayout = (LinearLayout)findViewById(R.id.linear_layout);
        TextView textView = (TextView)findViewById(R.id.text_view);
        int itemId = item.getItemId();
        if(itemId == R.id.red_menu)
        {
            mainLayout.setBackgroundColor(Color.RED);
            textView.setText("You select red menu.");
        }else if(itemId == R.id.yellow_menu)
        {
            mainLayout.setBackgroundColor(Color.YELLOW);
            textView.setText("You select yellow menu.");
        }else if(itemId == R.id.green_menu)
        {
            mainLayout.setBackgroundColor(Color.GREEN);
            textView.setText("You select green menu.");
        }
        return true;
    }
}
