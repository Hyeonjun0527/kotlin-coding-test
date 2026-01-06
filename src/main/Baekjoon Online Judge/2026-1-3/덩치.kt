//  덩치

fun main(args: Array<String>) {
    val n = readln().toInt()

    val 목록 = List(n) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        a to b
    }//페어를 내부로 가짐.

//    println(list)

    val 랭크목록 = 목록.map { (나의_몸무게, 나의_키) ->
        var 나보다덩치큰사람수 = 0
        목록.forEach { (상대의_몸무게, 상대의_키) ->
            if (나의_몸무게 < 상대의_몸무게 && 나의_키 < 상대의_키) {
                나보다덩치큰사람수++
            }
        }
        나보다덩치큰사람수+1
    }

    println(랭크목록.joinToString(" "))
}
//맵이 내 요소를 바꿔버리는거 아니였나?
//아님. 새로운거 만드는거임.
//mapIndexed, data class Person(val w: Int,...)
//

//mapIndexed 라는 것도 있음.
/*

val ranks = list.mapIndexed { idx, 나 ->
    var bigger = 0
    list.forEach { 상대 ->
        if (나.first < 상대.first && 나.second < 상대.second) bigger++
    }
    bigger + 1
}

//dataclass를 사용할수도 있다.
data class Person(val w: Int, val h: Int, val rank: Int = 0)

fun main() {
    val n = readln().toInt()

    val list = MutableList(n) {
        val (w, h) = readln().split(" ").map { it.toInt() }
        Person(w, h)
    }

    list.replaceAll { me ->
        var rank = 1
        list.forEach { other ->
            if (other.w > me.w && other.h > me.h) rank++
        }
        me.rank = rank
        me // 이거 꼭 필요함 (replaceAll은 Person 반환해야 함)
    }

    println(list.joinToString(" ") { it.rank.toString() })
}

*/