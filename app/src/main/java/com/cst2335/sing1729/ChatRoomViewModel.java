package com.cst2335.sing1729;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ChatRoomViewModel extends ViewModel {
    public MutableLiveData<List<ChatMessage>> messages = new MutableLiveData<>();
}