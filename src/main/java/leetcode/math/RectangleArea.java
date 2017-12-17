package leetcode.math;

/*
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 * Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
 * Assume that the total area is never beyond the maximum possible value of int.
 * (A, B), (E, F) - bottom left points, (C,D) (G,H) - top right points
 */
public class RectangleArea {
    public int computeArea_checkOverlap(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = (C - A) * (D - B), area2 = (G - E) * (H - F);

        if (E >= C || A >= G || F >= D || B >= H) {
            return area1 + area2;
        }

        if (A == C || B == D || (E <= A && F <= B && G >= C && H >= D)) {
            return area2;
        }

        if (E == G || F == H || (A <= E && B <= F && C >= G && D >= H)) {
            return area1;
        }

        int x1 = Math.max(A, E);
        int y1 = Math.max(B, F);
        int x2 = Math.min(C, G);
        int y2 = Math.min(D, H);

        return area1 + area2 - Math.abs((x2 - x1) * (y2 - y1));
    }

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int left = Math.max(A, E);
        int right = Math.max(Math.min(C, G), left); // need to compare with left
                                                    // for the non-overlapping
                                                    // case
        int bottom = Math.max(B, F);
        int top = Math.max(Math.min(D, H), bottom);

        // no need to check if two rectangles overlap since the overlapping area
        // will be zero if overlapping does not exist.
        return (C - A) * (D - B) + (G - E) * (H - F) - (right - left) * (top - bottom);
    }

    public static void main(String[] args) {
        RectangleArea ra = new RectangleArea();
        // int A=-3, B=0, C=3, D=4, E=0, F=-1, G=9, H=2;
        // int A=-2, B=-2, C=2, D=2, E=-2, F=-2, G=2, H=2;
        // int A=0, B=0, C=0, D=0, E=-1, F=-1, G=1, H=1;
        // int A=-2, B=-2, C=2, D=2, E=3, F=3, G=4, H=4;
        // int A=-2, B=-2, C=2, D=2, E=-1, F=-1, G=1, H=1;
        // int A=-2, B=-2, C=2, D=2, E=1, F=-3, G=3, H=-1;
        // int A=-2, B=-2, C=2, D=2, E=-3, F=1, G=-1, H=3;
        int A = -2, B = -2, C = 2, D = 2, E = -3, F = 1, G = 3, H = 3;
        System.out.println(ra.computeArea_checkOverlap(A, B, C, D, E, F, G, H));
    }
}
