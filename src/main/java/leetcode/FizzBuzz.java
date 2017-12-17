package leetcode;

import java.util.ArrayList;
import java.util.List;

/*
 * LEETCODE 412
 * Write a program that outputs the string representation of numbers from 1 to n.
 * But for multiples of three it should output “Fizz” instead of the number and for the multiples of 
 * five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.
 * Example: n = 15, 
 * Return:
 * [
 * 	"1",
 * 	"2",
 *	"Fizz",
 *	"4",
 *	"Buzz",
 *	"Fizz",
 *	"7",
 *	"8",
 *	"Fizz",
 *	"Buzz",
 *	"11",
 *	"Fizz",
 *	"13",
 *	"14",
 *	"FizzBuzz"
 *	]
 */
public class FizzBuzz {
    public List<String> fizzBuzz_withModulo(int n) {
        List<String> result = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0)
                result.add("FizzBuzz");
            else if (i % 3 == 0)
                result.add("Fizz");
            else if (i % 5 == 0)
                result.add("Buzz");
            else
                result.add(Integer.toString(i));
        }
        return result;
    }

    public List<String> fizzBuzz_withoutModulo(int n) {
        List<String> result = new ArrayList<>(n);
        for (int i = 1, fizz = 0, buzz = 0; i <= n; i++) {
            fizz++;
            buzz++;
            if (fizz == 3 && buzz == 5) {
                result.add("FizzBuzz");
                fizz = 0;
                buzz = 0;
            } else if (fizz == 3) {
                result.add("Fizz");
                fizz = 0;
            } else if (buzz == 5) {
                result.add("Buzz");
                buzz = 0;
            } else
                result.add(Integer.toString(i));
        }
        return result;
    }
}
