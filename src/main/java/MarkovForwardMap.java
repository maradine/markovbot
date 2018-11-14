import java.util.HashMap;

public class MarkovForwardMap extends HashMap<String,Long> {

    public MarkovForwardMap() {
        super();

    }

    public void ingest(String token){
        if (this.containsKey(token)){
            long count = this.get(token);
            count ++;
            this.put(token, count);
            System.out.println("increasing count on " + token + " to " + count);
        } else {
            this.put(token,1L);
            System.out.println("mapping token " + token + " at count 1");
        }

    }


}
