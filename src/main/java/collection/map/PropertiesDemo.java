package collection.map;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Features of Properties class:
 *
 * a. Properties is a subclass of Hashtable.
 * b. It is used to maintain a list of values in which the key is a string and the value is also a string i.e; it can be
 * used to store and retrieve string type data from the properties file.
 * c. Properties class can specify other properties list as it’s the default. If a particular key property is not present
 * in the original Properties list, the default properties will be searched.
 * d. Properties object does not require external synchronization and Multiple threads can share a single Properties object.
 * e. Also, it can be used to retrieve the properties of the system
 *
 * @link <a href="https://www.geeksforgeeks.org/java-util-properties-class-java/">...</a>
 *
 */
public class PropertiesDemo {

    public static void main(String[] args) throws Exception
    {
        // create a reader object on the properties file
        FileReader reader = new FileReader("C:\\Users\\Ashwanikumar_Singh\\Desktop\\Study\\multi-threading\\multi-threading\\src\\main\\resources\\db.properties");

        // create properties object
        Properties p = new Properties();

        // Add a wrapper around reader object
        p.load(reader);

        // access properties data
        System.out.println(p.getProperty("username"));
        System.out.println(p.getProperty("password"));

        //------------------------------------------------------------------------

        System.setProperty("prop1", "value1");
        System.setProperty("prop2", "value2");
        p = System.getProperties();

        // stores set of properties information
        Set<Map.Entry<Object, Object>> set = p.entrySet();

        // iterate over the set
        for (Map.Entry<Object, Object> entry : set) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        //---------------------------------------------------------------------------

        // add properties to it
        p.setProperty("name", "XYZ");
        p.setProperty("email", "xyz@gmail.com");

        // store the properties to a file
        p.store(new FileWriter("info.properties"), "Hashtable Properties Example");
    }
}
