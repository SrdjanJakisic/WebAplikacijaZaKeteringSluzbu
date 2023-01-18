package beans;

import interfaces.UserInterface;

public class Comment implements UserInterface
{
    int commentId;
    String commentContent;
    String username;
    int userId;

    public Comment(){}

    public Comment(int commentId, String commentContent, int userId)
    {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.userId = userId;
    }

    public int getCommentId()
    {
        return commentId;
    }

    public void setCommentId(int commentId)
    {
        this.commentId = commentId;
    }

    public String getCommentContent()
    {
        return commentContent;
    }

    public void setCommentContent(String commentContent)
    {
        this.commentContent = commentContent;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    @Override
    public String getUsername()
    {
        return username;
    }

    @Override
    public void setUsername(String username)
    {
        this.username = username;
    }
}
