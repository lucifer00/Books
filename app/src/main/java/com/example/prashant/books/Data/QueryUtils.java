package com.example.prashant.books.Data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Prashant on 11/10/2017.
 */

public class QueryUtils
{
    private QueryUtils(){}
    public static ArrayList<Books> extractBooks(String googlebooks_url)
    {
        ArrayList<Books> books=new ArrayList<>();
        URL url=createUrl(googlebooks_url);
        String json_response=null;
        try
        {
            json_response=makeHttpREquest(url);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try {
            JSONObject jsonObject=new JSONObject(json_response);
            JSONArray basejsonarray=jsonObject.getJSONArray("items");
            for(int i=0;i<basejsonarray.length();i++)
            {
                String authors="";
                JSONObject currentBook=basejsonarray.getJSONObject(i);
                JSONObject volumeinfo=currentBook.getJSONObject("volumeInfo");
                String title=volumeinfo.getString("title");
                String self_link=currentBook.getString("selfLink");
                JSONArray authors_array=volumeinfo.getJSONArray("authors");
                for(int j=0;j<authors_array.length();j++)
                {
                    authors+=authors_array.getString(j)+"\n";
                }
                String publisher=volumeinfo.getString("publisher");
                String description=volumeinfo.getString("description");
                JSONObject image_link_info=volumeinfo.getJSONObject("imageLinks");
                String image_link_small=image_link_info.getString("smallThumbnail");
                String image_link_large=image_link_info.getString("thumbnail");
                books.add(new Books(title,image_link_small,image_link_large,authors,publisher,description,self_link));
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return books;
    }
    private static URL createUrl(String usgs_url)
    {
        URL url=null;
        try {
            url=new URL(usgs_url);
        } catch (MalformedURLException e) {
            Log.e("LOG_ERROR","Error with creating url",e);
        }
        return url;
    }
    private static String makeHttpREquest(URL url)throws IOException
    {
        String jsonresponse="";
        if(url==null)return jsonresponse;
        HttpURLConnection httpURLConnection=null;
        InputStream inputStream=null;
        try
        {
            httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode()==200)
            {
                inputStream=httpURLConnection.getInputStream();
                jsonresponse=readfromstream(inputStream);
            }
            else
            {Log.e("Http error","Error response code: "+httpURLConnection.getResponseCode());}
        }
        catch (IOException e)
        {
            Log.e("JSON problem","Problem retrieving JSON");
        }
        finally {
            if(httpURLConnection!=null)httpURLConnection.disconnect();
            if(inputStream!=null)inputStream.close();
        }
        return jsonresponse;
    }
    private static String readfromstream(InputStream inputStream)throws IOException
    {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
