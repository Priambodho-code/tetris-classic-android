package com.example.tetrisclassic.model

data class GameState(
    val board: List<List<TetrominoType?>> = List(20) { List(10) { null } },
    val currentPiece: Tetromino? = null,
    val piecePosition: Position = Position(4, 0),
    val nextPiece: Tetromino = Tetromino.create(TetrominoType.entries.random()),
    val score: Int = 0,
    val level: Int = 1,
    val linesCleared: Int = 0,
    val highScore: Int = 0,
    val isGameOver: Boolean = false,
    val isPaused: Boolean = false,
    val isWaitingToStart: Boolean = true
)
