// 2025.11.06 at 15:38:06 GMT
import java.io.*
import kotlin.system.measureTimeMillis
import java.util.TreeMap
import java.util.TreeSet
import kotlin.collections.get
import kotlin.math.*
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.text.get

//All typealias that I use
typealias pii = Pair<Int,Int>
typealias pll = Pair<Long,Long> //stupid R/Gac two-sided recognitions
typealias ints = IntArray
typealias longs = LongArray
typealias bools = BooleanArray
// 1. Modded
fun Int.adjust():Int{ if(this >= p){ return this  - p }else if (this < 0){ return this + p };return this }
fun Int.snap():Int{ return if(this >= p){ this - p } else this }
infix fun Int.mm(b:Int):Int{ return ((this.toLong() * b) % p).toInt() }
infix fun Int.mp(b:Int):Int{ val ans = this + b;return if(ans >= p) ans - p else ans }
infix fun Int.ms(b:Int):Int{ val ans = this - b;return if(ans < 0) ans + p else ans }
fun Long.modded():Int = (this % p).toInt().adjust()
fun Int.inverse():Int = intPow(this,p-2)
fun Int.additiveInverse():Int = if(this == 0) 0 else p - this
infix fun Int.modDivide(b:Int):Int{ return this mm (b.inverse()) }
fun intPow(x:Int, e:Int):Int{
    var x = x; var e = e ; var ret = 1
    while(e > 0){
        if(e and 1 == 1) ret = ret mm x
        x = x mm x
        e = e shr 1
    }
    return ret
}
// 2. DP initial values
const val plarge = 1_000_000_727
const val nlarge = -plarge
const val phuge = 2_727_000_000_000_000_000L
const val nhuge = -phuge
//3. hard to write stuff
val mint get() = mutableListOf<Int>()
val mong get() = mutableListOf<Long>()
val mchar get() = mutableListOf<Char>()
fun IntArray.minindex() = this.indexOf(this.minOrNull()!!)
fun IntArray.maxindex() = this.indexOf(this.maxOrNull()!!)
//4. more outputs
fun List<Char>.conca():String = this.joinToString("")
val CharArray.conca :String get() = this.concatToString()
val IntArray.conca :String get() = this.joinToString(" ")
@JvmName("concaInt")
fun List<Int>.conca():String = this.joinToString(" ")
val LongArray.conca:String get() = this.joinToString(" ")
@JvmName("concaLong")
fun List<Long>.conca():String = this.joinToString(" ")
@JvmName("concaString")
fun List<String>.conca():String = this.joinToString("")
//5. Simple calculations
val Boolean.chi:Int get() = if(this) 1 else 0 //characteristic function
val BooleanArray.chiarray:IntArray get() = IntArray(this.size){this[it].chi}
val Char.code :Int get() = this.toInt() -  'a'.toInt()
fun order(a:Int, b:Int):Pair<Int,Int> = Pair(minOf(a,b), maxOf(a,b))
val String.size get() = this.length
fun Int.has(i:Int):Boolean = (this and (1 shl i) != 0)
fun Long.has(i:Int):Boolean = (this and (1L shl i) != 0L)
fun dist(a: Int, b: Int): Int = if(a > b) a-b else b - a
fun dist(a: Long, b: Long): Long = if(a > b) a-b else b - a //chatgpt is right this is faster
//8 TIME
inline fun TIME(f:()->Unit){
    val t = measureTimeMillis{ f() }
    println("$t ms")
}
//9 rand
fun rand(x:Int) = Random.nextInt(x)
fun rand(x:IntRange) = Random.nextInt(x)
inline fun assert(x:Boolean,act:()->Any = {}){ if(!x) error(act())}
const val interactive = false
//11 Quick output
val YES:Unit get() = put("YES")
val NO:Unit get() = put("NO")
//12 Array put
fun IntArray.put(i:Int,v:Int){ this[i] = (this[i] + v).adjust() }
infix fun Long.up(other:Long):Long = if(other > this) other else this
infix fun Long.down(other:Long):Long = if(other < this) other else this
infix fun Int.up(other:Int):Int = if(other > this) other else this
infix fun Int.down(other:Int):Int = if(other < this) other else this
fun LongArray.up(i:Int, v:Long){ if(v > this[i]) this[i] =v  }
fun LongArray.down(i:Int, v:Long){ if(v < this[i]) this[i] = v}
fun IntArray.up(i:Int, v:Int){ if(v > this[i]) this[i] = v }
fun IntArray.down(i:Int, v:Int){ if(v< this[i]) this[i]= v }
@Target(AnnotationTarget.FUNCTION)  @Retention(AnnotationRetention.RUNTIME)  annotation class show
object Reader{
    private const val BS = 1 shl 16
    private const val NC = 0.toChar()
    private val buf = ByteArray(BS)
    private var bId = 0
    private var size = 0
    private var c = NC
    var lastTC:String = ""
    private var IN: BufferedInputStream = BufferedInputStream(System.`in`, BS)
    val OUT: PrintWriter = PrintWriter(System.out)
    private val char: Char
        get() {
            if(interactive){
                val c = System.`in`.read()
                return if(c == -1) NC else c.toChar()
            }
            while (bId == size) {
                size = IN.read(buf)
                if (size == -1) return NC
                bId = 0
            }
            return buf[bId++].toChar()
        } // Must terminate input process after reading two NCs
    fun nextLong(): Long {
        var neg = false
        if (c == NC) c = char
        while (c < '0' || c > '9') {
            if (c == '-') neg = true
            if(c == NC) throw Error("Bad Input")
            c = char
        }
        var res = 0L
        while (c in '0'..'9') {
            res = (res shl 3) + (res shl 1) + (c - '0')
            c = char
        }
        return if (neg) -res else res
    }
    fun nextString():String{
        val ret = StringBuilder()
        while (true){
            c = char
            if(c == NC) throw Error("Bad Input")
            if(!isWhitespace(c)){ break}
        }
        do{
            ret.append(c)
            c = char
        }while(!isWhitespace(c))
        return ret.toString()
    }
    private fun isWhitespace(c:Char):Boolean{
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == NC
    }
    fun flush(){
        OUT.flush()
    }
    fun takeString(str:String){
        lastTC = str
        IN = BufferedInputStream(lastTC.byteInputStream(),BS)
    }
    fun takeFile(name:String){
        IN = BufferedInputStream(File(name).inputStream(),BS)
    }
}
fun put(aa:Any){
    Reader.OUT.println(aa)
    if(interactive){ Reader.flush()}
}
fun put(vararg x:Any){
    val count = x.size
    for((pt, c) in x.withIndex()){
        Reader.OUT.print(c)
        if(pt + 1 != count) Reader.OUT.print(" ")
    }
    Reader.OUT.print("\n")
    if(interactive){ Reader.flush()}
}
fun done() = Reader.OUT.close()
val getint:Int get(){ val ans = getlong ; if(ans > Int.MAX_VALUE){IntArray(1000000000);error("Input Overflow")};return ans.toInt() }
val getlong:Long get() = Reader.nextLong()
val getstr:String get() = Reader.nextString()
fun getline(n:Int):IntArray = IntArray(n){getint}
fun getlineL(n:Int):LongArray = LongArray(n){getlong}
fun subformat(a:Any?):String{ // for not a collection
    return when(a) {
        null -> "null"
        is Iterable<*> -> a.joinToString(" ")
        is BooleanArray -> a.joinToString("") { if (it) "1" else "0" }
        is IntArray -> a.joinToString(" ")
        is LongArray -> a.joinToString(" ")
        else -> a.toString()
    } }
