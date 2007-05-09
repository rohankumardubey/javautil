/*---------------------------------------------------------------------------*\
  $Id$
  ---------------------------------------------------------------------------
  This software is released under a BSD-style license:

  Copyright (c) 2004-2007 Brian M. Clapper. All rights reserved.

  Redistribution and use in source and binary forms, with or without
  modification, are permitted provided that the following conditions are
  met:

  1.  Redistributions of source code must retain the above copyright notice,
      this list of conditions and the following disclaimer.

  2.  The end-user documentation included with the redistribution, if any,
      must include the following acknowlegement:

        "This product includes software developed by Brian M. Clapper
        (bmc@clapper.org, http://www.clapper.org/bmc/). That software is
        copyright (c) 2004-2007 Brian M. Clapper."

      Alternately, this acknowlegement may appear in the software itself,
      if wherever such third-party acknowlegements normally appear.

  3.  Neither the names "clapper.org", "clapper.org Java Utility Library",
      nor any of the names of the project contributors may be used to
      endorse or promote products derived from this software without prior
      written permission. For written permission, please contact
      bmc@clapper.org.

  4.  Products derived from this software may not be called "clapper.org
      Java Utility Library", nor may "clapper.org" appear in their names
      without prior written permission of Brian M.a Clapper.

  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
  MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN
  NO EVENT SHALL BRIAN M. CLAPPER BE LIABLE FOR ANY DIRECT, INDIRECT,
  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
\*---------------------------------------------------------------------------*/

package org.clapper.util.scripting;

import junit.framework.TestCase;

/**
 *
 */
public class  ScriptFrameworkTypeTest extends TestCase
{
    public  ScriptFrameworkTypeTest(String testName)
    {
        super(testName);
    }

    public void testGetTypeFromString() throws Exception
    {
        ScriptFrameworkType type;

        type = ScriptFrameworkType.getTypeFromString("javax_script");
        assertNotNull("javax_script didn't parse", type);
        assertEquals("javax_script didn't parse",
                     type, ScriptFrameworkType.JAVAX_SCRIPT);

        type = ScriptFrameworkType.getTypeFromString("javax.script");
        assertNotNull("javax.script didn't parse", type);
        assertEquals("javax.script didn't parse",
                     type, ScriptFrameworkType.JAVAX_SCRIPT);

        type = ScriptFrameworkType.getTypeFromString("JAVAX.SCRIPT");
        assertNotNull("JAVAX.SCRIPT didn't parse", type);
        assertEquals("JAVAX.SCRIPT didn't parse",
                     type, ScriptFrameworkType.JAVAX_SCRIPT);

        type = ScriptFrameworkType.getTypeFromString("bsf");
        assertNotNull("bsf didn't parse", type);
        assertEquals("bsf didn't parse correctly",
                     type, ScriptFrameworkType.BSF);

        type = ScriptFrameworkType.getTypeFromString("BSF");
        assertNotNull("bsf didn't parse", type);
        assertEquals("bsf didn't parse correctly",
                     type, ScriptFrameworkType.BSF);
        
        type = ScriptFrameworkType.getTypeFromString("foo");
        assertNull("Huh? Got value " + type + " for 'foo'", type);
    }
}