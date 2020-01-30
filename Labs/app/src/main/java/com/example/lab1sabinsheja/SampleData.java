package com.example.lab1sabinsheja;

import java.util.ArrayList;
import java.util.List;

public class SampleData {
    public static List<Students> studentList;

    static {
        studentList = new ArrayList<>();
        addItem(new Students("Rukundo", "Jean","Sauveur","+250786635688"));
        addItem(new Students("Mugwaneza","Orly","Lewis","+250787163235"));
        addItem(new Students("Kagame","Fraterne","Madarubindi","+250788779077"));
        addItem(new Students("Omar", "Bassam","Kazungu","+250726442179"));
        addItem(new Students("Muhoza", "Marlyn","mimi","+250781492816"));
        addItem(new Students("Sheja", "sabin","GrandMaster","+250786182098"));
        }

        private static void addItem(Students item){
        studentList.add(item);
        }
        }
