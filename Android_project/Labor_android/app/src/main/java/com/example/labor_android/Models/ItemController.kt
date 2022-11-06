class ItemController(private val itemService: ItemService) {

    public var correctAnswers = 0
    var incorrectAnswers = 0
    lateinit var items: List<Item>

    fun quiz(num: Int){

        items = itemService.selectRandomItems(num)

        for ((i, item) in items.withIndex()){
            println("${i+1}. ${item.question}")
            for (j in item.answers.indices){
                println(" ${j+1}. ${item.answers[j]}")
            }
            println("Answer: ")
            val answer = readLine()

            if(answer?.toInt() == item.correct){
                correctAnswers++
            }else{
                incorrectAnswers++
            }
            println()
        }
        println("Correct answers: $correctAnswers \nIncorrect answers: $incorrectAnswers")
    }

}