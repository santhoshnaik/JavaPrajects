
public class Earth {
String name;
	public Earth(String s){
		this.name=s;
	}
	private static Earth obj;
	
	public static Earth getInstance()
	{
		if(obj==null)
		{
			obj=new Earth("Santhosh");
			
		}
		System.out.println("value of obj inside getInst "+ obj.name);
		return obj;
	}
}
