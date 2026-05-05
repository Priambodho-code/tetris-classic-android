package com.example.tetrisclassic.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0015H\u0002J8\u0010\u0018\u001a\u001c\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u001a0\u001a\u0012\u0004\u0012\u00020\u00150\u00192\u0014\u0010\u001c\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u001a0\u001aH\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\u001a\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010#\u001a\u00020$H\u0002J\b\u0010%\u001a\u00020&H\u0002J\u0010\u0010\'\u001a\u00020 2\u0006\u0010(\u001a\u00020$H\u0002J\u0006\u0010)\u001a\u00020 J\u0006\u0010*\u001a\u00020&J\u0006\u0010+\u001a\u00020&J\b\u0010,\u001a\u00020&H\u0014J\u0006\u0010-\u001a\u00020&J\u0006\u0010.\u001a\u00020&J\u0006\u0010/\u001a\u00020&J\u0010\u00100\u001a\u00020&2\u0006\u00101\u001a\u00020\u0015H\u0002J\u0006\u00102\u001a\u00020&R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00063"}, d2 = {"Lcom/example/tetrisclassic/ui/TetrisViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_gameState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/tetrisclassic/model/GameState;", "database", "Lcom/example/tetrisclassic/data/AppDatabase;", "gameJob", "Lkotlinx/coroutines/Job;", "gameState", "Lkotlinx/coroutines/flow/StateFlow;", "getGameState", "()Lkotlinx/coroutines/flow/StateFlow;", "highScoreDao", "Lcom/example/tetrisclassic/data/HighScoreDao;", "soundManager", "Lcom/example/tetrisclassic/ui/SoundManager;", "calculateScore", "", "lines", "level", "clearLines", "Lkotlin/Pair;", "", "Lcom/example/tetrisclassic/model/TetrominoType;", "board", "getDropDelay", "", "isValidMove", "", "piece", "Lcom/example/tetrisclassic/model/Tetromino;", "position", "Lcom/example/tetrisclassic/model/Position;", "lockPiece", "", "move", "offset", "moveDown", "moveLeft", "moveRight", "onCleared", "pauseGame", "resumeGame", "rotate", "saveHighScore", "score", "startGame", "app_debug"})
public final class TetrisViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.tetrisclassic.model.GameState> _gameState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.tetrisclassic.model.GameState> gameState = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.tetrisclassic.data.AppDatabase database = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.tetrisclassic.data.HighScoreDao highScoreDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.tetrisclassic.ui.SoundManager soundManager = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job gameJob;
    
    public TetrisViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.tetrisclassic.model.GameState> getGameState() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public final void startGame() {
    }
    
    public final void resumeGame() {
    }
    
    public final void pauseGame() {
    }
    
    private final long getDropDelay() {
        return 0L;
    }
    
    public final void moveLeft() {
    }
    
    public final void moveRight() {
    }
    
    public final boolean moveDown() {
        return false;
    }
    
    public final void rotate() {
    }
    
    private final boolean move(com.example.tetrisclassic.model.Position offset) {
        return false;
    }
    
    private final boolean isValidMove(com.example.tetrisclassic.model.Tetromino piece, com.example.tetrisclassic.model.Position position) {
        return false;
    }
    
    private final void lockPiece() {
    }
    
    private final void saveHighScore(int score) {
    }
    
    private final kotlin.Pair<java.util.List<java.util.List<com.example.tetrisclassic.model.TetrominoType>>, java.lang.Integer> clearLines(java.util.List<? extends java.util.List<? extends com.example.tetrisclassic.model.TetrominoType>> board) {
        return null;
    }
    
    private final int calculateScore(int lines, int level) {
        return 0;
    }
}