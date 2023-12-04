package com.sauhard.experiment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class Loops_Testing {

	private static long DEFAULT_SIZE = 1000000l;
	private static int NUMBER_OF_ITERATIONS = 50;

	public static void runTest(Optional<Long> size) {
		long sizeToBeUsed;
		List<Integer> dummyList = new ArrayList<>();
		if (size == null)
			sizeToBeUsed = DEFAULT_SIZE;
		sizeToBeUsed = size.map(val -> {
			return val <= 0 ? DEFAULT_SIZE : val;
		}).orElse(DEFAULT_SIZE);
		for (int i = 0; i < sizeToBeUsed; i++) {
			dummyList.add(i);
		}
		System.out.println("Size of each loop " + sizeToBeUsed);
		long totalTimeElapsedForIterator = 0;
		long totalTimeElapsedInRandomAccessForLoopWithSizeKnown = 0;
		long totalTimeElapsedInForLoopThroughIterator = 0;
		long totalTimeElapsedInRandomAccessForLoopWithSizeCalled = 0;

		for (int iteration = 0; iteration < NUMBER_OF_ITERATIONS; iteration++) {
			totalTimeElapsedForIterator += loopThroughIterator(dummyList);
			totalTimeElapsedInRandomAccessForLoopWithSizeKnown += runRandomAccessForLoopWithSizeKnown(dummyList);
			totalTimeElapsedInForLoopThroughIterator += runForLoopThroughIterator(dummyList);
			totalTimeElapsedInRandomAccessForLoopWithSizeCalled += runRandomAccessForLoopWithSizeCalled(dummyList);
		}
		System.out.println(
				"Iterator Elapsed time " + totalTimeElapsedForIterator / (NUMBER_OF_ITERATIONS * 1000) + " milliseconds");
		System.out.println("For each Elapsed time "
				+ totalTimeElapsedInForLoopThroughIterator / (NUMBER_OF_ITERATIONS * 1000) + " milliseconds");
		System.out.println("For index with size called each iteration Elapsed time "
				+ totalTimeElapsedInRandomAccessForLoopWithSizeCalled / (NUMBER_OF_ITERATIONS * 1000) + " milliseconds");
		System.out.println("For index with size known Elapsed time "
				+ totalTimeElapsedInRandomAccessForLoopWithSizeKnown / (NUMBER_OF_ITERATIONS * 1000) + " milliseconds");
	}

	private static long loopThroughIterator(List<Integer> dummyList) {
		long startTime = System.nanoTime();
		long ans = 0;
		Iterator<Integer> itr = dummyList.iterator();
		while (itr.hasNext()) {
			ans += dummyList.get(itr.next());
		}
		long endTime = System.nanoTime();
		return endTime - startTime;
	}

	private static long runRandomAccessForLoopWithSizeCalled(List<Integer> dummyList) {
		long startTime = System.nanoTime();
		long ans = 0;
		for (int i = 0; i < dummyList.size(); i++) {
			ans += dummyList.get(i);
		}
		long endTime = System.nanoTime();
		return endTime - startTime;
	}

	private static long runForLoopThroughIterator(List<Integer> dummyList) {
		long startTime = System.nanoTime();
		long ans = 0;

		for (int val : dummyList) {
			ans += dummyList.get(val);
		}
		long endTime = System.nanoTime();
		return endTime - startTime;
	}

	private static long runRandomAccessForLoopWithSizeKnown(List<Integer> dummyList) {
		long startTime = System.nanoTime();
		long ans = 0;
		int sz = dummyList.size();
		for (int idx = 0; idx < sz; idx++) {
			ans += dummyList.get(idx);
		}
		long endTime = System.nanoTime();
		return endTime - startTime;
	}

}