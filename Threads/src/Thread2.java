
public class Thread2 extends Thread{
	Table t;
	public Thread2(Table t)
	{
		this.t=t;
	}
	public void run(){
		//System.out.println("inside run");
		
	try{
		Thread.sleep(10000);
	}
	catch(Exception e)
	{
		System.out.println("excep= "+e.getMessage());
	}
	
		t.printTable(200);
	}
	
}
