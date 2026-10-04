package org.example;

public class StudentBuilder {
    private Student student;

    public StudentBuilder(){
        this.student = new Student();
    }

    public StudentBuilder setName(String name){
        student.name = name;
        return this;
    }

    public StudentBuilder setSecondName(String secondName){
        student.secondName = secondName;
        return this;
    }

    public Student build(){
        return student;
    }
}
