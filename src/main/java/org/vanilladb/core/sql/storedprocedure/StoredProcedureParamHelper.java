/*******************************************************************************
 * Copyright 2016, 2017 vanilladb.org contributors
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
 *******************************************************************************/
package org.vanilladb.core.sql.storedprocedure;

import org.vanilladb.core.remote.storedprocedure.SpResultSet;
import org.vanilladb.core.sql.Schema;

public abstract class StoredProcedureParamHelper {

	private boolean isCommitted = true;
	private boolean isReadOnly = false;

	/**
	 * Prepare parameters for this stored procedure.
	 * 
	 * @param pars
	 *            An object array contains all parameter for this stored
	 *            procedure.
	 */
	public abstract void prepareParameters(Object... pars);

	public abstract SpResultSet createResultSet();

	public static StoredProcedureParamHelper DefaultParamHelper() {
		return new StoredProcedureParamHelper() {

			@Override
			public void prepareParameters(Object... pars) {
				// do nothing
			}

			@Override
			public SpResultSet createResultSet() {
				// Return the result
				Schema sch = new Schema();
				SpResultRecord rec = new SpResultRecord();
				return new SpResultSet(isCommitted(), sch, rec);
			}
		};
	}
	
	protected void setReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}

	void setCommitted(boolean b) {
		isCommitted = b;
	}
	
	public boolean isReadOnly() {
		return isReadOnly;
	}

	public boolean isCommitted() {
		return isCommitted;
	}
}
