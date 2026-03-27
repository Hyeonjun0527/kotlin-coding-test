// BOJ: https://www.acmicpc.net/problem/14729
import java.util.Locale
import java.util.PriorityQueue

/*
[문제 요약]
- 점수 목록에서 가장 낮은 7개를 선발한다.
- 최종 출력은 오름차순이며 소수점 셋째 자리까지 고정한다.
*/

class Solution {

    /**
     * [핵심 아이디어]
     * - "낮은 점수 7개 유지"를 위해 크기 7의 최대 힙을 사용한다.
     * - 힙의 top은 현재 7개 중 가장 큰 값이므로,
     *   새 점수가 더 작을 때만 교체하면 된다.
     *
     * [데이터 구조]
     * - V0 정답개수(Int): 유지할 최소 점수 개수(7)
     * - V1 선발힙(PriorityQueue<Double>): 크기 제한 최대 힙
     * - V2 점수(Double): 현재 처리 점수
     * - V3 정렬결과(List<Double>): 최종 오름차순 점수 목록
     *
     * [처리 순서]
     * <1> 정답개수와 최대 힙 초기화
     * <2> 점수를 하나씩 순회
     * <3> 힙 크기가 7 미만이면 삽입
     * <4> 힙 크기가 7이면 top과 비교해 필요 시 교체
     * <5> 힙 내용을 오름차순 정렬 후 포맷팅
     *
     * [주석 템플릿 규칙]
     * - [S*] : 실행 단계
     * - [V*] : 상태 변수
     * - 핵심 분기/확장 라인에는 [S4]를 명시
     */
    fun solution(점수목록: DoubleArray): Array<String> {
        // [S0] 상태 초기화
        val 정답개수 = 7 // [V0]
        val 선발힙 = PriorityQueue<Double>(compareByDescending { it }) // [V1]

        // [S1] 점수 순회
        for (점수 in 점수목록) { // [V2]
            // [S2] 힙 크기 검사
            if (선발힙.size < 정답개수) {
                // [S3] 아직 7개 미만이면 삽입
                선발힙.add(점수)
            } else {
                // [S4] 핵심 분기: 더 작은 점수면 교체
                if (선발힙.peek() > 점수) { // [S4][V1][V2]
                    선발힙.poll()
                    선발힙.add(점수)
                }
            }
        }

        // [S5] 정렬 및 포맷팅
        val 정렬결과 = 선발힙.toList().sorted() // [V3]

        // [S_END] 결과 반환
        return 정렬결과.map { String.format(Locale.US, "%.3f", it) }.toTypedArray()
    }
}