fun format(a:Any?):String { return when(a) {
    null -> "null"
    is BooleanArray -> a.joinToString("") { if (it) "1" else "0" }
    is Array<*> -> "\n"+a.joinToString("\n"){subformat(it)}
    else -> subformat(a)
} }
var dmark = -1
infix fun Any.dei(a:Any?){
    dmark++ ; debug()
    println("<${dmark}>   $this : ${format(a)}")
}
const val just = " "
fun keepimport(){ val st1 = TreeSet<Int>(); val st2 = TreeMap<Int,Int>(); val c = (3).absoluteValue}
inline fun cases(onecase:()->Unit){
    val count = if(singleCase) 1 else getint
    repeat(count){ onecase() }
}
fun share(aa:Any){solve.fakein.append(format(aa) + '\n')}
fun share(vararg x:Any){
    val count = x.size
    for((pt, c) in x.withIndex()){
        solve.fakein.append(format(c))
        if(pt + 1 != count) solve.fakein.append(' ')
    }
    solve.fakein.append('\n')
}
enum class solveMode { real, rand, tc }
object solve{
    private var randCount = 100
    private var mode:solveMode = solveMode.real
    private var tcNum:Int = 0
    val fakein = StringBuilder()
    var rand:()->Unit = {}
    private var TC:MutableMap<Int,()->Unit> = mutableMapOf()
    private var tn:Long = 0
    fun cases(onecase:()->Unit){
        val t = if(mode == solveMode.real){if(singleCase) 1 else getint} else if(mode == solveMode.tc){1 } else randCount
        if(mode != solveMode.real) tn = System.currentTimeMillis()
        repeat(t){
            if(mode != solveMode.real){
                if(mode == solveMode.tc){
                    TC[tcNum]?.let { it() }
                }else if(mode == solveMode.rand){
                    rand()
                }
                val tcstr = fakein.toString()
                fakein.clear()
                println("New Case ")
                println(tcstr.take(80))
                println("...")
                Reader.takeString(tcstr)
            }
            onecase()
        }
        if(mode != solveMode.real){
            val dt = System.currentTimeMillis() - tn
            put("Time $dt ms ")
        }
    }
    fun rand(a:()->Unit){
        this.rand = a
    }
    fun tc(id:Int = 0,a:()->Unit){
        TC[id] = a
    }
    fun usetc(a:Int = 0 ){
        this.tcNum = a
        this.mode = solveMode.tc
    }
    fun userand(count:Int = 100){
        this.randCount = count
        this.mode = solveMode.rand
    }
}
fun debug(){}

