import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Professors {
   private Set<String> mySet = new HashSet<String>();
   private java.util.Map<String, String> myMap = new HashMap<String, String>();


    public void getProfessors() throws IOException {
        try {
            InputStream in = getClass().getResourceAsStream("/professors.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String st;
            while ((st = br.readLine()) != null) {
                if( st.contains(",")) {
                    mySet.add(st.split(Pattern.quote(","))[0]);
                    mySet.add(st.split(Pattern.quote(","))[1]);
                    myMap.put(st.split(Pattern.quote(","))[0], st.split(Pattern.quote(","))[1]);
                }
                else
                    mySet.add(st);
            }
            br.close();
        }
        catch(IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public Set<String> getMySet() {
        return mySet;
    }

    public void setMySet(Set<String> mySet) {
        this.mySet = mySet;
    }

    public java.util.Map<String, String> getMyMap() {
        return myMap;
    }

    public void setMyMap(java.util.Map<String, String> myMap) {
        this.myMap = myMap;
    }
}
