package com.cst2335.sing1729;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cst2335.sing1729.databinding.ActivityChatRoomBinding;
import com.cst2335.sing1729.databinding.ReceiveMessageBinding;
import com.cst2335.sing1729.databinding.SentMessageBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class chatRoom extends AppCompatActivity {
    private RecyclerView.Adapter myAdapter;
    ChatRoomViewModel chatModel ;
    ActivityChatRoomBinding binding;
    ArrayList<ChatMessage> messages = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chat_room);
//        setContentView(binding.getRoot());

        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        messages = chatModel.messages.getValue();
        if(messages == null)
        {
            chatModel.messages.postValue( messages = new ArrayList<ChatMessage>());
        }


        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.sendButton.setOnClickListener(click -> {
            String message = binding.textInput.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh:mm:ss a");
            String currentDateandTime = sdf.format(new Date());
            boolean sendButton=true;
            ChatMessage chatMessage = new ChatMessage(message, currentDateandTime, sendButton);
            messages.add(chatMessage);
            myAdapter.notifyItemInserted(messages.size()-1);
            //clear the previous text:
            binding.textInput.setText("");
        });
        binding.recieve.setOnClickListener(click -> {
            String message = binding.textInput.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh:mm:ss a");
            String currentDateandTime = sdf.format(new Date());
            boolean recieve=false;
            ChatMessage chatMessage = new ChatMessage(message, currentDateandTime, recieve);
            messages.add(chatMessage);
            myAdapter.notifyItemInserted(messages.size()-1);
            //clear the previous text:
            binding.textInput.setText("");
        });

        binding.recycleView.setAdapter (myAdapter = new RecyclerView. Adapter<MyRowHolder> () {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                if (viewType == 0) {
                    SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater());
                    return new MyRowHolder(binding.getRoot());
                } else {
                    ReceiveMessageBinding binding = ReceiveMessageBinding.inflate(getLayoutInflater());
                    return new MyRowHolder(binding.getRoot());
                }
            }


            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {

                Object message = messages.get(position);
                if (message instanceof ChatMessage) {
                    ChatMessage chatMessage = (ChatMessage) message;
                    holder.messageText.setText(chatMessage.getMessage());
                    holder.timeText.setText(chatMessage.getTimeSent());
                } else if (message instanceof String) {
                    holder.messageText.setText((String) message);
                    holder.timeText.setText("");
                }
            }


            @Override
            public int getItemCount() {
                return messages.size();
            }
            @Override
            public int getItemViewType(int position){
                Object message = messages.get(position);
                ChatMessage chatMessage = (ChatMessage) message;
                boolean s=chatMessage.isSentButton;
                if(s){
                    return 0;
                }
                else{
                    return 1;
                }
            }

        });
        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));
    }
}
class MyRowHolder extends RecyclerView.ViewHolder {
    TextView messageText;
    TextView timeText;

    public MyRowHolder(@NonNull View itemView) {
        super(itemView);
        messageText = itemView.findViewById(R.id.messagetext);
        timeText = itemView.findViewById(R.id.timeText);

    }
}
