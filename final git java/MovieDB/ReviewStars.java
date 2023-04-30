import java.util.Arrays;

public class ReviewStars extends Review {
	static int maxPoints = 5;
   
	public ReviewStars(int points, String comment) {
		super(points, comment);
	}

	public ReviewStars(int points) {
		super(points);
		this.setComment("");
	}

	public int getMaxPoints() {
		return maxPoints;
	}

	public String displayPoints() {
		char arr[] = new char[getPoints()];
        Arrays.fill(arr, '*');
        return "" + String.valueOf(arr);
	}
}


