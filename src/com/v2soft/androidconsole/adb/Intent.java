/*
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */
package com.v2soft.androidconsole.adb;

import java.util.Map;

/**
 * 
 * @author vshcryabets@gmail.com
 *
 */
public class Intent {
	private String mAction;
	private Map<String, Object> mExtras;
	private String mComponent;
	private String mCategory;
	
	public Intent(String action, Map<String, Object> extras, String component, String category) {
		mAction = action;
		mExtras = extras;
		mCategory = category;
		mComponent = component;
	}

	public String getAction() {
		return mAction;
	}

	public void setAction(String action) {
		this.mAction = action;
	}

	public Map<String, Object> getExtras() {
		return mExtras;
	}

	public void setExtras(Map<String, Object> extras) {
		this.mExtras = extras;
	}

	public String getComponent() {
		return mComponent;
	}

	public void setComponent(String component) {
		this.mComponent = component;
	}

	public String getCategory() {
		return mCategory;
	}

	public void setCategory(String category) {
		this.mCategory = category;
	}

	/**
	 * 
	 * @return returns string for am tool
	 */
	public String getAdbString() {
		StringBuilder builder = new StringBuilder();
		builder.append("-a ");
		builder.append(mAction);
		
		if ( mCategory != null && mCategory.length() > 0 ) {
			builder.append(" -c ");
			builder.append(mCategory);
		}

		if ( mComponent != null && mComponent.length() > 0 ) {
			builder.append(" -n ");
			builder.append(mComponent);
		}
		
		return builder.toString();
	}
}
