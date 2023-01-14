import java.util.Scanner
import java.io.File

val grn_arr = mutableMapOf<Int, String>()
val yel_arr = mutableListOf<String>()
val blacklist = mutableSetOf<String>()

fun main(){
    val scanner = Scanner(System.`in`)
    while(true){
        val alpha=scanner.next()
        if(alpha=="END") break
        val pos=scanner.nextInt()
        //Didn't deal with NOT INT & MORE THAN 2 input
        if(alpha.length>1||alpha>"z"||alpha<"a"||pos>4||pos<0) {
            println("Your input is invalid, please enter again.")
            continue
        }
        grn_arr[pos]=alpha
    }
    while(true){
        val alpha=scanner.next()
        if(alpha=="END") break
        val pos=scanner.nextInt()
        //Didn't deal with NOT INT & MORE THAN 2 input
        if(alpha.length>1||alpha>"z"||alpha<"a"||pos>4||pos<0) {
            println("Your input is invalid, please enter again.")
            continue
        }
        yel_arr.add(alpha+pos.toString())
    }
    while(true){
        val alpha=scanner.next()
        if(alpha=="END") break
        blacklist+=setOf(alpha)
    }
    val filename="src/words.txt"
    val word=File(filename).readLines()
    for(i in word){
        var flag=1
        for(j in grn_arr){
            if(i[j.key].toString()!=j.value) flag=0
        }
        for(j in yel_arr){
            if(i[j[1].toString().toInt()]==j[0] || !i.contains(j[0])) flag=0
        }
        for(j in blacklist){
            if(i.contains(j)) flag=0
        }
        if(flag==1){
            println(i)
        }
    }
}