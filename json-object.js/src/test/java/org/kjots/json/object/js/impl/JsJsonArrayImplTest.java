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

import org.kjots.json.object.js.JsArray;
import org.kjots.json.object.js.JsEngine;
import org.kjots.json.object.js.JsJsonObjectModule;
import org.kjots.json.object.js.JsObject;
import org.kjots.json.object.shared.JsonArray;
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.impl.JsonArrayImplTestBase;

/**
 * JavaScript JSON Array Implementation Test.
 * <p>
 * Created: 12th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.2
 */
public class JsJsonArrayImplTest extends JsonArrayImplTestBase<Object> {
  /** The JavaScript engine. */
  @Inject @JsEngine
  private Invocable jsEngine;
  
  /** The JavaScript object provider. */
  @Inject @JsObject
  private Provider<Object> jsObjectProvider;
  
  /** The JavaScript array provider. */
  @Inject @JsArray
  private Provider<Object> jsArrayProvider;
  
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
    this.jsArrayProvider = null;
  }
  
  /**
   * Create a JSON object with the given underlying JSON object.
   *
   * @param object The underlying JSON object.
   * @return The JSON object.
   */
  @Override
  protected JsonObject createJsonObject(Object object) {
    return new JsJsonObjectImpl(this.jsEngine, object);
  }

  /**
   * Create a JSON array with the given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @return The JSON array.
   */
  @Override
  protected JsonArray createJsonArray(Object array) {
    return new JsJsonArrayImpl(this.jsEngine, array);
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
   * Create an empty underlying JSON array.
   *
   * @return The empty underlying JSON array.
   */
  @Override
  protected Object createUnderlyingJsonArray() {
    return this.jsArrayProvider.get();
  }
  
  /**
   * Retrieve the length of the given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @return The length of the underlying JSON array.
   * @see #setArrayLength(Object, int)
   */
  @Override
  protected int getArrayLength(Object array) {
    Number arrayLength = this.invokeFunction("getArrayLength", array);
    
    return arrayLength.intValue();
  }
  
  /**
   * Set the length of the given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @param length The length of the underlying JSON array.
   * @see #getArrayLength(Object)
   */
  @Override
  protected void setArrayLength(Object array, int length) {
    this.invokeFunction("setArrayLength", array, length);
  }
  
  /**
   * Retrieve the boolean value of the element at the given index from the
   * given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @return The boolean value of the element.
   * @see #setBooleanElement(Object, int, Boolean)
   */
  @Override
  protected Boolean getBooleanElement(Object array, int elementIndex) {
    return this.invokeFunction("getProperty", array, elementIndex);
  }
  
  /**
   * Set the element with the given name in the given underlying JSON array to
   * the given boolean value.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @param elementValue The boolean value of the element.
   * @see #getBooleanElement(Object, int)
   */
  @Override
  protected void setBooleanElement(Object array, int elementIndex, Boolean elementValue) {
    this.invokeFunction("setProperty", array, elementIndex, elementValue);
  }
  
  /**
   * Retrieve the numeric value of the element at the given index from the
   * given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @return The numeric value of the element.
   * @see #setNumberElement(Object, int, Number)
   */
  @Override
  protected Number getNumberElement(Object array, int elementIndex) {
    return this.invokeFunction("getProperty", array, elementIndex);
  }
  
  /**
   * Set the element with the given name in the given underlying JSON array to
   * the given numeric value.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @param elementValue The numeric value of the element.
   * @see #getNumberElement(Object, int)
   */
  @Override
  protected void setNumberElement(Object array, int elementIndex, Number elementValue) {
    this.invokeFunction("setProperty", array, elementIndex, elementValue);
  }
  
  /**
   * Retrieve the string value of the element at the given index from the
   * given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @return The string value of the element.
   * @see #setStringElement(Object, int, String)
   */
  @Override
  protected String getStringElement(Object array, int elementIndex) {
    return this.invokeFunction("getProperty", array, elementIndex);
  }
  
  /**
   * Set the element with the given name in the given underlying JSON array to
   * the given string value.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @param elementValue The string value of the element.
   * @see #getStringElement(Object, int)
   */
  @Override
  protected void setStringElement(Object array, int elementIndex, String elementValue) {
    this.invokeFunction("setProperty", array, elementIndex, elementValue);
  }
  
  /**
   * Retrieve the object value of the element at the given index from the
   * given underlying JSON array.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @return The object value of the element.
   * @see #setObjectElement(Object, int, Object)
   */
  @Override
  protected Object getObjectElement(Object array, int elementIndex) {
    return this.invokeFunction("getProperty", array, elementIndex);
  }
  
  /**
   * Set the element with the given name in the given underlying JSON array to
   * the given object value.
   *
   * @param array The underlying JSON array.
   * @param elementIndex The index of the element.
   * @param elementValue The object value of the element.
   * @see #getObjectElement(Object, int)
   */
  @Override
  protected void setObjectElement(Object array, int elementIndex, Object elementValue) {
    this.invokeFunction("setProperty", array, elementIndex, elementValue);
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
