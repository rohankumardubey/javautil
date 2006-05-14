/*---------------------------------------------------------------------------*\
  $Id: CombinationClassFilter.java 5596 2005-08-18 15:34:24Z bmc $
  ---------------------------------------------------------------------------
  This software is released under a Berkeley-style license:

  Copyright (c) 2006 Brian M. Clapper. All rights reserved.

  Redistribution and use in source and binary forms are permitted provided
  that: (1) source distributions retain this entire copyright notice and
  comment; and (2) modifications made to the software are prominently
  mentioned, and a copy of the original software (or a pointer to its
  location) are included. The name of the author may not be used to endorse
  or promote products derived from this software without specific prior
  written permission.

  THIS SOFTWARE IS PROVIDED ``AS IS'' AND WITHOUT ANY EXPRESS OR IMPLIED
  WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE IMPLIED WARRANTIES OF
  MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.

  Effectively, this means you can do what you want with the software except
  remove this notice or take advantage of the author's name. If you modify
  the software and redistribute your modified version, you must indicate that
  your version is a modification of the original, and you must provide either
  a pointer to or a copy of the original.
\*---------------------------------------------------------------------------*/

package org.clapper.util.classutil;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>An <tt>OrClassFilter</tt> contains logically ORs other
 * {@link ClassFilter} objects. When its {@link #accept accept()} 
 * method is called, the <tt>OrClassFilter</tt> object passes
 * the class name through the contained filters. The class name is
 * accepted if it is accepted by any one of the contained filters. This
 * class conceptually provides a logical "OR" operator for class name
 * filters.</p>
 *
 * <p>The contained filters are applied in the order they were added to
 * the <tt>OrClassFilter</tt> object. This class's
 * {@link #accept accept()} method stops looping over the contained filters
 * as soon as it encounters one whose <tt>accept()</tt> method returns
 * <tt>true</tt> (implementing a "short-circuited OR" operation.) </p>
 *
 * @see ClassFilter
 * @see OrClassFilter
 * @see NotClassFilter
 * @see ClassFinder
 *
 * @version <tt>$Revision: 5596 $</tt>
 *
 * @author Copyright &copy; 2006 Brian M. Clapper
 */
public class OrClassFilter implements ClassFilter
{
    /*----------------------------------------------------------------------*\
                            Private Data Items
    \*----------------------------------------------------------------------*/

    private List<ClassFilter> filters = new LinkedList<ClassFilter>();

    /*----------------------------------------------------------------------*\
                            Constructor
    \*----------------------------------------------------------------------*/

    /**
     * Construct a new <tt>OrClassFilter</tt> with no contained filters.
     */
    public OrClassFilter()
    {
    }

    /**
     * Construct a new <tt>OrClassFilter</tt> with two contained filters.
     * Additional filters may be added later, via calls to the
     * {@link #addFilter addFilter()} method.
     *
     * @param filters  filters to add
     */
    public OrClassFilter (ClassFilter... filters)
    {
        for (ClassFilter filter : filters)
            addFilter (filter);
    }

    /*----------------------------------------------------------------------*\
                              Public Methods
    \*----------------------------------------------------------------------*/

    /**
     * Add a filter to the set of contained filters.
     *
     * @param filter the <tt>ClassFilter</tt> to add.
     *
     * @return this object, to permit chained calls.
     *
     * @see #removeFilter
     */
    public OrClassFilter addFilter (ClassFilter filter)
    {
        filters.add (filter);
        return this;
    }

    /**
     * Remove a filter from the set of contained filters.
     *
     * @param filter the <tt>ClassFilter</tt> to remove.
     *
     * @see #addFilter
     */
    public void removeFilter (ClassFilter filter)
    {
        filters.remove (filter);
    }

    /**
     * <p>Determine whether a class name is to be accepted or not, based on
     * the contained filters. The class name name is accepted if any
     * one of the contained filters accepts it. This method stops
     * looping over the contained filters as soon as it encounters one
     * whose {@link ClassFilter#accept accept()} method returns
     * <tt>true</tt> (implementing a "short-circuited OR" operation.)</p>
     *
     * <p>If the set of contained filters is empty, then this method
     * returns <tt>true</tt>.</p>
     *
     * @param className  the class name
     *
     * @return <tt>true</tt> if the name matches, <tt>false</tt> if it doesn't
     */
    public boolean accept (String className)
    {
        boolean accepted = false;

        if (filters.size() == 0)
            accepted = true;

        else
        {
            for (ClassFilter filter : filters)
            {
                accepted = filter.accept (className);
                if (accepted)
                    break;
            }
        }

        return accepted;
    }
}