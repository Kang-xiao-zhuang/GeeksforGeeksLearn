package com.zhuang.datastructure.array;

import java.util.Arrays;

/**
 * description: ArrayDemo01
 * date: 2022/10/9 11:19
 * author: Zhuang
 * version: 1.0
 */
public class ArrayDemo01 {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7};
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};
        int[] arr3 = {1, 2, 3, 4, 5, 6, 7};
        int[] arr4 = {1, 2, 3, 4, 5, 6, 7};
        int[] arr5 = {1, 2, 3, 4, 5, 6, 7};
        rotate(arr1, 2);
        rotate2(arr2, 2);
        leftRotate(arr3, 2);
        leftRotate2(arr4, 2);
        leftRotate(arr5, 2, 7);
    }

    /**
     * https://www.geeksforgeeks.org/array-rotation/
     *
     * @param arr 数组
     * @param d   旋转的位置
     */
    public static void rotate(int[] arr, int d) {
        // 定义临时数组
        int[] temp = new int[arr.length];
        // 跟踪temp数组的索引
        int k = 0;
        for (int i = d; i < arr.length; i++) {
            temp[k] = arr[i];
            k++;
        }
        for (int i = 0; i < d; i++) {
            temp[k] = arr[i];
            k++;
        }
        // 数组复制
        arr = Arrays.copyOf(temp, arr.length);
        printThearray(arr);
    }


    public static void rotate2(int[] arr, int d) {
        int n = arr.length;
        // 定义指针
        int point = 1;
        while (point <= d) {
            // 把第一个数拿到 准备赋值给尾部的值
            int last = arr[0];
            for (int i = 0; i < n - 1; i++) {
                arr[i] = arr[i + 1];
            }
            arr[n - 1] = last;
            point++;
        }
        printThearray(arr);
    }

    public static void leftRotate(int[] arr, int d) {
        int n = arr.length;
        d = d % n;
        int i, j, k, temp;
        int g_c_d = gcd(d, n);
        for (i = 0; i < g_c_d; i++) {
            temp = arr[i];
            j = i;
            while (true) {
                k = j + d;
                if (k >= n)
                    k = k - n;
                if (k == i)
                    break;
                arr[j] = arr[k];
                j = k;
            }
            arr[j] = temp;
        }
        printThearray(arr);
    }

    /**
     * 欧基里德法
     * a和b存在最大公约数x，那么就有整数n，m，使得a=nx,b=mx。
     * 让a-b
     * a-b
     * =nx-mx
     * =(n-m)x,
     * 因为n，m都为整数，所以（n-m）也一定是整数。
     * 结论：两个数的最大公约数，一定也是两数之差的约数（取余同理）。
     */
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    public static void leftRotate(int[] arr, int d, int n) {
        leftRotateRec(arr, 0, d, n);
        printThearray(arr);
    }


    public static void leftRotateRec(int[] arr, int i, int d, int n) {
        if (d == 0 || d == n) {
            return;
        }
        /*If number of elements to be rotated
        is exactly half of array size */
        if (n - d == d) {
            swap(arr, i, n - d + i, d);
            return;
        }
        /* If A is shorter*/
        if (d < n - d) {
            swap(arr, i, n - d + i, d);
            leftRotateRec(arr, i, d, n - d);
        } else /* If B is shorter*/ {
            swap(arr, i, d, n - d);
            leftRotateRec(arr, n - d + i, 2 * d - n, d); /*This is tricky*/
        }
    }

    /**
     * 交换
     *
     * @param arr 数组
     * @param a   索引
     * @param b   索引
     * @param d   深度
     */
    public static void swap(int[] arr, int a, int b, int d) {
        int temp = 0;
        for (int i = 0; i < d; i++) {
            temp = arr[a + i];
            arr[a + i] = arr[b + i];
            arr[b + i] = temp;
        }
    }

    public static void leftRotate2(int[] arr, int d) {
        if (d == 0) {
            return;
        }
        int n = arr.length;
        // in case the rotating factor is
        // greater than array length
        d = d % n;
        reverseArray(arr, 0, d - 1);
        reverseArray(arr, d, n - 1);
        reverseArray(arr, 0, n - 1);
        printThearray(arr);
    }


    public static void reverseArray(int[] arr, int start, int end) {
        int temp;
        while (start < end) {
            temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }


    /**
     * 打印数组
     *
     * @param arr 数组
     */
    private static void printThearray(int[] arr) {
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }
}
