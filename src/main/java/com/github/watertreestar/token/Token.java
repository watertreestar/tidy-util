package com.github.watertreestar.token;

import com.github.watertreestar.*;
import com.github.watertreestar.text.TextUtil;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A tool to generate a string with an id and optionally a list of string as payload,and encrypt the string so that it can
 * be sent through the wire
 * <p>
 * Like Java-Web-Token,this token play a same role to transfer some private info can be decode
 */
public class Token {
    public enum Life {
        /**
         * very short life token that live for only 1 min
         */
        ONE_MIN(60),
        /**
         * short life token live for 1 hour
         */
        SHORT(60 * 60),
        ONE_HOUR(60 * 60),
        /**
         * Normal life token live for 1 day
         */
        NORMAL(60 * 60 * 24),
        ONE_DAY(60 * 60 * 24),
        ONE_WEEK(60 * 60 * 24 * 7),
        THIRTY_DAYS(60 * 60 * 24 * 30),
        /**
         * Long life token live for 90 days
         */
        LONG(60 * 60 * 24 * 90),
        NINETY_DAYS(60 * 60 * 24 * 90),
        /**
         * Never expire token
         */
        FOREVER(-1);

        private long seconds;

        Life(long seconds) {
            this.seconds = seconds;
        }

        /**
         * Return the due time in time millis.
         * <p>
         * Note `0` or negative number means never due
         *
         * @return the due timestamp of this token life from now on
         */
        public long due() {
            return due(seconds);
        }

        static long due(long seconds) {
            if (seconds <= 0) {
                return -1;
            }
            long now = System.currentTimeMillis();
            long period = seconds * 1000;
            return now + period;
        }
    }

    /**
     * Identity the token
     */
    private String id;

    /**
     * A timestamp of expire time
     */
    private long due;

    /**
     * Payload of this token
     */
    private List<String> payload = new ArrayList<String>();

    /**
     * Container for consumed token
     */
    private final static Map<String, String> consumedToken = new ConcurrentHashMap<>();

    /**
     * Return the ID of the token
     *
     * @return the token ID
     */
    public String id() {
        return id;
    }

    /**
     * Return the payload of the token
     *
     * @return the token payload
     */
    public List<String> payload() {
        return CollectionUtil.copyOf(payload);
    }

    /**
     * Returns the first payload or null if no payload found
     *
     * @return the first payload for `null`
     */
    public String firstPayload() {
        return payload.isEmpty() ? null : payload.get(0);
    }

    /**
     * Returns a payload at `index` or `null` if no payload found
     * there
     *
     * @param index the index to fetch the payload
     * @return the payload at the position or `null` if not available
     */
    public String payload(int index) {
        return payload.size() > index ? payload.get(index) : null;
    }

    /**
     * Alias of {@link #expired()}
     *
     * @return `true` if the token {@link #expired() is expired}
     */
    public boolean isExpired() {
        return expired();
    }

    public boolean expired() {
        return due > 0 && due <= SystemClock.now();
    }

    /**
     * Alias of {@link #isEmpty()}
     *
     * @return if the token is empty
     */
    public boolean empty() {
        return isEmpty();
    }

    /**
     * Check if the token contains an ID or not
     *
     * @return {@code true} if the token contains a ID
     */
    public boolean isEmpty() {
        return StringUtil.isBlank(id);
    }

    /**
     * Alias of {@link #consumed()}
     *
     * @return `true` if the token is {@link #consumed() is consumed}
     */
    public boolean isConsumed() {
        return consumed();
    }

    /**
     * Check if the token is consumed or not
     *
     * @return {@code true} if the token {@link #consume() marked as consumed}
     */
    public boolean consumed() {
        return consumedToken.get("auth-tk-consumed-" + (id + due)) != null;
    }

    /**
     * Mark a token to be consumed
     */
    public void consume() {
        consumedToken.put("auth-tk-consumed-" + (id + due), "true");
    }

    /**
     * Alias of {@link #isValid()}
     *
     * @return `true` if the token {@link #isValid() is valid}
     */
    public boolean valid() {
        return isValid();
    }

    /**
     * Check if the token is valid.
     * <p>
     * A token is considered to be valid when none of the following criteria met:
     * * token {@link #isEmpty() is empty}
     * * token {@link #consumed() is consumed}
     * * token {@link #expired() is expired}
     *
     * @return
     */
    public boolean isValid() {
        return !isEmpty() && !expired() && !consumed();
    }

    /**
     * Check if the token is NOT valid
     *
     * @return `true` if the token is not {@link #isValid()}
     */
    public boolean notValid() {
        return !isValid();
    }

    @Override
    public int hashCode() {
        return HashCodeUtil.hashCode(id, due, payload);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj instanceof Token) {
            Token that = (Token) obj;
            return Objects.equals(that.id, this.id) && that.due == this.due && Objects.equals(that.payload, this.payload);
        }