fun intPowEXP(x:Int,e:Long,m:Int):Int{
    var X = x
    var E =e
    var Y = 1
    while(E > 0){
        if(E % 2 == 0L){
            X = ((1L * X * X) % m).toInt()
            E = E shr 1
        }else{
            Y = ((1L * X * Y) % m).toInt()
            E -= 1
        }
    }
    return Y
}

fun pow(x:Long,e:Long,m:Long):Long{
    var X = x
    var E =e
    var Y = 1L
    while(E > 0){
        if(E % 2 == 0L){
            X = (X * X) % m
            E /= 2
        }else{
            Y = (X * Y) % m
            E -= 1
        }
    }
    return Y
}
class FACT{
    companion object {
        var store = IntArray(0)
        var invStore = IntArray(0)

        var slowStore:IntArray = IntArray(0)

        fun preCal(upto:Int){
            store = IntArray(upto+1)
            invStore = IntArray(upto + 1 )
            store[0] = 1
            invStore[0] = 1

            for(i in 1..upto) {
                store[i] = store[i-1] mm i
            }
            invStore[upto] = store[upto].inverse()
            for(i in upto-1 downTo 1){
                invStore[i] = invStore[i+1] mm (i+1)
            }
        }
        fun choose(n:Int,r:Int):Int{
            if(r < 0 || r > n) return 0
            val a = store[n]
            val b = invStore[n-r]
            val c = invStore[r]
            return (a mm b) mm c
        }

        fun bigChoose(n:Int,r:Int):Int{
            var ret = 1
            for(i in 0 until r){
                ret = ret mm (n - i)
            }
            ret = ret mm (invStore[r])
            return ret
        }
        fun veryBigChoose(n:Long,r:Int):Int{
            var ret = 1
            for(i in 0 until r){
                ret = ret mm ((n - i) % p).toInt()
            }
            ret = ret mm (invStore[r])
            return ret
        }

    }
}

