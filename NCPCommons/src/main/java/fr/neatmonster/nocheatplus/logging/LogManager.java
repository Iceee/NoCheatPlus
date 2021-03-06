package fr.neatmonster.nocheatplus.logging;

import java.util.logging.Level;

/**
 * Central access point log manager with a bias towards String messages.
 * @author dev1mc
 *
 */
public interface LogManager {

    /**
     * A stream that skips all messages. It's not registered officially.
     * @return
     */
    public StreamID getVoidStreamID();

    /**
     * This should be a fail-safe direct String-logger, that has the highest probability of working
     * within the default context and rather skips messages instead of failing or scheduling tasks,
     * typically the main application primary thread.
     * @return
     */
    public StreamID getInitStreamID();

    /**
     * Don't use this prefix for custom registrations with StreamID and LoggerID.
     * @return
     */
    public String getDefaultPrefix();

    /**
     * Case-insensitive lookup.
     * @param name
     * @return
     */
    public boolean hasLogger(String name);

    /**
     * A newly created id can be used here (case-insensitive comparison by name). For logging use existing ids always.
     * @param loggerID
     * @return
     */
    public boolean hasLogger(final LoggerID loggerID);

    /**
     * Case-insensitive lookup.
     * @param name
     * @return Returns the registered StreamID or null, if not registered.
     */
    public LoggerID getLoggerID(String name);

    /**
     * Case-insensitive lookup.
     * @param name
     * @return
     */
    public boolean hasStream(String name);

    /**
     * A newly created id can be used here (case-insensitive comparison by name). For logging use existing ids always.
     * @param streamID
     * @return
     */
    public boolean hasStream(StreamID streamID);

    /**
     * Case-insensitive lookup.
     * @param name
     * @return Returns the registered StreamID or null, if not registered.
     */
    public StreamID getStreamID(String name);

    void debug(StreamID streamID, String message);

    void info(StreamID streamID, String message);

    void warning(StreamID streamID, String message);

    void severe(StreamID streamID, String message);

    void log(StreamID streamID, Level level, String message);

    void debug(StreamID streamID, Throwable t);

    void info(StreamID streamID, Throwable t);

    void warning(StreamID streamID, Throwable t);

    void severe(StreamID streamID, Throwable t);

    void log(StreamID streamID, Level level, Throwable t);

}
