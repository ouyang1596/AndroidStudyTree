package com.ouyang.lib_common;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class UnitTest {
    @Test
    public void addition_isCorrect() {
//        volatileTest()
//        String[] a = {"eat", "tea", "tan", "ate", "nat", "bat"};
//        System.out.println(groupAnagrams(a).toString());
        System.out.println(containsDuplicate(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}));
    }

    /**
     * 字符异位分组
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> map = new HashMap();

        for (int a = 0; a < strs.length; a++) {
            int[] counts = new int[26];
            String str = strs[a];
            for (int b = 0; b < str.length(); b++) {
                counts[str.charAt(b) - 'a']++;
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int c = 0; c < counts.length; c++) {
                if (counts[c] != 0) {
                    stringBuilder.append((char) (c + 'a'));
                    stringBuilder.append(counts[c]);
                }
            }
            String key = stringBuilder.toString();
            ArrayList<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
//        for (String ke : map.keySet()) {
//            System.out.println("key= "+ ke + " and value= " + map.get(ke).toString());
//        }
        return new ArrayList<List<String>>(map.values());
    }

    public boolean containsDuplicate(int[] nums) {
        HashMap map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == null) {
                map.put(nums[i], 0);
            } else {
                return true;
            }
        }
        return false;
    }

    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int next = 0;
        for (int i = 0; i < nums.length; i++) {
            next = Math.max(next + nums[i], nums[i]);
            max = Math.max(max, next);
        }
        return max;
    }

    public int[] twoSum(int[] nums, int target) {
        int[] a = new int[2];
        for (int i = 0; i < nums.length; i++) {
            a[0] = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    a[1] = j;
                    return a;
                }
            }
        }
        return a;
    }

    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        int k = -1;
        int n = 0;
        HashSet set = new HashSet();
        for (int i = 0; i < s.length(); i++) {
            if (i != 0) {
                set.remove(s.charAt(i - 1));
            }
            for (int j = n; j < s.length(); j++) {
                if (!set.contains(s.charAt(j))) {
                    set.add(s.charAt(j));
                } else {
                    n = j;
                    break;
                }
            }
            max = Math.max(max, set.size());
        }
        return max;
    }

    public boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        for (int i = 0; i < s.length(); i++) {
            int b = s.length() - 1 - i;
            if (i >= b) {
                break;
            } else {
                if (s.charAt(i) != s.charAt(b)) {
                    return false;
                }
            }
        }
        return true;
    }
}
