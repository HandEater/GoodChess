package Project;

import java.util.Arrays;
import java.util.Scanner;

/**
1. 初始化棋盘
2. 画棋盘
3. 选手落子（？
    1. 黑棋先下
    2. 白棋后下
    3. 棋子交替出现
4. 判断子的位置（必须空位置+在棋盘内）
5. 判断输赢
6. 退出游戏
 */



public class FiveChess {
    static final String Empty = "+" ;
   static final int Size = 16;
   static final int win = 5;
    static String[][] world = new String[Size][Size];

    static void CreatWorld(){
        for(int i = 0 ; i < world.length ; i++){
            for(int j = 0 ; j < world[i].length ; j++){
                world[i][j] = Empty;
            }
        }DrawWorld();
    }
    static void DrawWorld(){
        for(int i = 0; i < world.length; i++){
            System.out.print("\t"+i);
        }
        System.out.println();
        for(int i = 0; i < world.length; i++){
            for(int j = 0 ; j< world[i].length ; j++){
                if (j==0){
                System.out.print(i+"\t");
                }
                System.out.print(world[i][j]+"\t");
            }
            System.out.println();
        }
    }
    static int Put1(Scanner s){
        try {
            System.out.println("请下黑子：");
            String str = s.next();
            String[] strs = str.split(",");
            int x = Integer.parseInt(strs[0]);
            int y = Integer.parseInt(strs[1]);
            if(x >= Size || x < 0 || y < 0 || y >= Size){
                System.out.println("参数错误");
                return -2;
            } else{
                if(Empty.equals(world[x][y])){
                    world[x][y] = "X";
                    DrawWorld();
                    boolean W = isWin(x,y,"X");
                    if(W) {
                        System.out.println("黑棋win！！！");
                        return 1;
                    }
                    return 0;
                }else{
                    System.out.println("这里已经满员辣");
                    return -1;
                }
            }


        } catch (Exception e){
            //e.printStackTrace();
            return -1;
        }
    }

    static int Put2(Scanner s){
        try {
        System.out.println("请下白子：");
        String str = s.next();
        String[] strs = str.split(",");
        int x = Integer.parseInt(strs[0]);
        int y = Integer.parseInt(strs[1]);
            if(x >= Size || x < 0 || y < 0 || y >= Size){
                System.out.println("参数错误");
                return -2;
            } else{
                if(Empty.equals(world[x][y])){
                    world[x][y] = "O";
                    DrawWorld();
                    boolean W = isWin(x,y,"O");
                    if(W){
                        System.out.println("白棋win！！！");
                        return 1;
                    }
                    return 0;
                }else{
                    System.out.println("这里已经满员辣");
                    return -1;
                }
                }


        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    private static boolean isWin(int x, int y, String XO){
        return UpSlope(x,y,XO)||DownSlope(x, y, XO)||Horizontal(x,y,XO)||Vertical(x,y,XO);
    }
    static boolean UpSlope(int x, int y, String XO){
        int count = -1;
        for (int i = 0; x-i>=0 && y+i<Size ; i++){
            if(XO.equals(world[x-i][y+i])){
                count++;
            }else{
                break;
            }
    }
        for(int i = 0; x+i<Size && y-i>=0; i++){
            if(XO.equals(world[x+i][y-i])){
                count++;
            }else{
                break;
            }
        }
        return count>=win?true:false;
    }
    static boolean DownSlope(int x, int y, String XO){
        int count = -1;
        for (int i = 0; x+i>=Size && y+i<Size ; i++){
            if(XO.equals(world[x+i][y+i])){
                count++;
            }else{
                break;
            }
        }
        for(int i = 0; x-i<0 && y-i>=0; i++){
            if(XO.equals(world[x-i][y-i])){
                count++;
            }else{
                break;
            }
        }
        return count>=win?true:false;
    }
    static boolean Horizontal(int x, int y, String XO){
        int count = -1;
        for (int i = 0; x-i>=0 && y<Size ; i++){
            if(XO.equals(world[x-i][y])){
                count++;
            }else{
                break;
            }
        }
        for(int i = 0; x+i<Size && y>=0; i++){
            if(XO.equals(world[x+i][y])){
                count++;
            }else{
                break;
            }
        }
        return count>=win?true:false;
    }

    static boolean Vertical(int x, int y, String XO){
        int count = -1;
        for (int i = 0; x>=0 && y+i<Size ; i++){
            if(XO.equals(world[x][y+i])){
                count++;
            }else{
                break;
            }
        }
        for(int i = 0; x<Size && y-i>=0; i++){
            if(XO.equals(world[x][y-i])){
                count++;
            }else{
                break;
            }
        }
        return count>=win?true:false;
    }


    public static void main(String[] args){
        CreatWorld();
        Scanner s = new Scanner(System.in);
        int round = 0;
        int num = 0;
        while(true){
            if (round % 2 == 0){
                num = Put1(s);
            }else{
                num = Put2(s);
            }


            switch(num){
                case 1:
                    System.out.println("游戏结束");
                    return;
                case 0:
                    round = round + 1;
                    break;
                default:
                   // System.out.println("错误，请重来");
                    break;
            }
        }
    }
}
