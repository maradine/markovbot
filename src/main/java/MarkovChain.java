import java.util.HashMap;
import java.util.Scanner;

public class MarkovChain extends HashMap<String, MarkovForwardMap> {

    private static String startToken = "___start___";
    private static String endToken = "___end___";

    public MarkovChain() {
        super();
    }

    public void ingest(String message) {
        //append endtoken to string so we can use one loop to handle front and back cases
        message = message + " " + endToken;

        Scanner scanner = new Scanner(message);
        String currentToken = startToken;
        String forwardToken= null;

        while (scanner.hasNext()) {
            forwardToken = scanner.next();
            System.out.println("processing " + currentToken + " --> " +forwardToken);
            if (this.containsKey(currentToken)) {
                System.out.println("Chain has a mapping, ingesting forward token.");
                MarkovForwardMap map = this.get(currentToken);
                map.ingest(forwardToken);
            } else {
                System.out.println("Chain has no mapping, creating new Forward Map and ingesting.");
                MarkovForwardMap map = new MarkovForwardMap();
                map.ingest(forwardToken);
                this.put(currentToken, map);
            }
            currentToken = forwardToken;
        }

    }
}
