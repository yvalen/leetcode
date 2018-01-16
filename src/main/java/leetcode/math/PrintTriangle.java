package leetcode.math;

public class PrintTriangle {
    public static void print(int n) {
        for (int i = 1; i <= n; i++) {
            // additional space before 
            for (int j = 0; j < n -i; j++) {
                System.out.print("  "); // need to print two space here to account for the space between *
            }
            
            for (int k = 0; k < 2*i-1; k++) {
                System.out.print("* ");
            }
            
            System.out.println();
        }
    }
    
    public static void printInverse(int n) {
        for (int i = n; i >= 1; i--) {
            // additional space before 
            for (int j = 0; j < n -i; j++) {
                System.out.print("  "); // need to print two space here to account for the space between *
            }
            
            for (int k = 0; k < 2*i-1; k++) {
                System.out.print("* ");
            }
            
            System.out.println();
        }
    }
    
    

    public static void main(String[] args) {
        //print(4);
        printInverse(4);
        
    }
    
}
