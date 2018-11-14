import java.util.HashMap;

public class MarkovCloud extends HashMap<Long,MarkovChain> {

    public MarkovCloud() {
        super();

    }

    public void ingest(long id, String message) {
        if (this.containsKey(id)) {
            System.out.println("Cloud has seen this talker before.");
            MarkovChain chain = this.get(id);
            chain.ingest(message);
        } else {
            System.out.println("Cloud has never seen this talker, creating new chain.");
            MarkovChain chain = new MarkovChain();
            chain.ingest(message);
            this.put(id, chain);
        }
    }

}
