package patterns.factory;

public class CarFactory {

	public static Car build(CarType model) {
		Car car = null;
		switch (model) {
		case SMALL:
			car = new SmallCar();
			break;
		case SEDAN:
			car = new SedanCar();
			break;
		case LUXURY:
			car = new LuxuryCar();
			break;

		default:
			break;
		}
		return car;

	}

}
