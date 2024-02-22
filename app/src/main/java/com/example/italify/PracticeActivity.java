package com.example.italify;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PracticeActivity extends AppCompatActivity implements PracticeAdapter.OnItemCheckedChangeListener {
    List<DataInitializer.Question> questions;
    int destinationAfterThisActivityFinished, home =0, back=1, lesson=2, destinationLessonIndex;
    boolean finishedAnswering = false, changeOrSelectAtLeastOneAnswer = false;
    PracticeAdapter practiceAdapter;
    RecyclerView questionsList;
    Toolbar toolbar;
    Button goBackToLesson, evaluate;
    TextView practiceDescription;
    public static final int CORRECT = 1, WRONG = -1, UNANSWERED = 0;
    int [] userSelectedAnswers, numOfCorrectWrongUnanswered = {0,0,0}, eachAnswerStatusCWUnans;
    int lessonIndex = 1, videoIndex = 1;
    SharedPreferences sharedPreferences;
    TextView reportText;
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    String reportfinalText;
    @Override
    public void onItemCheckedChange(int position, int checkedRadioButtonId) {
        Log.i("kobra", "button ID: "+checkedRadioButtonId);
        userSelectedAnswers[position] = (checkedRadioButtonId%10)-5;
        changeOrSelectAtLeastOneAnswer = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practice);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
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
                    if (finishedAnswering || !changeOrSelectAtLeastOneAnswer){
                        finish();
                    } else {
                        destinationAfterThisActivityFinished = back;
                        showCustomDialog(view.getContext());
                    }
                }
            });
            homeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finishedAnswering || !changeOrSelectAtLeastOneAnswer){
                        Intent intent = new Intent(view.getContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        view.getContext().startActivity(intent);
                    } else {
                        destinationAfterThisActivityFinished = home;
                        showCustomDialog(view.getContext());
                    }
//                    Intent intent = new Intent(view.getContext(), MainActivity.class);
//                    //TODO(1): should we keep this home icon even in lesson page?? bcaz the functionality is same as back button or by device's back button
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    view.getContext().startActivity(intent);
                }
            });
            evaluate = findViewById(R.id.evaluatePractice);
            goBackToLesson = findViewById(R.id.goBackToLesson);
            practiceDescription = findViewById(R.id.practiceDescription);
        }

        Intent intent = getIntent();

        String title = " ", lessonTitle = " ";
        if (intent.hasExtra("lessonNumber")) {
            lessonIndex = intent.getIntExtra("lessonNumber",1);
        }
        if (intent.hasExtra("videoNumber")) {
            videoIndex = intent.getIntExtra("videoNumber",1);
        }
        userSelectedAnswers = new int[DataInitializer.allQuestions()[lessonIndex-1][videoIndex-1].size()];
        Arrays.fill(userSelectedAnswers,0);
        eachAnswerStatusCWUnans = new int[DataInitializer.allQuestions()[lessonIndex-1][videoIndex-1].size()];
        Arrays.fill(eachAnswerStatusCWUnans,0);
//        if (intent.hasExtra("lessonTitle")) {
//            lessonTitle = intent.getStringExtra("lessonTitle");
            TextView titleTextView = toolbar.findViewById(R.id.title);
//            if (titleTextView != null) {
        String[] numberOfLesson = DataInitializer.allLessons().get(lessonIndex-1).lessonTitles.split(" ");
                titleTextView.setText(DataInitializer.allLessons().get(lessonIndex-1).lessonTitles.substring((numberOfLesson[0]+" "+numberOfLesson[1]).length()));
//            }
//        }
//        if (intent.hasExtra("title")) { //this is title of the video
//            title = intent.getStringExtra("title");
//            if (practiceDescription != null) {
                practiceDescription.setText("Practices for video " + DataInitializer.allVideos()[lessonIndex-1].get(videoIndex-1).title + "\n" + practiceDescription.getText());
//            }
//        }

        questions = DataInitializer.allQuestions()[lessonIndex-1][videoIndex-1];
