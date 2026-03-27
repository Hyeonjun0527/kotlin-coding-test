package 코테.`12-9`

import java.util.ArrayDeque

/*
[문제 요약(현재 우리가 맞춘 버전)]
- 모든 항공권을 정확히 1번씩 사용해 경로를 만든다.
- 시작 공항은 항상 "ICN"이다.
- 가능한 경로가 여러 개면 알파벳 순으로 가장 앞선 경로를 반환한다.
- 결과 경로 길이는 항공권 수 + 1이다.
tickets = [["ICN","JFK"], ["HND","IAD"], ["JFK","HND"]]
answer = ["ICN","JFK","HND","IAD"]
*/

class Solution {

    /**
     * [핵심 아이디어]
     * - 이 문제는 "모든 간선을 한 번씩 쓰는 경로"이므로 Euler 경로 구성 문제로 볼 수 있다.
     * - 반복형 Hierholzer 알고리즘을 사용하면 모든 티켓을 정확히 1번씩 사용할 수 있다.
     * - 각 출발지의 도착지를 내림차순 정렬해 두고 뒤에서 꺼내면
     *   실제 탐색은 사전순으로 가장 작은 도착지를 먼저 선택하게 된다.
     *
     * [데이터 구조]
     * - V0 티켓목록(Array<Array<String>>): 입력 항공권
     * - V1 graph(HashMap<String, MutableList<String>>): 출발 공항별 도착 공항 목록
     * - V2 출발공항(String): 티켓 처리 시 출발 공항
     * - V3 도착목록(MutableList<String>): 현재 공항의 남은 도착 후보 목록
     * - V4 경로스택(ArrayDeque<String>): Hierholzer 진행 스택
     * - V5 역순경로(ArrayList<String>): 막다른 길에서 확정되는 역순 결과
     * - V6 현재공항(String): 스택 top 공항
     * - V7 다음공항(String): 다음으로 확장할 공항
     * - V8 정답(Array<String>): 최종 반환 경로
     *
     * [시뮬레이션(또는 탐색) 순서]
     * <1> 티켓으로 graph 구성
     * <2> 각 출발지 도착목록을 내림차순 정렬
     * <3> dfs("ICN") 실행
     * <4> 갈 곳이 있으면 다음공항으로 확장, 없으면 역순경로에 확정
     * <5> 역순경로를 뒤집어 정답 생성
     *
     * [주석 템플릿 규칙]
     * - [S*] : 알고리즘 실행 흐름 단계
     * - [V*] : 핵심 상태 변수(데이터 저장소)
     * - 본문 진행 순서: [S0] 초기화 -> [S1..S5] 탐색 처리 -> [S_END] 반환
     * - 상태 변수 선언/핵심 갱신 라인에 [V*]를 표기
     * - 알고리즘 핵심 분기/확장 라인에는 [S4]를 표기
     */
    fun solution(tickets: Array<Array<String>>): Array<String> {
        // [S0] 초기화: 입력/graph/스택/결과 저장소 준비
        val 티켓목록 = tickets // [V0] 입력 티켓 목록
        val graph = HashMap<String, MutableList<String>>() // [V1] 출발지 -> 도착지들
        val 경로스택 = ArrayDeque<String>() // [V4] Hierholzer 진행 스택
        val 역순경로 = ArrayList<String>(티켓목록.size + 1) // [V5] 역순으로 쌓이는 최종 경로

        // [S1] graph 구성
        for (티켓 in 티켓목록) {
            val 출발공항 = 티켓[0] // [V2] 현재 티켓의 출발 공항
            val 도착공항 = 티켓[1]
            graph.getOrPut(출발공항) { mutableListOf() }.add(도착공항) // [S1][V1][V2]
        }

        // [S2] 사전순 우선 처리를 위해 도착목록을 내림차순 정렬
        for (도착목록 in graph.values) { // [V3] 현재 출발지의 도착 목록
            도착목록.sortDescending() // [S2][V3]
        }

        // [S3] dfs() 함수 정의
        fun dfs(시작공항: String) {
            경로스택.addLast(시작공항) // [S3][V4]

            // [S4] 반복형 Hierholzer: 확장 또는 경로 확정
            while (경로스택.isNotEmpty()) { // [S4][V4]
                val 현재공항 = 경로스택.last() // [V6] 현재 공항
                val 도착목록 = graph[현재공항] // [V3] 현재 공항의 남은 티켓 목록

                if (도착목록 == null || 도착목록.isEmpty()) { // [S4][V3]
                    역순경로.add(경로스택.removeLast()) // [S4][V5][V4]
                    continue
                }

                val 다음공항 = 도착목록.removeAt(도착목록.lastIndex) // [S4][V7][V3]
                경로스택.addLast(다음공항) // [S4][V4][V7]
            }
        }
        dfs("ICN") // [S3]

        // [S5] 역순경로를 정방향으로 뒤집어 정답 완성
        val 정답 = 역순경로.asReversed().toTypedArray() // [S5][V8][V5]

        // [S_END] 결과 반환
        return 정답
    }
}
