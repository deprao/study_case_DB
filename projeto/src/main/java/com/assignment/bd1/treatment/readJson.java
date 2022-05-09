package com.assignment.bd1.treatment;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.*;

@SuppressWarnings("unchecked")
public class readJson {

    private LinkedList notebooks = new LinkedList();

    public LinkedList readFile(String fileName) {
        JSONParser parser = new JSONParser();

        try {
            String filePath = "src/main/resources/json/" + fileName;
            //System.out.println("Filepath no readJson:" + filePath);
            Object obj = parser.parse(new FileReader(filePath));
            JSONArray jsonArray = (JSONArray) obj;
            int length = jsonArray.size();

            for (int i = 0; i < length; i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Set s = jsonObject.entrySet();
                Iterator iter = s.iterator();

                HashMap hm = new HashMap();

                while (iter.hasNext()) {
                    Map.Entry me = (Map.Entry) iter.next();
                    hm.put(me.getKey(), me.getValue());
                }
                notebooks.add(hm);
            }



            //for (int i = 0; i < notebooks.size(); i++) {
                //HashMap hm = (HashMap) notebooks.get(i);
                //Set s = hm.entrySet();
                // iter = s.iterator();
                //System.out.println(hm.get("marca"));
                //while (iter.hasNext()) {
                    //Map.Entry me = (Map.Entry) iter.next();
                    //System.out.println(me.getKey() + " - " + me.getValue());
                //}

            //}
        }
        catch(FileNotFoundException e)
            {
                e.printStackTrace();
            }
        catch(Exception e)
            {
                e.printStackTrace();
            }
        return notebooks;
        }
}
