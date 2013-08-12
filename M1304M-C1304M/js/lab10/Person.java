package lab10;
public class Person implements Comparable<Person> {
    private int person_id;
    private String name;
    
    
    /**
     * Compare current person with specified person
     * return zero if person_id for both person is same 
     * return negative if current person_id is less than specified one
     * return positive if specified person_id is greater than specified one
     */
    @Override 
    public int compareTo(Person o) {
        Person p = (Person) o; 
        return this.getPerson_id() - o.getPerson_id() ;
    }


    public int getPerson_id() {
      return person_id;
    }


    public void setPerson_id(int person_id) {
      this.person_id = person_id;
    }


    public String getName() {
      return name;
    }


    public void setName(String name) {
      this.name = name;
    }

}

