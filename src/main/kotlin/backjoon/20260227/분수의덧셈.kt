package backjoon.`20260227`

class Solution5 {
    fun solution(numer1: Int, denom1: Int, numer2: Int, denom2: Int): IntArray {
        val 분자Numer = numer1 * denom2 + numer2 * denom1
        val 분모Denom = denom1 * denom2

        fun gcd(a0: Int, b0: Int): Int {
            var a = a0
            var b = b0
            while (b != 0) {
                a = b.also { b = a % b }
            }
            return a
        }

        val 최대공약수Gcd = gcd(분자Numer, 분모Denom)

        val 결과result = mutableListOf<Int>()
        결과result.add(분자Numer / 최대공약수Gcd)
        결과result.add(분모Denom / 최대공약수Gcd)

        return 결과result.toIntArray()
    }
}