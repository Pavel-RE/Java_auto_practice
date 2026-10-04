package org.example;

import java.util.List;
import lombok.Getter;

@Getter
public class Student {
     String name;
     String secondName;
     String studyGroup;
     Integer streamNumber;
     List<Integer> marksList;
     List<String> passedBlocks;

    public static StudentBuilder builder(){
        return new StudentBuilder();
    }
}

