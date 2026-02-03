
fun main() {
    val (r,c) = readln().split(" ").map { it.toInt() }
    val map = Array(r) { readln().toCharArray() }
    val result = IntArray(5)

    for (i in 0 until r - 1) {
        for (j in 0 until c - 1) {
            var 친횟수 = 0
            var 막힘 = false
            val 차 = arrayOf(
                map[i][j],
                map[i+1][j],
                map[i][j+1],
                map[i+1][j+1]
            )
            for (값 in 차) {
                if (값 == '#') {
                    막힘 = true
                    break
                }
                if (값 == 'X') {
                    친횟수++
                }
            }
            if (!막힘) {
                result[친횟수]++
            }
        }
    }

    result.forEach {
        println(it)
    }
}