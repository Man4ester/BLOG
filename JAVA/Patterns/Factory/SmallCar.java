package patterns.factory;

public class SmallCar extends Car{

	public SmallCar() {
		super(CarType.SMALL);
		start();
	}
	
	private void start(){
		System.out.println("start work small");
	}

}
