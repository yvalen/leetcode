package leetcode.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you 
 * and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and 
 * therefore the other person will be the winner
 */
public class FlipGame {
	/*
	 * Write a function to compute all possible states of the string after one valid move.
	 * For example, given s = "++++", after one move, it may become one of the following states:
	 * [
	 * 	"--++",
	 * 	"+--+",
	 * 	"++--"
	 * ]
	 * If there is no valid move, return an empty list [].
	 * 
	 * Company: Google
	 * Difficulty: easy
	 */
	public List<String> generatePossibleNextMoves(String s) {
        if (s == null || s.length() < 2) return Collections.emptyList();
        
        List<String> result = new ArrayList<>();
        int i = 0;
        while (i < s.length() - 1) { 
        	if (s.charAt(i) == '+') {
        		if (s.charAt(i+1) == '+') {
        			result.add(s.substring(0, i) + "--" + s.substring(i+2));
        			i++; // only increment by one when there two consecutive +
        		}
        		else i += 2; // skip - and go to the next char
        	}
        	else {
        		i++;
        	}
        }
        return result;
    }
	
	public List<String> generatePossibleNextMoves_withIndexOf(String s) {
		if (s == null || s.length() < 2) return Collections.emptyList();
        
        List<String> result = new ArrayList<>();
        for (int i = -1; (i = s.indexOf("++", i+1)) >= 0;) {
        	result.add(s.substring(0, i) + "--" + s.substring(i+2)); // won't throw IndexOutOfBoundException because i+2 will never be greater than the length of s
        }
        return result;
	}
	
	/*
	 * Write a function to determine if the starting player can guarantee a win.
	 * For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+". 
	 * 
	 * Company: Google
	 * Difficulty: medium
	 */
	// The idea is try to replace every "++" in the current string s to "--" and see if the opponent can win or not, 
	// if the opponent cannot win, great, we win!
	// There are at most n - 1 ways to replace "++" to "--" (imagine s is all "+++..."), once we replace one "++", 
	// there are at most (n - 2) - 1 ways to do the replacement, the time complexity is (n - 1) x (n - 3) x (n - 5) x ..., so it's O(n!!), double factorial.
	public boolean canWin(String s) {
		if (s == null || s.length() < 2) return false;
		/*
		for (int i = 0; i < s.length()-1; i++) {
			if (s.startsWith("++", i)) {
				String t = s.substring(0, i) + "--" + s.substring(i+2);
				if (!canWin(t)) return true;
			}
			
		}
		return false;
     	*/
		return canWin(s, new HashMap<>());
    }
	
	// memorize the state of each string
	private boolean canWin(String s, Map<String, Boolean> map) {
		if (map.containsKey(s)) return map.get(s);
		
		for (int i = 0; i < s.length()-1; i++) {
			if (s.startsWith("++", i)) {
				String sOpponent = s.substring(0, i) + "--" + s.substring(i+2);
				if (!canWin(sOpponent, map)) {
					map.put(s, true);  // state for s is true not sOpponent
					return true;
				}
			}
		}
		map.put(s, false);
		
		return false;
	}
	
	public static void main(String[] args) {
		FlipGame fg = new FlipGame();
		String s = "++++";
		//String s = "--++";
		//System.out.println(fg.generatePossibleNextMoves_withIndexOf(s));
		System.out.println(fg.canWin(s));
	}
}
