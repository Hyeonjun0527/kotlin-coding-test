package backjoon.치트시트.실전문제모음.dataStructure

// 알고리즘 분류: dataStructure

fun main(args: Array<String>) {
    readln()
    val inputs = readln().split(" ").map { it.toInt() }
    val stacks = MutableList(4) { ArrayDeque<Int>() }
    stacks[0].add(inputs[0])
    for (el in inputs.drop(1)) {
        //스택들을 본다. 스택이 비었거나 스택 first보다 큰 값이면 거기에 바로 넣는다. 되도록 이렇게 넣어야함.
        //기존값보다 작은값이면 새로운 스택에 넣는다.
        //스택이 비었으면 거기에 넣는다.
        var maxv = 0
        var maxi = -1
        var 후보찾았는지 = false
        var 빈스택있는지 = false
        for ((j,stack) in stacks.withIndex()) {
            // el이 더 크지만, first중에선 가장큰 first
            if (stack.isNotEmpty() && stack.first() < el) {//후보임
                maxv = maxOf(maxv,stack.first())
                후보찾았는지 = true
            }
        }

        if (후보찾았는지) {
            for ((i,stack) in stacks.withIndex()) {
                if (stack.isNotEmpty() && maxv == stack.first()) {
                    maxi = i
                }
            }
            stacks[maxi].addFirst(el)
        }

        if (!후보찾았는지) {
            for (stack in stacks) {
                if (stack.isEmpty()) {
                    stack.addFirst(el)
                    빈스택있는지 = true
                    break
                }
            }
        }
        if (!후보찾았는지 && !빈스택있는지) {
            println("NO")
            return
        }
    }
    println("YES")
}
