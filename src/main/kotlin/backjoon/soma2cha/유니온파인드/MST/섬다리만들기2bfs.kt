package backjoon.soma2cha.유니온파인드.MST// BOJ: https://www.acmicpc.net/problem/17472
import java.util.ArrayDeque

/*
[문제 요약]
- 격자에서 섬(1)을 다리로 연결할 때 최소 총 길이를 구한다.
- 다리는 상하좌우 직선으로만 놓고, 길이는 2 이상이어야 한다.
- 모든 섬을 연결할 수 없으면 -1을 반환한다.
*/

class Solution {

    /**
     * [핵심 아이디어]
     * - 1단계: BFS로 섬을 라벨링한다.
     * - 2단계: 각 섬 좌표에서 4방향 직선 탐색으로 다리 후보를 만든다.
     * - 3단계: 후보 다리에 크루스칼을 적용해 최소 연결 비용을 구한다.
     *
     * 이 문제 보고 크루스칼 mst 떠오르고 다리 (시작섬번호,도착섬번호,길이) 다리간선목록을 만들어야한다고 생각하고,
     * 다리간선목록을 만드는 방법으로 1 -> [[0,0] [0,1]...] 2 -> [[?,?],[?,?]....]
     * 이런 섬좌표목록을 만들어서 모든 섬 좌표마다 상하좌우 끝까지 이동해보며 다리간선목록 만들고
     * 그거 먼저 하기전 먼저 섬(구역)별로 bfs로 라벨링해야한다고 판단
     *
     * [데이터 구조]
     * - V0 xCnt(Int): 행 개수
     * - V1 yCnt(Int): 열 개수
     * - V2 graph(MutableList<MutableList<Int>>): 라벨링에 사용할 지도
     * - V3 visited(MutableList<MutableList<Boolean>>): BFS 방문 배열
     * - V4 dx(MutableList<Int>): 행 방향 벡터
     * - V5 dy(MutableList<Int>): 열 방향 벡터
     * - V6 섬좌표목록(MutableList<MutableList<Pair<Int, Int>>>): 섬별 좌표 목록
     * - V7 섬개수(Int): 섬 개수
     * - V8 다리후보목록(MutableList<다리>): 다리 후보 간선
     * - V9 부모(MutableList<Int>): 크루스칼 부모 배열
     * - V10 사용간선수(Int): 채택 간선 수
     * - V11 누적길이(Int): MST 누적 길이
     *
     * [탐색/처리 순서]
     * <1> 지도/방문/방향 배열 초기화
     * <2> bfs()로 섬 라벨링
     * <3> 섬 좌표에서 다리 후보 생성
     * <4> 크루스칼로 최소 연결 비용 계산
     * <5> 모든 섬 연결 여부 검사
     *
     * [주석 템플릿 규칙]
     * - [S*] : 실행 단계
     * - [V*] : 상태 변수
     * - 핵심 분기/확장 라인에는 [S4]를 명시
     */
    fun solution(지도: Array<IntArray>): Int {

        // [S0] 상태 초기화
        val xCnt = 지도.size // [V0]
        val yCnt = 지도[0].size // [V1]

        val 섬지도 = MutableList(xCnt) { x -> MutableList(yCnt) { y -> 지도[x][y] } } // [V2]
        val 방문목록 = MutableList(xCnt) { MutableList(yCnt) { false } } // [V3]
        val dx = mutableListOf(-1, 0, 1, 0) // [V4]
        val dy = mutableListOf(0, 1, 0, -1) // [V5]
        val 섬좌표목록 = mutableListOf<MutableList<Pair<Int, Int>>>() // [V6] 1 -> [(0,0) (0,1)]...
        var 섬개수 = 0 // [V7]

        // [S1] BFS로 섬 하나를 라벨링
        /**
         * 1 1 0 0 2
         * 1 0 0 0 2
         * 0 0 3 3 0 이런식으로..
         */
        fun bfs(x: Int, y: Int, 섬번호: Int) {
            val 큐 = ArrayDeque<Pair<Int, Int>>()
            //이번에 찾은 섬은 이 칸들로 이루어져 있습니다 좌표목록 = [ (0,0), (0,1), (1,0), (1,1) ]
            val 좌표목록 = mutableListOf<Pair<Int, Int>>()

            방문목록[x][y] = true // [V3]
            섬지도[x][y] = 섬번호 // [V2]
            큐.addLast(x to y)
            좌표목록.add(x to y)

            while (큐.isNotEmpty()) {
                val (현재x, 현재y) = 큐.removeFirst()

                for (방향 in 0 until 4) { // [S4] 핵심 확장: 4방향 BFS
                    val nx = 현재x + dx[방향]
                    val ny = 현재y + dy[방향]

                    if (nx !in 0 until xCnt || ny !in 0 until yCnt) continue // [S4][V0][V1]
                    if (방문목록[nx][ny]) continue // [S4][V3]
                    if (섬지도[nx][ny] == 0) continue // [S4][V2]

                    방문목록[nx][ny] = true // [S4][V3]
                    섬지도[nx][ny] = 섬번호 // [S4][V2]
                    큐.addLast(nx to ny)
                    좌표목록.add(nx to ny)
                }
            }

            섬좌표목록.add(좌표목록) // [V6]
        }

        // [S2] 전체 지도 순회로 섬 라벨링
        for (x in 0 until xCnt) {
            for (y in 0 until yCnt) {
                if (섬지도[x][y] == 0 || 방문목록[x][y]) continue
                섬개수++ // [V7]
                bfs(x, y, 섬개수)// 섬개수가 섬 번호로도 사용됨.
            }
        }

        if (섬개수 <= 1) return 0 // [S2][V7]

        // [S3] 다리 후보 간선 생성
        /**
         * (1번섬 -> 2번섬, 길이 3)
         * (2번섬 -> 3번섬, 길이 2)
         * (1번섬 -> 3번섬, 길이 4) 다리 (시작섬번호, 도착섬번호 길이)
         */

        data class 다리(val 시작섬: Int, val 도착섬: Int, val 길이: Int)
        val 다리간선목록 = mutableListOf<다리>() // [V8]
        for (섬좌표들 in 섬좌표목록) {
            for ((x, y) in 섬좌표들) {
                val 출발섬 = 섬지도[x][y]

                for (방향 in 0 until 4) {
                    var nx = x + dx[방향]
                    var ny = y + dy[방향]
                    var 다리길이 = 0

                    while (nx in 0 until xCnt && ny in 0 until yCnt) {
                        if (섬지도[nx][ny] == 출발섬) break
                        if (섬지도[nx][ny] != 0) {
                            if (다리길이 > 1) {
                                다리간선목록.add(다리(출발섬, 섬지도[nx][ny], 다리길이)) // [V8] 섬마다 연결 모든 가능한 후보 간선을 만듬.
                            }
                            break
                        }

                        다리길이++
                        nx += dx[방향]
                        ny += dy[방향]
                    }
                }
            }
        }

        // [S4] 크루스칼로 MST 계산
        val 정렬다리목록 = 다리간선목록.sortedBy { it.길이 }
        val 부모 = MutableList(섬개수 + 1) { it } // [V9]
        var 사용간선수 = 0 // [V10]
        var 누적길이 = 0 // [V11]

        fun find(섬번호: Int): Int {
            if (부모[섬번호] == 섬번호) return 섬번호
            부모[섬번호] = find(부모[섬번호]) // [S4][V9] 경로 압축
            return 부모[섬번호]
        }

        fun union(섬A: Int, 섬B: Int) {
            val 대표A = find(섬A)
            val 대표B = find(섬B)
            if (대표A != 대표B) 부모[대표B] = 대표A // [S4][V9]
        }

        for (현재다리 in 정렬다리목록) {
            if (사용간선수 == 섬개수 - 1) break
            if (find(현재다리.시작섬) == find(현재다리.도착섬)) continue // [S4] 핵심 분기: 사이클 차단
            union(현재다리.시작섬, 현재다리.도착섬)
            사용간선수++ // [S4][V10]
            누적길이 += 현재다리.길이 // [S4][V11]
        }

        // [S5] 연결성 검사
        if (사용간선수 != 섬개수 - 1) return -1 // [S5][V10][V7]

        // [S_END] 정답 반환
        return 누적길이
    }
}
