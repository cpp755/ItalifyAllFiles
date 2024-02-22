package com.example.italify;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LessonActivity extends AppCompatActivity {

    List<DataInitializer.Video> videos;
    LessonAdapter lessonAdapter;
    RecyclerView lessonList;
    Toolbar toolbar;
    int lessonIndex;
    public static String currentLessonTitle = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lesson);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        {
            toolbar = findViewById(R.id.toolbar);
            toolbar.findViewById(R.id.starIcon).setVisibility(View.GONE);
            toolbar.findViewById(R.id.starCount).setVisibility(View.GONE);
            toolbar.findViewById(R.id.homeIcon).setVisibility(View.VISIBLE);
            ImageView backButton = findViewById(R.id.backIcon);
            ImageView homeButton = findViewById(R.id.homeIcon);
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
            homeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    //TODO(1): should we keep this home icon even in lesson page?? bcaz the functionality is same as back button or by device's back button
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    view.getContext().startActivity(intent);
                }
            });
        }


        Intent intent = getIntent();
        if (intent.hasExtra("lesson")) {
            String numOfLesson = intent.getStringExtra("lesson");
//            String title = intent.getStringExtra("title");

            lessonIndex = Integer.parseInt(numOfLesson);
            videos = DataInitializer.allVideos()[lessonIndex-1];
            String asghar  = DataInitializer.allLessons().get(lessonIndex-1).lessonTitles;;
//            currentLessonTitle = DataInitializer.allLessons().get(Integer.parseInt(numOfLesson)-1).lessonTitles;
            String[] numberOfLesson = asghar.split(" ");
            TextView titleTextView = toolbar.findViewById(R.id.title);
            if (titleTextView != null) {
                titleTextView.setText(asghar.substring((numberOfLesson[0]+" "+numberOfLesson[1]).length()));
            }
        }

//        setSupportActionBar(toolbar);


//
//        ImageAdapter adapter = new ImageAdapter(imageList);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager();

    }

    @Override
    protected void onResume() {
        boolean [][] whichLessonHasPassedAlready = loadBooleanArray(this);
        lessonList = findViewById(R.id.lessonList);
        lessonAdapter = new LessonAdapter(this, videos, whichLessonHasPassedAlready[lessonIndex-1]);
        lessonList.setAdapter(lessonAdapter);
        lessonList.setLayoutManager(new LinearLayoutManager(this));
        super.onResume();
    }

    public static boolean[][] loadBooleanArray(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        String arrayString = sharedPreferences.getString("KEY_BOOLEAN_ARRAY", null);

        boolean[][] array = new boolean[DataInitializer.maxNumOfLessons][DataInitializer.maxNumOfVideosPerLesson];

        if (arrayString != null && arrayString.length() == DataInitializer.maxNumOfLessons * DataInitializer.maxNumOfVideosPerLesson) {
            int index = 0;
            for (int i = 0; i < DataInitializer.maxNumOfLessons; i++) {
                for (int j = 0; j < DataInitializer.maxNumOfVideosPerLesson; j++) {
                    array[i][j] = arrayString.charAt(index++) == '1';
                }
            }
        }
        return array;
    }
}