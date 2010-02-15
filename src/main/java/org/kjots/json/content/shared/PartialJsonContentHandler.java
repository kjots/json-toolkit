/*
 * Copyright © 2010 Space-Time Research Pty. Ltd.  All Rights Reserved.
 */
package org.kjots.json.content.shared;

/**
 * Partial JSON Content Handler.
 * <p>
 * This class implements {@link JsonContentHandler} and uses assertions to
 * cause every method to fail (if assertions are enabled).
 * <p>
 * It is intended that this class should be used as the base for classes that
 * only concern themselves with a part of the {@link JsonContentHandler}
 * contract.  Such classes would coordinate with other partial implementations
 * to provide a complete {@link JsonContentHandler} implementation.  By
 * extending this class, these partial implementations need only override the
 * methods that relate to their concern - under correct behaviour, the other
 * methods will never be invoked.
 * <p>
 * For example, a class that only concerns itself with the root JSON context
 * (i.e. the context directly after the invocation of {@link JsonContentHandler#startJson()})
 * knows that under normal circumstances the {@link {@link JsonContentHandler#primitive(Object)}
 * method will never be invoked - thus such a class need not provide an
 * implementation of that method.
 * <p>
 * If an unimplemented method is invoked (assuming that the implementing class
 * is correctly overriding every method it is supposed to), that indicates an
 * error in the generator of JSON content events, and the assertions feature of
 * the Java language may be used to trap such behaviour.
 * <p>
 * Created: 15th February 2010.
 *
 * @author <a href="mailto:karl.ots@str.com.au">Karl J. Ots &lt;karl.ots@str.com.au&gt;</a>
 */
public abstract class PartialJsonContentHandler implements JsonContentHandler {
  /**
   * Handle the start of the JSON content.
   */
  @Override
  public void startJson() {
    assert false : "Method not implemented";
  }
  
  /**
   * Handle the end of the JSON content.
   */
  @Override
  public void endJson() {
    assert false : "Method not implemented";
  }
  
  /**
   * Handle the start of a JSON object.
   */
  @Override
  public void startObject() {
    assert false : "Method not implemented";
  }
  
  /**
   * Handle the end of a JSON object.
   */
  @Override
  public void endObject() {
    assert false : "Method not implemented";
  }
  
  /**
   * Handle the start of a JSON array.
   */
  @Override
  public void startArray() {
    assert false : "Method not implemented";
  }
  
  /**
   * Handle the end of a JSON array.
   */
  @Override
  public void endArray() {
    assert false : "Method not implemented";
  }
  
  /**
   * Handle the name of a member of a JSON object.
   *
   * @param name The name of the member.
   */
  @Override
  public void memberName(String name) {
    assert false : "Method not implemented";
  }
  
  /**
   * Handle a JSON primitive.
   *
   * @param value The value of the JSON primitive.
   */
  @Override
  public void primitive(Object value) {
    assert false : "Method not implemented";
  }
}
