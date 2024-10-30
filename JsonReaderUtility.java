import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonReaderUtility {

    public static int[] getNKValues(String filePath) throws IOException {
        String jsonContent = readFile(filePath);
        int n = extractIntValue(jsonContent, "\"n\":");
        int k = extractIntValue(jsonContent, "\"k\":");

        return new int[]{n, k};
    }

    public static List<BaseValuePair> getBaseValuePairs(String filePath) throws IOException {
        String jsonContent = readFile(filePath);
        List<BaseValuePair> baseValuePairs = new ArrayList<>();

        int index = 1;
        int position = 0;
        while ((position = jsonContent.indexOf("\"" + index + "\":", position)) != -1) {
            // Locate each "base" and "value" from the position of each index entry
            String baseKey = "\"" + index + "\": {\"base\":";
            String valueKey = "\"value\":";
            String base = extractStringValue(jsonContent, baseKey, position);
            String value = extractStringValue(jsonContent, valueKey, position);

            if (base != null && value != null) {
                baseValuePairs.add(new BaseValuePair(Integer.parseInt(base), value));
            }
            index++;
        }

        return baseValuePairs;
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder jsonContent = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            jsonContent.append(line.trim());
        }
        reader.close();
        return jsonContent.toString();
    }

    private static int extractIntValue(String json, String key) {
        int index = json.indexOf(key);
        if (index != -1) {
            int start = json.indexOf(":", index) + 1;
            int end = Math.min(json.indexOf(",", start) != -1 ? json.indexOf(",", start) : json.length(),
                               json.indexOf("}", start) != -1 ? json.indexOf("}", start) : json.length());
            return Integer.parseInt(json.substring(start, end).trim());
        }
        return 0;
    }

    private static String extractStringValue(String json, String key, int startPosition) {
        int index = json.indexOf(key, startPosition);
        if (index != -1) {
            int start = json.indexOf("\"", index + key.length()) + 1;
            int end = json.indexOf("\"", start);
            return json.substring(start, end);
        }
        return null;
    }
}
