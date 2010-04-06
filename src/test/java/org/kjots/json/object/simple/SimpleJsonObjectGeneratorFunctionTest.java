/* 
 * Copyright © 2010 Karl J. Ots.  All Rights Reserved.
 */
package org.kjots.json.object.simple;

import org.junit.Before;

import com.google.inject.Guice;

import org.kjots.json.object.JvmJsonObjectGeneratorFunctionTestBase;

/**
 * Simple JSON Object Generator Function Test.
 * <p>
 * Created: 3rd April 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 */
public class SimpleJsonObjectGeneratorFunctionTest extends JvmJsonObjectGeneratorFunctionTestBase {
  /**
   * Set up the JSON object implementation test.
   */
  @Before
  public void setUp() {
    Guice.createInjector(new SimpleJsonObjectModule());
  }
}