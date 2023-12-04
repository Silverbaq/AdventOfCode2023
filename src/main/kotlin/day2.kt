fun main() {
    val bag = mapOf("red" to 12, "green" to 13, "blue" to 14)

    data class Reveal(var red: Int = 0, var green: Int = 0, var blue: Int = 0)
    class Game(val id: Int, val reveals: List<Reveal>)

    fun parseInput(input: String): List<Game> {
        return input.lines().map { line ->
            val id = line.split(":")[0].split(" ")[1].toInt()
            val revealsStr = line.split(":")[1].split(";")
            val reveals = revealsStr.map { reveal ->
                val cubes = reveal.split(",")
                val red = cubes.firstOrNull { it.contains("red") }?.trim()?.split(" ")?.get(0)?.toInt() ?: 0
                val blue = cubes.firstOrNull { it.contains("blue") }?.trim()?.split(" ")?.get(0)?.toInt() ?: 0
                val green = cubes.firstOrNull { it.contains("green") }?.trim()?.split(" ")?.get(0)?.toInt() ?: 0

                Reveal(red = red, green = green, blue = blue)
            }
            Game(id, reveals)
        }
    }

    fun isGamePossible(game: Game, bag: Map<String, Int>): Boolean {
        var red = bag.getValue("red")
        var green = bag.getValue("green")
        var blue = bag.getValue("blue")

        game.reveals.forEach { reveal ->
            if (reveal.red > red || reveal.blue > blue || reveal.green > green) return false
        }
        return true
    }

    fun getMinCubes(game: Game): Reveal {
        var red = 0
        var blue = 0
        var green = 0

        game.reveals.forEach {
            if (it.green > green) green = it.green
            if (it.red > red) red = it.red
            if (it.blue > blue) blue = it.blue
        }
        return Reveal(red, green, blue)
    }

    fun part1(games: List<Game>): Int {
        return games.filter { game -> isGamePossible(game, bag) }.sumOf { game -> game.id }
    }

    fun part2(games: List<Game>): Int {
        return games.map { game -> getMinCubes(game) }.sumOf { it.red * it.green * it.blue }
    }

    val games = parseInput(readInputText("day2"))

    println(part1(games))
    println(part2(games))
}