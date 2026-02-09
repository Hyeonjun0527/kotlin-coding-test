
/*
s = "a b c d e"
     0 1 2 3 4

i=0 → "ab"
i=1 → "bc"
i=2 → "cd"
i=3 → "de"

최종 결과:
["ab", "bc", "cd", "de"]

[문제]
절차형으로 윈도우 해보고,
라이브러리로도 해보자.
* */

fun main() {
    val s = "helloWorld"
    val list = s.windowed(2)

    val list2 = run {
        val l = mutableListOf<String>()
        s.forEachIndexed { i, _ ->
            if (i >= s.length - 1) return@forEachIndexed
            l.add(s.substring(i, i + 2))
        }
        l
    }

    val list3 = run {
        val l = mutableListOf<String>()
        for (i in 0..s.length - 2) {
            l.add(s.substring(i,i+2))
        }
        l
    }

    println(list)
    println(list2)
    println(list3)

}


//fun backjoon.main() {
//    val s = "helloworld"
//
//    val list = mutableListOf<String>()
//
//    for (i in 0..s.length-2) {
//        list.add(s.substring(i, i+2))
//    }
//
//    val list2 = s.windowed(2)
//}