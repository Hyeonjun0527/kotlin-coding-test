package 코테.`12-9`

import java.util.ArrayDeque

/*
[문제 요약(현재 우리가 맞춘 버전)]
- maps[x][y]가 1이면 이동 가능, 0이면 벽(이동 불가)이다.
- 시작 위치는 (0,0), 도착 위치는 (n-1,m-1)이다.
- 상하좌우 4방향으로 한 칸씩만 이동 가능하다.
- 도착까지 지나가야 하는 칸 수의 최솟값을 구한다.
- 도착 불가능하면 -1을 반환한다.
maps = [[1,0,1,1,1],[1,0,1,0,1],[1,0,1,1,1],[1,1,1,0,1],[0,0,0,0,1]]
answer = 11
*/

class Solution {

    /**
     * [핵심 아이디어]
     * - "최단 칸 수" 문제는 간선 가중치가 모두 동일하므로 BFS가 정답이다.
     * - 시작 칸의 거리를 1로 두고, 인접 칸으로 갈 때마다 +1 누적하면
     *   도착 칸 거리값이 곧 "지나간 칸 수의 최솟값"이 된다.
     *
     * [데이터 구조]
     * - V0 xCnt(Int): 맵의 행 개수
     * - V1 yCnt(Int): 맵의 열 개수
     * - V2 graph(Array<IntArray>): 입력 맵
     * - V3 거리배열(Array<IntArray>): 각 칸의 최소 도달 칸 수(0은 미방문)
     * - V4 큐(ArrayDeque<IntArray>): BFS 큐
     * - V5 x(Int): 큐에서 꺼낸 현재 칸의 행
     * - V6 y(Int): 큐에서 꺼낸 현재 칸의 열
     * - V7 dx(IntArray): 4방향 행 델타
     * - V8 dy(IntArray): 4방향 열 델타
     * - V9 정답(Int): 최종 반환값(기본 -1)
     *
     * [시뮬레이션(또는 탐색) 순서]
     * <1> 입력/상태 변수 초기화
     * <2> 시작/도착 칸이 막혀 있는지 먼저 검사
     * <3> 시작 칸을 거리 1로 설정하고 큐에 삽입
     * <4> BFS로 인접 칸을 확장하며 방문거리 갱신
     * <5> BFS 종료 후 도착 칸 거리 존재 여부로 정답 결정
     *
     * [주석 템플릿 규칙]
     * - [S*] : 알고리즘 실행 흐름 단계
     * - [V*] : 핵심 상태 변수(데이터 저장소)
     * - 본문 진행 순서: [S0] 초기화 -> [S1..S5] BFS 처리 -> [S_END] 반환
     * - 상태 변수 선언/핵심 갱신 라인에 [V*]를 표기
     * - 알고리즘 핵심 분기/확장 라인에는 [S4]를 표기
     */
    fun solution(maps: Array<IntArray>): Int {
        // [S0] 초기화: 입력/상태 변수 준비
        val xCnt = maps.size // [V0] 맵 행 개수
        val yCnt = maps[0].size // [V1] 맵 열 개수
        val graph = maps // [V2] 원본 맵
        val 거리배열 = Array(xCnt) { IntArray(yCnt) } // [V3] 최소 도달 칸 수
        val dx = intArrayOf(-1, 1, 0, 0) // [V7] 상하좌우 행 변화
        val dy = intArrayOf(0, 0, -1, 1) // [V8] 상하좌우 열 변화
        var 정답 = -1 // [V9] 기본값(도달 불가)

        // [S1] 시작/도착 칸 유효성 검사
        if (graph[0][0] == 0 || graph[xCnt - 1][yCnt - 1] == 0) return 정답 // [S1][V2][V9]

        // [S2] bfs() 함수 정의
        fun bfs(): Int {
            val 큐 = ArrayDeque<IntArray>() // [V4] BFS 큐
            거리배열[0][0] = 1 // [S2][V3] 시작 칸 포함 거리
            큐.addLast(intArrayOf(0, 0)) // [S2][V4] 시작 칸 enqueue

            // [S3] BFS 루프
            while (큐.isNotEmpty()) {
                val 현재칸 = 큐.removeFirst()
                val x = 현재칸[0] // [V5] 현재 칸 행
                val y = 현재칸[1] // [V6] 현재 칸 열

                if (x == xCnt - 1 && y == yCnt - 1) return 거리배열[x][y] // [S3][V3] 도착지점에 도착.

                // [S4] 4방향 확장(핵심 분기/확장)
                for (방향 in 0 until 4) { // [S4]
                    val nx = x + dx[방향]
                    val ny = y + dy[방향]

                    if (nx !in 0 until xCnt || ny !in 0 until yCnt) continue // [S4][V0][V1]
                    if (graph[nx][ny] == 0) continue // [S4][V2]
                    if (거리배열[nx][ny] != 0) continue // [S4][V3]

                    거리배열[nx][ny] = 거리배열[x][y] + 1 // [S4][V3][V5][V6]
                    큐.addLast(intArrayOf(nx, ny)) // [S4][V4]
                }
            }

            return -1
        }

        // [S5] bfs() 결과로 정답 확정
        정답 = bfs() // [S5][V9]

        // [S_END] 결과 반환
        return 정답
    }
}
