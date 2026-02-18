package backjoon.`20260212`//  회사에 있는 사람

//로그가 주어졌을 때, 현재 회사에 있는 모든 사람을 구하는 프로그램을 작성하시오.
fun main(args: Array<String>) {
    val n = readln().toInt()
    val set = HashSet<String>()
    repeat(n) {
        val (사람,기록) = readln().split(" ")
        if (기록 == "enter") {
            set.add(사람)
        } else {//leave
            set.remove(사람)
        }
    }

    val list = set.toMutableList()

    list.sortDescending()

    val sb = StringBuilder()

    for (이름 in list) {
        sb.append("${이름}\n")
    }
    print(sb)
}
//맵을 만드려면? 맵에 넣으려면? 맵에서 빼려면? 맵에 그게 있는 지 검사하려면? 맵에 있는걸 정렬하려면?