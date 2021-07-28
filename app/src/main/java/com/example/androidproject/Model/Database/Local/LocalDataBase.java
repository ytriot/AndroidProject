package com.example.androidproject.Model.Database.Local;

import androidx.lifecycle.LiveData;


import com.example.androidproject.Model.Database.MyModel;
import com.example.androidproject.Model.Entity.Comment;
import com.example.androidproject.Model.Entity.Message;
import com.example.androidproject.Model.Entity.Post;
import com.example.androidproject.Model.Entity.User;
import com.example.androidproject.Model.Listeners.GetCommentListener;
import com.example.androidproject.Model.Listeners.GetPostListener;

import java.util.List;

public class LocalDataBase  {//every method except LiveData must be called from a background thread

    public void insertPost(Message m, String username, GetPostListener actionComplete) {
        Post p = new Post();
        p.setPostID(MyModel.NEXT_POST_ID);
        MyModel.instance.incrementPostID();
        p.setPostUsername(username);
        p.setDeleted(false);
        p.setPostMessage(m);
        LocalDB.db.postDao().insertAllPosts(p);
        actionComplete.onComplete(p);
    }


    public void editPost(Post p) {

        LocalDB.db.postDao().insertAllPosts(p);
    }


    public void deletePost(Post p) {
        LocalDB.db.postDao().delete(p);

    }


    public void insertComment(Message m, String username, String postID, String parentCommentID, GetCommentListener actionComplete) {
        Comment c = new Comment();
        c.setCommentID(MyModel.NEXT_COMMENT_ID);
        MyModel.instance.incrementCommentID();
        c.setCommentUsername(username);
        c.setPostID(postID);
        c.setParentCommentID(parentCommentID);
        c.setDeleted(false);
        c.setCommentMessage(m);
        LocalDB.db.commentDao().insertAllComments(c);
        actionComplete.onComplete(c);
    }


    public void editComment(Comment c) {

        LocalDB.db.commentDao().insertAllComments(c);
    }


    public void deleteComment(Comment c) {
        LocalDB.db.commentDao().delete(c);

    }


    public Post getPostByID(String postID) {

        return LocalDB.db.postDao().getPost(postID);
    }


    public User getByUserName(String username) {

        return LocalDB.db.userDao().getUser(username);
    }


    public Comment getCommentByID(String commentID) {
        return LocalDB.db.commentDao().getComment(commentID);
    }


    public List<Comment> getCommentsOfPost(String postID) {

        return LocalDB.db.commentDao().getCommentsOfPost(postID);
    }


    public List<Comment> getCommentsOfComment(String parentCommentID) {
        return LocalDB.db.commentDao().getCommentsOfComment(parentCommentID);
    }


    public List<Comment> getCommentsOfUser(String username) {
        return LocalDB.db.commentDao().getCommentsOfUser(username);
    }


    public LiveData<List<Post>> getAllPosts() {

        return LocalDB.db.postDao().getAllPosts();
    }


    public LiveData<List<User>> getAllUsers() {
        return LocalDB.db.userDao().getAllUsers();
    }


    public LiveData<List<Comment>> getAllComments() {
       return LocalDB.db.commentDao().getAllComments();
    }


    public List<Post> getPostsOfUser(String username) {
        return LocalDB.db.postDao().getPostsFromUser(username);
    }
}
