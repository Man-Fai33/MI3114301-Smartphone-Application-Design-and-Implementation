package com.example.hw1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val txt_dieNum: TextView =findViewById(R.id.dillnum)
        val btn_minus: Button =findViewById(R.id.minus)

        //done
        btn_minus.setOnClickListener{
            var minusNum:Int = Integer.parseInt( txt_dieNum.getText().toString());
            if(checknum( minusNum-1)==true){
                txt_dieNum.setText(( minusNum-1).toString() )
            }

        }
        //done
        val btn_add:Button =findViewById(R.id.add)
        btn_add.setOnClickListener{
            var addNum:Int = Integer.parseInt( txt_dieNum.getText().toString());
            if(checknum( addNum+1)){

                txt_dieNum.setText(( addNum+1).toString() )
            }
        }
        val btn_roll:Button =findViewById(R.id.rolldie)
//        val  recyclerView =findViewById<RecyclerView>(R.id.recyclerView)
        var myList: MutableList<Dices> = mutableListOf()
        val list: List<Dices> = listOf()

        btn_roll.setOnClickListener{
            var num:Int = Integer.parseInt( txt_dieNum.getText().toString());
            var dice = rollDice(num)

            val myDataset = Dices(dice,dice.sum())
            myList.add(myDataset )

            val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
            val layoutManager = LinearLayoutManager(this)
            val list =myList.reversed()
            recyclerView.adapter = ListAdapter( this, list as MutableList<Dices>)
            recyclerView.layoutManager = layoutManager



        }

        btn_roll.setOnLongClickListener {
            myList=mutableListOf()
            Toast.makeText(this, "Clear!", Toast.LENGTH_SHORT).show()

            val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
            recyclerView.adapter = null
            recyclerView.removeAllViews()
            true
        }
    }




    //function
    fun checknum(num: Int):Boolean{
        val checker:Boolean  = if ( num>=1 && num<=10)  true else false ;
        return checker
    }
    private fun rollDice(counter:Int):List<Int> {
        val dice = Dice(6)
        var dicelist = listOf<Int>();
        for (i in 1.. counter ){
            dicelist += dice.roll();
        }

        return dicelist
    }
    class Dice(val numSides: Int) { fun roll(): Int {
//        return Dices ((1..numSides).random())
              return (1..numSides).random()
    }
    }
}