//        Log.i("asghar", String.valueOf(questions));
        questionsList = findViewById(R.id.practiceList);

        //TOD check & fill 4th parameter if there's any
        ArrayList<Integer> [][] temp = loadArray();
        if (temp[lessonIndex-1][videoIndex-1].size()>0){
            for (int i =0; i<userSelectedAnswers.length; i++){
                userSelectedAnswers[i] = temp[lessonIndex-1][videoIndex-1].get(i);
            }
        }
        practiceAdapter = new PracticeAdapter(this, questions, finishedAnswering, temp[lessonIndex-1][videoIndex-1] ,null);
        practiceAdapter.setOnItemCheckedChangeListener(this);
        questionsList.setAdapter(practiceAdapter);
        questionsList.setLayoutManager(new LinearLayoutManager(this));


        int finalLessonIndex = lessonIndex;
        goBackToLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destinationLessonIndex = finalLessonIndex;
                if (finishedAnswering || !changeOrSelectAtLeastOneAnswer){
                    Intent intent = new Intent(v.getContext(), LessonActivity.class);
                    intent.putExtra("lesson", "" + destinationLessonIndex);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    v.getContext().startActivity(intent);
                }else {
                    destinationAfterThisActivityFinished = lesson;
                    showCustomDialog(v.getContext());
                }
            }
        });
        evaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int isAtLeastOneAnswerSelected = 0;
                for (int i = 0; i < userSelectedAnswers.length; i++){
                    isAtLeastOneAnswerSelected += userSelectedAnswers[i];
                }
                if (isAtLeastOneAnswerSelected <= 0){
                    Toast.makeText(v.getContext(), "Try to answer at least one question.", Toast.LENGTH_SHORT).show();
                } else {
                    if (finishedAnswering) {
                        showReportDialog(v.getContext(),null,null,finishedAnswering);
                    } else {
                        discardSavedAnswers();
                        int [] eachResponseStatus = checkAnswers();
                        showReportDialog(v.getContext(), numOfCorrectWrongUnanswered, eachResponseStatus, finishedAnswering);
                        finishedAnswering = true;
                        evaluate.setText("See report");
                        boolean [][] temp = loadBooleanArray(v.getContext());
                        temp[lessonIndex-1][videoIndex-1] = true;
                        saveBooleanArray(v.getContext(), temp);
                        ArrayList<Integer> currentAnswerToHighlightRadioButton = new ArrayList<>();
                        for (int value : userSelectedAnswers) {
                            currentAnswerToHighlightRadioButton.add(value);
                        }
                        practiceAdapter = new PracticeAdapter(v.getContext(), questions, finishedAnswering, currentAnswerToHighlightRadioButton ,eachResponseStatus);
                        questionsList.setAdapter(practiceAdapter);
                        questionsList.setLayoutManager(new LinearLayoutManager(v.getContext()));
                    }
                }
            }
        });

    }

    //TOD         ****************************************************************************************
    //TOD if we load any saved answers, we should immediately update userSelectedAnswers with those values
    //TOD         ****************************************************************************************



    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        if (finishedAnswering || !changeOrSelectAtLeastOneAnswer){
            finish();
        } else {
            destinationAfterThisActivityFinished = back;
            showCustomDialog(this);
        }
