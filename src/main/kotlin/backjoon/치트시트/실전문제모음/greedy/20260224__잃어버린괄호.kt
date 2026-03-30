package backjoon.치트시트.실전문제모음.greedy

// 알고리즘 분류: greedy

fun main(args: Array<String>) {
    //읽다가 -를 발견하면 거기부터 수를 보관해.
// 하나하나. 언제까지? -가 오면 멈춰.
// 그렇게 해서 총 계산을 해.
// 그다음에 다음 마이너스로 해야해.
// 즉, 처음에 마이너스 배열을 만들어야함.
// 마이너스 배열에는 마이너스 숫자가 들어가잇을거임.
// 그거마다 순회해서 min 찾으면 되나.
    val 임시list = readln().split("-")

    val 정제된list = mutableListOf<Int>()

//    if (임시list )

    for (문자 in 임시list) {
        val 내부리스트 = 문자.split("+")
        정제된list.add(내부리스트.map {it.toInt()}.sum())
    }

    println(-정제된list.drop(1).sum()+정제된list[0])


//    val test = "5555"
//    print(test.split("+"))//그대로 5555나옴
}
// split()을 할때 어느인덱스에서 스플릿되는지 알려면 어케하지
