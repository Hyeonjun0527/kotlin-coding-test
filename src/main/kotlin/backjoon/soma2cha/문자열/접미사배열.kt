package backjoon.soma2cha.문자열// BOJ: https://www.acmicpc.net/problem/11656

/*
[문제 요약]
- 문자열의 모든 접미사를 만들어 사전순으로 정렬한 배열을 구한다.

예시
| S | return |
|---|---|
| "baekjoon" | ["aekjoon","baekjoon","ekjoon","joon","kjoon","n","on","oon"] |

의미
- S: 원본 문자열
- return: 모든 접미사를 사전순 정렬한 배열(출력 시 한 줄씩 사용)
*/

class Solution {

    /**
     * [핵심 아이디어]
     * - 인덱스 i마다 S[i..끝] 부분 문자열을 만들면 모든 접미사를 얻을 수 있다.
     * - 만들어진 접미사 목록을 사전순 정렬하면 정답이다.
     *
     * [데이터 구조]
     * - V0 문자열길이(Int): 입력 문자열 길이
     * - V1 접미사목록(MutableList<String>): 생성한 모든 접미사
     * - V2 시작인덱스(Int): 접미사 시작 위치
     * - V3 접미사(String): 현재 생성한 접미사
     * - V4 정렬결과(List<String>): 사전순 정렬된 접미사 목록
     *
     * [처리 순서]
     * <1> 문자열의 모든 시작 인덱스에서 접미사 생성
     * <2> 접미사 목록 사전순 정렬
     * <3> 배열 형태로 반환
     *
     * [주석 템플릿 규칙]
     * - [S*] : 실행 단계
     * - [V*] : 상태 변수
     * - 핵심 분기/확장 라인에는 [S4]를 명시
     */
    fun solution(문자열S: String): Array<String> {
        // [S0] 상태 초기화
        val 문자열길이 = 문자열S.length // [V0]
        val 접미사목록 = mutableListOf<String>() // [V1]

        // [S1] 접미사 생성
        for (시작인덱스 in 0 until 문자열길이) { // [V2]
            val 접미사 = 문자열S.substring(시작인덱스) // [V3]
            접미사목록.add(접미사) // [S4][V1][V3] 핵심 확장: 각 시작점에서 접미사 추가
        }

        // [S2] 사전순 정렬
        val 정렬결과 = 접미사목록.sorted() // [V4]

        // [S_END] 결과 반환
        return 정렬결과.toTypedArray()
    }
}
