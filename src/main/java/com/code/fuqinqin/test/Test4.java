package com.code.fuqinqin.test;

public class Test4 {
    public static void main(String[] args) {
        int[] nums1 = {1,2,3,4};
        int[] nums2 = {5,6,7};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArrays (int[] nums1, int[] nums2) {
        // write code here
        // write code here
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] nums3 = new int[len1 + len2];
        int n1 = 0,n2 = 0,index = 0;
        while (n1 != len1 && n2 != len2){
            if(nums1[n1] <= nums2[n2]){
                nums3[index++] = nums1[n1++];
            }else{
                nums3[index++] = nums2[n2++];
            }

        }
        if(n1 == len1){
            while (n2 != len2){
                nums3[index++] = nums2[n2++];
            }
        }
        if(n2 == len2){
            while (n1 != len1){
                nums3[index++] = nums1[n1++];
            }
        }
        int len3 = (len1 + len2)/2;
        if((len3 & 1) != 1){
            return nums3[len3];
        }else{
            return (double)(nums3[len3-1] + nums3[len3])/2;
        }
    }
}
