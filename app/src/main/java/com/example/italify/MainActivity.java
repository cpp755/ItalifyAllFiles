package com.example.italify;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Integer> images;
    List<String> titles;
    Adapter adapter;
    RecyclerView datalist;
    public static int stars = 0;
    Toolbar toolbar;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);

        boolean runJustOnce_happenedBefore = sharedPreferences.getBoolean("happenedBefore", false);
        if (!runJustOnce_happenedBefore){
            ArrayList<Integer> [][] asgharAgha = new ArrayList[DataInitializer.maxNumOfLessons][DataInitializer.maxNumOfVideosPerLesson];
            boolean [][] justForSaving = new boolean[DataInitializer.maxNumOfLessons][DataInitializer.maxNumOfVideosPerLesson];
            for (int i = 0; i < asgharAgha.length; i++) {
                for (int j = 0; j < asgharAgha[i].length; j++) {
                    asgharAgha[i][j] = new ArrayList<>();
                    justForSaving[i][j] = false;
                }
            }
//            Log.i("kobra from main",""+asgharAgha[0][0]);
//            asgharAgha[0][9].add(5);
//            asgharAgha[0][9].add(2);
            saveArray(asgharAgha);
            saveBooleanArray(this, justForSaving);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("happenedBefore", true);
            editor.apply();
        }


        toolbar = findViewById(R.id.toolbar);
        toolbar.findViewById(R.id.backIcon).setVisibility(View.GONE);
        TextView titleTextView = toolbar.findViewById(R.id.title);
        if (titleTextView != null) {
            titleTextView.setText("Italify");
        }

        datalist = findViewById(R.id.datalist);
//        titles = new ArrayList<>();
//        images = new ArrayList<>();
//
//        titles = DataInitializer.lessonsTitles();
//        images = DataInitializer.lessonsIcons();

        adapter = new Adapter(this, DataInitializer.allLessons());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        datalist.setLayoutManager(gridLayoutManager);
        datalist.setAdapter(adapter);

//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setTitle("Centered Title");

//        Button b = findViewById(R.id.button);
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), LessonActivity.class);
//                v.getContext().startActivity(intent);
//            }
//        });
    }


    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        showCustomDialog(this);
//        super.onBackPressed();
    }
    @Override
    protected void onResume() {
        stars = sharedPreferences.getInt("stars", 0);
        TextView starCount = toolbar.findViewById(R.id.starCount);
        starCount.setText("" + stars);
        adapter.notifyDataSetChanged();
        super.onResume();
    }


    public void saveArray(ArrayList<Integer>[][] array) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Convert the 2D array to a string
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                // Convert each ArrayList to a comma-separated string
                stringBuilder.append(TextUtils.join(",", array[i][j]));
                if (j < array[i].length - 1) {
                    stringBuilder.append("|");
                }
            }
            if (i < array.length - 1) {
                stringBuilder.append(";");
            }
        }
        editor.putString("array_key", stringBuilder.toString());
        editor.apply();
    }
    public void saveBooleanArray(Context context, boolean[][] array) {
        SharedPreferences sharedPreferences = context.getSharedPreferences( "my_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Convert the 2D boolean array to a string
        StringBuilder stringBuilder = new StringBuilder();
        for (boolean[] row : array) {
            for (boolean value : row) {
                stringBuilder.append(value ? "1" : "0");
            }
        }
        String arrayString = stringBuilder.toString();

        // Save the string representation of the boolean array
        editor.putString("KEY_BOOLEAN_ARRAY", arrayString);
        editor.apply();
    }



    private AlertDialog dialog;
    private void showCustomDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.leave_practice_confirmation_layout, null);

        TextView textViewMessage = dialogView.findViewById(R.id.textViewMessage);
        Button buttonPositive = dialogView.findViewById(R.id.buttonPositive);
        Button buttonNegative = dialogView.findViewById(R.id.buttonNegative);
        ImageView exitSign = dialogView.findViewById(R.id.exitSign);

        textViewMessage.setText("Are you sure to exit the Italify?");
        buttonPositive.setText("Exit");
        buttonNegative.setText("Stay in app");

        buttonPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finishAffinity(); // Finish all activities in the current task
            }
        });

        buttonNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        exitSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        builder.setView(dialogView);
        dialog = builder.create(); // Assign the created dialog to the global variable
        dialog.show();
    }
}