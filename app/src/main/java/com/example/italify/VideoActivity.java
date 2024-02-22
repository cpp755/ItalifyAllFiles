package com.example.italify;

import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerControlView;
import com.google.android.exoplayer2.ui.StyledPlayerView;

import java.io.IOException;

public class VideoActivity extends AppCompatActivity {

    private VideoView videoView;
    Button practiceNow, watchNextVideo;
    ExoPlayer player;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_video);
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
            watchNextVideo = findViewById(R.id.watchnextvideoB);
            practiceNow = findViewById(R.id.practicenowB);
        }
        Intent intent = getIntent();
        int lessonIndex = 1, videoIndex = 1;
        String title = " ", lessonTitle = " ";
        if (intent.hasExtra("lessonNumber")) {
            lessonIndex = intent.getIntExtra("lessonNumber",1);
        }
        if (intent.hasExtra("videoNumber")) {
            videoIndex = intent.getIntExtra("videoNumber",1);
        }
//        if (intent.hasExtra("lessonTitle")) {
//            lessonTitle = intent.getStringExtra("lessonTitle");
            TextView titleTextView = toolbar.findViewById(R.id.title);
//            if (titleTextView != null) {
        String[] numberOfLesson = DataInitializer.allLessons().get(lessonIndex-1).lessonTitles.split(" ");
        titleTextView.setText(DataInitializer.allLessons().get(lessonIndex-1).lessonTitles.substring((numberOfLesson[0]+" "+numberOfLesson[1]).length()));
//            }
//        }
//        if (intent.hasExtra("title")) {
//            title = intent.getStringExtra("title");
            TextView videoTitle = findViewById(R.id.videoTitle);
//            if (videoTitle != null) {
                videoTitle.setText(DataInitializer.allVideos()[lessonIndex-1].get(videoIndex-1).title);
//            }
//        }
        if (DataInitializer.allVideos()[lessonIndex-1].size() <= videoIndex){
            watchNextVideo.setText("Launch next lesson");
        }

//        StyledPlayerView playerView = findViewById(R.id.videoView);
//        player = new ExoPlayer.Builder(this).build();
//        playerView.setPlayer(player);
//        MediaItem mediaItem = MediaItem.fromUri("https://iframe.mediadelivery.net/play/196860/a8e6009c-dee1-4fd5-a9a6-44d8eb9fa137");
//        player.setMediaItem(mediaItem);
//        player.prepare();
//        player.setPlayWhenReady(true);

        WebView view = findViewById(R.id.webview);
        String videoLink = DataInitializer.allVideos()[lessonIndex-1].get(videoIndex-1).url;
        view.loadData(videoLink, "text/html","utf-8");
        view.getSettings().setJavaScriptEnabled(true);
        view.setWebChromeClient(new WebChromeClient());
//
//        view.getSettings().setLoadsImagesAutomatically(true);
//        view.getSettings().setAllowFileAccess(true);
//        view.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        view.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        view.getSettings().setPluginState(WebSettings.PluginState.ON);
//        view.getSettings().setMediaPlaybackRequiresUserGesture(false);
//        view.getSettings().setDomStorageEnabled(true);
////        view.getSettings().setAppCacheMaxSize(1024 * 8);
//        view.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
//        view.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
//        view.requestFocus(View.FOCUS_DOWN);
////        view.getSettings().setAppCacheEnabled(true);


        int finalLessonIndex = lessonIndex;
        int finalVideoIndex = videoIndex;
//        String finalTitle = title;
//        String finalLessonTitle = lessonTitle;
        practiceNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PracticeActivity.class);
                intent.putExtra("lessonNumber", finalLessonIndex);
                intent.putExtra("videoNumber", finalVideoIndex);
//                intent.putExtra("title", finalTitle);
//                intent.putExtra("lessonTitle", finalLessonTitle);
                v.getContext().startActivity(intent);
            }
        });
        watchNextVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (watchNextVideo.getText().equals("Watch next video")){
                    Intent intent = new Intent(v.getContext(), VideoActivity.class);
                    intent.putExtra("lessonNumber", finalLessonIndex);
                    intent.putExtra("videoNumber", finalVideoIndex+1);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    intent.putExtra("title", finalTitle);
//                    intent.putExtra("lessonTitle", finalLessonTitle);
                    v.getContext().startActivity(intent);
                } else if (watchNextVideo.getText().equals("Launch next lesson")){
                    if (MainActivity.stars < DataInitializer.allLessons().get(finalLessonIndex+1-1).requiredStarsToUnlock){
                        Toast.makeText(v.getContext(), "You need to unlock next lesson!", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(v.getContext(), LessonActivity.class);
                        intent.putExtra("lesson", String.valueOf(finalLessonIndex + 1));
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    intent.putExtra("title", "" + );
                        v.getContext().startActivity(intent);
                    }
                }
            }
        });

//        player = new ExoPlayer.Builder(this).build();
//        PlayerView playerView = findViewById(R.id.videoView);
//// Bind the player to the view.
//        playerView.setPlayer(player);
//        // Build the media item.
//        MediaItem mediaItem = MediaItem.fromUri(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sample));
//// Set the media item to be played.
//        player.setMediaItem(mediaItem);
//// Prepare the player.
//        player.prepare();
//// Start the playback.
//        player.play();
//        videoView = findViewById(R.id.videoView);
//
////        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample2);
//        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sample2));
//
//        MediaController mediaController = new MediaController(this);
//        videoView.setMediaController(mediaController);
//        mediaController.setAnchorView(videoView);
//
//
//        videoView.start();
//        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//                mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
//                mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
////                mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        player.setPlayWhenReady(false);
//        player.release();
//        player = null;
//        player.release();
    }
}