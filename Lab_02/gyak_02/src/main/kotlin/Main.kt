import java.util.function.ToIntFunction
import Date
import java.util.*

fun String.monogram(): String {
    val elso = this.split(" ")
    return elso.get(0).first().toString() + "." + elso.get(1).first().toString()
}

fun List<String>.separateString(separat: String): String = this.joinToString(separator = separat) { it }

fun List<String>.longestString(): String = this.maxBy { it.length }

fun random(from: Int, to: Int) : Int {
    return Random().nextInt(to - from) + from
}

fun main(args: Array<String>) {

    // Problem 2
    val proba = "John Smith"
    println("Problem 2 -  Monogram: " + proba.monogram())

    // Problem 2
    val problem2String = listOf<String>("apple", "pear", "melon", "valami", "kocsi", "ezaleghosszabbszo")
    println(problem2String.separateString("#"))

    // Problem 2
    println(problem2String.longestString())

    // Problem 3 Date class
    val randomDates : MutableList<Date> = mutableListOf()
    while (randomDates.size != 10)
    {
        val date = Date(random(10,2022),random(-10,40),random(-20,50))
        if (checkValidDate(date.year,date.month,date.day))
        randomDates.add(date)
        else
            println(date.year.toString() + "/" + date.month.toString()  + "/"+ date.day.toString())
    }

    // The right list
    randomDates.forEach { println(it) }





    //Problem 1
    val dict: IDictionary = ListDictionary
    println("Number of words: ${dict.size()}")
    var word: String?
    while (true) {
        print("What to find? ")
        word = readLine()
        if (word.equals("quit")) {
            break
        }
        println("Result: ${word?.let { dict.find(it) }}")
    }
}