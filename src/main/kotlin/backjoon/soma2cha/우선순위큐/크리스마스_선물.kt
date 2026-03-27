// BOJ: https://www.acmicpc.net/problem/14235
import java.util.PriorityQueue

/*
[문제 요약]
- 아이들이 선물을 요청하면 가장 가치가 큰 선물을 하나 준다.
- 새로운 선물 목록이 들어오면 선물 창고에 모두 넣는다.
- 요청 시 선물이 없으면 -1을 반환한다.
*/

class Solution {

    /**
     * [핵심 아이디어]
     * - "가장 큰 값"을 빠르게 꺼내기 위해 최대 힙을 사용한다.
     * - 연산은 두 종류다.
     *   - [0] : 선물 요청
     *   - [k, v1, v2, ...] : 선물 k개 추가
     *
     * [데이터 구조]
     * - V0 선물힙(PriorityQueue<Int>): 선물 가치 최대 힙
     * - V1 결과목록(MutableList<Int>): 요청 처리 결과
     * - V2 연산(IntArray): 현재 연산 배열
     * - V3 개수(Int): 연산의 첫 값
     * - V4 선물가치(Int): 추가할 선물 가치
     *
     * [처리 순서]
     * <1> 최대 힙과 결과 목록 초기화
     * <2> 연산 배열을 앞에서부터 순회
     * <3> 요청 연산이면 최대 힙에서 꺼내 결과에 저장
     * <4> 추가 연산이면 선물들을 최대 힙에 삽입
     * <5> 모든 연산 처리 후 결과 반환
     *
     * [주석 템플릿 규칙]
     * - [S*] : 실행 단계
     * - [V*] : 상태 변수
     * - 핵심 분기/확장 라인에는 [S4]를 명시
     */
    fun solution(연산목록: Array<IntArray>): IntArray {
        // [S0] 상태 초기화
        val 선물힙 = PriorityQueue<Int>(compareByDescending { it }) // [V0]
        val 결과목록 = mutableListOf<Int>() // [V1]

        // [S1] 연산 순회
        for (연산 in 연산목록) { // [V2]
            if (연산.isEmpty()) continue
            val 개수 = 연산[0] // [V3]

            // [S2] 요청 연산인지 검사
            if (개수 == 0) {
                // [S3] 요청 처리
                if (선물힙.isNotEmpty()) 결과목록.add(선물힙.poll())
                else 결과목록.add(-1)
            } else {
                // [S4] 핵심 확장: 선물 추가 연산 처리
                val 끝인덱스 = minOf(개수, 연산.size - 1)
                for (인덱스 in 1..끝인덱스) {
                    val 선물가치 = 연산[인덱스] // [V4]
                    선물힙.add(선물가치) // [S4][V0][V4]
                }
            }
        }

        // [S_END] 결과 반환
        return 결과목록.toIntArray()
    }
}
