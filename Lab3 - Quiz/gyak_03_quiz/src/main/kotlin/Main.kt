fun main(args: Array<String>) {
    val controller = ItemController(ItemService(ItemRepository))
    controller.quiz(2)
}