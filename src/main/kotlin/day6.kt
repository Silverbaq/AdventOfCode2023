fun main() {
    class RaceDetails(val time: Long, val distance: Long)

    fun parseInput(input: List<String>): List<RaceDetails> {
        val times = Regex("\\d+").findAll(input[0])
        val distances = Regex("\\d+").findAll(input[1])

        return times.zip(distances).map { (time, distance) -> RaceDetails(time.value.toLong(), distance.value.toLong()) }
            .toList()
    }

    fun part1(input: List<RaceDetails>): Long {
        return input.map { raceDetails ->
            (0..<raceDetails.time)
                .count { it * (raceDetails.time - it) > raceDetails.distance }
        }.reduce { acc, i -> acc * i }.toLong()
    }

    fun part2(time: Long, recordDistance: Long): Long {
        return (0..<time).count { (it * (time - it)) > recordDistance }.toLong()
    }

    val input = parseInput(readInputLines("day6"))
    println(part1(input))
    println(part2(59707878L, 430121812131276L))
}