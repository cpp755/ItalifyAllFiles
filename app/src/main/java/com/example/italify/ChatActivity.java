package com.example.italify;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MessageAdapter messageAdapter;
    private List<Message> messageList;
    private EditText messageEditText;
    ImageButton sendButton;
    public int counter =1 ;
    TextView[] chatsTV = new TextView[10];
    String[] chats = new String[10];
    Toolbar toolbar;

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        messageList = new ArrayList<>();
        messageList.add(new Message("How can I help you?", Message.SENT_BY_BOT));

        {
            toolbar = findViewById(R.id.toolbar);
            toolbar.findViewById(R.id.starIcon).setVisibility(View.GONE);
            toolbar.findViewById(R.id.starCount).setVisibility(View.GONE);
            toolbar.findViewById(R.id.homeIcon).setVisibility(View.VISIBLE);
            ImageView backButton = findViewById(R.id.backIcon);
            ImageView homeButton = findViewById(R.id.homeIcon);
            TextView titleTextView = toolbar.findViewById(R.id.title);
            titleTextView.setText("Chat with AI");
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
        recyclerView = findViewById(R.id.recycler_view_chat);
        sendButton = findViewById(R.id.send_btn);
        messageEditText = findViewById(R.id.message_edit_text);

        messageAdapter = new MessageAdapter(messageList);
        recyclerView.setAdapter(messageAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setStackFromEnd(true);
        recyclerView.setLayoutManager(llm);


        sendButton.setOnClickListener((v) ->{
            String question = messageEditText.getText().toString().trim();
            if (question.length() < 10){
                Toast.makeText(v.getContext(), "Your question should be at least 10 character long.", Toast.LENGTH_SHORT).show();
            } else {
                addToChat(question, Message.SENT_BY_ME);
                messageEditText.setText("");
                callAPI(question);
            }
        });


//        recyclerView = findViewById(R.id.recyclerView);
        /*
        messageInputEditText = findViewById(R.id.messageInputEditText);

         */
//
//        messages = new ArrayList<>();
//        messageAdapter = new MessageAdapter(messages);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(messageAdapter);
//
//        addRandomMessage("Hello! How can I help you today?");
        /*
        chats[0] = "Hello! How can I help you today?";
        chats[1] = "user 1 adifhidshf ihsadihf ihadsifh iahsdif hiadhfi hi hiadfni iadf \n\n .";
        chats[2] = "ai 2 \n\n\nadihfadf ihadifh ihadfih iadfhij kc, m";
        chats[3] = "user 2  adfadf adf";
        chats[4] = "ai 3a a adf dfdsdf ";
        chats[5] = "user 3sdf sdf sdf ";
        chats[6] = "ai 4ad dsdf sdf ";
        chats[7] = "user 4 sdf sdf sdf ";

        chatsTV[0] = findViewById(R.id.chatAI11);
        chatsTV[2] = findViewById(R.id.chatAI21);
        chatsTV[4] = findViewById(R.id.chatAI31);
        chatsTV[6] = findViewById(R.id.chatAI41);

        chatsTV[1] = findViewById(R.id.ChatUser11);
        chatsTV[3] = findViewById(R.id.ChatUser21);
        chatsTV[5] = findViewById(R.id.ChatUser31);
        chatsTV[7] = findViewById(R.id.ChatUser41);
        chatsTV[0].setText(chats[0]);
        */

    }
    void addToChat(String message,String sentBy){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageList.add(new Message(message,sentBy));
                messageAdapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(messageAdapter.getItemCount());
            }
        });
    }
    void addResponse(String response){
        messageList.remove(messageList.size()-1);
        addToChat(response,Message.SENT_BY_BOT);
    }
    void callAPI(String question){
        //okhttp
        messageList.add(new Message("Typing... ",Message.SENT_BY_BOT));

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("model","gpt-3.5-turbo-instruct");
            jsonBody.put("prompt",question);
            jsonBody.put("max_tokens",700);
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
                addResponse("Failed to load response due to "+e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    JSONObject  jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonObject.getJSONArray("choices");
                        String result = jsonArray.getJSONObject(0).getString("text");
                        addResponse(result.trim());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }else{
                    addResponse("Failed to load response due to "+response.body().toString());
                }
            }
        });





    }



    /*
    void callAPI(String question){
        //okhttp

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("model","gpt-3.5-turbo-instruct");
            jsonBody.put("prompt",question);
            jsonBody.put("max_tokens",400);
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
//                addResponse("Failed to load response due to "+e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    JSONObject  jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonObject.getJSONArray("choices");
                        String result = jsonArray.getJSONObject(0).getString("text");
//                        addResponse(result.trim());
//                        Toast.makeText(getBaseContext(), result.trim(), Toast.LENGTH_LONG).show();
                        asghar(result.trim());
//                        Log.i("asghar", result.trim());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else{
//                    addResponse("Failed to load response due to "+response.body().toString());
                }
            }
        });

    }




    public void asghar (String response){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //TODO doing is_typing here


                ViewGroup parentView = (ViewGroup) chatsTV[counter].getParent();
                parentView.setVisibility(View.VISIBLE);
                chats[counter] = response;
                chatsTV[counter].setText(chats[counter]);
                counter++;
            }
        });

    }



    public void sendMessage(View view) {
        String userMessage = messageInputEditText.getText().toString().trim();

        ViewGroup parentView = (ViewGroup) chatsTV[counter].getParent();
        parentView.setVisibility(View.VISIBLE);
        chats[counter] = userMessage;
        chatsTV[counter].setText(userMessage);
        counter++;
        callAPI(userMessage);
//        parentView = (ViewGroup) chatsTV[counter].getParent();
//        parentView.setVisibility(View.VISIBLE);
//        chatsTV[counter].setText(chats[counter]);
//        counter++;


//        if (!userMessage.isEmpty()) {
//            addMessage(new Message(userMessage, true));
//            messageInputEditText.getText().clear();
//            addRandomMessage(generateRandomResponse());
//        } else {
//            Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show();
//        }
    }


    */
//
//    private void addMessage(Message message) {
//        messages.add(message);
//        messageAdapter.notifyDataSetChanged();
//        recyclerView.scrollToPosition(messages.size() - 1);
//    }
//
//    private void addRandomMessage(String text) {
//        addMessage(new Message(text, false));
//    }
//
//    private String generateRandomResponse() {
//        String[] responses = {
//                "I'm not sure, could you provide more details?",
//                "That's interesting! Tell me more.",
//                "I'll look into that for you.",
//                "Is there anything else you'd like to ask?"
//        };
//
//        Random random = new Random();
//        return responses[random.nextInt(responses.length)];
//    }
}