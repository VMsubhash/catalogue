import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath1 = "C:\\Users\\subas\\OneDrive\\Desktop\\codes\\catalogue\\data1.json";
        String filePath2 = "C:\\Users\\subas\\OneDrive\\Desktop\\codes\\catalogue\\data2.json";

        try {
            List<BaseValuePair> points1 = processTestCase(filePath1);
            BigInteger secret1 = LagrangeInterpolation.interpolate(points1);
            System.out.println("Secret for first testcase (constant term c): " + secret1);

            List<BaseValuePair> points2 = processTestCase(filePath2);
            BigInteger secret2 = LagrangeInterpolation.interpolate(points2);
            System.out.println("Secret for second testcase (constant term c): " + secret2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<BaseValuePair> processTestCase(String filePath) throws IOException {
        List<BaseValuePair> baseValuePairs = JsonReaderUtility.getBaseValuePairs(filePath);
        List<BaseValuePair> points = new ArrayList<>();

        for (int i = 0; i < baseValuePairs.size(); i++) {
            int x = i + 1;
            int base = baseValuePairs.get(i).getBase();
            String value = baseValuePairs.get(i).getValue();

            BigInteger y = new BigInteger(value, base);
            points.add(new BaseValuePair(x, y.toString()));
        }

        return points;
    }
}
