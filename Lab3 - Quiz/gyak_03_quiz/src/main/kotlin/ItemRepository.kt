import java.io.File
import java.util.*

object ItemRepository {

    var items: MutableList<Item> = mutableListOf<Item>()

    init {
        val lines: List<String> =
            File("C:\\Users\\lukac\\Desktop\\IV_ev\\Android\\kotlin_labor\\kotlin\\Quiz\\gyak_03_quiz\\src\\main\\questions.txt").useLines { it.toList() }
        for (i in lines.indices step 6) {
            val question = lines[i]

            val ans1 = lines[i + 1]
            val ans2= lines[i + 2]
            val ans3 = lines[i + 3]
            val ans4 = lines[i + 4]

            val correct: String = lines[i + 5]

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