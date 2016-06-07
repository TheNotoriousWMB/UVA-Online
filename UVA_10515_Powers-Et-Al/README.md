#Description

This program takes any two numbers, m and n, less than 10^101 and finds the digit in the 1's position of the result of m^n.

Through a bit of testing, a pattern can be found.

Given equation result = m^n:

In place of the original m-value, for the sake of finding the 1's digit of result, we only need to use the 1's digit of m.

In place of the original n-value, for the sake of finding the 1's digit of result, we only need to use the 2 least significant digits (if 2 or more exist) which we then mod by 4. In place of n, we can just use the resultant of this % 4, unless that resultant is 0 in which we use 4.

Our final equation will look like
(1's digit of result) = (1's digit of m)^((2 least significant digits of n) % 4)


unless (2 least significant digits of n) % 4 = 0, then
(1's digit of result) = (1's digit of m)^4
