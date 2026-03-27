package 코테.`12-9`

import java.util.ArrayDeque

/*
[문제 요약(현재 우리가 맞춘 버전)]
- 이동 가능한 경로는 직사각형 합집합의 "가장 바깥 테두리"뿐이다.
- 시작 좌표(characterX, characterY)에서 아이템 좌표(itemX, itemY)까지
  테두리를 따라 이동하는 최단 거리를 구한다.
- 사각형이 겹치는 경우 내부 선분은 이동 경로에서 제거되어야 한다.
rectangle = [[1,1,7,4],[3,2,5,5],[4,3,6,9],[2,6,8,8]]
character = (1,3), item = (7,8)
answer = 17
*/

class Solution {

    /**
     * [핵심 아이디어]
     * - 좌표를 2배로 확장하면 대각선 모서리에서 잘못 "통과"되는 케이스를 안전하게 제거할 수 있다.
     * - 모든 직사각형 영역을 채운 뒤 내부를 다시 0으로 지우면 "외곽 테두리만 1"로 남는다.
     * - 이후 테두리(값 1)만 따라 BFS를 수행하면 최단 거리를 얻는다.
     *
     * [데이터 구조]
     * - V0 배율(Int): 좌표 스케일 배수(2)
     * - V1 최대좌표(Int): 스케일 후 좌표 경계
     * - V2 지도(Array<IntArray>): 0=비경계, 1=이동 가능한 경계
     * - V3 방문거리(Array<IntArray>): BFS 방문 거리(-1 미방문)
     * - V4 큐(ArrayDeque<IntArray>): BFS 큐
     * - V5 dx(IntArray): 4방향 행 델타
     * - V6 dy(IntArray): 4방향 열 델타
     * - V7 x(Int): 큐에서 꺼낸 현재 위치 행
     * - V8 y(Int): 큐에서 꺼낸 현재 위치 열
     * - V9 nx(Int): 확장할 다음 위치 행
     * - V10 ny(Int): 확장할 다음 위치 열
     * - V11 시작행2(Int): 스케일된 시작 행
     * - V12 시작열2(Int): 스케일된 시작 열
     * - V13 아이템행2(Int): 스케일된 아이템 행
     * - V14 아이템열2(Int): 스케일된 아이템 열
     * - V15 정답(Int): 최종 반환 거리
     *
     * [시뮬레이션(또는 탐색) 순서]
     * <1> 좌표 스케일 및 상태 변수 초기화
     * <2> 모든 직사각형을 지도에 채우고 내부를 제거해 경계만 남김
     * <3> 시작점을 큐에 넣고 BFS 시작
     * <4> 경계 칸만 확장하며 아이템까지 최단 거리 계산
     * <5> 스케일 거리값을 2로 나눠 원래 거리로 복원해 반환
     *
     * [주석 템플릿 규칙]
     * - [S*] : 알고리즘 실행 흐름 단계
     * - [V*] : 핵심 상태 변수(데이터 저장소)
     * - 본문 진행 순서: [S0] 초기화 -> [S1..S5] BFS 처리 -> [S_END] 반환
     * - 상태 변수 선언/핵심 갱신 라인에 [V*]를 표기
     * - 알고리즘 핵심 분기/확장 라인에는 [S4]를 표기
     */
    fun solution(
        rectangle: Array<IntArray>,
        characterX: Int,
        characterY: Int,
        itemX: Int,
        itemY: Int,
    ): Int {
        // [S0] 초기화: 스케일/지도/거리/큐/방향/좌표 변수 준비
        val 배율 = 2 // [V0] 좌표 확장 배수
        val 최대좌표 = 102 // [V1] 50*2=100 + 여유 경계
        val graph = Array(최대좌표 + 1) { IntArray(최대좌표 + 1) } // [V2] 경계 graph
        val 거리배열 = Array(최대좌표 + 1) { IntArray(최대좌표 + 1) { -1 } } // [V3] BFS 거리
        val dx = intArrayOf(-1, 1, 0, 0) // [V5] 상하좌우 행 변화
        val dy = intArrayOf(0, 0, -1, 1) // [V6] 상하좌우 열 변화

        val 시작행2 = characterY * 배율 // [V11] 스케일 시작 행
        val 시작열2 = characterX * 배율 // [V12] 스케일 시작 열
        val 아이템행2 = itemY * 배율 // [V13] 스케일 아이템 행
        val 아이템열2 = itemX * 배율 // [V14] 스케일 아이템 열
        var 정답 = 0 // [V15] 최종 거리

        // [S1] 직사각형 영역 채우기 + 내부 제거
        for (사각형 in rectangle) {
            val 좌하X2 = 사각형[0] * 배율
            val 좌하Y2 = 사각형[1] * 배율
            val 우상X2 = 사각형[2] * 배율
            val 우상Y2 = 사각형[3] * 배율

            for (행 in 좌하Y2..우상Y2) {
                for (열 in 좌하X2..우상X2) {
                    graph[행][열] = 1 // [S1][V2] 직사각형 전체 채우기
                }
            }
        }

        for (사각형 in rectangle) {
            val 좌하X2 = 사각형[0] * 배율
            val 좌하Y2 = 사각형[1] * 배율
            val 우상X2 = 사각형[2] * 배율
            val 우상Y2 = 사각형[3] * 배율

            for (행 in (좌하Y2 + 1) until 우상Y2) {
                for (열 in (좌하X2 + 1) until 우상X2) {
                    graph[행][열] = 0 // [S1][V2] 내부 제거(경계만 유지)
                }
            }
        }

        // [S2] bfs() 함수 정의
        fun bfs(): Int {
            val 큐 = ArrayDeque<IntArray>() // [V4] BFS 큐
            거리배열[시작행2][시작열2] = 0 // [S2][V3][V11][V12]
            큐.addLast(intArrayOf(시작행2, 시작열2)) // [S2][V4]

            // [S3] BFS 루프: 현재 위치를 꺼내 목표 도달 여부 검사
            while (큐.isNotEmpty()) {
                val 현재위치 = 큐.removeFirst()
                val x = 현재위치[0] // [V7] 현재 행
                val y = 현재위치[1] // [V8] 현재 열

                if (x == 아이템행2 && y == 아이템열2) return 거리배열[x][y]

                // [S4] 경계선 4방향 확장(핵심 분기/확장)
                for (방향 in 0 until 4) { // [S4]
                    val nx = x + dx[방향] // [V9]
                    val ny = y + dy[방향] // [V10]

                    if (nx !in 0..최대좌표 || ny !in 0..최대좌표) continue // [S4][V1][V9][V10]
                    if (graph[nx][ny] != 1) continue // [S4][V2]
                    if (거리배열[nx][ny] != -1) continue // [S4][V3]

                    거리배열[nx][ny] = 거리배열[x][y] + 1 // [S4][V3][V7][V8]
                    큐.addLast(intArrayOf(nx, ny)) // [S4][V4][V9][V10]
                }
            }

            return -1
        }

        // [S5] 스케일 거리 복원(2배 좌표 -> 원래 좌표 거리)
        val 스케일거리 = bfs() // [S5][V3]
        정답 = if (스케일거리 == -1) -1 else 스케일거리 / 배율 // [S5][V15][V0]

        // [S_END] 결과 반환
        return 정답
    }
}
