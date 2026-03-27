// BOJ: https://www.acmicpc.net/problem/1717
package backjoon.soma2cha.유니온파인드
    /*
    [문제 요약]
    - 원소 1..n이 있을 때, union 연산(0 a b)과 같은 집합 확인 연산(1 a b)을 처리한다.
    - 같은 집합 확인 연산마다 YES/NO를 순서대로 반환한다.
    */

    class Solution {

    /**
     * [핵심 아이디어]
     * - 유니온파인드(Disjoint Set Union)로 각 원소의 대표(root)를 관리한다.
     * - find는 경로 압축을 적용해 트리 높이를 줄이고, union은 대표끼리만 연결한다.
     *
     * [데이터 구조]
     * - V0 원소수(Int): 원소 개수
     * - V1 부모(IntArray): 부모(대표) 배열
     * - V2 결과목록(MutableList<String>): 질의 결과 저장
     * - V3 연산(IntArray): 현재 연산 [q,a,b]
     * - V4 연산종류(Int): 연산 타입
     * - V5 원소A(Int): 첫 번째 원소
     * - V6 원소B(Int): 두 번째 원소
     *
     * [탐색/처리 순서]
     * <1> 부모배열, 결과목록 초기화
     * <2> find/union 헬퍼 함수 정의
     * <3> 연산 배열을 순회하며 q,a,b 분해
     * <4> q가 0이면 union, q가 1이면 같은 집합 여부 질의
     * <5> 질의 결과를 결과목록에 추가
     *
     * [주석 템플릿 규칙]
     * - [S*] : 실행 단계
     * - [V*] : 상태 변수
     * - 핵심 분기 라인에는 [S4]를 명시
     */
    fun solution(원소개수: Int, 연산목록: Array<IntArray>): Array<String> {
        // [S0] 상태 초기화
        val 원소수 = 원소개수 // [V0] 원소 개수
        /*
        * i      0 1 2 3 4 5 6 7
        * mom[i] 0 1 2 3 4 5 6 7
        * */
        val 부모 = IntArray(원소수 + 1) { it } // [V1] 1-based parent
        val 결과목록 = mutableListOf<String>() // [V2] YES/NO 누적

        // [S1] find 함수: 경로 압축, 대표 찾기 && 대표 업데이트하기
        fun find(원소: Int): Int {
            if (부모[원소] == 원소) return 원소
            부모[원소] = find(부모[원소]) // [S4][V1] 핵심 갱신: 경로 압축
            return 부모[원소]
        }

        // [S2] union 함수: 대표끼리 연결
        fun union(a: Int, b: Int) {
            val 대표A = find(a)
            val 대표B = find(b)
            if (대표A != 대표B) 부모[대표B] = 대표A // [S4][V1] 핵심 분기: 다른 집합만 병합
        }

        // [S3] 연산 순회
        for (연산 in 연산목록) { // [V3]
            val 연산종류 = 연산[0] // [V4]
            val 원소A = 연산[1] // [V5]
            val 원소B = 연산[2] // [V6]

            // [S4] 핵심 분기: union 또는 같은 집합 질의
            if (연산종류 == 0) { // [S4][V4]
                union(원소A, 원소B)
            } else {
                결과목록.add(if (find(원소A) == find(원소B)) "YES" else "NO") // [S4][V2][V5][V6]
            }
        }

        // [S_END] 결과 반환
        return 결과목록.toTypedArray()
    }
}
