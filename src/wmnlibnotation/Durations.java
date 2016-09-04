
package wmnlibnotation;

/**
 * Collection of basic durations
 * @author Otso Björklund
 */
public class Durations {
    public static final Duration WHOLE = Duration.getDuration(1, 1);
    public static final Duration HALF = Duration.getDuration(1, 2);
    public static final Duration QUARTER = Duration.getDuration(1, 4);
    public static final Duration EIGHT = Duration.getDuration(1, 8);
    public static final Duration SIXTEENTH = Duration.getDuration(1, 16);
    public static final Duration THIRTYSECOND = Duration.getDuration(1, 32);
    public static final Duration SIXTYFOURTH = Duration.getDuration(1, 64);
    
    public static final Duration EIGHT_TRIPLET = Duration.getDuration(1, 12);
    
    private Durations() {
        // Not meant to be instantiated.
        throw new AssertionError();
    }
}