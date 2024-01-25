
//Max/Min Segment Tree(Query/Update):-
import java.util.*;

public class Max {

    static int tree[];

    public static void init(int n) {
        tree = new int[4 * n];
    }

    public static void buildST(int i, int si, int sj, int arr[]) { // O(n)
        if (si == sj) {
            tree[i] = arr[si];
            return;
        }
        int mid = (si + sj) / 2;
        buildST(2 * i + 1, si, mid, arr);
        buildST(2 * i + 2, mid + 1, sj, arr);

        tree[i] = Math.max(tree[2 * i + 1], tree[2 * i + 2]);
    }

    public static int getMax(int arr[], int qi, int qj) {
        int n = arr.length;
        return getMaxUtil(0, 0, n - 1, qi, qj);
    }

    public static int getMaxUtil(int i, int si, int sj, int qi, int qj) { // O(logn)
        if (si > qj || sj < qi) { // no overlap
            return Integer.MIN_VALUE;
        } else if (si >= qi && sj <= qj) {
            return tree[i];
        } else {
            int mid = (si + sj) / 2;
            int leftAns = getMaxUtil(2 * i + 1, si, mid, qi, qj);
            int rigthAns = getMaxUtil(2 * i + 2, mid + 1, sj, qi, qj);
            return Math.max(leftAns, rigthAns);
        }
    }

    public static void update(int arr[], int idx, int newVal) {
        arr[idx] = newVal;
        int n = arr.length;
        updateUtil(0, 0, n - 1, idx, newVal);
    }

    public static void updateUtil(int i, int si, int sj, int idx, int newVal) { // O(logn)
        if (idx < si || idx > sj) {
            return;
        }

        if (si == sj) {
            tree[i] = newVal;
        }

        if (si != sj) {
            tree[i] = Math.max(tree[i], newVal);
            if (si != sj) {
                int mid = (si + sj) / 2;
                updateUtil(2 * i + 1, si, mid, idx, newVal); // left
                updateUtil(2 * i + 2, mid + 1, sj, idx, newVal); // right
            }
        }
    }

    public static void main(String[] args) {
        int arr[] = { 6, 8, -1, 2, 17, 1, 3, 2, 4 };
        int n = arr.length;
        init(n);
        buildST(0, 0, n - 1, arr);
        int max = getMax(arr, 2, 5);
        System.out.println(max); // 7

        update(arr, 2, 20);

        max = getMax(arr, 2, 5);
        System.out.println(max); // 20
    }
}

/*
 * Output:-
 * 17
 * 20
 */
