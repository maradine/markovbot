import java.util.HashMap;
import java.util.Scanner;

public class MarkovChain extends HashMap<String, MarkovForwardMap> {

    private static String startToken = "___start___";
    private static String endToken = "___end___";

    public MarkovChain() {
        super();
    }

    public void ingest(String message) {
        Scanner scanner = new Scanner(message);
        String currentToken = startToken;
        String forwardToken = scanner.next();


        while (scanner.hasNext()){

        }


    }
}
