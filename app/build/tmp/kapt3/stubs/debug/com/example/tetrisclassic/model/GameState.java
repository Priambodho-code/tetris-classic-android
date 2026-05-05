package com.example.tetrisclassic.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b \n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0083\u0001\u0012\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00030\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0010\u00a2\u0006\u0002\u0010\u0013J\u0017\u0010!\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00030\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0010H\u00c6\u0003J\t\u0010#\u001a\u00020\u0010H\u00c6\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\t\u0010%\u001a\u00020\bH\u00c6\u0003J\t\u0010&\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\'\u001a\u00020\u000bH\u00c6\u0003J\t\u0010(\u001a\u00020\u000bH\u00c6\u0003J\t\u0010)\u001a\u00020\u000bH\u00c6\u0003J\t\u0010*\u001a\u00020\u000bH\u00c6\u0003J\t\u0010+\u001a\u00020\u0010H\u00c6\u0003J\u0087\u0001\u0010,\u001a\u00020\u00002\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00030\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u0010H\u00c6\u0001J\u0013\u0010-\u001a\u00020\u00102\b\u0010.\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010/\u001a\u00020\u000bH\u00d6\u0001J\t\u00100\u001a\u000201H\u00d6\u0001R\u001f\u0010\u0002\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00030\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000e\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u001aR\u0011\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u001aR\u0011\u0010\u0012\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u001aR\u0011\u0010\f\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\r\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0019\u00a8\u00062"}, d2 = {"Lcom/example/tetrisclassic/model/GameState;", "", "board", "", "Lcom/example/tetrisclassic/model/TetrominoType;", "currentPiece", "Lcom/example/tetrisclassic/model/Tetromino;", "piecePosition", "Lcom/example/tetrisclassic/model/Position;", "nextPiece", "score", "", "level", "linesCleared", "highScore", "isGameOver", "", "isPaused", "isWaitingToStart", "(Ljava/util/List;Lcom/example/tetrisclassic/model/Tetromino;Lcom/example/tetrisclassic/model/Position;Lcom/example/tetrisclassic/model/Tetromino;IIIIZZZ)V", "getBoard", "()Ljava/util/List;", "getCurrentPiece", "()Lcom/example/tetrisclassic/model/Tetromino;", "getHighScore", "()I", "()Z", "getLevel", "getLinesCleared", "getNextPiece", "getPiecePosition", "()Lcom/example/tetrisclassic/model/Position;", "getScore", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "", "app_debug"})
public final class GameState {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.util.List<com.example.tetrisclassic.model.TetrominoType>> board = null;
    @org.jetbrains.annotations.Nullable()
    private final com.example.tetrisclassic.model.Tetromino currentPiece = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.tetrisclassic.model.Position piecePosition = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.tetrisclassic.model.Tetromino nextPiece = null;
    private final int score = 0;
    private final int level = 0;
    private final int linesCleared = 0;
    private final int highScore = 0;
    private final boolean isGameOver = false;
    private final boolean isPaused = false;
    private final boolean isWaitingToStart = false;
    
    public GameState(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends java.util.List<? extends com.example.tetrisclassic.model.TetrominoType>> board, @org.jetbrains.annotations.Nullable()
    com.example.tetrisclassic.model.Tetromino currentPiece, @org.jetbrains.annotations.NotNull()
    com.example.tetrisclassic.model.Position piecePosition, @org.jetbrains.annotations.NotNull()
    com.example.tetrisclassic.model.Tetromino nextPiece, int score, int level, int linesCleared, int highScore, boolean isGameOver, boolean isPaused, boolean isWaitingToStart) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.util.List<com.example.tetrisclassic.model.TetrominoType>> getBoard() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.tetrisclassic.model.Tetromino getCurrentPiece() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.tetrisclassic.model.Position getPiecePosition() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.tetrisclassic.model.Tetromino getNextPiece() {
        return null;
    }
    
    public final int getScore() {
        return 0;
    }
    
    public final int getLevel() {
        return 0;
    }
    
    public final int getLinesCleared() {
        return 0;
    }
    
    public final int getHighScore() {
        return 0;
    }
    
    public final boolean isGameOver() {
        return false;
    }
    
    public final boolean isPaused() {
        return false;
    }
    
    public final boolean isWaitingToStart() {
        return false;
    }
    
    public GameState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.util.List<com.example.tetrisclassic.model.TetrominoType>> component1() {
        return null;
    }
    
    public final boolean component10() {
        return false;
    }
    
    public final boolean component11() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.tetrisclassic.model.Tetromino component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.tetrisclassic.model.Position component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.tetrisclassic.model.Tetromino component4() {
        return null;
    }
    
    public final int component5() {
        return 0;
    }
    
    public final int component6() {
        return 0;
    }
    
    public final int component7() {
        return 0;
    }
    
    public final int component8() {
        return 0;
    }
    
    public final boolean component9() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.tetrisclassic.model.GameState copy(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends java.util.List<? extends com.example.tetrisclassic.model.TetrominoType>> board, @org.jetbrains.annotations.Nullable()
    com.example.tetrisclassic.model.Tetromino currentPiece, @org.jetbrains.annotations.NotNull()
    com.example.tetrisclassic.model.Position piecePosition, @org.jetbrains.annotations.NotNull()
    com.example.tetrisclassic.model.Tetromino nextPiece, int score, int level, int linesCleared, int highScore, boolean isGameOver, boolean isPaused, boolean isWaitingToStart) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}