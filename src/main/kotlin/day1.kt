fun main() {
    fun part1(input: List<String>): Int {
        var result = 0

        input.forEach { line ->
            val first = line.first { it.isDigit() }
            val second = line.last { it.isDigit() }
            result += "$first$second".toInt()
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val numbers = mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9,
        )

        var result = 0

        input.forEach { line ->
            var first = line.first { it.isDigit() }.toString()
            val firstIndex = line.indexOf(first)

            val firstWords = numbers.keys.filter { val res = line.indexOf(it); res < firstIndex && res > -1 }
                .map { line.indexOf(it) to numbers[it] }.minByOrNull { it.first }?.second
            firstWords?.let { first = it.toString() }

            var second = line.last { it.isDigit() }.toString()
            val secondIndex = line.lastIndexOf(second)


            val secondWords = numbers.keys.filter { val res = line.lastIndexOf(it); res > secondIndex && res > -1 }
                .map { line.lastIndexOf(it) to numbers[it] }.maxByOrNull { it.first }?.second
            secondWords?.let { second = it.toString() }


            result += "$first$second".toInt()
        }
        return result
    }


    val input = readInputLines("day1")

    println(part1(input))
    println(part2(input))
}