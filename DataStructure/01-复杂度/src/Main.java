public class Main {


    public static void main(String[] args) {

        System.out.println("fib1(5) = " + fib1(6));
        System.out.println("fib2(5) = " + fib2(5));

    }

    //斐波那契数 第一种算法 效率比较低 O(2^n)
    public static int fib1(int n) {
        if (n <= 1) return n;
        return fib1(n - 2) + fib1(n - 1);
    }

    //斐波那契数 第二种算法 效率比较高  时间复杂度O(n)
    public static int fib2(int n) {
        if (n <= 1) return n;
        int first = 0;
        int second = 1;
        for (int i = 0; i < n - 1; i++) {
            second += first;
            first = second - first;
        }
        return second;
    }

    public static void test1(int n) {
        // 1
        if (n > 10) {
            System.out.println("n > 10");
        } else if (n > 5) {
            System.out.println("n > 5");
        } else {
            System.out.println("n <= 5");
        }
        // 1 + 4 + 4 + 4
        for (int i = 0; i < 4; i++) {
            System.out.println("test");
        }
        // 复杂度为 O(1)
    }

    public static void test2(int n) {
        // 1 + n + n + n
        for (int i = 0; i < n; i++) {
            System.out.println("test");
        }
        //复杂度为 O(n)
    }
    public static void test3(int n) {
        // 1 + 2n + n * (1 + 3n)
        // 1 + 2n + n + 3n^2
        // 3n^2 + 3n + 1
        // 复杂度为 O(n^2)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("test");
            }
        }
    }

    public static void test4(int n) {
        // 1 + 2n + n * (1 + 45)
        // 1 + 2n + 46n
        // 48n + 1
        //复杂度为 O(n)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 15; j++) {
                System.out.println("test");
            }
        }
    }

    public static void test5(int n) {
        // 8 = 2^3
        // 16 = 2^4

        // 3 = log2(8)
        // 4 = log2(16)

        // 执行次数 = log2(n)
        //复杂度为 O(logn)
        while ((n = n / 2) > 0) {
            System.out.println("test");
        }
    }

    public static void test6(int n) {
        // log5(n)
        //复杂度为 O(logn)
        while ((n = n / 5) > 0) {
            System.out.println("test");
        }
    }

    public static void test7(int n) {
        // 1 + 2*log2(n) + log2(n) * (1 + 3n)
        // 1 + 3*log2(n) + 2 * nlog2(n)
        //复杂度为 O(nlogn)
        for (int i = 1; i < n; i = i * 2) {
            // 1 + 3n
            for (int j = 0; j < n; j++) {
                System.out.println("test");
            }
        }
    }
}
