import kotlin.math.pow

fun main() {
    class Card(val numbers: List<Int>, val winningNumbers: List<Int>)

    fun part1(cards: List<Card>): Int {
        return cards.map { card ->
            2.0.pow(card.winningNumbers.count { card.numbers.contains(it) } - 1).toInt()
        }.sum()
    }

    fun part2(cards: List<Card>): Int {
        val cardCounts = MutableList(cards.size) { 1 } // Initialize with 1 for each original card

        for (i in cards.indices) {
            val card = cards[i]
            val matches = card.winningNumbers.count { card.numbers.contains(it) }
            val copies = cardCounts[i] // Get the current number of copies for this card

            for (j in 1..matches) {
                if (i + j < cards.size) {
                    cardCounts[i + j] += copies
                }
            }
        }

        return cardCounts.sum()
    }

    fun parseInput(input: List<String>): List<Card> {
        return input.map { line ->
            val (left, right) = line.replace("  ", " ").split(":")[1].split("|")
            val numbers = left.trim().split(" ").map { it.toInt() }
            val winningNumbers = right.trim().split(" ").map { it.toInt() }

            Card(numbers, winningNumbers)
        }
    }

    val input = parseInput(readInputLines("day4"))

    println(input)
    println(part1(input))
    println(part2(input))
}