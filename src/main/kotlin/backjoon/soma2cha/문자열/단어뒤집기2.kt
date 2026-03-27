package backjoon.soma2cha.문자열// BOJ: https://www.acmicpc.net/problem/17413

import java.util.ArrayDeque

/*
[문제 요약]
- 문자열에서 태그(`<...>`) 내부는 그대로 두고, 태그 밖의 단어만 뒤집는다.
- 단어는 공백으로 구분되며, 태그와 단어 사이에는 공백이 없을 수 있다.

예시
| S | return |
|---|---|
| "baekjoon online judge" | "noojkeab enilno egduj" |
| "<open>tag<close>" | "<open>gat<close>" |

의미
- S: 원본 문자열
- return: 태그 외 단어만 뒤집힌 결과 문자열
*/

class Solution {

    /**
     * [핵심 아이디어]
     * - 문자열을 왼쪽부터 한 글자씩 본다.
     * - 태그 밖에서 문자를 만나면 스택에 쌓아 단어를 뒤집고,
     *   공백 또는 태그 시작(`<`)을 만나면 스택을 비워 결과에 붙인다.
     * - 태그 안에서는 문자를 그대로 결과에 붙인다.
     *
     * [데이터 구조]
     * - V0 문자열길이(Int): 입력 문자열 길이
     * - V1 결과(StringBuilder): 최종 출력 문자열
     * - V2 단어스택(ArrayDeque<Char>): 태그 밖 단어 역순 출력을 위한 스택
     * - V3 태그모드(Boolean): 현재 태그 내부인지 여부
     * - V4 현재문자(Char): 순회 중인 현재 문자
     *
     * [처리 순서]
     * <1> 결과 버퍼/단어 스택/태그 상태 초기화
     * <2> 문자열을 한 글자씩 순회
     * <3> '<'를 만나면 스택 비우고 태그 모드 진입
     * <4> '>'를 만나면 태그 모드 종료
     * <5> 태그 밖에서는 공백 기준으로 단어를 뒤집어 출력
     * <6> 순회 종료 후 남은 단어 스택 비우기
     *
     * [주석 템플릿 규칙]
     * - [S*] : 실행 단계
     * - [V*] : 상태 변수
     * - 핵심 분기/확장 라인에는 [S4]를 명시
     */
    fun solution(문자열S: String): String {
        // [S0] 상태 초기화
        val 문자열길이 = 문자열S.length // [V0]
        val 결과 = StringBuilder() // [V1]
        val 단어스택 = ArrayDeque<Char>() // [V2]
        var 태그모드 = false // [V3]

        fun 스택비우기() {//지금까지 모아 둔 단어를 뒤집어서 결과 문자열에 꺼내 쓰는 것
            while (단어스택.isNotEmpty()) 결과.append(단어스택.removeLast())
        }

        // [S1] 문자 순회
        for (인덱스 in 0 until 문자열길이) {
            val 현재문자 = 문자열S[인덱스] // [V4]

            // [S2] 태그 시작 처리
            if (현재문자 == '<') {
                스택비우기()
                태그모드 = true // [V3]
                결과.append(현재문자) // [V1][V4]
                continue
            }

            // [S3] 태그 종료 처리
            if (현재문자 == '>') {
                태그모드 = false // [V3]
                결과.append(현재문자) // [V1][V4]
                continue
            }

            // [S4] 핵심 처리: 태그 밖 단어 뒤집기
            if (태그모드) {
                결과.append(현재문자) // [S4][V1][V4]
            } else {
                if (현재문자 == ' ') {
                    스택비우기()
                    결과.append(현재문자) // [S4][V1][V4]
                } else {
                    단어스택.addLast(현재문자) // [S4][V2][V4]
                }
            }
        }

        // [S5] 남은 단어 처리
        스택비우기()

        // [S_END] 결과 반환
        return 결과.toString()
    }
}
