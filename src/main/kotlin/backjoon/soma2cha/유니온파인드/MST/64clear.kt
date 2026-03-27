// BOJ: https://www.acmicpc.net/problem/1197
import java.util.PriorityQueue

/*
[문제 요약]
- 최소 힙 기반 크루스칼로 MST 비용을 구한다.
- 모든 정점을 연결하지 못하면 -1을 반환한다.
*/

class Solution {

    /**
     * [핵심 아이디어]
     * - 간선을 우선순위 큐(최소 힙)에 넣고 작은 가중치부터 꺼낸다.
     * - 매 간선마다 유니온파인드로 사이클을 막는다.
     *
     * [데이터 구조]
     * - V0 정점수(Int): 정점 개수
     * - V1 부모(IntArray): 유니온파인드 부모
     * - V2 간선힙(PriorityQueue<IntArray>): [u,v,w] 최소 힙
     * - V3 사용간선수(Int): 사용 간선 수
     * - V4 누적가중치(Int): MST 누적 비용
     *
     * [처리 순서]
     * <1> 부모배열, 간선힙 초기화
     * <2> find/union 정의
     * <3> 힙에서 최소 간선을 꺼내 검사
     * <4> 사이클이 아니면 채택
     * <5> n-1개 채택 여부로 결과 결정
     *
     * [주석 템플릿 규칙]
     * - [S*] : 실행 단계
     * - [V*] : 상태 변수
     * - 핵심 분기/확장 라인에 [S4]를 명시
     */
    fun solution(정점개수: Int, 간선목록: Array<IntArray>): Int {
        // [S0] 상태 초기화
        val 정점수 = 정점개수 // [V0]
        val 시작인덱스 = if (간선목록.any { it[0] == 0 || it[1] == 0 }) 0 else 1
        val 부모 = IntArray(정점수 + 시작인덱스) { it } // [V1]
        val 간선힙 = PriorityQueue<IntArray>(compareBy { it[2] }) // [V2]
        for (간선 in 간선목록) 간선힙.add(간선)
        var 사용간선수 = 0 // [V3]
        var 누적가중치 = 0 // [V4]

        // [S1] 유니온파인드 함수
        fun find(정점: Int): Int {
            if (부모[정점] == 정점) return 정점
            부모[정점] = find(부모[정점]) // [S4][V1] 경로 압축
            return 부모[정점]
        }

        fun union(정점A: Int, 정점B: Int) {
            val 대표A = find(정점A)
            val 대표B = find(정점B)
            if (대표A != 대표B) 부모[대표B] = 대표A // [S4][V1]
        }

        // [S2] 크루스칼 루프
        while (간선힙.isNotEmpty() && 사용간선수 < 정점수 - 1) {
            // [S3] 최소 간선 추출
            val 간선 = 간선힙.poll()
            val 시작정점 = 간선[0]
            val 끝정점 = 간선[1]
            val 가중치 = 간선[2]

            // [S4] 핵심 분기
            // 시작정점과 끝정점의 대표가 같으면 이미 연결된 상태다.
            // 여기에 간선을 더하면 사이클이 생기므로 MST에서는 제외한다.
            if (find(시작정점) == find(끝정점)) continue
            union(시작정점, 끝정점)
            사용간선수++ // [S4][V3]
            누적가중치 += 가중치 // [S4][V4]
        }

        // [S5] 연결성 검사
        if (사용간선수 != 정점수 - 1) return -1 // [S5][V3]

        // [S_END] 정답 반환
        return 누적가중치
    }
}
