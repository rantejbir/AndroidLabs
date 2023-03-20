package com.cst2335.sing1729;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
    ChatMessageDAO mDAO;
    ActivityChatRoomBinding binding;
    ArrayList<ChatMessage> messages = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        messages = chatModel.messages.getValue();
        MessageDatabase db = MessageDatabase.getInstance(this);
        mDAO = db.chatMessageDAO();

        if(messages == null)
        {
            chatModel.messages.postValue( messages = new ArrayList<ChatMessage>());
        }
        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.sendButton.setOnClickListener(click -> {
            String message = binding.textInput.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, hh:mm:ss a");
            String currentDateandTime = sdf.format(new Date());
            ChatMessage chatMessage = new ChatMessage(message,currentDateandTime,true);
            messages.add(chatMessage);
            myAdapter.notifyItemInserted(messages.size()-1);
            binding.textInput.setText("");
        });
        binding.recieve.setOnClickListener(click -> {
            String message = binding.textInput.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE,hh:mm:ss a");
            String currentDateandTime = sdf.format(new Date());
            ChatMessage chatMessage = new ChatMessage(message, currentDateandTime, false);
            messages.add(chatMessage);
            myAdapter.notifyItemInserted(messages.size()-1);
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
                boolean sent_Button= chatMessage.isSentButton();
                if(sent_Button){
                    return 0;
                }
                else{
                    return 1;
                }
            }

        });
        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void loadMessages() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                messages.addAll( mDAO.getAllMessages() );
            }
        }).start();
    }


class MyRowHolder extends RecyclerView.ViewHolder {
    TextView messageText;
    TextView timeText;

    public MyRowHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(clk -> {

            int AdapterPosition = getAbsoluteAdapterPosition();

            AlertDialog.Builder builder = new AlertDialog.Builder(chatRoom.this);
            //to set the message on the alert window, we use the following code
            builder.setMessage("Do you want to delete the message: " + messageText.getText())
                    .setTitle("Question:")
                    .setNegativeButton("No", (dialog, cl) -> {
                    })

                    .setPositiveButton("Yes", (dialog, cl) -> {

                        //this will delete the message row, and update the list
                        messages.remove(AdapterPosition);
                        myAdapter.notifyItemRemoved(AdapterPosition);
                    })
                    .create().show();

        });

        messageText = itemView.findViewById(R.id.messagetext);
        timeText = itemView.findViewById(R.id.timeText);

    }
}
}
