package com.nickname;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

  public static List<String> main(String[] args) {

    String csvFile = "./names.csv";
    String line = "";
    String searchName = args[0];
    String cvsSplitBy = ",";

    /* Intialize array of strings */
    List<String> list = new ArrayList<String>();

    ClassLoader classLoader = CSVReader.class.getClassLoader();
    File file = new File(classLoader.getResource("names.csv").getFile());

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {

      while ((line = br.readLine()) != null) {
        // use comma as separator
        String[] name = line.split(cvsSplitBy);

        /* Check first value for match, otherwise ignore */
        if (name[0].equals(searchName.toLowerCase())) {
          /* Add items to list */
          for (int i = 0; i < name.length; i++) {
            if (!list.contains(name[i])) {
              // Check for dupes before adding
              list.add(name[i]);
            }
          }
        }
      }

      System.out.println("search across the following names for ('" + searchName + "'): " + list.toString());

    } catch (IOException e) {
      e.printStackTrace();
    }

    return list;

  }

}
