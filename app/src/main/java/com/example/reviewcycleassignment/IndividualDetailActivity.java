package com.example.reviewcycleassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class IndividualDetailActivity extends AppCompatActivity {
    TextView textviewName,textviewGender,textviewdob,textviewcountry,textviewphone,textviewemail;
    ImageView individualUserImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_detail);

        individualUserImage= findViewById(R.id.ivUserImg);


        textviewName= findViewById(R.id.displayname);
        textviewGender= findViewById(R.id.displaygender);
        textviewdob= findViewById(R.id.displaydob);
        textviewcountry= findViewById(R.id.displaycountry);
        textviewphone= findViewById(R.id.displayphone);
        textviewemail= findViewById(R.id.displayemail);

        Intent intent = getIntent();
        String name= intent.getStringExtra("name");
        String gender= intent.getStringExtra("gender");
        String dob = intent.getStringExtra("dob");
        String country= intent.getStringExtra("country");
        String phone= intent.getStringExtra("phone");
        String email= intent.getStringExtra("email");
        String image= intent.getStringExtra("image");


        String uri = "@drawable/" + image;
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        individualUserImage.setImageResource(imageResource);


        textviewName.setText("Name :"+name);
        textviewGender.setText("Gender :"+gender);
        textviewdob.setText("DOB :"+dob);
        textviewcountry.setText("Country :"+country);
        textviewphone.setText("Phone :"+phone);
        textviewemail.setText("Email :"+email);
    }
}
