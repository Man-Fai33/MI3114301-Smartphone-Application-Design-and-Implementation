package com.example.hw1

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes

data class DiceList (  @DrawableRes val imageResourceId: Int)

 class Dices(var dice: List<Int>, sum: Int ) {
     var list: List<DiceList> = loaddata(dice);
     var sum: Int = sum
//
//     constructor(list: List<Int>, sum: Int,point:Int) : this(list,sum) {
//
//         this.list = loaddata(list)
//         this.sum=sum
//     }
     fun  loaddata( list:List<Int> ):List<DiceList>{
         var itemlist = listOf<DiceList>();
         for (item in list){
             val drawableResource = when (item) {
                 1 -> R.drawable.dice_1
                 2 -> R.drawable.dice_2
                 3 -> R.drawable.dice_3
                 4 -> R.drawable.dice_4
                 5 -> R.drawable.dice_5
                 else -> R.drawable.dice_6
             }


             itemlist +=DiceList(  drawableResource)
         }


         println(itemlist)
         return  itemlist
     }
     fun getlist():List<DiceList>{
         return list
     }
 }