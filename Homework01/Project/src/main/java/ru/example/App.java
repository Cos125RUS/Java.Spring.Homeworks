package ru.example;

import com.google.gson.Gson;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Person person1 = new Person("Ivan", "Ivanov", 25);
        Person person2 = new Person("Petr", "Petrov", 35);
        lang3(person1, person2);
        System.out.println("------------------------------------------------------------");
        gson(person1);
    }

    public static void lang3(Person person1, Person person2) {
        Person person3 = person1;
        ObjectUtils objectUtils = new ObjectUtils();
        System.out.println(objectUtils.equals(person1, person2));
        System.out.println(objectUtils.equals(person1, person3));
        System.out.println(objectUtils.hashCode(person1));
        System.out.println(objectUtils.hashCode(person2));
        System.out.println(objectUtils.hashCode(person3));
        System.out.println(objectUtils.toString(person1));
        System.out.println(objectUtils.toString(person2));
        System.out.println(ToStringBuilder.reflectionToString(person1));
        System.out.println(ToStringBuilder.reflectionToString(person1));
    }

    public static void gson(Person person) {
        Gson gson = new Gson();
        String json = gson.toJson(person);
        System.out.println(json);
        Person newPerson = gson.fromJson(json, Person.class);
        System.out.println(ToStringBuilder.reflectionToString(newPerson));
    }
}
