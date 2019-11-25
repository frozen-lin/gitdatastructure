package com.frozen.tree.impl;


import com.frozen.entity.WeightValObj;
import com.frozen.utils.MyUtils;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * <program> gitdatastructure </program>
 * <description> 赫夫曼编码(压缩) </description>
 *
 * @author : lw
 * @date : 2019-11-18 22:23
 **/
@Data
public class HuffManTreeCode extends HuffManTree<Byte> {

    /**
     * 赫夫曼编码Map
     */
    private Map<Byte, String> huffManCodesMap = new HashMap<>();

    /**
     * 最后1个byte的字符串长度
     */
    private int lastByteLength;

    /**
     * 压缩后的byte数组
     */
    private byte[] codeBytes;

    public static HuffManTreeCode buildHuffmanTreeCode(byte[] content) {
        int length = content.length;
        List<WeightValObj<Byte>> byteList = new ArrayList<>();
        Map<Byte, Integer> weightMap = new HashMap<>(length);
        int weight;
        for (int i = 0; i < length; i++) {
            weight = weightMap.get(content[i]) == null ? 0 : weightMap.get(content[i]);
            weightMap.put(content[i], ++weight);
        }
        Iterator<Map.Entry<Byte, Integer>> iterator = weightMap.entrySet().iterator();
        Map.Entry<Byte, Integer> entry;
        while (iterator.hasNext()) {
            entry = iterator.next();
            byteList.add(new WeightValObj<>(entry.getValue(), entry.getKey()));
        }
        List<TreeNode<Byte>> nodeList = new LinkedList<>();
        for (WeightValObj<Byte> obj : byteList) {
            nodeList.add(new TreeNode<>(obj.getWeight(), obj.getObj()));
        }
        TreeNode<Byte> top;
        TreeNode<Byte> left;
        TreeNode<Byte> right;
        while (nodeList.size() > 1) {
            Collections.sort(nodeList);
            left = nodeList.remove(0);
            right = nodeList.remove(0);
            int sum = left.getNo() + right.getNo();
            top = new TreeNode<>(sum, null);
            top.setLeft(left);
            top.setRight(right);
            nodeList.add(top);
        }
        HuffManTreeCode huffmanTreeCode = new HuffManTreeCode();
        huffmanTreeCode.setRoot(nodeList.get(0));
        huffmanTreeCode.codeBytes = HuffManTreeCode.str2ByteArray(huffmanTreeCode.getHuffManCodes(content));
        return huffmanTreeCode;
    }

    public Map<Byte, String> getHuffManCodesMap() {
        if (!Objects.isNull(this.root) && huffManCodesMap.isEmpty()) {
            getHuffManCodesMap(this.root.getLeft(), 0, new StringBuilder());
            getHuffManCodesMap(this.root.getRight(), 1, new StringBuilder());
        }
        return huffManCodesMap;
    }

    private void getHuffManCodesMap(TreeNode<Byte> treeNode, int path, StringBuilder stringBuilder) {
        if (Objects.isNull(treeNode)) {
            return;
        }
        //将code加入stringBuilder
        stringBuilder.append(path);
        //非叶子节点
        if (Objects.isNull(treeNode.getObj())) {
            //左节点重新new
            getHuffManCodesMap(treeNode.getLeft(), 0, new StringBuilder(stringBuilder));
            //右节点继续沿用该stringBuilder
            getHuffManCodesMap(treeNode.getRight(), 1, stringBuilder);
        } else {
            huffManCodesMap.put(treeNode.getObj(), stringBuilder.toString());
        }
    }

    /**
     * <description> 获取编码后的字符串 </description>
     *
     * @return : java.lang.String
     * @author : lw
     * @date : 2019/11/19 21:02
     */
    public String getHuffManCodes(byte[] originContent) {
        Map<Byte, String> huffManCodesMap = getHuffManCodesMap();
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : originContent) {
            stringBuilder.append(huffManCodesMap.get(b));
        }
        this.lastByteLength = stringBuilder.length() % 8;
        return stringBuilder.toString();
    }

    /**
     * <description>  </description>
     *
     * @param huffManCodes :  二进制字符串
     * @return : byte[]
     * @author : lw
     * @date : 2019/11/19 21:15
     */
    public static byte[] str2ByteArray(String huffManCodes) {
        if (StringUtils.isNotEmpty(huffManCodes)) {
            int length = huffManCodes.length();
            //截取字符串数字 8位一截
            int beginIndex;
            int endIndex = 0;
            int index = 0;
            //初始化byte数组
            byte[] bytes = new byte[(length + 7) / 8];
            String binStr;
            while (endIndex < length) {
                beginIndex = endIndex;
                if (endIndex + 8 < length) {
                    endIndex += 8;
                } else {
                    endIndex = length;
                }
                bytes[index++] = MyUtils.binary2Byte(huffManCodes.substring(beginIndex, endIndex));
            }
            return bytes;
        }
        throw new IllegalArgumentException("参数为空");
    }

    /**
     * <description> 根据赫夫曼编码byte[]和解码Map 获取原始byte[] </description>
     *
     * @param huffmanCodes    :赫夫曼编码byte[]
     * @param huffManCodesMap :赫夫曼编码表Map
     * @return : byte[]
     * @author : lw
     * @date : 2019/11/25 21:04
     */
    public static byte[] decode(byte[] huffmanCodes, Map<Byte, String> huffManCodesMap, int lastLength) {
        StringBuilder stringBuilder = new StringBuilder();
        int arrayLength = huffmanCodes.length;
        for (int i = 0; i < arrayLength; i++) {
            if (i == arrayLength - 1) {
                //最后一位
                stringBuilder.append(byte2BinaryString(huffmanCodes[i], lastLength));
            } else {
                stringBuilder.append(byte2BinaryString(huffmanCodes[i], 8));
            }
        }
        Set<Map.Entry<Byte, String>> entries = huffManCodesMap.entrySet();
        Map<String, Byte> huffManDeCodesMap = new HashMap<>();
        for (Map.Entry<Byte, String> entry : entries) {
            huffManDeCodesMap.put(entry.getValue(), entry.getKey());
        }
        List<Byte> byteList = new ArrayList<>();
        int offset;
        for (int i = 0; i < stringBuilder.length(); i = i + offset) {
            offset = 1;
            while (Objects.isNull(huffManDeCodesMap.get(stringBuilder.substring(i, i + offset)))) {
                offset++;
            }
            byteList.add(huffManDeCodesMap.get(stringBuilder.substring(i, i + offset)));
        }
        return MyUtils.byteList2Array(byteList);
    }

    /**
     * <description>  </description>
     *
     * @return : java.lang.String
     * @author : lw
     * @date : 2019/11/25 21:41
     */
    public static String byte2BinaryString(byte b, int length) {
        int temp = b | 256;
        String binaryString = Integer.toBinaryString(temp);
        return binaryString.substring(binaryString.length() - length);
    }
}
