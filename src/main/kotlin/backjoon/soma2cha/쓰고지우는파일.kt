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
 * 5 4
 * 5 5
 * 5 8
 *
 * 같은 x를 공유하는 점 쌍 어케찾냐. 맵 만들어야지 x값,점들로
 */
class Solution100 {
    fun solution(점목록: Array<LongArray>): Array<String> {
        val x값별점 = HashMap<Long,MutableList<Pair<Long,Long>>>()//x값 점리스트
        val y값별점 = HashMap<Long,MutableList<Pair<Long,Long>>>()//y값 점리스트

        for (점 in 점목록) {
            val x = 점[0]
            val y = 점[1]
            if (x !in x값별점) x값별점[x] = mutableListOf()
            x값별점[x]!!.add(x to y)

            if (y !in y값별점) y값별점[y] = mutableListOf()
            y값별점[y]!!.add(x to y)
        }

        val 결과 = mutableListOf<String>()
        for ( (x값,점리스트) in x값별점) {
            if (점리스트.size < 2) continue
            for (i in 0 until 점리스트.size) {
                for (j in i + 1 until 점리스트.size) {
                    val (x1,y1) = 점리스트[i]
                    val (x2,y2) = 점리스트[j]
                    결과.add("$x값 $x1 $y1 $x2 $y2")
                }
            }
            print("$x값 ")
        }

        return 결과.toTypedArray()
    }
}
