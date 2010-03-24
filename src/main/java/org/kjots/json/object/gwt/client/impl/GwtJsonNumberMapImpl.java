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
package org.kjots.json.object.gwt.client.impl;

import com.google.gwt.core.client.JavaScriptObject;

import org.kjots.json.object.shared.JsonNumberMap;

/**
 * GWT JSON Number Map Implementation.
 * <p>
 * Created: 9th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public class GwtJsonNumberMapImpl extends GwtJsonObjectImpl implements JsonNumberMap {
  /**
   * Construct a new GWT JSON Number Map Implementation.
   *
   * @param jsObject The JavaScript object.
   */
  public GwtJsonNumberMapImpl(JavaScriptObject jsObject) {
    super(jsObject);
  }
  
  /**
   * Retrieve the numeric value of the element with the given key.
   *
   * @param key The key.
   * @return The numeric value.
   * @see #set(String, Number)
   */
  @Override
  public final Number get(String key) {
    return this.getNumberProperty(key);
  }
  
  /**
   * Set the element with the given key to the given numeric value.
   *
   * @param key The key.
   * @param value The numeric value.
   * @see #get(String)
   */
  @Override
  public final void set(String key, Number value) {
    this.setNumberProperty(key, value);
  }
}
