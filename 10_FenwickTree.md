# Binary Indexed Tree (Fenwick Tree)

A hegh efficient DS for sum of indecies and updating one node value.
The key point is using LSB() to get the responsible range.

Least Siginificant Beat
LSB(x) = x & (-x)
i = 6   = 0110 (binary 6)
-i      = ~0110 + 1
        = 1001 + 1
        = 1010 (binary -6)

 i = 0110
-i = 1010
------------
&    0010  = 2

For any integer i, taking -i isolates the least significant 1-bit while clearing all the others.
Therefore, doing i & -i keeps only the least significant bit.

In Mandarin:
任何整數 i，取 -i，就是把「最低位的 1」保留下來，其它位數清空
所以做and就只有最後一位會保留一致