        return false;
    }

    @Override
    public String toString() {
        return TextUtil.format("{id: %s, expired: %s, due: %s, payload: %s", id, expired(), due, payload);
    }

    /**
     * This method is deprecated. Please use {@link #generateToken(byte[], String, String...)} instead
     * <p>
     * Generate a token string with secret key, ID and optionally payloads
     *
     * @param secret  the secret to encrypt to token string
     * @param oid     the ID of the token (could be customer ID etc)
     * @param payload the payload optionally indicate more information
     * @return an encrypted token string that is expiring in {@link Life#SHORT} time period
     */
    @Deprecated
    public static String generateToken(String secret, String oid, String... payload) {
        return generateToken(secret, Life.SHORT, oid, payload);
    }

    /**
     * Generate a token string with secret key, ID and optionally payloads
     *
     * @param secret  the secret to encrypt to token string
     * @param oid     the ID of the token (could be customer ID etc)
     * @param payload the payload optionally indicate more information
     * @return an encrypted token string that is expiring in {@link Life#SHORT} time period
     */
    public static String generateToken(byte[] secret, String oid, String... payload) {
        return generateToken(secret, Life.SHORT, oid, payload);
    }

    /**
     * This method is deprecated, please use {@link #generateToken(byte[], Life, String, String...)} instead
     * <p>
     * Generate a token string with secret key, ID and optionally payloads
     *
     * @param secret  the secret to encrypt to token string
     * @param tl      the expiration of the token
     * @param oid     the ID of the token (could be customer ID etc)
     * @param payload the payload optionally indicate more information
     * @return an encrypted token string that is expiring in {@link Life#SHORT} time period
     */
    @Deprecated
    public static String generateToken(String secret, Life tl, String oid, String... payload) {
        return generateToken(secret, tl.seconds, oid, payload);
    }

    /**
     * Generate a token string with secret key, ID and optionally payloads
     *
     * @param secret  the secret to encrypt to token string
     * @param tl      the expiration of the token
     * @param oid     the ID of the token (could be customer ID etc)
     * @param payload the payload optionally indicate more information
     * @return an encrypted token string that is expiring in {@link Life#SHORT} time period
     */
    public static String generateToken(byte[] secret, Life tl, String oid, String... payload) {
        return generateToken(secret, tl.seconds, oid, payload);
    }

    /**
     * This method is deprecated. please use {@link #generateToken(byte[], long, String, String...)} instead
     * <p>
     * Generate a token string with secret key, ID and optionally payloads
     *
     * @param secret  the secret to encrypt to token string
     * @param seconds the expiration of the token in seconds
     * @param oid     the ID of the token (could be customer ID etc)
     * @param payload the payload optionally indicate more information
     * @return an encrypted token string that is expiring in {@link Life#SHORT} time period
     */
    @Deprecated
    public static String generateToken(String secret, long seconds, String oid, String... payload) {
        return generateToken(secret.getBytes(StandardCharsets.UTF_8), seconds, oid, payload);
    }

    /**
     * Generate a token string with secret key, ID and optionally payloads
     *
     * @param secret  the secret to encrypt to token string
     * @param seconds the expiration of the token in seconds
     * @param oid     the ID of the token (could be customer ID etc)
     * @param payload the payload optionally indicate more information
     * @return an encrypted token string that is expiring in {@link Life#SHORT} time period
     */
    public static String generateToken(byte[] secret, long seconds, String oid, String... payload) {
        long due = Life.due(seconds);
        List<String> l = new ArrayList<String>(2 + payload.length);
        l.add(oid);
        l.add(String.valueOf(due));
        l.addAll(CollectionUtil.listOf(payload));
        String s = StringUtil.join("|", l);
        return CryptoUtil.encryptAES(s, secret);
    }

    /**
     * This method is deprecated. Please use {@link #parseToken(byte[], String)} instead
     * <p>
     * Parse a token string into token object
     *
     * @param token  the token string
     * @param secret the secret to decrypt the token string
     * @return a token instance parsed from the string
     */
    @Deprecated
    public static Token parseToken(String secret, String token) {
        return parseToken(secret.getBytes(StandardCharsets.UTF_8), token);
    }

    /**
     * Parse a token string into token object
     *
     * @param token  the token string
     * @param secret the secret to decrypt the token string
     * @return a token instance parsed from the string
     */
    public static Token parseToken(byte[] secret, String token) {
        Token tk = new Token();
        if (StringUtil.isBlank(token)) return tk;
        String s = "";
        try {
            s = CryptoUtil.decryptAES(token, secret);
        } catch (Exception e) {
            return tk;
        }
        String[] sa = s.split("\\|");
        if (sa.length < 2) return tk;
        tk.id = sa[0];
        try {
            tk.due = Long.parseLong(sa[1]);
            if (tk.expired()) {
                return tk;
            }
        } catch (Exception e) {
            tk.due = SystemClock.now() - 1000 * 60 * 60 * 24;
            return tk;
        }
        if (sa.length > 2) {
            sa = Arrays.copyOfRange(sa, 2, sa.length);
            tk.payload.addAll(CollectionUtil.listOf(sa));
        }
        return tk;
    }

    /**
     * This method is deprecated. Please use {@link #isTokenValid(byte[], String, String)} instead
     * <p>
     * Check if a string is a valid token
     *
     * @param secret the secret to decrypt the string
     * @param oid    the ID supposed to be encapsulated in the token
     * @param token  the token string
     * @return {@code true} if the token is valid
     */
    @Deprecated
    @SuppressWarnings("unused")
    public static boolean isTokenValid(String secret, String oid, String token) {
        return isTokenValid(secret.getBytes(StandardCharsets.UTF_8), oid, token);
    }

    /**
     * Check if a string is a valid token
     *
     * @param secret the secret to decrypt the string
     * @param oid    the ID supposed to be encapsulated in the token
     * @param token  the token string
     * @return {@code true} if the token is valid
     */
    @SuppressWarnings("unused")
    public static boolean isTokenValid(byte[] secret, String oid, String token) {
        if (StringUtil.anyBlank(oid, token)) {
            return false;
        }
        String s = CryptoUtil.decryptAES(token, secret);
        String[] sa = s.split("\\|");
        if (sa.length < 2) return false;
        if (!StringUtil.isEqual(oid, sa[0])) return false;
        try {
            long due = Long.parseLong(sa[1]);
            return (due < 1 || due > System.currentTimeMillis());
        } catch (Exception e) {
            return false;
        }
    }


}
