https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639cbb3b4374f9a7aada8ed0

XOR is a logical bitwise operator that returns 0 (false) if both bits are the same and returns 1 (true) otherwise.
In other words, it only returns 1 if exactly one bit is set to 1 out of the two bits in comparison.
It is surprising to know the approaches that the XOR operator enables us to solve certain problems.

Legend:
a^b - means a XOR b

Properties:
see https://medium.com/@Harshit_Raj_14/useful-properties-of-xor-in-coding-bitwise-manipulation-and-bitmasking-2c332256bd61
1) a^a = 0
2) IF a1^a2^..^an = a AND a1^a2^..^an-1 = b THEN b^an = a
3) a^1s = ~a

Useful expressions:
1) check if i-th bit of num equals 1
    if (((num >> i) & 1) == 1)

Sequence of problems:
1) Single Number (easy) - done
2) Two Single Numbers (medium) - done
3) Complement of Base 10 Number (medium) - done
4) Problem Challenge 1: Flip and Invert an Image (hard) - todo

5) https://leetcode.com/problems/single-number-ii (medium) - done
6) https://leetcode.com/problems/find-the-original-array-of-prefix-xor (medium) - done
7) https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor (medium) - done
8) https://leetcode.com/problems/decode-xored-array (easy) - todo