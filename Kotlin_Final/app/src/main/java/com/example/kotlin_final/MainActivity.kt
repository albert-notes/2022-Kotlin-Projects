package com.example.kotlin_final

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import java.util.*
import java.util.logging.Handler

class MainActivity : AppCompatActivity() {

    private val text1 by lazy { findViewById<TextView>(R.id.text1) }
    private val text2 by lazy { findViewById<TextView>(R.id.text2) }
    private val text3 by lazy { findViewById<TextView>(R.id.text3) }
    private val text4 by lazy { findViewById<TextView>(R.id.text4) }
    private val text5 by lazy { findViewById<TextView>(R.id.text5) }
    private val text6 by lazy { findViewById<TextView>(R.id.text6) }
    private val text7 by lazy { findViewById<TextView>(R.id.text7) }
    private val text8 by lazy { findViewById<TextView>(R.id.text8) }
    private val text9 by lazy { findViewById<TextView>(R.id.text9) }
    private val text10 by lazy { findViewById<TextView>(R.id.text10) }
    private val text11 by lazy { findViewById<TextView>(R.id.text11) }
    private val text12 by lazy { findViewById<TextView>(R.id.text12) }
    private val text13 by lazy { findViewById<TextView>(R.id.text13) }
    private val text14 by lazy { findViewById<TextView>(R.id.text14) }
    private val text15 by lazy { findViewById<TextView>(R.id.text15) }
    private val text16 by lazy { findViewById<TextView>(R.id.text16) }
    private val text17 by lazy { findViewById<TextView>(R.id.text17) }
    private val text18 by lazy { findViewById<TextView>(R.id.text18) }
    private val text19 by lazy { findViewById<TextView>(R.id.text19) }
    private val text20 by lazy { findViewById<TextView>(R.id.text20) }
    private val text21 by lazy { findViewById<TextView>(R.id.text21) }
    private val text22 by lazy { findViewById<TextView>(R.id.text22) }
    private val text23 by lazy { findViewById<TextView>(R.id.text23) }
    private val text24 by lazy { findViewById<TextView>(R.id.text24) }
    private val text25 by lazy { findViewById<TextView>(R.id.text25) }
    private val text26 by lazy { findViewById<TextView>(R.id.text26) }
    private val text27 by lazy { findViewById<TextView>(R.id.text27) }
    private val text28 by lazy { findViewById<TextView>(R.id.text28) }
    private val text29 by lazy { findViewById<TextView>(R.id.text29) }
    private val text30 by lazy { findViewById<TextView>(R.id.text30) }
    private val text31 by lazy { findViewById<TextView>(R.id.text31) }
    private val text32 by lazy { findViewById<TextView>(R.id.text32) }
    private val text33 by lazy { findViewById<TextView>(R.id.text33) }
    private val text34 by lazy { findViewById<TextView>(R.id.text34) }
    private val text35 by lazy { findViewById<TextView>(R.id.text35) }
    private val text36 by lazy { findViewById<TextView>(R.id.text36) }

    private val corres by lazy { listOf<TextView>(text1,text2,text3,text4,text5,text6,text7,text8,text9,text10,text11,text12,text13,text14,text15,text16,text17,text18,text19,text20,text21,text22,text23,text24,text25,text26,text27,text28,text29,text30,text31,text32,text33,text34,text35,text36) }

    private var ans = ""
    private var idx = 0
    private var times = 0
    private var win = false
    private var eq_pos = -1
    private var eq_times = 0
    private var op_pos = -1
    private var op_times = 0

    private fun prt(p: String){
        if(idx != times*6+6){
            if(p=="+"||p=="-"||p=="*"||p=="/"){
                op_pos = idx
                op_times++
            }else if(p=="="){
                eq_pos = idx
                eq_times++
            }
        }
        if(idx>=times*6 && idx<=times*6+5 && times<6 && !win) {
            corres[idx].text = p
            idx++
        }
    }

    private fun del(){
        if(!win && idx>times*6){
            idx--
            if(corres[idx].text=="+"||corres[idx].text=="-"||corres[idx].text=="*"||corres[idx].text=="/"){
                op_times--
            }else if(corres[idx].text=="="){
                eq_times--
            }
            corres[idx].text=""
        }
    }

