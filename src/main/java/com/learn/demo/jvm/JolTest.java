package com.learn.demo.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author gaobin
 * @date 2022/1/3 11:35 下午
 * @desc 查看对象的占用字节情况
 * https://note.youdao.com/yws/public/resource/0e14c4e1fa9ee6b3fda6da53fd20a04d/xmlnote/326F197DC4D5453090AEDD2ACB1A5E8E/100689
 */
public class JolTest {

    public static void main(String[] args) {
        /**
         *  OFFSET  SIZE   TYPE DESCRIPTION         VALUE
         *       0     4   (object header)     05 00 00 00 (00000101 00000000 00000000 00000000) (5)    Mark Word
         *       4     4   (object header)     00 00 00 00 (00000000 00000000 00000000 00000000) (0)    Mark Word
         *       8     4   (object header)     00 10 00 00 (00000000 00010000 00000000 00000000) (4096) Klass Pointer
         *      12     4   (loss due to the next object alignment)                                      对齐填充
         */
        ClassLayout layout = ClassLayout.parseInstance(new Object());
        System.out.println(layout.toPrintable());

        /**
         *  OFFSET  SIZE   TYPE DESCRIPTION          VALUE
         *       0     4        (object header)      01 00 00 00 (00000001 00000000 00000000 00000000) (1)     Mark Word
         *       4     4        (object header)      00 00 00 00 (00000000 00000000 00000000 00000000) (0)     MarkWord
         *       8     4        (object header)      10 0c 00 00 (00010000 00001100 00000000 00000000) (3088)  Klass Pointer
         *      12     4        (object header)      05 00 00 00 (00000101 00000000 00000000 00000000) (5)     数组长度
         *      16    20    int [I.<elements>        N/A                                                       数组属性长度
         *      36     4        (loss due to the next object alignment)                                        对齐填充
         */
        layout = ClassLayout.parseInstance(new int[5]);
        System.out.println(layout.toPrintable());
    }
}
