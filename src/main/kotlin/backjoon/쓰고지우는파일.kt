package backjoon

fun main() {
    val n = readln().toInt()

    val 입구인덱스 = HashMap<String,Int>()
    var i = 0
    repeat(n) {
        val 차량번호 = readln()
        입구인덱스[차량번호] = i
        i++
    }//먼저 이렇게 입구인덱스 만드는거 이제 차량번호별 있어야되는 번호가 붙은거임.

    //출구에서 한 차가 나오면 추월했는지 판단해야함. 그걸 판단하려면 원래 어떻게 나와야하는지를 알아야함.
    //추월이 없으면 입구 리스트의 “다음 차”가 출구에서도 다음으로 나와야 한다.
    var cnt = 0
    val 나감 = BooleanArray(n)
    var 기대인덱스 = 0
    repeat(n) {
        val 나온차량번호 = readln()
        val 나온인덱스 = 입구인덱스[나온차량번호] ?: 0
        while (기대인덱스 < n && 나감[기대인덱스]) {
            기대인덱스++
        }

        if (나온인덱스 != 기대인덱스) {
            cnt++
        }

        나감[나온인덱스] = true
    }

    println(cnt)


}