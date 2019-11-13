package com.example.reviewcycleassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.reviewcycleassignment.Model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener{


    EditText TextN, TextE, TextD, TextP;
    RadioGroup radioG;
    Spinner spin;
    Button buttonsubmit,buttonView;
    String name,gender,dob,country,email,image,phone;
    AutoCompleteTextView autoCompleteTextView;

    ArrayList<User> usersList = new ArrayList<>();


    String[] imagesuggestions = {"image1", "image2","image3","image4"};

    Calendar calendardata = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener mydatepicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            calendardata.set(Calendar.YEAR,i);
            calendardata.set(Calendar.MONTH,i1);
            calendardata.set(Calendar.DAY_OF_MONTH,i2);

            String mydateFormat ="dd-MM-y";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(mydateFormat, Locale.getDefault());
            TextD.setText(simpleDateFormat.format(calendardata.getTime()));
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        autoCompleteTextView=findViewById(R.id.autocompletetextview);
        TextN =findViewById(R.id.name);
        TextE =findViewById(R.id.email);
        TextD =findViewById(R.id.date);
        TextP =findViewById(R.id.phone);
        radioG=findViewById(R.id.gender);
        spin=findViewById(R.id.spCountry);

        buttonsubmit=findViewById(R.id.btnsubmit);
        buttonView=findViewById(R.id.btnview);

        List<String> countries = new ArrayList<>();


        countries.add(0,"Choose your country");
        countries.add("Nepal");
        countries.add("India");
        countries.add("Pakistan");
        countries.add("Bhutan");
        countries.add("Afganistan");
        countries.add("Srilanka");

        ArrayAdapter<String> adapter =new ArrayAdapter(this,R.layout.spinner_values,countries);
        spin.setAdapter(adapter);

        ArrayAdapter<String> dummyimageadapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, imagesuggestions);
        autoCompleteTextView = findViewById(R.id.autocompletetextview);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(dummyimageadapter);



        radioG.setOnCheckedChangeListener(this);
        buttonsubmit.setOnClickListener(this);
        buttonView.setOnClickListener(this);
        TextD.setOnClickListener(this);

        setSpinnerValue();
    }

    @Override
    public void onClick(View view) {
        name = TextN.getText().toString();
        dob= TextD.getText().toString();
        image=autoCompleteTextView.getText().toString();
        email= TextE.getText().toString();
        phone = TextP.getText().toString();



        if(view.getId()==R.id.btnsubmit)
        {
            if(validate())
            {
                usersList.add(new User(name,gender,dob,country,phone,email,image));
            }

            Toast.makeText(this, "User added", Toast.LENGTH_SHORT).show();
        }

        if(view.getId()==R.id.date)
        {
            new DatePickerDialog(this,mydatepicker,calendardata.get(Calendar.YEAR),calendardata.get(Calendar.MONTH),
                    calendardata.get(Calendar.DAY_OF_MONTH)).show();
        }

        if(view.getId()==R.id.btnview)
        {
            Intent intent = new Intent(this, DetailsView_RecycleView.class);

            intent.putExtra("userlist",usersList);
            startActivity(intent);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if(i== R.id.rbmale)
        {
            gender="Male";
        }
        if(i == R.id.rbfemale)
        {
            gender = "Female";
        }
        if(i== R.id.rother)
        {
            gender ="Other";
        }
    }

    private boolean validate(){
        if(TextUtils.isEmpty(name))
        {
            TextN.setError("Name Required");
            TextN.requestFocus();
            TextN.setHint("Enter your name here");
            return false;
        }
        if(TextUtils.isEmpty(dob))
        {
            TextD.setError("Enter A DOB");
            TextD.requestFocus();
            TextD.setHint("Enter your DOB");
            return false;
        }

        if(TextUtils.isEmpty(image))
        {
            autoCompleteTextView.setError("Enter a image name");
            autoCompleteTextView.requestFocus();
            autoCompleteTextView.setHint("Please enter a image name");
            return false;
        }

        if(TextUtils.isEmpty(email))
        {
            TextE.setError("Enter A Email");
            TextE.requestFocus();
            TextE.setHint("Please Enter a Email");
            return false;
        }

        if(TextUtils.isEmpty(gender))
        {
            Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(country))
        {
            Toast.makeText(this, "Please select a country", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(phone))
        {
            TextP.setError("Enter empty Phone");
            TextP.requestFocus();
            TextP.setHint("Please Enter a Phone");
            return false;
        }

        if(!TextUtils.isDigitsOnly(phone))
        {
            TextP.setError("Invalid Phone");
            TextP.requestFocus();
            TextP.setHint("Please Enter a Phone");
            return false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            TextE.setError("Invalid  Email");
            TextE.requestFocus();
            TextE.setHint("Please Enter a Email");
            return false;
        }

        return  true;
    }


    private void setSpinnerValue(){

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(adapterView.getItemAtPosition(i).equals("Choose Country"))
                {
                    Toast.makeText(getApplicationContext(),"Please Select any one option",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    country = adapterView.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
}
