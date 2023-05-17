/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.services;

import android.graphics.Bitmap;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.neilalexander.jnacl.NaCl;
import java.util.Date;
import java.util.Hashtable;
import ch.threema.app.utils.TestUtil;
import ch.threema.client.ProtocolDefines;
import ch.threema.client.Utils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class QRCodeServiceImpl implements QRCodeService {

    private final UserService userService;

    private static final String CONTENT_PREFIX = "3mid:";

    private Bitmap userQRCodeBitmap;

    public QRCodeServiceImpl(UserService userService) {
        this.userService = userService;
    }

    private String getContent() {
        return CONTENT_PREFIX + this.userService.getIdentity() + "," + Utils.byteArrayToHexString(this.userService.getPublicKey());
    }

    @Override
    public QRCodeContentResult getResult(String content) {
        if (!ListenerUtil.mutListener.listen(40708)) {
            if (!TestUtil.empty(content)) {
                final String[] pieces = content.substring(CONTENT_PREFIX.length()).split(",");
                if (!ListenerUtil.mutListener.listen(40707)) {
                    if ((ListenerUtil.mutListener.listen(40696) ? ((ListenerUtil.mutListener.listen(40691) ? ((ListenerUtil.mutListener.listen(40690) ? (pieces.length <= 2) : (ListenerUtil.mutListener.listen(40689) ? (pieces.length > 2) : (ListenerUtil.mutListener.listen(40688) ? (pieces.length < 2) : (ListenerUtil.mutListener.listen(40687) ? (pieces.length != 2) : (ListenerUtil.mutListener.listen(40686) ? (pieces.length == 2) : (pieces.length >= 2)))))) || pieces[0].length() == ProtocolDefines.IDENTITY_LEN) : ((ListenerUtil.mutListener.listen(40690) ? (pieces.length <= 2) : (ListenerUtil.mutListener.listen(40689) ? (pieces.length > 2) : (ListenerUtil.mutListener.listen(40688) ? (pieces.length < 2) : (ListenerUtil.mutListener.listen(40687) ? (pieces.length != 2) : (ListenerUtil.mutListener.listen(40686) ? (pieces.length == 2) : (pieces.length >= 2)))))) && pieces[0].length() == ProtocolDefines.IDENTITY_LEN)) || pieces[1].length() == (ListenerUtil.mutListener.listen(40695) ? (NaCl.PUBLICKEYBYTES % 2) : (ListenerUtil.mutListener.listen(40694) ? (NaCl.PUBLICKEYBYTES / 2) : (ListenerUtil.mutListener.listen(40693) ? (NaCl.PUBLICKEYBYTES - 2) : (ListenerUtil.mutListener.listen(40692) ? (NaCl.PUBLICKEYBYTES + 2) : (NaCl.PUBLICKEYBYTES * 2)))))) : ((ListenerUtil.mutListener.listen(40691) ? ((ListenerUtil.mutListener.listen(40690) ? (pieces.length <= 2) : (ListenerUtil.mutListener.listen(40689) ? (pieces.length > 2) : (ListenerUtil.mutListener.listen(40688) ? (pieces.length < 2) : (ListenerUtil.mutListener.listen(40687) ? (pieces.length != 2) : (ListenerUtil.mutListener.listen(40686) ? (pieces.length == 2) : (pieces.length >= 2)))))) || pieces[0].length() == ProtocolDefines.IDENTITY_LEN) : ((ListenerUtil.mutListener.listen(40690) ? (pieces.length <= 2) : (ListenerUtil.mutListener.listen(40689) ? (pieces.length > 2) : (ListenerUtil.mutListener.listen(40688) ? (pieces.length < 2) : (ListenerUtil.mutListener.listen(40687) ? (pieces.length != 2) : (ListenerUtil.mutListener.listen(40686) ? (pieces.length == 2) : (pieces.length >= 2)))))) && pieces[0].length() == ProtocolDefines.IDENTITY_LEN)) && pieces[1].length() == (ListenerUtil.mutListener.listen(40695) ? (NaCl.PUBLICKEYBYTES % 2) : (ListenerUtil.mutListener.listen(40694) ? (NaCl.PUBLICKEYBYTES / 2) : (ListenerUtil.mutListener.listen(40693) ? (NaCl.PUBLICKEYBYTES - 2) : (ListenerUtil.mutListener.listen(40692) ? (NaCl.PUBLICKEYBYTES + 2) : (NaCl.PUBLICKEYBYTES * 2)))))))) {
                        return new QRCodeContentResult() {

                            @Override
                            public String getIdentity() {
                                return pieces[0];
                            }

                            @Override
                            public byte[] getPublicKey() {
                                return Utils.hexStringToByteArray(pieces[1]);
                            }

                            @Override
                            public Date getExpirationDate() {
                                if (!ListenerUtil.mutListener.listen(40706)) {
                                    if ((ListenerUtil.mutListener.listen(40701) ? (pieces.length <= 3) : (ListenerUtil.mutListener.listen(40700) ? (pieces.length > 3) : (ListenerUtil.mutListener.listen(40699) ? (pieces.length < 3) : (ListenerUtil.mutListener.listen(40698) ? (pieces.length != 3) : (ListenerUtil.mutListener.listen(40697) ? (pieces.length == 3) : (pieces.length >= 3)))))))
                                        return new Date((ListenerUtil.mutListener.listen(40705) ? (Long.parseLong(pieces[2]) % 1000) : (ListenerUtil.mutListener.listen(40704) ? (Long.parseLong(pieces[2]) / 1000) : (ListenerUtil.mutListener.listen(40703) ? (Long.parseLong(pieces[2]) - 1000) : (ListenerUtil.mutListener.listen(40702) ? (Long.parseLong(pieces[2]) + 1000) : (Long.parseLong(pieces[2]) * 1000))))));
                                }
                                return null;
                            }
                        };
                    }
                }
            }
        }
        return null;
    }

    private BitMatrix renderQR(String contents, int width, int height, int border, boolean unicode) {
        BarcodeFormat barcodeFormat = BarcodeFormat.QR_CODE;
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>(2);
        if (!ListenerUtil.mutListener.listen(40709)) {
            hints.put(EncodeHintType.MARGIN, border);
        }
        if (!ListenerUtil.mutListener.listen(40711)) {
            if (unicode) {
                if (!ListenerUtil.mutListener.listen(40710)) {
                    hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
                }
            }
        }
        try {
            BitMatrix matrix = barcodeWriter.encode(contents, barcodeFormat, width, height, hints);
            return matrix;
        } catch (WriterException e) {
        }
        return null;
    }

    @Override
    public Bitmap getRawQR(String raw, boolean unicode) {
        if (!ListenerUtil.mutListener.listen(40748)) {
            if (this.userService.hasIdentity()) {
                BitMatrix matrix = null;
                if (!ListenerUtil.mutListener.listen(40720)) {
                    if ((ListenerUtil.mutListener.listen(40717) ? (raw != null || (ListenerUtil.mutListener.listen(40716) ? (raw.length() >= 0) : (ListenerUtil.mutListener.listen(40715) ? (raw.length() <= 0) : (ListenerUtil.mutListener.listen(40714) ? (raw.length() < 0) : (ListenerUtil.mutListener.listen(40713) ? (raw.length() != 0) : (ListenerUtil.mutListener.listen(40712) ? (raw.length() == 0) : (raw.length() > 0))))))) : (raw != null && (ListenerUtil.mutListener.listen(40716) ? (raw.length() >= 0) : (ListenerUtil.mutListener.listen(40715) ? (raw.length() <= 0) : (ListenerUtil.mutListener.listen(40714) ? (raw.length() < 0) : (ListenerUtil.mutListener.listen(40713) ? (raw.length() != 0) : (ListenerUtil.mutListener.listen(40712) ? (raw.length() == 0) : (raw.length() > 0))))))))) {
                        if (!ListenerUtil.mutListener.listen(40719)) {
                            matrix = this.renderQR(raw, 0, 0, 0, unicode);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(40718)) {
                            matrix = this.renderQR(getContent(), 0, 0, 0, unicode);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(40747)) {
                    if (matrix != null) {
                        final int WHITE = 0xFFFFFFFF;
                        int BLACK = 0xFF000000;
                        int width = matrix.getWidth();
                        int height = matrix.getHeight();
                        int[] pixels = new int[(ListenerUtil.mutListener.listen(40724) ? (width % height) : (ListenerUtil.mutListener.listen(40723) ? (width / height) : (ListenerUtil.mutListener.listen(40722) ? (width - height) : (ListenerUtil.mutListener.listen(40721) ? (width + height) : (width * height)))))];
                        if (!ListenerUtil.mutListener.listen(40745)) {
                            {
                                long _loopCounter464 = 0;
                                for (int y = 0; (ListenerUtil.mutListener.listen(40744) ? (y >= height) : (ListenerUtil.mutListener.listen(40743) ? (y <= height) : (ListenerUtil.mutListener.listen(40742) ? (y > height) : (ListenerUtil.mutListener.listen(40741) ? (y != height) : (ListenerUtil.mutListener.listen(40740) ? (y == height) : (y < height)))))); y++) {
                                    ListenerUtil.loopListener.listen("_loopCounter464", ++_loopCounter464);
                                    int offset = (ListenerUtil.mutListener.listen(40728) ? (y % width) : (ListenerUtil.mutListener.listen(40727) ? (y / width) : (ListenerUtil.mutListener.listen(40726) ? (y - width) : (ListenerUtil.mutListener.listen(40725) ? (y + width) : (y * width)))));
                                    if (!ListenerUtil.mutListener.listen(40739)) {
                                        {
                                            long _loopCounter463 = 0;
                                            for (int x = 0; (ListenerUtil.mutListener.listen(40738) ? (x >= width) : (ListenerUtil.mutListener.listen(40737) ? (x <= width) : (ListenerUtil.mutListener.listen(40736) ? (x > width) : (ListenerUtil.mutListener.listen(40735) ? (x != width) : (ListenerUtil.mutListener.listen(40734) ? (x == width) : (x < width)))))); x++) {
                                                ListenerUtil.loopListener.listen("_loopCounter463", ++_loopCounter463);
                                                if (!ListenerUtil.mutListener.listen(40733)) {
                                                    pixels[(ListenerUtil.mutListener.listen(40732) ? (offset % x) : (ListenerUtil.mutListener.listen(40731) ? (offset / x) : (ListenerUtil.mutListener.listen(40730) ? (offset * x) : (ListenerUtil.mutListener.listen(40729) ? (offset - x) : (offset + x)))))] = matrix.get(x, y) ? BLACK : WHITE;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        Bitmap bitmap = Bitmap.createBitmap(matrix.getWidth(), matrix.getHeight(), Bitmap.Config.RGB_565);
                        if (!ListenerUtil.mutListener.listen(40746)) {
                            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
                        }
                        return bitmap;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Bitmap getUserQRCode() {
        if (!ListenerUtil.mutListener.listen(40750)) {
            if (this.userQRCodeBitmap == null) {
                if (!ListenerUtil.mutListener.listen(40749)) {
                    this.userQRCodeBitmap = this.getRawQR(null, false);
                }
            }
        }
        return this.userQRCodeBitmap;
    }
}
