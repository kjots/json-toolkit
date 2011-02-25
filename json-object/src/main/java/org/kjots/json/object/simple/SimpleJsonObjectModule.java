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
package org.kjots.json.object.simple;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import org.kjots.json.object.shared.JsonObjectFactory;
import org.kjots.json.object.simple.impl.SimpleJsonObjectFactoryImpl;

/**
 * Simple JSON Object Module.
 * <p>
 * Created: 5th March 2010
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.0
 */
public class SimpleJsonObjectModule extends AbstractModule {
  /**
   * Configure the module.
   */
  @Override
  protected void configure() {
    this.bind(JsonObjectFactory.class).to(SimpleJsonObjectFactoryImpl.class).in(Singleton.class);
    
    this.requestStaticInjection(JsonObjectFactory.class);
  }
}