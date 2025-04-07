import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;

public class Main {

    public static int solution(int[]X , int[] Y) {
         int n = X.length;
        HashSet<String> diamonds = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        if (isDiamond(X[i], Y[i], X[j], Y[j], X[k], Y[k], X[l], Y[l])) {
                            String diamond = getDiamondString(X[i], Y[i], X[j], Y[j], X[k], Y[k], X[l], Y[l]);
                            diamonds.add(diamond);
                        }
                    }
                }
            }
        }

        return diamonds.size();
    }

    private static boolean isDiamond(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        int d1 = distanceSquared(x1, y1, x2, y2);
        int d2 = distanceSquared(x2, y2, x3, y3);
        int d3 = distanceSquared(x3, y3, x4, y4);
        int d4 = distanceSquared(x4, y4, x1, y1);
        int d5 = distanceSquared(x1, y1, x3, y3);
        int d6 = distanceSquared(x2, y2, x4, y4);

        return d1 == d3 && d2 == d4 && d5 == d6;
    }

    private static int distanceSquared(int x1, int y1, int x2, int y2) {
        return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
    }

    private static String getDiamondString(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        int[] xs = {x1, x2, x3, x4};
        int[] ys = {y1, y2, y3, y4};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(xs[i]).append(",").append(ys[i]).append(";");
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        // First example: Should output 3
        int[] X1 = {1, 2, 3, 3, 2, 1};
        int[] Y1 = {1, 2, 3, 3, 2, 1};
        System.out.println("Number of diamonds (example 1): " + solution(X1, Y1)); // Output: 3

        // Second example: Should output 2
        int[] X2 = {1, 1, 2, 2, 2, 3, 3};
        int[] Y2 = {3, 4, 1, 3, 5, 3, 4};
        System.out.println("Number of diamonds (example 2): " + solution(X2, Y2)); // Output: 2
    }
}
