package backjoon.`20260330`//  행운의 편지
import java.math.BigInteger
fun main(args: Array<String>) {
    //2부터 1000까지의 자연수 a에 대하여, n ≡ a-1 (mod a)인 정수 n
    //n = a-1, 2a-1, 3a-1, 4a-1, ...
    //n을 a로 나눈 나머지가 a - 1이다. n % a = a - 1
    //양변에 1을 더할 수가 있다. 그런데 % 때문에 원형으로 돈다.
    // n + 1 % a = a
    // n + 1 % a = 0
    //n+1을 a로 나눈 나머지가 0이다.
    /*
    *
    * a=2: -1,1,3,5,7,9,11,13,15,...
      a=3: -1,2,5,8,11,14,17,20,23,...
      a=4: -1,3,7,11,15,19,23,27,...
      * 
    * */
    println(-1)

//    gcd(a,b) = gcd(b, a % b)
//    lcm(a,b) = a / gcd(a,b) * b
//    fun 최대공약수(a: Long, b: Long): Long {
//        var x = a
//        var y = b
//        while (y != 0L) {
//            val temp = x % y
//            x = y
//            y = temp
//        }
//        return x
//    }
}
/*
x ≡ y (mod a)는 “x와 y를 a로 나눴을 때 나머지가 같다” 라는뜻이야 ok
=> x > y 라면 [x를 a로 나눈 나머지가 y]이다. 라는 뜻이 되는지는 모르겟어.
근데 이게 왜 “x - y가 a의 배수다”라는뜻이 되는지는 모르겟어

x = a * q1 + r
y = a * q2 + r
x - y = a * (q1 - q2)
x - y가 a의 배수

17 = 5 (mod 12)
17 = 29 (mod 12)

x ≡ y (mod a)를 **“x와 y는 a로 나눴을 때 같은 무리다
x ≡ 2 (mod 3)이면 x는 3으로 나누면 2 남는수다. 2, 5, 8, 11, 14 이런 부류라는뜻.
x ≡ 4 (mod 6)이면 4,10,16,22, ... 이런 수라는 뜻.
* */