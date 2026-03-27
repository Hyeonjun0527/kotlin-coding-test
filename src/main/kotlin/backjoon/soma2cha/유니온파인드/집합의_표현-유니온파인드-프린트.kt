// BOJ: https://www.acmicpc.net/problem/1717
/*
[문제 요약]
- 유니온파인드 연산 과정을 "출력 대신 문자열 로그"로 남겨 학습용으로 추적한다.
- q=0은 union, q=1은 같은 집합 여부를 확인하고 YES/NO를 기록한다.
*/

class Solution {

    /**
     * [핵심 아이디어]
     * - print를 쓰지 않고 로그 리스트를 만들어 경로 압축/union 과정을 남긴다.
     * - find(depth)를 사용해 재귀 깊이 기반 들여쓰기를 기록한다.
     *
     * [데이터 구조]
     * - V0 원소수(Int): 원소 개수
     * - V1 부모(IntArray): 부모(대표) 배열
     * - V2 로그목록(MutableList<String>): 추적 로그
     * - V3 연산(IntArray): 현재 연산 [q,a,b]
     * - V4 연산종류(Int): 연산 타입
     * - V5 원소A(Int): 첫 번째 원소
     * - V6 원소B(Int): 두 번째 원소
     *
     * [탐색/처리 순서]
     * <1> 부모배열, 로그목록 초기화
     * <2> find/union 헬퍼 정의
     * <3> 연산 배열 순회
     * <4> union 또는 질의를 수행하면서 로그 적재
     * <5> 연산 후 parent 스냅샷을 로그에 추가
     *
     * [주석 템플릿 규칙]
     * - [S*] : 실행 단계
     * - [V*] : 상태 변수
     * - 핵심 분기/확장에는 [S4]를 표기
     */
    fun solution(원소개수: Int, 연산목록: Array<IntArray>): Array<String> {
        // [S0] 상태 초기화
        val 원소수 = 원소개수 // [V0]
        val 부모 = IntArray(원소수 + 1) { it } // [V1]
        val 로그목록 = mutableListOf<String>() // [V2]

        // [S1] find(경로 압축) + 로그
        fun find(원소: Int, 깊이: Int = 0): Int {
            로그목록.add("${"  ".repeat(깊이)}find($원소) -> parent[$원소]=${부모[원소]}") // [S1][V2][V1]
            if (부모[원소] == 원소) {
                로그목록.add("${"  ".repeat(깊이)}root=$원소") // [S1][V2]
                return 원소
            }
            val 대표 = find(부모[원소], 깊이 + 1)
            부모[원소] = 대표 // [S4][V1] 핵심 갱신: 경로 압축
            로그목록.add("${"  ".repeat(깊이)}compress: parent[$원소]=$대표") // [S4][V2]
            return 부모[원소]
        }

        // [S2] union + 로그
        fun union(원소A: Int, 원소B: Int) {
            로그목록.add("union($원소A,$원소B)") // [S2][V2]
            val 대표A = find(원소A)
            val 대표B = find(원소B)
            if (대표A != 대표B) { // [S4]
                부모[대표B] = 대표A // [S4][V1] 핵심 분기: 병합
                로그목록.add("merge: $대표B -> $대표A") // [S4][V2]
            } else {
                로그목록.add("skip: same set") // [S4][V2]
            }
        }

        // [S3] 연산 순회
        for (연산 in 연산목록) { // [V3]
            val 연산종류 = 연산[0] // [V4]
            val 원소A = 연산[1] // [V5]
            val 원소B = 연산[2] // [V6]
            로그목록.add("op q=$연산종류 a=$원소A b=$원소B") // [S3][V2]

            // [S4] 핵심 분기
            if (연산종류 == 0) {
                union(원소A, 원소B)
            } else {
                val 같은집합 = find(원소A) == find(원소B)
                로그목록.add(if (같은집합) "YES" else "NO") // [S4][V2]
            }

            // [S5] 상태 스냅샷 기록
            로그목록.add("parent=${부모.joinToString()}") // [S5][V1][V2]
        }

        // [S_END] 로그 반환
        return 로그목록.toTypedArray()
    }
}
