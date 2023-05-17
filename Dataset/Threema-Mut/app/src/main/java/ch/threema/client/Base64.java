/**
 * <p>Encodes and decodes to and from Base64 notation.</p>
 * <p>Homepage: <a href="http://iharder.net/base64">http://iharder.net/base64</a>.</p>
 *
 * <p>Example:</p>
 *
 * <code>String encoded = Base64.encode( myByteArray );</code>
 * <br />
 * <code>byte[] myByteArray = Base64.decode( encoded );</code>
 *
 * <p>Note, according to <a href="http://www.faqs.org/rfcs/rfc3548.html">RFC3548</a>,
 * Section 2.1, implementations should not add line feeds unless explicitly told
 * to do so. I've got Base64 set to this behavior now, although earlier versions
 * broke lines by default.</p>
 *
 * <p>
 * Change Log:
 * </p>
 * <ul>
 *  <li>v3.0.0 - Major cleanup by Danilo to remove unused code like GZIP
 *  or URL safe alphabets</li>
 *  <li>v2.3.7 - Fixed subtle bug when base 64 input stream contained the
 *   value 01111111, which is an invalid base 64 character but should not
 *   throw an ArrayIndexOutOfBoundsException either. Led to discovery of
 *   mishandling (or potential for better handling) of other bad input
 *   characters. You should now get an IOException if you try decoding
 *   something that has bad characters in it.</li>
 *  <li>v2.3.6 - Fixed bug when breaking lines and the final byte of the encoded
 *   string ended in the last column; the buffer was not properly shrunk and
 *   contained an extra (null) byte that made it into the string.</li>
 *  <li>v2.3.5 - Fixed bug in {@link #encodeFromFile} where estimated buffer size
 *   was wrong for files of size 31, 34, and 37 bytes.</li>
 *  <li>v2.3.4 - Fixed bug when working with gzipped streams whereby flushing
 *   the Base64.OutputStream closed the Base64 encoding (by padding with equals
 *   signs) too soon. Also added an option to suppress the automatic decoding
 *   of gzipped streams. Also added experimental support for specifying a
 *   class loader when using the
 *   {@link #decodeToObject(java.lang.String, int, java.lang.ClassLoader)}
 *   method.</li>
 *  <li>v2.3.3 - Changed default char encoding to US-ASCII which reduces the internal Java
 *   footprint with its CharEncoders and so forth. Fixed some javadocs that were
 *   inconsistent. Removed imports and specified things like java.io.IOException
 *   explicitly inline.</li>
 *  <li>v2.3.2 - Reduced memory footprint! Finally refined the "guessing" of how big the
 *   final encoded data will be so that the code doesn't have to create two output
 *   arrays: an oversized initial one and then a final, exact-sized one. Big win
 *   when using the {@link #encodeBytesToBytes(byte[])} family of methods (and not
 *   using the gzip options which uses a different mechanism with streams and stuff).</li>
 *  <li>v2.3.1 - Added {@link #encodeBytesToBytes(byte[], int, int, int)} and some
 *   similar helper methods to be more efficient with memory by not returning a
 *   String but just a byte array.</li>
 *  <li>v2.3 - <strong>This is not a drop-in replacement!</strong> This is two years of comments
 *   and bug fixes queued up and finally executed. Thanks to everyone who sent
 *   me stuff, and I'm sorry I wasn't able to distribute your fixes to everyone else.
 *   Much bad coding was cleaned up including throwing exceptions where necessary
 *   instead of returning null values or something similar. Here are some changes
 *   that may affect you:
 *   <ul>
 *    <li><em>Does not break lines, by default.</em> This is to keep in compliance with
 *      <a href="http://www.faqs.org/rfcs/rfc3548.html">RFC3548</a>.</li>
 *    <li><em>Throws exceptions instead of returning null values.</em> Because some operations
 *      (especially those that may permit the GZIP option) use IO streams, there
 *      is a possiblity of an java.io.IOException being thrown. After some discussion and
 *      thought, I've changed the behavior of the methods to throw java.io.IOExceptions
 *      rather than return null if ever there's an error. I think this is more
 *      appropriate, though it will require some changes to your code. Sorry,
 *      it should have been done this way to begin with.</li>
 *    <li><em>Removed all references to System.out, System.err, and the like.</em>
 *      Shame on me. All I can say is sorry they were ever there.</li>
 *    <li><em>Throws NullPointerExceptions and IllegalArgumentExceptions</em> as needed
 *      such as when passed arrays are null or offsets are invalid.</li>
 *    <li>Cleaned up as much javadoc as I could to avoid any javadoc warnings.
 *      This was especially annoying before for people who were thorough in their
 *      own projects and then had gobs of javadoc warnings on this file.</li>
 *   </ul>
 *  <li>v2.2.1 - Fixed bug using URL_SAFE and ORDERED encodings. Fixed bug
 *   when using very small files (~&lt; 40 bytes).</li>
 *  <li>v2.2 - Added some helper methods for encoding/decoding directly from
 *   one file to the next. Also added a main() method to support command line
 *   encoding/decoding from one file to the next. Also added these Base64 dialects:
 *   <ol>
 *   <li>The default is RFC3548 format.</li>
 *   <li>Calling Base64.setFormat(Base64.BASE64_FORMAT.URLSAFE_FORMAT) generates
 *   URL and file name friendly format as described in Section 4 of RFC3548.
 *   http://www.faqs.org/rfcs/rfc3548.html</li>
 *   <li>Calling Base64.setFormat(Base64.BASE64_FORMAT.ORDERED_FORMAT) generates
 *   URL and file name friendly format that preserves lexical ordering as described
 *   in http://www.faqs.org/qa/rfcc-1940.html</li>
 *   </ol>
 *   Special thanks to Jim Kellerman at <a href="http://www.powerset.com/">http://www.powerset.com/</a>
 *   for contributing the new Base64 dialects.
 *  </li>
 *
 *  <li>v2.1 - Cleaned up javadoc comments and unused variables and methods. Added
 *   some convenience methods for reading and writing to and from files.</li>
 *  <li>v2.0.2 - Now specifies UTF-8 encoding in places where the code fails on systems
 *   with other encodings (like EBCDIC).</li>
 *  <li>v2.0.1 - Fixed an error when decoding a single byte, that is, when the
 *   encoded data was a single byte.</li>
 *  <li>v2.0 - I got rid of methods that used booleans to set options.
 *   Now everything is more consolidated and cleaner. The code now detects
 *   when data that's being decoded is gzip-compressed and will decompress it
 *   automatically. Generally things are cleaner. You'll probably have to
 *   change some method calls that you were making to support the new
 *   options format (<tt>int</tt>s that you "OR" together).</li>
 *  <li>v1.5.1 - Fixed bug when decompressing and decoding to a
 *   byte[] using <tt>decode( String s, boolean gzipCompressed )</tt>.
 *   Added the ability to "suspend" encoding in the Output Stream so
 *   you can turn on and off the encoding if you need to embed base64
 *   data in an otherwise "normal" stream (like an XML file).</li>
 *  <li>v1.5 - Output stream pases on flush() command but doesn't do anything itself.
 *      This helps when using GZIP streams.
 *      Added the ability to GZip-compress objects before encoding them.</li>
 *  <li>v1.4 - Added helper methods to read/write files.</li>
 *  <li>v1.3.6 - Fixed OutputStream.flush() so that 'position' is reset.</li>
 *  <li>v1.3.5 - Added flag to turn on and off line breaks. Fixed bug in input stream
 *      where last buffer being read, if not completely full, was not returned.</li>
 *  <li>v1.3.4 - Fixed when "improperly padded stream" error was thrown at the wrong time.</li>
 *  <li>v1.3.3 - Fixed I/O streams which were totally messed up.</li>
 * </ul>
 *
 * <p>
 * I am placing this code in the Public Domain. Do with it as you will.
 * This software comes with no guarantees or warranties but with
 * plenty of well-wishing instead!
 * Please visit <a href="http://iharder.net/base64">http://iharder.net/base64</a>
 * periodically to check for updates or to contribute improvements.
 * </p>
 *
 * @author Robert Harder
 * @author rob@iharder.net
 * @version 3.0.0
 */
