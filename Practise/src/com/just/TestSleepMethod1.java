package com.just;

class TestSleepMethod1 extends Thread{  
    public void run(){  
     for(int i=1;i<5;i++){  
       try{Thread.sleep(5000);}catch(InterruptedException e){System.out.println(e);}  
       System.out.println(i);  
     }  
    }
    
    
    public static void main(String args[]){  
     TestSleepMethod1 t1=new TestSleepMethod1(); 
     System.out.println(t1.hashCode());
     TestSleepMethod1 t2=new TestSleepMethod1();  
    // TestSleepMethod1 t3=new TestSleepMethod1();
   //  System.out.println(TestSleepMethod1.currentThread()); 
     t1.run(); 
     
    // System.out.println(TestSleepMethod1.currentThread()); 

     System.out.println(t1.getName());
     t2.run();
   //  System.out.println(TestSleepMethod1.currentThread()); 
    
     System.out.println(t2.getName()); 
     
    }  
   }  
