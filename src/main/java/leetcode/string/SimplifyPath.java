package leetcode.string;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/*
 * LEETCODE 71
 * Given an absolute path for a file (Unix-style), simplify it. 
 * For example, 
 * 	path = "/home/", => "/home"
 * 	path = "/a/./b/../../c/", => "/c"
 * Corner Cases:
 * - Did you consider the case where path = "/../"? 
 * In this case, you should return "/".
 * - Another corner case is the path might contain multiple slashes 
 * '/' together, such as "/home//foo/".
 * In this case, you should ignore redundant slashes and return "/home/foo".
 * 
 * Company: Facebook, Microsoft
 * Difficulty: medium
 */
public class SimplifyPath {
    public String simplifyPath_withStack(String path) {
        if (path == null || path.isEmpty())
            return path;

        String[] dirs = path.split("/");
        Stack<String> stack = new Stack<>();
        for (String dir : dirs) {
            // need to skip empty string as the array may contains empty string
            // e.g. path = "/..."
            if (dir.isEmpty() || ".".equals(dir)) {
                continue;
            }

            if ("..".equals(dir)) {
                if (!stack.isEmpty())
                    stack.pop();
            } else {
                stack.push(dir);
            }
        }

        // should prepend "/" to path, "///" -> "/"
        return "/" + String.join("/", stack);
    }

    public String simplifyPath_withDeque(String path) {
        if (path == null || path.isEmpty())
            return path;

        String[] dirs = path.split("/");
        Deque<String> list = new LinkedList<>();
        for (String dir : dirs) {
            if (dir.isEmpty() || ".".equals(dir)) {
                continue;
            }

            if ("..".equals(dir)) {
                if (!list.isEmpty())
                    list.removeLast();
            } else {
                list.add(dir);
            }
        }

        StringBuilder sb = new StringBuilder("/");
        for (String s : list) {
            sb.append(s).append("/");
        }
        if (sb.length() > 1)
            sb.setLength(sb.length() - 1);

        return sb.toString();
    }

    public static void main(String[] args) {
        SimplifyPath s = new SimplifyPath();
        String path = "/abc/....";
        System.out.println(s.simplifyPath_withDeque(path));

    }
}
