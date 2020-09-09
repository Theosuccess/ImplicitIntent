package com.example.implicitintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    //create private variables for the editText
    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareEditText;
    static final int REQUEST_IMAGE_CAPTURE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //find the views
        mWebsiteEditText = findViewById(R.id.website_editText);
        mLocationEditText = findViewById(R.id.location_editText);
        mShareEditText = findViewById(R.id.share_editText);
    }

    public void openwebsite(View view) {
        //Add a statement to the new openWebsite() method that gets the string value of the EditText
        String url = mWebsiteEditText.getText().toString();
        //Encode and parse that string into a Uri object
        Uri webpage = Uri.parse(url);
        //Create a new Intent with Intent.ACTION_VIEW as the action and the URI as the data:
        Intent intent = new Intent(Intent.ACTION_VIEW,webpage);
        //Use the resolveActivity() method and the Android package manager to find an Activity that can handle your implicit Intent.
        //make sure that the request resolved successfully.
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
        //Add an else block to print a Log message if the Intent could not be resolved
        else{
            Log.d("ImplicitIntent", "Can't handle this");
        }
    }

    public void openlocation(View view) {
        //get text from EditText and save it in loc variable
        String loc = mLocationEditText.getText().toString();
        //Parse that string into a Uri object with a geo search query
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        //Create a new Intent with Intent.ACTION_VIEW as the action and loc as the data.
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        // Find an activity to hand the intent and start that activity.
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
        else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    public void sharetext(View view) {
        //get text from EditText and save it in loc variable
        String txt = mShareEditText.getText().toString();
        //Define the mime type of the text to share
        String mimeType = "text/plain";
        //Call ShareCompat.IntentBuilder with these methods:
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setText(txt)
                .setChooserTitle("Share this Text with: ")
                .startChooser();
    }

    public void takePhoto(View view) {
        Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (photoIntent.resolveActivity(getPackageManager())!= null){
            startActivityForResult(photoIntent, REQUEST_IMAGE_CAPTURE);

        }
        //Add an else block to print a Log message if the Intent could not be resolved
        else{
            Log.d("ImplicitIntent", "Can't handle this");
        }
    }
}