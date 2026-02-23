//break는 바로 바깥 반복문 종료한다는 걸 잊어버리고 왜 제대로 못찾지 이러다 디버깅 실패
//

package backjoon.`20260222`//  크로스워드 만들기

fun main(args: Array<String>) {
    //먼저 A와 B에서 가장 먼저 겹치는 단어를 찾아.
    // 그 단어를 찾고, 그 단어의 인덱스 X인덱스는 A에서 Y인덱스는 B에서 정해.
    // B는 X인덱스 고려해서. A는 Y인덱스 고려해서 배열에 써.

    val (문장1, 문장2) = readln().split(" ")

    var (교차행, 교차열) = 0 to 0
    var 값 = 0
    종료@ for (i in 0 until 문장1.length) {
        for (j in 0 until 문장2.length) {
            if (문장1[i] == 문장2[j]) {
                교차열 = i//x
                교차행 = j//y
                break@종료
            }
        }
    }//x , y 가 겹치는 점

    /*
    * 이렇게도 풀 수 있음.
    * for (i in 0 until 문장1.length) {
    *     val 교차단어 = 문장2.indexOf(문장1[i])
    *     if (교차단어 != -1) {
    *         교차열 = i
    *         교차행 = j
    *         break
    *     }
    * }
    *
    * */

//    println("${교차행} ${교차열}")

    val 지도 = MutableList(문장2.length) { MutableList(문장1.length) { '.' } }


    for (i in 0 until 지도[0].size) {
        지도[교차행][i] = 문장1[i]
    }
    for (i in 0 until 지도.size) {
        지도[i][교차열] = 문장2[i]
    }

    for (row in 지도) {
        for (cell in row) {
            print(cell)
        }
        println()
    }
}