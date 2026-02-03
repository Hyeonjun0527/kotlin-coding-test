/*
-----------------------------------------
 replace.kt — replace를 절차형으로 구현한 예제
-----------------------------------------

목표:
s.replace(old, new)를 라이브러리 없이 직접 구현해본다.
문자열을 왼쪽에서부터 훑으며 old와 일치하면 new로 교체하고,
그렇지 않으면 그대로 붙여나가는 방식.
*/

fun main() {
    val s = "hellohelloworld"
    val target = "hello"
    val replacement = "X"

    // 라이브러리 replace 버전
    val result = s.replace(target, replacement)

    val result2 = run {
        var i = 0;
        val sb = StringBuilder()
        while (i < s.length) {
            val hasRoom = i + target.length <= s.length


            if (hasRoom && s.substring(i, i + target.length) == target) {
                sb.append(replacement)
                i += target.length   // target 길이만큼 점프
                continue
            }

            sb.append(s[i])
            i++
        }
        sb
    }

    println("library replace = $result")
    println("manual replace  = $result2")
}



/*
우리말 풀이:
replace의 핵심은 “왼쪽부터 탐색하면서 target이 나타나면 replacement를 넣고 target 길이만큼 건너뛴다”.
즉 직접 구현하면 substring 비교 + sb.append + 인덱스 점프 로 해결된다.
*/
