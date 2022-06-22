/*
 * Copyright 2019 Web3 Labs Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.web3j.crypto;

import java.math.BigInteger;

import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.crypto.params.ECDomainParameters;

/** An ECDSA Signature. */
public class ECDSASignature {

    public static final X9ECParameters K1_CURVE_PARAMS = CustomNamedCurves.getByName("secp256k1");
    static final ECDomainParameters K1_CURVE =
            new ECDomainParameters(
                    K1_CURVE_PARAMS.getCurve(),
                    K1_CURVE_PARAMS.getG(),
                    K1_CURVE_PARAMS.getN(),
                    K1_CURVE_PARAMS.getH());
    static final BigInteger K1_HALF_CURVE_ORDER = K1_CURVE_PARAMS.getN().shiftRight(1);

    public final BigInteger r;
    public final BigInteger s;

    public ECDSASignature(BigInteger r, BigInteger s) {
        this.r = r;
        this.s = s;
    }

    /**
     * @return true if the S component is "low", that means it is below {@link
     *     ECDSASignature#K1_HALF_CURVE_ORDER}. See <a
     *     href="https://github.com/bitcoin/bips/blob/master/bip-0062.mediawiki#Low_S_values_in_signatures">
     *     BIP62</a>.
     */
    public boolean isCanonical() {
        return s.compareTo(K1_HALF_CURVE_ORDER) <= 0;
    }

    /**
     * Will automatically adjust the S component to be less than or equal to half the curve order,
     * if necessary. This is required because for every signature (r,s) the signature (r, -s (mod
     * N)) is a valid signature of the same message. However, we dislike the ability to modify the
     * bits of a Bitcoin transaction after it's been signed, as that violates various assumed
     * invariants. Thus in future only one of those forms will be considered legal and the other
     * will be banned.
     *
     * @return the signature in a canonicalised form.
     */
    public ECDSASignature toCanonicalised() {
        if (!isCanonical()) {
            // The order of the curve is the number of valid points that exist on that curve.
            // If S is in the upper half of the number of valid points, then bring it back to
            // the lower half. Otherwise, imagine that
            //    N = 10
            //    s = 8, so (-8 % 10 == 2) thus both (r, 8) and (r, 2) are valid solutions.
            //    10 - 8 == 2, giving us always the latter solution, which is canonical.
            return new ECDSASignature(r, ECDSASignature.K1_CURVE.getN().subtract(s));
        } else {
            return this;
        }
    }

    public ECDomainParameters getCurve() {
        return K1_CURVE;
    }
}
