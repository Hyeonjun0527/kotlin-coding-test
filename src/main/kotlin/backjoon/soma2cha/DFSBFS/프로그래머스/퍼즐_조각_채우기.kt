package 코테.`12-9`

import java.util.ArrayDeque

/*
[문제 요약(현재 우리가 맞춘 버전)]
- game_board의 0(빈칸) 영역과 table의 1(퍼즐 조각) 영역을 매칭한다.
- 조각은 회전만 가능하고 뒤집기는 불가능하다.
- 규칙에 맞게 채웠을 때 채울 수 있는 칸 수의 최댓값을 구한다.
game_board = [[1,1,0,0,1,0],[0,0,1,0,1,0],[0,1,1,0,0,1],[1,1,0,1,1,1],[1,0,0,0,1,0],[0,1,1,1,0,0]]
table = [[1,0,0,1,1,0],[1,0,1,0,1,0],[0,1,1,0,1,1],[0,0,1,0,0,0],[1,1,0,1,1,0],[0,1,0,0,0,0]]
answer = 14
*/

class Solution {

    /**
     * [핵심 아이디어]
     * - 빈칸(0) 컴포넌트와 조각(1) 컴포넌트를 각각 BFS로 추출해 "좌표 집합 모양"으로 만든다.
     * - 각 모양을 정규화(좌상단 기준 이동 + 정렬)하고 90도 회전 4가지 중 사전순 최소 키를 대표키로 사용한다.
     * - 대표키 기준으로 빈칸/조각 개수를 세고, 같은 키끼리 min(개수)만큼 매칭해 칸 수를 누적한다.
     *
     * [데이터 구조]
     * - V0 보드크기(Int): 정사각 보드 한 변 길이
     * - V1 게임보드(Array<IntArray>): 빈칸(0)/채움(1)
     * - V2 테이블(Array<IntArray>): 빈칸(0)/조각(1)
     * - V3 빈칸모양목록(MutableList<List<칸>>): game_board의 0 컴포넌트들
     * - V4 조각모양목록(MutableList<List<칸>>): table의 1 컴포넌트들
     * - V5 빈칸개수맵(HashMap<String, Int>): 대표키별 빈칸 개수
     * - V6 조각개수맵(HashMap<String, Int>): 대표키별 조각 개수
     * - V7 모양크기맵(HashMap<String, Int>): 대표키별 칸 개수
     * - V8 정답(Int): 최종 채운 칸 수
     * - V9 visited(Array<BooleanArray>): BFS 방문 체크
     * - V10 큐(ArrayDeque<IntArray>): BFS 큐
     * - V11 dx(IntArray): 4방향 행 델타
     * - V12 dy(IntArray): 4방향 열 델타
     *
     * [시뮬레이션(또는 탐색) 순서]
     * <1> 정규화/회전/대표키 함수 준비
     * <2> BFS로 빈칸 모양과 조각 모양을 각각 추출
     * <3> 대표키 기준 개수 맵 구성
     * <4> 같은 키끼리 매칭 가능한 개수만큼 칸 수 누적
     * <5> 누적 결과 반환
     *
     * [주석 템플릿 규칙]
     * - [S*] : 알고리즘 실행 흐름 단계
     * - [V*] : 핵심 상태 변수(데이터 저장소)
     * - 본문 진행 순서: [S0] 초기화 -> [S1..S5] 처리 -> [S_END] 반환
     * - 상태 변수 선언/핵심 갱신 라인에 [V*]를 표기
     * - 알고리즘 핵심 분기/확장 라인에는 [S4]를 표기
     */
    fun solution(game_board: Array<IntArray>, table: Array<IntArray>): Int {
        data class 칸(val 행: Int, val 열: Int)

        // [S0] 초기화: 입력/상태 변수 준비
        val 보드크기 = game_board.size // [V0] 보드 한 변 길이
        val 게임보드 = game_board // [V1] 입력 게임 보드
        val 테이블 = table // [V2] 입력 테이블
        var 정답 = 0 // [V8] 최종 채운 칸 수

        fun 정규화(원본모양: List<칸>): List<칸> {
            val 최소행 = 원본모양.minOf { it.행 }
            val 최소열 = 원본모양.minOf { it.열 }
            return 원본모양
                .map { 칸(it.행 - 최소행, it.열 - 최소열) }
                .sortedWith(compareBy<칸> { it.행 }.thenBy { it.열 })
        }

        fun 모양키(모양: List<칸>): String =
            모양.joinToString(";") { "${it.행},${it.열}" }

        fun 회전90도(모양: List<칸>): List<칸> {
            val 회전모양 = 모양.map { 칸(it.열, -it.행) }
            return 정규화(회전모양)
        }

        fun 대표키(모양: List<칸>): String {
            var 현재모양 = 정규화(모양)
            var 최소키 = 모양키(현재모양)

            repeat(3) {
                현재모양 = 회전90도(현재모양)
                val 후보키 = 모양키(현재모양)
                if (후보키 < 최소키) 최소키 = 후보키 // [S4] 회전 후보 중 사전순 최소 선택
            }
            return 최소키
        }

        fun 컴포넌트모양추출(graph: Array<IntArray>, 목표값: Int): MutableList<List<칸>> {
            val visited = Array(보드크기) { BooleanArray(보드크기) } // [V9] BFS 방문 체크
            val 큐 = ArrayDeque<IntArray>() // [V10] BFS 큐
            val dx = intArrayOf(-1, 1, 0, 0) // [V11] 상하좌우 행 변화
            val dy = intArrayOf(0, 0, -1, 1) // [V12] 상하좌우 열 변화
            val 모양목록 = mutableListOf<List<칸>>()

            fun bfs(시작행: Int, 시작열: Int): List<칸> {
                val 한모양 = mutableListOf<칸>()
                visited[시작행][시작열] = true // [S4][V9]
                큐.addLast(intArrayOf(시작행, 시작열)) // [S4][V10]

                while (큐.isNotEmpty()) {
                    val 현재 = 큐.removeFirst()
                    val x = 현재[0]
                    val y = 현재[1]
                    한모양.add(칸(x, y))

                    for (방향 in 0 until 4) { // [S4] BFS 인접 확장
                        val nx = x + dx[방향]
                        val ny = y + dy[방향]

                        if (nx !in 0 until 보드크기 || ny !in 0 until 보드크기) continue // [S4][V0]
                        if (visited[nx][ny]) continue // [S4][V9]
                        if (graph[nx][ny] != 목표값) continue // [S4]

                        visited[nx][ny] = true // [S4][V9]
                        큐.addLast(intArrayOf(nx, ny)) // [S4][V10]
                    }
                }

                return 정규화(한모양)
            }

            for (시작행 in 0 until 보드크기) {
                for (시작열 in 0 until 보드크기) {
                    if (visited[시작행][시작열]) continue // [S4][V9]
                    if (graph[시작행][시작열] != 목표값) continue // [S4]
                    모양목록.add(bfs(시작행, 시작열)) // [S4]
                }
            }
            return 모양목록
        }

        // [S1] 빈칸 모양(0) / 조각 모양(1) 추출
        val 빈칸모양목록 = 컴포넌트모양추출(게임보드, 0) // [V3] 보드의 빈칸 컴포넌트 목록
        val 조각모양목록 = 컴포넌트모양추출(테이블, 1) // [V4] 테이블의 조각 컴포넌트 목록

        // [S2] 대표키 기준 개수 맵 구축
        val 빈칸개수맵 = HashMap<String, Int>() // [V5] 빈칸 대표키 카운트
        val 조각개수맵 = HashMap<String, Int>() // [V6] 조각 대표키 카운트
        val 모양크기맵 = HashMap<String, Int>() // [V7] 대표키별 모양 크기

        for (빈칸모양 in 빈칸모양목록) {
            val 키 = 대표키(빈칸모양)
            빈칸개수맵[키] = (빈칸개수맵[키] ?: 0) + 1 // [S2][V5]
            모양크기맵.putIfAbsent(키, 빈칸모양.size) // [S2][V7]
        }

        for (조각모양 in 조각모양목록) {
            val 키 = 대표키(조각모양)
            조각개수맵[키] = (조각개수맵[키] ?: 0) + 1 // [S2][V6]
            모양크기맵.putIfAbsent(키, 조각모양.size) // [S2][V7]
        }

        // [S3] 같은 대표키끼리 매칭 가능한 개수만큼 정답 누적
        for ((키, 빈칸개수) in 빈칸개수맵) {
            val 조각개수 = 조각개수맵[키] ?: 0
            val 매칭개수 = minOf(빈칸개수, 조각개수) // [S4] 핵심 매칭 분기 값
            if (매칭개수 == 0) continue // [S4]
            정답 += 매칭개수 * (모양크기맵[키] ?: 0) // [S3][V8][V7]
        }

        // [S5] 누적 정답 확정

        // [S_END] 결과 반환
        return 정답
    }
}