//        super.onBackPressed();
    }
    private int[] checkAnswers() {
        int numberOfQuestions = DataInitializer.allQuestions()[lessonIndex-1][videoIndex-1].size();
        ArrayList<DataInitializer.Question> questions = DataInitializer.allQuestions()[lessonIndex-1][videoIndex-1];
        int score = 0, unanswered = 0;
        for (int i=0; i<numberOfQuestions; i++){
            if (questions.get(i).correctAnswer == userSelectedAnswers[i]){
                score++;
                eachAnswerStatusCWUnans[i] = CORRECT;
                //TODO check to stop users from getting multiple stars from same questions --- **** it's too unnecessary right now

                //TOD              ASGHAR AGHA         اقا ی ارایه دیگه ام بنظر نیازه. واسه اینکه مشخص تو ی ارایه داشته باشیم هر جواب درست/غلط/خنثی بوده. که میفرستیم ب اداپتر راحت باشه اونجا کارش
                //TOD         ****************************************************************************************
                //TOD:         now, after finishing responding,         ***   remove any temporary saved answer   ***
                //TOD         ****************************************************************************************
                //TOD                  MAMMAD            on pressing DISCARD button in alert:  on dismiss, check if there's any saved answers from last time, remove them
            } else if (userSelectedAnswers[i] == 0){
                eachAnswerStatusCWUnans[i] = UNANSWERED;
                unanswered++;
            } else {
                eachAnswerStatusCWUnans[i] = WRONG;
            }
        }
        numOfCorrectWrongUnanswered[0] = score;
        numOfCorrectWrongUnanswered[1] = numberOfQuestions - score - unanswered;
        numOfCorrectWrongUnanswered[2] = unanswered;

        int stars = sharedPreferences.getInt("stars", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("stars", (stars + score));
        editor.apply();

        return eachAnswerStatusCWUnans; // TODO to get it and show it in report
    }

    private void discardSavedAnswers(){
        //TOD do we need to check if there is any saved answers before deleting??? i think no, because we make it null anyway
        ArrayList<Integer> [][] temp = loadArray();
//        Log.i("kobra DISCARD", ""+temp.length);
//        Log.i("kobra temp[0].length DISCARD", ""+temp[0].length);
//        Log.i("kobra +temp[0][0] DISCARD", ""+temp[0][0]);
        temp[lessonIndex-1][videoIndex-1] = new ArrayList<>();
        saveArray(temp);
    }
    //TOD implementation of save
    private void saveAnswers (){
//        int hasAnsweredAnyQuestion = 0; // if doesn't answer any question, then no need to save
//        for (int i=0; i<userSelectedAnswers.length; i++){
//            hasAnsweredAnyQuestion += userSelectedAnswers[i];
//        }
//        if (hasAnsweredAnyQuestion > 0) {
            ArrayList<Integer>[][] temp = loadArray();
            ArrayList<Integer> userAnswerToArraylist = new ArrayList<>();
            for (int value : userSelectedAnswers) {
                userAnswerToArraylist.add(value);
            }
//        Log.i("kobra", ""+temp.length);
//        Log.i("kobra temp[0].length", ""+temp[0].length);
//        Log.i("kobra +temp[0][0]", ""+temp[0][0]);
//TODO if user selected now answers, just skip saving
            temp[lessonIndex - 1][videoIndex - 1] = userAnswerToArraylist;
            saveArray(temp);
//        }

        //TOD              ASGHAR AGHA         اقا ی ارایه دیگه ام بنظر نیازه. واسه اینکه مشخص تو ی ارایه داشته باشیم هر جواب درست/غلط/خنثی بوده. که میفرستیم ب اداپتر راحت باشه اونجا کارش


        //TOD retrieve saved data => add new answers => save them
        //TOD practice adapter should check for any saved data, if there exist smthing, mark them and notify the user with toast
        //TODO on evaluation button pressed, remove saved responses if there is any *** AND *** save correct answers so user won't be able 2 get score again for them
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
    public ArrayList<Integer>[][] loadArray() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Retrieve the string from SharedPreferences
        String arrayString = sharedPreferences.getString("array_key", "");
Log.i("kobra arrayString",""+arrayString);
        // Split the string and reconstruct the 2D array of ArrayLists
        String[] rows = arrayString.split(";");
        Log.i("kobra, rows.lenght: ", ""+ rows.length);

        ArrayList<Integer>[][] array = new ArrayList[rows.length][];


        for (int i = 0; i < rows.length; i++) {
            rows[i] = rows[i] + '|';
            String[] cols = new String[DataInitializer.maxNumOfVideosPerLesson];

            int index;
            for (int y = 0; y< DataInitializer.maxNumOfVideosPerLesson; y++){
                index = rows[i].indexOf('|');
                cols[y] = rows[i].substring(0, index);
                rows[i] = rows[i].substring(index + 1);
            }
//            String[] cols = rows[i].split("\\|");
//            if (cols.length < DataInitializer.maxNumOfVideosPerLesson ){
//                for (int x=cols.length; x < DataInitializer.maxNumOfVideosPerLesson; x++){
//                }
//            }
            array[i] = new ArrayList[DataInitializer.maxNumOfVideosPerLesson];
//            Log.i("kobra, cols.lenght: ", ""+ cols.length);
            for (int j = 0; j < DataInitializer.maxNumOfVideosPerLesson; j++) {
                String[] elements = cols[j].split(",");
                array[i][j] = new ArrayList<>();
                for (String element : elements) {
                    if (!element.equals("")) {
//                        Log.i("kobra here.element: ", ""+element);
                        array[i][j].add(Integer.parseInt(element));
                    }
                }
            }
        }
        return array;
    }
    private AlertDialog dialog, reportDialog;
    private void showCustomDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.leave_practice_confirmation_layout, null);

        TextView textViewMessage = dialogView.findViewById(R.id.textViewMessage);
        Button buttonPositive = dialogView.findViewById(R.id.buttonPositive);
        Button buttonNegative = dialogView.findViewById(R.id.buttonNegative);
        ImageView exitSign = dialogView.findViewById(R.id.exitSign);

        textViewMessage.setText("do you want to save your progress before leaving?");

        buttonPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAnswers();
                dialog.dismiss();
                Toast.makeText(v.getContext(), "Your answers saved successfully.", Toast.LENGTH_SHORT).show();
                if (destinationAfterThisActivityFinished == home){
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    v.getContext().startActivity(intent);
                } else if (destinationAfterThisActivityFinished == back){
                    finish();
                } else if (destinationAfterThisActivityFinished == lesson){
                    Intent intent = new Intent(v.getContext(), LessonActivity.class);
                    intent.putExtra("lesson", "" + destinationLessonIndex);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    v.getContext().startActivity(intent);
                }
            }
        });
        buttonNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TOD                  MAMMAD              on dismiss, check if there's any saved answers from last time, remove them
                discardSavedAnswers();
                Toast.makeText(v.getContext(), "Your answers discarded successfully.", Toast.LENGTH_SHORT).show();

                dialog.dismiss();
                if (destinationAfterThisActivityFinished == home){
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    v.getContext().startActivity(intent);
                } else if (destinationAfterThisActivityFinished == back){
                    finish();
                } else if (destinationAfterThisActivityFinished == lesson){
                    Intent intent = new Intent(v.getContext(), LessonActivity.class);
                    intent.putExtra("lesson", "" + destinationLessonIndex);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    v.getContext().startActivity(intent);
                }
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
    private void showReportDialog(Context context, int [] numOfCWUnans, int [] eachResponseStatus, boolean finishedAnswering) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.practice_report_layout, null);

        reportText = dialogView.findViewById(R.id.reportText);
        Button buttonPositive = dialogView.findViewById(R.id.buttonPositive);
        ImageView exitSign = dialogView.findViewById(R.id.exitSign);

        if (finishedAnswering){
            reportText.setText(reportfinalText);
        } else {
            StringBuilder output = new StringBuilder();
            if (numOfCWUnans[0] >= 3) {
                output.append("You did a great Job! \nYou answered all the question correctly");
            } else if (numOfCWUnans[0] == 2) {
                output.append("You did a good Job! You answered 2 questions successfully\n" + "Number of wrong choises: " + numOfCWUnans[1] + "\nNumber of question left unanswered: " + numOfCWUnans[2]);
            } else if (numOfCWUnans[0] == 1) {
                output.append("Better luck next time! You answered only 1 questions successfully\n" + "Number of wrong choises: " + numOfCWUnans[1] + "\nNumber of question left unanswered: " + numOfCWUnans[2]);
            } else if (numOfCWUnans[0] == 0) {
                output.append("No correct response! you need to rewatch the video!\n" + "Number of wrong choises: " + numOfCWUnans[1] + "\nNumber of question left unanswered: " + numOfCWUnans[2]);
            }
            if (numOfCWUnans[0] != 3) {
                output.append("\n\n\nAI feedback on your answers:\nplease be patient while AI processing your answers.");
                reportText.setText(output.toString());
                StringBuilder AIprompt = new StringBuilder();
                AIprompt.append("can you please provide some extensive analyze & feedback on the correct answer for these questions respectively, regarding the possible options which comes after each question:  ");
                for (int i = 0; i <= 2; i++) {
                    if (eachResponseStatus[i] != CORRECT) {
                        AIprompt.append(questions.get(i).questionText + " a)" + questions.get(i).questionAnswerA + " b)" + questions.get(i).questionAnswerB + " c)" + questions.get(i).questionAnswerC + "\n\n");
                    }
                }
                Log.i("kobra", AIprompt.toString());
                callAPI(AIprompt.toString());
            } else {
                reportText.setText(output.toString());
            }
            reportfinalText = output.toString();
        }


        buttonPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportDialog.dismiss();
                Intent intent1 = new Intent(v.getContext(), ChatActivity.class);
                v.getContext().startActivity(intent1);
            }
        });
        exitSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportDialog.dismiss();
            }
        });

        builder.setView(dialogView);
        reportDialog = builder.create(); // Assign the created dialog to the global variable
        reportDialog.show();
    }


    void callAPI(String question){
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("model","gpt-3.5-turbo-instruct");
            jsonBody.put("prompt",question);
            jsonBody.put("max_tokens",2500);
            jsonBody.put("temperature",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(jsonBody.toString(),JSON);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/completions")
                .header("Authorization","Bearer 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")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        reportText.setText(reportText.getText() + "\nFailed to load response due to "+e.getMessage());
                    }
                });
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    JSONObject  jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonObject.getJSONArray("choices");
                        String result = jsonArray.getJSONObject(0).getString("text");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                reportText.setText(reportText.getText().subSequence(0, reportText.getText().length() - 51) + "\n" + result.trim()+"\n");
                                reportfinalText = reportText.getText().subSequence(0, reportText.getText().length() - 51) + "\n" + result.trim()+"\n";
                                Log.i("kobra",result);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            reportText.setText(reportText.getText() + "\nFailed to load response due to "+response.body().toString());
                        }
                    });
                }
            }
        });
    }

}