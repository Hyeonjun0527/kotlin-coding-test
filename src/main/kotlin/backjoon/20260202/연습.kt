/*
* 출력은 다섯 줄이다. 첫째 줄에는 해빈이가 아무 차도 부수지 않으면서 주차할 수 있는 공간의 개수,
*  둘째 줄은 차 한 대를 부수고 주차할 수 있는 공간의 개수, 셋째 줄은 차 두 대, 넷째 줄은 차 세 대,
*  다섯째 줄은 차 네 대를 부수고 주차할 수 있는 공간의 개수이다.*/

fun main() {
    val (r, c) = readln().split(" ").map { it.toInt() }

    val map = Array(r) {readln().toCharArray()}

    val result = IntArray(5)

    for (i in 0 until r - 1) {
        for (j in 0 until c - 1) {
            var 친횟수 = 0
            var 막힘 = false

            val cells = arrayOf(
                map[i][j],
                map[i + 1][j],
                map[i][j + 1],
                map[i + 1][j + 1]
            )

            for (c in cells) {
                if (c == '#') {
                    막힘 = true
                    break
                }
                if (c == 'X') 친횟수++
            }

            if (!막힘) {
                result[친횟수]++
            }
        }
    }

    for (i in 0..4) {
        println(result[i])
    }
}