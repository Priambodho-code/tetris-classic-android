# RPD (Rencana Pengembangan & Desain)
# Tetris Classic - Android Game

**Versi Dokumen:** 1.0  
**Tanggal:** Mei 2026  
**Platform Target:** Android (Play Store)  
**Development Tool:** Android Studio

---

## 1. EXECUTIVE SUMMARY

### 1.1 Deskripsi Project
Tetris Classic adalah game puzzle Android yang menghadirkan kembali pengalaman bermain Tetris era Game Boy dengan tampilan retro monokrom, sound effects 8-bit klasik, dan gameplay yang authentic. Game ini ditujukan untuk nostalgia gamer 90-an sekaligus memperkenalkan generasi baru pada gameplay puzzle timeless.

### 1.2 Target Audience
- **Primary:** Gamer berusia 25-45 tahun yang familiar dengan Game Boy era
- **Secondary:** Pemain casual puzzle game semua umur
- **Geographic:** Global (dengan fokus awal Indonesia, Asia Tenggara)

### 1.3 Unique Selling Points
- Tampilan visual 100% authentic Game Boy style (monokrom hijau/hitam)
- Sound effects dan musik 8-bit original
- Kontroler on-screen yang responsive dengan haptic feedback
- Leaderboard global & lokal
- Offline-first gameplay
- No ads intrusive (optional rewarded ads)
- Lightweight (<15MB install size)

---

## 2. GAME DESIGN DOCUMENT

### 2.1 Core Gameplay Mechanics

#### 2.1.1 Tetromino Pieces
7 bentuk standar Tetris (I, O, T, S, Z, J, L) dengan:
- Rotasi 4 arah (kecuali O-piece 1 arah, I-piece 2 arah)
- Preview piece berikutnya (1-3 pieces ahead sebagai opsi)
- Ghost piece (outline transparan menunjukkan landing position)

#### 2.1.2 Controls
- **D-Pad Virtual:** Gerak kiri/kanan
- **Rotate Button:** Rotasi clockwise (tap) / counter-clockwise (long press)
- **Soft Drop:** Swipe down atau hold down button
- **Hard Drop:** Double tap down atau dedicated button
- **Pause:** Dedicated pause button
- **Alternative:** Gesture controls (swipe) sebagai opsi

#### 2.1.3 Scoring System (Classic)
```
Single Line Clear:    100 points × level
Double Line Clear:    300 points × level
Triple Line Clear:    500 points × level
Tetris (4 lines):    800 points × level
Soft Drop:           1 point per cell
Hard Drop:           2 points per cell
```

#### 2.1.4 Level Progression
- Start dari Level 1
- Setiap 10 lines cleared → naik 1 level
- Speed increase per level: formula klasik (frames per drop berkurang)
- Maximum level 15 untuk balance gameplay

#### 2.1.5 Game Over Condition
- Piece spawn overlap dengan existing blocks
- Classic "top out" indicator dengan blinking effect

### 2.2 Visual Design

#### 2.2.1 Color Palette (Game Boy Classic)
```
Background:     #0f380f (dark green)
Primary:        #306230 (medium green)
Secondary:      #8bac0f (light green)
Highlight:      #9bbc0f (bright green)
```

#### 2.2.2 Screen Layout
```
┌──────────────────────────────┐
│  TETRIS CLASSIC              │
│                              │
│  ┌─────────┐  ┌──────────┐  │
│  │  NEXT   │  │  SCORE   │  │
│  │  [■■]   │  │  000000  │  │
│  └─────────┘  │  LEVEL   │  │
│               │    01    │  │
│  ┌─────────┐  │  LINES   │  │
│  │  HOLD   │  │    000   │  │
│  │  [■■]   │  └──────────┘  │
│  └─────────┘                │
│                              │
│      ┌──────────────┐        │
│      │  GAME FIELD  │        │
│      │  (10×20)     │        │
│      │              │        │
│      │    ██████    │        │
│      │              │        │
│      └──────────────┘        │
│                              │
│  [◄] [►] [▼] [⟲] [PAUSE]    │
└──────────────────────────────┘
```

#### 2.2.3 Pixel Art Assets
- Grid 10×20 cells (classic Tetris dimensions)
- Pixel perfect rendering dengan fixed pixel size
- Scanline effect opsional untuk authentic CRT feel
- Border decoration à la Game Boy cartridge aesthetic

### 2.3 Audio Design

#### 2.3.1 Music Tracks
- **Menu Theme:** 8-bit chiptune upbeat (loop ~30s)
- **Game Theme A:** Classic Korobeiniki (Russian theme) 8-bit version
- **Game Theme B:** Alternative track untuk variasi
- **High Score Theme:** Victory jingle (~5s)
- **Game Over Theme:** Sad/dramatic jingle (~3s)

#### 2.3.2 Sound Effects (SFX)
- Piece rotation: "click" 8-bit
- Piece movement: subtle "tick"
- Piece lock: "thud" 8-bit
- Line clear: "ding" dengan pitch naik per jumlah lines
- Tetris (4 lines): special "fanfare" effect
- Level up: ascending chiptune
- Menu navigation: "beep" sounds
- Pause/unpause: distinct tones

#### 2.3.3 Audio Settings
- Master volume control
- Music on/off toggle
- SFX on/off toggle
- Vibration on/off toggle

---

## 3. TECHNICAL SPECIFICATIONS

### 3.1 Technology Stack

#### 3.1.1 Development Environment
- **IDE:** Android Studio Hedgehog (2023.1.1) atau lebih baru
- **Language:** Kotlin (primary) dengan Jetpack Compose untuk UI
- **Min SDK:** Android 7.0 (API 24) - coverage ~95% devices
- **Target SDK:** Android 14 (API 34)
- **Build System:** Gradle 8.x dengan Kotlin DSL

