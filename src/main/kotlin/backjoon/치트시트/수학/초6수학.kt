package backjoon.math//  초6 수학

fun main(args: Array<String>) {
    val t = readln().toInt()

    fun gcd(a: Int, b: Int):Int {
        var x = a
        var y = b

        while (y != 0) {
            val 계산값 = x % y
            x = y
            y = 계산값
        }
        return x
    }

    val sb = StringBuilder()
    repeat(t) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        val gcd = gcd(a,b)
        val lcm = (a/gcd)* b //먼저 나눠서
        sb.append("$lcm $gcd\n")
    }
    println(sb)
}


/*

gcd(x, y) = gcd(y, x % y)
...
...
...
gcd(x, 0) = x

GCD * LCM = x * y
LCM = (x / GCD) * y
왜? => 먼저 나눠서 오버플로우 방지
* */