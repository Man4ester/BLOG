package patterns.factory;

public class LuxuryCar extends Car {

	public LuxuryCar() {
		super(CarType.LUXURY);
		start();
	}
	
	private void start(){
		System.out.println("start work luxury");
	}
	
	

}
