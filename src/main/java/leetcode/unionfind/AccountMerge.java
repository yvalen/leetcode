package leetcode.unionfind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/*
 * LEETCODE 721
 * Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, 
 * and the rest of the elements are emails representing emails of the account. Now, we would like to merge these accounts. 
 * Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even 
 * if two accounts have the same name, they may belong to different people as people could have the same name. A person can 
 * have any number of accounts initially, but all of their accounts definitely have the same name. After merging the accounts, 
 * return the accounts in the following format: the first element of each account is the name, and the rest of the elements 
 * are emails in sorted order. The accounts themselves can be returned in any order.
 * Example 1:
 * Input: 
 * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 * Explanation: The first and third John's are the same person as they have the common email "johnsmith@mail.com". The second 
 * John and Mary are different people as none of their email addresses are used by other accounts. We could return these lists 
 * in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 * Note:
 * - The length of accounts will be in the range [1, 1000].
 * - The length of accounts[i] will be in the range [1, 10].
 * - The length of accounts[i][j] will be in the range [1, 30].
 * 
 * Company: Facebook
 * Difficulty: medium
 * Similar Questions: 
 */
public class AccountMerge {
    //
    // DFS
    public List<List<String>> accountsMerge_dfs(List<List<String>> accounts) {
        // build the graph, a map that maps email to a list of accounts (use
        // index as account id)
        // this tracks which email links to which accounts
        Map<String, List<Integer>> emailAccountMap = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                emailAccountMap.putIfAbsent(email, new ArrayList<>());
                emailAccountMap.get(email).add(i);
            }
        }
        System.out.println(emailAccountMap);

        // dfs on each account, find accounts that are linked to this account
        // via common emails
        boolean[] visited = new boolean[accounts.size()];
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < accounts.size(); i++) {
            Set<String> accountEmails = new TreeSet<>(); // collects all emails
                                                         // that are linked to
                                                         // this account
            dfs(accounts, emailAccountMap, i, visited, accountEmails);
            if (!accountEmails.isEmpty()) { // need to check for empty here,
                                            // otherwise linked account will
                                            // appear in the result wih empty
                                            // email list
                List<String> r = new ArrayList<>(accountEmails.size() + 1);
                r.add(accounts.get(i).get(0));
                r.addAll(accountEmails);
                result.add(r);
            }
        }

        return result;
    }

    private void dfs(List<List<String>> accounts, Map<String, List<Integer>> emailAccountMap, int account,
            boolean[] visited, Set<String> accountEmails) {
        if (visited[account])
            return;

        visited[account] = true;
        List<String> currentEmails = accounts.get(account);
        for (int i = 1; i < currentEmails.size(); i++) {
            String email = currentEmails.get(i);
            accountEmails.add(email);
            for (Integer a : emailAccountMap.get(email)) {
                dfs(accounts, emailAccountMap, a, visited, accountEmails);
            }
        }
    }

    //
    // Union Find
    //
    private static class UnionFind {
        private int[] ids; // account ids

        UnionFind(int n) {
            ids = new int[n];
            for (int i = 0; i < n; i++)
                ids[i] = i;
        }

        int find(int p) {
            while (ids[p] != p)
                p = ids[p];
            return p;
        }

        void union(int p, int q) {
            int i = find(p);
            int j = find(q);
            if (i == j)
                return;
            ids[i] = j;
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        UnionFind uf = new UnionFind(n);

        Map<String, Integer> emailAccountMap = new HashMap<>(); // store the
                                                                // email to
                                                                // parent
                                                                // account
                                                                // mapping
        Map<Integer, Set<String>> accountEmailMap = new HashMap<>();
        // build the map and perform union
        for (int i = 0; i < n; i++) {
            accountEmailMap.put(i, new HashSet<>());
            List<String> emails = accounts.get(i);
            for (int j = 1; j < emails.size(); j++) {
                String email = emails.get(j);
                if (emailAccountMap.containsKey(email)) { // if this email
                                                          // belongs to an
                                                          // account already,
                                                          // union the current
                                                          // account with that
                                                          // account
                    uf.union(i, emailAccountMap.get(email));
                } else {
                    emailAccountMap.put(email, i);
                }
                accountEmailMap.get(i).add(email);
            }
        }

        // perform merge
        Map<Integer, Set<String>> resultMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            // get the parent account for the current account
            int parent = uf.find(i);
            resultMap.putIfAbsent(parent, new TreeSet<>()); // use tree set for
                                                            // sortimg
            resultMap.get(parent).addAll(accountEmailMap.get(i)); // add all
                                                                  // emails
                                                                  // associated
                                                                  // with
                                                                  // current
                                                                  // account to
                                                                  // parent's
                                                                  // email set
        }

        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<Integer, Set<String>> entry : resultMap.entrySet()) {
            List<String> list = new ArrayList<>(entry.getValue().size() + 1);
            list.add(accounts.get(entry.getKey()).get(0)); // add name
            list.addAll(entry.getValue());
            result.add(list);
        }
        return result;
    }

    public static void main(String[] args) {
        AccountMerge am = new AccountMerge();

        List<List<String>> accounts = Arrays.asList(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"),
                Arrays.asList("John", "johnnybravo@mail.com"),
                Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                Arrays.asList("Mary", "mary@mail.com"));

        /*
         * List<List<String>> accounts = Arrays.asList(
         * Arrays.asList("David","David0@m.co","David1@m.co"),
         * Arrays.asList("David","David3@m.co","David4@m.co"),
         * Arrays.asList("David","David4@m.co","David5@m.co"),
         * Arrays.asList("David","David2@m.co","David3@m.co"),
         * Arrays.asList("David","David1@m.co","David2@m.co"));
         */
        System.out.println(am.accountsMerge_dfs(accounts));
    }
}
