/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wmn4j.analysis.harmony;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.wmn4j.notation.elements.Durational;
import org.wmn4j.notation.elements.Key;
import org.wmn4j.notation.elements.PitchClass;

/**
 * Implements Krumhansl-Schmuckler algorithm.
 *
 * @author Otso Björklund
 */
public class KSKeyAnalyzer implements KeyAnalyzer {

	private final Map<Key, PCProfile> keyProfiles;

	/**
	 * Constructor.
	 */
	public KSKeyAnalyzer() {
		this.keyProfiles = new HashMap<>();
		this.readKeyProfiles();
	}

	/**
	 * Returns the most fitting key.
	 *
	 * @param durationals the durationals for which the key is computed
	 * @return the most fitting key
	 */
	public Key analyzeKey(List<Durational> durationals) {
		final PCProfile profile = PCProfile.getDurationWeightedProfile();
		durationals.forEach(durational -> profile.add(durational));

		Key bestKey = Key.C_MAJOR;
		double maxCorrelation = -2.0;

		for (Key key : this.keyProfiles.keySet()) {
			final double correlation = PCProfile.correlation(this.keyProfiles.get(key), profile);

			if (correlation > maxCorrelation) {
				maxCorrelation = correlation;
				bestKey = key;
			}
		}

		return bestKey;
	}

	private void readKeyProfiles() {

		final Map<String, Key> keyStrings = new HashMap<>();
		for (Key key : Key.values()) {
			keyStrings.put(key.toString(), key);
		}

		try {
			final File keyProfilesFile = new File(KSKeyAnalyzer.class.getResource("KSKeyProfiles.csv").getPath());
			keyProfilesFile.setReadOnly();

			final BufferedReader br = new BufferedReader(new FileReader(keyProfilesFile));
			String line = br.readLine();

			while (line != null && !line.isEmpty()) {
				if (line.charAt(0) != '#') {
					final String[] lineContents = line.split(",");
					final String cleanedKeyString = lineContents[0].trim();
					final Key key = keyStrings.get(cleanedKeyString);

					final List<Double> values = new ArrayList<>();
					for (int i = 1; i < lineContents.length; ++i) {
						final String cleanedString = lineContents[i].trim();
						values.add(Double.parseDouble(cleanedString));
					}

					final PCProfile profile = createPCProfile(values);
					this.keyProfiles.put(key, profile);
				}

				line = br.readLine();
			}

			br.close();

		} catch (final Exception e) {
			System.out.println(e);
		}
	}

	private PCProfile createPCProfile(List<Double> values) {
		final PCProfile profile = new PCProfile();
		int i = 0;

		for (PitchClass pc : PitchClass.values()) {
			profile.setValue(pc, values.get(i++));
		}

		return profile;
	}
}
