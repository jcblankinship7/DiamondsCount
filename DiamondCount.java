import java.util.HashMap;
import java.util.Map;

public class Main {
    
  public static int solution(int[] X, int[] Y) {
    int n = X.length;
    Map<String, Integer> pairCount = new HashMap<>();
    int diamondCount = 0;

    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        int midX = X[i] + X[j];
        int midY = Y[i] + Y[j];
        String midPoint = midX + "," + midY;

        diamondCount += pairCount.getOrDefault(midPoint, 0);
        pairCount.put(midPoint, pairCount.getOrDefault(midPoint, 0) + 1);
      }
    }

    return diamondCount;
  }
    
  public static void main(String args[]) {
    int[] X = {1, 2, 3, 3, 2, 1};
    int[] Y = {1, 1, 1, 2, 2, 2};
    System.out.println("Number of diamonds: " + solution(X, Y));
  }
}
