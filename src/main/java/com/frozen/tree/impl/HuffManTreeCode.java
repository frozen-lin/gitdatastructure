package com.frozen.tree.impl;


import com.frozen.entity.WeightValObj;
import com.frozen.utils.MyUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.*;

/**
 * <program> gitdatastructure </program>
 * <description> 赫夫曼编码(压缩) </description>
 *
 * @author : lw
 * @date : 2019-11-18 22:23
 **/
@Data
@EqualsAndHashCode(callSuper = true)
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
    /**
     * 压缩文件后缀名
     */
    final private static String ZIP_FILE_SUFFIX = ".huffman";

    public static HuffManTreeCode buildHuffmanTreeCode(byte[] content) {
        int length = content.length;
        List<WeightValObj<Byte>> byteList = new ArrayList<>();
        Map<Byte, Integer> weightMap = new HashMap<>(length);
        int weight;
        for (byte b : content) {
            weight = weightMap.get(b) == null ? 0 : weightMap.get(b);
            weightMap.put(b, ++weight);
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
        if (!Objects.isNull(root) && huffManCodesMap.isEmpty()) {
            if (Objects.isNull(root.getLeft()) && Objects.isNull(root.getRight())) {
                huffManCodesMap.put(root.getObj(), "0");
            } else {
                getHuffManCodesMap(root.getLeft(), 0, new StringBuilder());
                getHuffManCodesMap(root.getRight(), 1, new StringBuilder());
            }
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

    /**
     * <description> 压缩文件 </description>
     *
     * @param originFile : 待压缩的文件
     * @param targetDir  : 压缩文件生成的路径
     * @param fileName   :  压缩文件名
     * @return : void
     * @author : lw
     * @date : 2019/11/26 22:09
     */
    public static void zip(File originFile, File targetDir, String fileName) {
        if (!Objects.isNull(originFile) && originFile.exists() && originFile.isFile()) {
            if (!targetDir.exists()) {
                targetDir.mkdirs();
            }
            try (FileInputStream fips = new FileInputStream(originFile);
                 ObjectOutputStream oops = new ObjectOutputStream(new FileOutputStream(targetDir.getPath() + File.separatorChar + fileName + ZIP_FILE_SUFFIX))) {
                String originFileSuffix = StringUtils.substringAfterLast(originFile.getPath(), ".");
                int length;
                byte[] bytes = new byte[1024];
                List<Byte> byteList = new ArrayList<>();
                while ((length = fips.read(bytes)) != -1) {
                    for (int i = 0; i < length; i++) {
                        byteList.add(bytes[i]);
                    }
                }
                byte[] content = MyUtils.byteList2Array(byteList);
                HuffManTreeCode huffManTreeCode = HuffManTreeCode.buildHuffmanTreeCode(content);
                byte[] zipBytes = huffManTreeCode.getCodeBytes();
                Map<Byte, String> codesMap = huffManTreeCode.getHuffManCodesMap();
                Integer lastByteLength = huffManTreeCode.getLastByteLength();
                oops.writeObject(zipBytes);
                oops.writeObject(codesMap);
                oops.writeObject(lastByteLength);
                oops.writeObject(originFileSuffix);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * <description>  </description>
     *
     * @param zipFile  : 经过huffman压缩过的文件
     * @param unZipDir : 解压缩生成文件的目录
     * @param fileName :  解压缩生成的文件
     * @return : void
     * @author : lw
     * @date : 2019/11/29 16:53
     */
    public static void unzip(File zipFile, File unZipDir, String fileName) {
        if (!Objects.isNull(zipFile) && zipFile.isFile()) {
            //若生成文件夹不存在,创建文件夹
            if (!unZipDir.exists()) {
                unZipDir.mkdirs();
            }
            try (ObjectInputStream ips = new ObjectInputStream(new FileInputStream(zipFile))) {
                Object obj;
                byte[] zipBytes = null;
                Map<Byte, String> codesMap = null;
                Integer lastByteLength = null;
                String originFileSuffix = null;
                for (int i = 0; i < 4; i++) {
                    obj = ips.readObject();
                    if (obj instanceof byte[]) {
                        //赫夫曼编码字节数组
                        zipBytes = (byte[]) obj;
                    } else if (obj instanceof Map) {
                        //编码Map
                        codesMap = (Map<Byte, String>) obj;
                    } else if (obj instanceof Integer) {
                        //最后一节长度
                        lastByteLength = (Integer) obj;
                    } else if (obj instanceof String) {
                        //文件后缀名
                        originFileSuffix = (String) obj;
                    }
                }
                if (Objects.isNull(zipBytes)||Objects.isNull(codesMap)||Objects.isNull(lastByteLength)||Objects
                .isNull(originFileSuffix)) {
                    throw new RuntimeException("给定的压缩文件有误,不是Huffman压缩文件");
                }
                byte[] contents = HuffManTreeCode.decode(zipBytes, codesMap, lastByteLength);
                try(FileOutputStream fops = new FileOutputStream(unZipDir.getPath()+File.separatorChar+fileName+"."+originFileSuffix);
                    ByteArrayOutputStream bops = new ByteArrayOutputStream()){
                    bops.write(contents);
                    bops.writeTo(fops);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalArgumentException("参数有误");
        }
    }
}
