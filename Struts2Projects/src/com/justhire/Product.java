package com.justhire;

public class Product {
	
		private int id;  
		private String name;  
		private float price;  
		public void setId(int id) {  
		    this.id = id;  
		}
		public int getId() {  
		    return id;  
		}  
		public void setName(String name) {  
		    this.name = name;  
		}  
		public String getName() {  
		    return name;  
		}  
		public void setPrice(float price) {  
		    this.price = price;  
		}  
		public float getPrice() {  
		    return price;  
		}  
		  
		  
		public String execute(){  
			System.out.println("inside execute");
		    return "success"; 
		    
		}  
		

}
