package com.exercise.thesis.hellodoc.AboutApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.exercise.thesis.hellodoc.R;
import com.exercise.thesis.hellodoc.ui.MainActivity;
import com.exercise.thesis.hellodoc.ui.auth.WelcomeFragment;

public class AboutAppController extends AppCompatActivity {

    private ViewPager aboutAppViewPager;
    private LinearLayout dotsLayout;
    private AboutAppAdapter appAdapter;
    private TextView[] dots;
    private Button nextBtn;
    private Button prevBtn;
    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();*/
        setContentView(R.layout.activity_about_app_controller);
        aboutAppViewPager = (ViewPager) findViewById(R.id.about_app_viewpager);
        dotsLayout = (LinearLayout) findViewById(R.id.about_app_dots);
        prevBtn = (Button) findViewById(R.id.about_app_prevBtn);
        nextBtn = (Button) findViewById(R.id.about_app_nextBtn);
        appAdapter = new AboutAppAdapter(this);
        aboutAppViewPager.setAdapter(appAdapter); // Setting our created adapter to the viewpager
        dotsCreator(0); // When the view is created we will be in the first page
        aboutAppViewPager.addOnPageChangeListener(viewListener); // Set 'viewListener' as the listener when we will move from one page to another
        nextBtn.setOnClickListener(new View.OnClickListener() { // Get triggered when we press 'Next' button
            @Override
            public void onClick(View view) {
                if(nextBtn.getText().toString().toLowerCase().equals("got it")){
                    /*FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.doctor_patient_confirmation,new WelcomeFragment()).commit();*/
                    openNewActivity(); // If it is the final page then if we click 'Got It' Button we will move to the 'MainActivity'
                }
                else {
                    aboutAppViewPager.setCurrentItem(currentPage+1); // Else set current page to the next page
                }
            }
        });
        prevBtn.setOnClickListener(new View.OnClickListener() { // Get triggered when we press 'Previous' button
            @Override
            public void onClick(View view) {
                aboutAppViewPager.setCurrentItem(currentPage-1); // Set current page to previous page
            }
        });
    }
    private void openNewActivity() {
        Intent intent = new Intent(AboutAppController.this, MainActivity.class);
        startActivity(intent);
    }
    private void dotsCreator(int position){
        dots = new TextView[3]; // Initiate three textView for dots
        dotsLayout.removeAllViews(); // This removes all the previous views(It is important because we
        // are calling this function whenever page is changed from one to other. So we have to remove all
        // the views that we had set in previous function call & create a new view for the new page)
        for(int i=0;i<dots.length;i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;")); // This is for creating circle
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite)); // Setting color of the circle surface
            dotsLayout.addView(dots[i]); // Adding the dot to the view
        }
        if(dots.length>0){
            dots[position].setTextColor(getResources().getColor(R.color.orange)); // Changing the color of the dot to orange based on which page we are currently in.
        }
    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            dotsCreator(position); // When page is changed to another call the dot creator function to create new view for the new page
            currentPage = position;
            if(position==0){
                nextBtn.setEnabled(true);
                prevBtn.setEnabled(false);
                prevBtn.setVisibility(View.INVISIBLE);
                nextBtn.setText("Next");
                prevBtn.setText("");
            }
            else if(position==dots.length-1){
                nextBtn.setEnabled(true);
                prevBtn.setEnabled(true);
                prevBtn.setVisibility(View.VISIBLE);
                nextBtn.setText("Got It");
                prevBtn.setText("Back");
            }
            else {
                nextBtn.setEnabled(true);
                prevBtn.setEnabled(true);
                prevBtn.setVisibility(View.VISIBLE);
                nextBtn.setText("Next");
                prevBtn.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}