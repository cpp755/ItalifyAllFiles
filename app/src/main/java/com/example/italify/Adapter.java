package com.example.italify;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    ArrayList<DataInitializer.Lesson> lesson;
    LayoutInflater inflater;
    Context context;
    public Adapter(Context ctx, ArrayList<DataInitializer.Lesson> lesson){
        this.inflater = LayoutInflater.from(ctx);
        this.lesson = lesson;
        this.context = ctx;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_grid_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(lesson.get(position).lessonTitles);
        if (MainActivity.stars >= lesson.get(position).requiredStarsToUnlock){
            holder.gridIcon.setImageResource(lesson.get(position).lessonIcons);
        } else {
            holder.gridIcon.setImageResource(R.drawable.baseline_lock_24);
        }


        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenHeight = displayMetrics.heightPixels;

        // Set the height of the item
        int itemHeight = (int) (screenHeight * 0.17); // You can adjust the proportion as needed
        ViewGroup.LayoutParams layoutParams = holder.gridIcon.getLayoutParams();
        layoutParams.height = itemHeight;
        holder.gridIcon.setLayoutParams(layoutParams);
    }

    @Override
    public int getItemCount() {
        return lesson.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView gridIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.baseCellText);
            gridIcon = itemView.findViewById(R.id.baseCellImage);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView textView = v.findViewById(R.id.baseCellText);
                    String text ="", lessonTitle ="";
                    String[] numberOfLesson = new String[6]; //6 is the maximum number of words in the title of a lesson
                    if (textView != null) {
                        text = textView.getText().toString();
                        numberOfLesson = text.split(" ");
                        lessonTitle = text.substring((numberOfLesson[0]+" "+numberOfLesson[1]).length());
                    }
                    if (MainActivity.stars >= lesson.get(Integer.parseInt(numberOfLesson[1]) - 1).requiredStarsToUnlock){
                        Context context = v.getContext();
                        Intent intent = new Intent(context, LessonActivity.class);
                        intent.putExtra("lesson", numberOfLesson[1]);
//                        intent.putExtra("title", lessonTitle);
                        context.startActivity(intent);
                    } else {
                    Toast.makeText(v.getContext(),"You must have "+lesson.get(Integer.parseInt(numberOfLesson[1]) - 1).requiredStarsToUnlock+ " stars to unlock this lesson.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
