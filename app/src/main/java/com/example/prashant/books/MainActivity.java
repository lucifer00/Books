package com.example.prashant.books;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.prashant.books.Data.Books;
import com.example.prashant.books.Data.BooksLoader;
import com.example.prashant.books.Data.Books_customadapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Books>> {
    private int BOOKS_LOADER_ID=1;
    private String books_url="";
    private String url;
    Books_customadapter booksCustomadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SearchView searchView=(SearchView)findViewById(R.id.searchView1);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                for(int i=0;i<s.length();i++)
                {
                    if(Character.isWhitespace(s.charAt(i)))books_url+="+";
                    else
                        books_url+=s.charAt(i);
                }
                s=books_url;
                books_url="";
                url="https://www.googleapis.com/books/v1/volumes?q="+s+"&maxResults=10";
                System.out.println(url);
                LoaderManager loaderManager=getLoaderManager();
                loaderManager.initLoader(BOOKS_LOADER_ID++,null,MainActivity.this);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return true;
            }
        });
    }

    @Override
    public Loader<List<Books>> onCreateLoader(int i, Bundle bundle) {
        System.out.println(url);
        return new BooksLoader(this,url);
    }

    @Override
    public void onLoadFinished(Loader<List<Books>> loader, List<Books> books) {
        if(booksCustomadapter!=null)booksCustomadapter.clear();
        if(books!=null&&!books.isEmpty())
        {
            booksCustomadapter=new Books_customadapter(MainActivity.this,books);
            ListView listView=(ListView)findViewById(R.id.books_list_view);
            listView.setAdapter(booksCustomadapter);
        }
        url="";
    }

    @Override
    public void onLoaderReset(Loader<List<Books>> loader) {
            booksCustomadapter.clear();
    }
}
