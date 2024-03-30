package org.atguigu.huffmanCode;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {

        //测试压缩文件
//        String srcFile = "D:\\ITstudy\\proguramingstudy\\DataStructures\\test.txt";
//        String detFile = "D:\\ITstudy\\proguramingstudy\\DataStructures\\test.zip";
//        zipFile(srcFile, detFile);
//        System.out.println("压缩成功");

        //测试解压文件
        String zipFile = "D:\\ITstudy\\proguramingstudy\\DataStructures\\test.zip";
        String dstFile ="D:\\ITstudy\\proguramingstudy\\DataStructures\\test2.txt";
        unZipFile(zipFile,dstFile);
        System.out.println("解压成功");


//        String str = "i like like like java do you like a java";
//        byte[] contentBytes = str.getBytes();
//        System.out.println(contentBytes.length);//40
//
//        byte[] huffmanCodesBytes = huffmanZip(contentBytes);
//        System.out.println("压缩后的结果 " + Arrays.toString(huffmanCodesBytes) + ";长度位 " + huffmanCodesBytes.length);
//
////        System.out.println(byteToString((byte) -1));
//        byte[] decode = decode(huffmanCodes, huffmanCodesBytes);
//        System.out.println("解压后的结果 " + new String(decode));
        //如何将 数据进行解压(解码)
        //分步过程
        /*
        List<Node> nodes = getNodes(contentBytes);
        System.out.println("nodes=" + nodes);

        //测试 创建的赫夫曼树
        System.out.println("赫夫曼树");
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        System.out.println("前序遍历");
        huffmanTreeRoot.preOrder();

        //测试 是否生成了对应的赫夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        System.out.println("生成的赫夫曼编码表=" + huffmanCodes);
        //生成的赫夫曼编码表{32=01, 97=100, 100=11000, 117=11001, 101=1110,
        // 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}

        //测试
        byte[] huffmanCpdeBytes = zip(contentBytes, huffmanCodes);
        System.out.println(Arrays.toString(huffmanCpdeBytes));
*/
    }

    //完成数据的解压
    //思路
    //1.先将huffmanCodeBytes :[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //      重新先转成赫夫曼编码对应的二进制的字符串
    //2.将赫夫曼编码对应的二进制的字符串 对照赫夫曼编码

    //编写一个方法，完成对压缩数据的解码

    /**
     * @param huffmanCodes 赫夫曼编码表
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //1.先得到 huffmanBytes 对应的二进制的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //将byte 数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToString(!flag, b));
        }
//        System.out.println("赫夫曼字节数组对应的二进制字符串="+stringBuilder.toString());
        //把字符串安装指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换，因为反向查询
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
//        System.out.println("map="+map);

        //创建集合 存放byte
        ArrayList<Byte> list = new ArrayList<>();
        //i:索引,扫描stringBuilder
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;      //小的计数器
            Byte b = null;
            boolean flag = true;
            while (flag) {
                //递增的取出key，进行比较
                String key = stringBuilder.substring(i, i + count);//i不动   让count移动    指导匹配到一个字符
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;//i直接移动到count的位置
        }
        //当for循环结束后，集合中存放了所有的字符
        //把list 中的数据放入到byte[] 并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 将一个byte 转成一个二进制的字符串
     *
     * @param b    传入的byte
     * @param flag 标识是否需要补高位，如果是真，标识需要补高位，如果是false标识不补
     * @return 是该b 对应的二进制的字符串，(注：按补码返回)
     */
    private static String byteToString(boolean flag, byte b) {
        int temp = b;
        //如果是正数，还存在补高位
        if (flag) {
            temp |= 256; //按位与
        }
        //
        String str = Integer.toBinaryString(temp);//返回的是temp对应的二进制的补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }

    }

    //编写方法，完成对压缩文件的解压

    /**
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压到那个路径
     */
    public static void unZipFile(String zipFile, String dstFile) {

        //定义文件的输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和is 关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将bytes 数组写入到目标文件
            os = new FileOutputStream(dstFile);
            //写出数据
            os.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

    //编写方法，将一个文件进行压缩

    /**
     * @param srcFile 传入的希望压缩的文件的完整路径
     * @param dstFile 压缩后将压缩文件放置的目录
     */
    public static void zipFile(String srcFile, String dstFile) {
        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //创建一个文件的输入流
        FileInputStream is = null;
        try {
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //获取到文件对应的赫夫曼编码表,直接对b数组进行压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流,存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把赫夫曼编码后的字节数组，写入压缩文件
            oos.writeObject(huffmanBytes);  //先把 huffmanBytes

            //这里以对象流的方式写入 赫夫曼编码 ,为了以后回复源文件时使用
            //注意，一定要把赫夫曼编码写入压缩文件
            oos.writeObject(huffmanCodes);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                os.close();
                oos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //将方法封装，便于调用

    /**
     * @param bytes 原始的字符串对应的字节数组
     * @return
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        //2.根据nodes 创建赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //3.根据赫夫曼树 创建对应的赫夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //根据生成的赫夫曼编码，压缩得到压缩后的赫夫曼编码字节数组
        byte[] huffmanCpdeBytes = zip(bytes, huffmanCodes);
        return huffmanCpdeBytes;
    }


    //编写一个方法，将字符串对应的byte[] 数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte[]

    /**
     * @param bytes        原始的字符串对应的byte[]
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的byte[]
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //1.利用huffmanCodes 将bytes 转成 赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历 bytes[]
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
//        System.out.println("stringBuilder="+stringBuilder.toString());
        //int len = (stringBuilder.length()+7)/8
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建 存储压缩后的 byte[]
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//记录第几个bit
        for (int i = 0; i < stringBuilder.length(); i += 8) {//因为是每8位对应一个bit,所以步长位+8
            String strByte;
            if (i + 8 > stringBuilder.length()) {//不够8位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将stringByte 转成一个byte, 放入到 by
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }

        return huffmanCodeBytes;

    }

    //生成赫夫曼树对应的赫夫曼编码
    //思路：
    //1.将赫夫曼编码表，存放在Map<Byte,String>形式
    //      32-01  97-100   100-11000 等形式
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    //2.在生成赫夫曼编码表示，需要去拼接路径，定义一个StringBuilder 存储某个叶子 结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    //为了调用方便，重载getCodes
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        //处理root 的左子树
        getCodes(root.left, "0", stringBuilder);

        //处理root的右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 功能：  将传入的node 结点的所有叶子节点的赫夫曼编码得到，并放入到 huffmanCodes集合中
     *
     * @param node          传入的结点，
     * @param code          路径:左子节点 是0，右子节点是 1
     * @param stringBuilder 拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code 加入到 stringBuilder2
        stringBuilder2.append(code);
        if (node != null) {//如果node ==null 不处理
            //判断当前node 是叶子结点还是非叶子节点
            if (node.data == null) {//非叶子节点
                //递归处理
                //向左
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else {//叶子节点
                //就表示，找到了某个叶子结点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    /**
     * @param bytes 接收字节数组
     * @return 返回 List 形式 :
     */
    private static List<Node> getNodes(byte[] bytes) {
        //1.创建一个ArrayList
        ArrayList<Node> nodes = new ArrayList<>();

        //2.遍历 bytes,统计每个byte出现的次数 ->map[key,value]
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {  //map没有这个字符数据,第一次
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        //3.把每个键值对转成一个node 对象，并加入到nodes集合
        //遍历map
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //通过list 创建对应的赫夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //排序,从小到大
            Collections.sort(nodes);
            //取出第一颗最小的二叉树
            Node leftNode = nodes.get(0);
            //取出第二颗最小的二叉树
            Node rightNode = nodes.get(1);

            //创建一颗新的二叉树,他的根节点，没有data 只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            //将已经处理的两颗二叉树从nodes剔除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树，加入到nodes
            nodes.add(parent);
        }
        //nodes 最后的节点们就是赫夫曼树的根节点
        return nodes.get(0);
    }

    //前序遍历
    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空");
        }
    }
}

//创建Node,带数据和权值
class Node implements Comparable<Node> {
    Byte data;  //存放数据(字符)本身,例'a'=>97
    int weight; //权值,表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node[" +
                "data=" + data +
                ", weight=" + weight +
                ']';
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排序
        return this.weight - o.weight;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}