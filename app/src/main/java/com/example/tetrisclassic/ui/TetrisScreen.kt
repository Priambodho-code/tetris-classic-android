package com.example.tetrisclassic.ui

import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tetrisclassic.model.TetrominoType

// Game Boy Palette
val GB_DARK = Color(0xFF0F380F)
val GB_MEDIUM = Color(0xFF306230)
val GB_LIGHT = Color(0xFF8BAC0F)
val GB_HIGHLIGHT = Color(0xFF9BBC0F)

@Composable
fun TetrisScreen(viewModel: TetrisViewModel = viewModel()) {
    val state by viewModel.gameState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GB_HIGHLIGHT)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("TETRIS CLASSIC", color = GB_DARK, fontSize = 24.sp)
        
        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.weight(1f)) {
            // Main Game Board
            Box(
                modifier = Modifier
                    .weight(2f)
                    .aspectRatio(0.5f)
                    .border(4.dp, GB_DARK)
                    .background(GB_LIGHT)
            ) {
                GameBoard(state)
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Sidebar
            Column(modifier = Modifier.weight(1f)) {
                InfoSection("HI-SCORE", state.highScore.toString())
                InfoSection("SCORE", state.score.toString())
                InfoSection("LEVEL", state.level.toString())
                InfoSection("LINES", state.linesCleared.toString())
                
                Spacer(modifier = Modifier.height(16.dp))
                Text("NEXT", color = GB_DARK)
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .border(2.dp, GB_DARK)
                        .background(GB_LIGHT)
                ) {
                    NextPiecePreview(state.nextPiece.shape, state.nextPiece.type)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Controls
        GameControls(
            onLeft = viewModel::moveLeft,
            onRight = viewModel::moveRight,
            onDown = viewModel::moveDown,
            onRotate = viewModel::rotate,
            onPause = viewModel::pauseGame,
            isPaused = state.isPaused
        )

        if (state.isGameOver) {
            AlertDialog(
                onDismissRequest = { viewModel.startGame() },
                title = { Text("GAME OVER") },
                text = { Text("Final Score: ${state.score}") },
                confirmButton = {
                    Button(onClick = { viewModel.startGame() }) {
                        Text("RETRY")
                    }
                }
            )
        }

        if (state.isWaitingToStart) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(GB_DARK.copy(alpha = 0.8f)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "TETRIS",
                        color = GB_HIGHLIGHT,
                        fontSize = 48.sp,
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Button(
                        onClick = { viewModel.startGame() },
                        colors = ButtonDefaults.buttonColors(containerColor = GB_HIGHLIGHT),
                        modifier = Modifier.scale(1.5f)
                    ) {
                        Text("START GAME", color = GB_DARK)
                    }
                }
            }
        }
    }
}

@Composable
fun InfoSection(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(label, color = GB_DARK, fontSize = 12.sp)
        Text(value, color = GB_DARK, fontSize = 18.sp)
    }
}

@Composable
fun GameBoard(state: com.example.tetrisclassic.model.GameState) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val blockSize = maxWidth / 10
        val blockSizePx = with(androidx.compose.ui.platform.LocalDensity.current) { blockSize.toPx() }

        // Animated Position
        val animatedOffset by animateOffsetAsState(
            targetValue = Offset(state.piecePosition.x.toFloat(), state.piecePosition.y.toFloat()),
            animationSpec = tween(durationMillis = 60),
            label = "PieceAnimation"
        )

        Canvas(modifier = Modifier.fillMaxSize()) {
            // Draw Board
            state.board.forEachIndexed { y, row ->
                row.forEachIndexed { x, type ->
                    if (type != null) {
                        drawRect(
                            color = GB_DARK,
                            topLeft = Offset(x * blockSizePx, y * blockSizePx),
                            size = Size(blockSizePx - 1, blockSizePx - 1)
                        )
                    }
                }
            }

            // Draw Ghost Piece (Bayangan)
            state.currentPiece?.let { piece ->
                var ghostY = state.piecePosition.y
                while (isValidGhostMove(piece, state.piecePosition.x, ghostY + 1, state.board)) {
                    ghostY++
                }

                piece.shape.forEach { block ->
                    val x = state.piecePosition.x + block.x
                    val y = ghostY + block.y
                    if (y >= 0) {
                        drawRect(
                            color = GB_DARK,
                            topLeft = Offset(x * blockSizePx, y * blockSizePx),
                            size = Size(blockSizePx - 1, blockSizePx - 1),
                            alpha = 0.2f // Transparan
                        )
                    }
                }

                // Draw Current Piece (Animated)
                piece.shape.forEach { block ->
                    val x = animatedOffset.x + block.x
                    val y = animatedOffset.y + block.y
                    if (y >= 0) {
                        drawRect(
                            color = GB_DARK,
                            topLeft = Offset(x * blockSizePx, y * blockSizePx),
                            size = Size(blockSizePx - 1, blockSizePx - 1)
                        )
                    }
                }
            }
        }
    }
}

fun isValidGhostMove(
    piece: com.example.tetrisclassic.model.Tetromino,
    x: Int,
    y: Int,
    board: List<List<com.example.tetrisclassic.model.TetrominoType?>>
): Boolean {
    return piece.shape.all { block ->
        val nx = x + block.x
        val ny = y + block.y
        nx in 0..9 && ny < 20 && (ny < 0 || board[ny][nx] == null)
    }
}

@Composable
fun NextPiecePreview(shape: List<com.example.tetrisclassic.model.Position>, type: TetrominoType) {
    Canvas(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        val blockSize = size.width / 4
        shape.forEach { block ->
            drawRect(
                color = GB_DARK,
                topLeft = Offset((block.x + 1) * blockSize, (block.y + 1) * blockSize),
                size = Size(blockSize - 1, blockSize - 1)
            )
        }
    }
}

@Composable
fun GameControls(
    onLeft: () -> Unit,
    onRight: () -> Unit,
    onDown: () -> Unit,
    onRotate: () -> Unit,
    onPause: () -> Unit,
    isPaused: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Button(onClick = onLeft, colors = ButtonDefaults.buttonColors(containerColor = GB_DARK)) {
                Text("◄", color = GB_HIGHLIGHT)
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = onRotate, colors = ButtonDefaults.buttonColors(containerColor = GB_DARK)) {
                Text("⟲", color = GB_HIGHLIGHT)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onDown, colors = ButtonDefaults.buttonColors(containerColor = GB_DARK)) {
                Text("▼", color = GB_HIGHLIGHT)
            }
        }
        Column {
            Button(onClick = onRight, colors = ButtonDefaults.buttonColors(containerColor = GB_DARK)) {
                Text("►", color = GB_HIGHLIGHT)
            }
        }
        Button(onClick = onPause, colors = ButtonDefaults.buttonColors(containerColor = GB_MEDIUM)) {
            Text(if (isPaused) "RESUME" else "PAUSE", color = GB_HIGHLIGHT)
        }
    }
}

@androidx.compose.ui.tooling.preview.Preview
@Composable
fun TetrisPreview() {
    TetrisScreen()
}