#### 3.1.2 Architecture Pattern
**MVVM (Model-View-ViewModel)** dengan Clean Architecture:

```
┌─────────────────────────────────────┐
│         Presentation Layer          │
│  (Jetpack Compose UI + ViewModels)  │
└─────────────────────────────────────┘
                  ↓↑
┌─────────────────────────────────────┐
│          Domain Layer               │
│   (Use Cases, Game Logic, Entities) │
└─────────────────────────────────────┘
                  ↓↑
┌─────────────────────────────────────┐
│           Data Layer                │
│  (Repository, Local Storage, API)   │
└─────────────────────────────────────┘
```

#### 3.1.3 Core Libraries & Dependencies
```kotlin
// UI Framework
implementation("androidx.compose.ui:ui:1.6.0")
implementation("androidx.compose.material3:material3:1.2.0")

// Architecture Components
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

// Coroutines for async operations
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

// Local Database (High Scores)
implementation("androidx.room:room-runtime:2.6.1")
implementation("androidx.room:room-ktx:2.6.1")
kapt("androidx.room:room-compiler:2.6.1")

// Data Persistence
implementation("androidx.datastore:datastore-preferences:1.0.0")

// Audio Engine
implementation("androidx.media3:media3-exoplayer:1.2.0")
// OR custom SoundPool for low-latency SFX

// Canvas/Graphics
// Built-in Canvas Compose atau custom SurfaceView

// Dependency Injection (optional)
implementation("com.google.dagger:hilt-android:2.48")
kapt("com.google.dagger:hilt-compiler:2.48")

// Testing
testImplementation("junit:junit:4.13.2")
androidTestImplementation("androidx.test.ext:junit:1.1.5")
androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.6.0")
```

### 3.2 Game Engine Architecture

#### 3.2.1 Core Components

**1. GameState (Data Class)**
```kotlin
data class GameState(
    val board: Array<IntArray>,  // 10×20 grid
    val currentPiece: Tetromino?,
    val currentPosition: Position,
    val nextPieces: List<Tetromino>,
    val heldPiece: Tetromino?,
    val score: Int,
    val level: Int,
    val linesCleared: Int,
    val isGameOver: Boolean,
    val isPaused: Boolean
)
```

**2. GameEngine (Core Logic)**
- Tick system: UpdateThread dengan coroutines
- Fixed timestep untuk consistent gameplay
- Collision detection
- Line clearing algorithm
- Scoring calculation
- Level progression logic

**3. Tetromino Class**
```kotlin
enum class TetrominoType { I, O, T, S, Z, J, L }

data class Tetromino(
    val type: TetrominoType,
    val shape: Array<IntArray>,  // 4×4 matrix
    val rotation: Int = 0         // 0-3
) {
    fun rotate(direction: RotationDirection): Tetromino
    fun getKickTable(): List<Position> // Wall kick data
}
```

**4. Input Manager**
- Touch event handling
- Gesture detection
- Input buffering untuk responsive controls
- DAS (Delayed Auto Shift) implementation
- ARR (Auto Repeat Rate) tuning

**5. Render Engine**
- Custom Canvas drawing dengan Compose
- Sprite system untuk tetromino pieces
- Grid rendering dengan pixel-perfect alignment
- Particle effects untuk line clears
- Screen shake effect (optional)

**6. Audio Manager**
- SoundPool untuk low-latency SFX
- MediaPlayer untuk background music
- Audio ducking untuk focus management
- Haptic feedback integration

### 3.3 Data Management

#### 3.3.1 Local Storage (Room Database)

**Entities:**
```kotlin
@Entity(tableName = "high_scores")
data class HighScore(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val playerName: String,
    val score: Int,
    val level: Int,
    val linesCleared: Int,
    val timestamp: Long,
    val gameMode: String = "CLASSIC"
)

@Entity(tableName = "game_settings")
data class GameSettings(
    @PrimaryKey val id: Int = 1,
    val soundEnabled: Boolean = true,
    val musicEnabled: Boolean = true,
    val vibrationEnabled: Boolean = true,
    val ghostPieceEnabled: Boolean = true,
    val controlScheme: String = "DPAD",
    val difficulty: String = "NORMAL"
)
```

#### 3.3.2 DataStore (Preferences)
- User preferences (nama, avatar)
- Last game session (for resume)
- Statistics tracking (total games, play time)
- Achievement progress

### 3.4 Performance Optimization

#### 3.4.1 Memory Management
- Object pooling untuk Tetromino instances
- Bitmap caching untuk sprites
- Lazy initialization untuk non-critical components
- ProGuard/R8 optimization untuk release build

#### 3.4.2 Rendering Optimization
- Dirty rectangle rendering (only redraw changed areas)
- Hardware acceleration enabled
- Fixed framerate capping (60 FPS)
- Compose recomposition optimization

#### 3.4.3 Battery Optimization
- Suspend background threads saat pause
- Wake lock management
- Sensor usage minimal (only vibration)

---

## 4. FEATURES & FUNCTIONALITY

### 4.1 Core Features (MVP - Phase 1)

#### 4.1.1 Game Modes
- **Classic Mode:** Endless play dengan progressive difficulty
- **Marathon Mode:** Target 150 lines cleared
- **Sprint Mode:** Clear 40 lines secepat mungkin
- **Practice Mode:** No game over untuk latihan

#### 4.1.2 User Interface Screens

**Main Menu:**
- Start Game (mode selection)
- High Scores
- Settings
- How to Play (tutorial)
- About/Credits
- Exit

