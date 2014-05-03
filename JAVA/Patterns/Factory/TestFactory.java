package patterns.factory;

public class TestFactory {
	
	public static void main(String[] args){
		System.out.println(CarFactory.build(CarType.SMALL));
		System.out.println(CarFactory.build(CarType.SEDAN));
		System.out.println(CarFactory.build(CarType.LUXURY));
		
	}

}
