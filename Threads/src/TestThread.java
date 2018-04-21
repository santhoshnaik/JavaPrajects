
public class TestThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     Table tab=new Table();
		MyThread1 t1=new MyThread1(tab);
		Thread2 t2=new Thread2(tab);
		t1.start();
		t2.start();
	}

}
