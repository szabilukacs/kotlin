import java.time.LocalDate
import java.time.Month
import java.time.MonthDay

class DateCurrent() {
    val year = LocalDate.now().year.toInt()
    val month = LocalDate.now().monthValue.toInt()
    val day = LocalDate.now().dayOfMonth.toInt()
}
class Date(val year: Int,val month: Int,val day: Int) {
}
fun checkLeapYear(year: Int): Boolean {
    val isleap = if (year % 4 == 0) {
        if (year % 100 == 0) {
            // Century Year must be divisible by 400 for Leap year
            year % 400 == 0
        } else true
    } else false;
    println("$year is ${if (isleap) "Leap Year" else "Not a Leap Year"} ")
    return isleap
}

fun checkValidDate(year: Int, month: Int, day: Int) : Boolean{
    var maxNumberOfDays = 30
    var isValid = false
    if ((year > 0) and (month > 0) and (day > 0)) {
        when (month)
        {
            1 -> maxNumberOfDays = 31
            2 -> if (checkLeapYear(year)) maxNumberOfDays = 29 else maxNumberOfDays = 28
            3 -> maxNumberOfDays = 31
            4 -> maxNumberOfDays = 30
            5 -> maxNumberOfDays = 31
            6 -> maxNumberOfDays = 30
            7 -> maxNumberOfDays = 31
            8 -> maxNumberOfDays = 31
            9 -> maxNumberOfDays = 30
            10 -> maxNumberOfDays = 31
            11 -> maxNumberOfDays = 30
            12 -> maxNumberOfDays = 31
            else -> isValid = false
        }
        if (day > maxNumberOfDays)
            isValid = false
        else
            isValid = true
    }
    return isValid
}