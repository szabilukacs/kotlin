/**
 * You can edit, run, and share this code.
 * play.kotlinlang.org
 */
fun lab_01() {
    val a = 2
    val b = 7
    val c = a+b
    val s1 = "$a + $b = $c" 
    println(s1)
}

fun lab_02() {
    val daysOfWeek = listOf("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")
    for (dayOfWeek in daysOfWeek) {
    println(dayOfWeek)
	}
    
    println("\nStart with T:")
    daysOfWeek
        .filter {it.startsWith("T")}
        .forEach{(println(it))}
	println("\nContain e:")
    daysOfWeek
        .filter {it.contains("e")}
        .forEach{(println(it))}
    println("\nLength 6:")
    daysOfWeek
        .filter {it.length == 6}
        .forEach{(println(it))}
}

fun lab_03()
{
    // range
    val x = 1;
	val y = 20;
    var num = 2;
    var prim :Boolean
    for (i in x..y)
    {
        prim = true;
        for (j in 2..num/2)
        {
            if (num%j == 0)
            { prim = false; break;}
        }
        if (prim == true)
        	println(num);
        num++;
    }
}
fun lab_04_encode(msg: String): String
{
    var enc : Char;
    var encStr : String = "";
    for (i in 0..msg.length-1)
    {
        enc = msg[i]+3;
        encStr = encStr + enc;
    }
    println(encStr)
    return encStr;
}
fun lab_04_decode(msg: String): String
{
    var dec : Char;
    var decStr : String = "";
    for (i in 0..msg.length-1)
    {
        dec = msg[i]-3;
        decStr = decStr + dec;
    }
    println(decStr)
    return decStr;
}

fun lab_05(x: Int):Int? = if (x%2==0) x else null;

fun lab_06()
{
    val arr = listOf(1,2,3,4,5,6,7,8,9);
    println(arr.map {it*2});
    val list2 = listOf("monday","tuesday","wednesday","Thursday","friday","Saturday","Sunday")
    println(list2.map {it.uppercase()})
    println(list2.map {it.capitalize()})
    println(list2.map {it.length})
    val daysLength = list2.map {it.length}
    println("Avarage: "+daysLength.sum().div(7.0))
}

fun lab_07()
{
    val days = listOf("monday","tuesday","wednesday","Thursday","friday","Saturday","Sunday")
    var daysNew = days.toMutableList()
    var daysNew2 = listOf<String>()
    for (day in days)
    {
        if (!day.contains("n"))
        {
            println(day);
            daysNew2 = daysNew2 + day;
        }
    }
    
    for ((index,day) in daysNew2.withIndex())
    {
        println("Item at $index is $day");
    }
    daysNew2.toMutableList().sort();
    println(daysNew2)
}

fun lab_08()
{
    val rands: MutableList<Int> = mutableListOf((0..100).random())
    var sum :Int = 0;
    for (i in 1..9)
    {
        rands.add((0..100).random())
    }
    rands.forEach{(println(it))}
    //
    rands.sort()
    println(rands)
    //
    var even :Boolean = false;
    rands.forEach{ 
        if (lab_05(it)!=null)
        	even = true;
        sum += it
    }
    if (even == true) 
    	println("Have even number!")
    //
    even = false;
    rands.forEach{ if (lab_05(it)==null)
        {
        even = true;
        }
    }
    if (even == false)
    	println("All the numbers are even numbers!")
        else
    println("Have not even numbers!")
    //
    println("Avarage: "+sum.div(10.0))
    
}

fun main() {
    
    lab_01()
    lab_02()
    lab_03()
    val msg = lab_04_encode("hello")
    lab_04_decode(msg)
    val arr = listOf(1,2,3,4,5,6,7,8,9);
    for (ar in arr)
    {
        if (lab_05(ar)!=null)
      	println(lab_05(ar));
    }
    lab_06()
    lab_07()
  	lab_08()
    
    
}