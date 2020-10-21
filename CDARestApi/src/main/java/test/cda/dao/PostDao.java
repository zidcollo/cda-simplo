package test.cda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import test.cda.model.Post;



public class PostDao  {
	private Connection con;
	public PostDao(Connection con) {
		this.con = con;
	}

	public void createPost(Post post) {
		
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO post(id_topic, author, content, date) VALUES (?, ?, ?, ?)");
			ps.setInt(1, post.getTopic().getId());
			ps.setString(2, post.getAuthor());
		    ps.setString(3, post.getContent());
		    ps.setDate(4, new java.sql.Date(post.getDate().getMillis()));
		    ps.executeUpdate();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Post> getPosts() {
		 
		List<Post> posts = new ArrayList<Post>();
		TopicDao topicDao = new TopicDao(con);
		try {
			PreparedStatement ps= con.prepareStatement("SELECT * FROM post");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				posts.add(new Post(  rs.getInt("id"), 
						           topicDao.getTopic(rs.getInt("id_topic")),
						             rs.getString("content"),
						             rs.getString("author"), 
						             new DateTime(rs.getDate("date"))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return posts;
	}

	public void deletePost(int id) {
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM post WHERE id =?");
			ps.setInt(1, id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Post  updatePost(Post post, int id) {
		
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE post SET  content=?, author=?, date=?  where post.id =?");
			ps.setInt(1, post.getTopic().getId());
			ps.setString(1, post.getContent());
			ps.setString(2, post.getAuthor());
			ps.setDate(3, new java.sql.Date(post.getDate().getMillis()));
			
			ps.setInt(4, id);
	
		    ps.executeUpdate();
		    
		    System.out.println("resultat update :"+ps.executeUpdate());
		    
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return post;
	}

	
	
	public List<Post> findPostByTopic(int id) {
		List<Post> posts = new ArrayList<Post>();
		TopicDao topicDao = new TopicDao(con);
		
		try {
			PreparedStatement ps= con.prepareStatement("SELECT * FROM post p, topic t WHERE p.id_topic = t.id AND t.id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				posts.add(new Post(  rs.getInt("id"), 
						           topicDao.getTopic(rs.getInt("id_topic")),
						             				 rs.getString("content"),
						             				 rs.getString("author"), 
						             				 new DateTime(rs.getDate("date"))) );
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return posts;

}

}
