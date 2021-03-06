/* 
 * Copyright © 2010 Karl J. Ots <kjots@kjots.org>
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
package org.kjots.json.object.simple.impl;

import javax.inject.Inject;

import org.kjots.json.object.impl.JvmJsonObjectFactoryImplBase;
import org.kjots.json.object.shared.JsonArray;
import org.kjots.json.object.shared.JsonBooleanArray;
import org.kjots.json.object.shared.JsonBooleanMap;
import org.kjots.json.object.shared.JsonNumberArray;
import org.kjots.json.object.shared.JsonNumberMap;
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectArray;
import org.kjots.json.object.shared.JsonObjectMap;
import org.kjots.json.object.shared.JsonStringArray;
import org.kjots.json.object.shared.JsonStringMap;
import org.kjots.json.object.simple.SimpleJsonObjectGenerator;
import org.kjots.json.object.simple.SimpleJsonValue;

/**
 * Simple JSON Object Factory Implementation.
 * <p>
 * Created: 5th March 2010
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public class SimpleJsonObjectFactoryImpl extends JvmJsonObjectFactoryImplBase {
  /** The JSON object generator. */
  private final SimpleJsonObjectGenerator jsonObjectGenerator;
  
  /**
   * Construct a new  Simple JSON Object Factory Implementation.
   *
   * @param jsonObjectGenerator The JSON object generator.
   */
  @Inject
  public SimpleJsonObjectFactoryImpl(SimpleJsonObjectGenerator jsonObjectGenerator) {
    this.jsonObjectGenerator = jsonObjectGenerator;
  }
  
  /**
   * Create a new JSON object with the given underlying JSON object.
   *
   * @param <T> The type of the JSON object.
   * @param jsonObjectClass The class of the JSON object.
   * @param object The underlying JSON object.
   * @return The JSON object.
   */
  @Override
  @SuppressWarnings("unchecked")
  public final <T extends JsonObject> T createJsonObject(Class<T> jsonObjectClass, Object object) {
    SimpleJsonObjectImpl jsonObjectImpl = this.createStaticJsonObject(jsonObjectClass, (SimpleJsonValue)object);
    if (jsonObjectImpl == null) {
      jsonObjectImpl = this.jsonObjectGenerator.newJsonObjectImpl(jsonObjectClass, (SimpleJsonValue)object);
    }
    
    return (T)jsonObjectImpl;
  }

  /**
   * Create a new JSON object with the given underlying JSON object.
   *
   * @param <T> The type of the JSON object.
   * @param jsonObjectClassName The name of the class of the JSON object.
   * @param object The underlying JSON object.
   * @return The JSON object.
   */
  @Override
  @SuppressWarnings("unchecked")
  public final <T extends JsonObject> T createJsonObject(String jsonObjectClassName, Object object) {
    try {
      return this.createJsonObject((Class<T>)Class.forName(jsonObjectClassName), object);
    }
    catch (ClassNotFoundException cnfe) {
      throw new IllegalArgumentException(jsonObjectClassName, cnfe);
    }
  }
  
  /**
   * Create a new JSON object.
   *
   * @param <T> The type of the JSON object.
   * @param jsonObjectClass The class of the JSON object.
   * @return The JSON object.
   */
  @Override
  public final <T extends JsonObject> T createJsonObject(Class<T> jsonObjectClass) {
    return this.createJsonObject(jsonObjectClass, SimpleJsonValue.createObject());
  }

  /**
   * Create a new JSON object.
   *
   * @param <T> The type of the JSON object.
   * @param jsonObjectClassName The name of the class of the JSON object.
   * @return The JSON object.
   */
  @Override
  public final <T extends JsonObject> T createJsonObject(String jsonObjectClassName) {
    return this.<T>createJsonObject(jsonObjectClassName, SimpleJsonValue.createObject());
  }
  
  /**
   * Create a new JSON array.
   *
   * @param <T> The type of the JSON array.
   * @param jsonArrayClass The class of the JSON array.
   * @return The JSON array.
   */
  @Override
  public final <T extends JsonArray> T createJsonArray(Class<T> jsonArrayClass) {
    return this.createJsonObject(jsonArrayClass, SimpleJsonValue.createArray());
  }
  
  /**
   * Create a new JSON array.
   *
   * @param <T> The type of the JSON array.
   * @param jsonArrayClassName The name of the class of the JSON array.
   * @return The JSON array.
   */
  @Override
  public final <T extends JsonArray> T createJsonArray(String jsonArrayClassName) {
    return this.<T>createJsonObject(jsonArrayClassName, SimpleJsonValue.createArray());
  }
  
  /**
   * Create a new JSON object instance with given class using the given
   * underlying JSON object.
   * <p>
   * This method will only create JSON object instances with statically defined
   * implementations.
   *
   * @param jsonObjectClass The class of the JSON object.
   * @param object The underlying JSON object.
   * @return The JSON object.
   */
  private SimpleJsonObjectImpl createStaticJsonObject(Class<? extends JsonObject> jsonObjectClass, SimpleJsonValue object) {
    if (jsonObjectClass.equals(JsonObject.class)) {
      return new SimpleJsonObjectImpl(object);
    }
    else if (jsonObjectClass.equals(JsonArray.class)) {
      return new SimpleJsonArrayImpl(object);
    }
    else if (jsonObjectClass.equals(JsonBooleanArray.class)) {
      return new SimpleJsonBooleanArrayImpl(object);
    }
    else if (jsonObjectClass.equals(JsonNumberArray.class)) {
      return new SimpleJsonNumberArrayImpl(object);
    }
    else if (jsonObjectClass.equals(JsonStringArray.class)) {
      return new SimpleJsonStringArrayImpl(object);
    }
    else if (jsonObjectClass.equals(JsonObjectArray.class)) {
      return new SimpleJsonObjectArrayImpl<JsonObject>(object);
    }
    else if (jsonObjectClass.equals(JsonBooleanMap.class)) {
      return new SimpleJsonBooleanMapImpl(object);
    }
    else if (jsonObjectClass.equals(JsonNumberMap.class)) {
      return new SimpleJsonNumberMapImpl(object);
    }
    else if (jsonObjectClass.equals(JsonStringMap.class)) {
      return new SimpleJsonStringMapImpl(object);
    }
    else if (jsonObjectClass.equals(JsonObjectMap.class)) {
      return new SimpleJsonObjectMapImpl<JsonObject>(object);
    }
    else {
      return null;
    }
  }
}
