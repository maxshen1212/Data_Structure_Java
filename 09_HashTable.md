# Hash Table

Use for frequency record!
Collision? Seperat Chaining/Open Addressing

##  Seperat Chaining
array+linked list
k = key
H(k)
arr[0] = node
arr[1] = node
arr[2] = node -> node -> node
aee[3] = node -> node -> node
aee[4] = node -> node -> node
aee[3] = node

## Open Addressing
array store key-value pair into index which is hashed by hash function H(k), but when it comes to a collision, use a probing function P(x)=ax+b to recaculate the new index
* Infinite cycle loop will be a crucial issue, but if GCD(a,n)=1, no infinite cycle. In addition, P(x)=1x is a common choice because GCD(1,n) is always 1.
  Therefore, Hashing & Porbing functions used with open addressing are very specific!!!!!


arr[0] = (k,v)
arr[1] = (k,v)
arr[2] = (k,v)
k = key, n = capacity ,x = 0 (if collision: x++)
index = H(k)+P(x) mod n

## Linear Probing
P(x) = 1*x
H(k,x) = (H​(k)+P(x)) mod n = index

## Quadratic Probing
P(x) = x*x
H(k,x) = (H​(k)+P(x)) mod n = index

## Double Hashing
P(x) = x*H₂(x) = x*δ
δ = H₂(k) mod n (GCD(δ,n)=1)
H(k,x) = (H₁​(k)+P(x)) mod n = index

2 ways can satisfy full traversal
  1. n is always a prime number.
  2. n is even number and δ is old