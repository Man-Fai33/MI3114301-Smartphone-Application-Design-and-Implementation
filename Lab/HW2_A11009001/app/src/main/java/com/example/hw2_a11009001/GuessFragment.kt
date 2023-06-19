package com.example.hw2_a11009001

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

class GuessFragment :AppCompatActivity (){
    private  var stage:Int=1
    private var score:Int=0
    private final var Attempts:Int=11;
    private var guesnum:List<Int> = listOf();
    private lateinit var btn_guess:Button
    private lateinit var txt_score:TextView
    private lateinit var txt_stage:TextView
    private lateinit var txt_attempts:TextView
    private lateinit var edit_guess:EditText
    private  var stageList:List<Stage> = listOf()
    private var recordList:List<Record> = listOf()

    private var dataList:List<Data> = listOf()

    private  lateinit var viewModel : DataViewModel

    override fun onCreate(savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.guessfragment)



        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        txt_score=findViewById(R.id.txt_score)
        txt_stage=findViewById(R.id.txt_stage)
        txt_attempts=findViewById(R.id.txt_attempts)
        edit_guess =findViewById(R.id.edit_guessnum)
        btn_guess=findViewById(R.id.btn_guess)
        guesnum=getRandomNun()
        val intent = Intent(this, ResultFragment::class.java)

        var recyclerView=findViewById<RecyclerView>(R.id.Recordlist_item)
        if( intent.getSerializableExtra("data") !=null ) {
            dataList = intent.getSerializableExtra("data") as ArrayList<Data>
        }


        btn_guess.setOnClickListener {
            if(edit_guess.text.toString().equals("") || edit_guess.text.length<=3){
                Toast.makeText(this, "Please input 4 number", Toast.LENGTH_SHORT).show()
            }  else {
                if((stage)==5){
                viewModel = ViewModelProvider(this).get(DataViewModel::class.java)
                dataList+=Data(dataList.size+1,score,stageList)
                viewModel.updateDataList(dataList)

            intent.putExtra("data",ArrayList(dataList))
                startActivity(intent)
            }else if((stage)<=5){
                var stringab= checkNum(edit_guess.text.toString())
            if(stringab.get(0).toString() =="4"){
                CorrectGuess(stringab)
            }else{
                WrongGuess(stringab)
            }
            }
            val list =recordList.reversed()
            recyclerView.adapter = Recordlist_item(list)
            }
        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun getRandomNun(): List<Int> {
        val num= mutableListOf<Int>(0,1,2,3,4,5,6,7,8,9)
        num.shuffle()
        return num.take(4)
    }
    fun CorrectGuess(string: String) {
        Attempts--
        score+=25
        stageList+= Stage((stageList.size+1),  IntArraytoString(guesnum),Attempts, recordList)

        handleEndStage()
        showInfo()


    }
    fun WrongGuess( string :String){
        if(Attempts==1){
            handleEndStage()
            recordList= listOf()
            stageList+= Stage((stageList.size+1),  IntArraytoString(guesnum),Attempts-1, recordList)

            return
        }
        println(guesnum)

        Attempts--
        showInfo()
        recordList+= Record((recordList.size).toString(),edit_guess.text.toString(),string)

    }
    fun handleEndStage(){
        guesnum=getRandomNun()
        Attempts=10
        stage+=1
        recordList= listOf()
    }

    fun checkNum(guess: String):String{
        var a:Int =0
        var b:Int=0
        for (i in 0..3){
            if(guesnum[i].toString() ==guess.get(i).toString()){
                a++
            }else {
                for (num in guesnum) {
                    if (num.toString() == guess.get(i).toString()) {
                        b++
                        break
                    }
                }
            }
        }
    return ""+a+"A"+b+"B"
    }


    fun showInfo(){
        txt_score.setText("Score: "+score )
        txt_attempts.setText("Attempts: "+Attempts)
        txt_stage.setText("Stage: "+stage+"/4")
    }
    fun IntArraytoString(intarry:List<Int>):String{

        var stringnum= ""
        for (int in intarry){
            stringnum+=int.toString()
        }
        return stringnum
    }
}