class Graph(val n:Int, val m:Int, val directed:Boolean) {
    val maxedge = if (directed) m else m * 2
    /* directed edge counts, therefore possibly doublede*/
    var edgecount = 0
    val next = IntArray(maxedge)
    val head = IntArray(n) { -1 }
    val to = IntArray(maxedge)
    val from = IntArray(maxedge)
    val weights = IntArray(if (graphWeighed) m else 0) // Weights access always need shr
    private fun primitive_add(u: Int, v: Int): Int {
        next[edgecount] = head[u]
        head[u] = edgecount
        to[edgecount] = v
        from[edgecount] = u
        edgecount++
        return edgecount -1
    }
    fun add(u: Int, v: Int): Int { // return edge number, actual
        val e = primitive_add(u, v)
        if (!directed) primitive_add(v, u)
        return e
    }
    fun addWeighted(u: Int, v: Int, w: Int):Int{
        val e = add(u, v)
        weights[if(directed) e else (e shr 1)] = w
        return e
    }
    //Basic Transversals
    inline fun NS(a:Int, act:(Int)->Unit){
        var i= head[a]
        while(i != -1){
            act(to[i])
            i = next[i]
        }
    }
    inline fun NS_E(a:Int, act:(e:Int,v:Int)->Unit){
        var i= head[a]
        while(i != -1){
            act(i,to[i])
            i = next[i]
        }
    }
    inline fun everyEdge(act:(a:Int, b:Int, e:Int)->Unit){
        val s = if(directed) 1 else 2
        for(e in 0 until edgecount step s ){
            act(from[e], to[e], e)
        }
    }
    inline fun everyDirectedEdge(act:(a:Int, b:Int)->Unit){
        for(e in 0 until edgecount){
            act(from[e], to[e])
        }
    }
    //DFS order Related
    var root = 0
    var hasDFSorder:Boolean = false
    val preorder = IntArray(n){-1}
    var postorder = IntArray(0)
    val parent = IntArray(n){-1}
    val parentEdge:IntArray = IntArray(n){-1} //if undirected, this is upto 2m
    //the edge oriented from parent to here
    //if v is root of component, parent[v] = v , parentEdge[v] = -1
    fun dfstree(singleComponent:Boolean){
        //if singleComponent: perform search from the root, otherwise, search from 0 through n
        hasDFSorder = true
        var preorderpt = 0
        val explored = BooleanArray(n)
        val S = IntArray(m+1) //did not -1 because if out of bounds, good luck
        var pt = 0
        parent.fill(-1)
        for(i in 0 until n){
            if(parent[i] != -1 || (singleComponent && i != root)) continue
            S[pt++] = i
            parent[i] = i
            parentEdge[i] = -1
            while(pt > 0){
                val v = S[--pt]
                if(explored[v]){
                    continue
                }
                preorder[preorderpt++] = v
                explored[v] = true
                NS_E(v){e,w ->
                    if(!explored[w]){
                        S[pt++] = w
                        parent[w] = v
                        parentEdge[w] = e
                    }
                }
            }
        }
    }
    fun treeOrderDFS() = dfstree(true)
    fun dfsTreePostOrder(singleComponent:Boolean){
        //if singleComponent: perform search from the root, otherwise, search from 0 through n
        hasDFSorder = true
        var preorderpt = 0
        val explored = BooleanArray(n)
        val S = IntArray(m+1)
        val Spopped = BooleanArray(m+1)
        var pt = 0
        var postorderpt = 0
        postorder = IntArray(n){-1}
        parent.fill(-1)
        for(i in 0 until n){
            if(parent[i] != -1 || (singleComponent && i != root)) continue
            Spopped[pt] = false
            S[pt++] = i
            parent[i] = i
            parentEdge[i] = -1
            while(pt > 0){
                val v = S[--pt]
                if(Spopped[pt]){
                    postorder[postorderpt++] = v
                }
                if(explored[v]){
                    continue
                }
                preorder[preorderpt++] = v
                Spopped[pt] = true
                explored[v] = true
                pt++
                NS_E(v){e,w ->
                    if(!explored[w]){
                        Spopped[pt] = false
                        S[pt++] = w
                        parent[w] = v
                        parentEdge[w] = e
                    }
                }
            }
        }
    }
    inline fun BFS(distRoot:Int): IntArray {
        val Q = IntArray(n)
        val explored = IntArray(n){-1} // also store parents
        val dist = IntArray(n){plarge}
        var startpt = 0
        var endpt = -1
        //
        Q[++endpt] = distRoot
        explored[distRoot] = distRoot
        dist[distRoot] = 0
        //
        while(startpt <= endpt){
            val x = Q[startpt++]
            NS(x){ a->
                if(explored[a] == -1){
                    explored[a] = x
                    dist[a] = dist[x] + 1
                    Q[++endpt] = a
                }
            }
        }
        return dist
    }
    //Tree Transversals
    inline fun leafFirst(act:(Int)->Unit){
        if(!hasDFSorder) treeOrderDFS()
        for(i in preorder.lastIndex downTo 0){
            act(preorder[i])
        }
    }
    inline fun rootFirst(act:(Int)->Unit){
        if(!hasDFSorder) treeOrderDFS()
        for(a in preorder){
            act(a)
        }
    }
    inline fun rootFirstEdge(act:(from:Int, to:Int, e:Int)->Unit){
        if(!hasDFSorder) treeOrderDFS()
        for(i in preorder.indices) {
            val v = preorder[i]
            val p = parent[v]
            if(p == v) continue
            act(p,v,parentEdge[v])
        }
    }
    // Basic invariants maintaining
    fun calculateSizes():IntArray{
        val ret = IntArray(n){1}
        leafFirst { v -> if(parent[v] != v) ret[parent[v]] += ret[v] }
        return ret
    }
    fun calculateSubtreeSum(weights:IntArray){
        leafFirst { v -> if(parent[v] != v) weights[parent[v]] += weights[v] }
    }
    fun calculateDepth(): IntArray {
        val ret = IntArray(n)
        rootFirst { v -> if(parent[v] != v) ret[v] = ret[parent[v]] + 1  }
        return ret
    }
    inline fun subs(v:Int, act:(Int)->Unit){
        NS(v){w ->
            if(w != parent[v]) act(w)
        }
    }
    fun calculateDepthWeighted(): LongArray {
        val ret = LongArray(n)
        rootFirstEdge{from,to,e -> ret[to] = ret[from] + weights[e]}
        return ret
    }
    fun outdegree():IntArray{
        val ret = IntArray(n)
        everyDirectedEdge { a, b -> ret[a] ++  }
        return ret
    }
    fun indegree():IntArray{
        val ret = IntArray(n)
        everyDirectedEdge {a, b -> ret[b] ++}
        return ret
    }
    fun degree():IntArray = outdegree()
    fun intime():IntArray{
        val tin = IntArray(n)
        if(!hasDFSorder) treeOrderDFS()
        for(i in 0 until n) tin[preorder[i]] = i
        return tin
    }
    fun outtime():IntArray{
        val tout = intime()
        leafFirst { v ->
            val p = parent[v]
            if(p != v) tout[p] = maxOf(tout[p], tout[v])
        }
        return tout
    }
    fun rootComponent():IntArray{
        val ret = IntArray(n)
        rootFirst { v ->
            if(v == root) return@rootFirst
            if(parent[v] == root){ ret[v] = v; return@rootFirst}
            ret[v] = ret[parent[v]]
        }
        return ret
    }
    fun compress(map:IntArray): Graph {
        val max = map.maxOrNull()!!
        val G = Graph(max + 1,this.m,this.directed)
        this.everyEdge { a, b,_ ->
            if(map[a] != map[b]){
                G.add(map[a],map[b])
            }
        }
        return G
    }
}
const val graphWeighed = false


