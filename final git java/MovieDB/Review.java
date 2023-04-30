
public abstract class Review implements Comparable<Review>{
	private int points;
	private String comment;
		
	public Review(int points, String comment) {
		this.setPoints(points);
		this.setComment(comment);
	}
	
	public Review(int points) {
		this.setPoints(points);
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		if (checkPoints(points)) {
			this.points = points;  
		} else {
			System.out.println("Max points to enter is: " + this.getMaxPoints());
		}
	}

	public boolean checkPoints(int points) {
		boolean result = false;
		if ( points > 0 && points <= getMaxPoints()) {
			result =  true;			
		} 
		return result;
	};
	
	abstract public String displayPoints();
	abstract public int getMaxPoints();
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
   
    public String getReview() {
    	return "\nPoints: " + displayPoints() + "; Comment: \"" + getComment() + "\""; 
    };
    
    @Override
    public int compareTo(Review review) {
        return review.points - this.points;
    }
    
    @Override
    public String toString() {
        return String.format(getReview());
    }    
}
