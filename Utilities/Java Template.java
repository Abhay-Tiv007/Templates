
import java.util.*;
import java.io.*;

public class D_Tandem_Repeats {
    static FastReader scan = new FastReader();
    static PrintWriter outPrintWriter = new PrintWriter(System.out);
    static final Random random = new Random();
    static long mod = 1000000007L;

    // =======================Main Program==========================
    public static void main(String args[]) throws IOException {
        int t = scan.nextInt();
        while (t-- > 0) {
            solve();
        }

        outPrintWriter.flush();
        outPrintWriter.close();
    }

    // ! Use Ruffle sort for sorting otherwise ur soln will get fked up
    // Try hard for each problem
    // ? Think for all possible cases
    // * Always use StringBuilder for string manipulation
    // gcd(a, b) == gcd(b, a%b)
    // lcm(a,b) == (a*b)/gcd(a, b)

    

    public static void solve() {
        
        String s = scan.next();
        int n = s.length();
		
        println(s);
    }

    public static boolean valid(int i, int j, char[] arr){
        return (arr[i] == '?' || arr[j] == '?' || (arr[i] == arr[j]));
    }
    

    static long binExpo(long a, long b) {
        if (b == 0)
            return 1;
        long res = binExpo(a, b / 2) % mod;
        if ((b & 1) == 1) {
            return a * res * res % mod;
        } else {
            return res * res % mod;
        }
    }

    static class Pair {
        int f;
        int s;

        Pair(int f, int s) {
            this.s = s;
            this.f = f;
        }

        // @Override
        // public int hashCode() {
        // final int prime = 31;
        // int result = 1;
        // result = prime * result + f;
        // result = prime * result + s;
        // return result;
        // }

        // @Override
        // public boolean equals(Object obj) {
        // if (this == obj)
        // return true;
        // if (obj == null)
        // return false;
        // if (getClass() != obj.getClass())
        // return false;
        // Pair other = (Pair) obj;
        // if (f != other.f)
        // return false;
        // if (s != other.s)
        // return false;
        // return true;
        // }
    }

    static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    static void ruffleSort(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int oi = random.nextInt(n), temp = a[oi];
            a[oi] = a[i];
            a[i] = temp;
        }
        Arrays.sort(a);
    }

    public static int upper_bound(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target)
                low = mid + 1;
            else
                high = mid;
        }
        return nums[low] == target ? low : -1;
    }

    public static int lower_bound(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high + 1 - low) / 2;
            if (nums[mid] > target)
                high = mid - 1;
            else
                low = mid;
        }
        return nums[low] == target ? low : -1;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = lower_bound(nums, target);
        result[1] = upper_bound(nums, target);
        return result;
    }

    static void sort(int[] arr) {
        ArrayList<Integer> ls = new ArrayList<Integer>();
        for (int x : arr) {
            ls.add(x);
        }
        Collections.sort(ls);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ls.get(i);
        }
    }

    static void sort(long[] arr) {
        ArrayList<Long> ls = new ArrayList<Long>();
        for (long x : arr) {
            ls.add(x);
        }
        Collections.sort(ls);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ls.get(i);
        }
    }

    static void revsort(int[] arr) {
        ArrayList<Integer> ls = new ArrayList<Integer>();
        for (int x : arr) {
            ls.add(x);
        }
        Collections.sort(ls, Comparator.reverseOrder());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ls.get(i);
        }
    }

    static boolean[] sieve(int n) {
        // this will mark all the prime numbers true till n
        // tc : O(nlog(logn)) ~ O(n)
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;
        for (int i = 2; i * i < n; i++) {
            if (prime[i]) {
                for (int j = i * i; j < n; j += i) {
                    prime[j] = false;
                }
            }
        }
        return prime;
    }

    static HashSet<Integer> divisors(int n) {
        // tc: O(sqrt(n))
        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                set.add(i);
                set.add(n / i);
            }
        }
        return set;
    }

    static long add(long a, long b) {
        return (a + b) % mod;
    }

    static long sub(long a, long b) {

        return ((a - b) % mod + mod) % mod;
    }

    static long mul(long a, long b) {
        return (a * b) % mod;
    }

    static long exp(long base, long exp) {
        if (exp == 0)
            return 1;
        long half = exp(base, exp / 2);
        if (exp % 2 == 0)
            return mul(half, half);
        return mul(half, mul(half, base));
    }

    static long[] factorials = new long[2_000_001];
    static long[] invFactorials = new long[2_000_001];

    static void precompFacts() {
        factorials[0] = invFactorials[0] = 1;
        for (int i = 1; i < factorials.length; i++)
            factorials[i] = mul(factorials[i - 1], i);
        invFactorials[factorials.length - 1] = exp(factorials[factorials.length - 1], mod - 2);
        for (int i = invFactorials.length - 2; i >= 0; i--)
            invFactorials[i] = mul(invFactorials[i + 1], i + 1);
    }

    static long nCk(int n, int k) {
        return mul(factorials[n], mul(invFactorials[k], invFactorials[n - k]));
    }

    static void revsort(long[] arr) {
        ArrayList<Long> ls = new ArrayList<Long>();
        for (long x : arr) {
            ls.add(x);
        }
        Collections.sort(ls, Comparator.reverseOrder());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ls.get(i);
        }
    }

    // =======================Useful Functions======================
    static <E> void println(E res) {
        outPrintWriter.println(res);
    }

    static void println() {
        outPrintWriter.println();
    }

    static <E> void print(E res) {
        outPrintWriter.print(res);
    }

    static int[] intArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        return arr;

    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        int[] readintarray(int n) {
            int res[] = new int[n];
            for (int i = 0; i < n; i++)
                res[i] = nextInt();
            return res;
        }

        long[] readlongarray(int n) {
            long res[] = new long[n];
            for (int i = 0; i < n; i++)
                res[i] = nextLong();
            return res;
        }
    }

}
