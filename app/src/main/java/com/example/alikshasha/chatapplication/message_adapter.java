package com.example.alikshasha.chatapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class message_adapter extends BaseAdapter {
    Context context ;
    public static String last_message_statues = "send" ;

    public message_adapter( Context context) {
        this.context = context;
    }
    @Override
    public int getCount() {
        return Message.list_message.size();
    }
    @Override
    public Object getItem(int position) {
        return  Message.list_message.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = null;
        if( Message.list_message.get(position).getBody().contains("fileuploadingtoyourfiendthismessagenotify"))
        {
            view = layoutInflater.inflate(R.layout.file_uploaded_item, null);
            TextView textView= (TextView) view.findViewById(R.id.file_upload);
            if( !Message.list_message.get(position).getSender().equals(context.getSharedPreferences("logged", context.MODE_PRIVATE).getString("phone", "-1")))
            {
                textView.setText(R.string.friend_uploading);
            }else if( Message.list_message.get(position).getSender().equalsIgnoreCase(context.getSharedPreferences("logged", context.MODE_PRIVATE).getString("phone", "-1")))
            {
                textView.setText(R.string.uploading);
            }
        }else if(Message.list_message.get(position).getBody().contains("fileuploadedtoyourfiendthismessagenotify"))
        {
            view = layoutInflater.inflate(R.layout.file_uploaded_item, null);
            TextView textView= (TextView) view.findViewById(R.id.file_upload);
            textView.setEnabled(true);
                textView.setText(R.string.download);
        }
        else{
             if (Message.list_message.get(position).getSender().
                     equals(context.getSharedPreferences("logged", context.MODE_PRIVATE).getString("phone", "-1")))
                 view = layoutInflater.inflate(R.layout.message_send, null);
             else
                 view = layoutInflater.inflate(R.layout.message_recieve, null);
                 Log.d("message is ",Message.list_message.get(position).getBody()) ;
                 TextView message_body = (TextView) view.findViewById(R.id.message_body);
                 message_body.setText(Message.list_message.get(position).getBody());
                 ImageView imageView = (ImageView) view.findViewById(R.id.status);

        if (position == Message.list_message.size() - 1 && Message.list_message.get(position).getSender().
                equalsIgnoreCase(context.getSharedPreferences("logged", context.MODE_PRIVATE).getString("phone", "-1")))
        { if (last_message_statues.equalsIgnoreCase("send"))
                imageView.setImageResource(R.drawable.send);
            else if (last_message_statues.equalsIgnoreCase("rec"))
                imageView.setImageResource(R.drawable.rec);
            else if (last_message_statues.equalsIgnoreCase("seen"))
                imageView.setImageResource(R.drawable.seen);
            }
        }
        return view;
    }
}