package com.aem.training.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
@Model(adaptables = Resource.class)
public class Mapoflistiterator {
	private Map<Integer,List<String>> map;

	private List<String> book;


	public Map<Integer, List<String>> getMap() {
	  return map;
	 }

	@PostConstruct
	    public void constructMap() {
	    map =  new HashMap<Integer, List<String>>();

	    book = new ArrayList();
	    book.add("b1");
	    book.add("b2");

	    map.put(1,book);
	       
	    }

	/* public class Book {  
		int id;  
		public int getId() {
			return id;
		}
		public String getName() {
			return name;
		}
		public String getAuthor() {
			return author;
		}
		public String getPublisher() {
			return publisher;
		}
		public int getQuantity() {
			return quantity;
		}
		String name,author,publisher;  
		int quantity;  
		public Book(int id, String name, String author, String publisher, int quantity) {  
		    this.id = id;  
		    this.name = name;  
		    this.author = author;  
		    this.publisher = publisher;  
		    this.quantity = quantity;  
		}  
		}   
		    //Creating Books  
		    Book b1=new Book(101,"Let us C","Yashwant Kanetkar","BPB",8);  
		    Book b2=new Book(102,"Data Communications & Networking","Forouzan","Mc Graw Hill",4);  
		    Book b3=new Book(103,"Operating System","Galvin","Wiley",6);  
		   */
		}  


