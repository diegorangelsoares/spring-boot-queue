package com.diego.cadastro.redis;

import javax.websocket.Session;

public interface ExpiringSession extends Session {

    /**
     * Gets the time when this session was created in milliseconds since midnight of
     * 1/1/1970 GMT.
     *
     * @return the time when this session was created in milliseconds since midnight of
     * 1/1/1970 GMT.
     */
    long getCreationTime();

    /**
     * Sets the last accessed time in milliseconds since midnight of 1/1/1970 GMT.
     *
     * @param lastAccessedTime the last accessed time in milliseconds since midnight of
     * 1/1/1970 GMT
     */
    void setLastAccessedTime(long lastAccessedTime);

    /**
     * Gets the last time this {@link Session} was accessed expressed in milliseconds
     * since midnight of 1/1/1970 GMT.
     *
     * @return the last time the client sent a request associated with the session
     * expressed in milliseconds since midnight of 1/1/1970 GMT
     */
    long getLastAccessedTime();

    /**
     * Sets the maximum inactive interval in seconds between requests before this session
     * will be invalidated. A negative time indicates that the session will never timeout.
     *
     * @param interval the number of seconds that the {@link Session} should be kept alive
     * between client requests.
     */
    void setMaxInactiveIntervalInSeconds(int interval);

    /**
     * Gets the maximum inactive interval in seconds between requests before this session
     * will be invalidated. A negative time indicates that the session will never timeout.
     *
     * @return the maximum inactive interval in seconds between requests before this
     * session will be invalidated. A negative time indicates that the session will never
     * timeout.
     */
    int getMaxInactiveIntervalInSeconds();

    /**
     * Returns true if the session is expired.
     *
     * @return true if the session is expired, else false.
     */
    boolean isExpired();

}