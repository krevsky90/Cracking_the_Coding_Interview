package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.easy_arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * https://igotanoffer.com/blogs/tech/array-interview-questions
 * https://codeburst.io/understanding-the-two-sum-problem-9a6428083a7f
 * OR
 * 1. Two Sum (easy)
 * https://leetcode.com/problems/two-sum
 *
 * #Company: Adobe Aetion Affirm Airbnb Alibaba Amazon Apple Audible Baidu BlackRock Bloomberg Booking.com Box ByteDance Cisco Citadel Citrix Deutsche Bank DiDi Drawbridge Dropbox eBay EMC Expedia Facebook FactSet GE Digital GoDaddy Goldman Sachs Google Groupon Huawei IBM Indeed Intel Intuit caMorgan LinkedIn Lyft MAQ Software Mathworks Microsoft Morgan Stanley NetEase Nvidia Oracle Paypal Qualcomm Quora Radius Roblox Salesforce Samsung SAP ServiceNow Snapchat Splunk Spotify Tableau Tencent Twilio Twitter Two Sigma Uber Valve Visa VMware Walmart Labs Wayfair Wish Works Applications Yahoo Yandex Yelp Zillow Zoho
 *
 * <p>
 * Problem Description: Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * <p>
 * It is important to note that you CANNOT use the same element in the array twice, but you can assume that there will only be ONE solution for each test case.
 *
 * SOLUTION: https://www.youtube.com/watch?v=Aql6zHkONek
 */
public class Problem1_4_TwoSums {
    public static void main(String[] args) {
        int[] arr = {1, 4, 10, -3};
        int target = 14;
        int[] result = twoSums(arr, target);
        System.out.println("");
    }

    public static int[] twoSums(int[] arr, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();    //arr[i] -> i
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(target - arr[i])) {
                return new int[]{i, map.get(target - arr[i])};
            }
            map.put(arr[i], i);
        }

        return result;
    }
}
