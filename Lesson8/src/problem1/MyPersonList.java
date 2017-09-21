package problem1;
class Person {
	private String lastName;
	private String firstName;
	private int age;

	// --------------------------------------------------------------
	public Person(String last, String first, int a) {
		
		lastName = last;
		firstName = first;
		age = a;
	}

	// --------------------------------------------------------------
	public String getLast()
	
	{
		return lastName;
	}

	@Override
	public String toString() {
		return "Person [lastName=" + lastName + " FirstName=" + firstName + " Age=" + age + "]";
	}

}

public class MyPersonList {
	private final int INITIAL_LENGTH = 3;
	private Person[] PersonArray;
	private int size;

	
	public MyPersonList() {
		PersonArray = new Person[INITIAL_LENGTH];
		size = 0;
	}

	
	public void add(Person s) {
		if (size == PersonArray.length)
			resize();
		PersonArray[size++] = s;
	}

	
	public Person get(int i) {
		if (i < 0 || i >= size) {
			return null;
		}
		return PersonArray[i];
	}

	
	public boolean find(String lastName) {
		for (Person test : PersonArray) {
			if (test != null) {
				if (test.getLast().equals(lastName))
					return true;
			}
		}
		return false;
	}
 
	public void insert(Person s, int pos) {
		if (pos > size)
			return;
		if (pos >= PersonArray.length || size + 1 > PersonArray.length) {
			resize();
		}
		Person[] temp = new Person[PersonArray.length + 1];
		System.arraycopy(PersonArray, 0, temp, 0, pos);
		temp[pos] = s;
		System.arraycopy(PersonArray, pos, temp, pos + 1, PersonArray.length - pos);
		PersonArray = temp;
		++size;
	}
  
	public boolean remove(String lastName) {
		if (size == 0)
			return false;
		int index = -1;
		for (int i = 0; i < size; ++i) {
			if (PersonArray[i].getLast().equals(lastName)) {
				index = i;
				break;
			}
		}
		if (index == -1)
			return false;
		Person[] temp = new Person[PersonArray.length];
		System.arraycopy(PersonArray, 0, temp, 0, index);
		System.arraycopy(PersonArray, index + 1, temp, index, PersonArray.length - (index + 1));
		PersonArray = temp;
		--size;
		return true;
	}
 
	private void resize() {
		System.out.println("Resizing");
		int len = PersonArray.length;
		int newlen = 2 * len;
		Person[] temp = new Person[newlen];
		System.arraycopy(PersonArray, 0, temp, 0, len);
		PersonArray = temp;
	}
  
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < size - 1; ++i) {
			sb.append(PersonArray[i] + ", \n[");
		}
		sb.append(PersonArray[size - 1] + "]");
		return sb.toString();
	}
  
	public int size() {
		return size;
	}

	public static void main(String[] args) {
		MyPersonList list = new MyPersonList();
		list.add(new Person("Thomas", "Tibebu", 25));
		list.add(new Person("Solomon", "Haile", 30));
		list.add(new Person("Mekonnen", "Andualem", 35));
		list.add(new Person("Betty", "Dagne", 17));
		System.out.println("Size() : " + list.size() + "\nThe List elements are\n " + list);
		list.remove("Betty");
		System.out.println("Size() : " + list.size() + " is \n" + list);
		list.insert(new Person("Worku", "Habtamu", 10), 1);
		list.insert(new Person("Betew", "Henok", 20), 3);
		System.out.println("Size() : " + list.size() + " is \n" + list);
		System.out.println("\n Searching of Betew: " + list.find("Betew"));
		System.out.println(list.get(3));
	}
}
/*Output
Resizing
Size() : 4
The List elements are
 [Person [lastName=Thomas FirstName=Tibebu Age=25], 
[Person [lastName=Solomon FirstName=Haile Age=30], 
[Person [lastName=Mekonnen FirstName=Andualem Age=35], 
[Person [lastName=Betty FirstName=Dagne Age=17]]
Size() : 3 is 
[Person [lastName=Thomas FirstName=Tibebu Age=25], 
[Person [lastName=Solomon FirstName=Haile Age=30], 
[Person [lastName=Mekonnen FirstName=Andualem Age=35]]
Size() : 5 is 
[Person [lastName=Thomas FirstName=Tibebu Age=25], 
[Person [lastName=Worku FirstName=Habtamu Age=10], 
[Person [lastName=Solomon FirstName=Haile Age=30], 
[Person [lastName=Betew FirstName=Henok Age=20], 
[Person [lastName=Mekonnen FirstName=Andualem Age=35]]

 Searching of Betew: true
Person [lastName=Betew FirstName=Henok Age=20]
*/