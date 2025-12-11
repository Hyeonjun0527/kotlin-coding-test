
fun main(args: Array<String>) {
    val (n,m) = readln().split(" ").map {it.toInt()}

    val a = List(n) {
        readln().split(" ").map { it.toInt() }
    }

    val b = List(n) {
        readln().split(" ").map { it.toInt() }
    }

    //a.zip(b) 하면 a의 각 요소와 b의 각 요소를 합치는거임.
    //요소는 행 자체임! 그래서 rowA, rowB임.
    //rowA.zip(rowB)로 rowA의 각 요소와 rowB의 각 요소를 합침.
    //각 요소는 cell하나하나임. 이렇게 element-wise 연산한다.
    val result = a.zip(b) { rowA, rowB ->
        rowA.zip(rowB) { x, y -> x + y }
    }

    //먼저 \n로 행으로 나누고...그다음에 한 행마다 " "로 열 자름
    val output = result.joinToString("\n") { row ->
        row.joinToString(" ")
    }
    print(output)


}

/*
1 1 1    3 3 3      4 4 4
2 2 2    4 4 4      6 6 6
0 1 0    5 5 100    5 6 100
* */


/*
zip = 두 리스트를 나란히 두고, 같은 위치 요소끼리 짝을 만들어 주는 기능.
그냥 a.zip(b)하면 페어리스트만들어지는거군

* */