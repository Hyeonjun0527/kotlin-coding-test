package backjoon.치트시트.실전문제모음.DFS와BFS

// 알고리즘 분류: DFS와BFS

class Solution4 {
    fun solution(lines: Array<IntArray>): Int {

        val 선분들 = lines.map { it.toMutableList() }.toMutableList()
        val 덮인횟수 = MutableList(200) { 0 }

        for (선분 in 선분들) {
            val start = 선분[0]
            val end = 선분[1]

            for (x in start until end) {
                val idx = x + 100
                덮인횟수[idx] = 덮인횟수[idx] + 1
            }
        }

        var 겹친길이 = 0
        for (cnt in 덮인횟수) {
            if (cnt >= 2) 겹친길이++
        }

        return 겹친길이
    }
}
