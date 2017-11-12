package com.example.prashant.books.Data;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prashant.books.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Prashant on 11/10/2017.
 */

public class Books_customadapter extends ArrayAdapter<Books>{
    private Drawable d;
    public Books_customadapter(@NonNull Activity context, List<Books> booksadapter) {
        super(context, 0,booksadapter);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.custom_list_view,parent,false);
        }
        Books data=getItem(position);
        ImageView iv=(ImageView)listItemView.findViewById(R.id.book_image_view);
        TextView author=(TextView)listItemView.findViewById(R.id.author_text_view);
        TextView publised_by=(TextView)listItemView.findViewById(R.id.publisger_text_view);
        TextView title=(TextView)listItemView.findViewById(R.id.title_text_view);
        title.setText(data.getBook_name());
        author.setText(data.getAuthor_name());
        String url=data.getImg_url_small();
        Picasso.with(this.getContext()).load(url).into(iv);
        publised_by.setText(data.getPublished_by());
        return listItemView;
    }
}
