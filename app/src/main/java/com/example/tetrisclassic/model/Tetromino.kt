package com.example.tetrisclassic.model

enum class TetrominoType { I, O, T, S, Z, J, L }

data class Position(val x: Int, val y: Int)

data class Tetromino(
    val type: TetrominoType,
    val shape: List<Position>, // Relative positions to the center
    val rotation: Int = 0
) {
    companion object {
        fun create(type: TetrominoType): Tetromino {
            val shape = when (type) {
                TetrominoType.I -> listOf(Position(-1, 0), Position(0, 0), Position(1, 0), Position(2, 0))
                TetrominoType.O -> listOf(Position(0, 0), Position(1, 0), Position(0, 1), Position(1, 1))
                TetrominoType.T -> listOf(Position(-1, 0), Position(0, 0), Position(1, 0), Position(0, 1))
                TetrominoType.S -> listOf(Position(0, 0), Position(1, 0), Position(-1, 1), Position(0, 1))
                TetrominoType.Z -> listOf(Position(-1, 0), Position(0, 0), Position(0, 1), Position(1, 1))
                TetrominoType.J -> listOf(Position(-1, 0), Position(0, 0), Position(1, 0), Position(1, 1))
                TetrominoType.L -> listOf(Position(-1, 0), Position(0, 0), Position(1, 0), Position(-1, 1))
            }
            return Tetromino(type, shape)
        }
    }

    fun rotate(): Tetromino {
        if (type == TetrominoType.O) return this
        val newShape = shape.map { Position(-it.y, it.x) }
        return copy(shape = newShape, rotation = (rotation + 1) % 4)
    }
}