**In-Game HUD:**
- Current score display
- Level indicator
- Lines cleared counter
- Next piece preview (1-3 pieces)
- Hold piece slot
- Pause button
- Visual timer (untuk Sprint mode)

**Game Over Screen:**
- Final score & statistics
- Personal best indicator (if achieved)
- Share score (screenshot + text)
- Retry / Main Menu

**Settings Menu:**
- Audio settings (music/sfx volume, vibration)
- Control scheme selection
- Visual settings (ghost piece, grid, screen shake)
- Reset high scores (with confirmation)
- Language selection (EN/ID)

### 4.2 Advanced Features (Phase 2)

#### 4.2.1 Achievement System
```
- First Blood: Clear your first line
- Tetris Master: Clear 10 Tetris (4-lines)
- Speed Demon: Reach Level 10
- Marathon Runner: Complete Marathon mode
- Perfect Start: Get 5,000 points without clearing lines
- Ghost Buster: Play 10 games without ghost piece
- Combo King: Clear 10 consecutive line clears
- Century Club: Score 100,000 points
- (20+ achievements total)
```

#### 4.2.2 Daily Challenges
- Seeded random pieces (same untuk semua player)
- Special objectives (misal: "Score 10,000 with only T-pieces")
- Leaderboard khusus daily challenge
- Rewards: unlock themes/skins

#### 4.2.3 Customization (Unlock System)
**Themes:**
- Classic Game Boy Green (default)
- Game Boy Pocket (grayscale)
- Virtual Boy (red/black)
- NES Palette
- Custom color schemes

**Piece Skins:**
- Classic blocks
- Geometric patterns
- Emoji blocks
- Pixel art variants

#### 4.2.4 Online Features
- Global leaderboard (top 100)
- Friend system & friend leaderboard
- Ghost replays (watch top scores)
- Cloud save sync (Google Play Games)

### 4.3 Monetization Strategy

#### 4.3.1 Free Version Features
- Full gameplay tanpa restrictions
- Ads: Banner ads di menu (non-intrusive)
- Rewarded ads: Continue game after game over (1× per session)
- Local leaderboards only

#### 4.3.2 Premium Unlock (IAP - In-App Purchase)
**Price:** Rp 29.000 - Rp 49.000 (one-time)

**Premium Benefits:**
- Remove all ads permanently
- Unlock all themes & skins instantly
- Cloud save & sync
- Access global leaderboards
- Unlimited continues
- Exclusive "Gold Edition" theme
- Priority customer support

#### 4.3.3 Ads Implementation (Non-Premium)
- **AdMob Integration:** Banner + Rewarded Video
- Placement strategy:
  - Banner: Main menu bottom (collapsible)
  - Interstitial: Setiap 5 game over (skippable after 5s)
  - Rewarded: Optional continue (1× per session)
- **Frequency cap:** Max 1 interstitial per 5 minutes
- **User experience:** Always skippable, no forced watching

---

## 5. PLAY STORE REQUIREMENTS

### 5.1 App Metadata

#### 5.1.1 Title & Description
**Title:** Tetris Classic - Retro Puzzle Game

**Short Description (80 chars):**
"Classic Tetris with authentic Game Boy style graphics and 8-bit sounds!"

**Full Description (4000 chars):**
```
🎮 TETRIS CLASSIC - Nostalgia Game Boy Era! 🎮

Mainkan Tetris seperti jaman Game Boy dengan tampilan retro monokrom dan sound effects 8-bit yang authentic! Puzzle game timeless yang cocok untuk semua umur.

✨ FITUR UTAMA:
• Tampilan Game Boy Classic authentik (hijau monokrom)
• Sound effects & musik 8-bit original
• 4 Mode Permainan (Classic, Marathon, Sprint, Practice)
• Kontrol responsif dengan haptic feedback
• Leaderboard lokal & global
• 20+ Achievements untuk unlock
• Unlock themes & skins (NES, Virtual Boy, dll)
• Offline gameplay - main kapanpun tanpa internet
• Lightweight (< 15MB) - hemat storage

🎯 MODE PERMAINAN:
• Classic: Endless play dengan level progression
• Marathon: Target 150 lines
• Sprint: Race against time - 40 lines secepat mungkin
• Practice: Latihan tanpa game over

🏆 LEADERBOARD & ACHIEVEMENTS:
Kompetisi dengan pemain global, unlock 20+ achievements, dan buktikan kamu Tetris Master sejati!

🎨 CUSTOMIZATION:
Unlock berbagai tema retro (Game Boy, NES, Virtual Boy) dan piece skins unik!

📱 OPTIMIZED:
Berjalan smooth di semua device Android dari tahun 2016 keatas. Battery friendly!

💎 PREMIUM VERSION:
Unlock semua fitur tanpa ads, cloud save, dan theme eksklusif.

Download sekarang dan rasakan nostalgia era 90-an! 🕹️

---
Keywords: tetris, puzzle game, retro game, game boy, 8-bit, classic game, brick game, casual game, offline game
```

#### 5.1.2 Graphics Assets Required

**App Icon:**
- 512×512 px (PNG, 32-bit, transparency OK)
- Desain: Stylized Tetris pieces dengan Game Boy color palette

**Feature Graphic:**
- 1024×500 px (JPG/PNG, no transparency)
- Showcase: Game screen dengan "TETRIS CLASSIC" title

**Screenshots (minimal 2, recommended 8):**
- Phone: 1080×1920 atau 1080×2340
- Tablet (optional): 1200×1920 atau 1600×2560
- Content:
  1. Gameplay screen (active game)
  2. Main menu
  3. High score screen
  4. Settings/customization
  5. Different game modes
  6. Achievement unlock
  7. Leaderboard
  8. Theme variations

