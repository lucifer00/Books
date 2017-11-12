package com.example.prashant.books.Data;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Prashant on 11/10/2017.
 */

public class BooksLoader extends AsyncTaskLoader<List<Books>>
{
    String books_url;
    public BooksLoader(Context context,String url) {
        super(context);
        books_url=url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Books> loadInBackground() {
        if(books_url==null)
        return null;
        List<Books> books=QueryUtils.extractBooks(books_url);
        return books;
    }
}