class BIT(val n:Int){
    val A= IntArray(n+1)
    fun add(i:Int, v:Int){
        var i = i; i++;
        while(i <= n){ A[i] += v; i += i and (-i)}
    }
    fun set(i:Int, v:Int){
        add(i,v-sumquery(i,i))
    }
    fun presum(i:Int): Int {
        var ret = 0; var i = i; i ++;
        while(i != 0){ ret += A[i]; i -= i and (-i)}
        return ret
    }
    fun sumquery(l:Int, r:Int):Int{
        if(l > r) return 0
        return presum(r) - presum(l-1)
    }
    override fun toString(): String {
        return (0 until n).map { sumquery(it,it)  }.joinToString(" ")
    }
}

fun lead(x:Int):Int = 1 shl ( 31 - x.countLeadingZeroBits()) // could also be implementd with bits
fun BIT.order(i:Int):Int?{
    if(i < 0) return null
    var res = i
    var now = 0
    while(now != n){
        val next = lead(n-now)
        if(res < A[now + next]){
            var b = next shr 1
            while(b > 0){
                if(res >= A[now+b]){
                    now += b
                    res -= A[now]
                }
                b = b shr 1
            }
            return now
        }
        now += next
        res -= A[now]
    }
    return null
}
fun BIT.ceil(i:Int):Int? =  order(presum(i-1))
fun BIT.floor(i:Int):Int? = order(presum(i)-1)
fun BIT.higher(i:Int):Int? = ceil(i+1)
fun BIT.lower(i:Int):Int? = floor(i-1)
fun BIT.fakeRangeAdd(l:Int, r:Int,v:Int){
    this.add(l, v)
    if(r +1 in 0 until this.n) this.add(r +1, -v )
}
fun BIT.fakePointQuery(v:Int):Int{
    return this.presum(v)
}

