# Suffix Array

Longest Common Substring:
Suffix Array + LCP Array+ Sliding Window

Suffix Array:
Any substring must appear as a prefix of some suffix
In Mandarin: 任意子字串一定存在於某個後綴的前綴
SA is an array contains all the lexicographically sorted suffixes of a string
The actual "suffix array" is the array of sorted indices.
ex. camel = ["camel","amel","mel","el","l"],
SA = [1,0,3,4,2] => ["amel","camel","el","l","mel"]
only indices!!!

LCP Array:
"ABABBAB" = "ABABBAB","BABBAB","ABBAB","BBAB","BAB","AB","B"
SA = [5,0,2,6,4,1,3] = ["AB","ABABBAB","ABBAB","B","BAB","BABBAB","BBAB"]
LCP = [0,2,2,0,1,3,1] the first one will be set as 0
the number of substrings is n*(n+1)/2 - LCP.prefixSum(n) -> LCP[0]+...+LCP[n]

Longest Reapeated Substring
LRS is the maximum value of LCP array