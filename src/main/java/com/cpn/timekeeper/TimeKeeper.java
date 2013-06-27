package com.cpn.timekeeper;

import java.util.Date;

/**
 * A class for dealing with time traveling. You can set a fixed time > -1, and
 * any calls to currentTimeMillis will return that time. Setting it back to -1
 * will make it use the system time again. You can also set an offset, both
 * positive and negative which will be added to the current time, be it system
 * of fixed.
 * 
 * By combining these two functions together, you can make it appear to be any
 * time you want, and then easily advance the time while still keeping it fixed,
 * allowing you to consistently check your code with deterministic time values
 * and simulating intervals of time.
 * 
 * You can also artificially set the time to any time, although this will have no
 * effect if you've set a fixed time, and be reset if you call reset.
 * 
 * Reset will undo and offsetting, fastforwarding, rewinding, or fixing of time.
 * 
 * 
 * @author TSavo
 * 
 */
public class TimeKeeper {

	private static long fixed = -1;
	private static long offset = 0;

	public static long currentTimeMillis() {
		long time;
		if (fixed > -1) {
			time = fixed;
		} else {
			time = System.currentTimeMillis();
		}
		return time + offset;
	}

	public static void setTimeOffset(long anOffset) {
		offset = anOffset;
	}

	public static void setFixedTime(long aFixedTime) {
		fixed = aFixedTime;
	}

	public static long getFixedTime() {
		return fixed;
	}

	public static long getTimeOffset() {
		return offset;
	}

	public static void advanceTime(long anOffset) {
		if (anOffset < 0) {
			throw new IllegalArgumentException("You must advance by a positive amount. Did you mean to set the offset instead?");
		}
		offset += anOffset;
	}

	public static void rewindTime(long anOffset) {
		if (anOffset < 0) {
			throw new IllegalArgumentException("You must rewind by a positive amount. Did you mean to set the offset instead?");
		}
		offset -= anOffset;
	}

	public static void reset() {
		offset = 0;
		fixed = -1;
	}

	public static void setLogicalTime(long anActualTime) {
		offset = anActualTime - System.currentTimeMillis();
	}

	public static Date currentDate() {
		return new Date(currentTimeMillis());
	}

}
