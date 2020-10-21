package test.cda.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.joda.time.DateTime;

@XmlRootElement(name = "post")
@XmlAccessorType(XmlAccessType.FIELD)
public class Post{
	
	private int id;
	private String content;
	private String author;
	private DateTime date;
	private Topic topic;
	
	public Post(int id, Topic topic, String content, String author, DateTime date) {
		super();
		this.id = id;
		this.content = content;
		this.author = author;
		this.date = date;
		this.topic = topic;
	}

	public Post() {
		super();
		
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}
	
	@XmlElement
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}	
}
