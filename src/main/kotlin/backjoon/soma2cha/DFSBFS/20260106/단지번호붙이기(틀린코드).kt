package backjoon.soma2cha.DFSBFS.`20260106`
/*
[문제 요약(현재 우리가 맞춘 버전)]
- 0/1 격자에서 상하좌우 연결된 1들의 단지 크기를 구한다.
- 이 파일은 기존 틀린 접근을 템플릿 기준으로 교정한 버전이다.
- 출력: 단지별 집 수를 오름차순 정렬한 배열
*/

class Solution {

    /**
     * [이 풀이의 핵심 아이디어]
     * - 기존 틀린 코드의 핵심 문제(좌표 계산 오류, 경계 검사 누락, 카운트 전달 방식)를 수정한다.
     * - DFS 반환값으로 단지 크기를 합산하면 전역 카운터 의존을 줄일 수 있다.
     *
     * [데이터 구조]
     * - graph: 방문 시 0으로 바꾸는 지도
     * - 방향벡터: 상하좌우
     * - 결과목록: 단지 크기 저장
     *
     * [시뮬레이션 순서]
     * <1> 격자 구성
     * <2> 1을 만날 때마다 DFS 호출
     * <3> DFS가 단지 크기 반환
     * <4> 결과 정렬 후 반환
     *
     * [주석 템플릿 규칙]
     * - [S*] : 실행 단계
     * - [V*] : 상태 변수
    */
    fun solution(지도: Array<String>): IntArray {
        // [S0] 초기화(V0~V3)
        val xCnt = 지도.size
        val yCnt = if (xCnt == 0) 0 else 지도[0].length
        val graph = Array(xCnt) { x -> 지도[x].map { it.digitToInt() }.toIntArray() } // [V0]
        val dx = intArrayOf(-1, 1, 0, 0) // [V1]
        val dy = intArrayOf(0, 0, -1, 1) // [V2]
        val 결과목록 = ArrayList<Int>() // [V3]

        fun dfs(x: Int, y: Int): Int {
            graph[x][y] = 0
            var 크기 = 1

            // [S4] 인접 칸 탐색
            for (dir in 0..3) { // [S4]
                val nx = x + dx[dir]
                val ny = y + dy[dir]
                if (nx !in 0 until xCnt || ny !in 0 until yCnt) continue
                if (graph[nx][ny] != 1) continue
                크기 += dfs(nx, ny)
            }
            return 크기
        }

        // [S1] 전체 순회
        for (x in 0 until xCnt) {
            for (y in 0 until yCnt) {
                if (graph[x][y] != 1) continue

                // [S2] 단지 처리 시작
                val 크기 = dfs(x, y)

                // [S3] 단지 결과 저장
                결과목록.add(크기) // [S3][V3]
            }
        }

        // [S5] 오름차순 정렬
        결과목록.sort()

        // [S_END] 결과 반환
        return 결과목록.toIntArray()
    }
}
