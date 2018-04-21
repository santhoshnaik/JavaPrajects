
public class MyThread1 extends Thread {
Table t;
public MyThread1(Table t)
{
	this.t=t;
}
	public void run(){
		//System.out.println("inside run");
		t.printTable(5);
		
	}
}
