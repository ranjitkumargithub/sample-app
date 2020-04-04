package com.cwave.sample;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MapVsFileDemo {
    static Map<Integer, User> map = getData();
    public static void main(String[] args) {
        //readFromFile();
        readFromMap();
    }
    private static void readFromFile() {
        long start = System.currentTimeMillis();
        int count = 0;
        long fileSize = 0L;
        try {
            File f = new File("data/user_data_100000.txt");
            fileSize = f.length() / (1024 * 1024);
            FileInputStream fis = new FileInputStream(f);
            InputStreamReader reader = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(reader);
            User u = null;
            String line;
            while((line = br.readLine()) != null) {
                if(line.contains("9999")) {
                    System.out.println(line);
                    count++;
                }
            }
            br.close();
            reader.close();
            fis.close();
        } catch(FileNotFoundException fne) {
            fne.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("Total count in file:"+count);
        System.out.println("Time taken using file :"+(end-start));
        System.out.println("File size :"+fileSize+" MB");
    }
    private static Map<Integer, User> getData() {
        Map<Integer, User> map = new HashMap<>();
        User u = null;
        for(int i = 0; i < 100000; i++) {
            u = new User();
            u.setId(i);
            u.setName("name"+i);
            u.setEmail("email"+i+"@gmail.com");
            map.put(i, u);
        }
        return map;
    }

    private static void readFromMap() {
        int count = 0;
        long start = System.currentTimeMillis();
        for(Map.Entry entry : map.entrySet()) {
            if(entry.getValue().toString().contains("9999")) {
                System.out.println(entry.getValue().toString());
                count++;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Total count in map :"+count);
        System.out.println("Time taken using map :"+(end-start));
        System.out.println("Map size:"+map.size());
    }
}
