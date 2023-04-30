public abstract class Person implements Comparable<Person> {
	private String name;
	
	public Person() {
	}
	
	public Person(String name) {
	  this.setName(name);	
	}
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	 @Override
	 public int compareTo(Person person) {
		 return this.getName().compareTo(person.getName());
	 }
	    
	 @Override
	 public String toString() {
	    return String.format(getName());
	 }
}
