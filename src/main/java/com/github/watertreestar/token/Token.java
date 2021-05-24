package com.github.watertreestar.token;

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
}
