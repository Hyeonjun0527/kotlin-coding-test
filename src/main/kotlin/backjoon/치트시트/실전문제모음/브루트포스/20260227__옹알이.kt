package backjoon.치트시트.실전문제모음.브루트포스

// 알고리즘 분류: 브루트포스

class Solution {
    fun solution(babbling: Array<String>): Int {
        val 발음목록 = mutableListOf("aya", "ye", "woo", "ma")
        var 정답개수 = 0

        for (단어 in babbling) {
            var 위치 = 0
            val 사용함 = MutableList(발음목록.size) { false } // 각 발음 사용 여부
            var 가능 = true

            while (위치 < 단어.length) {
                var 이번위치에서맞음 = false
                for (인덱스 in 0 until 발음목록.size) {
                    if (사용함[인덱스]) continue

                    val 발음 = 발음목록[인덱스]
                    if (단어.startsWith(발음, 위치)) {
                        사용함[인덱스] = true
                        위치 += 발음.length
                        이번위치에서맞음 = true
                        break
                    }
                }
                if (!이번위치에서맞음) {
                    가능 = false
                    break
                }
            }

            if (가능) 정답개수++
        }

        return 정답개수
    }
}
