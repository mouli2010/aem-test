package com.aem.training.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import com.aem.training.models.Navigation.MultifieldBean;

@Model(adaptables=Resource.class)
public class StudentList {
	@Inject
	@Optional
	private  Resource studentItems;
	private  List<StudentMultifieldBean> studentListItems;
	public List<StudentMultifieldBean> getStudentListItems() {
		return studentListItems;
	}


	@PostConstruct
	private void init() {
		if(null!= studentItems && studentItems.hasChildren() )
		{
			studentListItems =new ArrayList<>();
			Iterator<Resource>listChildren=studentItems.listChildren();
			while(listChildren.hasNext())
			{
				StudentMultifieldBean bean=new StudentMultifieldBean();
				Resource resource=listChildren.next();
				ValueMap valueMap= resource.getValueMap();
			bean.setName(valueMap.get("sname", String.class));
			bean.setDepartment(valueMap.get("sdepart", String.class));
			bean.setDoj(valueMap.get("eventStartDate", String.class));
			studentListItems.add(bean);
			
			}
			
			  
		}
		
		Collections.sort(studentListItems,new NameComparator());
		
		
		
	}
	
    

	public  class NameComparator implements Comparator<StudentMultifieldBean>{  
		public int compare(StudentMultifieldBean s1,StudentMultifieldBean s2){  
		return s1.name.compareTo(s2.name);  
		}  
		} 
	

	private String fname;
	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public String getLname() {
		return lname;
	}


	public void setLname(String lname) {
		this.lname = lname;
	}




	private String lname;
       
	

	
	public class StudentMultifieldBean{
		
		private String name;
		private String department;
		private String doj;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDepartment() {
			return department;
		}
		public void setDepartment(String department) {
			this.department = department;
		}
		public String getDoj() {
			return doj;
		}
		public void setDoj(String doj) {
			this.doj = doj;
		}
		
		
	}
}
