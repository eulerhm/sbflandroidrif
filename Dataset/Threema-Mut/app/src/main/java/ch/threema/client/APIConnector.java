/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema Java Client
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
package ch.threema.client;

import android.annotation.SuppressLint;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.neilalexander.jnacl.NaCl;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.base.ThreemaException;
import ch.threema.client.work.WorkContact;
import ch.threema.client.work.WorkData;
import ch.threema.client.work.WorkDirectory;
import ch.threema.client.work.WorkDirectoryCategory;
import ch.threema.client.work.WorkDirectoryContact;
import ch.threema.client.work.WorkDirectoryFilter;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Fetches data and executes commands on the Threema API (such as creating a new
 * identity, fetching public keys for a given identity, linking e-mail addresses
 * and mobile phone numbers, etc.).
 * <p>
 * All calls run synchronously; if necessary the caller should dispatch a separate thread.
 */
@SuppressWarnings("DuplicateThrows")
public class APIConnector {

    private static final Logger logger = LoggerFactory.getLogger(APIConnector.class);

    /* HMAC-SHA256 keys for contact matching */
    private static final byte[] EMAIL_HMAC_KEY = new byte[] { (byte) 0x30, (byte) 0xa5, (byte) 0x50, (byte) 0x0f, (byte) 0xed, (byte) 0x97, (byte) 0x01, (byte) 0xfa, (byte) 0x6d, (byte) 0xef, (byte) 0xdb, (byte) 0x61, (byte) 0x08, (byte) 0x41, (byte) 0x90, (byte) 0x0f, (byte) 0xeb, (byte) 0xb8, (byte) 0xe4, (byte) 0x30, (byte) 0x88, (byte) 0x1f, (byte) 0x7a, (byte) 0xd8, (byte) 0x16, (byte) 0x82, (byte) 0x62, (byte) 0x64, (byte) 0xec, (byte) 0x09, (byte) 0xba, (byte) 0xd7 };

    private static final byte[] MOBILENO_HMAC_KEY = new byte[] { (byte) 0x85, (byte) 0xad, (byte) 0xf8, (byte) 0x22, (byte) 0x69, (byte) 0x53, (byte) 0xf3, (byte) 0xd9, (byte) 0x6c, (byte) 0xfd, (byte) 0x5d, (byte) 0x09, (byte) 0xbf, (byte) 0x29, (byte) 0x55, (byte) 0x5e, (byte) 0xb9, (byte) 0x55, (byte) 0xfc, (byte) 0xd8, (byte) 0xaa, (byte) 0x5e, (byte) 0xc4, (byte) 0xf9, (byte) 0xfc, (byte) 0xd8, (byte) 0x69, (byte) 0xe2, (byte) 0x58, (byte) 0x37, (byte) 0x07, (byte) 0x23 };

    private static final int DEFAULT_MATCH_CHECK_INTERVAL = 86400;

    @NonNull
    private final SSLSocketFactoryFactory sslSocketFactoryFactory;

    private final SecureRandom random;

    private final boolean isWork;

    private int matchCheckInterval = DEFAULT_MATCH_CHECK_INTERVAL;

    private Version version;

    private String language;

    private String serverUrl, workServerUrl;

    private final boolean sandbox;

    public APIConnector(boolean ipv6, String directoryServerUrlOverride, boolean isWork, boolean sandbox, @NonNull SSLSocketFactoryFactory sslSocketFactoryFactory) {
        this.random = new SecureRandom();
        if (!ListenerUtil.mutListener.listen(66930)) {
            this.version = new Version();
        }
        this.isWork = isWork;
        this.sandbox = sandbox;
        this.sslSocketFactoryFactory = sslSocketFactoryFactory;
        if (!ListenerUtil.mutListener.listen(66933)) {
            if (directoryServerUrlOverride != null) {
                if (!ListenerUtil.mutListener.listen(66932)) {
                    this.serverUrl = directoryServerUrlOverride;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(66931)) {
                    this.setServerUrls(ipv6);
                }
            }
        }
    }

    public APIConnector(boolean ipv6, boolean isWork, boolean sandbox, @NonNull SSLSocketFactoryFactory sslSocketFactoryFactory) {
        this(ipv6, null, isWork, sandbox, sslSocketFactoryFactory);
    }

