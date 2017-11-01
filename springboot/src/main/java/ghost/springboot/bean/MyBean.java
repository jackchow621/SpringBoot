package ghost.springboot.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@ConfigurationProperties(prefix="my")
@Component
public class MyBean {
	private String name;
    private int age;
    private int number;
    private String uuid;
    private int max;
    private String value;
    private String greeting;
    
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MyBean [name=");
		builder.append(name);
		builder.append(", age=");
		builder.append(age);
		builder.append(", number=");
		builder.append(number);
		builder.append(", uuid=");
		builder.append(uuid);
		builder.append(", max=");
		builder.append(max);
		builder.append(", value=");
		builder.append(value);
		builder.append(", greeting=");
		builder.append(greeting);
		builder.append("]");
		return builder.toString();
	}
	public String getName() {
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
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getGreeting() {
		return greeting;
	}
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
    
    
}
