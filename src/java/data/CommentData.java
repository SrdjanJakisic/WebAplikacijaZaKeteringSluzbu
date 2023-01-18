package data;

import beans.Comment;
import java.sql.*;
import java.util.ArrayList;

public class CommentData
{

    public void addComment(Connection conn, String commentContent, String userId) throws SQLException
    {
        String query = "INSERT INTO comments (commentContent, userId) VALUES(?, ?)";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1, commentContent);
        pstm.setInt(2, Integer.parseInt(userId));

        pstm.executeUpdate();
        pstm.close();
    }

    public ArrayList<Comment> getAllComments(Connection conn) throws SQLException
    {
        ArrayList<Comment> comments = new ArrayList<>();

        String query = "SELECT * FROM comments JOIN user ON comments.userId = user.id ORDER BY commentDate DESC";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next())
        {
            Comment comment = new Comment();
            comment.setCommentId(rs.getInt("commentId"));
            comment.setCommentContent(rs.getString("commentContent"));
            comment.setUsername(rs.getString("username"));
            comment.setUserId(rs.getInt("userId"));
            comments.add(comment);
        }

        stmt.close();
        rs.close();

        return comments;
    }
}
