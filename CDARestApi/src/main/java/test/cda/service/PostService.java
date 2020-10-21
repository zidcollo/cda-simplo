package test.cda.service;

import java.sql.Connection;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import tes.cda.main.Main;
import test.cda.dao.PostDao;
import test.cda.model.Post;

@Path("postService")
public class PostService {

	private PostDao postDao;
	private Connection connection;
	
	
	public PostService( ) {
		   connection= Main.getConnection();
		   postDao = new PostDao(connection);
	}

	//  les posts d'un topic 
	@GET
	@Path("/postsByTopic")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Post> postsByTopic(@QueryParam("id") int id){
		return postDao.findPostByTopic(id);
	}
	
	@POST
	@Path("/createPost")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Post savePost(Post post) {
		postDao.createPost(post);
		return post;
	}
	

	@DELETE
	@Path("/deletePost/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void deletePost(@QueryParam("id") int id) {
		postDao.deletePost(id);
	}
	
	
	@PUT
	@Path("/updatePost/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Post update( Post post , @PathParam(value="id") int id) {
		return  postDao.updatePost(post, id);

	}	
}
