import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.Rectangle;
import java.awt.BasicStroke;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineEvent;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.util.Arrays;
import java.util.Random;

/**
 * [ZombieSurvivalGame.java]
 * This program allows the user to play a zombie 
 * survival game where they are supposed to shoot
 * zombies to keep themselves alive for as many waves 
 * as possible.
 * @author Nathanael Ann, Alex Souphantong
 * @version 1.0, Jan 25, 2020
 */

public class ZombieSurvivalGame{

    //Declare variables

    //Set Game Window
    static JFrame gameWindow;
    static GraphicsPanel canvas;

    static final int WIDTH = 1380;
    static final int HEIGHT = 740;

    //Create Map
    static int tileX = 0;
    static int tileY = 0;

    
    static int counter =0;


    static int[][] map ={{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 9, 0, 0, 0, 0},
                            {0, 0, 0, 0, 4, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 0, 0, 0, 0},
                            {0, 0, 0, 0, 4, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 0, 0, 0, 0},
                            {0, 0, 0, 0, 4, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 0, 0, 0, 0},
                            {0, 0, 0, 0, 4, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 0, 0, 0, 0},
                            {0, 0, 0, 0, 4, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 0, 0, 0, 0},
                            {0, 0, 0, 0, 4, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 0, 0, 0, 0},
                            {0, 0, 0, 0, 4, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 0, 0, 0, 0},
                            {0, 0, 0, 0, 4, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 0, 0, 0, 0},
                            {0, 0, 0, 0, 4, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 0, 0, 0, 0},
                            {0, 0, 0, 0, 4, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 0, 0, 0, 0},
                            {0, 0, 0, 0, 4, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 0, 0, 0, 0},
                            {0, 0, 0, 0, 4, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 0, 0, 0, 0},
                            {0, 0, 0, 0, 4, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 0, 0, 0, 0},
                            {0, 0, 0, 0, 4, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 0, 0, 0, 0},
                            {0, 0, 0, 0, 4, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 0, 0, 0, 0},
                            {0, 0, 0, 0, 4, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 0, 0, 0, 0},
                            {0, 0, 0, 0, 4, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 0, 0, 0, 0},
                            {0, 0, 0, 0, 4, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 0, 0, 0, 0},
                            {0, 0, 0, 0, 4, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 0, 0, 0, 0},
                            {0, 0, 0, 0, 4, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 0, 0, 0, 0},
                            {0, 0, 0, 0, 4, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 0, 0, 0, 0},
                            {0, 0, 0, 0, 4, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 0, 0, 0, 0},
                            {0, 0, 0, 0, 4, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 0, 0, 0, 0},
                            {0, 0, 0, 0, 4, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 0, 0, 0, 0},
                            {0, 0, 0, 0, 4, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 0, 0, 0, 0},
                            {0, 0, 0, 0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                         
    };  

    static int[][] updatedMap = new int[map.length][map[0].length];

    static BufferedImage[] tileID = new BufferedImage[10];

    static boolean update = true;

    //Key and mouse listeners
    static MyKeyListener keyListener = new MyKeyListener();
    static MyMouseListener mouseListener = new MyMouseListener();

    static MyMouseMotionListener mouseMotionListener = new MyMouseMotionListener();
        
    static int mouseX;
    static int mouseY;

    //Player characteristics, animation, and movement
    static final int RUN_SPEED = 2;
    
    static int playerH = 31;
    static int playerW = 31;
    static int playerX = 288;
    static int playerY = 288;
    static int playerVx = 0;
    static int playerVy = 0;
    static int playerPicNum = 0;

    static double playerPercentage;
    static int playerBar = 25;
    static double playerHP = 100;

        
    static double elapsedTimePlayer = 0.0;  
    static long startPlayerTime = System.currentTimeMillis();
    static long endPlayerTime = 0;

    static Rectangle playerBox = new Rectangle(playerX, playerY, playerW, playerH);
    static Rectangle futureLeftPlayerHitBox = new Rectangle(playerX, playerY, playerW, playerH);
    static Rectangle futureRightPlayerHitBox = new Rectangle(playerX, playerY, playerW, playerH);
    static Rectangle futureUpPlayerHitBox = new Rectangle(playerX, playerY, playerW, playerH);
    static Rectangle futureDownPlayerHitBox = new Rectangle(playerX, playerY, playerW, playerH);

    static boolean goingLeft = false;
    static boolean goingRight = false;
    static boolean goingUp = false;
    static boolean goingDown = false;
   
    static boolean standLeft = false;
    static boolean standRight = false;
    static boolean standUp = false;
    static boolean standDown = false;

    static BufferedImage[] playerPic = new BufferedImage[17];

    static int[] nextLeftPic  = {1, 2, 3, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    static int[] nextRightPic = {5, 5, 5, 5, 5, 6, 7, 8, 5, 5, 5, 5, 5, 5, 5, 5, 5}; 
    static int[] nextUpPic = {9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 11, 12, 9, 9, 9, 9, 9};
    static int[] nextDownPic = {13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 14, 15, 16, 13};

    static int playerMoney = 0;

    static  int[] playerPos = new int[2];

    //Zombie characteristics, animation, and movement
    static int zombieH = 40;
    static int zombieW = 30;
    static int[] zombiePicNum;
    static int[] zombieX ;
    static int[] zombieY;
    static Rectangle[] zombieBox;
    static int numZombie = 0;
    static int[][][] zombiePath;
    static int currentZombie;

    static int[] downZombiePic = {1,2,3,0,0,0,0,0,0,0,0,0,0,0};
    static int[] leftZombiePic = {4,4,4,4,5,6,4,4,4,4,4,4,4,4};
    static int[] rightZombiePic = {7,7,7,7,7,7,7,8,9,7,7,7,7,7};
    static int[] upZombiePic =  {10,10,10,10,10,10,10,10,10,10,11,12,13,10};

    static int[] rightZombieMove;
    static int[] leftZombieMove;
    static int[] upZombieMove;
    static int[] downZombieMove;

    static BufferedImage[] zombiePic = new BufferedImage[14];

    static double[] zombieHP;
    static double[] zombiePercentage;
    static int[] zombieBar;


    static double[] elapsedTimeZombie;  
    static long[] startZombie;
    static long[] endZombie;

    static int[] zombieAttackPicNum;
    static BufferedImage[] zombieAttackPic = new BufferedImage[12];
    
    static int deadZombieCounter;
    static int[] zombieSpawn;

    static int[][] zombiePos;
    
    //Zombie attack
    static long[] startAttackTime;
    static long[] endAttackTime; 
    static double[] elapsedAttackTime; 
    
    static int[] nextLeftZombieAttack  = {1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static int[] nextRightZombieAttack  = {3, 3, 3, 4, 5, 3, 3, 3, 3, 3, 3, 3,};
    static int[] nextDownZombieAttack  = {6, 6, 6, 6, 6, 6, 7, 8, 6, 6, 6, 6};
    static int[] nextUpZombieAttack  = {9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 11, 9};

    static long[] startAttackPic;
    static long[] endAttackPic;
    static double[] elapsedAttackPic;

    //Zombie Freeze
    static long[] startFreezeTime;
    static long[] endFreezeTime;
    static double[] elapsedFreezeTime;
    
    static int[] freezeCounter;
    static boolean[] unfreeze;
    static boolean[] freezeZombie;

    static BufferedImage[] frozenZombiePic = new BufferedImage[4];
    static int[] nextFrozenZombie = {3,3, 3, 3, 2, 2, 2, 1, 1, 1, 0, 0, 0, 0};

    static String[] move;

    //Bullet characteristics, textures, and movement
    static double elapsedTimeBullet = 0.0;  
    static long startBullet = System.currentTimeMillis();
    static long endBullet = 0;

    static int numBullets = 40;    
    static boolean shooting = false;

    static int[] bulletRightX = new int[numBullets];
    static int[] bulletRightY = new int[numBullets];
    static int[] bulletLeftX = new int[numBullets];
    static int[] bulletLeftY = new int[numBullets];
    static int[] bulletUpX = new int[numBullets];
    static int[] bulletUpY = new int[numBullets];

    static Rectangle[] bulletRightHitBox = new Rectangle[numBullets];
    static boolean[] bulletRightVisible = new boolean[numBullets];
    static Rectangle[] bulletLeftHitBox = new Rectangle[numBullets];
    static boolean[] bulletLeftVisible = new boolean[numBullets];
    static Rectangle[] bulletUpHitBox = new Rectangle[numBullets];
    static boolean[] bulletUpVisible = new boolean[numBullets];
    static int[] bulletDownX = new int[numBullets];
    static int[] bulletDownY = new int[numBullets];
    static Rectangle[] bulletDownHitBox = new Rectangle[numBullets];
    static boolean[] bulletDownVisible = new boolean[numBullets];

    static int bulletW = 1;
    static int bulletH = 10;

    static int bulletSpeed = 10;

    static int currentBulletRight = 0;
    static int currentBulletLeft = 0;
    static int currentBulletUp = 0;
    static int currentBulletDown = 0;

    static boolean useHolyWater = false;
    static boolean useFireBullet = false;
    static boolean useLavaBullet = false;
    static boolean useFreezeBullet = false;

    static BufferedImage heartIcon;
    static BufferedImage rapidIcon;
    static BufferedImage bulletIcon;
    static BufferedImage wallIcon;

    static BufferedImage bullet1RightPic;
    static BufferedImage bullet1LeftPic;
    static BufferedImage bullet1DownPic;
    static BufferedImage bullet1UpPic;

    static int bulletDmg = 10;

    static boolean autoFire = false;
    static boolean canShoot = false;
    static boolean hasShot = false;

    static int shootingSpeed = 300;

    static int ammo = 0;
    static int speedAmmo = 0;

    //Player wall characteristics and placement
    static int wallH = 32;
    static int wallW = 32;

    static Rectangle[] wallBox;

    static boolean placeWall = false;
    static int MAX_WALL_AMOUNT = 20;

    static Rectangle[] playerWall = new Rectangle[MAX_WALL_AMOUNT];
    static int[] playerWallX = new int[MAX_WALL_AMOUNT];
    static int[] playerWallY = new int[MAX_WALL_AMOUNT];
    static int[] wallHealth = new int[MAX_WALL_AMOUNT];
    static boolean[] playerWallVisible = new boolean[MAX_WALL_AMOUNT];

    static Rectangle futureWallDownHitBox = new Rectangle(0, 0, 2, 2);
    static Rectangle futureWallUpHitBox = new Rectangle(0, 0, 2, 2);
    static Rectangle futureWallLeftHitBox = new Rectangle(0, 0, 2, 2);
    static Rectangle futureWallRightHitBox = new Rectangle(0, 0, 2, 2);

    static int currentPlayerWall = 0;
    static int wallHit = 0;
    static int playerWallInventory = 0;
    static int playerWallOnBoard = 0;

    static boolean noBlocksDestroyed = true;

    static BufferedImage playerWallPic;

    //Sound effects and background music
    static AudioInputStream audioFireBallStream;
    static AudioInputStream audioRunStream;
    static AudioInputStream audioWallStream;
    static AudioInputStream audioMusicStream;
    static AudioInputStream audioWallBreakStream;
    static AudioInputStream audioMenuStream;
    static AudioInputStream audioEndStream;
    static AudioInputStream audioZombieHitStream;
    static AudioInputStream audioPlayerHitStream;
    static AudioInputStream audioBuyStream;
    
    static Clip shot1Sound;
    static Clip runSound;
    static Clip wallSound;
    static Clip musicSound;
    static Clip wallBreakSound;
    static Clip menuSound;
    static Clip endSong;
    static Clip zombieHitSound;
    static Clip playerHitSound;
    static Clip buySound;


    //Screens and shop system
    static BufferedImage menuPic;
    static BufferedImage instructionPic;
    static BufferedImage endPic;

    static BufferedImage shopPic;

    static int screen = 0;

    //Wave system
    static int waveCount;
    static boolean newWave = true;



    public static void main(String[] args){

        //Set game window 
        gameWindow = new JFrame("Game Window");
        gameWindow.setSize(WIDTH,HEIGHT);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create mouse and key listener, and graphics panel
        canvas = new GraphicsPanel();
        canvas.addMouseListener(mouseListener);
        canvas.addMouseMotionListener(mouseMotionListener);
        canvas.addKeyListener(keyListener);
        gameWindow.add(canvas); 

        //Initialize playerWallVisible and wall Health
        Arrays.fill(playerWallVisible, true);
        Arrays.fill(wallHealth, 50);

        //Create Map
        int wallCount = 0;
        int wallBoxCount = 0;

        for(int i = 0; i < map.length; i++){

            wallCount = wallCount + amountInArray(map[i], 4) + amountInArray(map[i], 5) + amountInArray(map[i], 6) + amountInArray(map[i], 7) + amountInArray(map[i], 8) +  amountInArray(map[i], 9);
        
        }

        

        wallBox = new Rectangle[wallCount];


        for(int i = 0; i < map.length; i++){

            for(int count = 0; count < map[0].length; count++){
                
                if(map[i][count] == 4 || map[i][count] == 5 || map[i][count] == 6 || map[i][count] == 7 || map[i][count] == 8 || map[i][count] == 9){

                    wallBox[wallBoxCount] = new Rectangle(i*32, count*32, wallH, wallW);

                    wallBoxCount++;
                   
                    
                  
                }


            }


        }

        //Import images
        for(int i = 0; i < 10; i++){

            try{
                tileID[i] = ImageIO.read(new File("images/tile" + Integer.toString(i)+ ".png"));
            } catch (IOException ex){}
        }

        for (int i=0; i<17; i++){

            try {             
                playerPic[i] = ImageIO.read(new File("images/ninja" + Integer.toString(i)+ ".png"));
            } catch (IOException ex){} 
        }

        for(int i = 0; i < 14; i++){

            try {             
                zombiePic[i] = ImageIO.read(new File("images/zombie" + Integer.toString(i+1)+ ".png"));
            } catch (IOException ex){} 
        }

        for(int i  = 0; i < 4; i++){
            try {             
                frozenZombiePic[i] = ImageIO.read(new File("images/Freeze" + Integer.toString(i)+ ".png"));
            } catch (IOException ex){}

        }



        for(int i = 0; i < zombieAttackPic.length; i++){

            try {             
                zombieAttackPic[i] = ImageIO.read(new File("images/Attack" + Integer.toString(i+1)+ ".png"));
            } catch (IOException ex){} 
        }

        try {         
            
            heartIcon = ImageIO.read(new File("images/heartIcon.png"));
            bulletIcon = ImageIO.read(new File("images/bulletIcon.png"));
            wallIcon = ImageIO.read(new File("images/playerWall.png"));
            rapidIcon = ImageIO.read(new File("images/rapidIcon.png"));

            shopPic = ImageIO.read(new File("images/shop.png"));
            menuPic = ImageIO.read(new File("images/menu.png"));
            instructionPic = ImageIO.read(new File("images/instruction.png"));
            endPic = ImageIO.read(new File("images/endScreen.png"));

            playerWallPic = ImageIO.read(new File("images/playerWall.png"));
          
            bullet1RightPic = ImageIO.read(new File("images/bullet1right.png"));
            bullet1LeftPic = ImageIO.read(new File("images/bullet1left.png"));
            bullet1DownPic = ImageIO.read(new File("images/bullet1down.png"));          
            bullet1UpPic = ImageIO.read(new File("images/bullet1Up.png"));

            //Import Sounds
            File audioFileShot1 = new File("Sounds/shot1.wav");
            audioFireBallStream = AudioSystem.getAudioInputStream(audioFileShot1);
            shot1Sound = AudioSystem.getClip();
            shot1Sound.open(audioFireBallStream);

            File audioPlayerHit = new File("Sounds/playerHit.wav");
            audioPlayerHitStream = AudioSystem.getAudioInputStream(audioPlayerHit);
            playerHitSound = AudioSystem.getClip();
            playerHitSound.open(audioPlayerHitStream);

            File audioBuy = new File("Sounds/buySound.wav");
            audioBuyStream = AudioSystem.getAudioInputStream(audioBuy);
            buySound = AudioSystem.getClip();
            buySound.open(audioBuyStream);

            File audioFileRun = new File("Sounds/running.wav");
            audioRunStream = AudioSystem.getAudioInputStream(audioFileRun);
            runSound = AudioSystem.getClip();
            runSound.open(audioRunStream);

            File audioFileWall = new File("Sounds/placeWall.wav");
            audioWallStream = AudioSystem.getAudioInputStream(audioFileWall);
            wallSound = AudioSystem.getClip();
            wallSound.open(audioWallStream);

            File audioFileZombieHit = new File("Sounds/zombieHit.wav");
            audioZombieHitStream = AudioSystem.getAudioInputStream(audioFileZombieHit);
            zombieHitSound = AudioSystem.getClip();
            zombieHitSound.open(audioZombieHitStream);

            File audioFileMusic = new File("Sounds/gameMusic.wav");
            audioMusicStream = AudioSystem.getAudioInputStream(audioFileMusic);
            musicSound = AudioSystem.getClip();
            musicSound.open(audioMusicStream);

            File audioFileWallBreak = new File("Sounds/wallBreak.wav");
            audioWallBreakStream = AudioSystem.getAudioInputStream(audioFileWallBreak);
            wallBreakSound = AudioSystem.getClip();
            wallBreakSound.open(audioWallBreakStream);

            File audioMenu = new File("Sounds/menuSong.wav");
            audioMenuStream = AudioSystem.getAudioInputStream(audioMenu);
            menuSound = AudioSystem.getClip();
            menuSound.open(audioMenuStream);

            File audioEnd = new File("Sounds/endSong.wav");
            audioEndStream = AudioSystem.getAudioInputStream(audioEnd);
            endSong = AudioSystem.getClip();
            endSong.open(audioEndStream);

        } catch (Exception ex){} 

        //Set sound volumes
        FloatControl volumeShot1Sound = (FloatControl) shot1Sound.getControl(FloatControl.Type.MASTER_GAIN); 
        volumeShot1Sound.setValue(-10.0f);

        FloatControl volumePlayerHitSound = (FloatControl) playerHitSound.getControl(FloatControl.Type.MASTER_GAIN); 
        volumePlayerHitSound.setValue(+5.0f);

        FloatControl volumeRunSound = (FloatControl) runSound.getControl(FloatControl.Type.MASTER_GAIN); 
        volumeRunSound.setValue(+6.0f);

        FloatControl volumeMusicSound = (FloatControl) musicSound.getControl(FloatControl.Type.MASTER_GAIN); 
        volumeMusicSound.setValue(-10.0f);

        FloatControl volumeMenuSound = (FloatControl) menuSound.getControl(FloatControl.Type.MASTER_GAIN); 
        volumeMenuSound.setValue(-15.0f);

        FloatControl volumeWallBreakSound = (FloatControl) wallBreakSound.getControl(FloatControl.Type.MASTER_GAIN); 
        volumeMenuSound.setValue(+5.0f);


        //Update map
        for(int i = 0; i < map.length; i++){

            for(int count = 0; count < map[0].length; count++){

                if(map[i][count] != 1 &&  map[i][count] != 2 && map[i][count] != 3){

                    updatedMap[i][count] = -1;

                 }else {

                    updatedMap[i][count] = 0;
                }


            }

        }
        
        for(int i = 0; i < map.length; i++){

            for(int count = 0; count < map[0].length; count++){


            }

        }

        gameWindow.setVisible(true);
        runGameLoop();
    
    }

    
    public static void runGameLoop(){
    
        //Get player position
        playerPos[0] = getPlayerX();
        playerPos[1] = getPlayerY();


       while (true) {

            gameWindow.repaint();

            try  {Thread.sleep(15);} catch(Exception e){}

            if ((screen == 0) || (screen == 2)){ //Show menu screen and music

                menuSound.start();
                menuSound.loop(Clip.LOOP_CONTINUOUSLY);

            } else if (screen == 1) { //Show game screen and music

                menuSound.stop();
                endSong.stop();
                endSong.setFramePosition(0); 
                musicSound.start();
                musicSound.loop(Clip.LOOP_CONTINUOUSLY);

                //Update player position
                if (playerPos[0] != getPlayerX() ||playerPos[1] != getPlayerY() ){

                    update = true;
                }

                playerPos[0] = getPlayerX();
                playerPos[1] = getPlayerY();

                //Move atributes of game
                zombieMovement();
                bulletMovement();
                characterMovement();

                //Place walls
                wallPlacement();
                
                //Work Shop system
                bulletShopSystem();

                zombieAttack();

                //Check if player dies
                if(playerHP <= 0 ){

                    screen = 3;

                }
              
            } else if (screen == 3){ //Show end screen and play music

                musicSound.stop();
                musicSound.setFramePosition(0); 
                runSound.stop();
                endSong.start();
                endSong.loop(Clip.LOOP_CONTINUOUSLY);

            }
        }
    } // runGameLoop method end

    /**
     * zombieAttack
     * This method decreases the player health if it gets too close
     * to a zombie and adds attack animation for it
     */

    public static void zombieAttack(){

        //Check if zombie and player hitbox collide
        for (int i = 0; i < numZombie; i++){

            if (checkCollisionPlayer(zombieBox[i])){

                if (startAttackTime[i] == 0){

                    playerHitSound.stop();
                    playerHitSound.flush();              
                    playerHitSound.setFramePosition(0);  
                    playerHitSound.start();
                    playerHP = playerHP - 10;
                    startAttackTime[i] = System.currentTimeMillis(); 

                } 
                
                //Add delay for next attack
                if (elapsedAttackTime[i]/1000 > 1){

                    playerHitSound.stop();
                    playerHitSound.flush();              
                    playerHitSound.setFramePosition(0);  
                    playerHitSound.start();
                    playerHP = playerHP - 10;
                    startAttackTime[i] = System.currentTimeMillis(); 

                }

                if (startAttackPic[i] == 0){

                    startAttackPic[i] = System.currentTimeMillis();

                } 

                //Animation for zombie attack
                if (elapsedAttackPic[i]/200 > 1){

                    if ((zombiePicNum[i] >= 7) && (zombiePicNum[i] <= 9)){

                        zombieAttackPicNum[i] = nextRightZombieAttack[zombieAttackPicNum[i]];
                    
                    } else if ((zombiePicNum[i] >= 4) && (zombiePicNum[i] <= 6)){

                        zombieAttackPicNum[i] = nextLeftZombieAttack[zombieAttackPicNum[i]];
                    
                    }  else if ((zombiePicNum[i] >= 0) && (zombiePicNum[i] <= 3)){

                        zombieAttackPicNum[i] = nextDownZombieAttack[zombieAttackPicNum[i]];
                    
                    }  else if ((zombiePicNum[i] >= 10) && (zombiePicNum[i] <= 13)){

                        zombieAttackPicNum[i] = nextUpZombieAttack[zombieAttackPicNum[i]];
                    
                    }
                    startAttackPic[i] = System.currentTimeMillis();

                }
                
                endAttackPic[i] = System.currentTimeMillis();
                elapsedAttackPic[i] = endAttackPic[i] - startAttackPic[i];
                
                endAttackTime[i] = System.currentTimeMillis();
                elapsedAttackTime[i] = endAttackTime[i] - startAttackTime[i];
                
            } else {

                startAttackTime[i] = 0;
                startAttackPic[i] = 0;

            }
            
        }
    }

    /**
     * zombieMovement
     * This method moves the zombies across the map
     */

    public static void zombieMovement(){

        if(newWave){
            waveCount++;

    

            if(numZombie  < 6){


                numZombie++;

            }

            zombiePicNum = new int[numZombie];
            
            startAttackTime = new long[numZombie];
            endAttackTime = new long[numZombie];
            elapsedAttackTime = new double[numZombie];
            zombieAttackPicNum = new int[numZombie];
            startFreezeTime =  new long[numZombie];
            endFreezeTime =  new long[numZombie];
            elapsedFreezeTime = new double[numZombie];
            startAttackPic = new long[numZombie];
            endAttackPic = new long[numZombie];
            elapsedAttackPic = new double[numZombie];
            zombiePos = new int[numZombie][2];
            zombiePath = new int[numZombie][2][];
            move = new String[numZombie];
            rightZombieMove =new int[numZombie];
            leftZombieMove =new int[numZombie];
            upZombieMove = new int[numZombie];
            downZombieMove = new int[numZombie];
            zombieX = new int[numZombie];
            zombieY = new int[numZombie];
            elapsedTimeZombie= new double[numZombie];  
            startZombie = new long[numZombie];
            endZombie = new long[numZombie];
            zombieBox = new Rectangle[numZombie];
            zombieHP = new double[numZombie];
            zombiePercentage = new double[numZombie];
            zombieBar = new int[numZombie];
            freezeCounter = new int[numZombie];
            unfreeze = new boolean[numZombie];
            freezeZombie = new boolean[numZombie];

            for(int i = 0; i < numZombie; i++){

                leftZombieMove[i] = 0;
                rightZombieMove[i] = 0;
                upZombieMove[i] = 0;
                downZombieMove[i] = 0;
                zombiePicNum[i] = 0;
                zombieHP[i] = 100;
                freezeCounter[i] = 0;
                unfreeze[i] = false;
                freezeZombie[i] = false;

                zombieSpawn = findSpawnableArea();
                zombieX[i] = zombieSpawn[0];
                zombieY[i] = zombieSpawn[1];
                zombieBox[i] = new Rectangle(zombieX[i], zombieY[i],zombieW,zombieH);

                zombiePos[i][0] = (zombieX[i]+15)/32;
                zombiePos[i][1] = (zombieY[i]+20)/32;
                zombiePath[i] = pathfind(updatedMap,zombiePos[i],playerPos);
                
            
                
            }


            newWave = false;
        }

        for(int i = 0; i < numZombie;i++){

            if(zombieHP[i] > 0){

                zombiePos[i][0] = (zombieX[i]+15)/32;
                zombiePos[i][1] = (zombieY[i]+20)/32;

                    if(update){
                    zombiePath[i] = pathfind(updatedMap,zombiePos[i],playerPos);
                    

                }

                try{
                    move[i] = getNextMove(zombiePath[i], zombiePos[i]);

                }catch(Exception e){}


                if (( rightZombieMove[i] + upZombieMove[i] +  downZombieMove[i] == 0) && ((move[i] == "left") || (leftZombieMove[i] != 0)) && !checkZombiePos(zombiePos[i][0]- 1 ,zombiePos[i][1]) && !unfreeze[i]){

                    
                    zombieX[i] = zombieX[i] - 1;
                    leftZombieMove[i] ++;
                    
                    moveLeftZombie(i);
                    if (leftZombieMove[i] == 32){
                            leftZombieMove[i] = 0;
                    }

                    freezeCounter[i] = 0;

                }else if ((leftZombieMove[i]  + upZombieMove[i] +  downZombieMove[i] == 0) && ((move[i] == "right") || (rightZombieMove[i] != 0)) &&  !checkZombiePos(zombiePos[i][0]+ 1 ,zombiePos[i][1]) && !unfreeze[i]){
                    
                    zombieX[i] = zombieX[i] + 1;
                    rightZombieMove[i] ++;
                
                
                    moveRightZombie(i);
                    if (rightZombieMove[i] == 32){
                            rightZombieMove[i] = 0;
                    }
                    
                    freezeCounter[i] = 0;


                }else if ((leftZombieMove[i] + rightZombieMove[i] + downZombieMove[i] == 0) && ((move[i] == "up") || (upZombieMove[i] != 0)) && !checkZombiePos(zombiePos[i][0]  ,zombiePos[i][1]-1 ) && !unfreeze[i]){

                    zombieY[i] = zombieY[i] - 1;
                    upZombieMove[i]++;
                
                    moveUpZombie(i);

                    if (upZombieMove[i] == 32){
                        upZombieMove[i] = 0;
                    }
                    freezeCounter[i] = 0;

                }else if ((leftZombieMove[i] + rightZombieMove[i] + upZombieMove[i]  == 0) && ((move[i] == "down") || (downZombieMove[i] != 0)) && !checkZombiePos(zombiePos[i][0] ,zombiePos[i][1]+1)&& !unfreeze[i]){

                    zombieY[i] = zombieY[i] + 1;
                    downZombieMove[i] ++;
                
                
                    moveDownZombie(i);
                    if (downZombieMove[i] == 32){
                        downZombieMove[i] =0;
                    
                    }

                    freezeCounter[i] = 0;
                }else{


                    freezeCounter[i]++;
                }

                if(freezeCounter[i] > 75 && !freezeZombie[i]){

                    if(rightZombieMove[i] > 0){

                        

                        zombieX[i] = zombieX[i] + 1;
                        moveLeftZombie(i);
                        unfreeze[i] = true;

                        rightZombieMove[i] -- ;

                        if(rightZombieMove[i] == 0){

                            unfreeze[i] = false;
                            zombiePath[i] = pathfind(updatedMap,zombiePos[i],playerPos);

                            

                            freezeCounter[i] = 0;
                        }
                    
                        


                    
                    }else if(leftZombieMove[i] > 0){

                    
                            zombieX[i] = zombieX[i] - 1;
                            moveRightZombie(i);
                            unfreeze[i] = true;

                            leftZombieMove[i] --;

                            if(leftZombieMove[i] == 0){
                                unfreeze[i] = false;
                                zombiePath[i] = pathfind(updatedMap,zombiePos[i],playerPos);
                                

                                freezeCounter[i] = 0;
                            }




                    }else if(downZombieMove[i] > 0){

                        

                            zombieY[i] = zombieY[i] -1;
                            moveUpZombie(i);
                            unfreeze[i] = true;

                            downZombieMove[i] --;

                            if(downZombieMove[i] == 0){

                                unfreeze[i] = false;
                                zombiePath[i] = pathfind(updatedMap,zombiePos[i],playerPos);

                                freezeCounter[i] = 0;
                            }
                

                        

                    }else if(upZombieMove[i] > 0){

                        zombieY[i] = zombieY[i] + 1;
                        moveDownZombie(i);
                        unfreeze[i] = true;
                        upZombieMove[i]--;

                        if(upZombieMove[i] == 0){
                            zombiePath[i] = pathfind(updatedMap,zombiePos[i],playerPos);

                            unfreeze[i] = false;
                            freezeCounter[i] = 0;

                        }
                    }
                }

                zombiePos[i][0] = (zombieX[i]+15)/32;
                zombiePos[i][1] = (zombieY[i]+20)/32;

                zombieBox[i].setLocation(zombieX[i], zombieY[i]);
                
                zombiePercentage[i] = 25 * (zombieHP[i]/100);
                zombieBar[i] = (int) zombiePercentage[i];


                if(freezeZombie[i]){

                    endFreezeTime[i] =  System.currentTimeMillis();
                    elapsedFreezeTime[i] =  endFreezeTime[i] - startFreezeTime[i];

                    if(elapsedFreezeTime[i] > 4000){

                        freezeZombie[i] = false;

                    }
                }
            }
        }
        update = false;

        deadZombieCounter = 0;
            

        for(int count = 0; count < numZombie; count++){

            if(zombieHP[count] <= 0){
                
                deadZombieCounter++;
            }
        }

        if(deadZombieCounter  == numZombie){


            newWave = true;
        }

    }

    /**
     * bulletShopSystem
     * This method changes the damage and speed
     * of bullets fired
     */

    public static void bulletShopSystem(){

        if (useHolyWater){ //If holy water is bought

            bulletDmg = 50;

            try {
                bullet1DownPic = ImageIO.read(new File("images/holyBulletDown.png"));
                bullet1UpPic = ImageIO.read(new File("images/holyBulletUp.png"));
                bullet1LeftPic = ImageIO.read(new File("images/holyBulletLeft.png"));
                bullet1RightPic = ImageIO.read(new File("images/holyBulletRight.png"));
            } catch (IOException ex){} 

        } else if (useFireBullet){ //If fire bullet is bought

            bulletDmg = 15;

            try {
                bullet1DownPic = ImageIO.read(new File("images/fireBulletDown.png"));
                bullet1UpPic = ImageIO.read(new File("images/fireBulletUp.png"));
                bullet1LeftPic = ImageIO.read(new File("images/fireBulletLeft.png"));
                bullet1RightPic = ImageIO.read(new File("images/fireBulletRight.png"));
            } catch (IOException ex){} 

        } else if (useLavaBullet){ //If lave bullet is bought

            bulletDmg = 25;

            try {
                bullet1DownPic = ImageIO.read(new File("images/lavaBullet.png"));
                bullet1UpPic = ImageIO.read(new File("images/lavaBullet.png"));
                bullet1LeftPic = ImageIO.read(new File("images/lavaBullet.png"));
                bullet1RightPic = ImageIO.read(new File("images/lavaBullet.png"));
            } catch (IOException ex){} 

        } else if (useFreezeBullet){ //If freeze bullet is bought

            bulletDmg = 20;

            try {
                bullet1DownPic = ImageIO.read(new File("images/snowBulletDown.png"));
                bullet1UpPic = ImageIO.read(new File("images/snowBulletUp.png"));
                bullet1LeftPic = ImageIO.read(new File("images/snowBulletLeft.png"));
                bullet1RightPic = ImageIO.read(new File("images/snowBulletRight.png"));
            } catch (IOException ex){} 
        }

        //Reset bullet damage and image if used up all ammo
        if ((ammo <= 0) && (useHolyWater)){

            useHolyWater = false;

        }
        if ((ammo <= 0) && (useFireBullet)){

            useFireBullet = false;

        }
        if ((ammo <= 0) && (useLavaBullet)){

            useLavaBullet = false;

        }
        if ((ammo <= 0) && (useFreezeBullet)){

            useFreezeBullet = false;
            
        }
        if ((ammo <= 0) && !(useHolyWater) && !(useFireBullet) && !(useLavaBullet) && !(useFreezeBullet)){
            
            bulletDmg = 10;

            try {
                bullet1RightPic = ImageIO.read(new File("images/bullet1right.png"));
                bullet1LeftPic = ImageIO.read(new File("images/bullet1left.png"));
                bullet1DownPic = ImageIO.read(new File("images/bullet1down.png"));          
                bullet1UpPic = ImageIO.read(new File("images/bullet1Up.png"));
            } catch (IOException ex){} 

        }
        if (speedAmmo <= 0){ //If rapid fire is bought

            shootingSpeed = 300;

        }
    }

    /**
     * restartGame
     * This method resets all the variables to
     * how it was when the game started
     */
    public static void restartGame(){

        //Reset variables
        newWave = true;
        waveCount = 0;
        numZombie = 0;
        playerMoney = 0;
        ammo = 0;
        speedAmmo = 0;
        playerHP = 100;
        playerX = 288;
        playerY = 288;
        playerWallOnBoard = 0;
        playerWallInventory = 0;

        Arrays.fill(playerWall, null);
        Arrays.fill(wallHealth, 50);
        Arrays.fill(playerWallX, 0);
        Arrays.fill(playerWallY, 0);
        Arrays.fill(playerWallVisible, true);

    }

    /**
     * bulletMovement
     * This method moves the bullets and checks for collision
     * between the bullet and other assets of the game
     */

    public static void bulletMovement(){

        //Create bullet and reset to player position
        if ((elapsedTimeBullet/shootingSpeed > 1) && (canShoot)){
           
            if ((shooting) && (playerVy == 0) && ((playerPicNum == 6) || (goingRight))){
                
                if (ammo > 0){

                    ammo--;

                }  
                if (speedAmmo > 0){

                    speedAmmo--;

                }  

                //Set bullet to player position
                bulletRightX[currentBulletRight] = playerX + playerW/2 - bulletW/2;
                bulletRightY[currentBulletRight] = playerY + 25;
                bulletRightVisible[currentBulletRight] = true;
                currentBulletRight = (currentBulletRight + 1)%numBullets;

                shot1Sound.stop();
                shot1Sound.flush();              
                shot1Sound.setFramePosition(0);  
                shot1Sound.start();

                if (!(autoFire)){

                    shooting = false;

                }
                hasShot = true;

            } else if ((shooting) && (playerVy == 0) && ((playerPicNum == 4) || (goingLeft))){
                
                if (ammo > 0){

                    ammo--;

                }  
                if (speedAmmo > 0){

                    speedAmmo--;

                }  

                //Set bullet to player position
                bulletLeftX[currentBulletLeft] = playerX + playerW/2 - bulletW/2;
                bulletLeftY[currentBulletLeft] = playerY + 25;
                bulletLeftVisible[currentBulletLeft] = true;
                currentBulletLeft = (currentBulletLeft + 1)%numBullets;

                shot1Sound.stop();
                shot1Sound.flush();              
                shot1Sound.setFramePosition(0);  
                shot1Sound.start();

                if (!(autoFire)){

                    shooting = false;

                }
                hasShot = true;

            } else if ((shooting) && (playerVx == 0) && ((playerPicNum == 0) || (goingDown))){
                
                if (ammo > 0){

                    ammo--;

                }  
                if (speedAmmo > 0){

                    speedAmmo--;

                }  

                //Set bullet to player position
                bulletDownX[currentBulletDown] = playerX + 10;
                bulletDownY[currentBulletDown] = playerY + playerH;
                bulletDownVisible[currentBulletDown] = true;
                currentBulletDown = (currentBulletDown + 1)%numBullets;

                shot1Sound.stop();
                shot1Sound.flush();              
                shot1Sound.setFramePosition(0);  
                shot1Sound.start();
                
                if (!(autoFire)){

                    shooting = false;

                }
                hasShot = true;
            } else if ((shooting) && (playerVx == 0) && ((playerPicNum == 10) || (goingUp))){
                
                if (ammo > 0){

                    ammo--;

                }  
                if (speedAmmo > 0){

                    speedAmmo--;

                }  

                //Set bullet to player position
                bulletUpX[currentBulletUp] = playerX + playerW/2 - bulletW/2;
                bulletUpY[currentBulletUp] = playerY + 25;
                bulletUpVisible[currentBulletUp] = true;
                currentBulletUp = (currentBulletUp + 1)%numBullets;

                shot1Sound.stop();
                shot1Sound.flush();              
                shot1Sound.setFramePosition(0); 
                shot1Sound.start();

                if (!(autoFire)){

                    shooting = false;

                }
                hasShot = true;
            }    
            startBullet = System.currentTimeMillis();  
        }   
        endBullet = System.currentTimeMillis();
        elapsedTimeBullet = endBullet - startBullet; 


        for (int i=0; i<numBullets; i++){

            //Check collision of right bullet
            if (bulletRightVisible[i]){

                //Move bullet right
                bulletRightX[i] = bulletRightX[i] + bulletSpeed;
                bulletRightHitBox[i] = new Rectangle(bulletRightX[i], bulletRightY[i], bulletH, bulletW);
                
                //Check collision with player wall and right bullet
                if (checkCollisionPlayerWall(bulletRightHitBox[i])) {

                    wallHealth[wallHit] = wallHealth[wallHit] - 10;
                    bulletRightVisible[i] = false;

                    if (wallHealth[wallHit] <= 0){

                        playerWallOnBoard--;

                        wallBreakSound.stop();
                        wallBreakSound.flush();              
                        wallBreakSound.setFramePosition(0);  
                        wallBreakSound.start();

                        playerWallVisible[wallHit] = false;
                        updatedMap[playerWallX[wallHit]/32][playerWallY[wallHit]/32] =0 ;
                        playerWallX[wallHit] = 3000;
                        playerWallY[wallHit] = 3000;
                        playerWall[wallHit] = new Rectangle(playerWallX[wallHit],playerWallY[wallHit],wallH,wallW);
                    
                    }
                }

                //Delete bullet right
                if (checkCollision(bulletRightHitBox[i])) {

                    bulletRightVisible[i] = false;

                }
                
                //Check collision with zombie and right bullet
                currentZombie = checkZombieHit(bulletRightHitBox[i]);

                if(currentZombie != -1){

                    zombieHitSound.stop();
                    zombieHitSound.flush();              
                    zombieHitSound.setFramePosition(0); 
                    zombieHitSound.start();

                    bulletRightVisible[i] = false;

                    //Freeze zombie if zombie hit with freeze bullet
                    if (bulletDmg == 20){

                        unfreeze[currentZombie] = true;
                        freezeZombie[currentZombie] = true;
                        startFreezeTime[currentZombie] =  System.currentTimeMillis(); 

                    }
                    
                    zombieHP[currentZombie] = zombieHP[currentZombie] - bulletDmg;
                    if(zombieHP[currentZombie] <= 0){

                        zombieBox[currentZombie].setLocation(3000, 3000);
                        zombiePos[currentZombie][0] = 0;
                        zombiePos[currentZombie][1] = 0;
                        playerMoney = playerMoney + 50;

                    }
                }

        
            }
            //Check collision of left bullet
            if (bulletLeftVisible[i]){

                //Move bullet left
                bulletLeftX[i] = bulletLeftX[i] - bulletSpeed;
                bulletLeftHitBox[i] = new Rectangle(bulletLeftX[i], bulletLeftY[i], bulletH, bulletW);
                
                //Check collision of player wall and left bullet
                if (checkCollisionPlayerWall(bulletLeftHitBox[i])) {

                    wallHealth[wallHit] = wallHealth[wallHit] - 10;
                    bulletLeftVisible[i] = false;

                    if (wallHealth[wallHit] <= 0){

                        playerWallOnBoard--;
                        wallBreakSound.stop();
                        wallBreakSound.flush();              
                        wallBreakSound.setFramePosition(0);  
                        wallBreakSound.start();

                        playerWallVisible[wallHit] = false;
                        updatedMap[playerWallX[wallHit]/32][playerWallY[wallHit]/32] = 0 ;
                        playerWallX[wallHit] = 3000;
                        playerWallY[wallHit] = 3000;
                        playerWall[wallHit] = new Rectangle(playerWallX[wallHit],playerWallY[wallHit],wallH,wallW);
                    }
                }
                if (checkCollision(bulletLeftHitBox[i])) {

                    bulletLeftVisible[i] = false;
                
                }        

                currentZombie = checkZombieHit(bulletLeftHitBox[i]);

                if(currentZombie != -1){

                    zombieHitSound.stop();
                    zombieHitSound.flush();              
                    zombieHitSound.setFramePosition(0); 
                    zombieHitSound.start();

                    bulletLeftVisible[i] = false;

                    //Freeze zombie
                    if(bulletDmg == 20){

                        unfreeze[currentZombie] = true;
                        freezeZombie[currentZombie] = true;
                        startFreezeTime[currentZombie] =  System.currentTimeMillis(); 

                    }
                    
                    zombieHP[currentZombie] = zombieHP[currentZombie] - bulletDmg;

                    if(zombieHP[currentZombie] <= 0){

                        zombieBox[currentZombie].setLocation(3000, 3000);
                        zombiePos[currentZombie][0] = 0;
                        zombiePos[currentZombie][1] = 0;
                        playerMoney = playerMoney + 50;

                    }
                }
            }
            
            //Check collision of down bullet
            if (bulletDownVisible[i]){

                //Move bullet down
                bulletDownY[i] = bulletDownY[i] + bulletSpeed;
                bulletDownHitBox[i] = new Rectangle(bulletDownX[i], bulletDownY[i], bulletW, bulletH);
                
                //Check collision between player wall and bullet down
                if (checkCollisionPlayerWall(bulletDownHitBox[i])) {
                    
                    wallHealth[wallHit] = wallHealth[wallHit] - 10;
                    bulletDownVisible[i] = false;

                    if (wallHealth[wallHit] <= 0){

                        playerWallOnBoard--;

                        wallBreakSound.stop();
                        wallBreakSound.flush();              
                        wallBreakSound.setFramePosition(0);  
                        wallBreakSound.start();

                        playerWallVisible[wallHit] = false;
                        updatedMap[playerWallX[wallHit]/32][playerWallY[wallHit]/32] =0 ;
                        playerWallX[wallHit] = 3000;
                        playerWallY[wallHit] = 3000;
                        playerWall[wallHit] = new Rectangle(playerWallX[wallHit],playerWallY[wallHit],wallH,wallW);
                    }
                }
                if (checkCollision(bulletDownHitBox[i])) {

                    bulletDownVisible[i] = false;

                }

                currentZombie =checkZombieHit(bulletDownHitBox[i]);

                if(currentZombie != -1){

                    zombieHitSound.stop();
                    zombieHitSound.flush();              
                    zombieHitSound.setFramePosition(0); 
                    zombieHitSound.start();

                    bulletDownVisible[i] = false;
                    zombieHP[currentZombie] = zombieHP[currentZombie] - bulletDmg;

                    //Freeze zombie
                    if(bulletDmg == 20){

                        unfreeze[currentZombie] = true;
                        freezeZombie[currentZombie] = true;
                        startFreezeTime[currentZombie] =  System.currentTimeMillis(); 

                    }
                    if(zombieHP[currentZombie] <= 0){

                        zombieBox[currentZombie].setLocation(3000, 3000);
                        zombiePos[currentZombie][0] = 0;
                        zombiePos[currentZombie][1] = 0;
                        playerMoney = playerMoney + 50;

                    }
                }

            }
                
            //Check collision of up bullet
            if (bulletUpVisible[i]){

                //Move bullet up
                bulletUpY[i] = bulletUpY[i] - bulletSpeed;
                bulletUpHitBox[i] = new Rectangle(bulletUpX[i], bulletUpY[i], bulletW, bulletH);

                //Check collision between player wall and bullet up
                if (checkCollisionPlayerWall(bulletUpHitBox[i])) {

                    wallHealth[wallHit] = wallHealth[wallHit] - 10;
                    bulletUpVisible[i] = false;

                    if (wallHealth[wallHit] <= 0){

                        playerWallOnBoard--;
                        wallBreakSound.stop();
                        wallBreakSound.flush();              
                        wallBreakSound.setFramePosition(0);  
                        wallBreakSound.start();
                        playerWallVisible[wallHit] = false;
                        updatedMap[playerWallX[wallHit]/32][playerWallY[wallHit]/32] =0 ;
                        playerWallX[wallHit] = 3000;
                        playerWallY[wallHit] = 3000;
                        playerWall[wallHit] = new Rectangle(playerWallX[wallHit],playerWallY[wallHit],wallH,wallW);
                    }
                }

                if (checkCollision(bulletUpHitBox[i])) {

                    bulletUpVisible[i] = false;

                }
                
                currentZombie =checkZombieHit(bulletUpHitBox[i]);

                if(currentZombie != -1){
                    
                    zombieHitSound.stop();
                    zombieHitSound.flush();              
                    zombieHitSound.setFramePosition(0); 
                    zombieHitSound.start();

                    bulletUpVisible[i] = false;
                    zombieHP[currentZombie] = zombieHP[currentZombie] - bulletDmg;
                    
                    //Freeze zombie
                    if(bulletDmg == 20){

                        unfreeze[currentZombie] = true;
                        freezeZombie[currentZombie] = true;
                        startFreezeTime[currentZombie] =  System.currentTimeMillis(); 

                    }

                    if(zombieHP[currentZombie] <= 0){

                        zombieBox[currentZombie].setLocation(3000, 3000);
                        zombiePos[currentZombie][0] = 0;
                        zombiePos[currentZombie][1] = 0;
                        playerMoney = playerMoney + 50;
                    }
                }

        
            }

    
        }     
    }


    /**
     * wallPlacement
     * This method checks you are able to place a wall and places it
     */

    public static void wallPlacement(){

        //Create future hitbox
        futureWallDownHitBox = new Rectangle(playerX + playerW/2, playerY + playerH + wallH-1, 2, 2);
        futureWallUpHitBox = new Rectangle(playerX + playerW/2, playerY - wallH, 2, 2);
        futureWallRightHitBox = new Rectangle(playerX + playerW + wallW-1, playerY + playerH/2, 2, 2);
        futureWallLeftHitBox = new Rectangle(playerX - wallW, playerY + playerH/2, 2, 2);        
        
        //Check if wall is able to be placed
        if ((playerWallOnBoard < 20) && (playerWallInventory !=0) && (placeWall) && ((playerPicNum == 0) || (goingDown)) && !(checkCollision(futureWallDownHitBox)) && (!(checkCollisionPlayerWall(futureWallDownHitBox)))){
            
            futureWallDownHitBox = new Rectangle(playerX + playerW/2, playerY + playerH + wallH-1, wallW, wallH);
            
            if ((checkZombieHit(futureWallDownHitBox) == -1)){

                for (int i=0; i<MAX_WALL_AMOUNT; i++){

                    //Check if wall is destroyed
                    if (playerWallX[i] == 3000){

                        playerWallInventory--;
                        playerWallOnBoard++;

                        wallSound.stop();
                        wallSound.flush();              
                        wallSound.setFramePosition(0); 
                        wallSound.start();

                        wallHealth[i] = 50;

                        //Places wall
                        playerWallX[i] = (playerX + playerW/2)/32;
                        playerWallX[i] = playerWallX[i]*32;
                        playerWallY[i] = (playerY + playerH)/32;
                        playerWallY[i] = (playerWallY[i]+1)*32;

                        playerWallVisible[i] = true;
                        playerWall[i] = new Rectangle(playerWallX[i],playerWallY[i],wallH,wallW);
                        noBlocksDestroyed = false;
                        i = MAX_WALL_AMOUNT;

                        //Update map for pathfinding
                        updatedMap[playerWallX[i]/32][playerWallY[i]/32] = -1;

                    } else {

                        noBlocksDestroyed = true;

                    }
                }
                if (noBlocksDestroyed){

                    playerWallInventory--;
                    playerWallOnBoard++;
                    
                    wallSound.stop();
                    wallSound.flush();              
                    wallSound.setFramePosition(0); 
                    wallSound.start();

                    //Places wall
                    playerWallX[currentPlayerWall%MAX_WALL_AMOUNT] = (playerX + playerW/2)/32;
                    playerWallX[currentPlayerWall%MAX_WALL_AMOUNT] = playerWallX[currentPlayerWall%MAX_WALL_AMOUNT]*32;
                    playerWallY[currentPlayerWall%MAX_WALL_AMOUNT] = (playerY + playerH)/32;
                    playerWallY[currentPlayerWall%MAX_WALL_AMOUNT] = (playerWallY[currentPlayerWall%MAX_WALL_AMOUNT]+1)*32;

                    playerWall[currentPlayerWall%MAX_WALL_AMOUNT] = new Rectangle(playerWallX[currentPlayerWall%MAX_WALL_AMOUNT],playerWallY[currentPlayerWall%MAX_WALL_AMOUNT],wallH,wallW);
                    
                    //Update map for pathfinding
                    updatedMap[ playerWallX[currentPlayerWall%MAX_WALL_AMOUNT]/32][ playerWallY[currentPlayerWall%MAX_WALL_AMOUNT]/32] =-1;

                    currentPlayerWall++;
                }
            }

        //See if wall is able to be placed
        } else if ((playerWallOnBoard < 20) && (playerWallInventory !=0) && (placeWall) && ((playerPicNum == 10) || (goingUp)) && !(checkCollision(futureWallUpHitBox)) && !(checkCollision(futureWallUpHitBox)) && !(checkCollisionPlayerWall(futureWallUpHitBox))){
            
            futureWallUpHitBox = new Rectangle(playerX + playerW/2, playerY - wallH, wallW, wallH);
            
            if ((checkZombieHit(futureWallUpHitBox) == -1)){

                for (int i=0; i<MAX_WALL_AMOUNT; i++){

                    //Check if wall is destroyed
                    if (playerWallX[i] == 3000){

                        playerWallInventory--;
                        playerWallOnBoard++;

                        wallSound.stop();
                        wallSound.flush();              
                        wallSound.setFramePosition(0); 
                        wallSound.start();

                        wallHealth[i] = 50;

                        //Place wall
                        playerWallX[i] = (playerX + playerW/2)/32;
                        playerWallX[i] = playerWallX[i]*32;
                        playerWallY[i] = playerY/32;
                        playerWallY[i] = (playerWallY[i]-1)*32;

                        updatedMap[playerWallX[i]/32][playerWallY[i]/32] =-1;

                        playerWallVisible[i] = true;
                        playerWall[i] = new Rectangle(playerWallX[i],playerWallY[i],wallH,wallW);
                        noBlocksDestroyed = false;
                        i = MAX_WALL_AMOUNT;

                    } else {

                        noBlocksDestroyed = true;

                    }
                }
                if (noBlocksDestroyed){

                    playerWallInventory--;
                    playerWallOnBoard++;

                    wallSound.stop();
                    wallSound.flush();              
                    wallSound.setFramePosition(0);  
                    wallSound.start();

                    //Place wall
                    playerWallX[currentPlayerWall%MAX_WALL_AMOUNT] = (playerX + playerW/2)/32;
                    playerWallX[currentPlayerWall%MAX_WALL_AMOUNT] = playerWallX[currentPlayerWall%MAX_WALL_AMOUNT]*32;
                    playerWallY[currentPlayerWall%MAX_WALL_AMOUNT] = playerY/32;
                    playerWallY[currentPlayerWall%MAX_WALL_AMOUNT] = (playerWallY[currentPlayerWall%MAX_WALL_AMOUNT]-1)*32;
                    
                    //Update map for pathfinding
                    updatedMap[ playerWallX[currentPlayerWall%MAX_WALL_AMOUNT]/32][ playerWallY[currentPlayerWall%MAX_WALL_AMOUNT]/32] =-1;
                    
                    playerWall[currentPlayerWall%MAX_WALL_AMOUNT] = new Rectangle(playerWallX[currentPlayerWall%MAX_WALL_AMOUNT],playerWallY[currentPlayerWall%MAX_WALL_AMOUNT],wallH,wallW);
                    currentPlayerWall++;
                }
            }

        //See if wall is able to be placed
        } else if ((playerWallOnBoard < 20) && (playerWallInventory > 0) && (placeWall) && ((playerPicNum == 6) || (goingRight)) && !(checkCollision(futureWallRightHitBox)) && !(checkCollisionPlayerWall(futureWallRightHitBox))){
            
            futureWallRightHitBox = new Rectangle(playerX + playerW + wallW-1, playerY + playerH/2, wallW, wallH); 

            if ((checkZombieHit(futureWallRightHitBox) == -1)){

                for (int i=0; i<MAX_WALL_AMOUNT; i++){

                    //Check if wall is destroyed
                    if (playerWallX[i] == 3000){

                        playerWallInventory--;
                        playerWallOnBoard++;

                        wallSound.stop();
                        wallSound.flush();             
                        wallSound.setFramePosition(0); 
                        wallSound.start();

                        wallHealth[i] = 50;

                        //Place wall
                        playerWallX[i] = (playerX + playerW)/32;
                        playerWallX[i] = (playerWallX[i]+1)*32;
                        playerWallY[i] = (playerY + playerH/2)/32;
                        playerWallY[i] = playerWallY[i]*32;
                        playerWallVisible[i] = true;

                        updatedMap[playerWallX[i]/32][playerWallY[i]/32] =-1;

                        playerWall[i] = new Rectangle(playerWallX[i],playerWallY[i],wallH,wallW);
                        noBlocksDestroyed = false;
                        i = MAX_WALL_AMOUNT;

                    } else {

                        noBlocksDestroyed = true;

                    }
                }
                if (noBlocksDestroyed){

                    playerWallInventory--;
                    playerWallOnBoard++;

                    wallSound.stop();
                    wallSound.flush();              
                    wallSound.setFramePosition(0);  
                    wallSound.start();

                    //Place wall
                    playerWallX[currentPlayerWall%MAX_WALL_AMOUNT] = (playerX + playerW)/32;
                    playerWallX[currentPlayerWall%MAX_WALL_AMOUNT] = (playerWallX[currentPlayerWall%MAX_WALL_AMOUNT]+1)*32;
                    playerWallY[currentPlayerWall%MAX_WALL_AMOUNT] = (playerY + playerH/2)/32;
                    playerWallY[currentPlayerWall%MAX_WALL_AMOUNT] = playerWallY[currentPlayerWall%MAX_WALL_AMOUNT]*32;
                    
                    //Update map for pathfinding
                    updatedMap[ playerWallX[currentPlayerWall%MAX_WALL_AMOUNT]/32][ playerWallY[currentPlayerWall%MAX_WALL_AMOUNT]/32] =-1;
                    
                    playerWall[currentPlayerWall%MAX_WALL_AMOUNT] = new Rectangle(playerWallX[currentPlayerWall%MAX_WALL_AMOUNT],playerWallY[currentPlayerWall%MAX_WALL_AMOUNT],wallH,wallW);
                    currentPlayerWall++;

                }
            }

        //Check if wall is able to be placed 
        } else if ((playerWallOnBoard < 20) && (playerWallInventory !=0) && (placeWall) && ((playerPicNum == 4) || (goingLeft)) && !(checkCollision(futureWallLeftHitBox)) && !(checkCollisionPlayerWall(futureWallLeftHitBox))){
            
            futureWallLeftHitBox = new Rectangle(playerX - wallW, playerY + playerH/2, 2, 2);        

            if ((checkZombieHit(futureWallLeftHitBox) == -1)){

                for (int i=0; i<MAX_WALL_AMOUNT; i++){

                    //Check if wall is destroyed
                    if (playerWallX[i] == 3000){

                        playerWallInventory--;
                        playerWallOnBoard++;

                        wallSound.stop();
                        wallSound.flush();             
                        wallSound.setFramePosition(0);  
                        wallSound.start();

                        wallHealth[i] = 50;

                        //Place wall
                        playerWallX[i] = playerX/32;
                        playerWallX[i] = (playerWallX[i]-1)*32;
                        playerWallY[i] = (playerY + playerH/2)/32;
                        playerWallY[i] = playerWallY[i]*32;

                        updatedMap[playerWallX[i]/32][playerWallY[i]/32] =-1;
                        
                        playerWallVisible[i] = true;
                        playerWall[i] = new Rectangle(playerWallX[i],playerWallY[i],wallH,wallW);
                        noBlocksDestroyed = false;
                        i = MAX_WALL_AMOUNT;

                    } else {

                        noBlocksDestroyed = true;

                    }
                }
                if (noBlocksDestroyed){
                    
                    playerWallInventory--;
                    playerWallOnBoard++;

                    wallSound.stop();
                    wallSound.flush();             
                    wallSound.setFramePosition(0);
                    wallSound.start();

                    //Place wall
                    playerWallX[currentPlayerWall%MAX_WALL_AMOUNT] = playerX/32;
                    playerWallX[currentPlayerWall%MAX_WALL_AMOUNT] = (playerWallX[currentPlayerWall%MAX_WALL_AMOUNT]-1)*32;
                    playerWallY[currentPlayerWall%MAX_WALL_AMOUNT] = (playerY + playerH/2)/32;
                    playerWallY[currentPlayerWall%MAX_WALL_AMOUNT] = playerWallY[currentPlayerWall%MAX_WALL_AMOUNT]*32;
                    
                    //Update map for pathfinding
                    updatedMap[ playerWallX[currentPlayerWall%MAX_WALL_AMOUNT]/32][ playerWallY[currentPlayerWall%MAX_WALL_AMOUNT]/32] =-1;
                    
                    playerWall[currentPlayerWall%MAX_WALL_AMOUNT] = new Rectangle(playerWallX[currentPlayerWall%MAX_WALL_AMOUNT],playerWallY[currentPlayerWall%MAX_WALL_AMOUNT],wallH,wallW);
                    currentPlayerWall++;
                }
            }
        }
        if (placeWall) {

            placeWall = false;
            
        }

    }

    /**
     * characterMovement
     * This method moves the character and adds animation
     */
    public static void characterMovement(){

        //Set player hp bar
        playerPercentage = 25 * (playerHP/100);
        playerBar = (int) playerPercentage;

        //Set player direction
        if (goingLeft) {

            playerVx = -RUN_SPEED;

        } 
        if (goingRight) {

            playerVx = RUN_SPEED;

        } 
        if (goingUp) {

            playerVy = -RUN_SPEED;

        } 
        if (goingDown) {

            playerVy = RUN_SPEED;

        }
        
        //Set future player hitboxes
        futureLeftPlayerHitBox.setLocation(playerX - 2, playerY);
        futureRightPlayerHitBox.setLocation(playerX + 2, playerY);
        futureUpPlayerHitBox.setLocation(playerX, playerY - 2);
        futureDownPlayerHitBox.setLocation(playerX, playerY + 2);
        
        //Make sure player does not leave boarder
        if ((checkCollision(futureLeftPlayerHitBox)) && (goingLeft)){

            playerVx = 0;
            
        }
        if ((checkCollisionPlayerWall(futureLeftPlayerHitBox)) && (goingLeft)){
            
            playerVx = 0;

        }
        if ((checkCollision(futureRightPlayerHitBox)) && (goingRight)){

            playerVx = 0;

        }
        if ((checkCollisionPlayerWall(futureRightPlayerHitBox)) && (goingRight)){

            playerVx = 0;

        }
        if ((checkCollision(futureUpPlayerHitBox)) && (goingUp)){

            playerVy = 0;

        }
        if ((checkCollisionPlayerWall(futureUpPlayerHitBox)) && (goingUp)){

            playerVy = 0;

        }

        

        if ((checkCollision(futureDownPlayerHitBox)) && (goingDown)){

            playerVy = 0;

        }
        if ((checkCollisionPlayerWall(futureDownPlayerHitBox)) && (goingDown)){

            playerVy = 0;

        }

        //Move player
        playerX = playerX + playerVx;
        playerY = playerY + playerVy;
        playerBox.setLocation(playerX,playerY);
        
        //Add animation to player  
        if (elapsedTimePlayer/80 > 1){

            if ((playerVx == 0) && (playerVy == 0)){

                if (standLeft) {

                    runSound.stop();
                    runSound.setFramePosition(0);

                    playerPicNum = 4;

                } else if (standRight) {

                    runSound.stop();
                    runSound.setFramePosition(0);

                    playerPicNum = 6;

                } else if (standUp) {

                    runSound.stop();
                    runSound.setFramePosition(0);

                    playerPicNum = 10;

                } else if (standDown) {

                    runSound.stop();
                    runSound.setFramePosition(0);

                    playerPicNum = 0;

                } 
            
            //Set player pic
            } else if (playerVx < 0){

                runSound.start();
                runSound.loop(Clip.LOOP_CONTINUOUSLY);

                playerPicNum = nextLeftPic[playerPicNum]; 
                
            } else if (playerVx > 0){     

                runSound.start();
                runSound.loop(Clip.LOOP_CONTINUOUSLY);

                playerPicNum = nextRightPic[playerPicNum]; 

            } else if (playerVy < 0){

                runSound.start();
                runSound.loop(Clip.LOOP_CONTINUOUSLY);

                playerPicNum = nextUpPic[playerPicNum];

            } else if (playerVy > 0){

                runSound.start();
                runSound.loop(Clip.LOOP_CONTINUOUSLY);

                playerPicNum = nextDownPic[playerPicNum];

            }    

            startPlayerTime = System.currentTimeMillis();  
        }   

        endPlayerTime = System.currentTimeMillis();
        elapsedTimePlayer = endPlayerTime - startPlayerTime; 
    
    }

    static class GraphicsPanel extends JPanel{

        //Set graphics panel
        public GraphicsPanel(){

            setFocusable(true);
            requestFocusInWindow();

        }

        /**
         * paintComponent
         * This method draws images and shapes
         */
        public void paintComponent(Graphics g){ 
            
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;

            //Set font and thickness
            int thickness = 4;

            g2.setColor(Color.green);
            Stroke oldStroke = g2.getStroke();
            g2.setStroke(new BasicStroke(thickness));

            Font font = new Font("Arial", Font.BOLD, 50);
            g.setFont(font);

            Font smallFont = new Font("Arial", Font.BOLD, 20);

            //Draw menu screen
            if (screen == 0){ 
                
                if ((mouseX >= 985) && (mouseX <= 1353) &&  (mouseY >= 519) && (mouseY <=565)){
                    
                    try {             
                        menuPic = ImageIO.read(new File("images/menuStart.png"));
                    } catch (IOException ex){} 

                } else if ((mouseX >= 933) && (mouseX <= 1353) &&  (mouseY >= 622) && (mouseY <= 668)){
                    
                    try {             
                        menuPic = ImageIO.read(new File("images/menuInstructions.png"));
                    } catch (IOException ex){} 

                } else {
                    
                    try {             
                        menuPic = ImageIO.read(new File("images/menu.png"));
                    } catch (IOException ex){} 

                }

                g.drawImage(menuPic,0,0,this);

            // Draw game screen
            } else if (screen == 1){

                //Draw map
                for(int i = 0;i < map.length; i++ ){

                    tileX = i*32;

                    for(int count =0 ; count < map[0].length;count++){
        
                        tileY = count*32;
        
                        g.drawImage(tileID[map[i][count]],tileX, tileY,this);
                        

                    
                        
                    
                    }

                }


                for(int i = 0; i < map.length; i++){

                    for(int count = 0; count < map[0].length; count++){
                    
                        if(map[i][count] == 4 || map[i][count] == 5 || map[i][count] == 6 || map[i][count] == 7 || map[i][count] == 8 || map[i][count] == 9){


                        }


                    }
                }

                //Draw player walls
                for (int i=0; i<MAX_WALL_AMOUNT; i++){
                    
                    if ((playerWall[i] != null) && (playerWallVisible[i])){
                        
                        g.drawImage(playerWallPic,playerWallX[i],playerWallY[i],this);
                   
                    }
                }

                //Draw bullets
                for (int i=0; i<numBullets; i++){

                    if (bulletRightVisible[i]) {

                        g.drawImage(bullet1RightPic,bulletRightX[i],bulletRightY[i],this);
                    
                    }
                    if (bulletLeftVisible[i]) {

                        g.drawImage(bullet1LeftPic,bulletLeftX[i],bulletLeftY[i],this);
                    
                    }
                    if (bulletDownVisible[i]) {

                        g.drawImage(bullet1DownPic,bulletDownX[i],bulletDownY[i],this);
                    
                    }
                    if (bulletUpVisible[i]) {

                        g.drawImage(bullet1UpPic,bulletUpX[i],bulletUpY[i],this);
                    
                    }
                }

                //Draw zombies
                try {
                    
                    for(int i = 0; i < numZombie; i++){
  
                        if(zombieHP[i] > 0){

                            if (checkCollisionPlayer(zombieBox[i])){
                                g.drawImage(zombieAttackPic[zombieAttackPicNum[i]],zombieX[i],zombieY[i],this);
                            }

                            if (!(checkCollisionPlayer(zombieBox[i]) && !freezeZombie[i])){
                                
                                g.drawImage(zombiePic[zombiePicNum[i]],zombieX[i],zombieY[i],this);
                            
                            }

                            g.setColor(Color.red);
                            g.fillRect(zombieX[i] , zombieY[i] - 10, 25,5);
                            g.setColor(Color.green);
                            g.fillRect(zombieX[i] , zombieY[i] - 10, zombieBar[i],5);

                        }
                       
                         if(freezeZombie[i] && zombieHP[i] > 0){

                            g.drawImage(frozenZombiePic[nextFrozenZombie[zombiePicNum[i]]], zombieX[i], zombieY[i], this);

                        }
                
                    }

                } catch (Exception ex){} 

                //Draw player
                g.drawImage(playerPic[playerPicNum],playerX,playerY,this);

                g.setColor(Color.red);
                g.fillRect(playerX,playerY - 10, 25,5);
                g.setColor(Color.green);
                g.fillRect(playerX,playerY - 10, playerBar,5);
              
                //Display player statistics and abilities
                g.setFont(smallFont);
                String displayPlayerHealth = Double.toString(playerHP);
                g.setColor(Color.red);
                g.drawString(": " + displayPlayerHealth, 1100, 30);

                String displayAmmo = Integer.toString(ammo);
                g.drawString(": " + displayAmmo, 1100, 70);

                String displaySpeedAmmo = Integer.toString(speedAmmo);
                g.drawString(": " + displaySpeedAmmo, 1100, 110);

                String displayWallCount = Integer.toString(playerWallInventory);
                g.drawString(": " + displayWallCount, 1100, 150);

                String displayWallOnBoard = Integer.toString(playerWallOnBoard);
                g.drawString("Walls On Board (Max 20): " + displayWallOnBoard, 900, 600);

                g.setFont(font);

                //Draw wave numebr
                g.setColor(Color.WHITE);
                g.drawString("Wave: " + Integer.toString(waveCount),10,40);

                //Draw shop
                g.drawImage(heartIcon,1065,10,this);
                g.drawImage(bulletIcon,1055,52,this);
                g.drawImage(rapidIcon,1060,90,this);
                g.drawImage(wallIcon,1060,130,this);

                g.drawImage(shopPic,1170,0,this);

                String displayMoney = Integer.toString(playerMoney);
                g.setColor(Color.GREEN);
                g.drawString(displayMoney, 1235, 55);
                
                if ((mouseX >= 1175) && (mouseX <= 1260) &&  (mouseY >= 80) && (mouseY <=163)){

                    g2.setColor(Color.green);
                    g2.drawRect(1176, 80, 86, 85);
                    
                }  else if ((mouseX >= 1277) && (mouseX <= 1362) &&  (mouseY >= 80) && (mouseY <=163)){
                    
                    g2.setColor(Color.green);
                    g2.drawRect(1277, 80, 86, 85);

                } else if ((mouseX >= 1175) && (mouseX <= 1260) &&  (mouseY >= 215) && (mouseY <=299)){
                   
                    g2.setColor(Color.green);
                    g2.drawRect(1176, 215, 86, 85);

                } else if ((mouseX >= 1277) && (mouseX <= 1362) &&  (mouseY >= 215) && (mouseY <=299)){
                    
                    g2.setColor(Color.green);
                    g2.drawRect(1277, 215, 86, 85);

                } else if ((mouseX >= 1175) && (mouseX <= 1260) &&  (mouseY >= 357) && (mouseY <=441)){
                    
                    g2.setColor(Color.green);
                    g2.drawRect(1176, 357, 86, 85);

                } else if ((mouseX >= 1277) && (mouseX <= 1362) &&  (mouseY >= 357) && (mouseY <=441)){
                    
                    g2.setColor(Color.green);
                    g2.drawRect(1277, 357, 86, 85);

                } else if ((mouseX >= 1175) && (mouseX <= 1260) &&  (mouseY >= 499) && (mouseY <=582)){
                    
                    g2.setColor(Color.green);
                    g2.drawRect(1176, 499, 86, 85);

                } else if ((mouseX >= 1277) && (mouseX <= 1362) &&  (mouseY >= 499) && (mouseY <=582)){
                    
                    g2.setColor(Color.green);
                    g2.drawRect(1277, 499, 86, 85);

                }

            //Draw instruction screen
            } else if (screen == 2){

                if ((mouseX >= 31) && (mouseX <= 163) &&  (mouseY >= 40) && (mouseY <=77)){
                    
                    try {             
                        instructionPic = ImageIO.read(new File("images/instructionBack.png"));
                    } catch (IOException ex){} 

                } else if ((mouseX >= 1056) && (mouseX <= 1353) &&  (mouseY >= 637) && (mouseY <=675)){
                    
                    try {             
                        instructionPic = ImageIO.read(new File("images/instructionStart.png"));
                    } catch (IOException ex){} 
                    
                } else {
                    
                    try {             
                        instructionPic = ImageIO.read(new File("images/instruction.png"));
                    } catch (IOException ex){} 

                }

                g.drawImage(instructionPic,0,0,this);

            //Draw end screen
            } else if (screen == 3){

                if ((mouseX >= 431) && (mouseX <= 938) &&  (mouseY >= 587) && (mouseY <=655)){
                    
                    try {             
                        endPic = ImageIO.read(new File("images/endScreenAgain.png"));
                    } catch (IOException ex){} 

                } else {

                    try {             
                        endPic = ImageIO.read(new File("images/endScreen.png"));
                    } catch (IOException ex){} 

                }

                g.drawImage(endPic,0,0,this);
            }

        }

    }

    static class MyKeyListener implements KeyListener{  

        public void keyPressed(KeyEvent e){

            int key = e.getKeyCode();
            
            if (screen == 1){

                //Movement keys
                if (key == KeyEvent.VK_A){

                    goingLeft = true;
                    goingRight = false;

                } else if (key == KeyEvent.VK_D){

                    goingRight = true;
                    goingLeft = false;

                }
                if (key == KeyEvent.VK_W){ 

                    goingUp = true;   
                    goingDown = false;

                } else if (key == KeyEvent.VK_S){

                    goingDown = true;
                    goingUp = false;

                } 
                
            }
        }
        public void keyReleased(KeyEvent e){

            int key = e.getKeyCode();

            if (screen == 1){

                //Movement keys
                if (key == KeyEvent.VK_A) {

                    playerVx = 0;
                    goingLeft = false;
                    standLeft = true;
                    standRight = false;
                    standUp = false;
                    standDown = false;

                    if (goingRight){

                        playerVx = RUN_SPEED;

                    }
                }
                if (key == KeyEvent.VK_D) {

                    playerVx = 0;
                    goingRight = false;
                    standRight = true;
                    standLeft = false;
                    standUp = false;
                    standDown = false;

                    if (goingLeft){

                        playerVx = -RUN_SPEED;

                    }
                }
                if (key == KeyEvent.VK_W) {

                    playerVy = 0;
                    goingUp = false;
                    standUp = true;
                    standRight = false;
                    standLeft = false;
                    standDown = false;

                    if (goingDown){

                        playerVy = RUN_SPEED;

                    } 
                }
                if (key == KeyEvent.VK_S) {

                    playerVy = 0;
                    goingDown = false;
                    standDown = true;
                    standRight = false;
                    standUp = false;
                    standLeft = false;

                    if (goingUp){

                        playerVy = -RUN_SPEED;

                    } 
                }

                //Place wall key
                if (key == KeyEvent.VK_F){

                    placeWall = true;

                    for(int i =0; i <numZombie; i++){

                        zombiePath[i] = pathfind(updatedMap,zombiePos[i],playerPos);
                    
                    }
                }

            }

        }       
        public void keyTyped(KeyEvent e){
        }        
    }

    static class MyMouseListener implements MouseListener{

        public void mouseClicked(MouseEvent e){
        }

        public void mousePressed(MouseEvent e){   // changes box color and places the box at the mouse location
           
            if (screen == 1){

                //To shoot
                if (mouseX < 1170){

                    if (!(canShoot)) {

                        shooting = true;
                        canShoot = true;

                    }
                    if (autoFire){

                        shooting = true;

                    }
                }
            }
            
        }
        public void mouseReleased(MouseEvent e){  // restores box color
            
            if (screen == 0){

                //To start game
                if ((mouseX >= 985) && (mouseX <= 1353) &&  (mouseY >= 519) && (mouseY <=565)){
                    
                    screen = 1;

                //To go to instruction screen
                } else if ((mouseX >= 933) && (mouseX <= 1353) &&  (mouseY >= 621) && (mouseY <=669)){
                   
                    screen = 2;

                }
            } else if (screen == 1){

                //To stop shooting
                if (mouseX < 1170){
                    
                    canShoot = false;

                    if ((autoFire) && (hasShot)){

                        shooting = false;
                        hasShot = false;

                    }
                }

                //To buy items
                if ((playerMoney >= 500) && (mouseX >= 1175) && (mouseX <= 1260) &&  (mouseY >= 80) && (mouseY <=163)){
                    
                    playerMoney = playerMoney - 500;
                    
                    autoFire = true;

                    buySound.stop();
                    buySound.flush();              
                    buySound.setFramePosition(0);  
                    buySound.start();
                    
                } else if ((playerMoney >= 50) && (mouseX >= 1277) && (mouseX <= 1362) &&  (mouseY >= 80) && (mouseY <=163)){

                    playerMoney = playerMoney - 50;
                    ammo = 25;

                    useFireBullet = true;

                    buySound.stop();
                    buySound.flush();              
                    buySound.setFramePosition(0);  
                    buySound.start();

                    useFreezeBullet = false;
                    useHolyWater = false;
                    useLavaBullet = false;

                } else if ((playerMoney >= 80) && (mouseX >= 1175) && (mouseX <= 1260) &&  (mouseY >= 215) && (mouseY <=299)){

                    playerMoney = playerMoney - 80;
                    ammo = 25;

                    useLavaBullet = true;

                    buySound.stop();
                    buySound.flush();              
                    buySound.setFramePosition(0);  
                    buySound.start();

                    useFireBullet = false;
                    useHolyWater = false;
                    useFreezeBullet = false;

                } else if ((playerMoney >= 150) && (mouseX >= 1277) && (mouseX <= 1362) &&  (mouseY >= 215) && (mouseY <=299)){

                    playerMoney = playerMoney - 150;
                    ammo = 25;

                    useHolyWater = true;

                    buySound.stop();
                    buySound.flush();              
                    buySound.setFramePosition(0);  
                    buySound.start();

                    useFireBullet = false;
                    useFreezeBullet = false;
                    useLavaBullet = false;

                } else if ((playerMoney >= 150) && (mouseX >= 1175) && (mouseX <= 1260) &&  (mouseY >= 357) && (mouseY <=441)){

                    playerMoney = playerMoney - 150;
                    ammo = 5;

                    useFreezeBullet = true;

                    buySound.stop();
                    buySound.flush();              
                    buySound.setFramePosition(0);  
                    buySound.start();

                    useFireBullet = false;
                    useHolyWater = false;
                    useLavaBullet = false;
                    
                } else if ((playerMoney >= 100) && (mouseX >= 1277) && (mouseX <= 1362) &&  (mouseY >= 357) && (mouseY <=441)){

                    playerMoney = playerMoney - 100;

                    shootingSpeed = 80;
                    speedAmmo = 100;

                    buySound.stop();
                    buySound.flush();              
                    buySound.setFramePosition(0);  
                    buySound.start();

                } else if ((playerMoney >= 100) && (playerWallOnBoard < MAX_WALL_AMOUNT) && (playerWallInventory < MAX_WALL_AMOUNT) && (mouseX >= 1175) && (mouseX <= 1260) &&  (mouseY >= 499) && (mouseY <=582)){
                    
                    playerMoney = playerMoney - 100;

                    if (playerWallInventory >= MAX_WALL_AMOUNT-5){

                        playerWallInventory = MAX_WALL_AMOUNT;

                    } else if (playerWallOnBoard >= MAX_WALL_AMOUNT-5) {

                        playerWallInventory = playerWallInventory + (MAX_WALL_AMOUNT - playerWallOnBoard);

                    } else {

                        playerWallInventory = playerWallInventory + 5;

                    }

                    buySound.stop();
                    buySound.flush();              
                    buySound.setFramePosition(0);  
                    buySound.start();

                } else if ((playerMoney >= 1000) && (mouseX >= 1277) && (mouseX <= 1362) &&  (mouseY >= 499) && (mouseY <=582)){

                    playerMoney = playerMoney - 1000;
                    playerHP = playerHP + 100;

                    buySound.stop();
                    buySound.flush();              
                    buySound.setFramePosition(0);  
                    buySound.start();

                }
            
            } else if (screen == 2){

                //To go back to menu scren
                if ((mouseX >= 31) && (mouseX <= 163) &&  (mouseY >= 40) && (mouseY <=77)){

                    screen = 0;

                //To start game
                } else if ((mouseX >= 1056) && (mouseX <= 1353) &&  (mouseY >= 637) && (mouseY <=675)){

                    screen = 1;

                }

            } else if (screen == 3){

                //To restart the game
                if ((mouseX >= 431) && (mouseX <= 938) &&  (mouseY >= 587) && (mouseY <=655)){

                    screen = 1;
                    restartGame();

                }
            }
        }
        public void mouseEntered(MouseEvent e){
        }
        public void mouseExited(MouseEvent e){
        }
    } // MyMouseListener class end

    static class MyMouseMotionListener implements MouseMotionListener{

        public void mouseMoved(MouseEvent e){

            //Get the mouse position
            mouseX = e.getX();
            mouseY = e.getY();

        }

        public void mouseDragged(MouseEvent e){   // drags the box with the mouse       
        }         
    }

    public static int amountInArray(int[] array, int value){
        
        int count = 0;

        for(int i = 0; i < array.length; i++){
            
            if(array[i] == value){

                count++;
            }

        }

        return count;

    }

    /**
     * checkCollision
     * This method checks if a rectangular object intersects with a boarder wall
     * @param collisionObject is the object to check for intersection with the boarder wall
     * @return true if there is intersection between boarder wall and object and false if there is not
     */

    public static boolean checkCollision(Rectangle collisionObject){

        for (int i = 0; i < wallBox.length; i++){

            if (wallBox[i].intersects(collisionObject)){

                return true;

            }

        }

        return false;
    
    }

    /**
     * checkCollisionPlayer
     * This method checks if a rectangular object intersects with the player
     * @param collisionObject is the object to check for intersection with the player
     * @return true if there is intersection between player and object and false if there is not
     */

    public static boolean checkCollisionPlayer(Rectangle collisionObject){

        if (playerBox.intersects(collisionObject)){

            return true;
            
        }

        return false;
    
    }

    /**
     * checkCollisionPlayerWall
     * This method checks if a rectangular object intersects with a player wall
     * @param collisionObject is the object to check for intersection with the player wall
     * @return true if there is intersection between player wall and object and false if there is not
     */

    public static boolean checkCollisionPlayerWall(Rectangle collisionObject){

        for (int i = 0; i < MAX_WALL_AMOUNT; i++){

            if (playerWall[i] != null){

                if (playerWall[i].intersects(collisionObject)){

                    wallHit = i;

                    return true;

                }
            }
        } 

        return false;
    
    }
     

/**
* getPlayerX 
* This method returns the x of player position relative to the tile
* @return the x tile which the player is on
*/
   
    public static int getPlayerX(){

        return (playerX + 16) / 32;

    }

/**
* getPlayerX 
* This method returns the Y of player position relative to the tile
* @return the Y tile which the player is on
*/
    public static int getPlayerY(){

        return (playerY + 16)/ 32;

    }


    
/**
* findSpawnableArea 
* This method generates a random valid spawn location for the zombies
* @return a random spawnable square on the map
*/
    public static int[] findSpawnableArea(){

        int[] spawn = new int[2];
        Random rand = new Random();
        int x,y;
        
        boolean validSpawn = false;

        while(!validSpawn){

            x = rand.nextInt(map[0].length);
            y = rand.nextInt(map.length);

            if(updatedMap[y][x] == 0){

                spawn[0] = y*32;
                spawn[1] = x*32;
                validSpawn = true;
            }

        } 

        return spawn;

    }   

    /**
     * checkZombieHit
     * This method checks which zombie was hit by a object
     * @param object is the object to check for intersection with a zombie
     * @return the index of zombie that was detected of collision
     */

    public static int checkZombieHit(Rectangle object){

        for(int i = 0; i < zombieBox.length; i++){

            if(zombieBox[i].intersects(object)){

                return i;

            }

        }

        return -1;
    }

/**
* moveDownZombie 
* This method carries out the zombie down animation for any given zombie
* @param num the zombie to do the animation on
*/
  public static void moveDownZombie(int num){

    if (elapsedTimeZombie[num]/80 > 1){

        zombiePicNum[num] = downZombiePic[zombiePicNum[num]];

        startZombie[num] = System.currentTimeMillis();

        if (!(checkCollisionPlayer(zombieBox[num]))){

            zombieAttackPicNum[num] = 7;

        }
    }

    endZombie[num] = System.currentTimeMillis();
    elapsedTimeZombie[num] = endZombie[num] - startZombie[num];

  }

/**
* moveUpZombie 
* This method carries out the zombie up animation for any given zombie
* @param num the zombie to do the animation on
*/
  public static void moveUpZombie(int num){

    if (elapsedTimeZombie[num]/80 > 1){

        zombiePicNum[num] = upZombiePic[zombiePicNum[num]];

        startZombie[num] = System.currentTimeMillis();

        if (!(checkCollisionPlayer(zombieBox[num]))){

            zombieAttackPicNum[num] = 9;

        }
    }

    endZombie[num] = System.currentTimeMillis();
    elapsedTimeZombie[num] = endZombie[num] - startZombie[num];

  }

/**
* moveLeftZombie 
* This method carries out the zombie down animation for any given zombie
* @param num the zombie to do the animation on
*/
  public static void moveLeftZombie(int num){

    if (elapsedTimeZombie[num]/80 > 1){

        zombiePicNum[num] = leftZombiePic[zombiePicNum[num]];

        startZombie[num] = System.currentTimeMillis();

        if (!(checkCollisionPlayer(zombieBox[num]))){

            zombieAttackPicNum[num] = 0;

        }
    }

    endZombie[num] = System.currentTimeMillis();
    elapsedTimeZombie[num] = endZombie[num] - startZombie[num];

  }

  /**
* moveRightZombie 
* This method carries out the zombie right animation for any given zombie
* @param num the zombie to do the animation on
*/

  public static void moveRightZombie(int num){
      
    if (elapsedTimeZombie[num]/80 > 1){

        zombiePicNum[num] = rightZombiePic[zombiePicNum[num]];
        startZombie[num] = System.currentTimeMillis();

        if (!(checkCollisionPlayer(zombieBox[num]))){

            zombieAttackPicNum[num] = 3;

        }

    }

    endZombie[num] = System.currentTimeMillis();
    elapsedTimeZombie[num] = endZombie[num] - startZombie[num];
 }

/**
* checkZombiePos 
* This method carries out the zombie down animation for any given zombie
* @param posX the x position of the zombie
* @param posY the y position of the zombie
* @return true if there is a zombie in that pos; false if no zombie is in that pos

*/

    public static boolean checkZombiePos(int posX, int posY){
        
        int count = 0;

        for(int i = 0; i < zombiePos.length;i++){

            if(zombiePos[i][0] == posX && zombiePos[i][1] == posY){
                
                count++;
            }
        }

        if( count > 0){

            return true;

        }else{
            return false;
        }

       

    }  


/**
* pathfind 
* This method  finds a path from point A to point B using the A* pathfinding algorithm 
* @param chart a 2d array of the map with all immovable squares listed as -1
* @param startPos a arary of the start position that the path start from
* @param endingPos a arrray of the ending position that the algorithm is supposed to find
* @return a 2d array of all the moves neccesarry to reach the ending
*/
  public static int[][] pathfind(int[][] chart, int[]startPos, int[] endingPos){

        int[][] path;
        int mapSize = chart.length * chart[0].length;
        int nextMove = 0;
    
        int currentNum;
        int lowestFScore;
        int j = 0;
        boolean found = false;
    
        int[][] openList = new int[mapSize][3];
        int[][] closedList = new int[mapSize][3];
        int[][] listOfParents = new int[mapSize * 5][4];
        int[][] tempParentList = new int[mapSize * 5][4];
        
        int[] currentNode = new int[2];

        for(int i = 0; i < openList.length; i++){

            for(int count = 0; count < openList[0].length; count++){

                openList[i][count] = -1;
                closedList[i][count] = -1;

            }
       
        }

        for(int i = 0; i < listOfParents.length; i++){

            for(int count = 0; count < listOfParents[0].length; count++){

                listOfParents[i][count] = -1;
            }
         
        }

        listOfParents[0][0] = startPos[0];
        listOfParents[0][1] = startPos[1];
        listOfParents[0][2] = -1;
        listOfParents[0][3] = -1;

        openList[0][0] = startPos[0];
        openList[0][1] = startPos[1];
        openList[0][2] = hCost(startPos, endingPos);


        //Main algorithim begins
        do{

            //Set the node first square in the list as the current node and the one with the lowest fscore
            lowestFScore = openList[0][2];
            currentNode[0] = openList[0][0];
            currentNode[1] = openList[0][1];



            //Loop through the open list and find the node with the lowest Fscore
            for(int i = 0; i < openList.length;i++){
               
                if(openList[i][2] < lowestFScore && openList[i][2] != - 1){
                     
                    currentNode[0] = openList[i][0];
                    currentNode[1] = openList[i][1];
                    lowestFScore = openList[i][2];              

                }
            }

            //Add the current node to the closed list
            closedList[j][0] = currentNode[0];
            closedList[j][1] = currentNode[1];
            closedList[j][2] = lowestFScore;
            j++;
               
            //Remove the current node from the open list 
            openList = remove(openList, currentNode);
    
            //If the the ending position is the current node it means the path has been found and break the loop
            if(currentNode[0] == endingPos[0] && currentNode[1] == endingPos[1]){

                found = true;
                break;
            }

            //Add all the adj squares of the current node   
            int[][] adjSquares =  new int[4][2];

            adjSquares[0][0] = currentNode[0] + 1;
            adjSquares[0][1] = currentNode[1];

            adjSquares[1][0] = currentNode[0]; 
            adjSquares[1][1] = currentNode[1] + 1; 

            adjSquares[2][0] = currentNode[0] -1; 
            adjSquares[2][1] = currentNode[1]; 

            adjSquares[3][0] = currentNode[0]; 
            adjSquares[3][1] = currentNode[1] -1 ; 


            //Loop through all the valid adj squares and adds them to the parent list and open list 
            for(int i = 0; i < adjSquares.length;i++){

                
                //checks for valid square
                if(adjSquares[i][0] < 0 || adjSquares[i][1] < 0 || adjSquares[i][0] > chart.length  -1|| adjSquares[i][1] > chart[0].length -1 || containsSquare(closedList, adjSquares[i]) || adjSquares[i][0] == -2){

                    continue;
                }

                //check for valid square
                if(adjSquares[i][0] >= 0 && adjSquares[i][0] < chart.length  && adjSquares[i][1] < chart[0].length   && adjSquares[i][1] >= 0){

                    if(chart[adjSquares[i][0]][adjSquares[i][1]] == -1 ){

                        continue;

                    }
                }

                //Checks if a square is in the open list and if it is checks if there is a more efficent path
                if(containsSquare(openList, adjSquares[i])){

                    tempParentList = listOfParents;

                    tempParentList[getLastNum(tempParentList,"parents")][0] = adjSquares[i][0];
                    tempParentList[getLastNum(tempParentList,"parents")][1] = adjSquares[i][1];
                    tempParentList[getLastNum(tempParentList,"parents")][2] = currentNode[0];
                    tempParentList[getLastNum(tempParentList,"parents")][3] =  currentNode[1];

                    if(openList[getNum(openList,adjSquares[i])][2] > fCost(startPos,tempParentList, adjSquares[i], endingPos)){

                        listOfParents = tempParentList;
                        openList[getLastNum(openList, "")][0] = adjSquares[i][0];
                        openList[getLastNum(openList, "")][1] = adjSquares[i][1];
                        openList[getLastNum(openList, "")][2] = fCost(startPos,listOfParents, adjSquares[i], endingPos);

                    }

                //Adds the square to the parent list and the open list
                } else {

                    currentNum  = getLastNum(listOfParents, "parents");
            
                    listOfParents[currentNum][0] = adjSquares[i][0];
                    listOfParents[currentNum][1] = adjSquares[i][1];
                    listOfParents[currentNum][2] = currentNode[0];
                    listOfParents[currentNum][3] =  currentNode[1];

                    currentNum  = getLastNum(openList, "");
                    openList[currentNum][0] = adjSquares[i][0];
                    openList[currentNum][1] = adjSquares[i][1];
                    openList[currentNum][2] = fCost(startPos,listOfParents, adjSquares[i], endingPos);
    
                }
            }

        
        } while(openList[0][0] != -1 && openList[0][1] != -1 && openList[0][2] != -1); // Loops as long as the open list is not empty

            //If the path was found return the path
            if(found){

                path = tracePath(listOfParents, startPos, endingPos);
                return path;

            //If no path was found return the path array as empty
            }else{

                path = new int[1][2];
                path[0][0] = -2;
                path[0][1] = -2;
                return path;

            }
    }
  
/**
* getNextMove 
* This method searches the path array and figures out the next move
* @param startPos the position that the you are trying to find the next move of
* @param path an array of the already calculated path to the end point
* @return a string of the next move
*/
    public static String getNextMove(int[][] path, int[]startPos){

        int nextMove = 0;

        if(path[0][0] == -2 && path[0][1] == -2 ){

            return "noPath";
        }

        for(int i = 0; i < path.length;i++){
            

            if(path[i][0] == startPos[0] && path[i][1] == startPos[1]){

                nextMove = i-1;

                break;
            }
        }

        if(nextMove == -1){

                return "arrived";
    
        }
    
        if(path[nextMove][0]  == startPos[0] -1 && path[nextMove][1] == startPos[1]){

            return "left";

        } else if(path[nextMove][0]  == startPos[0] + 1 && path[nextMove][1] == startPos[1]){

            return "right";

        } else if(path[nextMove][1] == startPos[1] + 1 && path[nextMove][0] == startPos[0]){

            return "down";

        } else if(path[nextMove][1]  == startPos[1] -1 && path[nextMove][0] == startPos[0]){

            return "up";

        } else if(path[nextMove][1]  == startPos[1]- 1 && path[nextMove][0] == startPos[0] + 1){

            return "NEdiag";

        } else if(path[nextMove][1]  == startPos[1]- 1 && path[nextMove][0]  == startPos[0]-1){

            return "NWdiag";

        } else if(path[nextMove][1]  == startPos[1] + 1 && path[nextMove][0]  == startPos[0]+ 1){

            return "SEdiag";

        } else if(path[nextMove][1] == startPos[1] + 1 && path[nextMove][0]  == startPos[0]- 1){

            return "SWdiag";

        } else{
            
            return "error";
            
        }

    }
    

/**
* fCost  
* This method calculates the fCost of a square
* @param startPos
* @param listOfParents an array of the already calculated path to the end point\
* @param currentPos
* @param endPos
* @return the fcost of the square
*/
    public static int fCost(int[] startPos, int[][] listOfParents, int[]currentPos, int[]endPos){
       
       int counter = gCost(startPos, listOfParents, currentPos) + hCost(currentPos,endPos);
       return counter;
        
    }

/**
* fCost  
* This method calculates the gCost of a square
* @param startPos the starting position
* @param listOfParents an array of all the squares that have been searched as well as their parent squares
* @param currentPos the current square that is being searched
* @return the gcost of the square
*/
    public static int gCost(int[] startPos, int[][]listOfParents, int[] currentPos){

        int counter = 0;
        int[] location = new int[2];

        location[0] = currentPos[0];
        location[1] = currentPos[1];

        while(location[0] != -1 && location[1] != -1){

            for(int i = 0; i <
             listOfParents.length;i++){
                
                if(listOfParents[i][0] == location[0] && listOfParents[i][1] == location[1]){

                    location[0] = listOfParents[i][2];
                    location[1] = listOfParents[i][3];

                }
            }
            
            counter++;

        }        
        return counter -1;

    }


/**
* hCost  
* This method calculates the hCost of a square
* @param 
* @param currentPos the current square
* @param endPos the ending position
* @return the hCost of square
*/
    public static int hCost(int[]currentPos, int[] endPos){

        int count = 0;
   
        count = count  + Math.abs( currentPos[0]  - endPos[0]);
        count = count + Math.abs(currentPos[1] - endPos[1]);
        
        return count;
    }


/**
* getLastNum  
* This method gets the last number of a filled array
* @param array the array to get the last num
* @param type the type of array that is needed to get the last num of
* @return the index of the last number of a filled array
*/
    public static int getLastNum(int[][] array, String type){

        if(type == "parents"){

            for(int i = 1; i < array.length; i++){

                    if(array[i][0] == -1 || array[i][1] == -1 || array[i][2] == -1 || array[i][3] == -1){
           
                        return i; 
                    
                    }
            }

        } else {
     
            for(int i = 0; i < array.length; i++){

                for(int count = 0; count < array[0].length; count++){

                    if(array[i][count] == -1){

                        return i;

                    }
                }
            }
        }

        return -1;
    }

    

/**
* remove  
* This method removes an item from an array and shifts all the items
* @param itemToRemove the value that is being removed from an array
* @param array the array that has the value that needs to be removed
* @return the array with the value removed
*/
    public static int[][] remove(int[][] array, int[] itemToRemove){

        for(int i = 0; i < array.length; i++){

            if(array[i][0] == itemToRemove[0] && array[i][1] == itemToRemove[1]){

                for(int count = i; count < array.length; count++){

                    if(count  == array.length -1){

                        array[count][0] = -1;
                        array[count][1] = -1;

                        return array;

                    }

                    if(array[count + 1][0] == -1){

                        array[count][0] = -1;
                        array[count][1] = -1;
                        array[count][2] = -1;               

                        return array;
                    }

                    array[count][0] = array[count + 1][0];
                    array[count][1] = array[count+ 1][1];
                    array[count][2] = array[count+ 1][2];

                }
            }
        }

        return array;
    }


/**
* containsSquare  
* This method checks if an array has a value
* @param list the array to check
* @param itemToCheck the value that is being check for
* @return true if the value is there; return false if the value is not
*/
    public static boolean containsSquare(int[][] list, int[] itemToCheck){

        int[][] array = list;

        for(int i = 0; i < array.length;i++){
            
            if(itemToCheck[0] == array[i][0] && itemToCheck[1] == array[i][1]){

                return true;
             
            }

        }

        return false;

    }


/**
* getNum  
* This method calculates the fCost of a square
* @param itemToGetIndexOf the item you want to find in an aray
* @param list the array that has the item
* @return the index of the item being searched for
*/
    public static int getNum(int[][] list , int[] itemToGetIndexOf){

        int[][] array = list;
        
        for(int i =0; i < array.length;i++){

            if(array[i][0] == itemToGetIndexOf[0] && array[i][1] == itemToGetIndexOf[1] ){

                return i;

            }
        }

        return -1;

    }


/**
* tracePath  
* This method traces a list of parents and returns the path
* @param startPos the starting postion
* @param listOfParents  an array of all the squares that have been searched as well as their parent squares
* @param endPos the ending position
* @return the path from the start position to the end position
*/
    public static int[][] tracePath(int[][] parentList, int startPos[], int endPos[]){

        int[][] path  = new int[parentList.length][2];
        int[] currentLocation = new int[2];
        int startTrace =0;
        int count = 0;

        currentLocation[0] = endPos[0];
        currentLocation[1] = endPos[1];

        while(currentLocation[0] != -1 && currentLocation[1] != -1){

            for(int i = 0; i < parentList.length;i++){
                
                if(parentList[i][0] == currentLocation[0] && parentList[i][1] == currentLocation[1]){

                    path[count][0]= currentLocation[0];
                    path[count][1] = currentLocation[1];

                    currentLocation[0] = parentList[i][2];
                    currentLocation[1] = parentList[i][3];

                    count++;


                }


            }


        }

        return path;

    }

}