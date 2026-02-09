package backjoon.치트시트.dataStructure



//  천재 수학자 성필

fun main(args: Array<String>) {
    val list = readln().toList()

    val dq = ArrayDeque<Int>()
    list.forEach {
        if (it != '*' && it != '+' && it != '-' && it != '/') {
            //만약 이 4가지가 아니라면
            dq.addLast(it - '0')
        } else {
            val c = dq.removeLast()
            val b = dq.removeLast()

            val d = when (it) {
                '+' -> c + b
                '-' -> b - c
                '*' -> c * b
                '/' -> b / c
                else -> Int.MAX_VALUE
            }
            dq.addLast(d)
        }
    }
    print(dq.removeLast())
}

/*
dq  > addFirst, addLast, removeFirst, removeLast, first, last
* */