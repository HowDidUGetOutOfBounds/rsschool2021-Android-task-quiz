package com.rsschool.quiz

object Utills {
   val FRAGMENT_ID = "fragment_id_key"
   val FRAGMENT_STYLE = "fragment_style_key"
   val questionsList = arrayListOf<String>("Какой танк был введен в игру позже других?",
      "Какой танк раньше был выше уровнем?",
      "Какое самое высокое пробитие в игре?",
      "Сколько урона с выстрела у танка Tiger-1 в топовой комплектации?",
      "Что из перечисленного, не является танком?")
   val answers: ArrayList<ArrayList<String>> = arrayListOf(
      arrayListOf("Maus","Grille 15","112","Об. 907","T60"),
      arrayListOf("T30","Valentine","T150","MC-1","PZ-II"),
      arrayListOf("420","299","395","290","432"),
      arrayListOf("320","150","390","280","240"),
      arrayListOf("AT-2","Type-58","T-28","M4 Sherman","Mark-1")
   )
   val answersList = arrayListOf<Int>(5,2,1,4,1)
}