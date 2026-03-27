package backjoon.soma2cha.DFSBFS.`20260106`
/*
[문제 요약(현재 우리가 맞춘 버전)]
- 0/1 격자에서 1은 집, 0은 빈칸이다.
- 상하좌우로 연결된 1들의 묶음을 단지로 본다.
- 출력: 단지별 집 수를 오름차순으로 정렬한 배열
지도 = ["0110100", "0110101", ...]
answer = [7, 8, 9]
*/

class Solution {

    /**
     * [이 풀이의 핵심 아이디어]
     * - 모든 칸을 순회하다가 값이 1이면 DFS로 연결 컴포넌트를 전부 방문한다.
     * - 방문한 칸은 0으로 바꿔 재방문을 막는다.
     *
     * [데이터 구조]
     * - graph: 현재 지도 상태(방문하면 0으로 갱신)
     * - 단지크기목록: 각 단지의 집 수
     * - 방향벡터: 상하좌우
     *
     * [시뮬레이션 순서]
     * <1> 문자열 지도를 숫자 격자로 변환
     * <2> 각 칸 순회, 1을 만나면 DFS 시작
     * <3> DFS 중 연결된 1을 모두 방문 처리
     * <4> DFS가 끝나면 단지 크기 저장
     * <5> 결과 정렬 후 반환
     *
     * [주석 템플릿 규칙]
     * - [S*] : 알고리즘 실행 단계
     * - [V*] : 핵심 상태 변수
    */
    fun solution(지도: Array<String>): IntArray {
        // [S0] 초기화(V0~V3) 지도 = ["0110100", "0110101", ...]
        val xCnt = 지도.size
        val yCnt = if (xCnt == 0) 0 else 지도[0].length
        val graph = Array(xCnt) { x -> 지도[x].map { it.digitToInt() }.toIntArray() } // [V0]
        val dx = intArrayOf(-1, 1, 0, 0) // [V1]
        val dy = intArrayOf(0, 0, -1, 1) // [V2]
        val 단지크기목록 = mutableListOf<Int>() // [V3]

        fun dfs(x: Int, y: Int): Int {
            //방문처리
            graph[x][y] = 0
            var 크기 = 1

            // [S4] 인접 칸 확장
            for (dir in 0..3) { // [S4]
                val nx = x + dx[dir]
                val ny = y + dy[dir]
                if (nx !in 0 until xCnt || ny !in 0 until yCnt) continue
                if (graph[nx][ny] != 1) continue
                크기 += dfs(nx, ny)
            }
            return 크기
        }

        // [S1] 전체 격자 순회
        for (x in 0 until xCnt) {
            for (y in 0 until yCnt) {
                if (graph[x][y] != 1) continue

                // [S2] 새 단지 시작
                val 단지크기 = dfs(x, y)

                // [S3] 단지 결과 반영
                단지크기목록.add(단지크기) // [S3][V3]
            }
        }

        // [S5] 결과 정렬
        단지크기목록.sort()

        // [S_END] 결과 반환
        return 단지크기목록.toIntArray()
    }
}
