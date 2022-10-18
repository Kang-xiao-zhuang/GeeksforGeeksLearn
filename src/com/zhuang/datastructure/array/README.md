# Program for array rotation

Given an array of integers **arr[]** of size **N** and an integer, the task is to rotate the array elements to the **left** by **d** positions.

**Examples:** 

> **Input:** 
> arr[] = {1, 2, 3, 4, 5, 6, 7}, d = 2
> **Output:** 3 4 5 6 7 1 2
>
> **Input:** arr[] = {3, 4, 5, 6, 7, 1, 2}, d=2
> **Output:** 5 6 7 1 2 3 4

**Approach 1 (Using temp array):** This problem can be solved using the below idea:

After rotating **d** positions to the left, the first **d** elements become the last **d** elements of the array

- First store the elements from index **d** to **N-1** into the temp array.
- Then store the first **d** elements of the original array into the temp array.
- Copy back the elements of the temp array into the original array

Suppose the give array is **arr[] = [1, 2, 3, 4, 5, 6, 7]**, **d = 2**.

**First Step:**
  => Store the elements from 2nd index to the last.
  => **temp[] = [3, 4, 5, 6, 7]**

**Second Step:** 
  => Now store the first 2 elements into the temp[] array.
  => **temp[] = [3, 4, 5, 6, 7, 1, 2]**

**Third Steps:**
  => Copy the elements of the temp[] array into the original array.
  => **arr[] = temp[]** So **arr[] = [3, 4, 5, 6, 7, 1, 2]**

```java
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
        // 索引d后面的数添加到temp数组中
        // temp[] 3 4 5 6 7
        for (int i = d; i < arr.length; i++) {
            temp[k] = arr[i];
            k++;
        }
        // temp[] 3 4 5 6 7 1 2
        for (int i = 0; i < d; i++) {
            temp[k] = arr[i];
            k++;
        }
        // 数组复制
        arr = Arrays.copyOf(temp, arr.length);
        printThearray(arr);
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
```

**Time complexity:** O(N) 
**Auxiliary Space:** O(N)

****

**Approach 2 (Rotate one by one):** This problem can be solved using the below idea:

> - At each iteration, shift the elements by one position to the left circularly (i.e., first element becomes the last).
> - Perform this operation **d** times to rotate the elements to the left by **d** position.

**Illustration:**

> Let us take **arr[] = [1, 2, 3, 4, 5, 6, 7]**, **d = 2**.
>
> **First Step:**
>     => Rotate to left by one position.
>     => **arr[] = {2, 3, 4, 5, 6, 7, 1}**
>
> **Second Step:**
>     => Rotate again to left by one position
>     => **arr[] = {3, 4, 5, 6, 7, 1, 2}**
>
> Rotation is done by 2 times.
> So the array becomes **arr[] = {3, 4, 5, 6, 7, 1, 2}**

- Rotate the array to left by one position. For that do the following:
  - Store the first element of the array in a temporary variable.
  - Shift the rest of the elements in the original array by one place.
  - Update the last index of the array with the temporary variable.
- Repeat the above steps for the number of left rotations required.

```java
public static void rotate2(int[] arr, int d) {
        int n = arr.length;
        // 定义指针
        int point = 1;
        // 循环几次 这里d为2 就循环2次 往后依次移动两次
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
```

**Time Complexity:** O(N * d)
**Auxiliary Space:** O(1)