package com.example.italify;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.ViewHolder>{

    List<DataInitializer.Video> videos;
    LayoutInflater inflater;
    Context context;
    boolean [] alreadyWatchedAndPracticeStatus;
    public LessonAdapter(Context ctx, List<DataInitializer.Video> videos, boolean[] booleans){
        this.inflater = LayoutInflater.from(ctx);
        this.videos = videos;
        this.context = ctx;
        this.alreadyWatchedAndPracticeStatus = booleans;
    }

    @NonNull
    @Override
    public LessonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.lesson_grid_layout, parent, false);
        return new LessonAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonAdapter.ViewHolder holder, int position) {
        holder.thumbnail.setImageResource(videos.get(position).thumbnail);
        holder.title.setText(videos.get(position).title);

        if (alreadyWatchedAndPracticeStatus != null && alreadyWatchedAndPracticeStatus[position]){
            holder.alreadyDoneStatusIndicator.setVisibility(View.VISIBLE);
        }

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenHeight = displayMetrics.heightPixels;

        // Set the height of the item
        int itemHeight = (int) (screenHeight * 0.2); // You can adjust the proportion as needed
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = itemHeight;
        holder.itemView.setLayoutParams(layoutParams);
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView thumbnail, alreadyDoneStatusIndicator;
        Button practice, watch;
        TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.video_thumbnail);
            practice = itemView.findViewById(R.id.button_practice);
            watch = itemView.findViewById(R.id.button_watch);
            title = itemView.findViewById(R.id.videoTitle);
            alreadyDoneStatusIndicator = itemView.findViewById(R.id.lessonStatus);


            practice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, PracticeActivity.class);
                    intent.putExtra("lessonNumber",videos.get(getAdapterPosition()).lessonNumber);
                    intent.putExtra("videoNumber",videos.get(getAdapterPosition()).videoNumber);
//                    intent.putExtra("title",videos.get(getAdapterPosition()).title);
//                    intent.putExtra("lessonTitle", LessonActivity.currentLessonTitle);
                    context.startActivity(intent);
//                    TextView textView = v.findViewById(R.id.baseCellText);
//                    String text = "";
//                    if (textView != null) {
//                        text = textView.getText().toString();
//                        // Now 'text' variable contains the text from the TextView
//                    }
//                    intent.putExtra("lesson", text);
//                    context.startActivity(intent);
                }
            });
            watch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, VideoActivity.class);
                    intent.putExtra("lessonNumber",videos.get(getAdapterPosition()).lessonNumber);
                    intent.putExtra("videoNumber",videos.get(getAdapterPosition()).videoNumber);
//                    intent.putExtra("title",videos.get(getAdapterPosition()).title);
//                    intent.putExtra("lessonTitle", LessonActivity.currentLessonTitle);
                    context.startActivity(intent);
                }
            });
        }
    }
}
