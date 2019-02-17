package io.th0rgal.dpg;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import java.math.BigInteger;

public class DPG {

    public static String generate(String[] salts, int password_size, char[] allowed_chars) {

        // Seed generation (using sha3 256)
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest256();
        byte[] digest = digestSHA3.digest(String.join("", salts).getBytes());
        BigInteger seed = new BigInteger(1, digest);

        // Password generation (using a deterministic random number generator)
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < password_size; i++) {
            // seed = (1_103_515_245 * seed + 12345) % 999_999_937
            seed = seed.multiply(BigInteger.valueOf(1_103_515_245L)).add(BigInteger.valueOf(12345L)).mod(BigInteger.valueOf(999_999_937L));
            // output += allowed_chars[seed%allowed_chars.size]
            output.append(allowed_chars[seed.mod(BigInteger.valueOf(allowed_chars.length)).intValueExact()]);
        }

        return output.toString();

    }

}