import java.math.BigInteger;
import java.util.List;

public class LagrangeInterpolation {

    public static BigInteger interpolate(List<BaseValuePair> points) {
        BigInteger result = BigInteger.ZERO;

        for (int i = 0; i < points.size(); i++) {
            BigInteger x_i = BigInteger.valueOf(i + 1);
            BigInteger y_i = new BigInteger(points.get(i).getValue());

            BigInteger term = y_i;

            for (int j = 0; j < points.size(); j++) {
                if (i != j) {
                    BigInteger x_j = BigInteger.valueOf(j + 1);
                    term = term.multiply(x_j.negate()).divide(x_i.subtract(x_j));
                }
            }
            result = result.add(term);
        }

        return result;
    }
}
