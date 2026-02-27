package backjoon.`20260227`// 프로그래머스

class Solution2 {
    fun solution(babbling: Array<String>): Int {
        val 가능발음 = arrayOf("aya", "ye", "woo", "ma")
        var answer = 0

        for (word in babbling) {
            var tmp = word

            // 1) 가능한 발음을 전부 공백으로 치환
            for (p in 가능발음) {
                tmp = tmp.replace(p, " ")
            }

            // 2) 공백 제거했는데 아무것도 안 남으면 "딱 그 발음들로만" 구성된 것
            if (tmp.replace(" ", "").isEmpty()) {
                answer++
            }
        }

        return answer
    }
}

/*
replace할때는 한번에 ""를 하지말자. 그러면 새로 단어토큰이 우연히 만들어짐.

문자열: ABCD
p = BC

replace("BC","") → AD (B,C가 빠지면서 A와 D가 붙음)

replace("BC"," ") → A D (A와 D 사이에 공백이 남아서 안 붙음)

이 차이가 바로 핵심이야.
* */