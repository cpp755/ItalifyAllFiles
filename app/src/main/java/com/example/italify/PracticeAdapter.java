package com.example.italify;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
public class PracticeAdapter extends RecyclerView.Adapter<PracticeAdapter.ViewHolder> {
    List<DataInitializer.Question> questions;
    LayoutInflater inflater;
    Context context;
    private OnItemCheckedChangeListener listener;
    boolean comesFromEvaluation = false, isThereAnySavedAnswer = false, showToastOnce = true;
    ArrayList<Integer> previouslySavedAnswers;
    int[] currentResponsesCorrectnessStatus;






    //TODO check for nullity b4 any usage of 4th & 5th adapter parameters






    public interface OnItemCheckedChangeListener {
        void onItemCheckedChange(int position, int checkedRadioButtonId);
    }
    public PracticeAdapter(Context ctx, List<DataInitializer.Question> questions, boolean comesFromEvaluation, ArrayList<Integer> previouslySavedAnswers, int[] currentResponsesCorrectnessStatus){
        this.questions = questions;
        this.context = ctx;
        this.showToastOnce = true;
        this.previouslySavedAnswers = previouslySavedAnswers;
        this.currentResponsesCorrectnessStatus = currentResponsesCorrectnessStatus;
        this.comesFromEvaluation = comesFromEvaluation;
        this.inflater = LayoutInflater.from(ctx);
        if (previouslySavedAnswers != null && previouslySavedAnswers.size()>0){
            isThereAnySavedAnswer = true;
        }

    }
    @NonNull
    @Override
    public PracticeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.practice_grid_layout, parent, false);
        return new PracticeAdapter.ViewHolder(view);
    }

    //TOD make a toast if there's any previously saved answers and load them
    //TOD also specify the checked item. so when user changes some answers, it would be effective
    @Override
    public void onBindViewHolder(@NonNull PracticeAdapter.ViewHolder holder, int position) {
        holder.question.setText(questions.get(position).questionText);
        holder.answerA.setText("A) " + questions.get(position).questionAnswerA);
        holder.answerB.setText("B) " + questions.get(position).questionAnswerB);
        holder.answerC.setText("C) " + questions.get(position).questionAnswerC);
        holder.answerD.setText("D) " + questions.get(position).questionAnswerD);
        if (isThereAnySavedAnswer){
            if (showToastOnce && !comesFromEvaluation){
                Toast.makeText(holder.question.getContext(), "Your answers is retrieved successfully, you can resume your last effort.", Toast.LENGTH_SHORT).show();
                showToastOnce = false;
            }
            int temp;
                temp = previouslySavedAnswers.get(position);
                if (temp != 0){
                    if (temp == 1){
//                        holder.answerA.setButtonDrawable(R.drawable.custom_checked_radio_button);
                        holder.answerA.setChecked(true);
                    } else if (temp == 2){
//                        holder.answerB.setButtonDrawable(R.drawable.custom_checked_radio_button);
                        holder.answerB.setChecked(true);
                    } else if (temp == 3){
//                        holder.answerC.setButtonDrawable(R.drawable.custom_checked_radio_button);
                        holder.answerC.setChecked(true);
                    } else if (temp == 4){
//                        holder.answerD.setButtonDrawable(R.drawable.custom_checked_radio_button);
                        holder.answerD.setChecked(true);
                    }
                }
        }
        if (comesFromEvaluation){
            holder.answerA.setEnabled(false);
            holder.answerB.setEnabled(false);
            holder.answerC.setEnabled(false);
            holder.answerD.setEnabled(false);
            if (currentResponsesCorrectnessStatus != null){
                if (currentResponsesCorrectnessStatus[position] == PracticeActivity.CORRECT){
                    holder.responseStatusImageView.setImageResource(R.drawable.baseline_check_24);
                    holder.responseStatusImageView.setVisibility(View.VISIBLE);
                } else if (currentResponsesCorrectnessStatus[position] == PracticeActivity.WRONG){
                    holder.responseStatusImageView.setImageResource(R.drawable.baseline_clear_24_red);
                    holder.responseStatusImageView.setVisibility(View.VISIBLE);
                }
            }
        }
        holder.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (listener != null && !comesFromEvaluation) {
                listener.onItemCheckedChange(position, checkedId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public void setOnItemCheckedChangeListener(OnItemCheckedChangeListener listener) {
        this.listener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        TextView question;
        RadioButton answerA,answerB, answerC,answerD;
        Handler handler = new Handler();
        RadioGroup radioGroup;
        ImageView responseStatusImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.question);
            answerA = itemView.findViewById(R.id.answer1);
            answerB = itemView.findViewById(R.id.answer2);
            answerC = itemView.findViewById(R.id.answer3);
            answerD = itemView.findViewById(R.id.answer4);
            radioGroup = itemView.findViewById(R.id.optionsGroup);
            responseStatusImageView = itemView.findViewById(R.id.responseStatus);
            question.setOnLongClickListener(this);
        }


        @Override
        public boolean onLongClick(View v) {
            DataInitializer.Question currentQuestion = questions.get(getAdapterPosition());
            question.setText(currentQuestion.englishTranslationOfTheQuestion);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    question.setText(currentQuestion.questionText); // Revert to the original string
                }
            }, 5000); // Delay in milliseconds
            return true; // Consume the long press event
        }
    }
}
