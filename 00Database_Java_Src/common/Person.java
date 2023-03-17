package common;

public class Person {
	
	private String name;	//모든 속성데이터가 private : 규약2번
	private int age;
		
	public Person() {		//기본생성자 : 규약3번 (없으면 자바가 자동으로 만들어줌)
		// TODO Auto-generated constructor stub
	}

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {	//게터, 세터 : 규약 4,5번
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
}