    private fun ent(){
        //deal with illegal inputs
        //length isn't six
        if(idx%6!=0) {
            AlertDialog.Builder(this)
                .setTitle("Length Error")
                .setMessage("The length of your input should be 6.")
                .show()
            return
        }
        //negative numbers
        if(corres[6*times].text=="-" || corres[eq_pos+1].text=="-" || corres[op_pos+1].text=="-"){
            AlertDialog.Builder(this)
                .setTitle("Negative Numbers Error")
                .setMessage("There should not be any negative numbers in the equation.")
                .show()
            return
        }
        //more than one or no =
        if(eq_times != 1){
            AlertDialog.Builder(this)
                .setTitle("Equation Error")
                .setMessage("There should be only ONE = sign in the equation.")
                .show()
            return
        }
        //more than one or no operator
        if(op_times != 1){
            AlertDialog.Builder(this)
                .setTitle("Operator Error")
                .setMessage("There should be only ONE operator.")
                .show()
            return
        }
        //position wrong
        if(eq_pos<op_pos){
            AlertDialog.Builder(this)
                .setTitle("Position Error")
                .setMessage("The operator should be frontier than the = sign.")
                .show()
            return
        }
        //leading zero
        if((corres[times*6].text=="0" && times*6+1!=op_pos)||(corres[op_pos+1].text=="0" && op_pos+2!=eq_pos)||(corres[eq_pos+1].text=="0" && times*6+4!=eq_pos)){
            AlertDialog.Builder(this)
                .setTitle("Leading Zero Error")
                .setMessage("There should not be any leading zero in the equation.")
                .show()
            return
        }
        //divide by zero
        if(corres[op_pos].text=="/" && corres[op_pos+1].text=="0"){
            AlertDialog.Builder(this)
                .setTitle("Divide by Zero Error")
                .setMessage("Do not divide a number by zero.")
                .show()
            return
        }
        //mathematical wrong
        var a=""
        var b=""
        var c=""
        var check = 0
        for(i in times*6..op_pos-1) a+=corres[i].text
        for(i in op_pos+1..eq_pos-1) b+=corres[i].text
        for(i in eq_pos+1..times*6+5) c+=corres[i].text
        if(corres[op_pos].text=="+") check=a.toInt()+b.toInt()
        else if(corres[op_pos].text=="-") check=a.toInt()-b.toInt()
        else if(corres[op_pos].text=="*") check=a.toInt()*b.toInt()
        else if(corres[op_pos].text=="/") check=a.toInt()/b.toInt()
        if(check!=c.toInt()) {
            AlertDialog.Builder(this)
                .setTitle("Mathematical Error")
                .setMessage("Your equation is wrong.")
                .show()
            return
        }
        //no error
        var user_ans = ""
        for(i in times*6..times*6+5) user_ans+=corres[i].text
        if(user_ans==ans){
            win = true
            for(i in times*6..times*6+5){
                corres[i].setBackgroundColor(Color.parseColor("#398874"))
                corres[i].setTextColor(Color.parseColor("#FFFFFF"))
            }

            AlertDialog.Builder(this)
                .setTitle("Congratulations!")
                .setMessage("You have won this game! \uD83E\uDD73")
                .setPositiveButton("Exit") { _, _ -> System.exit(0); }
                .show()
        }
        var crt = mutableListOf<Int>()
        var num_crt = mutableListOf<Int>()
        var blacklist = mutableListOf<Int>()
        for(i in 0..5){
            if(ans[i]==user_ans[i]) crt.add(i)
            else if(user_ans[i] in ans){
                num_crt.add(i)
            }else{
                blacklist.add(i)
            }
        }
        for(i in crt){
            corres[times*6+i].setBackgroundColor(Color.parseColor("#398874"))
            corres[times*6+i].setTextColor(Color.parseColor("#FFFFFF"))
        }
        for(i in num_crt){
            corres[times*6+i].setBackgroundColor(Color.parseColor("#820458"))
            corres[times*6+i].setTextColor(Color.parseColor("#FFFFFF"))
        }
        for(i in blacklist){
            corres[times*6+i].setBackgroundColor(Color.parseColor("#161803"))
            corres[times*6+i].setTextColor(Color.parseColor("#FFFFFF"))
        }
        times++
        if(times==6 && !win){
            AlertDialog.Builder(this)
                .setTitle("Oh, You Losed...")
                .setMessage("You have only 6 chances... Try again later!\nThe correct answer is $ans")
                .setPositiveButton("Exit") { _, _ -> System.exit(0); }
                .show()
        }
        eq_times = 0
        op_times = 0
        eq_pos = -1
        op_pos = -1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scanner = Scanner(resources.openRawResource(R.raw.ans))
        val answer = mutableListOf<String>().apply {
            while(scanner.hasNext()){
                add(scanner.next())
            }
        }

        ans = answer.random()
        /*
        AlertDialog.Builder(this)
            .setTitle("The answer is ...")
            .setMessage(ans)
            .show()
        */
        findViewById<Button>(R.id.button0).setOnClickListener { prt("0") }
        findViewById<Button>(R.id.button1).setOnClickListener { prt("1") }
        findViewById<Button>(R.id.button2).setOnClickListener { prt("2") }
        findViewById<Button>(R.id.button3).setOnClickListener { prt("3") }
        findViewById<Button>(R.id.button4).setOnClickListener { prt("4") }
        findViewById<Button>(R.id.button5).setOnClickListener { prt("5") }
        findViewById<Button>(R.id.button6).setOnClickListener { prt("6") }
        findViewById<Button>(R.id.button7).setOnClickListener { prt("7") }
        findViewById<Button>(R.id.button8).setOnClickListener { prt("8") }
        findViewById<Button>(R.id.button9).setOnClickListener { prt("9") }
        findViewById<Button>(R.id.button_add).setOnClickListener { prt("+") }
        findViewById<Button>(R.id.button_sub).setOnClickListener { prt("-") }
        findViewById<Button>(R.id.button_mul).setOnClickListener { prt("*") }
        findViewById<Button>(R.id.button_quo).setOnClickListener { prt("/") }
        findViewById<Button>(R.id.button_eq).setOnClickListener { prt("=") }
        findViewById<Button>(R.id.button_del).setOnClickListener { del() }
        findViewById<Button>(R.id.button_enter).setOnClickListener{ ent() }

    }
}