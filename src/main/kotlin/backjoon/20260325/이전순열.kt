package backjoon.`20260325`//  이전 순열

/**
 * 과제 정렬함수없이 정렬하는 방법 생각해보기 부분배열에 대한 정렬을 정렬함수로 처리할 수 있는지?
 */
fun main(args: Array<String>) {
    readln()
    var list = readln().split(" ").map { it.toInt() }.toMutableList()
    var a = list.size-2
    var b = list.size-1

    var allpass = true
    for (i in a downTo 0) {
        var min = Int.MAX_VALUE
        if (list[a] < list[b]) {//패스
        }  else {//스왑
//            print("스왑 이꾸요")
            allpass = false
//            list[a] = list[b].also { list[b] = list[a] }
            //스왑
            for (j in list.size-1 downTo b) {
                if (list[a] > list[j]) {
                    list[a] = list[j].also { list[j] = list[a] }
                    break
                }
            }
            //내림차순정렬
            var temp = mutableListOf<Int>()
            for (j in b..list.size - 1) {
                temp.add(list[j])
            }
            temp.sortDescending()
            var t = b
            for (값 in temp) {
                list[t] = 값
                t++
            }
            break
        }
        a--
        b--
    }
    if (allpass) {
        print(-1)
    } else {
        print(list.joinToString(" "))
    }
}