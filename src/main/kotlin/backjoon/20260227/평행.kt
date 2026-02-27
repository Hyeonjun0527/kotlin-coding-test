package backjoon.`20260227`// 프로그래머스

class Solution3 {
    fun solution(dots: Array<IntArray>): Int {

        fun 평행인지(a: Int, b: Int, c: Int, d: Int): Boolean {
            val Xa = dots[a][0]; val Ya = dots[a][1]
            val Xb = dots[b][0]; val Yb = dots[b][1]
            val Xc = dots[c][0]; val Yc = dots[c][1]
            val Xd = dots[d][0]; val Yd = dots[d][1]

            val ABx = Xb - Xa
            val ABy = Yb - Ya
            val CDx = Xd - Xc
            val CDy = Yd - Yc

            // (Yb-Ya)/(Xb-Xa) == (Yd-Yc)/(Xd-Xc)
            return ABy * CDx == CDy * ABx
        }

        if (평행인지(0, 1, 2, 3)) return 1
        if (평행인지(0, 2, 1, 3)) return 1
        if (평행인지(0, 3, 1, 2)) return 1
        return 0
    }
}