**Promo Video (optional but recommended):**
- Duration: 30-120 seconds
- Format: MP4, max 15MB
- Content: Gameplay showcase, features highlight

### 5.2 Content Rating (IARC)

**Expected Rating:** Everyone / PEGI 3
- No violence, sexual content, drugs
- May contain ads (if non-premium)
- Online interactions (leaderboards)

**Required Declarations:**
- ✅ Appeals to children
- ✅ Contains ads (for free version)
- ✅ In-app purchases (premium unlock)
- ✅ Requests permissions (Internet, Vibrate)

### 5.3 Privacy & Compliance

#### 5.3.1 Privacy Policy (Required)
**URL:** https://yourdomain.com/tetris-classic-privacy-policy

**Must Include:**
- Data collection (high scores, preferences, analytics)
- Third-party services (AdMob, Google Play Games)
- How data is used & stored
- User rights (data deletion requests)
- Contact information
- GDPR & COPPA compliance

#### 5.3.2 Permissions Justification
```xml
<!-- AndroidManifest.xml -->
<uses-permission android:name="android.permission.INTERNET" />
<!-- Reason: Ads, leaderboards, cloud save -->

<uses-permission android:name="android.permission.VIBRATE" />
<!-- Reason: Haptic feedback untuk game controls -->

<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<!-- Reason: Check internet connectivity untuk online features -->

<!-- NO invasive permissions: Camera, Location, Contacts, etc -->
```

#### 5.3.3 Data Safety Section
Declare di Play Console:
- **Data Collected:** Device ID (analytics), high scores, game progress
- **Data Shared:** Anonymized analytics to Google Analytics
- **Security:** Data encrypted in transit (HTTPS)
- **User Control:** Can delete account data via settings

### 5.4 Target Audience & Categories

**Category:** Games > Puzzle  
**Secondary Category:** Games > Casual

**Target Countries (Phase 1):**
- Indonesia (primary)
- Malaysia, Philippines, Thailand, Vietnam
- USA, UK (English-speaking markets)

**Supported Languages:**
- English (primary)
- Indonesian (Bahasa Indonesia)

### 5.5 Release Strategy

#### 5.5.1 Closed Testing (Internal)
- **Duration:** 1 minggu
- **Testers:** 5-10 orang (team + friends)
- **Focus:** Bug hunting, stability, core gameplay

#### 5.5.2 Open Beta (Public)
- **Duration:** 2-4 minggu
- **Target:** 100-500 early adopters
- **Opt-in link:** Distributed via social media
- **Feedback collection:** In-app form + Google Forms
- **Focus:** Performance across devices, UX refinement

#### 5.5.3 Production Release
- **Soft Launch:** Indonesia only (1 minggu)
- **Global Launch:** All target countries
- **Launch Campaign:**
  - Social media (Instagram, TikTok, X/Twitter)
  - Gaming communities (Reddit r/AndroidGaming)
  - Press release (gaming blogs/websites)
  - Cross-promotion (jika ada game lain)

---

## 6. DEVELOPMENT ROADMAP

### 6.1 Phase 1: MVP Development (8-10 minggu)

#### Week 1-2: Setup & Core Architecture
- [ ] Project setup Android Studio + Git repository
- [ ] Setup MVVM architecture + dependency injection
- [ ] Design database schema (Room)
- [ ] Create mockups/wireframes (Figma)
- [ ] Setup CI/CD (optional: GitHub Actions)

#### Week 3-4: Game Logic Implementation
- [ ] Tetromino class & rotation system
- [ ] Board/Grid management
- [ ] Collision detection algorithm
- [ ] Line clearing logic
- [ ] Scoring system implementation
- [ ] Level progression logic
- [ ] Input handling (touch controls)

#### Week 5-6: UI Development
- [ ] Main menu screen (Compose)
- [ ] Game screen UI (HUD, grid, controls)
- [ ] Settings screen
- [ ] High scores screen
- [ ] Game over screen
- [ ] Implement transitions/animations

#### Week 7-8: Audio & Polish
- [ ] Create/source 8-bit sound effects
- [ ] Create/source background music (license check!)
- [ ] Audio manager implementation
- [ ] Haptic feedback integration
- [ ] Visual effects (line clear, particles)
- [ ] Performance optimization pass 1

#### Week 9-10: Testing & Bug Fixing
- [ ] Unit tests (game logic)
- [ ] UI tests (Espresso/Compose Testing)
- [ ] Device compatibility testing (5+ devices)
- [ ] Performance profiling (memory, CPU, battery)
- [ ] Bug fixing & polishing
- [ ] Internal closed testing

**Deliverables:** Playable MVP dengan Classic mode, basic UI, audio, local high scores

### 6.2 Phase 2: Feature Enhancement (4-6 minggu)

#### Week 11-12: Additional Game Modes
- [ ] Marathon mode implementation
- [ ] Sprint mode + timer system
- [ ] Practice mode
- [ ] Mode selection UI
- [ ] Testing & balancing

#### Week 13-14: Customization & Unlocks
- [ ] Achievement system backend
- [ ] Theme system (4-5 themes)
- [ ] Piece skins system
- [ ] Unlock progression logic
- [ ] UI for customization screen

#### Week 15-16: Online Features
- [ ] Google Play Games Services integration
- [ ] Cloud save implementation
- [ ] Global leaderboard setup
- [ ] Achievement sync
- [ ] Testing online features

**Deliverables:** Full-featured game dengan multiple modes, achievements, themes, online leaderboards

### 6.3 Phase 3: Monetization & Publishing (3-4 minggu)

#### Week 17-18: Ads & IAP Integration
- [ ] AdMob setup (banner, interstitial, rewarded)
- [ ] IAP implementation (premium unlock)
- [ ] Payment testing (sandbox)
- [ ] Ad placement optimization
- [ ] Analytics integration (Firebase)

