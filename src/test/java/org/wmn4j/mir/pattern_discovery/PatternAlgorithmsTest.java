/*
 * Copyright 2018 Otso Björklund.
 * Distributed under the MIT license (see LICENSE.txt or https://opensource.org/licenses/MIT).
 */
package org.wmn4j.mir.pattern_discovery;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.wmn4j.mir.pattern_discovery.NoteEventVector;
import org.wmn4j.mir.pattern_discovery.PatternAlgorithms;
import org.wmn4j.mir.pattern_discovery.PointSet;
import org.wmn4j.mir.pattern_discovery.TEC;

/**
 *
 * @author Otso Björklund
 */
public class PatternAlgorithmsTest {

	public PatternAlgorithmsTest() {
	}

	private List<NoteEventVector> getDataset() {
		final List<NoteEventVector> dataset = new ArrayList<>();
		final double[] components1 = { 3, 2 };
		dataset.add(new NoteEventVector(components1));

		final double[] components2 = { 2, 3 };
		dataset.add(new NoteEventVector(components2));

		final double[] components3 = { 2, 2 };
		dataset.add(new NoteEventVector(components3));

		final double[] components4 = { 2, 1 };
		dataset.add(new NoteEventVector(components4));

		final double[] components5 = { 1, 1 };
		dataset.add(new NoteEventVector(components5));

		final double[] components6 = { 1, 3 };
		dataset.add(new NoteEventVector(components6));

		return dataset;
	}

	private boolean tecsAreCorrect(List<TEC> tecs) {
		return tecs.size() == 4;
	}

	@Test
	public void testComputeTecs() {
		final List<TEC> tecs = PatternAlgorithms.computeTecs(new PointSet(getDataset()));
		for (TEC tec : tecs) {
			System.out.println(tec);
		}
		assertTrue(tecsAreCorrect(tecs));
	}
}
