/* 
 * Copyright © 2009 Karl J. Ots <kjots@kjots.org>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kjots.json.object.js.impl;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.script.Invocable;
import javax.script.ScriptException;

import com.google.inject.Guice;

import org.junit.After;
import org.junit.Before;

import org.kjots.json.object.js.JsEngine;
import org.kjots.json.object.js.JsJsonObjectModule;
import org.kjots.json.object.js.JsObject;
import org.kjots.json.object.shared.JsonStringMap;
import org.kjots.json.object.shared.impl.JsonStringMapImplTestBase;

/**
 * JavaScript JSON String Array Implementation Test.
 * <p>
 * Created: 12th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.2
 */
public class JsJsonStringMapImplTest extends JsonStringMapImplTestBase<Object> {
  /** The JavaScript engine. */
  @Inject @JsEngine
  private Invocable jsEngine;
  
  /** The JavaScript object provider. */
  @Inject @JsObject
  private Provider<Object> jsObjectProvider;
  
  /**
   * Set up the JSON object implementation test.
   */
  @Before
  public void setUp() {
    Guice.createInjector(new JsJsonObjectModule()).injectMembers(this);
  }
  
  /**
   * Tear down the JSON object implementation test.
   */
  @After
  public void tearDown() {
    this.jsEngine = null;
    this.jsObjectProvider = null;
  }
  
  /**
   * Create a JSON string map with the given underlying JSON object.
   *
   * @param object The underlying JSON object.
   * @return The JSON string map.
   */
  @Override
  protected JsonStringMap createJsonStringMap(Object object) {
    return new JsJsonStringMapImpl(this.jsEngine, object);
  }

  /**
   * Create an empty underlying JSON object.
   *
   * @return The empty underlying JSON object.
   */
  @Override
  protected Object createUnderlyingJsonObject() {
    return this.jsObjectProvider.get();
  }
  
  /**
   * Retrieve the string value of the property with the given name from the
   * given underlying JSON object.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @return The string value of the property.
   * @see #setStringProperty(Object, String, String)
   */
  @Override
  protected String getStringProperty(Object object, String propertyName) {
    return this.invokeFunction("getProperty", object, propertyName);
  }
  
  /**
   * Set the property with the given name in the given underlying JSON object
   * to the given string value.
   *
   * @param object The underlying JSON object.
   * @param propertyName The name of the property.
   * @param propertyValue The string value of the property.
   * @see #getStringProperty(Object, String)
   */
  @Override
  protected void setStringProperty(Object object, String propertyName, String propertyValue) {
    this.invokeFunction("setProperty", object, propertyName, propertyValue);
  }
  
  /**
   * Invoke the function with the given name and arguments and return the
   * result.
   *
   * @param <T> The type of the result.
   * @param name The name of the function.
   * @param args The arguments.
   * @return The result.
   */
  @SuppressWarnings("unchecked")
  private <T> T invokeFunction(String name, Object... args) {
    try {
      return (T)this.jsEngine.invokeFunction(name, args);
    }
    catch (ScriptException se) {
      throw new IllegalStateException(se);
    }
    catch (NoSuchMethodException nsme) {
      throw new IllegalStateException(nsme);
    }
  }
}
