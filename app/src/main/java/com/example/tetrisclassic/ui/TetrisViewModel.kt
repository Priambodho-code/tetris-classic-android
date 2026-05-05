package com.example.tetrisclassic.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tetrisclassic.model.GameState
import com.example.tetrisclassic.model.Position
import com.example.tetrisclassic.model.Tetromino
import com.example.tetrisclassic.model.TetrominoType
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import kotlin.math.pow

class TetrisViewModel : ViewModel() {
    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    private var gameJob: Job? = null

    init {
        startGame()
    }

    fun startGame() {
        _gameState.value = GameState(currentPiece = Tetromino.create(TetrominoType.entries.random()))
        resumeGame()
    }

    fun resumeGame() {
        gameJob?.cancel()
        gameJob = viewModelScope.launch {
            while (!_gameState.value.isGameOver && !_gameState.value.isPaused) {
                delay(getDropDelay())
                moveDown()
            }
        }
    }

    fun pauseGame() {
        _gameState.update { it.copy(isPaused = !it.isPaused) }
        if (!_gameState.value.isPaused) resumeGame()
    }

    private fun getDropDelay(): Long {
        return (1000 * 0.8.pow(_gameState.value.level - 1)).toLong().coerceAtLeast(100)
    }

    fun moveLeft() = move(Position(-1, 0))
    fun moveRight() = move(Position(1, 0))
    fun moveDown() = move(Position(0, 1))

    fun rotate() {
        val currentPiece = _gameState.value.currentPiece ?: return
        val rotatedPiece = currentPiece.rotate()
        if (isValidMove(rotatedPiece, _gameState.value.piecePosition)) {
            _gameState.update { it.copy(currentPiece = rotatedPiece) }
        }
    }

    private fun move(offset: Position): Boolean {
        val currentState = _gameState.value
        if (currentState.isGameOver || currentState.isPaused) return false

        val newPosition = Position(
            currentState.piecePosition.x + offset.x,
            currentState.piecePosition.y + offset.y
        )

        return if (isValidMove(currentState.currentPiece, newPosition)) {
            _gameState.update { it.copy(piecePosition = newPosition) }
            true
        } else {
            if (offset.y > 0) {
                lockPiece()
            }
            false
        }
    }

    private fun isValidMove(piece: Tetromino?, position: Position): Boolean {
        if (piece == null) return false
        return piece.shape.all { block ->
            val x = position.x + block.x
            val y = position.y + block.y
            (x in 0..9) && (y < 20) && (y < 0 || _gameState.value.board[y][x] == null)
        }
    }

    private fun lockPiece() {
        val currentState = _gameState.value
        val piece = currentState.currentPiece ?: return
        val pos = currentState.piecePosition

        val newBoard = currentState.board.map { it.toMutableList() }
        piece.shape.forEach { block ->
            val x = pos.x + block.x
            val y = pos.y + block.y
            if (y in 0..19 && x in 0..9) {
                newBoard[y][x] = piece.type
            }
        }

        val (clearedBoard, linesCleared) = clearLines(newBoard)
        val newLinesTotal = currentState.linesCleared + linesCleared
        val newLevel = (newLinesTotal / 10) + 1
        val newScore = currentState.score + calculateScore(linesCleared, currentState.level)

        val nextPiece = currentState.nextPiece
        val spawnPosition = Position(4, 0)

        if (!isValidMove(nextPiece, spawnPosition)) {
            _gameState.update { it.copy(board = clearedBoard, isGameOver = true) }
        } else {
            _gameState.update {
                it.copy(
                    board = clearedBoard,
                    currentPiece = nextPiece,
                    piecePosition = spawnPosition,
                    nextPiece = Tetromino.create(TetrominoType.entries.random()),
                    score = newScore,
                    linesCleared = newLinesTotal,
                    level = newLevel,
                )
            }
        }
    }

    private fun clearLines(board: List<List<TetrominoType?>>): Pair<List<List<TetrominoType?>>, Int> {
        val filteredBoard = board.filter { line -> line.any { it == null } }
        val linesCleared = 20 - filteredBoard.size
        val newBoard = List(linesCleared) { List(10) { null } } + filteredBoard
        return newBoard to linesCleared
    }

    private fun calculateScore(lines: Int, level: Int): Int {
        return when (lines) {
            1 -> 100 * level
            2 -> 300 * level
            3 -> 500 * level
            4 -> 800 * level
            else -> 0
        }
    }
}
