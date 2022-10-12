import java.io.File

object ItemRepository {
    private var itmes = MutableList<Item>()

    init {
        val lines: List<String> =
            File("C:\\Users\\lukac\\Desktop\\IV_ev\\Android\\kotlin_labor\\kotlin\\Quiz\\gyak_03_quiz\\src\\main\\questions.txt").useLines { it.toList() }
        for (i: Int in lines.indices step 6) {
            val question:String = lines[i]

            val ans1: String = lines[i+1]
            val ans2: String = lines[i+2]
            val ans3: String = lines[i+3]
            val ans4: String = lines[i+4]

            val correct: String = lines[i+5]

            save(Item(question, mutableListOf(ans1,ans2,ans3,ans4), correct.toInt()))
        }


    }

}