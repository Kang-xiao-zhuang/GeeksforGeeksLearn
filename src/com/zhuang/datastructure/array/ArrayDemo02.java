package com.zhuang.datastructure.array;

import java.util.Arrays;

/**
 * description: ArrayDemo02
 * date: 2022/10/12 9:33
 * author: Zhuang
 * version: 1.0
 */
public class ArrayDemo02 {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        int[] arr2 = new int[]{1, 2, 3, 4, 5};
        //rotate(arr);
        //rotate2(arr2);

        int[] arr3 = {11, 15, 6, 8, 9, 10};
        int x = 16;

        if (pairInSortedRotated(arr3, x)) {
            System.out.print("true");
        } else {
            System.out.print("false");
        }
    }

    /**
     * https://www.geeksforgeeks.org/c-program-cyclically-rotate-array-one/
     *
     * @param arr 数组
     */
    public static void rotate(int[] arr) {
        System.out.println(Arrays.toString(arr));
        System.out.println("rotate method···········");
        int n = arr.length - 1;
        int temp = arr[n];
        for (int i = n; i > 0; i--) {
            // 依次后移
            arr[i] = arr[i - 1];
        }
        arr[0] = temp;
        System.out.println(Arrays.toString(arr));
    }


    public static void rotate2(int[] arr) {
        System.out.println(Arrays.toString(arr));
        System.out.println("rotate method···········");
        int i = 0;
        int j = arr.length - 1;
        while (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * https://www.geeksforgeeks.org/given-a-sorted-and-rotated-array-find-if-there-is-a-pair-with-a-given-sum/
     *
     * @param arr 数组
     * @param x   指定的数
     */
    public static boolean pairInSortedRotated(int[] arr, int x) {
        int n = arr.length;

        int i;
        for (i = 0; i < n - 1; i++)
            if (arr[i] > arr[i + 1]) {
                break;
            }

        int l = (i + 1) % n;

        int r = i;

        while (l != r) {
            if (arr[l] + arr[r] == x) {
                return true;
            }

            if (arr[l] + arr[r] < x) {
                l = (l + 1) % n;
            } else {
                r = (n + r - 1) % n;
            }
        }
        return false;
    }
}
