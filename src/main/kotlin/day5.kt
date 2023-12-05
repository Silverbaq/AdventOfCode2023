import java.math.BigInteger

fun main() {
    class Mapping(val from: Long, val to: Long, val offset: Long) {
        fun isInRange(value: Long): Boolean = value in from..to

        fun getDestination(value: Long): Long = value + offset
    }

    fun parseInput(input: String): Pair<List<Long>, MutableMap<String, List<Mapping>>> {
        var seeds = listOf<Long>()
        val almanac = mutableMapOf<String, List<Mapping>>()

        input.split("\n\n").forEach { line ->
            val (name, values) = line.split(":")
            if (name == "seeds") {
                seeds = values.trim().split(" ").map { it.toLong() }
            } else {
                val almanacValues = values.trim().split("\n").filter { it.isNotEmpty() }.map { line ->
                    val (destination, source, range) = line.split(" ").map { it.toLong() }
                    Mapping(source, source + range - 1, destination - source)
                }

                almanac[name] = almanacValues
            }
        }

        return seeds to almanac
    }

    fun getLocation(almanac: MutableMap<String, List<Mapping>>, seed: Long): Long {
        val soil = almanac["seed-to-soil map"]!!.firstOrNull { it.isInRange(seed) }?.getDestination(seed) ?: seed
        val fertilizer = almanac["soil-to-fertilizer map"]!!.firstOrNull { it.isInRange(soil) }?.getDestination(soil) ?: soil
        val water = almanac["fertilizer-to-water map"]!!.firstOrNull { it.isInRange(fertilizer) }?.getDestination(fertilizer) ?: fertilizer
        val light = almanac["water-to-light map"]!!.firstOrNull { it.isInRange(water) }?.getDestination(water) ?: water
        val temperature = almanac["light-to-temperature map"]!!.firstOrNull { it.isInRange(light) }?.getDestination(light) ?: light
        val humidity = almanac["temperature-to-humidity map"]!!.firstOrNull { it.isInRange(temperature) }?.getDestination(temperature) ?: temperature
        val location = almanac["humidity-to-location map"]!!.firstOrNull { it.isInRange(humidity) }?.getDestination(humidity) ?: humidity
        return location
    }

    fun part1(seeds: List<Long>, almanac: MutableMap<String, List<Mapping>>) : Long {
        return seeds.map { seed -> getLocation(almanac, seed) }.min()
    }

    fun part2(seeds: List<Long>, almanac: MutableMap<String, List<Mapping>>) : Long {
        return 0
    }

    val input = parseInput(readInputText("day5"))
    println(part1(input.first, input.second))


}