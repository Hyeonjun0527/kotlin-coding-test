package backjoon.`20260320`

fun main() {
    val t = readln().toInt()
    fun gcd(aa: Long, bb:Long):Long {
        var a = aa
        var b = bb
        while (b != 0L) {
            val 나머지 = a % b
            a = b
            b = 나머지
        }
        return a
    }
    repeat(t) {
        var (a, b) = readln().split(" ").map { it.toLong() }
        // a/b라는 분수임.
        //x1 구하기 올림계산
        while (a != 1L) {
            var x1 = (b + a - 1) / a

            //통분
            var 분자 = a*x1 - b
            var 분모 = b*x1
            val 최대공약수 = gcd(분모,분자)
            분자 /= 최대공약수
            분모 /= 최대공약수
            a = 분자
            b = 분모
        }
        println(b)
    }
}