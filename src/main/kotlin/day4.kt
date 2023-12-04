import kotlin.math.pow

fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        input.forEach { line ->
            val (left, right) = line.replace("  ", " ").split(":")[1].split("|")
            val winningNumbers = right.trim().split(" ").map { it.toInt() }
            val cards = left.trim().split(" ").map { it.toInt() }

            result += 2.0.pow(winningNumbers.count { cards.contains(it) } - 1).toInt()
        }

        return result
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val input = readInputLines("day4")
    println(part1(input))
    println(part2(input))
}