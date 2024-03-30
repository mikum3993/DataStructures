package org.atguigu.Sparsearray;

/**
 * 稀疏数组的代码实现
 */
public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二位数组   11* 11
        //0:表示没有棋子， 1：表示黑子  2：表示白子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始的二维数组
        for (int[] row : chessArr1){
            for (int data : row){
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //将二维数组 转 稀疏数组的思想
        //1.先便利二维数组 得到非零数据的个数
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for(int j = 0 ;j < chessArr1.length;j++){
                if (chessArr1[i][j] != 0 ){
                    sum++;
                }
            }
        }
        System.out.println("sum ="+ sum);

        //2.创建对应的稀疏数组
        int sparesArr[][] = new int[sum + 1][3];
        //给稀疏数组赋值
        sparesArr[0][0] = 11;
        sparesArr[0][1] = 11;
        sparesArr[0][2] = sum;

        //遍历二维数组，将非零的值 存放到 稀疏数组sparesArr中
        int count = 0; // count 用于记录是第几行非零数据
        for (int i = 0; i < chessArr1.length; i++) {
            for(int j = 0 ;j < chessArr1.length;j++){
                if (chessArr1[i][j] != 0 ){
                    count++;
                    sparesArr[count][0] = i;
                    sparesArr[count][1] = j;
                    sparesArr[count][2] = chessArr1[i][j];
                }
            }
        }

        //输出稀疏数组的形式
        System.out.println();
        System.out.println("得到的稀疏数组为~~~");
        for (int i = 0; i < sparesArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t",sparesArr[i][0],sparesArr[i][1],sparesArr[i][2]);
        }
        System.out.println();


    //将稀疏数组 恢复成 原始的二维数组
    // 1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组

        int chessArr2[][] = new int[sparesArr[0][0]][sparesArr[0][1]];

        //2.在读取稀疏数组后几行的数据(从第二行开始)，并赋值给 原始的二维数组即可
        for (int i = 1; i < sparesArr.length; i++) {
            chessArr2[sparesArr[i][0]][sparesArr[i][1]] = sparesArr[i][2];
        }

        //输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组");
        for (int[] row : chessArr2){
            for (int data : row){
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

    }
}
