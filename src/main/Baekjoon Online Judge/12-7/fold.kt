/*
-----------------------------------------
 fold.kt — fold를 절차형으로 구현한 예제
-----------------------------------------

목표:
list.fold(initial) { acc, elem -> ... } 를
라이브러리 없이 절차형으로 직접 구현해보는 예제
*/

fun main() {
    val numbers = listOf(1, 2, 3, 4, 5)

    // 라이브러리 fold 사용
    val libFold = numbers.fold(0) { acc, elem ->
        acc + elem
    }

    // 절차형 fold 직접 구현
    val manualFold = run {
        var acc = 0
        for (elem in numbers) {
            acc = acc + elem
        }
        acc
    }

    println("library fold = $libFold")
    println("manual fold  = $manualFold")
}

/*
우리말 풀이:
fold는 “acc(누적 변수)를 하나 두고, 리스트를 앞에서부터 반복하며 acc를 계속 갱신하는 것”.
라이브러리 버전이나 수동 버전이나 원리는 동일하다.
*/
