
class LargestSquare {
    
    private static final class Square {
        private final int size;

        public Square(int size) {
            super();
            this.size = size;
        }
        
    }
    
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 0, 1, 0, 1},
                {1, 1, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 0, 1, 1, 1},
                {1, 0, 1, 1, 1}
            };
        
        Square sq = getLargestSquare(matrix);
        
        System.out.println("Largest square: " + sq);
    }

    private static Square getLargestSquare(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return null;
        }
        
        final int height = matrix.length;
        final int width = matrix[0].length;
        
        Square max = null;
        
        // cheat, here, and use the first matrix row as 'current'
        // this will become 'previous' in the loop, and will not be changed.
        // note that the y-loop starts from 1, not 0.
        int[] previous = null;
        int[] current = matrix[0];
        
        for (int y = 1; y < height; y++) {
            // prepare the memoization space.
            // Forget the previous, move current back, and make a new current
            previous = current;
            current = new int[width];
            for (int x = 0; x < width; x++) {
                if (matrix[y][x] == 1) {
                    int span = 1;
                    if (x > 0) {
                        // no need to check the left-most column, if set, it is always size 1.
                        span = 1 + Math.min(current[x - 1], Math.min(previous[x], previous[x - 1]));
                    }
                    if (max == null || span > max.size) {
                        // because we find the max at x, and y, which are the bottom-right,
                        // we need to subtract the span to get to the top-left instead.
                        max = new Square(span);
                    }
                    current[x] = span;
                }
            }
        }
        
        return max;
    }

}
