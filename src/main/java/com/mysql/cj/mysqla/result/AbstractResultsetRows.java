/*
  Copyright (c) 2016, Oracle and/or its affiliates. All rights reserved.

  The MySQL Connector/J is licensed under the terms of the GPLv2
  <http://www.gnu.org/licenses/old-licenses/gpl-2.0.html>, like most MySQL Connectors.
  There are special exceptions to the terms and conditions of the GPLv2 as it is applied to
  this software, see the FOSS License Exception
  <http://www.mysql.com/about/legal/licensing/foss-exception.html>.

  This program is free software; you can redistribute it and/or modify it under the terms
  of the GNU General Public License as published by the Free Software Foundation; version 2
  of the License.

  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
  See the GNU General Public License for more details.

  You should have received a copy of the GNU General Public License along with this
  program; if not, write to the Free Software Foundation, Inc., 51 Franklin St, Fifth
  Floor, Boston, MA 02110-1301  USA

 */

package com.mysql.cj.mysqla.result;

import com.mysql.cj.api.jdbc.result.ResultSetInternalMethods;
import com.mysql.cj.api.mysqla.result.ResultsetRows;
import com.mysql.cj.core.result.Field;
import com.mysql.cj.jdbc.result.ResultSetImpl;

public abstract class AbstractResultsetRows implements ResultsetRows {

    protected final static int BEFORE_START_OF_ROWS = -1;

    /**
     * Field-level metadata from the server. We need this, because it is not
     * sent for each batch of rows, but we need the metadata to unpack the
     * results for each field.
     */
    protected Field[] metadata;

    /**
     * Position in cache of rows, used to determine if we need to fetch more
     * rows from the server to satisfy a request for the next row.
     */
    protected int currentPositionInFetchedRows = BEFORE_START_OF_ROWS;

    protected boolean wasEmpty = false; // we don't know until we attempt to traverse

    /**
     * The result set that we 'belong' to.
     */
    protected ResultSetImpl owner;

    @Override
    public void setOwner(ResultSetImpl rs) {
        this.owner = rs;
    }

    @Override
    public ResultSetInternalMethods getOwner() {
        return this.owner;
    }

    @Override
    public void setMetadata(Field[] metadata) {
        this.metadata = metadata;
    }

    @Override
    public boolean wasEmpty() {
        return this.wasEmpty;
    }

}
