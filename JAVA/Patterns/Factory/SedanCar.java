package patterns.factory;

public class SedanCar extends Car{

	public SedanCar() {
		super(CarType.SEDAN);
		start();
	}
	
	private void start(){
		System.out.println("Start work sedan");
	}

}
