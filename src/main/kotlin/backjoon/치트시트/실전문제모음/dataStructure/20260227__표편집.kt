package backjoon.치트시트.실전문제모음.dataStructure

// 알고리즘 분류: dataStructure

class Solution8 {
    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        val 위 = MutableList(n) { it - 1 }
        val 아래 = MutableList(n) { if (it == n - 1) -1 else it + 1 }
        val 상태 = MutableList(n) { 'O' }

        val 삭제스택 = ArrayDeque<IntArray>() // (행, 위, 아래)
        var 커서 = k

        fun 위로이동(칸수: Int) {
            var 남은 = 칸수
            while (남은 > 0) {
                커서 = 위[커서]
                남은--
            }
        }

        fun 아래로이동(칸수: Int) {
            var 남은 = 칸수
            while (남은 > 0) {
                커서 = 아래[커서]
                남은--
            }
        }

        fun 삭제() {
            상태[커서] = 'X'

            val 위행 = 위[커서]
            val 아래행 = 아래[커서]
            삭제스택.addLast(intArrayOf(커서, 위행, 아래행))

            if (위행 != -1) 아래[위행] = 아래행
            if (아래행 != -1) 위[아래행] = 위행

            커서 = if (아래행 != -1) 아래행 else 위행
        }

        fun 복구() {
            val 기록 = 삭제스택.removeLast()
            val 복구행 = 기록[0]
            val 위행 = 기록[1]
            val 아래행 = 기록[2]

            상태[복구행] = 'O'

            if (위행 != -1) 아래[위행] = 복구행
            if (아래행 != -1) 위[아래행] = 복구행
            위[복구행] = 위행
            아래[복구행] = 아래행
        }

        for (명령 in cmd) {
            when (명령[0]) {
                'U' -> 위로이동(명령.substring(2).toInt())
                'D' -> 아래로이동(명령.substring(2).toInt())
                'C' -> 삭제()
                'Z' -> 복구()
            }
        }

        return 상태.joinToString("")
    }
}
