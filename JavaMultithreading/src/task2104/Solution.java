package task2104;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* 
Equals and HashCode
*/

public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Object n) {
        if(n==null)return false;
        if(n==this)return true;
        if (!(n instanceof Solution)) return false;
        Solution sol=(Solution) n;
        if (first != null ? !first.equals(sol.first) : sol.first != null) return false;
        return last != null ? last.equals(sol.last) : sol.last == null;
    }

    public int hashCode() {
        return Objects.hash(first, last);
        //return 31 * first.hashCode() + last.hashCode();
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Donald", "Duck"));
        System.out.println(s.contains(new Solution("Donald","Duck")));

    }
}
