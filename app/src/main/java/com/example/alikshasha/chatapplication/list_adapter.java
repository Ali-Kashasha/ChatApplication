package com.example.alikshasha.chatapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import data.User;
import de.hdodenhof.circleimageview.CircleImageView;


public class list_adapter extends BaseAdapter {
    Context context;
    ArrayList<User> users ;
    FirebaseStorage storage ;
    StorageReference storageRef ;
    public list_adapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
        storage = FirebaseStorage.getInstance();
        FirebaseStorage storage = FirebaseStorage.getInstance();
         storageRef = storage.getReferenceFromUrl("gs://chatapp-21cbe.appspot.com/");
    }

    @Override

    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view =layoutInflater.inflate(R.layout.message_item,null);
        final CircleImageView circleImageView = (CircleImageView) view.findViewById(R.id.profile_round_image);
        TextView profile_name = (TextView) view.findViewById(R.id.profile_name);
        profile_name.setText(MainActivity.users.get(position).getUsername());


        StorageReference islandRef = storageRef.child("profile"+ users.get(position).getPhone());
        final long ONE_MEGABYTE = 1024 * 1024;
        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                circleImageView.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                circleImageView.setImageResource(R.drawable.public_profile);
            }
        });

        return view;
    }



}