#### Week 19: Play Store Preparation
- [ ] Create all graphics assets (icon, screenshots, feature graphic)
- [ ] Write store listing (title, descriptions, keywords)
- [ ] Record promo video
- [ ] Privacy policy creation + hosting
- [ ] Content rating questionnaire
- [ ] Build signed APK/AAB

#### Week 20: Beta Testing & Launch
- [ ] Upload to Play Console (internal testing)
- [ ] Closed beta with 10-20 testers
- [ ] Open beta (2 weeks)
- [ ] Bug fixes from beta feedback
- [ ] Final QA pass
- [ ] Production release!

**Deliverables:** Live app on Google Play Store

### 6.4 Phase 4: Post-Launch Support (Ongoing)

#### Month 1-2 Post-Launch
- [ ] Monitor crash reports (Firebase Crashlytics)
- [ ] Respond to user reviews (aim: 24-48 hours)
- [ ] Analytics review (DAU, retention, monetization)
- [ ] Hotfix critical bugs
- [ ] A/B testing ad placements

#### Month 3+: Updates & New Features
- [ ] Daily challenges system
- [ ] New themes/skins
- [ ] Seasonal events (Halloween, Christmas themes)
- [ ] Multiplayer mode (future consideration)
- [ ] Platform expansion (iOS version)

---

## 7. TESTING STRATEGY

### 7.1 Unit Testing

**Target Coverage:** 70%+ for core game logic

**Test Cases:**
```kotlin
// GameEngineTest.kt
- testPieceRotation()
- testCollisionDetection()
- testLineClearingSingle()
- testLineClearingMultiple()
- testScoringCalculation()
- testLevelProgression()
- testGameOverCondition()
- testPieceRandomization() // ensure fair distribution

// TetrominoTest.kt
- testAllPieceRotations()
- testWallKickSystem()
- testFloorKickSystem()
```

### 7.2 UI Testing

**Espresso/Compose Tests:**
```kotlin
- testMainMenuNavigation()
- testGameStart()
- testPauseResume()
- testSettingsChange()
- testHighScoreDisplay()
- testAchievementUnlock()
```

### 7.3 Device Compatibility Testing

**Test Matrix (Minimum):**
| Device Type | OS Version | Screen Size | Specs |
|-------------|------------|-------------|-------|
| Low-end | Android 7.0 | 5" 720p | 2GB RAM |
| Mid-range | Android 10 | 6" 1080p | 4GB RAM |
| High-end | Android 14 | 6.5" 1440p | 8GB RAM |
| Tablet | Android 12 | 10" 1200p | 6GB RAM |

**Test Criteria:**
- ✅ Stable FPS (60 FPS target, min 30 FPS acceptable)
- ✅ No memory leaks (max 100MB RAM usage)
- ✅ No ANR (Application Not Responding)
- ✅ Battery drain < 5%/hour during gameplay
- ✅ UI elements properly scaled
- ✅ Touch controls responsive (< 50ms latency)

### 7.4 Beta Testing Program

**Recruitment:**
- Google Play open beta opt-in
- Social media invitation (Twitter, Reddit, Discord)
- Target: 100-500 beta testers

**Feedback Collection:**
- In-app feedback form
- Google Forms survey (post-session)
- Beta tester Discord channel
- Play Console reviews

**Focus Areas:**
- Gameplay balance (is it too easy/hard?)
- Control responsiveness
- Audio quality & volume levels
- UI clarity & readability
- Bug reports with device info
- Feature requests prioritization

---

## 8. ANALYTICS & KPIs

### 8.1 Key Performance Indicators

**User Acquisition:**
- Daily Active Users (DAU)
- Monthly Active Users (MAU)
- DAU/MAU ratio (target: >20%)
- Install conversion rate from store views

**Engagement:**
- Average session duration (target: 10+ minutes)
- Sessions per user per day (target: 3+)
- Day 1/7/30 retention rates (target: 40%/20%/10%)
- Games played per session (target: 5+)

**Monetization:**
- Ad revenue per user (ARPU)
- IAP conversion rate (target: 2-5% of users)
- Average Revenue Per Paying User (ARPPU)
- Lifetime Value (LTV) estimate

**Gameplay Metrics:**
- Average score distribution
- Highest level reached distribution
- Most popular game mode
- Average game duration
- Tetris rate (4-line clears per game)

### 8.2 Analytics Tools

**Firebase Analytics (Primary):**
- User acquisition tracking
- In-app events (game_start, game_over, achievement_unlock)
- Custom events (tetris_cleared, level_reached)
- User properties (device, country, premium status)
- Funnel analysis (onboarding flow)

**Google Play Console Metrics:**
- Store listing impressions
- Install conversion rate
- Crash reports
- ANR rate
- User reviews sentiment analysis

**AdMob Reporting:**
- Ad impressions
- eCPM (effective cost per mille)
- Fill rate
- Click-through rate (CTR)

### 8.3 Event Tracking Schema

```kotlin
// Custom Firebase Events
analytics.logEvent("game_start") {
    param("mode", "classic")
    param("level", 1)
}

analytics.logEvent("game_over") {
    param("score", 15400)
    param("level_reached", 8)
    param("lines_cleared", 42)
    param("duration_seconds", 320)
    param("mode", "classic")
}

analytics.logEvent("achievement_unlock") {
    param("achievement_id", "tetris_master")
    param("timestamp", System.currentTimeMillis())
}

analytics.logEvent("iap_purchase") {
    param("product_id", "premium_unlock")
    param("price", "Rp 39000")
    param("currency", "IDR")
}
```

---

## 9. MARKETING & USER ACQUISITION

