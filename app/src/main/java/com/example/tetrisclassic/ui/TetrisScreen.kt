package com.example.tetrisclassic.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    Canvas(modifier = Modifier.fillMaxSize()) {
        val blockSize = size.width / 10

        // Draw Board
        state.board.forEachIndexed { y, row ->
            row.forEachIndexed { x, type ->
                if (type != null) {
                    drawRect(
                        color = GB_DARK,
                        topLeft = Offset(x * blockSize, y * blockSize),
                        size = Size(blockSize - 1, blockSize - 1)
                    )
                }
            }
        }

        // Draw Current Piece
        state.currentPiece?.let { piece ->
            piece.shape.forEach { block ->
                val x = state.piecePosition.x + block.x
                val y = state.piecePosition.y + block.y
                if (y >= 0) {
                    drawRect(
                        color = GB_DARK,
                        topLeft = Offset(x * blockSize, y * blockSize),
                        size = Size(blockSize - 1, blockSize - 1)
                    )
                }
            }
        }
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
