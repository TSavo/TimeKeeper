package com.cpn.timekeeper;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class TimeKeeperTest {

	@After
	public void reset(){
		TimeKeeper.reset();
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void testCurrentTimeMillis() {
		long actualTime = System.currentTimeMillis();
		long timeKeeperTime = TimeKeeper.currentTimeMillis();
		// allow for up to 2 milliseconds between calls.
		Assert.assertTrue(actualTime == timeKeeperTime || actualTime <= timeKeeperTime + 10);
	}

	@Test
	public void testFixedTime() {
		long fixedTime = 1000;// presumably we're older than that
		TimeKeeper.setFixedTime(fixedTime);
		Assert.assertEquals(fixedTime, TimeKeeper.currentTimeMillis());
		TimeKeeper.reset();
		testCurrentTimeMillis();
	}

	@SuppressWarnings("static-method")
	@Test
	public void testFixedTimeWithOffset() {
		long fixedTime = 1000;// presumably we're older than that
		long timeOffset = 500;
		TimeKeeper.setFixedTime(fixedTime);
		TimeKeeper.setTimeOffset(timeOffset);
		Assert.assertEquals(fixedTime + timeOffset, TimeKeeper.currentTimeMillis());
		timeOffset = -500;
		TimeKeeper.setTimeOffset(timeOffset);
		Assert.assertEquals(fixedTime + timeOffset, TimeKeeper.currentTimeMillis());
	}

	@Test
	public void testForwardAndReverse() {
		long fixedTime = 1000;
		long advanceTime = 500;
		long rewindTime = 250;
		TimeKeeper.setFixedTime(fixedTime);
		TimeKeeper.advanceTime(advanceTime);
		Assert.assertEquals(fixedTime + advanceTime, TimeKeeper.currentTimeMillis());
		TimeKeeper.rewindTime(rewindTime);
		Assert.assertEquals(fixedTime + advanceTime - rewindTime, TimeKeeper.currentTimeMillis());
	}
	
	

}