### 9.1 Pre-Launch Campaign (2-4 minggu sebelum launch)

**Teaser Content:**
- Screenshots + short video clips (Instagram Reels, TikTok)
- "Coming Soon" landing page dengan email signup
- Behind-the-scenes development (dev blog/vlog)
- Countdown posts (1 week, 3 days, 1 day, launch!)

**Community Building:**
- Create Discord server untuk early adopters
- Reddit presence di r/AndroidGaming, r/Tetris
- Twitter/X account dengan dev updates
- Press kit untuk gaming blogs/journalists

### 9.2 Launch Day Strategy

**Play Store Optimization (ASO):**
- Keyword research (use Google Play Console search)
- Optimized title & description dengan keywords
- High-quality screenshots showcasing features
- Promo video (30-60s gameplay montage)

**Announcements:**
- Press release distribution (PR Newswire, GamesPress)
- Reddit launch post dengan AMA (Ask Me Anything)
- Social media blast (all platforms)
- Email blast to pre-launch signups
- Submit to app review sites (AndroidPolice, Droid Gamers)

**Launch Promotion:**
- Limited-time discount (Rp 39k → Rp 29k untuk 1 minggu)
- Launch exclusive theme (hanya unlock minggu pertama)
- Giveaway (10× premium unlock codes untuk social media contest)

### 9.3 Ongoing Marketing

**Content Marketing:**
- Weekly tips & tricks posts (Instagram carousel)
- High score showcase (feature top players)
- Community challenges dengan prizes
- Development roadmap transparency

**Influencer Outreach:**
- Send free premium codes ke gaming YouTubers/streamers
- Target micro-influencers (10k-100k subs) di niche retro/puzzle gaming
- Provide review copies 1 week pre-launch

**Cross-Promotion:**
- Partner dengan developer game puzzle lain
- Ad exchange (jika ada game lain)
- Bundle deals (future)

**Paid Acquisition (Budget: Low/Test):**
- Google Ads (UAC - Universal App Campaigns)
- Facebook/Instagram ads targeting:
  - Interest: Tetris, retro gaming, puzzle games
  - Age: 18-45
  - Lookalike audience dari current users
- Budget: Start dengan $5-10/day, scale jika ROAS > 1.5

---

## 10. LEGAL & COMPLIANCE

### 10.1 Trademark Considerations

**⚠️ CRITICAL ISSUE: "Tetris" adalah trademark terdaftar**

**Problem:**
- "Tetris" adalah intellectual property The Tetris Company LLC
- Penggunaan nama "Tetris" tanpa lisensi bisa:
  - Takedown dari Play Store
  - Cease & Desist letter
  - Lawsuit untuk trademark infringement

**Solutions:**

**Option A: Generic Name (RECOMMENDED untuk indie dev)**
- **Title Alternatif:**
  - "Block Drop Classic"
  - "Retro Blocks"
  - "Brick Puzzle Classic"
  - "Block Stack Retro"
  - "Falling Blocks GB"
  
**Option B: Lisensi Resmi**
- Contact The Tetris Company untuk licensing
- Biaya: Biasanya $10,000+ USD + royalty
- Proses: 3-6 bulan
- **Tidak praktis untuk solo/indie developer**

**Option C: Parody/Homage (Gray Area)**
- Gunakan subtitle yang jelas parody
- Ubah mekanik sedikit (tambah power-ups, dll)
- Disclaimer: "Inspired by the classic falling block puzzle game"
- **Tetap berisiko, tapi lebih rendah**

**RECOMMENDATION FINAL:**
Gunakan nama generic + subtitle retro:
**"Block Drop Classic - Retro GB Puzzle"**

### 10.2 Music & Audio Licensing

**Problem:** Korobeiniki (Tetris Theme A) ada copyright

**Solutions:**
1. **Public Domain Arrangement:** Melody Korobeiniki adalah Russian folk song (public domain), tapi arrangement spesifik bisa di-copyright
2. **Create Original 8-bit Cover:** Legal jika arrangement benar-benar original
3. **Use Royalty-Free Music:** Cari di:
   - OpenGameArt.org
   - Freesound.org
   - Incompetech (Kevin MacLeod)
   - Purple Planet Music
4. **Commission Original Music:** Hire composer untuk $100-500

**RECOMMENDATION:**
Combine original 8-bit composition + royalty-free chiptunes dengan credit attribution

### 10.3 GDPR & Privacy Compliance

**Requirements (even untuk non-EU apps):**
- ✅ Privacy Policy accessible dari app & store listing
- ✅ Transparent data collection disclosure
- ✅ User consent untuk analytics/ads (consent banner)
- ✅ Right to deletion (provide email contact)
- ✅ Data encryption in transit
- ✅ No data collection dari anak <13 tahun (COPPA)

**Implementation:**
```kotlin
// First launch: show consent dialog
if (!hasShownConsentDialog) {
    showDialog {
        title = "Privacy & Data"
        message = "We collect anonymous analytics to improve the game. 
                   You can opt-out in Settings."
        positiveButton("Accept") { 
            enableAnalytics()
            hasShownConsentDialog = true
        }
        negativeButton("Decline") {
            disableAnalytics()
            hasShownConsentDialog = true
        }
        neutralButton("Privacy Policy") {
            openUrl("https://yoursite.com/privacy")
        }
    }
}
```

### 10.4 Content Rating & Age Restrictions

**Target Rating:** EVERYONE (ESRB) / PEGI 3

**To Maintain:**
- ❌ No violence or combat
- ❌ No sexual/suggestive content
- ❌ No gambling mechanics (loot boxes OK jika cosmetic only)
- ❌ No scary/horror elements
- ✅ Online interactions OK (with report/block features)
- ✅ In-app purchases OK (clearly labeled)

