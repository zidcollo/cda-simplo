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
//import test.cda.dao.PostDao;
import test.cda.dao.TopicDao;
import test.cda.model.Topic;

@Path("topicService")
public class TopicService {

	
	//private PostDao postDao;
	private TopicDao topicDao;
	private Connection connection;
	
	
	public TopicService( ) {
		   connection= Main.getConnection();
		   topicDao = new TopicDao(connection);
	}	
	
		@GET
		@Path("/topics")
		@Produces(MediaType.APPLICATION_JSON)
		public List<Topic> listTopics(){
			
			return topicDao.getAllTopics();
			
		}
		
		@POST
		@Path("/createTopic")
		@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
		public Topic saveTopic(Topic topic) {
			topicDao.createTopic(topic);
			return topic;
		}
		
		@DELETE
		@Path("/deleteTopic/{id}")
		@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
		public void deleteTopic(@QueryParam("id") int id) {
			
			topicDao.deleteTopic(id);;
		}

		@PUT
		@Path("/updateTopic/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Topic update( Topic topic , @PathParam(value="id") int id) {
			
			return  topicDao.updateTopic(topic);
	
		}	
}
