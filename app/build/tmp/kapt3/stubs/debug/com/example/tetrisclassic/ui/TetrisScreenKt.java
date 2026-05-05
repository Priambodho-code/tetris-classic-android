package com.example.tetrisclassic.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000X\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007\u001aV\u0010\u000f\u001a\u00020\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u00112\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\f0\u00112\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\f0\u00112\u0006\u0010\u0016\u001a\u00020\u0017H\u0007\u001a\u0018\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0007\u001a\u001e\u0010\u001c\u001a\u00020\f2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0006\u0010 \u001a\u00020!H\u0007\u001a\b\u0010\"\u001a\u00020\fH\u0007\u001a\u0012\u0010#\u001a\u00020\f2\b\b\u0002\u0010$\u001a\u00020%H\u0007\u001a4\u0010&\u001a\u00020\u00172\u0006\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\u0014\u0010,\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010!0\u001e0\u001e\"\u0013\u0010\u0000\u001a\u00020\u0001\u00a2\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0002\u0010\u0003\"\u0013\u0010\u0005\u001a\u00020\u0001\u00a2\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0006\u0010\u0003\"\u0013\u0010\u0007\u001a\u00020\u0001\u00a2\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\b\u0010\u0003\"\u0013\u0010\t\u001a\u00020\u0001\u00a2\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\n\u0010\u0003\u00a8\u0006-"}, d2 = {"GB_DARK", "Landroidx/compose/ui/graphics/Color;", "getGB_DARK", "()J", "J", "GB_HIGHLIGHT", "getGB_HIGHLIGHT", "GB_LIGHT", "getGB_LIGHT", "GB_MEDIUM", "getGB_MEDIUM", "GameBoard", "", "state", "Lcom/example/tetrisclassic/model/GameState;", "GameControls", "onLeft", "Lkotlin/Function0;", "onRight", "onDown", "onRotate", "onPause", "isPaused", "", "InfoSection", "label", "", "value", "NextPiecePreview", "shape", "", "Lcom/example/tetrisclassic/model/Position;", "type", "Lcom/example/tetrisclassic/model/TetrominoType;", "TetrisPreview", "TetrisScreen", "viewModel", "Lcom/example/tetrisclassic/ui/TetrisViewModel;", "isValidGhostMove", "piece", "Lcom/example/tetrisclassic/model/Tetromino;", "x", "", "y", "board", "app_debug"})
public final class TetrisScreenKt {
    private static final long GB_DARK = 0L;
    private static final long GB_MEDIUM = 0L;
    private static final long GB_LIGHT = 0L;
    private static final long GB_HIGHLIGHT = 0L;
    
    public static final long getGB_DARK() {
        return 0L;
    }
    
    public static final long getGB_MEDIUM() {
        return 0L;
    }
    
    public static final long getGB_LIGHT() {
        return 0L;
    }
    
    public static final long getGB_HIGHLIGHT() {
        return 0L;
    }
    
    @androidx.compose.runtime.Composable()
    public static final void TetrisScreen(@org.jetbrains.annotations.NotNull()
    com.example.tetrisclassic.ui.TetrisViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void InfoSection(@org.jetbrains.annotations.NotNull()
    java.lang.String label, @org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void GameBoard(@org.jetbrains.annotations.NotNull()
    com.example.tetrisclassic.model.GameState state) {
    }
    
    public static final boolean isValidGhostMove(@org.jetbrains.annotations.NotNull()
    com.example.tetrisclassic.model.Tetromino piece, int x, int y, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends java.util.List<? extends com.example.tetrisclassic.model.TetrominoType>> board) {
        return false;
    }
    
    @androidx.compose.runtime.Composable()
    public static final void NextPiecePreview(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.tetrisclassic.model.Position> shape, @org.jetbrains.annotations.NotNull()
    com.example.tetrisclassic.model.TetrominoType type) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void GameControls(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onLeft, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onRight, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDown, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onRotate, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onPause, boolean isPaused) {
    }
    
    @androidx.compose.ui.tooling.preview.Preview()
    @androidx.compose.runtime.Composable()
    public static final void TetrisPreview() {
    }
}