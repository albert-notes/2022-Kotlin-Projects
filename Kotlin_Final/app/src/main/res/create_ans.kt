fun main(){
    val ans = mutableListOf<String>()
    for(a in 0..99){
        for(b in 0..99){
            val sum = "$a+$b=${a+b}"
            if(sum.length == 6) ans.add(sum)
            val sub = "$a-$b=${a-b}"
            if(sub.length == 6) ans.add(sub)
            val mul = "$a*$b=${a*b}"
            if(mul.length == 6) ans.add(mul)
            if(b != 0 && a%b == 0){
                val quo = "$a/$b=${a/b}"
                if(quo.length == 6) ans.add(mul)
            }
        }
    }
    println(ans)
}