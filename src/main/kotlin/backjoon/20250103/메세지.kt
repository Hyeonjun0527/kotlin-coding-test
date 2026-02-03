    //  메시지

    fun main(args: Array<String>) {
        data class Box(val name, val list)
        var n = readLine()!!.toInt()
        var run_cnt = 1
        while(n != 0) {
            val listBox = ???
            repeat(n) {
                val line = readLine()!!.split(" ")
                var box = Box()빈 박스객체만들기, 그리고 빈 mutableList 넣기
                for ((x,i) in line.withIndexed()) {
                    if (i == 0) {
                        box.name = x
                    }
                    box.list.add(x)
                }
                listBox.add(box)
            }

            Group
            listBox를 순회. 순회할때마다 name과 list 를 조회 가능.
                        list를 순회함. 순회할때마다 j와 값 순회 간으
                                이때 N이 발견되면 (i - (j + 1) + n) % n 해서 상대 index를 찾고 출력

            n = readLine()!!.toInt()
            run_cnt++
        }
    }