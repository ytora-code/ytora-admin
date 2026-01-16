package xyz.ytora.base.storage.strategy;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import xyz.ytora.base.storage.IBucketStrategy;
import xyz.ytora.ytool.str.Strs;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

/**
 * created by YT on 2026/1/16 23:36:12
 * <br/>
 * 基于 murmur3_128 的桶策略
 */
public class Murmur3_128StrategyImpl implements IBucketStrategy {

    private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    @Override
    public String fileId() {
        // 时间戳+随机数产生原始熵源
        String raw = Long.toString(System.currentTimeMillis(), Character.MAX_RADIX).toUpperCase()
                + "-" + Strs.randomStr(16, 0, 61);

        // 雪崩打散
        HashCode hc = Hashing.murmur3_128().hashString(raw, StandardCharsets.UTF_8);

        // 128-bit -> base62
        return toBase62(hc.asBytes());
    }

    @Override
    public Path bucketDir(String fileId) {
        if (fileId == null || fileId.isBlank()) {
            throw new IllegalArgumentException("fileId 不能为空");
        }

        byte[] b = Hashing.murmur3_128().hashString(fileId, StandardCharsets.UTF_8).asBytes();

        // 两层 256×256：00..ff / 00..ff
        String l1 = hexByte(b[0]);
        String l2 = hexByte(b[1]);

        // 返回桶的路径
        return Path.of(l1, l2);
    }

    private static String hexByte(byte b) {
        int v = b & 0xFF; // 0..255
        return (v < 16 ? "0" : "") + Integer.toHexString(v); // 00..ff
    }

    /**
     * 16字节 -> Base62
     */
    private static String toBase62(byte[] bytes) {
        BigInteger bi = new BigInteger(1, bytes);
        if (bi.equals(BigInteger.ZERO)) return "0";

        BigInteger base = BigInteger.valueOf(62);
        StringBuilder sb = new StringBuilder(24);

        while (bi.signum() > 0) {
            BigInteger[] dr = bi.divideAndRemainder(base);
            sb.append(BASE62[dr[1].intValue()]);
            bi = dr[0];
        }
        return sb.reverse().toString();
    }
}
