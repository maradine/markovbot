import java.util.HashMap;

public class MarkovCloud extends HashMap<Long,MarkovChain> {

    public MarkovCloud() {
        super();

    }

    public void ingest(long id, String message) {
        if (this.containsKey(id)) {
            MarkovChain chain = this.get(id);
            chain.ingest(message);
        } else {
            MarkovChain chain = new MarkovChain();
            chain.ingest(message);
            this.put(id, chain);

        }
    }

}
