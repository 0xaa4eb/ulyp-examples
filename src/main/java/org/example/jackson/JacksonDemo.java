package org.example.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JacksonDemo {

    static class Person {
        private String firstName;
        private String lastName;
        private int age;
        private List<String> hobbies;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public List<String> getHobbies() {
            return hobbies;
        }

        public void setHobbies(List<String> hobbies) {
            this.hobbies = hobbies;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", age=" + age +
                    ", hobbies=" + hobbies +
                    '}';
        }
    }

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println(objectMapper.readValue(
                "{\"firstName\":\"Peter\",\"lastName\":\"Quill\",\"age\":40,\"hobbies\":[\"Dancing\",\"Starships\",\"Music\"]}",
                Person.class)
        );
        System.out.println(objectMapper.readValue(
                "{\"firstName\":\"Peter\",\"lastName\":\"Quill\",\"age\":40,\"hobbies\":[\"Dancing\",\"Starships\",\"Music\"]}",
                Person.class)
        );
    }
}
