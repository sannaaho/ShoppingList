package com.example.shoppinglist;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.ShoppingList.MESSAGE";
    private EditText mEditText;
    private Button mSaveButton;
    private Button mDoneButton;
    private ArrayList<String> Items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.Items = new ArrayList<String>();
        this.mEditText = findViewById(R.id.item_editText);
        this.mSaveButton = findViewById(R.id.back_button);
        this.mDoneButton = findViewById(R.id.done_button);


        this.mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = mEditText.getText().toString();
                if(item.length()> 3 && item.length() < 15){
                    Items.add(item);
                    Toast.makeText(getApplicationContext(),"Lisätty ostoslistaan",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Virhe! Yritä uudestaan",Toast.LENGTH_SHORT).show();
                }
                mEditText.setText("");
            }
        });
        this.mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Uuden activityn ShowList avaus
                Intent myIntent = new Intent(getApplicationContext(),
                        ShowList.class);
                String list = "";
                for(String item : Items) {
                    list += item + "\n";
                }
                myIntent.putExtra(EXTRA_MESSAGE,list);
                startActivity(myIntent);
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}