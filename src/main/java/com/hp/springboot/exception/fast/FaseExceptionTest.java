package com.hp.springboot.exception.fast;

/**
 * comment
 *
 * @author hupan
 * @since 2018-03-19 20:42
 */
public class FaseExceptionTest {
    public static void main(String[] args) throws FastCheckedException {
        int i = 1000, j = 0;
        testException(i, j);
    }

    public static void testException(int i, int j) throws FastCheckedException {
        try {
            System.out.println(i / j);
        } catch (Exception e) {
            throw new FastCheckedException("抛出异常", e);
        }
    }
}