---

## 11. POST-LAUNCH ROADMAP

### 11.1 Version 1.1 (1-2 bulan post-launch)

**Critical Fixes:**
- Bug fixes dari user reports
- Performance optimization untuk low-end devices
- Balance tweaking berdasarkan analytics

**New Features:**
- Daily challenge system
- 3× new themes (NES, Virtual Boy, Custom)
- Leaderboard improvements (regional filter)
- Social sharing enhancements

### 11.2 Version 1.2 (3-4 bulan post-launch)

**Major Feature:**
- **Multiplayer Mode (Local):** 2-player split-screen battle
- Garbage block system (classic Tetris Attack style)
- Real-time leaderboard updates
- Profile customization (avatar, banner)

### 11.3 Version 2.0 (6+ bulan post-launch)

**Platform Expansion:**
- iOS version development (Swift/SwiftUI)
- Cross-platform cloud save
- Universal leaderboards

**Advanced Features:**
- Online multiplayer (PvP ranked matches)
- Tournament system
- Seasonal events dengan limited themes
- Replay system (save & share best games)

---

## 12. BUDGET ESTIMATION

### 12.1 Development Costs (Solo Dev)

| Item | Cost (IDR) | Notes |
|------|------------|-------|
| Android Studio | Gratis | IDE free |
| Domain + Hosting (Privacy Policy) | 200.000/tahun | Niagahoster/Hostinger |
| Google Play Developer Account | 325.000 (one-time) | $25 USD |
| Music Licensing/Commission | 500.000 - 2.000.000 | Jika hire composer |
| Graphic Assets (if outsource) | 1.000.000 - 3.000.000 | Icon, screenshots, promo |
| Testing Devices (if needed) | 1.000.000 - 3.000.000 | 1-2 used phones untuk testing |
| AdMob Setup | Gratis | Ads platform free |
| Firebase (Free Tier) | Gratis | Up to 10GB/month |
| **TOTAL (Minimal)** | **~2.000.000 - 5.000.000** | DIY approach |
| **TOTAL (Comfortable)** | **~5.000.000 - 10.000.000** | With outsourcing |

### 12.2 Marketing Budget (Optional)

| Item | Cost (IDR) | Notes |
|------|------------|-------|
| Google UAC Ads (Test) | 1.500.000 | $100 USD test budget |
| Social Media Ads | 500.000 - 1.500.000 | Facebook/Instagram |
| Influencer Outreach | 1.000.000 - 5.000.000 | Micro-influencers |
| Press Release Distribution | 500.000 | PR services |
| **TOTAL (Minimal)** | **0** | Organic only |
| **TOTAL (Paid)** | **3.000.000 - 10.000.000** | Aggressive push |

### 12.3 Revenue Projections (Conservative)

**Assumptions:**
- Month 1: 1,000 installs
- Month 3: 5,000 total installs
- Month 6: 15,000 total installs
- IAP conversion: 3%
- Ad revenue: $0.50 per user per month (free users)

**Month 6 Projection:**
- Total Installs: 15,000
- Active Users (MAU): 6,000 (40% retention)
- Premium Purchases: 450 users × Rp 39,000 = **Rp 17,550,000**
- Ad Revenue (5,550 free users): ~$2,775 = **Rp 42,195,000** (@Rp 15,200/USD)
- **Total Revenue (6 months):** ~Rp 60,000,000
- **Net Profit:** Rp 50,000,000 - Rp 60,000,000 (after costs)

**⚠️ Disclaimer:** Projections sangat optimistic. Real results bisa jauh lebih rendah tanpa marketing proper.

---

## 13. RISK ASSESSMENT & MITIGATION

### 13.1 Technical Risks

| Risk | Probability | Impact | Mitigation |
|------|-------------|--------|------------|
| Performance issues on old devices | Medium | High | Extensive testing on low-end devices, performance profiling |
| Memory leaks | Low | High | Code review, profiler tools, beta testing |
| Audio sync issues | Medium | Medium | Use SoundPool, test on multiple devices |
| Multiplayer networking (future) | High | Medium | Start with local multiplayer, research libraries (Photon, Netcode) |

### 13.2 Business Risks

| Risk | Probability | Impact | Mitigation |
|------|-------------|--------|------------|
| Low user acquisition | High | High | Marketing strategy, ASO optimization, community building |
| Trademark infringement (using "Tetris") | High | Critical | **Use generic name** (critical action!) |
| Competition from established games | High | Medium | Differentiate dengan authentic retro style, free model |
| Low IAP conversion | Medium | Medium | Test pricing, offer compelling premium value |
| Ad revenue lower than projected | Medium | Medium | Optimize ad placements, A/B testing |

### 13.3 Legal Risks

| Risk | Probability | Impact | Mitigation |
|------|-------------|--------|------------|
| Tetris Company C&D letter | Medium-High (if use "Tetris") | Critical | **Rename to generic title** |
| Music copyright claim | Low | Medium | Use royalty-free or original compositions |
| GDPR non-compliance | Low | High | Implement proper consent, privacy policy |
| Play Store policy violation | Low | High | Review policies before each update |

---

## 14. SUCCESS METRICS

### 14.1 Launch Goals (Month 1)

- ✅ **1,000+ installs** dalam 30 hari
- ✅ **4.0+ star rating** dengan minimal 50 reviews
- ✅ **Day 1 retention > 30%**
- ✅ **0 critical crashes** (crash-free rate > 99%)
- ✅ **Top 100** di kategori Puzzle (Indonesia)

### 14.2 3-Month Goals

- ✅ **10,000+ total installs**
- ✅ **4.2+ star rating** dengan 200+ reviews
- ✅ **Day 7 retention > 15%**
- ✅ **2%+ IAP conversion rate**
- ✅ **$500+ monthly revenue** (combined IAP + ads)

