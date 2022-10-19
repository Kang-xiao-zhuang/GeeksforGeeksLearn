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

# Reversal algorithm for Array rotation

Given an [array](https://www.geeksforgeeks.org/introduction-to-arrays/) **arr[]** of size **N**, the task is to rotate the array by **d** position to the left.

**Examples:** 

> **Input:** arr[] = {1, 2, 3, 4, 5, 6, 7}, d = 2
> **Output:** 3, 4, 5, 6, 7, 1, 2
> **Explanation:** If the array is rotated by 1 position to the left, 
> it becomes {2, 3, 4, 5, 6, 7, 1}.
> When it is rotated further by 1 position,
> it becomes: {3, 4, 5, 6, 7, 1, 2}
>
> **Input:** arr[] = {1, 6, 7, 8}, d = 3
> **Output:** 8, 1, 6, 7

**Approach:** We have already discussed several methods in [**this**](https://www.geeksforgeeks.org/array-rotation/) post. The ways discussed there are:

- Using another temporary array.
- Rotating one by one.
- Using a juggling algorithm.

**Another Approach (The Reversal Algorithm):** Here we will be discussing another method which uses the concept of reversing a part of array. The intuition behind the idea is mentioned below:

**Intuition:**

> If we observe closely, we can see that a group of array elements is changing its position. For example see the following array:
> **arr[] = {1, 2, 3, 4, 5, 6, 7}** and **d = 2**. The rotated array is **{3, 4, 5, 6, 7, 1, 2}**
>
> The group having the first two elements is moving to the end of the array. This is like reversing the array.
>
> - But the issue is that if we only reverse the array, it becomes {7, 6, 5, 4, 3, 2, 1}. 
> - After rotation the elements in the chunks having the first 5 elements **{7, 6, 5, 4, 3}** and the last 2 elements **{2, 1}** should be in the actual order as of the initial array [i.e., **{3, 4, 5, 6, 7} and {1, 2}**]but here it gets reversed. 
> - So if those blocks are reversed again we get the desired rotated array.
>
> So the sequence of operations is:
>
> - Reverse the whole array 
> - Then reverse the last ‘d’ elements and 
> - Then reverse the first (N-d) elements.
>
> As we are performing reverse operations it is also similar to the following sequence:
>
> - Reverse the first ‘d’ elements
> - Reverse last (N-d) elements
> - Reverse the whole array.

**Pseudocode:** 

> Algorithm reverse(arr, start, end):
>   mid = (start + end)/2
>   loop from i = start to mid:
>     swap (arr[i], arr[end-(mid-i+1)])
>
> Algorithm rotate(arr, d, N):
>   reverse(arr, 1, d) ;
>   reverse(arr, d + 1, N);
>   reverse(arr, 1, N);

**Illustration:**

Follow the illustration below to for  better understanding of the algorithm and intuition:

> For example take the array **arr[] = {1, 2, 3, 4, 5, 6, 7}** and **d = 2**.
>
> ![在这里插入图片描述](https://img-blog.csdnimg.cn/9bb5943ccba5434bb1c2c12aff1ef693.png)
>
> *The rotated array will look like:*
>
> ![在这里插入图片描述](https://img-blog.csdnimg.cn/f2ac6d944ac64dd881c7af10f2010809.png)
>
> **1st Step:** *Consider the array as a combination of two blocks. One containing the first two elements and the other containing the remaining elements as shown above.*
>
> ![在这里插入图片描述](https://img-blog.csdnimg.cn/c80a40dfb5374691bff082ae2071fbe1.png)
>
> **2nd Step:** Now reverse the first **d** elements. It becomes as shown in the image
>
> ![在这里插入图片描述](https://img-blog.csdnimg.cn/5ac3591d55d54168a9b97d7669a192fa.png)
>
> **3rd Step:** *Now reverse the last* **(N-d)** *elements. It become as it is shown in the below image:*
>
> ![在这里插入图片描述](https://img-blog.csdnimg.cn/1778a4a697ce48dda696abf25c3ab303.png)
>
> **4th Step:** *Now the array is the exact reversed form of how it should be if left shifted* **d** *times. So reverse the whole array and you will get the required rotated array.*
>
> ![在这里插入图片描述](https://img-blog.csdnimg.cn/b51e41e8de804dbd9cbb6ead2bb6d98e.png)
>
> *See that the array is now the same as the rotated array.*

```java
	/**
     * https://www.geeksforgeeks.org/program-for-array-rotation-continued-reversal-algorithm/
     *
     * @param arr 数组
     * @param d   开始旋转的索引
     */
    public static void leftRotate(int[] arr, int d) {
        if (d == 0) {
            return;
        }
        int n = arr.length;
        // in case the rotating factor is
        // greater than array length、
        // 防止旋转大于数组的长度
        d = d % n;
        //  0-d 左边旋转
        reverseArray(arr, 0, d - 1);
        // d-n-1 右边旋转
        reverseArray(arr, d, n - 1);
        // 整体旋转
        reverseArray(arr, 0, n - 1);
        printThearray(arr);
    }

    /**
     * @param arr   数组
     * @param start 开始索引
     * @param end   结束索引
     */
    public static void reverseArray(int[] arr, int start, int end) {
        int temp;
        // 条件是 开始索引小于结束索引
        while (start < end) {
            // temp存储 交换值
            temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            // 索引改变
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
```

