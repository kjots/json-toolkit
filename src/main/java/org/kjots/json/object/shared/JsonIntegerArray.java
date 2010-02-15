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
package org.kjots.json.object.shared;

/**
 * JSON Integer Array.
 * <p>
 * Created: 7th November 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public interface JsonIntegerArray extends JsonArray {
  /**
   * Retrieve the integer value of the element at the given index.
   *
   * @param index The index.
   * @return The integer value.
   * @see #set(int, int)
   */
  public int get(int index);
  
  /**
   * Set the element at the given index to the given integer value.
   *
   * @param index The index.
   * @param value The integer value.
   * @see #get(int)
   */
  public void set(int index, int value);
  
  /**
   * Insert the given integer value at the given index.
   *
   * @param index The index.
   * @param value The integer value.
   */
  public void insert(int index, int value);
}