### 14.3 6-Month Goals

- ✅ **50,000+ total installs**
- ✅ **4.5+ star rating** dengan 500+ reviews
- ✅ **Top 50** di kategori Puzzle (Indonesia)
- ✅ **Profitable** (revenue > development costs)
- ✅ **Featured** di Play Store (any section)

### 14.4 1-Year Vision

- ✅ **200,000+ installs**
- ✅ **iOS version launched**
- ✅ **$2,000+ monthly revenue**
- ✅ **Active community** (Discord 500+ members)
- ✅ **Platform expansion** planning (Steam, Switch?)

---

## 15. CONCLUSION & NEXT STEPS

### 15.1 Summary

Game **"Block Drop Classic - Retro GB Puzzle"** (nama sementara untuk avoid trademark issues) adalah Tetris-inspired puzzle game dengan fokus pada authentic retro experience, targeting nostalgia market dan casual puzzle gamers. Dengan development timeline 14-20 minggu untuk full launch, project ini feasible untuk solo/small team development.

**Unique Value Proposition:**
- 100% authentic Game Boy aesthetic (visual + audio)
- Multiple game modes untuk variasi gameplay
- Monetization balance (free yang generous + premium yang worthwhile)
- Modern features (achievements, cloud save) dengan classic gameplay

### 15.2 Critical Success Factors

1. **Trademark Safety:** Gunakan generic name, JANGAN "Tetris"
2. **Performance:** Smooth gameplay di semua devices (target 60 FPS)
3. **Polish:** Retro aesthetic harus authentic, bukan "cheap retro"
4. **Monetization Balance:** Free version harus fun, premium harus worthwhile
5. **Community Engagement:** Build loyal fanbase sejak pre-launch

### 15.3 Immediate Next Steps (Week 1)

**Priority Actions:**
1. ✅ **Finalize App Name** - brainstorm 10 alternatives, test ASO
2. ✅ **Setup Development Environment** - Android Studio, Git, dependencies
3. ✅ **Create Project Structure** - MVVM architecture skeleton
4. ✅ **Design Mockups** - Figma wireframes untuk all screens
5. ✅ **Music/SFX Research** - Find royalty-free sources atau composer
6. ✅ **Domain Registration** - Untuk privacy policy hosting
7. ✅ **Google Play Developer Account** - Register + pay $25

**Week 1 Deliverable:** Project setup complete, mockups done, name finalized, ready untuk coding sprint

---

## 16. APPENDIX

### 16.1 Useful Resources

**Development:**
- [Android Developer Docs](https://developer.android.com)
- [Jetpack Compose Tutorials](https://developer.android.com/jetpack/compose/tutorial)
- [Kotlin Coroutines Guide](https://kotlinlang.org/docs/coroutines-guide.html)
- [Game Development Patterns](https://gameprogrammingpatterns.com)

**Design:**
- [Game Boy Color Palette](https://lospec.com/palette-list/nintendo-gameboy-bgb)
- [8-bit Sound Effects](https://opengameart.org)
- [Pixel Art Tutorials](https://www.pixilart.com/tutorials)

**Business:**
- [Google Play Academy](https://playacademy.exceedlms.com)
- [ASO Best Practices](https://developer.android.com/distribute/best-practices/grow)
- [Indie Game Marketing Guide](https://www.reddit.com/r/gamedev/wiki/marketing)

**Legal:**
- [The Tetris Company](https://tetris.com) - Know your competition/limitations
- [GDPR Compliance Checklist](https://gdpr.eu/checklist)
- [Google Play Policy Center](https://play.google.com/about/developer-content-policy/)

### 16.2 Competitor Analysis

| Game | Downloads | Rating | Monetization | Unique Feature |
|------|-----------|--------|--------------|----------------|
| Tetris (EA Official) | 100M+ | 4.5 | Free + IAP + Ads | Brand power, multiplayer |
| Block Puzzle Jewel | 50M+ | 4.4 | Free + Ads | Simplified mechanics |
| Quadris Puzzle | 1M+ | 4.3 | Free + IAP | Retro style (our direct competitor) |
| Falling Lightblocks | 500K+ | 4.6 | Free + Ads | Minimalist design |

**Our Differentiation:**
- Most authentic Game Boy recreation
- Best-in-class 8-bit audio
- Premium unlock (no subscription)
- Strong nostalgia focus

### 16.3 Contact & Support

**Developer Contact:**
- Email: [your-email]@gmail.com
- Discord: [your-discord-server-invite]
- Twitter/X: @[your-handle]
- GitHub: github.com/[your-username]

**Player Support:**
- In-app: Settings > Help & Support
- Email: support@[yourdomain].com
- FAQ: [yourdomain].com/faq
- Response time target: 24-48 hours

---

**Document Version:** 1.0  
**Last Updated:** May 2026  
**Author:** [Your Name / Studio Name]  
**Status:** READY FOR DEVELOPMENT

---

## FINAL CHECKLIST BEFORE DEVELOPMENT

- [ ] App name finalized (no trademark conflicts)
- [ ] Budget approved (minimal Rp 2-5 juta)
- [ ] Time commitment clear (3-6 months)
- [ ] Development environment setup
- [ ] Music/SFX sources identified
- [ ] Privacy policy planned
- [ ] Play Store account registered
- [ ] Target launch date decided
- [ ] Marketing strategy outlined
- [ ] Team roles assigned (jika team)

**When all checked:** ✅ Ready to code! 🚀

---

*Good luck dengan development! Remember: Start small, iterate fast, launch early, and listen to users. The best game is the one that actually ships!* 🎮