package ch.threema.client;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Base64 {

    /**
     * The equals sign (=) as a byte.
     */
    private static final byte EQUALS_SIGN = (byte) '=';

    // Indicates white space in encoding
    private static final byte WHITE_SPACE_ENC = -5;

    // Indicates equals sign in encoding
    private static final byte EQUALS_SIGN_ENC = -1;

    /* Host platform might be something funny like EBCDIC, so we hardcode these values. */
    private static final byte[] ALPHABET = { (byte) 'A', (byte) 'B', (byte) 'C', (byte) 'D', (byte) 'E', (byte) 'F', (byte) 'G', (byte) 'H', (byte) 'I', (byte) 'J', (byte) 'K', (byte) 'L', (byte) 'M', (byte) 'N', (byte) 'O', (byte) 'P', (byte) 'Q', (byte) 'R', (byte) 'S', (byte) 'T', (byte) 'U', (byte) 'V', (byte) 'W', (byte) 'X', (byte) 'Y', (byte) 'Z', (byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd', (byte) 'e', (byte) 'f', (byte) 'g', (byte) 'h', (byte) 'i', (byte) 'j', (byte) 'k', (byte) 'l', (byte) 'm', (byte) 'n', (byte) 'o', (byte) 'p', (byte) 'q', (byte) 'r', (byte) 's', (byte) 't', (byte) 'u', (byte) 'v', (byte) 'w', (byte) 'x', (byte) 'y', (byte) 'z', (byte) '0', (byte) '1', (byte) '2', (byte) '3', (byte) '4', (byte) '5', (byte) '6', (byte) '7', (byte) '8', (byte) '9', (byte) '+', (byte) '/' };

    /**
     *  Translates a Base64 value to either its 6-bit reconstruction value
     *  or a negative number indicating some other meaning.
     */
    private static final byte[] DECODABET = { // Decimal  0 -  8
    -9, // Decimal  0 -  8
    -9, // Decimal  0 -  8
    -9, // Decimal  0 -  8
    -9, // Decimal  0 -  8
    -9, // Decimal  0 -  8
    -9, // Decimal  0 -  8
    -9, // Decimal  0 -  8
    -9, // Decimal  0 -  8
    -9, // Whitespace: Tab and Linefeed
    -5, // Whitespace: Tab and Linefeed
    -5, // Decimal 11 - 12
    -9, // Decimal 11 - 12
    -9, // Whitespace: Carriage Return
    -5, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 27 - 31
    -9, // Decimal 27 - 31
    -9, // Decimal 27 - 31
    -9, // Decimal 27 - 31
    -9, // Decimal 27 - 31
    -9, // Whitespace: Space
    -5, // Decimal 33 - 42
    -9, // Decimal 33 - 42
    -9, // Decimal 33 - 42
    -9, // Decimal 33 - 42
    -9, // Decimal 33 - 42
    -9, // Decimal 33 - 42
    -9, // Decimal 33 - 42
    -9, // Decimal 33 - 42
    -9, // Decimal 33 - 42
    -9, // Decimal 33 - 42
    -9, // Plus sign at decimal 43
    62, // Decimal 44 - 46
    -9, // Decimal 44 - 46
    -9, // Decimal 44 - 46
    -9, // Slash at decimal 47
    63, // Numbers zero through nine
    52, // Numbers zero through nine
    53, // Numbers zero through nine
    54, // Numbers zero through nine
    55, // Numbers zero through nine
    56, // Numbers zero through nine
    57, // Numbers zero through nine
    58, // Numbers zero through nine
    59, // Numbers zero through nine
    60, // Numbers zero through nine
    61, // Decimal 58 - 60
    -9, // Decimal 58 - 60
    -9, // Decimal 58 - 60
    -9, // Equals sign at decimal 61
    -1, // Decimal 62 - 64
    -9, // Decimal 62 - 64
    -9, // Decimal 62 - 64
    -9, // Letters 'A' through 'N'
    0, // Letters 'A' through 'N'
    1, // Letters 'A' through 'N'
    2, // Letters 'A' through 'N'
    3, // Letters 'A' through 'N'
    4, // Letters 'A' through 'N'
    5, // Letters 'A' through 'N'
    6, // Letters 'A' through 'N'
    7, // Letters 'A' through 'N'
    8, // Letters 'A' through 'N'
    9, // Letters 'A' through 'N'
    10, // Letters 'A' through 'N'
    11, // Letters 'A' through 'N'
    12, // Letters 'A' through 'N'
    13, // Letters 'O' through 'Z'
    14, // Letters 'O' through 'Z'
    15, // Letters 'O' through 'Z'
    16, // Letters 'O' through 'Z'
    17, // Letters 'O' through 'Z'
    18, // Letters 'O' through 'Z'
    19, // Letters 'O' through 'Z'
    20, // Letters 'O' through 'Z'
    21, // Letters 'O' through 'Z'
    22, // Letters 'O' through 'Z'
    23, // Letters 'O' through 'Z'
    24, // Letters 'O' through 'Z'
    25, // Decimal 91 - 96
    -9, // Decimal 91 - 96
    -9, // Decimal 91 - 96
    -9, // Decimal 91 - 96
    -9, // Decimal 91 - 96
    -9, // Decimal 91 - 96
    -9, // Letters 'a' through 'm'
    26, // Letters 'a' through 'm'
    27, // Letters 'a' through 'm'
    28, // Letters 'a' through 'm'
    29, // Letters 'a' through 'm'
    30, // Letters 'a' through 'm'
    31, // Letters 'a' through 'm'
    32, // Letters 'a' through 'm'
    33, // Letters 'a' through 'm'
    34, // Letters 'a' through 'm'
    35, // Letters 'a' through 'm'
    36, // Letters 'a' through 'm'
    37, // Letters 'a' through 'm'
    38, // Letters 'n' through 'z'
    39, // Letters 'n' through 'z'
    40, // Letters 'n' through 'z'
    41, // Letters 'n' through 'z'
    42, // Letters 'n' through 'z'
    43, // Letters 'n' through 'z'
    44, // Letters 'n' through 'z'
    45, // Letters 'n' through 'z'
    46, // Letters 'n' through 'z'
    47, // Letters 'n' through 'z'
    48, // Letters 'n' through 'z'
    49, // Letters 'n' through 'z'
    50, // Letters 'n' through 'z'
    51, // Decimal 123 - 127
    -9, // Decimal 123 - 127
    -9, // Decimal 123 - 127
    -9, // Decimal 123 - 127
    -9, // Decimal 123 - 127
    -9, // Decimal 128 - 139
    -9, // Decimal 128 - 139
    -9, // Decimal 128 - 139
    -9, // Decimal 128 - 139
    -9, // Decimal 128 - 139
    -9, // Decimal 128 - 139
    -9, // Decimal 128 - 139
    -9, // Decimal 128 - 139
    -9, // Decimal 128 - 139
    -9, // Decimal 128 - 139
    -9, // Decimal 128 - 139
    -9, // Decimal 128 - 139
    -9, // Decimal 140 - 152
    -9, // Decimal 140 - 152
    -9, // Decimal 140 - 152
    -9, // Decimal 140 - 152
    -9, // Decimal 140 - 152
    -9, // Decimal 140 - 152
    -9, // Decimal 140 - 152
    -9, // Decimal 140 - 152
    -9, // Decimal 140 - 152
    -9, // Decimal 140 - 152
    -9, // Decimal 140 - 152
    -9, // Decimal 140 - 152
    -9, // Decimal 140 - 152
    -9, // Decimal 153 - 165
    -9, // Decimal 153 - 165
    -9, // Decimal 153 - 165
    -9, // Decimal 153 - 165
    -9, // Decimal 153 - 165
    -9, // Decimal 153 - 165
    -9, // Decimal 153 - 165
    -9, // Decimal 153 - 165
    -9, // Decimal 153 - 165
    -9, // Decimal 153 - 165
    -9, // Decimal 153 - 165
    -9, // Decimal 153 - 165
    -9, // Decimal 153 - 165
    -9, // Decimal 166 - 178
    -9, // Decimal 166 - 178
    -9, // Decimal 166 - 178
    -9, // Decimal 166 - 178
    -9, // Decimal 166 - 178
    -9, // Decimal 166 - 178
    -9, // Decimal 166 - 178
    -9, // Decimal 166 - 178
    -9, // Decimal 166 - 178
    -9, // Decimal 166 - 178
    -9, // Decimal 166 - 178
    -9, // Decimal 166 - 178
    -9, // Decimal 166 - 178
    -9, // Decimal 179 - 191
    -9, // Decimal 179 - 191
    -9, // Decimal 179 - 191
    -9, // Decimal 179 - 191
    -9, // Decimal 179 - 191
    -9, // Decimal 179 - 191
    -9, // Decimal 179 - 191
    -9, // Decimal 179 - 191
    -9, // Decimal 179 - 191
    -9, // Decimal 179 - 191
    -9, // Decimal 179 - 191
    -9, // Decimal 179 - 191
    -9, // Decimal 179 - 191
    -9, // Decimal 192 - 204
    -9, // Decimal 192 - 204
    -9, // Decimal 192 - 204
    -9, // Decimal 192 - 204
    -9, // Decimal 192 - 204
    -9, // Decimal 192 - 204
    -9, // Decimal 192 - 204
    -9, // Decimal 192 - 204
    -9, // Decimal 192 - 204
    -9, // Decimal 192 - 204
    -9, // Decimal 192 - 204
    -9, // Decimal 192 - 204
    -9, // Decimal 192 - 204
    -9, // Decimal 205 - 217
    -9, // Decimal 205 - 217
    -9, // Decimal 205 - 217
    -9, // Decimal 205 - 217
    -9, // Decimal 205 - 217
    -9, // Decimal 205 - 217
    -9, // Decimal 205 - 217
    -9, // Decimal 205 - 217
    -9, // Decimal 205 - 217
    -9, // Decimal 205 - 217
    -9, // Decimal 205 - 217
    -9, // Decimal 205 - 217
    -9, // Decimal 205 - 217
    -9, // Decimal 218 - 230
    -9, // Decimal 218 - 230
    -9, // Decimal 218 - 230
    -9, // Decimal 218 - 230
    -9, // Decimal 218 - 230
    -9, // Decimal 218 - 230
    -9, // Decimal 218 - 230
    -9, // Decimal 218 - 230
    -9, // Decimal 218 - 230
    -9, // Decimal 218 - 230
    -9, // Decimal 218 - 230
    -9, // Decimal 218 - 230
    -9, // Decimal 218 - 230
    -9, // Decimal 231 - 243
    -9, // Decimal 231 - 243
    -9, // Decimal 231 - 243
    -9, // Decimal 231 - 243
    -9, // Decimal 231 - 243
    -9, // Decimal 231 - 243
    -9, // Decimal 231 - 243
    -9, // Decimal 231 - 243
    -9, // Decimal 231 - 243
    -9, // Decimal 231 - 243
    -9, // Decimal 231 - 243
    -9, // Decimal 231 - 243
    -9, // Decimal 231 - 243
    -9, // Decimal 244 - 255
    -9, // Decimal 244 - 255
    -9, // Decimal 244 - 255
    -9, // Decimal 244 - 255
    -9, // Decimal 244 - 255
    -9, // Decimal 244 - 255
    -9, // Decimal 244 - 255
    -9, // Decimal 244 - 255
    -9, // Decimal 244 - 255
    -9, // Decimal 244 - 255
    -9, // Decimal 244 - 255
    -9, // Decimal 244 - 255
    -9 };

    /**
     * Defeats instantiation.
     */
    private Base64() {
    }

    /**
     *  <p>Encodes up to three bytes of the array <var>source</var>
     *  and writes the resulting four Base64 bytes to <var>destination</var>.
     *  The source and destination arrays can be manipulated
     *  anywhere along their length by specifying
     *  <var>srcOffset</var> and <var>destOffset</var>.
     *  This method does not check to make sure your arrays
     *  are large enough to accomodate <var>srcOffset</var> + 3 for
     *  the <var>source</var> array or <var>destOffset</var> + 4 for
     *  the <var>destination</var> array.
     *  The actual number of significant bytes in your array is
     *  given by <var>numSigBytes</var>.</p>
     *  <p>This is the lowest level of the encoding methods with
     *  all possible parameters.</p>
     *
     *  @param source the array to convert
     *  @param srcOffset the index where conversion begins
     *  @param numSigBytes the number of significant bytes in your array
     *  @param destination the array to hold the conversion
     *  @param destOffset the index where output will be put
     *  @since 1.3
     */
    private static void encode3to4(byte[] source, int srcOffset, int numSigBytes, byte[] destination, int destOffset) {
        // when Java treats a value as negative that is cast from a byte to an int.
        int inBuff = ((ListenerUtil.mutListener.listen(67704) ? (numSigBytes >= 0) : (ListenerUtil.mutListener.listen(67703) ? (numSigBytes <= 0) : (ListenerUtil.mutListener.listen(67702) ? (numSigBytes < 0) : (ListenerUtil.mutListener.listen(67701) ? (numSigBytes != 0) : (ListenerUtil.mutListener.listen(67700) ? (numSigBytes == 0) : (numSigBytes > 0)))))) ? ((source[srcOffset] << 24) >>> 8) : 0) | ((ListenerUtil.mutListener.listen(67709) ? (numSigBytes >= 1) : (ListenerUtil.mutListener.listen(67708) ? (numSigBytes <= 1) : (ListenerUtil.mutListener.listen(67707) ? (numSigBytes < 1) : (ListenerUtil.mutListener.listen(67706) ? (numSigBytes != 1) : (ListenerUtil.mutListener.listen(67705) ? (numSigBytes == 1) : (numSigBytes > 1)))))) ? ((source[(ListenerUtil.mutListener.listen(67713) ? (srcOffset % 1) : (ListenerUtil.mutListener.listen(67712) ? (srcOffset / 1) : (ListenerUtil.mutListener.listen(67711) ? (srcOffset * 1) : (ListenerUtil.mutListener.listen(67710) ? (srcOffset - 1) : (srcOffset + 1)))))] << 24) >>> 16) : 0) | ((ListenerUtil.mutListener.listen(67718) ? (numSigBytes >= 2) : (ListenerUtil.mutListener.listen(67717) ? (numSigBytes <= 2) : (ListenerUtil.mutListener.listen(67716) ? (numSigBytes < 2) : (ListenerUtil.mutListener.listen(67715) ? (numSigBytes != 2) : (ListenerUtil.mutListener.listen(67714) ? (numSigBytes == 2) : (numSigBytes > 2)))))) ? ((source[(ListenerUtil.mutListener.listen(67722) ? (srcOffset % 2) : (ListenerUtil.mutListener.listen(67721) ? (srcOffset / 2) : (ListenerUtil.mutListener.listen(67720) ? (srcOffset * 2) : (ListenerUtil.mutListener.listen(67719) ? (srcOffset - 2) : (srcOffset + 2)))))] << 24) >>> 24) : 0);
        if (!ListenerUtil.mutListener.listen(67771)) {
            switch(numSigBytes) {
                case 3:
                    if (!ListenerUtil.mutListener.listen(67723)) {
                        destination[destOffset] = ALPHABET[(inBuff >>> 18)];
                    }
                    if (!ListenerUtil.mutListener.listen(67728)) {
                        destination[(ListenerUtil.mutListener.listen(67727) ? (destOffset % 1) : (ListenerUtil.mutListener.listen(67726) ? (destOffset / 1) : (ListenerUtil.mutListener.listen(67725) ? (destOffset * 1) : (ListenerUtil.mutListener.listen(67724) ? (destOffset - 1) : (destOffset + 1)))))] = ALPHABET[(inBuff >>> 12) & 0x3f];
                    }
                    if (!ListenerUtil.mutListener.listen(67733)) {
                        destination[(ListenerUtil.mutListener.listen(67732) ? (destOffset % 2) : (ListenerUtil.mutListener.listen(67731) ? (destOffset / 2) : (ListenerUtil.mutListener.listen(67730) ? (destOffset * 2) : (ListenerUtil.mutListener.listen(67729) ? (destOffset - 2) : (destOffset + 2)))))] = ALPHABET[(inBuff >>> 6) & 0x3f];
                    }
                    if (!ListenerUtil.mutListener.listen(67738)) {
                        destination[(ListenerUtil.mutListener.listen(67737) ? (destOffset % 3) : (ListenerUtil.mutListener.listen(67736) ? (destOffset / 3) : (ListenerUtil.mutListener.listen(67735) ? (destOffset * 3) : (ListenerUtil.mutListener.listen(67734) ? (destOffset - 3) : (destOffset + 3)))))] = ALPHABET[(inBuff) & 0x3f];
                    }
                    break;
                case 2:
                    if (!ListenerUtil.mutListener.listen(67739)) {
                        destination[destOffset] = ALPHABET[(inBuff >>> 18)];
                    }
                    if (!ListenerUtil.mutListener.listen(67744)) {
                        destination[(ListenerUtil.mutListener.listen(67743) ? (destOffset % 1) : (ListenerUtil.mutListener.listen(67742) ? (destOffset / 1) : (ListenerUtil.mutListener.listen(67741) ? (destOffset * 1) : (ListenerUtil.mutListener.listen(67740) ? (destOffset - 1) : (destOffset + 1)))))] = ALPHABET[(inBuff >>> 12) & 0x3f];
                    }
                    if (!ListenerUtil.mutListener.listen(67749)) {
                        destination[(ListenerUtil.mutListener.listen(67748) ? (destOffset % 2) : (ListenerUtil.mutListener.listen(67747) ? (destOffset / 2) : (ListenerUtil.mutListener.listen(67746) ? (destOffset * 2) : (ListenerUtil.mutListener.listen(67745) ? (destOffset - 2) : (destOffset + 2)))))] = ALPHABET[(inBuff >>> 6) & 0x3f];
                    }
                    if (!ListenerUtil.mutListener.listen(67754)) {
                        destination[(ListenerUtil.mutListener.listen(67753) ? (destOffset % 3) : (ListenerUtil.mutListener.listen(67752) ? (destOffset / 3) : (ListenerUtil.mutListener.listen(67751) ? (destOffset * 3) : (ListenerUtil.mutListener.listen(67750) ? (destOffset - 3) : (destOffset + 3)))))] = EQUALS_SIGN;
                    }
                    break;
                case 1:
                    if (!ListenerUtil.mutListener.listen(67755)) {
                        destination[destOffset] = ALPHABET[(inBuff >>> 18)];
                    }
                    if (!ListenerUtil.mutListener.listen(67760)) {
                        destination[(ListenerUtil.mutListener.listen(67759) ? (destOffset % 1) : (ListenerUtil.mutListener.listen(67758) ? (destOffset / 1) : (ListenerUtil.mutListener.listen(67757) ? (destOffset * 1) : (ListenerUtil.mutListener.listen(67756) ? (destOffset - 1) : (destOffset + 1)))))] = ALPHABET[(inBuff >>> 12) & 0x3f];
                    }
                    if (!ListenerUtil.mutListener.listen(67765)) {
                        destination[(ListenerUtil.mutListener.listen(67764) ? (destOffset % 2) : (ListenerUtil.mutListener.listen(67763) ? (destOffset / 2) : (ListenerUtil.mutListener.listen(67762) ? (destOffset * 2) : (ListenerUtil.mutListener.listen(67761) ? (destOffset - 2) : (destOffset + 2)))))] = EQUALS_SIGN;
                    }
                    if (!ListenerUtil.mutListener.listen(67770)) {
                        destination[(ListenerUtil.mutListener.listen(67769) ? (destOffset % 3) : (ListenerUtil.mutListener.listen(67768) ? (destOffset / 3) : (ListenerUtil.mutListener.listen(67767) ? (destOffset * 3) : (ListenerUtil.mutListener.listen(67766) ? (destOffset - 3) : (destOffset + 3)))))] = EQUALS_SIGN;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     *  Encodes a byte array into Base64 notation.
     *
     *  @param source The data to convert
     *  @return The data in Base64-encoded form
     *  @throws NullPointerException if source array is null
     *  @since 1.4
     */
    public static String encodeBytes(byte[] source) {
        return encodeBytes(source, 0, source.length);
    }

    /**
     *  Encodes a byte array into Base64 notation.
     *
     *  @param source The data to convert
     *  @param off Offset in array where conversion should begin
     *  @param len Length of data to convert
     *  @return The Base64-encoded data as a String
     *  @throws NullPointerException if source array is null
     *  @throws IllegalArgumentException if source array, offset, or length are invalid
     *  @since 1.4
     */
    private static String encodeBytes(byte[] source, int off, int len) {
        byte[] encoded = encodeBytesToBytes(source, off, len);
        return new String(encoded, StandardCharsets.US_ASCII);
    }

    /**
     *  Similar to {@link #encodeBytes(byte[], int, int)} but returns
     *  a byte array instead of instantiating a String. This is more efficient
     *  if you're working with I/O streams and have large data sets to encode.
     *
     *  @param source The data to convert
     *  @param off Offset in array where conversion should begin
     *  @param len Length of data to convert
     *  @return The Base64-encoded data as a String
     *  @throws NullPointerException if source array is null
     *  @throws IllegalArgumentException if source array, offset, or length are invalid
     *  @since 2.3.1
     */
    private static byte[] encodeBytesToBytes(byte[] source, int off, int len) {
        if (!ListenerUtil.mutListener.listen(67772)) {
            if (source == null) {
                throw new NullPointerException("Cannot serialize a null array.");
            }
        }
        if (!ListenerUtil.mutListener.listen(67778)) {
            if ((ListenerUtil.mutListener.listen(67777) ? (off >= 0) : (ListenerUtil.mutListener.listen(67776) ? (off <= 0) : (ListenerUtil.mutListener.listen(67775) ? (off > 0) : (ListenerUtil.mutListener.listen(67774) ? (off != 0) : (ListenerUtil.mutListener.listen(67773) ? (off == 0) : (off < 0))))))) {
                throw new IllegalArgumentException("Cannot have negative offset: " + off);
            }
        }
        if (!ListenerUtil.mutListener.listen(67784)) {
            if ((ListenerUtil.mutListener.listen(67783) ? (len >= 0) : (ListenerUtil.mutListener.listen(67782) ? (len <= 0) : (ListenerUtil.mutListener.listen(67781) ? (len > 0) : (ListenerUtil.mutListener.listen(67780) ? (len != 0) : (ListenerUtil.mutListener.listen(67779) ? (len == 0) : (len < 0))))))) {
                throw new IllegalArgumentException("Cannot have length offset: " + len);
            }
        }
        if (!ListenerUtil.mutListener.listen(67794)) {
            if ((ListenerUtil.mutListener.listen(67793) ? ((ListenerUtil.mutListener.listen(67788) ? (off % len) : (ListenerUtil.mutListener.listen(67787) ? (off / len) : (ListenerUtil.mutListener.listen(67786) ? (off * len) : (ListenerUtil.mutListener.listen(67785) ? (off - len) : (off + len))))) >= source.length) : (ListenerUtil.mutListener.listen(67792) ? ((ListenerUtil.mutListener.listen(67788) ? (off % len) : (ListenerUtil.mutListener.listen(67787) ? (off / len) : (ListenerUtil.mutListener.listen(67786) ? (off * len) : (ListenerUtil.mutListener.listen(67785) ? (off - len) : (off + len))))) <= source.length) : (ListenerUtil.mutListener.listen(67791) ? ((ListenerUtil.mutListener.listen(67788) ? (off % len) : (ListenerUtil.mutListener.listen(67787) ? (off / len) : (ListenerUtil.mutListener.listen(67786) ? (off * len) : (ListenerUtil.mutListener.listen(67785) ? (off - len) : (off + len))))) < source.length) : (ListenerUtil.mutListener.listen(67790) ? ((ListenerUtil.mutListener.listen(67788) ? (off % len) : (ListenerUtil.mutListener.listen(67787) ? (off / len) : (ListenerUtil.mutListener.listen(67786) ? (off * len) : (ListenerUtil.mutListener.listen(67785) ? (off - len) : (off + len))))) != source.length) : (ListenerUtil.mutListener.listen(67789) ? ((ListenerUtil.mutListener.listen(67788) ? (off % len) : (ListenerUtil.mutListener.listen(67787) ? (off / len) : (ListenerUtil.mutListener.listen(67786) ? (off * len) : (ListenerUtil.mutListener.listen(67785) ? (off - len) : (off + len))))) == source.length) : ((ListenerUtil.mutListener.listen(67788) ? (off % len) : (ListenerUtil.mutListener.listen(67787) ? (off / len) : (ListenerUtil.mutListener.listen(67786) ? (off * len) : (ListenerUtil.mutListener.listen(67785) ? (off - len) : (off + len))))) > source.length))))))) {
                throw new IllegalArgumentException(String.format(Locale.US, "Cannot have offset of %d and length of %d with array of length %d", off, len, source.length));
            }
        }
        // Bytes needed for actual encoding
        int encLen = (ListenerUtil.mutListener.listen(67815) ? ((ListenerUtil.mutListener.listen(67802) ? (((ListenerUtil.mutListener.listen(67798) ? (len % 3) : (ListenerUtil.mutListener.listen(67797) ? (len * 3) : (ListenerUtil.mutListener.listen(67796) ? (len - 3) : (ListenerUtil.mutListener.listen(67795) ? (len + 3) : (len / 3)))))) % 4) : (ListenerUtil.mutListener.listen(67801) ? (((ListenerUtil.mutListener.listen(67798) ? (len % 3) : (ListenerUtil.mutListener.listen(67797) ? (len * 3) : (ListenerUtil.mutListener.listen(67796) ? (len - 3) : (ListenerUtil.mutListener.listen(67795) ? (len + 3) : (len / 3)))))) / 4) : (ListenerUtil.mutListener.listen(67800) ? (((ListenerUtil.mutListener.listen(67798) ? (len % 3) : (ListenerUtil.mutListener.listen(67797) ? (len * 3) : (ListenerUtil.mutListener.listen(67796) ? (len - 3) : (ListenerUtil.mutListener.listen(67795) ? (len + 3) : (len / 3)))))) - 4) : (ListenerUtil.mutListener.listen(67799) ? (((ListenerUtil.mutListener.listen(67798) ? (len % 3) : (ListenerUtil.mutListener.listen(67797) ? (len * 3) : (ListenerUtil.mutListener.listen(67796) ? (len - 3) : (ListenerUtil.mutListener.listen(67795) ? (len + 3) : (len / 3)))))) + 4) : (((ListenerUtil.mutListener.listen(67798) ? (len % 3) : (ListenerUtil.mutListener.listen(67797) ? (len * 3) : (ListenerUtil.mutListener.listen(67796) ? (len - 3) : (ListenerUtil.mutListener.listen(67795) ? (len + 3) : (len / 3)))))) * 4))))) % ((ListenerUtil.mutListener.listen(67811) ? ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) >= 0) : (ListenerUtil.mutListener.listen(67810) ? ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) <= 0) : (ListenerUtil.mutListener.listen(67809) ? ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) < 0) : (ListenerUtil.mutListener.listen(67808) ? ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) != 0) : (ListenerUtil.mutListener.listen(67807) ? ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) == 0) : ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) > 0)))))) ? 4 : 0)) : (ListenerUtil.mutListener.listen(67814) ? ((ListenerUtil.mutListener.listen(67802) ? (((ListenerUtil.mutListener.listen(67798) ? (len % 3) : (ListenerUtil.mutListener.listen(67797) ? (len * 3) : (ListenerUtil.mutListener.listen(67796) ? (len - 3) : (ListenerUtil.mutListener.listen(67795) ? (len + 3) : (len / 3)))))) % 4) : (ListenerUtil.mutListener.listen(67801) ? (((ListenerUtil.mutListener.listen(67798) ? (len % 3) : (ListenerUtil.mutListener.listen(67797) ? (len * 3) : (ListenerUtil.mutListener.listen(67796) ? (len - 3) : (ListenerUtil.mutListener.listen(67795) ? (len + 3) : (len / 3)))))) / 4) : (ListenerUtil.mutListener.listen(67800) ? (((ListenerUtil.mutListener.listen(67798) ? (len % 3) : (ListenerUtil.mutListener.listen(67797) ? (len * 3) : (ListenerUtil.mutListener.listen(67796) ? (len - 3) : (ListenerUtil.mutListener.listen(67795) ? (len + 3) : (len / 3)))))) - 4) : (ListenerUtil.mutListener.listen(67799) ? (((ListenerUtil.mutListener.listen(67798) ? (len % 3) : (ListenerUtil.mutListener.listen(67797) ? (len * 3) : (ListenerUtil.mutListener.listen(67796) ? (len - 3) : (ListenerUtil.mutListener.listen(67795) ? (len + 3) : (len / 3)))))) + 4) : (((ListenerUtil.mutListener.listen(67798) ? (len % 3) : (ListenerUtil.mutListener.listen(67797) ? (len * 3) : (ListenerUtil.mutListener.listen(67796) ? (len - 3) : (ListenerUtil.mutListener.listen(67795) ? (len + 3) : (len / 3)))))) * 4))))) / ((ListenerUtil.mutListener.listen(67811) ? ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) >= 0) : (ListenerUtil.mutListener.listen(67810) ? ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) <= 0) : (ListenerUtil.mutListener.listen(67809) ? ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) < 0) : (ListenerUtil.mutListener.listen(67808) ? ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) != 0) : (ListenerUtil.mutListener.listen(67807) ? ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) == 0) : ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) > 0)))))) ? 4 : 0)) : (ListenerUtil.mutListener.listen(67813) ? ((ListenerUtil.mutListener.listen(67802) ? (((ListenerUtil.mutListener.listen(67798) ? (len % 3) : (ListenerUtil.mutListener.listen(67797) ? (len * 3) : (ListenerUtil.mutListener.listen(67796) ? (len - 3) : (ListenerUtil.mutListener.listen(67795) ? (len + 3) : (len / 3)))))) % 4) : (ListenerUtil.mutListener.listen(67801) ? (((ListenerUtil.mutListener.listen(67798) ? (len % 3) : (ListenerUtil.mutListener.listen(67797) ? (len * 3) : (ListenerUtil.mutListener.listen(67796) ? (len - 3) : (ListenerUtil.mutListener.listen(67795) ? (len + 3) : (len / 3)))))) / 4) : (ListenerUtil.mutListener.listen(67800) ? (((ListenerUtil.mutListener.listen(67798) ? (len % 3) : (ListenerUtil.mutListener.listen(67797) ? (len * 3) : (ListenerUtil.mutListener.listen(67796) ? (len - 3) : (ListenerUtil.mutListener.listen(67795) ? (len + 3) : (len / 3)))))) - 4) : (ListenerUtil.mutListener.listen(67799) ? (((ListenerUtil.mutListener.listen(67798) ? (len % 3) : (ListenerUtil.mutListener.listen(67797) ? (len * 3) : (ListenerUtil.mutListener.listen(67796) ? (len - 3) : (ListenerUtil.mutListener.listen(67795) ? (len + 3) : (len / 3)))))) + 4) : (((ListenerUtil.mutListener.listen(67798) ? (len % 3) : (ListenerUtil.mutListener.listen(67797) ? (len * 3) : (ListenerUtil.mutListener.listen(67796) ? (len - 3) : (ListenerUtil.mutListener.listen(67795) ? (len + 3) : (len / 3)))))) * 4))))) * ((ListenerUtil.mutListener.listen(67811) ? ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) >= 0) : (ListenerUtil.mutListener.listen(67810) ? ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) <= 0) : (ListenerUtil.mutListener.listen(67809) ? ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) < 0) : (ListenerUtil.mutListener.listen(67808) ? ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) != 0) : (ListenerUtil.mutListener.listen(67807) ? ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) == 0) : ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) > 0)))))) ? 4 : 0)) : (ListenerUtil.mutListener.listen(67812) ? ((ListenerUtil.mutListener.listen(67802) ? (((ListenerUtil.mutListener.listen(67798) ? (len % 3) : (ListenerUtil.mutListener.listen(67797) ? (len * 3) : (ListenerUtil.mutListener.listen(67796) ? (len - 3) : (ListenerUtil.mutListener.listen(67795) ? (len + 3) : (len / 3)))))) % 4) : (ListenerUtil.mutListener.listen(67801) ? (((ListenerUtil.mutListener.listen(67798) ? (len % 3) : (ListenerUtil.mutListener.listen(67797) ? (len * 3) : (ListenerUtil.mutListener.listen(67796) ? (len - 3) : (ListenerUtil.mutListener.listen(67795) ? (len + 3) : (len / 3)))))) / 4) : (ListenerUtil.mutListener.listen(67800) ? (((ListenerUtil.mutListener.listen(67798) ? (len % 3) : (ListenerUtil.mutListener.listen(67797) ? (len * 3) : (ListenerUtil.mutListener.listen(67796) ? (len - 3) : (ListenerUtil.mutListener.listen(67795) ? (len + 3) : (len / 3)))))) - 4) : (ListenerUtil.mutListener.listen(67799) ? (((ListenerUtil.mutListener.listen(67798) ? (len % 3) : (ListenerUtil.mutListener.listen(67797) ? (len * 3) : (ListenerUtil.mutListener.listen(67796) ? (len - 3) : (ListenerUtil.mutListener.listen(67795) ? (len + 3) : (len / 3)))))) + 4) : (((ListenerUtil.mutListener.listen(67798) ? (len % 3) : (ListenerUtil.mutListener.listen(67797) ? (len * 3) : (ListenerUtil.mutListener.listen(67796) ? (len - 3) : (ListenerUtil.mutListener.listen(67795) ? (len + 3) : (len / 3)))))) * 4))))) - ((ListenerUtil.mutListener.listen(67811) ? ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) >= 0) : (ListenerUtil.mutListener.listen(67810) ? ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) <= 0) : (ListenerUtil.mutListener.listen(67809) ? ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) < 0) : (ListenerUtil.mutListener.listen(67808) ? ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) != 0) : (ListenerUtil.mutListener.listen(67807) ? ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) == 0) : ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) > 0)))))) ? 4 : 0)) : ((ListenerUtil.mutListener.listen(67802) ? (((ListenerUtil.mutListener.listen(67798) ? (len % 3) : (ListenerUtil.mutListener.listen(67797) ? (len * 3) : (ListenerUtil.mutListener.listen(67796) ? (len - 3) : (ListenerUtil.mutListener.listen(67795) ? (len + 3) : (len / 3)))))) % 4) : (ListenerUtil.mutListener.listen(67801) ? (((ListenerUtil.mutListener.listen(67798) ? (len % 3) : (ListenerUtil.mutListener.listen(67797) ? (len * 3) : (ListenerUtil.mutListener.listen(67796) ? (len - 3) : (ListenerUtil.mutListener.listen(67795) ? (len + 3) : (len / 3)))))) / 4) : (ListenerUtil.mutListener.listen(67800) ? (((ListenerUtil.mutListener.listen(67798) ? (len % 3) : (ListenerUtil.mutListener.listen(67797) ? (len * 3) : (ListenerUtil.mutListener.listen(67796) ? (len - 3) : (ListenerUtil.mutListener.listen(67795) ? (len + 3) : (len / 3)))))) - 4) : (ListenerUtil.mutListener.listen(67799) ? (((ListenerUtil.mutListener.listen(67798) ? (len % 3) : (ListenerUtil.mutListener.listen(67797) ? (len * 3) : (ListenerUtil.mutListener.listen(67796) ? (len - 3) : (ListenerUtil.mutListener.listen(67795) ? (len + 3) : (len / 3)))))) + 4) : (((ListenerUtil.mutListener.listen(67798) ? (len % 3) : (ListenerUtil.mutListener.listen(67797) ? (len * 3) : (ListenerUtil.mutListener.listen(67796) ? (len - 3) : (ListenerUtil.mutListener.listen(67795) ? (len + 3) : (len / 3)))))) * 4))))) + ((ListenerUtil.mutListener.listen(67811) ? ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) >= 0) : (ListenerUtil.mutListener.listen(67810) ? ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) <= 0) : (ListenerUtil.mutListener.listen(67809) ? ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) < 0) : (ListenerUtil.mutListener.listen(67808) ? ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) != 0) : (ListenerUtil.mutListener.listen(67807) ? ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) == 0) : ((ListenerUtil.mutListener.listen(67806) ? (len / 3) : (ListenerUtil.mutListener.listen(67805) ? (len * 3) : (ListenerUtil.mutListener.listen(67804) ? (len - 3) : (ListenerUtil.mutListener.listen(67803) ? (len + 3) : (len % 3))))) > 0)))))) ? 4 : 0))))));
        byte[] outBuff = new byte[encLen];
        int d = 0;
        int e = 0;
        int len2 = (ListenerUtil.mutListener.listen(67819) ? (len % 2) : (ListenerUtil.mutListener.listen(67818) ? (len / 2) : (ListenerUtil.mutListener.listen(67817) ? (len * 2) : (ListenerUtil.mutListener.listen(67816) ? (len + 2) : (len - 2)))));
        if (!ListenerUtil.mutListener.listen(67830)) {
            {
                long _loopCounter849 = 0;
                for (; (ListenerUtil.mutListener.listen(67829) ? (d >= len2) : (ListenerUtil.mutListener.listen(67828) ? (d <= len2) : (ListenerUtil.mutListener.listen(67827) ? (d > len2) : (ListenerUtil.mutListener.listen(67826) ? (d != len2) : (ListenerUtil.mutListener.listen(67825) ? (d == len2) : (d < len2)))))); d += 3, e += 4) {
                    ListenerUtil.loopListener.listen("_loopCounter849", ++_loopCounter849);
                    if (!ListenerUtil.mutListener.listen(67824)) {
                        encode3to4(source, (ListenerUtil.mutListener.listen(67823) ? (d % off) : (ListenerUtil.mutListener.listen(67822) ? (d / off) : (ListenerUtil.mutListener.listen(67821) ? (d * off) : (ListenerUtil.mutListener.listen(67820) ? (d - off) : (d + off))))), 3, outBuff, e);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(67846)) {
            if ((ListenerUtil.mutListener.listen(67835) ? (d >= len) : (ListenerUtil.mutListener.listen(67834) ? (d <= len) : (ListenerUtil.mutListener.listen(67833) ? (d > len) : (ListenerUtil.mutListener.listen(67832) ? (d != len) : (ListenerUtil.mutListener.listen(67831) ? (d == len) : (d < len))))))) {
                if (!ListenerUtil.mutListener.listen(67844)) {
                    encode3to4(source, (ListenerUtil.mutListener.listen(67839) ? (d % off) : (ListenerUtil.mutListener.listen(67838) ? (d / off) : (ListenerUtil.mutListener.listen(67837) ? (d * off) : (ListenerUtil.mutListener.listen(67836) ? (d - off) : (d + off))))), (ListenerUtil.mutListener.listen(67843) ? (len % d) : (ListenerUtil.mutListener.listen(67842) ? (len / d) : (ListenerUtil.mutListener.listen(67841) ? (len * d) : (ListenerUtil.mutListener.listen(67840) ? (len + d) : (len - d))))), outBuff, e);
                }
                if (!ListenerUtil.mutListener.listen(67845)) {
                    e += 4;
                }
            }
        }
        // Only resize array if we didn't guess it right.
        if ((ListenerUtil.mutListener.listen(67855) ? (e >= (ListenerUtil.mutListener.listen(67850) ? (outBuff.length % 1) : (ListenerUtil.mutListener.listen(67849) ? (outBuff.length / 1) : (ListenerUtil.mutListener.listen(67848) ? (outBuff.length * 1) : (ListenerUtil.mutListener.listen(67847) ? (outBuff.length + 1) : (outBuff.length - 1)))))) : (ListenerUtil.mutListener.listen(67854) ? (e > (ListenerUtil.mutListener.listen(67850) ? (outBuff.length % 1) : (ListenerUtil.mutListener.listen(67849) ? (outBuff.length / 1) : (ListenerUtil.mutListener.listen(67848) ? (outBuff.length * 1) : (ListenerUtil.mutListener.listen(67847) ? (outBuff.length + 1) : (outBuff.length - 1)))))) : (ListenerUtil.mutListener.listen(67853) ? (e < (ListenerUtil.mutListener.listen(67850) ? (outBuff.length % 1) : (ListenerUtil.mutListener.listen(67849) ? (outBuff.length / 1) : (ListenerUtil.mutListener.listen(67848) ? (outBuff.length * 1) : (ListenerUtil.mutListener.listen(67847) ? (outBuff.length + 1) : (outBuff.length - 1)))))) : (ListenerUtil.mutListener.listen(67852) ? (e != (ListenerUtil.mutListener.listen(67850) ? (outBuff.length % 1) : (ListenerUtil.mutListener.listen(67849) ? (outBuff.length / 1) : (ListenerUtil.mutListener.listen(67848) ? (outBuff.length * 1) : (ListenerUtil.mutListener.listen(67847) ? (outBuff.length + 1) : (outBuff.length - 1)))))) : (ListenerUtil.mutListener.listen(67851) ? (e == (ListenerUtil.mutListener.listen(67850) ? (outBuff.length % 1) : (ListenerUtil.mutListener.listen(67849) ? (outBuff.length / 1) : (ListenerUtil.mutListener.listen(67848) ? (outBuff.length * 1) : (ListenerUtil.mutListener.listen(67847) ? (outBuff.length + 1) : (outBuff.length - 1)))))) : (e <= (ListenerUtil.mutListener.listen(67850) ? (outBuff.length % 1) : (ListenerUtil.mutListener.listen(67849) ? (outBuff.length / 1) : (ListenerUtil.mutListener.listen(67848) ? (outBuff.length * 1) : (ListenerUtil.mutListener.listen(67847) ? (outBuff.length + 1) : (outBuff.length - 1)))))))))))) {
            // Not too bad of an estimate on array size, I'd say.
            byte[] finalOut = new byte[e];
            if (!ListenerUtil.mutListener.listen(67856)) {
                System.arraycopy(outBuff, 0, finalOut, 0, e);
            }
            return finalOut;
        } else {
            return outBuff;
        }
    }

    /**
     *  Decodes four bytes from array <var>source</var>
     *  and writes the resulting bytes (up to three of them)
     *  to <var>destination</var>.
     *  The source and destination arrays can be manipulated
     *  anywhere along their length by specifying
     *  <var>srcOffset</var> and <var>destOffset</var>.
     *  This method does not check to make sure your arrays
     *  are large enough to accomodate <var>srcOffset</var> + 4 for
     *  the <var>source</var> array or <var>destOffset</var> + 3 for
     *  the <var>destination</var> array.
     *  This method returns the actual number of bytes that
     *  were converted from the Base64 encoding.
     *  <p>This is the lowest level of the decoding methods with
     *  all possible parameters.</p>
     *
     *  @param source the array to convert
     *  @param srcOffset the index where conversion begins
     *  @param destination the array to hold the conversion
     *  @param destOffset the index where output will be put
     *  @return the number of decoded bytes converted
     *  @throws NullPointerException if source or destination arrays are null
     *  @throws IllegalArgumentException if srcOffset or destOffset are invalid
     *          or there is not enough room in the array.
     *  @since 1.3
     */
    private static int decode4to3(byte[] source, int srcOffset, byte[] destination, int destOffset) {
        if (!ListenerUtil.mutListener.listen(67857)) {
            // Lots of error checking and exception throwing
            if (source == null) {
                throw new NullPointerException("Source array was null.");
            }
        }
        if (!ListenerUtil.mutListener.listen(67858)) {
            if (destination == null) {
                throw new NullPointerException("Destination array was null.");
            }
        }
        if (!ListenerUtil.mutListener.listen(67874)) {
            if ((ListenerUtil.mutListener.listen(67873) ? ((ListenerUtil.mutListener.listen(67863) ? (srcOffset >= 0) : (ListenerUtil.mutListener.listen(67862) ? (srcOffset <= 0) : (ListenerUtil.mutListener.listen(67861) ? (srcOffset > 0) : (ListenerUtil.mutListener.listen(67860) ? (srcOffset != 0) : (ListenerUtil.mutListener.listen(67859) ? (srcOffset == 0) : (srcOffset < 0)))))) && (ListenerUtil.mutListener.listen(67872) ? ((ListenerUtil.mutListener.listen(67867) ? (srcOffset % 3) : (ListenerUtil.mutListener.listen(67866) ? (srcOffset / 3) : (ListenerUtil.mutListener.listen(67865) ? (srcOffset * 3) : (ListenerUtil.mutListener.listen(67864) ? (srcOffset - 3) : (srcOffset + 3))))) <= source.length) : (ListenerUtil.mutListener.listen(67871) ? ((ListenerUtil.mutListener.listen(67867) ? (srcOffset % 3) : (ListenerUtil.mutListener.listen(67866) ? (srcOffset / 3) : (ListenerUtil.mutListener.listen(67865) ? (srcOffset * 3) : (ListenerUtil.mutListener.listen(67864) ? (srcOffset - 3) : (srcOffset + 3))))) > source.length) : (ListenerUtil.mutListener.listen(67870) ? ((ListenerUtil.mutListener.listen(67867) ? (srcOffset % 3) : (ListenerUtil.mutListener.listen(67866) ? (srcOffset / 3) : (ListenerUtil.mutListener.listen(67865) ? (srcOffset * 3) : (ListenerUtil.mutListener.listen(67864) ? (srcOffset - 3) : (srcOffset + 3))))) < source.length) : (ListenerUtil.mutListener.listen(67869) ? ((ListenerUtil.mutListener.listen(67867) ? (srcOffset % 3) : (ListenerUtil.mutListener.listen(67866) ? (srcOffset / 3) : (ListenerUtil.mutListener.listen(67865) ? (srcOffset * 3) : (ListenerUtil.mutListener.listen(67864) ? (srcOffset - 3) : (srcOffset + 3))))) != source.length) : (ListenerUtil.mutListener.listen(67868) ? ((ListenerUtil.mutListener.listen(67867) ? (srcOffset % 3) : (ListenerUtil.mutListener.listen(67866) ? (srcOffset / 3) : (ListenerUtil.mutListener.listen(67865) ? (srcOffset * 3) : (ListenerUtil.mutListener.listen(67864) ? (srcOffset - 3) : (srcOffset + 3))))) == source.length) : ((ListenerUtil.mutListener.listen(67867) ? (srcOffset % 3) : (ListenerUtil.mutListener.listen(67866) ? (srcOffset / 3) : (ListenerUtil.mutListener.listen(67865) ? (srcOffset * 3) : (ListenerUtil.mutListener.listen(67864) ? (srcOffset - 3) : (srcOffset + 3))))) >= source.length))))))) : ((ListenerUtil.mutListener.listen(67863) ? (srcOffset >= 0) : (ListenerUtil.mutListener.listen(67862) ? (srcOffset <= 0) : (ListenerUtil.mutListener.listen(67861) ? (srcOffset > 0) : (ListenerUtil.mutListener.listen(67860) ? (srcOffset != 0) : (ListenerUtil.mutListener.listen(67859) ? (srcOffset == 0) : (srcOffset < 0)))))) || (ListenerUtil.mutListener.listen(67872) ? ((ListenerUtil.mutListener.listen(67867) ? (srcOffset % 3) : (ListenerUtil.mutListener.listen(67866) ? (srcOffset / 3) : (ListenerUtil.mutListener.listen(67865) ? (srcOffset * 3) : (ListenerUtil.mutListener.listen(67864) ? (srcOffset - 3) : (srcOffset + 3))))) <= source.length) : (ListenerUtil.mutListener.listen(67871) ? ((ListenerUtil.mutListener.listen(67867) ? (srcOffset % 3) : (ListenerUtil.mutListener.listen(67866) ? (srcOffset / 3) : (ListenerUtil.mutListener.listen(67865) ? (srcOffset * 3) : (ListenerUtil.mutListener.listen(67864) ? (srcOffset - 3) : (srcOffset + 3))))) > source.length) : (ListenerUtil.mutListener.listen(67870) ? ((ListenerUtil.mutListener.listen(67867) ? (srcOffset % 3) : (ListenerUtil.mutListener.listen(67866) ? (srcOffset / 3) : (ListenerUtil.mutListener.listen(67865) ? (srcOffset * 3) : (ListenerUtil.mutListener.listen(67864) ? (srcOffset - 3) : (srcOffset + 3))))) < source.length) : (ListenerUtil.mutListener.listen(67869) ? ((ListenerUtil.mutListener.listen(67867) ? (srcOffset % 3) : (ListenerUtil.mutListener.listen(67866) ? (srcOffset / 3) : (ListenerUtil.mutListener.listen(67865) ? (srcOffset * 3) : (ListenerUtil.mutListener.listen(67864) ? (srcOffset - 3) : (srcOffset + 3))))) != source.length) : (ListenerUtil.mutListener.listen(67868) ? ((ListenerUtil.mutListener.listen(67867) ? (srcOffset % 3) : (ListenerUtil.mutListener.listen(67866) ? (srcOffset / 3) : (ListenerUtil.mutListener.listen(67865) ? (srcOffset * 3) : (ListenerUtil.mutListener.listen(67864) ? (srcOffset - 3) : (srcOffset + 3))))) == source.length) : ((ListenerUtil.mutListener.listen(67867) ? (srcOffset % 3) : (ListenerUtil.mutListener.listen(67866) ? (srcOffset / 3) : (ListenerUtil.mutListener.listen(67865) ? (srcOffset * 3) : (ListenerUtil.mutListener.listen(67864) ? (srcOffset - 3) : (srcOffset + 3))))) >= source.length))))))))) {
                throw new IllegalArgumentException(String.format(Locale.US, "Source array with length %d cannot have offset of %d and still process four bytes.", source.length, srcOffset));
            }
        }
        if (!ListenerUtil.mutListener.listen(67890)) {
            if ((ListenerUtil.mutListener.listen(67889) ? ((ListenerUtil.mutListener.listen(67879) ? (destOffset >= 0) : (ListenerUtil.mutListener.listen(67878) ? (destOffset <= 0) : (ListenerUtil.mutListener.listen(67877) ? (destOffset > 0) : (ListenerUtil.mutListener.listen(67876) ? (destOffset != 0) : (ListenerUtil.mutListener.listen(67875) ? (destOffset == 0) : (destOffset < 0)))))) && (ListenerUtil.mutListener.listen(67888) ? ((ListenerUtil.mutListener.listen(67883) ? (destOffset % 2) : (ListenerUtil.mutListener.listen(67882) ? (destOffset / 2) : (ListenerUtil.mutListener.listen(67881) ? (destOffset * 2) : (ListenerUtil.mutListener.listen(67880) ? (destOffset - 2) : (destOffset + 2))))) <= destination.length) : (ListenerUtil.mutListener.listen(67887) ? ((ListenerUtil.mutListener.listen(67883) ? (destOffset % 2) : (ListenerUtil.mutListener.listen(67882) ? (destOffset / 2) : (ListenerUtil.mutListener.listen(67881) ? (destOffset * 2) : (ListenerUtil.mutListener.listen(67880) ? (destOffset - 2) : (destOffset + 2))))) > destination.length) : (ListenerUtil.mutListener.listen(67886) ? ((ListenerUtil.mutListener.listen(67883) ? (destOffset % 2) : (ListenerUtil.mutListener.listen(67882) ? (destOffset / 2) : (ListenerUtil.mutListener.listen(67881) ? (destOffset * 2) : (ListenerUtil.mutListener.listen(67880) ? (destOffset - 2) : (destOffset + 2))))) < destination.length) : (ListenerUtil.mutListener.listen(67885) ? ((ListenerUtil.mutListener.listen(67883) ? (destOffset % 2) : (ListenerUtil.mutListener.listen(67882) ? (destOffset / 2) : (ListenerUtil.mutListener.listen(67881) ? (destOffset * 2) : (ListenerUtil.mutListener.listen(67880) ? (destOffset - 2) : (destOffset + 2))))) != destination.length) : (ListenerUtil.mutListener.listen(67884) ? ((ListenerUtil.mutListener.listen(67883) ? (destOffset % 2) : (ListenerUtil.mutListener.listen(67882) ? (destOffset / 2) : (ListenerUtil.mutListener.listen(67881) ? (destOffset * 2) : (ListenerUtil.mutListener.listen(67880) ? (destOffset - 2) : (destOffset + 2))))) == destination.length) : ((ListenerUtil.mutListener.listen(67883) ? (destOffset % 2) : (ListenerUtil.mutListener.listen(67882) ? (destOffset / 2) : (ListenerUtil.mutListener.listen(67881) ? (destOffset * 2) : (ListenerUtil.mutListener.listen(67880) ? (destOffset - 2) : (destOffset + 2))))) >= destination.length))))))) : ((ListenerUtil.mutListener.listen(67879) ? (destOffset >= 0) : (ListenerUtil.mutListener.listen(67878) ? (destOffset <= 0) : (ListenerUtil.mutListener.listen(67877) ? (destOffset > 0) : (ListenerUtil.mutListener.listen(67876) ? (destOffset != 0) : (ListenerUtil.mutListener.listen(67875) ? (destOffset == 0) : (destOffset < 0)))))) || (ListenerUtil.mutListener.listen(67888) ? ((ListenerUtil.mutListener.listen(67883) ? (destOffset % 2) : (ListenerUtil.mutListener.listen(67882) ? (destOffset / 2) : (ListenerUtil.mutListener.listen(67881) ? (destOffset * 2) : (ListenerUtil.mutListener.listen(67880) ? (destOffset - 2) : (destOffset + 2))))) <= destination.length) : (ListenerUtil.mutListener.listen(67887) ? ((ListenerUtil.mutListener.listen(67883) ? (destOffset % 2) : (ListenerUtil.mutListener.listen(67882) ? (destOffset / 2) : (ListenerUtil.mutListener.listen(67881) ? (destOffset * 2) : (ListenerUtil.mutListener.listen(67880) ? (destOffset - 2) : (destOffset + 2))))) > destination.length) : (ListenerUtil.mutListener.listen(67886) ? ((ListenerUtil.mutListener.listen(67883) ? (destOffset % 2) : (ListenerUtil.mutListener.listen(67882) ? (destOffset / 2) : (ListenerUtil.mutListener.listen(67881) ? (destOffset * 2) : (ListenerUtil.mutListener.listen(67880) ? (destOffset - 2) : (destOffset + 2))))) < destination.length) : (ListenerUtil.mutListener.listen(67885) ? ((ListenerUtil.mutListener.listen(67883) ? (destOffset % 2) : (ListenerUtil.mutListener.listen(67882) ? (destOffset / 2) : (ListenerUtil.mutListener.listen(67881) ? (destOffset * 2) : (ListenerUtil.mutListener.listen(67880) ? (destOffset - 2) : (destOffset + 2))))) != destination.length) : (ListenerUtil.mutListener.listen(67884) ? ((ListenerUtil.mutListener.listen(67883) ? (destOffset % 2) : (ListenerUtil.mutListener.listen(67882) ? (destOffset / 2) : (ListenerUtil.mutListener.listen(67881) ? (destOffset * 2) : (ListenerUtil.mutListener.listen(67880) ? (destOffset - 2) : (destOffset + 2))))) == destination.length) : ((ListenerUtil.mutListener.listen(67883) ? (destOffset % 2) : (ListenerUtil.mutListener.listen(67882) ? (destOffset / 2) : (ListenerUtil.mutListener.listen(67881) ? (destOffset * 2) : (ListenerUtil.mutListener.listen(67880) ? (destOffset - 2) : (destOffset + 2))))) >= destination.length))))))))) {
                throw new IllegalArgumentException(String.format(Locale.US, "Destination array with length %d cannot have offset of %d and still store three bytes.", destination.length, destOffset));
            }
        }
        // Example: Dk==
        if ((ListenerUtil.mutListener.listen(67899) ? (source[(ListenerUtil.mutListener.listen(67894) ? (srcOffset % 2) : (ListenerUtil.mutListener.listen(67893) ? (srcOffset / 2) : (ListenerUtil.mutListener.listen(67892) ? (srcOffset * 2) : (ListenerUtil.mutListener.listen(67891) ? (srcOffset - 2) : (srcOffset + 2)))))] >= EQUALS_SIGN) : (ListenerUtil.mutListener.listen(67898) ? (source[(ListenerUtil.mutListener.listen(67894) ? (srcOffset % 2) : (ListenerUtil.mutListener.listen(67893) ? (srcOffset / 2) : (ListenerUtil.mutListener.listen(67892) ? (srcOffset * 2) : (ListenerUtil.mutListener.listen(67891) ? (srcOffset - 2) : (srcOffset + 2)))))] <= EQUALS_SIGN) : (ListenerUtil.mutListener.listen(67897) ? (source[(ListenerUtil.mutListener.listen(67894) ? (srcOffset % 2) : (ListenerUtil.mutListener.listen(67893) ? (srcOffset / 2) : (ListenerUtil.mutListener.listen(67892) ? (srcOffset * 2) : (ListenerUtil.mutListener.listen(67891) ? (srcOffset - 2) : (srcOffset + 2)))))] > EQUALS_SIGN) : (ListenerUtil.mutListener.listen(67896) ? (source[(ListenerUtil.mutListener.listen(67894) ? (srcOffset % 2) : (ListenerUtil.mutListener.listen(67893) ? (srcOffset / 2) : (ListenerUtil.mutListener.listen(67892) ? (srcOffset * 2) : (ListenerUtil.mutListener.listen(67891) ? (srcOffset - 2) : (srcOffset + 2)))))] < EQUALS_SIGN) : (ListenerUtil.mutListener.listen(67895) ? (source[(ListenerUtil.mutListener.listen(67894) ? (srcOffset % 2) : (ListenerUtil.mutListener.listen(67893) ? (srcOffset / 2) : (ListenerUtil.mutListener.listen(67892) ? (srcOffset * 2) : (ListenerUtil.mutListener.listen(67891) ? (srcOffset - 2) : (srcOffset + 2)))))] != EQUALS_SIGN) : (source[(ListenerUtil.mutListener.listen(67894) ? (srcOffset % 2) : (ListenerUtil.mutListener.listen(67893) ? (srcOffset / 2) : (ListenerUtil.mutListener.listen(67892) ? (srcOffset * 2) : (ListenerUtil.mutListener.listen(67891) ? (srcOffset - 2) : (srcOffset + 2)))))] == EQUALS_SIGN))))))) {
            int outBuff = ((DECODABET[source[srcOffset]] & 0xFF) << 18) | ((DECODABET[source[(ListenerUtil.mutListener.listen(67949) ? (srcOffset % 1) : (ListenerUtil.mutListener.listen(67948) ? (srcOffset / 1) : (ListenerUtil.mutListener.listen(67947) ? (srcOffset * 1) : (ListenerUtil.mutListener.listen(67946) ? (srcOffset - 1) : (srcOffset + 1)))))]] & 0xFF) << 12);
            if (!ListenerUtil.mutListener.listen(67950)) {
                destination[destOffset] = (byte) (outBuff >>> 16);
            }
            return 1;
        } else // Example: DkL=
        if ((ListenerUtil.mutListener.listen(67908) ? (source[(ListenerUtil.mutListener.listen(67903) ? (srcOffset % 3) : (ListenerUtil.mutListener.listen(67902) ? (srcOffset / 3) : (ListenerUtil.mutListener.listen(67901) ? (srcOffset * 3) : (ListenerUtil.mutListener.listen(67900) ? (srcOffset - 3) : (srcOffset + 3)))))] >= EQUALS_SIGN) : (ListenerUtil.mutListener.listen(67907) ? (source[(ListenerUtil.mutListener.listen(67903) ? (srcOffset % 3) : (ListenerUtil.mutListener.listen(67902) ? (srcOffset / 3) : (ListenerUtil.mutListener.listen(67901) ? (srcOffset * 3) : (ListenerUtil.mutListener.listen(67900) ? (srcOffset - 3) : (srcOffset + 3)))))] <= EQUALS_SIGN) : (ListenerUtil.mutListener.listen(67906) ? (source[(ListenerUtil.mutListener.listen(67903) ? (srcOffset % 3) : (ListenerUtil.mutListener.listen(67902) ? (srcOffset / 3) : (ListenerUtil.mutListener.listen(67901) ? (srcOffset * 3) : (ListenerUtil.mutListener.listen(67900) ? (srcOffset - 3) : (srcOffset + 3)))))] > EQUALS_SIGN) : (ListenerUtil.mutListener.listen(67905) ? (source[(ListenerUtil.mutListener.listen(67903) ? (srcOffset % 3) : (ListenerUtil.mutListener.listen(67902) ? (srcOffset / 3) : (ListenerUtil.mutListener.listen(67901) ? (srcOffset * 3) : (ListenerUtil.mutListener.listen(67900) ? (srcOffset - 3) : (srcOffset + 3)))))] < EQUALS_SIGN) : (ListenerUtil.mutListener.listen(67904) ? (source[(ListenerUtil.mutListener.listen(67903) ? (srcOffset % 3) : (ListenerUtil.mutListener.listen(67902) ? (srcOffset / 3) : (ListenerUtil.mutListener.listen(67901) ? (srcOffset * 3) : (ListenerUtil.mutListener.listen(67900) ? (srcOffset - 3) : (srcOffset + 3)))))] != EQUALS_SIGN) : (source[(ListenerUtil.mutListener.listen(67903) ? (srcOffset % 3) : (ListenerUtil.mutListener.listen(67902) ? (srcOffset / 3) : (ListenerUtil.mutListener.listen(67901) ? (srcOffset * 3) : (ListenerUtil.mutListener.listen(67900) ? (srcOffset - 3) : (srcOffset + 3)))))] == EQUALS_SIGN))))))) {
            int outBuff = ((DECODABET[source[srcOffset]] & 0xFF) << 18) | ((DECODABET[source[(ListenerUtil.mutListener.listen(67935) ? (srcOffset % 1) : (ListenerUtil.mutListener.listen(67934) ? (srcOffset / 1) : (ListenerUtil.mutListener.listen(67933) ? (srcOffset * 1) : (ListenerUtil.mutListener.listen(67932) ? (srcOffset - 1) : (srcOffset + 1)))))]] & 0xFF) << 12) | ((DECODABET[source[(ListenerUtil.mutListener.listen(67939) ? (srcOffset % 2) : (ListenerUtil.mutListener.listen(67938) ? (srcOffset / 2) : (ListenerUtil.mutListener.listen(67937) ? (srcOffset * 2) : (ListenerUtil.mutListener.listen(67936) ? (srcOffset - 2) : (srcOffset + 2)))))]] & 0xFF) << 6);
            if (!ListenerUtil.mutListener.listen(67940)) {
                destination[destOffset] = (byte) (outBuff >>> 16);
            }
            if (!ListenerUtil.mutListener.listen(67945)) {
                destination[(ListenerUtil.mutListener.listen(67944) ? (destOffset % 1) : (ListenerUtil.mutListener.listen(67943) ? (destOffset / 1) : (ListenerUtil.mutListener.listen(67942) ? (destOffset * 1) : (ListenerUtil.mutListener.listen(67941) ? (destOffset - 1) : (destOffset + 1)))))] = (byte) (outBuff >>> 8);
            }
            return 2;
        } else // Example: DkLE
        {
            int outBuff = ((DECODABET[source[srcOffset]] & 0xFF) << 18) | ((DECODABET[source[(ListenerUtil.mutListener.listen(67912) ? (srcOffset % 1) : (ListenerUtil.mutListener.listen(67911) ? (srcOffset / 1) : (ListenerUtil.mutListener.listen(67910) ? (srcOffset * 1) : (ListenerUtil.mutListener.listen(67909) ? (srcOffset - 1) : (srcOffset + 1)))))]] & 0xFF) << 12) | ((DECODABET[source[(ListenerUtil.mutListener.listen(67916) ? (srcOffset % 2) : (ListenerUtil.mutListener.listen(67915) ? (srcOffset / 2) : (ListenerUtil.mutListener.listen(67914) ? (srcOffset * 2) : (ListenerUtil.mutListener.listen(67913) ? (srcOffset - 2) : (srcOffset + 2)))))]] & 0xFF) << 6) | ((DECODABET[source[(ListenerUtil.mutListener.listen(67920) ? (srcOffset % 3) : (ListenerUtil.mutListener.listen(67919) ? (srcOffset / 3) : (ListenerUtil.mutListener.listen(67918) ? (srcOffset * 3) : (ListenerUtil.mutListener.listen(67917) ? (srcOffset - 3) : (srcOffset + 3)))))]] & 0xFF));
            if (!ListenerUtil.mutListener.listen(67921)) {
                destination[destOffset] = (byte) (outBuff >> 16);
            }
            if (!ListenerUtil.mutListener.listen(67926)) {
                destination[(ListenerUtil.mutListener.listen(67925) ? (destOffset % 1) : (ListenerUtil.mutListener.listen(67924) ? (destOffset / 1) : (ListenerUtil.mutListener.listen(67923) ? (destOffset * 1) : (ListenerUtil.mutListener.listen(67922) ? (destOffset - 1) : (destOffset + 1)))))] = (byte) (outBuff >> 8);
            }
            if (!ListenerUtil.mutListener.listen(67931)) {
                destination[(ListenerUtil.mutListener.listen(67930) ? (destOffset % 2) : (ListenerUtil.mutListener.listen(67929) ? (destOffset / 2) : (ListenerUtil.mutListener.listen(67928) ? (destOffset * 2) : (ListenerUtil.mutListener.listen(67927) ? (destOffset - 2) : (destOffset + 2)))))] = (byte) (outBuff);
            }
            return 3;
        }
    }

    /**
     *  Low-level access to decoding ASCII characters in
     *  the form of a byte array. <strong>Ignores GUNZIP option, if
     *  it's set.</strong> This is not generally a recommended method,
     *  although it is used internally as part of the decoding process.
     *  Special case: if len = 0, an empty array is returned. Still,
     *  if you need more speed and reduced memory footprint, consider this method.
     *
     *  @param source The Base64 encoded data
     *  @param off    The offset of where to begin decoding
     *  @param len    The length of characters to decode
     *  @return decoded data
     *  @throws java.io.IOException If bogus characters exist in source data
     *  @since 1.3
     */
    private static byte[] decode(byte[] source, int off, int len) throws java.io.IOException {
        if (!ListenerUtil.mutListener.listen(67951)) {
            // Lots of error checking and exception throwing
            if (source == null) {
                throw new NullPointerException("Cannot decode null source array.");
            }
        }
        if (!ListenerUtil.mutListener.listen(67967)) {
            // end if
            if ((ListenerUtil.mutListener.listen(67966) ? ((ListenerUtil.mutListener.listen(67956) ? (off >= 0) : (ListenerUtil.mutListener.listen(67955) ? (off <= 0) : (ListenerUtil.mutListener.listen(67954) ? (off > 0) : (ListenerUtil.mutListener.listen(67953) ? (off != 0) : (ListenerUtil.mutListener.listen(67952) ? (off == 0) : (off < 0)))))) && (ListenerUtil.mutListener.listen(67965) ? ((ListenerUtil.mutListener.listen(67960) ? (off % len) : (ListenerUtil.mutListener.listen(67959) ? (off / len) : (ListenerUtil.mutListener.listen(67958) ? (off * len) : (ListenerUtil.mutListener.listen(67957) ? (off - len) : (off + len))))) >= source.length) : (ListenerUtil.mutListener.listen(67964) ? ((ListenerUtil.mutListener.listen(67960) ? (off % len) : (ListenerUtil.mutListener.listen(67959) ? (off / len) : (ListenerUtil.mutListener.listen(67958) ? (off * len) : (ListenerUtil.mutListener.listen(67957) ? (off - len) : (off + len))))) <= source.length) : (ListenerUtil.mutListener.listen(67963) ? ((ListenerUtil.mutListener.listen(67960) ? (off % len) : (ListenerUtil.mutListener.listen(67959) ? (off / len) : (ListenerUtil.mutListener.listen(67958) ? (off * len) : (ListenerUtil.mutListener.listen(67957) ? (off - len) : (off + len))))) < source.length) : (ListenerUtil.mutListener.listen(67962) ? ((ListenerUtil.mutListener.listen(67960) ? (off % len) : (ListenerUtil.mutListener.listen(67959) ? (off / len) : (ListenerUtil.mutListener.listen(67958) ? (off * len) : (ListenerUtil.mutListener.listen(67957) ? (off - len) : (off + len))))) != source.length) : (ListenerUtil.mutListener.listen(67961) ? ((ListenerUtil.mutListener.listen(67960) ? (off % len) : (ListenerUtil.mutListener.listen(67959) ? (off / len) : (ListenerUtil.mutListener.listen(67958) ? (off * len) : (ListenerUtil.mutListener.listen(67957) ? (off - len) : (off + len))))) == source.length) : ((ListenerUtil.mutListener.listen(67960) ? (off % len) : (ListenerUtil.mutListener.listen(67959) ? (off / len) : (ListenerUtil.mutListener.listen(67958) ? (off * len) : (ListenerUtil.mutListener.listen(67957) ? (off - len) : (off + len))))) > source.length))))))) : ((ListenerUtil.mutListener.listen(67956) ? (off >= 0) : (ListenerUtil.mutListener.listen(67955) ? (off <= 0) : (ListenerUtil.mutListener.listen(67954) ? (off > 0) : (ListenerUtil.mutListener.listen(67953) ? (off != 0) : (ListenerUtil.mutListener.listen(67952) ? (off == 0) : (off < 0)))))) || (ListenerUtil.mutListener.listen(67965) ? ((ListenerUtil.mutListener.listen(67960) ? (off % len) : (ListenerUtil.mutListener.listen(67959) ? (off / len) : (ListenerUtil.mutListener.listen(67958) ? (off * len) : (ListenerUtil.mutListener.listen(67957) ? (off - len) : (off + len))))) >= source.length) : (ListenerUtil.mutListener.listen(67964) ? ((ListenerUtil.mutListener.listen(67960) ? (off % len) : (ListenerUtil.mutListener.listen(67959) ? (off / len) : (ListenerUtil.mutListener.listen(67958) ? (off * len) : (ListenerUtil.mutListener.listen(67957) ? (off - len) : (off + len))))) <= source.length) : (ListenerUtil.mutListener.listen(67963) ? ((ListenerUtil.mutListener.listen(67960) ? (off % len) : (ListenerUtil.mutListener.listen(67959) ? (off / len) : (ListenerUtil.mutListener.listen(67958) ? (off * len) : (ListenerUtil.mutListener.listen(67957) ? (off - len) : (off + len))))) < source.length) : (ListenerUtil.mutListener.listen(67962) ? ((ListenerUtil.mutListener.listen(67960) ? (off % len) : (ListenerUtil.mutListener.listen(67959) ? (off / len) : (ListenerUtil.mutListener.listen(67958) ? (off * len) : (ListenerUtil.mutListener.listen(67957) ? (off - len) : (off + len))))) != source.length) : (ListenerUtil.mutListener.listen(67961) ? ((ListenerUtil.mutListener.listen(67960) ? (off % len) : (ListenerUtil.mutListener.listen(67959) ? (off / len) : (ListenerUtil.mutListener.listen(67958) ? (off * len) : (ListenerUtil.mutListener.listen(67957) ? (off - len) : (off + len))))) == source.length) : ((ListenerUtil.mutListener.listen(67960) ? (off % len) : (ListenerUtil.mutListener.listen(67959) ? (off / len) : (ListenerUtil.mutListener.listen(67958) ? (off * len) : (ListenerUtil.mutListener.listen(67957) ? (off - len) : (off + len))))) > source.length))))))))) {
                throw new IllegalArgumentException(String.format(Locale.US, "Source array with length %d cannot have offset of %d and process %d bytes.", source.length, off, len));
            }
        }
        if (!ListenerUtil.mutListener.listen(67978)) {
            if ((ListenerUtil.mutListener.listen(67972) ? (len >= 0) : (ListenerUtil.mutListener.listen(67971) ? (len <= 0) : (ListenerUtil.mutListener.listen(67970) ? (len > 0) : (ListenerUtil.mutListener.listen(67969) ? (len < 0) : (ListenerUtil.mutListener.listen(67968) ? (len != 0) : (len == 0))))))) {
                return new byte[0];
            } else if ((ListenerUtil.mutListener.listen(67977) ? (len >= 4) : (ListenerUtil.mutListener.listen(67976) ? (len <= 4) : (ListenerUtil.mutListener.listen(67975) ? (len > 4) : (ListenerUtil.mutListener.listen(67974) ? (len != 4) : (ListenerUtil.mutListener.listen(67973) ? (len == 4) : (len < 4))))))) {
                throw new IllegalArgumentException("Base64-encoded string must have at least four characters, but length specified was " + len);
            }
        }
        // Estimate on array size
        int len34 = (ListenerUtil.mutListener.listen(67986) ? ((ListenerUtil.mutListener.listen(67982) ? (len % 3) : (ListenerUtil.mutListener.listen(67981) ? (len / 3) : (ListenerUtil.mutListener.listen(67980) ? (len - 3) : (ListenerUtil.mutListener.listen(67979) ? (len + 3) : (len * 3))))) % 4) : (ListenerUtil.mutListener.listen(67985) ? ((ListenerUtil.mutListener.listen(67982) ? (len % 3) : (ListenerUtil.mutListener.listen(67981) ? (len / 3) : (ListenerUtil.mutListener.listen(67980) ? (len - 3) : (ListenerUtil.mutListener.listen(67979) ? (len + 3) : (len * 3))))) * 4) : (ListenerUtil.mutListener.listen(67984) ? ((ListenerUtil.mutListener.listen(67982) ? (len % 3) : (ListenerUtil.mutListener.listen(67981) ? (len / 3) : (ListenerUtil.mutListener.listen(67980) ? (len - 3) : (ListenerUtil.mutListener.listen(67979) ? (len + 3) : (len * 3))))) - 4) : (ListenerUtil.mutListener.listen(67983) ? ((ListenerUtil.mutListener.listen(67982) ? (len % 3) : (ListenerUtil.mutListener.listen(67981) ? (len / 3) : (ListenerUtil.mutListener.listen(67980) ? (len - 3) : (ListenerUtil.mutListener.listen(67979) ? (len + 3) : (len * 3))))) + 4) : ((ListenerUtil.mutListener.listen(67982) ? (len % 3) : (ListenerUtil.mutListener.listen(67981) ? (len / 3) : (ListenerUtil.mutListener.listen(67980) ? (len - 3) : (ListenerUtil.mutListener.listen(67979) ? (len + 3) : (len * 3))))) / 4)))));
        // Upper limit on size of output
        byte[] outBuff = new byte[len34];
        // Keep track of where we're writing
        int outBuffPosn = 0;
        // Four byte buffer from source, eliminating white space
        byte[] b4 = new byte[4];
        // Keep track of four byte input buffer
        int b4Posn = 0;
        if (!ListenerUtil.mutListener.listen(68023)) {
            {
                long _loopCounter850 = 0;
                for (int i = off; (ListenerUtil.mutListener.listen(68022) ? (i >= (ListenerUtil.mutListener.listen(68017) ? (off % len) : (ListenerUtil.mutListener.listen(68016) ? (off / len) : (ListenerUtil.mutListener.listen(68015) ? (off * len) : (ListenerUtil.mutListener.listen(68014) ? (off - len) : (off + len)))))) : (ListenerUtil.mutListener.listen(68021) ? (i <= (ListenerUtil.mutListener.listen(68017) ? (off % len) : (ListenerUtil.mutListener.listen(68016) ? (off / len) : (ListenerUtil.mutListener.listen(68015) ? (off * len) : (ListenerUtil.mutListener.listen(68014) ? (off - len) : (off + len)))))) : (ListenerUtil.mutListener.listen(68020) ? (i > (ListenerUtil.mutListener.listen(68017) ? (off % len) : (ListenerUtil.mutListener.listen(68016) ? (off / len) : (ListenerUtil.mutListener.listen(68015) ? (off * len) : (ListenerUtil.mutListener.listen(68014) ? (off - len) : (off + len)))))) : (ListenerUtil.mutListener.listen(68019) ? (i != (ListenerUtil.mutListener.listen(68017) ? (off % len) : (ListenerUtil.mutListener.listen(68016) ? (off / len) : (ListenerUtil.mutListener.listen(68015) ? (off * len) : (ListenerUtil.mutListener.listen(68014) ? (off - len) : (off + len)))))) : (ListenerUtil.mutListener.listen(68018) ? (i == (ListenerUtil.mutListener.listen(68017) ? (off % len) : (ListenerUtil.mutListener.listen(68016) ? (off / len) : (ListenerUtil.mutListener.listen(68015) ? (off * len) : (ListenerUtil.mutListener.listen(68014) ? (off - len) : (off + len)))))) : (i < (ListenerUtil.mutListener.listen(68017) ? (off % len) : (ListenerUtil.mutListener.listen(68016) ? (off / len) : (ListenerUtil.mutListener.listen(68015) ? (off * len) : (ListenerUtil.mutListener.listen(68014) ? (off - len) : (off + len))))))))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter850", ++_loopCounter850);
                    // Loop through source
                    int sbiDecode = DECODABET[source[i] & 0xFF];
                    if (!ListenerUtil.mutListener.listen(68013)) {
                        // DECODABETs at the top of the file.
                        if ((ListenerUtil.mutListener.listen(67991) ? (sbiDecode <= WHITE_SPACE_ENC) : (ListenerUtil.mutListener.listen(67990) ? (sbiDecode > WHITE_SPACE_ENC) : (ListenerUtil.mutListener.listen(67989) ? (sbiDecode < WHITE_SPACE_ENC) : (ListenerUtil.mutListener.listen(67988) ? (sbiDecode != WHITE_SPACE_ENC) : (ListenerUtil.mutListener.listen(67987) ? (sbiDecode == WHITE_SPACE_ENC) : (sbiDecode >= WHITE_SPACE_ENC))))))) {
                            if (!ListenerUtil.mutListener.listen(68012)) {
                                if ((ListenerUtil.mutListener.listen(67996) ? (sbiDecode <= EQUALS_SIGN_ENC) : (ListenerUtil.mutListener.listen(67995) ? (sbiDecode > EQUALS_SIGN_ENC) : (ListenerUtil.mutListener.listen(67994) ? (sbiDecode < EQUALS_SIGN_ENC) : (ListenerUtil.mutListener.listen(67993) ? (sbiDecode != EQUALS_SIGN_ENC) : (ListenerUtil.mutListener.listen(67992) ? (sbiDecode == EQUALS_SIGN_ENC) : (sbiDecode >= EQUALS_SIGN_ENC))))))) {
                                    if (!ListenerUtil.mutListener.listen(67997)) {
                                        // Save non-whitespace
                                        b4[b4Posn++] = source[i];
                                    }
                                    if (!ListenerUtil.mutListener.listen(68011)) {
                                        if ((ListenerUtil.mutListener.listen(68002) ? (b4Posn >= 3) : (ListenerUtil.mutListener.listen(68001) ? (b4Posn <= 3) : (ListenerUtil.mutListener.listen(68000) ? (b4Posn < 3) : (ListenerUtil.mutListener.listen(67999) ? (b4Posn != 3) : (ListenerUtil.mutListener.listen(67998) ? (b4Posn == 3) : (b4Posn > 3))))))) {
                                            if (!ListenerUtil.mutListener.listen(68003)) {
                                                // Time to decode?
                                                outBuffPosn += decode4to3(b4, 0, outBuff, outBuffPosn);
                                            }
                                            if (!ListenerUtil.mutListener.listen(68004)) {
                                                b4Posn = 0;
                                            }
                                            if (!ListenerUtil.mutListener.listen(68010)) {
                                                // If that was the equals sign, break out of 'for' loop
                                                if ((ListenerUtil.mutListener.listen(68009) ? (source[i] >= EQUALS_SIGN) : (ListenerUtil.mutListener.listen(68008) ? (source[i] <= EQUALS_SIGN) : (ListenerUtil.mutListener.listen(68007) ? (source[i] > EQUALS_SIGN) : (ListenerUtil.mutListener.listen(68006) ? (source[i] < EQUALS_SIGN) : (ListenerUtil.mutListener.listen(68005) ? (source[i] != EQUALS_SIGN) : (source[i] == EQUALS_SIGN))))))) {
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            // There's a bad input character in the Base64 stream.
                            throw new java.io.IOException(String.format(Locale.US, "Bad Base64 input character decimal %d in array position %d", ((int) source[i]) & 0xFF, i));
                        }
                    }
                }
            }
        }
        byte[] out = new byte[outBuffPosn];
        if (!ListenerUtil.mutListener.listen(68024)) {
            System.arraycopy(outBuff, 0, out, 0, outBuffPosn);
        }
        return out;
    }

    /**
     *  Decodes data from Base64 notation.
     *
     *  @param s the string to decode
     *  @return the decoded data
     *  @throws java.io.IOException if there is an error
     *  @throws NullPointerException if <tt>s</tt> is null
     *  @since 1.4
     */
    public static byte[] decode(String s) throws java.io.IOException {
        if (!ListenerUtil.mutListener.listen(68025)) {
            if (s == null) {
                throw new NullPointerException("Input string was null.");
            }
        }
        byte[] bytes = s.getBytes(StandardCharsets.US_ASCII);
        // Decode
        return decode(bytes, 0, bytes.length);
    }
}
