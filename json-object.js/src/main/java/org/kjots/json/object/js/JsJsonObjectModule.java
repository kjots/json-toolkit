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
package org.kjots.json.object.js;

import javax.inject.Singleton;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import org.kjots.json.object.js.impl.JsJsonObjectFactoryImpl;
import org.kjots.json.object.js.impl.JsJsonObjectImpl;
import org.kjots.json.object.shared.JsonObjectFactory;

/**
 * JavaScript JSON Object Module.
 * <p>
 * Created: 12th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.2
 */
public class JsJsonObjectModule extends AbstractModule {
  /**
   * Provide the JavaScript engine.
   *
   * @return The JavaScript engine.
   */
  @Provides @JsEngine @Singleton
  public Invocable provideJsEngine() {
    ScriptEngine jsEngine = new ScriptEngineManager().getEngineByName("JavaScript");
    
    try {
      jsEngine.eval("function newObject() { return {}; }");
      jsEngine.eval("function newArray() { return []; }");
      jsEngine.eval("function isArray(jsObject) { return jsObject instanceof Array; }");
      jsEngine.eval("function getArrayLength(jsObject) { return jsObject.length; }");
      jsEngine.eval("function setArrayLength(jsObject, length) { jsObject.length = length; }");
      jsEngine.eval("function getPropertyNames(jsObject) { var propertyNames = []; for(var propertyName in jsObject) { propertyNames.push(propertyName); }; return propertyNames; }");
      jsEngine.eval("function getPropertyType(jsObject, propertyName) { return typeof jsObject[propertyName]; }");
      jsEngine.eval("function getProperty(jsObject, propertyName) { return jsObject[propertyName]; }");
      jsEngine.eval("function setProperty(jsObject, propertyName, propertyValue) { jsObject[propertyName] = propertyValue; }");
      jsEngine.eval("function deleteProperty(jsObject, propertyName) { delete jsObject[propertyName]; }");
      jsEngine.eval("function insertElement(jsObject, index, value) { jsObject.splice(index, 0, value); }");
      jsEngine.eval("function prependElement(jsObject, value) { jsObject.unshift(value); }");
      jsEngine.eval("function appendElement(jsObject, value) { jsObject.push(value); }");
      jsEngine.eval("function removeElements(jsObject, index, count) { jsObject.splice(index, count); }");
    }
    catch (ScriptException se) {
      throw new IllegalStateException(se);
    }
    
    return (Invocable)jsEngine;
  }
  
  /**
   * Provide a JavaScript object.
   *
   * @param jsEngine The JavaScript engine.
   * @return The JavaScript object.
   */
  @Provides @JsObject
  public Object provideJsObject(@JsEngine Invocable jsEngine) {
    try {
      return jsEngine.invokeFunction("newObject");
    }
    catch (ScriptException se) {
      throw new IllegalStateException(se);
    }
    catch (NoSuchMethodException nsme) {
      throw new IllegalStateException(nsme);
    }
  }
  
  /**
   * Provide a JavaScript array.
   *
   * @param jsEngine The JavaScript engine.
   * @return The JavaScript array.
   */
  @Provides @JsArray
  public Object provideJsArray(@JsEngine Invocable jsEngine) {
    try {
      return jsEngine.invokeFunction("newArray");
    }
    catch (ScriptException se) {
      throw new IllegalStateException(se);
    }
    catch (NoSuchMethodException nsme) {
      throw new IllegalStateException(nsme);
    }
  }
  
  /**
   * Configure the module.
   */
  @Override
  protected void configure() {
    this.bind(JsJsonObjectGenerator.class).in(Singleton.class);
    this.bind(JsonObjectFactory.class).to(JsJsonObjectFactoryImpl.class).in(Singleton.class);
    
    this.requestStaticInjection(JsonObjectFactory.class);
    this.requestStaticInjection(JsJsonObjectImpl.class);
  }
}