fun Graph.tsort(): IntArray {
    dfsTreePostOrder(false)
    return postorder.reversedArray()
}
fun Graph.eulersweep(L:IntArray):IntArray{

//    val inside = BIT(n)
    val inside = mint
    val tsort = Graph(n , 2 * n , true)
    var size =0
    fun out(v:Int){ //called once: when the point first leave subtree of v
//        inside.add(v, -1)
        inside.removeAt(L[v])
        size --
    }
    fun goin(v:Int){ //called once: when the point first enter subtree of v
        val here = L[v]
        if(here > 0){

            val a = inside[here-1]
//            val a = inside.order(here-1)!!
            val b = v
//            just dei "$a $b "
            tsort.add(a,b)
        }
        if(here < size){
            val a= v
            val b = inside[here]
//            val b = inside.order(here)!!
//            just dei "$a $b "
            tsort.add(a,b)
        }
        inside.add(here, v)
//        inside.add(v,1)
        size++
    }
    goin(root)
    val depth = calculateDepth()
    var now = root
    rootFirst { v ->
        if(v != root){
            while(depth[now] != depth[v] - 1) {
                out(now)
                now = parent[now]
            }
            goin(v)
            now = v
        }
        // everything ok here
    }
    while(now != root){
        out(now)
        now = parent[now]
    }
    out(root)
    val order = tsort.tsort()
    val time = ints(n)
    for((i,v) in order.withIndex()){
        time[v] = i
    }
    return time
}

inline fun bfirst(l:Int, r:Int, isTrue:(Int)->Boolean):Int?{
    assert(l<=r);var L = l ;var R = r+1
    while(L < R){ val m = (L + R) shr 1;if(isTrue(m)) R = m else L = m + 1 }
    return if(L == r+1) null else L
}
inline fun blast(l:Int, r:Int, isTrue:(Int)->Boolean):Int?{
    assert(l<=r);var L = l-1 ;var R = r
    while(L < R){ val m = ((L + R) shr 1) + 1;if(isTrue(m)) L = m else R = m-1 }
    return if(L == l-1) null else L
}
inline fun bfirst(l:Long, r:Long, isTrue:(Long)->Boolean):Long?{
    assert(l<=r);var L = l ;var R = r+1
    while(L < R){ val m = (L + R) shr 1;if(isTrue(m)) R = m else L = m + 1 }
    return if(L == r+1) null else L
}
inline fun blast(l:Long, r:Long, isTrue:(Long)->Boolean):Long?{
    assert(l<=r);var L = l-1 ;var R = r
    while(L < R){ val m = ((L + R) shr 1) + 1;if(isTrue(m)) L = m else R = m-1 }
    return if(L == l-1) null else L
}
const val p = 998244353
const val singleCase = false
fun main(){
    FACT.preCal(200005)
    solve.cases{

        val n= getint

        val G = Graph(n,n-1,false)
        for(i in 1 until n){
            G.add(i, getint-1)
        }
        val L = getline(n)

        val code = G.eulersweep(L)

        val tin = G.intime()
        val tout = G.outtime()
        fun isancestor(v:Int,of:Int):Boolean{
            return tin[v] <= tin[of] && tout[v] >= tout[of]
        }

//        just dei code

        var ret = 1
        for(v in 0 until n){
            val items = mint
            for(i in 0 until n){
                if(isancestor(i, v)){
                    items.add(code[i])
                }
            }
            items.sort()
            val rn = items.size

            val types = ints(rn+1)
            var local = 1

            G.subs(v){c ->
                val typenow = ints(rn+1)
                for(t in tin[c]..tout[c]){
                    val w = G.preorder[t]
                    val cd = code[w]

//                    just dei cd
                    val bin = bfirst(0,rn-1){items[it] > cd} ?: rn
                    typenow[bin]++
                }

//                v dei "adding"
//                v dei types
//                v dei typenow
                for(i in 0..rn){
                    local = local mm FACT.choose(types[i] + typenow[i], typenow[i])
                    types[i] += typenow[i]
                }
            }
//            v dei local
            ret = ret mm local
        }
        put(ret)

        // give any tsort










    }
    done()
}
/*
top down?
bottom up ?
 
chain decompose?
 
 
 
3 6
 
1/2 4/5 7/8
 
how many smaller, how many bigger
 
 
 
 
1
5
1 2 1 1
0 1 0 0 0
 
1
8
1 1 3 3 4 5 7
0 0 1 0 1 3 3 1
 
 
4 6 3
 
2/5
0 7
 */
