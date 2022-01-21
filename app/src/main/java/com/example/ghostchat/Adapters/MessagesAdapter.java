package com.example.ghostchat.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghostchat.Models.Message;
import com.example.ghostchat.R;
import com.example.ghostchat.databinding.ItemReceiveBinding;
import com.example.ghostchat.databinding.ItemSentBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MessagesAdapter extends RecyclerView.Adapter{

    Context context;
    ArrayList<Message>  messages;
    final int ITEM_SENT =1;
    final int ITEM_RECEIVED =2;

    public MessagesAdapter(Context context,ArrayList<Message>messages){
        this.context=context;
        this.messages=messages;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==ITEM_SENT){
            View view = LayoutInflater.from(context).inflate(R.layout.item_sent,parent,false);
            return  new SentViewHolder(view);
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_receive,parent,false);
            return  new ReceiveViewHolder(view);
        }

    }

    @Override
    public int getItemViewType(int position) {
        Message  message =messages.get(position);
        if(FirebaseAuth.getInstance().getUid().equals(message.getSenderId())){
            return ITEM_SENT;
        }
        else {
            return ITEM_RECEIVED;
        }
      //  return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message  message = messages.get(position);
        if (holder.getClass()==SentViewHolder.class){
            SentViewHolder viewHolder =(SentViewHolder)holder;
            viewHolder.binding.messege.setText(message.getMessage());
        }else {
            ReceiveViewHolder viewHolder =(ReceiveViewHolder) holder;
            viewHolder.binding.messege.setText(message.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class  SentViewHolder extends RecyclerView.ViewHolder {
        ItemSentBinding binding;

        public SentViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ItemSentBinding.bind((itemView));
        }
    }

    public class ReceiveViewHolder extends RecyclerView.ViewHolder{
        ItemReceiveBinding binding;

        public ReceiveViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ItemReceiveBinding.bind(itemView);
        }
    }

}