    /**
     *  Create a new identity and store it in the given identity store.
     *
     *  @param identityStore   the store for the new identity
     *  @param seed            additional random data to be used for key generation
     *  @param requestData    licensing requestData based on build flavor (hms, google or serial)
     *  @throws Exception
     */
    public void createIdentity(IdentityStoreInterface identityStore, byte[] seed, @NonNull CreateIdentityRequestDataInterface requestData) throws Exception {
        String url = serverUrl + "identity/create";
        if (!ListenerUtil.mutListener.listen(66934)) {
            /* generate new key pair and store */
            logger.debug("Generating new key pair");
        }
        byte[] publicKey = new byte[NaCl.PUBLICKEYBYTES];
        byte[] privateKey = new byte[NaCl.SECRETKEYBYTES];
        /* seed available? */
        byte[] hashedSeed = null;
        if (!ListenerUtil.mutListener.listen(66936)) {
            if (seed != null) {
                /* hash the seed to ensure it is unbiased and has the right length */
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                if (!ListenerUtil.mutListener.listen(66935)) {
                    hashedSeed = md.digest(seed);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(66937)) {
            NaCl.genkeypair(publicKey, privateKey, hashedSeed);
        }
        if (!ListenerUtil.mutListener.listen(66938)) {
            /* phase 1: send public key to server */
            logger.debug("Sending public key to server");
        }
        JSONObject p1Body = new JSONObject();
        if (!ListenerUtil.mutListener.listen(66939)) {
            p1Body.put("publicKey", Base64.encodeBytes(publicKey));
        }
        String p1ResultString = doPost(url, p1Body.toString());
        JSONObject p1Result = new JSONObject(p1ResultString);
        String tokenString = p1Result.getString("token");
        byte[] token = Base64.decode(tokenString);
        byte[] tokenRespKeyPub = Base64.decode(p1Result.getString("tokenRespKeyPub"));
        if (!ListenerUtil.mutListener.listen(66940)) {
            logger.debug("Got token from server; sending response");
        }
        /* phase 2: encrypt token and send response to server */
        String nonceStr = "createIdentity response.";
        NaCl nacl = new NaCl(privateKey, tokenRespKeyPub);
        byte[] clientResponse = nacl.encrypt(token, nonceStr.getBytes());
        JSONObject p2Body = requestData.createIdentityRequestDataJSON();
        if (!ListenerUtil.mutListener.listen(66941)) {
            p2Body.put("publicKey", Base64.encodeBytes(publicKey));
        }
        if (!ListenerUtil.mutListener.listen(66942)) {
            p2Body.put("token", tokenString);
        }
        if (!ListenerUtil.mutListener.listen(66943)) {
            p2Body.put("response", Base64.encodeBytes(clientResponse));
        }
        String p2ResultString = doPost(url, p2Body.toString());
        JSONObject p2Result = new JSONObject(p2ResultString);
        boolean success = p2Result.getBoolean("success");
        if (!ListenerUtil.mutListener.listen(66944)) {
            if (!success)
                throw new ThreemaException("TA001: " + p2Result.getString("error"));
        }
        String identity = p2Result.getString("identity");
        String serverGroup = p2Result.getString("serverGroup");
        if (!ListenerUtil.mutListener.listen(66945)) {
            logger.info("New identity: {}, server group: {}", identity, serverGroup);
        }
        if (!ListenerUtil.mutListener.listen(66946)) {
            identityStore.storeIdentity(identity, serverGroup, publicKey, privateKey);
        }
    }

    /**
     *  Fetch identity-related information (public key) for
     *  a given identity.
     *
     *  @param identity the desired identity
     *  @return information related to identity
     *  @throws FileNotFoundException if identity not found
     *  @throws Exception             on network error
     */
    public FetchIdentityResult fetchIdentity(String identity) throws FileNotFoundException, Exception {
        String responseStr = doGet(serverUrl + "identity/" + identity);
        JSONObject jsonResponse = new JSONObject(responseStr);
        FetchIdentityResult result = new FetchIdentityResult();
        if (!ListenerUtil.mutListener.listen(66947)) {
            result.publicKey = Base64.decode(jsonResponse.getString("publicKey"));
        }
        if (!ListenerUtil.mutListener.listen(66948)) {
            result.featureLevel = jsonResponse.optInt("featureLevel");
        }
        if (!ListenerUtil.mutListener.listen(66949)) {
            result.featureMask = jsonResponse.optInt("featureMask");
        }
        if (!ListenerUtil.mutListener.listen(66950)) {
            result.identity = jsonResponse.getString("identity");
        }
        if (!ListenerUtil.mutListener.listen(66951)) {
            result.state = jsonResponse.optInt("state");
        }
        if (!ListenerUtil.mutListener.listen(66952)) {
            result.type = jsonResponse.optInt("type");
        }
        return result;
    }

    /**
     *  Fetch identity-related information for given identities.
     *
     *  @param identities the desired identities
     *  @return array list of information related to identity
     *  @throws FileNotFoundException if identity not found
     *  @throws Exception             on network error
     */
    public ArrayList<FetchIdentityResult> fetchIdentities(ArrayList<String> identities) throws FileNotFoundException, Exception {
        if (!ListenerUtil.mutListener.listen(66959)) {
            if ((ListenerUtil.mutListener.listen(66958) ? (identities == null && (ListenerUtil.mutListener.listen(66957) ? (identities.size() >= 1) : (ListenerUtil.mutListener.listen(66956) ? (identities.size() <= 1) : (ListenerUtil.mutListener.listen(66955) ? (identities.size() > 1) : (ListenerUtil.mutListener.listen(66954) ? (identities.size() != 1) : (ListenerUtil.mutListener.listen(66953) ? (identities.size() == 1) : (identities.size() < 1))))))) : (identities == null || (ListenerUtil.mutListener.listen(66957) ? (identities.size() >= 1) : (ListenerUtil.mutListener.listen(66956) ? (identities.size() <= 1) : (ListenerUtil.mutListener.listen(66955) ? (identities.size() > 1) : (ListenerUtil.mutListener.listen(66954) ? (identities.size() != 1) : (ListenerUtil.mutListener.listen(66953) ? (identities.size() == 1) : (identities.size() < 1))))))))) {
                throw new ThreemaException("empty identities array");
            }
        }
        JSONObject postObject = new JSONObject();
        if (!ListenerUtil.mutListener.listen(66960)) {
            postObject.put("identities", new JSONArray(identities));
        }
        String postResponse = doPost(serverUrl + "identity/fetch_bulk", postObject.toString());
        if (!ListenerUtil.mutListener.listen(66961)) {
            if (postResponse == null) {
                throw new ThreemaException("no valid response or network error");
            }
        }
        JSONObject resultObject = new JSONObject(postResponse);
        JSONArray resultArray = resultObject.getJSONArray("identities");
        ArrayList<FetchIdentityResult> fetchIdentityResults = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(66974)) {
            {
                long _loopCounter826 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(66973) ? (i >= resultArray.length()) : (ListenerUtil.mutListener.listen(66972) ? (i <= resultArray.length()) : (ListenerUtil.mutListener.listen(66971) ? (i > resultArray.length()) : (ListenerUtil.mutListener.listen(66970) ? (i != resultArray.length()) : (ListenerUtil.mutListener.listen(66969) ? (i == resultArray.length()) : (i < resultArray.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter826", ++_loopCounter826);
                    JSONObject jsonResponse = resultArray.getJSONObject(i);
                    FetchIdentityResult fetchIdentityResult = new FetchIdentityResult();
                    if (!ListenerUtil.mutListener.listen(66962)) {
                        fetchIdentityResult.publicKey = Base64.decode(jsonResponse.getString("publicKey"));
                    }
                    if (!ListenerUtil.mutListener.listen(66963)) {
                        fetchIdentityResult.featureLevel = jsonResponse.optInt("featureLevel");
                    }
                    if (!ListenerUtil.mutListener.listen(66964)) {
                        fetchIdentityResult.featureMask = jsonResponse.optInt("featureMask");
                    }
                    if (!ListenerUtil.mutListener.listen(66965)) {
                        fetchIdentityResult.identity = jsonResponse.getString("identity");
                    }
                    if (!ListenerUtil.mutListener.listen(66966)) {
                        fetchIdentityResult.state = jsonResponse.optInt("state");
                    }
                    if (!ListenerUtil.mutListener.listen(66967)) {
                        fetchIdentityResult.type = jsonResponse.optInt("type");
                    }
                    if (!ListenerUtil.mutListener.listen(66968)) {
                        fetchIdentityResults.add(fetchIdentityResult);
                    }
                }
            }
        }
        return fetchIdentityResults;
    }

    /**
     *  Fetch private identity-related information (server group, linked e-mail/mobile number).
     *
     *  @param identityStore the identity store to use
     *  @return fetched private identity information
     */
    public FetchIdentityPrivateResult fetchIdentityPrivate(IdentityStoreInterface identityStore) throws Exception {
        String url = serverUrl + "identity/fetch_priv";
        /* phase 1: send identity */
        JSONObject request = new JSONObject();
        if (!ListenerUtil.mutListener.listen(66975)) {
            request.put("identity", identityStore.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(66976)) {
            logger.debug("Fetch identity private phase 1: sending to server: {}", request);
        }
        JSONObject p1Result = new JSONObject(doPost(url, request.toString()));
        if (!ListenerUtil.mutListener.listen(66977)) {
            logger.debug("Fetch identity private phase 1: response from server: {}", p1Result);
        }
        if (!ListenerUtil.mutListener.listen(66979)) {
            if ((ListenerUtil.mutListener.listen(66978) ? (p1Result.has("success") || !p1Result.getBoolean("success")) : (p1Result.has("success") && !p1Result.getBoolean("success")))) {
                throw new ThreemaException(p1Result.getString("error"));
            }
        }
        if (!ListenerUtil.mutListener.listen(66980)) {
            makeTokenResponse(p1Result, request, identityStore);
        }
        if (!ListenerUtil.mutListener.listen(66981)) {
            /* phase 2: send token response */
            logger.debug("Fetch identity private: sending to server: {}", request);
        }
        JSONObject p2Result = new JSONObject(doPost(url, request.toString()));
        if (!ListenerUtil.mutListener.listen(66982)) {
            logger.debug("Fetch identity private: response from server: {}", p2Result);
        }
        if (!ListenerUtil.mutListener.listen(66983)) {
            if (!p2Result.getBoolean("success")) {
                throw new ThreemaException(p2Result.getString("error"));
            }
        }
        FetchIdentityPrivateResult result = new FetchIdentityPrivateResult();
        if (!ListenerUtil.mutListener.listen(66984)) {
            result.serverGroup = p2Result.getString("serverGroup");
        }
        if (!ListenerUtil.mutListener.listen(66986)) {
            if (p2Result.has("email"))
                if (!ListenerUtil.mutListener.listen(66985)) {
                    result.email = p2Result.getString("email");
                }
        }
        if (!ListenerUtil.mutListener.listen(66988)) {
            if (p2Result.has("mobileNo"))
                if (!ListenerUtil.mutListener.listen(66987)) {
                    result.mobileNo = p2Result.getString("mobileNo");
                }
        }
        return result;
    }

    /**
     *  Link an e-mail address with the identity from the given store. The user gets a verification
     *  e-mail with a link. {@link #linkEmailCheckStatus(String, IdentityStoreInterface)} should be called
     *  to check whether the user has already confirmed.
     *  <p>
     *  To unlink, pass an empty string as the e-mail address. In that case, checking status is not
     *  necessary as the unlink operation does not need e-mail verification.
     *
     *  @param email         e-mail address to be linked, or empty string to unlink
     *  @param language      language for confirmation e-mail, ISO-639-1 (e.g. "de", "en", "fr")
     *  @param identityStore identity store for authentication of request
     *  @return true if e-mail address is accepted for verification, false if already linked
     *  @throws LinkEmailException if the server reports an error (should be displayed to the user verbatim)
     *  @throws Exception          if a network error occurs
     */
    public boolean linkEmail(String email, String language, IdentityStoreInterface identityStore) throws LinkEmailException, Exception {
        String url = serverUrl + "identity/link_email";
        /* phase 1: send identity and e-mail */
        JSONObject request = new JSONObject();
        if (!ListenerUtil.mutListener.listen(66989)) {
            request.put("identity", identityStore.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(66990)) {
            request.put("email", email);
        }
        if (!ListenerUtil.mutListener.listen(66991)) {
            request.put("lang", language);
        }
        if (!ListenerUtil.mutListener.listen(66992)) {
            logger.debug("Link e-mail phase 1: sending to server: {}", request);
        }
        JSONObject p1Result = new JSONObject(doPost(url, request.toString()));
        if (!ListenerUtil.mutListener.listen(66993)) {
            logger.debug("Link e-mail phase 1: response from server: {}", p1Result);
        }
        if (!ListenerUtil.mutListener.listen(66994)) {
            if (!p1Result.has("linked"))
                throw new LinkEmailException(p1Result.getString("error"));
        }
        if (!ListenerUtil.mutListener.listen(66995)) {
            if (p1Result.getBoolean("linked"))
                return false;
        }
        if (!ListenerUtil.mutListener.listen(66996)) {
            makeTokenResponse(p1Result, request, identityStore);
        }
        if (!ListenerUtil.mutListener.listen(66997)) {
            /* phase 2: send token response */
            logger.debug("Link e-mail phase 2: sending to server: {}", request);
        }
        JSONObject p2Result = new JSONObject(doPost(url, request.toString()));
        if (!ListenerUtil.mutListener.listen(66998)) {
            logger.debug("Link e-mail phase 2: response from server: {}", p2Result);
        }
        if (!ListenerUtil.mutListener.listen(66999)) {
            if (!p2Result.getBoolean("success"))
                throw new LinkEmailException(p2Result.getString("error"));
        }
        return true;
    }

    /**
     *  Check whether a given e-mail address is already linked to the identity (i.e. the user
     *  has confirmed the verification mail).
     *
     *  @param email         e-mail address to be linked
     *  @param identityStore identity store for authentication of request
     *  @return e-mail address linked true/false
     *  @throws Exception if a network error occurs
     */
    public boolean linkEmailCheckStatus(String email, IdentityStoreInterface identityStore) throws Exception {
        String url = serverUrl + "identity/link_email";
        JSONObject request = new JSONObject();
        if (!ListenerUtil.mutListener.listen(67000)) {
            request.put("identity", identityStore.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(67001)) {
            request.put("email", email);
        }
        if (!ListenerUtil.mutListener.listen(67002)) {
            logger.debug("Link e-mail check: sending to server: {}", request);
        }
        JSONObject p1Result = new JSONObject(doPost(url, request.toString()));
        if (!ListenerUtil.mutListener.listen(67003)) {
            logger.debug("Link e-mail check: response from server: {}", p1Result);
        }
        return p1Result.getBoolean("linked");
    }

    /**
     *  Link a mobile phone number with the identity from the given store. The user gets a verification code via
     *  SMS; this code should be passed to {@link #linkMobileNoVerify(String, String)} along with the verification ID
     *  returned by this method to complete the operation.
     *  <p>
     *  To unlink, pass an empty string as the mobile number.
     *
     *  @param mobileNo      mobile phone number in E.164 format without + (e.g. 41791234567)
     *  @param language      language for SMS text, ISO-639-1 (e.g. "de", "en", "fr")
     *  @param identityStore identity store for authentication of request
     *  @return verification ID that should be passed to {@link #linkMobileNoVerify(String, String)}, or null if verification is already complete
     *  @throws LinkMobileNoException if the server reports an error (should be displayed to the user verbatim)
     *  @throws Exception             if a network error occurs
     */
    public String linkMobileNo(String mobileNo, String language, IdentityStoreInterface identityStore) throws LinkMobileNoException, Exception {
        return this.linkMobileNo(mobileNo, language, identityStore, null);
    }

    /**
     *  Link a mobile phone number with the identity from the given store. The user gets a verification code via
     *  SMS; this code should be passed to {@link #linkMobileNoVerify(String, String)} along with the verification ID
     *  returned by this method to complete the operation.
     *  <p>
     *  To unlink, pass an empty string as the mobile number.
     *
     *  @param mobileNo      mobile phone number in E.164 format without + (e.g. 41791234567)
     *  @param language      language for SMS text, ISO-639-1 (e.g. "de", "en", "fr")
     *  @param identityStore identity store for authentication of request
     *  @param urlScheme     optional parameter (url schema of the verification link)
     *  @return verification ID that should be passed to {@link #linkMobileNoVerify(String, String)}, or null if verification is already complete
     *  @throws LinkMobileNoException if the server reports an error (should be displayed to the user verbatim)
     *  @throws Exception             if a network error occurs
     */
    public String linkMobileNo(String mobileNo, String language, IdentityStoreInterface identityStore, String urlScheme) throws LinkMobileNoException, Exception {
        String url = serverUrl + "identity/link_mobileno";
        /* phase 1: send identity and mobile no */
        JSONObject request = new JSONObject();
        if (!ListenerUtil.mutListener.listen(67004)) {
            request.put("identity", identityStore.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(67005)) {
            request.put("mobileNo", mobileNo);
        }
        if (!ListenerUtil.mutListener.listen(67006)) {
            request.put("lang", language);
        }
        if (!ListenerUtil.mutListener.listen(67007)) {
            request.put("httpsUrl", true);
        }
        if (!ListenerUtil.mutListener.listen(67015)) {
            if ((ListenerUtil.mutListener.listen(67013) ? (urlScheme != null || (ListenerUtil.mutListener.listen(67012) ? (urlScheme.length() >= 0) : (ListenerUtil.mutListener.listen(67011) ? (urlScheme.length() <= 0) : (ListenerUtil.mutListener.listen(67010) ? (urlScheme.length() < 0) : (ListenerUtil.mutListener.listen(67009) ? (urlScheme.length() != 0) : (ListenerUtil.mutListener.listen(67008) ? (urlScheme.length() == 0) : (urlScheme.length() > 0))))))) : (urlScheme != null && (ListenerUtil.mutListener.listen(67012) ? (urlScheme.length() >= 0) : (ListenerUtil.mutListener.listen(67011) ? (urlScheme.length() <= 0) : (ListenerUtil.mutListener.listen(67010) ? (urlScheme.length() < 0) : (ListenerUtil.mutListener.listen(67009) ? (urlScheme.length() != 0) : (ListenerUtil.mutListener.listen(67008) ? (urlScheme.length() == 0) : (urlScheme.length() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(67014)) {
                    request.put("urlScheme", true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(67016)) {
            logger.debug("Link mobile number phase 1: sending to server: {}", request);
        }
        JSONObject p1Result = new JSONObject(doPost(url, request.toString()));
        if (!ListenerUtil.mutListener.listen(67017)) {
            logger.debug("Link mobile number phase 1: response from server: {}", p1Result);
        }
        if (!ListenerUtil.mutListener.listen(67018)) {
            if (!p1Result.has("linked"))
                throw new LinkMobileNoException(p1Result.getString("error"));
        }
        if (p1Result.getBoolean("linked"))
            return null;
        if (!ListenerUtil.mutListener.listen(67019)) {
            makeTokenResponse(p1Result, request, identityStore);
        }
        if (!ListenerUtil.mutListener.listen(67020)) {
            /* phase 2: send token response */
            logger.debug("Link mobile number phase 2: sending to server: {}", request);
        }
        JSONObject p2Result = new JSONObject(doPost(url, request.toString()));
        if (!ListenerUtil.mutListener.listen(67021)) {
            logger.debug("Link mobile number phase 2: response from server: {}", p2Result);
        }
        if (!ListenerUtil.mutListener.listen(67022)) {
            if (!p2Result.getBoolean("success"))
                throw new LinkMobileNoException(p2Result.getString("error"));
        }
        if ((ListenerUtil.mutListener.listen(67027) ? (mobileNo.length() >= 0) : (ListenerUtil.mutListener.listen(67026) ? (mobileNo.length() <= 0) : (ListenerUtil.mutListener.listen(67025) ? (mobileNo.length() < 0) : (ListenerUtil.mutListener.listen(67024) ? (mobileNo.length() != 0) : (ListenerUtil.mutListener.listen(67023) ? (mobileNo.length() == 0) : (mobileNo.length() > 0)))))))
            return p2Result.getString("verificationId");
        else
            return null;
    }

    /**
     *  Complete verification of mobile number link.
     *
     *  @param verificationId the verification ID returned by {@link #linkMobileNo(String, String, IdentityStoreInterface)}
     *  @param code           the SMS code (usually 6 digits)
     *  @throws LinkMobileNoException if the server reports an error, e.g. wrong code or too many attempts (should be displayed to the user verbatim)
     *  @throws Exception             if a network error occurs
     */
    public void linkMobileNoVerify(String verificationId, String code) throws LinkMobileNoException, Exception {
        String url = serverUrl + "identity/link_mobileno";
        JSONObject request = new JSONObject();
        if (!ListenerUtil.mutListener.listen(67028)) {
            request.put("verificationId", verificationId);
        }
        if (!ListenerUtil.mutListener.listen(67029)) {
            request.put("code", code);
        }
        JSONObject result = new JSONObject(doPost(url, request.toString()));
        if (!ListenerUtil.mutListener.listen(67030)) {
            if (!result.getBoolean("success"))
                throw new LinkMobileNoException(result.getString("error"));
        }
    }

    /**
     *  Trigger a phone call for the given verification ID. This should only be done if the SMS doesn't arrive
     *  in a normal amount of time (e.g. 10 minutes). The verification code will be read to the user twice,
     *  and {@link #linkMobileNoVerify(String, String)} should then be called with the code.
     *
     *  @param verificationId verification ID returned from {@link #linkMobileNo(String, String, IdentityStoreInterface)}
     *  @throws LinkMobileNoException if the server reports an error, e.g. unable to call the destination, already called etc. (should be displayed to the user verbatim)
     *  @throws Exception             if a network error occurs
     */
    public void linkMobileNoCall(String verificationId) throws LinkMobileNoException, Exception {
        String url = serverUrl + "identity/link_mobileno_call";
        JSONObject request = new JSONObject();
        if (!ListenerUtil.mutListener.listen(67031)) {
            request.put("verificationId", verificationId);
        }
        JSONObject result = new JSONObject(doPost(url, request.toString()));
        if (!ListenerUtil.mutListener.listen(67032)) {
            if (!result.getBoolean("success"))
                throw new LinkMobileNoException(result.getString("error"));
        }
    }

    /**
     *  Find identities that have been linked with the given e-mail addresses and/or mobile phone numbers.
     *  The mobile phone numbers can be provided in national or international format, as they will be automatically
     *  passed through libphonenumber (which also takes care of spaces, brackets etc.).
     *  <p>
     *  The server also returns its desired check interval to the {@code APIConnector} object during this call.
     *  The caller should use {@link #getMatchCheckInterval()} to determine the earliest time for the next call
     *  after this call. This is important so that the server can request longer intervals from its clients during
     *  periods of heavy traffic or temporary capacity problems.
     *
     *  @param emails          map of e-mail addresses (key = e-mail, value = arbitrary object for reference that is returned with any found identities)
     *  @param mobileNos       map of phone numbers (key = phone number, value = arbitrary object for reference that is returned with any found identities)
     *  @param userCountry     the user's home country (for correct interpretation of national phone numbers), ISO 3166-1, e.g. "CH" (or null to disable normalization)
     *  @param includeInactive if true, inactive IDs will be included in the results also
     *  @param identityStore   identity store to use for obtaining match token
     *  @param matchTokenStore for storing match token for reuse (may be null)
     *  @return map of found identities (key = identity). The value objects from the {@code emails} and {@code mobileNos} parameters
     *  will be returned in {@code refObject}.
     */
    @SuppressLint("DefaultLocale")
    public Map<String, MatchIdentityResult> matchIdentities(Map<String, ?> emails, Map<String, ?> mobileNos, String userCountry, boolean includeInactive, IdentityStoreInterface identityStore, MatchTokenStoreInterface matchTokenStore) throws Exception {
        /* normalize and hash e-mail addresses */
        Map<String, Object> emailHashes = new HashMap<>();
        Mac emailMac = Mac.getInstance("HmacSHA256");
        if (!ListenerUtil.mutListener.listen(67033)) {
            emailMac.init(new SecretKeySpec(EMAIL_HMAC_KEY, "HmacSHA256"));
        }
        if (!ListenerUtil.mutListener.listen(67040)) {
            {
                long _loopCounter827 = 0;
                for (Map.Entry<String, ?> entry : emails.entrySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter827", ++_loopCounter827);
                    String normalizedEmail = entry.getKey().toLowerCase().trim();
                    byte[] emailHash = emailMac.doFinal(normalizedEmail.getBytes(StandardCharsets.US_ASCII));
                    if (!ListenerUtil.mutListener.listen(67034)) {
                        emailHashes.put(Base64.encodeBytes(emailHash), entry.getValue());
                    }
                    /* Gmail address? If so, hash with the other domain as well */
                    String normalizedEmailAlt = null;
                    if (!ListenerUtil.mutListener.listen(67037)) {
                        if (normalizedEmail.endsWith("@gmail.com")) {
                            if (!ListenerUtil.mutListener.listen(67036)) {
                                normalizedEmailAlt = normalizedEmail.replace("@gmail.com", "@googlemail.com");
                            }
                        } else if (normalizedEmail.endsWith("@googlemail.com"))
                            if (!ListenerUtil.mutListener.listen(67035)) {
                                normalizedEmailAlt = normalizedEmail.replace("@googlemail.com", "@gmail.com");
                            }
                    }
                    if (!ListenerUtil.mutListener.listen(67039)) {
                        if (normalizedEmailAlt != null) {
                            byte[] emailHashAlt = emailMac.doFinal(normalizedEmailAlt.getBytes(StandardCharsets.US_ASCII));
                            if (!ListenerUtil.mutListener.listen(67038)) {
                                emailHashes.put(Base64.encodeBytes(emailHashAlt), entry.getValue());
                            }
                        }
                    }
                }
            }
        }
        /* normalize and hash phone numbers */
        Map<String, Object> mobileNoHashes = new HashMap<>();
        Mac mobileNoMac = Mac.getInstance("HmacSHA256");
        if (!ListenerUtil.mutListener.listen(67041)) {
            mobileNoMac.init(new SecretKeySpec(MOBILENO_HMAC_KEY, "HmacSHA256"));
        }
        PhoneNumberUtil phoneNumberUtil = null;
        if (!ListenerUtil.mutListener.listen(67043)) {
            if (userCountry != null)
                if (!ListenerUtil.mutListener.listen(67042)) {
                    phoneNumberUtil = PhoneNumberUtil.getInstance();
                }
        }
        if (!ListenerUtil.mutListener.listen(67046)) {
            {
                long _loopCounter828 = 0;
                for (Map.Entry<String, ?> entry : mobileNos.entrySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter828", ++_loopCounter828);
                    try {
                        String normalizedMobileNo;
                        if (phoneNumberUtil != null) {
                            Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(entry.getKey(), userCountry);
                            String normalizedMobileNoWithPlus = phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164);
                            normalizedMobileNo = normalizedMobileNoWithPlus.replace("+", "");
                        } else {
                            normalizedMobileNo = entry.getKey().replaceAll("[^0-9]", "");
                        }
                        byte[] mobileNoHash = mobileNoMac.doFinal(normalizedMobileNo.getBytes("US-ASCII"));
                        if (!ListenerUtil.mutListener.listen(67045)) {
                            mobileNoHashes.put(Base64.encodeBytes(mobileNoHash), entry.getValue());
                        }
                    } catch (NumberParseException e) {
                        if (!ListenerUtil.mutListener.listen(67044)) {
                            /* skip/ignore this number */
                            logger.debug("Failed to parse phone number {}: {}", entry.getKey(), e.getMessage());
                        }
                    }
                }
            }
        }
        return matchIdentitiesHashed(emailHashes, mobileNoHashes, includeInactive, identityStore, matchTokenStore);
    }

    public Map<String, MatchIdentityResult> matchIdentitiesHashed(Map<String, ?> emailHashes, Map<String, ?> mobileNoHashes, boolean includeInactive, IdentityStoreInterface identityStore, MatchTokenStoreInterface matchTokenStore) throws Exception {
        String matchToken = obtainMatchToken(identityStore, matchTokenStore, false);
        try {
            return matchIdentitiesHashedToken(emailHashes, mobileNoHashes, includeInactive, matchToken);
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(67047)) {
                // Match token may be invalid/expired, refresh and try again
                logger.debug("Match failed", e);
            }
            if (!ListenerUtil.mutListener.listen(67048)) {
                matchToken = obtainMatchToken(identityStore, matchTokenStore, true);
            }
            return matchIdentitiesHashedToken(emailHashes, mobileNoHashes, includeInactive, matchToken);
        }
    }

    private Map<String, MatchIdentityResult> matchIdentitiesHashedToken(Map<String, ?> emailHashes, Map<String, ?> mobileNoHashes, boolean includeInactive, String matchToken) throws Exception {
        String url = serverUrl + "identity/match";
        /* send hashes to server */
        JSONObject request = new JSONObject();
        if (!ListenerUtil.mutListener.listen(67050)) {
            if (matchToken != null) {
                if (!ListenerUtil.mutListener.listen(67049)) {
                    request.put("matchToken", matchToken);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(67051)) {
            request.put("emailHashes", new JSONArray(emailHashes.keySet()));
        }
        if (!ListenerUtil.mutListener.listen(67052)) {
            request.put("mobileNoHashes", new JSONArray(mobileNoHashes.keySet()));
        }
        if (!ListenerUtil.mutListener.listen(67054)) {
            if (includeInactive)
                if (!ListenerUtil.mutListener.listen(67053)) {
                    request.put("includeInactive", Boolean.TRUE);
                }
        }
        if (!ListenerUtil.mutListener.listen(67055)) {
            logger.debug(String.format("Match identities: sending to server: %s", request.toString()));
        }
        JSONObject result = new JSONObject(doPost(url, request.toString()));
        if (!ListenerUtil.mutListener.listen(67056)) {
            logger.debug(String.format("Match identities: response from server: %s", result.toString()));
        }
        if (!ListenerUtil.mutListener.listen(67057)) {
            matchCheckInterval = result.getInt("checkInterval");
        }
        if (!ListenerUtil.mutListener.listen(67058)) {
            logger.debug(String.format("Server requested check interval of %d seconds", matchCheckInterval));
        }
        JSONArray identities = result.getJSONArray("identities");
        Map<String, MatchIdentityResult> returnMap = new HashMap<>(identities.length());
        if (!ListenerUtil.mutListener.listen(67072)) {
            {
                long _loopCounter829 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(67071) ? (i >= identities.length()) : (ListenerUtil.mutListener.listen(67070) ? (i <= identities.length()) : (ListenerUtil.mutListener.listen(67069) ? (i > identities.length()) : (ListenerUtil.mutListener.listen(67068) ? (i != identities.length()) : (ListenerUtil.mutListener.listen(67067) ? (i == identities.length()) : (i < identities.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter829", ++_loopCounter829);
                    JSONObject identity = identities.getJSONObject(i);
                    MatchIdentityResult resultId = new MatchIdentityResult();
                    if (!ListenerUtil.mutListener.listen(67059)) {
                        resultId.publicKey = Base64.decode(identity.getString("publicKey"));
                    }
                    if (!ListenerUtil.mutListener.listen(67062)) {
                        if (identity.has("emailHash")) {
                            if (!ListenerUtil.mutListener.listen(67060)) {
                                resultId.emailHash = Base64.decode(identity.getString("emailHash"));
                            }
                            if (!ListenerUtil.mutListener.listen(67061)) {
                                resultId.refObjectEmail = emailHashes.get(identity.getString("emailHash"));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(67065)) {
                        if (identity.has("mobileNoHash")) {
                            if (!ListenerUtil.mutListener.listen(67063)) {
                                resultId.mobileNoHash = Base64.decode(identity.getString("mobileNoHash"));
                            }
                            if (!ListenerUtil.mutListener.listen(67064)) {
                                resultId.refObjectMobileNo = mobileNoHashes.get(identity.getString("mobileNoHash"));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(67066)) {
                        returnMap.put(identity.getString("identity"), resultId);
                    }
                }
            }
        }
        return returnMap;
    }

    private String obtainMatchToken(IdentityStoreInterface identityStore, MatchTokenStoreInterface matchTokenStore, boolean forceRefresh) throws Exception {
        if (!ListenerUtil.mutListener.listen(67075)) {
            if ((ListenerUtil.mutListener.listen(67074) ? ((ListenerUtil.mutListener.listen(67073) ? (identityStore == null && identityStore.getIdentity() == null) : (identityStore == null || identityStore.getIdentity() == null)) && identityStore.getIdentity().length() == 0) : ((ListenerUtil.mutListener.listen(67073) ? (identityStore == null && identityStore.getIdentity() == null) : (identityStore == null || identityStore.getIdentity() == null)) || identityStore.getIdentity().length() == 0))) {
                return null;
            }
        }
        // Cached match token?
        String matchToken = null;
        if (!ListenerUtil.mutListener.listen(67077)) {
            if (matchTokenStore != null) {
                if (!ListenerUtil.mutListener.listen(67076)) {
                    matchToken = matchTokenStore.getMatchToken();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(67079)) {
            if ((ListenerUtil.mutListener.listen(67078) ? (matchToken != null || !forceRefresh) : (matchToken != null && !forceRefresh))) {
                return matchToken;
            }
        }
        String url = serverUrl + "identity/match_token";
        /* phase 1: send identity */
        JSONObject request = new JSONObject();
        if (!ListenerUtil.mutListener.listen(67080)) {
            request.put("identity", identityStore.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(67081)) {
            logger.debug("Fetch match token phase 1: sending to server: {}", request);
        }
        JSONObject p1Result = new JSONObject(doPost(url, request.toString()));
        if (!ListenerUtil.mutListener.listen(67082)) {
            logger.debug("Fetch match token phase 1: response from server: {}", p1Result);
        }
        if (!ListenerUtil.mutListener.listen(67083)) {
            makeTokenResponse(p1Result, request, identityStore);
        }
        if (!ListenerUtil.mutListener.listen(67084)) {
            /* phase 2: send token response */
            logger.debug("Fetch match token: sending to server: {}", request);
        }
        JSONObject p2Result = new JSONObject(doPost(url, request.toString()));
        if (!ListenerUtil.mutListener.listen(67085)) {
            logger.debug("Fetch match token: response from server: {}", p2Result);
        }
        if (!ListenerUtil.mutListener.listen(67086)) {
            if (!p2Result.getBoolean("success"))
                throw new ThreemaException(p2Result.getString("error"));
        }
        if (!ListenerUtil.mutListener.listen(67087)) {
            matchToken = p2Result.getString("matchToken");
        }
        if (!ListenerUtil.mutListener.listen(67089)) {
            if (matchTokenStore != null) {
                if (!ListenerUtil.mutListener.listen(67088)) {
                    matchTokenStore.storeMatchToken(matchToken);
                }
            }
        }
        return matchToken;
    }

    /**
     *  Set the group chat flag for the identity in the given store.
     *
     *  @param featureBuilder feature mask builder of the current identity
     *  @param identityStore  identity store for authentication of request
     *  @throws LinkMobileNoException if the server reports an error (should be displayed to the user verbatim)
     *  @throws Exception             if a network error occurs
     */
    public void setFeatureMask(ThreemaFeature.Builder featureBuilder, IdentityStoreInterface identityStore) throws Exception {
        String url = serverUrl + "identity/set_featuremask";
        // /* phase 1: send identity */
        JSONObject request = new JSONObject();
        if (!ListenerUtil.mutListener.listen(67090)) {
            request.put("identity", identityStore.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(67091)) {
            request.put("featureMask", featureBuilder.build());
        }
        if (!ListenerUtil.mutListener.listen(67092)) {
            logger.debug("Set feature mask phase 1: sending to server: {}", request);
        }
        JSONObject p1Result = new JSONObject(doPost(url, request.toString()));
        if (!ListenerUtil.mutListener.listen(67093)) {
            logger.debug("Set feature mask phase 1: response from server: {}", p1Result);
        }
        if (!ListenerUtil.mutListener.listen(67094)) {
            makeTokenResponse(p1Result, request, identityStore);
        }
        if (!ListenerUtil.mutListener.listen(67095)) {
            /* phase 2: send token response */
            logger.debug("Set feature mask phase 2: sending to server: {}", request);
        }
        JSONObject p2Result = new JSONObject(doPost(url, request.toString()));
        if (!ListenerUtil.mutListener.listen(67096)) {
            logger.debug("Set feature mask  phase 2: response from server: {}", p2Result);
        }
        if (!ListenerUtil.mutListener.listen(67097)) {
            if (!p2Result.getBoolean("success")) {
                throw new ThreemaException(p2Result.getString("error"));
            }
        }
    }

    /**
     *  Fetch the feature masks of the supplied identities
     *
     *  @param identities list of IDs to be checked
     *  @return list of feature masks (null if a invalid identity was set)
     *  @throws Exception on network error
     */
    public Integer[] checkFeatureMask(String[] identities) throws Exception {
        String url = serverUrl + "identity/check_featuremask";
        JSONObject request = new JSONObject();
        JSONArray jsonIdentities = new JSONArray();
        if (!ListenerUtil.mutListener.listen(67099)) {
            {
                long _loopCounter830 = 0;
                for (String identity : identities) {
                    ListenerUtil.loopListener.listen("_loopCounter830", ++_loopCounter830);
                    if (!ListenerUtil.mutListener.listen(67098)) {
                        jsonIdentities.put(identity);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(67100)) {
            request.put("identities", jsonIdentities);
        }
        JSONObject result = new JSONObject(doPost(url, request.toString()));
        JSONArray jsonArrayFeatureMasks = result.getJSONArray("featureMasks");
        Integer[] featureMasks = new Integer[jsonArrayFeatureMasks.length()];
        if (!ListenerUtil.mutListener.listen(67109)) {
            {
                long _loopCounter831 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(67108) ? (i >= jsonArrayFeatureMasks.length()) : (ListenerUtil.mutListener.listen(67107) ? (i <= jsonArrayFeatureMasks.length()) : (ListenerUtil.mutListener.listen(67106) ? (i > jsonArrayFeatureMasks.length()) : (ListenerUtil.mutListener.listen(67105) ? (i != jsonArrayFeatureMasks.length()) : (ListenerUtil.mutListener.listen(67104) ? (i == jsonArrayFeatureMasks.length()) : (i < jsonArrayFeatureMasks.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter831", ++_loopCounter831);
                    if (!ListenerUtil.mutListener.listen(67103)) {
                        if (jsonArrayFeatureMasks.isNull(i)) {
                            if (!ListenerUtil.mutListener.listen(67102)) {
                                featureMasks[i] = null;
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(67101)) {
                                featureMasks[i] = jsonArrayFeatureMasks.getInt(i);
                            }
                        }
                    }
                }
            }
        }
        return featureMasks;
    }

    /**
     *  Check the revocation key
     *
     *  @param identityStore
     *  @return
     *  @throws Exception
     */
    public CheckRevocationKeyResult checkRevocationKey(IdentityStoreInterface identityStore) throws Exception {
        String url = serverUrl + "identity/check_revocation_key";
        JSONObject request = new JSONObject();
        if (!ListenerUtil.mutListener.listen(67110)) {
            request.put("identity", identityStore.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(67111)) {
            logger.debug("checkRevocationKey phase 1: sending to server: {}", request);
        }
        JSONObject p1Result = new JSONObject(doPost(url, request.toString()));
        if (!ListenerUtil.mutListener.listen(67112)) {
            logger.debug("checkRevocationKey phase 1: response from server: {}", p1Result);
        }
        if (!ListenerUtil.mutListener.listen(67113)) {
            makeTokenResponse(p1Result, request, identityStore);
        }
        if (!ListenerUtil.mutListener.listen(67114)) {
            /* phase 2: send token response */
            logger.debug("checkRevocationKey phase 2: sending to server: {}", request);
        }
        JSONObject result = new JSONObject(doPost(url, request.toString()));
        if (!ListenerUtil.mutListener.listen(67115)) {
            logger.debug("checkRevocationKey phase 2: response from server: {}", result);
        }
        boolean set = result.getBoolean("revocationKeySet");
        Date lastChanged = null;
        if (!ListenerUtil.mutListener.listen(67117)) {
            if (set) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
                if (!ListenerUtil.mutListener.listen(67116)) {
                    lastChanged = dateFormat.parse(result.getString("lastChanged"));
                }
            }
        }
        return new CheckRevocationKeyResult(set, lastChanged);
    }

    /**
     *  Set the revocation key for the stored identity
     *
     *  @param identityStore
     *  @param revocationKey
     *  @return
     *  @throws Exception
     */
    public SetRevocationKeyResult setRevocationKey(IdentityStoreInterface identityStore, String revocationKey) throws Exception {
        // calculate key
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] sha256 = md.digest(revocationKey.getBytes(StandardCharsets.UTF_8));
        String base64KeyPart = Base64.encodeBytes(Arrays.copyOfRange(sha256, 0, 4));
        String url = serverUrl + "identity/set_revocation_key";
        JSONObject request = new JSONObject();
        if (!ListenerUtil.mutListener.listen(67118)) {
            request.put("identity", identityStore.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(67119)) {
            request.put("revocationKey", base64KeyPart);
        }
        if (!ListenerUtil.mutListener.listen(67120)) {
            logger.debug("setRevocationKey phase 1: sending to server: {}", request);
        }
        JSONObject p1Result = new JSONObject(doPost(url, request.toString()));
        if (!ListenerUtil.mutListener.listen(67121)) {
            logger.debug("setRevocationKey phase 1: response from server: {}", p1Result);
        }
        if (!ListenerUtil.mutListener.listen(67122)) {
            makeTokenResponse(p1Result, request, identityStore);
        }
        if (!ListenerUtil.mutListener.listen(67123)) {
            /* phase 2: send token response */
            logger.debug("setRevocationKey phase 2: sending to server: {}", request);
        }
        JSONObject result = new JSONObject(doPost(url, request.toString()));
        if (!ListenerUtil.mutListener.listen(67124)) {
            logger.debug("setRevocationKey phase 2: response from server: {}", result);
        }
        if (result.getBoolean("success")) {
            return new SetRevocationKeyResult(true, null);
        } else {
            return new SetRevocationKeyResult(true, result.getString("error"));
        }
    }

    /**
     *  This call is used to check a list of IDs and determine the status of each ID. The response contains a list of status codes, one for each ID in the same order as in the request.
     *
     *  @param identities
     *  @return
     *  @throws Exception
     */
    public CheckIdentityStatesResult checkIdentityStates(String[] identities) throws Exception {
        String url = serverUrl + "identity/check";
        JSONObject request = new JSONObject();
        JSONArray jsonIdentities = new JSONArray();
        if (!ListenerUtil.mutListener.listen(67126)) {
            {
                long _loopCounter832 = 0;
                for (String identity : identities) {
                    ListenerUtil.loopListener.listen("_loopCounter832", ++_loopCounter832);
                    if (!ListenerUtil.mutListener.listen(67125)) {
                        jsonIdentities.put(identity);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(67127)) {
            request.put("identities", jsonIdentities);
        }
        JSONObject result = new JSONObject(doPost(url, request.toString()));
        int interval = result.getInt("checkInterval");
        JSONArray jsonStates = result.getJSONArray("states");
        int[] states = new int[jsonStates.length()];
        if (!ListenerUtil.mutListener.listen(67134)) {
            {
                long _loopCounter833 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(67133) ? (i >= jsonStates.length()) : (ListenerUtil.mutListener.listen(67132) ? (i <= jsonStates.length()) : (ListenerUtil.mutListener.listen(67131) ? (i > jsonStates.length()) : (ListenerUtil.mutListener.listen(67130) ? (i != jsonStates.length()) : (ListenerUtil.mutListener.listen(67129) ? (i == jsonStates.length()) : (i < jsonStates.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter833", ++_loopCounter833);
                    if (!ListenerUtil.mutListener.listen(67128)) {
                        states[i] = jsonStates.getInt(i);
                    }
                }
            }
        }
        JSONArray jsonTypes = result.getJSONArray("types");
        int[] types = new int[jsonTypes.length()];
        if (!ListenerUtil.mutListener.listen(67141)) {
            {
                long _loopCounter834 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(67140) ? (i >= jsonTypes.length()) : (ListenerUtil.mutListener.listen(67139) ? (i <= jsonTypes.length()) : (ListenerUtil.mutListener.listen(67138) ? (i > jsonTypes.length()) : (ListenerUtil.mutListener.listen(67137) ? (i != jsonTypes.length()) : (ListenerUtil.mutListener.listen(67136) ? (i == jsonTypes.length()) : (i < jsonTypes.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter834", ++_loopCounter834);
                    if (!ListenerUtil.mutListener.listen(67135)) {
                        types[i] = jsonTypes.getInt(i);
                    }
                }
            }
        }
        JSONArray jsonFeatureMasks = result.getJSONArray("featureMasks");
        Integer[] featureMasks = new Integer[jsonFeatureMasks.length()];
        if (!ListenerUtil.mutListener.listen(67150)) {
            {
                long _loopCounter835 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(67149) ? (i >= jsonFeatureMasks.length()) : (ListenerUtil.mutListener.listen(67148) ? (i <= jsonFeatureMasks.length()) : (ListenerUtil.mutListener.listen(67147) ? (i > jsonFeatureMasks.length()) : (ListenerUtil.mutListener.listen(67146) ? (i != jsonFeatureMasks.length()) : (ListenerUtil.mutListener.listen(67145) ? (i == jsonFeatureMasks.length()) : (i < jsonFeatureMasks.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter835", ++_loopCounter835);
                    if (!ListenerUtil.mutListener.listen(67144)) {
                        if (jsonFeatureMasks.isNull(i)) {
                            if (!ListenerUtil.mutListener.listen(67143)) {
                                featureMasks[i] = null;
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(67142)) {
                                featureMasks[i] = jsonFeatureMasks.getInt(i);
                            }
                        }
                    }
                }
            }
        }
        return new CheckIdentityStatesResult(states, types, identities, interval, featureMasks);
    }

    /**
     *  Obtain temporary TURN server URLs and credentials, e.g. for use with VoIP.
     *
     *  @param identityStore The identity store to use for authentication
     *  @param type The desired TURN server type (usually "voip").
     *  @return TURN server info
     *  @throws Exception If servers could not be obtained
     */
    public TurnServerInfo obtainTurnServers(IdentityStoreInterface identityStore, String type) throws Exception {
        if (!ListenerUtil.mutListener.listen(67153)) {
            if ((ListenerUtil.mutListener.listen(67152) ? ((ListenerUtil.mutListener.listen(67151) ? (identityStore == null && identityStore.getIdentity() == null) : (identityStore == null || identityStore.getIdentity() == null)) && identityStore.getIdentity().length() == 0) : ((ListenerUtil.mutListener.listen(67151) ? (identityStore == null && identityStore.getIdentity() == null) : (identityStore == null || identityStore.getIdentity() == null)) || identityStore.getIdentity().length() == 0))) {
                return null;
            }
        }
        String url = serverUrl + "identity/turn_cred";
        /* phase 1: send identity and type */
        JSONObject request = new JSONObject();
        if (!ListenerUtil.mutListener.listen(67154)) {
            request.put("identity", identityStore.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(67155)) {
            request.put("type", type);
        }
        if (!ListenerUtil.mutListener.listen(67156)) {
            logger.debug("Obtain TURN servers phase 1: sending to server: {}", request);
        }
        JSONObject p1Result = new JSONObject(doPost(url, request.toString()));
        if (!ListenerUtil.mutListener.listen(67157)) {
            logger.debug("Obtain TURN servers phase 1: response from server: {}", p1Result);
        }
        if (!ListenerUtil.mutListener.listen(67158)) {
            makeTokenResponse(p1Result, request, identityStore);
        }
        if (!ListenerUtil.mutListener.listen(67159)) {
            /* phase 2: send token response */
            logger.debug("Obtain TURN servers phase 2: sending to server: {}", request);
        }
        JSONObject p2Result = new JSONObject(doPost(url, request.toString()));
        if (!ListenerUtil.mutListener.listen(67160)) {
            logger.debug("Obtain TURN servers phase 2: response from server: {}", p2Result);
        }
        if (!ListenerUtil.mutListener.listen(67161)) {
            if (!p2Result.getBoolean("success"))
                throw new ThreemaException(p2Result.getString("error"));
        }
        String[] turnUrls = jsonArrayToStringArray(p2Result.getJSONArray("turnUrls"));
        String[] turnUrlsDualStack = jsonArrayToStringArray(p2Result.getJSONArray("turnUrlsDualStack"));
        String turnUsername = p2Result.getString("turnUsername");
        String turnPassword = p2Result.getString("turnPassword");
        int expiration = p2Result.getInt("expiration");
        Date expirationDate = new Date((ListenerUtil.mutListener.listen(67169) ? (new Date().getTime() % (ListenerUtil.mutListener.listen(67165) ? (expiration % 1000) : (ListenerUtil.mutListener.listen(67164) ? (expiration / 1000) : (ListenerUtil.mutListener.listen(67163) ? (expiration - 1000) : (ListenerUtil.mutListener.listen(67162) ? (expiration + 1000) : (expiration * 1000)))))) : (ListenerUtil.mutListener.listen(67168) ? (new Date().getTime() / (ListenerUtil.mutListener.listen(67165) ? (expiration % 1000) : (ListenerUtil.mutListener.listen(67164) ? (expiration / 1000) : (ListenerUtil.mutListener.listen(67163) ? (expiration - 1000) : (ListenerUtil.mutListener.listen(67162) ? (expiration + 1000) : (expiration * 1000)))))) : (ListenerUtil.mutListener.listen(67167) ? (new Date().getTime() * (ListenerUtil.mutListener.listen(67165) ? (expiration % 1000) : (ListenerUtil.mutListener.listen(67164) ? (expiration / 1000) : (ListenerUtil.mutListener.listen(67163) ? (expiration - 1000) : (ListenerUtil.mutListener.listen(67162) ? (expiration + 1000) : (expiration * 1000)))))) : (ListenerUtil.mutListener.listen(67166) ? (new Date().getTime() - (ListenerUtil.mutListener.listen(67165) ? (expiration % 1000) : (ListenerUtil.mutListener.listen(67164) ? (expiration / 1000) : (ListenerUtil.mutListener.listen(67163) ? (expiration - 1000) : (ListenerUtil.mutListener.listen(67162) ? (expiration + 1000) : (expiration * 1000)))))) : (new Date().getTime() + (ListenerUtil.mutListener.listen(67165) ? (expiration % 1000) : (ListenerUtil.mutListener.listen(67164) ? (expiration / 1000) : (ListenerUtil.mutListener.listen(67163) ? (expiration - 1000) : (ListenerUtil.mutListener.listen(67162) ? (expiration + 1000) : (expiration * 1000)))))))))));
        return new TurnServerInfo(turnUrls, turnUrlsDualStack, turnUsername, turnPassword, expirationDate);
    }

    private String[] jsonArrayToStringArray(JSONArray jsonArray) throws JSONException {
        String[] stringArray = new String[jsonArray.length()];
        if (!ListenerUtil.mutListener.listen(67176)) {
            {
                long _loopCounter836 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(67175) ? (i >= jsonArray.length()) : (ListenerUtil.mutListener.listen(67174) ? (i <= jsonArray.length()) : (ListenerUtil.mutListener.listen(67173) ? (i > jsonArray.length()) : (ListenerUtil.mutListener.listen(67172) ? (i != jsonArray.length()) : (ListenerUtil.mutListener.listen(67171) ? (i == jsonArray.length()) : (i < jsonArray.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter836", ++_loopCounter836);
                    if (!ListenerUtil.mutListener.listen(67170)) {
                        stringArray[i] = jsonArray.getString(i);
                    }
                }
            }
        }
        return stringArray;
    }

    /**
     *  Check a license key for direct distribution.
     *
     *  This will implicitly check for updates as well.
     *
     *  @param licenseKey the license key (format: XXXXX-XXXXX where X = A-Z/0-9)
     *  @param deviceId   unique device ID
     *  @return result of license check (success status, error message if success = false)
     *  @throws Exception on network error
     */
    public CheckLicenseResult checkLicense(String licenseKey, String deviceId) throws Exception {
        JSONObject request = new JSONObject();
        if (!ListenerUtil.mutListener.listen(67177)) {
            request.put("licenseKey", licenseKey);
        }
        return this.checkLicense(request, deviceId);
    }

    /**
     *  Check a username/password for direct distribution (work only).
     *
     *  @param username the license username
     *  @param password the license password
     *  @param deviceId unique device ID
     *  @return result of license check (success status, error message if success = false)
     *  @throws Exception on network error
     */
    public CheckLicenseResult checkLicense(String username, String password, String deviceId) throws Exception {
        JSONObject request = new JSONObject();
        if (!ListenerUtil.mutListener.listen(67178)) {
            request.put("licenseUsername", username);
        }
        if (!ListenerUtil.mutListener.listen(67179)) {
            request.put("licensePassword", password);
        }
        return this.checkLicense(request, deviceId);
    }

    /**
     *  Check license for direct distribution
     *
     *  @param request  prefilled json request object
     *  @param deviceId unique device ID
     *  @return result of license check (success status, error message if success = false)
     *  @throws Exception on network error
     */
    private CheckLicenseResult checkLicense(JSONObject request, String deviceId) throws Exception {
        String url = serverUrl + "check_license";
        if (!ListenerUtil.mutListener.listen(67180)) {
            request.put("deviceId", deviceId);
        }
        if (!ListenerUtil.mutListener.listen(67181)) {
            request.put("version", version.getFullVersion());
        }
        if (!ListenerUtil.mutListener.listen(67182)) {
            request.put("arch", version.getArchitecture());
        }
        JSONObject result = new JSONObject(doPost(url, request.toString()));
        CheckLicenseResult checkLicenseResult = new CheckLicenseResult();
        if (!ListenerUtil.mutListener.listen(67194)) {
            if (result.getBoolean("success")) {
                if (!ListenerUtil.mutListener.listen(67185)) {
                    checkLicenseResult.success = true;
                }
                if (!ListenerUtil.mutListener.listen(67187)) {
                    if (result.has("updateMessage"))
                        if (!ListenerUtil.mutListener.listen(67186)) {
                            checkLicenseResult.updateMessage = result.getString("updateMessage");
                        }
                }
                if (!ListenerUtil.mutListener.listen(67189)) {
                    if (result.has("updateUrl"))
                        if (!ListenerUtil.mutListener.listen(67188)) {
                            checkLicenseResult.updateUrl = result.getString("updateUrl");
                        }
                }
                if (!ListenerUtil.mutListener.listen(67191)) {
                    if (result.has("logoLightUrl"))
                        if (!ListenerUtil.mutListener.listen(67190)) {
                            checkLicenseResult.logoLightUrl = result.getString("logoLightUrl");
                        }
                }
                if (!ListenerUtil.mutListener.listen(67193)) {
                    if (result.has("logoDarkUrl"))
                        if (!ListenerUtil.mutListener.listen(67192)) {
                            checkLicenseResult.logoDarkUrl = result.getString("logoDarkUrl");
                        }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(67183)) {
                    checkLicenseResult.success = false;
                }
                if (!ListenerUtil.mutListener.listen(67184)) {
                    checkLicenseResult.error = result.getString("error");
                }
            }
        }
        return checkLicenseResult;
    }

    /**
     *  Fetch all custom work data from work api
     *
     *  @param username
     *  @param password
     *  @param identities (list of existing threema id
     *  @return
     *  @throws IOException
     *  @throws JSONException
     */
    public WorkData fetchWorkData(String username, String password, String[] identities) throws Exception {
        WorkData workData = new WorkData();
        JSONObject request = new JSONObject();
        if (!ListenerUtil.mutListener.listen(67195)) {
            request.put("username", username);
        }
        if (!ListenerUtil.mutListener.listen(67196)) {
            request.put("password", password);
        }
        JSONArray identityArray = new JSONArray();
        if (!ListenerUtil.mutListener.listen(67198)) {
            {
                long _loopCounter837 = 0;
                for (String identity : identities) {
                    ListenerUtil.loopListener.listen("_loopCounter837", ++_loopCounter837);
                    if (!ListenerUtil.mutListener.listen(67197)) {
                        identityArray.put(identity);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(67199)) {
            request.put("contacts", identityArray);
        }
        String data = doPost(this.workServerUrl + "fetch2", request.toString());
        if (!ListenerUtil.mutListener.listen(67206)) {
            if ((ListenerUtil.mutListener.listen(67205) ? (data == null && (ListenerUtil.mutListener.listen(67204) ? (data.length() >= 0) : (ListenerUtil.mutListener.listen(67203) ? (data.length() <= 0) : (ListenerUtil.mutListener.listen(67202) ? (data.length() > 0) : (ListenerUtil.mutListener.listen(67201) ? (data.length() < 0) : (ListenerUtil.mutListener.listen(67200) ? (data.length() != 0) : (data.length() == 0))))))) : (data == null || (ListenerUtil.mutListener.listen(67204) ? (data.length() >= 0) : (ListenerUtil.mutListener.listen(67203) ? (data.length() <= 0) : (ListenerUtil.mutListener.listen(67202) ? (data.length() > 0) : (ListenerUtil.mutListener.listen(67201) ? (data.length() < 0) : (ListenerUtil.mutListener.listen(67200) ? (data.length() != 0) : (data.length() == 0))))))))) {
                return workData;
            }
        }
        JSONObject jsonResponse = new JSONObject(data);
        if (!ListenerUtil.mutListener.listen(67209)) {
            if ((ListenerUtil.mutListener.listen(67207) ? (jsonResponse.has("support") || !jsonResponse.isNull("support")) : (jsonResponse.has("support") && !jsonResponse.isNull("support")))) {
                if (!ListenerUtil.mutListener.listen(67208)) {
                    workData.supportUrl = jsonResponse.getString("support");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(67216)) {
            if (jsonResponse.has("logo")) {
                final JSONObject logos = jsonResponse.getJSONObject("logo");
                if (!ListenerUtil.mutListener.listen(67212)) {
                    if ((ListenerUtil.mutListener.listen(67210) ? (logos.has("dark") || !logos.isNull("dark")) : (logos.has("dark") && !logos.isNull("dark")))) {
                        if (!ListenerUtil.mutListener.listen(67211)) {
                            workData.logoDark = logos.getString("dark");
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(67215)) {
                    if ((ListenerUtil.mutListener.listen(67213) ? (logos.has("light") || !logos.isNull("light")) : (logos.has("light") && !logos.isNull("light")))) {
                        if (!ListenerUtil.mutListener.listen(67214)) {
                            workData.logoLight = logos.getString("light");
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(67217)) {
            workData.checkInterval = (jsonResponse.has("checkInterval") ? jsonResponse.getInt("checkInterval") : 0);
        }
        if (!ListenerUtil.mutListener.listen(67227)) {
            if (jsonResponse.has("contacts")) {
                JSONArray contacts = jsonResponse.getJSONArray("contacts");
                if (!ListenerUtil.mutListener.listen(67226)) {
                    {
                        long _loopCounter838 = 0;
                        for (int n = 0; (ListenerUtil.mutListener.listen(67225) ? (n >= contacts.length()) : (ListenerUtil.mutListener.listen(67224) ? (n <= contacts.length()) : (ListenerUtil.mutListener.listen(67223) ? (n > contacts.length()) : (ListenerUtil.mutListener.listen(67222) ? (n != contacts.length()) : (ListenerUtil.mutListener.listen(67221) ? (n == contacts.length()) : (n < contacts.length())))))); n++) {
                            ListenerUtil.loopListener.listen("_loopCounter838", ++_loopCounter838);
                            JSONObject contact = contacts.getJSONObject(n);
                            if (!ListenerUtil.mutListener.listen(67220)) {
                                // validate fields
                                if ((ListenerUtil.mutListener.listen(67218) ? (contact.has("id") || contact.has("pk")) : (contact.has("id") && contact.has("pk")))) {
                                    if (!ListenerUtil.mutListener.listen(67219)) {
                                        workData.workContacts.add(new WorkContact(contact.getString("id"), Base64.decode(contact.getString("pk")), contact.has("first") ? contact.getString("first") : null, contact.has("last") ? contact.getString("last") : null));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(67232)) {
            if (jsonResponse.has("mdm")) {
                JSONObject jsonMDM = jsonResponse.getJSONObject("mdm");
                if (!ListenerUtil.mutListener.listen(67228)) {
                    workData.mdm.override = jsonMDM.optBoolean("override", false);
                }
                if (!ListenerUtil.mutListener.listen(67231)) {
                    if (jsonMDM.has("params")) {
                        JSONObject jsonMAMParameters = jsonMDM.getJSONObject("params");
                        Iterator<String> keys = jsonMAMParameters.keys();
                        if (!ListenerUtil.mutListener.listen(67230)) {
                            {
                                long _loopCounter839 = 0;
                                while (keys.hasNext()) {
                                    ListenerUtil.loopListener.listen("_loopCounter839", ++_loopCounter839);
                                    String currentKey = keys.next();
                                    if (!ListenerUtil.mutListener.listen(67229)) {
                                        workData.mdm.parameters.put(currentKey, jsonMAMParameters.get(currentKey));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        // Since Release: work-directory
        JSONObject jsonResponseOrganization = jsonResponse.optJSONObject("org");
        if (!ListenerUtil.mutListener.listen(67234)) {
            if (jsonResponseOrganization != null) {
                if (!ListenerUtil.mutListener.listen(67233)) {
                    workData.organization.name = jsonResponseOrganization.isNull("name") ? null : jsonResponseOrganization.optString("name");
                }
            }
        }
        JSONObject directory = jsonResponse.optJSONObject("directory");
        if (!ListenerUtil.mutListener.listen(67239)) {
            if (directory != null) {
                if (!ListenerUtil.mutListener.listen(67235)) {
                    workData.directory.enabled = directory.optBoolean("enabled", false);
                }
                JSONObject categories = directory.optJSONObject("cat");
                if (!ListenerUtil.mutListener.listen(67238)) {
                    if (categories != null) {
                        Iterator<String> keys = categories.keys();
                        if (!ListenerUtil.mutListener.listen(67237)) {
                            {
                                long _loopCounter840 = 0;
                                while (keys.hasNext()) {
                                    ListenerUtil.loopListener.listen("_loopCounter840", ++_loopCounter840);
                                    String categoryId = keys.next();
                                    if (!ListenerUtil.mutListener.listen(67236)) {
                                        workData.directory.categories.add(new WorkDirectoryCategory(categoryId, categories.getString(categoryId)));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return workData;
    }

    /**
     *  Fetch work contacts from work api
     *
     *  @param username (threema work license username)
     *  @param password (threema work license password)
     *  @param identities (list of threema id to check)
     *  @return list of valid threema work contacts - empty list if there are no matching contacts in this package
     *  @throws IOException
     *  @throws JSONException
     */
    @NonNull
    public List<WorkContact> fetchWorkContacts(@NonNull String username, @NonNull String password, @NonNull String[] identities) throws Exception {
        List<WorkContact> contactsList = new ArrayList<>();
        JSONObject request = new JSONObject();
        if (!ListenerUtil.mutListener.listen(67240)) {
            request.put("username", username);
        }
        if (!ListenerUtil.mutListener.listen(67241)) {
            request.put("password", password);
        }
        JSONArray identityArray = new JSONArray();
        if (!ListenerUtil.mutListener.listen(67243)) {
            {
                long _loopCounter841 = 0;
                for (String identity : identities) {
                    ListenerUtil.loopListener.listen("_loopCounter841", ++_loopCounter841);
                    if (!ListenerUtil.mutListener.listen(67242)) {
                        identityArray.put(identity);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(67244)) {
            request.put("contacts", identityArray);
        }
        String data = doPost(this.workServerUrl + "identities", request.toString());
        if (!ListenerUtil.mutListener.listen(67251)) {
            if ((ListenerUtil.mutListener.listen(67250) ? (data == null && (ListenerUtil.mutListener.listen(67249) ? (data.length() >= 0) : (ListenerUtil.mutListener.listen(67248) ? (data.length() <= 0) : (ListenerUtil.mutListener.listen(67247) ? (data.length() > 0) : (ListenerUtil.mutListener.listen(67246) ? (data.length() < 0) : (ListenerUtil.mutListener.listen(67245) ? (data.length() != 0) : (data.length() == 0))))))) : (data == null || (ListenerUtil.mutListener.listen(67249) ? (data.length() >= 0) : (ListenerUtil.mutListener.listen(67248) ? (data.length() <= 0) : (ListenerUtil.mutListener.listen(67247) ? (data.length() > 0) : (ListenerUtil.mutListener.listen(67246) ? (data.length() < 0) : (ListenerUtil.mutListener.listen(67245) ? (data.length() != 0) : (data.length() == 0))))))))) {
                return contactsList;
            }
        }
        JSONObject jsonResponse = new JSONObject(data);
        if (!ListenerUtil.mutListener.listen(67261)) {
            if (jsonResponse.has("contacts")) {
                JSONArray contacts = jsonResponse.getJSONArray("contacts");
                if (!ListenerUtil.mutListener.listen(67260)) {
                    {
                        long _loopCounter842 = 0;
                        for (int n = 0; (ListenerUtil.mutListener.listen(67259) ? (n >= contacts.length()) : (ListenerUtil.mutListener.listen(67258) ? (n <= contacts.length()) : (ListenerUtil.mutListener.listen(67257) ? (n > contacts.length()) : (ListenerUtil.mutListener.listen(67256) ? (n != contacts.length()) : (ListenerUtil.mutListener.listen(67255) ? (n == contacts.length()) : (n < contacts.length())))))); n++) {
                            ListenerUtil.loopListener.listen("_loopCounter842", ++_loopCounter842);
                            JSONObject contact = contacts.getJSONObject(n);
                            if (!ListenerUtil.mutListener.listen(67254)) {
                                // validate fields
                                if ((ListenerUtil.mutListener.listen(67252) ? (contact.has("id") || contact.has("pk")) : (contact.has("id") && contact.has("pk")))) {
                                    if (!ListenerUtil.mutListener.listen(67253)) {
                                        contactsList.add(new WorkContact(contact.getString("id"), Base64.decode(contact.getString("pk")), contact.isNull("first") ? null : contact.getString("first"), contact.isNull("last") ? null : contact.getString("last")));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return contactsList;
    }

    /**
     *  Search the threema work directory without categories
     *
     *  @param username
     *  @param password
     *  @param filter
     *  @return Can be null
     *  @throws IOException
     *  @throws JSONException
     */
    public WorkDirectory fetchWorkDirectory(String username, String password, IdentityStoreInterface identityStore, WorkDirectoryFilter filter) throws Exception {
        JSONObject request = new JSONObject();
        if (!ListenerUtil.mutListener.listen(67262)) {
            request.put("username", username);
        }
        if (!ListenerUtil.mutListener.listen(67263)) {
            request.put("password", password);
        }
        if (!ListenerUtil.mutListener.listen(67264)) {
            request.put("identity", identityStore.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(67265)) {
            request.put("query", filter.getQuery());
        }
        if (!ListenerUtil.mutListener.listen(67275)) {
            // Filter category
            if ((ListenerUtil.mutListener.listen(67271) ? (filter.getCategories() != null || (ListenerUtil.mutListener.listen(67270) ? (filter.getCategories().size() >= 0) : (ListenerUtil.mutListener.listen(67269) ? (filter.getCategories().size() <= 0) : (ListenerUtil.mutListener.listen(67268) ? (filter.getCategories().size() < 0) : (ListenerUtil.mutListener.listen(67267) ? (filter.getCategories().size() != 0) : (ListenerUtil.mutListener.listen(67266) ? (filter.getCategories().size() == 0) : (filter.getCategories().size() > 0))))))) : (filter.getCategories() != null && (ListenerUtil.mutListener.listen(67270) ? (filter.getCategories().size() >= 0) : (ListenerUtil.mutListener.listen(67269) ? (filter.getCategories().size() <= 0) : (ListenerUtil.mutListener.listen(67268) ? (filter.getCategories().size() < 0) : (ListenerUtil.mutListener.listen(67267) ? (filter.getCategories().size() != 0) : (ListenerUtil.mutListener.listen(67266) ? (filter.getCategories().size() == 0) : (filter.getCategories().size() > 0))))))))) {
                JSONArray jsonCategories = new JSONArray();
                if (!ListenerUtil.mutListener.listen(67273)) {
                    {
                        long _loopCounter843 = 0;
                        for (WorkDirectoryCategory category : filter.getCategories()) {
                            ListenerUtil.loopListener.listen("_loopCounter843", ++_loopCounter843);
                            if (!ListenerUtil.mutListener.listen(67272)) {
                                jsonCategories.put(category.id);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(67274)) {
                    request.put("categories", jsonCategories);
                }
            }
        }
        // Sorting
        JSONObject jsonSort = new JSONObject();
        if (!ListenerUtil.mutListener.listen(67276)) {
            jsonSort.put("asc", filter.isSortAscending());
        }
        if (!ListenerUtil.mutListener.listen(67279)) {
            switch(filter.getSortBy()) {
                case WorkDirectoryFilter.SORT_BY_LAST_NAME:
                    if (!ListenerUtil.mutListener.listen(67277)) {
                        jsonSort.put("by", "lastName");
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(67278)) {
                        jsonSort.put("by", "firstName");
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(67280)) {
            request.put("sort", jsonSort);
        }
        if (!ListenerUtil.mutListener.listen(67281)) {
            // Paging
            request.put("page", filter.getPage());
        }
        String data = doPost(workServerUrl + "directory", request.toString());
        if (!ListenerUtil.mutListener.listen(67288)) {
            // Verify request
            if ((ListenerUtil.mutListener.listen(67287) ? (data == null && (ListenerUtil.mutListener.listen(67286) ? (data.length() >= 0) : (ListenerUtil.mutListener.listen(67285) ? (data.length() <= 0) : (ListenerUtil.mutListener.listen(67284) ? (data.length() > 0) : (ListenerUtil.mutListener.listen(67283) ? (data.length() < 0) : (ListenerUtil.mutListener.listen(67282) ? (data.length() != 0) : (data.length() == 0))))))) : (data == null || (ListenerUtil.mutListener.listen(67286) ? (data.length() >= 0) : (ListenerUtil.mutListener.listen(67285) ? (data.length() <= 0) : (ListenerUtil.mutListener.listen(67284) ? (data.length() > 0) : (ListenerUtil.mutListener.listen(67283) ? (data.length() < 0) : (ListenerUtil.mutListener.listen(67282) ? (data.length() != 0) : (data.length() == 0))))))))) {
                // Return null
                return null;
            }
        }
        JSONObject jsonResponse = new JSONObject(data);
        if (!ListenerUtil.mutListener.listen(67323)) {
            if ((ListenerUtil.mutListener.listen(67289) ? (jsonResponse.has("contacts") || !jsonResponse.isNull("contacts")) : (jsonResponse.has("contacts") && !jsonResponse.isNull("contacts")))) {
                // Verify content
                JSONArray contacts = jsonResponse.getJSONArray("contacts");
                int total = contacts.length();
                int pageSize = total;
                WorkDirectoryFilter filterNext = null;
                WorkDirectoryFilter filterPrevious = null;
                if (!ListenerUtil.mutListener.listen(67301)) {
                    if ((ListenerUtil.mutListener.listen(67290) ? (jsonResponse.has("paging") || !jsonResponse.isNull("paging")) : (jsonResponse.has("paging") && !jsonResponse.isNull("paging")))) {
                        JSONObject paging = jsonResponse.getJSONObject("paging");
                        if (!ListenerUtil.mutListener.listen(67291)) {
                            pageSize = paging.optInt("size", pageSize);
                        }
                        if (!ListenerUtil.mutListener.listen(67292)) {
                            total = paging.optInt("total", total);
                        }
                        if (!ListenerUtil.mutListener.listen(67294)) {
                            if (paging.has("next")) {
                                if (!ListenerUtil.mutListener.listen(67293)) {
                                    // Next filter
                                    filterNext = filter.copy().page(jsonResponse.optInt("next", filter.getPage() + 1));
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(67300)) {
                            if (paging.has("prev")) {
                                if (!ListenerUtil.mutListener.listen(67299)) {
                                    // Next filter
                                    filterPrevious = filter.copy().page(jsonResponse.optInt("prev", (ListenerUtil.mutListener.listen(67298) ? (filter.getPage() % 1) : (ListenerUtil.mutListener.listen(67297) ? (filter.getPage() / 1) : (ListenerUtil.mutListener.listen(67296) ? (filter.getPage() * 1) : (ListenerUtil.mutListener.listen(67295) ? (filter.getPage() + 1) : (filter.getPage() - 1)))))));
                                }
                            }
                        }
                    }
                }
                WorkDirectory workDirectory = new WorkDirectory(total, pageSize, filter, filterNext, filterPrevious);
                if (!ListenerUtil.mutListener.listen(67322)) {
                    {
                        long _loopCounter845 = 0;
                        for (int n = 0; (ListenerUtil.mutListener.listen(67321) ? (n >= contacts.length()) : (ListenerUtil.mutListener.listen(67320) ? (n <= contacts.length()) : (ListenerUtil.mutListener.listen(67319) ? (n > contacts.length()) : (ListenerUtil.mutListener.listen(67318) ? (n != contacts.length()) : (ListenerUtil.mutListener.listen(67317) ? (n == contacts.length()) : (n < contacts.length())))))); n++) {
                            ListenerUtil.loopListener.listen("_loopCounter845", ++_loopCounter845);
                            JSONObject contact = contacts.getJSONObject(n);
                            if (!ListenerUtil.mutListener.listen(67316)) {
                                // validate fields
                                if ((ListenerUtil.mutListener.listen(67302) ? (contact.has("id") || contact.has("pk")) : (contact.has("id") && contact.has("pk")))) {
                                    WorkDirectoryContact directoryContact = new WorkDirectoryContact(contact.getString("id"), Base64.decode(contact.getString("pk")), contact.has("first") ? contact.optString("first") : null, contact.has("last") ? contact.optString("last") : null, contact.has("csi") ? contact.optString("csi") : null);
                                    if (!ListenerUtil.mutListener.listen(67306)) {
                                        if (!contact.isNull("org")) {
                                            JSONObject jsonResponseOrganization = contact.optJSONObject("org");
                                            if (!ListenerUtil.mutListener.listen(67305)) {
                                                if ((ListenerUtil.mutListener.listen(67303) ? (jsonResponseOrganization != null || !jsonResponseOrganization.isNull("name")) : (jsonResponseOrganization != null && !jsonResponseOrganization.isNull("name")))) {
                                                    if (!ListenerUtil.mutListener.listen(67304)) {
                                                        directoryContact.organization.name = jsonResponseOrganization.optString("name");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    JSONArray categoryArray = contact.optJSONArray("cat");
                                    if (!ListenerUtil.mutListener.listen(67314)) {
                                        if (categoryArray != null) {
                                            if (!ListenerUtil.mutListener.listen(67313)) {
                                                {
                                                    long _loopCounter844 = 0;
                                                    for (int cN = 0; (ListenerUtil.mutListener.listen(67312) ? (cN >= categoryArray.length()) : (ListenerUtil.mutListener.listen(67311) ? (cN <= categoryArray.length()) : (ListenerUtil.mutListener.listen(67310) ? (cN > categoryArray.length()) : (ListenerUtil.mutListener.listen(67309) ? (cN != categoryArray.length()) : (ListenerUtil.mutListener.listen(67308) ? (cN == categoryArray.length()) : (cN < categoryArray.length())))))); cN++) {
                                                        ListenerUtil.loopListener.listen("_loopCounter844", ++_loopCounter844);
                                                        if (!ListenerUtil.mutListener.listen(67307)) {
                                                            directoryContact.categoryIds.add(categoryArray.getString(cN));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(67315)) {
                                        workDirectory.workContacts.add(directoryContact);
                                    }
                                }
                            }
                        }
                    }
                }
                return workDirectory;
            }
        }
        // Invalid request
        return null;
    }

    /**
     *  Update work info of a license
     *
     *  @param username      the license username
     *  @param password      the license password
     *  @param identityStore store of the work identity
     *  @param firstName from MDM property th_firstname
     *  @param lastName from MDM property th_lastname
     *  @param csi from MDM property th_csi
     *  @param category from MDM property th_category
     *  @return result of license check (success status, error message if success = false)
     *  @throws Exception on network error
     */
    public boolean updateWorkInfo(String username, String password, IdentityStoreInterface identityStore, String firstName, String lastName, String csi, String category) throws Exception {
        String url = serverUrl + "identity/update_work_info";
        JSONObject request = new JSONObject();
        if (!ListenerUtil.mutListener.listen(67324)) {
            request.put("licenseUsername", username);
        }
        if (!ListenerUtil.mutListener.listen(67325)) {
            request.put("licensePassword", password);
        }
        if (!ListenerUtil.mutListener.listen(67326)) {
            request.put("identity", identityStore.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(67327)) {
            request.put("publicNickname", identityStore.getPublicNickname());
        }
        if (!ListenerUtil.mutListener.listen(67328)) {
            request.put("version", version.getFullVersion());
        }
        if (!ListenerUtil.mutListener.listen(67330)) {
            if (firstName != null) {
                if (!ListenerUtil.mutListener.listen(67329)) {
                    request.put("firstName", firstName);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(67332)) {
            if (lastName != null) {
                if (!ListenerUtil.mutListener.listen(67331)) {
                    request.put("lastName", lastName);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(67334)) {
            if (csi != null) {
                if (!ListenerUtil.mutListener.listen(67333)) {
                    request.put("csi", csi);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(67336)) {
            if (category != null) {
                if (!ListenerUtil.mutListener.listen(67335)) {
                    request.put("category", category);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(67337)) {
            logger.debug("Update work info phase 1: sending to server: {}", request);
        }
        JSONObject p1Result = new JSONObject(doPost(url, request.toString()));
        if (!ListenerUtil.mutListener.listen(67338)) {
            logger.debug("Update work info phase 1: response from server: {}", p1Result);
        }
        if (!ListenerUtil.mutListener.listen(67339)) {
            makeTokenResponse(p1Result, request, identityStore);
        }
        if (!ListenerUtil.mutListener.listen(67340)) {
            /* phase 2: send token response */
            logger.debug("Update work info phase 2: sending to server: {}", request);
        }
        JSONObject p2Result = new JSONObject(doPost(url, request.toString()));
        if (!ListenerUtil.mutListener.listen(67341)) {
            logger.debug("Update work info phase 2: response from server: {}", p2Result);
        }
        if (!ListenerUtil.mutListener.listen(67342)) {
            if (!p2Result.getBoolean("success")) {
                throw new UpdateWorkInfoException(p2Result.getString("error"));
            }
        }
        return true;
    }

    public int getMatchCheckInterval() {
        return matchCheckInterval;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        if (!ListenerUtil.mutListener.listen(67343)) {
            this.version = version;
        }
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        if (!ListenerUtil.mutListener.listen(67344)) {
            this.language = language;
        }
    }

    public void setServerUrls(boolean ipv6) {
        if (!ListenerUtil.mutListener.listen(67349)) {
            if (this.sandbox) {
                if (!ListenerUtil.mutListener.listen(67347)) {
                    this.serverUrl = ipv6 ? ProtocolStrings.API_SERVER_URL_SANDBOX_IPV6 : ProtocolStrings.API_SERVER_URL_SANDBOX;
                }
                if (!ListenerUtil.mutListener.listen(67348)) {
                    this.workServerUrl = ipv6 ? ProtocolStrings.WORK_SERVER_URL_SANDBOX_IPV6 : ProtocolStrings.WORK_SERVER_URL_SANDBOX;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(67345)) {
                    this.serverUrl = ipv6 ? ProtocolStrings.API_SERVER_URL_IPV6 : ProtocolStrings.API_SERVER_URL;
                }
                if (!ListenerUtil.mutListener.listen(67346)) {
                    this.workServerUrl = ipv6 ? ProtocolStrings.WORK_SERVER_URL_IPV6 : ProtocolStrings.WORK_SERVER_URL;
                }
            }
        }
    }

    protected String doGet(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        if (!ListenerUtil.mutListener.listen(67350)) {
            urlConnection.setSSLSocketFactory(this.sslSocketFactoryFactory.makeFactory(url.getHost()));
        }
        if (!ListenerUtil.mutListener.listen(67355)) {
            urlConnection.setConnectTimeout((ListenerUtil.mutListener.listen(67354) ? (ProtocolDefines.API_REQUEST_TIMEOUT % 1000) : (ListenerUtil.mutListener.listen(67353) ? (ProtocolDefines.API_REQUEST_TIMEOUT / 1000) : (ListenerUtil.mutListener.listen(67352) ? (ProtocolDefines.API_REQUEST_TIMEOUT - 1000) : (ListenerUtil.mutListener.listen(67351) ? (ProtocolDefines.API_REQUEST_TIMEOUT + 1000) : (ProtocolDefines.API_REQUEST_TIMEOUT * 1000))))));
        }
        if (!ListenerUtil.mutListener.listen(67360)) {
            urlConnection.setReadTimeout((ListenerUtil.mutListener.listen(67359) ? (ProtocolDefines.API_REQUEST_TIMEOUT % 1000) : (ListenerUtil.mutListener.listen(67358) ? (ProtocolDefines.API_REQUEST_TIMEOUT / 1000) : (ListenerUtil.mutListener.listen(67357) ? (ProtocolDefines.API_REQUEST_TIMEOUT - 1000) : (ListenerUtil.mutListener.listen(67356) ? (ProtocolDefines.API_REQUEST_TIMEOUT + 1000) : (ProtocolDefines.API_REQUEST_TIMEOUT * 1000))))));
        }
        if (!ListenerUtil.mutListener.listen(67361)) {
            urlConnection.setRequestMethod("GET");
        }
        if (!ListenerUtil.mutListener.listen(67362)) {
            urlConnection.setRequestProperty("User-Agent", ProtocolStrings.USER_AGENT + "/" + version.getVersion());
        }
        if (!ListenerUtil.mutListener.listen(67364)) {
            if (language != null) {
                if (!ListenerUtil.mutListener.listen(67363)) {
                    urlConnection.setRequestProperty("Accept-Language", language);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(67365)) {
            urlConnection.setDoOutput(false);
        }
        if (!ListenerUtil.mutListener.listen(67366)) {
            urlConnection.setDoInput(true);
        }
        try {
            return IOUtils.toString(urlConnection.getInputStream(), StandardCharsets.UTF_8);
        } finally {
            if (!ListenerUtil.mutListener.listen(67367)) {
                urlConnection.disconnect();
            }
        }
    }

    protected String doPost(String urlStr, String body) throws Exception {
        URL url = new URL(urlStr);
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        if (!ListenerUtil.mutListener.listen(67368)) {
            urlConnection.setSSLSocketFactory(this.sslSocketFactoryFactory.makeFactory(url.getHost()));
        }
        if (!ListenerUtil.mutListener.listen(67373)) {
            urlConnection.setConnectTimeout((ListenerUtil.mutListener.listen(67372) ? (ProtocolDefines.API_REQUEST_TIMEOUT % 1000) : (ListenerUtil.mutListener.listen(67371) ? (ProtocolDefines.API_REQUEST_TIMEOUT / 1000) : (ListenerUtil.mutListener.listen(67370) ? (ProtocolDefines.API_REQUEST_TIMEOUT - 1000) : (ListenerUtil.mutListener.listen(67369) ? (ProtocolDefines.API_REQUEST_TIMEOUT + 1000) : (ProtocolDefines.API_REQUEST_TIMEOUT * 1000))))));
        }
        if (!ListenerUtil.mutListener.listen(67378)) {
            urlConnection.setReadTimeout((ListenerUtil.mutListener.listen(67377) ? (ProtocolDefines.API_REQUEST_TIMEOUT % 1000) : (ListenerUtil.mutListener.listen(67376) ? (ProtocolDefines.API_REQUEST_TIMEOUT / 1000) : (ListenerUtil.mutListener.listen(67375) ? (ProtocolDefines.API_REQUEST_TIMEOUT - 1000) : (ListenerUtil.mutListener.listen(67374) ? (ProtocolDefines.API_REQUEST_TIMEOUT + 1000) : (ProtocolDefines.API_REQUEST_TIMEOUT * 1000))))));
        }
        if (!ListenerUtil.mutListener.listen(67379)) {
            urlConnection.setRequestMethod("POST");
        }
        if (!ListenerUtil.mutListener.listen(67380)) {
            urlConnection.setRequestProperty("Content-Type", "application/json");
        }
        if (!ListenerUtil.mutListener.listen(67381)) {
            urlConnection.setRequestProperty("User-Agent", ProtocolStrings.USER_AGENT + "/" + version.getVersion());
        }
        if (!ListenerUtil.mutListener.listen(67383)) {
            if (language != null) {
                if (!ListenerUtil.mutListener.listen(67382)) {
                    urlConnection.setRequestProperty("Accept-Language", language);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(67384)) {
            urlConnection.setDoOutput(true);
        }
        if (!ListenerUtil.mutListener.listen(67385)) {
            urlConnection.setDoInput(true);
        }
        try {
            OutputStreamWriter osw = new OutputStreamWriter(urlConnection.getOutputStream(), StandardCharsets.UTF_8);
            if (!ListenerUtil.mutListener.listen(67387)) {
                osw.write(body);
            }
            if (!ListenerUtil.mutListener.listen(67388)) {
                osw.close();
            }
            InputStream inputStream = urlConnection.getInputStream();
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            if (!ListenerUtil.mutListener.listen(67389)) {
                inputStream.close();
            }
            return result;
        } finally {
            if (!ListenerUtil.mutListener.listen(67386)) {
                urlConnection.disconnect();
            }
        }
    }

    private void makeTokenResponse(JSONObject p1Result, JSONObject request, IdentityStoreInterface identityStore) throws JSONException, IOException, ThreemaException {
        byte[] token = Base64.decode(p1Result.getString("token"));
        byte[] tokenRespKeyPub = Base64.decode(p1Result.getString("tokenRespKeyPub"));
        /* sign token with our secret key */
        byte[] nonce = new byte[NaCl.NONCEBYTES];
        if (!ListenerUtil.mutListener.listen(67390)) {
            random.nextBytes(nonce);
        }
        byte[] response = identityStore.encryptData(token, nonce, tokenRespKeyPub);
        if (!ListenerUtil.mutListener.listen(67391)) {
            if (response == null) {
                throw new ThreemaException("TM047");
            }
        }
        if (!ListenerUtil.mutListener.listen(67392)) {
            request.put("token", Base64.encodeBytes(token));
        }
        if (!ListenerUtil.mutListener.listen(67393)) {
            request.put("response", Base64.encodeBytes(response));
        }
        if (!ListenerUtil.mutListener.listen(67394)) {
            request.put("nonce", Base64.encodeBytes(nonce));
        }
    }

    @Nullable
    public APIConnector.FetchIdentityResult getFetchResultByIdentity(ArrayList<APIConnector.FetchIdentityResult> results, String identity) {
        if (!ListenerUtil.mutListener.listen(67397)) {
            if (identity != null) {
                if (!ListenerUtil.mutListener.listen(67396)) {
                    {
                        long _loopCounter846 = 0;
                        for (APIConnector.FetchIdentityResult result : results) {
                            ListenerUtil.loopListener.listen("_loopCounter846", ++_loopCounter846);
                            if (!ListenerUtil.mutListener.listen(67395)) {
                                if (identity.equals(result.identity)) {
                                    return result;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public class FetchIdentityResult {

        public String identity;

        public byte[] publicKey;

        /**
         *  @deprecated use {@link #featureMask} instead.
         */
        @Deprecated
        public int featureLevel;

        public int featureMask;

        public int state;

        public int type;
    }

    public class FetchIdentityPrivateResult {

        public String serverGroup;

        public String email;

        public String mobileNo;
    }

    public class MatchIdentityResult {

        public byte[] publicKey;

        public byte[] mobileNoHash;

        public byte[] emailHash;

        public Object refObjectMobileNo;

        public Object refObjectEmail;
    }

    public class CheckBetaResult {

        public boolean success;

        public String error;
    }

    public class CheckLicenseResult {

        public boolean success;

        public String error;

        public String updateMessage;

        public String updateUrl;

        public String logoLightUrl;

        public String logoDarkUrl;
    }

    public class CheckIdentityStatesResult {

        public final int[] states;

        public final int[] types;

        public final String[] identities;

        public final int checkInterval;

        public final Integer[] featureMasks;

        public CheckIdentityStatesResult(int[] states, int[] types, String[] identities, int checkInterval, Integer[] featureMasks) {
            this.states = states;
            this.identities = identities;
            this.types = types;
            this.checkInterval = checkInterval;
            this.featureMasks = featureMasks;
        }
    }

    public class CheckRevocationKeyResult {

        public final boolean isSet;

        public final Date lastChanged;

        public CheckRevocationKeyResult(boolean isSet, Date lastChanged) {
            this.isSet = isSet;
            this.lastChanged = lastChanged;
        }
    }

    public class SetRevocationKeyResult {

        public final boolean success;

        public final String error;

        public SetRevocationKeyResult(boolean success, String error) {
            this.success = success;
            this.error = error;
        }
    }

    public class TurnServerInfo {

        public final String[] turnUrls;

        public final String[] turnUrlsDualStack;

        public final String turnUsername;

        public final String turnPassword;

        public final Date expirationDate;

        public TurnServerInfo(String[] turnUrls, String[] turnUrlsDualStack, String turnUsername, String turnPassword, Date expirationDate) {
            this.turnUrls = turnUrls;
            this.turnUrlsDualStack = turnUrlsDualStack;
            this.turnUsername = turnUsername;
            this.turnPassword = turnPassword;
            this.expirationDate = expirationDate;
        }
    }
}
