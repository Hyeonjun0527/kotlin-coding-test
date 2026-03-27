package backjoon.soma2cha.DFSBFS.`20260305`

/**
 * https://www.acmicpc.net/problem/2358 문제를 더 어렵게 변형한 버전
 *
 * 입력: 점 좌표 목록 (각 원소는 [x, y])
 * 출력:
 * - 같은 x를 공유하는 점 쌍(세로선 페어)
 * - 같은 y를 공유하는 점 쌍(가로선 페어)
 * 를 문자열로 만든 목록
 *
 * 예시
 * | 점목록 | return |
 * |---|---|
 * | [[1,1],[1,3],[2,3],[4,3]] | ["1 1 1 1 3","3 1 3 2 3","3 1 3 4 3","3 2 3 4 3"] |
 *
 * 값 설명
 * - 점목록의 4개 점은 (1,1), (1,3), (2,3), (4,3) 이다.
 * - return 문자열 형식은 "기준값 x1 y1 x2 y2" 이다.
 * - "1 1 1 1 3"은 x=1을 공유하는 두 점 (1,1), (1,3)으로 만든 세로선 페어다.
 * - "3 1 3 2 3", "3 1 3 4 3", "3 2 3 4 3"은 y=3을 공유하는 가로선 페어 3개다.
 */
class Solution {
    fun solution(점목록: Array<LongArray>): Array<String> {
        val x별점목록 = mutableMapOf<Long, MutableList<Pair<Long, Long>>>()
        val y별점목록 = mutableMapOf<Long, MutableList<Pair<Long, Long>>>()

        for (점 in 점목록) {
            val x = 점[0]
            val y = 점[1]

            if (x !in x별점목록) x별점목록[x] = mutableListOf()
            x별점목록[x]!!.add(Pair(x, y))

            if (y !in y별점목록) y별점목록[y] = mutableListOf()
            y별점목록[y]!!.add(Pair(x, y))
        }

        val 결과목록 = mutableListOf<String>()

        for ((x값, 점들) in x별점목록) {
            if (점들.size < 2) continue
            for (i in 0 until 점들.size) {
                for (j in i + 1 until 점들.size) {
                    val (x1, y1) = 점들[i]
                    val (x2, y2) = 점들[j]
                    결과목록.add("$x값 $x1 $y1 $x2 $y2")
                }
            }
        }

        for ((y값, 점들) in y별점목록) {
            if (점들.size < 2) continue
            for (i in 0 until 점들.size) {
                for (j in i + 1 until 점들.size) {
                    val (x1, y1) = 점들[i]
                    val (x2, y2) = 점들[j]
                    결과목록.add("$y값 $x1 $y1 $x2 $y2")
                }
            }
        }

        return 결과목록.toTypedArray()
    }
}
