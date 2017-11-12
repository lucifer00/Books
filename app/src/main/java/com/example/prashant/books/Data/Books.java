package com.example.prashant.books.Data;

/**
 * Created by Prashant on 11/10/2017.
 */

public class Books {
    private String img_url_small;
    private String img_url_thumbnail;
    private String author_name;
    private String published_by;
    private String description;
    private String book_name;
    private String self_link;
    public Books(String bookname,String imgurlsmall,String imgurlthumbnail,String auth_name,String pb_by,String desc,String slf)
    {
        img_url_small=imgurlsmall;
        img_url_thumbnail=imgurlthumbnail;
        book_name=bookname;
        author_name=auth_name;
        published_by=pb_by;
        description=desc;
        self_link=slf;
    }
    public String getAuthor_name() {
        return author_name;
    }

    public String getImg_url_small() {
        return img_url_small;
    }

    public String getImg_url_thumbnail() {
        return img_url_thumbnail;
    }

    public String getPublished_by() {
        return published_by;
    }

    public String getDescription() {
        return description;
    }

    public String getBook_name() {
        return book_name;
    }

    public String getSelf_link() {
        return self_link;
    }
}
