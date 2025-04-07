import java.util.HashSet;

class Solution {
    public int solution(int[] X, int[] Y) {
        int n = X.length;
        HashSet<String> uniqueDiamonds = new HashSet<>();
        
        // Create a hashset of points for O(1) lookup
        HashSet<String> points = new HashSet<>();
        for (int i = 0; i < n; i++) {
            points.add(X[i] + "," + Y[i]);
        }
        
        // Check every pair of points as potential opposite corners of a diamond
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Only consider pairs that could form a diamond's diagonal
                // Centers of diamonds must have integer coordinates (average of opposite corners)
                if ((X[i] + X[j]) % 2 != 0 || (Y[i] + Y[j]) % 2 != 0) {
                    continue;
                }
                
                // Calculate center of potential diamond
                int centerX = (X[i] + X[j]) / 2;
                int centerY = (Y[i] + Y[j]) / 2;
                
                // Vector from center to point i
                int vectorX = X[i] - centerX;
                int vectorY = Y[i] - centerY;
                
                // Calculate other two corners of diamond by rotating the vector 90 degrees
                // Rotation matrix for 90 degrees: (x,y) -> (-y,x)
                int corner1X = centerX - vectorY;
                int corner1Y = centerY + vectorX;
                
                // Rotation matrix for -90 degrees: (x,y) -> (y,-x)
                int corner2X = centerX + vectorY;
                int corner2Y = centerY - vectorX;
                
                // Check if the other two corners exist in our point set
                if (points.contains(corner1X + "," + corner1Y) && 
                    points.contains(corner2X + "," + corner2Y)) {
                    
                    // Found a diamond - add it to our set of unique diamonds
                    // Sort the coordinates for consistent representation
                    int[] xCoords = {X[i], X[j], corner1X, corner2X};
                    int[] yCoords = {Y[i], Y[j], corner1Y, corner2Y};
                    
                    String diamond = getSortedDiamondString(xCoords, yCoords);
                    uniqueDiamonds.add(diamond);
                }
            }
        }
        
        return uniqueDiamonds.size();
    }
    
    // Helper method to get a consistent string representation of a diamond
    private String getSortedDiamondString(int[] x, int[] y) {
        // Sort points by x, then y for consistent representation
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                if (x[i] > x[j] || (x[i] == x[j] && y[i] > y[j])) {
                    // Swap
                    int tempX = x[i];
                    int tempY = y[i];
                    x[i] = x[j];
                    y[i] = y[j];
                    x[j] = tempX;
                    y[j] = tempY;
                }
            }
        }
        
        // Create a string representation with points in sorted order
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            if (i > 0) sb.append(";");
            sb.append(x[i]).append(",").append(y[i]);
        }
        return sb.toString();
    }
}

// Main class to test the solution
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Example 1: Should return 2
        int[] X1 = {1, 1, 2, 2, 2, 3, 3};
        int[] Y1 = {3, 4, 1, 3, 5, 3, 4};
        System.out.println("Example 1 output: " + solution.solution(X1, Y1));
        
        // Example 2: Should return 0
        int[] X2 = {1, 2, 3, 3, 2, 1};
        int[] Y2 = {1, 1, 1, 2, 2, 2};
        System.out.println("Example 2 output: " + solution.solution(X2, Y2));
    }
}
