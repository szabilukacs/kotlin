import java.io.File
import java.util.*

object ItemRepository {

    val lines: String = "What is a correct syntax to output \"Hello World\" in Kotlin?\n" +
            "System.out.printline(\"Hello World\")\n" +
            "cout << \"Hello World\";\n" +
            "Console.WriteLine(\"Hello World\");\n" +
            "println(\"Hello World\")\n" +
            "4\n" +
            "How do you insert COMMENTS in Kotlin code?\n" +
            "1This is a comment\n" +
            "2This is a comment\n" +
            "3This is a comment\n" +
            "4This is a comment\n" +
            "1\n" +
            "Which keyword is used to declare a function?\n" +
            "fun\n" +
            "function\n" +
            "define\n" +
            "decl\n" +
            "1\n" +
            "How can you create a variable with the numeric value 5?\n" +
            "val num = 5  \n" +
            "int num = 5\n" +
            "num = 5 int\n" +
            "num = 5\n" +
            "1\n" +
            "How can you create a variable with the floating number 2.8?\n" +
            "double num = 2.8\n" +
            "num = 2.8 float\n" +
            "val num = 2.8 \n" +
            "float num = 2.8\n" +
            "3\n" +
            "Which operator is used to add together two values?\n" +
            "The ADD keyword\n" +
            "The + sign\n" +
            "The * sign\n" +
            "The & sign\n" +
            "2\n" +
            "How can you create a range of numbers between 5 and 15 in Kotlin?\n" +
            "for (x in 5 to 15)\n" +
            "for (5 to 15)\n" +
            "for (5..15)\n" +
            "for (x in 5..15)  \n" +
            "4\n" +
            "Which statement is used to stop a loop?\n" +
            "stop\n" +
            "break\n" +
            "exit\n" +
            "void\n" +
            "2\n" +
            "What is the correct way to create an object called myObj of MyClass?\n" +
            "MyClass.new myObj\n" +
            "Main myObj = new MyClass();\n" +
            "val myObj = MyClass()\n" +
            "var myObj = MyClass()\n" +
            "3"

    var items: MutableList<Item> = mutableListOf<Item>()

    var linesuj = lines.lines();

    init {


        for (i in linesuj.indices step 6) {

            val question: String  = linesuj[i]

            val ans1 = linesuj[i + 1]
            val ans2= linesuj[i + 2]
            val ans3 = linesuj[i + 3]
            val ans4 = linesuj[i + 4]

            val correct: String = linesuj[i + 5]

            save(Item(question, mutableListOf(ans1, ans2, ans3, ans4), correct.toInt()))
        }

    }

    private fun save(item: Item) {
        items.add(Item(item.question, item.answers, item.correct))
    }

    public fun itemsSize(items: MutableList<Item>): Int {
        return items.size
    }

    public fun randomItem(): Item {
        return  items[Random().nextInt(itemsSize(items) - 0) + 0]
    }

}