public class ReviewNumbers extends Review {
	static int maxPoints = 10;

	public ReviewNumbers(int points, String comment) {
		super(points, comment);
	}

	public ReviewNumbers(int points) {
		super(points);
		this.setComment("");
	}
	
	public  int getMaxPoints() {
		return maxPoints;
	}

	public String displayPoints() {
		return "" + String.valueOf(getPoints());
	}
}
