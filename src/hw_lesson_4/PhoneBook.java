package hw_lesson_4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    private HashMap<String, String> hm = new HashMap<String, String>();

    public void add (String phone, String name) {
        hm.put(phone, name);
    }
    public void get (String name) {
        List<String> result = new ArrayList<String>();
        for (Map.Entry entry: hm.entrySet()) {
            if (name.equalsIgnoreCase((String) entry.getValue())) {
                result.add((String) entry.getKey());
            }
        }
        System.out.println(name + ":" + result);
    }
}
